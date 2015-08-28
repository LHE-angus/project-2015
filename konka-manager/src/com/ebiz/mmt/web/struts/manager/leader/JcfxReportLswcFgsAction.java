package com.ebiz.mmt.web.struts.manager.leader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.StatisticalDimensionFgsLatlng;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.CacheObjUtil;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Gauge;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.util.EnhancedOption;

/**
 * @author TUDP
 * @version 2014-12-08
 * @desc 分公司零售完成情况
 */
public class JcfxReportLswcFgsAction extends BaseAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		request.setAttribute("title", "分公司零售完成情况");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String filter_by = (String) dynaBean.get("filter_by");
		String fgs_name = "";
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		
		KonkaDept dept=super.getKonkaDeptForFgs(user.getDept_id());
		if(dept!=null&&dept.getDept_id()!=null){
			fgs_id = dept.getDept_id().toString();
		}
		
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);
			request.setAttribute("year", year);
		}
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);
			request.setAttribute("month", month);
		}
		if (!GenericValidator.isInt(fgs_id)) {
			fgs_id = "2";
			dynaBean.set("fgs_id", fgs_id);
			request.setAttribute("fgs_id", fgs_id);
		}
		if (!GenericValidator.isBlankOrNull(filter_by)) {
			// fgs_id="2";
			dynaBean.set("filter_by", filter_by);
			request.setAttribute("filter_by", filter_by);
		}
		
		StatisticalDimensionFgsLatlng fgs = new StatisticalDimensionFgsLatlng();
		if(dept!=null&&dept.getDept_id()!=null&&dept.getDept_id().intValue()>1){
			fgs.setFgs_id(dept.getDept_id()); 
		}
		
		List<StatisticalDimensionFgsLatlng> fgsLatlngList = super.getFacade()
				.getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlngList(fgs);
		request.setAttribute("fgsLatlngList", fgsLatlngList);
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		return mapping.findForward("view");
	}

	public ActionForward table1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String filter_by = (String) dynaBean.get("filter_by");
		String fgs_name = "";
		Calendar calendar = Calendar.getInstance();
		if (!GenericValidator.isInt(year)) {
			int c_year = calendar.get(Calendar.YEAR);
			year = c_year + "";
			dynaBean.set("year", year);

		}
		request.setAttribute("year", year);
		if (!GenericValidator.isInt(month)) {
			int c_month = calendar.get(Calendar.MONTH) + 1;
			month = c_month + "";
			dynaBean.set("month", month);

		}
		request.setAttribute("month", month);
		if (!GenericValidator.isInt(fgs_id)) {
			fgs_id = "2";
			dynaBean.set("fgs_id", fgs_id);

		}
		request.setAttribute("fgs_id", fgs_id);
		if (!GenericValidator.isBlankOrNull(filter_by)) {
			filter_by = new String(filter_by.getBytes("ISO-8859-1"), "UTF-8");
			// fgs_id="2";
			dynaBean.set("filter_by", filter_by);
			//System.out.println(filter_by + "+++++++++++++");
			request.setAttribute("filter_by", filter_by);
		}
		StatisticalDimensionFgsLatlng fgs = new StatisticalDimensionFgsLatlng();
		fgs.setFgs_id(new Long(fgs_id));
		fgs = super.getFacade().getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlng(fgs);
		if(fgs != null)
			fgs_name = fgs.getFgs_name();

		// 根据区域分组，获取信息
		StatisticalDimensionDataMonth sddmarea = new StatisticalDimensionDataMonth();
		sddmarea.getMap().put("year", year);
		sddmarea.getMap().put("fgs_id", fgs_id);
		String groupby = "";

		if (!GenericValidator.isBlankOrNull(filter_by)) {
			// fgs_id="2";
			sddmarea.getMap().put("filter_by",
					"and d.c_comm='" + filter_by + "'");
			groupby = "groupbyWhat" + "d.c_name";
			sddmarea.getMap().put("groupbyWhat", "d.c_name");
		} else {
			sddmarea.getMap().put("groupbyWhat", "d.c_comm");
			groupby = "groupbyWhat" + "d.c_comm";
		}

		List<StatisticalDimensionDataMonth> areaListLj;

		int objTime = 3600 * 24;
		String objName = "JcfxReportLswcFgsAction.table1.table1Listlj";
		String objKey = "year" + year + "fgs_id" + fgs_id + groupby;

		CacheObjUtil cacheObjUtil = new CacheObjUtil(super.getFacade());
		areaListLj = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (areaListLj == null) {
			areaListLj = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaLj(sddmarea);

			cacheObjUtil.saveObj(areaListLj, objTime, objName, objKey);
		}

		objName = "JcfxReportLswcFgsAction.table1.table1List";
		objKey = "year" + year + "fgs_id" + fgs_id + "month=" + month + groupby;
		List<StatisticalDimensionDataMonth> areaList;
		sddmarea.getMap().put("month", month);
		areaList = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (areaList == null) {
			areaList = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaForQd(sddmarea);
			for (int i = 0; i < areaList.size(); i++) {
				areaList.get(i)
						.getMap()
						.put("customer_sale_lj",
								areaListLj.get(i).getMap()
										.get("customer_sale_lj"));
				areaList.get(i)
						.getMap()
						.put("retail_money_lj",
								areaListLj.get(i).getMap()
										.get("retail_money_lj"));
				areaList.get(i)
						.getMap()
						.put("month_retail_money_task_lj",
								areaListLj.get(i).getMap()
										.get("month_retail_money_task_lj"));
				areaList.get(i)
						.getMap()
						.put("customer_rwwcbfb_lj",
								areaListLj.get(i).getMap()
										.get("customer_rwwcbfb_lj"));
			}

			cacheObjUtil.saveObj(areaList, objTime, objName, objKey);

		}
		request.setAttribute("areaList", areaList);

		return new ActionForward(
				"/../manager/leader/JcfxReportLswcFgs/table1.jsp");
	}

	public ActionForward table2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String fgs_name = "";
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
		if (!GenericValidator.isInt(fgs_id)) {
			fgs_id = "2";
			dynaBean.set("fgs_id", fgs_id);
		}
		StatisticalDimensionFgsLatlng fgs = new StatisticalDimensionFgsLatlng();
		List<StatisticalDimensionFgsLatlng> fgsLatlngList = super.getFacade()
				.getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlngList(fgs);
		request.setAttribute("fgsLatlngList", fgsLatlngList);

		fgs = new StatisticalDimensionFgsLatlng();
		fgs.setFgs_id(new Long(fgs_id));
		fgs = super.getFacade().getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlng(fgs);
		if(fgs != null)
			fgs_name = fgs.getFgs_name();

		// 根据区域分组，获取信息
		StatisticalDimensionDataMonth sddmarea = new StatisticalDimensionDataMonth();
		sddmarea.getMap().put("year", year);
		sddmarea.getMap().put("fgs_id", fgs_id);
		sddmarea.getMap().put("groupbyWhat", "b.fgs_name");

		List<StatisticalDimensionDataMonth> areaListLj;

		int objTime = 3600 * 24;
		String objName = "JcfxReportLswcFgsAction.table2.table1Listlj";
		String objKey = "year" + year + "fgs_id" + fgs_id
				+ "group by b.fgs_name";

		CacheObjUtil cacheObjUtil = new CacheObjUtil(super.getFacade());
		areaListLj = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (areaListLj == null) {
			areaListLj = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaLj(sddmarea);
			cacheObjUtil.saveObj(areaListLj, objTime, objName, objKey);

		}

		sddmarea.getMap().put("month", month);
		List<StatisticalDimensionDataMonth> areaList;
		objName = "JcfxReportLswcFgsAction.table2.table1List";
		objKey = "year" + year + "fgs_id" + fgs_id + "month" + month
				+ "group by b.fgs_name";
		areaList = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (areaList == null) {
			areaList = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaForQd(sddmarea);
			for (int i = 0; i < areaList.size(); i++) {
				areaList.get(i)
						.getMap()
						.put("customer_sale_lj",
								areaListLj.get(i).getMap()
										.get("customer_sale_lj"));
				areaList.get(i)
						.getMap()
						.put("retail_money_lj",
								areaListLj.get(i).getMap()
										.get("retail_money_lj"));
				areaList.get(i)
						.getMap()
						.put("month_retail_money_task_lj",
								areaListLj.get(i).getMap()
										.get("month_retail_money_task_lj"));
				areaList.get(i)
						.getMap()
						.put("customer_rwwcbfb_lj",
								areaListLj.get(i).getMap()
										.get("customer_rwwcbfb_lj"));
			}

			cacheObjUtil.saveObj(areaList, objTime, objName, objKey);
		}
		request.setAttribute("areaList", areaList);

		StatisticalDimensionDataMonth sddmarea2 = new StatisticalDimensionDataMonth();
		sddmarea2.getMap().put("year", year);
		sddmarea2.getMap().put("fgs_id", fgs_id);
		sddmarea2.getMap().put("groupbyWhat", "b.jyb_name");

		List<StatisticalDimensionDataMonth> fgsListLj;

		objName = "JcfxReportLswcFgsAction.jyb.table1Listlj";
		objKey = "year" + year + "fgs_id" + fgs_id + "group by b.jyb_name";
		fgsListLj = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (fgsListLj == null) {
			fgsListLj = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaLj(sddmarea2);
			cacheObjUtil.saveObj(fgsListLj, objTime, objName, objKey);
		}

		List<StatisticalDimensionDataMonth> fgsListx;
		sddmarea2.getMap().put("month", month);
		objName = "JcfxReportLswcFgsAction.jyb.table2List";
		objKey = "year" + year + "fgs_id" + fgs_id + "month" + month
				+ "group by b.jyb_name";
		fgsListx = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		sddmarea2.getMap().put("month", month);
		if (fgsListx == null) {
			List<StatisticalDimensionDataMonth> fgsListQd = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getMoneysGroupByAreaForQd(sddmarea2);

			 fgsListx= new ArrayList<StatisticalDimensionDataMonth>();
			for (int i = 0; i < fgsListQd.size(); i++) {
				fgsListQd
						.get(i)
						.getMap()
						.put("customer_sale_lj",
								fgsListLj.get(i).getMap()
										.get("customer_sale_lj"));
				fgsListQd
						.get(i)
						.getMap()
						.put("retail_money_lj",
								fgsListLj.get(i).getMap()
										.get("retail_money_lj"));
				fgsListQd
						.get(i)
						.getMap()
						.put("month_retail_money_task_lj",
								fgsListLj.get(i).getMap()
										.get("month_retail_money_task_lj"));
				fgsListQd
						.get(i)
						.getMap()
						.put("customer_rwwcbfb_lj",
								fgsListLj.get(i).getMap()
										.get("customer_rwwcbfb_lj"));
				fgsListx.add(fgsListQd.get(i));
			}
			cacheObjUtil.saveObj(fgsListx, objTime, objName, objKey);
		}
		request.setAttribute("fgsList", fgsListx);
		return new ActionForward(
				"/../manager/leader/JcfxReportLswcFgs/table2.jsp");
	}

	public ActionForward option1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_id = (String) dynaBean.get("fgs_id");
		String fgs_name = "";
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
		if (!GenericValidator.isInt(fgs_id)) {
			fgs_id = "2";
			dynaBean.set("fgs_id", fgs_id);
		}
		StatisticalDimensionFgsLatlng fgs = new StatisticalDimensionFgsLatlng();
		fgs.setFgs_id(new Long(fgs_id));
		fgs = super.getFacade().getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlng(fgs);
		fgs_name = fgs.getFgs_name();

		StatisticalDimensionDataMonth sddm1 = new StatisticalDimensionDataMonth();
		sddm1.getMap().put("year", year);
		sddm1.getMap().put("fgs_id", fgs_id);
		sddm1.getMap().put("month", month);
		
		List<StatisticalDimensionDataMonth> optionList1 ;
		CacheObjUtil cacheObjUtil = new CacheObjUtil(super.getFacade());
		String objName = "JcfxReportLswcFgsAction.option1.table1Listlj";
		String objKey = "year" + year + "fgs_id" + fgs_id +"month"+ month + "group by b.jyb_name";
		optionList1 = (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (optionList1 == null) {
			optionList1= super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getRetailMoneyByYearForMonths(sddm1);
			
			cacheObjUtil.saveObj(optionList1, null, objName, objKey);
		}

		BigDecimal month_retail_money = new BigDecimal("0");
		BigDecimal month_retail_money_task = new BigDecimal("0");
		BigDecimal wcl = new BigDecimal("0");
		if (null != optionList1 && optionList1.size() > 0) {
			if (optionList1.get(0) != null) {
				month_retail_money = optionList1.get(0).getMonth_retail_money();
				month_retail_money_task = optionList1.get(0)
						.getMonth_retail_money_task();
				if (month_retail_money_task == null) {
					month_retail_money_task = new BigDecimal("0");
				}
				if (month_retail_money == null) {
					month_retail_money = new BigDecimal("0");
				}
				if (!String.valueOf(month_retail_money_task).equals("0")) {
					wcl = month_retail_money.multiply(new BigDecimal("100"))
							.divide(month_retail_money_task, 2);
				}
			}
		}
		EnhancedOption option1 = new EnhancedOption();
		option1 = this.getOption1(year, wcl, fgs_name);
		String optionStr1 = GsonUtil.format(option1);
		Map map = new HashMap();
		map.put("title", year + "年" + month + "月" + fgs_name + "分公司零售情况");
		map.put("option_a",
				month_retail_money.setScale(2, BigDecimal.ROUND_HALF_UP));
		map.put("option_b", month_retail_money_task);
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
		String fgs_id = (String) dynaBean.get("fgs_id");
		String fgs_name = "";
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
		if (!GenericValidator.isInt(fgs_id)) {
			fgs_id = "2";
			dynaBean.set("fgs_id", fgs_id);
		}

		StatisticalDimensionFgsLatlng fgs = new StatisticalDimensionFgsLatlng();
		fgs.setFgs_id(new Long(fgs_id));
		fgs = super.getFacade().getStatisticalDimensionFgsLatlngService()
				.getStatisticalDimensionFgsLatlng(fgs);
		fgs_name = fgs.getFgs_name();

		StatisticalDimensionDataMonth sddm1 = new StatisticalDimensionDataMonth();
		sddm1.getMap().put("year", year);
		sddm1.getMap().put("fgs_id", fgs_id);
		
		
		List<StatisticalDimensionDataMonth> optionList2 ;
		CacheObjUtil cacheObjUtil = new CacheObjUtil(super.getFacade());
		String objName = "JcfxReportLswcFgsAction.option2.table1Listlj";
		String objKey = "year" + year + "fgs_id" + fgs_id ;
		optionList2= (List<StatisticalDimensionDataMonth>) cacheObjUtil.getObj(
				null, objName, objKey);
		if (optionList2 == null) {
			 optionList2 = super.getFacade()
						.getStatisticalDimensionDataMonthService()
						.getRetailMoneyByYearForMonths(sddm1);
			 cacheObjUtil.saveObj(optionList2, null, objName, objKey);
		}

		BigDecimal[] month_retail_money2 = new BigDecimal[12];
		BigDecimal[] month_retail_money_task2 = new BigDecimal[12];
		BigDecimal[] pre_year_retail_money2 = new BigDecimal[12];
		Object[] year2 = new Object[12];
		Object[] month2 = new Object[12];
		String[] data2 = new String[12];
		for (int i = 0; i < 12; i++) {
			month_retail_money2[i] = new BigDecimal("0");
			month_retail_money_task2[i] = new BigDecimal("0");
			pre_year_retail_money2[i] = new BigDecimal("0");
			year2[i] = year;
			month2[i] = "" + (i + 1);
			data2[i] = year2[i].toString() + "年" + month2[i].toString() + "月";
		}

		for (int i = 0; i < optionList2.size(); i++) {
			month_retail_money2[i] = optionList2.get(i).getMonth_retail_money();
			month_retail_money_task2[i] = optionList2.get(i)
					.getMonth_retail_money_task() == null ? new BigDecimal("0")
					: optionList2.get(i).getMonth_retail_money_task();
			pre_year_retail_money2[i] = optionList2.get(i)
					.getPre_year_retail_money() == null ? new BigDecimal("0")
					: optionList2.get(i).getPre_year_retail_money();
			year2[i] = optionList2.get(i).getMap().get("year");
			month2[i] = optionList2.get(i).getMap().get("month");
			data2[i] = year2[i].toString() + "年" + month2[i].toString() + "月";
		}
		EnhancedOption option2 = new EnhancedOption();
		option2.title("", "金额:万元");
		option2.tooltip().trigger(Trigger.axis);
		option2.legend("目标零售额", "实际零售额", "同期零售额");
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

		Line l1 = new Line("目标零售额");
		l1.smooth(true).itemStyle().normal();
		l1.data(month_retail_money_task2);
		Line l2 = new Line("实际零售额");
		l2.smooth(true).itemStyle().normal();
		l2.data(month_retail_money2);
		Line l3 = new Line("同期零售额");
		l3.smooth(true).itemStyle().normal();
		l3.data(pre_year_retail_money2);
		option2.series(l1, l2, l3);
		String optionStr = GsonUtil.format(option2);
		Map map = new HashMap();
		map.put("title", year + "年" + fgs_name + "分公司零售情况");
		map.put("option", optionStr);
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}

	private EnhancedOption getOption1(String year, BigDecimal f, String fgs_name)
			throws Exception {
		EnhancedOption option = new EnhancedOption();
		option.tooltip().formatter("{a} <br/>{b} : {c}%");
		option.toolbox().show(true)
				.feature(Tool.mark, Tool.restore, Tool.saveAsImage);
		option.series(new Gauge(year + fgs_name + "零售情况").startAngle(180)
				.endAngle(0).detail(new Detail().formatter("{value}%"))
				.data(new Data("完成率", f)));
		return option;
	}

}
