package com.ebiz.mmt.web.struts.wap.center;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.struts.wap.BaseMemberAction;

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class IndexAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		return mapping.findForward("list");
	} 
}
