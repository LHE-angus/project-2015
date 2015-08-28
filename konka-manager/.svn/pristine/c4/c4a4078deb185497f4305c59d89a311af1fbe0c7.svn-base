package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @desc 开心猫机型日销售报表
 * @author Pan,Gang
 * @version 2014-07-25
 */
public class PdSellCountAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String day_time_start = (String) dynaBean.get("day_time_start");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String month_first_day = df.format(calendar.getTime());// 当月第一天

		calendar.setTime(new Date());
		String now_day = df.format(calendar.getTime());// 当天

		PshowOrdeDetails entity = new PshowOrdeDetails();
		entity.getMap().put("prod_type", 0);// 彩电
		entity.getMap().put("add_dept_id", 0);// 上架表dept_id
		entity.getMap().put("own_sys", 1);// 所属系统
		entity.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });// 订单状态
		entity.getMap().put("order_from", 1);// 订单来源1工卡

		if (StringUtils.isNotBlank(day_time_start)) {
			entity.getMap().put("day_time_start", day_time_start + " 00:00:00");
			entity.getMap().put("day_time_end", day_time_start + " 23:59:59");
			Date dd = df.parse(day_time_start);
			calendar.setTime(dd);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String month_time_start = df.format(calendar.getTime());
			entity.getMap().put("month_time_start", month_time_start + " 00:00:00");
			entity.getMap().put("month_time_end", day_time_start + " 23:59:59");
			dynaBean.set("day_time_start", day_time_start);
			dynaBean.set("month_time_start", month_time_start);
		} else {
			entity.getMap().put("day_time_start", now_day + " 00:00:00");
			entity.getMap().put("day_time_end", now_day + " 23:59:59");
			entity.getMap().put("month_time_start", month_first_day + " 00:00:00");
			entity.getMap().put("month_time_end", now_day + " 23:59:59");
			dynaBean.set("day_time_start", now_day);
			dynaBean.set("month_time_start", month_first_day);
		}

		List<PshowOrdeDetails> entityList = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsAndDaySellForTjList(entity);
		BigDecimal day_money = new BigDecimal("0.00");
		BigDecimal day_num = new BigDecimal("0.00");
		// BigDecimal day_avg_price= new BigDecimal("0.00");
		BigDecimal month_money = new BigDecimal("0.00");
		BigDecimal month_num = new BigDecimal("0.00");
		// BigDecimal month_avg_price = new BigDecimal("0.00");
		if (null != entityList && entityList.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : entityList) {
				BigDecimal day_total_price = (BigDecimal) pshowOrdeDetails.getMap().get("day_total_price");
				day_money = day_money.add(day_total_price);
				BigDecimal day_sell_num = (BigDecimal) pshowOrdeDetails.getMap().get("day_sell_num");
				day_num = day_num.add(day_sell_num);
				BigDecimal month_total_price = (BigDecimal) pshowOrdeDetails.getMap().get("month_total_price");
				month_money = month_money.add(month_total_price);
				BigDecimal month_sell_num = (BigDecimal) pshowOrdeDetails.getMap().get("month_sell_num");
				month_num = month_num.add(month_sell_num);
			}
		}
		// 彩电合计
		dynaBean.set("day_money", day_money);
		dynaBean.set("day_num", day_num);
		// dynaBean.set("day_avg_price", day_money.divide(day_num));
		dynaBean.set("month_money", month_money);
		dynaBean.set("month_num", month_num);
		// dynaBean.set("month_avg_price", month_money.divide(month_num));
		request.setAttribute("entityList", entityList);

		// 生活电器累计
		PshowOrdeDetails pd2 = new PshowOrdeDetails();
		pd2.getMap().put("prod_type", 3);// 生活电器
		pd2.getMap().put("add_dept_id", 0);// 上架表dept_id
		pd2.getMap().put("own_sys", 1);// 所属系统
		pd2.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });// 订单状态
		pd2.getMap().put("order_from", 1);// 订单来源1工卡

		if (StringUtils.isNotBlank(day_time_start)) {
			pd2.getMap().put("day_time_start", day_time_start + " 00:00:00");
			pd2.getMap().put("day_time_end", day_time_start + " 23:59:59");
			Date dd = df.parse(day_time_start);
			calendar.setTime(dd);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String month_time_start = df.format(calendar.getTime());
			pd2.getMap().put("month_time_start", month_time_start + " 00:00:00");
			pd2.getMap().put("month_time_end", day_time_start + " 23:59:59");
		} else {
			pd2.getMap().put("day_time_start", now_day + " 00:00:00");
			pd2.getMap().put("day_time_end", now_day + " 23:59:59");
			pd2.getMap().put("month_time_start", month_first_day + " 00:00:00");
			pd2.getMap().put("month_time_end", now_day + " 23:59:59");
		}
		List<PshowOrdeDetails> pd2List = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsByProdTypeList(pd2);

		BigDecimal day_money2 = new BigDecimal("0.00");
		BigDecimal day_num2 = new BigDecimal("0.00");
		// BigDecimal day_avg_price= new BigDecimal("0.00");
		BigDecimal month_money2 = new BigDecimal("0.00");
		BigDecimal month_num2 = new BigDecimal("0.00");
		if (null != pd2List && pd2List.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : pd2List) {
				BigDecimal day_price = (BigDecimal) pshowOrdeDetails.getMap().get("day_price");
				day_money2 = day_money2.add(day_price);
				BigDecimal day_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("day_num");
				day_num2 = day_num2.add(day_num_2);
				BigDecimal month_price = (BigDecimal) pshowOrdeDetails.getMap().get("month_total_price");
				month_money2 = month_money2.add(month_price);
				BigDecimal month_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("month_num");
				month_num2 = month_num2.add(month_num_2);
			}
		}
		dynaBean.set("day_money_shdq", day_money2);
		dynaBean.set("day_num_shdq", day_num2);
		dynaBean.set("month_money_shdq", month_money2);
		dynaBean.set("month_num_shdq", month_num2);

		// 白电累计
		PshowOrdeDetails pd3 = new PshowOrdeDetails();
		pd3.getMap().put("prod_type", 4);// 冰箱
		pd3.getMap().put("add_dept_id", 0);// 上架表dept_id
		pd3.getMap().put("own_sys", 1);// 所属系统
		pd3.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });// 订单状态
		pd3.getMap().put("order_from", 1);// 订单来源1工卡

		if (StringUtils.isNotBlank(day_time_start)) {
			pd3.getMap().put("day_time_start", day_time_start + " 00:00:00");
			pd3.getMap().put("day_time_end", day_time_start + " 23:59:59");
			Date dd = df.parse(day_time_start);
			calendar.setTime(dd);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String month_time_start = df.format(calendar.getTime());
			pd3.getMap().put("month_time_start", month_time_start + " 00:00:00");
			pd3.getMap().put("month_time_end", day_time_start + " 23:59:59");
		} else {
			pd3.getMap().put("day_time_start", now_day + " 00:00:00");
			pd3.getMap().put("day_time_end", now_day + " 23:59:59");
			pd3.getMap().put("month_time_start", month_first_day + " 00:00:00");
			pd3.getMap().put("month_time_end", now_day + " 23:59:59");
		}
		List<PshowOrdeDetails> pd3List = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsByProdTypeList(pd3);

		BigDecimal day_money3 = new BigDecimal("0.00");
		BigDecimal day_num3 = new BigDecimal("0.00");
		// BigDecimal day_avg_price= new BigDecimal("0.00");
		BigDecimal month_money3 = new BigDecimal("0.00");
		BigDecimal month_num3 = new BigDecimal("0.00");
		if (null != pd3List && pd3List.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : pd3List) {
				BigDecimal day_price = (BigDecimal) pshowOrdeDetails.getMap().get("day_price");
				day_money3 = day_money3.add(day_price);
				BigDecimal day_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("day_num");
				day_num3 = day_num3.add(day_num_2);
				BigDecimal month_price = (BigDecimal) pshowOrdeDetails.getMap().get("month_total_price");
				month_money3 = month_money3.add(month_price);
				BigDecimal month_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("month_num");
				month_num3 = month_num3.add(month_num_2);
			}
		}
		dynaBean.set("day_money3", day_money3);
		dynaBean.set("day_num3", day_num3);
		dynaBean.set("month_money3", month_money3);
		dynaBean.set("month_num3", month_money3);

		// 白电累计
		PshowOrdeDetails pd4 = new PshowOrdeDetails();
		pd4.getMap().put("prod_type", 5);// 洗衣机
		pd4.getMap().put("add_dept_id", 0);// 上架表dept_id
		pd4.getMap().put("own_sys", 1);// 所属系统
		pd4.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });// 订单状态
		pd4.getMap().put("order_from", 1);// 订单来源1工卡

		if (StringUtils.isNotBlank(day_time_start)) {
			pd4.getMap().put("day_time_start", day_time_start + " 00:00:00");
			pd4.getMap().put("day_time_end", day_time_start + " 23:59:59");
			Date dd = df.parse(day_time_start);
			calendar.setTime(dd);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String month_time_start = df.format(calendar.getTime());
			pd4.getMap().put("month_time_start", month_time_start + " 00:00:00");
			pd4.getMap().put("month_time_end", day_time_start + " 23:59:59");
		} else {
			pd4.getMap().put("day_time_start", now_day + " 00:00:00");
			pd4.getMap().put("day_time_end", now_day + " 23:59:59");
			pd4.getMap().put("month_time_start", month_first_day + " 00:00:00");
			pd4.getMap().put("month_time_end", now_day + " 23:59:59");
		}
		List<PshowOrdeDetails> pd4List = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsByProdTypeList(pd4);

		BigDecimal day_money4 = new BigDecimal("0.00");
		BigDecimal day_num4 = new BigDecimal("0.00");
		// BigDecimal day_avg_price= new BigDecimal("0.00");
		BigDecimal month_money4 = new BigDecimal("0.00");
		BigDecimal month_num4 = new BigDecimal("0.00");
		if (null != pd4List && pd4List.size() > 0) {
			for (PshowOrdeDetails pshowOrdeDetails : pd4List) {
				BigDecimal day_price = (BigDecimal) pshowOrdeDetails.getMap().get("day_price");
				day_money4 = day_money4.add(day_price);
				BigDecimal day_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("day_num");
				day_num4 = day_num4.add(day_num_2);
				BigDecimal month_price = (BigDecimal) pshowOrdeDetails.getMap().get("month_total_price");
				month_money4 = month_money4.add(month_price);
				BigDecimal month_num_2 = (BigDecimal) pshowOrdeDetails.getMap().get("month_num");
				month_num4 = month_num4.add(month_num_2);
			}
		}
		dynaBean.set("day_money4", day_money4);
		dynaBean.set("day_num4", day_num4);
		dynaBean.set("month_money4", month_money4);
		dynaBean.set("month_num4", month_num4);

		dynaBean.set("day_money_bd", day_money3.add(day_money4));
		dynaBean.set("day_num_bd", day_num3.add(day_num4));
		dynaBean.set("month_money_bd", month_money3.add(month_money4));
		dynaBean.set("month_num_bd", month_num3.add(month_num4));

		dynaBean.set("total_day_money", day_money.add(day_money2).add(day_money3).add(day_money4));
		dynaBean.set("total_day_num", day_num.add(day_num2).add(day_num3).add(day_num4));
		dynaBean.set("total_month_money", month_money.add(month_money2).add(month_money3).add(month_money4));
		dynaBean.set("total_month_num", month_num.add(month_num2).add(month_num3).add(month_num4));
		return mapping.findForward("list");
	}

}
