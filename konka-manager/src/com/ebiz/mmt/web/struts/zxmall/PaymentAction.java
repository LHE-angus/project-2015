package com.ebiz.mmt.web.struts.zxmall;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alipay.config.AlipayConfig;
import com.alipay.util.Payment;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.epay.config.EpayConfig;
import com.unionpay.b2c.QuickPayConf;
import com.unionpay.b2c.QuickPayUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-0-16
 * @desc 付款和通知Action，涉及到通知URL所以不能写在manager目录下
 */
public class PaymentAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		if (StringUtils.isBlank(trade_index)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Trade information." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("list");
	}

	public ActionForward pay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		String pay_way = (String) dynaBean.get("pay_way");

		if (StringUtils.isBlank(trade_index) || !GenericValidator.isLong(pay_way)) {
			String msg = "Trade index lost.";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 查询订单信息
		PshowOrder orders = new PshowOrder();
		orders.getRow().setCount(2);
		orders.setTrade_index(trade_index);
		List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderList(orders);
		if (null == ordersList || ordersList.size() == 0) {
			String msg = "Order is lost.";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		orders = ordersList.get(0);
		if (orders.getState() >= 1) {
			renderJavaScript(response, "alert('您所要支付的订单已支付订单，请您查看并确认，避免重复付款！');window.close();");
			return null;
		}

		if (StringUtils.equals("3", pay_way)) { // 银联
			this.unionpayB2c(orders, request, response);
		} else if (StringUtils.equals("2", pay_way)) {// 支付宝
			this.alipay(orders, request, response);
		} else if (StringUtils.equals("5", pay_way)) {// 支付宝
			this.epay(orders, request, response);
		}
		return null;
	}

	/**
	 * @author Ren,zhong
	 * @desc 新的银联支付接口B2C，支持无卡支付，信用卡支付，和网银支付
	 */
	private ActionForward unionpayB2c(PshowOrder pshowOrder, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		Map<String, String> map = getOrdersInfo(pshowOrder); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request);

		String out_trade_no = pshowOrder.getTrade_index(); // 商户订单号
		String pdUrl = ctx + "/zxmall/Payment.do?method=view&trade_index=" + pshowOrder.getTrade_index();
		; // 商品展示页面，跳转到订单详细页面

		String pdName = "productId(" + map.get("title") + ")";
		logger.info("_____________________________pdName=" + pdName);

		// 前台回调商户URL
		String merFrontEndUrl = ctx + "/unionpay/EcOrderNotify.do?method=unionpayB2cReceiveNotice";

		// 后台回调商户URL
		String merBackEndUrl = ctx + "/unionpay/EcOrderNotify.do?method=unionpayB2cReceiveNoticeForHt";

		String pdCount = map.get("pdCount");
		String commodityUnitPrice = "0"; // 商品单价 单位：分

		String orderAmount = String.valueOf(pshowOrder.getTotal_price().multiply(new BigDecimal(100)).intValue());// 订单金额，单位：分
		if (pshowOrder.getPay_price() != null) {
			orderAmount = String.valueOf(pshowOrder.getPay_price().multiply(new BigDecimal(100)).intValue());// 订单金额，单位：分
		}
		// 测试价格，以后注释
		orderAmount = "1";

		logger.info("_______________________orderAmount = " + orderAmount);
		String transferFee = "0";// 运费， 单位：分
		logger.warn("====merFrontEndUrl:{}", merFrontEndUrl);
		logger.warn("====merBackEndUrl:{}", merBackEndUrl);

		// 商户需要组装如下对象的数据
		String[] valueVo = new String[] { QuickPayConf.version,// 协议版本
		        QuickPayConf.charset,// 字符编码
		        "01",// 交易类型 01:消费 31:消费撤销 02:预授权32:预授权撤销03:预授权完成33:预授权完成撤销04:退货
		        "",// 原始交易流水号
		        QuickPayConf.merCode,// 商户代码
		        QuickPayConf.merName,// 商户简称
		        "",// 收单机构代码（仅收单机构接入需要填写）
		        "",// 商户类别（收单机构接入需要填写）
		        pdUrl,// 商品URL
		        pdName,// 商品名称
		        commodityUnitPrice,// 商品单价 单位：分
		        pdCount,// 商品数量
		        "0",// 折扣 单位：分
		        transferFee,// 运费 单位：分
		        out_trade_no,// 订单号（需要商户自己生成）
		        orderAmount,// 交易金额 单位：分
		        "156",// 交易币种
		        DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"),// 交易时间
		        super.getIpAddr(request),// 用户IP
		        "",// 用户真实姓名
		        "",// 默认支付方式
		        "",// 默认银行编号
		        // "120000",// 交易超时时间
		        "1200000",// 交易超时时间
		        merFrontEndUrl,// 前台回调商户URL
		        merBackEndUrl,// 后台回调商户URL
		        ""// 商户保留域
		};

		/*
		 * 说明：以下代码直接返回跳转到银联在线支付页面字符串 new QuickPayUtils().createPayHtml(valueVo)
		 */
		String html = new QuickPayUtils().createPayHtml(valueVo);// 跳转到银联页面支付

		/*
		 * 说明：以下代码直接返回跳转到银行支付页面字符串
		 * 目前:支持工行(ICBC)，农行(ABC)，中行(BOC)，建行(CCB)，招行(CMB)，广发(GDB)，浦发(SPDB) new
		 * QuickPayUtils().createPayHtml(valueVo, "CCB")
		 */
		// String html = new QuickPayUtils().createPayHtml(valueVo,
		// "CCB");//直接跳转到网银页面支付
		response.setContentType("text/html;charset=" + QuickPayConf.charset);
		response.setCharacterEncoding(QuickPayConf.charset);
		try {
			response.getWriter().write(html);
		} catch (IOException e) {

		}
		response.setStatus(HttpServletResponse.SC_OK);

		return null;
	}

	private ActionForward alipay(PshowOrder pshowOrder, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		Map<String, String> map = getOrdersInfo(pshowOrder); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request); // 请求的地址如：http://www.mymyty.com

		String seller_email = AlipayConfig.sellerEmail; // 卖家支付宝帐户,例如：gwl25@126.com

		String body = map.get("body"); // 商品描述，推荐格式：商品名称（订单编号：订单编号）
		String subject = map.get("title"); // 商品名称

		String out_trade_no = pshowOrder.getTrade_index();// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）

		String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; // 支付接口（不可以修改）
		String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
		String sign_type = "MD5";// 文件加密机制（不可以修改）
		String input_charset = "utf-8"; // （不可以修改）
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String key = AlipayConfig.key; // 支付宝安全校验码(账户内提取)

		String total_fee = pshowOrder.getTotal_price().toString(); // 订单总价
		if (pshowOrder.getPay_price() != null) {
			total_fee = pshowOrder.getPay_price().toString();
		}
		// 测试价格，以后注释
		// total_fee = "0.01";
		logger.info("_____________________________________total_fee = " + total_fee);
		String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）

		String show_url = ctx + "/zxmall/Payment.do?method=view&trade_index=" + pshowOrder.getTrade_index();// 商品展示的RUL

		String notify_url = ctx + "/alipay/EcOrderNotify.do"; // 通知接收URL(本地测试时，服务器返回无法测试)

		String return_url = ctx + "/alipay/AlipayReturn.do"; // 支付完成后跳转返回的网址URL
		// 注意以上两个地址 要用 http://格式的完整路径
		String paymethod = "directPay";// 赋值:bankPay(网银);cartoon(卡通);directPay(余额);

		String ItemUrl = Payment.createURL(paygateway, service, sign_type, out_trade_no, input_charset, partner, key,
		        show_url, body, total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod,
		        null, null);
		logger.info("______________________________________show_url = " + show_url);
		logger.info("______________________________________notify_url = " + notify_url);

		if (logger.isInfoEnabled()) {
			logger.info("AlipayPayAction.unspecified sendRedirectUrl:{}", ItemUrl);
		}
		response.sendRedirect(ItemUrl);
		return null;
	}

	private ActionForward epay(PshowOrder pshowOrder, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		Map<String, String> map = getOrdersInfo(pshowOrder); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request); // 请求的地址如：http://www.mymyty.com

		String seller_email = EpayConfig.sellerEmail; // 卖家支付帐户,例如：gwl25@126.com

		String body = map.get("body").trim(); // 商品描述，推荐格式：商品名称（订单编号：订单编号）
		String subject = map.get("title").trim(); // 商品名称

		// 民生银行参数有长度限制，85个汉字，截取
		if (body.length() > 85) {
			body = body.substring(0, 80);
		}
		if (subject.length() > 85) {
			subject = subject.substring(0, 80);
		}

		String out_trade_no = pshowOrder.getTrade_index();// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）

		String paygateway = "https://epay.cmbc.com.cn/ipad/service.html?"; // 支付接口（不可以修改）
		String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
		String sign_type = "MD5";// 文件加密机制（不可以修改）
		String input_charset = "utf-8"; // （不可以修改）
		String partner_id = EpayConfig.partnerID; // 支付合作伙伴id (账户内提取)
		String key = EpayConfig.key; // 支付安全校验码(账户内提取)

		String amount = pshowOrder.getTotal_price().toString(); // 订单总价
		if (pshowOrder.getPay_price() != null) {
			amount = pshowOrder.getPay_price().toString();
		}
		// 测试价格，以后注释
		// amount = "0.01";
		logger.info("_____________________________________amount = " + amount);

		String show_url = ctx + "/zxmall/Payment.do?method=view&trade_index=" + pshowOrder.getTrade_index();// 商品展示的RUL

		String notify_url = ctx + "/epay/EcOrderNotify.do"; // 通知接收URL(本地测试时，服务器返回无法测试)

		String return_url = ctx + "/epay/Return.do"; // 支付完成后跳转返回的网址URL
		// 注意以上两个地址 要用 http://格式的完整路径
		String payMethod = "bankPay";// null;//
									 // 赋值:bankPay(网银);cartoon(卡通);directPay(余额);

		String ItemUrl = com.epay.util.Payment.createURL(paygateway, service, sign_type, out_trade_no, input_charset,
		        partner_id, key, show_url, body, amount, seller_email, subject, notify_url, return_url, payMethod,
		        null, null);
		logger.info("______________________________________show_url = " + show_url);
		logger.info("______________________________________notify_url = " + notify_url);

		if (logger.isInfoEnabled()) {
			logger.info("AlipayPayAction.unspecified sendRedirectUrl:{}", ItemUrl);
		}
		response.sendRedirect(ItemUrl);
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		if (StringUtils.isBlank(trade_index)) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		entity.setTrade_index(trade_index);
		List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderIncludeDetailsList(entity);
		if (null == ordersList || ordersList.size() == 0) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		}

		// 循环处理订单明细
		for (PshowOrder orders : ordersList) {
			orders.getMap().put("p_full_name", super.getPIndexName(orders.getBuyer_p_index().toString(), ""));
			PshowOrdeDetails pod = new PshowOrdeDetails();
			pod.setOrder_id(orders.getId());
			List<PshowOrdeDetails> podList = super.getFacade().getPshowOrdeDetailsService()
			        .getPshowOrdeDetailsList(pod);
			for (PshowOrdeDetails pshowOrdeDetails : podList) {
				EcBindingPdOrdeDetails epd = new EcBindingPdOrdeDetails();
				epd.setDetails_id(pshowOrdeDetails.getBill_item_id());
				List<EcBindingPdOrdeDetails> epdList = super.getFacade().getEcBindingPdOrdeDetailsService()
				        .getEcBindingPdOrdeDetailsList(epd);
				pshowOrdeDetails.setEcBindingPdOrdeDetailsList(epdList);
			}
			orders.setPshowOrdeDetailsList(podList);
		}

		request.setAttribute("pshowOrderList", ordersList);
		return mapping.findForward("view");
	}

	public ActionForward checkOrderStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		PshowOrder entity = new PshowOrder();
		entity.setTrade_index(trade_index);
		List<PshowOrder> orderList = getFacade().getPshowOrderService().getPshowOrderList(entity);
		if (null == orderList || orderList.size() == 0) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		} else {
			for (PshowOrder od : orderList) {
				if (od.getState() == 0) {
					renderJavaScript(response, "alert('订单支付失败！');history.back();");
					return null;
				}
			}
		}

		renderJavaScript(response, "window.onload=function(){alert('恭喜，订单支付成功！');location.href = '"
		        + super.getCtxPath(request) + "/zxmall/Payment.do?method=view&trade_index=" + trade_index + "';}");
		return null;
	}

	public Map<String, String> getOrdersInfo(PshowOrder pshowOrder) {
		Map<String, String> map = new HashMap<String, String>();

		Long pdCount = 0L;

		List<String> title = new ArrayList<String>();
		List<String> body = new ArrayList<String>();
		PshowOrdeDetails ordersProduct = new PshowOrdeDetails();
		ordersProduct.setOrder_id(pshowOrder.getId());
		List<PshowOrdeDetails> ordersProductList = getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(
		        ordersProduct);
		if (null != ordersProductList && ordersProductList.size() > 0) {
			for (int i = 0; i < ordersProductList.size(); i++) {
				PshowOrdeDetails op = ordersProductList.get(i);
				if (i < 2) {
					title.add(op.getPd_name());
				} else {
					title.add("...");
					break;
				}
			}

			for (int i = 0; i < ordersProductList.size(); i++) {
				PshowOrdeDetails op = ordersProductList.get(i);
				pdCount =pdCount.longValue()+ op.getNum().longValue();
				body.add(op.getPd_name());
			}
		}

		map.put("title", StringUtils.join(title, ","));
		map.put("body", StringUtils.join(body, ","));
		map.put("pdCount", pdCount.toString());

		return map;
	}
}
