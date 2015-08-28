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

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2014-02-20
 */
public class EcUserScoreRecAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_id = (String) dynaBean.get("user_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcUserScoreRec entity = new EcUserScoreRec();
		entity.setUser_id(new Long(user_id));
		Long recordCount = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcUserScoreRec> entityList = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		String user_id = (String) dynaBean.get("user_id");
        EcUser ecUser = new EcUser();
        ecUser.setId(new Long(user_id));
        ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcUserScoreRec entity = new EcUserScoreRec();
		entity.setUser_id(new Long(user_id));
		entity.setOpr_type(new Integer(0));
		super.copyProperties(form, entity);
		request.setAttribute("real_name", ecUser.getReal_name());
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		 
		
		EcUserScoreRec entity = new EcUserScoreRec();
		super.copyProperties(entity, form);
		
		EcUser ecUser = new EcUser();
        ecUser.setId(entity.getUser_id());
        ecUser = super.getFacade().getEcUserService().getEcUser(ecUser);
        entity.setUser_name(ecUser.getUser_name());
        entity.setOpr_id(user.getId());
        entity.setOpr_date(new Date());
        entity.setAll_score(new Integer(0));
		if (StringUtils.isEmpty(id)) {
			super.getFacade().getEcUserScoreRecService().createEcUserScoreRec(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getEcUserScoreRecService().modifyEcUserScoreRec(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&user_id=" + entity.getUser_id());
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
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcUserScoreRec entity = new EcUserScoreRec();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcUserScoreRecService().getEcUserScoreRec(entity);

		super.copyProperties(form, entity);

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

		EcUserScoreRec entity = new EcUserScoreRec();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcUserScoreRecService().getEcUserScoreRec(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}
}
