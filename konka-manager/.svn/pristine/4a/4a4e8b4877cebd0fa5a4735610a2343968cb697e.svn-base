package com.ebiz.mmt.web.struts;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alipay.config.AlipayConfig;
import com.alipay.util.Payment;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.unionpay.b2c.QuickPayConf;
import com.unionpay.b2c.QuickPayUtils;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-14
 * @desc 付款和通知Action，涉及到通知URL所以不能写在manager目录下
 */
public class PaymentAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	// 合并订单支付
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == ui || StringUtils.isBlank(trade_index)) {
			renderJavaScript(response, "alert('" + super.getMessage(request, "need.login") +"');location.href = '" + super.getCtxPath(request) + "/customer';");
			return null;
		}

		BigDecimal totMoney = new BigDecimal(0);
		if (StringUtils.isNotBlank(trade_index)) {
			PshowOrder orders = new PshowOrder();
			orders.setTrade_index(trade_index);
			List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderList(orders);
			if (null == ordersList || ordersList.size() == 0) {
				renderJavaScript(response, "alert('查无此订单！');history.back();");
				return null;
			}
			if (null != ordersList && ordersList.size() > 0) {
				for (PshowOrder od : ordersList) {
					BigDecimal orderMoney = od.getTotal_price();
					if (null == orderMoney) {
						renderJavaScript(response, "alert('数据丢失！');history.back();");
						return null;
					}
					totMoney = totMoney.add(orderMoney);
					request.setAttribute("ordersNum", ordersList.size());
				}
			}
		}

		request.setAttribute("trade_index", trade_index);
		request.setAttribute("total_money", totMoney.toString());

		return mapping.findForward("list");
	}

	public ActionForward pay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		String pay_way = (String) dynaBean.get("pay_way");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			renderJavaScript(response, "alert('session丢失！');history.back();");
			return null;
		}

		if (StringUtils.isBlank(pay_way)) {
			renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		PshowOrder orders = new PshowOrder();
		if (StringUtils.isNotBlank(trade_index)) {
			orders.setTrade_index(trade_index);
		}
		List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderList(orders);
		if (null == ordersList || ordersList.size() == 0) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		} else {
			for (PshowOrder od : ordersList) {
				if (od.getState() >= 1) {
					renderJavaScript(response, "alert('您所要支付的订单中存在已支付订单，请您查看并确认，避免重复付款！');window.close();");
					return null;
				}
			}
		}

		if (StringUtils.equals("3", pay_way)) { // 银联
			this.unionpayB2c(trade_index, request, response);
		} else if (StringUtils.equals("2", pay_way)) {// 支付宝
			this.alipay(trade_index, request, response);
		}
		return null;
	}

	/**
	 * @author Ren,zhong
	 * @desc 新的银联支付接口B2C，支持无卡支付，信用卡支付，和网银支付
	 */
	private ActionForward unionpayB2c(String trade_index, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			super.renderJavaScript(response, "alert('会话过期！');history.back();");
			return null;
		}

		Map<String, String> map = getOrdersInfo(trade_index); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request);

		String out_trade_no = ""; // 商户订单号
		String pdUrl = ""; // 商品展示页面，跳转到订单详细页面
		if (StringUtils.isNotBlank(trade_index)) {
			out_trade_no = trade_index;
			pdUrl = ctx + "/Payment.do?trade_index=" + trade_index;
		}

		String pdName = "productId(" + map.get("pdName") + ")";
		logger.info("_____________________________pdName=" + pdName);
		logger.info("_____________________________pdId=" + map.get("pdId"));

		// 前台回调商户URL
		String merFrontEndUrl = ctx + "/unionpay/OrdersPayBindNotify.do?method=unionpayB2cReceiveNotice";

		// 后台回调商户URL
		String merBackEndUrl = ctx + "/unionpay/OrdersPayBindNotify.do?method=unionpayB2cReceiveNoticeForHt";

		String pdCount = map.get("pdCount");
		String commodityUnitPrice = "0"; // 商品单价 单位：分

		String orderAmount = String.valueOf((new BigDecimal(map.get("totalMoney")).multiply(new BigDecimal(100))).intValue());// 订单金额，单位：分
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
		 * 说明：以下代码直接返回跳转到银行支付页面字符串 目前:支持工行(ICBC)，农行(ABC)，中行(BOC)，建行(CCB)，招行(CMB)，广发(GDB)，浦发(SPDB) new
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

	private ActionForward alipay(String trade_index, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == ui) {
			super.renderJavaScript(response, "alert('会话过期！');history.back();");
			return null;
		}

		Map<String, String> map = getOrdersInfo(trade_index); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request); // 请求的地址如：http://www.mymyty.com

		String seller_email = AlipayConfig.sellerEmail; // 卖家支付宝帐户,例如：gwl25@126.com

		String body = map.get("pdName"); // 商品描述，推荐格式：商品名称（订单编号：订单编号）
		String subject = map.get("pdName"); // 商品名称

		String out_trade_no = "";
		if (StringUtils.isNotBlank(trade_index)) {
			out_trade_no = trade_index;// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
		}

		String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; // 支付接口（不可以修改）
		String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
		String sign_type = "MD5";// 文件加密机制（不可以修改）
		String input_charset = "utf-8"; // （不可以修改）
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String key = AlipayConfig.key; // 支付宝安全校验码(账户内提取)

		String total_fee = String.valueOf(new BigDecimal(map.get("totalMoney"))); // 订单总价
		// 测试价格，以后注释
		total_fee = "0.01";
		logger.info("_____________________________________total_fee = " + total_fee);
		String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）

		String show_url = ctx + "/Payment.do?method=view&trade_index=" + trade_index;// 商品展示的RUL

		String notify_url = ctx + "/alipay/PShowOrderNotify.do"; // 通知接收URL(本地测试时，服务器返回无法测试)

		String return_url = ctx + "/alipay/AlipayReturn.do"; // 支付完成后跳转返回的网址URL
		// 注意以上两个地址 要用 http://格式的完整路径
		String paymethod = "directPay";// 赋值:bankPay(网银);cartoon(卡通);directPay(余额);

		String ItemUrl = Payment.createURL(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body,
				total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod, null, null);
		logger.info("______________________________________show_url = " + show_url);
		logger.info("______________________________________notify_url = " + notify_url);

		if (logger.isInfoEnabled()) {
			logger.info("AlipayPayAction.unspecified sendRedirectUrl:{}", ItemUrl);
		}
		response.sendRedirect(ItemUrl);
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		
		if(StringUtils.isBlank(trade_index)){
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

		BigDecimal totalMoney = new BigDecimal(0);
		for (PshowOrder orders : ordersList) {
			totalMoney = totalMoney.add(orders.getTotal_price());
		}

		// 购买客户信息
		request.setAttribute("link_p_index_name", super.getPIndexName(ordersList.get(0).getBuyer_p_index().toString(), "&nbsp;"));
		request.setAttribute("real_name", ordersList.get(0).getBuyer_name());
		request.setAttribute("link_phone", ordersList.get(0).getBuyer_mp());
		request.setAttribute("link_tel", ordersList.get(0).getBuyer_tel());
		request.setAttribute("state", ordersList.get(0).getState());
		
		request.setAttribute("ordersList", ordersList);
		request.setAttribute("totalMoney", totalMoney);
		return mapping.findForward("view");
	}

	public ActionForward checkOrderStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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

		renderJavaScript(response, "window.onload=function(){alert('恭喜，订单支付成功！');location.href = '" + super.getCtxPath(request) + "/Payment.do?method=view&trade_index=" + trade_index + "';}");
		return null;
	}

	public Map<String, String> getOrdersInfo(String trade_index) {
		Map<String, String> map = new HashMap<String, String>();

		BigDecimal total_money = new BigDecimal(0);
		String pdName = "";
		Long pdCount = 0L;

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(trade_index)) {
			entity.setTrade_index(trade_index);
		}
		List<PshowOrder> ordersList = getFacade().getPshowOrderService().getPshowOrderList(entity);
		if (null != ordersList && ordersList.size() > 0) {
			for (PshowOrder orders : ordersList) {
				total_money = total_money.add(orders.getTotal_price());

				List<String> arrsPdNames = new ArrayList<String>();
				PshowOrdeDetails ordersProduct = new PshowOrdeDetails();
				ordersProduct.setOrder_id(orders.getId());
				List<PshowOrdeDetails> ordersProductList = getFacade().getPshowOrdeDetailsService().getPshowOrdeDetailsList(ordersProduct);
				if (null != ordersProductList && ordersProductList.size() > 0) {
					for (int i = 0; i < ordersProductList.size(); i++) {
						PshowOrdeDetails op = ordersProductList.get(i);

						pdCount += op.getNum();

						if (i < 2) {
							arrsPdNames.add(op.getPd_name());
						} else {
							arrsPdNames.add("...");
							break;
						}
					}
				}
				if (arrsPdNames.size() > 0) {
					pdName = StringUtils.join(arrsPdNames, ",");
				}
			}
		}

		map.put("pdName", pdName);
		map.put("totalMoney", total_money.toString());
		map.put("pdCount", pdCount.toString());

		return map;
	}
}
