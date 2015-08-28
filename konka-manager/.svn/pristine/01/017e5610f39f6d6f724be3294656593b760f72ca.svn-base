package com.ebiz.mmt.web.struts;

import java.math.BigDecimal;
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

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.MobileUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Pan,Gang
 * @version 2014-1-03
 */
public class OrdersAction extends BaseMemberAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		MobileUser mu = (MobileUser) session.getAttribute("mobile_user");

		if (null == mu) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		logger.info("i coming in------------》");

		PshowOrder entity = new PshowOrder();
		super.copyProperties(entity, form);
		String ts = (String) dynaBean.get("ts");
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
		} else {// 进行中
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

		entity.setOrder_user_id(Long.valueOf(mu.getUser_id()));
		entity.setOrder_from(1);// 订单来源 1：工卡,2触网

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
		dynaBean.set("username", mu.getUsername());
		dynaBean.set("userpass", mu.getUserpass());
		return new ActionForward("/mobile/orders/list.jsp");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		EcUser ui = new EcUser();
		String username = "";
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser2(username, userpass);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！" + username);
			return null;
		}

		PshowOrder entity = new PshowOrder();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		entity = super.getFacade().getPshowOrderService().getPshowOrder(entity);
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
		ecVouchers.setUser_id(ui.getId());
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
			dynaBean.set("sf_dh", ec.getLogistic_sn());
			if (null != ec.getLogistic_content()) {
				dynaBean.set("dd_state", ec.getLogistic_content());
			}
		} else {
			dynaBean.set("is_sf", "0");
		}

		return new ActionForward("/mobile/orders/view.jsp");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		EcUser ui = new EcUser();
		String username = "";
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser2(username, userpass);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！" + username);
			return null;
		}
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			PshowOrder entity = new PshowOrder();
			entity.setId(new Long(id));
			entity.setState(new Integer(-10));
			entity.setOrder_user_id(ui.getId());
			int i = getFacade().getPshowOrderService().modifyPshowOrderForCancel(entity);
			if (i == 0) {
				String msg = "对不起，订单状态已改变，取消订单失败！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');location.href='Orders.do';}");
				return null;
			} else {//
				EcVouchers t = new EcVouchers();
				t.setUser_id(ui.getId());
				t.setOrder_id(id);
				getFacade().getEcVouchersService().modifyEcVouchersByOrderId(t);
			}
			// saveMessage(request, "entity.deleted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(super.getCtxPath(request));
		pathBuffer.append("/Orders.do?method=list&username=" + username);
		pathBuffer.append("&userpass=" + userpass);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		logger.info("url-------------->" + pathBuffer.toString());
		return forward;
	}

	public ActionForward getCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");
		EcUser ui = new EcUser();
		if (user_id != null) {
			ui.setId(Long.valueOf(user_id));
			ui.setPass_word(new DESPlus().encrypt(userpass));
			ui = getFacade().getEcUserService().getEcUser(ui);
		} else {
			username = request.getParameter("username");
			ui = checkUser2(username, userpass);
		}

		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		PshowOrder entity = new PshowOrder();
		String ts = (String) dynaBean.get("ts");
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
		} else {// 进行中
			entity.getMap().put("state_in", new Integer[] { 0, 10, 20, 30, 40 });
			dynaBean.set("orderState", "1");
		}
		entity.setOrder_user_id(ui.getId());
		entity.setOrder_from(ui.getUser_type());// 订单来源 1：工卡,2触网
		Long recordCount = super.getFacade().getPshowOrderService().getPshowOrderCount(entity);

		super.renderText(response, String.valueOf(Math.ceil(recordCount)));
		return null;
	}

}
