package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-11-02
 */
public class KonkaR3DeptStoreAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String fac_sn_like = (String) dynaBean.get("fac_sn_like");
		String store_sn_like = (String) dynaBean.get("store_sn_like");
		String is_del = (String) dynaBean.get("is_del");

		Pager pager = (Pager) dynaBean.get("pager");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);

		// 取用户角色并判断是不是分公司级别的角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		Boolean role_id_gt_30_btw_200_300 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
				role_id_gt_30_btw_200_300 = true;
		}

		KonkaR3DeptStore entity = new KonkaR3DeptStore();
		if (StringUtils.isNotBlank(fac_sn_like))
			entity.getMap().put("fac_sn_like", fac_sn_like);

		if (StringUtils.isNotBlank(store_sn_like))
			entity.getMap().put("store_sn_like", store_sn_like);
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
			dynaBean.set("is_del", 0);
		}

		if (role_id_gt_30_btw_200_300) {
			if (StringUtils.isNotBlank(dept_name_like))
				entity.getMap().put("dept_name_like", dept_name_like);

			request.setAttribute("is_admin", "1");
		} else {
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (null != kDept)
				entity.setDept_name(kDept.getDept_name());
		}

		entity.getMap().put("order_by_dept_name_desc", true);

		Long recordCount = getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3DeptStore> entityList = getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStorePaginatedList(
				entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String is_admin = (String) dynaBean.get("is_admin");
		if (StringUtils.isNotBlank(is_admin)) {
			request.setAttribute("is_admin", is_admin);
		} else {
			PeProdUser ui = super.getSessionUserInfo(request);
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (null != kDept)
				dynaBean.set("dept_name", kDept.getDept_name());
		}
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		if (!super.isTokenValid(request, true)) {
//			super.saveMessage(request, "errors.token");
//			return this.list(mapping, form, request, response);
//
//		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		KonkaR3DeptStore entity = new KonkaR3DeptStore();
		super.copyProperties(entity, form);

		// 获取dept_sn
		String dept_id = (String) dynaBean.get("dept_sn");
		if (!"".equals(dept_id) && null != dept_id) {
			KonkaDept t = new KonkaDept();
			t.setDept_id(Long.valueOf(dept_id));
			KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(t);
			entity.setDept_sn(kd.getDept_sn());
			entity.setDept_name(kd.getDept_name());
		}

		KonkaR3DeptStore kStore = new KonkaR3DeptStore();
		kStore.getMap().put("dept_name_upper", entity.getDept_name());
		kStore.getMap().put("fac_sn_upper", entity.getFac_sn());
		kStore.getMap().put("store_sn_upper", entity.getStore_sn());

		if (GenericValidator.isLong(id)) {
			kStore.getMap().put("id_value", id);
			Long count = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreCount(kStore);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.dept.fac.store");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaR3DeptStoreService().modifyKonkaR3DeptStore(entity);
			saveMessage(request, "entity.updated");
		} else {

			Long count = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreCount(kStore);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.dept.fac.store");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaR3DeptStoreService().createKonkaR3DeptStore(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		//System.out.print("url_\n" + pathBuffer.toString());
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String is_admin = (String) dynaBean.get("is_admin");
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(is_admin))
			request.setAttribute("is_admin", is_admin);

		KonkaR3DeptStore entity = new KonkaR3DeptStore();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStore(entity);

		// 当有销售部门编号时，置空分公司名称,并转化成部门ID
		if (!"".equals(entity.getDept_sn()) && null != entity.getDept_sn()) {
			entity.setDept_name(null);
			KonkaDept t = new KonkaDept();
			t.setDept_sn(entity.getDept_sn());
			KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(t);
			if (null != kd) {
				entity.setDept_sn(kd.getDept_id().toString());
			}
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward resStore(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaR3DeptStore entity = new KonkaR3DeptStore();
		entity.setId(Long.valueOf(id));
		entity.setIs_del(0);
		super.getFacade().getKonkaR3DeptStoreService().modifyKonkaR3DeptStore(entity);

		saveMessage(request, "konka.restart.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward stopStore(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaR3DeptStore entity = new KonkaR3DeptStore();
		entity.setId(Long.valueOf(id));
		entity.setIs_del(1);
		super.getFacade().getKonkaR3DeptStoreService().modifyKonkaR3DeptStore(entity);

		saveMessage(request, "konka.close.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
