package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.SapExecuteLog;
import com.ebiz.mmt.web.struts.BaseAction;

public class SapExecuteLogAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		List<HashMap> list = new ArrayList<HashMap>();
		list = super.getFacade().getSapExecuteLogService().getSapExecuteLogForReport();

		request.setAttribute("entityList", list);


		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 由于系统压力,以及日志的存在意义,决定只保留一个月的日志
		setNaviStringToRequestScope(form, request);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1);
		int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxday, 23, 59, 59);
		
		SapExecuteLog sap = new SapExecuteLog();
		sap.setEnd_time(calendar.getTime());
		
		super.getFacade().getSapExecuteLogService().removeSapExecuteLog(sap);
		super.saveMessage(request, "entity.updated");

		List<HashMap> list = new ArrayList<HashMap>();
		list = super.getFacade().getSapExecuteLogService().getSapExecuteLogForReport();
		request.setAttribute("entityList", list);

		return mapping.findForward("list");
	}

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		// calendar.set(Calendar.MONTH, 0);
		calendar.add(Calendar.MONTH, 0);
		int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxday, 23, 59, 59);
		//System.out.println(maxday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sdf.format(calendar.getTime()));

	}

}
