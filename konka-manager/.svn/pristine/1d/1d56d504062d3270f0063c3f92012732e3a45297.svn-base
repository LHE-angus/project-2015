package com.ebiz.mmt.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Liu,AiZHou
 */
public class SimpleAuthenticateFilter4Zxmall extends GenericFilterBean {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String sessionKey;

	private String loginPage;

	private String keySeqError;

	private String logonStrings;

	public String getLogonStrings() {
		return logonStrings;
	}

	public void setLogonStrings(String logonStrings) {
		this.logonStrings = logonStrings;
	}

	private final static String[] filterUrls = new String[] { "/customer/manager/JxcKonkaOrderRegister.do?method=addWithUsername" };

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	        throws IOException, ServletException {

		logger.info("invoke SimpleAuthenticateFilter.doFilter()");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String requestUrl = request.getRequestURL().toString();

		if (logonStrings != null && !"".equals(logonStrings)) {
			String[] ls = logonStrings.split(","); 
			for (String rr : ls) {
				if (StringUtils.endsWithIgnoreCase(requestUrl, rr)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}

		boolean no_auth = false;
		for (String url : filterUrls) {
			if (StringUtils.endsWithIgnoreCase(requestUrl, url)) {
				no_auth = true;
				break;
			} else if (url.indexOf("?") > -1) {
				if (StringUtils.endsWithIgnoreCase(requestUrl, StringUtils.substringBefore(url, "?"))) {
					String requestParams = StringUtils.substringAfter(url, "?");

					for (String kv : requestParams.split("&|(&amp;)")) {
						if (StringUtils.isNotBlank(kv) && StringUtils.contains(kv, "=")) {
							String k = kv.split("=")[0];
							String v = kv.split("=")[0];

							if (StringUtils.equals(request.getParameter(k), v)) {
								no_auth = true;
								break;
							}
						}
					}
				}
			}
		}
		if (no_auth) {
			chain.doFilter(request, response);
			return;
		}

		if (StringUtils.isBlank(this.loginPage)) {
			logger.warn("loginPage is empty, it will redirect to welcome page.");
		}
		logger.info("requestURI is {}, contextPath is {}", request.getRequestURI(), request.getContextPath());

		String contextPath = request.getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		if (!this.isAuthenticated(request, this.sessionKey)) {
			response.sendRedirect(contextPath + this.loginPage);
			return;
		}

		// 验证重复登录
		HttpSession session = request.getSession(false);
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");

		HttpSession acSession = (HttpSession) this.getServletContext().getAttribute(ecUser.getId().toString());
		if (null != acSession) {
			EcUser eu = (EcUser) session.getAttribute("zxmall");
			EcUser ac_eu = (EcUser) acSession.getAttribute("zxmall");
			if ((!session.getId().equals(acSession.getId())) && eu.getId().longValue() == ac_eu.getId().longValue()) {

				Date loginDate = (Date) acSession.getAttribute("loginDate");

				request.setAttribute("userName", eu.getUser_name());
				request.setAttribute("loginIp", acSession.getAttribute("loginIp"));
				request.setAttribute("loginDate", DateFormatUtils.format(loginDate, "yyyy-MM-dd HH:mm:ss"));
				session.invalidate();

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/repeatLogin.jsp");
				rd.forward(request, response);

				return;
			}
		}
		 
		 
		if(ecUser.getIs_act()!=null&&ecUser.getIs_act().intValue()!=0){  
			if(requestUrl.indexOf("zxmall/center/RegUser.do")==-1&&requestUrl.indexOf("KonkaGroupPeArticleInfo")==-1){
			response.sendRedirect(contextPath + "/zxmall/center/RegUser.do?"); 
			return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean isAuthenticated(HttpServletRequest request, String sessionKey) {

		if (StringUtils.isBlank(sessionKey)) {
			logger.info("session key is empty!");
			return false;
		}
		HttpSession session = request.getSession(false);
		if (null == session) {
			return false;
		}
		if (null == session.getAttribute(sessionKey)) {
			return false;
		}
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		if (null == ecUser) {
			return false;
		}
		String requestURI = request.getRequestURI();
		String ctx = request.getContextPath();
		if (null == ctx) {
			return false;
		} else if ("/".equals(ctx)) {
			ctx = "";
		}
		if (null == requestURI) {
			return false;
		}

		return true;
	} 
	public String getKeySeqError() {
		return keySeqError;
	}

	public void setKeySeqError(String keySeqError) {
		this.keySeqError = keySeqError;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

}