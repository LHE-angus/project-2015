package com.ebiz.mmt.web.struts.webservice;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class SfTuiSongAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		logger.info("+++++++++++顺丰接口进来了！！");

		String ss = request.getParameter("ss");
		//System.out.println(ss);
		BufferedReader in = request.getReader();
		String line;
		try {
			while ((line = in.readLine()) != null) {
				//System.out.println(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
