package com.ebiz.mmt.web.struts.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGift;
import com.ebiz.mmt.domain.EcGiftJfBuy;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author TUDP
 * @version 2014-11-25
 */
public class EcGiftJfAction extends BaseMemberAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
		if(ecUser.getUser_type().intValue()==1){
			String touch = (String)session.getAttribute("touch");
			if(!"1".equals(touch)){
				String ctx = super.getCtxPath(request);
				String url=ctx+"/member/center/Skip.do";
				response.sendRedirect(url);
				return null;  
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		//账户中心用户登录验证
		if(ecUser.getUser_type().intValue()==1){
			String touch = (String)session.getAttribute("touch");
			if(!"1".equals(touch)){
				String ctx = super.getCtxPath(request);
				String url=ctx+"/member/center/Skip.do";
				response.sendRedirect(url);
				return null; 
			}
		} 
		Long surplus_integral = 0L;// 剩余积分
		surplus_integral = getIntegralByUserId(ecUser.getId());// 公用的剩余积分查询方法
		request.setAttribute("surplus_integral", surplus_integral);

		EcGift e = new EcGift();
		e.setOwn_sys(ecUser.getUser_type());// 所属系统：1-工卡，2-触网，3-会员		
		e.setState(1);// 状态：0-已停用 1-正常 -1-已删除		
		e.getMap().put("is_upself", "true");// 只查询上架积分商品
		e.getMap().put("map.order_by_sale_num_desc", "true");
		e.getRow().setCount(10);
		List<EcGift> ec_gift_list_top_10 = super.getFacade().getEcGiftService().getEcGiftList(e);
		request.setAttribute("ec_gift_list_top_10", ec_gift_list_top_10);
		return mapping.findForward("list");
	} 
	
	public ActionForward createOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)) {
			String msg = "请勿重复提交订单";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String integral = (String) dynaBean.get("integral"); 
		// 从Session中取用户并判断
		EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
		if (null == ecUser) {
			String msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/login.do';}");
			return null;
		}
		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")+ StringUtils.substring(String.valueOf(now.getTime()), 7);
		BigDecimal integral_int =new BigDecimal("0.0");
		if (!StringUtils.isBlank(integral) && GenericValidator.isLong(integral)) {
			integral_int=new BigDecimal(integral);
		}
		String msg="";
		if(integral_int.intValue()<100){
			msg="最少充值100积分";
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/EcGiftJf.do';}");
			return null;
		}
		try{
			BigDecimal price= integral_int.divide(new BigDecimal(100));
			EcGiftJfBuy jfBuy =new EcGiftJfBuy();
			jfBuy.setTrade_index(trade_index);
			jfBuy.setTitle("积分在线充值");
			jfBuy.setIntegral(integral_int.longValue());
			jfBuy.setPrice(price);
			jfBuy.setUser_id(ecUser.getId());
			jfBuy.setUser_name(ecUser.getUser_name());
			jfBuy.setReal_name(ecUser.getReal_name());
			jfBuy.setOwn_sys(ecUser.getUser_type());
			Long id=super.getFacade().getEcGiftJfBuyService().createEcGiftJfBuy(jfBuy); 
			msg="积分充值订单提交成功，立即支付";
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/EcGiftJfPay.do?trade_index="+trade_index+"';}");
			return null;
		}catch(Exception e){
			msg="对不起发生错误,请稍后再试";
			super.renderJavaScript(response,"window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/member/EcGiftJf.do';}");
			return null;
		}
	}
	
}