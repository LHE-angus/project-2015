package com.ebiz.mmt.web.struts.webservice.wap.center;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcUserScoreRec;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EcUserScoreRecAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
					+ "/webservice/wap/login.do';}");
			return null;
		}
		
		request.setAttribute("totalScore", super.getFacade().getEcUserScoreRecService()
				.getEcUserScoreRecForUserTotalScore(ecUser.getId()));

		// 付款积分
		String payTotalScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForPayJf(ecUser.getId());
		request.setAttribute("payTotalScore", new BigDecimal(payTotalScore).setScale(2, BigDecimal.ROUND_HALF_UP));

		// 奖励积分
		String jlScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserJLScore(ecUser.getId());
		request.setAttribute("jlScore", new BigDecimal(jlScore).setScale(2, BigDecimal.ROUND_HALF_UP));

		// 已消费积分
		String xfScore = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecForUserXFScore(ecUser.getId());
		request.setAttribute("xfScore", xfScore);
		logger.info("payTotalScore--------->", payTotalScore);// 取整

		PshowOrdeDetails orderDetail = new PshowOrdeDetails();
		orderDetail.getRow().setFirst(0);
		orderDetail.getRow().setCount(20);
		orderDetail.getMap().put("user_id", ecUser.getId());
		orderDetail.getMap().put("state_in", new Integer[] { 60 });
		orderDetail.getMap().put("order_from", ecUser.getUser_type());
		List<PshowOrdeDetails> orderDetailList = super.getFacade().getPshowOrdeDetailsService()
				.getPshowOrdeDetailsForFgsPaginatedList(orderDetail);
		if (null != orderDetailList && orderDetailList.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : orderDetailList) {
				if (null != pshowOrdeDetails.getIntegral()) {
					pshowOrdeDetails.getMap().put("jl_jf",
							pshowOrdeDetails.getIntegral().setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
		request.setAttribute("orderDetailList", orderDetailList);
		
		EcUserScoreRec entity = new EcUserScoreRec();
		String ts = (String) dynaBean.get("ts");
		if ("before3months".equals(ts)) {
			entity.getMap().put("before3months", "before3months");
		} else {
			entity.getMap().put("last3months", "last3months");
			ts = "last3months";
		}
		dynaBean.set("ts", ts);
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
		entity.setUser_id(ecUser.getId());
		//总条数recordCount
		Long recordCount = super.getFacade().getEcUserScoreRecService().getEcUserScoreRecCount(entity);

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
		return mapping.findForward("list");
	}

}
