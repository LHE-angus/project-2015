package com.ebiz.mmt.web.struts.manager.admin;

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

import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileThingsBase;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileThingsBaseAction extends BaseAction {
	
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
		
		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setIs_type(0);
		entity.getMap().put("things",1);
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryListForSelect(entity);
		request.setAttribute("categoryList", categoryList);
		
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String thing_name =(String) dynaBean.get("thing_name");
		pager.setPageSize(15);

		
		KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
		
		if(thing_name!=null){
			entity.setThing_name(thing_name);
		}
		Long recordCount = getFacade().getKonkaMobileThingsBaseService().getKonkaMobileThingsBaseCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileThingsBase> entityList = getFacade().getKonkaMobileThingsBaseService().getKonkaMobileThingsBasePaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		
		String wl_type = (String) dynaBean.get("wl_type");
		
		KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
		if(StringUtils.isNotBlank(wl_type)){
			entity.setThing_type(new Integer(wl_type));
		}
	
		
		super.copyProperties(entity, form);

		if (null == entity.getId()) {
			getFacade().getKonkaMobileThingsBaseService().createKonkaMobileThingsBase(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileThingsBaseService().modifyKonkaMobileThingsBase(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
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
		
		KonkaMobileCategory kmc = new KonkaMobileCategory();
		kmc.setIs_type(0);
		kmc.getMap().put("things",1);
		List<KonkaMobileCategory> categoryList = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryListForSelect(kmc);
		request.setAttribute("categoryList", categoryList);
		
		if (GenericValidator.isLong(id)) {
			KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
			entity.setId(new Long(id));
			entity = getFacade().getKonkaMobileThingsBaseService().getKonkaMobileThingsBase(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			
			kmc.setC_index(new Long(entity.getThing_type()));
			kmc.setIs_type(1);
			kmc.setIs_del(0);
			KonkaMobileCategory kmCategory = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategory(kmc);
			dynaBean.set("wl_index", kmCategory.getC_type());
			dynaBean.set("wl_type", entity.getThing_type());
			
			super.copyProperties(form, entity);
			
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
			entity.setId(new Long(id));
			getFacade().getKonkaMobileThingsBaseService().removeKonkaMobileThingsBase(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaMobileThingsBaseService().removeKonkaMobileThingsBase(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
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

		if (GenericValidator.isLong(id)) {
			KonkaMobileThingsBase entity = new KonkaMobileThingsBase();
			entity.setId(new Long(id));
			entity = getFacade().getKonkaMobileThingsBaseService().getKonkaMobileThingsBase(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}