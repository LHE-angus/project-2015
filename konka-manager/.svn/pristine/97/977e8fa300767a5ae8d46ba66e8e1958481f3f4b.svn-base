package com.ebiz.mmt.web.struts.customer;

import java.util.regex.Pattern;

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
 * @author Hu,Hao
 * @version 2013-06-11
 * 
 * @author zhou
 * @version 2014-11-11 光棍节
 */
/**
 * 
 * 客户端下的
 * 
 */
public class JxcPasswardUpdateAction extends BaseClientJxcAction {

	private static final String regEx = "^(?![a-zA-Z]+$)(?!\\d+$).{6,15}$";

	@Override
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

		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);

		if (!p.matcher(new_password).matches()) {
			saveDirectlyError(request, "新密码要求6-15位,而且要包括字母和数字.");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		DESPlus des = new DESPlus();
		PeProdUser entity = new PeProdUser();
		entity.setId(ui.getId());
		entity.setPass_word(des.encrypt(old_password));
		if (null == super.getFacade().getPeProdUserService().getPeProdUser(entity)) {
			saveError(request, "password.old.invalid");
			return this.list(mapping, form, request, response);
		}
		entity.setPass_word(null);
		entity.setPass_word(des.encrypt(new_password));
		super.getFacade().getPeProdUserService().modifyPeProdUser(entity);

		saveMessage(request, "password.updated.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}


}
