package com.unionpay.b2c;

/**
 * @desc 名称：支付配置类
 * @desc 功能：配置类
 * @desc 类属性：公共类
 * @desc 版本：1.0
 * @desc 日期：2011-03-11
 * @desc 作者：中国银联UPOP团队 021-58581157刘兆强
 * @desc 版权：中国银联
 * @desc 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 */

public class QuickPayConf {

	// 版本号
	public final static String version = "1.0.0";

	// 编码方式
	public final static String charset = "UTF-8";

	// 支付网址,前台交易
	// 银联正式：https://unionpaysecure.com/api/Pay.action
	// 银联测试：https://www.epay.lxdns.com/UpopWeb/api/Pay.action
	public final static String gateWay = "https://unionpaysecure.com/api/Pay.action";

	// 后续交易网址
	// 银联正式：https://unionpaysecure.com/api/BSPay.action
	// 银联测试：https://www.epay.lxdns.com/UpopWeb/api/BSPay.action
	public final static String backStagegateWay = "https://unionpaysecure.com/api/BSPay.action";

	// 查询网址
	// 银联正式：查询地址：https://unionpaysecure.com/api/Query.action
	// 银联测试：查询地址：https://www.epay.lxdns.com/UpopWeb/api/Query.action
	public final static String queryUrl = "https://unionpaysecure.com/api/Query.action";

	// 商户代码
	public final static String merCode = "001340173720061";

	// 商户名称
	public final static String merName = "安徽买卖提";

	// 前台返回通知链接
	public final static String merFrontEndUrl = "";

	// 后台返回通知链接
	public final static String merBackEndUrl = "";

	// 加密方式
	public final static String signType = "MD5";

	// 商城密匙，需要和银联商户网站上配置的一样
	// 银联正式：DFH3KFGHH5K2FK8D8S92XASDCF6GH
	// 银联测试：88888888
	public final static String securityKey = "DFH3KFGHH5K2FK8D8S92XASDCF6GH";

	// 签名
	public final static String signature = "signature";

	public final static String signMethod = "signMethod";

	// 组装消费请求包
	public final static String[] reqVo = new String[] { "version", "charset", "transType", "origQid", "merId",
			"merAbbr", "acqCode", "merCode", "commodityUrl", "commodityName", "commodityUnitPrice",
			"commodityQuantity", "commodityDiscount", "transferFee", "orderNumber", "orderAmount", "orderCurrency",
			"orderTime", "customerIp", "customerName", "defaultPayType", "defaultBankNumber", "transTimeout",
			"frontEndUrl", "backEndUrl", "merReserved" };

	public final static String[] notifyVo = new String[] { "charset", "cupReserved", "exchangeDate", "exchangeRate",
			"merAbbr", "merId", "orderAmount", "orderCurrency", "orderNumber", "qid", "respCode", "respMsg",
			"respTime", "settleAmount", "settleCurrency", "settleDate", "traceNumber", "traceTime", "transType",
			"version" };

	public final static String[] queryVo = new String[] { "version", "charset", "transType", "merId", "orderNumber",
			"orderTime", "merReserved" };

}
