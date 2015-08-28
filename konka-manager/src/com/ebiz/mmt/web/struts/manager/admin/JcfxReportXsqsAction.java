package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JcfxReportXsqs;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Polar;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.Data;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.data.ScatterData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.samples.bar.BarTest1;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Gauge;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Radar;
import com.github.abel533.echarts.series.Scatter;
import com.github.abel533.echarts.series.gauge.Detail;
import com.github.abel533.echarts.series.gauge.Pointer;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.util.EnhancedOption;

/**
 * @author Wangkl
 * @version 2014-12-08
 * @desc  销售趋势报表
 */
public class JcfxReportXsqsAction extends BaseAction {
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.init(mapping, form, request, response);
	}
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		
		DynaBean dynaBean = (DynaBean) form; 
		String mod_id = (String) dynaBean.get("mod_id");
		String year=(String) dynaBean.get("year");
		String month=(String) dynaBean.get("month");
		String dept_id=(String) dynaBean.get("dept_id");
        String  year_last="";
       
        DateFormat df_year = new SimpleDateFormat("yyyy");
        DateFormat df_month = new SimpleDateFormat("MM");
		Calendar now = Calendar.getInstance();
		
		if (null == year) {
			year = df_year.format(now.getTime());
			now.add(now.YEAR, -1);
			year_last = df_year.format(now.getTime());
		} else {
			year_last = "" + (Integer.parseInt(year) - 1);
		}
		
		if(null==month){
			now.add(now.YEAR, +1);
		//	now.add(now.MONTH, -1);
			month = df_month.format(now.getTime());
		}
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		//当前用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				request.setAttribute("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				request.setAttribute("dept_id", dept_fgs.getDept_id());
			}
		}
		return mapping.findForward("view");
	}
	
	
	public ActionForward getOPtionWeek(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		DynaBean dynaBean = (DynaBean) form; 
	String year=(String) dynaBean.get("year");
	String month=(String) dynaBean.get("month");
	String dept_id=(String) dynaBean.get("dept_id");
    String  year_last="";
    
    DateFormat df_year = new SimpleDateFormat("yyyy");
    DateFormat df_month = new SimpleDateFormat("MM");
	Calendar now = Calendar.getInstance();
	
	if (null == year) {
		year = df_year.format(now.getTime());
		now.add(now.YEAR, -1);
		year_last = df_year.format(now.getTime());
	} else {
		year_last = "" + (Integer.parseInt(year) - 1);
	}
	
	if(null==month){
		now.add(now.YEAR, +1);
		month = df_month.format(now.getTime());
	}

		//第一步：去数据库查到数据
		
		JcfxReportXsqs jcfxReportXsqs=new JcfxReportXsqs();
		String [] v_years={year,year_last};
		//jcfxReportXsqs.setV_year(year);
		jcfxReportXsqs.getMap().put("v_years", v_years);
		
		if(null!=dept_id){
			jcfxReportXsqs.getMap().put("dept_id", dept_id);
		}
		
		List<JcfxReportXsqs> jcfxReportXsqsList=super.getFacade().getJcfxReportXsqsService().getJcfxReportXsqsForWeekList(jcfxReportXsqs);
		
		
		
		EnhancedOption option = new EnhancedOption();
		option.title("", "金额:万元");
		option.tooltip().trigger(Trigger.axis);
		option.legend("本期零售额", "本期结算额","上期零售额","上期结算额");
		option.toolbox().show(true).feature(Tool.mark,
		             Tool.dataView,
		             new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
		             Tool.restore,
		             Tool.saveAsImage);
		    option.calculable(true);
		
		
		//零售额
		List<Object> list1=new ArrayList<Object>();
		//结算额
		List<Object> list2=new ArrayList<Object>();
		//上期零售额
		List<Object> list3=new ArrayList<Object>();
		//上期结算额
		List<Object> list4=new ArrayList<Object>();
		//下面节点的
		List<Object> list5=new ArrayList<Object>();
		
		if (null != jcfxReportXsqsList && jcfxReportXsqsList.size() > 0) {
			for(int i=0 ; i<jcfxReportXsqsList.size();i++){
				JcfxReportXsqs	entity=jcfxReportXsqsList.get(i);
				if(year_last.equals(entity.getV_year())){
					list5.add("第"+entity.getV_week()+"周");
					list3.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list4.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}else{
					list1.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list2.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}
			}
		}else{
			for(int i=0;i<30;i++){
				list1.add(0);
				list2.add(0);
				list3.add(0);
				list4.add(0);
				list5.add("第"+i+"周");
			}
		
		}
		
		option.xAxis(new CategoryAxis().boundaryGap(false).data(list5));
		option.yAxis(new ValueAxis());
		
		
		    Line l1 = new Line("本期零售额");
		    l1.smooth(true).itemStyle().normal();
		    l1.data(list1);
		
		    Line l2 = new Line("本期结算额");
		    l2.smooth(true).itemStyle().normal();
		    l2.data(list2);
		   
		    Line l3 = new Line("上期零售额");
		    l3.smooth(true).itemStyle().normal();
		    l3.data(list3);
		    
		    Line l4 = new Line("上期结算额");
		    l4.smooth(true).itemStyle().normal();
		    l4.data(list4);
		
		option.series(l1, l2, l3, l4);
		
		String optionStr = GsonUtil.format(option);
		ArrayList<HashMap> list=new ArrayList<HashMap>();
		HashMap map=new HashMap<String, Object>();
		map.put("main_week", optionStr);
		map.put("title_week", "年度筛选条件-周销售趋势图");
		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(map));
	 return null;
	}
	
	
	public ActionForward getOPtionDay(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		DynaBean dynaBean = (DynaBean) form; 
		String year=(String) dynaBean.get("year");
		String month=(String) dynaBean.get("month");
		String dept_id=(String) dynaBean.get("dept_id");
		String  year_last="";
		
		DateFormat df_year = new SimpleDateFormat("yyyy");
		DateFormat df_month = new SimpleDateFormat("MM");
		Calendar now = Calendar.getInstance();
		
		if (null == year) {
			year = df_year.format(now.getTime());
			now.add(now.YEAR, -1);
			year_last = df_year.format(now.getTime());
		} else {
			year_last = "" + (Integer.parseInt(year) - 1);
		}
		
		if(null==month){
			now.add(now.YEAR, +1);
			month = df_month.format(now.getTime());
		}
		
		//第一步：去数据库查到数据
		
		JcfxReportXsqs jcfxReportXsqs=new JcfxReportXsqs();
		String [] v_years={year,year_last};
		//jcfxReportXsqs.setV_year(year);
		jcfxReportXsqs.getMap().put("v_years", v_years);
		
		if(null!=dept_id){
			jcfxReportXsqs.getMap().put("dept_id", dept_id);
		}
		
		if(null!=month){
			jcfxReportXsqs.setV_month(month);
		}
		List<JcfxReportXsqs> jcfxReportXsqsList=super.getFacade().getJcfxReportXsqsService().getJcfxReportXsqsForDayList(jcfxReportXsqs);
		
		
		
		EnhancedOption option = new EnhancedOption();
		option.title("", "金额:万元");
		option.tooltip().trigger(Trigger.axis);
		option.legend("本期零售额", "本期结算额","上期零售额","上期结算额");
		option.toolbox().show(true).feature(Tool.mark,
				Tool.dataView,
				new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
				Tool.restore,
				Tool.saveAsImage);
		option.calculable(true);
	
		
		//零售额
		List<Object> list1=new ArrayList<Object>();
		//结算额
		List<Object> list2=new ArrayList<Object>();
		//上期零售额
		List<Object> list3=new ArrayList<Object>();
		//上期结算额
		List<Object> list4=new ArrayList<Object>();
		
		//节点标注
		List<Object> list5=new ArrayList<Object>();
		
		if (null != jcfxReportXsqsList && jcfxReportXsqsList.size() > 0) {
			for(int i=0 ; i<jcfxReportXsqsList.size();i++){
				JcfxReportXsqs	entity=jcfxReportXsqsList.get(i);
				if(year_last.equals(entity.getV_year())){
				
					list3.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list4.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}else{
					list5.add((entity.getV_day()).substring(0, 10)+"号");
					list1.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list2.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}
			}
		}else{
			for(int i=0;i<30;i++){
				list1.add(0);
				list2.add(0);
				list3.add(0);
				list4.add(0);
				list5.add(i+"号");
			}
			
		}
		
		option.xAxis(new CategoryAxis().boundaryGap(false).data(list5));
		option.yAxis(new ValueAxis());
		
		Line l1 = new Line("本期零售额");
		l1.smooth(true).itemStyle().normal();
		l1.data(list1);
		
		Line l2 = new Line("本期结算额");
		l2.smooth(true).itemStyle().normal();
		l2.data(list2);
		
		Line l3 = new Line("上期零售额");
		l3.smooth(true).itemStyle().normal();
		l3.data(list3);
		
		Line l4 = new Line("上期结算额");
		l4.smooth(true).itemStyle().normal();
		l4.data(list4);
		
		option.series(l1, l2, l3, l4);
		
		String optionStr = GsonUtil.format(option);
		ArrayList<HashMap> list=new ArrayList<HashMap>();
		HashMap map=new HashMap<String, Object>();
		map.put("main_day", optionStr);
		map.put("title_day", "年月筛选条件-日销售趋势图");
		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(map));
		return null;
	}
	public ActionForward getOPtionMonth(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		DynaBean dynaBean = (DynaBean) form; 
		String year=(String) dynaBean.get("year");
		String month=(String) dynaBean.get("month");
		String dept_id=(String) dynaBean.get("dept_id");
		String  year_last="";
		
		DateFormat df_year = new SimpleDateFormat("yyyy");
		DateFormat df_month = new SimpleDateFormat("MM");
		Calendar now = Calendar.getInstance();
		
		if (null == year) {
			year = df_year.format(now.getTime());
			now.add(now.YEAR, -1);
			year_last = df_year.format(now.getTime());
		} else {
			year_last = "" + (Integer.parseInt(year) - 1);
		}
		
		if(null==month){
			now.add(now.YEAR, +1);
			month = df_month.format(now.getTime());
		}
		
		//第一步：去数据库查到数据
		
		JcfxReportXsqs jcfxReportXsqs=new JcfxReportXsqs();
		String [] v_years={year,year_last};
		//jcfxReportXsqs.setV_year(year);
		jcfxReportXsqs.getMap().put("v_years", v_years);
		
		if(null!=dept_id){
			jcfxReportXsqs.getMap().put("dept_id", dept_id);
		}
		
		List<JcfxReportXsqs> jcfxReportXsqsList=super.getFacade().getJcfxReportXsqsService().getJcfxReportXsqsForMonthList(jcfxReportXsqs);
		
		
		
		EnhancedOption option = new EnhancedOption();
		option.title("", "金额:万元");
		option.tooltip().trigger(Trigger.axis);
		option.legend("本期零售额", "本期结算额","上期零售额","上期结算额");
		option.toolbox().show(true).feature(Tool.mark,
				Tool.dataView,
				new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
				Tool.restore,
				Tool.saveAsImage);
		option.calculable(true);
		option.xAxis(new CategoryAxis().boundaryGap(false).data(year + "年01月",
				year + "年02月", year + "年03月", year + "年04月", year + "年05月",
				year + "年06月", year + "年07月", year + "年08月", year + "年09月",
				year + "年10月", year + "年11月", year + "年12月"));
		option.yAxis(new ValueAxis());
		
		//零售额
		List<Object> list1=new ArrayList<Object>();
		//结算额
		List<Object> list2=new ArrayList<Object>();
		//上期零售额
		List<Object> list3=new ArrayList<Object>();
		//上期结算额
		List<Object> list4=new ArrayList<Object>();
		
		if (null != jcfxReportXsqsList && jcfxReportXsqsList.size() > 0) {
			for(int i=0 ; i<jcfxReportXsqsList.size();i++){
				JcfxReportXsqs	entity=jcfxReportXsqsList.get(i);
				if(year_last.equals(entity.getV_year())){
					list3.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list4.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}else{
					list1.add(("".equals(entity.getV_sale_money()) || null ==entity.getV_sale_money())?"0":entity.getV_sale_money());
					list2.add(("".equals(entity.getV_settle_money()) || null ==entity.getV_settle_money())?"0":entity.getV_settle_money());
				}
			}
		}else{
			for(int i=0;i<12;i++){
				list1.add(0);
				list2.add(0);
				list3.add(0);
				list4.add(0);
			}
			
		}
		
		
		Line l1 = new Line("本期零售额");
		l1.smooth(true).itemStyle().normal();
		l1.data(list1);
		
		Line l2 = new Line("本期结算额");
		l2.smooth(true).itemStyle().normal();
		l2.data(list2);
		
		Line l3 = new Line("上期零售额");
		l3.smooth(true).itemStyle().normal();
		l3.data(list3);
		
		Line l4 = new Line("上期结算额");
		l4.smooth(true).itemStyle().normal();
		l4.data(list4);
		
		option.series(l1, l2, l3, l4);
		
		String optionStr = GsonUtil.format(option);
		ArrayList<HashMap> list=new ArrayList<HashMap>();
		HashMap map=new HashMap<String, Object>();
		map.put("main_month", optionStr);
		map.put("title_month", "年度筛选条件-月销售趋势图");
		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(map));
		return null;
	}
	
}
