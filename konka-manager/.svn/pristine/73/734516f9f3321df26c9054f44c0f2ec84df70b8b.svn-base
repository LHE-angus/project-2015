package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBaseType;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-06-11
 */
public class JBaseTypeAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		super.setNaviStringToRequestScope(form, request);

		// PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		PeProdUser user_info = super.getSessionCustomerUserInfo(request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String type_name_like = (String) dynaBean.get("type_name_like");
		String par_id = (String) dynaBean.get("par_id");

		JBaseType entity = new JBaseType();

		if (StringUtils.isNotBlank(type_name_like)) {
			entity.getMap().put("type_name_like", type_name_like);
		}

		if (StringUtils.isNotBlank(par_id)) {
			entity.setPar_id(Long.valueOf(par_id));
			dynaBean.set("par_id", par_id);
		}

		entity.setIs_del(0);
		entity.setC_id(user_info.getCust_id());

		entity.getMap().put("order_by_order_value", true);
		Long recordCount = getFacade().getJBaseTypeService().getJBaseTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<JBaseType> entityList = getFacade().getJBaseTypeService().getJBaseTypePaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String par_id = (String) dynaBean.get("par_id");

		if (!GenericValidator.isLong(par_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		dynaBean.set("par_id", par_id);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String type_id = (String) dynaBean.get("type_id");

		if (!GenericValidator.isLong(type_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		JBaseType entity = new JBaseType();
		entity.setType_id(Long.valueOf(type_id));
		entity = super.getFacade().getJBaseTypeService().getJBaseType(entity);
		if (null == entity) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}

		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String type_id = (String) dynaBean.get("type_id");
		String par_id = (String) dynaBean.get("par_id");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);

		JBaseType entity = new JBaseType();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(par_id)) {
			entity.setPar_id(Long.valueOf(par_id));
		}

		if (GenericValidator.isLong(type_id)) {
			super.getFacade().getJBaseTypeService().modifyJBaseType(entity);
			saveMessage(request, "entity.updated");
		} else {
			entity.setC_id(user_info.getCust_id());
			super.getFacade().getJBaseTypeService().createJBaseType(entity);
			saveMessage(request, "entity.inserted");
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&par_id=" + par_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String type_id = (String) dynaBean.get("type_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String par_id = (String) dynaBean.get("par_id");

		if (!GenericValidator.isLong(type_id)) {
			String msg = super.getMessage(request, "errors.param");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		JBaseType entity = new JBaseType();
		entity.setType_id(Long.valueOf(type_id));
		entity.setIs_del(1);
		super.getFacade().getJBaseTypeService().modifyJBaseType(entity);

		saveMessage(request, "entity.deleted");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&par_id=" + par_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String type_name = (String) dynaBean.get("type_name");
		String par_id = (String) dynaBean.get("par_id");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);

		JBaseType entity = new JBaseType();
		entity.setC_id(ui.getCust_id());
		entity.setIs_del(0);
		String isExist = "null";

		if (StringUtils.isNotBlank(type_name) && StringUtils.isNotBlank(par_id)) {
			entity.setType_name(type_name);
			entity.setPar_id(Long.valueOf(par_id));
			List<JBaseType> entityList = super.getFacade().getJBaseTypeService().getJBaseTypeList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
