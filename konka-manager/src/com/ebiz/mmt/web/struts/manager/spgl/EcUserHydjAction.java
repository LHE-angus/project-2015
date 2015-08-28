package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserLevelAuditInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2013-12-20
 */
public class EcUserHydjAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request); 
		DynaBean dynaBean = (DynaBean) form;  
		String name_like = (String) dynaBean.get("name_like");
		Pager pager = (Pager) dynaBean.get("pager");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcUser entity = new EcUser(); 
		
		entity.getMap().put("name_like", name_like);
		Long recordCount = super.getFacade().getEcUserService().getEcUserForHydjCount(entity);
		
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserPaginatedForHydjList(entity);
		
		request.setAttribute("entityList", entityList); 
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String queryString = (String) dynaBean.get("queryString");
		

		String cardLevel = (String) dynaBean.get("cardLevel");
		String memo = (String) dynaBean.get("memo");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		
		if (StringUtils.isNotEmpty(id)) {
		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcUserService().getEcUser(entity);
		 
			EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel(); 
			ecBaseCardLevel.setCard_level(new Long(cardLevel)); 
			ecBaseCardLevel =super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevel(ecBaseCardLevel);
			 
			EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
			auditInfo.setCard_no(entity.getCard_no());
			auditInfo.setEc_user_id(entity.getId());
			auditInfo.setLevel_id(ecBaseCardLevel.getCard_level());
			auditInfo.setLevel_name(ecBaseCardLevel.getCard_level_name());
			auditInfo.setOld_level_name(entity.getEcBaseCardLevel().getCard_level_name());
			auditInfo.setOpr_user_id(user.getId().toString());
			auditInfo.setOpr_user_name(user.getUser_name());
			auditInfo.setReal_name(entity.getReal_name());
			auditInfo.setMemo(memo);
			
			EcBaseCardNo ecBaseCardNo = new EcBaseCardNo();
			ecBaseCardNo.setCard_no(entity.getCard_no());
			ecBaseCardNo.setCard_level(ecBaseCardLevel.getCard_level());
			super.getFacade().getEcBaseCardNoService().modifyEcBaseCardNoBYCardNo(ecBaseCardNo);
			super.getFacade().getEcUserLevelAuditInfoService().createEcUserLevelAuditInfo(auditInfo);
			
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcUserService().getEcUser(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);
		EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
		auditInfo.setEc_user_id(entity.getId());
		request.setAttribute("auditInfoList", super.getFacade().getEcUserLevelAuditInfoService().getEcUserLevelAuditInfoList(auditInfo));
		request.setAttribute("integral", super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(entity.getId()));
		EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel(); 
		request.setAttribute("eEcBaseCardLevelList", super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(ecBaseCardLevel));
		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcUserService().getEcUser(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);
		EcUserLevelAuditInfo auditInfo = new EcUserLevelAuditInfo();
		auditInfo.setEc_user_id(entity.getId());
		request.setAttribute("auditInfoList", super.getFacade().getEcUserLevelAuditInfoService().getEcUserLevelAuditInfoList(auditInfo));
		request.setAttribute("integral", super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(entity.getId()));
		EcBaseCardLevel ecBaseCardLevel = new EcBaseCardLevel(); 
		request.setAttribute("eEcBaseCardLevelList", super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelList(ecBaseCardLevel));
		return mapping.findForward("input");
	} 
}
