package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileSailDataBill;
import com.ebiz.mmt.domain.KonkaMobileSailDataBillLo;
import com.ebiz.mmt.domain.KonkaMobileSailDataBillSw;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileSailDataBillAction extends BaseAction {
	public String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47", "48", "50",
			"55", "58", "60", "65", "84" };

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaMobileSailData entity = getKonkaMobileSailData(mapping, form, request);
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		String is_for_audit = (String) dynaBean.get("is_for_audit");
		if (StringUtils.isNotBlank(is_for_audit) && "0".equals(is_for_audit)) {
			entity.getMap().put("is_for_audit", "yes");// 有发票记录
		} else if (StringUtils.isNotBlank(is_for_audit) && "1".equals(is_for_audit)) {
			entity.getMap().put("having_no_bill", "yes");// 有发票记录
		}
		Long recordCount = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataAndBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaMobileSailData> entityList = getFacade().getKonkaMobileSailDataService()
				.getKonkaMobileSailDataAndBillPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 是否显示0销量
		ArrayList<HashMap> showList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "0");
		map.put("name", "是");
		showList.add(map);

		map = new HashMap();
		map.put("val", "1");
		map.put("name", "否");
		showList.add(map);
		request.setAttribute("showList", showList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());

		if (StringUtils.isNotBlank(excel_to_all) && "1".equals(excel_to_all)) {
			if (recordCount.intValue() > 100000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("销售明细")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<KonkaMobileSailData> entityList1 = getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataInR3InfoPaginatedList(entity);

			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		if (StringUtils.isNotBlank(excel_to_all) && "2".equals(excel_to_all)) {

			if (recordCount.intValue() > 100000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("全部发票清单")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<KonkaMobileSailData> entityList1 = getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataInR3InfoPaginatedList(entity);

			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/view_all.jsp");
		}
		return mapping.findForward("list");

	}

	public ActionForward listForAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> roleList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		boolean role_id_124 = false; // 是否是核算专员
		boolean role_id_125 = false; // 是否是财务专员
		for (PeRoleUser RoleUser : roleList) {
			//System.out.println(RoleUser.getRole_id() + "role_id");
			if (null != RoleUser && RoleUser.getRole_id().equals(124L)) {
				role_id_124 = true;
			}
			if (null != RoleUser && RoleUser.getRole_id().equals(125L)) {
				role_id_125 = true;
			}
		}

		//System.out.println(role_id_124 + "//System.out.println(role_id_124);");
		//System.out.println(role_id_125 + "//System.out.println(role_id_125);");
		request.setAttribute("role_id_124", role_id_124);
		request.setAttribute("role_id_125", role_id_125);
		KonkaMobileSailData entity = getKonkaMobileSailData(mapping, form, request);

		entity.getMap().put("is_for_audit", "yes");
		Long recordCount = getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataAndBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaMobileSailData> entityList = getFacade().getKonkaMobileSailDataService()
				.getKonkaMobileSailDataAndBillPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 是否显示0销量
		ArrayList<HashMap> showList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "0");
		map.put("name", "是");
		showList.add(map);

		map = new HashMap();
		map.put("val", "1");
		map.put("name", "否");
		showList.add(map);
		request.setAttribute("showList", showList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}
		
		//导出
		if (StringUtils.isNotBlank(excel_to_all) && "2".equals(excel_to_all)) {

			if (recordCount.intValue() > 100000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("已初审发票清单")
					+ ".xls");

			entity.getRow().setCount(recordCount.intValue());
			List<KonkaMobileSailData> entityList1 = getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataAndBillPaginatedList(entity);

			request.setAttribute("allList", entityList1);
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/view_all.jsp");
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());
		request.setAttribute("ext_audit_state", null == entity.getMap().get("ext_audit_state_back") ? 0 : entity
				.getMap().get("ext_audit_state_back"));
		if ("2".equals(entity.getMap().get("ext_audit_state_back"))) {
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_for_audited.jsp");
		} else {
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_for_audit.jsp");
		}
	}

	public ActionForward listForFinalAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String switch_user_name_like = (String) dynaBean.get("switch_user_name_like");
		String final_audit_flag = (String) dynaBean.get("final_audit_flag");
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> roleList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		// boolean role_id_124=false; //是否是核算专员
		boolean role_id_125 = false; // 是否是财务专员
		for (PeRoleUser RoleUser : roleList) {
			/*
			 * if(null!=RoleUser && RoleUser.getRole_id() == 124 ){
			 * role_id_124=true;
			 * }
			 */
			if (null != RoleUser && RoleUser.getRole_id() == 125) {
				role_id_125 = true;
			}
		}
		// request.setAttribute("role_id_124",role_id_124);
		request.setAttribute("role_id_125", role_id_125);

		KonkaMobileSailDataBillSw entity = new KonkaMobileSailDataBillSw();
		if (StringUtils.isNotBlank(switch_user_name_like)) {
			entity.getMap().put("switch_user_name_like", switch_user_name_like);
		}

		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		if (null != konkaDeptList && konkaDeptList.size() == 1) {
			entity.getMap().put("subcomp_id", konkaDeptList.get(0).getDept_id());
		}

		if (StringUtils.isNotBlank(dept_id) && GenericValidator.isInt(dept_id)) {
			entity.getMap().put("subcomp_id", Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(final_audit_flag) && "1".equals(final_audit_flag)) {
			entity.getMap().put("final_audit_flag", "yes");
		}
		request.setAttribute("konkaDeptList", konkaDeptList);

		Long recordCount = getFacade().getKonkaMobileSailDataBillSwService().getKonkaMobileSailDataBillSwAndBillCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaMobileSailDataBillSw> entityList = getFacade().getKonkaMobileSailDataBillSwService()
				.getKonkaMobileSailDataBillSwAndBillPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 是否显示0销量
		ArrayList<HashMap> showList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "0");
		map.put("name", "是");
		showList.add(map);

		map = new HashMap();
		map.put("val", "1");
		map.put("name", "否");
		showList.add(map);
		request.setAttribute("showList", showList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());
		request.setAttribute("ext_audit_state", null == entity.getMap().get("ext_audit_state_back") ? 0 : entity
				.getMap().get("ext_audit_state_back"));
		if (StringUtils.isNotBlank(final_audit_flag) && "1".equals(final_audit_flag)) {
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_for_final_audited.jsp");
		} else {
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_for_final_audit.jsp");
		}
	}

	// 打开初审的界面
	public ActionForward toAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (StringUtils.isBlank(id) || !GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
		konkaMobileSailData.setId(Long.valueOf(id));
		konkaMobileSailData = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(
				konkaMobileSailData);
		if (null == konkaMobileSailData) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		konkaMobileSailDataBill.setSail_id(Long.valueOf(id));
		// konkaMobileSailDataBill.getMap().put("ext_audit_state_0_4", "yes");// 待初审的记录
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
				.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillAndAttachmentList(
						konkaMobileSailDataBill);
		
		//条件回显
		konkaMobileSailData.setQueryString(super.serialize(request, "id", "mod_id"));
		super.copyProperties(form, konkaMobileSailData);

		request.setAttribute("entity", konkaMobileSailData);

		if (null != konkaMobileSailDataBillList) {
			request.setAttribute("konkaMobileSailDataBillList", konkaMobileSailDataBillList);
		}

		return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/to_audit.jsp");
	}

	// 保存初审
	public ActionForward saveAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return this.listForAudit(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			super.renderJavaScript(response, "alert('用户信息获取失败，请重新登录');history.back();");
			return null;
		}
		String id = (String) dynaBean.get("id");

		String[] bill_ids = request.getParameterValues("bill_id");
		String[] bill_nos = request.getParameterValues("bill_no");
		String[] audit_moneys = request.getParameterValues("audit_money");
		String[] is_valid_for_pays = request.getParameterValues("is_valid_for_pay");
		String[] bill_mems = request.getParameterValues("bill_mem");
		String[] states = request.getParameterValues("state");
		int statu = 2;
		if (null != bill_ids && bill_ids.length > 0) {
			for (int i = 0; i < bill_ids.length; i++) {
				if (GenericValidator.isLong(bill_ids[i])) {
					KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
					KonkaMobileSailDataBillLo konkaMobileSailDataBillLo = new KonkaMobileSailDataBillLo();
					konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_ids[i]));
					konkaMobileSailDataBillLo.setBill_id(Long.valueOf(bill_ids[i]));
					if (null != audit_moneys && GenericValidator.isDouble(audit_moneys[i])) {
						konkaMobileSailDataBill.setAudit_money(new BigDecimal(audit_moneys[i]));
						konkaMobileSailDataBillLo.setOper_money(new BigDecimal(audit_moneys[i]));
					}
					if (null != is_valid_for_pays && GenericValidator.isInt(is_valid_for_pays[i])) {
						konkaMobileSailDataBill.setIs_valid_for_pay(Integer.parseInt(is_valid_for_pays[i]));

					}
					if (null != bill_mems && StringUtils.isNotBlank(bill_mems[i])) {
						konkaMobileSailDataBill.setBill_mem(bill_mems[i]);
					}
					if (null != states && GenericValidator.isInt(states[i])) {
						konkaMobileSailDataBill.setState(Integer.parseInt(states[i]));
						if ("4".equals(states[i])) {
							statu = 4;
						}
					}
					konkaMobileSailDataBill.setAudit_date(new Date());
					super.getFacade().getKonkaMobileSailDataBillService().modifyKonkaMobileSailDataBill(
							konkaMobileSailDataBill);

					konkaMobileSailDataBillLo.setOper_date(new Date());
					konkaMobileSailDataBillLo.setOper_user_id(peProdUser.getId());

					konkaMobileSailDataBillLo.setOper_mem("初审");
					super.getFacade().getKonkaMobileSailDataBillLoService().createKonkaMobileSailDataBillLo(
							konkaMobileSailDataBillLo);
				}

			}
		}
		KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
		konkaMobileSailData.setId(Long.valueOf(id));
		konkaMobileSailData.setAudit_state(statu);
		konkaMobileSailData.setAudit_date(new Date());
		super.getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(konkaMobileSailData);
		konkaMobileSailData = new KonkaMobileSailData();
		super.copyProperties(konkaMobileSailData, form);
	//	pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		super.renderJavaScript(response,
				"location.href='../admin/KonkaMobileSailDataBill.do?method=listForAudit&ext_audit_state=0&mod_id="
						+ mod_id + "&"+super.encodeSerializedQueryString(konkaMobileSailData.getQueryString())+"'");
		return null;

	}

	// 进入终审的界面
	public ActionForward toFinalAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (StringUtils.isBlank(id) || !GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaMobileSailDataBillSw konkaMobileSailDataBillSw = new KonkaMobileSailDataBillSw();
		konkaMobileSailDataBillSw.setSwitch_id(Long.valueOf(id));
		konkaMobileSailDataBillSw = super.getFacade().getKonkaMobileSailDataBillSwService()
				.getKonkaMobileSailDataBillSw(konkaMobileSailDataBillSw);
		if (null == konkaMobileSailDataBillSw) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		super.copyProperties(form, konkaMobileSailDataBillSw);

		request.setAttribute("entity", konkaMobileSailDataBillSw);
		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		konkaMobileSailDataBill.setSwitch_id(Long.valueOf(id));
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
				.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillAndAttachmentBySailList(
						konkaMobileSailDataBill);
		if (null != konkaMobileSailDataBillList) {
			request.setAttribute("konkaMobileSailDataBillList", konkaMobileSailDataBillList);
		}
		return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/to_final_audit.jsp");
	}

	// 查看已转单，待终审，以终审
	public ActionForward viewFinalAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String store_id = (String) dynaBean.get("store_id");
		String user_id = (String) dynaBean.get("user_id");
		if (StringUtils.isBlank(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return this.unspecified(mapping, form, request, response);
		}

		KonkaMobileSailDataBillSw konkaMobileSailDataBillSw = new KonkaMobileSailDataBillSw();
		konkaMobileSailDataBillSw.setSwitch_id(Long.valueOf(id));
		konkaMobileSailDataBillSw = super.getFacade().getKonkaMobileSailDataBillSwService()
				.getKonkaMobileSailDataBillSw(konkaMobileSailDataBillSw);
		if (null == konkaMobileSailDataBillSw) {
			this.saveError(request, "errors.long", new String[] { id });
			return this.unspecified(mapping, form, request, response);
		}

		super.copyProperties(form, konkaMobileSailDataBillSw);

		request.setAttribute("entity", konkaMobileSailDataBillSw);
		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		konkaMobileSailDataBill.setSwitch_id(Long.valueOf(id));
		if (null != user_id) {
			konkaMobileSailDataBill.getMap().put("user_id", user_id);
		}
		if (null != store_id) {
			konkaMobileSailDataBill.getMap().put("store_id", store_id);
		}
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
				.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillAndAttachmentBySailList(
						konkaMobileSailDataBill);
		if (null != konkaMobileSailDataBillList) {
			request.setAttribute("konkaMobileSailDataBillList", konkaMobileSailDataBillList);
		}
		return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/view_final_audit.jsp");
	}

	public ActionForward saveFinalAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return this.listForFinalAudit(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			super.renderJavaScript(response, "alert('用户信息获取失败，请重新登录');history.back();");
			return null;
		}
		String switch_id = (String) dynaBean.get("switch_id");
		String final_audit_remark = (String) dynaBean.get("final_audit_remark");

		String[] sail_data_id = request.getParameterValues("sail_data_id");
		String[] bill_ids = request.getParameterValues("bill_id");
		String[] final_audit_moneys = request.getParameterValues("final_audit_money");
		String[] is_valid_for_pays = request.getParameterValues("is_valid_for_pay");
		String[] bill_mems = request.getParameterValues("bill_mem");
		String[] states = request.getParameterValues("state");
		int statu = 8;
		if (null != bill_ids && bill_ids.length > 0) {
			for (int i = 0; i < bill_ids.length; i++) {
				if (GenericValidator.isLong(bill_ids[i])) {
					KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
					KonkaMobileSailDataBillLo konkaMobileSailDataBillLo = new KonkaMobileSailDataBillLo();
					konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_ids[i]));
					konkaMobileSailDataBillLo.setBill_id(Long.valueOf(bill_ids[i]));
					if (null != final_audit_moneys && GenericValidator.isDouble(final_audit_moneys[i])) {
						konkaMobileSailDataBill.setFinal_audit_money(new BigDecimal(final_audit_moneys[i]));
						konkaMobileSailDataBillLo.setOper_money(new BigDecimal(final_audit_moneys[i]));
					}
					if (null != is_valid_for_pays && GenericValidator.isInt(is_valid_for_pays[i])) {
						konkaMobileSailDataBill.setIs_valid_for_pay(Integer.parseInt(is_valid_for_pays[i]));

					}
					if (null != bill_mems && StringUtils.isNotBlank(bill_mems[i])) {
						konkaMobileSailDataBill.setBill_mem(bill_mems[i]);
					}
					if (null != states && GenericValidator.isInt(states[i])) {
						konkaMobileSailDataBill.setState(Integer.parseInt(states[i]));
						if ("10".equals(states[i])) {
							statu = 10;
						}
					}
					konkaMobileSailDataBill.setAudit_date(new Date());
					super.getFacade().getKonkaMobileSailDataBillService().modifyKonkaMobileSailDataBill(
							konkaMobileSailDataBill);

					konkaMobileSailDataBillLo.setOper_date(new Date());
					konkaMobileSailDataBillLo.setOper_user_id(peProdUser.getId());

					konkaMobileSailDataBillLo.setOper_mem("终审");
					super.getFacade().getKonkaMobileSailDataBillLoService().createKonkaMobileSailDataBillLo(
							konkaMobileSailDataBillLo);
				}

			}
			KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
			konkaMobileSailData.getMap().put("pks", sail_data_id);
			konkaMobileSailData.setAudit_state(statu);
			konkaMobileSailData.setAudit_date(new Date());
			super.getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(konkaMobileSailData);

			// 处理转单表
			KonkaMobileSailDataBillSw konkaMobileSailBillDataSw = new KonkaMobileSailDataBillSw();
			konkaMobileSailBillDataSw.setSwitch_id(Long.valueOf(switch_id));
			konkaMobileSailBillDataSw.setFinal_audit_date(new Date());
			konkaMobileSailBillDataSw.setFinal_audit_remark(final_audit_remark);
			konkaMobileSailBillDataSw.setFinal_audit_id(peProdUser.getId());
			konkaMobileSailBillDataSw.setFinal_audit_name(peProdUser.getUser_name());
			konkaMobileSailBillDataSw.setState(statu == 8 ? 1 : 2);
			super.getFacade().getKonkaMobileSailDataBillSwService().modifyKonkaMobileSailDataBillSw(
					konkaMobileSailBillDataSw);
		}
		super.renderJavaScript(response,
				"location.href='../admin/KonkaMobileSailDataBill.do?method=listForFinalAudit&mod_id=" + mod_id + "'");
		return null;
	}

	/*
	 * 转单功能（列表）
	 */
	public ActionForward listSwitch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;

		String report_name_like = (String) dynaBean.get("report_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String dept_name_like = (String) dynaBean.get("dept_name_like");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		/**
		 * @val 状态 Audit_state
		 * @val 0上传中，
		 * @val 2 初审合格
		 * @val 4初审不合格
		 * @val 6初审通过并转单
		 * @val 8终审通过
		 * @val 10 终审不通过
		 * @val
		 * @val 有任何一个发票不合格，则整个记录不合格
		 * @val 所有的发票审核合格后，整个记录则为合格，并且才允许走下一个步骤
		 */
		String audit_state = (String) dynaBean.get("audit_state");

		// 是否显示0销量,0显示，1不显示
		String isShow = (String) dynaBean.get("isShow");
		String r3_code = (String) dynaBean.get("r3_code");

		// 型号模糊搜索
		String model_like = (String) dynaBean.get("model_like");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(audit_state) && GenericValidator.isInt(audit_state)) {
			entity.setAudit_state(Integer.parseInt(audit_state));
		}

		if (StringUtils.isNotBlank(model_like)) {
			entity.getMap().put("model_like", model_like);
		}

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

		// 数据级别判断开始
		Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_job_id", peProdUser.getJob_id());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_job_id_", peProdUser.getJob_id());
			break;
		case 0:
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
			break;
		default:
			// 出错
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start_value", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start_value", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start_value", l3_dept_id);
		}

		entity.getMap().put("report_name_like", report_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);
		entity.getMap().put("c_comm", c_comm);

		// 是否显示0销量
		if ("1".equals(isShow)) {
			entity.getMap().put("not_Show", "true");
		}

		// 是否有效
		entity.setIs_del(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_id", r3_code);
		}
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月");
		Calendar ca = Calendar.getInstance();
		ca.setTime(today); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		entity.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
		entity.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(year + "-" + month + "-01");
			ca.setTime(date); // someDate 为你要获取的那个月的时间
			ca.set(Calendar.DAY_OF_MONTH, 1);
			firstDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			lastDate = ca.getTime();
			entity.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
			entity.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
			request.setAttribute("year_month", year + "年" + month + "月");
		}

		List<KonkaMobileSailData> entityList = getFacade().getKonkaMobileSailDataService()
				.getkonkaMobileSailDataAndBillForSwitchList(entity);

		request.setAttribute("entityList", entityList);
		// 尺寸sizeList
		// List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		// for (String str : size_strs) {
		// MobileSelectItem t = new MobileSelectItem();
		// t.setId(str);
		// t.setName(str);
		// sizeList.add(t);
		// }
		// request.setAttribute("sizeList", sizeList);

		// 是否显示0销量
		ArrayList<HashMap> showList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "0");
		map.put("name", "是");
		showList.add(map);

		map = new HashMap();
		map.put("val", "1");
		map.put("name", "否");
		showList.add(map);
		request.setAttribute("showList", showList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());
		return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_switch.jsp");

	}

	/*
	 * 已转单列表
	 */
	public ActionForward listSwitched(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String switch_user_name_like = (String) dynaBean.get("switch_user_name_like");
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> roleList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		// boolean role_id_124=false; //是否是核算专员
		boolean role_id_125 = false; // 是否是财务专员
		for (PeRoleUser RoleUser : roleList) {
			/*
			 * if(null!=RoleUser && RoleUser.getRole_id() == 124 ){
			 * role_id_124=true;
			 * }
			 */
			if (null != RoleUser && RoleUser.getRole_id() == 125) {
				role_id_125 = true;
			}
		}
		// request.setAttribute("role_id_124",role_id_124);
		request.setAttribute("role_id_125", role_id_125);

		KonkaMobileSailDataBillSw entity = new KonkaMobileSailDataBillSw();
		if (StringUtils.isNotBlank(switch_user_name_like)) {
			entity.getMap().put("switch_user_name_like", switch_user_name_like);
		}

		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		if (null != konkaDeptList && konkaDeptList.size() == 1) {
			entity.getMap().put("subcomp_id", konkaDeptList.get(0).getDept_id());
		}

		if (StringUtils.isNotBlank(dept_id) && GenericValidator.isInt(dept_id)) {
			entity.getMap().put("subcomp_id", Long.valueOf(dept_id));
		}

		request.setAttribute("konkaDeptList", konkaDeptList);

		Long recordCount = getFacade().getKonkaMobileSailDataBillSwService()
				.getKonkaMobileSailDataBillSwAndBillForSwitchedCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaMobileSailDataBillSw> entityList = getFacade().getKonkaMobileSailDataBillSwService()
				.getKonkaMobileSailDataBillSwAndBillForSwitchedPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 是否显示0销量
		ArrayList<HashMap> showList = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		map.put("val", "0");
		map.put("name", "是");
		showList.add(map);

		map = new HashMap();
		map.put("val", "1");
		map.put("name", "否");
		showList.add(map);
		request.setAttribute("showList", showList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		Date two_days_ago = DateUtils.addDays(now, -2);
		request.setAttribute("now", now);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("two_days_ago", two_days_ago);

		KonkaDeptTree t = new KonkaDeptTree();
		t.setDept_id(peProdUser.getDept_id());
		t = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(t);
		if (null != t && t.getIs_leaf() == 1) {
			request.setAttribute("current_dept", t);
		}

		request.setAttribute("cs_par_id", peProdUser.getDept_id());
		request.setAttribute("ext_audit_state", null == entity.getMap().get("ext_audit_state_back") ? 0 : entity
				.getMap().get("ext_audit_state_back"));
		return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_switched.jsp");

	}

	/*
	 * 转单功能（保存）
	 */
	public ActionForward saveSwitch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return this.listSwitch(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			super.renderJavaScript(response, "alert('用户登录信息过时，请重新登录');history.back();");
			return null;
		}
		// String report_name_like = (String) dynaBean.get("sail_ids");

		String[] sail_ids = request.getParameterValues("sail_ids");
		String[] bill_ids = request.getParameterValues("bill_ids");
		String switch_title = (String) dynaBean.get("switch_title");
		String switch_remark = (String) dynaBean.get("switch_remark");
		if (null == bill_ids || bill_ids.length == 0 || null == sail_ids || sail_ids.length == 0) {
			super.renderJavaScript(response, "alert('请至少选择一行记录进行操作');history.back();");
			return null;
		}

		KonkaMobileSailDataBillSw konkaMobileSailDataBillSw = new KonkaMobileSailDataBillSw();
		if (StringUtils.isNotBlank(switch_title)) {
			konkaMobileSailDataBillSw.setSwitch_title(switch_title);
		}
		if (StringUtils.isNotBlank(switch_remark)) {
			konkaMobileSailDataBillSw.setSwitch_remark(switch_remark);
		}
		konkaMobileSailDataBillSw.setSwitch_user_id(peProdUser.getId());
		konkaMobileSailDataBillSw.setSwitch_user_name(peProdUser.getUser_name());
		konkaMobileSailDataBillSw.setSwitch_date(new Date());
		konkaMobileSailDataBillSw.setState(0);// 0表示转单成功
		Long sw_id = super.getFacade().getKonkaMobileSailDataBillSwService().createKonkaMobileSailDataBillSw(
				konkaMobileSailDataBillSw);
		if (null != sw_id) {
			for (String billids : bill_ids) {
				String[] bill_id = billids.split(",");
				if (null != bill_id && bill_id.length > 0) {
					for (String id : bill_id) {
						if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
							KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
							konkaMobileSailDataBill.setBill_id(Long.valueOf(id));
							konkaMobileSailDataBill.setState(6);
							konkaMobileSailDataBill.setAudit_date(new Date());
							konkaMobileSailDataBill.setSwitch_id(sw_id);
							super.getFacade().getKonkaMobileSailDataBillService().modifyKonkaMobileSailDataBill(
									konkaMobileSailDataBill);

							KonkaMobileSailDataBillLo konkaMobileSailDataBillLo = new KonkaMobileSailDataBillLo();

							konkaMobileSailDataBillLo.setBill_id(Long.valueOf(id));
							konkaMobileSailDataBillLo.setOper_date(new Date());
							konkaMobileSailDataBillLo.setOper_mem("转单");
							konkaMobileSailDataBillLo.setOper_state(6);
							konkaMobileSailDataBillLo.setOper_user_id(peProdUser.getId());

							super.getFacade().getKonkaMobileSailDataBillLoService().createKonkaMobileSailDataBillLo(
									konkaMobileSailDataBillLo);
						}
					}
				}

			}

			for (String sailids : sail_ids) {
				String[] sail_id = sailids.split(",");
				if (null != sail_id && sail_id.length > 0) {
					for (String id : sail_id) {
						if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
							KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
							konkaMobileSailData.setId(Long.valueOf(id));
							konkaMobileSailData.setAudit_state(6);
							konkaMobileSailData.setAudit_date(new Date());
							super.getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(
									konkaMobileSailData);
						}
					}
				}

			}
			konkaMobileSailDataBillSw.setSwitch_id(sw_id);
			konkaMobileSailDataBillSw.setState(0);
			super.getFacade().getKonkaMobileSailDataBillSwService().modifyKonkaMobileSailDataBillSw(
					konkaMobileSailDataBillSw);

		}
		/**
		 * PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		 * if (null == peProdUser) {
		 * super.renderJavaScript(response, "alert('用户登录信息过时，请重新登录');history.back();");
		 * return null;
		 * }
		 * if (null == super.checkUserModPopeDom(form, request, "0")) {
		 * return super.checkPopedomInvalid(request, response);
		 * }
		 * 
		 * // DynaBean dynaBean = (DynaBean) form;
		 * 
		 * String report_name_like = (String) dynaBean.get("report_name_like");
		 * String is_del = (String) dynaBean.get("is_del");
		 * String dept_name_like = (String) dynaBean.get("dept_name_like");
		 * 
		 * String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		 * String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		 * String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		 * String c_comm = (String) dynaBean.get("c_comm");
		 * 
		 * String year = (String) dynaBean.get("year");
		 * String month = (String) dynaBean.get("month");
		 * /**
		 * 
		 * @val 状态 Audit_state
		 * @val 0上传中，
		 * @val 2 初审合格
		 * @val 4初审不合格
		 * @val 6初审通过并转单
		 * @val 8终审通过
		 * @val 10 终审不通过
		 * @val
		 * @val 有任何一个发票不合格，则整个记录不合格
		 * @val 所有的发票审核合格后，整个记录则为合格，并且才允许走下一个步骤
		 * 
		 *      String audit_state = (String) dynaBean.get("audit_state");
		 * 
		 *      // 是否显示0销量,0显示，1不显示
		 *      String isShow = (String) dynaBean.get("isShow");
		 *      String r3_code = (String) dynaBean.get("r3_code");
		 * 
		 *      // 型号模糊搜索
		 *      String model_like = (String) dynaBean.get("model_like");
		 * 
		 *      KonkaMobileSailData entity = new KonkaMobileSailData();
		 *      super.copyProperties(entity, form);
		 * 
		 *      if (StringUtils.isNotBlank(audit_state) && GenericValidator.isInt(audit_state)) {
		 *      entity.setAudit_state(Integer.parseInt(audit_state));
		 *      }
		 * 
		 *      if (StringUtils.isNotBlank(model_like)) {
		 *      entity.getMap().put("model_like", model_like);
		 *      }
		 * 
		 *      PeRoleUser _peRoleUser = new PeRoleUser();
		 *      _peRoleUser.setUser_id(peProdUser.getId());
		 *      List<PeRoleUser> peRoleUserList =
		 *      this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		 * 
		 *      boolean role_id_eq_10 = false; // 非系统管理员
		 *      boolean role_id_eq_60 = false; // 业务员
		 *      // boolean role_id_eq_30 = false; // 分公司管理员
		 *      // boolean role_id_eq_60 = false;
		 *      String role_ids = "";
		 *      for (PeRoleUser peRoleUser : peRoleUserList) {
		 *      role_ids = role_ids + "," + peRoleUser.getRole_id();
		 *      if (peRoleUser.getRole_id() == 10L) {
		 *      role_id_eq_10 = true;
		 *      }
		 *      if (peRoleUser.getRole_id() == 60L) {
		 *      role_id_eq_60 = true;
		 *      }
		 *      }
		 * 
		 *      entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
		 *      entity.getMap().put("session_user_id", peProdUser.getId());
		 * 
		 *      if (!role_id_eq_10) {
		 *      // 非系统管理员
		 *      // 分公司用户 未指定分公司查询条件 取当前登录用户部门ID
		 *      KonkaDept kd = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		 * 
		 *      if (null != kd) {
		 *      if (null == entity.getMap().get("dept_id_start")) {
		 *      entity.getMap().put("dept_id_start", peProdUser.getDept_id());
		 *      }
		 *      }
		 * 
		 *      // 业务员
		 *      if (role_id_eq_60) {
		 *      entity.getMap().put("ye_name", peProdUser.getUser_name());
		 * 
		 *      if (null != peRoleUserList && peRoleUserList.size() == 1) {
		 *      entity.getMap().put("filter_by_ywy", "true");
		 *      entity.getMap().remove("dept_id_start");
		 *      }
		 *      }
		 * 
		 *      } else {
		 *      // 系统管理员
		 *      request.setAttribute("isFgsUser", "true");
		 *      }
		 * 
		 *      // 数据级别判断开始
		 *      Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		 *      int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		 *      logger.info("Max level : {}", max_dlevel);
		 *      switch (max_dlevel) {
		 *      case 9:
		 *      // 集团及以下部门可见
		 *      __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
		 *      break;
		 *      case 8:
		 *      // 分公司及以下部门可见
		 *      KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
		 *      if (null != dept_fgs && null != dept_fgs.getDept_id()) {
		 *      __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
		 *      entity.getMap().put("dept_id_start", __dept_id);
		 *      entity.getMap().put("filter_by_job_id", peProdUser.getJob_id());
		 *      }
		 *      break;
		 *      case 7:
		 *      // 我所在的部门及以下部门可见
		 *      __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		 *      entity.getMap().put("dept_id_start", __dept_id);
		 *      entity.getMap().put("filter_by_job_id_", peProdUser.getJob_id());
		 *      break;
		 *      case 0:
		 *      __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		 *      entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
		 *      entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
		 *      break;
		 *      default:
		 *      // 出错
		 *      }
		 * 
		 *      if (StringUtils.isNotBlank(l5_dept_id)) {
		 *      entity.getMap().put("dept_id_start_value", l5_dept_id);
		 *      } else if (StringUtils.isNotBlank(l4_dept_id)) {
		 *      entity.getMap().put("dept_id_start_value", l4_dept_id);
		 *      } else if (StringUtils.isNotBlank(l3_dept_id)) {
		 *      entity.getMap().put("dept_id_start_value", l3_dept_id);
		 *      }
		 * 
		 *      entity.getMap().put("report_name_like", report_name_like);
		 *      entity.getMap().put("dept_name_like", dept_name_like);
		 *      entity.getMap().put("c_comm", c_comm);
		 * 
		 *      // 是否显示0销量
		 *      if ("1".equals(isShow)) {
		 *      entity.getMap().put("not_Show", "true");
		 *      }
		 * 
		 *      // 是否有效
		 *      entity.setIs_del(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);
		 * 
		 *      entity.getMap().put("oper_user_id", peProdUser.getId());
		 * 
		 *      if (StringUtils.isNotBlank(r3_code)) {
		 *      entity.getMap().put("r3_id", r3_code);
		 *      }
		 *      Date today = new Date();
		 *      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 *      Calendar ca = Calendar.getInstance();
		 *      ca.setTime(today); // someDate 为你要获取的那个月的时间
		 *      ca.set(Calendar.DAY_OF_MONTH, 1);
		 *      Date firstDate = ca.getTime();
		 *      ca.add(Calendar.MONTH, 1);
		 *      ca.add(Calendar.DAY_OF_MONTH, -1);
		 *      Date lastDate = ca.getTime();
		 *      entity.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
		 *      entity.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
		 *      if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
		 *      request.setAttribute("year", year);
		 *      request.setAttribute("month", month);
		 *      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 *      Date date = sdf.parse(year + "-" + month + "-01");
		 *      ca.setTime(date); // someDate 为你要获取的那个月的时间
		 *      ca.set(Calendar.DAY_OF_MONTH, 1);
		 *      firstDate = ca.getTime();
		 *      ca.add(Calendar.MONTH, 1);
		 *      ca.add(Calendar.DAY_OF_MONTH, -1);
		 *      lastDate = ca.getTime();
		 *      entity.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
		 *      entity.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
		 *      request.setAttribute("year_month", year + "年" + month + "月");
		 *      }
		 * 
		 *      getFacade().getKonkaMobileSailDataService().getkonkaMobileSailDataAndBillForSwitchSave(entity);
		 */
		return this.listSwitch(mapping, form, request, response);

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 销售单或者是发票单的list
	 * @throws Exception
	 */

	public ActionForward listSailOrBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");
		String store_id = (String) dynaBean.get("store_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String type = (String) dynaBean.get("type");
		String audit_state = (String) dynaBean.get("audit_state");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		KonkaMobileSailDataBill bill = new KonkaMobileSailDataBill();

		if (StringUtils.isNotBlank(user_id) && GenericValidator.isLong(user_id)) {
			entity.setReport_id(Long.valueOf(user_id));
			bill.getMap().put("user_id", user_id);
			PeProdUser puser = new PeProdUser();
			puser.setId(Long.valueOf(user_id));
			puser.setIs_del(0);
			puser = super.getFacade().getPeProdUserService().getPeProdUser(puser);
			request.setAttribute("user_name", puser.getUser_name());

		}
		if (StringUtils.isNotBlank(store_id) && GenericValidator.isLong(store_id)) {
			entity.setDept_id(Long.valueOf(store_id));
			bill.getMap().put("store_id", store_id);
		}
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(audit_state) && GenericValidator.isInt(audit_state)) {
			entity.setAudit_state(Integer.parseInt(audit_state));
			bill.setState(Integer.parseInt(audit_state));
		}
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月");
		Calendar ca = Calendar.getInstance();
		ca.setTime(today); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		entity.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
		entity.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
		bill.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
		bill.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
		request.setAttribute("this_time", df1.format(today));
		if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year)) {
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(year + "-" + month + "-01");
			ca.setTime(date); // someDate 为你要获取的那个月的时间
			ca.set(Calendar.DAY_OF_MONTH, 1);
			firstDate = ca.getTime();
			ca.add(Calendar.MONTH, 1);
			ca.add(Calendar.DAY_OF_MONTH, -1);
			lastDate = ca.getTime();
			bill.getMap().put("report_date_begin", df.format(firstDate) + " 00:00:00");
			bill.getMap().put("report_date_end", df.format(lastDate) + " 23:59:59");
			request.setAttribute("this_time", df1.format(date));
		}
		if (StringUtils.isNotEmpty(type) && "sail".equals(type)) {
			List<KonkaMobileSailData> konkaMobileSailDataList = super.getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataList(entity);
			if (null != konkaMobileSailDataList && konkaMobileSailDataList.size() > 0) {
				request.setAttribute("entityList", konkaMobileSailDataList);
			}
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_sail.jsp");
		}
		if (StringUtils.isNotEmpty(type) && "bill".equals(type)) {

			List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
					.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillAndAttachmentBySailList(bill);
			if (null != konkaMobileSailDataBillList && konkaMobileSailDataBillList.size() > 0) {
				request.setAttribute("entityList", konkaMobileSailDataBillList);
			}
			return new ActionForward("/../manager/admin/KonkaMobileSailDataBill/list_bill.jsp");
		}

		return this.listSwitch(mapping, form, request, response);

	}

	public ActionForward ListSalary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		KonkaMobileSailData entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getKonkaMobileSailDataService()
				.getkonkaMobileSailDataAndBillForSalaryCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, pager.getPageSize(), (String) entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;

		if (recordCount > 0) {
			entityList = super.getFacade().getKonkaMobileSailDataService().getkonkaMobileSailDataAndBillForSalaryList(
					entity);

		}

		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			m.put("rows", str);
		} else {
			m.put("rows", entityList);
		}

		JSONArray jsonArray = JSONArray.fromObject(m);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	public KonkaMobileSailData getKonkaMobileSailData(ActionMapping mapping, ActionForm form, HttpServletRequest request)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		String report_name_like = (String) dynaBean.get("report_name_like");
		String date_begin = (String) dynaBean.get("date_begin");
		String date_end = (String) dynaBean.get("date_end");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String excel_all = (String) dynaBean.get("excel_all");
		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String single_price_end = (String) dynaBean.get("single_price_end");
		String single_price_begin = (String) dynaBean.get("single_price_begin");

		String l3_dept_id = (String) dynaBean.get("l3_dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String c_comm = (String) dynaBean.get("c_comm");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		/**
		 * @val 状态 Audit_state
		 * @val 0上传中，
		 * @val 2 初审合格
		 * @val 4初审不合格
		 * @val 6初审通过并转单
		 * @val 8终审通过
		 * @val 10 终审不通过
		 * @val
		 * @val 有任何一个发票不合格，则整个记录不合格
		 * @val 所有的发票审核合格后，整个记录则为合格，并且才允许走下一个步骤
		 */
		String audit_state = (String) dynaBean.get("audit_state");
		String ext_audit_state = (String) dynaBean.get("ext_audit_state");

		// 是否显示0销量,0显示，1不显示
		String isShow = (String) dynaBean.get("isShow");
		String r3_code = (String) dynaBean.get("r3_code");

		// 型号模糊搜索
		String model_like = (String) dynaBean.get("model_like");

		KonkaMobileSailData entity = new KonkaMobileSailData();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(audit_state) && GenericValidator.isInt(audit_state)) {
			entity.setAudit_state(Integer.parseInt(audit_state));

		}
		if (StringUtils.isNotBlank(ext_audit_state) && GenericValidator.isInt(ext_audit_state)) {
			entity.getMap().put("ext_audit_state_back", ext_audit_state);
			if (0 == Integer.parseInt(ext_audit_state)) {
				entity.getMap().put("ext_audit_state_0_4", "yes");
			} else if (2 == Integer.parseInt(ext_audit_state)) {
				entity.getMap().put("ext_audit_state_2", "yes");
			} else if (6 == Integer.parseInt(ext_audit_state)) {
				entity.getMap().put("ext_audit_state_6_10", "yes");
			} else if (8 == Integer.parseInt(ext_audit_state)) {
				entity.getMap().put("ext_audit_state_8", "yes");
			} else {
				entity.setAudit_state(Integer.parseInt(ext_audit_state));
			}

			request.setAttribute("ext_audit_state", ext_audit_state);
		}
		if (StringUtils.isNotBlank(single_price_end)) {
			entity.getMap().put("single_price_end", single_price_end);
		}
		if (StringUtils.isNotBlank(single_price_begin)) {
			entity.getMap().put("single_price_begin", single_price_begin);
		}
		if (StringUtils.isNotBlank(model_like)) {
			entity.getMap().put("model_like", model_like);
		}

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

		// 数据级别判断开始
		Long __dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_job_id", peProdUser.getJob_id());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_job_id_", peProdUser.getJob_id());
			break;
		case 0:
			__dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			entity.getMap().put("filter_by_job_id_eq", peProdUser.getJob_id());
			break;
		default:
			// 出错
		}

		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("dept_id_start_value", l5_dept_id);
		} else if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("dept_id_start_value", l4_dept_id);
		} else if (StringUtils.isNotBlank(l3_dept_id)) {
			entity.getMap().put("dept_id_start_value", l3_dept_id);
		}

		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("report_name_like", report_name_like);
		entity.getMap().put("dept_name_like", dept_name_like);
		entity.getMap().put("c_comm", c_comm);

		// 是否显示0销量
		if ("1".equals(isShow)) {
			entity.getMap().put("not_Show", "true");
		}

		// 是否有效
		entity.setIs_del(StringUtils.isNotBlank(is_del) ? Integer.valueOf(is_del) : 0);

		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_id", r3_code);
		}

		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNotBlank(date_begin)) {
			//Date d = DateUtils.parseDate(date_begin + " 00:00:00", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_begin", date_begin + " 00:00:00");
		} else {
			// 判断是否有时间没有的话取当前一个月的时间
			entity.getMap().put("report_date_begin", mm_fmt.format(new Date()) + "-01 00:00:00");
			dynaBean.set("date_begin", mm_fmt.format(new Date()) + "-01");
		}
		if (StringUtils.isNotBlank(date_end)) {
		//	Date d = DateUtils.parseDate(date_end + " 23:59:59", new String[] { "yyy-MM-dd HH:mm:ss" });
			entity.getMap().put("report_date_end", date_end + " 23:59:59");
		} else {
			entity.getMap().put("report_date_end", fmt.format(new Date()));
			dynaBean.set("date_end", fmt1.format(new Date()));
		}

		return entity;
	}

	public KonkaMobileSailData getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String dept_id = request.getParameter("dept_id");
		String audit_state = request.getParameter("audit_state");
		String report_name_like = request.getParameter("report_name_like");
		String report_user_like = request.getParameter("report_user_like");
		String dept_name_like = request.getParameter("dept_name_like");
		String year = request.getParameter("year");
		String page = request.getParameter("page");

		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaMobileSailData entity = new KonkaMobileSailData();

		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");

		entity.getMap().put("report_date_begin", df.format(today) + "-01-01 00:00:00");
		entity.getMap().put("report_date_end", df.format(today) + "-12-31 23:59:59");
		if (StringUtils.isNotBlank(year)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			Date date = sdf.parse(year);
			entity.getMap().put("report_date_begin", df.format(date) + "-01-01 00:00:00");
			entity.getMap().put("report_date_end", df.format(date) + "-12-31 23:59:59");
		}
		// 分公司ID
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setSubcomp_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(audit_state) && GenericValidator.isInt(audit_state)) {
			entity.setAudit_state(Integer.parseInt(audit_state));
		} else {
			entity.setAudit_state(8);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setSubcomp_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(report_name_like)) {
			entity.getMap().put("report_name_like", report_name_like);
		}
		if (StringUtils.isNotBlank(report_user_like)) {
			entity.getMap().put("ywy_job_id_like", report_user_like);
		}
		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);
		}
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		entity.getMap().put("page", page);
		return entity;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		// String day_first = theFirstDayOfCurrentMonth();
		String year = df.format(today);

		//		
		// KonkaMobileSailData entity = new KonkaMobileSailData();
		// entity.getMap().put("date_begin", day_first);
		// entity.getMap().put("date_end", day_last);
		//        
		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("year", year);

		// allmap.put("date_begin", day_first);
		// allmap.put("date_end", day_last);

		// 位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);

		// 当前用户信息
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int max_dlevel = super.getMaxDLevel(ui.getId());
		if (max_dlevel == 9) {
			allmap.put("dept_id", "");
		} else {
			allmap.put("dept_id", ui.getDept_id());
		}

		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (StringUtils.isBlank(id) || !GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaMobileSailData konkaMobileSailData = new KonkaMobileSailData();
		konkaMobileSailData.setId(Long.valueOf(id));
		konkaMobileSailData = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(
				konkaMobileSailData);
		if (null == konkaMobileSailData) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		konkaMobileSailDataBill.setSail_id(Long.valueOf(id));
		// konkaMobileSailDataBill.getMap().put("ext_audit_state_0_4", "yes");// 待初审的记录
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
				.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillAndAttachmentList(
						konkaMobileSailDataBill);

		super.copyProperties(form, konkaMobileSailData);

		request.setAttribute("entity", konkaMobileSailData);

		if (null != konkaMobileSailDataBillList) {
			request.setAttribute("konkaMobileSailDataBillList", konkaMobileSailDataBillList);
		}

		return mapping.findForward("view");
	}
}
