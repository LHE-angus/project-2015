package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,hao
 * @version 2013-09-14
 */
public class KonkaJybSailsTopAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		ChannelDataImport entity = new ChannelDataImport();

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Date now = new Date();
		String this_year = formaty.format(now);// 当前年份
		String this_month = formatm.format(now);// 当前月份
		// 去年
		Integer last_year = Integer.valueOf(formaty.format(now)) - 1;

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

		if (entityList.size() > 0) {
			for (ChannelDataImport temp : entityList) {
				BigDecimal cur_money_of_month_task = new BigDecimal(temp.getMap().get("cur_money_of_month_task")
						.toString());
				BigDecimal hs_money = new BigDecimal(temp.getMap().get("hs_money").toString());
				BigDecimal l_hs_money = new BigDecimal(temp.getMap().get("l_hs_money").toString());
				BigDecimal hs_num = new BigDecimal(temp.getMap().get("hs_num").toString());
				BigDecimal d3_num = new BigDecimal(temp.getMap().get("d3_num").toString());
				BigDecimal int_num = new BigDecimal(temp.getMap().get("int_num").toString());
				BigDecimal www_num = new BigDecimal(temp.getMap().get("www_num").toString());

				// 计算任务完成率
				if (!"0".equals(temp.getMap().get("cur_money_of_month_task").toString()))
					temp.getMap().put(
							"rw_wcl",
							hs_money.divide(cur_money_of_month_task, 4, BigDecimal.ROUND_HALF_EVEN).multiply(
									new BigDecimal(100)));

				// 计算同比增长率
				if (!"0".equals(temp.getMap().get("l_hs_money").toString()))
					temp.getMap().put(
							"tb_zml",
							(hs_money.subtract(l_hs_money)).divide(l_hs_money, 4, BigDecimal.ROUND_HALF_EVEN).multiply(
									new BigDecimal(100)));

				// 计算3D 占比
				if (!"0".equals(temp.getMap().get("d3_num").toString())
						|| !"0".equals(temp.getMap().get("int_num").toString())
						|| !"0".equals(temp.getMap().get("www_num").toString()))
					temp.getMap().put(
							"d3_zb",
							d3_num.divide((d3_num.add(int_num).add(www_num)), 4, BigDecimal.ROUND_HALF_EVEN).multiply(
									new BigDecimal(100)));

				// 计算平均单价
				if (!"0".equals(temp.getMap().get("hs_num").toString()))
					temp.getMap().put("price",
							(hs_money.multiply(new BigDecimal(10000))).divide(hs_num, 2, BigDecimal.ROUND_HALF_EVEN));

				// 统计当月经办的零售额和零售量
				KonkaMobileSailData kData = new KonkaMobileSailData();
				kData.setOffice_name(temp.getMap().get("dept_name").toString());
				kData.getMap().put("report_date_s", this_date_s);
				kData.getMap().put("report_date_e", this_date_s);
				List<KonkaMobileSailData> kDataList = super.getFacade().getKonkaMobileSailDataService()
						.getKonkaMobileSailDataForFgsTopCount(kData);
				if (kDataList.size() > 0) {
					temp.getMap().put("all_price", kDataList.get(0).getMap().get("all_price"));
					temp.getMap().put("num", kDataList.get(0).getMap().get("num"));
				} else {
					temp.getMap().put("all_price", "0");
					temp.getMap().put("num", "0");
				}
			}
		}

		request.setAttribute("entityList", entityList);

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
