package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Jiang,JiaYong
 * @version 2013-06-07
 */
public class Html4sjsbAction extends MobileBaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DESPlus des = new DESPlus();
		PeProdUser u = this.getSessionUserInfo(request);
		String url = super.getCtxPath(request) + "/mobile/webstatic/shuj.html?from_pc=1&username="
				+ this.escape(u.getUser_name()) + "&userpass=" + escape(des.decrypt(u.getPass_word())) + "&timestamp="
				+ new Date().getTime();
		logger.info("----url--->{}", url);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");
		return null;
	}

	public ActionForward add_bill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DESPlus des = new DESPlus();
		PeProdUser u = this.getSessionUserInfo(request);
		String url = super.getCtxPath(request) + "/mobile/webstatic/shuj_bill_audit.html?from_pc=1&username="
				+ this.escape(u.getUser_name()) + "&userpass=" + escape(des.decrypt(u.getPass_word())) + "&timestamp="
				+ new Date().getTime();
		logger.info("----url--->{}", url);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");
		return null;
	}

	public ActionForward tccx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DESPlus des = new DESPlus();
		PeProdUser u = this.getSessionUserInfo(request);
		String url = super.getCtxPath(request) + "/mobile/webstatic/shuj_bill_audit_tccx.html?from_pc=1&username="
				+ this.escape(u.getUser_name()) + "&userpass=" + escape(des.decrypt(u.getPass_word())) + "&timestamp="
				+ new Date().getTime();
		logger.info("----url--->{}", url);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");
		return null;
	}
}
