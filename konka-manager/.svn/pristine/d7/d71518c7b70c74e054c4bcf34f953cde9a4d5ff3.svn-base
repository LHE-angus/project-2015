package com.ebiz.mmt.web.struts.member.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcLuckyBuy;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcLuckyBuyAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser"); 
		
		Pager pager = (Pager) dynaBean.get("pager"); 
		String start_date=(String) dynaBean.get("start_date");
		String end_date=(String) dynaBean.get("end_date");
		EcLuckyBuy entity=new EcLuckyBuy(); 		 
		entity.setOwn_sys(ecUser.getUser_type()); 
		entity.setUser_id(ecUser.getId()); 
		entity.setLucky_type(new Integer(0));
		entity.getMap().put("add_date_start", start_date);
		entity.getMap().put("add_date_end", end_date);	
		
		Long recordCount=super.getFacade().getEcLuckyBuyService().getEcLuckyBuyCount(entity);
				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcLuckyBuy> entityList=super.getFacade().getEcLuckyBuyService().getEcLuckyBuyPaginatedList(entity);				
		request.setAttribute("entityList", entityList);
	  
		return mapping.findForward("list");
	} 
}
