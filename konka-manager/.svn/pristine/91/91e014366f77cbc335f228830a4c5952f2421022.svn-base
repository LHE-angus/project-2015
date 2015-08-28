package com.ebiz.mmt.web.struts.mobile.admin;

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
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wang Hao
 */
public class SxOperLogAction extends BaseAction {

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
		
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		dynaBean.set("user_name", ui.getUser_name());
		OperLog t = new OperLog();
		t.setOper_uid(ui.getId());
		t.getRow().setFirst((currentPage - 1) * pageSize);
		t.getRow().setCount(pageSize);
		
		Long count = super.getFacade().getOperLogService()
				.getOperLogCount(t);
		if (count % pageSize > 0)
			request.setAttribute("pagelimit", (count / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (count / pageSize));
		if (count > 0) {
			List<OperLog> list = super.getFacade()
					.getOperLogService()
					.getOperLogPaginatedList(t);
			request.setAttribute("baseList", list);
		}
		return mapping.findForward("list");
	}
}
