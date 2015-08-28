package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author hu hao
 * @version 2012-3-3
 */
public class KonkaXxStdStoreAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
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

		Pager pager = (Pager) dynaBean.get("pager");
		String factory_id_like = (String) dynaBean.get("factory_id_like");
		String store_id_like = (String) dynaBean.get("store_id_like");
		String dept_name = (String) dynaBean.get("dept_name");

		KonkaXxStdStore entity = new KonkaXxStdStore();

		if (StringUtils.isNotBlank(factory_id_like)) {
			entity.getMap().put("factory_id_like", factory_id_like);
		}
		if (StringUtils.isNotBlank(store_id_like)) {
			entity.getMap().put("store_id_like", store_id_like);
		}
		if (StringUtils.isNotBlank(dept_name)) {
			entity.setDept_name(dept_name);
		}

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 100)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (role_id_btw_300_400) {
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (null != kDept) {
				request.setAttribute("dept_name", kDept.getDept_name());
				entity.setDept_name(kDept.getDept_name());
			}
		}

		Long recordCount = super.getFacade().getKonkaXxStdStoreService().getKonkaXxStdStoreCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		request.setAttribute("konkaDeptList", super.getKonkaDepts());

		List<KonkaXxStdStore> entityList = super.getFacade().getKonkaXxStdStoreService()
				.getKonkaXxStdStorePaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 100)) {
					role_id_btw_300_400 = true;
				}
			}
		}
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			// request.setAttribute("dept_name",
			// getKonkaDeptForFgs(user_id.getDept_id()).getDept_name());
			KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
			if (null != kDept)
				dynaBean.set("dept_name", kDept.getDept_desc());
		}

		request.setAttribute("konkaDeptList", super.getKonkaDepts());
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String factory_id = (String) dynaBean.get("factory_id");
		String store_id = (String) dynaBean.get("store_id");
		String store_desc = (String) dynaBean.get("store_desc");
		String dept_name = (String) dynaBean.get("dept_name");
		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaXxStdStore entity = new KonkaXxStdStore();
		String dept_name1 = null;
		if (StringUtils.isNotBlank(dept_name)) {
			dept_name1 = dept_name;
		} else {
			KonkaDept kDept = getKonkaDeptForFgs(userInfo.getDept_id());
			if (null != kDept)
				dept_name1 = kDept.getDept_name();
		}

		entity.setDept_name(dept_name1);
		if (StringUtils.isNotBlank(factory_id)) {
			entity.setFactory_id(factory_id);
		}
		if (StringUtils.isNotBlank(store_id)) {
			entity.setStore_id(store_id);
		}
		if (StringUtils.isNotBlank(store_desc)) {
			entity.setStore_desc(store_desc);
		}

		KonkaXxStdStore konkaXxStdStore = new KonkaXxStdStore();
		konkaXxStdStore.setFactory_id(factory_id);
		konkaXxStdStore.setDept_name(dept_name1);
		konkaXxStdStore.setStore_id(store_id);
		List<KonkaXxStdStore> konkaXxStdStoreList = super.getFacade().getKonkaXxStdStoreService()
				.getKonkaXxStdStoreList(konkaXxStdStore);
		if (null != konkaXxStdStoreList && konkaXxStdStoreList.size() > 0) {
			String msg = super.getMessage(request, "konka.xx.std.store.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		super.getFacade().getKonkaXxStdStoreService().createKonkaXxStdStore(entity);
		saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String factory_id = (String) dynaBean.get("factory_id");
		String store_id = (String) dynaBean.get("store_id");

		KonkaXxStdStore entity = new KonkaXxStdStore();
		entity.setFactory_id(factory_id);
		entity.setStore_id(store_id);
		entity = super.getFacade().getKonkaXxStdStoreService().getKonkaXxStdStore(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
}
