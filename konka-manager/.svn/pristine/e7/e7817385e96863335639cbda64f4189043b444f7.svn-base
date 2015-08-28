package com.ebiz.mmt.web.struts.m;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;
import org.springframework.web.util.WebUtils;

import com.ebiz.mmt.domain.KonkaMobileLoginFlag;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Xing,XiuDong
 * @date 2013-04-13
 */
public class LoginAndroidAction extends BaseAction {
	private static final String DEFAULT_PASSWORD_MOBILE = "......";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.login(mapping, form, request, response);
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginAndroidReturn ret = new LoginAndroidReturn();
		logger.info("----request.getMethod()-->{}", request.getMethod());
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")
				&& StringUtils.isBlank(request.getParameter("jsonpcallback"))) {
			ret.setStatus(1006);
			ret.setMsg("Request method is not valid.");
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;

		String user_name = (String) dynaBean.get("username");
		String password = (String) dynaBean.get("password");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//user_name="测试业务员";
		//password="0";
		String logintype = (String) dynaBean.get("logintype");
		// String verificationCode = (String) lazyForm.get("verificationCode");
		// String is_remember = (String) lazyForm.get("is_remember_mobile");

		if (StringUtils.isBlank(user_name)) {
			ret.setStatus(1001);
			ret.setMsg(getMessage(request, "login.failed.username.isEmpty"));
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			return null;
		}

		if (StringUtils.isBlank(password)) {
			ret.setStatus(1002);
			ret.setMsg(getMessage(request, "login.failed.password.isEmpty"));
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			return null;
		}
		//用户名解密
		DESPlus des = new DESPlus();
		if(StringUtils.isNotBlank(android_version)&& Long.valueOf(android_version)>37)
		{
			user_name=new String(des.decrypt(user_name).getBytes("iso-8859-1"),"utf-8");
		}
		if(StringUtils.isNotBlank(ios_version)&& Long.valueOf(ios_version)>317)
		{
			user_name=new String(user_name.getBytes("iso-8859-1"),"utf-8");
		}
		HttpSession session = request.getSession();

		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name.trim());
		entity.setIs_del(0);
		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			ret.setStatus(1003);
			ret.setMsg(getMessage(request, "login.failed.username.invalid"));
			ret.setUser(entity);
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			return null;
		} else if (userInfoList.size() > 1) {
			ret.setStatus(1004);
			ret.setMsg(getMessage(request, "login.failed.username.repeat"));
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "password_mobile");
		if (null != passwordCookie && DEFAULT_PASSWORD_MOBILE.equals(password)) {
			entity.setPass_word(passwordCookie.getValue());
		} else {
			if(StringUtils.isNotBlank(ios_version)&& Long.valueOf(ios_version)>37){
				entity.setPass_word(password);
			}else
			if(StringUtils.isNotBlank(android_version)&& Long.valueOf(android_version)>37)
			{
				entity.setPass_word(password);
			}else {
				entity.setPass_word(des.encrypt(password));
			}
			
		}
		PeProdUser userInfo = getFacade().getPeProdUserService().getPeProdUser(entity);
		if (null == userInfo) {
			ret.setStatus(1005);
			ret.setMsg(getMessage(request, "login.failed.password.invalid"));
			super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString());
			/*记录登陆日志*/
			try{
				OperLog t = new OperLog();
				t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
				t.setOper_ip(IpUtils.getIpAddr(request));
				t.setLink_tab("KONKA_PE_PROD_USER");
				t.setLink_id(0l);
				String type = "账户名：" + user_name + "登陆渠道系统手机端";
				t.setOper_type(type);
				String name = "零售通登陆失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(user_name);
				getFacade().getOperLogService().createOperLog(t);
			}catch(Exception e){
				
			}
			return null;
		}
		/*记录登陆日志*/
		try{		
			
			if (StringUtils.isNotBlank(logintype)&&StringUtils.isNumeric(logintype)&&"2".equals(logintype)) {
				KonkaMobileLoginFlag loginFlag = new KonkaMobileLoginFlag();
				loginFlag.setUser_id(userInfo.getId());
				loginFlag.setType(Long.valueOf(logintype));
				loginFlag = super.getFacade().getKonkaMobileLoginFlagService()
						.getKonkaMobileLoginFlag(loginFlag);
				if (null == loginFlag) {
					loginFlag = new KonkaMobileLoginFlag();
					loginFlag.setUser_id(userInfo.getId());
					loginFlag.setType(2L);
					loginFlag.setAdd_date(new Date());
					loginFlag.setLast_login_date(new Date());
					super.getFacade().getKonkaMobileLoginFlagService()
							.createKonkaMobileLoginFlag(loginFlag);
				}
			}
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + "登陆渠道系统手机端";
			t.setOper_type(type);
			String name = "零售通登陆成功";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);
		}catch(Exception e){
			
		}
		Date now = new Date();
		
		
		
		String nows = DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss");

		ret.setUser(userInfo);
		ret.setSid(session.getId());
		ret.setStatus(0);
		ret.setTimeout(60);
		ret.setMsg("login success.");
		ret.setSuccess(super.getCtxPath(request) + "/m/admin/Frames.do?method=index");
		ret.setLoginTime(nows);

		// update login count
		PeProdUser ui = new PeProdUser();
		ui.setId(userInfo.getId());
		ui.setLogin_count(userInfo.getLogin_count().longValue() + 1);
		ui.setLast_login_time(new Date());
		ui.setLast_login_ip(this.getIpAddr(request));
		getFacade().getPeProdUserService().modifyPeProdUser(ui);

		userInfo.setLogin_count(userInfo.getLogin_count().longValue() + 1);
		userInfo.setLast_login_time(ui.getLast_login_time());
		userInfo.setLast_login_ip(ui.getLast_login_ip());
		session.setAttribute(Constants.USER_MOBILE, userInfo);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		session.setAttribute(Constants.ROLE_MOBILE_LIST, peRoleUserList);

		userInfo.setPeRoleUserList(peRoleUserList);

		boolean role_id_eq_188 = false;
		boolean role_id_eq_60 = false;
		boolean role_id_eq_lt_10000 = false;
		for (PeRoleUser r : peRoleUserList) {
			if (r.getRole_id() == 60L) {
				role_id_eq_60 = true;
				role_id_eq_60 = true;
			}
			if (r.getRole_id() == 188L) {
				role_id_eq_188 = true;
				role_id_eq_lt_10000 = true;
			}
			if (r.getRole_id() < 10000L) {
				role_id_eq_lt_10000 = true;
			}
		}

		// 10 领导（管理版），20 业务员（客户版）， 30 促销员（导购版）
		if (null == userInfo.getCust_id()) {
			if (!role_id_eq_60 && !role_id_eq_188 && role_id_eq_lt_10000) {
				userInfo.setMobile_user_type(10L);
			} else if (role_id_eq_60) {
				userInfo.setMobile_user_type(40L);
			} else if (role_id_eq_188) {
				userInfo.setMobile_user_type(30L);
			} else {
				// 其他情况均默认为促销员
				userInfo.setMobile_user_type(30L);
			}
		} else {
			userInfo.setMobile_user_type(20L);
		}

		ServletContext application = request.getSession().getServletContext();
		application.setAttribute(session.getId(), userInfo.getUser_name() + "," + now.getTime());

		// ret.setDataPatch("201305091430");

		String s = new JSONObject(ret).toString();
		s = s.replaceAll(":null", ":\"\"");
		super.renderTextJsonOrJsonp(request, response, s);
		return null;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean lazyForm = (DynaBean) form;
		String sid = (String) lazyForm.get("sid");

		LoginAndroidReturn ret = new LoginAndroidReturn();
		ret.setStatus(0);
		ret.setMsg("logout success.");

		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.USER_MOBILE);
			session.removeAttribute(Constants.ROLE_MOBILE);
			session.invalidate();
			ret.setMsg("logout success.[session.invalidate]");
		}
		if (null != sid) {
			ServletContext application = request.getSession().getServletContext();

			String user = (String) application.getAttribute(sid);
			if (null != user) {
				Long loginTime = new Long(user.split(",")[1]);
				String m = DurationFormatUtils.formatPeriod(loginTime, new Date().getTime(), "m");
				ret.setMsg("logout success.[ sid: " + sid + ", Time online(min): " + m + " ]");
			} else {
				ret.setMsg("logout success.[ sid: not exist ]");
			}
			application.removeAttribute(sid);
		}

		setCookies2RequestScope(request);
		super.renderTextJsonOrJsonp(request, response, new JSONObject(ret).toString().replaceAll(":null", ":\"\""));
		return null;
	}

	private void setCookies2RequestScope(HttpServletRequest request) throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "user_name_mobile");
		Cookie password = WebUtils.getCookie(request, "password_mobile");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember_mobile");

		if (null != user_name) {
			request.setAttribute("user_name_mobile", URLDecoder.decode(user_name.getValue(), "UTF-8"));
		}
		if (null != password) {
			request.setAttribute("password_mobile", DEFAULT_PASSWORD_MOBILE);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember_mobile", URLDecoder.decode(is_remember.getValue(), "UTF-8"));
		}
	}

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