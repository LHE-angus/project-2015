package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaXxPropCategory;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-04-02
 */
public class KonkaXxPropCategoryAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String category_name_like = (String) dynaBean.get("category_name_like");
		String cls_id = (String) dynaBean.get("cls_id");

		KonkaXxPropCategory entity = new KonkaXxPropCategory();
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			entity.setCls_id(Long.valueOf(cls_id));
		}

		Long recordCount = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxPropCategory> entityList = super.getFacade().getKonkaXxPropCategoryService()
				.getKonkaXxPropCategoryForPdNamePaginatedList(entity);

		if (entityList.size() > 0) {
			request.setAttribute("entityList", entityList);
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(category_id)) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		KonkaXxPropCategory entity = new KonkaXxPropCategory();
		entity.setCategory_id(Long.valueOf(category_id));
		entity = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategory(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		entity.setQueryString(super.serialize(request, "category_id", "mod_id"));

		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String cls_id = (String) dynaBean.get("cls_id");
		String category_name = (String) dynaBean.get("category_name");
		String category_id = (String) dynaBean.get("category_id");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaXxPropCategory konkaXxPropCategory = new KonkaXxPropCategory();
		konkaXxPropCategory.setCategory_name(category_name);
		konkaXxPropCategory.setCls_id(Long.valueOf(cls_id));
		Long c = super.getFacade().getKonkaXxPropCategoryService().getKonkaXxPropCategoryCount(konkaXxPropCategory);

		KonkaXxPropCategory entity = new KonkaXxPropCategory();
		super.copyProperties(entity, form);

		if (GenericValidator.isLong(category_id)) {

			if (c > 1) {
				String msg = super.getMessage(request, "konka.xx.property.exist");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				entity.setCategory_id(Long.valueOf(category_id));
				super.getFacade().getKonkaXxPropCategoryService().modifyKonkaXxPropCategory(entity);
			}

		} else {
			if (c > 0) {
				String msg = super.getMessage(request, "konka.xx.property.exist");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				super.getFacade().getKonkaXxPropCategoryService().createKonkaXxPropCategory(entity);
			}
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
