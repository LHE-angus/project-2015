package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeExchange;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcOrderExchangeAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String oper_date_start = (String) dynaBean.get("oper_date_start");
		String oper_date_end = (String) dynaBean.get("oper_date_end");

		PshowOrdeExchange entity = new PshowOrdeExchange();		
		super.copyProperties(entity, form);
		if(trade_index_like!=null&&!"".equals(trade_index_like)){
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if(oper_date_start!=null&&!"".equals(oper_date_start)){
			entity.getMap().put("oper_date_start", oper_date_start);
		}		
		if(oper_date_end!=null&&!"".equals(oper_date_end)){
			entity.getMap().put("oper_date_end", oper_date_end);
		}		
		
		Long recordCount = getFacade().getPshowOrdeExchangeService().getPshowOrdeExchangeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrdeExchange> entityList = getFacade().getPshowOrdeExchangeService().getPshowOrdeExchangePaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg
					+ "');history.back();}");
			return null;
		}

		PshowOrdeExchange entity = new PshowOrdeExchange();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrdeExchangeService()
				.getPshowOrdeExchange(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		PshowOrdeExchange entity = new PshowOrdeExchange();
		super.copyProperties(entity, form);
		getFacade().getPshowOrdeExchangeService().modifyPshowOrdeExchange(
				entity);
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			String msg = super.getMessage(request, "errors.parm");
			renderJavaScript(response, "window.onload=function(){alert('" + msg+ "');history.back();}");
			return null;
		}
		PshowOrdeExchange entity = new PshowOrdeExchange();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getPshowOrdeExchangeService()
				.getPshowOrdeExchange(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
	
	public ActionForward sheet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request); 
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String oper_date_start = (String) dynaBean.get("oper_date_start");
		String oper_date_end = (String) dynaBean.get("oper_date_end");

		PshowOrdeExchange entity = new PshowOrdeExchange();		
		if(trade_index_like!=null&&!"".equals(trade_index_like)){
			entity.getMap().put("trade_index_like", trade_index_like);
		}
		if(oper_date_start!=null&&!"".equals(oper_date_start)){
			entity.getMap().put("oper_date_start", oper_date_start);
		}		
		if(oper_date_end!=null&&!"".equals(oper_date_end)){
			entity.getMap().put("oper_date_end", oper_date_end);
		}		
		 
		List<PshowOrdeExchange> entityList = getFacade().getPshowOrdeExchangeService().getPshowOrdeExchangeList(entity);
		request.setAttribute("entityList", entityList);
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		return new ActionForward("/spgl/EcOrderExchange/sheet.jsp");
	}
}
