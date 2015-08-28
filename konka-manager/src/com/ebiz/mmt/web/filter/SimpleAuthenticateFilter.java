package com.ebiz.mmt.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Hui,Gang
 * @version 2011-10-24 下午4:54:55
 */
public class SimpleAuthenticateFilter extends GenericFilterBean {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String sessionKey;

	private String loginPage;
	private String logonStrings;

	public String getLogonStrings() {
		return logonStrings;
	}

	public void setLogonStrings(String logonStrings) {
		this.logonStrings = logonStrings;
	}

//	private static final List<String> filterList = new ArrayList<String>() {
//		{
//			add("/customer/manager/JxcKonkaOrderRegister.do?method=add");
//		}
//	};

	// private final static String[] filterUrls = new String[] {
	// "/customer/manager/JxcKonkaOrderRegister.do?method=addWithUsername" };

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		logger.info("invoke SimpleAuthenticateFilter.doFilter()");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String requestUrl = request.getRequestURL().toString();
		
		
		
		if (StringUtils.isBlank(this.loginPage)) {
			logger.warn("loginPage is empty, it will redirect to welcome page.");
		}
		logger.info("requestURI is {}, contextPath is {}", request.getRequestURI(), request.getContextPath());

		String method = request.getParameter("method");
		String cust_id = request.getParameter("CUSTID");

		// 人员统计斌状图不监听session
		if ("getPersonJsonForBranch".equals(method)) {
			chain.doFilter(request, response);
		}
			
		// 过滤URL1：判断和处理是否由业务员点击过来的订单等级处理
		if (StringUtils.endsWith(request.getRequestURI(), "/customer/manager/JxcKonkaOrderRegister.do")
				&& null != cust_id && GenericValidator.isLong(cust_id)) {
			chain.doFilter(request, response);
			return;
		}
		if (logonStrings != null && !"".equals(logonStrings)) {
			String[] ls = logonStrings.split(",");
			for (String rr : ls) {
				if (StringUtils.endsWithIgnoreCase(requestUrl, rr)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		
		String contextPath = request.getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}

		if (!this.isAuthenticated(request, this.sessionKey)) {
			response.sendRedirect(contextPath + this.loginPage);
			return;
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
		PeProdUser entpUser = (PeProdUser) session.getAttribute(sessionKey);
		if (null == entpUser) {
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