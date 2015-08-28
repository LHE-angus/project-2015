package com.ebiz.mmt.web.struts.manager.zmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class KonkaXxMessageFrameAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.iframe(mapping, form, request, response);
	}
	
	public ActionForward iframe(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String msg_id = (String) dynaBean.get("msg_id");
		dynaBean.set("msg_id", msg_id);
		return mapping.findForward("list");
	}
	
	public ActionForward right(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/../manager/zmd/KonkaXxMessageFrame/rightFrame.jsp");
	}
	
	public ActionForward middle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/../manager/zmd/KonkaXxMessageFrame/middleFrame.jsp");
	}

}
