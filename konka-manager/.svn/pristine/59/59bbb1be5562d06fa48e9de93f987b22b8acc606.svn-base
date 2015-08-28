package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,hao
 * @version 2013-11-04
 */
public class KonkaR3BackMoneyJdYjtbEmAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String jb_type = (String) dynaBean.get("jb_type");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		KonkaR3Backmoney entity = new KonkaR3Backmoney();
		if (StringUtils.isNotBlank(jb_type)) {
			request.setAttribute("jb_type", jb_type);
			if ("4".equals(jb_type)) {
				entity.getMap().put("jb_type_4", true);
			} else {
				entity.getMap().put("jb_type", jb_type);
			}
		} else {
			entity.getMap().put("jb_type_4", true);
			request.setAttribute("jb_type", 4);
		}

		String this_year = null;
		String this_month = null;

		// 取当前时间
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatm = new SimpleDateFormat("MM");
		Date now = new Date();

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			this_year = year;
			this_month = month;
		} else {
			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份
			// 去年
		}
		Integer last_year = Integer.valueOf(this_year) - 1;

		// 今年数据时间段
		String this_date_s = this_year + "-" + this_month + "-01 00:00:00";
		String this_date_e = this_year + "-" + this_month + "-"
				+ getMaxDay(Integer.valueOf(this_month), Integer.valueOf(this_year)) + " 23:59:59";

		// 去年数据时间段
		// String last_date_s = last_year + "-" + this_month + "-01 00:00:00";
		// String last_date_e = last_year + "-" + this_month + "-" +
		// getMaxDay(Integer.valueOf(this_month)) + " 23:59:59";
		entity.getMap().put("column_value", "column_" + Integer.valueOf(this_month));
		entity.getMap().put("this_year", this_year);
		entity.getMap().put("this_month", Integer.valueOf(this_month));
		entity.getMap().put("last_year", last_year);
		entity.getMap().put("this_date_s", this_date_s);
		entity.getMap().put("this_date_e", this_date_e);
		// entity.getMap().put("last_date_s", last_date_s);
		// entity.getMap().put("last_date_e", last_date_e);

		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);
		dynaBean.set("jb_type", jb_type);
		request.setAttribute("mm", Integer.valueOf(this_month));
		List<KonkaR3Backmoney> entityList = super.getFacade().getKonkaR3BackmoneyService()
				.getKonkaR3BackmoneyListForHkJd(entity);

		// 同比增长率
		ComparatorZzl comparatorZzl = new ComparatorZzl();
		Collections.sort(entityList, comparatorZzl);

		int i = entityList.size() + 1;
		for (KonkaR3Backmoney temp : entityList) {
			i--;
			temp.getMap().put("hk_zz_pm", i);
		}

		// 回款率排名
		ComparatorWcl comparatorWcl = new ComparatorWcl();
		Collections.sort(entityList, comparatorWcl);

		int ii = entityList.size() + 1;
		for (KonkaR3Backmoney temp : entityList) {
			ii--;
			temp.getMap().put("hk_wc_pm", ii);
			if (null != temp.getMap().get("ratio"))
				temp.getMap().put("ratio",
						(new BigDecimal(temp.getMap().get("ratio").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("hk_rw"))
				temp.getMap().put("hk_rw",
						(new BigDecimal(temp.getMap().get("hk_rw").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("hk_money"))
				temp.getMap().put(
						"hk_money",
						(new BigDecimal(temp.getMap().get("hk_money").toString()))
								.setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("l_hk_money"))
				temp.getMap().put(
						"l_hk_money",
						(new BigDecimal(temp.getMap().get("l_hk_money").toString())).setScale(2,
								BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("hk_wcl"))
				temp.getMap().put("hk_wcl",
						(new BigDecimal(temp.getMap().get("hk_wcl").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("hk_zzl"))
				temp.getMap().put("hk_zzl",
						(new BigDecimal(temp.getMap().get("hk_zzl").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("db_zb"))
				temp.getMap().put("db_zb",
						(new BigDecimal(temp.getMap().get("db_zb").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));
		}

		Collections.reverse(entityList);
		request.setAttribute("entityList", entityList);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		return mapping.findForward("list");
	}

	public class ComparatorWcl implements Comparator<KonkaR3Backmoney> {

		@Override
		public int compare(KonkaR3Backmoney arg0, KonkaR3Backmoney arg1) {
			KonkaR3Backmoney user0 = (KonkaR3Backmoney) arg0;
			KonkaR3Backmoney user1 = (KonkaR3Backmoney) arg1;

			BigDecimal hk_wcl1 = (BigDecimal) user0.getMap().get("hk_wcl");
			BigDecimal hk_wcl2 = (BigDecimal) user1.getMap().get("hk_wcl");
			int flag = 0;
			if (hk_wcl1 != null && hk_wcl2 != null) {
				flag = hk_wcl1.compareTo(hk_wcl2);
			}
			return flag;

		}
	}

	public class ComparatorZzl implements Comparator<KonkaR3Backmoney> {

		@Override
		public int compare(KonkaR3Backmoney arg0, KonkaR3Backmoney arg1) {
			KonkaR3Backmoney user0 = (KonkaR3Backmoney) arg0;
			KonkaR3Backmoney user1 = (KonkaR3Backmoney) arg1;

			BigDecimal hk_zzl1 = (BigDecimal) user0.getMap().get("hk_zzl");
			BigDecimal hk_zzl2 = (BigDecimal) user1.getMap().get("hk_zzl");
			int flag = 0;
			if (hk_zzl2 != null && hk_zzl1 != null) {
				flag = hk_zzl1.compareTo(hk_zzl2);
			}
			return flag;

		}
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
}
