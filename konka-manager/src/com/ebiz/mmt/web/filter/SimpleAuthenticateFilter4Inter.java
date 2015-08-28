package com.ebiz.mmt.web.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.domain.KonkaInterfaceIp;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * @author TUDP
 * @version 2014-9-22
 * 
 *          IHS接口访问较验
 * 
 */
public class SimpleAuthenticateFilter4Inter extends GenericFilterBean {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String loginPage;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
	        throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (StringUtils.isBlank(this.loginPage)) {
			logger.warn("loginPage is empty, it will redirect to welcome page.");
		}

		logger.info("inter_filter----------->coming in");
		String contextPath = request.getContextPath();
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		String licenses_sn = request.getParameter("licenses_sn");
		String user_id = request.getParameter("user_id");
		String user_key = request.getParameter("user_key");
		if (StringUtils.isBlank(licenses_sn)) {
			licenses_sn = request.getAttribute("licenses_sn") == null ? "" : (String) request
			        .getAttribute("licenses_sn");
		}

		if (StringUtils.isBlank(user_id)) {
			user_id = request.getAttribute("user_id") == null ? "" : (String) request.getAttribute("user_id");
		}

		if (StringUtils.isBlank(user_key)) {
			user_key = request.getAttribute("user_key") == null ? "" : (String) request.getAttribute("user_key");
		}

		if (StringUtils.isBlank(licenses_sn) || StringUtils.isBlank(user_id) || StringUtils.isBlank(user_key)) {
			response.sendRedirect(contextPath + this.loginPage + "?user_id=" + user_id + "&user_key=" + user_key
			        + "&licenses_sn=" + licenses_sn + "&error_msg="
			        + "licenses_sn-user_id-userk_key-all-can-not-be-empty");
			return;
		}

		ServletContext servletContext = request.getSession().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		Facade facade = (Facade) wac.getBean("facade");

		// 验证ip地址是否授权
		String req_ip = IpUtils.getRemortIP(request);
		KonkaInterfaceIp kip = new KonkaInterfaceIp();
		kip.setLicenses_sn(licenses_sn);
		List<KonkaInterfaceIp> kipList = facade.getKonkaInterfaceIpService().getKonkaInterfaceIpList(kip);
		if (null == kipList || kipList.size() == 0) {
			response.sendRedirect(contextPath + this.loginPage + "?user_id=" + user_id + "&user_key=" + user_key
			        + "&licenses_sn=" + licenses_sn + "&error_msg=" + "not-authorized-by-ip" + "");
			return;
		}

		// 可能绑定多个ip
		List<String> ipList = new ArrayList<String>();
		if (kipList.size() > 0) {
			for (KonkaInterfaceIp ip : kipList) {
				if (ip.getIp() != null) {
					ipList.add(ip.getIp());
				}
			}
		}

		if (!ipList.contains(req_ip)) {
			response.sendRedirect(contextPath + this.loginPage + "?user_id=" + user_id + "&user_key=" + user_key
			        + "&licenses_sn=" + licenses_sn + "&error_msg=" + "not-authorized-by-ip" + "");
			return;
		}

		// 验证user_id user_key licenses_sn 是否合法
		KonkaInterfaceBindsUser kbUser = new KonkaInterfaceBindsUser();
		kbUser.setUser_id(Long.valueOf(user_id));
		kbUser.setUser_key(user_key);
		kbUser.setLicenses_sn(licenses_sn);
		List<KonkaInterfaceBindsUser> kbUsersList = facade.getKonkaInterfaceBindsUserService()
		        .getKonkaInterfaceBindsUserList(kbUser);

		if (null == kbUsersList || kbUsersList.size() == 0 || kbUsersList.size() > 1) {
			response.sendRedirect(contextPath + this.loginPage + "?user_id=" + user_id + "&user_key=" + user_key
			        + "&licenses_sn=" + licenses_sn + "&error_msg=" + "user_id-user_key-licenses_sn-does-not-match"
			        + "");
			return;
		}

		// r3客户数据，r3客户联系人，客户细分类型3个接口 每天5-7点开放，其他接口时间不做限制
		if (StringUtils.endsWith(request.getRequestURI(), "/inter/service/KonkaCategoryTypeInterface.do")
		        || StringUtils.endsWith(request.getRequestURI(), "/inter/service/CmsCustomerInterface.do")
		        || StringUtils.endsWith(request.getRequestURI(), "/inter/service/KonkaR3ShopLinkInterface.do")) {
			// 每天的5-7点可以访问接口
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sf.format(date);
			String start_time = time + " 04:00:00";
			String end_time = time + " 07:59:59";

			SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sf2.format(date);

			Calendar cc = Calendar.getInstance();
			try {
				cc.setTime(sf2.parse(now));
				Long now_time = cc.getTime().getTime();
				cc.setTime(sf2.parse(start_time));
				Long s_time = cc.getTime().getTime();
				cc.setTime(sf2.parse(end_time));
				Long e_time = cc.getTime().getTime();
				if (now_time < s_time || now_time > e_time) {
					response.sendRedirect(contextPath + this.loginPage + "?user_id=" + user_id + "&user_key="
					        + user_key + "&licenses_sn=" + licenses_sn + "&error_msg="
					        + "The-interface-of-the-access-time-is-the-day-4:00--7:59" + "");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		chain.doFilter(request, response);

	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

}