package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.KonkaR3StoreTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class KonkaR3StoreTaskAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));
		dynaBean.set("month", String.valueOf(calendar.get(Calendar.MONTH) + 1));
		// dynaBean.set("month_end", String.valueOf(calendar.get(Calendar.MONTH)
		// + 1));

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String jb_name_like = (String) dynaBean.get("jb_name_like");
		String cxy_name_like = (String) dynaBean.get("cxy_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		// String month_end = (String) dynaBean.get("month_end");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreTask entity = new KonkaR3StoreTask();
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(jb_name_like)) {
			entity.getMap().put("jb_name_like", jb_name_like);
		}
		if (StringUtils.isNotBlank(cxy_name_like)) {
			entity.getMap().put("cxy_name_like", cxy_name_like);
		}
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		// 月份区间
		// if (StringUtils.isBlank(month_start)) {
		// month_start = "1";
		// }
		// if (StringUtils.isBlank(month_end)) {
		// month_end = "12";
		// }
		// int month_start_num = Integer.valueOf(month_start);
		// int month_end_num = Integer.valueOf(month_end);
		// int start_num = 0;
		// int end_num = 0;
		// if (month_start_num < month_end_num) {
		// start_num = month_start_num;
		// end_num = month_end_num;
		// } else {
		// start_num = month_end_num;
		// end_num = month_end_num;
		// }
		// String m_s = year + "-";
		// String m_e = year + "-";
		// if (start_num < 10) {
		// m_s = m_s + "0" + start_num;
		// } else {
		// m_s = m_s + start_num;
		// }
		// if (end_num < 10) {
		// m_e = m_e + "0" + end_num;
		// } else {
		// m_e = m_e + end_num;
		// }
		// entity.getMap().put("start_date", m_s);
		// entity.getMap().put("end_date", m_e);

		// entity.getMap().put("month_start", month_start);
		// entity.getMap().put("month_end", month_end);
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.valueOf(month));
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 188L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		if (role_id_eq_30) {

		} else if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}

		Long recordCount = super.getFacade().getKonkaR3StoreTaskService().getKonkaR3StoreTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3StoreTask> entityList = super.getFacade().getKonkaR3StoreTaskService()
		        .getKonkaR3StoreTaskPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("1111111111111111111111");
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String jb_name_like = (String) dynaBean.get("jb_name_like");
		String cxy_name_like = (String) dynaBean.get("cxy_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreTask entity = new KonkaR3StoreTask();
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(jb_name_like)) {
			entity.getMap().put("jb_name_like", jb_name_like);
		}
		if (StringUtils.isNotBlank(cxy_name_like)) {
			entity.getMap().put("cxy_name_like", cxy_name_like);
		}
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(year)) {
			entity.setYear(Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(month)) {
			entity.setMonth(Integer.valueOf(month));
		}

		Long recordCount = super.getFacade().getKonkaR3StoreTaskService().getKonkaR3StoreTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(new Integer(0));
		entity.getRow().setCount(new Integer(recordCount.intValue()));

		List<KonkaR3StoreTask> entityList = super.getFacade().getKonkaR3StoreTaskService()
		        .getKonkaR3StoreTaskPaginatedList(entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "年度");
		e.setCell(2, "月份");
		e.setCell(3, "分公司");
		e.setCell(4, "所属经办");
		e.setCell(5, "客户名称");
		e.setCell(6, "客户R3编码");
		e.setCell(7, "门店ID");
		e.setCell(8, "门店名称");
		e.setCell(9, "业务员");
		e.setCell(10, "促销员");
		e.setCell(11, "任务额");

		for (KonkaR3StoreTask pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getYear());
			e.setCell(2, pds.getMonth());
			e.setCell(3, pds.getDept_name());
			e.setCell(4, pds.getJb_name());
			e.setCell(5, pds.getCustomer_name());
			e.setCell(6, pds.getR3_code());
			e.setCell(7, pds.getStore_id());
			e.setCell(8, pds.getStore_name());
			e.setCell(9, pds.getYwy_name());
			e.setCell(10, pds.getCxy_name());
			e.setCell(11, pds.getTask_money().toString());

		}
		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreTask entity = new KonkaR3StoreTask();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(id)) {
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaR3StoreTaskService().modifyKonkaR3StoreTask(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward excelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		super.copyProperties(entity, form);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		File isFile = null;
		String fileSavePath = null;

		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("excel".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
			}
			logger.info("********************** {}", BeanUtils.describe(uploadFile));
		}

		if (fileSavePath != null) {
			String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
			if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
				super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
				return null;
			}
			String ctxDir = request.getSession().getServletContext().getRealPath("/");
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_MD_PATH)) {
				ctxDir = "";
				fileSavePath = "/Attachment_new/konka-files/"+ fileSavePath;
			}

			fileSavePath = ctxDir + fileSavePath;
			isFile = new File(fileSavePath);
			String[][] dd = ExcelUtil.getExcelData(isFile, 0);
			List<KonkaR3StoreTask> rList = new ArrayList<KonkaR3StoreTask>();
			KonkaR3StoreTask carno = new KonkaR3StoreTask();
			for (int i = 1; i < dd.length; i++) {
				carno = new KonkaR3StoreTask();
				for (int j = 0; j < dd[i].length; j++) {
					if ("年度".equals(dd[0][j].trim())) {
						if (isValidInt(dd[i][j])) {
							carno.setYear(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "年份请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("月份".equals(dd[0][j].trim())) {
						if (isValidInt(dd[i][j])) {
							carno.setMonth(Integer.valueOf(dd[i][j]));
						} else {
							String ss = "月份请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("门店ID".equals(dd[0][j].trim())) {
						if (isValidLong(dd[i][j])) {
							carno.setStore_id(Long.valueOf(dd[i][j]));

							KonkaR3Store ks = new KonkaR3Store();
							ks.setStore_id(carno.getStore_id());
							ks = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(ks);
							if (null != ks) {
								if (null != ks.getDept_name()) {
									carno.setDept_name(ks.getDept_name());
								}
								if (null != ks.getJb_name()) {
									carno.setJb_name(ks.getJb_name());
								}
								if (null != ks.getKh_name()) {
									carno.setCustomer_name(ks.getKh_name());
								}
								if (null != ks.getR3_code()) {
									carno.setR3_code(ks.getR3_code());
								}
								if (null != ks.getYwy_name()) {
									carno.setYwy_name(ks.getYwy_name());
								}
								carno.setDept_id(ks.getDept_id());
							}

							KonkaMobileSpRelation ksp = new KonkaMobileSpRelation();
							ksp.setShop_id(carno.getStore_id());
							List<KonkaMobileSpRelation> konkaMobileSpRelationList = super.getFacade()
							        .getKonkaMobileSpRelationService().getKonkaMobileSpRelationList(ksp);
							String cxy_names = "";
							List<String> namesList = new ArrayList<String>();
							if (konkaMobileSpRelationList.size() > 0) {
								for (KonkaMobileSpRelation konkaMobileSpRelation2 : konkaMobileSpRelationList) {
									PeProdUser pp = new PeProdUser();
									pp.setIs_del(0);
									pp.setId(konkaMobileSpRelation2.getSeller_id());
									pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
									if (null != pp && !"".equals(pp.getReal_name()) && null != pp.getReal_name()) {
										namesList.add(pp.getUser_name());
									}
								}
							}
							cxy_names = StringUtils.join(namesList, ",");
							carno.setCxy_name(cxy_names);

						} else {
							String ss = "门店ID请填写正整数！";
							super.renderJavaScript(response, "window.onload=function(){alert('" + ss
							        + "');window.history.back();}");
							return null;
						}
					} else if ("门店名称".equals(dd[0][j].trim())) {
						carno.setStore_name(dd[i][j]);
					} else if ("任务额".equals(dd[0][j].trim())) {
						try {
							BigDecimal bd = new BigDecimal(dd[i][j]);
							carno.setTask_money(bd);
						} catch (Exception e) {
							carno.setTask_money(new BigDecimal("0.00"));
						}
					}
					carno.setAdd_date(new Date());
					carno.setAdd_user_id(user.getId());
				}
				rList.add(carno);

			}

			// list去重，并按原顺序重新生成list
			List<KonkaR3StoreTask> rList2 = new ArrayList<KonkaR3StoreTask>();
			HashSet<KonkaR3StoreTask> result = new HashSet<KonkaR3StoreTask>();
			for (int i = 0; i < rList.size(); i++) {
				if (!result.add(rList.get(i))) {
				} else {
					result.add(rList.get(i));
					rList2.add(rList.get(i));
				}

			}
			String msg = super.getFacade().getKonkaR3StoreTaskService().createKonkaR3StoreTask(rList2);
			if (!"".equals(msg)) {
				msg = " 系统提示 ,信息导入失败：\\n" + msg;
				log.info(msg);
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
		} else {
			super.renderJavaScript(response, "alert('请选择上传Excel文件！');history.back();");
			return null;
		}
		super.saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreTask ec = new KonkaR3StoreTask();
		ec.setId(Long.valueOf(id));
		ec = super.getFacade().getKonkaR3StoreTaskService().getKonkaR3StoreTask(ec);
		super.copyProperties(form, ec);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaR3StoreTask entity = new KonkaR3StoreTask();
		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaR3StoreTaskService().removeKonkaR3StoreTask(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaR3StoreTask entity = new KonkaR3StoreTask();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaR3StoreTaskService().getKonkaR3StoreTask(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidInt(String str) {
		try {
			int _v = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidDouble(String str) {
		try {
			double _v = Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
