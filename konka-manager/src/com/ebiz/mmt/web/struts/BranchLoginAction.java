package com.ebiz.mmt.web.struts;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.ebiz.mmt.domain.MmtUserInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Wang,XuLi
 * @Version 2010-01-25
 */
public class BranchLoginAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setCookies2RequestScope(request);

		return mapping.findForward("login");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		String pass_word = (String) dynaBean.get("pass_word");
		String verification_code = (String) dynaBean.get("verificationCode");
		String is_remember = (String) dynaBean.get("is_remember");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String validate_tag = (String) dynaBean.get("validate_tag");

		if (StringUtils.isBlank(user_name)) {
			String msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		}
		if (StringUtils.isEmpty(pass_word)) {
			String msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		}

		/* 是否验证验证码（validate_tag） */
		if (StringUtils.isBlank(validate_tag)) {
			if (StringUtils.isBlank(verification_code)) {
				String msg = super.getMessage(request, "login.failed.verificationCode.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='BranchLogin.do';}");
				return null;
			}
		}
		/* 是否验证验证码（validate_tag） */

		HttpSession session = request.getSession();

		if (!verification_code.equals((String) session.getAttribute("verificationCode"))) {
			String msg = super.getMessage(request, "login.failed.verificationCode.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		}

		if (null != user_name) {
			user_name = user_name.trim();
		}
		MmtUserInfo entity = new MmtUserInfo();
		entity.setUser_name(user_name);
		entity.setUser_state(2);
		if (null != super.getFacade().getMmtUserInfoService().getMmtUserInfo(entity)) {
			String msg = super.getMessage(request, "login.failed.username.locked");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		}

		entity.setUser_state(0);
		if (null == super.getFacade().getMmtUserInfoService().getMmtUserInfo(entity)) {
			String msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(pass_word)) {
			entity.setPass_word(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(pass_word));
			logger.info("########des_pass:{}", des.encrypt(pass_word));
		}
		MmtUserInfo userInfo = super.getFacade().getMmtUserInfoService().getMmtUserInfo(entity);
		if (null == userInfo) {
			String msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');location.href='BranchLogin.do';}");
			return null;
		} else {

			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null && StringUtils.isNotBlank(is_remember)) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("user_name");
				cg.addCookie(response, URLEncoder.encode(user_name, Constants.SYS_ENCODING));
				cg.setCookieName("password");
				cg.addCookie(response, URLEncoder.encode(entity.getPass_word(), Constants.SYS_ENCODING));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, Constants.SYS_ENCODING));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("user_name");
				cg.removeCookie(response);
				cg.setCookieName("password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}

			// modify user_info in session starting...
			session.setAttribute(Constants.MMT_USER_INFO, userInfo);
			// modify user_info in session ended.

			if (StringUtils.isNotBlank(returnUrl)) {
				response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
				return null;
			}
			if (1 == userInfo.getUser_type()) {
				return new ActionForward("/branch/manager/Frames.do", true);
			} else {
				String msg = super.getMessage(request, "login.failed.username.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='BranchLogin.do';}");
				return null;
			}
		}
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);

		if (null != session) {
			MmtUserInfo userInfo = (MmtUserInfo) session.getAttribute(Constants.MMT_USER_INFO);

			if (null != userInfo) {
			}

			session.invalidate();
		}
		setCookies2RequestScope(request);

		return mapping.findForward("login");
	}

	private void setCookies2RequestScope(HttpServletRequest request) throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "user_name");
		Cookie password = WebUtils.getCookie(request, "password");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember");

		if (null != user_name) {
			request.setAttribute("user_name", URLDecoder.decode(user_name.getValue(), Constants.SYS_ENCODING));
		}
		if (null != password) {
			request.setAttribute("password", DEFAULT_PASSWORD);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember", URLDecoder.decode(is_remember.getValue(), Constants.SYS_ENCODING));
		}
	}

}
