package com.ebiz.mmt.web.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;

/**
 * @author Pan,Gang
 * @version 2014-1-09
 */
public class EcUserScoreRecAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		MobileUser mu = (MobileUser) session.getAttribute("mobile_user");

		if (null == mu) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		EcUserScoreRec entity = new EcUserScoreRec();
		String ts = (String) dynaBean.get("ts");
		if ("before3months".equals(ts)) {
			entity.getMap().put("before3months", "before3months");
		} else {
			entity.getMap().put("last3months", "last3months");
			ts = "last3months";
		}
		// dynaBean.set("ts", ts);
		entity.setUser_id(Long.valueOf(mu.getUser_id()));
		Long recordCount = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecCount(entity);

		// 分页
		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		int pageCount = (int) Math.ceil((recordCount + pageSize - 1) / pageSize);

		dynaBean.set("recordCount", recordCount);

		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);
			dynaBean.set("pageCount", pageCount);
			if (currentPage > pageCount) {
				super.renderJavaScript(response, "window.onload=function(){alert('当前页已是最后一页！');history.back();}");
				return null;
			}
			List<EcUserScoreRec> entityList = super.getFacade().getEcUserScoreRecService()
			        .getEcUserScoreRecPaginatedList(entity);
			request.setAttribute("entityList", entityList);

		}
		dynaBean.set("user_id", mu.getUser_id());
		dynaBean.set("userpass", mu.getUserpass());
		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
		        .getEcUserScoreRecForUserTotalScore(Long.valueOf(mu.getUser_id())));

		PshowOrdeDetails orderDetail = new PshowOrdeDetails();
		orderDetail.getRow().setFirst(0);
		orderDetail.getRow().setCount(20);
		orderDetail.getMap().put("user_id", Long.valueOf(mu.getUser_id()));
		orderDetail.getMap().put("state_in", new Integer[] { 60 });
		orderDetail.getMap().put("order_from", 1);
		List<PshowOrdeDetails> orderDetailList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(orderDetail);
		for (PshowOrdeDetails pshowOrdeDetails : orderDetailList) {
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
			konkaBcompPd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
			pshowOrdeDetails.getMap().put("pd_sn", konkaBcompPd.getPd_sn());
		}
		request.setAttribute("orderDetailList", orderDetailList);
		return new ActionForward("/mobile/EcUserScoreRec/list.jsp");
	}

}
