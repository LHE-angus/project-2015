package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.MdasShopBrandsales;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Cheng,Bing
 * @version 2011-10-08
 */
public class EntpShopSellAnalysisAction extends BaseAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String shop_id = (String) dynaBean.get("shop_id");
		String page_id = (String) dynaBean.get("page_id");

		if(page_id != null){
			request.getSession().setAttribute(page_id, dynaBean);
		}
		
		if (!GenericValidator.isLong(shop_id)) {
			String msg = super.getMessage(request, "client.login.entpshop.info.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		MmtEntpShop entpShop = new MmtEntpShop();
		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = super.getFacade().getMmtEntpShopService().getMmtEntpShop(entpShop);	
		
		// 处理此网点的所处地区
		BaseProvinceList baseProvice = new BaseProvinceList();
		baseProvice.setP_index(entpShop.getP_index().longValue());
		List<BaseProvinceList> baseProvinceListList = getFacade().getBaseProvinceListService().getBaseProvinceListParentList(baseProvice);
		
		//把买买提中的表名查出来，优化网点经营情况的查询
		MdasShopBrandsales _msb = new MdasShopBrandsales();
		_msb.getMap().put("shop_name", "MDAS_SHOP_BRANDSALES_");
		List<String> shop_names = super.getFacade().getMdasShopBrandsalesService().selectMdasShopNamesForQuery(_msb);
			
		// 处理此网点的销售品类,品牌
		HttpSession session = request.getSession();
		List<MdasShopBrandsales> msbList = (List<MdasShopBrandsales>) session.getAttribute(shop_id + "msbList");
		if(msbList == null || (msbList != null && msbList.size() == 0)){
			MdasShopBrandsales msb = new MdasShopBrandsales();
			msb.setShop_id(Long.valueOf(shop_id));
			msb.getMap().put("shop_names", shop_names);
			msbList = super.getFacade().getMdasShopBrandsalesService().getMdasShopBrandsalesForPdTypeAndBrandList(msb);
			
			session.setAttribute(shop_id + "msbList", msbList);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		for (MdasShopBrandsales mdasShopBrandsales : msbList) {
			if(mdasShopBrandsales.getPd_type()==1 || mdasShopBrandsales.getPd_type()== 2 || mdasShopBrandsales.getPd_type()==4){//限制，只出先彩电，冰箱，洗衣机
				if(sb.length() == 0){
					sb.append(mdasShopBrandsales.getPd_type().toString());
					if(mdasShopBrandsales.getBrand_id()!=null){
						sb.append(",").append(mdasShopBrandsales.getBrand_id().toString());
						sb.append(",").append(mdasShopBrandsales.getBrand_name());
					}else{
						sb.append(",").append("");
						sb.append(",").append("");
					}
				}else{
					sb.append(":").append(mdasShopBrandsales.getPd_type().toString());
					if(mdasShopBrandsales.getBrand_id()!=null){
						sb.append(",").append(mdasShopBrandsales.getBrand_id().toString());
						sb.append(",").append(mdasShopBrandsales.getBrand_name());
					}else{
						sb.append(",").append("");
						sb.append(",").append("");
					}
				}
				map.put(mdasShopBrandsales.getPd_type().toString(), mdasShopBrandsales.getPd_name());
			}
		}
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // 日历对象
		calendar.setTime(date);

		String nowYear = DateFormatUtils.format(calendar, "yyyy");
		Map<Integer,Integer> yearMap = new HashMap<Integer, Integer>();
		for(int i=2010 ;i<=Integer.parseInt(nowYear);i++){
			yearMap.put(i, i);
		}
		
		session.setAttribute("basePdTypeMap", map);
		session.setAttribute("yearMap", yearMap);
		session.setAttribute("mdasShopBrandsalesInBasePdTypeWithBrandList", msbList);
		session.setAttribute("msbInbptWithBrandListString", sb.toString());
		session.setAttribute("entpShop", entpShop);
		session.setAttribute("baseProvinceListList", baseProvinceListList);

		return mapping.findForward("list");
	}

	/**
	 * @des 1当前月与上个月该网点品类销售额环比图
	 */
	public Map<String,Object> chart1(EntpShop shop, String year, String pd_type, String brand_id) throws Exception {

		StringBuffer caption = new StringBuffer();
		caption.append(year).append("年 ");
		caption.append(shop.getShop_name());
		caption.append(" 当前月与上个月该网点品类销售额环比图");

		// 初始化 X 轴 月份
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		for (int ii = 1; ii <= 12; ii++) {
			BaseChart baseChart = new BaseChart();
			baseChart.setCategory_label(String.valueOf(ii) + "月");

			baseChartList.add(baseChart);
		}

		// 初始化，取指定年12个月的销售数据
		MdasShopBrandsales mdasShopBrandsales = new MdasShopBrandsales();
		mdasShopBrandsales.setShop_id(shop.getShop_id());
		Map<String, List<MdasShopBrandsales>> map = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, year);

		// 得到 BasePdType 列表条件是： is_model =1 and del_mark = 0
		BasePdType bpt = new BasePdType();
		bpt.setIs_model(new Short("1"));
		bpt.setDel_mark(new Short("0"));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(bpt);

		// 初始化曲线
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<MdasShopBrandsales> msbList;

		for (BasePdType entity : basePdTypeList) {
			List<BaseChart> baseChartListForMSLine = new ArrayList<BaseChart>();
			MsLineChart msLineChart = new MsLineChart();

			msLineChart.setSeries_name(entity.getPd_name());

			// 循环初始化1-12月的数据
			for (int i = 1; i <= 12; i++) {
				BaseChart baseChart = new BaseChart();

				// 得到某年某月的这个企业销售情况
				msbList = map.get("msbList" + this.getYYYYMM(year, i));
				
				if(msbList!=null){
					// 遍历设置指定大类的当月销售额
					Double value = 0.00;
					for (MdasShopBrandsales m : msbList) {
						if (m.getPd_type().toString().equals(entity.getPd_type().toString())) {
							value += Double.valueOf(m.getXe().toString());
						}
					}
					
					// 处理成万元
					value = value / 10000;
					
					baseChart.setValue(value.toString());
					baseChartListForMSLine.add(baseChart);
				}
			}

			msLineChart.setBaseChartList(baseChartListForMSLine);
			msLineChartList.add(msLineChart);
		}

		// 返回Map
		Map<String,Object> value_map = new HashMap<String,Object>();
		value_map.put("caption", caption.toString());
		value_map.put("unit", "单位：万元");
		value_map.put("baseChartList", baseChartList);
		value_map.put("msLineChartList", msLineChartList);	

		return value_map;
	}

	/**
	 * @des 2当前与去年同期该网点品类销售额同比图
	 */

	public Map<String,Object> chart2(EntpShop shop, String year, String pd_type, String brand_id) throws Exception {

		// 得到 BasePdType 列表条件是： is_model =1 and del_mark = 0
		BasePdType bpt = new BasePdType();
		bpt.setIs_model(new Short("1"));
		bpt.setDel_mark(new Short("0"));
		List<BasePdType> basePdTypeList = super.getFacade().getBasePdTypeService().getBasePdTypeList(bpt);
		
		// 找出需要的是哪个大类
		BasePdType basePdType = new BasePdType();
		for (BasePdType t : basePdTypeList) {
			if (("" + t.getPd_type()).equals(pd_type)) {
				basePdType = t;
				break;
			}
		}

		StringBuffer caption = new StringBuffer();
		caption.append(shop.getShop_name());
		caption.append(" ").append(basePdType.getPd_name());
		caption.append(" 当前与去年同期该网点品类销售额同比图");

		// 初始化 X 轴 月份
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		for (int ii = 1; ii <= 12; ii++) {
			BaseChart baseChart = new BaseChart();
			baseChart.setCategory_label(String.valueOf(ii) + "月");

			baseChartList.add(baseChart);
		}

		// 初始化，取指定年12个月的销售数据
		MdasShopBrandsales mdasShopBrandsales = new MdasShopBrandsales();
		mdasShopBrandsales.setShop_id(shop.getShop_id());
		mdasShopBrandsales.setPd_type(basePdType.getPd_type());

		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // 日历对象
		calendar.setTime(date);

		String nowYear = DateFormatUtils.format(calendar, "yyyy");
		calendar.add(Calendar.YEAR, -1); // 年减一
		String lastYear = DateFormatUtils.format(calendar, "yyyy");

		Map<String, List<MdasShopBrandsales>> nowYearMap = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, nowYear);
		Map<String, List<MdasShopBrandsales>> lastYearMap = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, lastYear);

		// 初始化曲线
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<MdasShopBrandsales> msbList;

		for (int iii = 0; iii < 2; iii++) {
			List<BaseChart> baseChartListForMSLine = new ArrayList<BaseChart>();
			MsLineChart msLineChart = new MsLineChart();

			if (iii == 0) {
				msLineChart.setSeries_name(nowYear + "年");
			} else {
				msLineChart.setSeries_name(lastYear + "年");
			}

			// 循环初始化1-12月的数据
			for (int i = 1; i <= 12; i++) {
				BaseChart baseChart = new BaseChart();

				// 得到某年某月的这个企业销售情况
				if (iii == 0) {
					msbList = nowYearMap.get("msbList" + this.getYYYYMM(nowYear, i));
				} else {
					msbList = lastYearMap.get("msbList" + this.getYYYYMM(lastYear, i));
				}

				if(msbList!=null){
					// 遍历设置指定大类的当月销售额
					Double value = 0.00;
					for (MdasShopBrandsales m : msbList) {
						if (m.getPd_type().toString().equals(pd_type)) {
							value += Double.valueOf(m.getXe().toString());
						}
					}
					
					// 处理成万元
					value = value / 10000;
					
					baseChart.setValue(value.toString());
					baseChartListForMSLine.add(baseChart);
				}
			}

			msLineChart.setBaseChartList(baseChartListForMSLine);
			msLineChartList.add(msLineChart);
		}

		// 返回Map
		Map<String,Object> value_map = new HashMap<String,Object>();
		value_map.put("caption", caption.toString());
		value_map.put("unit", "单位：万元");
		value_map.put("baseChartList", baseChartList);
		value_map.put("msLineChartList", msLineChartList);	

		return value_map;
	}

	/**
	 * @des 3当前月与上个月该网点品类品牌销售量环比图
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> chart3(EntpShop shop, String year, String pd_type, String brand_id, HttpServletRequest request) throws Exception {

		StringBuffer caption = new StringBuffer();
		caption.append(year).append("年 ");
		caption.append(shop.getShop_name());
		caption.append(" 当前月与上个月该网点品类品牌销售量环比图");

		// 初始化 X 轴 月份
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		for (int ii = 1; ii <= 12; ii++) {
			BaseChart baseChart = new BaseChart();
			baseChart.setCategory_label(String.valueOf(ii) + "月");
			baseChartList.add(baseChart);
		}

		// 初始化，取指定年12个月的销售数据
		MdasShopBrandsales mdasShopBrandsales = new MdasShopBrandsales();
		mdasShopBrandsales.setShop_id(shop.getShop_id());
		mdasShopBrandsales.setPd_type(Long.valueOf(pd_type));
		Map<String, List<MdasShopBrandsales>> map = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, year);

		// 查找此网点的销售的所有的品牌
		List<MdasShopBrandsales> mdasShopBrandsalesInBasePdTypeWithBrandList = (List<MdasShopBrandsales>) request.getSession().getAttribute("mdasShopBrandsalesInBasePdTypeWithBrandList");
		List<MdasShopBrandsales> brandList = new ArrayList<MdasShopBrandsales>();
		for (MdasShopBrandsales tt : mdasShopBrandsalesInBasePdTypeWithBrandList) {
			if(tt.getPd_type().toString().equals(pd_type)){
				MdasShopBrandsales t = new MdasShopBrandsales();
				t.setBrand_id(tt.getBrand_id());
				t.setBrand_name(tt.getBrand_name());
				brandList.add(t);
			}
		}
		
		// 初始化曲线
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<MdasShopBrandsales> msbList;

		for (MdasShopBrandsales entity : brandList) {
			List<BaseChart> baseChartListForMSLine = new ArrayList<BaseChart>();
			MsLineChart msLineChart = new MsLineChart();

			msLineChart.setSeries_name(entity.getBrand_name());

			// 循环初始化1-12月的数据
			for (int i = 1; i <= 12; i++) {
				BaseChart baseChart = new BaseChart();

				// 得到某年某月的这个企业销售情况
				msbList = map.get("msbList" + this.getYYYYMM(year, i));
				
				if(msbList!=null){
					// 遍历设置指定大类的当月销售量
					Double value = 0.00;
					for (MdasShopBrandsales m : msbList) {
						if (m.getBrand_id().toString().equals(entity.getBrand_id().toString())) {
							value += Double.valueOf(m.getXl().toString());
						}
					}
					
					baseChart.setValue(value.toString());
					baseChartListForMSLine.add(baseChart);
				}
			}

			msLineChart.setBaseChartList(baseChartListForMSLine);
			msLineChartList.add(msLineChart);
		}

		// 返回Map
		Map<String,Object> value_map = new HashMap<String,Object>();
		value_map.put("caption", caption.toString());
		value_map.put("unit", "单位：台");
		value_map.put("baseChartList", baseChartList);
		value_map.put("msLineChartList", msLineChartList);	

		return value_map;
	}

	/**
	 * @des 4当前与去年同期该网点品类品牌销售量同比图
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> chart4(EntpShop shop, String year, String pd_type, String brand_id, HttpServletRequest request) throws Exception {

		// 查找此网点的销售的所有的品牌
		List<MdasShopBrandsales> mdasShopBrandsalesInBasePdTypeWithBrandList = (List<MdasShopBrandsales>) request.getSession().getAttribute("mdasShopBrandsalesInBasePdTypeWithBrandList");
		
		// 找出需要的是哪个大类
		MdasShopBrandsales msb = new MdasShopBrandsales();
		for (MdasShopBrandsales t : mdasShopBrandsalesInBasePdTypeWithBrandList) {
			if (t.getBrand_id().toString().equals(brand_id)) {
				msb = t;
				break;
			}
		}

		StringBuffer caption = new StringBuffer();
		caption.append(shop.getShop_name());
		caption.append(" ").append(msb.getBrand_name());
		caption.append(" 当前与去年同期该网点品类销售额同比图");

		// 初始化 X 轴 月份
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		for (int ii = 1; ii <= 12; ii++) {
			BaseChart baseChart = new BaseChart();
			baseChart.setCategory_label(String.valueOf(ii) + "月");

			baseChartList.add(baseChart);
		}

		// 初始化，取指定年12个月的销售数据
		MdasShopBrandsales mdasShopBrandsales = new MdasShopBrandsales();
		mdasShopBrandsales.setShop_id(shop.getShop_id());
		mdasShopBrandsales.setPd_type(Long.valueOf(pd_type));
		mdasShopBrandsales.setBrand_id(msb.getBrand_id());

		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // 日历对象
		calendar.setTime(date);

		String nowYear = DateFormatUtils.format(calendar, "yyyy");
		calendar.add(Calendar.YEAR, -1); // 年减一
		String lastYear = DateFormatUtils.format(calendar, "yyyy");

		Map<String, List<MdasShopBrandsales>> nowYearMap = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, nowYear);
		Map<String, List<MdasShopBrandsales>> lastYearMap = this.getWithShopIdOneYearAllMonthSellData(mdasShopBrandsales, lastYear);

		// 初始化曲线
		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();
		List<MdasShopBrandsales> msbList;

		for (int iii = 0; iii < 2; iii++) {
			List<BaseChart> baseChartListForMSLine = new ArrayList<BaseChart>();
			MsLineChart msLineChart = new MsLineChart();

			if (iii == 0) {
				msLineChart.setSeries_name(nowYear + "年");
			} else {
				msLineChart.setSeries_name(lastYear + "年");
			}

			// 循环初始化1-12月的数据
			for (int i = 1; i <= 12; i++) {
				BaseChart baseChart = new BaseChart();

				// 得到某年某月的这个企业销售情况
				if (iii == 0) {
					msbList = nowYearMap.get("msbList" + this.getYYYYMM(nowYear, i));
				} else {
					msbList = lastYearMap.get("msbList" + this.getYYYYMM(lastYear, i));
				}

				if(msbList!=null){
					// 遍历设置指定大类的当月销售额
					Double value = 0.00;
					for (MdasShopBrandsales m : msbList) {
						if (m.getBrand_id().toString().equals(brand_id)) {
							value += Double.valueOf(m.getXl().toString());
						}
					}
					
					baseChart.setValue(value.toString());
					baseChartListForMSLine.add(baseChart);
				}
			}

			msLineChart.setBaseChartList(baseChartListForMSLine);
			msLineChartList.add(msLineChart);
		}

		// 返回Map
		Map<String,Object> value_map = new HashMap<String,Object>();
		value_map.put("caption", caption.toString());
		value_map.put("unit", "单位：台");
		value_map.put("baseChartList", baseChartList);
		value_map.put("msLineChartList", msLineChartList);	

		return value_map;
	}

	private String getYYYYMM(String year, int mm) {
		if (mm < 10) {
			return year + "0" + mm;
		}
		return year + mm;
	}
	
	/**
	 * @des 根据shop_id和year 查询一年的12个月销售情况
	 */
	private Map<String, List<MdasShopBrandsales>> getWithShopIdOneYearAllMonthSellData(
			MdasShopBrandsales mdasShopBrandsales, String year) throws Exception {
		Map<String, List<MdasShopBrandsales>> map = new HashMap<String, List<MdasShopBrandsales>>();
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance(); // 日历对象
		calendar.setTime(date);
		String nowYear = DateFormatUtils.format(calendar, "yyyy");

		Calendar calendarLast = Calendar.getInstance(); // 日历对象
		calendarLast.setTime(date);
		calendarLast.add(Calendar.MONTH, -1);
		String Month =DateFormatUtils.format(calendarLast, "MM");
		
		List<MdasShopBrandsales> msbList;
		int j=12;
		if(nowYear.equals(year)){
			j=Integer.parseInt(Month);
		}
		for (int i = 1; i <= j; i++) {
				mdasShopBrandsales.getMap().put("year_month", this.getYYYYMM(year, i));
				msbList = super.getFacade().getMdasShopBrandsalesService().getMdasShopBrandsalesList(mdasShopBrandsales);
				map.put("msbList" + this.getYYYYMM(year, i), msbList);
		}

		return map;
	}
	
	public ActionForward chart_sell(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String chart_type = (String) dynaBean.get("chart_type");		
		String shop_id = (String) dynaBean.get("shop_id");	
		String year = (String) dynaBean.get("year_"+chart_type);
		String pd_type = (String) dynaBean.get("pd_type_"+chart_type);
		String brand_id = (String) dynaBean.get("brand_id_"+chart_type);	
	    if (!GenericValidator.isLong(shop_id)) {
		     return null;
	    }
	    if(!GenericValidator.isLong(year) && "1".equals(chart_type)) {
	         return null;
       }else if (!GenericValidator.isLong(pd_type) && "2".equals(chart_type)) {
	         return null;
       }else if ((!GenericValidator.isLong(year) || !GenericValidator.isLong(pd_type)) && "3".equals(chart_type)) {
	         return null;
       }else if ((!GenericValidator.isLong(pd_type) || !GenericValidator.isLong(brand_id)) && "4".equals(chart_type)) {
	         return null;
       }

		// 网点信息
		EntpShop es = new EntpShop();
		es.getRow().setCount(2);
		es.setShop_id(Long.valueOf(shop_id));
		List<EntpShop> esList = super.getFacade().getEntpShopService().getEntpShopList(es);
		if (esList == null || esList.size() != 1) {
			return null;
		}

		Map<String,Object>  map = null;
		switch(Integer.parseInt(chart_type)){
		case 1: map = chart1(esList.get(0), year, pd_type, brand_id);
		        break;
		case 2: map = chart2(esList.get(0), year, pd_type, brand_id);
                break;
		case 3: map = chart3(esList.get(0), year, pd_type, brand_id, request);
                break;
		case 4: map = chart4(esList.get(0), year, pd_type, brand_id, request);
                break;
         default:
        	    break;
		}
	
		openChart(map,response);
		return null;
	}
	
	public void openChart(Map<String, Object> map, HttpServletResponse response) throws Exception  {
		// 图标数据初始化
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("caption", map.get("caption"));
		model.put("unit", map.get("unit"));
		model.put("baseFont", "宋体");

		model.put("baseChartList", map.get("baseChartList"));
		model.put("msLineChartList", map.get("msLineChartList"));
		String chartStr = getFacade().getTemplateService().getContent("chart/MSLine.ftl", model);
		//// //System.out.println("resultXML:="+chartStr);
		//renderXmlWithEncoding(response, chartStr, "GBK");
		OutputStream out = response.getOutputStream();
		out.write(chartStr.getBytes("UTF-8"));
		out.flush();
	}

    /////////////////以下网点筛选柱状图/////////////////////////////////
	/*
	 * 销售额柱状图
	 * 
	 */
	public ActionForward column3D(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String shop_ids = (String)dynaBean.get("shop_id_list");
		if(shop_ids == null){
			return null;
		}
		String ids[] = shop_ids.split(",");
	    String xmlStr = chart(ids);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;
	}
	
	public String chart(String shop_ids[])throws Exception {

		StringBuffer resultXML = new StringBuffer();
		resultXML.append("<?xml version='1.0' encoding='UTF-8'?><chart caption='");
		String title = "网点销售额柱状图"; // 标题
		resultXML.append(title); // 标题

		resultXML.append("'").append("subcaption='");
		resultXML.append("单位：万元"); // 单位
		resultXML.append("'");
		resultXML.append("decimals='2' numberPrefix='' baseFontColor='666666' showValues='0' decimals='0' formatNumberScale='0' baseFont='宋体' BaseFontSize ='12'>");
	
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i =0;i< shop_ids.length;i++){
			// 初始化，取指定年12个月的销售数据
			MdasShopBrandsales mdasShopBrandsales = new MdasShopBrandsales();
			List<MdasShopBrandsales> msbList;
			String yearMonth = "201107";
			mdasShopBrandsales.setShop_id(Long.valueOf(shop_ids[i]));
		    mdasShopBrandsales.getMap().put("year_month", yearMonth);
			msbList = super.getFacade().getMdasShopBrandsalesService().getMdasShopBrandsalesList(mdasShopBrandsales);
			
			if(msbList!=null){
				// 遍历设置指定大类的当月销售额
				Double value = 0.00;
				for (MdasShopBrandsales m : msbList) {
					value += Double.valueOf(m.getXl().toString());
				}
				// 格式如： <set value='100' label='type1' />
				resultXML.append("<set value=\"");
				resultXML.append(value); // value
				resultXML.append("\" ");
				resultXML.append("label=\"");
				resultXML.append(letters.charAt(i)); // label
				resultXML.append("\" ");
				resultXML.append("link=\""); // label
				resultXML.append("JavaScript:alert('"+shop_ids[i]+"');"); // label
				resultXML.append("\"/> ");
			}
		}

		resultXML.append("</chart>");
		//// //System.out.println("resultXML:="+resultXML.toString());
		return resultXML.toString();
	}
}