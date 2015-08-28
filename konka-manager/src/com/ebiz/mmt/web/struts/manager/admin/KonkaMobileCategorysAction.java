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
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xu,Wei
 * 
 */

public class KonkaMobileCategorysAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);

		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");
		dynaBean.set("c_index", c_index);
		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setIs_type(0);
		List<KonkaMobileCategory> category = super.getFacade()
				.getKonkaMobileCategoryService()
				.getKonkaMobileCategoryListForSelect(entity);
		request.setAttribute("category", category);

		dynaBean.set("order_sort", "0");

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String category_name = (String) dynaBean.get("category_name");
		String c_index = (String) dynaBean.get("c_index");
		KonkaMobileCategory entity = new KonkaMobileCategory();
		if (StringUtils.isNotBlank(category_name)) {
			entity.setC_name(category_name);
		}
		if (StringUtils.isNotBlank(c_index)) {
			entity.setC_type(new Integer(c_index));
		}
		entity.setIs_del(0);
		entity.setIs_type(0);
		Long recordCount = getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		request.setAttribute("entityList", getFacade()
				.getKonkaMobileCategoryService()
				.getKonkaMobileCategoryPaginatedList(entity));
		return mapping.findForward("list");
	}

	public ActionForward childlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String cate_name = (String) dynaBean.get("cate_name");
		String c_index = (String) dynaBean.get("c_index");
		KonkaMobileCategory entity = new KonkaMobileCategory();
		if (StringUtils.isNotBlank(cate_name)) {
			entity.setC_name(cate_name);
		}
		if (StringUtils.isNotBlank(c_index)) {
			entity.setC_type(new Integer(c_index));
		}
		dynaBean.set("c_index", c_index);
		entity.setIs_del(0);
		entity.setIs_type(1);
		Long recordCount = getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		request.setAttribute("entityList", getFacade()
				.getKonkaMobileCategoryService()
				.getKonkaMobileCategoryPaginatedList(entity));
		return new ActionForward("/admin/KonkaMobileCategorys/childlist.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		if (!GenericValidator.isLong(c_index)) {
			saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}

		// 获得网点的父类别
		if (StringUtils.isNotBlank(c_index)) {
			if (Long.parseLong(c_index) != 0l) {
				KonkaMobileCategory en = new KonkaMobileCategory();
				en.setC_index(Long.valueOf(c_index));
				en.setIs_del(0);
				en.setIs_type(0);
				KonkaMobileCategory cate = super.getFacade()
						.getKonkaMobileCategoryService()
						.getKonkaMobileCategory(en);
				if (cate != null) {
					KonkaMobileCategory category = new KonkaMobileCategory();
					category.setC_type(new Integer(c_index));
					category.setIs_type(1);
					category.setIs_del(0);
					List<KonkaMobileCategory> categoryList = super.getFacade()
							.getKonkaMobileCategoryService()
							.getKonkaMobileCategoryList(category);
					request.setAttribute("cate", cate);
					super.copyProperties(form, cate);
					request.setAttribute("categoryList", categoryList);
				} else {
					KonkaMobileCategory cate1 = new KonkaMobileCategory();
					cate1.setC_index(Long.valueOf(c_index));
					cate1.setIs_type(1);
					cate1.setIs_del(0);
					cate1 = super.getFacade().getKonkaMobileCategoryService()
							.getKonkaMobileCategory(cate1);

					KonkaMobileCategory cate2 = new KonkaMobileCategory();
					cate2.setC_index(new Long(cate1.getC_type()));
					cate2.setIs_type(0);
					cate2.setIs_del(0);
					cate2 = super.getFacade().getKonkaMobileCategoryService()
							.getKonkaMobileCategory(cate2);
					request.setAttribute("dadyname", cate2.getC_name());
					request.setAttribute("cate1", cate1);
					super.copyProperties(form, cate1);

				}
			}
		}

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
		String c_index = (String) dynaBean.get("c_index");
		String old_index = (String) dynaBean.get("old_index");
		KonkaMobileCategory entity = new KonkaMobileCategory();
		super.copyProperties(entity, form);

		if (null == old_index) {
			entity.setPar_index("0");
			entity.setIs_type(1);

			KonkaMobileCategory ca = new KonkaMobileCategory();
			//ca.setIs_type(0);
			ca.setC_index(Long.valueOf(c_index));
			List<KonkaMobileCategory> category = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryListForSelect(ca);

			for (int i = 0; i < category.size(); i++) {
				entity.setC_type(Integer.valueOf(category.get(i).getC_index()
						.toString()));
			}
			super.getFacade().getKonkaMobileCategoryService()
					.createKonkaMobileCategory(entity);
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {
			entity.getMap().put("c_index", old_index);
			entity.setC_type(Integer.valueOf(c_index));
			getFacade().getKonkaMobileCategoryService()
					.modifyKonkaMobileCategory(entity);
			saveMessage(request, "entity.updated");
		}

		updateDataPatch();
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + entity.getC_type());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		if (GenericValidator.isLong(c_index)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.setIs_type(0);
			entity.setIs_del(0);
			List<KonkaMobileCategory> category = super.getFacade()
					.getKonkaMobileCategoryService()
					.getKonkaMobileCategoryListForSelect(entity);
			entity.setC_index(Long.valueOf(c_index));
			entity.setIs_type(1);
			entity.setIs_del(0);
			entity = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileCategory(entity);

			request.setAttribute("category", category);
			if (null == category) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			// the line below is added for pagination
			entity
					.setQueryString(super.serialize(request, "c_index",
							"method"));
			// end
			dynaBean.set("c_name_1", entity.getC_type());
			super.copyProperties(form, entity);

			return new ActionForward("/admin/KonkaMobileCategorys/edit.jsp");
		} else {
			this.saveError(request, "errors.long", new String[] { c_index });
			return mapping.findForward("list");
		}
	}

	public ActionForward editForCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");

		KonkaMobileCategory entity = new KonkaMobileCategory();

		if (StringUtils.isNotBlank(c_index)) {
			entity.setC_index(Long.valueOf(c_index));
		}
		entity.setIs_type(0);
		entity.setIs_del(0);
		List<KonkaMobileCategory> category = super.getFacade()
				.getKonkaMobileCategoryService()
				.getKonkaMobileCategoryListForSelect(entity);
		entity = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategory(entity);
		if (null == category) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "c_index", "method"));
		// end
		super.copyProperties(form, entity);
		return new ActionForward(
				"/admin/KonkaMobileCategorys/editForCategory.jsp");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String c_index = (String) dynaBean.get("c_index");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String c_type = (String) dynaBean.get("c_type");

		if (!StringUtils.isBlank(c_index) && GenericValidator.isLong(c_index)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.setC_index(new Long(c_index));
			entity.setIs_del(1);
			super.getFacade().getKonkaMobileCategoryService()
					.removeKonkaMobileCategory(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			super.getFacade().getKonkaMobileCategoryService()
					.removeKonkaMobileCategory(entity);
			saveMessage(request, "entity.deleted");
		}
		updateDataPatch();
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + c_type);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward addType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		setNaviStringToRequestScope(form, request);

		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("order_sort", "0");

		return new ActionForward("/admin/KonkaMobileCategorys/addType.jsp");
	}

	public ActionForward saveAddType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (isCancelled(request)) {
		// return list(mapping, form, request, response);
		// }
		// if (!isTokenValid(request)) {
		// saveError(request, "errors.token");
		// return list(mapping, form, request, response);
		// }
		// resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String c_index = (String) dynaBean.get("c_index");
		log.info("c_index=" + c_index);
		KonkaMobileCategory entity = new KonkaMobileCategory();

		if (StringUtils.isNotBlank(c_index)) {
			KonkaMobileCategory en = new KonkaMobileCategory();
			en.setC_index(Long.valueOf(c_index));
			en = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileCategory(en);
			if (en != null) {
				super
						.renderJavaScript(response,
								"window.onload=function(){alert('父类别编号重复，请重新填写。');history.back();}");
				return null;
			} else {
				super.copyProperties(entity, form);
				entity.setC_index(Long.valueOf(c_index));
				entity.setC_type(0);
				entity.setPar_index("0");
				entity.setIs_type(0);
				super.getFacade().getKonkaMobileCategoryService()
						.createKonkaMobileCategoryForAddType(entity);
				saveMessage(request, "entity.inserted");
			}
		} else {
			super.copyProperties(entity, form);
			entity.setC_type(0);
			entity.setPar_index("0");
			entity.setIs_type(0);
			super.getFacade().getKonkaMobileCategoryService()
					.createKonkaMobileCategory(entity);
			saveMessage(request, "entity.inserted");
		}
		updateDataPatch();

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + entity.getC_type());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward saveEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String c_name = (String) dynaBean.get("c_name");
		String c_index = (String) dynaBean.get("c_index");

		String old_index = (String) dynaBean.get("old_index");
		KonkaMobileCategory entity = new KonkaMobileCategory();
		super.copyProperties(entity, form);
		KonkaMobileCategory en = new KonkaMobileCategory();
		en.setC_index(Long.valueOf(old_index));
		en = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategory(en);
		if (null != en && !(c_index.equals(old_index))) {
			super
					.renderJavaScript(
							response,
							"window.onload=function(){alert('类别枚举值重复，请重新填写。');history.back();return false;}");
			return null;
		} else {
			entity.setC_index(new Long(c_index));
			entity.setC_type(0);
			entity.setPar_index("0");
			entity.setC_name(c_name);
			entity.setIs_type(0);
			entity.getMap().put("c_index", old_index);

			getFacade().getKonkaMobileCategoryService()
					.modifyKonkaMobileCategory(entity);

			KonkaMobileCategory cate = new KonkaMobileCategory();
			cate.setC_type(Integer.valueOf(old_index));
			cate.setIs_type(1);
			List<KonkaMobileCategory> cate1 = super.getFacade()
					.getKonkaMobileCategoryService()
					.getKonkaMobileCategoryList(cate);
			for (int i = 0; i < cate1.size(); i++) {
				cate.setIs_type(1);
				cate.setC_type(Integer.valueOf(c_index));
				cate.getMap().put("c_index", cate1.get(i).getC_index());
				super.copyProperties(cate, form);
				getFacade().getKonkaMobileCategoryService()
						.modifyKonkaMobileCategory(cate);
			}
			saveMessage(request, "entity.updated");
		}
		updateDataPatch();
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&c_type=" + entity.getC_type());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward deleteForCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String c_index = (String) dynaBean.get("c_index");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		if (StringUtils.isNotBlank(c_index)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.setIs_type(0);
			entity.setIs_del(0);
			entity.setC_index(Long.valueOf(c_index));
			super.getFacade().getKonkaMobileCategoryService()
					.removeKonkaMobileCategory(entity);

			KonkaMobileCategory entity1 = new KonkaMobileCategory();
			entity1.setC_type(new Integer(c_index));
			List<KonkaMobileCategory> en = super.getFacade()
					.getKonkaMobileCategoryService()
					.getKonkaMobileCategoryList(entity1);
			for (KonkaMobileCategory t : en) {
				KonkaMobileCategory entity11 = new KonkaMobileCategory();
				entity11.setC_index(t.getC_index());
				super.getFacade().getKonkaMobileCategoryService()
						.removeKonkaMobileCategory(entity11);
			}

			saveMessage(request, "entity.deleted");
		}

		else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileCategory entity = new KonkaMobileCategory();
			entity.getMap().put("pks", pks);
			entity.setIs_del(0);
			entity.setIs_type(0);
			super.getFacade().getKonkaMobileCategoryService()
					.removeKonkaMobileCategory(entity);

			KonkaMobileCategory entity1 = new KonkaMobileCategory();
			for (int i = 0; i < pks.length; i++) {
				entity1.setC_type(new Integer(pks[i]));
				List<KonkaMobileCategory> en = super.getFacade()
						.getKonkaMobileCategoryService()
						.getKonkaMobileCategoryList(entity1);
				for (KonkaMobileCategory t : en) {
					KonkaMobileCategory entity11 = new KonkaMobileCategory();
					entity11.setC_index(t.getC_index());
					super.getFacade().getKonkaMobileCategoryService()
							.removeKonkaMobileCategory(entity11);
				}

			}

			saveMessage(request, "entity.deleted");
		}
		updateDataPatch();
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
