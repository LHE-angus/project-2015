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
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.manager.admin.KonkaSellRankAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Liu,ZhiXiang
 * @version 2013-08-23
 */
public class KonkaSellRankInterfaceAction extends KonkaSellRankAction {

	// 零售畅销机型统计
	public ActionForward KonkaSellRankToJsonForModelNameForCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		//为了不更新手机端的客户端功能，做的特殊处理，传1标示按照销量，传2标示按照金额排序
		if(tj_type==null || tj_type.equals("1")){
			tj_type = "2";
		}else{
			tj_type = "1";
		}
		
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String model_name = (String) dynaBean.get("model_name");
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else {
				// 用户没有操作权限！
				//super.renderJson(response,
				//		"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				//return null;
			}
		}

		List<?> entityList = this.getSellModelNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, model_name, show_top,userInfo);

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
			sb = sb.append("\"model_name\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 产品型号
			sb = sb.append("\"all_price\":\"").append((obj[1] == null ? "0" : obj[1].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// R3销售畅销机型统计
	public ActionForward KonkaSellRankToJsonForPdNameForCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String pd_name = (String) dynaBean.get("pd_name");
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = this.getR3PdNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, pd_name, show_top);

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
			sb = sb.append("\"pd_name\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 产品型号
			sb = sb.append("\"all_price\":\"").append((obj[1] == null ? "0" : obj[1].toString())).append("\",");// R3销售额
			sb = sb.append("\"all_num\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// R3销售量
			sb = sb.append("\"price\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// 零售分公司排名
	public ActionForward KonkaSellRankToJsonFor100(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
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

		String dept_id = "";
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(userInfo.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}

			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = super.getSellRankFor100(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				show_top);
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
			sb = sb.append("\"dept_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 分公司id
			sb = sb.append("\"dept_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 分公司名称
			sb = sb.append("\"all_price\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// 零售经办排名
	public ActionForward KonkaSellRankToJsonFor200(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = "";
		String l5_dept_id = "";
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(userInfo.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}

			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = super.getSellRankFor200(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, show_top);
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
			sb = sb.append("\"dept_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 分公司id
			sb = sb.append("\"dept_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 分公司名称
			sb = sb.append("\"l4_dept_id\":\"").append((obj[2] == null ? "" : obj[2].toString())).append("\",");// 经办id
			sb = sb.append("\"l4_dept_name\":\"").append((obj[3] == null ? "" : obj[3].toString())).append("\",");// 经办名称
			sb = sb.append("\"all_price\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[5] == null ? "0" : obj[5].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[6] == null ? "0" : obj[6].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// 零售业务员排名
	public ActionForward KonkaSellRankToJsonFor300(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(userInfo.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}

			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = super.getSellRankFor300(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, show_top);
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
			sb = sb.append("\"user_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 业务员id
			sb = sb.append("\"ywy_user_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 业务员名称
			sb = sb.append("\"all_price\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// 零售客户排名
	public ActionForward KonkaSellRankToJsonFor400(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(userInfo.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}

			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = super.getSellRankFor400(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, customer_name, show_top);
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
			sb = sb.append("\"konka_r3_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 客户id
			sb = sb.append("\"customer_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 客户名称
			sb = sb.append("\"all_price\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	// 零售促销员排名
	public ActionForward KonkaSellRankToJsonFor500(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String rank_type = (String) dynaBean.get("rank_type");
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
			dynaBean.set("tj_type", "1");
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");
		String customer_name = (String) dynaBean.get("customer_name");
		String cxy_user_name = (String) dynaBean.get("cxy_user_name");
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
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				PeRoleUser p = new PeRoleUser();
				p.setRole_id(new Long(34));// 分公司总经理
				p.setUser_id(userInfo.getId());
				List<?> list_ru = super.getFacade().getPeRoleUserService().getPeRoleUserList(p);

				if (null != list_ru && list_ru.size() > 0) {
					// 分公司总经理
					dynaBean.set("role_id", "34");
				} else {
					// 分公司
					dynaBean.set("dept_type", "3");
					dynaBean.set("dept_id", konkaDept.getDept_id().toString());
					dept_id = konkaDept.getDept_id().toString();
				}

			} else {
				// 用户没有操作权限！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户没有操作权限！\"]}");
				return null;
			}
		}

		List<?> entityList = super.getSellRankFor500(rank_type, tj_type, sell_date_start, sell_date_end, dept_id,
				l4_dept_id, l5_dept_id, ywy_user_name, customer_name, cxy_user_name, show_top);
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
			sb = sb.append("\"cxy_user_id\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// 促销员id
			sb = sb.append("\"cxy_user_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 促销员名称
			sb = sb.append("\"all_price\":\"").append((obj[2] == null ? "0" : obj[2].toString())).append("\",");// 零售额
			sb = sb.append("\"all_num\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\",");// 零售量
			sb = sb.append("\"price\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\"");// 平均单价
			sb = sb.append("},");
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;

	}

	public List<?> getSellModelNameForCx(String tj_type, String sell_date_start, String sell_date_end, String dept_id,
			String l4_dept_id, String l5_dept_id, String model_name, String show_top, PeProdUser userInfo) {
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
		
		String sql = " select t_.* from ( select aa.model_name,aa.all_price,aa.all_num,decode(aa.all_num,0,0,round(aa.all_price/aa.all_num,2)) as price from ( ";
		sql += " select t.model_name,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num ";
		sql += " from v_a_details_of_sales t where 1 = 1 and t.model_name is not null ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.sale_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.sale_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(model_name)) {
			sql += " and t.model_name like '%' ||?|| '%' ";
			array.add(model_name);
		}
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
			String job_id1=userInfo.getJob_id();
			sql += "AND ( (ywy_job_id = '" + job_id1  + "' or ywzg_job_id = '" + job_id1 + "' or jbjl_job_id ='" + job_id1 + "')";
			sql += "  	 or t.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + userInfo.getId() + "  ))";
		}
		
		sql += " group by t.model_name ";
		sql += " order by t.model_name asc ";
		sql += " ) aa where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by aa.all_num desc,aa.all_price desc ";
		} else {
			sql += " order by aa.all_price desc,aa.all_num desc ";
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

	public List<?> getR3PdNameForCx(String tj_type, String sell_date_start, String sell_date_end, String dept_id,
			String l4_dept_id, String l5_dept_id, String pd_name, String show_top) {
		List<String> array = new ArrayList<String>();

		String sql = " select t_.* from ( select aa.pd_name,aa.all_price,aa.all_num,decode(aa.all_num,0,0,round(aa.all_price/aa.all_num,2)) as price from ( ";
		sql += " select t.pd_name,value(sum(t.pd_total_money), 0) as all_price,value(sum(t.pd_count), 0) as all_num ";
		sql += " from v_a_details_of_purchase t where 1 = 1 and t.pd_name is not null ";
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql += " and t.create_date >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql += " and t.create_date <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(pd_name)) {
			sql += " and t.pd_name like '%'||?||'%' ";
			array.add(pd_name);
		}

		sql += " group by t.pd_name ";
		sql += " order by t.pd_name asc ";
		sql += " ) aa where 1 = 1 ";

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql += " order by aa.all_num desc,aa.all_price desc ";
		} else {
			sql += " order by aa.all_price desc,aa.all_num desc ";
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

}