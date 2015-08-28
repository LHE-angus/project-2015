package com.ebiz.mmt.web.struts.alipay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alipay.config.AlipayConfig;
import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.EcOrderPay;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-16
 * 
 */
public class EcGiftJfNotifyAction extends BaseAction {

	private static final String success = "success";

	private static final String fail = "fail";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String privateKey = AlipayConfig.key; // 支付宝安全校验码(账户内提取)
		// **********************************************************************************
		// 如果您服务器不支持https交互，可以使用http的验证查询地址
		/*
		 * 注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接，另外检查本地端口，
		 * 请挡开80或者443端口
		 */
		// String alipayNotifyURL =
		// "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"

		StringBuilder alipayNotifyUrlBuilder = new StringBuilder();
		alipayNotifyUrlBuilder.append("http://notify.alipay.com/trade/notify_query.do?");
		alipayNotifyUrlBuilder.append("partner=").append(partner);
		alipayNotifyUrlBuilder.append("&notify_id=").append(request.getParameter("notify_id"));
		String alipayNotifyURL = alipayNotifyUrlBuilder.toString();

		// **********************************************************************************
		// 获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		// String responseTxt = CheckURL.check(alipayNotifyURL);

		String out_trade_no = request.getParameter("out_trade_no");
		String trade_no = request.getParameter("trade_no");

		Map<String, String> params = new HashMap<String, String>();
		// 获得POST 过来参数设置到新的params中

		// params.put("param", keySeq);

		Map<String, String[]> requestParams = request.getParameterMap();

		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
			// 如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");

			params.put(name, valueStr);
		}

		String mysign = com.alipay.util.SignatureHelper.sign(params, privateKey);

		if (logger.isInfoEnabled()) {
			logger.info("alipayNotifyURL = {}", alipayNotifyURL);
			logger.info("mysign = {}", mysign);
			logger.info("request.getParameter(\"sign\") = {}", request.getParameter("sign"));
			// logger.info("responseTxt = {}", responseTxt);
			logger.info("request.getParameter(\"trade_status\") = {}", request.getParameter("trade_status"));
		}

		if (mysign.equals(request.getParameter("sign"))) {
			/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
			logger.info("______________________________________________out_trade_no = " + out_trade_no);
			// 查询订单信息
			EcGiftJfBuy jfBuy =new EcGiftJfBuy();
			jfBuy.setTrade_index(out_trade_no);
			jfBuy=super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(jfBuy);
			
			if (null == jfBuy || jfBuy.getId()==null) {
				response.getWriter().println(fail);
				logger.info(" trade_status = {}", "ordersList is null");
				return null;
			}

			if ("WAIT_BUYER_PAY".equals(request.getParameter("trade_status"))) {
				// 等待买家付款
				logger.info(" trade_status = {}", "WAIT_BUYER_PAY");
			} else if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
				logger.info(" trade_status = {}", "TRADE_SUCCESS");
				if(jfBuy.getState().intValue()==0){
					// 更新订单状态
					EcGiftJfBuy entity =new EcGiftJfBuy(); 
					entity.setId(jfBuy.getId());
					entity.setPay_type(new Integer(2)); // 2: 支付宝 ；3:银联
					entity.setTrade_no(trade_no); // 交易单号赋值
					entity.setState(new Integer(1)); 
					entity.setPay_date(new Date()); // 支付时间
					super.getFacade().getEcGiftJfBuyService().modifyEcGiftJfBuyForPay(entity);
					// 保存购买记录
					try {
						String price = request.getParameter("total_fee");
						EcOrderPay ecOrderPay = new EcOrderPay();
						ecOrderPay.setPay_way(new Integer(2));
						if (price != null && !"".equals(price)) {
							ecOrderPay.setPrice(new BigDecimal(price));
						}
						ecOrderPay.setTrade_index(out_trade_no);
						ecOrderPay.setTrade_no(trade_no);
						super.getFacade().getEcOrderPayService().createEcOrderPay(ecOrderPay);
					} catch (Exception e) {
					}
				}
				response.getWriter().println(success);

			} else if ("TRADE_FINISHED".equals(request.getParameter("trade_status"))) {// 交易成功结束
				logger.info(" trade_status = {}", "TRADE_FINISHED");
			} else if ("TRADE_CLOSED".equals(request.getParameter("trade_status"))) {// 交易关闭
				logger.info(" trade_status = {}", "TRADE_CLOSED");
			}

		} else {
			response.getWriter().println(fail);
		}
		return null;
	}
}
