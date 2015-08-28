package com.ebiz.mmt.web.struts;

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

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-17
 */
public class MyOrderAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String state = (String) dynaBean.get("state");
		
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			renderJavaScript(response, "alert('" + super.getMessage(request, "need.login") +"');location.href = '" + super.getCtxPath(request) + "/customer';");
			return null;
		}
		PshowOrder entity = new PshowOrder();
		entity.setOrder_user_id(ui.getId());
		if (StringUtils.isNotBlank(state)) {
			entity.getMap().put("state_in", StringUtils.split(state, "_"));
		}
		
		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrderCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PshowOrder> entityList = super.getFacade().getPshowOrderService().getPshowOrderIncludeDetailsPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}
	
	public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			PshowOrder entity = new PshowOrder();
			entity.setId(new Long(id));
			entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
			if (entity.getState() >= 10) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "cannot.close") + "');history.back();");
				return null;
			}

			entity.setState(-10);
			super.getFacade().getPshowOrderService().modifyPshowOrder(entity);
			saveMessage(request, "order.close.success");
			renderJavaScript(response, "alert('" + super.getMessage(request, "order.close.success")+ "');location.href = '" + super.getCtxPath(request)+ "/MyOrder.do';");
			return null;
		} 
		
		return this.list(mapping, form, request, response);
	}
}
