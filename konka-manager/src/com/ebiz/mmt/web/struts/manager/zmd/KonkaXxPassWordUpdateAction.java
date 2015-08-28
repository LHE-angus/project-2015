package com.ebiz.mmt.web.struts.manager.zmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author hu hao
 *@version 2012-3-22
 */
public class KonkaXxPassWordUpdateAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (isCancelled(request))
			return unspecified(mapping, form, request, response);
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return mapping.findForward("input");
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String old_password = (String) dynaBean.get("old_password");
		String new_password = (String) dynaBean.get("new_password");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PeProdUser entity = new PeProdUser();
		entity.setId(ui.getId());
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(old_password));

		if (null == super.getFacade().getPeProdUserService().getPeProdUser(entity)) {
			saveError(request, "password.old.invalid");
			return this.list(mapping, form, request, response);
		}

		entity.setPass_word(des.encrypt(new_password));
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "password.updated.success");

		String msg = null;
		msg = super.getMessage(request, "password.updated.success.info");
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.top.location.href='"
		        + super.getCtxPath(request) + "/login.do?method=logout'}");

		// StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append(mapping.findForward("success").getPath());
		// pathBuffer.append("&mod_id=" + mod_id);
		// ActionForward forward = new ActionForward(pathBuffer.toString(),
		// true);
		return null;
	}
}
