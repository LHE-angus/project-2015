package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-22
 */
public class KonkaSellModelNameForCxAction extends BaseAction {

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {

		HashMap allmap = new HashMap();
		//位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
		String day_first = mm_fmt.format(today)+"-01";
		String day_last = df.format(today);

		allmap.put("sell_date_start", day_first);
		allmap.put("sell_date_end", day_last);

		//当前用户信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				allmap.put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				allmap.put("dept_id", dept_fgs.getDept_id());
			}
		}

		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}


	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Date today = new Date();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
		String day_first = mm_fmt.format(today)+"-01";
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
			} else if (konkaDept.getDept_type() == 4) {
				dynaBean.set("dept_type", "4");
				return this.list(mapping, form, request, response);
			} else if (konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				dynaBean.set("dept_type", "5");
				return this.list(mapping, form, request, response);
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
		String model_name = (String) dynaBean.get("model_name");
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");
		String md_size = (String) dynaBean.get("md_size");
		String size_sec = (String) dynaBean.get("size_sec");
		String kh_name_like = (String) dynaBean.get("kh_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String v_customer_type1 = (String) dynaBean.get("v_customer_type1");
		String v_customer_type2 = (String) dynaBean.get("v_customer_type2");
		String excel_to_all = (String) dynaBean.get("excel_to_all");

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
			} else if (konkaDept.getDept_type() == 4) {
				// 经营处、办事处
				dynaBean.set("dept_type", "4");
				dynaBean.set("l4_dept_id", konkaDept.getDept_id().toString());
				dynaBean.set("dept_id", super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id());
				dept_id = super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id().toString();
				l4_dept_id = konkaDept.getDept_id().toString();
			} else if (konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				dynaBean.set("dept_type", "5");
				dynaBean.set("l5_dept_id", konkaDept.getDept_id().toString());
				dynaBean.set("dept_id", super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id());
				dept_id = super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id().toString();
				l5_dept_id = konkaDept.getDept_id().toString();
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}

//		List<?> entityList = this.getSellModelNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
//				l5_dept_id, model_name, show_top, ywy_user_name_like, md_size, size_sec,kh_name_like,r3_code_like,v_customer_type1,v_customer_type2);
//		request.setAttribute("entityList", entityList);
//		request.setAttribute("char_x_heigth", entityList.size() * 30);

		//导出
		if("1".equals(excel_to_all)){
			SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");
			Calendar calendar = Calendar.getInstance();
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("零售畅销机型-" + timestr)
					+ ".xls");
//			request.setAttribute("entityList", entityList);
			return new ActionForward("/admin/KonkaSellModelNameForCx/excel.jsp");
		}
		
		//尺寸段基础数据
        super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return new ActionForward("/admin/KonkaSellModelNameForCx/list.jsp");

	}

	/**
	 * 查询数据列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> m = new HashMap<String, Object>();
		String tj_type = (String) dynaBean.get("tj_type");// 1:按销售额排名，2:按销售量排名
		String show_top = (String) dynaBean.get("show_top");// 排名显示数量
		if (StringUtils.isBlank(tj_type)) {
			tj_type = "1";
		}
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String dept_id = (String) dynaBean.get("dept_id");
//		String l4_dept_id = (String) dynaBean.get("l4_dept_id");
//		String l5_dept_id = (String) dynaBean.get("l5_dept_id");
		String model_name = (String) dynaBean.get("model_name");
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");
		String md_size = (String) dynaBean.get("md_size");
		String size_sec = (String) dynaBean.get("size_sec");
		String kh_name_like = (String) dynaBean.get("kh_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String v_customer_type1 = (String) dynaBean.get("v_customer_type1");
		String v_customer_type2 = (String) dynaBean.get("v_customer_type2");
		String export = (String) dynaBean.get("export");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
//		if (peProdUser.getUser_type() == 0) {
//			// 康佳总部
//			m.put("dept_type","1");
//		} else if (peProdUser.getUser_type() == 1) {
//			// 康佳分公司
//			KonkaDept konkaDept = new KonkaDept();
//			konkaDept.setDept_id(peProdUser.getDept_id());
//			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
//			if (null == konkaDept) {
//				String msg = super.getMessage(request, "popedom.check.invalid");
//				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
//				return null;
//			} else if (konkaDept.getDept_type() == 3) {
//				// 分公司
//				m.put("dept_type", "3");
//				m.put("dept_id",konkaDept.getDept_id().toString());
//				dept_id = konkaDept.getDept_id().toString();
//			} else if (konkaDept.getDept_type() == 4) {
//				// 经营处、办事处
//				m.put("dept_type", "4");
//				m.put("l4_dept_id", konkaDept.getDept_id().toString());
//				m.put("dept_id", super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id());
//				dept_id = super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id().toString();
//				l4_dept_id = konkaDept.getDept_id().toString();
//			} else if (konkaDept.getDept_type() == 5) {
//				// 经营处、办事处
//				m.put("dept_type", "5");
//				m.put("l5_dept_id", konkaDept.getDept_id().toString());
//				m.put("dept_id", super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id());
//				dept_id = super.getKonkaDeptForFgs(konkaDept.getDept_id()).getDept_id().toString();
//				l5_dept_id = konkaDept.getDept_id().toString();
//			} else {
//				String msg = super.getMessage(request, "popedom.check.invalid");
//				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
//				return null;
//			}
//		}

//		List<?> entityList = this.getSellModelNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
//				l5_dept_id, model_name, show_top, ywy_user_name_like, md_size, size_sec,kh_name_like,r3_code_like,v_customer_type1,v_customer_type2);

		KonkaMobileSailData entity = new KonkaMobileSailData();
		if (StringUtils.isNotBlank(sell_date_start)) {
			entity.getMap().put("sell_date_start", sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setSubcomp_id(Long.parseLong(dept_id));
		}
		if (StringUtils.isNotBlank(model_name)) {
			entity.getMap().put("model_name_like", model_name);
		}
		if (StringUtils.isNotBlank(ywy_user_name_like)) {
			entity.getMap().put("ywy_user_name_like", ywy_user_name_like);
		}
		if (StringUtils.isNotBlank(md_size)) {
			entity.getMap().put("md_size", md_size);
		}
		if (StringUtils.isNotBlank(size_sec)) {
			entity.getMap().put("size_sec", size_sec);
		}
		if (StringUtils.isNotBlank(kh_name_like)) {
			entity.getMap().put("kh_name_like", kh_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(v_customer_type1)) {
			entity.getMap().put("v_customer_type1", v_customer_type1);
		}
		if (StringUtils.isNotBlank(v_customer_type2)) {
			entity.getMap().put("v_customer_type2",v_customer_type2);
		}
		if (StringUtils.isNotBlank(show_top)) {
			entity.getMap().put("show_top", show_top);
		}
		if("1".equals(tj_type)){
			entity.getMap().put("price_desc", true);
		}
		if("2".equals(tj_type)){
			entity.getMap().put("num_desc", true);
		}

		List<HashMap> entityList = super.getFacade().getKonkaMobileSailDataService().getCxModelList(entity);
		if(StringUtils.isNotBlank(export)){
			request.setAttribute("entityList",entityList);
			SimpleDateFormat exformat = new SimpleDateFormat("yyyyMMddhhmmss");
			Calendar calendar = Calendar.getInstance();
			String timestr = exformat.format(calendar.getTime());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("零售畅销机型-" + timestr)
					+ ".xls");
			return new ActionForward("/admin/KonkaSellModelNameForCx/excel.jsp");
		}
		//封装成JSON字符串

		m.put("dataList", entityList);
		m.put("char_x_heigth",entityList.size() * 30);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
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
		String model_name = (String) dynaBean.get("model_name");
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");
		String md_size = (String) dynaBean.get("md_size");
		String size_sec = (String) dynaBean.get("size_sec");
		String kh_name_like = (String) dynaBean.get("kh_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String v_customer_type1 = (String) dynaBean.get("v_customer_type1");
		String v_customer_type2 = (String) dynaBean.get("v_customer_type2");

		if (StringUtils.isNotBlank(model_name)) {
			model_name = new String(model_name.getBytes("iso-8859-1"), "UTF-8");
		}
		if (StringUtils.isNotBlank(ywy_user_name_like)) {
			ywy_user_name_like = new String(ywy_user_name_like.getBytes("iso-8859-1"), "UTF-8");
		}

		List<?> list = this.getSellModelNameForCx(tj_type, sell_date_start, sell_date_end, dept_id, l4_dept_id,
				l5_dept_id, model_name, show_top, ywy_user_name_like, md_size, size_sec,kh_name_like,r3_code_like,v_customer_type1,v_customer_type2);
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
		// render(response,
		// getFacade().getTemplateService().getContent("chart/MSLine.ftl",
		// model),
		// "text/xml;charset=GBK");
		// return null;
		// }
		model.put("caption", "畅销产品统计(" + caption + ")");
		// render(response,
		// getFacade().getTemplateService().getContent("chart/MSBar3D.ftl",
		// model),
		// "text/xml;charset=GBK");
		String xmlStr = getFacade().getTemplateService().getContent("chart/MSBar3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.flush();
		return null;

	}

	public List<?> getSellModelNameForCx(String tj_type, String sell_date_start, String sell_date_end, String dept_id,
			String l4_dept_id, String l5_dept_id, String model_name, String show_top, String ywy_user_name_like,
			String md_size, String size_sec,String kh_name_like,String r3_code_like,String v_customer_type1,String v_customer_type2) {
		List<String> array = new ArrayList<String>();

		StringBuffer sql = new StringBuffer(" select t_.* from ( select aa.model_name,aa.TYPE_NAME,aa.all_price,aa.all_num,decode(aa.all_num,0,0,round(aa.all_price/aa.all_num,2)) as price from ( "+
			" select t.model_name,bt.TYPE_NAME,value(sum(t.all_price), 0) as all_price,value(sum(t.num), 0) as all_num "+
			" from (select model_name,num, all_price,CUSTOMER_CATE_ID,REPORT_DATE,CUSTOMER_NAME,DEPT_ID,CUSTOMER_R3_CODE FROM konka_mobile_sail_data " +
			" WHERE IS_DEL = 0 AND num <> 0 AND all_price <> 0) t LEFT JOIN KONKA_CATEGORY c ON t.CUSTOMER_CATE_ID = c.C_INDEX " +
			" left join konka_r3_store store on store.store_id = t.DEPT_ID left join v_org_of_dept dept ON store.JB_JOB_ID = dept.cur_dept_id left join KONKA_PE_PD_MODEL kp on kp.MD_NAME = t.MODEL_NAME "+
			" left join (select TYPE_NAME,FIELD1 from KONKA_BASE_TYPE_DATA where PAR_TYPE_ID = 100023) bt on kp.SIZE_SEC=bt.FIELD1"+
			" where 1 = 1 and t.model_name is not null ");
		if (StringUtils.isNotBlank(sell_date_start)) {
			sql.append(" and t.REPORT_DATE >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			array.add(sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			sql.append(" and t.REPORT_DATE <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			array.add(sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql.append(" and store.dept_id = ? ");
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql.append(" and dept.L4_DEPT_ID = ? ");
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql.append(" and dept.l5_dept_id = ? ");
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(model_name)) {
			sql.append(" and t.model_name like '%' ||?|| '%' ");
			array.add(model_name);
		}
		if (StringUtils.isNotBlank(ywy_user_name_like)) {
			sql.append(" and store.YWY_NAME like '%' ||?|| '%' ");
			array.add(ywy_user_name_like);
		}
		if (StringUtils.isNotBlank(md_size)) {
			sql.append(" and kp.md_size = ? ");
			array.add(md_size);
		}
		if (StringUtils.isNotBlank(size_sec)) {
			sql.append(" and kp.size_sec = ? ");
			array.add(size_sec);
		}
		if (StringUtils.isNotBlank(kh_name_like)) {
			sql.append(" and t.CUSTOMER_NAME like '%'||?||'%' ");
			array.add(kh_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			sql.append(" and t.CUSTOMER_R3_CODE like '%'||?||'%' ");
			array.add(r3_code_like);
		}
		if (StringUtils.isNotBlank(v_customer_type1)) {
			sql.append(" and c.par_index = ? ");
			array.add(v_customer_type1);
		}
		if (StringUtils.isNotBlank(v_customer_type2)) {
			sql.append(" and t.CUSTOMER_CATE_ID = ? ");
			array.add(v_customer_type2);
		}

		sql.append(" group by t.model_name,bt.TYPE_NAME order by t.model_name asc ) aa where 1 = 1 ");

		if (StringUtils.isNotBlank(tj_type) && "2".equals(tj_type)) {
			sql.append(" order by aa.all_num desc,aa.all_price desc ");
		} else {
			sql.append(" order by aa.all_price desc,aa.all_num desc ");
		}

		// 排名显示数量
		sql.append(" ) t_ where 1 = 1 ");
		if (StringUtils.isNotBlank(show_top)) {
			sql.append(" and rownum <= ? ");
			array.add(show_top);
		}

		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql.toString(), array.toArray());

		return list;
	}

}