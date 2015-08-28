package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.ano.SupervisorService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderPlan;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaOrderPlanStatementAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.channellist(mapping, form, request, response);
	}
	
	public String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47", "48", "49",
			"50", "55", "57", "58", "60", "65", "84" };
	
	public ActionForward channellist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String plan_year = (String) dynaBean.get("plan_year")	;// 计划年份
		String plan_month = (String) dynaBean.get("plan_month");// 计划月份
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		
		// 判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaOrderPlan entity = new KonkaOrderPlan();
		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
        if (StringUtils.isNotBlank(plan_year) && GenericValidator.isInt(plan_year)) {
			entity.getMap().put("plan_year", plan_year);
		}else {
			Calendar calendar = Calendar.getInstance();
			int year_now = calendar.get(Calendar.YEAR);
			entity.getMap().put("plan_year", year_now);
			dynaBean.set("plan_year", year_now);

		}
        if (StringUtils.isNotBlank(plan_month) && GenericValidator.isInt(plan_month)) {
        	entity.getMap().put("plan_month", plan_month);
		}else {
			Calendar calendar = Calendar.getInstance();
			int month_now = calendar.get(Calendar.MONTH) + 1;//calendar出来的月份是从0开始的
				if(month_now <10){
					entity.getMap().put("plan_month", "0"+month_now);
					dynaBean.set("plan_month", "0"+month_now);
			}else{
			entity.getMap().put("plan_month", month_now);
			dynaBean.set("plan_month", month_now);
			}
		}
    	
        // 添加客户类型筛选条件
		if (StringUtils.isNotBlank(category_id2)) {
			entity.getMap().put("category_id2", category_id2);
		} else {
			if (StringUtils.isNotBlank(category_id1)) {
				entity.getMap().put("category_id1", category_id1);
			}
		}
		// 添加部门类型筛选条件
//		if (StringUtils.isNotBlank(category_id2)) {
//			entity.getMap().put("category_id2", category_id2);
//		} else {
//			if (StringUtils.isNotBlank(category_id1)) {
//				entity.getMap().put("category_id1", category_id1);
//			}
//		}
        
        // 检查权限
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
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
		
//		// 分公司
//		KonkaDept kd = new KonkaDept();
//		if (max_dlevel == 9) {
//			kd.setDept_id(0L);
//		} else {
//			kd.setDept_id(ui.getDept_id());
//		}
//		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
//		entity.getMap().put("dept_name", kd.getDept_name());
//		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
//		
		// 分页
		Long recordCount = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForChannelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
        //查询
		List<KonkaOrderPlan> entityList = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForChannelPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		return new ActionForward("/admin/KonkaOrderPlanStatement/channel_list.jsp");
	}
	
	public ActionForward customerlist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String plan_year = (String) dynaBean.get("plan_year");// 计划年份
		String plan_month = (String) dynaBean.get("plan_month");// 计划月份
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String pd_id = (String) dynaBean.get("model_id"); //机型
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 一级部门
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 二级级部门
		String r3_code_like = (String) dynaBean.get("r3_code_like"); //客户编码
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		
		
		// 判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaOrderPlan entity = new KonkaOrderPlan();
		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
        if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("l4_dept_id", l4_dept_id);
		}
        if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("l5_dept_id", l5_dept_id);
		}
        //默认当前年
        if (StringUtils.isNotBlank(plan_year) && GenericValidator.isInt(plan_year)) {
        	entity.getMap().put("plan_year", plan_year);
		}else {
			Calendar calendar = Calendar.getInstance();
			int year_now = calendar.get(Calendar.YEAR);
			entity.getMap().put("plan_year", plan_year);
			dynaBean.set("plan_year", year_now);

		}
        //默认当前月
        if (StringUtils.isNotBlank(plan_month) && GenericValidator.isInt(plan_month)) {
        	entity.getMap().put("plan_month" ,plan_month);
		}else {
			Calendar calendar = Calendar.getInstance();
			int month_now = calendar.get(Calendar.MONTH) + 1 ;//calendar出来的月份是从0开始的
			
			if(month_now <10){
			//	entity.setPlan_month(month_now);
				entity.getMap().put("plan_month","0"+month_now);
				dynaBean.set("plan_month","0"+month_now);
		}else{
			//entity.setPlan_month(month_now);
			entity.getMap().put("plan_month",month_now);
			dynaBean.set("plan_month", month_now);
		}
		}
        
        if (StringUtils.isNotBlank(pd_id)&& GenericValidator.isLong(pd_id)) {
			entity.setPd_id(Long.valueOf(pd_id));
		}
        if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
        if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
        // 添加客户类型筛选条件
		if (StringUtils.isNotBlank(category_id2)) {
			entity.getMap().put("category_id2", category_id2);
		} else {
			if (StringUtils.isNotBlank(category_id1)) {
				entity.getMap().put("category_id1", category_id1);
			}
		}
		// 添加部门类型筛选条件
//		if (StringUtils.isNotBlank(category_id2)) {
//			entity.getMap().put("category_id2", category_id2);
//		} else {
//			if (StringUtils.isNotBlank(category_id1)) {
//				entity.getMap().put("category_id1", category_id1);
//			}
//		}
		// 检查权限
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
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
		
		// 分公司
//		KonkaDept kd = new KonkaDept();
//		if (max_dlevel == 9) {
//			kd.setDept_id(0L);
//		} else {
//			kd.setDept_id(ui.getDept_id());
//		}
//		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
//		entity.getMap().put("dept_name", kd.getDept_name());
//		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		
		// 分页
		Long recordCount = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
        //查询
		List<KonkaOrderPlan> entityList = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForCustomerPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		
		
		return new ActionForward("/admin/KonkaOrderPlanStatement/customer_list.jsp");
	}
	
	public ActionForward modelslist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String plan_year = (String) dynaBean.get("plan_year");// 计划年份
		String plan_month = (String) dynaBean.get("plan_month");// 计划月份
		String category_id1 = (String) dynaBean.get("v_customer_type1");// 一级客户类型
		String category_id2 = (String) dynaBean.get("v_customer_type2");// 二级客户类型
		String pd_name = (String) dynaBean.get("pd_name"); //机型
		String r3_code_like = (String) dynaBean.get("r3_code_like"); //R3客户编码
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String series_name_like = (String) dynaBean.get("series_name_like"); //系列
		String pd_id = (String) dynaBean.get("model_id"); //机型
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 一级部门
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 二级级部门
		
		
		// 判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaOrderPlan entity = new KonkaOrderPlan();
		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		//默认当前年
        if (StringUtils.isNotBlank(plan_year) && GenericValidator.isInt(plan_year)) {
        	entity.setPlan_year(Integer.parseInt(plan_year));
			entity.getMap().put("plan_year", plan_year);
		}else {
			Calendar calendar = Calendar.getInstance();
			int year_now = calendar.get(Calendar.YEAR);
			entity.setPlan_year(year_now);
			entity.getMap().put("plan_year", year_now);
			dynaBean.set("plan_year", year_now);

		}
        //默认当前月
        if (StringUtils.isNotBlank(plan_month) && GenericValidator.isInt(plan_month)) {
        	entity.setPlan_month(Integer.parseInt(plan_month));
        	entity.getMap().put("plan_month", plan_month);
		}else {
			Calendar calendar = Calendar.getInstance();
			int month_now = calendar.get(Calendar.MONTH) + 1 ;//calendar出来的月份是从0开始的
			entity.setPlan_month(month_now);
			
			if(month_now <10){
				//	entity.setPlan_month(month_now);
					entity.getMap().put("plan_month","0"+month_now);
					dynaBean.set("plan_month","0"+month_now);
			}else{
				//entity.setPlan_month(month_now);
				entity.getMap().put("plan_month",month_now);
				dynaBean.set("plan_month", month_now);
			}
		
		}
        if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.getMap().put("l4_dept_id", l4_dept_id);
		}
        if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.getMap().put("l5_dept_id", l5_dept_id);
		}
        if (StringUtils.isNotBlank(pd_id) && GenericValidator.isLong(pd_id)) {
			entity.setPd_id(Long.valueOf(pd_id));
		}
        if (StringUtils.isNotBlank(series_name_like)) {
			entity.getMap().put("series_name_like", series_name_like);
		}
        if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
        if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
        // 添加客户类型筛选条件
		if (StringUtils.isNotBlank(category_id2)) {
			entity.getMap().put("category_id2", category_id2);
		} else {
			if (StringUtils.isNotBlank(category_id1)) {
				entity.getMap().put("category_id1", category_id1);
			}
		}
		// 添加部门类型筛选条件
//		if (StringUtils.isNotBlank(category_id2)) {
//			entity.getMap().put("category_id2", category_id2);
//		} else {
//			if (StringUtils.isNotBlank(category_id1)) {
//				entity.getMap().put("category_id1", category_id1);
//			}
//		}
		// 检查权限
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
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
		
		
		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(ui.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);
		
		// 分页
		Long recordCount = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForModelsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
        //查询
		List<KonkaOrderPlan> entityList = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanStatementForModelsPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/KonkaOrderPlanStatement/models_list.jsp");
	}
	
	/*
	 * 产品型号
	 */
	public ActionForward getModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String size_id = (String) dynaBean.get("size_id");
		if (StringUtils.isNotEmpty(size_id)) {

			// String[] cls_ids = { category_id };
			// List<PePdModel> pePdModelList = super.getFacade()
			// .getRetailMsBaseService().getKonkaPePdModelListByClsIds(
			// cls_ids);
			//
			List<String> dataList = new ArrayList<String>();
			PePdModel t = new PePdModel();
			t.getMap().put("led_name_like", size_id);
			List<PePdModel> tList = new ArrayList<PePdModel>();
			tList = super.getFacade().getPePdModelService().getPePdModelList(t);
			for (PePdModel _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getMd_name(), "\",\"",
						String.valueOf(_t.getPd_id()), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}
	
	
}
