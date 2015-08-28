package com.ebiz.mmt.web.struts.manager.chengduoa;

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

import com.ebiz.mmt.domain.KonkaItemProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jin, QingHua
 */
public class ItemPropertyManagerAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String p_id = (String) dynaBean.get("p_id");

		KonkaItemProperty entity = new KonkaItemProperty();
		entity.setP_id(new Long(p_id));
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(p_id) || GenericValidator.isLong(p_id)) {
			entity = super.getFacade().getKonkaItemPropertyService().getKonkaItemProperty(entity);
			entity.setQueryString(super.serialize(request, "p_id", "method"));
			super.copyProperties(form, entity);
		}

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		//PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String p_name = (String) dynaBean.get("p_name");
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaItemProperty entity = new KonkaItemProperty();
		super.copyProperties(entity, form);
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(p_name)) {
			entity.setP_name(null);
			entity.getMap().put("p_name", p_name);
		}

		Long recordCount = super.getFacade().getKonkaItemPropertyService().getKonkaItemPropertyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaItemProperty> entityList = super.getFacade().getKonkaItemPropertyService()
				.getKonkaItemPropertyPaginatedList(entity);
		request.setAttribute("entityList", entityList);

//		if ("1000".equals(ui.getId().toString()) || isKonkaBaseManager(ui)) {
//			request.setAttribute("isKonkaBaseManager", "true");
//		}

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String p_id = (String) dynaBean.get("p_id");
		if (GenericValidator.isLong(p_id)) {
			KonkaItemProperty entity = new KonkaItemProperty();
			entity.setP_id(Long.valueOf(p_id));
			entity = super.getFacade().getKonkaItemPropertyService().getKonkaItemProperty(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { p_id });
			return mapping.findForward("list");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String p_id = (String) dynaBean.get("p_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaItemProperty entity = new KonkaItemProperty();
		super.copyProperties(entity, form);
		entity.setAdd_time(new Date());
		if (StringUtils.isNotBlank(p_id) && GenericValidator.isLong(p_id)) {
			super.getFacade().getKonkaItemPropertyService().modifyKonkaItemProperty(entity);
			saveMessage(request, "entity.updated");
		} else {
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			entity.setIs_del(0);
			super.getFacade().getKonkaItemPropertyService().createKonkaItemProperty(entity);
			saveMessage(request, "entity.inerted");
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String p_id = (String) dynaBean.get("p_id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(p_id) && GenericValidator.isLong(p_id)) {
			KonkaItemProperty entity = new KonkaItemProperty();
			entity.setP_id(new Long(p_id));
			entity.setIs_del(1);
			getFacade().getKonkaItemPropertyService().modifyKonkaItemProperty(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaItemProperty entity = new KonkaItemProperty();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1);
			getFacade().getKonkaItemPropertyService().modifyKonkaItemProperty(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "p_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward validateProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String p_name = (String) dynaBean.get("p_name");
		String p_type = (String) dynaBean.get("p_type");

		KonkaItemProperty entity = new KonkaItemProperty();
		entity.setIs_del(0);
		entity.setP_name(p_name);
		entity.setP_type(Integer.valueOf(p_type));
		Long count = super.getFacade().getKonkaItemPropertyService().getKonkaItemPropertyCount(entity);

		StringBuffer oper = new StringBuffer("{");
			oper.append("\"result\":").append(count);
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

}