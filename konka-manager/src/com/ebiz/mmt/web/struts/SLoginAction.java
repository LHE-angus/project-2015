package com.ebiz.mmt.web.struts;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.customer.BaseClientJxcAction;
import com.ebiz.mmt.web.util.DESPlus;

public class SLoginAction extends BaseClientJxcAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.login(mapping, form, request, response);
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
		// super.renderHtml(response, "Bad request: Not a post request.",
		// "Error.");
		// return null;
		// }

		DynaBean dynaBean = (DynaBean) form;
		String __username = (String) dynaBean.get("__username");
		String __password = (String) dynaBean.get("__password");
		String __return_url = (String) dynaBean.get("__return_url");

		HttpSession session = request.getSession();

		if (null != session.getAttribute(Constants.CUSTOMER_USER_INFO)
				|| null != session.getAttribute(Constants.USER_INFO)) {
			response.sendRedirect(__return_url);
			return null;
		}

		if (StringUtils.isBlank(__username) || StringUtils.isBlank(__username) || "null".equalsIgnoreCase(__username)
				|| "null".equalsIgnoreCase(__password)) {
			super.renderHtml(response, "Bad request: Request parameter is empty.", "Error.");
			return null;
		}

		if (StringUtils.isBlank(__return_url)) {
			super.renderHtml(response, "Bad request: No target url.", "Error.");
			return null;
		}

		logger.info("User_name:{}, Pass_word:{} : {}", new String[] { __username, __password, __return_url });
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {

			__username = URLDecoder.decode(__username, "UTF-8");
			__password = URLDecoder.decode(__password, "UTF-8");
			__return_url = URLDecoder.decode(__return_url, "UTF-8");

			logger.info("User_name:{}, Pass_word:{} : {}", new String[] { __username, __password, __return_url });
		}

		PeProdUser user = new PeProdUser();
		user.setUser_name(__username.trim());
		user.setIs_del(0);
		user.setPass_word(new DESPlus().encrypt(__password));
		user = getFacade().getPeProdUserService().getPeProdUser(user);

		if (null == user) {
			super.renderHtml(response, "Bad request: username or password is not valid.", "Error.");
			return null;
		}

		session.setAttribute("userMobile", user);
		if (user.getCust_id() == null) {
			session.setAttribute(Constants.USER_INFO, user);
		} else {
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		}

		response.sendRedirect(__return_url);
		return null;
	}

	public ActionForward loginOnGet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String __username = (String) dynaBean.get("__username");
		String __password = (String) dynaBean.get("__password");
		String __return_url = (String) dynaBean.get("__return_url");

		HttpSession session = request.getSession();

		if (null != session.getAttribute(Constants.CUSTOMER_USER_INFO)
				|| null != session.getAttribute(Constants.USER_INFO)) {
			// response.sendRedirect(__return_url);
			// return null;
		}

		if (StringUtils.isBlank(__username) || StringUtils.isBlank(__password)) {
			super.renderHtml(response, "Bad request: Request parameter is empty.", "Error.");
			return null;
		}

		logger.info("User_name:{}, Pass_word:{} : {}", new String[] { __username, __password, __return_url });

		__username = URLDecoder.decode(__username, "UTF-8");
		__password = URLDecoder.decode(__password, "UTF-8");
		__return_url = URLDecoder.decode(__return_url, "UTF-8");

		logger.info("After decode. User_name:{}, Pass_word:{} : {}", new String[] { __username, __password,
				__return_url });

		PeProdUser user = new PeProdUser();
		user.setUser_name(__username.trim());
		user.setIs_del(0);
		user.setPass_word(new DESPlus().encrypt(__password));
		user = getFacade().getPeProdUserService().getPeProdUser(user);

		if (null == user) {
			super.renderHtml(response, "Bad request: username or password is not valid.", "Error.");
			return null;
		}

		if (user.getCust_id() == null) {
			session.setAttribute(Constants.USER_INFO, user);
		} else {
			session.setAttribute(Constants.CUSTOMER_USER_INFO, user);
		}

		response.sendRedirect(__return_url);
		return null;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("isDebugMode", super.isDebugMode(request));
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.CUSTOMER_USER_INFO);
			session.removeAttribute(Constants.ROLE_INFO);
			session.removeAttribute(Constants.ROLE_INFO_LIST);
			session.invalidate();
		}
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));

		super.getKonkaDeptListOfUser(55L, true);

		super.renderHtml(response, "Login out success!");
		return null;
	}

	@Override
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
