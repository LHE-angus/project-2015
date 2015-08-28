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

import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class HistoryReportAction extends MobileBaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String type_id = (String) dynaBean.get("type_id");
		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaMobileReportHistory t = new KonkaMobileReportHistory();
		t.setReport_man(ui.getId());
		if (type_id!=null && type_id!="") {
			t.setType_id(new Long(type_id));
		} 
		dynaBean.set("type_id", type_id);
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
		Long count = super.getFacade().getKonkaMobileReportHistoryService()
				.getKonkaMobileReportHistoryCount(t);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		String str = "{htmlData:'";
		if (count > 0) {
			List<KonkaMobileReportHistory> baselist = super.getFacade()
					.getKonkaMobileReportHistoryService()
					.getKonkaMobileReportHistoryPaginatedList(t);
			str += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\" >";
			for (KonkaMobileReportHistory _t : baselist) {
				str +="<li data-role=\"list-divider\">";
				if(_t.getType_id()==1){
					str +="销售数据";
				}else if(_t.getType_id()==2){
					str +="退货数据";
				}else if(_t.getType_id()==3){
					str +="终端物料";
				}else if(_t.getType_id()==4){
					str +="竞品数据";
				}
				str +="<span class=\"ui-li-count\"><strong>";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				str +=simpleDateFormat.format(_t.getReport_time());
				str+="</strong></span></li>";
				str+="<li><span style=\"font-size: 12px; font-weight: normal;\">";
				str+=_t.getContent();
				str+="</span></li>";
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