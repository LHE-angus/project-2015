package com.ebiz.mmt.web.struts.alipay;

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

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.ebiz.mmt.domain.EcOrderPay;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-16
 * 
 */
public class WapReturnAction extends BaseAction {

	private static final String success = "success";

	private static final String fail = "fail";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String privateKey = AlipayConfig.key; // 支付宝安全校验码(账户内提取)
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		String trade_no = request.getParameter("trade_no");
		String is_success = request.getParameter("is_success");
		//String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		//String result = new String(request.getParameter("result").getBytes("ISO-8859-1"),"UTF-8");
		log.info(is_success);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verifyReturn(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			logger.info("______________________________________________out_trade_no = " + out_trade_no);
			if("T".equals(is_success)){
			// 查询订单信息
			PshowOrder entity = new PshowOrder();
			entity.setTrade_index(out_trade_no); 
			List<PshowOrder> orderList = getFacade().getPshowOrderService().getPshowOrderList(entity);

			if (null == orderList || orderList.size() == 0) {
				response.getWriter().println(fail);
				logger.info(" trade_status = {}", "ordersList is null");
				return null;
			}
			entity.setPay_way(2); // 2: 支付宝 ；3:银联
			entity.setTrade_no(trade_no); // 交易单号赋值

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

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序

			
			//该页面可做页面美工编辑 
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				renderJavaScript(response, "window.onload=function(){alert('恭喜，订单支付成功！');window.close();}");
				return null;
			}else{
				renderJavaScript(response, "window.onload=function(){alert('订单支付失败！');window.close();}");
				return null;
			}
		}else{
			//该页面可做页面美工编辑 
			response.getWriter().println("验证失败");
		} 
		
		return null;
	}
}
