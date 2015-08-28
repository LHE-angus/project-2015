package com.ebiz.mmt.web.struts.webservice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBackPassword;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class IsActAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key = (String) dynaBean.get("key");
		// String email = (String) dynaBean.get("email");
		// String s1 = (String) dynaBean.get("s1");
		//System.out.println("key---------->" + key);
		// //System.out.println("email---------->" + email);
		// //System.out.println("s1---------->" + s1);
		if (StringUtils.isBlank(key)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		// DESPlus des = new DESPlus();
		// String id = des.decrypt(s1);
		// //System.out.println("id--------->" + id);
		// String s2 = des.decrypt(email);
		// //System.out.println("s2--------->" + s2);

		EcBackPassword ecb = new EcBackPassword();
		ecb.setYz_key(key);
		ecb = super.getFacade().getEcBackPasswordService().getEcBackPassword(ecb);
		if (null == ecb) {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起！没有找到用户信息');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}
		if (ecb.getState() == 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('链接只能修改一次，链接已经失效！');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ecb.getAdd_date());
		calendar.add(Calendar.DATE, 1);
		Date date = new Date();

		if (calendar.getTime().getTime() - date.getTime() < 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('链接已经超时，链接已经失效！');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}

		EcUser ec = new EcUser();
		ec.setCard_no(ecb.getCard_no());
		List<EcUser> ecList = super.getFacade().getEcUserService().getEcUserList(ec);
		if (null == ecList || ecList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('没有找到用户信息');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		} else if (ecList.size() > 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('用户名重复，请联系管理员');location.href='"
			        + super.getCtxPath(request) + "/member/login.do';}");
			return null;
		}
		ec = ecList.get(0);

		dynaBean.set("id", ec.getId());
		dynaBean.set("e_id", ecb.getId());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String e_id = (String) dynaBean.get("e_id");
		String new_password = (String) dynaBean.get("new_password");
		HttpSession session = request.getSession();
		String verificationCode = (String) dynaBean.get("verificationCode");

		String msg = "";

		if (StringUtils.isBlank(verificationCode)) {
			msg = "验证码不能为空";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		if (!verificationCode.equals((String) session.getAttribute("verificationCode"))) {
			msg = "验证码不正确";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(id));
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(new_password));
		entity.getMap().put("e_id", e_id);

		super.getFacade().getEcUserService().modifyEcUserAndEid(entity);

		saveMessage(request, "password.updated.success");

		String msg2 = "密码修改成功";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg2 + "');location.href='"
		        + super.getCtxPath(request) + "/member/login.do';}");
		return null;
	}
}
