package com.ebiz.mmt.web.struts.touch.center;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcRebatesAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/touch/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 
		
		Pager pager = (Pager) dynaBean.get("pager"); 
		String start_date=(String) dynaBean.get("start_date");
		String end_date=(String) dynaBean.get("end_date");
		String rebates_status=(String) dynaBean.get("rebates_status");
		
		PshowOrdeDetails entity=new PshowOrdeDetails(); 		 
		 
		entity.getMap().put("user_id", ecUser.getId()); 
		entity.getMap().put("state_in", new Integer[]{60}); 
		entity.getMap().put("order_from", ecUser.getUser_type()); 
		request.setAttribute("rebates", super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsSumRebates(entity));

		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);	
		if(!"".equals(rebates_status)&&rebates_status!=null){
			entity.setRebates_status(new Integer(rebates_status));
		}
		
		Long recordCount = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForRebatesCount(entity);
				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForRebatesPaginatedList(entity);				
		request.setAttribute("entityList", entityList);
	  
		return mapping.findForward("list");
	} 
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String msg = "对不起，发生错误！";
		int i=0;
	 
			HttpSession session = request.getSession();
			EcUser user = (EcUser) session.getAttribute(Constants.EPP_USER);			 
			DynaBean dynaBean = (DynaBean) form;
			String id = (String) dynaBean.get("id");
			String type = (String) dynaBean.get("type"); 
			if(id!=null&&!"".equals(id)){
				if("2".equals(type)){
				i =	 super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetailsRebates(new Long(id), type, user);
				}else if("3".equals(type)){
				i =  super.getFacade().getPshowOrdeDetailsService().modifyPshowOrdeDetailsRebates(new Long(id), type, user);
				}
			} 
			if(i!=0){
				msg="操作成功！";
			}
			super.renderJavaScript(response, "window.onload=function(){alert('"+msg+"');location.href=\""+super.getCtxPath(request)+"/touch/center/EcRebates.do\";}");
			return null; 
	}
}
