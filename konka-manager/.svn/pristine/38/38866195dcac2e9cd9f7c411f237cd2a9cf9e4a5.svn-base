package com.ebiz.mmt.web.struts.sfmall.center;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-11-23
 */
public class SkipAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String msg="";
		String ctx = super.getCtxPath(request);
		String url=ctx+"/sfmall/center/Index.do";
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser)session.getAttribute("ecUser"); 
		String 	pwd = (String)dynaBean.get("pwd");
		if (StringUtils.isBlank(pwd)) {
			msg = super.getMessage(request, "login.failed.password.isEmpty");
		 
		}
		if(ecUser.getUser_type().intValue()==1){
			DESPlus des = new DESPlus();
			pwd=des.encrypt(pwd);
			if(pwd!=null&&pwd.equals(ecUser.getPay_pwd())){
				session.setAttribute("sfmall", "1");
			}else{
				msg = super.getMessage(request, "login.failed.password.invalid");	
			}
		} 
		if(!"".equals(msg)){
			dynaBean.set("msg", msg);
			return mapping.findForward("input");
		}
		response.sendRedirect(url);
		return null; 
	} 
	
	public ActionForward exit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ctx = super.getCtxPath(request);
		String url=ctx+"/sfmall/center/Index.do";
		HttpSession session = request.getSession();
		session.setAttribute("sfmall", "0");
		response.sendRedirect(url);
		return null; 
	}
}
