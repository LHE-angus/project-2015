package com.ebiz.mmt.web.struts.wage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;

public class PasswordAction extends BaseWageAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.edit(mapping, form, request, response);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pwdId = (String) dynaBean.get("pwdId");
		String id_card_no = (String) dynaBean.get("id_card_no");
		
		HttpSession session = request.getSession();
		KonkaPersonPwd user = (KonkaPersonPwd) session.getAttribute(Constants.WAGE_USER_INDO);
		if (null == user) {
			String msg = super.getMessage(request, "wage.session.userinfo.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (!StringUtils.equals(id_card_no, user.getId_card_no())) {
			String msg = super.getMessage(request, "wage.session.id_card_no.is.ne.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		dynaBean.set("pwdId", pwdId);
		dynaBean.set("id_card_no", id_card_no);
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pwdId = (String) dynaBean.get("pwdId");
		String id_card_no = (String) dynaBean.get("id_card_no");
		String old_pwd = (String) dynaBean.get("old_pwd");
		String new_pwd = (String) dynaBean.get("new_pwd");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String msg;
		
		if (StringUtils.isBlank(pwdId) || StringUtils.isBlank(id_card_no) || StringUtils.isBlank(old_pwd) || StringUtils.isBlank(new_pwd)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(verificationCode)) {
			msg = super.getMessage(request, "login.failed.verificationCode.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		HttpSession session = request.getSession();
		KonkaPersonPwd user = (KonkaPersonPwd) session.getAttribute(Constants.WAGE_USER_INDO);
		if (null == user) {
			msg = super.getMessage(request, "wage.session.userinfo.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		} else if (!StringUtils.equals(id_card_no, user.getId_card_no())) {
			msg = super.getMessage(request, "wage.session.id_card_no.is.ne.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		KonkaPersonPwd temp = new KonkaPersonPwd();
		temp.setId(Long.valueOf(pwdId));
		temp.setId_card_no(id_card_no);
		temp = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(temp);
		if (null == temp) {
			msg = super.getMessage(request, "wage.login.userinfo.notfound");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		DESPlus des = new DESPlus();
		if (!des.encrypt(old_pwd).equals(temp.getPwd())) {
			msg = super.getMessage(request, "wage.old.password.enter.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPersonPwd entity = new KonkaPersonPwd();
		entity.setId(Long.valueOf(pwdId));
		entity.setId_card_no(id_card_no);
		entity.setPwd(des.encrypt(new_pwd));
		super.getFacade().getKonkaPersonPwdService().modifyKonkaPersonPwd(entity);
		
		//修改后的新用户信息重新传入session
		KonkaPersonPwd su = new KonkaPersonPwd();
		su.setId(Long.valueOf(pwdId));
		su = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(su);
		session.setAttribute(Constants.WAGE_USER_INDO, su);
		
		msg = super.getMessage(request, "password.updated.success.info");
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
		return null;
	}
	
}
