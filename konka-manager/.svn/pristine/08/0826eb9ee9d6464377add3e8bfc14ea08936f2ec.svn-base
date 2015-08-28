package com.ebiz.mmt.web.struts.manager.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Category;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xu,ZhengYang
 * @date 2011-02-10 09:09:08
 */

public class SetCategoryAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		setNaviStringToRequestScope(form, request);

		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;

		dynaBean.set("order_sort", "0");

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		Category entity = new Category();
		super.copyProperties(entity, form);

		if (null == entity.getIs_del()) {
			entity.setIs_del(0);
			dynaBean.set("is_del", "0");
		}

		Long recordCount = getFacade().getCategoryService().getCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		request.setAttribute("entityList", getFacade().getCategoryService().getCategoryPaginatedList(entity));

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

		Category entity = new Category();
		super.copyProperties(entity, form);

		if (null == entity.getC_index()) {
			entity.setIs_del(0);
			entity.setPar_index("0");
			super.getFacade().getCategoryService().createCategory(entity);
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			getFacade().getCategoryService().modifyCategory(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + entity.getC_type());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		if (GenericValidator.isLong(c_index)) {
			Category entity = new Category();
			entity.setC_index(Long.valueOf(c_index));
			entity = super.getFacade().getCategoryService().getCategory(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "c_index", "method"));
			// end
			super.copyProperties(form, entity);

			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String c_index = (String) dynaBean.get("c_index");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String c_type = (String) dynaBean.get("c_type");

		if (!StringUtils.isBlank(c_index) && GenericValidator.isLong(c_index)) {
			Category entity = new Category();
			entity.setC_index(new Long(c_index));
			entity.setIs_del(1);
			super.getFacade().getCategoryService().modifyCategory(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			Category entity = new Category();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			super.getFacade().getCategoryService().modifyCategory(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + c_type);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}
