package com.ebiz.mmt.web.struts.manager.oa;

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

import com.ebiz.mmt.domain.KonkaItem;
import com.ebiz.mmt.domain.KonkaItemProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author guo,jun
 * @version Build 2011-10-20
 */
public class KonkaItemManagerAction extends BaseMmtoaAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

//		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String receive_user_name_like = (String) dynaBean.get("receive_user_name_like");
		String item_content_like = (String) dynaBean.get("item_content_like");
		String st_plan_finish_date = (String) dynaBean.get("st_plan_finish_date");
		String en_plan_finish_date = (String) dynaBean.get("en_plan_finish_date");

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaItem entity = new KonkaItem();
		super.copyProperties(entity, form);
		entity.getMap().put("receive_user_name_like", receive_user_name_like);
		entity.getMap().put("item_content_like", item_content_like);
		entity.getMap().put("st_plan_finish_date", st_plan_finish_date);
		entity.getMap().put("en_plan_finish_date", en_plan_finish_date);
		entity.setIs_del(0);

		Long recordCount = super.getFacade().getKonkaItemService().getKonkaItemCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaItem> entityList = super.getFacade().getKonkaItemService().getKonkaItemPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		KonkaItemProperty kip = new KonkaItemProperty();
		kip.setIs_del(0);
		List<KonkaItemProperty> propertyList = getFacade().getKonkaItemPropertyService().getKonkaItemPropertyList(kip);
		request.setAttribute("propertyList", propertyList);

//		if ("1000".equals(ui.getId().toString()) || isKonkaBaseManager(ui)) {
//			request.setAttribute("isKonkaBaseManager", "true");
//		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		KonkaItemProperty kip = new KonkaItemProperty();
		kip.setIs_del(0);
		List<KonkaItemProperty> propertyList = getFacade().getKonkaItemPropertyService().getKonkaItemPropertyList(kip);
		request.setAttribute("propertyList", propertyList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaItem entity = new KonkaItem();
		entity.setId(new Long(id));
		entity = super.getFacade().getKonkaItemService().getKonkaItem(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id", "method"));

		super.copyProperties(form, entity);

		KonkaItemProperty kip = new KonkaItemProperty();
		kip.setIs_del(0);
		List<KonkaItemProperty> propertyList = getFacade().getKonkaItemPropertyService().getKonkaItemPropertyList(kip);
		request.setAttribute("propertyList", propertyList);

		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaItem entity = new KonkaItem();
			entity.setId(new Long(id));
			getFacade().getKonkaItemService().removeKonkaItem(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaItem entity = new KonkaItem();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaItemService().removeKonkaItem(entity);
			saveMessage(request, "entity.deleted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaItem entity = new KonkaItem();
		super.copyProperties(entity, dynaBean);

		if (null == entity.getId()) {// insert
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_time(new Date());
			entity.setAdd_user_name(ui.getUser_name());
			entity.setIs_del(0);

			getFacade().getKonkaItemService().createKonkaItem(entity);
			saveMessage(request, "entity.inerted");
		} else {// update
			entity.getMap().put("updateUserAbroadRecord", "true");
			getFacade().getKonkaItemService().modifyKonkaItem(entity);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateUserName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String user_name = (String) dynaBean.get("user_name");

		UserInfo entity = new UserInfo();
		entity.setUser_name(user_name);
		entity = getFacade().getUserInfoService().getUserInfo(entity);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"user_id\":\"");
		if (null != entity) {
			sb.append(entity.getId());
		}
		sb.append("\"}");

		super.renderJson(response, sb.toString());

		return null;
	}

}