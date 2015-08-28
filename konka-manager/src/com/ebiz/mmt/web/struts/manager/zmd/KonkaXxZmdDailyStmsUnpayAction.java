package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxZmdDailyStms;
import com.ebiz.mmt.domain.KonkaXxZmdDailyStmsDetail;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2012-03-20
 */
public class KonkaXxZmdDailyStmsUnpayAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_eq_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() == 400) {
					role_id_eq_400 = true;
				}
			}
		}

		if (!role_id_eq_400) { // 专卖店人员
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdDailyStms entity = new KonkaXxZmdDailyStms();

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

		entity.setStm_type(0);

		Long recordCount = super.getFacade().getKonkaXxZmdDailyStmsService().getKonkaXxZmdDailyStmsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxZmdDailyStms> entityList = super.getFacade().getKonkaXxZmdDailyStmsService()
				.getKonkaXxZmdDailyStmsPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward showDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String stm_id = (String) dynaBean.get("stm_id");

		if (!GenericValidator.isLong(stm_id)) {
			saveError(request, "errors.long", stm_id);
			return mapping.findForward("list");
		}

		KonkaXxZmdDailyStms stms = new KonkaXxZmdDailyStms();
		stms.setId(Long.valueOf(stm_id));

		stms = super.getFacade().getKonkaXxZmdDailyStmsService().getKonkaXxZmdDailyStms(stms);

		if (null == stms) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		request.setAttribute("stm_title", stms.getStm_title());

		KonkaXxZmdDailyStmsDetail entity = new KonkaXxZmdDailyStmsDetail();
		entity.setStm_id(Long.valueOf(stm_id));

		List<KonkaXxZmdDailyStmsDetail> entityList = super.getFacade().getKonkaXxZmdDailyStmsDetailService()
				.getKonkaXxZmdDailyStmsDetailList(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("today_total_money", stms.getToday_total_money().toString());
		request.setAttribute("today_total_counts", stms.getToday_total_counts().toString());
		request.setAttribute("his_total_money", stms.getHis_total_money().toString());
		request.setAttribute("remarks", stms.getStm_remarks());

		return mapping.findForward("view");
	}

	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toWord(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
