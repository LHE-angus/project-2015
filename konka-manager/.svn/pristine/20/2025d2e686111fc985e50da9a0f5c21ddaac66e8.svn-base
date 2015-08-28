package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionArea;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.StatisticalDimensionRetailData;
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wangkl
 * @version 2014-12-08
 * @desc  销售趋势报表
 */
public class JcfxProductAreaMapAction extends BaseAction {
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		String[] provinces = {"广东","青海","四川","海南","陕西","甘肃", "云南", "湖南", "湖北", "黑龙江",
			    			  "贵州", "山东", "江西", "河南", "河北","山西", "安徽", "福建", "浙江", "江苏", 
			    			  "吉林", "辽宁", "台湾","新疆", "广西", "宁夏", "内蒙古", "西藏", "北京", "天津", 
			    			  "上海", "重庆","香港", "澳门"};
		
		DynaBean dynaBean = (DynaBean) form;
		String year = (String)dynaBean.get("year");
		String month = (String)dynaBean.get("month");
		String province = (String)dynaBean.get("province");
		
		
		// 遍历年份，取自2012年之后年份
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
		int now_year = Integer.valueOf(fmt.format(new Date()));
		ArrayList<String> yearList = new ArrayList<String>();
		for (int y = now_year; y >=2012 ; y--) {
			yearList.add(y + "");
		}
		request.setAttribute("yearList", yearList);
		if (StringUtils.isNotBlank(year)) {
			request.setAttribute("year", year);
		}else{
			request.setAttribute("year", now_year);
			year = now_year+"";
		}
//		
//		//月度列表
//		StatisticalDimensionTimeMonth mon = new StatisticalDimensionTimeMonth();
//		mon.setYear(Integer.parseInt(year));
//		List<StatisticalDimensionTimeMonth> monthList = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMontOrderByMonthAsc(mon);
//		ArrayList<String> monList = new ArrayList<String>();
//		for(StatisticalDimensionTimeMonth m:monthList){
//			monList.add(m.getMonth().toString());
//		}
//		request.setAttribute("monList", monList);
//		
		if (StringUtils.isNotBlank(month)) {
			request.setAttribute("month", month);
		}else{
			request.setAttribute("month", "1");
			month = "1";
		}
		dynaBean.set("year", year);
		dynaBean.set("month", month);
		
		//省份列表
		StatisticalDimensionArea sda = new StatisticalDimensionArea();
		sda.getMap().put("province", true);
		List<HashMap> provinceList = super.getFacade().getStatisticalDimensionAreaService().getAreaListForMap(sda);
		request.setAttribute("provinceList", provinceList);
		if (StringUtils.isNotBlank(province)) {
			request.setAttribute("province", province);
		}else{
			request.setAttribute("province", "440000");
			province = "440000";
		}
		dynaBean.set("province", province);
		String pro_name = "";
		for(HashMap map : provinceList){
			if(province.equals(map.get("PROVINCE_ID").toString())){
				pro_name = (String) map.get("PROVINCE_NAME");
				break;
			}
		}
		
		String short_name = pro_name;
		for(String p : provinces){
			if(short_name.contains(p)){
				short_name=p;
				break;
			}
		}
		request.setAttribute("province_name", short_name);
		request.setAttribute("map_show", "");
		request.setAttribute("table_show", "none");
		
		//获取月度信息
		StatisticalDimensionTimeMonth sdtm = new StatisticalDimensionTimeMonth();
		sdtm.setYear(Integer.parseInt(year));
		sdtm.setMonth(Integer.parseInt(month));
		sdtm = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(sdtm);
		
		if (null == sdtm) {
			String msg = super.getMessage(request, "no.year.month");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		String title_text = year+"年"+month+"月"+short_name;
		request.setAttribute("title_text", title_text);
		//左侧地图信息
		StringBuffer sb = new StringBuffer();
		request.setAttribute("map1_title", title_text+"零售量分布");
		request.setAttribute("map1_maptype", short_name);
		request.setAttribute("map1_legend_data", super.getMessage(request, "retail.number"));  //零售量
		
		//获取该省份下城市零售量列表
		StatisticalDimensionDataMonth sddm = new StatisticalDimensionDataMonth();
		sddm.getMap().put("month_id", sdtm.getId());
		sddm.getMap().put("province", province);
		List<HashMap> city_nums = super.getFacade().getStatisticalDimensionDataMonthService().getCitysDataList(sddm);
		
		List<String> modList = new ArrayList<String>();
		List<Object> objList = new ArrayList<Object>();
		//城市列表数据
		for(HashMap map: city_nums){
			Double num = Double.valueOf(map.get("NUM").toString());
			Double par_num = Double.valueOf(map.get("PAR_NUM").toString());
			Double money = Double.valueOf(map.get("MONEY").toString());
			Double par_money = Double.valueOf(map.get("PAR_MONEY").toString());
			if(num>0&&par_num>0){
				Double per_num = (num-par_num)/par_num;
				map.put("PER_NUM", per_num);
			}else if(num==0&&par_num>0){
				map.put("PER_NUM", -1);
			}else if(num>0&&par_num==0){
				map.put("PER_NUM", 1);
			}else{
				map.put("PER_NUM", 0);
			}
			if(money>0&&par_money>0){
				Double per_money = (money-par_money)/par_money;
				map.put("PER_MONEY", per_money);
			}else if(money==0&&par_money>0){
				map.put("PER_MONEY", -1);
			}else if(money>0&&par_money==0){
				map.put("PER_MONEY", 1);
			}else{
				map.put("PER_MONEY", 0);
			}
			
			//组织各城市前10机型数据
			StatisticalDimensionRetailData city_data = new StatisticalDimensionRetailData();
			city_data.getMap().put("begin_date", sdtm.getStart_date());
			city_data.getMap().put("end_date", sdtm.getEnd_date());
			city_data.getMap().put("city_id", map.get("CITY_ID"));
			List<HashMap> clist = super.getFacade().getStatisticalDimensionRetailDataService().getTop10CityList(city_data);
			for(HashMap tmap : clist){
				String m_name = tmap.get("MODEL_NAME").toString();
				if(!modList.contains(m_name)){
					modList.add(m_name);
				}
			}
			objList.add(clist);
		}
		request.setAttribute("city_list", city_nums);
		
		StringBuffer sby = new StringBuffer();
		sby.append("[");
		String[] city_arr = new String[city_nums.size()];
		for(int i=0; i<city_nums.size(); i++){
			sb.append("{name: '"+city_nums.get(i).get("CITY_NAME")+"',value: "+city_nums.get(i).get("NUM")+"}");
			sby.append("'"+city_nums.get(i).get("CITY_NAME")+"'");
			city_arr[i]=(String) city_nums.get(i).get("CITY_NAME");
			if(i<city_nums.size()-1){
				sb.append(",");
				sby.append(",");
			}
		}
		sby.append("]");
		request.setAttribute("map1_data", sb.toString());
		
		//右侧地图信息
		sb.setLength(0);
		request.setAttribute("map2_title", title_text+"各城市各尺寸段零售量分布");
		request.setAttribute("map2_legend_data", "'32-', '32-36','37-39','40-45','46-50','51-59','60-69','70+','其他'");
		request.setAttribute("map2_y_data", sby.toString());
		request.setAttribute("map2_maptype", short_name);
		
		//尺寸段信息
		StatisticalDimensionRetailData sdrd = new StatisticalDimensionRetailData();
		sdrd.getMap().put("begin_date", sdtm.getStart_date());
		sdrd.getMap().put("end_date", sdtm.getEnd_date());
		sdrd.getMap().put("province", province);
		List<HashMap> rlist = super.getFacade().getStatisticalDimensionRetailDataService().getSizeOfCityList(sdrd);
		
		//赋值到二维数组中
		String[][] range_data = new String[9][city_nums.size()];
		for(int r=0;r<rlist.size(); r++){
			String city = (String) rlist.get(r).get("CITY_NAME");
			String num = String.valueOf(rlist.get(r).get("NUMS"));
			
			int index =(Integer) rlist.get(r).get("RANGE_SIZE");
			for(int c=0;c<city_arr.length;c++){
				if(city.equals(city_arr[c])){
					range_data[index-1][c] = num;
					break;
				}
			}
		}
		
		//组装前台显示数据
		String[] datas = new String[9];
		for(int k=0;k<range_data.length;k++){
			sb.setLength(0);
			String[] temp = range_data[k];
			sb.append("[");
			for(int m=0;m<temp.length;m++){
				if(null!=temp[m]){
					sb.append(temp[m]);
				}else{
					sb.append("0");
				}
				if(m<temp.length-1){
					sb.append(",");
				}
			}
			sb.append("]");
			datas[k]=sb.toString();
		}
		
		String[] md_arr = {"32-", "32-36","37-39","40-45","46-50","51-59","60-69","70+","其他"};
		
		sb.setLength(0);
		for(int i=0;i<md_arr.length;i++){
			sb.append("{name:'"+md_arr[i]+"'," +
					   " type:'bar'," +
					   " stack:'总量'," +
					   " itemStyle : { normal: {label : {show: true, position: 'insideRight',textStyle:{fontSize:7}}}}," +
					   " data:"+datas[i]+"}");
			if(i<md_arr.length-1){
				sb.append(",");
			}
		}
		request.setAttribute("map2_data", sb.toString());
		
		//前10地图信息
		request.setAttribute("map3_title", title_text+"各城市零售量前10机型");
		request.setAttribute("map3_machine_type", super.getMessage(request, "machine.type"));
		request.setAttribute("map3_retail_number", super.getMessage(request, "retail.number"));
		request.setAttribute("map3_average_price", super.getMessage(request, "average.price"));
		int rota = 0;  //倾斜角度
		if(city_nums.size()>18){
			rota = -45;
		}
		request.setAttribute("map3_rotate", rota);
		request.setAttribute("map3_x_data", sby.toString());
		
		//前10机型数组
		StringBuffer sbm = new StringBuffer();
		Object[] mod_arr = modList.toArray();
		
//		String[] mod_arr = {"LED42M1600B", "LED46X8000D","LED75X9800U","LED65X8800U"};
//		String[] mod_datas = {
//				"['深圳市',30,90]",
//				"['中山市',10,100]",
//				"['清远市',50,99],['湛江市',40,60]",
//			    "['清远市',50,99],['湛江市',40,60]"
//				};
		String[] mod_datas = new String[mod_arr.length];
		for(int m=0; m<mod_arr.length; m++){
			sb.setLength(0);
			for(Object o : objList){
				List<HashMap> tlist = (List<HashMap>) o;
				for(int h=0;h<tlist.size();h++){
					Object model_name = tlist.get(h).get("MODEL_NAME");
					if(model_name.equals(mod_arr[m])){
						sb.append("['"+tlist.get(h).get("CITY_NAME")+"',"+tlist.get(h).get("NUMS")+","+tlist.get(h).get("MONEYS")+"],");
					}
				}
				if(sb.length()>0){
					mod_datas[m]=sb.toString().substring(0, sb.length()-1);
				}else{
					mod_datas[m]="";
				}
			}
		}
		
		sb.setLength(0);
		for(int i=0;i<mod_arr.length;i++){
			sb.append("{name:'"+mod_arr[i]+"'," +
					" type:'scatter'," +
					" symbol:'emptyCircle',"+
		            " symbolSize: function (value){"+
		            "    return Math.round(value[2]/500);"+
		            " }," +
					" data:["+mod_datas[i]+"]}");
			if(i<mod_arr.length-1){
				sb.append(",");
			}
		}
		request.setAttribute("map3_data", sb.toString());
		
		return mapping.findForward("view");
	}
	
	
	/**
	 * 点击城市查看机型详情
	 * @author Liang,HouEn
	 * 2015-2-4
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toViewModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("map_show", "none");
		request.setAttribute("table_show", "");
		
		DynaBean dynaBean = (DynaBean) form;
		String year = (String)dynaBean.get("year");
		String month = (String)dynaBean.get("month");
		String city_id = (String)dynaBean.get("city_id");
		String province = (String)dynaBean.get("province");
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("province", province);
		
		StatisticalDimensionArea sda = new StatisticalDimensionArea();
		sda.setCity_id(Long.parseLong(city_id));
		HashMap city = super.getFacade().getStatisticalDimensionAreaService().getCityNameById(sda);
		request.setAttribute("city_name", city.get("CITY_NAME"));
		
		// 遍历年份，取自2012年之后年份
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
		int now_year = Integer.valueOf(fmt.format(new Date()));
		ArrayList<String> yearList = new ArrayList<String>();
		for (int y = now_year; y >=2012 ; y--) {
			yearList.add(y + "");
		}
		request.setAttribute("yearList", yearList);
		
		//获取月度信息
		StatisticalDimensionTimeMonth sdtm = new StatisticalDimensionTimeMonth();
		sdtm.setYear(Integer.parseInt(year));
		sdtm.setMonth(Integer.parseInt(month));
		sdtm = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(sdtm);
		
		//上一年度月度信息
		StatisticalDimensionTimeMonth par_year = new StatisticalDimensionTimeMonth();
		par_year.setYear(Integer.parseInt(year)-1);
		par_year.setMonth(Integer.parseInt(month));
		par_year = super.getFacade().getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(par_year);
		
		StatisticalDimensionRetailData sdrd = new StatisticalDimensionRetailData();
		sdrd.getMap().put("begin_date", sdtm.getStart_date());
		sdrd.getMap().put("end_date", sdtm.getEnd_date());
		sdrd.getMap().put("par_begin_date", par_year.getStart_date());
		sdrd.getMap().put("par_end_date", par_year.getEnd_date());
		sdrd.getMap().put("city_id", city_id);
		List<HashMap> modellist = super.getFacade().getStatisticalDimensionRetailDataService().getModleDataList(sdrd);
		
		request.setAttribute("modellist", modellist);
		
		return mapping.findForward("view");
	}
	
	
	
	
	
	public ActionForward ajaxGetDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynabean=(DynaBean)form;
		String terminal_id=(String) dynabean.get("terminal_id");
		if(StringUtils.isBlank(terminal_id)){
			return null;
		}
		KonkaMobileSailData entity=new KonkaMobileSailData();
		entity.setDept_id(Long.valueOf(terminal_id));
		entity.setIs_del(0);
		entity.getMap().put("report_date_begin",new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime())+"-01-01 00:00:00");
		entity.getMap().put("report_date_end",new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime())+"-12-31 23:59:59");
		List<HashMap<String, Object>> entityList=super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataForTerminalMap(entity);
		if(null!=entityList && entityList.size() > 0){
			logger.info(entityList.toString());
			JSONArray jsonArray = JSONArray.fromObject(entityList);
//			 int start = jsonArray.toString().indexOf("[");
//			 int end = jsonArray.toString().lastIndexOf("}");
//			 String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
}
