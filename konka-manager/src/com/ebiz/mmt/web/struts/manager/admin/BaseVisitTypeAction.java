package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
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

import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 拜访类型基础数据
 * @author Administrator
 *
 */
public class BaseVisitTypeAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String report_type = (String) dynaBean.get("report_type");
		String visit_type_name = (String) dynaBean.get("visit_type_name");
		String state=(String) dynaBean.get("state");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		BaseVisitType entity = new BaseVisitType();
		if (null!=userInfo&&null!=userInfo.getId()) {
			entity.setAdd_id(userInfo.getId());
		}
	    if (null!=report_type&&StringUtils.isNotBlank(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
		}
	    if (null!=visit_type_name&&StringUtils.isNotBlank(visit_type_name)) {
			entity.setVisit_type_name(visit_type_name);
		}
	    if (null!=state&&StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
	    
		Long recordCount = super.getFacade().getBaseVisitTypeService().getBaseVisitTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<BaseVisitType> entityList = super.getFacade().getBaseVisitTypeService().getBaseVisitTypePaginatedList(entity);
        for (BaseVisitType baseVisitType : entityList) {
        	PeProdUser peuser=new PeProdUser();
        	peuser.setId(baseVisitType.getAdd_id());
        	peuser=super.getFacade().getPeProdUserService().getPeProdUser(peuser);
        	if (null!=peuser&&null!=peuser.getUser_name()) {
        		baseVisitType.getMap().put("add_name", peuser.getUser_name());
			}
		}
		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);
		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		return mapping.findForward("input");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String visit_type_id = (String) dynaBean.get("visit_type_id");
		String report_type = (String) dynaBean.get("report_type");
		String visit_type_name = (String) dynaBean.get("visit_type_name");
		String state = (String) dynaBean.get("state");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		BaseVisitType entity = new BaseVisitType();
		super.copyProperties(entity, form);
		
		if (StringUtils.isNotBlank(visit_type_id)) {
			entity.setVisit_type_id(Long.valueOf(visit_type_id));
		}
		if (StringUtils.isNotBlank(report_type)) {
			entity.setReport_type(Integer.valueOf(report_type));
		}
		if (StringUtils.isNotBlank(visit_type_name)) {
			entity.setVisit_type_name(String.valueOf(visit_type_name));
		}
		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		}
		
		if (null!=visit_type_id&&StringUtils.isNotBlank(visit_type_id)) {//有id就修改 没有就插入
			super.getFacade().getBaseVisitTypeService().modifyBaseVisitType(entity);
		}else{
			entity.setAdd_id(user.getId());
			entity.setAdd_date(new Date());
			super.getFacade().getBaseVisitTypeService().createBaseVisitType(entity);	
		}
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String visit_type_id = (String) dynaBean.get("visit_type_id");
		if (!GenericValidator.isLong(visit_type_id)) {
			super.saveError(request, "errors.long", "visit_type_id");
			return this.list(mapping, form, request, response);
		}
		BaseVisitType entity = new BaseVisitType();
		entity.setVisit_type_id(Long.valueOf(visit_type_id));
		entity = super.getFacade().getBaseVisitTypeService().getBaseVisitType(entity);
		
		entity.setQueryString(super.serialize(request, "visit_type_id", "method"));
		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);
		return mapping.findForward("input");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String visit_type_id = (String) dynaBean.get("visit_type_id");
		String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(visit_type_id)) {
			super.saveError(request, "errors.long", "visit_type_id");
			return this.list(mapping, form, request, response);
		}
		BaseVisitType entity = new BaseVisitType();
		entity.setVisit_type_id(Long.valueOf(visit_type_id));

		Integer number=super.getFacade().getBaseVisitTypeService().removeBaseVisitType(entity);
		//System.out.println(number);
		entity.setQueryString(super.serialize(request, "visit_type_id", "method"));
		
		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
}
