package com.ebiz.mmt.web.struts.webservice;

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
import com.ebiz.mmt.web.SelectBean;
import com.ebiz.mmt.web.struts.manager.admin.KonkaR3OrSellReportAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Liu,ZhiXiang
 * @version 2013-08-01
 */
public class KonkaR3OrSellReportInterfaceAction extends KonkaR3OrSellReportAction {

	public ActionForward getKonkaR3OrSellReportToJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String size_sec = (String) dynaBean.get("size_sec");
		String model_name = (String) dynaBean.get("model_name");
		String label_db = (String) dynaBean.get("label_db");
		String label_int = (String) dynaBean.get("label_int");

		String group_by_field = (String) dynaBean.get("group_by_field");// 分组标示
		String show_field = (String) dynaBean.get("show_field");// 显示字段
		String contrast = (String) dynaBean.get("contrast");// 同比、环比

		String[] group_by_field_array = {};
		String[] show_field_array = {};
		String[] contrast_array = {};
		if (StringUtils.isNotBlank(group_by_field)) {
			group_by_field_array = group_by_field.split(",");
		}
		if (StringUtils.isNotBlank(show_field)) {
			show_field_array = show_field.split(",");
		}
		if (StringUtils.isNotBlank(contrast)) {
			contrast_array = contrast.split(",");
		}

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
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"用户不存在！\"]}");
			return null;
		} else if (userInfoList.size() > 1) {
			// 用户名重复！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"用户名重复！\"]}");
			return null;
		}
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(password));
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			// 密码错误！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"500\",\"msg\":\"密码错误！\"]}");
			return null;
		}

		if (userInfo.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (userInfo.getUser_type() == 1) {
			// 用户没有操作权限！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户没有操作权限！\"]}");
			return null;
		}
		int num = 0;
		if (null == group_by_field_array || group_by_field_array.length <= 0) {
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"701\",\"msg\":\"分组标示不能为空！\"]}");
			return null;
		} else {
			for (int i = 0; i < group_by_field_array.length; i++) {
				if (group_by_field_array[i].contains("bb_")) {
					num++;
				}
			}
			if (num > 1) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"702\",\"msg\":\"时间维度只能选择一个！\"]}");
				return null;
			}
		}
		if (null != contrast_array && contrast_array.length > 0 && num < 1) {
			super.renderJson(response,
					"{\"status\":\"-1\",\"error\":[\"error_code\":\"703\",\"msg\":\"选择同比或环比时，必须选择时间维度！\"]}");
			return null;
		}
		if (null == show_field_array || show_field_array.length <= 0) {
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"800\",\"msg\":\"显示字段不能为空！\"]}");
			return null;
		}

		SelectBean b = new SelectBean();
		b.setSell_date_start(sell_date_start);
		b.setSell_date_end(sell_date_end);
		b.setDept_id(dept_id);
		b.setL4_dept_id(l4_dept_id);
		b.setL5_dept_id(l5_dept_id);
		b.setYwy_user_name(ywy_user_name);
		b.setCustomer_name(customer_name);
		b.setSize_sec(size_sec);
		b.setModel_name(model_name);
		b.setLabel_db(label_db);
		b.setLabel_int(label_int);
		b.setGroup_by_field(group_by_field_array);
		b.setShow_field(show_field_array);
		b.setContrast(contrast_array);

		b = super.getKonkaR3OrSellReport(b);
		List<?> entityList = b.getEntityList();
		int show_num = b.getShow_num();

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}
		for (int i = 0; i < entityList.size() - 1; i++) {
			Object[] obj = (Object[]) entityList.get(i);
			sb = sb.append("[");
			for (int j = 0; j < show_num - 1; j++) {
				sb = sb.append("\"").append((obj[j] == null ? "" : obj[j].toString())).append("\",");
			}
			sb = sb.append("\"").append((obj[show_num - 1] == null ? "" : obj[show_num - 1].toString())).append("\"");
			sb = sb.append("],");

		}
		Object[] obj = (Object[]) entityList.get(entityList.size() - 1);
		sb = sb.append("[");
		for (int j = 0; j < show_num - 1; j++) {
			sb = sb.append("\"").append((obj[j] == null ? "" : obj[j].toString())).append("\",");
		}
		sb = sb.append("\"").append((obj[show_num - 1] == null ? "" : obj[show_num - 1].toString())).append("\"");
		sb = sb.append("]");

		sb = sb.append("],");
		sb = sb.append("\"show_num\":\"").append(b.getShow_num()).append("\",");
		sb = sb.append("\"group_flag_string\":\"").append(b.getGroup_flag_string()).append("\",");
		sb = sb.append("\"show_field_string\":\"").append(b.getShow_field_string()).append("\",");
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + ",\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public ActionForward getKonkaR3OrSellReportForMulToJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String size_sec = (String) dynaBean.get("size_sec");
		String model_name = (String) dynaBean.get("model_name");
		String label_db = (String) dynaBean.get("label_db");
		String label_int = (String) dynaBean.get("label_int");

		String group_by_field = (String) dynaBean.get("group_by_field");// 分组标示
		String show_field = (String) dynaBean.get("show_field");// 显示字段
		String contrast = (String) dynaBean.get("contrast");// 同比、环比

		String[] group_by_field_array = {};
		String[] show_field_array = {};
		String[] contrast_array = {};
		if (StringUtils.isNotBlank(group_by_field)) {
			group_by_field_array = group_by_field.split(",");
		}
		if (StringUtils.isNotBlank(show_field)) {
			show_field_array = show_field.split(",");
		}
		if (StringUtils.isNotBlank(contrast)) {
			contrast_array = contrast.split(",");
		}

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
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"用户不存在！\"]}");
			return null;
		} else if (userInfoList.size() > 1) {
			// 用户名重复！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"用户名重复！\"]}");
			return null;
		}
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(password));
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			// 密码错误！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"500\",\"msg\":\"密码错误！\"]}");
			return null;
		}

		if (userInfo.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (userInfo.getUser_type() == 1) {
			// 用户没有操作权限！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户没有操作权限！\"]}");
			return null;
		}
		int time_num = 0;
		int month_num = 0;
		int model_num = 0;
		int dept_fgs_jyb_num = 0;
		int dept_num = 0;
		if (null == group_by_field_array || group_by_field_array.length <= 0) {
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"701\",\"msg\":\"分组标示不能为空！\"]}");
			return null;
		} else {
			for (int i = 0; i < group_by_field_array.length; i++) {
				if (group_by_field_array[i].contains("bb_")) {
					time_num++;
				}
				if (group_by_field_array[i].equals("bb_3")) {
					month_num++;
				}
				if (group_by_field_array[i].equals("cc_2")) {
					model_num++;
				}
				if (group_by_field_array[i].equals("aa_1") || group_by_field_array[i].equals("aa_2")) {
					dept_fgs_jyb_num++;
				}
				if (group_by_field_array[i].equals("aa_3") || group_by_field_array[i].equals("aa_4")
						|| group_by_field_array[i].equals("aa_5") || group_by_field_array[i].contains("cc_")
						|| group_by_field_array[i].contains("dd_")) {
					dept_num++;
				}
			}
			if (time_num > 1) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"702\",\"msg\":\"时间维度只能选择一个！\"]}");
				return null;
			}
		}
		if (null != contrast_array && contrast_array.length > 0 && time_num < 1) {
			super.renderJson(response,
					"{\"status\":\"-1\",\"error\":[\"error_code\":\"703\",\"msg\":\"选择同比或环比时，必须选择时间维度！\"]}");
			return null;
		}
		int pd_num = 0;
		int ratio_num = 0;
		if (null == show_field_array || show_field_array.length <= 0) {
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"800\",\"msg\":\"显示字段不能为空！\"]}");
			return null;
		} else {
			for (int i = 0; i < show_field_array.length; i++) {
				if (show_field_array[i].equals("10131") || show_field_array[i].equals("10141")) {
					pd_num++;
				}
				if (show_field_array[i].equals("10051") || show_field_array[i].equals("10061")
						|| show_field_array[i].equals("10071") || show_field_array[i].equals("10081")) {
					ratio_num++;
				}
			}
			if (pd_num > 0 && (model_num <= 0 || month_num <= 0)) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"801\",\"msg\":\"选择产品零售指导价或产品现款价时，必须选择月份和产品维度的型号！\"]}");
				return null;
			}
			if (ratio_num > 0 && time_num <= 0) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"802\",\"msg\":\"选择任务系数、任务额回款、任务完成度、销售任务完成度时，必须选择时间维度！\"]}");
				return null;
			}
			if (ratio_num > 0 && dept_num > 0) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"803\",\"msg\":\"选择任务系数、任务额回款、任务完成度、销售任务完成度时，只能选择到分公司、经营部、时间维度！\"]}");
				return null;
			}
			if (ratio_num > 0 && dept_fgs_jyb_num <= 0) {
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"804\",\"msg\":\"选择任务系数、任务额回款、任务完成度、销售任务完成度时，必须选择分公司或经营部！\"]}");
				return null;
			}
		}

		SelectBean b = new SelectBean();
		b.setSell_date_start(sell_date_start);
		b.setSell_date_end(sell_date_end);
		b.setDept_id(dept_id);
		b.setL4_dept_id(l4_dept_id);
		b.setL5_dept_id(l5_dept_id);
		b.setYwy_user_name(ywy_user_name);
		b.setCustomer_name(customer_name);
		b.setSize_sec(size_sec);
		b.setModel_name(model_name);
		b.setLabel_db(label_db);
		b.setLabel_int(label_int);
		b.setGroup_by_field(group_by_field_array);
		b.setShow_field(show_field_array);
		b.setContrast(contrast_array);

		b = super.getKonkaR3OrSellReportForMul(b);
		List<?> entityList = b.getEntityList();
		int show_num = b.getShow_num();

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}
		for (int i = 0; i < entityList.size() - 1; i++) {
			Object[] obj = (Object[]) entityList.get(i);
			sb = sb.append("[");
			for (int j = 0; j < show_num - 1; j++) {
				sb = sb.append("\"").append((obj[j] == null ? "" : obj[j].toString())).append("\",");
			}
			sb = sb.append("\"").append((obj[show_num - 1] == null ? "" : obj[show_num - 1].toString())).append("\"");
			sb = sb.append("],");

		}
		Object[] obj = (Object[]) entityList.get(entityList.size() - 1);
		sb = sb.append("[");
		for (int j = 0; j < show_num - 1; j++) {
			sb = sb.append("\"").append((obj[j] == null ? "" : obj[j].toString())).append("\",");
		}
		sb = sb.append("\"").append((obj[show_num - 1] == null ? "" : obj[show_num - 1].toString())).append("\"");
		sb = sb.append("]");

		sb = sb.append("],");
		sb = sb.append("\"show_num\":\"").append(b.getShow_num()).append("\",");
		sb = sb.append("\"group_flag_string\":\"").append(b.getGroup_flag_string()).append("\",");
		sb = sb.append("\"show_field_string\":\"").append(b.getShow_field_string()).append("\",");
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + ",\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public ActionForward getKonkaR3OrSellReportForR3ToJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		String begin_date = (String) dynaBean.get("begin_date");
		String end_date = (String) dynaBean.get("end_date");
		String user_name = (String) dynaBean.get("user_name");// 用户名
		String password = (String) dynaBean.get("password");// 密码
		String ywy_name = (String) dynaBean.get("ywy_name");// 密码
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
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
		
		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		if(StringUtils.isNotBlank(android_version) && Long.valueOf(android_version)>37)
		{
			entity.setUser_name(new String(new DESPlus().decrypt(user_name).getBytes("iso-8859-1"),"utf-8"));
			entity.setPass_word(password);
		}else if(StringUtils.isNotBlank(ios_version) && Long.valueOf(ios_version)>317){
			entity.setUser_name(new String(new DESPlus().decrypt(user_name).getBytes("iso-8859-1"),"utf-8"));
			entity.setPass_word(password);
		}else
		{
			entity.setUser_name(user_name);
			entity.setPass_word(new DESPlus().encrypt(password));
		}
		entity.getRow().setCount(2);
		entity.setIs_del(0);
		// entity.setIs_xx_user(0L); //不是新兴渠道用户 -----改在SQL中写死，以免各个调用模块都需要\
		entity.getMap().put("user_type_in", "0,1"); // Ren zhong, Add by2013-06-06

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			// 用户不存在！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"用户不存在！\"]}");
			return null;
		} else if (userInfoList.size() > 1) {
			// 用户名重复！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"用户名重复！\"]}");
			return null;
		}
		
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			// 密码错误！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"500\",\"msg\":\"密码错误！\"]}");
			return null;
		}
		List<KonkaDept> list = super.getKonkaDeptListOfUser(userInfo.getId(), true);
		logger.info("userInfo.getId() {}", userInfo.getId());
		String dept_id = "";
		for (KonkaDept k : list) {
			if (!dept_id.equals("")) {
				dept_id += ",";
			}
			dept_id += k.getDept_id();

		}
		// if (userInfo.getUser_type() == 0) {
		// // 康佳总部
		// dynaBean.set("dept_type", "1");
		// } else if (userInfo.getUser_type() == 1) {
		// // 用户没有操作权限！
		// super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户没有操作权限！\"]}");
		// return null;
		// }

		List<?> entityList = this.getKonkaR3OrSellReportForR3ToJson(r3_code, begin_date, end_date, ywy_name, userInfo);

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}
		for (int i = 0; i < entityList.size(); i++) {
			Object[] obj = (Object[]) entityList.get(i);
			sb = sb.append("{");
			sb = sb.append("\"r3_code\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");
			sb = sb.append("\"add_day\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");
			sb = sb.append("\"total_counts_of_sale\":\"").append((obj[4] == null ? "0" : obj[4].toString()))
					.append("\",");// 零售量
			sb = sb.append("\"total_money_of_sale\":\"").append((obj[5] == null ? "0" : obj[5].toString()))
					.append("\",");// 零售额
			sb = sb.append("\"total_counts_of_buy\":\"").append((obj[2] == null ? "0" : obj[2].toString()))
					.append("\",");// R3销售量
			sb = sb.append("\"total_money_of_buy\":\"").append((obj[3] == null ? "0" : obj[3].toString()))
					.append("\",");// R3销售额
			sb = sb.append("\"credit\":\"").append((obj[6] == null ? "0" : obj[6].toString()))
					.append("\",");// 客户账期	
			sb = sb.append("\"cur_month_real_backmoney\":\"").append((obj[7] == null ? "0" : obj[7].toString()))
					.append("\",");// 当月实际回款额		
			sb = sb.append("\"r3_name\":\"").append((obj[8] == null ? "" : obj[8].toString())).append("\",");
			sb = sb.append("\"xdxe\":\"").append((obj[9] == null ? "0" : obj[9].toString())).append("\"");// 信贷限额	
			sb = sb.append("},");

		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public List<?> getKonkaR3OrSellReportForR3ToJson(String r3_code, String begin_date, String end_date,
			String ywy_name, PeProdUser userInfo) {
		List<String> array = new ArrayList<String>();
		

		// 数据级别判断开始
		Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		String dept_id_in = "";
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			
			List<KonkaDept> list = super.getKonkaDeptListOfUser(userInfo.getId(), true);
			for (KonkaDept k : list) {
				if (!dept_id_in.equals("")) {
					dept_id_in += ",";
				}
				dept_id_in += k.getDept_id();

			}
			
			break;
		case 0:
			break;
		default:
			// 出错
		}
		// 数据级别判断结束

		String sql = "SELECT T.R3_CODE,'' as ADD_DAY,";
		sql += "	 ";
		sql += "    value(Sum(T1.r3_num),0)    AS total_num_of_buy,";
		sql += "	value(Sum(T1.r3_money),0)  AS total_money_of_buy,";
		sql += "	value(Sum(T2.num),0)       total_counts_of_sale,";
		sql += "	value(Sum(T2.ALL_PRICE),0) total_money_of_sale,";
		sql += "	FUNC_GET_R3CREDIT(t.r3_code) as credit,";
		sql += "	FUNC_GET_R3BACKMONEY(t.r3_code,?,?) as cur_month_real_backmoney,";
		sql += "	T.CUSTOMER_NAME as r3_name,(SELECT SKFOR from KONKA_R3_SHOP_CREDIT　where kunnr=t.r3_code and rownum<2) klimk ";
		sql += "		FROM   MV_ORG_OF_CUSTOMER t";
		sql += " left JOIN (SELECT Sum(T11.column_12) AS r3_num,";
		sql += "                   Sum(T11.column_14) AS r3_money,";
		sql += " 		            COLUMN_1";
		sql += "   			FROM   channel_data_import t11";
		sql += "  			WHERE  t11.column_26 >= To_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		sql += "    			AND t11.column_26 < To_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		sql += " 			GROUP  BY COLUMN_1) t1";
		sql += "   ON t.R3_CODE = t1.COLUMN_1";
		sql += "  LEFT JOIN (SELECT CUSTOMER_R3_CODE,";
		sql += "Sum(num)       num,";
		sql += "      Sum(all_price) all_price";
		sql += "   FROM   KONKA_MOBILE_SAIL_DATA t21";
		sql += " WHERE  t21.sale_date >= To_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		sql += "  AND t21.sale_date < To_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		sql += "   AND t21.num IS NOT NULL";
		sql += "   AND T21.STATUS = 0";
		sql += "   GROUP  BY CUSTOMER_R3_CODE) T2";
		sql += "   ON T2.customer_r3_code = t.R3_CODE";
		sql += "	WHERE  1=1 ";
		
		
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			//sql += "  	 AND rownum<100 ";
			break;
		case 8:
			// 分公司及以下部门可见
			sql += "  	 AND t.dept_id IN ( " + __dept_id + " )";
			break;
		case 7:
			// 我所在的部门及以下部门可见
			sql += "  	 AND (t.l4_dept_id IN ( " + dept_id_in + " ) or t.l5_dept_id in ( " + dept_id_in + " ))";
			
			break;

		default:
			// 集团及以下部门可见
			//sql += "  	 AND rownum<100 ";
			// 出错
		}	
		if (max_dlevel == 0) {
			sql += "  	 AND  t.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + userInfo.getId() + "  )";
		}

		/*
		 * String sql = " SELECT t.R3_CODE,t.ADD_DAY,value (sum (t.TOTAL_COUNTS_OF_SALE), 0) AS total_counts_of_sale ";
		 * sql += " ,value (sum (t.TOTAL_MONEY_OF_SALE), 0) AS total_money_of_sale "; sql +=
		 * " ,value (sum (t.TOTAL_COUNTS_OF_BUY), 0) AS total_counts_of_buy "; sql +=
		 * " ,value (sum (t.TOTAL_MONEY_OF_BUY), 0) AS total_money_of_buy "; sql +=
		 * " ,value (sum (t.cur_month_real_backmoney), 0) AS cur_month_real_backmoney "; sql +=
		 * " ,CUSTOMER_NAME AS r3_name "; sql += " FROM v_a_details_of_sale_and_buy_mx t "; sql +=
		 * " WHERE 1 = 1 AND t.R3_CODE IS NOT NULL AND t.ADD_DAY IS NOT NULL "; sql +=
		 * " GROUP BY t.R3_CODE, t.ADD_DAY, t.CUSTOMER_NAME ";
		 */
		array.add(begin_date);
		array.add(begin_date);
		array.add(begin_date);
		array.add(end_date);
		array.add(begin_date);
		array.add(end_date);
		// array.add(dept_id);
		log.info("**********dept_id:" + __dept_id);
		if (r3_code != null && !r3_code.equals("")) {
			sql += "   AND lower(t.r3_code) like '%' ||?|| '%' ";
			array.add(r3_code.toLowerCase());
		}
		if (ywy_name != null && !ywy_name.equals("")) {
			sql += "   AND t.ywy_user_name like  '%' ||?|| '%' ";
			array.add(ywy_name);
		}
		sql += "GROUP  BY T.R3_CODE, T.CUSTOMER_NAME";
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