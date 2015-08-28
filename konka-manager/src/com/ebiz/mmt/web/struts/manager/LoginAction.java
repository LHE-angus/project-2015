package com.ebiz.mmt.web.struts.manager;

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
import com.ebiz.mmt.domain.KonkaPeArticleInfo;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.DESPlus;

public class LoginAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "~~@^_^@~~";

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("isDebugMode", super.isDebugMode(request));
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		DynaBean dynaBean = (DynaBean) form;
		String url = (String) dynaBean.get("url");
		saveToken(request);
		request.setAttribute("url", url);

		// 如果会话中又session则直接跳转进去
		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		// 当前时间
		Date now = new Date();
		request.setAttribute("now", now.getTime());

		// 取轮播图
		ArticleImg img = new ArticleImg();
		img.setNews_module(1102L); // 管理端登陆页面轮播图
		img.getMap().put("today", DateFormatUtils.format(now, "yyyy-MM-dd"));
		img.setInfo_state(new Short("1"));
		img.getRow().setCount(5);
		List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
		request.setAttribute("imgList", imgList);

		// 取资讯
		KonkaPeArticleInfo article = new KonkaPeArticleInfo();
		article.setStates(1L); // 已发布
		// article.setArticle_mod_id(960115L);//只获取发布到首页的资讯信息
		String[] strValue = new String[] { "1010", "1020", "1030", "1060" };
		article.getMap().put("article_type_id_in", strValue);
		article.setMsg_info_type(0); // 公开
		article.setIs_del(0L);
		article.getRow().setCount(10);

		List<KonkaPeArticleInfo> articleList = super.getFacade().getKonkaPeArticleInfoService()
				.getKonkaPeArticleInfoList(article);

		request.setAttribute("articleList", articleList);

		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();

		konkaBaseTypeData.setPar_type_id(100001L);// 100001 表示所有和版本相关的基础数据

		konkaBaseTypeData.setIs_del(0);// 没有删除的

		konkaBaseTypeData.setType_id(10000103L);// 表示web端

		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);

		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			konkaBaseTypeData = konkaBaseTypeDataList.get(0);
			request.setAttribute("VersionOfManage",
					null == konkaBaseTypeData.getMemo() ? "V14.1.4" : konkaBaseTypeData.getMemo());
		} else {
			request.setAttribute("VersionOfManage", "V14.1.4");
		}

		if (null != userInfo) {
			return mapping.findForward("success");
		}
		return mapping.findForward("login");
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
//		PeProdUser entity = new PeProdUser();
//		entity.getRow().setCount(2);
//		entity.setUser_name(user_name);
//		entity.setIs_del(0);
//		// entity.setIs_xx_user(0L); //不是新兴渠道用户 -----改在SQL中写死，以免各个调用模块都需要\
//		entity.getMap().put("user_type_in", "0,1"); // Ren zhong, Add
//		// by2013-06-06
//
//		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);

		//仅查询符合条件的用户数
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		entity.setIs_del(0);
		entity.setIs_xx_user(0L);
		entity.getMap().put("user_type_in", "0,1");
		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getLoginUserList(entity);

		if (null == userInfoList || 0 == userInfoList.size()) {

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
		} else if (1 < userInfoList.size()) {

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

			// boolean role_id_lt_10000 = false;
			// if (null != peRoleUserList && peRoleUserList.size() > 0) {
			// for (PeRoleUser peRoleUser : peRoleUserList) {
			// if (peRoleUser.getRole_id() < 10000L) {
			// role_id_lt_10000 = true;
			// break;
			// }
			// }
			// }
			//
			// if (!role_id_lt_10000) {
			// KonkaDept dept_fgs =
			// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
			// if (null != dept_fgs && userInfo.getCust_id() == null) {
			// PeRoleUser ru = new PeRoleUser();
			// ru.getMap().put("user_ids", new String[] {
			// String.valueOf(userInfo.getId()) });
			// ru.setRole_id(32L);
			// super.getFacade().getPeRoleUserService().createPeRoleUser(ru);
			// }
			// }

			userInfo.getMap().put("password", new DESPlus().decrypt(userInfo.getPass_word()));
			session.setAttribute(Constants.USER_INFO, userInfo);
			session.setAttribute(Constants.ROLE_INFO, super.getRoleInfoByThisLogin(request));

			if (null != peRoleUserList && peRoleUserList.size() > 0) {
				String[] roleNames = new String[peRoleUserList.size()];
				int i = 0;
				for (PeRoleUser peRoleUser : peRoleUserList) {
					roleNames[i] = (String) peRoleUser.getMap().get("role_name");
				}
				session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
				session.setAttribute(Constants.ROLE_INFO_LIST, peRoleUserList);
			}

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
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
				ActionForward af = mapping.findForward("success");
				String path = super.getCtxPath(request) + af.getPath();
				super.renderJavaScript(response, "window.onload=function(){alert('密码过于简单，请尽快修改！');location.href='"
						+ path + "&url=" + url + "'}");
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
		entity.setIs_del(0);
		entity.getMap().put("user_type_in", "0,1");

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {

			OperLog t = new OperLog();
			t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
			t.setOper_ip(IpUtils.getIpAddr(request));
			t.setLink_tab("KONKA_PE_PROD_USER");
			t.setLink_id(0l);
			String type = "账户名：" + user_name + ";";
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
			String type = "账户名：" + user_name + ";";
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
		logger.info("Login User : {}", BeanUtils.describe(entity));
		PeProdUser userInfo = userInfoList.get(0);

		// update login count
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = super.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		// boolean role_id_lt_10000 = false;
		// if (null != peRoleUserList && peRoleUserList.size() > 0) {
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() < 10000L) {
		// role_id_lt_10000 = true;
		// break;
		// }
		// }
		// }
		// if (!role_id_lt_10000) {
		// KonkaDept dept_fgs =
		// super.getSuperiorForDeptType(userInfo.getDept_id(), 3);
		// if (null != dept_fgs && userInfo.getCust_id() == null) {
		// PeRoleUser ru = new PeRoleUser();
		// ru.getMap().put("user_ids", new String[] {
		// String.valueOf(userInfo.getId()) });
		// ru.setRole_id(32L);
		// super.getFacade().getPeRoleUserService().createPeRoleUser(ru);
		// }
		// }

		userInfo.getMap().put("password", new DESPlus().decrypt(userInfo.getPass_word()));
		session.setAttribute(Constants.USER_INFO, userInfo);
		session.setAttribute(Constants.ROLE_INFO, super.getRoleInfoByThisLogin(request));

		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			String[] roleNames = new String[peRoleUserList.size()];
			int i = 0;
			for (PeRoleUser peRoleUser : peRoleUserList) {
				roleNames[i++] = (String) peRoleUser.getMap().get("role_name");
			}
			session.setAttribute(Constants.ROLE_NAMES, StringUtils.join(roleNames, ","));
			session.setAttribute(Constants.ROLE_INFO_LIST, peRoleUserList);
		}

		OperLog t = new OperLog();
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip(IpUtils.getIpAddr(request));
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
			session.removeAttribute(Constants.USER_INFO);
			session.removeAttribute(Constants.ROLE_INFO);
			session.removeAttribute(Constants.ROLE_INFO_LIST);
			session.invalidate();
		}
		request.setAttribute("isEnabledCode", super.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		saveToken(request);
		return this.showLoginForm(mapping, form, request, response);
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