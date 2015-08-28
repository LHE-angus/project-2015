package com.ebiz.mmt.web.struts.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.r3.ZJ98;
import com.ebiz.mmt.r3.helper.DAOFactory;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-12-11
 */
public class KonkaPdJxcAnalyseToEmAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

		String t_ = format.format(new Date()) + "01";

		List<ZJ98> list = new ArrayList<ZJ98>();

		list = DAOFactory.getInstance().getZlesZJ98(null, null, "10", "10", t_, null, null, null);

		request.setAttribute("entityList", list);
		return mapping.findForward("list");
	}
}
