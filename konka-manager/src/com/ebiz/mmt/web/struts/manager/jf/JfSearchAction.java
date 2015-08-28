package com.ebiz.mmt.web.struts.manager.jf;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.domain.JfScortsExchange;
import com.ebiz.mmt.domain.MemberInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 * @version 2013-06-26
 */
public class JfSearchAction extends BaseJfAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_sn_like = (String) dynaBean.get("user_sn_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		JfScorts entity = new JfScorts();
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(user_sn_like)) {
			entity.getMap().put("user_sn_like", user_sn_like);
		}

		Long recordCount = super.getFacade().getJfScortsService().getJfScortsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<JfScorts> entityList = super.getFacade().getJfScortsService().getJfScortsPaginatedList(entity);

		if (null != entityList && entityList.size() > 0) {
			for (JfScorts temp : entityList) {
				MemberInfo mem = new MemberInfo();
				mem.setUser_sn(temp.getUser_sn());
				mem = super.getFacade().getMemberInfoService().getMemberInfo(mem);
				if (null != mem) {
					temp.getMap().put("user_name", mem.getUser_name());
				}
			}

		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}
		JfScorts entity = new JfScorts();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJfScortsService().getJfScorts(entity);

		if (null == entity) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		entity.setQueryString(super.serialize(request, "id", "method"));

		dynaBean.set("user_sn", entity.getUser_sn());

		MemberInfo mem = new MemberInfo();
		mem.setUser_sn(entity.getUser_sn());
		mem = super.getFacade().getMemberInfoService().getMemberInfo(mem);
		if (null != mem) {
			dynaBean.set("user_name", mem.getUser_name());
		}

		dynaBean.set("total_scorts", entity.getTotal_scorts());

		// 会员积分明细
		JfScortsDetails details = new JfScortsDetails();
		details.setUser_sn(entity.getUser_sn());
		details.setStatus(1);
		details.getMap().put("add_date_asc", true);

		List<JfScortsDetails> detailsList = super.getFacade().getJfScortsDetailsService()
				.getJfScortsDetailsAndDeptNameList(details);
		request.setAttribute("detailsList", detailsList);

		// 会员礼品兑换
		JfScortsExchange exchange = new JfScortsExchange();
		exchange.setUser_sn(entity.getUser_sn());
		exchange.getMap().put("add_date_asc", true);
		List<JfScortsExchange> exchangeList = super.getFacade().getJfScortsExchangeService()
				.getJfScortsExchangeAndGiftNameList(exchange);
		request.setAttribute("exchangeList", exchangeList);

		return mapping.findForward("view");
	}
}
