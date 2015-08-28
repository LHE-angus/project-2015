package com.ebiz.mmt.web.struts.touch.center;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class PwdAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
	 
		super.copyProperties(form, ecUser); 
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
	 
		DynaBean dynaBean = (DynaBean) form;
		String pwd=(String)dynaBean.get("pwd");
		String newpwd=(String)dynaBean.get("newpwd");
		String repwd=(String)dynaBean.get("repwd"); 
		String msg="";
		if(!StringUtils.isNotBlank(pwd) ){
			msg="原密码不能为空！";
			super.renderJavaScript(response, "window.onload=function(){alert('"+msg+"');history.back();}");
			return null;
		}
		if(!StringUtils.isNotBlank(newpwd) ){
			msg="新原密码不能为空！";
			super.renderJavaScript(response, "window.onload=function(){alert('"+msg+"');history.back();}");
			return null;
		}
		DESPlus des = new DESPlus();
		if(!des.encrypt(pwd).equals(ecUser.getPass_word())){
			msg="原密码不正确！";
			super.renderJavaScript(response, "window.onload=function(){alert('"+msg+"');history.back();}");
			return null;
		}
		if(!newpwd.equals(repwd)){
			msg="两次输入的密码不一致,请重新输入！";
			super.renderJavaScript(response, "window.onload=function(){alert('"+msg+"');history.back();}");
			return null;
		}
		
		
		EcUser entity=new EcUser();
		super.copyProperties(entity, form);
		entity.setId(ecUser.getId());
		entity.setPass_word(des.encrypt(newpwd));
		 
		super.getFacade().getEcUserService().modifyEcUser(entity);
		ecUser.setPass_word(entity.getPass_word());
		saveMessage(request, "password.updated.success");
		session.setAttribute("ecUser", ecUser);
		super.renderJavaScript(response, "window.onload=function(){location.href=\"Pwd.do?s=1\";}");
		return null;
	}
}
