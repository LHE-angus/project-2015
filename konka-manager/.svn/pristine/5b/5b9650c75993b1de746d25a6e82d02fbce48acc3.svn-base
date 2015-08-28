package com.sso.ssoclient;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.landray.sso.client.EKPSSOUserData;

//该文件不用修改
public class EKPSSOClientAuthenticationFilter implements Filter {

	private static final Log logger = LogFactory
			.getLog(EKPSSOClientAuthenticationFilter.class);

	private AutoLoginHelper autoLoginHelper = null;

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
		autoLoginHelper = new AutoLoginHelper();
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		if (userData.isUserChanged()) {
			HttpServletResponse response = (HttpServletResponse) res;
			String username = userData.getCurrentUsername();
			userData.acceptUserChange();
			if (username != null && username.length() > 0) {
				if (autoLoginHelper.doAutoLogin(username, request.getSession()) == null) {
					logger.warn("根据用户名：" + username + "找不到相应的用户，跳转到无权限页面。AutoLoginHelper.doAutoLogin失败");
				}
				logger.debug("SSO成功执行登录操作，登录用户：" + username);
			} else {
				autoLoginHelper.doLogout(request, response);
				logger.debug("成功执行注销操作。");
			}
		}
		chain.doFilter(req, res);
	}
}