package com.ebiz.mmt.web.struts.webservice.wap.center;

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
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;
import com.sf.integration.warehouse.MailnoQuery;
import com.sf.integration.warehouse.SfOrderService;

/**
 * @author Pan,Gang
 * @version 2014-1-03
 */
public class OrdersAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form; 
		String ts = (String) dynaBean.get("ts");
		HttpSession session = request.getSession();
		// 从Session中取用户并判断
				EcUser ecUser = (EcUser) super.getSessionAttribute(request, Constants.EPP_USER);
				if (null == ecUser) {
					String msg = super.getMessage(request, "ec.nologin");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)
							+ "/webservice/wap/login.do';}");
					return null;
				}

		PshowOrder entity = new PshowOrder();
		super.copyProperties(entity, form);
		String orderState = (String) dynaBean.get("orderState");
		if ("2".equals(ts)) {
			entity.getMap().put("before3months", "before3months");
		} else if ("0".equals(ts)) {

		} else {
			dynaBean.set("ts", "1");
			entity.getMap().put("last3months", "last3months");
		}

		if ("3".equals(orderState)) {// 已取消
			entity.getMap().put("state_in", new Integer[] { -10, -20, -30 });
		} else if ("2".equals(orderState)) {// 已完成
			entity.getMap().put("state_in", new Integer[] { 50, 60 });
		} else if ("1".equals(orderState)) {// 进行中
			entity.getMap().put("state_in", new Integer[] { 0, 10, 20, 30, 40 });
			dynaBean.set("orderState", "1");
		}

		// 分页
		String page = request.getParameter("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 5;
		if (!StringUtils.isEmpty(page)) {
			currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);

		entity.setOrder_user_id(Long.valueOf(ecUser.getId()));
		//entity.setOrder_from(1);// 订单来源 1：工卡,2触网

		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrderCount(entity);

		int pageCount = (int) Math.ceil((recordCount + pageSize - 1) / pageSize);

		dynaBean.set("recordCount", recordCount);
		if (recordCount > 0) {
			dynaBean.set("currentPage", currentPage);
			dynaBean.set("pageCount", pageCount);
			if (currentPage > pageCount) {
				super.renderJavaScript(response, "window.onload=function(){alert('当前页已是最后一页！');history.back();}");
				return null;
			}
			List<PshowOrder> entityList = super.getFacade().getPshowOrderService()
			        .getPshowOrderIncludeDetailsPaginatedList(entity);
			request.setAttribute("entityList", entityList);
		}
		// dynaBean.set("username", ecUser.getUser_name());
		// dynaBean.set("userpass", ecUser.getPass_word());
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String buyer_mp = (String) dynaBean.get("buyer_mp");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		if (null == ecUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);
		if (ecUser.getUser_type().intValue() == 1) {
			String msg="";
			String touch = (String) session.getAttribute("touch");
			 if(!"1".equals(touch)){
				if(StringUtils.isBlank(buyer_mp)){
					msg="请输入手机号查看订单";
				}else if(!buyer_mp.trim().equals(entity.getBuyer_mp())){
					msg="手机号错误";
				}
				if(!"".equals(msg)){
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
					return null;
				}
			 }
		}
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
		psd.setOrder_id(Long.valueOf(id));
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
		ecVouchers.setOrder_id(id);
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
			if (null != ec.getLogistic_sn()) {
				dynaBean.set("sf_dh", ec.getLogistic_sn());
				logger.info("顺丰单号---------》" + ec.getLogistic_sn());
			}
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
		String id = (String) dynaBean.get("id");

		//System.out.println("取消订单----》" + id);

		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		if (null == ecUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			//System.out.println("取消订单----》11111");
			PshowOrder entity = new PshowOrder();
			entity.setId(new Long(id));
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
				t.setOrder_id(id);
				getFacade().getEcVouchersService().modifyEcVouchersByOrderId(t);
			}
			// saveMessage(request, "entity.deleted");
		}

		/*
		 * StringBuffer pathBuffer = new StringBuffer();
		 * pathBuffer.append(super.getCtxPath(request));
		 * pathBuffer.append("/Orders.do?method=list&username=" + username);
		 * pathBuffer.append("&userpass=" + userpass); ActionForward forward =
		 * new ActionForward(pathBuffer.toString(), true);
		 * logger.info("url-------------->" + pathBuffer.toString());
		 */
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward sfList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		//System.out.println("i am coming-------------------->");
		PshowOrder entity = new PshowOrder();
		entity.setId(Long.valueOf(id));
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
			if (entity.getOpr_dept_id() != null && entity.getOpr_dept_id().intValue() == 10) {// 哈尔滨分公司
				mq.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");
			}
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
		//System.out.println("list----------->" + JSON.toJSONString(list));
		return null;
	}

}
