package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
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
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.domain.StatisticalDimensionTime;
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class StatisticalDimensionDataMonthAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.storeAgentMonthList(mapping, form, request, response);
	}

	// 门店网点统计表 月度
	public ActionForward storeAgentMonthList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		// String category_id = (String) dynaBean.get("category_id");// 客户类型
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String is_sdf = (String) dynaBean.get("is_sdf");// R3分类
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String is_cw = (String) dynaBean.get("is_cw");// 年度类别
		String excel_all = (String) dynaBean.get("excel_all");// 导出
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionDataMonth entity = new StatisticalDimensionDataMonth();
		entity.setIs_del(0);// 默认只统计未停用的客户
		// 取得月度，年度
		if (GenericValidator.isInt(year)) { // 如果年度不为空
			StatisticalDimensionTimeMonth time_now = new StatisticalDimensionTimeMonth();
			int yearnow = Integer.parseInt(year);
			int monthnow = Integer.parseInt(month);
			time_now.setYear(yearnow);
			time_now.setMonth(monthnow);
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeMonthService()
					.getStatisticalDimensionTimeMonth(time_now);
			if (null != time_now && null != time_now.getId()) {
				entity.setMonth_id(new BigDecimal(time_now.getId() + ""));// 当前月度
			}

		} else { // 默认当前月度
			Calendar calendar = Calendar.getInstance();
			int year_now = calendar.get(Calendar.YEAR);
			int month_now = calendar.get(Calendar.MONTH) + 1;// calendar出来的月份是从0开始的
			dynaBean.set("month", "" + month_now);
			// dynaBean.set("year_id", year); 传值
			// dynaBean.set("month_id", month);
			StatisticalDimensionTimeMonth time_now = new StatisticalDimensionTimeMonth();
			time_now.setMonth(month_now);
			time_now.setYear(year_now);
			time_now.setIs_cw(0);
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeMonthService()
					.getStatisticalDimensionTimeMonth(time_now);
			if (null != time_now && null != time_now.getId()) {
				entity.setMonth_id(new BigDecimal(time_now.getId() + ""));// 当前月度
			}
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (GenericValidator.isInt(fgs_id)) {
			entity.getMap().put("fgs_id", fgs_id);
		}
		if (GenericValidator.isInt(is_cw)) {
			entity.getMap().put("is_cw", is_cw);
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(category_id2)) {
			entity.getMap().put("category_id2", category_id2);
		} else {
			if (StringUtils.isNotBlank(category_id1)) {
				entity.getMap().put("category_id1", category_id1);
			}
		}
		if (GenericValidator.isInt(is_sdf)) {
			entity.setIs_sdf(Integer.parseInt(is_sdf));
		} else {
			entity.setIs_sdf(0);// 默认售达方
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

		Long recordCount = getFacade().getStatisticalDimensionDataMonthService()
				.getStatisticalDimensionDataMonthForStoreAndAgentCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "!');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName("门店网点统计表(月度)") + ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionDataMonth> entityList1 = super.getFacade()
					.getStatisticalDimensionDataMonthService()
					.getStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionDataMonth/store_agent_month_list_forReport.jsp");
		}

		List<StatisticalDimensionDataMonth> entityList = super.getFacade().getStatisticalDimensionDataMonthService()
				.getStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		// 取得年度下拉框
		StatisticalDimensionTime time = new StatisticalDimensionTime();
		time.setIs_cw(0);// 自然年度
		time.setIs_del(0);
		List<StatisticalDimensionTime> yearList = super.getFacade().getStatisticalDimensionTimeService()
				.getStatisticalDimensionTimeList(time);
		if (null != yearList && yearList.size() > 0) {
			request.setAttribute("yearList", yearList);
		}

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

		return new ActionForward("/admin/StatisticalDimensionDataMonth/store_agent_month_list.jsp");
	}
}
