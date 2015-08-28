package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3ShopTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-12-06
 */
public class KonkaR3ShopTaskAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String p_user_name_like = (String) dynaBean.get("p_user_name_like");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String task_type = (String) dynaBean.get("task_type");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaR3ShopTask entity = new KonkaR3ShopTask();

		if (StringUtils.isNotBlank(r3_code_like))
			entity.getMap().put("r3_code_like", r3_code_like);
		if (StringUtils.isNotBlank(p_user_name_like))
			entity.getMap().put("p_user_name_like", p_user_name_like);
		if (StringUtils.isNotBlank(year))
			entity.setYear(Integer.valueOf(year));
		if (StringUtils.isNotBlank(month))
			entity.setMonth(Integer.valueOf(month));
		if (StringUtils.isNotBlank(task_type))
			entity.setTask_type(Integer.valueOf(task_type));

		KonkaDept kd = super.getKonkaDeptForFgs(ui.getDept_id());

		if (kd != null) {
			request.setAttribute("is_fgs", "is_fgs");
			entity.setDept_id(kd.getDept_id());
		} else {
			if (StringUtils.isNotBlank(dept_id))
				entity.setDept_id(Long.valueOf(dept_id));
		}

		Long recordCount = getFacade().getKonkaR3ShopTaskService().getKonkaR3ShopTaskCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3ShopTask> entityList = super.getFacade().getKonkaR3ShopTaskService()
				.getKonkaR3ShopTaskPaginatedList(entity);

		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		request.setAttribute("entityList", entityList);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}
}
