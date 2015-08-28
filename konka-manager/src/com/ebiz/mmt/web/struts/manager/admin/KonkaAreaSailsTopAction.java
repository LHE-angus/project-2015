package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,hao
 * @version 2013-09-22
 */
public class KonkaAreaSailsTopAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;

		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		ChannelDataImport entity = new ChannelDataImport();

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
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

		// 去年
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
		String this_date_e = this_year + "-" + this_month + "-" + getMaxDay(Integer.valueOf(this_month),Integer.valueOf(this_year)) + " 23:59:59";

		// 去年数据时间段
		String last_date_s = last_year + "-" + this_month + "-01 00:00:00";
		String last_date_e = last_year + "-" + this_month + "-" + getMaxDay(Integer.valueOf(this_month),Integer.valueOf(last_year)) + " 23:59:59";

		entity.getMap().put("this_year", this_year);
		entity.getMap().put("this_month", Integer.valueOf(this_month));
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);
		entity.getMap().put("last_date_s", last_date_s);
		entity.getMap().put("last_date_e", last_date_e);

		request.setAttribute("yyyy", this_year);
		request.setAttribute("mm", Integer.valueOf(this_month));
		List<ChannelDataImport> entityList = super.getFacade().getChannelDataImportService()
				.getChannelDataImportForJybTop(entity);

		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);

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
		
		if (entityList.size() > 0) {
			for (ChannelDataImport temp : entityList) {

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
			}
		}

		// 合计
		request.setAttribute("t_hs_money", t_hs_money);
		request.setAttribute("t_l_hs_money", t_l_hs_money);
		request.setAttribute("t_d3_num", t_d3_num);
		request.setAttribute("t_int_num", t_int_num);
		request.setAttribute("t_db_num", t_db_num);
		request.setAttribute("t_pb_num", t_pb_num);
		request.setAttribute("t_ratio", t_ratio);
		request.setAttribute("t_cur_money_of_month_task", t_cur_money_of_month_task);
		request.setAttribute("sale_num", sale_num);
		request.setAttribute("sale_all_price", sale_all_price);

		if (t_cur_money_of_month_task.compareTo(new BigDecimal(0)) != 0) {// 合计任务完成率
			request.setAttribute("t_rw_wcl", (t_hs_money.subtract(t_cur_money_of_month_task)).divide(
					t_cur_money_of_month_task, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
		}
		if (t_l_hs_money.compareTo(new BigDecimal(0)) != 0) {// 合计同步增长率
			request.setAttribute("t_tb_zzl", (t_hs_money.subtract(t_l_hs_money)).divide(t_l_hs_money, 4,
					BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
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
		
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		request.setAttribute("entityList", entityList);
		request.setAttribute("size", entityList.size());

		return mapping.findForward("list");
	}

	public int getMaxDay(int mm,int year) {
		int day = 0;
		if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			day = 31;
		} else if (mm == 2) {
			if (year % 4 == 0) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = 30;
		}
		return day;

	}
}
