package com.ebiz.mmt.web.struts.wap.center;

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

/**
 * @author tudp
 * @version 2013-09-09
 */
public class UserAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/wap/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 
		EcUser entity=new EcUser();
		entity.setId(ecUser.getId());
		entity=super.getFacade().getEcUserService().getEcUser(entity);
		if(entity.getIs_own()==null){
			entity.setIs_own(new Integer(0));
		}
		super.copyProperties(form, entity);
		
		 String province="";
		 String city="";
		 String country="";
		 String town=""; 
		 if(entity.getP_index()!=null){
			 town=entity.getP_index().toString();
			 country=entity.getP_index().toString().substring(0, 6); 
			 city=entity.getP_index().toString().substring(0, 4)+"00";
			 province=entity.getP_index().toString().substring(0, 2)+"0000";
			 
			 request.setAttribute("province", province);
			 request.setAttribute("city", city);
			 request.setAttribute("country", country);
			 request.setAttribute("town", town);
		 }
		 
		return mapping.findForward("input");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser"); 
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/wap/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 
		
		String country=(String)dynaBean.get("country");
		String town=(String)dynaBean.get("town"); 
		
		EcUser entity=new EcUser();
		super.copyProperties(entity, form);
		
		if (!StringUtils.isBlank(town) && GenericValidator.isLong(town)) {
			entity.setP_index(new Long(town));
		}else if (!StringUtils.isBlank(country) && GenericValidator.isLong(country)){
			entity.setP_index(new Long(country));
		}
		entity.setId(ecUser.getId());
		super.getFacade().getEcUserService().modifyEcUser(entity); 
		saveMessage(request, "entity.updated");
		
		super.renderJavaScript(response, "window.onload=function(){location.href=\"User.do\";}");
		return null;
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/wap/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 
		EcUser entity=new EcUser();
		entity.setId(ecUser.getId());
		entity=super.getFacade().getEcUserService().getEcUser(entity);
		if(entity.getIs_own()==null){
			entity.setIs_own(new Integer(0));
		}
		super.copyProperties(form, entity);
		
		 String province="";
		 String city="";
		 String country="";
		 String town=""; 
		 if(entity.getP_index()!=null){
			 town=entity.getP_index().toString();
			 country=entity.getP_index().toString().substring(0, 6); 
			 city=entity.getP_index().toString().substring(0, 4)+"00";
			 province=entity.getP_index().toString().substring(0, 2)+"0000";
			 
			 request.setAttribute("province", province);
			 request.setAttribute("city", city);
			 request.setAttribute("country", country);
			 request.setAttribute("town", town);
		 }
		 
		return mapping.findForward("view");
	}
}
