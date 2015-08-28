package com.ebiz.mmt.web.struts.jxc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Wu,Yang
 * @version 2011-10-10
 */
public class FramesAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}
	
	public ActionForward leftKonkaOrderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 订单管理
		logger.info("FramesJxcStockBillAction leftKonkaOrderInfo");

		return new ActionForward("/Frames/leftFrameKonkaOrderInfo.jsp");
	}

	public ActionForward leftJxcStockBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 进货管理
		logger.info("FramesJxcStockBillAction leftJxcStockBill");

		return new ActionForward("/Frames/leftFrameJxcStockBill.jsp");
	}

	public ActionForward leftJxcSellBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 产品销售
		logger.info("FramesJxcStockBillAction leftJxcSellBill");
		return new ActionForward("/Frames/leftFrameJxcSellBill.jsp");
	}

	public ActionForward leftJxcStock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 库存管理
		logger.info("FramesJxcStockBillAction leftJxcStock");
		return new ActionForward("/Frames/leftFrameJxcStock.jsp");
	}

	public ActionForward leftJxcMyProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {// 我的信息
		logger.info("FramesJxcStockBillAction leftJxcStock");
		return new ActionForward("/Frames/leftFrameJxcMyProfile.jsp");
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("FramesJxcStockBillAction indexFrame");
		DynaBean dynaBean = (DynaBean) form;
		String goToLeftMethod = (String) dynaBean.get("goToLeftMethod");
		if (StringUtils.isBlank(goToLeftMethod)) {
			return null;
		}

		return mapping.findForward("indexFrame");
	}

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("FramesJxcStockBillAction mainFrame");
		DynaBean dynaBean = (DynaBean) form;
		String toCustomerFrameJsp = (String) dynaBean.get("toCustomerFrameJsp");
		if (StringUtils.isNotBlank(toCustomerFrameJsp)) {
			return new ActionForward("/Frames/" + toCustomerFrameJsp + ".jsp");
		}
		return mapping.findForward("mainFrame");
	}

	public ActionForward lr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("FramesJxcStockBillAction lrFrame");
		return mapping.findForward("lrFrame");
	}

}
