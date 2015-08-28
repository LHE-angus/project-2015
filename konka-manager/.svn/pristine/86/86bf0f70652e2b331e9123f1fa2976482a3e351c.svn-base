package com.ebiz.mmt.web.struts.manager.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOaModuleContent;
import com.ebiz.mmt.domain.KonkaOaModuleType;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-05-16
 */
public class KonkaOaModuleTypeAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String module_name_like = (String) dynaBean.get("module_name_like");
		// String dept_id = (String) dynaBean.get("dept_id");

		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == ui || null == ui.getDept_id()) {
			String msg = super.getMessage(request, "errors.permission");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaOaModuleType entity = new KonkaOaModuleType();

		KonkaDept konkaDept = new KonkaDept();
		if (ui.getDept_id() == 0L) {
			konkaDept.setDept_id(0L);
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		} else {
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
		}

		entity.setDept_id(konkaDept.getDept_id());
		
		if (StringUtils.isNotBlank(module_name_like)) {
			entity.getMap().put("module_name_like", module_name_like);
		}
		entity.setIs_del(0);

		Long recordCount = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaOaModuleType> entityList = super.getFacade().getKonkaOaModuleTypeService()
				.getKonkaOaModuleTypePaginatedList(entity);

		if (entityList.size() > 0) {
			request.setAttribute("entityList", entityList);
		}

		dynaBean.set("dept_name", konkaDept.getDept_name());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == ui || null == ui.getDept_id()) {
			String msg = super.getMessage(request, "errors.permission");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaDept konkaDept = new KonkaDept();
		if (ui.getDept_id() == 0L) {
			konkaDept.setDept_id(0L);
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		} else {
			konkaDept = getKonkaDeptForFgs(ui.getDept_id());
		}

		dynaBean.set("dept_name", konkaDept.getDept_name());
		dynaBean.set("dept_id", konkaDept.getDept_id());
		
		KonkaBaseTypeData oaType=new KonkaBaseTypeData();
		oaType.setType_name("模板类型");
        oaType.setType_id(100018L);
		oaType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(oaType);
		
		List<KonkaBaseTypeData> oaTypeList=null;
		if (null!=oaType && null!=oaType.getType_id()) {
			Long parTypeId=oaType.getType_id();
			oaType=new KonkaBaseTypeData();
			oaType.setPar_type_id(parTypeId);
			oaTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(oaType);
		}
		dynaBean.set("oaTypeList", oaTypeList);
		
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String module_id = (String) dynaBean.get("module_id");

		if (!GenericValidator.isLong(module_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaOaModuleType entity = new KonkaOaModuleType();
		entity.setModule_id(Long.valueOf(module_id));
		entity = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleType(entity);

		if (null == entity) {
			String msg = super.getMessage(request, "entity.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(form, entity);

		KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();// 取模板内容
		konkaOaModuleContent.setModule_id(Long.valueOf(module_id));
		List<KonkaOaModuleContent> konkaOaModuleContentList = super.getFacade().getKonkaOaModuleContentService()
				.getKonkaOaModuleContentList(konkaOaModuleContent);
		if (null != konkaOaModuleContentList && konkaOaModuleContentList.size() > 0) {
			dynaBean.set("module_content", konkaOaModuleContentList.get(0).getContent());
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		dynaBean.set("dept_name", konkaDept.getDept_name());
		
        // 获取模板类型(此方法在20150319发现异常.需要写死TYPE_ID=100018L)
        KonkaBaseTypeData oaType = new KonkaBaseTypeData();
        oaType.setType_name("模板类型");
        oaType.setType_id(100018L);
        oaType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(oaType);

        List<KonkaBaseTypeData> oaTypeList = null;
        if (null != oaType && null != oaType.getType_id()) {
            Long parTypeId = oaType.getType_id();
            oaType = new KonkaBaseTypeData();
            oaType.setPar_type_id(parTypeId);
            oaTypeList =
                    super.getFacade().getKonkaBaseTypeDataService()
                            .getKonkaBaseTypeDataList(oaType);
        }
        dynaBean.set("oaTypeList", oaTypeList);


		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String module_id = (String) dynaBean.get("module_id");

		if (!GenericValidator.isLong(module_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaOaModuleType entity = new KonkaOaModuleType();
		entity.setModule_id(Long.valueOf(module_id));
		entity = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleType(entity);

		if (null == entity) {
			String msg = super.getMessage(request, "entity.empty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		super.copyProperties(form, entity);

		KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();// 取模板内容
		konkaOaModuleContent.setModule_id(Long.valueOf(module_id));
		List<KonkaOaModuleContent> konkaOaModuleContentList = super.getFacade().getKonkaOaModuleContentService()
				.getKonkaOaModuleContentList(konkaOaModuleContent);
		if (null != konkaOaModuleContentList && konkaOaModuleContentList.size() > 0) {
			dynaBean.set("module_content", konkaOaModuleContentList.get(0).getContent());
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		dynaBean.set("dept_name", konkaDept.getDept_name());
		
		//获取模板类型
		KonkaBaseTypeData oaType=new KonkaBaseTypeData();
		oaType.setType_name("模板类型");
        oaType.setType_id(100018L);
		oaType=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(oaType);
		
		List<KonkaBaseTypeData> oaTypeList=null;
		if (null!=oaType && null!=oaType.getType_id()) {
			Long parTypeId=oaType.getType_id();
			oaType=new KonkaBaseTypeData();
			oaType.setPar_type_id(parTypeId);
			oaTypeList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(oaType);
		}
		dynaBean.set("oaTypeList", oaTypeList);
		
		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String module_id = (String) dynaBean.get("module_id");
		String module_content = (String) dynaBean.get("module_content");

		KonkaOaModuleType entity = new KonkaOaModuleType();
		super.copyProperties(entity, form);
		if (GenericValidator.isLong(module_id)) {

			entity.setModule_id(Long.valueOf(module_id));
			super.getFacade().getKonkaOaModuleTypeService().modifyKonkaOaModuleTypeForContent(entity, module_content);
			saveMessage(request, "entity.updated");
		} else {
			super.getFacade().getKonkaOaModuleTypeService().createKonkaOaModuleTypeForContent(entity, module_content);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateModulename(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String module_name = (String) dynaBean.get("module_name");
		String dept_id = (String) dynaBean.get("dept_id");
		KonkaOaModuleType entity = new KonkaOaModuleType();
		String isExist = "null";

		if (StringUtils.isNotBlank(module_name) && GenericValidator.isLong(dept_id)) {
			entity.setModule_name(module_name);
			entity.setDept_id(Long.valueOf(dept_id));
			List<KonkaOaModuleType> entityList = getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleTypeList(
					entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {

				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward getContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String module_id = (String) dynaBean.get("module_id");
		KonkaOaModuleContent entity = new KonkaOaModuleContent();
		entity.setModule_id(Long.valueOf(module_id));

		List<KonkaOaModuleContent> entityList = getFacade().getKonkaOaModuleContentService()
				.getKonkaOaModuleContentList(entity);

		if (entityList.size() == 0 || entityList == null) {
			return null;
		}

		JSONArray json = JSONArray.fromObject(entityList);

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward getExpenseClaims(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String module_id = (String) dynaBean.get("module_id");
		KonkaoaCategory entity = new KonkaoaCategory();
		entity.setModule_id(Long.valueOf(module_id));

		List<KonkaoaCategory> entityList = getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(entity);

		if (entityList.size() == 0 || entityList == null) {
			return null;
		}

		JSONArray json = JSONArray.fromObject(entityList);

		super.render(response, json.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String module_id = (String) dynaBean.get("module_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(module_id) && GenericValidator.isLong(module_id)) {
			KonkaOaModuleType entity = new KonkaOaModuleType();
			entity.setModule_id(new Long(module_id));
			super.getFacade().getKonkaOaModuleTypeService().removeKonkaOaModuleType(entity);

			KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();
			konkaOaModuleContent.setId(new Long(module_id));
			super.getFacade().getKonkaOaModuleContentService().removeKonkaOaModuleContent(konkaOaModuleContent);

			saveMessage(request, "entity.deleted");

		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaOaModuleType entity = new KonkaOaModuleType();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaOaModuleTypeService().removeKonkaOaModuleType(entity);

			KonkaOaModuleContent konkaOaModuleContent = new KonkaOaModuleContent();
			konkaOaModuleContent.getMap().put("pks", pks);
			super.getFacade().getKonkaOaModuleContentService().removeKonkaOaModuleContent(konkaOaModuleContent);

			saveMessage(request, "entity.deleted");

		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
