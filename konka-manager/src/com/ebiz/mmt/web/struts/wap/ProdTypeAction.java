package com.ebiz.mmt.web.struts.wap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping; 

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class ProdTypeAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		return mapping.findForward("list");
	} 
}
