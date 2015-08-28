package com.ebiz.mmt.web.struts.webservice.wap.center;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author tudp
 * @version 2013-12-30
 */
public class RegUserAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser"); 
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==1){
			return mapping.findForward("list");
		}else{ 
			if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==0){
				session.setAttribute("ecUser", entity);
			}
			request.setAttribute("is_act", entity.getIs_act());
			return mapping.findForward("view");
		}
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");  
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if(entity.getIs_own()==null){
			entity.setIs_own(new Integer(0));
		}
		if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==1){ 
			entity.setPass_word(null);
			entity.setPay_pwd(null);
			entity.setBank_name(null);
			entity.setBank_account(null);
			entity.setBank_account_name(null);
			entity.setReal_name(null);
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		}else{ 
			if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==0){
				session.setAttribute("ecUser", entity);
			}
			request.setAttribute("is_act", entity.getIs_act()); 
			return mapping.findForward("view");
		} 
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");  
		EcUser entity = new EcUser();
		entity.setId(ecUser.getId());
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		if(entity.getIs_own()==null){
			entity.setIs_own(new Integer(0));
		}
		if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==3){  
			entity.setBank_name(null);
			entity.setBank_account(null);
			entity.setBank_account_name(null);
			entity.setReal_name(null);
			super.copyProperties(form, entity);
			return mapping.findForward("input");
		}else{ 
			if(entity.getIs_act()!=null&&entity.getIs_act().intValue()==0){
				session.setAttribute("ecUser", entity);
			}
			request.setAttribute("is_act", entity.getIs_act()); 
			return mapping.findForward("view");
		} 
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser"); 
		EcUser user = new EcUser();
		user.setId(ecUser.getId());
		user = super.getFacade().getEcUserService().getEcUser(user);
		if(user.getIs_act()!=null&&(user.getIs_act().intValue()==0||user.getIs_act().intValue()==2)){
			request.setAttribute("is_act", user.getIs_act()); 
			return mapping.findForward("view");
		} 
		
		String country=(String)dynaBean.get("country");
		String town=(String)dynaBean.get("town"); 
		
		EcUser entity=new EcUser();
		super.copyProperties(entity, form);
		String msg="";
		if(entity.getId().intValue()!=ecUser.getId().intValue()){
			msg="用户已变更请重新登录";
		} 
		if(entity.getReal_name()==null){
			msg="真实姓名错误";
		}else if(!entity.getReal_name().trim().equals(ecUser.getReal_name().trim())){
			msg="您的输入的姓名与工卡号不匹配";
		}
		if (entity.getUser_name() != null && !"".equals(entity.getUser_name())) {
			EcUser t = new EcUser();
			t.setUser_name(entity.getUser_name());
			List<EcUser> ecUserList = super.getFacade().getEcUserService().getEcUserList(t);
			if (ecUserList != null && ecUserList.size() > 0) {
				msg = "该用户名已经存在";
			}
		}
		if (!"".equals(msg)) {
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "'); history.back();}");
			return null;
		}
		
		if (!StringUtils.isBlank(town) && GenericValidator.isLong(town)) {
			entity.setP_index(new Long(town));
		}else if (!StringUtils.isBlank(country) && GenericValidator.isLong(country)){
			entity.setP_index(new Long(country));
		}
		 
		entity.setId(ecUser.getId());
		entity.setIs_act(new Integer(2));
		entity.setAdd_date(new Date());
		DESPlus des = new DESPlus();	
		if(entity.getPass_word()!=null)
		entity.setPass_word(des.encrypt(entity.getPass_word()));
		if(entity.getPay_pwd()!=null)
		entity.setPay_pwd(des.encrypt(entity.getPay_pwd()));
		super.getFacade().getEcUserService().modifyEcUser(entity); 
		saveMessage(request, "entity.updated");
		
		super.renderJavaScript(response, "window.onload=function(){location.href=\"RegUser.do\";}");
		return null;
	}
}
