package com.ebiz.mmt.web.struts.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JsSells;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2014-01-11
 * @desc 进销存月度结算
 */
public class JsSellsAction extends BaseClientJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		String sell_date_ge = (String) dynaBean.get("sell_date_ge"); // 开始时间
		String sell_date_le = (String) dynaBean.get("sell_date_le"); // 结束时间

		String md_name_like = (String) dynaBean.get("md_name_like");

		JsSells entity = new JsSells();
		entity.setC_id(user.getCust_id());

		if (StringUtils.isNotBlank(sell_date_ge)) {
			entity.getMap().put("sell_date_ge", sell_date_ge);
		}
		if (StringUtils.isNotBlank(sell_date_le)) {
			entity.getMap().put("sell_date_le", sell_date_le);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		Long recordCount = super.getFacade().getJsSellsService().getJsSellsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JsSells> entityList = super.getFacade().getJsSellsService().getJsSellsPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");

	}

	public ActionForward list_ml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		String sell_date_ge = (String) dynaBean.get("sell_date_ge"); // 开始时间
		String sell_date_le = (String) dynaBean.get("sell_date_le"); // 结束时间

		String md_name_like = (String) dynaBean.get("md_name_like");

		JsSells entity = new JsSells();
		entity.setC_id(user.getCust_id());

		if (StringUtils.isNotBlank(sell_date_ge)) {
			entity.getMap().put("sell_date_ge", sell_date_ge);
		}
		if (StringUtils.isNotBlank(sell_date_le)) {
			entity.getMap().put("sell_date_le", sell_date_le);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}

		List<JsSells> entityList = super.getFacade().getJsSellsService().getJsSellsListForML(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/JsSells/list_ml.jsp");

	}
}