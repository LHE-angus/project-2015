package com.ebiz.mmt.web.struts.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-06-14
 */
public class UpDateBankAction extends BaseClientJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		EcUser ecUser = new EcUser();
		ecUser.setC_id(ui.getId());
		ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
		super.copyProperties(form, ecUser);
		if (null == ecUser) {
			super.saveError(request, "对不起！该R3客户没有绑定触网账号！");
			return null;
		} else {
			dynaBean.set("user_id", ecUser.getId());
		}

		return new ActionForward("/../customer/UpDateBankAction/form.jsp");
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
		String mod_id = (String) dynaBean.get("mod_id");
		String user_id = (String) dynaBean.get("user_id");
		String pwd = (String) dynaBean.get("pwd");
		String newpwd = (String) dynaBean.get("newpwd");
		String repwd = (String) dynaBean.get("repwd");

		EcUser ecUser = new EcUser();
		ecUser.setId(Long.valueOf(user_id));
		ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);

		String msg = "";
		if (!StringUtils.isNotBlank(pwd)) {
			msg = "原密码不能为空！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href=\""
			        + super.getCtxPath(request) + "/customer/manager/UpDateBank.do?mod_id=200000300\";}");
			return null;
		}
		if (!StringUtils.isNotBlank(newpwd)) {
			msg = "新原密码不能为空！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href=\""
			        + super.getCtxPath(request) + "/customer/manager/UpDateBank.do?mod_id=200000300\";}");
			return null;
		}
		DESPlus des = new DESPlus();
		if (!des.encrypt(pwd).equals(ecUser.getPass_word())) {
			msg = "原密码不正确！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href=\""
			        + super.getCtxPath(request) + "/customer/manager/UpDateBank.do?mod_id=200000300\";}");
			return null;
		}
		if (!newpwd.equals(repwd)) {
			msg = "两次输入的密码不一致,请重新输入！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href=\""
			        + super.getCtxPath(request) + "/customer/manager/UpDateBank.do?mod_id=200000300\";}");
			return null;
		}
		super.saveMessage(request, "entity.updated");
		super.copyProperties(ecUser, form);
		ecUser.setId(Long.valueOf(user_id));
		ecUser.setPass_word(des.encrypt(newpwd));
		super.getFacade().getEcUserService().modifyEcUser(ecUser);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;

	}
}
