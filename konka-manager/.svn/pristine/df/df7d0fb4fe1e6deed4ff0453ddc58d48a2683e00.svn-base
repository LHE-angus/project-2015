package com.ebiz.mmt.web.struts.customer;

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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;

public class LoginAction extends BaseClientJxcAction {

	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		request.setAttribute("isDebugMode", super.isDebugMode(request));
		setCookies2RequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");
		saveToken(request);
		request.setAttribute("url", url);

		// 如果会话中又session则直接跳转进去
		HttpSession session = request.getSession();
		session.setAttribute("defaulturl", url);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null != userInfo) {
			return mapping.findForward("success");
		}

		// 当前时间
		Date now = new Date();
		request.setAttribute("now", now.getTime());

		// 取轮播图
		ArticleImg img_1201 = new ArticleImg();
		img_1201.setNews_module(1201L); // 客户端登陆页面左侧轮播图
		img_1201.getMap().put("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		img_1201.setInfo_state(new Short("1"));
		img_1201.getRow().setCount(5);
		List<ArticleImg> imgList_1201 = super.getFacade().getArticleImgService().getArticleImgList(img_1201);
		request.setAttribute("imgList_1201", imgList_1201);

		ArticleImg img_1202 = new ArticleImg();
		img_1202.setNews_module(1202L); // 客户端登陆页面左侧轮播图
		img_1202.getMap().put("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		img_1202.setInfo_state(new Short("1"));
		img_1202.getRow().setCount(5);
		List<ArticleImg> imgList_1202 = super.getFacade().getArticleImgService().getArticleImgList(img_1202);
		request.setAttribute("imgList_1202", imgList_1202);

		// 取版本号
		// SysSetting sysSetting = new SysSetting();
		// sysSetting.setTitle("VersionOfVip");
		// List<SysSetting> sysSettingList =
		// super.getFacade().getSysSettingService().getSysSettingList(sysSetting);
		// if(null != sysSettingList && sysSettingList.size()> 0){
		// sysSetting = sysSettingList.get(0);
		// request.setAttribute("VersionOfVip",sysSetting.getContent()==null?"v4.1.2":sysSetting.getContent());
		// }
		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();

		konkaBaseTypeData.setPar_type_id(100001L);// 100001 表示所有和版本相关的基础数据

		konkaBaseTypeData.setIs_del(0);// 没有删除的

		konkaBaseTypeData.setType_id(10000105L);// 表示web端VIp

		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);

		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			konkaBaseTypeData = konkaBaseTypeDataList.get(0);
			request.setAttribute("VersionOfVip",
					null == konkaBaseTypeData.getMemo() ? "V14.1.4" : konkaBaseTypeData.getMemo());
		} else {
			request.setAttribute("VersionOfVip", "V14.1.4");
		}

		return mapping.findForward("login");
	}

	public ActionForward pshowLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("isDebugMode", super.isDebugMode(request));

		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");
		String public_pwd = (String) dynaBean.get("password");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String is_remember = (String) dynaBean.get("is_remember");
		logger.info("public_pwd {}" + public_pwd);

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		if (StringUtils.isBlank(public_pwd)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		HttpSession session = request.getSession();
		if ("1".equals(super.getSysSetting("isEnabledCode"))) {
			if (StringUtils.isBlank(verificationCode)) {

				OperLog t = new OperLog();
				t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
				t.setOper_ip(IpUtils.getIpAddr(request));
				t.setLink_tab("KONKA_PE_PROD_USER");
				t.setLink_id(0l);
				String type = "账户名：" + user_name + ",密码：" + public_pwd;
				t.setOper_type(type);
				String name = "验证码为空，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(user_name);
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
				t.setLink_tab("KONKA_PE_PROD_USER");
				t.setLink_id(0l);
				String type = "账户名：" + user_name + ",密码：" + public_pwd;
				t.setOper_type(type);
				String name = verificationCode + "验证码输入不正确，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(user_name);
				getFacade().getOperLogService().createOperLog(t);

				msg = super.getMessage(request, "login.failed.verificationCode.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}
		}

		PeProdUser entity = new PeProdUser();
		entity.getRow().setCount(2);
		entity.setUser_name(user_name);
		entity.setUser_type(2);// 康佳客户：user_type=2
		entity.getMap().put("cust_id_not_null", "true");
		entity.setIs_del(0);

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + public_pwd;
			t.setOper_type(type);
			String name = user_name + "用户不存在，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + public_pwd;
			t.setOper_type(type);
			String name = user_name + "用户名重复，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "konka_password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(public_pwd)) {
			entity.setPublic_pwd(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			if (StringUtils.isNotBlank(public_pwd)) {
				entity.setPublic_pwd(des.encrypt(public_pwd));
			}
		}
		logger.info("Login User : {}", BeanUtils.describe(entity));
		PeProdUser userInfo = userInfoList.get(0);

		if (null == userInfo.getPublic_pwd()) {
			msg = super.getMessage(request, "login.failed.public_pwd.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		if (!userInfo.getPublic_pwd().equals(entity.getPublic_pwd())) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + public_pwd;
			t.setOper_type(type);
			String name = "密码错误，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		} else {
			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("konka_user_name");
				cg.addCookie(response, URLEncoder.encode(user_name, "UTF-8"));
				cg.setCookieName("konka_password");
				cg.addCookie(response, URLEncoder.encode(entity.getPublic_pwd(), "UTF-8"));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, "UTF-8"));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("konka_user_name");
				cg.removeCookie(response);
				cg.setCookieName("konka_password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}

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

			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(userInfo.getId());
			List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			if (null != peRoleUserList && peRoleUserList.size() > 0) {
				String[] roleNames = new String[peRoleUserList.size()];

				int i = 0;
				boolean role_id_eq_400 = false;
				for (PeRoleUser peRoleUser : peRoleUserList) {
					if (peRoleUser.getRole_id() == 400L) {
						role_id_eq_400 = true;
					}

					roleNames[i] = (String) peRoleUser.getMap().get("role_name");
				}

				if (role_id_eq_400) {
					session.setAttribute(Constants.USER_INFO, userInfo);
				}

				session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
			}

			session.setAttribute(Constants.CUSTOMER_USER_INFO, userInfo);

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(userInfo.getId());
			String type = "账户名：" + userInfo.getUser_name() + ",密码：" + userInfo.getPublic_pwd();
			t.setOper_type(type);
			String name = userInfo.getUser_name() + "-登录管理系统成功";
			t.setPpdm_name(name);
			if (userInfo != null) {
				t.setOper_uid(userInfo.getId());
				t.setOper_uname(userInfo.getUser_name());
			}
			getFacade().getOperLogService().createOperLog(t);

			if (checkPassword(public_pwd) < 7) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('密码过于简单，请尽快修改！');location.href='../customer/manager/PShow.do'}");
				return null;
			} else {
				super.renderJavaScript(response,
						"window.onload=function(){location.href='../customer/manager/PShow.do'}");
				return null;
			}
		}
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (super.isDebugMode(request)) {
			return this.loginX(mapping, form, request, response);
		}

		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return showLoginForm(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean lazyForm = (DynaBean) form;

		String user_name = (String) lazyForm.get("user_name");
		String password = (String) lazyForm.get("password");
		String verificationCode = (String) lazyForm.get("verificationCode");
		String is_remember = (String) lazyForm.get("is_remember");
		String url = request.getParameter("url");

		String type1 = request.getParameter("type1");
		logger.info("type1 {}" + type1);
		type1 = null == type1 ? "1" : type1;
		lazyForm.set("type1", type1);

		if ("2".equals(type1)) {
			return this.pshowLogin(mapping, form, request, response);
		}

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		String valiMsg = validateLoginErrLogs(user_name, 5, 5);
		if (null != valiMsg) {
			super.renderJavaScript(response, "window.onload=function(){alert('" + valiMsg
					+ "');location.href='login.do'}");
			return null;
		}

		if (StringUtils.isBlank(password)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		}

		HttpSession session = request.getSession();
		if ("1".equals(super.getSysSetting("isEnabledCode"))) {
			if (StringUtils.isBlank(verificationCode)) {

				OperLog t = new OperLog();
				t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
				t.setOper_ip(IpUtils.getIpAddr(request));
				t.setLink_tab("KONKA_PE_PROD_USER");
				t.setLink_id(0l);
				String type = "账户名：" + user_name + ",密码：" + password;
				t.setOper_type(type);
				String name = "验证码为空，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(user_name);
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
				t.setLink_tab("KONKA_PE_PROD_USER");
				t.setLink_id(0l);
				String type = "账户名：" + user_name + ",密码：" + password;
				t.setOper_type(type);
				String name = verificationCode + "验证码输入不正确，登录管理系统失败";
				t.setPpdm_name(name);
				t.setOper_uid(0l);
				t.setOper_uname(user_name);
				getFacade().getOperLogService().createOperLog(t);

				msg = super.getMessage(request, "login.failed.verificationCode.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}
		}

		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		entity.getRow().setCount(2);
		entity.setUser_name(user_name);
		entity.setUser_type(2);// 康佳客户：user_type=2
		entity.getMap().put("cust_id_not_null", "true");
		entity.setIs_del(0);

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = user_name + "用户不存在，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = user_name + "用户名重复，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "konka_password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(password)) {
			entity.setPass_word(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(password));
		}
		logger.info("Login User : {}", BeanUtils.describe(entity));
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {

			String logmsg = createLoginErrLogs(userInfo.getUser_name(), password, 5, 5);
			if (null != logmsg) {
				super.renderJavaScript(response, "window.onload=function(){alert('" + logmsg
						+ "');location.href='login.do'}");
				return null;
			}

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ",密码：" + password;
			t.setOper_type(type);
			String name = "密码错误，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		} else {
			if (null == userInfo.getCust_id() || "".equals(String.valueOf(userInfo.getCust_id()))) {
				msg = super.getMessage(request, "login.failed.cust.norelatoion");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
			} else {
				// 验证是否绑定客户
				KonkaR3Shop s = new KonkaR3Shop();
				s.setId(userInfo.getCust_id());
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

				if (s == null || s.getIs_del() == 1L) {
					msg = super.getMessage(request, "login.failed.cust.stop");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg
							+ "');location.href='login.do'}");
					return null;
				}

				if (s.getIs_konka() == 1) {
					PeProdUser ywy = getYwyOfCustomerByCustomerId(userInfo.getCust_id());

					if (null == ywy) {
						msg = super.getMessage(request, "login.failed.cust.norelatoion");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
								+ "');location.href='login.do'}");
						return null;
					}
				}
			}

			CookieGenerator cg = new CookieGenerator();
			if (is_remember != null) {
				cg.setCookieMaxAge(60 * 60 * 24 * 14);
				cg.setCookieName("konka_user_name");
				cg.addCookie(response, URLEncoder.encode(user_name, "UTF-8"));
				cg.setCookieName("konka_password");
				cg.addCookie(response, URLEncoder.encode(entity.getPass_word(), "UTF-8"));
				cg.setCookieName("is_remember");
				cg.addCookie(response, URLEncoder.encode(is_remember, "UTF-8"));
			} else {
				cg.setCookieMaxAge(0);
				cg.setCookieName("konka_user_name");
				cg.removeCookie(response);
				cg.setCookieName("konka_password");
				cg.removeCookie(response);
				cg.setCookieName("is_remember");
				cg.removeCookie(response);
			}

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

			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(userInfo.getId());
			List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

			if (null != peRoleUserList && peRoleUserList.size() > 0) {
				String[] roleNames = new String[peRoleUserList.size()];

				int i = 0;
				boolean role_id_eq_400 = false;
				for (PeRoleUser peRoleUser : peRoleUserList) {
					if (peRoleUser.getRole_id() == 400L) {
						role_id_eq_400 = true;
					}

					roleNames[i] = (String) peRoleUser.getMap().get("role_name");
				}

				if (role_id_eq_400) {
					session.setAttribute(Constants.USER_INFO, userInfo);
				}

				session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
			}
			session.setAttribute(Constants.CUSTOMER_USER_INFO, userInfo);

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			// t.setOper_desc(BeanUtils.describe(peRoleUser).toString());
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(userInfo.getId());
			String type = "账户名：" + userInfo.getUser_name() + ",密码：" + userInfo.getPass_word();
			t.setOper_type(type);
			String name = userInfo.getUser_name() + "-登录管理系统成功";
			t.setPpdm_name(name);
			if (userInfo != null) {
				t.setOper_uid(userInfo.getId());
				t.setOper_uname(userInfo.getUser_name());
			}
			getFacade().getOperLogService().createOperLog(t);

			clearLoginErrLogs(userInfo.getUser_name());

			if (checkPassword(password) < 7) {
				super.renderJavaScript(response,
						"window.onload=function(){alert('密码过于简单，为了使用安全请尽快修改！');location.href='../customer/manager/Frames.do?method=index&url="
								+ url + "'}");
				return null;
			} else {
				return mapping.findForward("success");
			}
		}
	}

	public ActionForward loginX(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		DynaBean lazyForm = (DynaBean) form;
		String user_name = (String) lazyForm.get("user_name");
		String type1 = request.getParameter("type1");
		type1 = null == type1 ? "1" : type1;
		lazyForm.set("type1", type1);

		if ("2".equals(type1)) {
			return this.pshowLogin(mapping, form, request, response);
		}

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}

		HttpSession session = request.getSession();

		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		entity.getRow().setCount(2);
		entity.setUser_name(user_name);
		entity.setUser_type(2);// 康佳客户：user_type=2
		entity.getMap().put("cust_id_not_null", "true");
		entity.setIs_del(0);

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name;
			t.setOper_type(type);
			String name = user_name + "用户不存在，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (userInfoList.size() > 1) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name;
			t.setOper_type(type);
			String name = user_name + "用户名在系统里面有重复，登录管理系统失败";
			t.setPpdm_name(name);
			t.setOper_uid(0l);
			t.setOper_uname(user_name);
			getFacade().getOperLogService().createOperLog(t);

			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");

			return null;
		}

		PeProdUser userInfo = userInfoList.get(0);

		// Login is OK!
		if (null == userInfo.getCust_id() || "".equals(String.valueOf(userInfo.getCust_id()))) {
			msg = super.getMessage(request, "login.failed.cust.norelatoion2");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
		} else {
			// 验证是否绑定客户
			KonkaR3Shop s = new KonkaR3Shop();
			s.setId(userInfo.getCust_id());
			s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

			if (s == null || s.getIs_del() == 1L) {
				msg = super.getMessage(request, "login.failed.cust.stop");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						+ "');location.href='login.do'}");
				return null;
			}

			if (s.getIs_konka() == 1) {
				PeProdUser ywy = getYwyOfCustomerByCustomerId(userInfo.getCust_id());

				if (null == ywy) {
					msg = super.getMessage(request, "login.failed.cust.norelatoion");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg
							+ "');location.href='login.do'}");
					return null;
				}
			}
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			String[] roleNames = new String[peRoleUserList.size()];

			int i = 0;
			boolean role_id_eq_400 = false;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				if (peRoleUser.getRole_id() == 400L) {
					role_id_eq_400 = true;
				}

				roleNames[i] = (String) peRoleUser.getMap().get("role_name");
			}

			if (role_id_eq_400) {
				session.setAttribute(Constants.USER_INFO, userInfo);
			}
			session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
		}

		session.setAttribute(Constants.CUSTOMER_USER_INFO, userInfo);

		// session.setAttribute(Constants.ROLE_INFO,
		// super.getRoleInfoByThisLogin(request));

		OperLog t = new OperLog();
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip(IpUtils.getIpAddr(request));
		// t.setOper_desc(BeanUtils.describe(peRoleUser).toString());
		t.setLink_tab("KONKA_PE_PROD_USER");
		t.setLink_id(userInfo.getId());
		String type = "账户名：" + userInfo.getUser_name() + ",密码：" + userInfo.getPass_word();
		t.setOper_type(type);
		String name = userInfo.getUser_name() + "-登录管理系统成功";
		t.setPpdm_name(name);
		if (userInfo != null) {
			t.setOper_uid(userInfo.getId());
			t.setOper_uname(userInfo.getUser_name());
		}
		getFacade().getOperLogService().createOperLog(t);

		return mapping.findForward("success");
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
		setCookies2RequestScope(request);
		saveToken(request);
		return mapping.findForward("login");
	}

	private void setCookies2RequestScope(HttpServletRequest request) throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "konka_user_name");
		Cookie password = WebUtils.getCookie(request, "konka_password");
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
