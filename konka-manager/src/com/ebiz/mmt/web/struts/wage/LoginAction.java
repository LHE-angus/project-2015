package com.ebiz.mmt.web.struts.wage;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.IDCard;

public class LoginAction extends BaseWageAction {

	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";
	
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}
	
	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");
		saveToken(request);
		request.setAttribute("url", url);
		return mapping.findForward("login");
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return showLoginForm(mapping, form, request, response);
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id_card_no = (String) dynaBean.get("id_card_no");
		String pwd = (String) dynaBean.get("pwd");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String is_remember = (String) dynaBean.get("is_remember");
		String msg = null;
		if (StringUtils.isBlank(id_card_no)) {
			msg = super.getMessage(request, "wage.login.failed.id_card_no.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		if (StringUtils.isBlank(pwd)) {
			msg = super.getMessage(request, "wage.login.failed.pwd.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		HttpSession session = request.getSession();
		if ("1".equals(super.getSysSetting("isEnabledCode"))) {
			if (StringUtils.isBlank(verificationCode)) {

				OperLog t = new OperLog();
				t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
				t.setOper_ip(IpUtils.getIpAddr(request));
				t.setLink_tab("KONKA_PERSON_PWD");
				t.setLink_id(0l);
				String type = "身份证：" + id_card_no + ",密码：" + pwd;
				t.setOper_type(type);
				String name = "验证码为空，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(id_card_no);
				getFacade().getOperLogService().createOperLog(t);

				msg = super.getMessage(request, "login.failed.verificationCode.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}

			if (!verificationCode.equals(session.getAttribute("verificationCode"))) {

				OperLog t = new OperLog();
				t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
				t.setOper_ip(IpUtils.getIpAddr(request));
				t.setLink_tab("KONKA_PERSON_PWD");
				t.setLink_id(0l);
				String type = "身份证：" + id_card_no + ",密码：" + pwd;
				t.setOper_type(type);
				String name = verificationCode + "验证码输入不正确，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(id_card_no);
				getFacade().getOperLogService().createOperLog(t);

				msg = super.getMessage(request, "login.failed.verificationCode.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}
		}
		
		//验证身份证合法性
		id_card_no = id_card_no.trim();
		IDCard idCard = new IDCard();
		String mark = idCard.IDCardValidate(id_card_no);
		if (!"0".equals(mark)) {
			msg = mark;
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		KonkaPersonPwd temp = new KonkaPersonPwd();
		temp.setId_card_no(id_card_no);
		Long countRecord = getFacade().getKonkaPersonPwdService().getKonkaPersonPwdCount(temp);
		if (0 >= countRecord.intValue()) { //康佳人员薪水查询凭证表：不存在id_card_no
			if (!"888888".equals(pwd)) {
				msg = super.getMessage(request, "wage.login.failed.pwd.first.lg");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
				return null;
			} else { //首次登录
				session.setAttribute("idCard", id_card_no); //存入session，带到register页面
				//**************************完成注册流程*************************
				return mapping.findForward("register");
			}
		}
		
		//身份证存在
		KonkaPersonPwd entity = new KonkaPersonPwd();
		entity.setId_card_no(id_card_no);
		entity.getRow().setCount(2);
		List<KonkaPersonPwd> personPwdList = getFacade().getKonkaPersonPwdService().getKonkaPersonPwdList(entity);
		//无需验证null == personPwdList || personPwdList.size() == 0
		if (personPwdList.size() > 1) {
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PERSON_PWD");
			t.setLink_id(0l);
			String type = "身份证：" + id_card_no + ",密码：" + pwd;
			t.setOper_type(type);
			String name = id_card_no + "身份证重复，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(id_card_no);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "wage.login.failed.id_card_no.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		Cookie passwordCookie = WebUtils.getCookie(request, "konka_wage_password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(pwd)) {
			entity.setPwd(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			entity.setPwd(des.encrypt(pwd));
		}
		logger.info("Login User : {}", BeanUtils.describe(entity));
		
		KonkaPersonPwd user = personPwdList.get(0);
		if (!user.getPwd().equals(entity.getPwd())) {
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PERSON_PWD");
			t.setLink_id(0l);
			String type = "身份证：" + id_card_no + ",密码：" + id_card_no;
			t.setOper_type(type);
			String name = "密码错误，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(id_card_no);
			getFacade().getOperLogService().createOperLog(t);
			
			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else {
			// Login is OK!............................
			
			
			//Cookies 操作
			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("konka_wage_user_name");
				cg.addCookie(response, URLEncoder.encode(id_card_no, "UTF-8"));
				cg.setCookieName("konka_wage_password");
				cg.addCookie(response, URLEncoder.encode(entity.getPwd(), "UTF-8"));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, "UTF-8"));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("konka_wage_user_name");
				cg.removeCookie(response);
				cg.setCookieName("konka_wage_password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}
			
			
			//更新登录信息
			KonkaPersonPwd personPwd = new KonkaPersonPwd();
			personPwd.setId(user.getId());
			personPwd.setLast_login_date(new Date());
			personPwd.setLast_login_ip(super.getIpAddr(request));
			super.getFacade().getKonkaPersonPwdService().modifyKonkaPersonPwd(personPwd);
			
			personPwd.getMap().put("log_count", 1);
			super.getFacade().getKonkaPersonPwdService().modifyKonkaPersonPwdLoginCount(personPwd);
			
			KonkaPersonPwd pp = new KonkaPersonPwd();
			pp.setId(user.getId());
			pp = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(pp);
			
			user.setLast_login_date(pp.getLast_login_date());
			user.setLast_login_ip(pp.getLast_login_ip());
			user.setLogin_counts(pp.getLogin_counts());
			session.setAttribute(Constants.WAGE_USER_INDO, user);
			
			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			// t.setOper_desc(BeanUtils.describe(peRoleUser).toString());
			t.setLink_tab("KONKA_PERSON_PWD");
			t.setLink_id(user.getId());
			String type = "身份证：" + user.getId_card_no() + ",密码：" + user.getPwd();
			t.setOper_type(type);
			String name = user.getId_card_no() + "-登录管理系统成功";
			t.setPpdm_name(name);
			if (user != null) {
				t.setOper_uid(user.getId());
				t.setOper_uname(user.getId_card_no());
			}
			getFacade().getOperLogService().createOperLog(t);

			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&id=" + user.getId() + "&id_card_no=" + user.getId_card_no());
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		}
	}
	
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.WAGE_USER_INDO);
			session.invalidate();
		}
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		saveToken(request);
		return mapping.findForward("login");
	}
	
	private void setCookies2RequestScope(HttpServletRequest request) throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "konka_wage_user_name");
		Cookie password = WebUtils.getCookie(request, "konka_wage_password");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember");

		if (null != user_name) {
			request.setAttribute("user_name", URLDecoder.decode(user_name.getValue(), "UTF-8"));
		}
		if (null != password) {
			request.setAttribute("password", DEFAULT_PASSWORD);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember", URLDecoder.decode(is_remember.getValue(), "UTF-8"));
		}
	}
	
}
