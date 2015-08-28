package com.ebiz.mmt.web.struts.epay;

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

import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.EcOrderPay;
import com.ebiz.mmt.web.struts.BaseAction;
import com.epay.config.EpayConfig;

/**
 * @author TUDP
 * @version 2013-12-10
 * 
 */
public class EcGiftJfReturnAction extends BaseAction {

	private static final String success = "success";

	private static final String fail = "fail";

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
			logger.info("request.getParameter(\"sign\") = {}", request.getParameter("md5_sign"));
			logger.info("is_success = {}", request.getParameter("is_success"));
			logger.info("request.getParameter(\"trade_status\") = {}", request.getParameter("trade_status"));
		}

		if (mysign.equals(request.getParameter("md5_sign"))) {
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
			if ("TRADE_CREATED".equals(request.getParameter("trade_status"))) {// 等待买家付款
				logger.info(" trade_status = {}", "TRADE_CREATED");
			} else if ("T".equals(request.getParameter("is_success"))) {
				logger.info(" is_success = {}", "T");
				if(jfBuy.getState().intValue()==0){
					// 更新订单状态
					EcGiftJfBuy entity =new EcGiftJfBuy(); 
					entity.setId(jfBuy.getId());
					entity.setPay_type(new Integer(5)); // 2: 支付宝 ；3:银联
					entity.setTrade_no(pay_order_no); // 交易单号赋值
					entity.setState(new Integer(1)); 
					entity.setPay_date(new Date()); // 支付时间
					super.getFacade().getEcGiftJfBuyService().modifyEcGiftJfBuyForPay(entity);
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
				}
				renderJavaScript(response, "window.onload=function(){alert('恭喜，订单支付成功！');");
				return null;
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
