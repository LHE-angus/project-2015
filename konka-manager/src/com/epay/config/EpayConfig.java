package com.epay.config;

/**
 * @作者：涂登攀
 * 民生银行 e支付
 */
public class EpayConfig {

	// partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
	public static final String partnerID = "1002201312182521";

	public static final String key = "d7b5a762652b518bf27cc92fb0489f4f";

	// 卖家支付宝帐户
	public static final String sellerEmail = "ghzx@konka.com";

	// 分润支付宝帐户
	public static final String royaltyEmail = "ghzx@konka.com";

	// 页面编码
	public static final String CharSet = "utf-8";

	// 第三方提成底线,最低限额
	public static final String royalty_money = "0.01";

}
