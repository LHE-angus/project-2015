package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileVisitPlan;
import com.ebiz.mmt.domain.KonkaMobileVisitPlanDetail;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 拜访类型基础数据
 * @author Administrator
 *
 */
public class KonkaMobileVisitPlanAction extends BaseAction {
	
	/**
	 * 终端
	 */
	public ActionForward storeList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 通过用户找终端
		KonkaR3Store store = new KonkaR3Store();
		store.getMap().put("user_id", userInfo.getId());
		store.getMap().put("ywy_job_id", userInfo.getJob_id());
		List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
				store);
        
		super.renderJson(response, JSON.toJSONString(storeList));
		return null;
	}
	
	/**
	 * 客户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward branchAssignList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		    DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");

		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		Long user_id = userInfo.getId();
		BranchAssign branchAssign = new BranchAssign();
		branchAssign.setUser_id(user_id);
		List<BranchAssign> branchAssignList = super.getFacade().getBranchAssignService()
				.getBranchAssignAndKonkaR3ShopList(branchAssign);
		super.renderJson(response, JSON.toJSONString(branchAssignList));
		return null;
	}
	
	/**
	 * 拜访列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");
		
		String plan_id=(String) dynaBean.get("plan_id");
		
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		String begin_date = (String) dynaBean.get("begin_date");
		String end_date = (String) dynaBean.get("end_date");
		String r3_code = (String) dynaBean.get("r3_code");
		String shop_id = (String) dynaBean.get("shop_id");// 
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		KonkaMobileVisitPlan entity = new KonkaMobileVisitPlan();
		
		if(StringUtils.isNotBlank(plan_id) && GenericValidator.isLong(plan_id)){
			entity.setPlan_id(Long.valueOf(plan_id));
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		 if(flag){//如果是业务员，只能看到自己的拜访记录
	        	entity.setAdd_user_id(userInfo.getId());
			}else{

				
				Long _dept_id=0L;
				int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
				logger.info("Max level : {}", max_dlevel);
				switch (max_dlevel) {
				case 9:
					// 集团及以下部门可见
					_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
					break;
				case 8:
					// 分公司及以下部门可见
					
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						 entity.getMap().put("dept_id_start", _dept_id);
					//	entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				case 7:
					// 我所在的部门及以下部门可见
					_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
					entity.getMap().put("dept_id_start", _dept_id);
					break;
				case 0:
					_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
					// entity.getMap().put("dept_id_start", __dept_id);
					entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
					break;
				default:
					// 出错
				}
				entity.getMap().put("session_user_id", userInfo.getId());
				
			}
		
		if (StringUtils.isNotBlank(begin_date)) {
			entity.getMap().put("time_start", begin_date);
			dynaBean.set("time_start", begin_date);
		}
		if (StringUtils.isNotBlank(end_date)) {
			entity.getMap().put("time_end", end_date);
			dynaBean.set("time_end", end_date);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
			dynaBean.set("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_id", shop_id);
			dynaBean.set("shop_id", shop_id);
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", report_nae_like);
		}
		List<KonkaMobileVisitPlan> entityList = null;
		Long recordCount = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		
		//entity.getRow().setFirst(pager.getFirstRow());
		//entity.getRow().setCount(pager.getRowCount());
	    
		//没有传入起始行默认第一行开始
		if (StringUtils.isNotBlank(first)&&StringUtils.isNumeric(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		}else{
			entity.getRow().setFirst(0);
		}
		//没有传入查看几行默认查看起始行后面所有行数
		if (StringUtils.isNotBlank(count)&&StringUtils.isNumeric(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		}else{
			entity.getRow().setCount(recordCount.intValue());
		}
		
		entity.setAdd_user_id(userInfo.getId());
		
		entityList = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBPaginatedList(entity);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		for (KonkaMobileVisitPlan konkaMobileVisitPlan : entityList) {
			map = new HashMap();
			
			map.put("plan_id", konkaMobileVisitPlan.getPlan_id() == null ? "" : konkaMobileVisitPlan.getPlan_id());
			map.put("plan_type", konkaMobileVisitPlan.getPlan_type() == null ? "" : konkaMobileVisitPlan.getPlan_type());
			map.put("report_nae", konkaMobileVisitPlan.getReport_nae() == null ? "" : konkaMobileVisitPlan.getReport_nae());
			map.put("data_source", konkaMobileVisitPlan.getData_source() == null ? "" : konkaMobileVisitPlan.getData_source());
			map.put("ywy_job_id", konkaMobileVisitPlan.getYwy_job_id() == null ? "" : konkaMobileVisitPlan.getYwy_job_id());
			map.put("is_del", konkaMobileVisitPlan.getIs_del() == null ? "" : konkaMobileVisitPlan.getIs_del());
			map.put("plan_of_access", konkaMobileVisitPlan.getPlan_of_access() == null ? "" : konkaMobileVisitPlan.getPlan_of_access());
			map.put("plan_of_access_cust", konkaMobileVisitPlan.getPlan_of_access_cust() == null ? "" : konkaMobileVisitPlan.getPlan_of_access_cust());
			map.put("plan_of_dev_cust", konkaMobileVisitPlan.getPlan_of_dev_cust() == null ? "" : konkaMobileVisitPlan.getPlan_of_dev_cust());
			map.put("plan_task_money", konkaMobileVisitPlan.getPlan_task_money() == null ? "" : konkaMobileVisitPlan.getPlan_task_money());
			map.put("plan_task_count", konkaMobileVisitPlan.getPlan_task_count() == null ? "" : konkaMobileVisitPlan.getPlan_task_count());
			map.put("plan_desc", konkaMobileVisitPlan.getPlan_desc() == null ? "" : konkaMobileVisitPlan.getPlan_desc());
			map.put("report_nae", konkaMobileVisitPlan.getReport_nae() == null ? "" : konkaMobileVisitPlan.getReport_nae());
			map.put("plan_of_access_shop", konkaMobileVisitPlan.getPlan_of_access_shop() == null ? "" : konkaMobileVisitPlan.getPlan_of_access_shop());
			
			map.put("dept_name", konkaMobileVisitPlan.getMap().get("dept_name") == null ? "" : konkaMobileVisitPlan.getMap().get("dept_name"));
			map.put("subcomp_name", konkaMobileVisitPlan.getMap().get("subcomp_name") == null ? "" : konkaMobileVisitPlan.getMap().get("subcomp_name"));
			
			map.put("customer_ids", konkaMobileVisitPlan.getMap().get("customer_ids") == null ? "" : konkaMobileVisitPlan.getMap().get("customer_ids"));
			map.put("shop_ids", konkaMobileVisitPlan.getMap().get("shop_ids") == null ? "" : konkaMobileVisitPlan.getMap().get("shop_ids"));
			map.put("customer_names", konkaMobileVisitPlan.getMap().get("customer_names") == null ? "" : konkaMobileVisitPlan.getMap().get("customer_names"));
			map.put("shop_names", konkaMobileVisitPlan.getMap().get("shop_names") == null ? "" : konkaMobileVisitPlan.getMap().get("shop_names"));
			
			if (null != konkaMobileVisitPlan.getAdd_date()) {
				String add_dateStr = sdf.format(konkaMobileVisitPlan
						.getAdd_date());
				map.put("add_date", add_dateStr == null ? "" : add_dateStr);
			}
			if (null != konkaMobileVisitPlan.getUpdate_date()) {
				String update_dateStr = sdf.format(konkaMobileVisitPlan
						.getUpdate_date());
				map.put("update_date", update_dateStr == null ? "" : update_dateStr);
			}
			if (null != konkaMobileVisitPlan.getPlan_begin_date()) {
				String begin_date_Str = sdf.format(konkaMobileVisitPlan
						.getPlan_begin_date());
				map.put("plan_begin_date", begin_date_Str == null ? ""
						: begin_date_Str);
			}
			if (null != konkaMobileVisitPlan.getPlan_end_date()) {
				String end_date_Str = sdf.format(konkaMobileVisitPlan
						.getPlan_end_date());
				map.put("plan_end_date", end_date_Str == null ? ""
						: end_date_Str);
			}
			KonkaMobileVisitPlanDetail plandetail=new KonkaMobileVisitPlanDetail();
			plandetail.setPlan_id(konkaMobileVisitPlan.getPlan_id());
			List<KonkaMobileVisitPlanDetail> listdetail= super.getFacade().getKonkaMobileVisitPlanDetailService().getKonkaMobileVisitPlanDetailList(plandetail);
			map.put("detail", listdetail == null ? "": listdetail);
			list.add(map);
			
		/*	KonkaMobileVisitPlanDetail plandetail=new KonkaMobileVisitPlanDetail();
			plandetail.setPlan_id(konkaMobileVisitPlan.getPlan_id());
			String add_dateStr=sdf.format(konkaMobileVisitPlan.getAdd_date());
			String begin_date_Str= sdf.format(konkaMobileVisitPlan.getPlan_begin_date());
            String end_date_Str=sdf.format(konkaMobileVisitPlan.getPlan_end_date());
            konkaMobileVisitPlan.getMap().put("add_date", add_dateStr);
            konkaMobileVisitPlan.getMap().put("begin_date", begin_date_Str);
            konkaMobileVisitPlan.getMap().put("end_date", end_date_Str);
			 List<KonkaMobileVisitPlanDetail> listdetail= super.getFacade().getKonkaMobileVisitPlanDetailService().getKonkaMobileVisitPlanDetailList(plandetail);
			 konkaMobileVisitPlan.setDetail(listdetail);*/
		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}
	/**
	 * 添加修改保存拜访计划
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");

		// 时间
		String start_date = (String) dynaBean.get("start_date");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 用户名或者密码不存在， 资讯信息类别为公开
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		String result="操作异常";
		if (null == userInfo) {
			result ="用户验证错误，请重新登录后提交";
			super.renderJson(response, JSON.toJSONString(result));
		}

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String plan_id = (String) dynaBean.get("plan_id");// 
		//plan
		String plan_of_access = (String) dynaBean.get("plan_of_access");// 计划拜访量
		String plan_of_access_cust = (String) dynaBean.get("plan_of_access_cust");// 计划拜访客户数量
		String plan_of_access_shop = (String) dynaBean.get("plan_of_access_shop");//计划拜访终端数量 
		String plan_of_dev_cust = (String) dynaBean.get("plan_of_dev_cust");// 计划开拓数量
		String plan_task_money = (String) dynaBean.get("plan_task_money");//计划任务额
		String plan_task_count = (String) dynaBean.get("plan_task_count");// 计划任务量
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");// 
		String plan_desc = (String) dynaBean.get("plan_desc");//
		//planDetail
		String r3_code=(String)dynaBean.get("r3_code");
		String shop_id=(String)dynaBean.get("shop_id");
		String customer_name=(String)dynaBean.get("customer_name");
		String shop_name=(String)dynaBean.get("shop_name");
		
		if (StringUtils.isNotBlank(year)&&StringUtils.isNumeric(year)) {
			
		}else {
			result ="年度参数错误";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		
		if (StringUtils.isNotBlank(month)&&StringUtils.isNumeric(month)) {
			
		}	else {
			result ="月份参数错误";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
			
		KonkaMobileVisitPlan plan=new KonkaMobileVisitPlan();
		//控制一个月只能报一次计划
		Integer yearI = Integer.valueOf(year);
		Integer monthI = Integer.valueOf(month);
		String day=""+KonkaMobileVisitPlanAction.day(yearI,monthI);
		
		Date begin= sdf.parse(year+"-"+month+"-"+"01");
		String beginStr=year+"-"+month+"-"+"01"+" 00:00:00";
		Date end= sdf.parse(year+"-"+month+"-"+day);
		String endStr=year+"-"+month+"-"+day+" 00:00:00";
		plan.setPlan_begin_date(begin);
		plan.setPlan_end_date(end);
		plan.getMap().put("beginStr", beginStr);
		plan.getMap().put("endStr", endStr);
		plan.getMap().put("add_user_id", userInfo.getId());
	    
	  //修改权限
	   /* boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			//分公司管理员
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				flag = true;
			}
			//总经理
			if (peRoleUser.getRole_id().equals(new Long(24701))) {
				flag = true;
			}
		}*/
		 /**
	     * 修改
	     */
	    if (StringUtils.isNotBlank(plan_id)) {
	    	//系统当前时间
	        Calendar cal = Calendar.getInstance();
	        if (cal.get(Calendar.DAY_OF_MONTH)>=10) {
				result="只能在10号之前修改计划！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
		}else{//添加
			Long numb= super.getFacade().getKonkaMobileVisitPlanService().getCurrentMonthVisitCount(plan);
		    if (numb>0) {
		    	result="当前月已经做过计划了！";
				super.renderJson(response, JSON.toJSONString(result));
				return null;
			}
		}
		if (StringUtils.isNotBlank(plan_id)&&StringUtils.isNumeric(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
		}
		
		if (StringUtils.isNotBlank(plan_of_access)&&StringUtils.isNumeric(plan_of_access)
				&& StringUtils.isNotBlank(plan_of_access_cust)&& StringUtils.isNumeric(plan_of_access_cust)
				&& StringUtils.isNotBlank(plan_of_access_shop)&& StringUtils.isNumeric(plan_of_access_shop)) {
             if(Integer.valueOf(plan_of_access)<Integer.valueOf(plan_of_access_cust)+Integer.valueOf(plan_of_access_shop)){
         		result ="拜访次数必须大于计划客户和终端的总数！";
    			super.renderJson(response, JSON.toJSONString(result));
    			return null;        	 
            	 
             }
		}
		if (StringUtils.isNotBlank(plan_of_access)&&StringUtils.isNumeric(plan_of_access)) {
			plan.setPlan_of_access(Integer.valueOf(plan_of_access));
		}else{
			result ="计划任务量不能为空或必须为数字";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}
		
		if (StringUtils.isNotBlank(plan_of_dev_cust)&&StringUtils.isNumeric(plan_of_dev_cust)) {
			plan.setPlan_of_dev_cust(Integer.valueOf(plan_of_dev_cust));
		}/*else{
			result ="计划开拓数量不能为空或必须为数字";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}*/
		
		if (StringUtils.isBlank(r3_code)&&StringUtils.isBlank(shop_id)) {
			result ="拜访客户和拜访终端必须选一项";
			super.renderJson(response, JSON.toJSONString(result));
			return null;
		}else{
			if (StringUtils.isNotBlank(r3_code)) {
				if (StringUtils.isNotBlank(plan_of_access_cust)&&StringUtils.isNumeric(plan_of_access_cust)) {
					plan.setPlan_of_access_cust(Integer.valueOf(plan_of_access_cust));
			    }else{
			    	result ="计划拜访客户数量不能为空或必须为数字";
					super.renderJson(response, JSON.toJSONString(result));
					return null;
				}
			}
			if(StringUtils.isNotBlank(shop_id)){
				if (StringUtils.isNotBlank(plan_of_access_shop)&&StringUtils.isNumeric(plan_of_access_shop)) {
					plan.setPlan_of_access_shop(Integer.valueOf(plan_of_access_shop));
			    }else{
			    	result ="计划拜访终端数量不能为空或必须为数字";
					super.renderJson(response, JSON.toJSONString(result));
					return null;
				}
			}
		}
        if (StringUtils.isNotBlank(plan_task_money)&&StringUtils.isNumeric(plan_task_money)) {
        	BigDecimal task_money=new BigDecimal(plan_task_money);
			plan.setPlan_task_money(task_money);
		}else{
			
		}
		if (StringUtils.isNotBlank(plan_task_count)&&StringUtils.isNumeric(plan_task_count)) {
			plan.setPlan_task_count(Long.valueOf(plan_task_count));
		}else{
			
		}
		if (StringUtils.isNotBlank(plan_desc)) {
			plan.setPlan_desc(plan_desc);
		}
		
		//查找当前记录的创建人
		KonkaMobileVisitPlan creatR=new KonkaMobileVisitPlan();
		//修改
		if (StringUtils.isNotBlank(plan_id)) {
			creatR.setPlan_id(Long.valueOf(plan_id));
			creatR= super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(creatR);
		
			//如果穿件记录的人是当前登陆的人则可以修改用户人信息
			if (null != userInfo && null != userInfo.getId()
					&& Long.valueOf(creatR.getAdd_user_id()) == Long.valueOf(userInfo.getId())) {
					plan.setAdd_user_id(userInfo.getId());	
				if (StringUtils.isNotBlank(userInfo.getReal_name())) {
					plan.setReport_nae(userInfo.getReal_name());	
				}
				if (null!=userInfo.getDept_id()) {
					plan.setDept_id(userInfo.getDept_id());	
				}
			}
		}else{
			plan.setAdd_user_id(userInfo.getId());	
			if (StringUtils.isNotBlank(userInfo.getReal_name())) {
				plan.setReport_nae(userInfo.getReal_name());	
			}
			if (null!=userInfo.getDept_id()) {
				plan.setDept_id(userInfo.getDept_id());	
			}
		}
		plan.setData_source(1);//web端
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept.getMap().put("dept_type_eq", 3);
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);

		if (null != konkaDept && null != konkaDept.getDept_id()) {
			plan.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}
		plan.setAdd_date(new Date());
		if(StringUtils.isNotBlank(r3_code)){
			String []r3_code1 = r3_code.split(",");
			plan.setPlan_of_access_cust(r3_code1.length);
		}else if(StringUtils.isNotBlank(shop_id)){
			String []shop_id1 = shop_id.split(",");
			plan.setPlan_of_access_cust(shop_id1.length);
		}
		
		List<KonkaMobileVisitPlanDetail> detaillst=new ArrayList<KonkaMobileVisitPlanDetail>();
		KonkaMobileVisitPlanDetail detailentity=null;
		if (StringUtils.isNotBlank(r3_code)) {
			String[] r3code = r3_code.split(",");
			String[] cus_name = customer_name.split(",");
			for(int i=0;i<r3code.length;i++){
	        	detailentity=new KonkaMobileVisitPlanDetail();
	        	detailentity.setR3_code(r3code[i]);
	        	detailentity.setCustomer_name(cus_name[i]);
	        	detailentity.setVisit_date(new Date());
	        	detaillst.add(detailentity);
			}
		}
		if (StringUtils.isNotBlank(shop_id)) {
			String[] shopid = shop_id.split(",");
			String[] s_name = shop_name.split(",");
			for(int i=0;i<shopid.length;i++){
	        	detailentity=new KonkaMobileVisitPlanDetail();
	        	detailentity.setShop_id(Long.valueOf(shopid[i]));
	        	detailentity.setShop_name(s_name[i]);
	        	detailentity.setVisit_date(new Date());
	        	if (shopid[i].endsWith("191919")) {
					detailentity.setCustomer_type(Long.valueOf(""+10));
				}
	        	detaillst.add(detailentity);
			}
		}
		plan.setDetail(detaillst);
		
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setUpdate_user_id(userInfo.getId());
			plan.setUpdate_date(new Date());
			Long return_id = super.getFacade().getKonkaMobileVisitPlanService().modifyKonkaMobileVisitPlanJL(plan);
			result="success:"+return_id;
		} else {
			Long return_id =super.getFacade().getKonkaMobileVisitPlanService().createKonkaMobileVisitPlanJL(plan);
			result="success:"+return_id;
		}
		super.renderJson(response, result);
		return null;
	}
	private static int day(int year,int month){
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance，
		 * 以获得此类型的一个通用的对象。Calendar 的 getInstance 方法返回一
		 * 个 Calendar 对象，其日历字段已由当前日期和时间初始化： 
		 */
        Calendar calendar = Calendar.getInstance();
        /**
         * 实例化日历各个字段,这里的day为实例化使用
         */
        calendar.set(year,month - 1,day);
        /**
         * Calendar.Date:表示一个月中的某天
         * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
         */
        maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}

}
