package com.ebiz.mmt.web.struts.touch.center;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author tudp
 * @version 2013-09-09
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
		Pager pager = (Pager) dynaBean.get("pager");
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		// 账户中心用户登录验证
		if (ecUser.getUser_type().intValue() == 1) {
			String touch = (String) session.getAttribute("touch");
			if (!"1".equals(touch)) {
				String ctx = super.getCtxPath(request);
				String url = ctx + "/touch/center/Skip.do";
				response.sendRedirect(url);
				return null;
			}
		}
		EcUserScoreRec entity = new EcUserScoreRec();
		String ts = (String) dynaBean.get("ts");
		if ("before3months".equals(ts)) {
			entity.getMap().put("before3months", "before3months");
		} else {
			entity.getMap().put("last3months", "last3months");
			ts = "last3months";
		}
		dynaBean.set("ts", ts);
		entity.setUser_id(ecUser.getId());
		Long recordCount = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcUserScoreRec> entityList = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecPaginatedList(
		        entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
		        .getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		// 付款积分
		String payTotalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(ecUser.getId());
		request.setAttribute("payTotalScore", new BigDecimal(payTotalScore).setScale(0, BigDecimal.ROUND_HALF_UP));
		logger.info("payTotalScore--------->", payTotalScore);// 取整

		PshowOrdeDetails orderDetail = new PshowOrdeDetails();
		orderDetail.getRow().setFirst(0);
		orderDetail.getRow().setCount(20);
		orderDetail.getMap().put("user_id", ecUser.getId());
		orderDetail.getMap().put("state_in", new Integer[] { 60 });
		orderDetail.getMap().put("order_from", ecUser.getUser_type());
		List<PshowOrdeDetails> orderDetailList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(orderDetail);
		request.setAttribute("orderDetailList", orderDetailList);
		if (null != orderDetailList && orderDetailList.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : orderDetailList) {
				if (null != pshowOrdeDetails.getIntegral()) {
					pshowOrdeDetails.getMap().put("jl_jf",
					        pshowOrdeDetails.getIntegral().setScale(0, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		return mapping.findForward("list");
	}

}
