package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.ebiz.mmt.domain.KonkaDeptJbTask;
import com.ebiz.mmt.domain.KonkaR3Backmoney;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,hao
 * @version 2013-09-17
 */
public class KonkaDeptJbTaskForBackMoneyToEmAction extends BaseAction {
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

		KonkaDeptJbTask entity = new KonkaDeptJbTask();
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

		dynaBean.set("year", this_year);
		dynaBean.set("month", this_month);
		dynaBean.set("jb_type", jb_type);
		request.setAttribute("mm", Integer.valueOf(this_month));
		List<KonkaDeptJbTask> entityList = super.getFacade().getKonkaDeptJbTaskService()
				.getKonkaDeptJbTaskForBackMoneyList(entity);

		// 同比增长率
		ComparatorZzl comparatorZzl = new ComparatorZzl();
		Collections.sort(entityList, comparatorZzl);

		int i = entityList.size() + 1;
		for (KonkaDeptJbTask temp : entityList) {
			i--;
			temp.getMap().put("hk_zz_pm", i);
		}

		// 回款率排名
		ComparatorWcl comparatorWcl = new ComparatorWcl();
		Collections.sort(entityList, comparatorWcl);

		int ii = entityList.size() + 1;
		for (KonkaDeptJbTask temp : entityList) {
			ii--;
			temp.getMap().put("hk_wc_pm", ii);

			if (null != temp.getMap().get("task"))
				temp.getMap().put("task",
						(new BigDecimal(temp.getMap().get("task").toString())).setScale(2, BigDecimal.ROUND_HALF_UP));

			if (null != temp.getMap().get("task_money"))
				temp.getMap().put(
						"task_money",
						(new BigDecimal(temp.getMap().get("task_money").toString())).setScale(2,
								BigDecimal.ROUND_HALF_UP));

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
		}

		BigDecimal t_task = new BigDecimal(0);
		BigDecimal t_task_money = new BigDecimal(0);
		BigDecimal t_hk_money = new BigDecimal(0);
		BigDecimal t_l_hk_money = new BigDecimal(0);
		BigDecimal t_hk_wcl = new BigDecimal(0);
		BigDecimal t_hk_zzl = new BigDecimal(0);
		float t_jj_hk_wcl = 0;
		float t_jj_hk_zzl = 0;
		if (null != entityList && entityList.size() > 0) {
			for (int j = 0; j < entityList.size(); j++) {
				KonkaDeptJbTask temp = entityList.get(j);
				BigDecimal task = new BigDecimal(0);
				BigDecimal task_money = new BigDecimal(0);
				BigDecimal hk_money = new BigDecimal(0);
				BigDecimal l_hk_money = new BigDecimal(0);
				// BigDecimal hk_wcl = new BigDecimal(0);
				// BigDecimal hk_zzl = new BigDecimal(0);
				float jj_hk_wcl;
				float jj_hk_zzl;
				int t_jb_type = 0;
				float f_hk_wcl = 0f;
				float f_hk_zzl = 0f;
				int t_hk_zz_pm = 0;
				int t_hk_wc_pm = 0;
				if (null != temp.getMap().get("task"))
					task = (new BigDecimal(temp.getMap().get("task").toString())).setScale(2, BigDecimal.ROUND_HALF_UP);
				temp.getMap().put("task", task);
				if (null != temp.getMap().get("task_money"))
					task_money = (new BigDecimal(temp.getMap().get("task_money").toString())).setScale(0,
							BigDecimal.ROUND_HALF_UP);
				temp.getMap().put("task_money", task_money);
				if (null != temp.getMap().get("hk_money"))
					hk_money = (new BigDecimal(temp.getMap().get("hk_money").toString())).setScale(0,
							BigDecimal.ROUND_HALF_UP);
				temp.getMap().put("hk_money", hk_money);
				if (null != temp.getMap().get("l_hk_money"))
					l_hk_money = (new BigDecimal(temp.getMap().get("l_hk_money").toString())).setScale(0,
							BigDecimal.ROUND_HALF_UP);
				temp.getMap().put("l_hk_money", l_hk_money);
//				if (null != temp.getMap().get("hk_wcl"))
//					hk_wcl = (new BigDecimal(temp.getMap().get("hk_wcl").toString())).setScale(2,
//							BigDecimal.ROUND_HALF_UP);
//				if (null != temp.getMap().get("hk_zzl"))
//					hk_zzl = (new BigDecimal(temp.getMap().get("hk_zzl").toString())).setScale(2,
//							BigDecimal.ROUND_HALF_UP);
				if (null != temp.getMap().get("jb_type")) {
					t_jb_type = Integer.parseInt(temp.getMap().get("jb_type").toString());
				}
				if (null != temp.getMap().get("hk_zz_pm")) {
					t_hk_zz_pm = Integer.parseInt(temp.getMap().get("hk_zz_pm").toString());
				}
				if (null != temp.getMap().get("hk_wc_pm")) {
					t_hk_wc_pm = Integer.parseInt(temp.getMap().get("hk_wc_pm").toString());
				}
				// t_hk_wcl = t_hk_wcl.add(hk_wcl);
				// t_hk_zzl = t_hk_zzl.add(hk_zzl);
				f_hk_wcl = Float.parseFloat(temp.getMap().get("hk_wcl").toString());
				f_hk_zzl = Float.parseFloat(temp.getMap().get("hk_zzl").toString());
				t_task = t_task.add(task);
				t_task_money = t_task_money.add(task_money);
				t_hk_money = t_hk_money.add(hk_money);
				t_l_hk_money = t_l_hk_money.add(l_hk_money);
				// t_hk_wcl = t_hk_wcl.add(hk_wcl);
				// t_hk_zzl = t_hk_zzl.add(hk_zzl);
				// 回款完成率奖金
				if (f_hk_wcl >= 60) {
					// A类经办
					if (t_jb_type == 1) {
						// 第一名
						if (t_hk_wc_pm == 1) {
							jj_hk_wcl = 1f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						}
						// 第2-3名
						else if (t_hk_wc_pm >= 2 && t_hk_wc_pm <= 3) {
							jj_hk_wcl = 0.8f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						} else if (t_hk_wc_pm >= 4 && t_hk_wc_pm <= 7) {
							jj_hk_wcl = 0.6f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						}
					}
					// B类经办
					else if (t_jb_type == 2) {
						if (t_hk_wc_pm <= 2) {
							jj_hk_wcl = 0.8f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						} else if (t_hk_wc_pm >= 3 && t_hk_wc_pm <= 5) {
							jj_hk_wcl = 0.6f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						} else if (t_hk_wc_pm >= 6 && t_hk_wc_pm <= 10) {
							jj_hk_wcl = 0.4f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						}
					}
					// C类经办
					else if (t_jb_type == 3) {
						if (t_hk_wc_pm <= 2) {
							jj_hk_wcl = 0.6f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						} else if (t_hk_wc_pm >= 3 && t_hk_wc_pm <= 6) {
							jj_hk_wcl = 0.4f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						} else if (t_hk_wc_pm >= 7 && t_hk_wc_pm <= 13) {
							jj_hk_wcl = 0.2f;
							t_jj_hk_wcl += jj_hk_wcl;
							temp.getMap().put("jj_hk_wcl", jj_hk_wcl);
						}
					}
				}
				// 回款同比增长奖金
				if (f_hk_wcl >= 60 && f_hk_zzl >= 5) {
					// A类经办
					if (t_jb_type == 1) {
						// 第一名
						if (t_hk_zz_pm == 1) {
							jj_hk_zzl = 1.0f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						}
						// 第2-3名
						else if (t_hk_zz_pm >= 2 && t_hk_zz_pm <= 3) {
							jj_hk_zzl = 0.8f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						}
						// 第4-7名
						else if (t_hk_zz_pm >= 4 && t_hk_zz_pm <= 7) {
							jj_hk_zzl = 0.6f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						}
					}
					// B类经办
					else if (t_jb_type == 2) {
						if (t_hk_zz_pm <= 2) {
							jj_hk_zzl = 0.8f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						} else if (t_hk_zz_pm >= 3 && t_hk_zz_pm <= 5) {
							jj_hk_zzl = 0.6f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						} else if (t_hk_zz_pm >= 6 && t_hk_zz_pm <= 10) {
							jj_hk_zzl = 0.4f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						}
					}
					// C类经办
					else if (t_jb_type == 3) {
						if (t_hk_zz_pm <= 2) {
							jj_hk_zzl = 0.6f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						} else if (t_hk_zz_pm >= 3 && t_hk_zz_pm <= 6) {
							jj_hk_zzl = 0.4f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						} else if (t_hk_zz_pm >= 7 && t_hk_zz_pm <= 13) {
							jj_hk_zzl = 0.2f;
							t_jj_hk_zzl += jj_hk_zzl;
							temp.getMap().put("jj_hk_zzl", jj_hk_zzl);
						}
					}

				}
			}
		}

		if (t_task_money.compareTo(BigDecimal.valueOf(0)) != 0) {
			t_hk_wcl = t_hk_money.divide(t_task_money, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
		}
		if (t_l_hk_money.compareTo(BigDecimal.valueOf(0)) != 0) {
			t_hk_zzl = (t_hk_money.subtract(t_l_hk_money)).divide(t_l_hk_money, 4, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100));
		}

		request.setAttribute("t_jj_hk_wcl", new BigDecimal(t_jj_hk_wcl).setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_jj_hk_zzl", new BigDecimal(t_jj_hk_zzl).setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("task", t_task);
		request.setAttribute("task_money", t_task_money);
		request.setAttribute("hk_money", t_hk_money);
		request.setAttribute("l_hk_money", t_l_hk_money);
		request.setAttribute("t_hk_wcl", t_hk_wcl.setScale(2, BigDecimal.ROUND_HALF_UP));
		request.setAttribute("t_hk_zzl", t_hk_zzl.setScale(2, BigDecimal.ROUND_HALF_UP));

		Collections.reverse(entityList);
		request.setAttribute("entityList", entityList);

		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		c.set(Calendar.DAY_OF_MONTH, 1);// 当月第一天
		String firstDay = format.format(c.getTime());
		request.setAttribute("firstDay", firstDay);
		c1.add(c1.DATE, -1); // 设置为前一天
		String yestDay = format.format(c1.getTime());
		request.setAttribute("yestDay", yestDay);

		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(now)) - y) + "");
		}
		request.setAttribute("yearList", yearList);

		// 取最新時間
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		KonkaR3Backmoney cData = new KonkaR3Backmoney();
		cData.getMap().put("max_date", true);
		cData = super.getFacade().getKonkaR3BackmoneyService().getKonkaR3Backmoney(cData);
		request.setAttribute("new_date", sfmt.format(cData.getAdd_date()));

		request.setAttribute("size", entityList.size());

		return mapping.findForward("list");

	}

	public class ComparatorWcl implements Comparator<KonkaDeptJbTask> {

		@Override
		public int compare(KonkaDeptJbTask arg0, KonkaDeptJbTask arg1) {
			KonkaDeptJbTask user0 = (KonkaDeptJbTask) arg0;
			KonkaDeptJbTask user1 = (KonkaDeptJbTask) arg1;

			BigDecimal hk_wcl1 = (BigDecimal) user0.getMap().get("hk_wcl");
			BigDecimal hk_wcl2 = (BigDecimal) user1.getMap().get("hk_wcl");
			int flag = 0;
			if (hk_wcl1 != null && hk_wcl2 != null) {
				flag = hk_wcl1.compareTo(hk_wcl2);
			}
			return flag;

		}
	}

	public class ComparatorZzl implements Comparator<KonkaDeptJbTask> {

		@Override
		public int compare(KonkaDeptJbTask arg0, KonkaDeptJbTask arg1) {
			KonkaDeptJbTask user0 = (KonkaDeptJbTask) arg0;
			KonkaDeptJbTask user1 = (KonkaDeptJbTask) arg1;

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
