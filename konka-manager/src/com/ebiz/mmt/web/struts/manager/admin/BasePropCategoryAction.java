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

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropCategory;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,ShunHua
 * @version 2011-09-23
 */
public class BasePropCategoryAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		// String mod_id = (String) dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String category_name_like = (String) dynaBean.get("category_name_like");
		String cls_id_par = (String) dynaBean.get("cls_id");

		BasePropCategory entity = new BasePropCategory();
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}
		if (GenericValidator.isLong(cls_id_par)) {
			entity.getMap().put("cls_id_par", cls_id_par);
		}

		Long recordCount = super.getFacade().getBasePropCategoryService().getBasePropCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<BasePropCategory> basePropCategoryList = super.getFacade().getBasePropCategoryService()
				.getBasePropCategoryPaginatedList(entity);

		if (null != basePropCategoryList && basePropCategoryList.size() > 0) {
			for (BasePropCategory temp : basePropCategoryList) {
				Long cls_id = temp.getCls_id();
				BasePdClazz basePdClazz = new BasePdClazz();
				basePdClazz.setCls_id(cls_id);
				basePdClazz.setIs_del(0);

				basePdClazz = super.getFacade().getBasePdClazzService().getBasePdClazz(basePdClazz);

				if (null != basePdClazz) {
					temp.getMap().put("cls_full_name", basePdClazz.getFull_name().replace(",", ">>"));
				}
			}
		}

//		PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);
		
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList =  (List<PeRoleUser>) request.getSession().getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ge_20_le_29_or_eq_10 = false;
		for (PeRoleUser  peRoleUser: peRoleUserList) {
			if ((peRoleUser.getRole_id() >= 20 && peRoleUser.getRole_id() <= 29) || peRoleUser.getRole_id() == 10) {
				role_id_ge_20_le_29_or_eq_10 = true;
				break;
			}
		}

		// 判断当前用户是否是事业部或系统管理员
		String is_division_or_admin = "false";
		if (role_id_ge_20_le_29_or_eq_10) {
			is_division_or_admin = "true";
		}
		request.setAttribute("is_division_or_admin", is_division_or_admin);

		request.setAttribute("entityList", basePropCategoryList);

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		
		setNaviStringToRequestScope(form, request);

		dynaBean.set("order_value", "0");

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(category_id)) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		BasePropCategory entity = new BasePropCategory();
		entity.setCategory_id(Long.valueOf(category_id));
		entity = super.getFacade().getBasePropCategoryService().getBasePropCategory(entity);

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
		String category_id = (String) dynaBean.get("category_id");
		String mod_id = (String) dynaBean.get("mod_id");
		// String tree_param = (String) dynaBean.get("tree_param");
		String pd_type_small = (String) dynaBean.get("pd_type_small");
		String pd_type = (String) dynaBean.get("pd_type");
		String pd_type_big = (String) dynaBean.get("pd_type_big");

		BasePropCategory entity = new BasePropCategory();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(pd_type_small)) {
			entity.setCls_id(Long.valueOf(pd_type_small));
		} else if (StringUtils.isNotBlank(pd_type)) {
			entity.setCls_id(Long.valueOf(pd_type));
		} else if (StringUtils.isNotBlank(pd_type_big)) {
			entity.setCls_id(Long.valueOf(pd_type_big));
		}

		if (StringUtils.isBlank(category_id)) {
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}

			super.getFacade().getBasePropCategoryService().createBasePropCategory(entity);
			saveMessage(request, "entity.inserted");

		} else {
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}

			super.getFacade().getBasePropCategoryService().modifyBasePropCategory(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		// pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");
		String mod_id = (String) dynaBean.get("mod_id");
		// String tree_param = (String) dynaBean.get("tree_param");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(category_id) && GenericValidator.isLong(category_id)) {
			BasePropCategory entity = new BasePropCategory();
			entity.setCategory_id(new Long(category_id));
			getFacade().getBasePropCategoryService().removeBasePropCategory(entity);

			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			BasePropCategory entity = new BasePropCategory();
			entity.getMap().put("pks", pks);
			super.getFacade().getBasePropCategoryService().removeBasePropCategory(entity);

			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		// pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "category_id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getHadCateList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("cls_id");

		BasePropCategory entity = new BasePropCategory();
		if (GenericValidator.isLong(cls_id)) {
			entity.getMap().put("par_cls_id", cls_id);
		}
		List<BasePropCategory> entityList = super.getFacade().getBasePropCategoryService()
				.getBasePropCategoryByNameList(entity);

		StringBuffer sb = new StringBuffer("{'result':'");
		if (null != entityList && entityList.size() > 0) {
			for (BasePropCategory temp : entityList) {
				if (null != temp.getMap().get("total_category_name")) {
					String str = temp.getMap().get("total_category_name").toString();
					sb.append(str).append(";");
				}
			}
		} else {
			sb.append("");
		}
		sb.append("'}");
		super.render(response, sb.toString(), "text/x-json;charset=UTF-8");

		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");

		if (!GenericValidator.isLong(category_id)) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		BasePropCategory entity = new BasePropCategory();
		entity.setCategory_id(Long.valueOf(category_id));
		entity = super.getFacade().getBasePropCategoryService().getBasePropCategory(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		if (null != entity.getCls_id()) {
			BasePdClass bpc = new BasePdClass();
			bpc.setCls_id(entity.getCls_id());
			bpc = super.getFacade().getBasePdClassService().getBasePdClass(bpc);
			request.setAttribute("cls_name", bpc.getCls_name());
		}
		if (null != entity.getCategory_id()) {
			BasePropCategory bpcg = new BasePropCategory();
			bpcg.setCategory_id(entity.getCategory_id());
			bpcg = super.getFacade().getBasePropCategoryService().getBasePropCategory(bpcg);
			request.setAttribute("category_name", bpcg.getCategory_name());
		}
		return mapping.findForward("view");
	}
}
