package com.ebiz.mmt.web.struts.unionpay;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.BaseAction;
import com.unionpay.b2c.QuickPayConf;
import com.unionpay.b2c.QuickPayUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-16
 */
public class EcOrderNotifyAction extends BaseAction {

	/**
	 * @desc 银联交易B2C【后台通知】接口：银行支付成功后，银联会自动调用此接口改变订单状态。
	 * @desc 与【前台通知】的区别是，只更新订单状态，不跳转页面。
	 * @author Ren,zhong
	 * @version 2012-07-10
	 */
	public ActionForward unionpayB2cReceiveNoticeForHt(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.warn("---------- Begin [unionpayB2cReceiveNoticeForHt] process......");
		String unionpayMethod = "unionpayB2cReceiveNoticeForHt";
		request.setCharacterEncoding(QuickPayConf.charset);

		String[] resArr = new String[QuickPayConf.notifyVo.length];
		for (int i = 0; i < QuickPayConf.notifyVo.length; i++) {
			resArr[i] = request.getParameter(QuickPayConf.notifyVo[i]);
		}
		String signature = request.getParameter(QuickPayConf.signature);
		String signMethod = request.getParameter(QuickPayConf.signMethod);

		response.setContentType("text/html;charset=" + QuickPayConf.charset);
		response.setCharacterEncoding(QuickPayConf.charset);

		Boolean signatureCheck = new QuickPayUtils().checkSign(resArr, signMethod, signature);
		if (signatureCheck) {
			if ("00".equals(resArr[10])) {
				String orderNumber = resArr[8];// 订单号
				String qid = resArr[9];// 银联返回，交易流水号，方便和银联那边技术人员沟通，查询订单状态
				logger.info("___________________unionpayB2cReceiveNoticeForHt________________qid =" + qid);
				List<PshowOrder> orderList = this.modifyOrderInfo(orderNumber, qid, request);
				if (null != orderList && orderList.size() > 0) {
					logger.warn("银联交易B2C【后台通知】接口 支付成功，订单号：{},交易流水号:{}", orderNumber, qid);
					response.getWriter().print(unionpayMethod + "支付成功，订单号：" + orderNumber);
				}
			} else {
				response.getWriter().print(unionpayMethod + "签名不正确");
			}
		} else {
			response.getWriter().print(unionpayMethod + "交易失败，失败原因:" + resArr[11]);
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return null;
	}

	/**
	 * @desc 银联交易B2C【前台通知】接口：银行支付成功后，点击下一步返回产品购买页面时调用,更新订单状态
	 * @author Ren,zhong
	 * @version 2012-07-10
	 */
	public ActionForward unionpayB2cReceiveNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String unionpayMethod = "unionpayB2cReceiveNotice";
		logger.warn("---------- Begin [unionpayB2cReceiveNotice] process......");
		request.setCharacterEncoding(QuickPayConf.charset);

		String[] resArr = new String[QuickPayConf.notifyVo.length];
		for (int i = 0; i < QuickPayConf.notifyVo.length; i++) {
			resArr[i] = request.getParameter(QuickPayConf.notifyVo[i]);
		}
		String signature = request.getParameter(QuickPayConf.signature);
		String signMethod = request.getParameter(QuickPayConf.signMethod);

		response.setContentType("text/html;charset=" + QuickPayConf.charset);
		response.setCharacterEncoding(QuickPayConf.charset);

		Boolean signatureCheck = new QuickPayUtils().checkSign(resArr, signMethod, signature);
		if (signatureCheck) {
			if ("00".equals(resArr[10])) {
				String orderNumber = resArr[8];// 订单号
				String qid = resArr[9];// 银联返回，交易流水号，方便和银联那边技术人员沟通，查询订单状态
				logger.info("___________________unionpayB2cReceiveNotice________________qid =" + qid);
				List<PshowOrder> orderList = this.modifyOrderInfo(orderNumber, qid, request);
				if (null != orderList && orderList.size() > 0) {// 迁移回网点后台【支付结算】页面
					logger.warn("银联交易B2C【前台通知】接口 支付成功，订单号：{},交易流水号:{}", orderNumber, qid);
					return new ActionForward(super.getCtxPath(request) + "/Payment.do?method=view&trade_index=" + orderNumber, true); // 跳转到订单详细页面
				}
			} else {
				logger.warn("{} 签名不正确", unionpayMethod);
			}
		} else {
			logger.warn("{} 交易失败，失败原因:{}", resArr[11]);
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return null;
	}

	public List<PshowOrder> modifyOrderInfo(String out_trade_no, String trade_no, HttpServletRequest request) {
		PshowOrder orders = new PshowOrder();
		orders.setTrade_index(out_trade_no);
		List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderList(orders);
		if (null == ordersList) {
			return null;
		}
		orders.setPay_way(3); // 2: 支付宝 ；3:银联
		orders.setTrade_no(trade_no); // 交易单号赋值
		orders.setState(5); // 更改状态
		orders.setPay_date(new Date()); // 支付时间
		super.getFacade().getPshowOrderService().modifyPshowOrderAndSaleNum(orders);

		return ordersList;
	}

}
