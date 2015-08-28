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

import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaSellReportDetailAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String state = (String) dynaBean.get("state");
		String cus_sn_like = (String) dynaBean.get("cus_sn_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		// PeProdUser peProdUser = (PeProdUser) session.getAttribute(Constants.PE_PROD_USER_SESSION);

		KonkaSell entity = new KonkaSell();
		entity.setIs_del(0);
		entity.getMap().put("cus_sn_like", cus_sn_like);
		entity.getMap().put("r3_code_like", r3_code_like);
		entity.getMap().put("handle_name_like", handle_name_like);
		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("ywy_name_like", ywy_name_like);

		if (StringUtils.isNotBlank(sell_date_start)) {
			entity.getMap().put("sell_date_start", sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

		// 处理不同权限的人登陆系统所需要查询的数据
		// if ("true".equals(peProdUser.getMap().get("is_leader"))) {
		// if (peProdUser.getUser_type() != 0) {
		// entity.getMap().put("is_leader_dept_id", peProdUser.getDept_id());
		// }
		//
		// if (StringUtils.isEmpty(state)) {
		// entity.getMap().put("state_in", new Integer[] { 1, 2 }); // 部门领导看不到暂存的数据
		// } else {
		// entity.setState(Integer.parseInt(state));
		// }
		// } else {
		// entity.setAdd_user_id(ui.getId());
		if (0 != ui.getId()) { // 超级管理员可以查看全部
			entity.getMap().put("dept_id", ui.getDept_id());
		}
		if (StringUtils.isEmpty(state)) {
			entity.getMap().put("state_in", new Integer[] { 0, 1, 2 });
		} else {
			entity.setState(Integer.parseInt(state));
		}
		// }

		Long recordCount = super.getFacade().getKonkaSellService().getKonkaSellCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSell> konkaSellList = super.getFacade().getKonkaSellService().getKonkaSellPaginatedList(entity);

		request.setAttribute("konkaSellList", konkaSellList);
		// request.setAttribute("is_leader", peProdUser.getMap().get("is_leader"));
		return mapping.findForward("list");
	}

}