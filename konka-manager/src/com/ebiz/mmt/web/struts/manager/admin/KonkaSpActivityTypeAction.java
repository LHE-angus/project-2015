package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;
import com.ebiz.mmt.domain.KonkaSpActivityType;
import com.ebiz.mmt.domain.KonkaSpMdType;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author OuYang,BaiYang
 * @version 2014-1-23
 */

public class KonkaSpActivityTypeAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String p_type = (String)dynaBean.get("p_type");
		
		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(entity);
		request.setAttribute("pePdModelList", pePdModelList);

		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		
		if(StringUtils.isNotBlank(p_type) && GenericValidator.isLong(p_type) && 1 == Long.valueOf(p_type)) {
			return new ActionForward("/admin/KonkaSpActivityType/orderMeetingForm.jsp");
		}
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String hd_type_like = (String) dynaBean.get("hd_type_like");
		String meeting_type = (String) dynaBean.get("meeting_type");
		// PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaSpActivityType entity = new KonkaSpActivityType();

		entity.setIs_del(0);
		if (StringUtils.isNotBlank(hd_type_like)) {
			entity.getMap().put("hd_type_like", hd_type_like);
		}
		
		if(StringUtils.isNotEmpty(meeting_type) && GenericValidator.isLong(meeting_type) && 1 == Long.valueOf(meeting_type)) {
			entity.setP_type(1l);
		} else {
			meeting_type = "0";
			entity.setP_type(0l);
		}
		super.copyProperties(entity, form);
		
		Long recordCount = getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSpActivityType> entityList = getFacade().getKonkaSpActivityTypeService()
				.getKonkaSpActivityTypePaginatedList(entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("p_type", meeting_type);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 指定品类/机型信息
		String md_name_pls = (String) dynaBean.get("md_name_pls");
		String md_name_jxs = (String) dynaBean.get("md_name_jxs");
		String meeting_type = (String) dynaBean.get("meeting_type");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaSpActivityType entity = new KonkaSpActivityType();
		super.copyProperties(entity, form);

		if (null == entity.getId()) {
			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			entity.setAdd_user(ui.getId().toString());
			entity.setAdd_user_job_id(ui.getJob_id());
			if (StringUtils.isNotBlank(md_name_pls)) {
				entity.getMap().put("md_name_pls", md_name_pls);
			}
			if (StringUtils.isNotBlank(md_name_jxs)) {
				entity.getMap().put("md_name_jxs", md_name_jxs);
			}

			getFacade().getKonkaSpActivityTypeService().createKonkaSpActivityType(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user(ui.getId().toString());
			entity.setUpdate_user_job_id(ui.getJob_id());
			if (StringUtils.isNotBlank(md_name_pls)) {
				entity.getMap().put("md_name_pls", md_name_pls);
			}
			if (StringUtils.isNotBlank(md_name_jxs)) {
				entity.getMap().put("md_name_jxs", md_name_jxs);
			}
			getFacade().getKonkaSpActivityTypeService().modifyKonkaSpActivityType(entity);
			saveMessage(request, "entity.updated");
		}
		dynaBean.set("meeting_type", meeting_type);

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&meeting_type=" + meeting_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String p_type = (String) dynaBean.get("p_type");

		KonkaSpActivityType entity = new KonkaSpActivityType();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityType(entity);
		request.setAttribute("entity", entity);
		
		KonkaSpMdType mdType_pl = new KonkaSpMdType();
		mdType_pl.setLink_id(entity.getId());
		mdType_pl.setType(1);
		List<KonkaSpMdType> mdTypePlList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(mdType_pl);
		request.setAttribute("mdTypePlList", mdTypePlList);
		
		KonkaSpMdType mdType_jx = new KonkaSpMdType();
		mdType_jx.setLink_id(entity.getId());
		mdType_jx.setType(2);
		List<KonkaSpMdType> mdTypeJxList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(mdType_jx);
		request.setAttribute("mdTypeJxList", mdTypeJxList);
		
		PePdModel pePdModel = new PePdModel();
	    pePdModel.setIs_del(0);
		List<PePdModel> pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);
		
		entity.setQueryString(super.serialize(request, "id", "method"));
		
		super.copyProperties(form, entity);
		dynaBean.set("p_type", p_type);
		
		if(StringUtils.isNotBlank(p_type) && GenericValidator.isLong(p_type) && 1 == Long.valueOf(p_type)) {
			return new ActionForward("/admin/KonkaSpActivityType/orderMeetingForm.jsp");
		}
		
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String p_type = (String) dynaBean.get("p_type");
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSpActivityType entity = new KonkaSpActivityType();
			entity.setIs_del(0);
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaSpActivityTypeService().removeKonkaSpActivityType(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileTerminalFeedback entity = new KonkaMobileTerminalFeedback();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaMobileTerminalFeedbackService().removeKonkaMobileTerminalFeedback(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&meeting_type=" + p_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String p_type = (String) dynaBean.get("p_type");
		
		if (GenericValidator.isLong(id)) {
			KonkaSpActivityType entity = new KonkaSpActivityType();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaSpActivityTypeService().getKonkaSpActivityType(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			request.setAttribute("entity", entity);

			KonkaSpMdType mdType_pl = new KonkaSpMdType();
			mdType_pl.setLink_id(entity.getId());
			mdType_pl.setType(1);
			List<KonkaSpMdType> mdTypePlList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(mdType_pl);
			request.setAttribute("mdTypePlList", mdTypePlList);

			KonkaSpMdType mdType_jx = new KonkaSpMdType();
			mdType_jx.setLink_id(entity.getId());
			mdType_jx.setType(2);
			List<KonkaSpMdType> mdTypeJxList = getFacade().getKonkaSpMdTypeService().getKonkaSpMdTypeList(mdType_jx);
			request.setAttribute("mdTypeJxList", mdTypeJxList);

			super.copyProperties(form, entity);
			dynaBean.set("p_type", p_type);

			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

}