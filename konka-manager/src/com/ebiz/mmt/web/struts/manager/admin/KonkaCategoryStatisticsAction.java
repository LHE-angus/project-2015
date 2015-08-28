package com.ebiz.mmt.web.struts.manager.admin;

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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.domain.chart.MsLineChart;
import com.ebiz.mmt.web.struts.BaseAction;

public class KonkaCategoryStatisticsAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String v_rankType = (String) dynaBean.get("rank_type");// 100 || 200
		String v_year = (String) dynaBean.get("v_year");// 年份
		String v_month = (String) dynaBean.get("v_month");// 月份
		String v_company = (String) dynaBean.get("v_company"); // 分公司
		String v_handle_name = (String) dynaBean.get("v_handle_name"); // 经办

		List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户大类和小类
		List<String> p_CategoryList = new ArrayList<String>();// 去重后的客户大类 名称

		// 大类小类要处理
		String v_c_comm = (String) dynaBean.get("v_customer_type");
		String v_c_name = (String) dynaBean.get("v_customer_type2");

		String v_date = "";
		// v_year v_month 要么都为空,要么都不能为空

		if (StringUtils.isNotBlank(v_year) && StringUtils.isNotBlank(v_month)) {
			v_date = v_year + "-" + v_month;
		} else {
			String this_year = null;
			String this_month = null;
			// 取当前时间
			SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
			SimpleDateFormat formatm = new SimpleDateFormat("MM");
			Date now = new Date();

			this_year = formaty.format(now);// 当前年份
			this_month = formatm.format(now);// 当前月份

			v_date = this_year + "-" + this_month;
			dynaBean.set("v_year", this_year);
			dynaBean.set("v_month", this_month);
		}

		// 分公司
		KonkaDept kdept = new KonkaDept();
		kdept.setDept_type(3);
		List<?> entityList = new ArrayList<String>();
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(kdept);
		request.setAttribute("konkaDeptList", konkaDeptList);// 分公司

		// 客户类型 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		// 客户大类
		for (KonkaCategory temp : konkaCategoryList) {
			if (temp.getC_comm() != null && temp.getC_comm().length() > 0) {
				if (!p_CategoryList.contains(temp.getC_comm())) {
					p_CategoryList.add(temp.getC_comm());
				}
			}
		}
		request.setAttribute("p_CategoryList", p_CategoryList);

		// 默认查询一个
		if (StringUtils.isBlank(v_rankType)) {
			v_rankType = "200";
		}

		if (StringUtils.isNotBlank(v_rankType) && v_rankType.equals("100")) {
			dynaBean.set("rank_type", "100");
			entityList = this.getCustomerTypeStatisticsData(v_date, v_company);
			request.setAttribute("entityList", entityList);
			request.setAttribute("char_x_heigth", entityList.size() * 10 == 0 ? 500 : entityList.size() * 10);
			return new ActionForward("/admin/KonkaR3Shop/list100.jsp");// 客户分类对比

		} else if (StringUtils.isNotBlank(v_rankType) && v_rankType.equals("200")) {
			dynaBean.set("rank_type", "200");
			dynaBean.set("v_company", v_company);
			dynaBean.set("v_handle_name", v_handle_name);
			dynaBean.set("v_customer_type", v_c_comm);
			dynaBean.set("v_customer_type2", v_c_name);
			entityList = this.getCustomerTypeStatisticsData2(v_year, v_company, v_handle_name, v_c_name, v_c_comm);
			request.setAttribute("entityList2", entityList);
			request.setAttribute("char_x_heigth", entityList.size() == 0 ? 500 : entityList.size() * 10);
			return new ActionForward("/admin/KonkaR3Shop/list200.jsp");// 客户月度增长趋势

		}
		// 先进首页
		return new ActionForward("/admin/KonkaR3Shop/customerCategoryStatistics.jsp");
	}

	/**
	 * 根据年份+月份 , 分公司 获取客户分类数据
	 * 
	 * @param v_date
	 *            eg: 2012-01
	 * @param v_company
	 * @return
	 */
	protected List<?> getCustomerTypeStatisticsData(String v_date, String v_company) {

		List<String> array = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("select t.par_index ,t.c_comm ,t.c_index,t.c_name ");
		sb.append(",value(lf_1.total_counts, 0) total_counts");
		sb.append(",value(lf_2.current_month_counts, 0) current_month_counts");
		sb.append(" from konka_category t");
		sb.append(" left join (select value(t1.customer_type, -1) customer_type,count(1) total_counts");
		sb.append(" from konka_r3_shop t1 where t1.is_del=0 and t1.is_sdf = 0 ");
		if (StringUtils.isNotBlank(v_company)) {
			sb.append(" and t1.branch_area_name = ? ");
			array.add(v_company);
		}
		sb.append(" group by t1.customer_type) lf_1 on lf_1.customer_type = t.c_index");
		sb.append(" left join (select value(t1.customer_type, -1) customer_type,count(1) current_month_counts");
		sb.append(" from konka_r3_shop t1");
		sb.append(" where 1=1 and t1.is_del = 0 and t1.is_sdf = 0 ");

		if (StringUtils.isNotBlank(v_date)) {
			sb.append("  and t1.add_date >= to_date(? || '-01' ,'yyyy-MM-dd') ");
			array.add(v_date);
		} else {// 默认当前月
			sb.append("  and t1.add_date >= to_date(? || '-01' ,'yyyy-MM-dd') ");
			array.add(v_date);
		}
		Integer v_max_day = getLastDayOfMonth(Integer.valueOf(v_date.split("-")[1]),
				Integer.valueOf(v_date.split("-")[0]));
		if (StringUtils.isNotBlank(v_date)) {
			sb.append(" and t1.add_date <= last_day(to_date(? || '" + "-" + v_max_day + "' ,'yyyy-MM-dd')) ");
			array.add(v_date);
		} else {
			sb.append(" and t1.add_date <= last_day(to_date(? || '" + "-" + v_max_day + "' ,'yyyy-MM-dd')) ");
			array.add(v_date);
		}

		if (StringUtils.isNotBlank(v_company)) {
			sb.append(" and t1.branch_area_name = ? ");
			array.add(v_company);
		}

		sb.append(" group by t1.customer_type) lf_2 on lf_2.customer_type =  t.c_index ");
		sb.append(" where t.c_type = 10 ");
		sb.append(" order by t.par_index ,t.c_index ");
		// logger.info("executeSql:{}", sb.toString());
		List<?> list = getFacade().getBaseReportService().getBaseReportForBindToArray(sb.toString(), array.toArray());
		return list;
	}

	/**
	 * 客户分类月度趋势统计 按年份自动生成12个月
	 * 
	 * @param v_year
	 *            一定不能为空
	 * @param v_branch_area_name
	 * @param v_handle_name
	 * @param v_c_name
	 *            小类
	 * @param v_c_comm
	 *            大类
	 * @return
	 */
	protected List<?> getCustomerTypeStatisticsData2(String v_year, String v_branch_area_name, String v_handle_name,
			String v_c_name, String v_c_comm) {
		List<String> array = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		if (v_year == null || v_year.equals("")) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			v_year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		// /版本1
		/**
		 * 
		 * SELECT aa.ym, nvl (bb.count_by_month, 0) count_by_month FROM (SELECT
		 * '2012' || m_month AS ym FROM (SELECT left (digits (i), 2) m_month
		 * FROM n WHERE rownum < 13) ) aa LEFT JOIN (SELECT to_char (nvl
		 * (t.add_date, sysdate), 'yyyyMM') add_date ,COUNT (1) count_by_month
		 * FROM konka_r3_shop T LEFT JOIN konka_category kc ON t.customer_type =
		 * kc.c_index WHERE 1 = 1 AND to_char (nvl (t.add_date, sysdate),
		 * 'YYYY') = '2012' and t.... GROUP BY to_char (nvl (t.add_date,
		 * sysdate), 'yyyyMM')) bb ON aa.ym = bb.add_date
		 * 
		 */

		/**
		 * sb.append("SELECT aa.ym, nvl (bb.count_by_month, 0) count_by_month ")
		 * ; sb.append("FROM   (SELECT " + v_year + " || m_month AS ym ");
		 * sb.append("FROM   (SELECT left (digits (i), 2) m_month ");
		 * sb.append(" FROM   n  WHERE  rownum < 13) )  aa "); sb.append(
		 * "LEFT JOIN (SELECT to_char (nvl (t.add_date, sysdate), 'yyyyMM') add_date "
		 * ); sb.append(" ,COUNT (1) count_by_month ");
		 * sb.append("FROM      konka_r3_shop T "); sb.append(
		 * "LEFT JOIN  konka_category kc  ON t.customer_type = kc.c_index ");
		 * sb.append("WHERE   1 = 1 ");
		 * sb.append("AND to_char (nvl (t.add_date, sysdate), 'YYYY') = " +
		 * v_year + " "); if (StringUtils.isNotBlank(v_branch_area_name)) {
		 * sb.append("			and t.branch_area_name = ? ");
		 * array.add(v_branch_area_name);// 分公司 } if
		 * (StringUtils.isNotBlank(v_handle_name)) {
		 * sb.append("			and t.handle_name = ? "); array.add(v_handle_name);//
		 * 经办 } if (StringUtils.isNotBlank(v_c_name)) {
		 * sb.append("			and kc.c_name = ? "); array.add(v_c_name);// 小分类 } if
		 * (StringUtils.isNotBlank(v_c_comm)) {
		 * sb.append("			and kc.c_comm = ? "); array.add(v_c_comm);// 大分类 }
		 * sb.append("GROUP BY to_char (nvl (t.add_date, sysdate), 'yyyyMM')) bb ON aa.ym = bb.add_date "
		 * ); sb.append("order by aa.YM asc ");
		 **/
		// /版本1 end

		// /版本2
		/**
		 * 
		 * select aa.year_month ,aa.number1 ,(select nvl(sum(bb.number1), 0)
		 * number1 from (select to_char(t.add_date, 'yyyyMM') as year_month,
		 * count(t.r3_code) as number1 from konka_r3_shop t LEFT JOIN
		 * KONKA_CATEGORY kc ON kc.C_INDEX = t.CUSTOMER_TYPE WHERE 1 = 1 and
		 * kc.C_COMM = '' and kc.C_NAME = '' and t.BRANCH_AREA_NAME = '' and
		 * t.HANDLE_NAME = '' group by to_char(t.add_date, 'yyyyMM') order by
		 * to_char(t.add_date, 'yyyyMM') asc) bb where bb.year_month <=
		 * aa.year_month) as number2 from (select t1.year_month, nvl(t2.number1,
		 * 0) number1 from (select '2012' || m_month as year_month from (select
		 * left(digits(i), 2) m_month from n where rownum < 13 order by m_month
		 * asc) ) t1 left join (SELECT to_char(t.add_date, 'yyyyMM') AS
		 * year_month, count(t.r3_code) AS number1 FROM konka_r3_shop t LEFT
		 * JOIN KONKA_CATEGORY kc ON kc.C_INDEX = t.CUSTOMER_TYPE WHERE 1 = 1
		 * and kc.C_COMM = '' and kc.C_NAME = '' and t.BRANCH_AREA_NAME='' and
		 * t.HANDLE_NAME ='' GROUP BY to_char(t.add_date, 'yyyyMM') ORDER BY
		 * to_char(t.add_date, 'yyyyMM') ASC) t2 on t1.year_month =
		 * t2.year_month) aa
		 * 
		 **/
		sb.append("select aa.year_month ");
		sb.append(",aa.number1 ");
		sb.append(",(select nvl(sum(bb.number1), 0) number1 ");
		sb.append("from   (select   to_char(t.add_date, 'yyyyMM') as year_month, count(t.r3_code) as number1 ");
		sb.append(" from     konka_r3_shop t LEFT JOIN KONKA_CATEGORY kc ON kc.C_INDEX = t.CUSTOMER_TYPE ");
		sb.append("WHERE    1 = 1 and t.is_del=0 and t.is_sdf = 0 ");

		if (StringUtils.isNotBlank(v_c_comm)) {
			sb.append(" and      kc.C_COMM = ? ");
			array.add(v_c_comm);// 客户大类
		}
		if (StringUtils.isNotBlank(v_c_name)) {
			sb.append(" and      kc.C_NAME = ? ");
			array.add(v_c_name);// 客户小类
		}
		if (StringUtils.isNotBlank(v_branch_area_name)) {
			sb.append(" and      t.BRANCH_AREA_NAME  = ? ");
			array.add(v_branch_area_name);// 分公司
		}
		if (StringUtils.isNotBlank(v_handle_name)) {
			sb.append(" and      t.HANDLE_NAME  = ? ");
			array.add(v_handle_name);// 经办
		}
		sb.append("group by to_char(t.add_date, 'yyyyMM') ");
		sb.append("order by to_char(t.add_date, 'yyyyMM') asc) bb ");
		sb.append("where  bb.year_month <= aa.year_month) as number2 ");
		sb.append("from   (select t1.year_month, nvl(t2.number1, 0) number1 ");

		sb.append("from   (select " + v_year + " || m_month as year_month ");

		sb.append("from   (select   right(digits(i), 2) m_month ");
		sb.append("from     n ");
		sb.append("where    rownum < 13 ");
		sb.append("order by m_month asc) )     t1 ");
		sb.append("left join (SELECT   to_char(t.add_date, 'yyyyMM') AS year_month, count(t.r3_code) AS number1 ");
		sb.append("FROM     konka_r3_shop t LEFT JOIN KONKA_CATEGORY kc ON kc.C_INDEX = t.CUSTOMER_TYPE ");
		sb.append("WHERE    1 = 1 and t.is_del=0 and t.is_sdf = 0 ");

		if (StringUtils.isNotBlank(v_c_comm)) {
			sb.append(" and      kc.C_COMM = ? ");
			array.add(v_c_comm);// 客户大类
		}
		if (StringUtils.isNotBlank(v_c_name)) {
			sb.append(" and      kc.C_NAME = ? ");
			array.add(v_c_name);// 客户小类
		}
		if (StringUtils.isNotBlank(v_branch_area_name)) {
			sb.append(" and      t.BRANCH_AREA_NAME  = ? ");
			array.add(v_branch_area_name);// 分公司
		}
		if (StringUtils.isNotBlank(v_handle_name)) {
			sb.append(" and      t.HANDLE_NAME  = ? ");
			array.add(v_handle_name);// 经办
		}

		sb.append("GROUP BY to_char(t.add_date, 'yyyyMM') ");
		sb.append(" ORDER BY to_char(t.add_date, 'yyyyMM') ASC) t2 on t1.year_month = t2.year_month) aa  ");

		// /版本2 end

		// logger.info("executeSQL:{}", sb.toString());
		List<?> list = getFacade().getBaseReportService().getBaseReportForBindToArray(sb.toString(), array.toArray());
		return list;

	}

	public ActionForward showChart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String v_year = (String) dynaBean.get("v_year");
		String v_month = (String) dynaBean.get("v_month");
		String v_company = (String) dynaBean.get("v_company");

		String v_date = "";
		// v_year v_month 要么都为空,要么都不能为空

		if (StringUtils.isNotBlank(v_year)) {
			if (StringUtils.isNotBlank(v_month)) {
				v_date = v_year + "-" + v_month;
			}
		}

		// get data
		List<?> list = getCustomerTypeStatisticsData(v_date, v_company);

		List<MsLineChart> msLineChartList = new ArrayList<MsLineChart>();// x坐标
		List<BaseChart> baseChartList = new ArrayList<BaseChart>(); // y坐标

		/**
		 * y |超市连锁 |渠道 | | |33 66 |______________________x
		 */
		if (null != list && list.size() > 0) {

			List<BaseChart> baseChartList_1 = new ArrayList<BaseChart>();

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				// 客户大类
				BaseChart baseChart = new BaseChart();
				baseChart.setCategory_label(obj[1] == null ? "" : obj[1].toString());
				baseChartList.add(baseChart); // y 坐标 客户大类

				// 客户增长量
				BaseChart baseChart1 = new BaseChart(); // x坐标增加量
				baseChart1.setValue(obj[4] == null ? "0" : obj[4].toString());
				baseChartList_1.add(baseChart1);

			}

			MsLineChart msLineChart1 = new MsLineChart();
			msLineChart1.setSeries_name("新增客户数(个)");
			msLineChart1.setColor("3366FF");
			msLineChart1.setAnchor_border_color("3366FF");
			msLineChart1.setAnchor_bg_color("3366FF");
			msLineChart1.setBaseChartList(baseChartList_1);
			msLineChartList.add(msLineChart1);

		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		model.put("msLineChartList", msLineChartList);
		model.put("mSColumn3DChartChartList", msLineChartList);
		String caption = "按客户增长量排名";
		model.put("caption", "分公司排名-" + caption + "(柱状图)");
		render(response, getFacade().getTemplateService().getContent("chart/MSLine.swf", model), "text/xml;charset=GBK");
		return null;
	}

	// TODO need to fixed
	/**
	 * 根据分公司获取分公司下面的所有经办信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJBDataBydeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zbukrs = (String) dynaBean.get("branch_area_name"); // 分公司
		List<String> jbList = new ArrayList<String>();

		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setBranch_area_name(zbukrs);
		List<KonkaR3Shop> kkList = super.getFacade().getKonkaR3ShopService().getJBDataBydeptName(konkaR3Shop);

		if (kkList != null && kkList.size() > 0) {
			for (KonkaR3Shop r : kkList) {
				if (r.getHandle_name() != null || "".equals(r.getHandle_name()))
					jbList.add(r.getHandle_name());// 数据已经去重
			}
		}
		request.setAttribute("kkList", kkList);
		super.renderJson(response, JSON.toJSONString(jbList));
		return null;
	}

	public ActionForward getCategoryListByParent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String v_customer_type = (String) dynaBean.get("v_customer_type"); // 客户大类
		List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户大类和小类

		// 客户类型 网点类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		kc.setC_comm(v_customer_type);
		konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
		request.setAttribute("konkaCategoryList", konkaCategoryList);
		super.renderJson(response, JSON.toJSONString(konkaCategoryList));
		return null;
	}

	public int getLastDayOfMonth(int mm, int year) {
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
