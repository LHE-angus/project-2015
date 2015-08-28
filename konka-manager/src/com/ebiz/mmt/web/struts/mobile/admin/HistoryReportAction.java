package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
/**
 * @author Wang Hao
 */
public class HistoryReportAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		String type_id = (String) dynaBean.get("type_id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaMobileReportHistory t = new KonkaMobileReportHistory();
		dynaBean.set("mod_id", mod_id);
		request.setAttribute("type_id", type_id);
		t.setReport_man(ui.getId());
		String type_name =null;
		if (type_id!=null && type_id!="") {
			t.setType_id(new Long(type_id));
			if(t.getType_id()==1){
				type_name ="销售数据";
			}else if(t.getType_id()==2){
				type_name ="退货数据";
			}else if(t.getType_id()==3){
				 type_name ="终端物料数据";
			}else{
				type_name ="竞品数据";
			}
			super.createMobileSysOperLog(request, "KONKA_MOBILE_REPORT_HISTORY",new Long(730000), "730000", "查询：" + ui.getUser_name()+"查询自己上报的所有的"+type_name, BeanUtils.describe(t).toString());
		} 
		dynaBean.set("type_id", type_id);
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		t.getRow().setFirst((currentPage - 1) * pageSize);
		t.getRow().setCount(pageSize);
		Long count = super.getFacade().getKonkaMobileReportHistoryService()
				.getKonkaMobileReportHistoryCount(t);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<KonkaMobileReportHistory> list = super.getFacade()
					.getKonkaMobileReportHistoryService()
					.getKonkaMobileReportHistoryPaginatedList(t);
			request.setAttribute("baseList", list);
		}

		return mapping.findForward("list");
	}
}
