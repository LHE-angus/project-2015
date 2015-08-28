package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-22
 */
public class KonkaR3PdNameForCxAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		calendar.add(Calendar.DATE, -30);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("sell_date_start", day_first);// 默认当前日期的前30天
		dynaBean.set("sell_date_end", day_last);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
			return this.list(mapping, form, request, response);
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				return this.list(mapping, form, request, response);
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		return null;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

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

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

		List<?> entityList = this.getR3PdNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, pd_name, show_top);
		request.setAttribute("entityList", entityList);
		request.setAttribute("char_x_heigth", entityList.size() * 30);
		return new ActionForward("/admin/KonkaR3PdNameForCx/list.jsp");

	}

	public ActionForward chart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String pd_name = (String) dynaBean.get("pd_name");

		if (StringUtils.isNotBlank(pd_name)) {
			pd_name = new String(pd_name.getBytes("iso-8859-1"), "UTF-8");
		}

		List<?> list = this.getR3PdNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id, l5_dept_id,
				pd_name, show_top);
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();

		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();
			// List<BaseChart> baseChartList_2 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[0] == null ? "" : obj[0].toString());
				baseChartList.add(baseChart);

				if ("1".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[1] == null ? "0" : obj[1].toString());
					baseChartList_1.add(baseChart1);
				} else if ("2".equals(tj_type)) {
					BaseChart baseChart1 = new BaseChart();
					baseChart1.setValue(obj[2] == null ? "0" : obj[2].toString());
					baseChartList_1.add(baseChart1);
				}

			}

			if ("1".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售额（元）");
				msLineChart1.setColor("3366FF");
				msLineChart1.setAnchor_border_color("3366FF");
				msLineChart1.setAnchor_bg_color("3366FF");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			} else if ("2".equals(tj_type)) {
				//
				MsLineChart msLineChart1 = new MsLineChart();
				msLineChart1.setSeries_name("销售量（台）");
				msLineChart1.setColor("F1683C");
				msLineChart1.setAnchor_border_color("F1683C");
				msLineChart1.setAnchor_bg_color("F1683C");
				msLineChart1.setBaseChartList(baseChartList_1);
				msLineChartList.add(msLineChart1);
			}

		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		// model.put("unit", "单位：元");
		String caption = "销售额";
		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			caption = "销售量";
		}
		// if (StringUtils.isNotBlank(chart_type) && "2".equals(chart_type)) {
		// model.put("caption", "销售毛利分析-" + caption + "(折线图)");
		// render(response, getFacade().getTemplateService().getContent("chart/MSLine.ftl", model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "畅销产品统计(" + caption + ")");
		// render(response, getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model),
		// "text/xml;charset=GBK");
		String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;

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