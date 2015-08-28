package com.ebiz.mmt.web.struts.epay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcOrderPay;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.BaseAction;
import com.epay.config.EpayConfig;

/**
 * @author TUDP
 * @version 2013-12-10
 * 
 */
public class EcOrderNotifyAction extends BaseAction {

	private static final String success = "true";

	private static final String fail = "false";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//System.out.println("RequestURI:" + request.getRequestURI());
		String partner_id = EpayConfig.partnerID; // 支付合作伙伴id (账户内提取)
		String privateKey = EpayConfig.key; // 支付安全校验码(账户内提取)
		// **********************************************************************************
		// 如果您服务器不支持https交互，可以使用http的验证查询地址
		/*
		 * 注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接，另外检查本地端口，
		 * 请挡开80或者443端口
		 */
		// String alipayNotifyURL =
		// "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"

		// StringBuilder epayNotifyUrlBuilder = new StringBuilder();
		// epayNotifyUrlBuilder.append("http://epay.cmbc.com.cn/ipad/auto_generate/notify.html?");
		// epayNotifyUrlBuilder.append("partner_id=").append(partner_id);
		// epayNotifyUrlBuilder.append("&notify_id=").append(request.getParameter("notify_id"));
		// String epayNotifyURL = epayNotifyUrlBuilder.toString();

		// **********************************************************************************
		// 获取支付ATN返回结果，true是正确的订单信息，false 是无效的
		// String responseTxt = CheckURL.check(epayNotifyURL);

		String out_trade_no = request.getParameter("out_trade_no");
		String pay_order_no = request.getParameter("pay_order_no");

		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();

		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		String mysign = com.epay.util.SignatureHelper.sign(params, privateKey);

		if (logger.isInfoEnabled()) {
			logger.info("QueryString = {}", request.getQueryString());
			logger.info("mysign = {}", mysign);
			logger.info("request.getParameter(\"sign\") = {}", request.getParameter("sign"));
			logger.info("request.getParameter(\"trade_status\") = {}", request.getParameter("trade_status"));
		}

		if (mysign.equals(request.getParameter("sign"))) {
			/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
			logger.info("______________________________________________out_trade_no = " + out_trade_no);
			// 查询订单信息
			PshowOrder entity = new PshowOrder();
			entity.setTrade_index(out_trade_no);

			List<PshowOrder> orderList = getFacade().getPshowOrderService().getPshowOrderList(entity);

			if (null == orderList || orderList.size() == 0) {
				response.getWriter().println(fail);
				logger.info(" trade_status = {}", "ordersList is null");
				return null;
			}

			if ("TRADE_CREATED".equals(request.getParameter("trade_status"))) {
				// 等待买家付款
				logger.info(" trade_status = {}", "TRADE_CREATED");
			} else if ("TRADE_PAYED".equals(request.getParameter("trade_status"))) {
				logger.info(" trade_status = {}", "TRADE_PAYED");

				// 更新订单状态
				entity.setPay_way(5); // 2: 支付宝 ；3:银联；5: 民生 epay
				entity.setTrade_no(pay_order_no); // 交易单号赋值

				// 如果订单状态为已预订更新订单状态
				PshowOrder o = orderList.get(0);
				if (o.getState() != null && o.getState().intValue() < 10 && o.getState().intValue() != -10) {
					entity.setState(5); // 更改状态
				}
				entity.setPay_date(new Date()); // 支付时间

				// 团购商品，支付成功，发送短信
				PshowOrdeDetails pd = new PshowOrdeDetails();
				pd.setOrder_id(o.getId());
				List<PshowOrdeDetails> pdList = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(
				        pd);
				String tuan = "1";
				if (pdList != null && pdList.size() > 0) {
					for (PshowOrdeDetails pshowOrdeDetails : pdList) {
						KonkaBcompPd kp = new KonkaBcompPd();
						kp.setId(pshowOrdeDetails.getPd_id());
						kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
						if (kp != null && kp.getProd_type().intValue() == 8) {
							tuan = "2";
						}
					}
				}
				entity.getMap().put("tuan", tuan);

				super.getFacade().getPshowOrderService().modifyPshowOrderAndSaleNum(entity);

				// 支付成功发送短信

				// 保存购买记录
				try {
					String price = request.getParameter("amount");
					EcOrderPay ecOrderPay = new EcOrderPay();
					ecOrderPay.setPay_way(new Integer(5));
					if (price != null && !"".equals(price)) {
						ecOrderPay.setPrice(new BigDecimal(price));
					}
					ecOrderPay.setTrade_index(out_trade_no);
					ecOrderPay.setTrade_no(pay_order_no);
					super.getFacade().getEcOrderPayService().createEcOrderPay(ecOrderPay);
				} catch (Exception e) {
				}

				response.getWriter().println(success);
			} else if ("TRADE_FINISHED".equals(request.getParameter("trade_status"))) {
				// 交易成功结束
				logger.info(" trade_status = {}", "TRADE_FINISHED");
				// 更新订单状态
				entity.setPay_way(5); // 2: 支付宝 ；3:银联；5: 民生 epay
				entity.setTrade_no(pay_order_no); // 交易单号赋值

				// 如果订单状态为已预订更新订单状态
				PshowOrder o = orderList.get(0);
				if (o.getState() != null && o.getState().intValue() < 10 && o.getState().intValue() != -10) {
					entity.setState(5); // 更改状态
				}

				entity.setPay_date(new Date()); // 支付时间
				super.getFacade().getPshowOrderService().modifyPshowOrderAndSaleNum(entity);
				// 保存购买记录
				try {
					String price = request.getParameter("total_fee");
					EcOrderPay ecOrderPay = new EcOrderPay();
					ecOrderPay.setPay_way(new Integer(5));
					if (price != null && !"".equals(price)) {
						ecOrderPay.setPrice(new BigDecimal(price));
					}
					ecOrderPay.setTrade_index(out_trade_no);
					ecOrderPay.setTrade_no(pay_order_no);
					super.getFacade().getEcOrderPayService().createEcOrderPay(ecOrderPay);
				} catch (Exception e) {
				}
				response.getWriter().println(success);
			} else if ("TRADE_CLOSED".equals(request.getParameter("trade_status"))) {
				// 交易关闭
				logger.info(" trade_status = {}", "TRADE_CLOSED");
			}

		} else {
			response.getWriter().println(fail);
		}
		return null;
	}
}
