package com.ebiz.mmt.web.struts.jxc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.domain.StdEntpUser;

/**
 * @author Chen,WeiWei
 * @version 2011-4-19
 */
public class JxcStoresOperStatusAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		
		request.setAttribute("not_validate_record", "true");
		StdEntpUser user = super.getStdEntpUserFromRequest(request, response, keySeq);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getStdEntpMain().getShop_id();
		String pattern = "yyyy-MM-dd";
		Date date = new Date();
		String date_pattern = DateFormatUtils.format(date, pattern);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start_date", date_pattern);
		map.put("end_date", date_pattern);
		map.put("shop_id", shop_id);
		JxcSellBillDetails jsbd = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForReal(map);
		request.setAttribute("jsbd_day", jsbd);

		JxcStockBillDetails jstockbd = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsStatForReal(map);
		request.setAttribute("jstockbd_day", jstockbd);

		String date_week_start = getMondayOfThisWeek();
		String date_week_end = getSundayOfThisWeek();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("start_date", date_week_start);
		map1.put("end_date", date_week_end);
		map1.put("shop_id", shop_id);
		JxcSellBillDetails jsbd_week = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForReal(map1);
		request.setAttribute("jsbd_week", jsbd_week);

		JxcStockBillDetails jstockbd_week = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsStatForReal(map1);
		request.setAttribute("jstockbd_week", jstockbd_week);

		String date_month_start = getFristDateOfMonth();
		String date_month_end = getLastDateOfMonth();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("start_date", date_month_start);
		map2.put("end_date", date_month_end);
		map2.put("shop_id", shop_id);
		JxcSellBillDetails jsbd_month = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForReal(map2);
		request.setAttribute("jsbd_month", jsbd_month);

		JxcStockBillDetails jstockbd_month = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsStatForReal(map2);
		request.setAttribute("jstockbd_month", jstockbd_month);

		String date_year_start = getFristDateOfYear();
		String date_year_end = getLastDateOfYear();
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("start_date", date_year_start);
		map3.put("end_date", date_year_end);
		map3.put("shop_id", shop_id);
		JxcSellBillDetails jsbd_year = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForReal(map3);
		request.setAttribute("jsbd_year", jsbd_year);

		JxcStockBillDetails jstockbd_year = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsStatForReal(map3);
		request.setAttribute("jstockbd_year", jstockbd_year);

		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("shop_id", shop_id);
		JxcSellBillDetails jsbd_total = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForReal(map4);
		request.setAttribute("jsbd_total", jsbd_total);

		JxcStockBillDetails jstockbd_total = super.getFacade().getJxcStockBillDetailsService()
				.getJxcStockBillDetailsStatForReal(map4);
		request.setAttribute("jstockbd_total", jstockbd_total);

		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("shop_id", shop_id);
		map5.put("start_date", date_pattern);
		map5.put("end_date", date_pattern);
		map5.put("rownum", 5);
		List<JxcSellBillDetails> entityListForToday = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForPdReal(map5);

		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("shop_id", shop_id);
		map6.put("start_date", getMondayOfThisWeek());
		map6.put("end_date", getSundayOfThisWeek());
		map6.put("rownum", 5);
		List<JxcSellBillDetails> entityListForWeek = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForPdReal(map6);

		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("shop_id", shop_id);
		map7.put("start_date", getFristDateOfMonth());
		map7.put("end_date", getLastDateOfMonth());
		map7.put("rownum", 5);
		List<JxcSellBillDetails> entityListForMonth = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForPdReal(map7);

		Map<String, Object> map8 = new HashMap<String, Object>();
		map8.put("shop_id", shop_id);
		map8.put("start_date", getFristDateOfYear());
		map8.put("end_date", getLastDateOfYear());
		map8.put("rownum", 5);
		List<JxcSellBillDetails> entityListForYear = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsStatForPdReal(map8);

		request.setAttribute("entityListForToday", entityListForToday);
		request.setAttribute("entityListForWeek", entityListForWeek);
		request.setAttribute("entityListForMonth", entityListForMonth);
		request.setAttribute("entityListForYear", entityListForYear);

		return mapping.findForward("list");
	}

	/**
	 * 得到本周周一
	 * 
	 * @return yyyy-MM-dd
	 */
	public String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 *得到本周周日
	 * 
	 * @return yyyy-MM-dd
	 */
	public String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		c.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本月最后一天
	 * 
	 * @return
	 */
	public String getLastDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 得到本月第一天
	 * 
	 * @return
	 */
	public String getFristDateOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, days);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 得到本年第一天
	 * 
	 * @return
	 */
	public String getFristDateOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, days);
		String result = format.format(cal.getTime());
		return result;
	}

	/**
	 * 得到本年最后一天
	 * 
	 * @return
	 */
	public String getLastDateOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = getDate();

		if (dt == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int days = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, days);
		String result = format.format(cal.getTime());
		return result;
	}

	public Date getDate() {
		Date date = new Date();
		return date;
	}

}
