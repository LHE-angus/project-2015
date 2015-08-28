package com.ebiz.mmt.web.struts.wage;

import java.util.Date;

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

public class RegisterAction extends BaseWageAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		
		HttpSession session = request.getSession();
		String id_card_no = (String) session.getAttribute("idCard");
		dynaBean.set("id_card_no", id_card_no);
		
		return mapping.findForward("input");
	}
	
	/**
	 * @desc 注册保存
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String id_card_no = (String) dynaBean.get("id_card_no");
		String pwd = (String) dynaBean.get("pwd");
		String verificationCode = (String) dynaBean.get("verificationCode");
		String msg;
		
		HttpSession session = request.getSession();
		if (StringUtils.isBlank(id_card_no) || StringUtils.isBlank(pwd)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		if (StringUtils.isBlank(verificationCode)) {
			msg = super.getMessage(request, "login.failed.verificationCode.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='login.do'}");
			return null;
		}
		
		KonkaPersonPwd entity = new KonkaPersonPwd();
		super.copyProperties(entity, form);
		
		DESPlus des = new DESPlus();
		pwd = des.encrypt(pwd);
		entity.setPwd(pwd);
		
		KonkaPersonPwd person = new KonkaPersonPwd();
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("reg_success").getPath());
		if (StringUtils.isBlank(id)) {
			entity.setReg_date(new Date());
			entity.setLogin_counts(0L);
			entity.setLast_login_date(new Date());
			entity.setLast_login_ip(super.getIpAddr(request));
			entity.setLogin_counts(1L);
			Long pId = getFacade().getKonkaPersonPwdService().createKonkaPersonPwd(entity);
			
			person.setId(pId);
			pathBuffer.append("&id=" + pId);
		} else {
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaPersonPwdService().modifyKonkaPersonPwd(entity);
			
			person.setId(Long.valueOf(id));
			pathBuffer.append("&id=" + id);
		}
		person = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(person);
		session.setAttribute(Constants.WAGE_USER_INDO, person);
		
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward success(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		
		HttpSession session = request.getSession();
		KonkaPersonPwd user = (KonkaPersonPwd) session.getAttribute(Constants.WAGE_USER_INDO);
		super.copyProperties(form, user);
		
		dynaBean.set("id", id);
		return mapping.findForward("update_success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		
		if (StringUtils.isBlank(id)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		
		KonkaPersonPwd entity = new KonkaPersonPwd();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(entity);
		super.copyProperties(form, entity);
		
		return mapping.findForward("input");
	}
	
}
