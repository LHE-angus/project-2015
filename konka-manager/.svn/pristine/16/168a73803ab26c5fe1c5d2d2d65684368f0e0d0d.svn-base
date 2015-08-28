package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-11
 */
public class KonkaR3BackMoneyReportInterfaceAction extends BaseAction {

	public ActionForward getR3BackMoneyListToJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");// 分公司名称
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部名称
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处名称
		String year = (String) dynaBean.get("year");// 年度
		String month_start = (String) dynaBean.get("month_start");// 开始月份
		String month_end = (String) dynaBean.get("month_end");// 结束月份
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");// 业务员
		String customer_name = (String) dynaBean.get("customer_name");// 客户名称
		String user_name = (String) dynaBean.get("user_name");// 用户名
		String password = (String) dynaBean.get("password");// 密码

		if (StringUtils.isBlank(user_name)) {
			// 用户名不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"100\",\"msg\":\"用户名不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(password)) {
			// 密码不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"200\",\"msg\":\"密码不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(year)) {
			// 年度不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"年度不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(month_start)) {
			// 开始月份不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"开始月份不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(month_end)) {
			// 结束月份不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"500\",\"msg\":\"结束月份不能为空！\"]}");
			return null;
		}

		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		entity.getRow().setCount(2);
		entity.setUser_name(user_name);
		entity.setIs_del(0);
		// entity.setIs_xx_user(0L); //不是新兴渠道用户 -----改在SQL中写死，以免各个调用模块都需要\
		entity.getMap().put("user_type_in", "0,1"); // Ren zhong, Add by2013-06-06

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			// 用户不存在！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户不存在！\"]}");
			return null;
		} else if (userInfoList.size() > 1) {
			// 用户名重复！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户名重复！\"]}");
			return null;
		}
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(password));
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			// 密码错误！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"800\",\"msg\":\"密码错误！\"]}");
			return null;
		}

		if (userInfo.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (userInfo.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"900\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"1000\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		// 回款月份区间
		if (StringUtils.isBlank(month_start)) {
			month_start = "1";
		}
		if (StringUtils.isBlank(month_end)) {
			month_start = "12";
		}
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}
		//

		// 任务系数需要的月份格式
		log.info("*********month_start处理前：" + month_start);
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
		log.info("*********month_start处理后：" + month_start);
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
		//

		List<?> entityList = this.getR3BackMoneyList(year, month_start, month_end, dept_id, l4_dept_id, l5_dept_id,
				ywy_user_name, customer_name);

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		if (null != entityList && entityList.size() > 0) {
			for (int i = 0; i < entityList.size(); i++) {
				Object[] obj = (Object[]) entityList.get(i);
				sb = sb.append("{");
				sb = sb.append("\"dept_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// dept_id
				sb = sb.append("\"dept_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 部门名称
				sb = sb.append("\"ratio\":\"").append((obj[17] == null ? "0" : obj[17].toString())).append("\",");// 任务系数
				sb = sb.append("\"rw_money\":\"").append((obj[18] == null ? "0" : obj[18].toString())).append("\",");// 任务销售额（元）
				// 4---15 12个月
				BigDecimal backmoney = new BigDecimal("0");
				for (int j = start_num; j <= end_num; j++) {
					backmoney = backmoney.add(new BigDecimal((obj[j + 3] == null ? "0" : obj[j + 3].toString())));
				}
				sb = sb.append("\"back_money\":\"").append(backmoney.toString()).append("\",");// 回款（元）
				BigDecimal rw_money = new BigDecimal((obj[18] == null ? "0" : obj[18].toString()));
				String sale = "";
				if (rw_money.compareTo(new BigDecimal(0)) != 0) {
					sale = String.valueOf(backmoney.divide(rw_money, 4, BigDecimal.ROUND_HALF_EVEN)
							.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					sb = sb.append("\"sale\":\"").append(sale).append("\",");// 任务完成%
				} else {
					sb = sb.append("\"sale\":\"").append("100\",");// 任务完成%
				}

				sb = sb.append("},");
			}
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public List<?> getR3BackMoneyList(String year, String month_start, String month_end, String dept_id,
			String l4_dept_id, String l5_dept_id, String ywy_user_name, String customer_name) {
		List<String> array = new ArrayList<String>();

		String sql = " select aa.dept_id,aa.dept_name,bb.*,cc.* from konka_dept aa left join ";
		if (StringUtils.isNotBlank(dept_id)) {
			// 经办
			sql += " (select a.l4_dept_id as dept_id,a.l4_dept_name as dept_name, ";
		} else {
			// 分公司
			sql += " (select a.dept_id,a.dept_name, ";
		}

		sql += " sum(value(a.column_1, 0) * 10000) as column_1,sum(value(a.column_2, 0) * 10000) as column_2,";
		sql += " sum(value(a.column_3, 0) * 10000) as column_3,sum(value(a.column_4, 0) * 10000) as column_4,sum(value(a.column_5, 0) * 10000) as column_5,sum(value(a.column_6, 0) * 10000) as column_6, ";
		sql += " sum(value(a.column_7, 0) * 10000) as column_7,sum(value(a.column_8, 0) * 10000) as column_8,sum(value(a.column_9, 0) * 10000) as column_9,sum(value(a.column_10, 0) * 10000) as column_10,";
		sql += " sum(value(a.column_11, 0) * 10000) as column_11,sum(value(a.column_12, 0) * 10000) as column_12 from v_a_details_of_backmoney a where 1 = 1 ";

		if (StringUtils.isNotBlank(year)) {
			sql += " and a.year = ? ";
			array.add(year);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and a.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and a.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and a.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}

		if (StringUtils.isNotBlank(dept_id)) {
			// 经办
			sql += " group by a.l4_dept_id, a.l4_dept_name ";
		} else {
			// 分公司
			sql += " group by a.dept_id, a.dept_name ";
		}

		sql += " ) bb on aa.dept_id = bb.dept_id ";

		if (StringUtils.isNotBlank(dept_id)) {
			// 经办
			sql += " left join (select (select k.dept_id from konka_dept k where k.dept_sn = a.dept_sn) as dept_id,";
		} else {
			// 分公司
			sql += " left join (select (select k.dept_id from konka_dept k where k.dept_sn = a.fgs_sn) as dept_id,";
		}
		sql += " round(value(sum(a.ratio), 0), 6) as ratio,value(sum(a.ratio), 0) * (select value(sum(b.m), 0) from konka_plan_money b ";
		sql += " where 1 = 1 and b.p = 1 ";
		// 任务销售额的时间选择
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month_start)) {
			sql += " and b.y >= ? ";
			array.add(year + month_start);
		}
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month_end)) {
			sql += " and b.y <= ? ";
			array.add(year + month_end);
		}
		sql += " ) * 10000 as rw_money from konka_plan_ratio a where 1 = 1 ";
		// 任务系数的年度选择
		if (StringUtils.isNotBlank(year)) {
			sql += " and a.y = ? ";
			array.add(year);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.fgs_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.dept_sn = (select d.dept_sn from konka_dept d where d.dept_id = ?) ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(dept_id)) {
			// 经办
			sql += " and a.fgs_sn != a.dept_sn group by a.dept_sn ";
		} else {
			// 分公司
			sql += " and a.fgs_sn = a.dept_sn group by a.fgs_sn ";
		}
		sql += " ) cc on aa.dept_id = cc.dept_id where 1 = 1 ";
		if (StringUtils.isNotBlank(dept_id)) {
			// 经办
			sql += " and aa.par_id in (select kd.dept_id from konka_dept kd where kd.dept_type = 3 and kd.dept_id = ?) ";
			array.add(dept_id);
			if (StringUtils.isNotBlank(l4_dept_id)) {
				sql += " and aa.dept_id = ? ";
				array.add(l4_dept_id);
			}
		} else {
			// 分公司
			sql += " and aa.dept_type = 3 ";
		}

		sql += " order by aa.dept_name asc, aa.dept_id asc ";

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