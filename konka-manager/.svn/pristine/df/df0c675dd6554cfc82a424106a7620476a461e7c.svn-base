package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class SxOperLogAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		dynaBean.set("user_name", ui.getUser_name());
		OperLog t = new OperLog();
		t.setOper_uid(ui.getId());
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isNotEmpty(page)) {
			currentPage = Integer.parseInt(page);
		}
		if (StringUtils.isNotEmpty(pagelimit)) {
			pageSize = Integer.parseInt(pagelimit);
		}
		t.getRow().setFirst((currentPage - 1) * pageSize);
		t.getRow().setCount(pageSize);
		
		Long count = super.getFacade().getOperLogService()
				.getOperLogCount(t);
		String str = "{htmlData:'";
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<OperLog> loglist = super.getFacade()
					.getOperLogService()
					.getOperLogPaginatedList(t);
			str += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\" >";
			for (OperLog _t : loglist) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				str += "<li data-role=\"list-divider\"><span><strong>";
				str+=simpleDateFormat.format(_t.getOper_time());
				str+="</strong></span></li>";
				str+="<li>操作IP：<span style=\"font-size: 12px; font-weight: normal;\">";
				str += _t.getOper_ip();
				str+="</span></li>";
				str+="<li>操作方式：<span style=\"font-size: 12px; font-weight: normal;\">";
				str+=_t.getOper_type();
				str+="</span></li><li></li>";
			}
				str += "</ul>',";
				str += "currentPage:" + currentPage + ",recordCount:" + count
						+ "}";
			} else {
				str += "',";
				str += "currentPage:" + currentPage + ",recordCount:" + count
						+ "}";
			}
			super.renderText(response, str);
		return null;
	}
}
