package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionArea;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Xiao,GuoJian
 * @version 2014-11-22
 */
public class StatisticalDimensionAreaAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.listForCustomer(mapping, form, request, response);
	}

	// 区域客户统计表 2014-11-22
	public ActionForward listForCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(50);
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fgs_id = (String) dynaBean.get("fgs_id");// 分公司ID
		String wpzb_l = (String) dynaBean.get("wpzb_l");// 我品占比
		String wpzb_g = (String) dynaBean.get("wpzb_g");
		String month_retail_money_l = (String) dynaBean.get("month_retail_money_l");// 零售额
		String month_retail_money_g = (String) dynaBean.get("month_retail_money_g");
		String retail_price_l = (String) dynaBean.get("retail_price_l");// 零售单价
		String retail_price_g = (String) dynaBean.get("retail_price_g");

		String province = (String) dynaBean.get("province");// 省
		String city = (String) dynaBean.get("city");// 市
		String country = (String) dynaBean.get("country");// 县
		String town = (String) dynaBean.get("town");// 乡镇

		String excel_all = (String) dynaBean.get("excel_all");// 导出
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionArea entity = new StatisticalDimensionArea();
		entity.setIs_del(0);// 默认只统计未停用的客户

		Calendar calendar = Calendar.getInstance();
		int c_year = calendar.get(Calendar.YEAR);
		int c_month = calendar.get(Calendar.MONTH) + 1;
		if (GenericValidator.isInt(month)) {
			StatisticalDimensionTimeMonth tmonth = new StatisticalDimensionTimeMonth();
			tmonth.setIs_cw(0);
			tmonth.setIs_del(0);
			tmonth.setYear(Integer.valueOf(year));
			tmonth.setMonth(Integer.valueOf(month));
			StatisticalDimensionTimeMonth monthResult = super.getFacade().getStatisticalDimensionTimeMonthService()
					.getStatisticalDimensionTimeMonth(tmonth);
			if (null != monthResult) {
				entity.getMap().put("month_id", monthResult.getId());
			} else {
				tmonth.setYear(Integer.valueOf(c_year));
				tmonth.setMonth(Integer.valueOf(c_month));
				StatisticalDimensionTimeMonth monthResult1 = super.getFacade()
						.getStatisticalDimensionTimeMonthService().getStatisticalDimensionTimeMonth(tmonth);
				entity.getMap().put("month_id", monthResult1.getId());
			}
		} else {// 默认当前年度,当前月
			dynaBean.set("year", c_year);
			dynaBean.set("month", "" + c_month);
			//System.out.println("===============year=" + c_year);
			StatisticalDimensionTimeMonth tmonth = new StatisticalDimensionTimeMonth();
			tmonth.setIs_cw(0);
			tmonth.setIs_del(0);
			tmonth.setYear(Integer.valueOf(c_year));
			tmonth.setMonth(Integer.valueOf(c_month));
			StatisticalDimensionTimeMonth monthResult = super.getFacade().getStatisticalDimensionTimeMonthService()
					.getStatisticalDimensionTimeMonth(tmonth);
			if (null != monthResult) {
				entity.getMap().put("month_id", monthResult.getId());
				dynaBean.set("month", "" + c_month);
			}

		}
		if (StringUtils.isNotBlank(wpzb_l)) {
			entity.getMap().put("wpzb_l", wpzb_l);
		}
		if (StringUtils.isNotBlank(wpzb_g)) {
			entity.getMap().put("wpzb_g", wpzb_g);
		}
		if (StringUtils.isNotBlank(month_retail_money_l)) {
			entity.getMap().put("month_retail_money_l", month_retail_money_l);
		}
		if (GenericValidator.isInt(month_retail_money_g)) {
			entity.getMap().put("month_retail_money_g", month_retail_money_g);
		}
		if (GenericValidator.isInt(retail_price_l)) {
			entity.getMap().put("retail_price_l", retail_price_l);
		}
		if (GenericValidator.isInt(retail_price_g)) {
			entity.getMap().put("retail_price_g", retail_price_g);
		}

		if (GenericValidator.isInt(town)) {// 精确到乡镇
			entity.setP_index(Long.parseLong(town));
		} else if (GenericValidator.isInt(country)) {// 精确到县区
			entity.setCounty_id(Long.parseLong(country));
		} else if (GenericValidator.isInt(city)) {// 精确到市
			entity.setCity_id(Long.parseLong(city));
		} else if (GenericValidator.isInt(province)) {// 精确到省
			entity.setProvince_id(Long.parseLong(province));
		}

		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(fgs_id) ? Long.valueOf(fgs_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(fgs_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", fgs_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		Long recordCount = getFacade().getStatisticalDimensionAreaService()
				.getStatisticalDimensionAreaForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		//导出
		if(StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)){
			if(recordCount.intValue()>20000){
				renderJavaScript(response, "alert('"+super.getMessage(request, "export.too.many")
						+"!');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("区域客户统计表")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionArea> entityList1 = super.getFacade()
					.getStatisticalDimensionAreaService()
					.getStatisticalDimensionAreaForCustomerPaginatedList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionArea/listForCustomer_report.jsp");
		}
		

		List<StatisticalDimensionArea> entityList = super.getFacade().getStatisticalDimensionAreaService()
				.getStatisticalDimensionAreaForCustomerPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 取分公司
		KonkaDept kd = new KonkaDept();
		List<KonkaDept> sybDeptInfoList = new ArrayList<KonkaDept>();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			entity.getMap().put("dept_name", kd.getDept_name());
			sybDeptInfoList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
			List<KonkaDept> removelist = new ArrayList<KonkaDept>();
			if (null != sybDeptInfoList && sybDeptInfoList.size() > 0) {
				for (KonkaDept dp : sybDeptInfoList) {
					if ("KF001".equals(dp.getDept_sn()) || "KFSF001".equals(dp.getDept_sn())
							|| "KFDFD001".equals(dp.getDept_sn())) {
						removelist.add(dp);
					}
				}
			}
			sybDeptInfoList.removeAll(removelist);
		} else {
			kd.setDept_id(ui.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			entity.getMap().put("dept_name", kd.getDept_name());
			sybDeptInfoList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		}
		request.setAttribute("sybDeptInfoList", sybDeptInfoList);

		// 取年
		StatisticalDimensionTimeMonth timeMonth = new StatisticalDimensionTimeMonth();
		timeMonth.setIs_cw(0);
		timeMonth.setIs_del(0);
		List<Integer> yearList = super.getFacade().getStatisticalDimensionTimeMonthService()
				.getStatisticalDimensionTimeMonthGroupByYear(timeMonth);
		request.setAttribute("yearList", yearList);
		// 取月
//		StatisticalDimensionTimeMonth timeMonth1 = new StatisticalDimensionTimeMonth();
//		timeMonth1.setIs_cw(0);
//		timeMonth1.setIs_del(0);
//		if (GenericValidator.isInt(year)) {// 如果年度不为空
//			timeMonth1.setYear(Integer.parseInt(year));
//		} else {// 年度为空，则当前年度
//			timeMonth1.setYear(Integer.valueOf(c_year));
//		}
//		List<StatisticalDimensionTimeMonth> monthList = super.getFacade().getStatisticalDimensionTimeMonthService()
//				.getStatisticalDimensionTimeMontOrderByMonthAsc(timeMonth1);
//		request.setAttribute("monthList", monthList);

		return new ActionForward("/admin/StatisticalDimensionArea/listForCustomer.jsp");
	}

}
