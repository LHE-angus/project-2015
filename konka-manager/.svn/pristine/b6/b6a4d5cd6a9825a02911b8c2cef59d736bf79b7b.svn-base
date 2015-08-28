package com.ebiz.mmt.web.struts.mobile;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author WangHao
 */
public class LoginAction extends BaseAction {
	private static final String DEFAULT_PASSWORD = "......";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.showLoginForm(mapping, form, request, response);
	}

	public ActionForward showLoginForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("isEnabledCode", super
				.getSysSetting("isEnabledCode"));
		setCookies2RequestScope(request);
		return mapping.findForward("login");
	}

	public ActionForward getCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keys = (String) dynaBean.get("keys");
		KonkaDept konkaDept = new KonkaDept();
		switch (new Integer(keys)) {
		case 1:
			konkaDept.getMap().put("letter_name_a", "a");
			konkaDept.getMap().put("letter_name_b", "e");
			break;
		case 2:
			konkaDept.getMap().put("letter_name_a", "f");
			konkaDept.getMap().put("letter_name_b", "j");
			break;
		case 3:
			konkaDept.getMap().put("letter_name_a", "k");
			konkaDept.getMap().put("letter_name_b", "o");
			break;
		case 4:
			konkaDept.getMap().put("letter_name_a", "p");
			konkaDept.getMap().put("letter_name_b", "t");
			break;
		default:
			konkaDept.getMap().put("letter_name_a", "u");
			konkaDept.getMap().put("letter_name_b", "z");
			break;
		}

		konkaDept.setDept_type(3);
		konkaDept.setPar_id(0L);

		List<KonkaDept> baseDeptList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konkaDept);

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaDept _t : baseDeptList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getDept_id().toString());
			t.setName(_t.getDept_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	public ActionForward getDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept");
		if (StringUtils.isNotEmpty(dept_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(dept_id));

			List<KonkaDept> baseDeptList = super.getFacade()
					.getKonkaDeptService().getKonkaDeptList(konkaDept);

			List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
			for (KonkaDept _t : baseDeptList) {
				MobileSelectItem t = new MobileSelectItem();
				t.setId(_t.getDept_id().toString());
				t.setName(_t.getDept_name());
				list.add(t);
			}
			if (list.size() > 0)
				super.renderJson(response, JSON.toJSONString(list));
		}
		return null;
	}

	public ActionForward getMan(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept");
		if (StringUtils.isNotEmpty(dept_id)) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(new Long(dept_id));

			PeProdUser peProdUser = new PeProdUser();
			peProdUser.setDept_id(new Long(dept_id));
			List<PeProdUser> userList = super.getFacade()
					.getPeProdUserService().getPeProdUserList(peProdUser);

			List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
			for (PeProdUser _t : userList) {
				MobileSelectItem t = new MobileSelectItem();
				t.setId(_t.getId().toString());
				t.setName(_t.getUser_name());
				list.add(t);
			}
			if (list.size() > 0)
				super.renderJson(response, JSON.toJSONString(list));
		}
		return null;
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		DynaBean lazyForm = (DynaBean) form;

		String user_name = (String) lazyForm.get("user_name");
		String password = (String) lazyForm.get("password");

		String msg = null;
		if (StringUtils.isBlank(user_name)) {
			msg = super.getMessage(request, "login.failed.username.isEmpty");
			super.renderText(response, msg);
			return null;
		}

		if (StringUtils.isBlank(password)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
			super.renderText(response, msg);
			return null;
		}

		HttpSession session = request.getSession();

		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		entity.setUser_name(user_name);
		entity.setIs_del(0);
		List<PeProdUser> userInfoList = getFacade().getPeProdUserService()
				.getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			msg = super.getMessage(request, "login.failed.username.invalid");
			super.renderText(response, msg);
			return null;
		} else if (userInfoList.size() > 1) {
			msg = super.getMessage(request, "login.failed.username.repeat");
			super.renderText(response, msg);
			return null;
		}

		Cookie passwordCookie = WebUtils.getCookie(request, "password");
		if (null != passwordCookie && DEFAULT_PASSWORD.equals(password)) {
			entity.setPass_word(passwordCookie.getValue());
		} else {
			DESPlus des = new DESPlus();
			entity.setPass_word(des.encrypt(password));
		}
		PeProdUser userInfo = getFacade().getPeProdUserService().getPeProdUser(
				entity);
		if (null == userInfo) {
			msg = super.getMessage(request, "login.failed.password.invalid");
			super.renderText(response, msg);
			return null;
		} else {
			CookieGenerator cg = new CookieGenerator();
			cg.setCookieMaxAge(0);
			cg.setCookieName("user_name");
			cg.removeCookie(response);
			cg.setCookieName("password");
			cg.removeCookie(response);

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
			session.setAttribute(Constants.USER_INFO, userInfo);

			PeRoleUser peRoleUser = new PeRoleUser();
			peRoleUser.setUser_id(userInfo.getId());
			peRoleUser = super.getFacade().getPeRoleUserService()
					.getPeRoleUser(peRoleUser);
			session.setAttribute(Constants.ROLE_INFO, peRoleUser);

			super.renderText(response, "success");
			return null;

		}
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.removeAttribute(Constants.USER_MOBILE);
			session.removeAttribute(Constants.ROLE_INFO);
			session.invalidate();
		}
		setCookies2RequestScope(request);
		return mapping.findForward("login");
	}

	private void setCookies2RequestScope(HttpServletRequest request)
			throws Exception {
		Cookie user_name = WebUtils.getCookie(request, "user_name");
		Cookie password = WebUtils.getCookie(request, "password");
		Cookie is_remember = WebUtils.getCookie(request, "is_remember");

		if (null != user_name) {
			request.setAttribute("user_name", URLDecoder.decode(user_name
					.getValue(), "UTF-8"));
		}
		if (null != password) {
			request.setAttribute("password", DEFAULT_PASSWORD);
		}
		if (null != is_remember) {
			request.setAttribute("is_remember", URLDecoder.decode(is_remember
					.getValue(), "UTF-8"));
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