package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Hu,hao
 * @version 2013-11-05
 */
public class KonkaR3JsBackMoneySailAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		// String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String user_id = (String) dynaBean.get("user_id");

		// if (StringUtils.isNotBlank(username)) {
		// username = new String(username.getBytes("iso-8859-1"), "UTF-8");
		// }
		// username = URLDecoder.decode(username, "utf-8");
		// userpass = URLDecoder.decode(userpass, "utf-8");

		// 密码
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

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

		// 上一个月的年月
		Integer s_year = null;
		String s_month = null;
		if (Integer.valueOf(this_month) == 1) {
			s_year = Integer.valueOf(this_year) - 1;
			s_month = "12";
		} else {
			s_year = Integer.valueOf(this_year);
			s_month = addZero(Integer.valueOf(this_month) - 1);
		}

		// 去年
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
		String this_date_e = this_year + "-" + this_month + "-"
		        + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year)) + " 23:59:59";

		// 去年数据时间段
		String last_date_s = last_year + "-" + this_month + "-01 00:00:00";
		String last_date_e = last_year + "-" + this_month + "-"
		        + getMaxDay(Integer.valueOf(this_month), Integer.valueOf(last_year)) + " 23:59:59";

		// 上一个月时间段
		String s_date_s = s_year + "-" + s_month + "-01 00:00:00";
		String s_date_e = s_year + "-" + s_month + "-" + getMaxDay(Integer.valueOf(s_month), Integer.valueOf(s_year))
		        + " 23:59:59";

		if (!GenericValidator.isLong(user_id) || StringUtils.isBlank(userpass)) {
			super.renderText(response, "参数错误，请联系管理员！");
			return null;
		}

		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser ui = checkUserid(user_id, userpass,android_version,ios_version);
		if (null == ui) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		dynaBean.set("user_id", user_id);
		dynaBean.set("userpass", userpass);

		// PeRoleUser pUser = new PeRoleUser();
		// pUser.setUser_id(ui.getId());
		// List<PeRoleUser> pUserList =
		// super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);
		//
		// Boolean role_id_gt_30 = false;
		// Boolean role_id_30_btw_60 = false;
		// Boolean role_id_eq_30_or_34 = false;
		// for (PeRoleUser p : pUserList) {
		// if (p.getRole_id() < 30)
		// role_id_gt_30 = true;
		// if (p.getRole_id() > 30 && p.getRole_id() < 60 && p.getRole_id() !=
		// 34)
		// role_id_30_btw_60 = true;
		// if (p.getRole_id() == 30 || p.getRole_id() == 34)
		// role_id_eq_30_or_34 = true;
		// }

		KonkaR3Backmoney entity = new KonkaR3Backmoney();// 结算数据

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("dept_sn", dept_fgs.getDept_sn());
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			KonkaDept fgs = new KonkaDept();
			fgs.setDept_id(ui.getDept_id());
			fgs = super.getFacade().getKonkaDeptService().getKonkaDept(fgs);
			if (fgs != null && null != fgs.getDept_sn()) {
				entity.getMap().put("dept_sn", fgs.getDept_sn());
			} else {
				entity.getMap().put("dept_sn", "-1");
			}

			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("dept_sn", "-1");
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		// 数据查询的时间(当前年月)
		entity.getMap().put("this_year", this_year);
		entity.getMap().put("this_month", Integer.valueOf(this_month));
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);

		// 去年同期
		entity.getMap().put("last_year", last_year);
		entity.getMap().put("last_date_s", last_date_s);
		entity.getMap().put("last_date_e", last_date_e);

		// 上一个月
		entity.getMap().put("s_year", this_year);
		entity.getMap().put("s_month", Integer.valueOf(s_month));
		entity.getMap().put("s_date_s", s_date_s);
		entity.getMap().put("s_date_e", s_date_e);

		entity.getMap().put("column_value", "column_" + Integer.valueOf(this_month));
		entity.getMap().put("s_column_value", "column_" + Integer.valueOf(s_month));

		dynaBean.set("month", this_month);
		dynaBean.set("year", this_year);

		List<KonkaR3Backmoney> hkList = super.getFacade().getKonkaR3BackmoneyService()
		        .getKonkaR3BackmoneyFoHkDataPaginatedList(entity);// 回款
		List<KonkaR3Backmoney> sailList = super.getFacade().getKonkaR3BackmoneyService()
		        .getKonkaR3BackmoneyFoSailDataPaginatedList(entity);// 零售
		List<KonkaR3Backmoney> jsList = super.getFacade().getKonkaR3BackmoneyService()
		        .getKonkaR3BackmoneyFoJsDataPaginatedList(entity);// 结算
		List<KonkaR3Backmoney> rwList = super.getFacade().getKonkaR3BackmoneyService()
		        .getKonkaR3BackmoneyFoRwDataPaginatedList(entity); // 回款任务

		request.setAttribute("hk", hkList.get(0));
		request.setAttribute("sail", sailList.get(0));
		request.setAttribute("js", jsList.get(0));
		request.setAttribute("rw", rwList.get(0));

		// 判断圆柱体的高度
		BigDecimal max = new BigDecimal(0);
		BigDecimal js_length = new BigDecimal(0);
		BigDecimal sail_length = new BigDecimal(0);
		BigDecimal hk_length = new BigDecimal(0);

		BigDecimal js_money = new BigDecimal(jsList.get(0).getMap().get("js_money").toString());
		BigDecimal sale_all_price = new BigDecimal(sailList.get(0).getMap().get("sale_all_price").toString());
		BigDecimal hk_money = new BigDecimal(hkList.get(0).getMap().get("hk_money").toString());

		if (js_money.compareTo(sale_all_price) == -1) {
			max = sale_all_price;
		} else {
			max = js_money;
		}
		if (max.compareTo(hk_money) == -1) {
			max = hk_money;
		}

		if (max.compareTo(new BigDecimal(0)) == 1) {
			js_length = js_money.divide(max, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(150));
			sail_length = sale_all_price.divide(max, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(150));
			hk_length = hk_money.divide(max, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(150));
		}

		request.setAttribute("js_length", js_length.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("sail_length", sail_length.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("hk_length", hk_length.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP));

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		return mapping.findForward("list");
	}

	public int getMaxDay(int mm, int year) {
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

	public String addZero(int s) {
		String value = null;
		if (s < 10) {
			value = "0" + s;
		} else {
			value = s + "";
		}
		return value;
	}
}
