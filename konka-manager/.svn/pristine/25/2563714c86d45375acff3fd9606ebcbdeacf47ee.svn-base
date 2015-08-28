package com.ebiz.mmt.web.struts.manager.oa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Xu,ZhengYang
 */
public class FramesAction extends BaseMmtoaAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward left(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		request.setAttribute("dept_id", ui.getDept_id());
		return mapping.findForward("leftFrame");
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String mmtTreeNodes = this.getMmtTreeNodes(request);
		request.setAttribute("mmtTreeNodes", mmtTreeNodes);

		return mapping.findForward("indexFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("mainFrame");
	}

	public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("topFrame");
	}

	public ActionForward toIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ActionForward("/manager/index.jsp");
	}

	public String getMmtTreeNodes(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("mmtTreeNodes");
	}
}
