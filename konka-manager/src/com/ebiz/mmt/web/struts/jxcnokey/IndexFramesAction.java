package com.ebiz.mmt.web.struts.jxcnokey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Wu,Yang
 * @version 2011-10-10
 */
public class IndexFramesAction extends BaseJxcAction {
	public ActionForward top(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("IndexFramesAction topFrame");
		return mapping.findForward("topFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("IndexFramesAction mainFrame");
		DynaBean dynaBean = (DynaBean) form;
		String toCustomerFrameJsp = (String) dynaBean.get("toCustomerFrameJsp");
		String keySeq = (String) dynaBean.get("keySeq");
		logger.info("keySeq:{}", keySeq);
		if (StringUtils.isBlank(keySeq)) {
			return null;
		}
		if (StringUtils.isNotBlank(toCustomerFrameJsp)) {
			return new ActionForward("/IndexFrames/" + toCustomerFrameJsp + ".jsp");
		}
		return mapping.findForward("mainFrame");
	}

}
