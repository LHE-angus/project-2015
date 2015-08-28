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

import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeArticleType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

public class KonkaPeArticleTypeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaPeArticleType entity = new KonkaPeArticleType();

		super.copyProperties(entity, form);
		entity.getMap().put("type_name_like",
				(String) dynaBean.get("type_name_like"));

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);

		// PeRoleUser peRoleUser = (PeRoleUser)
		// request.getSession().getAttribute(Constants.ROLE_INFO);

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request
				.getSession().getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_eq_10 = false;
		boolean role_id_ge_20_le_29 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			} else if (peRoleUser.getRole_id() >= 20L
					&& peRoleUser.getRole_id() <= 29L) {
				role_id_ge_20_le_29 = true;
			}

		}

		if (role_id_eq_10) {// 系统管理员
			// 部门列表
			KonkaDept konkaDept = new KonkaDept();
			// konkaDept.getMap().put("dept_name_like", "事业部");
			konkaDept.setDept_type(2);// 设置事业部的类型为2，表示部门性质为：事业部
			List<KonkaDept> konkaDeptList = super.getFacade()
					.getKonkaDeptService().getKonkaDeptList(konkaDept);
			request.setAttribute("konkaDeptList", konkaDeptList);
		} else {// 非系统管理员
			entity.setDept_id(ui.getDept_id());
		}

		// 判断当前用户是否是事业部
		String is_division = "false";
		if (role_id_ge_20_le_29) {
			is_division = "true";
		}
		request.setAttribute("is_division", is_division);

		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPeArticleType> entityList = getFacade()
				.getKonkaPeArticleTypeService()
				.getKonkaPeArticleTypePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		// // 部门列表
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.getMap().put("dept_name_like", "事业部");
		// konkaDept.setDept_type(2);//设置部门类别为2，表示部门性质为：事业部
		// List<KonkaDept> konkaDeptList =
		// super.getFacade().getKonkaDeptService()
		// .getKonkaDeptList(konkaDept);
		// request.setAttribute("konkaDeptList", konkaDeptList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaPeArticleType entity = new KonkaPeArticleType();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleType(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		//
		// // 部门列表
		// KonkaDept konkaDept = new KonkaDept();
		// konkaDept.getMap().put("dept_name_like", "事业部");
		// konkaDept.setDept_type(2);//设置部门类别为2，表示部门性质为：事业部
		// List<KonkaDept> konkaDeptList =
		// super.getFacade().getKonkaDeptService()
		// .getKonkaDeptList(konkaDept);
		// request.setAttribute("konkaDeptList", konkaDeptList);

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		// end

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String type_name = (String) dynaBean.get("type_name");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		KonkaPeArticleType konkaPeArticleType = new KonkaPeArticleType();
		konkaPeArticleType.setType_name(type_name);
		if (peProdUser.getUser_type() == 0) {
			konkaPeArticleType.setDept_id(peProdUser.getDept_id());
		} else {
			konkaPeArticleType.setDept_id(getKonkaDeptForFgs(
					peProdUser.getDept_id()).getDept_id());
		}
		Long count1 = super.getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleTypeCount(konkaPeArticleType);

		KonkaPeArticleType entity = new KonkaPeArticleType();
		super.copyProperties(entity, form);

		entity.setAdd_datetime(new Date());
		entity.setAdd_user_id(peProdUser.getId());// 保存登陆者的用户信息

		entity.setDept_id(peProdUser.getDept_id());
		logger.info("-----------------------" + peProdUser.getDepartment());

		if (StringUtils.isBlank(id)) {// insert
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			if (count1 > 0) {
				String msg = super.getMessage(request, "pe.article.type.error");
				super.renderJavaScript(response,
						"window.onload=function(){alert('" + msg
								+ "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaPeArticleTypeService()
					.createKonkaPeArticleType(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}

			if (count1 == 1) {

				KonkaPeArticleType kType = new KonkaPeArticleType();
				kType.setId(Long.valueOf(id));
				kType = super.getFacade().getKonkaPeArticleTypeService()
						.getKonkaPeArticleType(kType);

				if (!(kType.getType_name().toString()).equals(type_name)) {
					String msg = super.getMessage(request,
							"pe.article.type.error");
					super.renderJavaScript(response,
							"window.onload=function(){alert('" + msg
									+ "');history.back();}");
					return null;
				}
			} else if (count1 > 1) {
				String msg = super.getMessage(request, "pe.article.type.error");
				super.renderJavaScript(response,
						"window.onload=function(){alert('" + msg
								+ "');history.back();}");
				return null;
			}

			getFacade().getKonkaPeArticleTypeService()
					.modifyKonkaPeArticleType(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl,
					Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=").append(tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaPeArticleType entity = new KonkaPeArticleType();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPeArticleTypeService()
				.getKonkaPeArticleType(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeArticleType entity = new KonkaPeArticleType();
			entity.setId(new Long(id));
			super.getFacade().getKonkaPeArticleTypeService()
					.removeKonkaPeArticleType(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPeArticleType entity = new KonkaPeArticleType();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaPeArticleTypeService()
					.removeKonkaPeArticleType(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}
}