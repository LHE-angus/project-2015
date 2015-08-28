package com.ebiz.mmt.web.struts.jxc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.StdEntpUser;

/**
 * @author Wu,Yang
 * @date 2011-10-19
 */
public class JxcMyProfileAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		return mapping.findForward("input");
	}

}
