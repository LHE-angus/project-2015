package com.ebiz.mmt.web.struts.member.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcUserGwjfAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		Pager pager = (Pager) dynaBean.get("pager"); 
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
				if(ecUser.getUser_type().intValue()==1){
					String touch = (String)session.getAttribute("touch");
					if(!"1".equals(touch)){
						String ctx = super.getCtxPath(request);
						String url=ctx+"/member/center/Skip.do";
						response.sendRedirect(url);
						return null; 
					}
				} 

		PshowOrdeDetails entity= new PshowOrdeDetails();
		entity.getMap().put("user_id", ecUser.getId());
		entity.getMap().put("state_in", new Integer[]{60}); 
		entity.getMap().put("order_from", ecUser.getUser_type()); 
		Long recordCount= super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsCount(entity);
				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());			 
		
		List<PshowOrdeDetails> orderDetailList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsForFgsPaginatedList(entity);
		request.setAttribute("orderDetailList",orderDetailList);
		return mapping.findForward("list");
	}

}
