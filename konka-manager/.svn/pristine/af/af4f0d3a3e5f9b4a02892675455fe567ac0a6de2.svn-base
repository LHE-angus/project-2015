package com.ebiz.mmt.web.struts.webservice.wap.center;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;

/**
 * @author TUDP
 * @version 2013-12-02
 */
public class IndexAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		// 从Session中取用户并判断
				EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
				if (null == ecUser) {
					String msg = super.getMessage(request, "ec.nologin");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
							+ "/webservice/wap/login.do';}");
					return null;
				}
		return mapping.findForward("list");
	} 
}
