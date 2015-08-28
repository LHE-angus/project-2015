package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaRealStockAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		// add_date_st = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		// Calendar c = Calendar.getInstance();
		// c.set(Calendar.DAY_OF_MONTH, 1);
		// String add_date_st = DateFormatUtils.format(c.getTime(),
		// "yyyy-MM-dd"); // 取当月第一天
		// dynaBean.set("add_date_st", add_date_st);
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.DAY_OF_MONTH,
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		// String add_date_en = DateFormatUtils.format(calendar.getTime(),
		// "yyyy-MM-dd");
		// dynaBean.set("add_date_en", add_date_en);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_lt_30 = false;
		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_ge_10_lt_30 = true;
			}
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		List<KonkaDept> deptList = new ArrayList<KonkaDept>();
		KonkaDept dept = new KonkaDept();
		if (role_id_ge_10_lt_30) {
			dept.setPar_id(0L);
			deptList = getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		}
		if (role_id_ge_30) {
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (dept.getDept_type() == 3) {// 分公司
				dynaBean.set("branch_name", dept.getDept_name());
				dynaBean.set("branch_area_name_link", dept.getDept_name());
				dept.setDept_type(3);
				deptList = getFacade().getKonkaDeptService().getKonkaDeptList(dept);
			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				dynaBean.set("branch_name", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name_link", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				deptList.add(super.getSuperiorForDeptType(dept.getDept_id(), 3));
			}
		}

		request.setAttribute("deptList", deptList);
		dynaBean.set("customer_type", "1");
		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String customer_type = (String) dynaBean.get("customer_type");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String pd_ids = (String) dynaBean.get("pd_ids");
		String handle_name = (String) dynaBean.get("handle_name");
		String branch_name = (String) dynaBean.get("branch_name");
		String customer_code = (String) dynaBean.get("customer_code");

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaR3Shop r3Shop = super.getKonkaR3ShopForStockSell(mapping, form, request, response, ui.getDept_id());

		if (StringUtils.isBlank(customer_type)) {
			dynaBean.set("customer_type", "1");
		} else {
			dynaBean.set("customer_type", customer_type);
		}

		List<KonkaR3Shop> sList = new ArrayList<KonkaR3Shop>();

		if (("1").equals(customer_type)) {

			if (StringUtils.isNotBlank(keyword)) {
				r3Shop.getMap().put("keyword_like", keyword);
			}
			if (StringUtils.isNotBlank(customer_code)) {
				r3Shop.setCustomer_code(customer_code);
			}
			if (StringUtils.isNotBlank(customer_name_like)) {
				r3Shop.getMap().put("customer_name_like", customer_name_like);
			}
			if (StringUtils.isBlank(keyword) && StringUtils.isBlank(customer_name_like)
					&& StringUtils.isBlank(customer_code)) {
				String msg = "请至少输入R3编码、合并编码或客户名称中其中一项";
				super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
				return null;
			}
		} else if (("3").equals(customer_type)) { // 分公司
			if (StringUtils.isNotBlank(branch_name)) {
				r3Shop.setBranch_area_name(branch_name);
				request.setAttribute("name", branch_name);
			}
		} else if (("5").equals(customer_type)) { // 经办
			if (StringUtils.isNotBlank(handle_name)) {
				r3Shop.setHandle_name(handle_name);
				request.setAttribute("handle_name", handle_name);
				request.setAttribute("name", handle_name);
			}
		}
		r3Shop.getMap().put("OrderByHandle", true);
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(r3Shop);
		pager.init(recordCount, 1000000, pager.getRequestPage());
		r3Shop.getRow().setFirst(pager.getFirstRow());
		r3Shop.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> r3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(r3Shop);

		if (r3ShopList.size() == 0) {
			String msg = "输入的条件查询不到相关网点，或者查询条件相对应的网点不在你的权限之内";
			super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
			return null;
		}

		List<PePdModel> pePdModelList = new ArrayList<PePdModel>();
		if (("1").equals(customer_type)) {
			PePdModel ppm = new PePdModel();
			if (StringUtils.isNotBlank(keyword)) {
				ppm.getMap().put("keyword", keyword);
			}
			if (StringUtils.isNotBlank(customer_name_like)) {
				ppm.getMap().put("customer_name_like", customer_name_like);
			}
			if (StringUtils.isNotBlank(customer_code)) {
				ppm.getMap().put("customer_code", customer_code);
			}
			if (StringUtils.isNotBlank(customer_name_like) || StringUtils.isNotBlank(keyword)
					|| StringUtils.isNotBlank(customer_code)) {
				pePdModelList = getFacade().getKonkaRealTimeStockService().getIsUseProductByKeyword(ppm);
			}
		} else {
			PePdModel ppm = new PePdModel();
			ppm.setIs_del(0);
			ppm.getMap().put("selects", pd_ids);
			ppm.getMap().put("OrderByMd", true);
			pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(ppm);
		}

		for (KonkaR3Shop i : r3ShopList) {

			KonkaR3Shop r3 = new KonkaR3Shop();
			r3.setHandle_name(i.getHandle_name());
			r3.setCustomer_name(i.getCustomer_name());

			KonkaStockDetails stockDetails = new KonkaStockDetails();
			stockDetails.setR3_code(i.getR3_code());
			stockDetails.getMap().put("stock_date_end", s.format(new Date()) + " 23:59:59");
			List<KonkaStockDetails> StockList = getFacade().getKonkaStockDetailsService().getKonkaStockDetailsList(
					stockDetails);

			List<KonkaStockDetails> list = new ArrayList<KonkaStockDetails>();
			for (PePdModel t : pePdModelList) {
				KonkaStockDetails stock = new KonkaStockDetails();
				Long count = getResultForDKHNew(i, t, StockList, new Date());
				stock.setStock_count(count);
				list.add(stock);
			}

			r3.getMap().put("list", list);
			sList.add(r3);
		}

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_lt_30 = false;
		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_ge_10_lt_30 = true;
			}
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		List<KonkaDept> deptList = new ArrayList<KonkaDept>();
		KonkaDept dept = new KonkaDept();
		if (role_id_ge_10_lt_30) {
			dept.setPar_id(0L);
			deptList = getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		} else if (role_id_ge_30) {
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (dept.getDept_type() == 3) {// 分公司
				dynaBean.set("branch_name", dept.getDept_name());
				dynaBean.set("branch_area_name_link", dept.getDept_name());
				dept.setDept_type(3);
				deptList = getFacade().getKonkaDeptService().getKonkaDeptList(dept);
			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				dynaBean.set("branch_name", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name_link", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				deptList.add(super.getSuperiorForDeptType(dept.getDept_id(), 3));
			}
		}
		List<KonkaStockDetails> l = (List<KonkaStockDetails>) sList.get(0).getMap().get("list");
		int i = l.size();
		List<Long> totleList = new ArrayList<Long>();
		for (int j = 0; j < i; j++) {
			Long count = 0L;
			for (KonkaR3Shop t : sList) {
				List<KonkaStockDetails> sl = (List<KonkaStockDetails>) t.getMap().get("list");
				if (sl != null && sl.get(j).getStock_count() != null)
					count = count + sl.get(j).getStock_count();
			}
			totleList.add(count);
		}

		request.setAttribute("pePdModelList", pePdModelList);
		request.setAttribute("r3StList", sList);
		request.setAttribute("totleList", totleList);

		request.getSession().setAttribute("plList", pePdModelList);
		request.getSession().setAttribute("stList", sList);
		request.getSession().setAttribute("tList", totleList);
		request.setAttribute("deptList", deptList);
		return mapping.findForward("list");
	}

	public Long getResultForDKHNew(KonkaR3Shop i, PePdModel t, List<KonkaStockDetails> stockList, Date endDate)
			throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		Long stockCount = new Long(0);

		for (KonkaStockDetails sd : stockList) {
			if (sd.getPd_id().equals(t.getPd_id())) {
				stockCount = super.getCurrentCount(sd, s.format(endDate) + " 00:00:00");
			}
			if (t.getPd_id() == null) {
				stockCount = null;
			}
		}

		return stockCount;

	}

	@SuppressWarnings("unchecked")
	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String name = (String) dynaBean.get("name");
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		List<KonkaR3Shop> stockList = (List<KonkaR3Shop>) request.getSession().getAttribute("stList");
		List<PePdModel> pePdModelList = (List<PePdModel>) request.getSession().getAttribute("plList");
		List<Long> totleList = (List<Long>) request.getSession().getAttribute("tList");
		WritableWorkbook wwb = null;
		String title = name + s.format(new Date()) + "实时库存";
		String excel_name = "Customer_Invoicing.xls";

		try {
			// 获取系统实际路径
			String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(SystemPath + "files/" + excel_name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建一个可写入的工作表
		// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		if (wwb != null) {
			ws.addCell(new Label(2, 0, title));
			ws.addCell(new Label(0, 2, "序号"));
			ws.addCell(new Label(1, 2, "办事处"));
			ws.addCell(new Label(2, 2, "客户名称"));
			Integer x = 3;
			for (PePdModel model : pePdModelList) {
				if (model.getMd_name() != null) {
					ws.addCell(new Label(x, 2, model.getMd_name().toString()));
				} else if (model.getMd_name() == null) {
					ws.addCell(new Label(x, 2, ""));
				}
				x++;
			}
			ws.addCell(new Label(x, 2, "合计"));
			ws.addCell(new Label(2, 3, "合计"));
			int k = 3;
			Long allTotle = 0L;
			for (Long totle : totleList) {
				if (totle != null) {
					ws.addCell(new Label(k, 3, totle.toString()));
					allTotle = allTotle + totle;
				}
				k++;
			}
			ws.addCell(new Label(k, 3, allTotle.toString()));
		}
		Integer i = 4, j = 1;
		for (KonkaR3Shop t : stockList) {
			Long totle = 0L;
			if (wwb != null) {
				ws.addCell(new Label(0, i, j.toString()));
				ws.addCell(new Label(1, i, t.getHandle_name()));
				ws.addCell(new Label(2, i, t.getCustomer_name()));
				Integer k = 3;
				if (t.getMap().get("list") != null) {
					List<KonkaStockDetails> list = (List<KonkaStockDetails>) t.getMap().get("list");
					for (KonkaStockDetails stock : list) {
						if (stock.getStock_count() != null) {
							totle = totle + stock.getStock_count();
							ws.addCell(new Label(k, i, stock.getStock_count().toString()));
						} else {
							ws.addCell(new Label(k, i, ""));
						}
						k++;
					}
					ws.addCell(new Label(k, i, totle.toString()));
				}
			}
			i++;
			j++;
		}
		try {
			wwb.write();// 从内存中写入文件中
			wwb.close();// 关闭资源，释放内存
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

		String ctx = super.getCtxPath(request);
		super.renderJavaScript(response, "window.location.href='" + ctx + "/files/" + excel_name + "';");
		return null;
	}

	public ActionForward getPePdModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String pd_ids = (String) dynaBean.get("pd_ids");
		String customer_type = (String) dynaBean.get("customer_type");
		String keyword = (String) dynaBean.get("keyword");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String handle_name = (String) dynaBean.get("handle_name");
		String branch_name = (String) dynaBean.get("branch_name");

		dynaBean.set("customer_type", customer_type);
		dynaBean.set("keyword", keyword);
		dynaBean.set("customer_name_like", customer_name_like);
		dynaBean.set("handle_name", handle_name);
		dynaBean.set("branch_name", branch_name);

		PePdModel entity = new PePdModel();
		if (StringUtils.isNotBlank(pd_ids)) {
			entity.setIs_del(0);
			entity.getMap().put("selects", pd_ids);
			entity.getMap().put("OrderByMd", true);
			List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(entity);
			request.setAttribute("pePdModelList", pePdModelList);

			PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
			
			// 获取用户角色
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(ui.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			boolean role_id_ge_30 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() >= 30L) {
					role_id_ge_30 = true;
				}
			}

			KonkaDept dept = new KonkaDept();
			dept.setDept_type(3);
			if (role_id_ge_30) {
				dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				if (dept.getDept_type() == 3) {// 分公司
					dynaBean.set("branch_name", dept.getDept_name());
					dynaBean.set("branch_area_name_link", dept.getDept_name());
				} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
					dynaBean.set("branch_name", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
					dynaBean.set("branch_area_name_link", super.getSuperiorForDeptType(dept.getDept_id(), 3)
							.getDept_name());
				}
			}
			List<KonkaDept> deptList = getFacade().getKonkaDeptService().getKonkaDeptList(dept);
			request.setAttribute("deptList", deptList);
		}
		return mapping.findForward("list");
	}

	public ActionForward getPdIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		// PeRoleUser peRoleUser = (PeRoleUser)
		// request.getSession().getAttribute(Constants.ROLE_INFO);

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ge_30 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}

			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}

			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 60L) {
				role_id_ge_40_le_60 = true;
			}

		}

		PePdModel pepdModel = new PePdModel();
		pepdModel.setIs_del(0);
		pepdModel.getMap().put("OrderByMd", 1);
		if (role_id_ge_30) {
			pepdModel.getMap().put("getOwnDept", "true");
		}
		if (role_id_ge_30_le_39) {// 若登录的是分公司用户或分公司领导
			pepdModel.getMap().put("dept_id", ui.getDept_id());
		}
		// 若登录的是经营部、办事处、业务员用户
		if (role_id_ge_40_le_60) {
			KonkaDept konkaDept = this.getSuperiorForDeptType(ui.getDept_id(), 3);
			pepdModel.getMap().put("dept_id", konkaDept.getDept_id());
		}

		String pd_names = (String) dynaBean.get("Pd_names");
		String[] pd_name_arr = pd_names.split(",");
		StringBuffer sb = new StringBuffer("[{");
		if (pd_name_arr.length > 50) {
			sb.append("\"msg\":\"").append("输入的产品名称数量大于50，为减轻系统负担，将查询的产品数量限制为50,50个之后的产品不会进行查询，如需变更请重新输入。\",");
		}
		pd_names = "'" + pd_names.replace(",", "','") + "'";
		PePdModel pdModel = new PePdModel();
		pdModel.getMap().put("md_names", pd_names);
		List<PePdModel> pdModelList = getFacade().getPePdModelService().getPePdModelList(pdModel);
		if (pdModelList.size() == 0) {
			sb.append("\"msg\":\"").append("输入的型号没有找到对应的产品，请手动选择输入。\"");
			sb.append("}]");
			super.renderJson(response, sb.toString());
			return null;
		}
		String[] pd_ids_array = new String[pdModelList.size()];
		int i = 50;
		if (pdModelList.size() < i) {
			i = pdModelList.size();
		}
		for (int j = 0; j < i; j++) {
			pd_ids_array[j] = pdModelList.get(j).getPd_id().toString();
		}
		String pd_ids = StringUtils.join(pd_ids_array, ",");
		sb.append("\"pd_ids\":\"").append(pd_ids).append("\"}]");
		super.renderJson(response, sb.toString());
		return null;

	}
}
