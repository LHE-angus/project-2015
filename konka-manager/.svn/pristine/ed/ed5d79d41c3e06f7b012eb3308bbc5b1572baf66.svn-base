package com.ebiz.mmt.web.struts.manager.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaOaModuleType;
import com.ebiz.mmt.domain.KonkaoaCategory;

/**
 * @author Hu,Hao
 * @version 2013-05-19
 */
public class KonkaoaCategoryAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String module_id = (String) dynaBean.get("module_id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		if (!GenericValidator.isLong(module_id) || !GenericValidator.isLong(mod_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
		konkaOaModuleType.setModule_id(Long.valueOf(module_id));
		konkaOaModuleType = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleType(konkaOaModuleType);

		if (null != konkaOaModuleType) {
			dynaBean.set("module_name", konkaOaModuleType.getModule_name());
		}

		KonkaoaCategory entity = new KonkaoaCategory();
		entity.setModule_id(Long.valueOf(module_id));
		entity.setIs_del(0);

		List<KonkaoaCategory> entityList = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryList(entity);
		request.setAttribute("entityList", entityList);

		dynaBean.set("mod_id", mod_id);
		dynaBean.set("module_id", module_id);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		if (null != entity) {
			dynaBean.set("module_name", entity.getModule_name());
		}

		dynaBean.set("module_id", module_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String module_id = (String) dynaBean.get("module_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(module_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaoaCategory entity = new KonkaoaCategory();

		super.copyProperties(entity, form);
		
		entity.setPar_index("0");

		super.getFacade().getKonkaoaCategoryService().createKonkaoaCategory(entity);

		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&module_id=" + module_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");
		String mod_id = (String) dynaBean.get("mod_id");
		String module_id = (String) dynaBean.get("module_id");

		if (!GenericValidator.isLong(c_index)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaoaCategory entity = new KonkaoaCategory();
		entity.setC_index(Long.valueOf(c_index));
		entity.setIs_del(1);
		super.getFacade().getKonkaoaCategoryService().modifyKonkaoaCategory(entity);
		saveMessage(request, "entity.deleted");
		
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&module_id=" + module_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
