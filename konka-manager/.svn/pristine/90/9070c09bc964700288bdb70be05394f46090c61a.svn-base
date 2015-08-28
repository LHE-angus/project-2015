package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxSellBillDetailsDst;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaCustomerSailDataAction extends MobileBaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		// super.getModPopeDom(form, request); //先屏蔽，查询的数据太多，影响效率

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String excel_all = (String) dynaBean.get("excel_all");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");
		String customer_cate_id = (String) dynaBean.get("customer_cate_id");
		String md_name_like = (String) dynaBean.get("md_name_like");

		// String model_id = (String) dynaBean.get("model_id");

		JBillDetails entity = new JBillDetails();
		super.copyProperties(entity, form);
		entity.getMap().put("md_name_like", md_name_like);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 非系统管理员
		boolean role_id_eq_60 = false; // 业务员
		// boolean role_id_eq_30 = false; // 分公司管理员
		// boolean role_id_eq_60 = false;
		String role_ids = "";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start", l3_dept_id);
		}
		entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
		entity.getMap().put("session_user_id", peProdUser.getId());

		if (!role_id_eq_10) {
			// 非系统管理员
			// 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
			KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);

			if (null != kd) {
				// entity.setSubcomp_id(kd.getDept_id());
				if (null == entity.getMap().get("dept_id_start")) {
					entity.getMap().put("dept_id_start", peProdUser.getDept_id());
				}
			}

			// if (!role_id_eq_30 && !role_id_eq_10) {
			// entity.getMap().put("filter_by_job_id_eq",
			// peProdUser.getJob_id());
			// entity.getMap().remove("dept_id_start");
			// }

			// 业务员
			if (role_id_eq_60) {
				entity.getMap().put("ye_name", peProdUser.getUser_name());

				if (null != peRoleUserList && peRoleUserList.size() == 1) {
					entity.getMap().put("filter_by_ywy", "true");
					entity.getMap().remove("dept_id_start");
				}
			}

		} else {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);
		entity.getMap().put("c_comm", c_comm);
		entity.getMap().put("customer_cate_id", customer_cate_id);

		// entity.getMap().put("l3_dept_id", l3_dept_id);
		// entity.getMap().put("l4_dept_id", l4_dept_id);
		// entity.getMap().put("l5_dept_id", l5_dept_id);

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("add_date_begin", date_begin + " 00:00:00");

		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("add_date_end", date_end + " 23:59:59");
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isBlank(date_begin) && StringUtils.isBlank(date_end)) {
			date_end = dateformat.format(calendar.getTime());
			calendar.add(Calendar.MONTH, -3);
			date_begin = dateformat.format(calendar.getTime());
		}

		dynaBean.set("date_begin", date_begin);
		dynaBean.set("date_end", date_end);

		Long recordCount = getFacade().getJBillDetailsService().getJBillDetailsForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<HashMap> entityList = getFacade().getJBillDetailsService().getJBillDetailsForCustomerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<HashMap> entityList1 = getFacade().getJBillDetailsService().getJBillDetailsForCustomerPaginatedList(
					entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());

		return mapping.findForward("list");
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String yw_name = (String) dynaBean.get("yw_name");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");

		JBillDetails entity = new JBillDetails();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false; // 非系统管理员
		boolean role_id_eq_60 = false; // 业务员
		boolean role_id_eq_30 = false; // 分公司管理员
		// boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start", l3_dept_id);
		}
		entity.getMap().put("session_user_id", peProdUser.getId());

		if (!role_id_eq_10) {
			// 非系统管理员
			// 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
			KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
			if (null != kd) {
				// entity.setSubcomp_id(kd.getDept_id());
				if (null == entity.getMap().get("dept_id_start")) {
					entity.getMap().put("dept_id_start", peProdUser.getDept_id());
				}
			}

			if (!role_id_eq_30 && !role_id_eq_10) {
				entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
				entity.getMap().remove("dept_id_start");
			}

			// 业务员
			if (role_id_eq_60) {
				entity.getMap().put("ye_name", peProdUser.getUser_name());

				if (null != peRoleUserList && peRoleUserList.size() == 1) {
					entity.getMap().put("filter_by_ywy", "true");
				}
			} else {
				if (StringUtils.isNotBlank(yw_name)) {
					entity.getMap().put("ye_name", yw_name);
				}
			}

		} else {
			// 系统管理员
			request.setAttribute("isFgsUser", "true");
		}

		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("report_name_like", report_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);
		entity.getMap().put("c_comm", c_comm);

		if (StringUtils.isNotBlank(date_begin)) {
			Date d = DateUtils.parseDate(date_begin + " 00:00:00", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_begin", d);
		}
		if (StringUtils.isNotBlank(date_end)) {
			Date d = DateUtils.parseDate(date_end + " 23:59:59", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_end", d);
		}

		List<HashMap> entityList = getFacade().getJBillDetailsService().getJBillDetailsForCustomerPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("date", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return mapping.findForward("excel");
	}

	/*
	 * 办事处
	 */
	public ActionForward getDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		logger.info("-------subcomp_id--->{}", subcomp_id);
		if (StringUtils.isNotEmpty(subcomp_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(subcomp_id));

			List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			List<String> dataList = new ArrayList<String>();
			for (KonkaDept _t : baseDeptList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getDept_name(), "\",\"",
						String.valueOf(_t.getDept_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		// if (StringUtils.isNotEmpty(size_id)) {

		// String[] cls_ids = { category_id };
		// List<PePdModel> pePdModelList = super.getFacade()
		// .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
		// cls_ids);
		//
		List<String> dataList = new ArrayList<String>();
		PePdModel t = new PePdModel();
		// t.getMap().put("led_name_like", size_id);
		t.getMap().put("order_by_pd_name_desc", true);
		List<PePdModel> tList = new ArrayList<PePdModel>();
		tList = super.getFacade().getPePdModelService().getPePdModelList(t);
		for (PePdModel _t : tList) {
			dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
					String.valueOf(_t.getPd_id()), "\"]" }));
		}
		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		// }
		return null;
	}

	/*
	 * 产品型号
	 */
	public ActionForward getCType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String c_comm = (String) dynaBean.get("c_comm");
		if (StringUtils.isNotEmpty(c_comm)) {

			List<String> dataList = new ArrayList<String>();
			KonkaCategory t = new KonkaCategory();
			t.setPar_index(c_comm);
			List<KonkaCategory> tList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(t);
			for (KonkaCategory _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getC_name(), "\",\"",
						String.valueOf(_t.getC_index()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	public ActionForward showcust(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String add_date_ge = (String) dynaBean.get("add_date_ge");
		String add_date_le = (String) dynaBean.get("add_date_le");
		String sell_state = (String) dynaBean.get("sell_state");
		String add_user_realname_like = (String) dynaBean.get("add_user_realname_like");
		String buyer_name_like = (String) dynaBean.get("buyer_name_like");
		String pay_way = (String) dynaBean.get("pay_way");
		String order_type = (String) dynaBean.get("order_type");
		String stock_from = (String) dynaBean.get("stock_from");
		String sell_man_like = (String) dynaBean.get("sell_man_like");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		KonkaXxBaseType konkaXxBaseType = new KonkaXxBaseType();
		konkaXxBaseType.setPar_id(50000L); // 付款方式
		List<KonkaXxBaseType> pay_way_arr = super.getFacade().getKonkaXxBaseTypeService()
				.getKonkaXxBaseTypeList(konkaXxBaseType);
		request.setAttribute("pay_way_arr", pay_way_arr);

		KonkaXxSellBill entity = new KonkaXxSellBill();
		entity.setOrder_type(1);
		if (GenericValidator.isInt(order_type)) {
			entity.setOrder_type(Integer.valueOf(order_type));
		}
		if (GenericValidator.isInt(stock_from)) {
			entity.setStock_from(Integer.valueOf(stock_from));
		}

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_info.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		// List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_400 = false;
		Boolean role_id_eq_390 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() == 390) {
					role_id_eq_390 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		// 页面角色控制 start
		// long role_id = role_info.getRole_id();
		request.setAttribute("role_id_eq_400", role_id_eq_400);

		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
				entity.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			} else {
				String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
				saveDirectlyError(request, msg);
				return mapping.findForward("list");
			}

			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(zmd.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("zmd_sn", zmd.getZmd_sn());

		} else if (role_id_btw_300_400) { // 分公司人员
			if (role_id_eq_390) { // 分公司业务员
				KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
				konkaXxZmdUsers.setUser_id(user_info.getId());

				List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
						.getKonkaXxZmdUsersList(konkaXxZmdUsers);
				if (null != konkaXxZmdUsersList && konkaXxZmdUsersList.size() > 0) {
					List<Long> zmds = new ArrayList<Long>();
					for (KonkaXxZmdUsers temp : konkaXxZmdUsersList) {
						zmds.add(temp.getZmd_id());
					}

					entity.getMap().put("zmds", zmds.toArray());

					// 初始化业务员下面专卖店的下拉框
					KonkaXxZmd zmd = new KonkaXxZmd();
					zmd.getMap().put("zmds", zmds.toArray());
					List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

					request.setAttribute("zmdList", zmdList);
				} else {
					String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
					saveDirectlyError(request, msg);
					return mapping.findForward("list");
				}
			} else {
				KonkaDept kDept = getKonkaDeptForFgs(user_info.getDept_id());

				if (kDept != null)
					entity.setDept_id(kDept.getDept_id());

				// 初始化业务员下面专卖店的下拉框
				KonkaXxZmd zmd = new KonkaXxZmd();
				if (kDept != null)
					zmd.setDept_id(kDept.getDept_id());
				List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

				request.setAttribute("zmdList", zmdList);
			}
		}
		// 页面角色控制 end

		// 选择的条件字段
		if (GenericValidator.isLong(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		if (GenericValidator.isLong(sell_bill_id)) {
			entity.setSell_bill_id(Long.valueOf(sell_bill_id));
		}
		// 开单时间设置Begin
		Date now = new Date();
		if (StringUtils.isBlank(add_date_ge)) {
			Date lastDay = DateUtils.addMonths(now, -1);
			add_date_ge = df.format(lastDay);
			entity.getMap().put("add_date_ge", add_date_ge);
		} else {
			entity.getMap().put("add_date_ge", add_date_ge);
		}
		dynaBean.set("add_date_ge", add_date_ge);
		if (StringUtils.isBlank(add_date_le)) {
			add_date_le = df.format(now);
			entity.getMap().put("add_date_le", add_date_le);
		} else {
			entity.getMap().put("add_date_le", add_date_le);
		}
		dynaBean.set("add_date_le", add_date_le);
		// 开单时间设置End
		if (GenericValidator.isLong(sell_state)) {
			entity.setSell_state(Long.valueOf(sell_state));
		}
		if (StringUtils.isNotBlank(add_user_realname_like)) {
			entity.getMap().put("add_user_realname_like", add_user_realname_like);
		}
		if (StringUtils.isNotBlank(buyer_name_like)) {
			entity.getMap().put("buyer_name_like", buyer_name_like);
		}
		if (GenericValidator.isLong(pay_way)) {
			entity.setPay_way(Long.valueOf(pay_way));
		}
		if (StringUtils.isNotBlank(sell_man_like))
			entity.getMap().put("sell_man_like", sell_man_like);
		// Long recordCount =
		// super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCount(entity);原来的查总数方法
		Long recordCount = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountfordetails(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setCount(pager.getRowCount());
		entity.getRow().setFirst(pager.getFirstRow());

		// List<KonkaXxSellBill> entityList =
		// super.getFacade().getKonkaXxSellBillService()
		// .getKonkaXxSellBillPaginatedList(entity);原来的查询

		List<KonkaXxSellBill> entityList = super.getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillPaginatedListfordetails(entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/KonkaCustomerSailData/list2.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (StringUtils.isBlank(sell_bill_id)) {
			String msg = getMessage(request, "konka.zmd.sales.order.sellBillId.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
		}

		// new
		// BaseZmdAction.setOrderNavigationRequest(Long.valueOf(sell_bill_id),
		// request);

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		if (null != konkaXxSellBill.getSell_post_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getSell_post_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {
				dynaBean.set("sell_post_area", baseProvinceListFour.getFull_name());
			}
		}

		if (null != konkaXxSellBill.getBuyer_p_index()) {

			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(konkaXxSellBill.getBuyer_p_index());
			baseProvinceListFour = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
					baseProvinceListFour);
			if (null != baseProvinceListFour) {

				dynaBean.set("buyer_area", baseProvinceListFour.getFull_name());
			}
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		Integer total_counts = 0;

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		// konkaXxSellBillDetails.setLock_stock_state(1);
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		if (null != konkaXxSellBillDetailsList && konkaXxSellBillDetailsList.size() > 0) {
			for (KonkaXxSellBillDetails kxsbd : konkaXxSellBillDetailsList) {
				KonkaXxSellBillDetailsDst kxsbdd = new KonkaXxSellBillDetailsDst();
				kxsbdd.setSell_bill_details_id(kxsbd.getSell_bill_details_id());
				List<KonkaXxSellBillDetailsDst> list = getFacade().getKonkaXxSellBillDetailsDstService()
						.getKonkaXxSellBillDetailsDstList(kxsbdd);
				kxsbd.getMap().put("dstList", list);

				kxsbd.getMap().put("all_money", kxsbd.getPrice().multiply(new BigDecimal(kxsbd.getCounts())));

				total_counts += kxsbd.getCounts();
			}
		}
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);
		request.setAttribute("total_counts", total_counts);

		if (konkaXxSellBill.getDist_employee_id() != null) {
			KonkaXxDistEmployee user = new KonkaXxDistEmployee();
			user.setDist_employee_id(konkaXxSellBill.getDist_employee_id());
			user.setIs_del(0);
			user = getFacade().getKonkaXxDistEmployeeService().getKonkaXxDistEmployee(user);
			if (null != user) {
				dynaBean.set("dist_employee_name", user.getReal_name());
			}
		}

		// super.setBaseTypeListByParIdToRequest(50000L, request);
		// super.setBaseTypeListByParIdToRequest(70000L, request);
		// super.setBaseTypeListByParIdToRequest(90000L, request);

		return mapping.findForward("view");
	}

}