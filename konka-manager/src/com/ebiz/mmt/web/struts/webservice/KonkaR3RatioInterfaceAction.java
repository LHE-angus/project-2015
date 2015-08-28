package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.ParseException;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-13
 */
public abstract class KonkaR3RatioInterfaceAction extends BaseAction {

	public String getKonkaR3SellToJson(String user_id, String year, String month) throws ParseException {
		// DynaBean dynaBean = (DynaBean) form;

		// String user_id = (String) dynaBean.get("user_id");// 用户id

		if (StringUtils.isBlank(user_id)) {
			// 用户信息为空！
			// super.renderJson(response,
			// "{\"status\":\"-1\",\"error\":[\"error_code\":\"100\",\"msg\":\" 用户信息为空！\"]}");
			// return null;
			return "{\"status\":\"-1\",\"error\":[\"error_code\":\"100\",\"msg\":\" 用户信息为空！\"]}";
		}
		if (StringUtils.isBlank(year)) {

			return "{\"status\":\"-1\",\"error\":[\"error_code\":\"500\",\"msg\":\" 年度为空！\"]}";
		}
		if (StringUtils.isBlank(month)) {

			return "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\" 月份为空！\"]}";
		}

		PeProdUser entity = new PeProdUser();
		entity.setId(new Long(user_id));
		entity.setIs_del(0);

		PeProdUser userInfo = super.getFacade().getPeProdUserService().getPeProdUser(entity);
		if (null == userInfo) {
			// 用户不存在！
			// super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"200\",\"msg\":\" 用户不存在！\"]}");
			// return null;
			return "{\"status\":\"-1\",\"error\":[\"error_code\":\"200\",\"msg\":\" 用户不存在！\"]}";
		}
		String dept_id = "";
		String dept_type = "";
		if (userInfo.getUser_type() == 0) {
			// 康佳总部
			// dynaBean.set("dept_type", "1");
			dept_type = "1";
		} else if (userInfo.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				// super.renderJson(response,
				// "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"用户所属部门为空！\"]}");
				// return null;
				return "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"用户所属部门为空！\"]}";
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				// dynaBean.set("dept_type", "3");
				dept_type = "3";
				// dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else {
				// 用户没有操作权限！
				// super.renderJson(response,
				// "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"用户没有操作权限！\"]}");
				// return null;
				return "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"用户没有操作权限！\"]}";
			}
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		String now_year = String.valueOf(calendar.get(Calendar.YEAR));
		String now_month = String.valueOf(calendar.get(Calendar.MONTH) + 1);

		String year_start = year;
		String year_end = year;
		String month_start = month;
		String month_end = month;

		String day_first = "";
		String day_last = "";
		if (now_year.equals(year) && now_month.equals(month)) {
			// 查询当前月份
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			day_first = df.format(calendar.getTime());

			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
			day_last = df.format(calendar.getTime());
		} else {
			if (StringUtils.isNotBlank(month)) {
				switch (Integer.valueOf(month)) {
				case 1:
					month = "01";
					break;
				case 2:
					month = "02";
					break;
				case 3:
					month = "03";
					break;
				case 4:
					month = "04";
					break;
				case 5:
					month = "05";
					break;
				case 6:
					month = "06";
					break;
				case 7:
					month = "07";
					break;
				case 8:
					month = "08";
					break;
				case 9:
					month = "09";
					break;
				default:
					break;
				}
			}
			day_first = year + "-" + month + "-01";
			calendar.setTime(df.parse(day_first));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			day_last = df.format(calendar.getTime());
		}

		if (StringUtils.isNotBlank(month_start)) {
			switch (Integer.valueOf(month_start)) {
			case 1:
				month_start = "01";
				break;
			case 2:
				month_start = "02";
				break;
			case 3:
				month_start = "03";
				break;
			case 4:
				month_start = "04";
				break;
			case 5:
				month_start = "05";
				break;
			case 6:
				month_start = "06";
				break;
			case 7:
				month_start = "07";
				break;
			case 8:
				month_start = "08";
				break;
			case 9:
				month_start = "09";
				break;
			default:
				break;
			}
		}
		if (StringUtils.isNotBlank(month_end)) {
			switch (Integer.valueOf(month_end)) {
			case 1:
				month_end = "01";
				break;
			case 2:
				month_end = "02";
				break;
			case 3:
				month_end = "03";
				break;
			case 4:
				month_end = "04";
				break;
			case 5:
				month_end = "05";
				break;
			case 6:
				month_end = "06";
				break;
			case 7:
				month_end = "07";
				break;
			case 8:
				month_end = "08";
				break;
			case 9:
				month_end = "09";
				break;
			default:
			}
		}

		String rank_type = "";
		String tj_type = "";
		String show_top = "";

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");

		String rw_sale = "";
		BigDecimal total_price = new BigDecimal("0");
		BigDecimal total_num = new BigDecimal("0");
		BigDecimal rw_money = new BigDecimal("0");
		int top_num = 10;
		if (dept_type.equals("1")) {
			// 康佳总部
			List<?> entityList = this.getR3SellForRW_100(rank_type, tj_type, year_start, month_start, year_end,
					month_end, day_first, day_last, dept_id, show_top);

			if (null != entityList && entityList.size() > 0) {
				for (int i = 0; i < entityList.size(); i++) {
					Object[] obj = (Object[]) entityList.get(i);
					total_price = total_price.add(new BigDecimal((obj[2] == null ? "0" : obj[2].toString())));
					total_num = total_num.add(new BigDecimal((obj[3] == null ? "0" : obj[3].toString())));
					rw_money = rw_money.add(new BigDecimal(obj[5] == null ? "0" : obj[5].toString()));
					if (i < top_num) {
						// 只展示前十
						sb = sb.append("{");
						sb = sb.append("\"dept_id\":\"").append((obj[0] == null ? "" : obj[0].toString()))
								.append("\",");// 分公司部门id
						sb = sb.append("\"dept_name\":\"").append((obj[1] == null ? "" : obj[1].toString()))
								.append("\",");// 分公司名称
						sb = sb.append("\"all_price\":\"").append((obj[2] == null ? "0" : obj[2].toString()))
								.append("\",");// 实际销售额
						sb = sb.append("\"all_num\":\"").append((obj[3] == null ? "0" : obj[3].toString()))
								.append("\",");// 实际销售量
						sb = sb.append("\"ratio\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\",");// 分公司任务系数
						sb = sb.append("\"rw_money\":\"").append((obj[5] == null ? "0" : obj[5].toString()))
								.append("\",");// 任务销售额
						sb = sb.append("\"sale\":\"").append((obj[6] == null ? "0" : obj[6].toString())).append("\"");// 任务完成%
						sb = sb.append("},");
					}
				}
			}
		} else if (dept_type.equals("3")) {
			// 分公司
			String l4_dept_id = "";
			String l5_dept_id = "";
			List<?> entityList = this.getR3SellForRW_200(rank_type, tj_type, year_start, month_start, year_end,
					month_end, day_first, day_last, dept_id, l4_dept_id, l5_dept_id, show_top);

			if (null != entityList && entityList.size() > 0) {
				for (int i = 0; i < entityList.size(); i++) {
					Object[] obj = (Object[]) entityList.get(i);
					total_price = total_price.add(new BigDecimal((obj[4] == null ? "0" : obj[4].toString())));
					total_num = total_num.add(new BigDecimal((obj[5] == null ? "0" : obj[5].toString())));
					rw_money = rw_money.add(new BigDecimal(obj[7] == null ? "0" : obj[7].toString()));
					if (i < top_num) {
						// 只展示前十
						sb = sb.append("{");
						sb = sb.append("\"dept_id\":\"").append((obj[2] == null ? "" : obj[2].toString()))
								.append("\",");// 经办id
						sb = sb.append("\"dept_name\":\"").append((obj[3] == null ? "" : obj[3].toString()))
								.append("\",");// 经办名称
						sb = sb.append("\"all_price\":\"").append((obj[4] == null ? "0" : obj[4].toString()))
								.append("\",");// 实际销售额
						sb = sb.append("\"all_num\":\"").append((obj[5] == null ? "0" : obj[5].toString()))
								.append("\",");// 实际销售量
						sb = sb.append("\"ratio\":\"").append((obj[6] == null ? "0" : obj[6].toString())).append("\",");// 经办任务系数
						sb = sb.append("\"rw_money\":\"").append((obj[7] == null ? "0" : obj[7].toString()))
								.append("\",");// 任务销售额
						sb = sb.append("\"sale\":\"").append((obj[8] == null ? "0" : obj[8].toString())).append("\"");// 任务完成%
						sb = sb.append("},");
					}
				}
			}
		}

		List<?> list = this.getSellRankFor100(day_first, day_last, dept_id);

		if (rw_money.compareTo(new BigDecimal(0)) != 0) {
			rw_sale = String.valueOf(total_price.divide(rw_money, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		} else {
			rw_sale = "100";
		}

		String sell_money = "0";
		String sell_num = "0";
		if (null != list && list.size() > 0) {
			Object[] obj = (Object[]) list.get(0);
			sell_money = obj[0] == null ? "0" : obj[0].toString();
			sell_num = obj[1] == null ? "0" : obj[1].toString();
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"total_price\":\""
				+ String.valueOf(total_price) + "\",\"total_num\":\"" + String.valueOf(total_num) + "\",\"rw_sale\":\""
				+ rw_sale + "\",\"sell_money\":\"" + sell_money + "\",\"sell_num\":\"" + sell_num
				+ "\",\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		// super.renderJson(response, sb_str);
		// return null;
		return sb_str;
	}

	public ActionForward getKonkaR3SellToJsonTest(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String user_id = (String) dynaBean.get("user_id");// 用户id
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String str = this.getKonkaR3SellToJson(user_id, year, month);
		request.setAttribute("json_str", str);
		super.renderJson(response, str);
		return null;
	}

	public List<?> getR3SellForRW_100(String rank_type, String tj_type, String year_start, String month_start,
			String year_end, String month_end, String day_first, String day_last, String dept_id, String show_top) {
		List<String> array = new ArrayList<String>();
		String sql = " select t.* from ( select dd.* from ( select cc.*,round(decode(cc.rw_money, 0, '0', cc.all_price / cc.rw_money), 4) * 100 as sale";
		sql += " from (select kk.dept_id,kk.dept_name,value(aa.all_price,0) as all_price,value(aa.all_num,0) as all_num,value(bb.fgs_ratio, 0) as fgs_ratio,value(bb.rw_money, 0) as rw_money ";
		sql += " from konka_dept kk left join (select t.dept_id,t.dept_name,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
		sql += " from v_a_details_of_purchase t where 1 = 1 ";

		// R3销售时间的选择 t.create_date
		if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(day_first)) {
			sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(day_first + " 00:00:00");
		}
		if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(day_last)) {
			sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(day_last + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " group by t.dept_id, t.dept_name ) aa on kk.dept_id = aa.dept_id ";
		sql += " left join (select (select k.dept_id from konka_dept k where k.dept_sn = a.fgs_sn) as dept_id,round(value(sum(a.ratio), 0),6) as fgs_ratio, ";
		sql += " value(sum(a.ratio), 0) * (select value(sum(b.m), 0) from konka_plan_money b where 1 = 1  and b.p = 1 ";

		// 任务销售额的时间选择
		if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
			sql += " and b.y >= ? ";
			array.add(year_start + month_start);
		}
		if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
			sql += " and b.y <= ? ";
			array.add(year_end + month_end);
		}
		sql += " ) * 10000 as rw_money from konka_plan_ratio a where 1 = 1 and a.fgs_sn = a.dept_sn ";

		// 任务系数的年度选择
		if (StringUtils.isNotBlank(year_start)) {
			sql += " and a.y = ? ";
			array.add(year_start);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.fgs_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
			array.add(dept_id);
		}
		sql += " group by a.fgs_sn) bb on kk.dept_id = bb.dept_id where 1 = 1 and kk.dept_type = 3 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and kk.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) cc ) dd where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by dd.sale desc,dd.all_num desc,dd.all_price desc,dd.dept_name asc,dd.dept_id asc ";
		} else {
			sql += " order by dd.sale desc,dd.all_price desc,dd.all_num desc,dd.dept_name asc,dd.dept_id asc ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}
		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getR3SellForRW_200(String rank_type, String tj_type, String year_start, String month_start,
			String year_end, String month_end, String day_first, String day_last, String dept_id, String l4_dept_id,
			String l5_dept_id, String show_top) {
		List<String> array = new ArrayList<String>();
		String sql = " select t_.* from ( select dd.* from ( select cc.dept_id,(select k1.dept_name from konka_dept k1 where k1.dept_id = cc.dept_id) as dept_name,";
		sql += " cc.l4_dept_id,cc.l4_dept_name,cc.all_price,cc.all_num,cc.jb_ratio,cc.rw_money,";
		sql += " round(decode(cc.rw_money, 0, '0', cc.all_price / cc.rw_money), 4) * 100 as sale ";
		sql += " from (select kk.par_id as dept_id,kk.dept_id as l4_dept_id,kk.dept_name as l4_dept_name,value(aa.all_price,0) as all_price,value(aa.all_num,0) as all_num,value(bb.jb_ratio, 0) as jb_ratio,value(bb.rw_money, 0) as rw_money from konka_dept kk left join (select ";
		sql += " t.L4_DEPT_ID as dept_id,t.L4_DEPT_NAME as dept_name,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
		sql += " from v_a_details_of_purchase t where 1 = 1 and t.l4_dept_id is not null ";

		// R3销售时间的选择 t.create_date
		if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(day_first)) {
			sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(day_first + " 00:00:00");
		}
		if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(day_last)) {
			sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(day_last + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		sql += " group by t.L4_DEPT_ID,t.L4_DEPT_NAME ) aa on kk.dept_id = aa.dept_id ";
		sql += " left join (select (select k.dept_id from konka_dept k where k.dept_sn = a.dept_sn) as dept_id,round(value(sum(a.ratio), 0), 6) as jb_ratio,";
		sql += " value(sum(a.ratio), 0) * (select value(sum(b.m), 0) from konka_plan_money b  where 1 = 1 and b.p = 1 ";

		// 任务销售额的时间选择
		if (StringUtils.isNotBlank(year_start) && StringUtils.isNotBlank(month_start)) {
			sql += " and b.y >= ? ";
			array.add(year_start + month_start);
		}
		if (StringUtils.isNotBlank(year_end) && StringUtils.isNotBlank(month_end)) {
			sql += " and b.y <= ? ";
			array.add(year_end + month_end);
		}

		sql += " ) * 10000 as rw_money from konka_plan_ratio a where 1 = 1 and a.fgs_sn != a.dept_sn ";
		// 任务系数的年度选择
		if (StringUtils.isNotBlank(year_start)) {
			sql += " and a.y = ? ";
			array.add(year_start);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.fgs_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.dept_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
			array.add(l4_dept_id);
		}
		sql += " group by a.dept_sn) bb on kk.dept_id = bb.dept_id where 1 = 1 and kk.par_id in (select kd.dept_id from konka_dept kd where kd.dept_type = 3 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and kd.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) ";
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and kk.dept_id = ? ";
			array.add(l4_dept_id);
		}
		sql += " ) cc ) dd where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by dd.sale desc,dd.all_num desc,dd.all_price desc,dd.dept_name asc,dd.dept_id asc,dd.l4_dept_id asc,dd.l4_dept_name asc  ";
		} else {
			sql += " order by dd.sale desc,dd.all_price desc,dd.all_num desc,dd.dept_name asc,dd.dept_id asc,dd.l4_dept_id asc,dd.l4_dept_name asc  ";
		}

		// 排名显示数量
		sql += " ) t_ where 1 = 1 ";
		if (StringUtils.isNotBlank(show_top)) {
			sql += " and rownum <= ? ";
			array.add(show_top);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getSellRankFor100(String sell_date_start, String sell_date_end, String dept_id) {
		List<String> array = new ArrayList<String>();

		String sql = " select value(sum(t.all_price), 0) as all_price ,value(sum(t.num), 0) as all_num";
		sql += " from v_a_details_of_sales t where 1 = 1 ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + "23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}

		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}
}