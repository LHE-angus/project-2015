package com.ebiz.mmt.web.struts.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alipay.config.AlipayConfig;
import com.alipay.util.Payment;
import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.epay.config.EpayConfig;

/**
 * @author Tudp
 * @version 2014-11-25
 * @desc 付款和通知Action，涉及到通知URL所以不能写在manager目录下
 */
public class EcGiftJfPayAction extends BaseMemberAction {

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
		EcGiftJfBuy jfBuy =new EcGiftJfBuy();
		jfBuy.setTrade_index(trade_index);
		jfBuy=super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(jfBuy);
		if(jfBuy!=null&&jfBuy.getState().intValue()!=0){
			String msg="";
			if(jfBuy.getState().intValue()==1){
				msg="订单已经支付,请勿重复支付";
			}else{
				msg="订单已经取消";
			}
			super.renderJavaScript(response,  "window.onload=function(){alert('"+msg+"');location.href='" + super.getCtxPath(request) + "/member/EcGiftJfBuy.do';}");
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

		EcGiftJfBuy jfBuy =new EcGiftJfBuy();
		jfBuy.setTrade_index(trade_index);
		jfBuy=super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(jfBuy);
		if(jfBuy!=null&&jfBuy.getState().intValue()!=0){ 
			renderJavaScript(response, "alert('您所要支付的订单已支付订单，请您查看并确认，避免重复付款！');window.close();");
			return null;
		}

	  if (StringUtils.equals("2", pay_way)) {// 支付宝
			this.alipay(jfBuy, request, response);
		} else if (StringUtils.equals("5", pay_way)) {// 支付宝
			this.epay(jfBuy, request, response);
		}
		return null;
	} 

	private ActionForward alipay(EcGiftJfBuy jfBuy, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		Map<String, String> map = getOrdersInfo(jfBuy); // 获取订单商品相关信息

		String ctx = super.getCtxPath(request); // 请求的地址如：http://www.mymyty.com

		String seller_email = AlipayConfig.sellerEmail; // 卖家支付宝帐户,例如：gwl25@126.com

		String body = map.get("body"); // 商品描述，推荐格式：商品名称（订单编号：订单编号）
		String subject = map.get("title"); // 商品名称

		String out_trade_no = jfBuy.getTrade_index();// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）

		String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; // 支付接口（不可以修改）
		String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
		String sign_type = "MD5";// 文件加密机制（不可以修改）
		String input_charset = "utf-8"; // （不可以修改）
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String key = AlipayConfig.key; // 支付宝安全校验码(账户内提取)

		String total_fee = jfBuy.getPrice().toString(); // 订单总价
		 
		// total_fee = "0.01";
		logger.info("_____________________________________total_fee = " + total_fee);
		String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）

		String show_url = ctx + "/member/EcGiftJfPay.do?method=view&trade_index=" + jfBuy.getTrade_index();// 商品展示的RUL 
		String notify_url = ctx + "/alipay/EcGiftJfNotify.do"; // 通知接收URL(本地测试时，服务器返回无法测试)
		String return_url = ctx + "/alipay/EcGiftJfReturn.do"; // 支付完成后跳转返回的网址URL
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

	private ActionForward epay(EcGiftJfBuy jfBuy, HttpServletRequest request, HttpServletResponse response)
	        throws Exception {
		Map<String, String> map = getOrdersInfo(jfBuy); // 获取订单商品相关信息

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
		String out_trade_no = jfBuy.getTrade_index();// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
		String paygateway = "https://epay.cmbc.com.cn/ipad/service.html?"; // 支付接口（不可以修改）
		String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
		String sign_type = "MD5";// 文件加密机制（不可以修改）
		String input_charset = "utf-8"; // （不可以修改）
		String partner_id = EpayConfig.partnerID; // 支付合作伙伴id (账户内提取)
		String key = EpayConfig.key; // 支付安全校验码(账户内提取)

		String amount = jfBuy.getPrice().toString(); // 订单总价		 
		// 测试价格，以后注释
		// amount = "0.01";
		logger.info("_____________________________________amount = " + amount);
		String show_url = ctx + "/member/EcGiftJfPay.do?method=view&trade_index=" + jfBuy.getTrade_index();// 商品展示的RUL
		String notify_url = ctx + "/epay/EcGiftJfNotify.do"; // 通知接收URL(本地测试时，服务器返回无法测试)
		String return_url = ctx + "/epay/EcGiftJfReturn.do"; // 支付完成后跳转返回的网址URL
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
		EcGiftJfBuy jfBuy =new EcGiftJfBuy();
		jfBuy.setTrade_index(trade_index);
		jfBuy=super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(jfBuy);
		if (null == jfBuy ) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		} 
		request.setAttribute("jfBuy", jfBuy);
		return mapping.findForward("view");
	}

	public ActionForward checkOrderStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		EcGiftJfBuy jfBuy =new EcGiftJfBuy();
		jfBuy.setTrade_index(trade_index);
		jfBuy=super.getFacade().getEcGiftJfBuyService().getEcGiftJfBuy(jfBuy);
		if (null == jfBuy ) {
			renderJavaScript(response, "alert('查无此订单！');history.back();");
			return null;
		} else {
			if (jfBuy.getState() == 0) {
				renderJavaScript(response, "alert('订单支付失败！');history.back();");
				return null;
			} 
		}
		renderJavaScript(response, "window.onload=function(){alert('恭喜，订单支付成功！');location.href = '"
		        + super.getCtxPath(request) + "/member/EcGiftJfPay.do?method=view&trade_index=" + trade_index + "';}");
		return null;
	}

	public Map<String, String> getOrdersInfo(EcGiftJfBuy jfBuy) {
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("title", jfBuy.getTitle());
		map.put("body", jfBuy.getTitle()+"(积分："+jfBuy.getIntegral()+")");
		map.put("pdCount", "1"); 
		return map;
	}
}
