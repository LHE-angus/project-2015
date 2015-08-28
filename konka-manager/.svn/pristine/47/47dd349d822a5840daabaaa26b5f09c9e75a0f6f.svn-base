package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class KonkaInterfaceAccessLogAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String licenses_sn_like = (String) dynaBean.get("licenses_sn_like");
		String req_mod_name_like = (String) dynaBean.get("req_mod_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaInterfaceAccessLog entity = new KonkaInterfaceAccessLog();
		if (StringUtils.isNotBlank(licenses_sn_like)) {
			entity.getMap().put("licenses_sn_like", licenses_sn_like);

		}
		if (StringUtils.isNotBlank(req_mod_name_like)) {
			entity.getMap().put("req_mod_name_like", req_mod_name_like);

		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		Long recordCount = super.getFacade().getKonkaInterfaceAccessLogService()
		        .getKonkaInterfaceAccessLogCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaInterfaceAccessLog> entityList = super.getFacade().getKonkaInterfaceAccessLogService()
		        .getKonkaInterfaceAccessLogForUserNamePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		KonkaInterfaceAccessLog entity = new KonkaInterfaceAccessLog();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaInterfaceAccessLogService().getKonkaInterfaceAccessLog(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		if (entity.getUser_id() != null) {
			PeProdUser p = new PeProdUser();
			p.setId(entity.getUser_id());
			p = super.getFacade().getPeProdUserService().getPeProdUser(p);
			if (p != null) {
				dynaBean.set("user_name", p.getUser_name());
			}

		}

		return mapping.findForward("view");
	}

}
