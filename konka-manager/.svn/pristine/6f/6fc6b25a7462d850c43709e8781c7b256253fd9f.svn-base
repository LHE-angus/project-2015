package com.ebiz.mmt.web.struts.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Wu,ShangLong
 * @version 2013-07-25
 */
public class JxcUserPosAction extends BaseClientJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.view(mapping, form, request, response);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user_info.getCust_id());

		konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

		if (null != konkaR3Shop) {
			if (null != konkaR3Shop.getB_lng() && null != konkaR3Shop.getB_lat()) {
				request.setAttribute("b_lng", konkaR3Shop.getB_lng());
				request.setAttribute("b_lat", konkaR3Shop.getB_lat());
			}
			request.setAttribute("cust_name", konkaR3Shop.getCustomer_name());
		}

		return mapping.findForward("view");
	}

	public ActionForward ajaxSaveLngLat(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String b_lng = (String) dynaBean.get("b_lng");
		String b_lat = (String) dynaBean.get("b_lat");

		PeProdUser user_info = super.getSessionCustomerUserInfo(request);

		StringBuffer sb = new StringBuffer("{");

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(user_info.getCust_id());
		konkaR3Shop.setB_lng(b_lng);
		konkaR3Shop.setB_lat(b_lat);

		int count = super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(konkaR3Shop);

		if (count == 1) {
			sb.append("\"status\":\"1\"");
		} else {
			sb.append("\"status\":\"0\"");
		}

		sb.append("}");

		super.renderJson(response, sb.toString());
		return null;
	}
}
