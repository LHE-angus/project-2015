package com.ebiz.mmt.web.struts.epp.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;

/**
 * @author Tudp
 * @version 2014-02-08
 */
public class PdContentAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String type = (String) dynaBean.get("type");
		KonkaBcompPdContent kbpc = new KonkaBcompPdContent();
		kbpc.setKbp_id(new Long(goods_id));
		kbpc.setType(new Integer(type));
		request.setAttribute("kbpc",getFacade().getKonkaBcompPdContentService().getKonkaBcompPdContent(kbpc));
		return mapping.findForward("list");
	}
}	