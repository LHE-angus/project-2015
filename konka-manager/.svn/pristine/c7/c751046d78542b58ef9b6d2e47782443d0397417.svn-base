package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaSendMailUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author TUDP
 * @version 2013-11-02
 */
public class KonkaSendMailAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String info_type = (String) dynaBean.get("info_type");

		dynaBean.set("info_type", info_type);
		dynaBean.set("send_type", 1);
		dynaBean.set("state", 1);
		dynaBean.set("order_value", 0);

		KonkaSendMailUser entity = new KonkaSendMailUser();
		entity.setInfo_type(Integer.valueOf(info_type));
		List<KonkaSendMailUser> entityList = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUserList(
		        entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaSendMailUser entity = new KonkaSendMailUser();
		entity.setId(new Long(id));
		entity = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUser(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser userInfo = super.getSessionUserInfo(request);
		KonkaSendMailUser entity = new KonkaSendMailUser();
		super.copyProperties(entity, form);

		entity.setAdd_user_id(userInfo.getId());
		entity.setAdd_user_name(userInfo.getReal_name());

		if (entity.getId() == null) {
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaSendMailUserService().createKonkaSendMailUser(entity);
			saveMessage(request, "entity.inserted");

		} else {
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaSendMailUserService().modifyKonkaSendMailUser(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSendMailUser entity = new KonkaSendMailUser();
			entity.setId(new Long(id));
			getFacade().getKonkaSendMailUserService().removeKonkaSendMailUser(entity);

			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaSendMailUser entity = new KonkaSendMailUser();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaSendMailUserService().removeKonkaSendMailUser(entity);

			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaSendMailUser entity = new KonkaSendMailUser();
		entity.setId(new Long(id));
		entity = super.getFacade().getKonkaSendMailUserService().getKonkaSendMailUser(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		return mapping.findForward("view");
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaSendMailUser entity = new KonkaSendMailUser();
			entity.setId(new Long(id));
			getFacade().getKonkaSendMailUserService().removeKonkaSendMailUser(entity);
		}

		super.renderText(response, "success");
		return null;
	}
}
