package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StatisticalDimensionData;
import com.ebiz.mmt.domain.StatisticalDimensionTime;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;


/**
 * @author Xiao,GuoJian
 * @version 2014-11-06
 */
public class StatisticalDimensionDataAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.storeAgentList(mapping, form, request, response);
	}

	// 门店网点统计表 2014-11-08
	public ActionForward storeAgentList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(50);
		String mod_id = (String) dynaBean.get("mod_id");
		String year_id = (String) dynaBean.get("year_id");
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

		StatisticalDimensionData entity = new StatisticalDimensionData();
		entity.setIs_del(0);// 默认只统计未停用的客户
		if (GenericValidator.isInt(year_id)) {
			entity.setYear_id(Long.parseLong(year_id));
		} else {// 默认当前年度
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			StatisticalDimensionTime time = new StatisticalDimensionTime();
			time.setYear(year);
			time.setIs_cw(0);// 自然年度
			time.setIs_del(0);
			time = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time);
			if (null != time && null != time.getId()) {
				entity.setYear_id(time.getId());
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

		Long recordCount = getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForStoreAndAgentCount(entity);
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
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店网点统计表 ")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionData> entityList1 = super.getFacade()
					.getStatisticalDimensionDataService()
					.getStatisticalDimensionDataForStoreAndAgentPaginatedList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionData/store_agent_list_Report.jsp");
		}

		List<StatisticalDimensionData> entityList = super.getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForStoreAndAgentPaginatedList(entity);

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

		return new ActionForward("/admin/StatisticalDimensionData/store_agent_list.jsp");
	}

	// 客户类型统计表 2014-11-10
	public ActionForward customerTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		String mod_id = (String) dynaBean.get("mod_id");
		String year_id = (String) dynaBean.get("year_id");
		String fgs_id = (String) dynaBean.get("fgs_id");// 分公司ID
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String is_sdf = (String) dynaBean.get("is_sdf");// R3分类
		String is_cw = (String) dynaBean.get("is_cw");// 年度类别
		String excel_all = (String) dynaBean.get("excel_all");//导出数据
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionData entity = new StatisticalDimensionData();
		entity.setIs_del(0);// 默认只统计未停用的客户

		if (GenericValidator.isInt(year_id)) {
			entity.getMap().put("year_id_now", year_id);// 当前年度
			StatisticalDimensionTime time_now = new StatisticalDimensionTime();
			time_now.setId(Long.parseLong(year_id));
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_now);
			if (null != time_now && null != time_now.getId()) {// 找上一年度的year_id
				request.setAttribute("year_now", time_now.getYear());
				StatisticalDimensionTime time_pre = new StatisticalDimensionTime();
				time_pre.setYear(time_now.getYear() - 1);
				time_pre.setIs_cw(0);// 自然年度
				time_pre.setIs_del(0);
				time_pre = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_pre);
				if (null != time_pre && null != time_pre.getId()) {
					entity.getMap().put("year_id_pre", time_pre.getId());
					request.setAttribute("year_pre", time_pre.getYear());
				} else {
					request.setAttribute("year_pre", time_now.getYear() - 1);
				}
			}
		} else {// 默认当前年度
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			StatisticalDimensionTime time_now = new StatisticalDimensionTime();
			time_now.setYear(year);
			time_now.setIs_cw(0);// 自然年度
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_now);
			if (null != time_now && null != time_now.getId()) {
				entity.getMap().put("year_id_now", time_now.getId());// 当前年度
				request.setAttribute("year_now", time_now.getYear());
			}
			StatisticalDimensionTime time_pre = new StatisticalDimensionTime();
			time_pre.setYear(year - 1);
			time_pre.setIs_cw(0);// 自然年度
			time_pre.setIs_del(0);
			time_pre = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_pre);
			if (null != time_pre && null != time_pre.getId()) {
				entity.getMap().put("year_id_pre", time_pre.getId());
				request.setAttribute("year_pre", time_pre.getYear());
			}
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
	
        //分页
		Long recordCount = getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForCustomerFltjCount(entity);
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
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户分类统计表")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionData> entityList1 = super.getFacade()
					.getStatisticalDimensionDataService()
					.getStatisticalDimensionDataForCustomerFltjList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionData/customer_type_tj_forReport.jsp");
		}
		
        //查询
		List<StatisticalDimensionData> entityList = super.getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForCustomerFltjPaginatedList(entity);

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

		return new ActionForward("/admin/StatisticalDimensionData/customer_type_tj.jsp");
	}

	//门店类型统计表
	public ActionForward storeType(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(10);
		String mod_id = (String) dynaBean.get("mod_id");
		String year_id = (String) dynaBean.get("year_id");
		String fgs_id = (String) dynaBean.get("fgs_id");// 分公司ID
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String excel_all = (String) dynaBean.get("excel_all");//导出数据
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		StatisticalDimensionData entity = new StatisticalDimensionData();
		entity.setIs_del(0);//默认统计未停用的客户
		if (GenericValidator.isInt(year_id)) {
			entity.getMap().put("year_id_now", year_id);// 当前年度
			StatisticalDimensionTime time_now = new StatisticalDimensionTime();
			time_now.setId(Long.parseLong(year_id));
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_now);
			if (null != time_now && null != time_now.getId()) {// 找上一年度的year_id
				request.setAttribute("year_now", time_now.getYear());
				StatisticalDimensionTime time_pre = new StatisticalDimensionTime();
				time_pre.setYear(time_now.getYear() - 1);
				time_pre.setIs_cw(0);// 自然年度
				time_pre.setIs_del(0);
				time_pre = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_pre);
				if (null != time_pre && null != time_pre.getId()) {
					entity.getMap().put("year_id_pre", time_pre.getId());
					request.setAttribute("year_pre", time_pre.getYear());
				} else {
					request.setAttribute("year_pre", time_now.getYear() - 1);
				}
			}
		} else {// 默认当前年度
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			StatisticalDimensionTime time_now = new StatisticalDimensionTime();
			time_now.setYear(year);
			time_now.setIs_cw(0);// 自然年度
			time_now.setIs_del(0);
			time_now = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_now);
			if (null != time_now && null != time_now.getId()) {
				entity.getMap().put("year_id_now", time_now.getId());// 当前年度
				request.setAttribute("year_now", time_now.getYear());
			}
			StatisticalDimensionTime time_pre = new StatisticalDimensionTime();
			time_pre.setYear(year - 1);
			time_pre.setIs_cw(0);// 自然年度
			time_pre.setIs_del(0);
			time_pre = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(time_pre);
			if (null != time_pre && null != time_pre.getId()) {
				entity.getMap().put("year_id_pre", time_pre.getId());
				request.setAttribute("year_pre", time_pre.getYear());
			}else{
				request.setAttribute("year_pre",year - 1);
			}
		}
		if (GenericValidator.isInt(fgs_id)) {
			entity.getMap().put("fgs_id", fgs_id);
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(category_id2)) {
			entity.getMap().put("category_id2",category_id2);
		} else {
			if (StringUtils.isNotBlank(category_id1)) {
				entity.getMap().put("category_id1",category_id1);
			}
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
		
        //取分页
		Long recordCount = getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForStoreTypeFltjCount(entity);
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
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店分类统计表")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionData> entityList1 = super.getFacade()
					.getStatisticalDimensionDataService()
					.getStatisticalDimensionDataForStoreTypeFltjPaginatedList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionData/stroe_type_tj_Report.jsp");
		}
		
        //取数据
		List<StatisticalDimensionData> entityList = super.getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForStoreTypeFltjPaginatedList(entity);

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
		return new ActionForward("/admin/StatisticalDimensionData/store_type_tj.jsp");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {// 左边菜单栏导航树
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);// 导航

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String year = (String) dynaBean.get("year");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String is_cw = (String) dynaBean.get("is_cw");

		StatisticalDimensionTime entity = new StatisticalDimensionTime();
		if (GenericValidator.isInt(year)) {
			entity.setYear(Integer.parseInt(year));
		}
		if (GenericValidator.isInt(is_cw)) {
			entity.setIs_cw(Integer.parseInt(is_cw));
		}
		entity.setIs_del(0);
		Long count = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTimeCount(entity);
		if (count != null && count > 0) {// 说明之前有这个年度，不准添加
			super.renderJavaScript(response, "alert('年度已经存在，请重新添加！');history.back();");
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotBlank(start_date) && StringUtils.isNotBlank(end_date)) {
			start_date = start_date + " 00:00:00";
			end_date = end_date + " 23:59:59";
			entity.setStart_date(dateFormat.parse(start_date));
			entity.setEnd_date(dateFormat.parse(end_date));
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAdd_user_id(ui.getId());
		entity.setAdd_date(new Date());
		if (GenericValidator.isLong(id)) {// 老数据更新
			entity.setId(Long.parseLong(id));
			super.getFacade().getStatisticalDimensionTimeService().modifyStatisticalDimensionTime(entity);
			saveMessage(request, "entity.updated");
		} else {// 新数据插入
			super.getFacade().getStatisticalDimensionTimeService().createStatisticalDimensionTime(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		StatisticalDimensionTime entity = new StatisticalDimensionTime();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		StatisticalDimensionTime entity = new StatisticalDimensionTime();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getStatisticalDimensionTimeService().getStatisticalDimensionTime(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			StatisticalDimensionTime entity = new StatisticalDimensionTime();
			entity.setId(Long.valueOf(id));
			getFacade().getStatisticalDimensionTimeService().removeStatisticalDimensionTime(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			StatisticalDimensionTime entity = new StatisticalDimensionTime();
			entity.getMap().put("pks", pks);
			getFacade().getStatisticalDimensionTimeService().removeStatisticalDimensionTime(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	
	//客户户龄分析表
	public ActionForward customerAgeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(50);
		String mod_id = (String) dynaBean.get("mod_id");
		String fgs_id = (String) dynaBean.get("fgs_id");// 分公司ID
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String is_sdf = (String) dynaBean.get("is_sdf");// R3分类
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String joindate_s = (String) dynaBean.get("joindate_s");// 加盟时间
		String joindate_e = (String) dynaBean.get("joindate_e");// 加盟时间
		String joindatetype_like = (String) dynaBean.get("joindatetype_like");// 加盟年限
		String excel_all = (String) dynaBean.get("excel_all");// 导出
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		StatisticalDimensionData entity = new StatisticalDimensionData();
		entity.setIs_del(0);// 默认只统计未停用的客户
		if (StringUtils.isNotBlank(joindatetype_like)) {
			entity.getMap().put("joindatetype_like", joindatetype_like);
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
		if (StringUtils.isNotBlank(joindate_s)) {
			entity.getMap().put("joindate_s", joindate_s+" 00:00:00");
		}
        if (StringUtils.isNotBlank(joindate_e)) {
			entity.getMap().put("joindate_e", joindate_e+" 23:59:59");
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

		Long recordCount = getFacade().getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForCustomerAgeCount(entity);
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
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户户龄分析统计表")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<StatisticalDimensionData> entityList1 = super.getFacade()
					.getStatisticalDimensionDataService()
					.getStatisticalDimensionDataForCustomerAgePaginatedList(entity);
			// List<TestUser> entityList1 =
			// super.getFacade().getTestUserService().getTestUserList(entity);
			request.setAttribute("entityList1", entityList1);
			return new ActionForward("/admin/StatisticalDimensionData/customer_age_list_Report.jsp");
		}

		List<StatisticalDimensionData> entityList = super.getFacade()
				.getStatisticalDimensionDataService()
				.getStatisticalDimensionDataForCustomerAgePaginatedList(entity);

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

		return new ActionForward("/admin/StatisticalDimensionData/customer_age_list.jsp");
	}
}
