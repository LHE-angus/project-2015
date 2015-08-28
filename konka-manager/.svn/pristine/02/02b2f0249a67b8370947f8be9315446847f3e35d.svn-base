package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
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

import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeExchange;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcBaseExpressAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String express_name_like = (String) dynaBean.get("express_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseExpress entity = new EcBaseExpress();
		if (StringUtils.isNotBlank(express_name_like)) {
			entity.getMap().put("express_name_like", express_name_like);

		}

		Long recordCount = super.getFacade().getEcBaseExpressService().getEcBaseExpressCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseExpress> entityList = super.getFacade().getEcBaseExpressService().getEcBaseExpressPaginatedList(
		        entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return mapping.findForward("input");
	}

	public ActionForward test(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return new ActionForward("/../manager/spgl/EcBaseExpress/test.jsp");
	}

	public ActionForward test2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return new ActionForward("/../manager/spgl/EcBaseExpress/test2.jsp");
	}

	public ActionForward test3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		List<PshowOrder> entityList = new ArrayList<PshowOrder>();
		PshowOrder p1 = new PshowOrder();
		p1.setId(1L);
		p1.setTrade_index("111");
		List<PshowOrdeExchange> l1 = new ArrayList<PshowOrdeExchange>();
		PshowOrdeExchange pe1 = new PshowOrdeExchange();
		pe1.setId(333L);
		pe1.setExchange_info(22);
		l1.add(pe1);
		PshowOrdeExchange pe2 = new PshowOrdeExchange();
		pe2.setId(444L);
		pe2.setExchange_info(33);
		l1.add(pe2);
		p1.setPshowOrdeExchangeList(l1);

		entityList.add(p1);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcBaseExpress/test3.jsp");
	}

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		return new ActionForward("/../manager/spgl/EcBaseExpress/show.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String express_id = (String) dynaBean.get("express_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String express_ui_type = (String) dynaBean.get("express_ui_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseExpress entity = new EcBaseExpress();
		super.copyProperties(entity, form);
		entity.setAdd_date(new Date());
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(express_ui_type)) {
			entity.setExpress_ui_type(Integer.valueOf(express_ui_type));
		}

		if (StringUtils.isEmpty(express_id)) {
			super.getFacade().getEcBaseExpressService().createEcBaseExpress(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcBaseExpressService().modifyEcBaseExpress(entity);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String express_id = (String) dynaBean.get("express_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(express_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseExpress entity = new EcBaseExpress();
		entity.setExpress_id(Long.valueOf(express_id));
		super.getFacade().getEcBaseExpressService().removeEcBaseExpress(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "express_id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String express_id = (String) dynaBean.get("express_id");
		if (!GenericValidator.isLong(express_id)) {
			super.saveError(request, "errors.long", "express_id");
			return this.list(mapping, form, request, response);
		}

		EcBaseExpress entity = new EcBaseExpress();
		entity.setExpress_id(Long.valueOf(express_id));

		entity = super.getFacade().getEcBaseExpressService().getEcBaseExpress(entity);
		entity.setQueryString(super.serialize(request, "express_id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
}
