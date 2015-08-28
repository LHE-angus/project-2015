package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.SysObjData;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.SerializeUtil;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Gauge;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.util.EnhancedOption;

/**
 * @author TUDP
 * @version 2014-12-08
 * @desc 多媒体销售完成情况
 */
public class JcfxReportXswcAction extends BaseAction {
	public static String[][] city = { { "北京", "116.46", "39.92" },
			{ "长春", "125.35", "43.88" }, { "长沙", "113", "28.21" },
			{ "成都", "104.06", "30.67" }, { "东莞", "113.75", "23.04" },
			{ "福州", "119.3", "26.08" }, { "广州", "113.23", "23.16" },
			{ "贵阳", "106.71", "26.57" }, { "哈尔滨", "126.63", "45.75" },
			{ "海口", "110.35", "20.02" }, { "杭州", "120.19", "30.26" },
			{ "合肥", "117.27", "31.86" }, { "衡阳", "112.61", "26.89" },
			{ "呼和浩特", "111.65", "40.82" }, { "锦州", "121.15", "41.13" },
			{ "荆州", "112.18", "30.35" }, { "昆明", "102.73", "25.04" },
			{ "兰州", "103.73", "36.03" }, { "内江", "105.04", "29.59" },
			{ "南昌", "115.89", "28.68" }, { "南京", "118.78", "32.04" },
			{ "南宁", "108.33", "22.84" }, { "南通", "120.86", "32.01" },
			{ "南阳", "112.53", "33.01" }, { "上海", "121.48", "31.22" },
			{ "深圳", "114.07", "22.62" }, { "沈阳", "123.38", "41.8" },
			{ "石家庄", "114.48", "38.03" }, { "苏州", "120.62", "31.32" },
			{ "太原", "112.53", "37.87" }, { "天津", "117.2", "39.13" },
			{ "温州", "120.65", "28.01" }, { "乌鲁木齐", "87.68", "43.77" },
			{ "芜湖", "118.38", "31.33" }, { "武汉", "114.31", "30.52" },
			{ "西安", "108.95", "34.27" }, { "厦门", "118.1", "24.46" },
			{ "徐州", "117.2", "34.26" }, { "郑州", "113.65", "34.76" },
			{ "重庆", "106.54", "29.59" }, { "保定", "115.48", "38.85" },
			{ "唐山", "118.02", "39.63" }, { "大连", "121.62", "38.92" },
			{ "佛山", "113.11", "23.05" }, { "南充", "106.06", "30.8" },
			{ "怀化", "109.95", "27.52" }, { "邯郸", "114.47", "36.6" },
			{ "济宁", "116.59", "35.38" }, { "柳州", "109.4", "24.33" },
			{ "齐齐哈尔", "123.97", "47.33" }, { "宁波", "121.56", "29.86" },
			{ "青岛", "120.33", "36.07" }, { "中山", "113.38", "22.52" },
			{ "济南", "117", "36.65" }, { "茂名", "110.88", "21.68" },
			{ "淄博", "118.05", "36.78" }, { "临沂", "118.35", "35.05" },
			{ "牡丹江", "129.58", "44.6" }, { "电子商务", "111.35", "30.05" },
			{ "德绵", "103.45", "30.31" } };

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		request.setAttribute("title", "多媒体销售完成情况");
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}
		StatisticalDimensionDataMonth sddmarea2 = new StatisticalDimensionDataMonth();
		sddmarea2.getMap().put("year", year);
		sddmarea2.getMap().put("month", month);
		sddmarea2.getMap().put("groupbyWhat", "b.fgs_name");
		String option3="";
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
				+ "b.fgs_name");
		obj.setObj_name("JcfxReportXswcAction.option3");
		List<SysObjData> objList = super.getFacade().getSysObjDataService().getSysObjDataList(obj);
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			option3 = (String) SerializeUtil.byteArrayToObject(obj.getObj_content());
		}else{
			List<StatisticalDimensionDataMonth> fgsListLj = null;
			int objTime = 3600;
			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
					+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.fgsListLj");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				fgsListLj = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				fgsListLj = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByAreaLj(sddmarea2);
				obj.setObj_content(SerializeUtil.objectToByteArray(fgsListLj));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}
	
			for (int i = 0; i < fgsListLj.size(); i++) {
				String name = (String) fgsListLj.get(i).getMap().get("name");
				fgsListLj.get(i).getMap().get("customer_sale_lj");
				StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
				m.getMap().put("dept_name", name);
				m.getMap().put("year", year);
				m.getMap().put("month", month);
				String x = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getDimensionDataMonthFgsJieSuanTask(m);
				if (x == null) {
					x = "0";
				}
				fgsListLj.get(i).getMap()
						.put("customer_sale_lj", new BigDecimal(x));
			}
			option3=this.getOption3(year, month, fgsListLj);
		
			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.option3");
			obj.setObj_content(SerializeUtil.objectToByteArray(option3));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
		}
		request.setAttribute("option3", option3);
		return mapping.findForward("view");
	}

	public ActionForward table1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}
		List<StatisticalDimensionDataMonth> table1List=null;
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
				+ "b.fgs_name");
		obj.setObj_name("JcfxReportXswcAction.table1.table1List");
		List<SysObjData> objList = super.getFacade().getSysObjDataService()
				.getSysObjDataList(obj); 
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			table1List = (List<StatisticalDimensionDataMonth>) SerializeUtil.byteArrayToObject(obj.getObj_content());
		} else {
			// 根据区域分组，获取信息
			StatisticalDimensionDataMonth sddmarea = new StatisticalDimensionDataMonth();
			sddmarea.getMap().put("year", year);
			sddmarea.getMap().put("groupbyWhat", "b.area_name");
			List<StatisticalDimensionDataMonth> areaListLj = null;
	
			obj = new SysObjData();
			obj.setObj_key("year" + year + "groupbyWhat" + "b.area_name");
			obj.setObj_name("JcfxReportXswcAction.areaListLj");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);
	
			int objTime = 3600;
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				areaListLj = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				areaListLj = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByAreaLj(sddmarea);
				obj.setObj_content(SerializeUtil.objectToByteArray(areaListLj));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}
			sddmarea.getMap().put("month", month);
			List<StatisticalDimensionDataMonth> areaList = null;
	
			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
					+ "b.area_name");
			obj.setObj_name("JcfxReportXswcAction.areaList");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);
	
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				areaList = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				areaList = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByArea(sddmarea);
				obj.setObj_content(SerializeUtil.objectToByteArray(areaList));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}
	
			for (int i = 0; i < areaList.size(); i++) {
				// 修改任务目标
				StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
				m.getMap().put(
						"area_name",
						areaList.get(i).getMap().get("name") == null ? "-"
								: areaList.get(i).getMap().get("name"));
				m.getMap().put("year", year);
				m.getMap().put("is_fgs", "1");
				m.getMap().put("month", "0");
				String x = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getDimensionDataMonthFgsJieSuanTask(m);
				m.getMap().put("month", month);
				String x1 = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getDimensionDataMonthFgsJieSuanTask(m);
				if (x1 == null) {
					x1 = "0";
				}
				if (x == null) {
					x = "0";
				}
				areaList.get(i).setMonth_customer_sale(new BigDecimal(x1));
				areaList.get(i).getMap().put("customer_sale_lj", new BigDecimal(x));
				// areaList.get(i).getMap().put("customer_sale_lj",
				// areaListLj.get(i).getMap().get("customer_sale_lj"));
				areaList.get(i)
						.getMap()
						.put("settle_money_lj",
								areaListLj.get(i).getMap().get("settle_money_lj"));
				areaList.get(i)
						.getMap()
						.put("customer_rwwcbfb_lj",
								areaListLj.get(i).getMap()
										.get("customer_rwwcbfb_lj"));
			}
			table1List=areaList;
			
			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.table1.table1List");
			obj.setObj_content(SerializeUtil.objectToByteArray(table1List));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
			
		}
		request.setAttribute("areaList", table1List);
		return new ActionForward("/../manager/admin/JcfxReportXswc/table1.jsp");
	}

	public ActionForward table2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}

		List<StatisticalDimensionDataMonth> fgsListx = new ArrayList<StatisticalDimensionDataMonth>();
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
				+ "b.fgs_name");
		obj.setObj_name("JcfxReportXswcAction.table2.fgsListx");
		List<SysObjData> objList = super.getFacade().getSysObjDataService()
				.getSysObjDataList(obj);

		int objTime = 3600;
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			fgsListx = (List<StatisticalDimensionDataMonth>) SerializeUtil
					.byteArrayToObject(obj.getObj_content());
		} else {
			List<StatisticalDimensionDataMonth> fgsListLj = null;
			StatisticalDimensionDataMonth sddmarea2 = new StatisticalDimensionDataMonth();
			sddmarea2.getMap().put("year", year);
			sddmarea2.getMap().put("groupbyWhat", "b.fgs_name");
			obj = new SysObjData();
			obj.setObj_key("year" + year + "groupbyWhat" + "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.fgsListLj");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				fgsListLj = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				fgsListLj = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByAreaLj(sddmarea2);
				obj.setObj_content(SerializeUtil.objectToByteArray(fgsListLj));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}

			sddmarea2.getMap().put("month", month);
			List<StatisticalDimensionDataMonth> fgsListQd = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaForQd(sddmarea2);

			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
					+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.fgsListQd");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);

			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				fgsListQd = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				fgsListQd = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByAreaForQd(sddmarea2);
				obj.setObj_content(SerializeUtil.objectToByteArray(fgsListQd));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}

			for (int i = 0; i < fgsListQd.size(); i++) {
				String name = (String) fgsListLj.get(i).getMap().get("name");
				if (name == null) {
					name = "-";
				}
				int z = this.getCityIndex(name);
				//if (z != -1) {
					// 修改任务目标
					StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
					m.getMap().put("dept_name", name);
					m.getMap().put("year", year);
					m.getMap().put("month", "0");
					String x = super.getFacade()
							.getStatisticalDimensionDataMonthService()
							.getDimensionDataMonthFgsJieSuanTask(m);
					m.getMap().put("month", month);
					String x1 = super.getFacade()
							.getStatisticalDimensionDataMonthService()
							.getDimensionDataMonthFgsJieSuanTask(m);
					if (x == null) {
						x = "0";
					}
					if (x1 == null) {
						x1 = "0";
					}
					fgsListQd.get(i).setMonth_customer_sale(new BigDecimal(x1));
					fgsListQd.get(i).getMap()
							.put("customer_sale_lj", new BigDecimal(x));

					// fgsListQd.get(i).getMap().put("customer_sale_lj",
					// fgsListLj.get(i).getMap().get("customer_sale_lj"));

					fgsListQd
							.get(i)
							.getMap()
							.put("settle_money_lj",
									fgsListLj.get(i).getMap()
											.get("settle_money_lj"));
					fgsListQd
							.get(i)
							.getMap()
							.put("customer_rwwcbfb_lj",
									fgsListLj.get(i).getMap()
											.get("customer_rwwcbfb_lj"));
					fgsListx.add(fgsListQd.get(i));
				//}
			}

			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.table2.fgsListx");
			obj.setObj_content(SerializeUtil.objectToByteArray(fgsListx));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
		}
		request.setAttribute("fgsList", fgsListx);
		return new ActionForward("/../manager/admin/JcfxReportXswc/table2.jsp");
	}

	public ActionForward option1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}

		StatisticalDimensionDataMonth sddm1 = new StatisticalDimensionDataMonth();
		sddm1.getMap().put("year", year);
		sddm1.getMap().put("month", month);
		List<StatisticalDimensionDataMonth> optionList1 = null;
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month);
		obj.setObj_name("JcfxReportXswcAction.optionList1");
		List<SysObjData> objList = super.getFacade().getSysObjDataService()
				.getSysObjDataList(obj);

		int objTime = 3600;
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			optionList1 = (List<StatisticalDimensionDataMonth>) SerializeUtil
					.byteArrayToObject(obj.getObj_content());
		} else {
			optionList1 = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getRetailMoneyByYearForMonths(sddm1);
			obj.setObj_content(SerializeUtil.objectToByteArray(optionList1));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
		}

		BigDecimal month_settle_money = new BigDecimal("0");
		BigDecimal month_customer_sale = new BigDecimal("0");

		StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
		m.getMap().put("year", year);
		m.getMap().put("month", month);
		m.getMap().put("is_fgs", "1");
		String x = super.getFacade().getStatisticalDimensionDataMonthService()
				.getDimensionDataMonthFgsJieSuanTask(m);
		if (x == null) {
			x = "0";
		}
		month_customer_sale = new BigDecimal(x);
		BigDecimal wcl = new BigDecimal("0");
		if (null != optionList1 && optionList1.size() > 0) {
			if (optionList1.get(0) != null) {
				month_settle_money = optionList1.get(0).getMonth_settle_money();
				// month_customer_sale =
				// optionList1.get(0).getMonth_customer_sale();
				if (month_settle_money == null) {
					month_settle_money = new BigDecimal("0");
				}
				if (!String.valueOf(month_customer_sale).equals("0")) {
					wcl = month_settle_money.multiply(new BigDecimal("100"))
							.divide(month_customer_sale, 2);
				}
			}
		}

		EnhancedOption option1 = new EnhancedOption();
		option1 = this.getOption1(year,
				wcl.setScale(2, BigDecimal.ROUND_HALF_UP));
		String optionStr1 = GsonUtil.format(option1);
		Map map = new HashMap();
		map.put("title", year + "年" + month + "月多媒体销售情况");
		map.put("option_a",
				month_settle_money.setScale(2, BigDecimal.ROUND_HALF_UP));
		map.put("option_b", month_customer_sale);
		map.put("option", optionStr1);
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	public ActionForward option2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}
		Map map = new HashMap();
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year+"month"+month);
		obj.setObj_name("JcfxReportXswcAction.option2.map");
		List<SysObjData> objList = super.getFacade().getSysObjDataService().getSysObjDataList(obj); 
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			map = (HashMap) SerializeUtil.byteArrayToObject(obj.getObj_content());
		} else {	
			StatisticalDimensionDataMonth sddm1 = new StatisticalDimensionDataMonth();
			sddm1.getMap().put("year", year);
			List<StatisticalDimensionDataMonth> optionList2 = null;
			obj = new SysObjData();
			obj.setObj_key("year" + year);
			obj.setObj_name("JcfxReportXswcAction.optionList2");
			objList = super.getFacade().getSysObjDataService().getSysObjDataList(obj);
			int objTime = 3600;
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				optionList2 = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				optionList2 = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getRetailMoneyByYearForMonths(sddm1);
				obj.setObj_content(SerializeUtil.objectToByteArray(optionList2));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}
	
			BigDecimal[] month_settle_money2 = new BigDecimal[12];
			BigDecimal[] month_customer_sale2 = new BigDecimal[12];
			BigDecimal[] pre_year_settle_money2 = new BigDecimal[12];
			Object[] year2 = new Object[12];
			Object[] month2 = new Object[12];
			String[] data2 = new String[12];
	
			for (int i = 0; i < 12; i++) {
				month_settle_money2[i] = new BigDecimal("0");
				month_customer_sale2[i] = new BigDecimal("0");
				StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
				m.getMap().put("year", year);
				m.getMap().put("month", i + 1);
				m.getMap().put("is_fgs", "1");
				// 目标销售额
				String x = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getDimensionDataMonthFgsJieSuanTask(m);
				if (x == null) {
					x = "0";
				}
				month_customer_sale2[i] = new BigDecimal(x);
	
				pre_year_settle_money2[i] = new BigDecimal("0");
				year2[i] = year;
				month2[i] = "" + (i + 1);
				data2[i] = year2[i].toString() + "年" + month2[i].toString() + "月";
			}
	
			for (int i = 0; i < optionList2.size(); i++) {
				month_settle_money2[i] = optionList2.get(i).getMonth_settle_money() == null ? new BigDecimal(
						"0") : optionList2.get(i).getMonth_settle_money();
				// month_customer_sale2[i] =
				// optionList2.get(i).getMonth_customer_sale()==null? new
				// BigDecimal("0"): optionList2.get(i).getMonth_customer_sale();
				pre_year_settle_money2[i] = optionList2.get(i)
						.getPre_year_settle_money() == null ? new BigDecimal("0")
						: optionList2.get(i).getPre_year_settle_money();
				year2[i] = optionList2.get(i).getMap().get("year");
				month2[i] = optionList2.get(i).getMap().get("month");
				data2[i] = year2[i].toString() + "年" + month2[i].toString() + "月";
			}
			EnhancedOption option2 = new EnhancedOption();
			option2.title("", "金额:万元");
			option2.tooltip().trigger(Trigger.axis);
			option2.legend("目标销售额", "实际销售额", "同期销售额");
			option2.toolbox()
					.show(true)
					.feature(
							Tool.mark,
							Tool.dataView,
							new MagicType(Magic.line, Magic.bar, Magic.stack,
									Magic.tiled), Tool.restore, Tool.saveAsImage);
			option2.calculable(true);
			option2.xAxis(new CategoryAxis().boundaryGap(false).data(data2));
			option2.yAxis(new ValueAxis());
	
			Line l1 = new Line("目标销售额");
			l1.smooth(true).itemStyle().normal();
			l1.data(month_customer_sale2);
			Line l2 = new Line("实际销售额");
			l2.smooth(true).itemStyle().normal();
			l2.data(month_settle_money2);
			Line l3 = new Line("同期销售额");
			l3.smooth(true).itemStyle().normal();
			l3.data(pre_year_settle_money2);
			option2.series(l1, l2, l3);
			String optionStr = GsonUtil.format(option2);
			map.put("title", year + "年多媒体销售情况");
			map.put("option", optionStr);
			
			obj = new SysObjData();
			obj.setObj_key("year" + year+"month"+month);
			obj.setObj_name("JcfxReportXswcAction.option2.map");
			obj.setObj_content(SerializeUtil.objectToByteArray(map));
			obj.setObj_time(objTime*24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
			
		}
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	public ActionForward option3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}
		StatisticalDimensionDataMonth sddmarea2 = new StatisticalDimensionDataMonth();
		sddmarea2.getMap().put("year", year);
		sddmarea2.getMap().put("month", month);
		sddmarea2.getMap().put("groupbyWhat", "b.fgs_name");
		List<StatisticalDimensionDataMonth> fgsListLj = null;

		int objTime = 3600;
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
				+ "b.fgs_name");
		obj.setObj_name("JcfxReportXswcAction.fgsListLj");
		List<SysObjData> objList = super.getFacade().getSysObjDataService()
				.getSysObjDataList(obj);
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			fgsListLj = (List<StatisticalDimensionDataMonth>) SerializeUtil.byteArrayToObject(obj.getObj_content());
		} else {
			fgsListLj = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaLj(sddmarea2);
			obj.setObj_content(SerializeUtil.objectToByteArray(fgsListLj));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
		}

		for (int i = 0; i < fgsListLj.size(); i++) {
			String name = (String) fgsListLj.get(i).getMap().get("name");
			fgsListLj.get(i).getMap().get("customer_sale_lj");
			StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
			m.getMap().put("dept_name", name);
			m.getMap().put("year", year);
			m.getMap().put("month", month);
			String x = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getDimensionDataMonthFgsJieSuanTask(m);
			if (x == null) {
				x = "0";
			}
			fgsListLj.get(i).getMap()
					.put("customer_sale_lj", new BigDecimal(x));
		}
		String optionStr = this.getOption3(year, month, fgsListLj);
		Map map = new HashMap();
		map.put("title", year + "年" + month + "月 多媒体销售情况");
		map.put("option", optionStr);
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	public ActionForward option4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
		}
		Map map = new HashMap();
		SysObjData obj = new SysObjData();
		obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
				+ "b.fgs_name");
		obj.setObj_name("JcfxReportXswcAction.option4.map");
		List<SysObjData> objList = super.getFacade().getSysObjDataService()
				.getSysObjDataList(obj);
		if (objList != null && objList.size() > 0) {
			obj = objList.get(0);
			map = (HashMap) SerializeUtil.byteArrayToObject(obj
					.getObj_content());
		} else {
			// 根据区域分组，获取信息
			StatisticalDimensionDataMonth sddmarea2 = new StatisticalDimensionDataMonth();
			sddmarea2.getMap().put("year", year);
			sddmarea2.getMap().put("groupbyWhat", "b.fgs_name");
			sddmarea2.getMap().put("month", month);
			List<StatisticalDimensionDataMonth> areaList2 = null;
			int objTime = 3600;
			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
					+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.option4.areaList2");
			objList = super.getFacade().getSysObjDataService()
					.getSysObjDataList(obj);
			if (objList != null && objList.size() > 0) {
				obj = objList.get(0);
				areaList2 = (List<StatisticalDimensionDataMonth>) SerializeUtil
						.byteArrayToObject(obj.getObj_content());
			} else {
				areaList2 = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getMoneysGroupByArea(sddmarea2);
				obj.setObj_content(SerializeUtil.objectToByteArray(areaList2));
				obj.setObj_time(objTime * 24);
				super.getFacade().getSysObjDataService().createSysObjData(obj);
			}

			List<Object> option4_city = new ArrayList<Object>();
			List<Object> option4_data1 = new ArrayList<Object>();
			List<Object> option4_data2 = new ArrayList<Object>();
			List<Object> option4_data3 = new ArrayList<Object>();
			String citys = "";
			for (int i = 0; i < city.length; i++) {
				citys += "," + city[i][0];
			}
			for (int i = 0; i < areaList2.size(); i++) {
				StatisticalDimensionDataMonth xx = areaList2.get(i);
				String name = (String) xx.getMap().get("name");
				if (name != null && !"".equals(name)
						&& citys.indexOf(name) != -1) {
					option4_city.add(xx.getMap().get("name"));

					StatisticalDimensionDataMonth m = new StatisticalDimensionDataMonth();
					m.getMap().put("dept_name", name);
					m.getMap().put("year", year);
					m.getMap().put("month", month);
					// 目标销售额
					String x = super.getFacade()
							.getStatisticalDimensionDataMonthService()
							.getDimensionDataMonthFgsJieSuanTask(m);
					option4_data1.add(new BigDecimal(x));
					// option4_data1.add(xx.getMonth_customer_sale()==null?new
					// BigDecimal("0"):xx.getMonth_customer_sale());
					option4_data2
							.add(xx.getMonth_settle_money() == null ? new BigDecimal(
									"0") : xx.getMonth_settle_money());
					option4_data3
							.add(xx.getPre_year_settle_money() == null ? new BigDecimal(
									"0") : xx.getPre_year_settle_money());
				}
			}

			if (option4_data1 == null || option4_data1.size() < 1) {
				for (int i = 0; i < this.city.length; i++) {
					option4_city.add(city[i][0]);
					option4_data1.add(new BigDecimal("0"));
					option4_data2.add(new BigDecimal("0"));
					option4_data3.add(new BigDecimal("0"));
				}
			}

			String optionStr = GsonUtil.format(this.getOption4(year,
					option4_city, option4_data1, option4_data2, option4_data3));
			map.put("title", year + "年" + month + "月分公司销售情况");
			map.put("option", optionStr);
			map.put("city", option4_city);

			obj = new SysObjData();
			obj.setObj_key("year" + year + "month" + month + "groupbyWhat"
					+ "b.fgs_name");
			obj.setObj_name("JcfxReportXswcAction.option4.map");
			obj.setObj_content(SerializeUtil.objectToByteArray(map));
			obj.setObj_time(objTime * 24);
			super.getFacade().getSysObjDataService().createSysObjData(obj);
		}

		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	private EnhancedOption getOption1(String year, BigDecimal f)
			throws Exception {
		EnhancedOption option = new EnhancedOption();
		option.tooltip().formatter("{a} <br/>{b} : {c}%");
		option.toolbox().show(true)
				.feature(Tool.mark, Tool.restore, Tool.saveAsImage);
		option.series(new Gauge(year + "多媒体销售情况").startAngle(180).endAngle(0)
				.detail(new Detail().formatter("{value}%"))
				.data(new Data("完成率", f)));
		return option;
	}

	private EnhancedOption getOption4(String year, List<Object> city,
			List<Object> data1, List<Object> data2, List<Object> data3)
			throws Exception {
		EnhancedOption option = new EnhancedOption();
		// option.title().text(year+"多媒体销售情况");
		option.tooltip().trigger(Trigger.axis);
		Legend legend = new Legend();
		legend.itemWidth(10);
		legend.itemHeight(10);
		legend.padding(25);
		TextStyle textStyle = new TextStyle();
		textStyle.fontSize(11);
		legend.setTextStyle(textStyle);
		legend.data(city);
		option.legend(legend);
		option.toolbox()
				.show(true)
				.feature(Tool.mark, Tool.dataView,
						new MagicType(Magic.line, Magic.bar).show(true),
						Tool.restore, Tool.saveAsImage);
		option.calculable(true);
		option.xAxis(new CategoryAxis().data("目标销售额", "实际销售额", "同期销售额"));
		option.yAxis(new ValueAxis());
		option.grid().height(200);
		option.grid().y(140);
		option.grid().y2(5);
		option.grid().x(5);
		option.grid().x2(5);

		Bar[] bars = new Bar[city.size()];
		Bar bar = new Bar();
		for (int i = 0; i < city.size(); i++) {
			bar = new Bar(city.get(i).toString());
			bar.data(data1.get(i), data2.get(i), data3.get(i));
			// bar.markPoint().data(new
			// PointData().type(MarkType.max).name("最大值"), new
			// PointData().type(MarkType.min).name("最小值"));
			// bar.markLine().data(new
			// PointData().type(MarkType.average).name("平均值"));
			bars[i] = bar;
		}
		option.series(bars);
		return option;
	}

	private String getOption3(String year, String month,
			List<StatisticalDimensionDataMonth> fgsListLj) throws Exception {
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		if (fgsListLj != null)
			for (int i = 0; i < fgsListLj.size(); i++) {
				map = new HashMap();
				String name = (String) fgsListLj.get(i).getMap().get("name");
				BigDecimal value = new BigDecimal("0.0");
				String latlng = "";
				BigDecimal customer_sale_lj = (BigDecimal) fgsListLj.get(i)
						.getMap().get("customer_sale_lj");
				BigDecimal settle_money_lj = (BigDecimal) fgsListLj.get(i)
						.getMap().get("settle_money_lj");
				if (customer_sale_lj.intValue() != 0) {
					value = settle_money_lj.multiply(new BigDecimal(100))
							.divide(customer_sale_lj, 2)
							.setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				int z = this.getCityIndex(name);
				if (z != -1) {
					latlng = city[z][1] + "," + city[z][2];
					map.put("name", name);
					map.put("value", value.toString());
					map.put("latlng", latlng);
					list.add(map);
				}
			}

		Collections.sort(list, new Comparator<HashMap>() {
			@Override
			public int compare(HashMap arg0, HashMap arg1) {
				return new Float(arg1.get("value").toString())
						.compareTo(new Float(arg0.get("value").toString()));
			}
		});
		StringBuffer sb = new StringBuffer();
		sb.append("{	\n");
		sb.append("	    title : {	\n");
		sb.append("	        text: '" + year + "年" + month + "月份 分公司销售完成情况',	\n");
		sb.append("	        subtext: ' ',	\n");
		sb.append("	        sublink: ' ',	\n");
		sb.append("	        x:'center'	\n");
		sb.append("	    },	\n");
		sb.append("	    tooltip : {	\n");
		sb.append("	        trigger: 'item'	\n");
		sb.append("	    },	\n");
		sb.append("	    legend: {	\n");
		sb.append("	        orient: 'vertical',	\n");
		sb.append("	        x:'left',	\n");
		sb.append("	        data:['完成率']	\n");
		sb.append("	    },	\n");
		sb.append("	    dataRange: {	\n");
		sb.append("	        min : 0,	\n");
		sb.append("	        max : 100,	\n");
		sb.append("	        calculable : true,	\n");
		sb.append("	        color: ['maroon','purple','red','orange','yellow','lightgreen']	\n");
		sb.append("	    },	\n");
		sb.append("	    toolbox: {	\n");
		sb.append("	        show : true,	\n");
		sb.append("	        orient : 'vertical',	\n");
		sb.append("	        x: 'right',	\n");
		sb.append("	        y: 'center',	\n");
		sb.append("	        feature : {	\n");
		sb.append("	            mark : {show: true},	\n");
		sb.append("	            dataView : {show: true, readOnly: false},	\n");
		sb.append("	            restore : {show: true},	\n");
		sb.append("	            saveAsImage : {show: true}	\n");
		sb.append("	        }	\n");
		sb.append("	    },	\n");
		sb.append("	    series : [	\n");
		sb.append("	        {	\n");
		sb.append("	            name: '完成率%',	\n");
		sb.append("	            type: 'map',	\n");
		sb.append("	            mapType: 'china',	\n");
		sb.append("	            hoverable: false,	\n");
		sb.append("	            roam:true,	\n");
		sb.append("	            data : [],	\n");
		sb.append("	            markPoint : {	\n");
		sb.append("	                symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2	\n");
		sb.append("	                itemStyle: {	\n");
		sb.append("	                    normal: {	\n");
		sb.append("	                        borderColor: '#87cefa',	\n");
		sb.append("	                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    },	\n");
		sb.append("	                    emphasis: {	\n");
		sb.append("	                        borderColor: '#1e90ff',	\n");
		sb.append("	                        borderWidth: 5,	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    }	\n");
		sb.append("	                },	\n");
		sb.append("	                data : [	\n");
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).get("name").toString();
			String value = list.get(i).get("value").toString();
			if (i + 1 != list.size()) {
				sb.append("	                    {name: \"" + name
						+ "\", value: " + value + "},	\n");
			} else {
				sb.append("	                    {name: \"" + name
						+ "\", value: " + value + "}	\n");
			}
		}
		// sb.append("	                    {name: \"海门\", value: 9},	\n"); //数据
		// sb.append("	                    {name: \"大庆\", value: 279}	\n");
		sb.append("	                ]	\n");
		sb.append("	            },	\n");
		sb.append("	            geoCoord: {	\n");
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).get("name").toString();
			String latlng = list.get(i).get("latlng").toString();
			if (i + 1 != list.size()) {
				sb.append("	                \"" + name + "\":[" + latlng
						+ "],	\n");
			} else {
				sb.append("	                \"" + name + "\":[" + latlng
						+ "]	\n");
			}
		}
		// sb.append("	                \"海门\":[121.15,31.89],	\n"); //经纬度
		// sb.append("	                \"大庆\":[125.03,46.58]	\n");
		sb.append("	            }	\n");
		sb.append("	        },	\n");
		sb.append("	        {	\n");
		sb.append("	            name: 'Top5',	\n");
		sb.append("	            type: 'map',	\n");
		sb.append("	            mapType: 'china',	\n");
		sb.append("	            data:[],	\n");
		sb.append("	            markPoint : {	\n");
		sb.append("	                symbol:'emptyCircle',	\n");
		sb.append("	                symbolSize : function (v){	\n");
		sb.append("	                    return 10 + v/100	\n");
		sb.append("	                },	\n");
		sb.append("	                effect : {	\n");
		sb.append("	                    show: true,	\n");
		sb.append("	                    shadowBlur : 0	\n");
		sb.append("	                },	\n");
		sb.append("	                itemStyle:{	\n");
		sb.append("	                    normal:{	\n");
		sb.append("	                        label:{show:false}	\n");
		sb.append("	                    }	\n");
		sb.append("	                },	\n");
		sb.append("	                data : [	\n");
		for (int i = 0; i < 5 && i < list.size(); i++) {
			String name = list.get(i).get("name").toString();
			String value = list.get(i).get("value").toString();
			if (i + 1 != list.size()) {
				sb.append("	                    {name: \"" + name
						+ "\", value: " + value + "},	\n");
			} else {
				sb.append("	                    {name: \"" + name
						+ "\", value: " + value + "}	\n");
			}
		}
		// sb.append("	                    {name: \"廊坊\", value: 193},	\n");
		// //top5
		// sb.append("	                    {name: \"大庆\", value: 279}	\n");
		sb.append("	                ]	\n");
		sb.append("	            }	\n");
		sb.append("	        }	\n");
		sb.append("	    ]	\n");
		sb.append("	};	\n");
		return sb.toString();
	}

	private int getCityIndex(String name) {
		int z = -1;
		for (int i = 0; i < city.length; i++) {
			if (city[i][0].equals(name)) {
				return i;
			}
		}
		return z;
	}
}
