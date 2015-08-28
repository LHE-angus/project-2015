package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Ren,Peng
 * @version 2011-10-18
 */
public class KonkaJxcStoreInfoAction extends BaseJxcAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String store_name = (String) dynaBean.get("store_name");
		String store_addr = (String) dynaBean.get("store_addr");
		String link_man = (String) dynaBean.get("link_man");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(store_name)) {
			entity.setStore_name(store_name);
		}
		if (StringUtils.isNotBlank(store_addr)) {
			entity.setStore_addr(store_addr);
		}
		if (StringUtils.isNotBlank(link_man)) {
			entity.setLink_man(link_man);
		}
		entity.setAdd_dept_id(peProdUser.getDept_id());
		//entity.getMap().put("all_son_dept_id", peProdUser.getDept_id());

		Long recordCount = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcStoreInfo> entityList = super.getFacade().getKonkaJxcStoreInfoService()
				.getKonkaJxcStoreInfoPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		dynaBean.set("add_user_id", peProdUser.getId());
		dynaBean.set("add_dept_id", peProdUser.getDept_id());

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		// String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}
		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(entity);
		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			return this.list(mapping, form, request, response);
		}

		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(entity);

		if (null == entity) {
			return this.list(mapping, form, request, response);
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		super.copyProperties(entity, dynaBean);
		if (!GenericValidator.isLong(id)) {// 创建用户
			super.getFacade().getKonkaJxcStoreInfoService().createKonkaJxcStoreInfo(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user_id(peProdUser.getId());
			super.getFacade().getKonkaJxcStoreInfoService().modifyKonkaJxcStoreInfo(entity);
			super.saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		// pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			if (store_exist_hw(Long.valueOf(id))) {
				String msg = super.getMessage(request, "konka_jxc_store.state.error");
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			} else {
				KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
				entity.setId(Long.valueOf(id));
				entity.setIs_del(1);
				entity.setUpdate_date(new Date());
				entity.setUpdate_user_id(peProdUser.getId());
				entity.setDel_date(new Date());
				entity.setDel_user_id(peProdUser.getId());
				super.getFacade().getKonkaJxcStoreInfoService().modifyKonkaJxcStoreInfo(entity);
				saveMessage(request, "entity.deleted");
			}
		} else {
			for (String pk : pks) {
				if (store_exist_hw(Long.valueOf(pk))) {
					String msg = super.getMessage(request, "konka_jxc_store.state.error");
					super.renderJavaScript(response, "alert('" + msg + "');history.back();");
					return null;
				} else {
					KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
					entity.setId(Long.valueOf(pk));
					entity.setIs_del(1);
					entity.setUpdate_date(new Date());
					entity.setUpdate_user_id(peProdUser.getId());
					entity.setDel_date(new Date());
					entity.setDel_user_id(peProdUser.getId());
					super.getFacade().getKonkaJxcStoreInfoService().modifyKonkaJxcStoreInfo(entity);
					saveMessage(request, "entity.deleted");
				}
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;

		String store_name = (String) dynaBean.get("store_name");
		KonkaJxcStoreInfo entity = new KonkaJxcStoreInfo();
		entity.setIs_del(0);
		entity.setStore_name(store_name);
		Long count = 0L;
		StringBuffer oper = new StringBuffer("{\"result\":");
		if (StringUtils.isNotBlank(store_name)) {
			count = super.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoCount(entity);
		}
		if (count > 0) {
			oper.append("\""+false+"\"");
		} else {
			oper.append("\""+true+"\"");
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	// 判断仓库中是否有货物
	private boolean store_exist_hw(Long id) {
		KonkaJxcStoreState state = new KonkaJxcStoreState();
		state.setIs_del(0);
		state.setStore_id(Long.valueOf(id));
		Long count = super.getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreStateCount(state);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

}
