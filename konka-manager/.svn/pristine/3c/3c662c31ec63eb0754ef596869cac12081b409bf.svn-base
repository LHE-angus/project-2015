package com.ebiz.mmt.web.struts.jxcnokey;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.util.EncryptUtilsV2;

/**
 * @author Chen,WeiWei
 * @version 2011-4-20
 */
@SuppressWarnings("unused")
public class JxcStoresAnalysisAction extends BaseJxcAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
//		return new ActionForward("/../WEB-INF/pages/jsp/client/JxcStoresAnalysis/list.jsp");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		
		Date now = new Date();
		Calendar n = Calendar.getInstance();
		n.setTime(now);
		if (StringUtils.isBlank(year)) {
			year = String.valueOf(n.get(Calendar.YEAR));
		}
		if (StringUtils.isBlank(month)) {
			if (n.get(Calendar.MONTH) + 1 < 10) {
				month = "0".concat(String.valueOf(n.get(Calendar.MONTH) + 1));
			} else {
				month = String.valueOf(n.get(Calendar.MONTH) + 1);
			}
		}
		
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shop_id", shop_id);
			String start_date = getFristDateOfMonth(year, month);
			String end_date = getLastDateOfMonth(year, month);
			map.put("start_date", start_date);
			map.put("end_date", end_date);
			List<JxcSellBillDetails> entityListForThisMonth = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysis(map);// 本月销额
			
			List<JxcSellBillDetails> entityListForThisMonthTotal = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysisAnother(map);// 本月合计销额
			
			map.remove("start_date");
			map.remove("end_date");
			List<JxcSellBillDetails> entityListForTotal = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysis(map);// 总销额
			List<JxcSellBillDetails> entityListForTotalTotal = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysisAnother(map);// 合计总销额
			
			map.put("start_date", getPreviousMonthFirstDate(year, month));
			map.put("end_date", getPreviousMonthLastDate(year, month));
			List<JxcSellBillDetails> entityListForPreMonth = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysis(map);// 上月销额
			List<JxcSellBillDetails> entityListForPreMonthTotal = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysisAnother(map);// 合计上月销额
			
			map.remove("start_date");
			map.remove("end_date");
			map.put("start_date", getPreviousYearFirstDate(year, month));
			map.put("end_date", getPreviousYearLastDate(year, month));
			List<JxcSellBillDetails> entityListForPreYear = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysis(map);// 上年同期销额
			List<JxcSellBillDetails> entityListForPreYearTotal = super.getFacade().getJxcSellBillDetailsService()
			.getJxcStoresAnalysisAnother(map);// 合计上年同期销额
			
			for (JxcSellBillDetails elftm : entityListForThisMonth) {
				for (JxcSellBillDetails elft : entityListForTotal) {
					BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
					BigDecimal elft_brand_id = (BigDecimal) elft.getMap().get("brand_id");
					BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
					BigDecimal elft_pd_type = (BigDecimal) elft.getMap().get("pd_type");
					if (elftm_brand_id.longValue() == elft_brand_id.longValue()
							&& elftm_pd_type.longValue() == elft_pd_type.longValue()) {
						
						// elftm.getMap().put("sales_revenue_total",
						// elft.getMap().get("sales_revenue"));
						
						BigDecimal sales_revenue_total = (BigDecimal) elft.getMap().get("sales_revenue");
						Double sales_revenue_total_db = sales_revenue_total.doubleValue();
						BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();
						Double zhanbi = sales_revenue_db / sales_revenue_total_db;
						elftm.getMap().put("zhanbi", zhanbi);
						
					}
				}
			}
			
			for (JxcSellBillDetails elftm : entityListForThisMonth) {
				for (JxcSellBillDetails elfpm : entityListForPreMonth) {
					BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
					BigDecimal elfpm_brand_id = (BigDecimal) elfpm.getMap().get("brand_id");
					BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
					BigDecimal elfpm_pd_type = (BigDecimal) elfpm.getMap().get("pd_type");
					if (elftm_brand_id.longValue() == elfpm_brand_id.longValue()
							&& elftm_pd_type.longValue() == elfpm_pd_type.longValue()) {
						
						// elftm.getMap().put("sales_revenue_total",
						// elft.getMap().get("sales_revenue"));
						BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
						BigDecimal sales_revenue_pre = (BigDecimal) elfpm.getMap().get("sales_revenue");
						Double sales_revenue_pre_db = sales_revenue_pre.doubleValue();// 上月销额
						Double annulus = (sales_revenue_db - sales_revenue_pre_db) / sales_revenue_pre_db;
						elftm.getMap().put("annulus", annulus);// 环比
						elftm.getMap().put("sales_revenue_pre", sales_revenue_pre_db);
					}
				}
			}
			
			for (JxcSellBillDetails elftm : entityListForThisMonth) {
				for (JxcSellBillDetails elfpy : entityListForPreYear) {
					BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
					BigDecimal elfpy_brand_id = (BigDecimal) elfpy.getMap().get("brand_id");
					BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
					BigDecimal elfpy_pd_type = (BigDecimal) elfpy.getMap().get("pd_type");
					if (elftm_brand_id.longValue() == elfpy_brand_id.longValue()
							&& elftm_pd_type.longValue() == elfpy_pd_type.longValue()) {
						
						// elftm.getMap().put("sales_revenue_total",
						// elft.getMap().get("sales_revenue"));
						
						BigDecimal sales_revenue_pre_year = (BigDecimal) elfpy.getMap().get("sales_revenue");
						Double sales_revenue_pre_year_db = sales_revenue_pre_year.doubleValue();// 上年同期
						BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
						Double year_on_year = (sales_revenue_db - sales_revenue_pre_year_db) / sales_revenue_pre_year_db;// 同比
						elftm.getMap().put("year_on_year", year_on_year);
						elftm.getMap().put("sales_revenue_pre_year", sales_revenue_pre_year_db);
					}
				}
			}
			
			for (JxcSellBillDetails elftm : entityListForThisMonth) {
				BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
				Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
				
				BigDecimal cost_sales = (BigDecimal) elftm.getMap().get("cost_sales");
				Double cost_sales_db = cost_sales.doubleValue();// 本月成本
				Double maori = sales_revenue_db - cost_sales_db;
				Double maori_rate = maori / sales_revenue_db;
				elftm.getMap().put("maori", maori);// 毛利
				elftm.getMap().put("maori_rate", maori_rate);// 毛利率
			}
			
			for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
				for (JxcSellBillDetails enlftt : entityListForTotalTotal) {
					BigDecimal elftmt_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
					BigDecimal enlftt_brand_id = (BigDecimal) enlftt.getMap().get("brand_id");
					if (elftmt_brand_id.longValue() == enlftt_brand_id.longValue()) {
						
						BigDecimal sales_revenue_total = (BigDecimal) enlftt.getMap().get("sales_revenue");
						Double sales_revenue_total_db = sales_revenue_total.doubleValue();
						BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();
						Double zhanbi = sales_revenue_db / sales_revenue_total_db;
						elftmt.getMap().put("zhanbi", zhanbi);
					}
					
				}
			}
			
			for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
				for (JxcSellBillDetails elfpmt : entityListForPreMonthTotal) {
					BigDecimal elftmt_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
					BigDecimal elfpmt_brand_id = (BigDecimal) elfpmt.getMap().get("brand_id");
					if (elfpmt_brand_id.longValue() == elftmt_brand_id.longValue()) {
						BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
						BigDecimal sales_revenue_pre = (BigDecimal) elfpmt.getMap().get("sales_revenue");
						Double sales_revenue_pre_db = sales_revenue_pre.doubleValue();// 上月销额
						Double annulus = (sales_revenue_db - sales_revenue_pre_db) / sales_revenue_pre_db;
						elftmt.getMap().put("annulus", annulus);// 环比
						elftmt.getMap().put("sales_revenue_pre", sales_revenue_pre_db);
					}
				}
			}
			
			for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
				for (JxcSellBillDetails elfpyt : entityListForPreYearTotal) {
					BigDecimal elftm_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
					BigDecimal elfpy_brand_id = (BigDecimal) elfpyt.getMap().get("brand_id");
					if (elftm_brand_id.longValue() == elfpy_brand_id.longValue()) {
						
						BigDecimal sales_revenue_pre_year = (BigDecimal) elfpyt.getMap().get("sales_revenue");
						Double sales_revenue_pre_year_db = sales_revenue_pre_year.doubleValue();// 上年同期
						BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
						Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
						Double year_on_year = (sales_revenue_db - sales_revenue_pre_year_db) / sales_revenue_pre_year_db;// 同比
						elftmt.getMap().put("year_on_year", year_on_year);
						elftmt.getMap().put("sales_revenue_pre_year", sales_revenue_pre_year_db);
					}
				}
			}
			
			for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
				BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
				Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
				
				BigDecimal cost_sales = (BigDecimal) elftmt.getMap().get("cost_sales");
				Double cost_sales_db = cost_sales.doubleValue();// 本月成本
				Double maori = sales_revenue_db - cost_sales_db;
				Double maori_rate = maori / sales_revenue_db;
				elftmt.getMap().put("maori", maori);// 毛利
				elftmt.getMap().put("maori_rate", maori_rate);// 毛利率
			}
			
			for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
				entityListForThisMonth.add(elftmt);
			}
			ComparatorUser comparator = new ComparatorUser();
			Collections.sort(entityListForThisMonth, comparator);
			request.setAttribute("entityList", entityListForThisMonth);
		}

		dynaBean.set("year", year);
		dynaBean.set("month", month);
		return mapping.findForward("list");
	}

	public class ComparatorUser implements Comparator<JxcSellBillDetails> {

		@Override
		public int compare(JxcSellBillDetails arg0, JxcSellBillDetails arg1) {
			JxcSellBillDetails user0 = (JxcSellBillDetails) arg0;
			JxcSellBillDetails user1 = (JxcSellBillDetails) arg1;

			BigDecimal brand_id1 = (BigDecimal) user0.getMap().get("brand_id");
			BigDecimal brand_id2 = (BigDecimal) user1.getMap().get("brand_id");
			int flag = brand_id1.compareTo(brand_id2);

			return flag;

		}

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

	public Date getDate() {
		Date date = new Date();
		return date;
	}

	public String getFristDateOfMonth(String year, String month) {
		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String result = year + "-" + month + "-" + "01";
			return result;
		}
	}

	public String getLastDateOfMonth(String year, String month) throws ParseException {

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String firstDay = year + "-" + month + "-" + "01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = format.parse(firstDay);

			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(firstDate);
			lastDate.add(Calendar.MONTH, 1);
			lastDate.set(Calendar.DATE, 1);
			lastDate.add(Calendar.DATE, -1);
			String result = format.format(lastDate.getTime());
			return result;
		}

	}

	public String getPreviousMonthFirstDate(String year, String month) throws ParseException {

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String firstDay = year + "-" + month + "-" + "01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = format.parse(firstDay);

			Calendar firstDatePreMonth = Calendar.getInstance();
			firstDatePreMonth.setTime(firstDate);
			firstDatePreMonth.add(Calendar.MONTH, -1);
			firstDatePreMonth.set(Calendar.DATE, 1);
			String result = format.format(firstDatePreMonth.getTime());
			return result;
		}

	}

	public String getPreviousMonthLastDate(String year, String month) throws ParseException {

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String firstDay = year + "-" + month + "-" + "01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = format.parse(firstDay);

			Calendar firstDatePreMonth = Calendar.getInstance();
			firstDatePreMonth.setTime(firstDate);
			firstDatePreMonth.set(Calendar.DATE, 1);
			firstDatePreMonth.add(Calendar.DATE, -1);
			String result = format.format(firstDatePreMonth.getTime());
			return result;
		}

	}

	public String getPreviousYearFirstDate(String year, String month) throws ParseException {

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String firstDay = year + "-" + month + "-" + "01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = format.parse(firstDay);

			Calendar firstDatePreMonth = Calendar.getInstance();
			firstDatePreMonth.setTime(firstDate);
			firstDatePreMonth.add(Calendar.YEAR, -1);
			firstDatePreMonth.set(Calendar.DATE, 1);
			String result = format.format(firstDatePreMonth.getTime());
			return result;
		}

	}

	public String getPreviousYearLastDate(String year, String month) throws ParseException {

		if (StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			return null;
		} else {
			String firstDay = year + "-" + month + "-" + "01";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = format.parse(firstDay);

			Calendar firstDatePreMonth = Calendar.getInstance();
			firstDatePreMonth.setTime(firstDate);
			firstDatePreMonth.add(Calendar.YEAR, -1);
			firstDatePreMonth.set(Calendar.DATE, 1);
			firstDatePreMonth.add(Calendar.MONTH, 1);
			firstDatePreMonth.add(Calendar.DATE, -1);
			String result = format.format(firstDatePreMonth.getTime());
			return result;
		}

	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		
		request.setAttribute("not_validate_record", "true");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		Long shop_id = user.getEntp_shop().getShop_id();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_id", shop_id);
		String start_date = getFristDateOfMonth(year, month);
		String end_date = getLastDateOfMonth(year, month);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		List<JxcSellBillDetails> entityListForThisMonth = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysis(map);// 本月销额

		List<JxcSellBillDetails> entityListForThisMonthTotal = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysisAnother(map);// 本月合计销额

		map.remove("start_date");
		map.remove("end_date");
		List<JxcSellBillDetails> entityListForTotal = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysis(map);// 总销额
		List<JxcSellBillDetails> entityListForTotalTotal = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysisAnother(map);// 合计总销额

		map.put("start_date", getPreviousMonthFirstDate(year, month));
		map.put("end_date", getPreviousMonthLastDate(year, month));
		List<JxcSellBillDetails> entityListForPreMonth = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysis(map);// 上月销额
		List<JxcSellBillDetails> entityListForPreMonthTotal = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysisAnother(map);// 合计上月销额

		map.remove("start_date");
		map.remove("end_date");
		map.put("start_date", getPreviousYearFirstDate(year, month));
		map.put("end_date", getPreviousYearLastDate(year, month));
		List<JxcSellBillDetails> entityListForPreYear = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysis(map);// 上年同期销额
		List<JxcSellBillDetails> entityListForPreYearTotal = super.getFacade().getJxcSellBillDetailsService()
				.getJxcStoresAnalysisAnother(map);// 合计上年同期销额

		for (JxcSellBillDetails elftm : entityListForThisMonth) {
			for (JxcSellBillDetails elft : entityListForTotal) {
				BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
				BigDecimal elft_brand_id = (BigDecimal) elft.getMap().get("brand_id");
				BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
				BigDecimal elft_pd_type = (BigDecimal) elft.getMap().get("pd_type");
				if (elftm_brand_id.longValue() == elft_brand_id.longValue()
						&& elftm_pd_type.longValue() == elft_pd_type.longValue()) {

					// elftm.getMap().put("sales_revenue_total",
					// elft.getMap().get("sales_revenue"));

					BigDecimal sales_revenue_total = (BigDecimal) elft.getMap().get("sales_revenue");
					Double sales_revenue_total_db = sales_revenue_total.doubleValue();
					BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();
					Double zhanbi = sales_revenue_db / sales_revenue_total_db;
					elftm.getMap().put("zhanbi", zhanbi);

				}
			}
		}

		for (JxcSellBillDetails elftm : entityListForThisMonth) {
			for (JxcSellBillDetails elfpm : entityListForPreMonth) {
				BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
				BigDecimal elfpm_brand_id = (BigDecimal) elfpm.getMap().get("brand_id");
				BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
				BigDecimal elfpm_pd_type = (BigDecimal) elfpm.getMap().get("pd_type");
				if (elftm_brand_id.longValue() == elfpm_brand_id.longValue()
						&& elftm_pd_type.longValue() == elfpm_pd_type.longValue()) {

					// elftm.getMap().put("sales_revenue_total",
					// elft.getMap().get("sales_revenue"));
					BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
					BigDecimal sales_revenue_pre = (BigDecimal) elfpm.getMap().get("sales_revenue");
					Double sales_revenue_pre_db = sales_revenue_pre.doubleValue();// 上月销额
					Double annulus = (sales_revenue_db - sales_revenue_pre_db) / sales_revenue_pre_db;
					elftm.getMap().put("annulus", annulus);// 环比
					elftm.getMap().put("sales_revenue_pre", sales_revenue_pre_db);
				}
			}
		}

		for (JxcSellBillDetails elftm : entityListForThisMonth) {
			for (JxcSellBillDetails elfpy : entityListForPreYear) {
				BigDecimal elftm_brand_id = (BigDecimal) elftm.getMap().get("brand_id");
				BigDecimal elfpy_brand_id = (BigDecimal) elfpy.getMap().get("brand_id");
				BigDecimal elftm_pd_type = (BigDecimal) elftm.getMap().get("pd_type");
				BigDecimal elfpy_pd_type = (BigDecimal) elfpy.getMap().get("pd_type");
				if (elftm_brand_id.longValue() == elfpy_brand_id.longValue()
						&& elftm_pd_type.longValue() == elfpy_pd_type.longValue()) {

					// elftm.getMap().put("sales_revenue_total",
					// elft.getMap().get("sales_revenue"));

					BigDecimal sales_revenue_pre_year = (BigDecimal) elfpy.getMap().get("sales_revenue");
					Double sales_revenue_pre_year_db = sales_revenue_pre_year.doubleValue();// 上年同期
					BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
					Double year_on_year = (sales_revenue_db - sales_revenue_pre_year_db) / sales_revenue_pre_year_db;// 同比
					elftm.getMap().put("year_on_year", year_on_year);
					elftm.getMap().put("sales_revenue_pre_year", sales_revenue_pre_year_db);
				}
			}
		}

		for (JxcSellBillDetails elftm : entityListForThisMonth) {
			BigDecimal sales_revenue = (BigDecimal) elftm.getMap().get("sales_revenue");
			Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额

			BigDecimal cost_sales = (BigDecimal) elftm.getMap().get("cost_sales");
			Double cost_sales_db = cost_sales.doubleValue();// 本月成本
			Double maori = sales_revenue_db - cost_sales_db;
			Double maori_rate = maori / sales_revenue_db;
			elftm.getMap().put("maori", maori);// 毛利
			elftm.getMap().put("maori_rate", maori_rate);// 毛利率
		}

		for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
			for (JxcSellBillDetails enlftt : entityListForTotalTotal) {
				BigDecimal elftmt_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
				BigDecimal enlftt_brand_id = (BigDecimal) enlftt.getMap().get("brand_id");
				if (elftmt_brand_id.longValue() == enlftt_brand_id.longValue()) {

					BigDecimal sales_revenue_total = (BigDecimal) enlftt.getMap().get("sales_revenue");
					Double sales_revenue_total_db = sales_revenue_total.doubleValue();
					BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();
					Double zhanbi = sales_revenue_db / sales_revenue_total_db;
					elftmt.getMap().put("zhanbi", zhanbi);
				}

			}
		}

		for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
			for (JxcSellBillDetails elfpmt : entityListForPreMonthTotal) {
				BigDecimal elftmt_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
				BigDecimal elfpmt_brand_id = (BigDecimal) elfpmt.getMap().get("brand_id");
				if (elfpmt_brand_id.longValue() == elftmt_brand_id.longValue()) {
					BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
					BigDecimal sales_revenue_pre = (BigDecimal) elfpmt.getMap().get("sales_revenue");
					Double sales_revenue_pre_db = sales_revenue_pre.doubleValue();// 上月销额
					Double annulus = (sales_revenue_db - sales_revenue_pre_db) / sales_revenue_pre_db;
					elftmt.getMap().put("annulus", annulus);// 环比
					elftmt.getMap().put("sales_revenue_pre", sales_revenue_pre_db);
				}
			}
		}

		for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
			for (JxcSellBillDetails elfpyt : entityListForPreYearTotal) {
				BigDecimal elftm_brand_id = (BigDecimal) elftmt.getMap().get("brand_id");
				BigDecimal elfpy_brand_id = (BigDecimal) elfpyt.getMap().get("brand_id");
				if (elftm_brand_id.longValue() == elfpy_brand_id.longValue()) {

					BigDecimal sales_revenue_pre_year = (BigDecimal) elfpyt.getMap().get("sales_revenue");
					Double sales_revenue_pre_year_db = sales_revenue_pre_year.doubleValue();// 上年同期
					BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
					Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额
					Double year_on_year = (sales_revenue_db - sales_revenue_pre_year_db) / sales_revenue_pre_year_db;// 同比
					elftmt.getMap().put("year_on_year", year_on_year);
					elftmt.getMap().put("sales_revenue_pre_year", sales_revenue_pre_year_db);
				}
			}
		}

		for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
			BigDecimal sales_revenue = (BigDecimal) elftmt.getMap().get("sales_revenue");
			Double sales_revenue_db = sales_revenue.doubleValue();// 本月销额

			BigDecimal cost_sales = (BigDecimal) elftmt.getMap().get("cost_sales");
			Double cost_sales_db = cost_sales.doubleValue();// 本月成本
			Double maori = sales_revenue_db - cost_sales_db;
			Double maori_rate = maori / sales_revenue_db;
			elftmt.getMap().put("maori", maori);// 毛利
			elftmt.getMap().put("maori_rate", maori_rate);// 毛利率
		}

		for (JxcSellBillDetails elftmt : entityListForThisMonthTotal) {
			entityListForThisMonth.add(elftmt);
		}
		ComparatorUser comparator = new ComparatorUser();
		Collections.sort(entityListForThisMonth, comparator);

		createExcelFile(request, response, entityListForThisMonth, year, month);

		return null;
	}

	public void createExcelFile(HttpServletRequest request, HttpServletResponse response, List<JxcSellBillDetails> entityListForThisMonth, String year, String month)
			throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		if (null != entityListForThisMonth && entityListForThisMonth.size() > 0) {
			// for (EntpInfo ei : entpInfoList) {
			// ei.getMap().put("lat", StringUtils.split(ei.getEntp_latlng(), ",")[0]);
			// ei.getMap().put("lng", StringUtils.split(ei.getEntp_latlng(), ",")[1]);
			// }
			model.put("entityList", entityListForThisMonth);
		}
		model.put("title", "门店经营分析表");
		model.put("year", year);
		model.put("month", month);
		String content = getFacade().getTemplateService().getContent("jxc/jxc_stores_analysis_list.ftl", model);
		// 下载文件出现乱码时，请参见此处
		String fname = EncryptUtilsV2.encodingFileName("门店经营分析表.xls");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
	}
}
