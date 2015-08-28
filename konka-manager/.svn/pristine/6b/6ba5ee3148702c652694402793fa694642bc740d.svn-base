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

import com.ebiz.mmt.domain.KonkaMobileStorages;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileStoragesAction extends BaseAction {
	
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
		dynaBean.set("queryString", super.serialize(request, "storage_id", "method"));
//		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		return mapping.findForward("input");
	}
	//添加仓位
	public ActionForward addForStorages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
//		String storage_id = (String) dynaBean.get("storage_id");
		
		String storage_id = (String)dynaBean.get("storage_id");
		dynaBean.set("storage_id", storage_id);
		
		dynaBean.set("queryString", super.serialize(request, "storage_id", "method"));
		
		KonkaMobileStorages entity = new KonkaMobileStorages();
		entity.setIs_del(0);
		entity.setPar_id(0l);
		List<KonkaMobileStorages> entityList = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesList(entity);
		request.setAttribute("entityList", entityList);
		
		return new ActionForward("/admin/KonkaMobileStorages/addForStorages.jsp");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String storage_name = (String) dynaBean.get("storage_name");
		String is_del = (String) dynaBean.get("is_del");
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaMobileStorages entity = new KonkaMobileStorages();
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(new Integer(is_del));
		}else{
			entity.setIs_del(0);
		}
		if(StringUtils.isNotBlank(storage_name)){
			entity.setStorage_name(storage_name);
		}
		entity.getMap().put("is_storages", 1);
		Long recordCount = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileStorages> entityList = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}
	public ActionForward storages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");
		String is_del = (String) dynaBean.get("is_del");
		String storage_name = (String) dynaBean.get("storage_name");
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		
		KonkaMobileStorages entity = new KonkaMobileStorages();
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(new Integer(is_del));
		}else{
			entity.setIs_del(0);
		}
		if(StringUtils.isNotBlank(storage_name)){
			entity.setStorage_name(storage_name);
		}
		if(StringUtils.isNotBlank(storage_id)){
			entity.setPar_id(new Long(storage_id));
		}
		entity.getMap().put("is_child", 1);
		dynaBean.set("storage_id", storage_id);
		
		Long recordCount = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileStorages> entityList = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return new ActionForward("/admin/KonkaMobileStorages/storages.jsp");
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
		String storage_type = (String) dynaBean.get("storage_type");
		String storage_par_name = (String) dynaBean.get("storage_par_name");
		KonkaMobileStorages entity = new KonkaMobileStorages();
		super.copyProperties(entity, form);
		if(StringUtils.isNotBlank(storage_type)&&storage_type.equals("1")){
			entity.setStorage_com(new Long(storage_par_name));
		}
		if(StringUtils.isNotBlank(storage_type)&&storage_type.equals("2")){
			entity.setStorage_areacom(new Long(storage_par_name));
		}
		entity.setIs_del(0);
		entity.setPar_id(0l);
		if (null == entity.getStorage_id()) {
			getFacade().getKonkaMobileStoragesService().createKonkaMobileStorages(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			
			KonkaMobileStorages entity1 = new KonkaMobileStorages();
			entity1.setPar_id(entity.getStorage_id());
			entity1.setStorage_type(3);
			entity1.setIs_del(0);
			List<KonkaMobileStorages> storageList =super.getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesList(entity1);
			for(KonkaMobileStorages konkaStorages :  storageList){
				KonkaMobileStorages entity2 = new KonkaMobileStorages();
				entity2.setStorage_id(konkaStorages.getStorage_id());
				if(StringUtils.isNotBlank(storage_type)&&storage_type.equals("1")){
					entity2.setStorage_com(new Long(storage_par_name));
				}
				if(StringUtils.isNotBlank(storage_type)&&storage_type.equals("2")){
					entity2.setStorage_areacom(new Long(storage_par_name));
				}
				entity2.setPar_id(entity.getStorage_id());
				entity2.setStorage_type(3);
				entity2.setIs_del(0);
				getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity2);
			}
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
	//存入仓位信息
	public ActionForward saveForStorages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		String par_id = (String) dynaBean.get("par_id");
		String storage_name = (String) dynaBean.get("storage_name");
		
		KonkaMobileStorages entity = new KonkaMobileStorages();
		entity.setIs_del(0);
		entity.setStorage_type(3);
		if(StringUtils.isNotBlank(par_id)){
		entity.setPar_id(new Long(par_id));
		}
		if(StringUtils.isNotBlank(storage_name)){
			entity.setStorage_name(storage_name);
		}
		KonkaMobileStorages storages = new KonkaMobileStorages();
		storages.setStorage_id(entity.getPar_id());
		storages.setIs_del(0);
		storages.setPar_id(0l);
		storages= super.getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(storages);
		if(storages.getStorage_areacom()!=null){
			entity.setStorage_areacom(storages.getStorage_areacom());
		}
		if(storages.getStorage_com()!=null){
			entity.setStorage_com(storages.getStorage_com());
		}
		if (null == entity.getStorage_id()) {
			getFacade().getKonkaMobileStoragesService().createKonkaMobileStorages(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString((String) dynaBean.get("queryString")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
	
	public ActionForward saveForEditStorages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		String par_id = (String) dynaBean.get("par_id");
		String storage_name = (String) dynaBean.get("storage_name");
		
		KonkaMobileStorages entity = new KonkaMobileStorages();
		entity.setIs_del(0);
		entity.setStorage_type(3);
		super.copyProperties(entity, form);
		if(StringUtils.isNotBlank(par_id)){
		entity.setPar_id(new Long(par_id));
		}
		if(StringUtils.isNotBlank(storage_name)){
			entity.setStorage_name(storage_name);
		}
		KonkaMobileStorages storages = new KonkaMobileStorages();
		storages.setStorage_id(entity.getPar_id());
		storages.setIs_del(0);
		storages.setPar_id(0l);
		storages= super.getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(storages);
		if(storages.getStorage_areacom()!=null){
			entity.setStorage_areacom(storages.getStorage_areacom());
		}
		if(storages.getStorage_com()!=null){
			entity.setStorage_com(storages.getStorage_com());
		}
	
		getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
		saveMessage(request, "entity.updated");

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
		String storage_id = (String) dynaBean.get("storage_id");
		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		if (GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity = getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			if(entity.getStorage_type()==1){
				dynaBean.set("storage_par_name", entity.getStorage_com());
//				ImportDataTypes data = new ImportDataTypes();    
//				data.setData_type(5l);
//				data.setId(entity.getStorage_com());
//				data=super.getFacade().getImportDataTypesService().getImportDataTypes(data);
//				dynaBean.set("storage_parname", data.getType_name());
			}
			if(entity.getStorage_type()==2){
				dynaBean.set("storage_par_name", entity.getStorage_areacom());
//				KonkaDept deptInfo = new KonkaDept();
//				deptInfo.setPar_id(1l);
//				deptInfo.setDept_id(entity.getStorage_areacom());
//				deptInfo= super.getFacade().getKonkaDeptService().getKonkaDept(deptInfo);
//				
//				dynaBean.set("storage_parname", deptInfo.getDept_name());
			}
			
			entity.setQueryString(super.serialize(request, "storage_id", "method"));
			super.copyProperties(form, entity);
			return mapping.findForward("input");
			//return new ActionForward("/admin/KonkaMobileStorages/edit.jsp");
		} else {
			saveError(request, "errors.long", storage_id);
			return mapping.findForward("list");
		}
	}
	public ActionForward editForStorage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");
		
		KonkaMobileStorages entityStorages = new KonkaMobileStorages();
		entityStorages.setIs_del(0);
		entityStorages.setPar_id(0l);
		List<KonkaMobileStorages> entityList = getFacade().getKonkaMobileStoragesService().getKonkaMobileStoragesList(entityStorages);
		request.setAttribute("entityList", entityList);
		
		if (GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity.setIs_del(0);
			entity.setStorage_type(3);
			entity = getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "storage_id", "method"));
			super.copyProperties(form, entity);
			
			return new ActionForward("/admin/KonkaMobileStorages/editForStorages.jsp");
		} else {
			saveError(request, "errors.long", storage_id);
			return mapping.findForward("list");
			
		}
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(storage_id) && GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity.setIs_del(1);
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "storage_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	public ActionForward deleteForStorages(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(storage_id) && GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity.setIs_del(1);
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getKonkaMobileStoragesService().modifyKonkaMobileStorages(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "storage_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");

		if (GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity = getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", storage_id);
			return mapping.findForward("list");
		}
	}
	public ActionForward viewForStorage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String storage_id = (String) dynaBean.get("storage_id");

		if (GenericValidator.isLong(storage_id)) {
			KonkaMobileStorages entity = new KonkaMobileStorages();
			entity.setStorage_id(new Long(storage_id));
			entity = getFacade().getKonkaMobileStoragesService().getKonkaMobileStorages(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return new ActionForward("/admin/KonkaMobileStorages/viewStorages.jsp");
		} else {
			saveError(request, "errors.long", storage_id);
			return mapping.findForward("list");
		}
	}
}