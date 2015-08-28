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

import com.ebiz.mmt.domain.KonkaInterfaceIp;
import com.ebiz.mmt.domain.KonkaInterfaceLicenses;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class KonkaInterfaceIpAction extends BaseAction {
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
		String is_del = (String) dynaBean.get("is_del");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaInterfaceIp entity = new KonkaInterfaceIp();
		if (StringUtils.isNotBlank(licenses_sn_like)) {
			entity.getMap().put("licenses_sn_like", licenses_sn_like);

		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}

		Long recordCount = super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIpCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaInterfaceIp> entityList = super.getFacade().getKonkaInterfaceIpService()
		        .getKonkaInterfaceIpPaginatedList(entity);
		for (KonkaInterfaceIp konkaInterfaceIp : entityList) {
			PeProdUser pp = new PeProdUser();
			pp.setId(konkaInterfaceIp.getAdd_user_id());
			pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
			if (pp != null) {
				konkaInterfaceIp.getMap().put("add_user_name", pp.getUser_name());
			}
		}
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

		KonkaInterfaceIp entity = new KonkaInterfaceIp();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(id)) {

			// if (StringUtils.isNotBlank(licenses_sn)) {
			// KonkaInterfaceIp kl = new KonkaInterfaceIp();
			// kl.setLicenses_sn(licenses_sn);
			// Long count =
			// super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIpCount(kl);
			// if (count > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('该授权码已经被绑定！');history.back();}");
			// return null;
			// }
			// }

			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user.getId());
			super.getFacade().getKonkaInterfaceIpService().createKonkaInterfaceIp(entity);
			super.saveMessage(request, "entity.inserted");
		} else {

			// KonkaInterfaceIp kl = new KonkaInterfaceIp();
			// kl.setId(Long.valueOf(id));
			// kl =
			// super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIp(kl);
			// if (StringUtils.isNotBlank(licenses_sn)) {
			// if (!kl.getLicenses_sn().equalsIgnoreCase(licenses_sn)) {
			// KonkaInterfaceIp kl2 = new KonkaInterfaceIp();
			// kl2.setLicenses_sn(licenses_sn);
			// Long count =
			// super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIpCount(kl2);
			// if (count > 0) {
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('该授权码已经被绑定！');history.back();}");
			// return null;
			// }
			// }
			// }

			super.getFacade().getKonkaInterfaceIpService().modifyKonkaInterfaceIp(entity);
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

		KonkaInterfaceIp entity = new KonkaInterfaceIp();
		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaInterfaceIpService().removeKonkaInterfaceIp(entity);

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

		KonkaInterfaceIp entity = new KonkaInterfaceIp();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIp(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		dynaBean.set("licenses_sn", entity.getLicenses_sn());
		dynaBean.set("licenses_sn_2", entity.getLicenses_sn());

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

		KonkaInterfaceIp entity = new KonkaInterfaceIp();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIp(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		PeProdUser pp = new PeProdUser();
		pp.setId(entity.getAdd_user_id());
		pp = super.getFacade().getPeProdUserService().getPeProdUser(pp);
		if (pp != null) {
			dynaBean.set("add_user_name", pp.getUser_name());
		}

		return mapping.findForward("view");
	}

	public ActionForward selectLicenses_sn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String licenses_sn_like = (String) dynaBean.get("licenses_sn_like");
		KonkaInterfaceLicenses entity = new KonkaInterfaceLicenses();
		if (StringUtils.isNotBlank(licenses_sn_like)) {
			entity.getMap().put("licenses_sn_like", licenses_sn_like);
		}

		// List<String> snList = new ArrayList<String>();
		// KonkaInterfaceIp ki = new KonkaInterfaceIp();
		// List<KonkaInterfaceIp> kiList =
		// super.getFacade().getKonkaInterfaceIpService().getKonkaInterfaceIpList(ki);
		// if (kiList != null && kiList.size() > 0) {
		// for (KonkaInterfaceIp konkaInterfaceIp : kiList) {
		// snList.add(konkaInterfaceIp.getLicenses_sn());
		// }
		// }
		// if (snList.size() > 0) {
		// entity.getMap().put("sn_not_in", snList);
		// }

		entity.setInfo_state(0);// 未注销的
		entity.setIs_del(0);// 未删除的

		Long recordCount = super.getFacade().getKonkaInterfaceLicensesService().getKonkaInterfaceLicensesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaInterfaceLicenses> entityList = super.getFacade().getKonkaInterfaceLicensesService()
		        .getKonkaInterfaceLicensesPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/admin/KonkaInterfaceIp/selectlicenses_sn.jsp");
	}
}
