package com.epay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epay.config.EpayConfig;

/**
 * 
 */
public class Payment {
	/**
	 * 生成url方法
	 * 
	 * @param paygateway
	 *            网关
	 * @param service
	 *            服务参数
	 * @param sign_type
	 *            签名类型
	 * @param out_trade_no
	 *            外部订单号
	 * @param input_charset
	 *            编码机制
	 * @param partner_id
	 *            合作者ID
	 * @param key
	 *            安全校验码
	 * @param show_url
	 *            商品展示地址
	 * @param body
	 *            商品描述
	 * @param amount
	 *            商品价格
	 * @param seller_email
	 *            卖家账户
	 * @param subject
	 *            商品名称
	 * @param notify_url
	 *            异步返回地址
	 * @param return_url
	 *            同步返回地址
	 * @param payMethod
	 *            支付方式
	 * @param default_bank
	 *            默认银行
	 * @return
	 */
	public static String createUrl(String paygateway, String service,
			String sign_type, String out_trade_no, String input_charset,
			String partner_id, String key, String show_url, String body,
			String amount, String seller_email, String subject,
			String notify_url, String return_url, String payMethod,
			String default_bank, String royalty_type, String royalty_parameters) {// ,
																					// String
																					// it_b_pay

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("service", service);
		params.put("partner_id", partner_id);
		params.put("subject", subject);
		params.put("sign_type", "MD5");
		if (null != body) {
			params.put("body", body);
		}
		params.put("out_trade_no", out_trade_no);
		params.put("amount", amount);
		if (null != show_url) {
			params.put("show_url", show_url);
		}
		params.put("seller_email", seller_email);
		// params.put("it_b_pay", it_b_pay);
		if (null != return_url) {
		params.put("return_url", return_url);
		}
		params.put("notify_url", notify_url);
		if (null != payMethod) {
		params.put("payMethod", payMethod);
		}
		params.put("input_charset", input_charset);

		if (null != default_bank) {
			params.put("default_bank", default_bank);
		}

		if (null != royalty_type) {
			params.put("royalty_type", royalty_type);
		}

		if (null != royalty_parameters) {
			params.put("royalty_parameters", royalty_parameters);
		}

		String prestr = "";

		prestr = prestr + key;
		// //System.out.println("prestr=" + prestr);

		String sign = com.alipay.util.Md5Encrypt.md5(getContent(params, key));

		String parameter = "";
		parameter = parameter + paygateway;

		List<String> keys = new ArrayList<String>(params.keySet());
		for (int i = 0; i < keys.size(); i++) {
			try {
				parameter = parameter
						+ keys.get(i)
						+ "="
						+ URLEncoder.encode((String) params.get(keys.get(i)),
								input_charset) + "&";
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
		}

		parameter = parameter + "sign=" + sign ;

		return parameter;

	}

	/**
	 * @author Liu,Huan
	 */
	public static String createURL(String paygateway, String service,
			String sign_type, String out_trade_no, String input_charset,
			String partner_id, String key, String show_url, String body,
			String amount, String seller_email, String subject,
			String notify_url, String return_url, String payMethod,
			String royalty_type, String royalty_parameters) {// ,
																// String
																// it_b_pay

		return createUrl(paygateway, service, sign_type, out_trade_no,
				input_charset, partner_id, key, show_url, body, amount,
				seller_email, subject, notify_url, return_url, payMethod, null,
				royalty_type, royalty_parameters);
	}

	/**
	 * 功能：将安全校验码和参数排序 参数集合
	 * 
	 * @param params
	 *            安全校验码
	 * @param privateKey
	 */
	@SuppressWarnings("unchecked")
	private static String getContent(Map params, String privateKey) {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) params.get(key);

			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr + privateKey;
	}

	/**
	 * @author Zhao,XingGuo
	 * @param out_trade_no
	 *            流水号
	 * @param amount
	 *            总金额
	 * @param seller_email
	 *            经销商支付宝账号
	 * @param subject
	 *            商品名称
	 * @param notify_url
	 *            通知url
	 * @param return_url
	 *            返回url
	 * @param show_url
	 *            展示商品url
	 * @param money
	 *            打入易商数码公司的钱，数据精度为2位小数
	 * @param money
	 *            为null时表示立即支付
	 * @return a url send to alipay
	 */
	@SuppressWarnings("unchecked")
	public static String createDefaultUrl(String out_trade_no, String amount,
			String seller_email, String subject, String notify_url,
			String return_url, String show_url, String money) {

		Map params = new HashMap();
		params.put("service", "create_direct_pay_by_user");
		params.put("partner_id", EpayConfig.partnerID);
		params.put("subject", subject);
		params.put("out_trade_no", out_trade_no);// 订单号
		params.put("amount", amount);
		if (null != show_url) {
			params.put("show_url", show_url);
		}
		params.put("seller_email", seller_email);
		// params.put("return_url", return_url);
		params.put("notify_url", notify_url);
		// 赋值:bankPay(网银);cartoon(卡通);directPay(余额)
		params.put("payMethod", "directPay");
		params.put("input_charset", "utf-8");

		// add by Zhao,XingGuo 增加分润功能
		if (null != money) {
			params.put("royalty_type", "10");
			params.put("royalty_parameters", EpayConfig.royaltyEmail + "^"
					+ money + "^分账");
		}

		String prestr = "";

		prestr = prestr + EpayConfig.key;
		// //System.out.println("prestr=" + prestr);

		String sign = com.alipay.util.Md5Encrypt.md5(getContent(params,
				EpayConfig.key));

		String parameter = "";
		parameter = parameter + "https://www.alipay.com/cooperate/gateway.do?";

		List keys = new ArrayList(params.keySet());
		for (int i = 0; i < keys.size(); i++) {
			try {
				parameter = parameter
						+ keys.get(i)
						+ "="
						+ URLEncoder.encode((String) params.get(keys.get(i)),
								"utf-8") + "&";
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
		}

		parameter = parameter + "sign=" + sign + "&sign_type=" + "MD5";

		return parameter;

	}
}
