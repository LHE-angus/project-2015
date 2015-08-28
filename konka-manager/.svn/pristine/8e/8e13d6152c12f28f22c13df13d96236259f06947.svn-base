package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaYjglYjed;
import com.ebiz.mmt.domain.KonkaYjglYjedAdd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaYjglYjedAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");
		String yjed_year = (String) dynaBean.get("yjed_year");

		KonkaYjglYjed entity = new KonkaYjglYjed();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
				break;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_gt_30_lt_60 = true;
				break;
			}
		}
		if (role_id_eq_30) {

		} else if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(yjed_year)) {
			entity.setYjed_year(Integer.valueOf(yjed_year));
		}

		Long recordCount = super.getFacade().getKonkaYjglYjedService().getKonkaYjglYjedAndDeptNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaYjglYjed> entityList = super.getFacade().getKonkaYjglYjedService()
		        .getKonkaYjglYjedAndDeptNamePaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		saveToken(request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaYjglYjed entity = new KonkaYjglYjed();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaYjglYjedService().getKonkaYjglYjed(entity);
		if (null == entity) {
			return null;
		}
		super.copyProperties(form, entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(entity.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}
		dynaBean.set("dept_name", konkaDept.getDept_name());

		KonkaYjglYjedAdd ka = new KonkaYjglYjedAdd();
		ka.setDept_id(entity.getDept_id());
		ka.setYjed_year(entity.getYjed_year());
		List<KonkaYjglYjedAdd> kaList = super.getFacade().getKonkaYjglYjedAddService().getKonkaYjglYjedAddList(ka);
		request.setAttribute("kaList", kaList);

		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		KonkaYjglYjed entity = new KonkaYjglYjed();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaYjglYjedService().getKonkaYjglYjed(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		// super.copyProperties(form, entity);

		dynaBean.set("dept_id", entity.getDept_id().toString());
		dynaBean.set("yjed_year", entity.getYjed_year().toString());
		// dynaBean.set("is_add", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		if (StringUtils.isBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaYjglYjedAdd entity = new KonkaYjglYjedAdd();
		super.copyProperties(entity, form);
		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			entity.setAdd_user_id(user.getId());
			entity.setReal_name(user.getUser_name());
			super.getFacade().getKonkaYjglYjedAddService().createKonkaYjglYjedAdd(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			KonkaYjglYjed ky = new KonkaYjglYjed();
			ky.setId(Long.valueOf(id));
			ky = super.getFacade().getKonkaYjglYjedService().getKonkaYjglYjed(ky);
			if (ky != null) {
				entity.setDept_id(ky.getDept_id());
				entity.setYjed_year(ky.getYjed_year());
				entity.setAdd_date(new Date());
				entity.setIs_del(0);
				entity.setAdd_user_id(user.getId());
				entity.setReal_name(user.getUser_name());
				super.getFacade().getKonkaYjglYjedAddService().createKonkaYjglYjedAdd(entity);
				super.saveMessage(request, "entity.inserted");
			} else {
				return null;
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