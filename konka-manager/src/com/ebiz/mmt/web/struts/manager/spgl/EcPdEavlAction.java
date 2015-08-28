package com.ebiz.mmt.web.struts.manager.spgl;

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
import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;


public class EcPdEavlAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String eval_title_like = (String) dynaBean.get("eval_title_like");
		String audit_state = (String) dynaBean.get("audit_state");
		EcPdEavl entity = new EcPdEavl();
		if (StringUtils.isNotBlank(eval_title_like)) {
			entity.getMap().put("eval_title_like", eval_title_like);
		}
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(new Integer(audit_state));
		}
		entity.setIs_del(0);
		Long recordCount = getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcPdEavl> entityList = getFacade().getEcPdEavlService().getEcPdEavlPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}


	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		EcPdEavl entity = new EcPdEavl();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcPdEavlService().getEcPdEavl(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		if(entity.getRe_real_name()==null){
			entity.setRe_real_name(user.getReal_name());
		}

		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		EcPdEavl entity = new EcPdEavl();
		super.copyProperties(entity, form);
		entity.setRe_user_id(user.getId());
		getFacade().getEcPdEavlService().modifyEcPdEavl(entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
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

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		EcPdEavl entity = new EcPdEavl();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcPdEavlService().getEcPdEavl(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String eval_title_like = (String) dynaBean.get("eval_title_like");
		String audit_state = (String) dynaBean.get("audit_state");
		EcPdEavl entity = new EcPdEavl();
		if (StringUtils.isNotBlank(eval_title_like)) {
			entity.getMap().put("eval_title_like", eval_title_like);
		}
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(new Integer(audit_state));
		}
		entity.setIs_del(0);
		Long recordCount = getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<EcPdEavl> entityList = getFacade().getEcPdEavlService().getEcPdEavlPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/spgl/EcPdEavl/sheet.jsp");
	}
}
