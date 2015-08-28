package com.ebiz.mmt.web.struts.touch.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcPdEavlAction extends BaseMemberAction {

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
		EcPdEavl entity=new EcPdEavl(); 		 
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_del(0);
		entity.setEval_user_id(ecUser.getId()); 
		entity.getMap().put("eval_date_start", start_date);
		entity.getMap().put("eval_date_end", end_date);	
		
		Long recordCount=super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcPdEavl> entityList=super.getFacade().getEcPdEavlService().getEcPdEavlPaginatedList(entity);				
		request.setAttribute("entityList", entityList);
	  
		return mapping.findForward("list");
	} 
}
