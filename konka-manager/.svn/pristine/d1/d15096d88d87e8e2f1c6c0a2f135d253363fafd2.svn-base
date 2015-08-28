package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcHomeFloor;
import com.ebiz.mmt.domain.EcHomeFloorData;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * 
 * @author TUDP
 * @version 2015-1-28 下午2:17:40
 */
public class EcHomeFloorDataAction extends BaseAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager"); 
		String data_type_id = (String) dynaBean.get("data_type_id"); 
		String floor_id = (String) dynaBean.get("floor_id"); 

		EcHomeFloor ecHomeFloor = new EcHomeFloor(); 
		ecHomeFloor.setId(new Long(floor_id));
		ecHomeFloor = super.getFacade().getEcHomeFloorService().getEcHomeFloor(ecHomeFloor);
		request.setAttribute("ecHomeFloor", ecHomeFloor);
		
		EcHomeFloorData entity = new EcHomeFloorData(); 
		super.copyProperties(entity, form);
		entity.setData_type_id(new Long(data_type_id));
		entity.setFloor_id(new Long(floor_id));
		
		Long recordCount = super.getFacade().getEcHomeFloorDataService().getEcHomeFloorDataCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcHomeFloorData> entityList = super.getFacade().getEcHomeFloorDataService().getEcHomeFloorDataPaginatedList(entity);
		
		KonkaBaseTypeData baseTypeData = new KonkaBaseTypeData();
		baseTypeData.setId(entity.getData_type_id());
		baseTypeData =super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(baseTypeData);
		request.setAttribute("baseTypeData", baseTypeData);
	 
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;  
		String data_type_id = (String) dynaBean.get("data_type_id"); 
		String floor_id = (String) dynaBean.get("floor_id"); 
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method")); 
		
		EcHomeFloor ecHomeFloor = new EcHomeFloor(); 
		ecHomeFloor.setId(new Long(floor_id));
		ecHomeFloor = super.getFacade().getEcHomeFloorService().getEcHomeFloor(ecHomeFloor);
		request.setAttribute("ecHomeFloor", ecHomeFloor);
		
		KonkaBaseTypeData baseTypeData = new KonkaBaseTypeData();
		baseTypeData.setId(new Long(data_type_id));
		baseTypeData =super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(baseTypeData);
		request.setAttribute("baseTypeData", baseTypeData);
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {

			EcHomeFloorData entity = new EcHomeFloorData();
			entity.setId(new Long(id));
			entity = getFacade().getEcHomeFloorDataService().getEcHomeFloorData(entity);
			super.copyProperties(form, entity); 
			 
			return mapping.findForward("view");
		} else {
			saveMessage(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id"); 

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		EcHomeFloorData entity = new EcHomeFloorData();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcHomeFloorDataService().getEcHomeFloorData(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		} 
		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);
		EcHomeFloor ecHomeFloor = new EcHomeFloor(); 
		ecHomeFloor.setId(entity.getFloor_id());
		ecHomeFloor = super.getFacade().getEcHomeFloorService().getEcHomeFloor(ecHomeFloor);
		request.setAttribute("ecHomeFloor", ecHomeFloor);
		
		KonkaBaseTypeData baseTypeData = new KonkaBaseTypeData();
		baseTypeData.setId(entity.getData_type_id());
		baseTypeData =super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(baseTypeData);
		request.setAttribute("baseTypeData", baseTypeData);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id"); 
		String data_type_id = (String) dynaBean.get("data_type_id"); 
		String floor_id = (String) dynaBean.get("floor_id"); 
		EcHomeFloorData entity = new EcHomeFloorData();
		super.copyProperties(entity, form);

		List<UploadFile> uploadFileList = super.uploadFile(form, "files/lunbotu/", true, 0, new int[] { 60, 240, 400, 480, 720, 800 });
		String ctx = super.getCtxPath(request);

		for (UploadFile uploadFile : uploadFileList) {
			if ("img_file".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setImg_url(ctx + "/" + uploadFile.getFileSavePath());
			}
		}

		if (StringUtils.isBlank(id)) { 
			super.getFacade().getEcHomeFloorDataService().createEcHomeFloorData(entity);
			saveMessage(request, "entity.inserted");
		} else {
			if (!GenericValidator.isLong(id)) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}
			
			super.getFacade().getEcHomeFloorDataService().modifyEcHomeFloorData(entity);
			saveMessage(request, "entity.updated");
		} 
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("data_type_id=" + data_type_id);
		pathBuffer.append("&").append("floor_id=" + floor_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String floor_id = (String) dynaBean.get("floor_id");
		String data_type_id = (String) dynaBean.get("data_type_id"); 
		
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			EcHomeFloorData entity = new EcHomeFloorData();
			entity.setId(new Long(id)); 
			getFacade().getEcHomeFloorDataService().removeEcHomeFloorData(entity);
		} else if (!ArrayUtils.isEmpty(pks)) {
			EcHomeFloorData entity = new EcHomeFloorData(); 
			entity.getMap().put("pks", pks);
			getFacade().getEcHomeFloorDataService().removeEcHomeFloorData(entity);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&floor_id=" + floor_id);
		pathBuffer.append("&data_type_id=" + data_type_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
