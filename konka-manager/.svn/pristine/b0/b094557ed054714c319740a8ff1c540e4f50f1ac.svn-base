package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillCstSatform;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2012-04-12
 */
public class KonkaXxSellBillCstSatformAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String sell_date_begin = (String) dynaBean.get("sell_date_begin");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String return_v_state = (String) dynaBean.get("return_v_state");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		// 取用户角色并判断是不是分公司级别的角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
		konkaXxZmdUsers.setUser_id(ui.getId());
		konkaXxZmdUsers.getRow().setCount(2);
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(
				konkaXxZmdUsers);
		if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
			String m = getMessage(request, "konka.zmd.userinfo.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}
		if (konkaXxZmdUsersList.size() == 2) {
			String m = getMessage(request, "konka.zmd.userinfo.too.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
			return null;
		}

		// 查询专卖店信息
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.getRow().setCount(2);
		konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
		List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
		if (1 != konkaXxZmdList.size()) {
			String msg = super.getMessage(request, "konka.zmd.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxSellBill entity = new KonkaXxSellBill();
		if (StringUtils.isNotBlank(return_v_state) && "1".equals(return_v_state)) {
			entity.getMap().put("return_v_had", true);
		} else {
			entity.getMap().put("return_v_not", true);
		}
		if (StringUtils.isNotBlank(sell_date_begin)) {
			entity.getMap().put("sell_date_begin", sell_date_begin);
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end);
		}

		entity.setDept_id(konkaXxZmd.getDept_id());
		entity.setZmd_id(konkaXxZmd.getZmd_id());
		// 销售单状态 --->40用户已确认收货
		entity.getMap().put("sell_state_ge", 40);
		entity.getMap().put("order_by_dept", true); // 设置排序

		Long recordCount = getFacade().getKonkaXxSellBillService().getKonkaXxSellBillCountForJs(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxSellBill> entityList = getFacade().getKonkaXxSellBillService()
				.getKonkaXxSellBillForJSPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		if (!GenericValidator.isLong(sell_bill_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 订单信息
		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill = getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(konkaXxSellBill);
		if (null == konkaXxSellBill) {
			return null;
		}
		super.copyProperties(form, konkaXxSellBill);

		// 分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(konkaXxSellBill.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		// 订单明细
		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);
		request.setAttribute("konkaXxSellBillDetailsList", konkaXxSellBillDetailsList);

		// 查询满意信息
		KonkaXxSellBillCstSatform konkaXxSellBillCstSatform = new KonkaXxSellBillCstSatform();
		konkaXxSellBillCstSatform.setSell_bill_id(konkaXxSellBill.getSell_bill_id());
		List<KonkaXxSellBillCstSatform> konkaXxSellBillCstSatformList = super.getFacade()
				.getKonkaXxSellBillCstSatformService().getKonkaXxSellBillCstSatformList(konkaXxSellBillCstSatform);
		request.setAttribute("konkaXxSellBillCstSatformList", konkaXxSellBillCstSatformList);

		request.setAttribute("queryString", super.serialize(request, "sell_bill_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String queryString = (String) dynaBean.get("queryString");

		if (StringUtils.isBlank(sell_bill_id)) {
			String msg = super.getMessage(request, "errors.param");
			;
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取会话中的用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if (null == ui) {
			String msg = super.getMessage(request, "konka.xx.zmd.session.userinfo.none");
			;
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 订单明细
		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.setSell_bill_id(Long.valueOf(sell_bill_id));
		List<KonkaXxSellBillDetails> konkaXxSellBillDetailsList = getFacade().getKonkaXxSellBillDetailsService()
				.getKonkaXxSellBillDetailsList(konkaXxSellBillDetails);

		List<KonkaXxSellBillCstSatform> konkaXxSellBillCstSatformList = new ArrayList<KonkaXxSellBillCstSatform>();
		for (KonkaXxSellBillDetails t1 : konkaXxSellBillDetailsList) {
			KonkaXxSellBillCstSatform t2 = new KonkaXxSellBillCstSatform();
			t2.setSell_bill_id(Long.valueOf(sell_bill_id));
			t2.setMd_name(t1.getMd_name());
			t2.setTitle((String) dynaBean.get(t1.getSell_bill_details_id() + "_title"));
			t2.setMark_star(Integer.valueOf((String) dynaBean.get(t1.getSell_bill_details_id() + "_mark_star")));
			t2.setPros((String) dynaBean.get(t1.getSell_bill_details_id() + "_pros"));
			t2.setCons((String) dynaBean.get(t1.getSell_bill_details_id() + "_cons"));
			t2.setContent((String) dynaBean.get(t1.getSell_bill_details_id() + "_content"));
			konkaXxSellBillCstSatformList.add(t2);
		}

		KonkaXxSellBill konkaXxSellBill = new KonkaXxSellBill();
		konkaXxSellBill.setSell_state(70L);
		konkaXxSellBill.setSell_bill_id(Long.valueOf(sell_bill_id));
		konkaXxSellBill.setReturn_v_date(new Date());
		konkaXxSellBill.setReturn_v_user_id(ui.getId());
		konkaXxSellBill.setReturn_v_real_name(ui.getReal_name());

		super.getFacade().getKonkaXxSellBillCstSatformService().createKonkaXxSellBillCstSatformListAndSellBill(
				konkaXxSellBillCstSatformList, konkaXxSellBill);
		saveMessage(request, "entity.inserted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
