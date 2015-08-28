package com.ebiz.mmt.web.struts.touch.center;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserFavotrites;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class EcUserFavotritesAction extends BaseMemberAction {

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
		
		EcUserFavotrites entity=new EcUserFavotrites();		
		entity.setUser_id(ecUser.getId());
		entity.setOwn_sys(ecUser.getUser_type());		
		
		Long recordCount=super.getFacade().getEcUserFavotritesService().getEcUserFavotritesCount(entity);				
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcUserFavotrites> entityList=super.getFacade().getEcUserFavotritesService().getEcUserFavotritesPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		 
		return mapping.findForward("list");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		 
		EcUserFavotrites entity=new EcUserFavotrites();		
		 super.copyProperties(entity, form);
		 
		 entity.setUser_id(ecUser.getId());		 
		 if(entity.getId()==null){
			entity.setOwn_sys(ecUser.getUser_type());			
			super.getFacade().getEcUserFavotritesService().createEcUserFavotrites(entity);
			super.saveMessage(request, "entity.inserted");
		 }
		return mapping.findForward("success");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		HttpSession session = request.getSession();
		EcUser ecUser=(EcUser)session.getAttribute("ecUser");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcUserFavotrites entity = new EcUserFavotrites();
			entity.setId(new Long(id));
			entity.setUser_id(ecUser.getId());
			getFacade().getEcUserFavotritesService().removeEcUserFavotrites(entity);
			//saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			EcUserFavotrites entity = new EcUserFavotrites();
			entity.getMap().put("pks", pks);
			entity.setUser_id(ecUser.getId());
			getFacade().getEcUserFavotritesService().removeEcUserFavotrites(entity);
			//saveMessage(request, "entity.deleted");
		}
	 
		return mapping.findForward("success");
	}
}
