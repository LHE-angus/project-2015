package com.ebiz.mmt.web.struts.manager.admin;

import java.net.URLDecoder;
import java.util.Date;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class PeShopCategoryAction extends BaseAction {

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

		String category_name_like = (String) dynaBean.get("category_name_like");
		Pager pager = (Pager) dynaBean.get("pager");
		String pid = (String) dynaBean.get("category_pid");

		PeShopCategory entity = new PeShopCategory();
		entity.setIs_del(0l);

		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

//		PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);
		
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ne_10 = true;
		boolean role_id_ge_20_le_29 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10) {
				role_id_ne_10 = false;
			}
			if (peRoleUser.getRole_id() >= 20 && peRoleUser.getRole_id() <= 29) {
				role_id_ge_20_le_29 = true;
			}
		}
		
		if (role_id_ne_10) {// 非管理员只能查看本事业部类别
			KonkaDept konkaDept = super.getSuperiorForDeptType(ui.getDept_id(), 2);
			if (null != konkaDept) {
				entity.setDept_id(konkaDept.getDept_id());
			}
			// 事业部管理员，可以看到锁定的两个类别(废弃)
			// if (peRoleUser.getRole_id() == 20 && "0".equals(pid)) {
			// entity.getMap().put("role_id_20", "20");
			// }
		} else {// 管理员有事业部查询选项
			//entity.setDept_id(null);
			if ("0".equals(pid)) {
				KonkaDept konkaDept = new KonkaDept();
				// konkaDept.getMap().put("dept_name_like", "事业部");
				konkaDept.setDept_type(2);// 设置部门类别为2，表示部门性质为：事业部
				List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
				request.setAttribute("konkaDeptList", konkaDeptList);
			}
		}

		if ("0".equals(pid)) {
			entity.getMap().put("role_id_20", "20");
		}
		
		Long recordCount = super.getFacade().getPeShopCategoryService().getPeShopCategoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeShopCategory> entityList = getFacade().getPeShopCategoryService().getPeShopCategoryPaginatedList(entity);

		for (PeShopCategory en : entityList) {
			PeShopCategory e = new PeShopCategory();
			e.setCategory_pid(en.getCategory_id());
			e.setDept_id(entity.getDept_id());
			// if (peRoleUser.getRole_id() != 10) {// 非管理员只能查看本部门类别
			// e.setAdd_user_id(ui.getId());
			// }
			e.setIs_del(0l);
			List<PeShopCategory> sonEntityList = super.getFacade().getPeShopCategoryService().getPeShopCategoryList(e);
			if (null != sonEntityList) {
				en.setPeShopCategoryList(sonEntityList);
			}
		}
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(pid) && !"0".equals(pid)) {
			PeShopCategory psc = new PeShopCategory();
			psc.setIs_del(0l);
			psc.setCategory_id(new Long(pid));
			psc = super.getFacade().getPeShopCategoryService().getPeShopCategory(psc);
			if (null != psc) {
				request.setAttribute("category_pName", psc.getCategory_name());
			}
		}

		// 判断当前用户是否是事业部
		String is_division = "false";
		if (role_id_ge_20_le_29) {
			is_division = "true";
		}
		request.setAttribute("is_division", is_division);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");

		if (!GenericValidator.isLong(category_id)) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		PeShopCategory entity = new PeShopCategory();
		entity.setCategory_id(Long.valueOf(category_id));
		entity = super.getFacade().getPeShopCategoryService().getPeShopCategory(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		// 部门列表
		KonkaDept konkaDept = new KonkaDept();
		// konkaDept.getMap().put("dept_name_like", "事业部");
		konkaDept.setDept_type(2);// 设置部门类别为2，表示部门性质为：事业部
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("konkaDeptList", konkaDeptList);

		entity.setQueryString(super.serialize(request, "category_id", "mod_id"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");
		String category_pid = (String) dynaBean.get("category_pid");

		if (!GenericValidator.isLong(category_id)) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}

		// 获得网点的父类别
		if (StringUtils.isNotBlank(category_pid)) {
			if (Long.parseLong(category_pid) != 0l) {
				PeShopCategory en = new PeShopCategory();
				en.setCategory_id(Long.valueOf(category_pid));
				en.setIs_del(0l);
				List<PeShopCategory> entitList = super.getFacade().getPeShopCategoryService().getPeShopCategoryList(en);
				String category_pName = entitList.get(0).getCategory_name();
				request.setAttribute("category_pName", category_pName);
			}
		}

		PeShopCategory entity = new PeShopCategory();
		entity.setCategory_id(Long.valueOf(category_id));
		entity.setIs_del(0l);
		entity = super.getFacade().getPeShopCategoryService().getPeShopCategory(entity);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
//		PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);
		
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ne_10 = true;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10) {
				role_id_ne_10 = false;
				break;
			}
		}
		
		// 获得网点的子类别
		if (StringUtils.isNotBlank(category_id)) {
			PeShopCategory ent = new PeShopCategory();
			ent.setCategory_pid(Long.valueOf(category_id));
			ent.setIs_del(0l);
			if(role_id_ne_10){//非超级管理员只能看到所属部门所添加的子类别
				ent.setDept_id(ui.getDept_id());
			}
			List<PeShopCategory> peShopCategoryList = super.getFacade().getPeShopCategoryService()
					.getPeShopCategoryList(ent);
			if (null != peShopCategoryList) {
				entity.setPeShopCategoryList(peShopCategoryList);
			}
		}

		if (null == entity) {
			saveError(request, "errors.long", new String[] { category_id });
			return mapping.findForward("list");
		}
		request.setAttribute("entity", entity);
		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String category_id = (String) dynaBean.get("category_id");
		String category_pid = (String) dynaBean.get("category_pid");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeShopCategory entity = new PeShopCategory();
		super.copyProperties(entity, form);

		entity.setAdd_date(new Date());
		entity.setAdd_user_id(peProdUser.getId());// 保存登陆者的用户信息

		entity.setDept_id(peProdUser.getDept_id());
		logger.info("-----------------------" + peProdUser.getDepartment());

		if (StringUtils.isBlank(category_id)) {
			super.getFacade().getPeShopCategoryService().createPeShopCategory(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setCategory_id(Long.valueOf(category_id));
			super.getFacade().getPeShopCategoryService().modifyPeShopCategory(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&").append("tree_param=").append(tree_param);
		if (!"0".equals(category_pid)) {
			pathBuffer.append("&").append("dept_id=").append(dept_id);
		}
		pathBuffer.append("&").append("category_pid=").append(category_pid);
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
		String category_pid = (String) dynaBean.get("category_pid");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String dept_id = (String) dynaBean.get("dept_id");

		if (!StringUtils.isBlank(category_id) && GenericValidator.isLong(category_id)) {
			PeShopCategory entity = new PeShopCategory();
			entity.setCategory_id(Long.valueOf(category_id));
			entity.getMap().put("category_pid", "category_pid");
			entity.getMap().put("dept_id", "dept_id");
			entity.setIs_del((long) 1);
			getFacade().getPeShopCategoryService().modifyPeShopCategory(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			PeShopCategory entity = new PeShopCategory();
			entity.getMap().put("pks", pks);
			entity.getMap().put("category_pid", "category_pid");
			entity.getMap().put("dept_id", "dept_id");
			entity.setIs_del((long) 1);
			super.getFacade().getPeShopCategoryService().modifyPeShopCategory(entity);

			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&").append("category_pid=").append(category_pid);
		if (!"0".equals(category_pid)) {
			pathBuffer.append("&").append("dept_id=").append(dept_id);
		}
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "category_id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward listForLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String selects = (String) dynaBean.get("selects");

		PeShopCategory peShopCategory = new PeShopCategory();
		peShopCategory.setIs_del(0l);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaDept konkaDept = super.getSuperiorForDeptType(peProdUser.getDept_id(), 2);

		if (peProdUser.getDept_id() != 0) {// 非管理员
			peShopCategory.setDept_id(konkaDept.getDept_id());
		}

		request.setAttribute("peShopCategoryList", getFacade().getPeShopCategoryService()
				.getPeShopCategoryListWithLevel(peShopCategory));

		if (StringUtils.isNotBlank(selects)) {
			PeShopCategory _peShopCategory = new PeShopCategory();
			_peShopCategory.setIs_del(0l);
			_peShopCategory.getMap().put("category_selects", selects);
			request.setAttribute("selectCategoryList", getFacade().getPeShopCategoryService()
					.getPeShopCategoryListWithLevel(_peShopCategory));
		}

		return new ActionForward("/../manager/admin/PeShopCategory/listForLevel.jsp");
	}
}