package com.ebiz.mmt.web.struts.manager.spgl;

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

import com.ebiz.mmt.domain.EcBaseCardLevel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcBaseCardLevelAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String card_level_name_like = (String) dynaBean.get("card_level_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardLevel entity = new EcBaseCardLevel();
		if (StringUtils.isNotBlank(card_level_name_like)) {
			entity.getMap().put("card_level_name_like", card_level_name_like);

		}

		Long recordCount = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseCardLevel> entityList = super.getFacade().getEcBaseCardLevelService()
		        .getEcBaseCardLevelPaginatedList(entity);
		request.setAttribute("entityList", entityList);

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

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String card_level = (String) dynaBean.get("card_level");
		String mod_id = (String) dynaBean.get("mod_id");

		if (StringUtils.isBlank(card_level)) {
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}

			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardLevel entity = new EcBaseCardLevel();
		super.copyProperties(entity, form);

		if (StringUtils.isEmpty(card_level)) {
			entity.setCard_type_addman(user.getReal_name());
			entity.setCard_type_adddate(new Date());
			super.getFacade().getEcBaseCardLevelService().createEcBaseCardLevel(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcBaseCardLevelService().modifyEcBaseCardLevel(entity);
			super.saveMessage(request, "entity.updated");
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String card_level = (String) dynaBean.get("card_level");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardLevel entity = new EcBaseCardLevel();
		entity.setCard_level(Long.valueOf(card_level));
		entity = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevel(entity);

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String card_level = (String) dynaBean.get("card_level");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(card_level)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseCardLevel entity = new EcBaseCardLevel();
		entity.setCard_level(Long.valueOf(card_level));
		super.getFacade().getEcBaseCardLevelService().removeEcBaseCardLevel(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "card_level", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String card_level = (String) dynaBean.get("card_level");
		if (!GenericValidator.isLong(card_level)) {
			super.saveError(request, "errors.long", "card_level");
			return this.list(mapping, form, request, response);
		}

		EcBaseCardLevel entity = new EcBaseCardLevel();
		entity.setCard_level(Long.valueOf(card_level));

		entity = super.getFacade().getEcBaseCardLevelService().getEcBaseCardLevel(entity);
		entity.setQueryString(super.serialize(request, "card_level", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
}
