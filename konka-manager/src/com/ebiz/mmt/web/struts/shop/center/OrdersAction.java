package com.ebiz.mmt.web.struts.shop.center;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author tudp
 * @version 2013-09-09
 */
public class OrdersAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		Pager pager = (Pager) dynaBean.get("pager");
		String trade_index = (String) dynaBean.get("trade_index");
		PshowOrder entity = new PshowOrder(); 
		if(trade_index!=null&&!"".equals(trade_index)){
		entity.setTrade_index(trade_index);
		entity.setOrder_user_id(ecUser.getId());
		entity.setOrder_from(ecUser.getUser_type());// 订单来源 1：工卡,2触网

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrderCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PshowOrder> entityList = super.getFacade().getPshowOrderService()
		        .getPshowOrderIncludeDetailsPaginatedList(entity);
		request.setAttribute("entityList", entityList);}
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(trade_index)) {
			entity.setTrade_index(trade_index);
		}
		entity.setOrder_user_id(ecUser.getId());
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		if (entity == null) {
			String msg = "对不起，为找到该订单信息！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg
			        + "');location.href='Orders.do';}");
			return null;
		}

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		// 地区全称显示
		if (null != entity.getBuyer_p_index()) {
			BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
			baseProvinceListFour.setP_index(entity.getBuyer_p_index());
			baseProvinceListFour = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(
			        baseProvinceListFour);
			String p_index_name = baseProvinceListFour.getFull_name();

			request.setAttribute("p_index_name", p_index_name);
		}

		PshowOrdeDetails psd = new PshowOrdeDetails();
		psd.setOrder_id(entity.getId());
		List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeForPDSNDetailsList(psd);

		int t_num = 0;
		double t_price = 0.00;
		for (PshowOrdeDetails ps : pshowordedetails) {
			t_num += ps.getNum();
			t_price += ps.getTotal_price().doubleValue();
		}
		request.setAttribute("t_num", t_num);
		request.setAttribute("t_price", t_price);
		request.setAttribute("pshowOrdeDetails", pshowordedetails);

		EcBindingPdOrdeDetails ecBindingDetail = new EcBindingPdOrdeDetails();
		ecBindingDetail.setTrade_index(entity.getTrade_index());
		ecBindingDetail.setState(new Integer(0));
		request.setAttribute("ecBindingDetailList", super.getFacade().getEcBindingPdOrdeDetailsService()
		        .getEcBindingPdOrdeDetailsList(ecBindingDetail));

		// 购物券
		EcVouchers ecVouchers = new EcVouchers();
		ecVouchers.setUser_id(ecUser.getId());
		ecVouchers.setOrder_id(entity.getId().toString());
		List<EcVouchers> ecVouchersList = super.getFacade().getEcVouchersService().getEcVouchersList(ecVouchers);
		if (ecVouchersList != null && ecVouchersList.size() > 0) {
			BigDecimal voucher_price = new BigDecimal("0");
			for (EcVouchers e : ecVouchersList) {
				voucher_price.add(e.getPrice());
			}
			request.setAttribute("ecVouchersList", ecVouchersList);
			request.setAttribute("voucher_price", voucher_price);
		} 
		// 顺丰物流信息
		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);
		if (null != ec) {
			dynaBean.set("is_sf", "1");
			dynaBean.set("sf_dh", ec.getLogistic_sn());
			if (null != ec.getLogistic_content()) {
				dynaBean.set("dd_state", ec.getLogistic_content());
			}
		} else {
			dynaBean.set("is_sf", "0");
		}

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String trade_index = (String) dynaBean.get("trade_index");

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		if (!StringUtils.isBlank(trade_index) && GenericValidator.isLong(trade_index)) { 
			PshowOrder order = new PshowOrder();
			if (StringUtils.isNotBlank(trade_index)) {
				order.setTrade_index(trade_index);
				order.setOrder_user_id(ecUser.getId());
				order = super.getFacade().getPshowOrderService().getPshowOrder(order);
				if(order!=null&&order.getId()!=null){
					PshowOrder entity = new PshowOrder();
					entity.setTrade_index(trade_index);
					entity.setState(new Integer(-10));
					entity.setOrder_user_id(ecUser.getId());				
					int i = getFacade().getPshowOrderService().modifyPshowOrderForCancel(entity);
					if (i == 0) {
						String msg = "对不起，订单状态已改变，取消订单失败！";
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						        + "');location.href='Orders.do';}");
						return null;
					} else {//
						EcVouchers t = new EcVouchers();
						t.setUser_id(ecUser.getId());
						t.setOrder_id(order.getId().toString());
						getFacade().getEcVouchersService().modifyEcVouchersByOrderId(t);
					}
				}
			} 
		}
		return mapping.findForward("success");
	}

	public ActionForward lookSfState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String trade_index = (String) dynaBean.get("trade_index");
		PshowOrder entity = new PshowOrder();
		entity.setTrade_index(trade_index);
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		String ss = orderState(ec);
		super.render(response, ss, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward sfList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String trade_index = (String) dynaBean.get("trade_index");
		PshowOrder entity = new PshowOrder();
		entity.setTrade_index(trade_index);
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		String logistic_content = "";
		if (null != ec.getLogistic_sn() && ec.getLogistic_sn().startsWith("11")) {
			// 处理之前没有走顺丰接口的那些订单
			logistic_content = ec.getLogistic_content();
		} else {

			SfOrderService sf = new SfOrderService();
			String sxddmxOpName = "sfexpressService";
			MailnoQuery mq = new MailnoQuery();
			mq.setTracking_type("1");
			mq.setMethod_type("1");
			mq.setTracking_number(ec.getLogistic_sn().trim());
			String xml2 = mq.toXml();
			//System.out.println("xml------------->" + xml2);
			String returnXml = sf.sfWebService2(xml2, sxddmxOpName);
			//System.out.println("returnXml======>{}" + returnXml);

			if (null == ec.getLogistic_content() || ec.getLogistic_content().indexOf("已签收") == -1) {
				if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
					EcOrderExpressInfo ed = new EcOrderExpressInfo();
					ed.setId(ec.getId());
					ed.setLogistic_content(returnXml.replaceAll("&#x9", ""));
					super.getFacade().getEcOrderExpressInfoService().modifyEcOrderExpressInfo(ed);
				}
			}

			EcOrderExpressInfo ef = new EcOrderExpressInfo();
			ef.setId(ec.getId());
			ef = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ef);

			if (!"".equals(returnXml) && returnXml.indexOf("remark") != -1) {
				logistic_content = returnXml;
			} else {
				if (null != ef.getLogistic_content()) {
					logistic_content = ef.getLogistic_content();
				}
			}
		}

		// [["2014-01-19 11:54:55","已收件"],["2014-01-19 13:28:46","快件在东莞,准备送往下一站"]]
		List<HashMap> list = new ArrayList<HashMap>();
		try {
			if ("".equals(logistic_content)) {
				super.renderJson(response, "[{\"accept_time\":\"  \",\"remark\":\"顺丰接口查询超时 \"}]");
				return null;
			}
			Document doc = DocumentHelper.parseText(logistic_content);
			Element rootElt = doc.getRootElement();
			Iterator<Element> iter = rootElt.elementIterator("Body");
			while (iter.hasNext()) {
				Element em = iter.next();
				Iterator<Element> iter2 = em.elementIterator("RouteResponse");
				while (iter2.hasNext()) {
					Element em2 = iter2.next();
					Iterator<Element> iter3 = em2.elementIterator("Route");
					while (iter3.hasNext()) {
						Element em3 = iter3.next();
						HashMap map = new HashMap();
						map.put("accept_time", em3.attributeValue("accept_time").trim());
						map.put("remark", em3.attributeValue("remark").replaceAll(" ", "").replaceAll(" ", "").trim());
						list.add(map);
					}
				} 
			}
		} catch (Exception e) {
			// [{"accept_time":"2014-01-18 12:07:12","remark":"已收件"}
			super.renderJson(response, "[{\"accept_time\":\"  \",\"remark\":\"顺丰接口查询超时 \"}]");
			return null;
		}

		super.renderJson(response, JSON.toJSONString(list)); 
		return null;
	}

	public ActionForward sfList2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String trade_index = (String) dynaBean.get("trade_index");
		PshowOrder entity = new PshowOrder();
		entity.setTrade_index(trade_index);
		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);

		EcOrderExpressInfo ec = new EcOrderExpressInfo();
		ec.setTrade_index(entity.getTrade_index());
		ec = super.getFacade().getEcOrderExpressInfoService().getEcOrderExpressInfo(ec);

		List<HashMap> list = new ArrayList<HashMap>();
		if (ec!=null&&null != ec.getLogistic_content()) {
			Document doc = DocumentHelper.parseText(ec.getLogistic_content());
			Element rootElt = doc.getRootElement();
			Iterator<Element> iter = rootElt.elementIterator("Body");
			while (iter.hasNext()) {
				Element em = iter.next();
				Iterator<Element> iter2 = em.elementIterator("RouteResponse");
				while (iter2.hasNext()) {
					Element em2 = iter2.next();
					Iterator<Element> iter3 = em2.elementIterator("Route");
					while (iter3.hasNext()) {
						Element em3 = iter3.next();
						HashMap map = new HashMap();
						map.put("accept_time", em3.attributeValue("accept_time").trim());
						map.put("remark", em3.attributeValue("remark").replaceAll(" ", "").replaceAll(" ", "").trim());
						list.add(map);
					}
				}

			}
		}

		logger.info("entityList------------>" + list.size());
		request.setAttribute("entityList", list);

		return mapping.findForward("input");
	}
}
