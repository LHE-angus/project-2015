package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
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

import com.ebiz.mmt.domain.KonkaInterfaceLicenses;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class KonkaInterfaceLicensesAction extends BaseAction {
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
		String org_name_like = (String) dynaBean.get("org_name_like");
		String is_del = (String) dynaBean.get("is_del");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		if (StringUtils.isNotBlank(licenses_sn_like)) {
			entity.getMap().put("licenses_sn_like", licenses_sn_like);

		}
		if (StringUtils.isNotBlank(org_name_like)) {
			entity.getMap().put("org_name_like", org_name_like);

		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}

		Long recordCount = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicensesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaInterfaceLicenses> entityList = super.getFacade().getKonkaInterfaceLicensesService()
		        .getKonkaInterfaceLicensesPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String licenses_sn = (String) dynaBean.get("licenses_sn");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());

		if (StringUtils.isEmpty(id)) {
			if (StringUtils.isNotBlank(licenses_sn)) {
				KonkaInterfaceLicenses kl = new KonkaInterfaceLicenses();
				kl.setLicenses_sn(licenses_sn);
				Long count = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicensesCount(kl);
				if (count > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('该授权码已经存在！');history.back();}");
					return null;
				}
			}
			super.getFacade().getKonkaInterfaceLicensesService().createKonkaInterfaceLicenses(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			KonkaInterfaceLicenses kl = new KonkaInterfaceLicenses();
			kl.setId(Long.valueOf(id));
			kl = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicenses(kl);
			if (StringUtils.isNotBlank(licenses_sn)) {
				if (!kl.getLicenses_sn().equalsIgnoreCase(licenses_sn)) {
					KonkaInterfaceLicenses kl2 = new KonkaInterfaceLicenses();
					kl2.setLicenses_sn(licenses_sn);
					Long count = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicensesCount(
					        kl2);
					if (count > 0) {
						super
						        .renderJavaScript(response,
						                "window.onload=function(){alert('该授权码已经存在！');history.back();}");
						return null;
					}
				}
			}

			super.getFacade().getKonkaInterfaceLicensesService().modifyKonkaInterfaceLicenses(entity);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaInterfaceLicensesService().removeKonkaInterfaceLicenses(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
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

		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicenses(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
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

		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicenses(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward validateLicenses_sn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String licenses_sn = (String) dynaBean.get("licenses_sn");
		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		entity.setLicenses_sn(licenses_sn);
		Long count = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicensesCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}
}
