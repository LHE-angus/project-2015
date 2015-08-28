package com.ebiz.mmt.web.struts.manager.admin;

import java.net.URLDecoder;
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
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-04-21
 */
public class OpinionTypeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String c_name_like = (String) dynaBean.get("c_name_like");
		
		KonkaMobileCategory entity = new KonkaMobileCategory();
		super.copyProperties(entity, form);
		entity.setC_type(7);
		entity.setIs_del(0);
		entity.getMap().put("c_name_like", c_name_like);
		
		Long recordCount = getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileCategory> entityList = getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		if (!GenericValidator.isLong(c_index)) {
			this.saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}

		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setC_index(Long.valueOf(c_index));
		entity = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategory(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "c_index", "mod_id"));
		// end

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");

		KonkaMobileCategory entity = new KonkaMobileCategory();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(c_index)) {// insert
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			entity.setPar_index("0");
			entity.setIs_del(0);
			entity.setC_type(7);
			super.getFacade().getKonkaMobileCategoryService().createKonkaMobileCategory(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			entity.getMap().put("c_index", entity.getC_index());
			getFacade().getKonkaMobileCategoryService().modifyKonkaMobileCategory(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=").append(tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		if (!GenericValidator.isLong(c_index)) {
			saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}

		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setC_index(Long.valueOf(c_index));
		entity = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategory(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "c_index", "mod_id"));
		request.setAttribute("entity", entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String c_index = (String) dynaBean.get("c_index");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(c_index) && GenericValidator.isLong(c_index)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.setC_index(Long.valueOf(c_index));
			entity.setIs_del(1);
			super.getFacade().getKonkaMobileCategoryService().removeKonkaMobileCategory(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaMobileCategoryService().removeKonkaMobileCategory(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "c_index", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}
}