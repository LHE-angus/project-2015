package com.ebiz.mmt.web.struts.manager.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hui,Gang
 * @version 2011-10-29 下午6:58:08
 */
public class PersonPositionAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String type = (String) dynaBean.get("type");

		if ("0".equals(type)) {
			return mapping.findForward("list");
		} else if ("1".equals(type)) {
			return new ActionForward("/admin/PersonPosition/list2.jsp");
		} else {
			return new ActionForward("/admin/PersonPosition/list3.jsp");
		}

	}
}