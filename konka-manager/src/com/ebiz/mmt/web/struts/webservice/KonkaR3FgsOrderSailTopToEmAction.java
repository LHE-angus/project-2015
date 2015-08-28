package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-12-17
 */
public class KonkaR3FgsOrderSailTopToEmAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		ChannelDataImport2 entity = new ChannelDataImport2();

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		SimpleDateFormat fday = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();

		String this_year = null;// 当前年份
		String this_month = null;// 当前月份
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
		}

		/********************* 初始换基础数据 **********************************/
		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		// 取最新時間
		ChannelDataImport cData = new ChannelDataImport();
		cData.getMap().put("max_date", true);
		cData = super.getFacade().getChannelDataImportService().getChannelDataImport(cData);
		request.setAttribute("new_date", cData.getImport_date());
		/*******************************************************************/

		// 今年时间区间
		KonkaSpList ks = getR3Date(this_year, this_month);
		if (null == ks) {
			return mapping.findForward("list");
		}

		// 去年时间区间
		Integer last_year = Integer.valueOf(this_year) - 1;

		KonkaSpList yks = getR3Date(last_year.toString(), this_month);
		if (null == yks) {
			return mapping.findForward("list");
		}

		// 昨日时间区间
		String y_day = getYesDay(ks, 1);
		entity.getMap().put("this_day_s", y_day + " 00:00:00");
		entity.getMap().put("this_day_e", y_day + " 23:59:59");

		// 前日时间区间
		if (y_day.split("-")[2].equals("01")) {
			entity.getMap().put("l_day_s", y_day + " 00:00:00");
			entity.getMap().put("l_day_e", y_day + " 23:59:59");
		} else {
			String l_y_day = getYesDay(ks, 2);
			entity.getMap().put("l_day_s", l_y_day + " 00:00:00");
			entity.getMap().put("l_day_e", l_y_day + " 23:59:59");
		}

		// 今年数据时间段
		String this_date_s = fday.format(ks.getS_date()) + " 00:00:00";
		String this_date_e = fday.format(ks.getE_date()) + " 23:59:59";

		// 去年数据时间段
		String last_date_s = fday.format(yks.getS_date()) + " 00:00:00";
		String last_date_e = fday.format(yks.getE_date()) + " 23:59:59";

		entity.getMap().put("this_year", this_year);
		entity.getMap().put("this_month", Integer.valueOf(this_month));
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);
		entity.getMap().put("last_date_s", last_date_s);
		entity.getMap().put("last_date_e", last_date_e);

		request.setAttribute("yyyy", this_year);
		request.setAttribute("mm", Integer.valueOf(this_month));
		List<ChannelDataImport2> entityList = super.getFacade().getChannelDataImport2Service()
				.getChannelDataImport2ForFgsTop(entity);

		BigDecimal t_hs_money = new BigDecimal(0);// 合计金额
		BigDecimal t_l_hs_money = new BigDecimal(0);// 合计历史金额
		BigDecimal t_d3_num = new BigDecimal(0);// 3D销量
		BigDecimal t_int_num = new BigDecimal(0);// 安卓销量
		BigDecimal t_db_num = new BigDecimal(0);// 大板销量
		BigDecimal t_pb_num = new BigDecimal(0);// 平板销量
		BigDecimal t_ratio = new BigDecimal(0);// 任务系数
		BigDecimal t_cur_money_of_month_task = new BigDecimal(0);// 任务销售额
		BigDecimal sale_num = new BigDecimal(0);// 零售量
		BigDecimal sale_all_price = new BigDecimal(0);// 零售额
		BigDecimal t_k4_num = new BigDecimal(0);// 4K销量
		BigDecimal t_dr_hs_money = new BigDecimal(0);// 当日结算额

		if (entityList.size() > 0) {
			for (ChannelDataImport2 temp : entityList) {

				// 合计
				BigDecimal hs_money = new BigDecimal(temp.getMap().get("hs_money").toString());
				BigDecimal l_hs_money = new BigDecimal(temp.getMap().get("l_hs_money").toString());
				BigDecimal d3_num = new BigDecimal(temp.getMap().get("d3_num").toString());
				BigDecimal int_num = new BigDecimal(temp.getMap().get("int_num").toString());
				BigDecimal db_num = new BigDecimal(temp.getMap().get("db_num").toString());
				BigDecimal pb_num = new BigDecimal(temp.getMap().get("pb_num").toString());
				BigDecimal ratio = new BigDecimal(temp.getMap().get("ratio").toString());
				BigDecimal cur_money_of_month_task = new BigDecimal(temp.getMap().get("cur_money_of_month_task")
						.toString());
				BigDecimal num = new BigDecimal(temp.getMap().get("num").toString());
				BigDecimal all_price = new BigDecimal(temp.getMap().get("all_price").toString());
				BigDecimal k4_num = new BigDecimal(temp.getMap().get("k4_num").toString());
				BigDecimal dr_hs_money = new BigDecimal(temp.getMap().get("dr_hs_money").toString());

				temp.getMap().put("k4_num", k4_num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("dr_hs_money", dr_hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("hs_money", hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("l_hs_money", l_hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("d3_num", d3_num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("int_num", int_num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("db_num", db_num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("pb_num", pb_num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("ratio", ratio.setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("cur_money_of_month_task",
						cur_money_of_month_task.setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("num", num.setScale(0, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("all_price", all_price.setScale(2, BigDecimal.ROUND_HALF_UP));

				temp.getMap().put("rw_wcl",
						new BigDecimal(temp.getMap().get("rw_wcl").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("tb_zzl",
						new BigDecimal(temp.getMap().get("tb_zzl").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("k4_zb",
						new BigDecimal(temp.getMap().get("k4_zb").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("d3_zb",
						new BigDecimal(temp.getMap().get("d3_zb").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("db_zb",
						new BigDecimal(temp.getMap().get("db_zb").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
				temp.getMap().put("price",
						new BigDecimal(temp.getMap().get("price").toString()).setScale(2, BigDecimal.ROUND_HALF_UP));

				t_hs_money = t_hs_money.add(hs_money);
				t_l_hs_money = t_l_hs_money.add(l_hs_money);
				t_d3_num = t_d3_num.add(d3_num);
				t_int_num = t_int_num.add(int_num);
				t_db_num = t_db_num.add(db_num);
				t_pb_num = t_pb_num.add(pb_num);
				t_ratio = t_ratio.add(ratio);
				t_cur_money_of_month_task = t_cur_money_of_month_task.add(cur_money_of_month_task);
				sale_num = sale_num.add(num);
				sale_all_price = sale_all_price.add(all_price);
				t_k4_num = t_k4_num.add(k4_num);
				t_dr_hs_money = t_dr_hs_money.add(dr_hs_money);
			}
		}

		// 合计
		request.setAttribute("t_hs_money", t_hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_l_hs_money", t_l_hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_d3_num", t_d3_num.setScale(0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_int_num", t_int_num.setScale(0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_db_num", t_db_num.setScale(0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_pb_num", t_pb_num.setScale(0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_ratio", t_ratio.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_cur_money_of_month_task", t_cur_money_of_month_task.setScale(2,
				BigDecimal.ROUND_HALF_UP));
		request.setAttribute("sale_num", sale_num.setScale(0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("sale_all_price", sale_all_price.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_dr_hs_money", t_dr_hs_money.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_k4_num", t_k4_num.setScale(0, BigDecimal.ROUND_HALF_UP));

		if (t_cur_money_of_month_task.compareTo(new BigDecimal(0)) != 0) {// 合计任务完成率
			request.setAttribute("t_rw_wcl", t_hs_money
					.divide(t_cur_money_of_month_task, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
		}
		if (t_l_hs_money.compareTo(new BigDecimal(0)) != 0) {// 合计同步增长率
			request.setAttribute("t_tb_zzl", (t_hs_money.subtract(t_l_hs_money)).divide(t_l_hs_money, 4,
					BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
		}
		if (t_pb_num.compareTo(new BigDecimal(0)) != 0) {// 合计4K占比
			request.setAttribute("t_k4_zb", (t_k4_num.divide(t_pb_num, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100))));
		}
		if (t_pb_num.compareTo(new BigDecimal(0)) != 0) {// 合计3D占比
			request.setAttribute("t_d3_zb", (t_d3_num.divide(t_pb_num, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100))));
		}
		if (t_pb_num.compareTo(new BigDecimal(0)) != 0) {// 合计大板占比
			request.setAttribute("t_db_zb", (t_db_num.divide(t_pb_num, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100))));
		}
		if (t_pb_num.compareTo(new BigDecimal(0)) != 0) {// 平均单价
			request.setAttribute("t_price", (t_hs_money.divide(t_pb_num, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100))));
		}

		if (sale_num.compareTo(new BigDecimal(0)) != 0) {// 平均单价
			request.setAttribute("sale_price", ((sale_all_price.multiply(new BigDecimal(10000))).divide(sale_num, 4,
					BigDecimal.ROUND_HALF_EVEN)));
		}

		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);

		request.setAttribute("entityList", entityList);
		request.setAttribute("size", entityList.size());

		return mapping.findForward("list");
	}

	// 跟据今天的日期去昨日时间
	public String getYesDay(KonkaSpList k, int i) {

		Calendar c = Calendar.getInstance();
		Date date = null;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");

		// 所选月份开始日期
		Long s_date = Long.valueOf(fmt.format(k.getS_date()));
		// 所选月份结束日期
		Long e_date = Long.valueOf(fmt.format(k.getE_date()));

		// 今天日期
		Long this_date = Long.valueOf(fmt.format(new Date()));

		if (this_date >= s_date && this_date <= e_date) {
			date = new Date();
		} else {
			date = k.getE_date();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - i);

		return fmt1.format(c.getTime());
	}
}
