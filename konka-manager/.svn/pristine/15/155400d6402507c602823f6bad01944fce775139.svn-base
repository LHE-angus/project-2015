package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileVisitPlan;
import com.ebiz.mmt.domain.KonkaMobileVisitPlanDetail;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class KonkaMobileVisitPlanAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("is_del", "0");
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));

		String begin_year = (String) dynaBean.get("begin_year");//
		String end_year = (String) dynaBean.get("end_year");//
		String begin_month = (String) dynaBean.get("begin_month");// 
		String end_month = (String) dynaBean.get("end_month");// 

		String r3_code = (String) dynaBean.get("r3_code");
		String shop_id = (String) dynaBean.get("shop_id");// 
		String plan_desc = (String) dynaBean.get("plan_desc");//
		String excel_all = (String) dynaBean.get("excel_all");//
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_name_like = (String) dynaBean.get("subcomp_name_like");// 分公司
		String isfirst = (String) dynaBean.get("isfirst");// 第一次进来
		String is_del = (String) dynaBean.get("is_del");// 是否删除
		String data_source = (String) dynaBean.get("data_source");// 数据源
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		KonkaMobileVisitPlan entity = new KonkaMobileVisitPlan();

		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		/*if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(userInfo.getId());
		} else {*/

			Long _dept_id = 0L;
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
					// entity.getMap().put("fgs_dept_value", _dept_id);
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

		//}
		/*
		 * Long user_id = userInfo.getId();
		 * BranchAssign branchAssign = new BranchAssign();
		 * branchAssign.setUser_id(user_id);
		 * List<BranchAssign> branchAssignList = super.getFacade().getBranchAssignService()
		 * .getBranchAssignAndKonkaR3ShopList(branchAssign);
		 */
		userInfo.getMap().put("default", "all");
		// 通过用户找终端
		List<KonkaR3Shop> custList = getcust(userInfo);

		// 通过用户找终端
		/*
		 * KonkaR3Store store = new KonkaR3Store();
		 * store.getMap().put("user_id", userInfo.getId());
		 * store.getMap().put("ywy_job_id", userInfo.getJob_id());
		 * List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
		 * store);
		 */
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);
		request.setAttribute("storeList", storeList);
		request.setAttribute("custList", custList);
		if (StringUtils.isEmpty(isfirst)) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			// calendar.set(Calendar.DAY_OF_MONTH,
			// calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			String beginStr =  calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + "01";
			entity.getMap().put("time_start", beginStr);
			dynaBean.set("begin_year", ""+calendar.get(Calendar.YEAR));
			dynaBean.set("begin_month", "" + (calendar.get(Calendar.MONTH)+1));
			
			int endday=this.day(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1));
			String endStr = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + endday;
			dynaBean.set("end_year", ""+calendar.get(Calendar.YEAR));
			dynaBean.set("end_month", "" + (calendar.get(Calendar.MONTH)+1));
			entity.getMap().put("time_end", endStr);
		    entity.setIs_del(0);
		    dynaBean.set("is_del", "0");
		}else{
			// 开始时间
			if (StringUtils.isNotBlank(begin_year) && StringUtils.isNotBlank(begin_month)) {
				String beginStr = "";
				Integer yearI = Integer.valueOf(begin_year);
				dynaBean.set("begin_year", begin_year);

				Integer monthI = Integer.valueOf(begin_month);
				dynaBean.set("begin_month", "" + begin_month);
				beginStr = yearI + "-" + monthI + "-" + "01";
				entity.getMap().put("time_start", beginStr);
			}
			// 结束时间
			if (StringUtils.isNotBlank(end_year) && StringUtils.isNotBlank(end_month)) {
				String endStr = "";
				Integer yearI = Integer.valueOf(end_year);
				Integer monthI = Integer.valueOf(end_month);
				dynaBean.set("end_year", end_year);
				dynaBean.set("end_month", "" + end_month);

				String day = "" + KonkaMobileVisitPlanAction.day(yearI, monthI);
				endStr = yearI + "-" + monthI + "-" + day;
				entity.getMap().put("time_end", endStr);
			}
		}
		
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
			dynaBean.set("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_id", shop_id);
			dynaBean.set("shop_id", shop_id);
		}
		if ((StringUtils.isBlank(r3_code) || StringUtils.isBlank(shop_id))
				&& !((StringUtils.isBlank(r3_code) && StringUtils.isBlank(shop_id)))) {
			entity.getMap().put("cust_shop", 1);
		}
		if (StringUtils.isNotBlank(r3_code) && StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_cust", 1);
		}
		if (StringUtils.isNotBlank(plan_desc)) {
			entity.setPlan_desc(plan_desc);
		}
		if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", report_nae_like);
			dynaBean.set("report_nae_like", report_nae_like);
		}
		if (StringUtils.isNotBlank(subcomp_name_like)) {
			entity.getMap().put("subcomp_name_like", subcomp_name_like);
			dynaBean.set("subcomp_name_like", subcomp_name_like);
		}
        if(StringUtils.isNotBlank(is_del)){
        	entity.setIs_del(Integer.valueOf(is_del));
        	dynaBean.set("is_del", is_del);
        }
        if(StringUtils.isNotBlank(data_source)){
        	entity.setData_source(Integer.valueOf(data_source));
        	dynaBean.set("data_source", data_source);
        }
		List<KonkaMobileVisitPlan> entityList = null;
		Long recordCount = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			// Long recordCount1 =
			// super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileCustVisitAcountPaginatedListCount(entity);
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("拜访计划报表数据") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaMobileVisitPlan> entityList1 = super.getFacade().getKonkaMobileVisitPlanService()
					.getKonkaMobileVisitPlanLBPaginatedList(entity);

			request.setAttribute("allList", entityList1);
			Integer count = entityList1.size();
			if (count != null && count > 0) {
				return new ActionForward("/admin/KonkaMobileVisitPlan/plan_report_list.jsp");
			} else {
				// super.renderJavaScript(response, "alert('没有数据要导出！');history.back();");
				return null;
			}
		}
		entityList = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBPaginatedList(entity);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		// 拿分公司
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
		
		return mapping.findForward("list");
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		userInfo.getMap().put("state", 1);
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);

		// 默认显示当前1月的时间区间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String year=df.format(today);
		df = new SimpleDateFormat("MM");
		String month=df.format(today);

		Map<String, Object> allmap = new HashMap<String, Object>();
		allmap.put("year", year);
		allmap.put("month", month);

		// 位置信息
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

		// 当前用户信息
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		int max_dlevel = super.getMaxDLevel(ui.getId());
		if (max_dlevel == 9) {
			allmap.put("dept_id", "");
		} else {
			allmap.put("dept_id", ui.getDept_id());
		}
		
		List<Map<String, Object>> custListMap=new ArrayList<Map<String,Object>>();
		Map<String, Object> temp=new HashMap<String, Object>();
		temp.put("customer_code", "");
		temp.put("customer_name", "-请选择-");
		custListMap.add(temp);
		if (null!=custList) {
			 for (KonkaR3Shop konkaR3Shop : custList) {
			    	custListMap.add(konkaR3Shop.getMap());
			}
			allmap.put("custList", custListMap);
		}
		KonkaR3Store tempshop=new KonkaR3Store();
		tempshop.setStore_id(0L);
		tempshop.setStore_name("-请选择-");
		if (null!=storeList) {
			allmap.put("storeList", storeList);
		}
		allmap.put("is_del", "0");
		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}
	
	public ActionForward list1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = String.valueOf(dynaBean.get("mod_id"));

		String begin_year = (String) dynaBean.get("begin_year");//
		String end_year = (String) dynaBean.get("end_year");//
		String begin_month = (String) dynaBean.get("begin_month");// 
		String end_month = (String) dynaBean.get("end_month");// 

		String r3_code = (String) dynaBean.get("r3_code");
		String shop_id = (String) dynaBean.get("shop_id");// 
		String plan_desc = (String) dynaBean.get("plan_desc");//
		String excel_all = (String) dynaBean.get("excel_all");//
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_name_like = (String) dynaBean.get("subcomp_name_like");// 分公司
		String is_del = (String) dynaBean.get("is_del");// 是否删除
		String data_source = (String) dynaBean.get("data_source");// 数据源
		String page =(String) dynaBean.get("page");
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		KonkaMobileVisitPlan entity = new KonkaMobileVisitPlan();

		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setAdd_user_id(userInfo.getId());
		} else {

			Long _dept_id = 0L;
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
					// entity.getMap().put("fgs_dept_value", _dept_id);
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
		
		// 开始时间
		if (StringUtils.isNotBlank(begin_year) && StringUtils.isNotBlank(begin_month)) {
			String beginStr = "";
			Integer yearI = Integer.valueOf(begin_year);
			dynaBean.set("begin_year", begin_year);

			Integer monthI = Integer.valueOf(begin_month);
			dynaBean.set("begin_month", "" + begin_month);
			beginStr = yearI + "-" + monthI + "-" + "01";
			entity.getMap().put("time_start", beginStr);
		}
		// 结束时间
		if (StringUtils.isNotBlank(end_year) && StringUtils.isNotBlank(end_month)) {
			String endStr = "";
			Integer yearI = Integer.valueOf(end_year);
			Integer monthI = Integer.valueOf(end_month);
			dynaBean.set("end_year", end_year);
			dynaBean.set("end_month", "" + end_month);

			String day = "" + KonkaMobileVisitPlanAction.day(yearI, monthI);
			endStr = yearI + "-" + monthI + "-" + day;
			entity.getMap().put("time_end", endStr);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
			dynaBean.set("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_id", shop_id);
			dynaBean.set("shop_id", shop_id);
		}
		if ((StringUtils.isBlank(r3_code) || StringUtils.isBlank(shop_id))
				&& !((StringUtils.isBlank(r3_code) && StringUtils.isBlank(shop_id)))) {
			entity.getMap().put("cust_shop", 1);
		}
		if (StringUtils.isNotBlank(r3_code) && StringUtils.isNotBlank(shop_id)) {
			entity.getMap().put("shop_cust", 1);
		}
		if (StringUtils.isNotBlank(plan_desc)) {
			entity.setPlan_desc(plan_desc);
		}

		if (StringUtils.isNotBlank(report_nae_like)) {
			entity.getMap().put("report_nae_like", report_nae_like);
			dynaBean.set("report_nae_like", report_nae_like);
		}
		if (StringUtils.isNotBlank(subcomp_name_like)) {
			entity.getMap().put("subcomp_name_like", subcomp_name_like);
			dynaBean.set("subcomp_name_like", subcomp_name_like);
		}
		 if(StringUtils.isNotBlank(is_del)){
	        	entity.setIs_del(Integer.valueOf(is_del));
	        	dynaBean.set("is_del", is_del);
        }
        if(StringUtils.isNotBlank(data_source)){
        	entity.setData_source(Integer.valueOf(data_source));
        	dynaBean.set("data_source", data_source);
        }
		List<KonkaMobileVisitPlan> entityList = null;
		Long recordCount = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBCount(entity);
		
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ EncryptUtils.encodingFileName("拜访计划报表数据") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaMobileVisitPlan> entityList1 = super.getFacade().getKonkaMobileVisitPlanService()
					.getKonkaMobileVisitPlanLBPaginatedList(entity);

			request.setAttribute("allList", entityList1);
			Integer count = entityList1.size();
			if (count != null && count > 0) {
				return new ActionForward("/admin/KonkaMobileVisitPlan/plan_report_list.jsp");
			} else {
				// super.renderJavaScript(response, "alert('没有数据要导出！');history.back();");
				return null;
			}
		}
		
		pager.init(recordCount, pager.getPageSize(), page);
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		entityList = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBPaginatedList(entity);
		for (KonkaMobileVisitPlan konkaMobileVisitPlan : entityList) {
			konkaMobileVisitPlan.getMap().put("plan_begin_date", df.format(konkaMobileVisitPlan.getPlan_begin_date()));
			konkaMobileVisitPlan.getMap().put("add_date",df1.format(konkaMobileVisitPlan.getAdd_date()));
			if (null!=konkaMobileVisitPlan&&null!=konkaMobileVisitPlan.getUpdate_date()) {
				konkaMobileVisitPlan.getMap().put("update_date",
						df1.format(konkaMobileVisitPlan.getUpdate_date()));
			}
		}
		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if (entityList == null) {
			String[] str = {};
			m.put("rows", str);
		} else {
			m.put("rows", entityList);
		}
		m.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
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
		if (!flag) {// 说明不是业务员，不给他进页面
			// super.renderHtml(response, "该用户（" + userInfo.getReal_name() + "）不是业务员！");
			// return null;
		}

		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) + 1;
		dynaBean.set("year", now.get(Calendar.YEAR));
		dynaBean.set("month", month < 10 ? "0" + month : month);

		// 通过用户找终端
		/*
		 * Long user_id = userInfo.getId();
		 * BranchAssign branchAssign = new BranchAssign();
		 * branchAssign.setUser_id(user_id);
		 * List<BranchAssign> branchAssignList = super.getFacade().getBranchAssignService()
		 * .getBranchAssignAndKonkaR3ShopList(branchAssign);
		 */
		// 通过用户找终端
		List<KonkaR3Shop> custList = getcust(userInfo);

		// 通过用户找终端
		/*
		 * KonkaR3Store store = new KonkaR3Store();
		 * store.getMap().put("user_id", userInfo.getId());
		 * store.getMap().put("ywy_job_id", userInfo.getJob_id());
		 * List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
		 * store);
		 */
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);
		request.setAttribute("storeList", storeList);
		request.setAttribute("custList", custList);

		request.setAttribute("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		return mapping.findForward("input");
	}
	
	public ActionForward add1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) + 1;
		allmap.put("year", now.get(Calendar.YEAR));
		allmap.put("month", month < 10 ? "0" + month : month);

		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);

		List<Map<String, Object>> custListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> custtemp=null;
		if (null != custList) {
			for (KonkaR3Shop konkaR3Shop : custList) {
				custtemp=new HashMap<String, Object>();
				custtemp.put("customer_code", konkaR3Shop.getMap().get("customer_code")+"#"+konkaR3Shop.getMap().get("customer_name"));
				custtemp.put("customer_name", konkaR3Shop.getMap().get("customer_name"));
				custListMap.add(custtemp);
			}
			allmap.put("custList", custListMap);
		}
		List<Map<String, Object>> shopListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> shoptemp=null;
		if (null != storeList) {
			for (KonkaR3Store konkaR3Store : storeList) {
				shoptemp=new HashMap<String, Object>();
				shoptemp.put("store_id", konkaR3Store.getStore_id()+"#"+konkaR3Store.getStore_name());
				shoptemp.put("store_name", konkaR3Store.getStore_name());
				shopListMap.add(shoptemp);	
			}
			allmap.put("storeList", shopListMap);
		}
		allmap.put("add_date", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		
		
		// 转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start + 1, end + 1));
		out.flush();
		out.close();
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String plan_id = (String) dynaBean.get("plan_id");// 
		String mod_id = (String) dynaBean.get("mod_id");//
		// plan
		String data_source = (String) dynaBean.get("data_source");// 
		String plan_of_access = (String) dynaBean.get("plan_of_access");// 计划拜访量
		String plan_of_access_cust = (String) dynaBean.get("plan_of_access_cust");// 计划拜访客户数量
		String plan_of_access_shop = (String) dynaBean.get("plan_of_access_shop");// 计划拜访终端数量
		String plan_of_dev_cust = (String) dynaBean.get("plan_of_dev_cust");// 计划开拓数量
		String plan_task_money = (String) dynaBean.get("plan_task_money");// 计划任务额
		String plan_task_count = (String) dynaBean.get("plan_task_count");// 计划任务量
		String plan_desc = (String) dynaBean.get("plan_desc");// 计划说明
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");// 
		// planDetail
		String[] r3_code_name = request.getParameterValues("multiselect_r3_code_name");// 客户
		String[] shop_id_name = request.getParameterValues("multiselect_shop_id_name");// 终端

		KonkaMobileVisitPlan plan = new KonkaMobileVisitPlan();
		Integer yearI = Integer.valueOf(year);
		Integer monthI = Integer.valueOf(month);
		String day = "" + KonkaMobileVisitPlanAction.day(yearI, monthI);

		Date begin = sdf.parse(year + "-" + month + "-" + "01");
		String beginStr = year + "-" + month + "-" + "01" + " 00:00:00";
		Date end = sdf.parse(year + "-" + month + "-" + day);
		String endStr = year + "-" + month + "-" + day + " 00:00:00";
		plan.setPlan_begin_date(begin);
		plan.setPlan_end_date(end);
		plan.getMap().put("beginStr", beginStr);
		plan.getMap().put("endStr", endStr);
		plan.getMap().put("add_user_id", userInfo.getId());

		// 修改权限
		/*boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 分公司管理员
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				flag = true;
			}
			// 总经理
			if (peRoleUser.getRole_id().equals(new Long(24701))) {
				flag = true;
			}
		}*/
		/**
		 * 修改
		 */
		
		if (StringUtils.isNotBlank(plan_id)) {
			// 系统当前时间
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
				super.getFacade().getKonkaSxOperLogService()
						.createKonkaSxOperLog(userInfo, plan_id,
								"KONKA_MOBILE_VISIT_PLAN",
								request.getLocalAddr(), "修改拜访计划");
			} else {
				super.renderJavaScript(response,
						"alert('只能在10号之前修改计划！');history.back();");
				//return null;
			}
		} else {// 添加
			Long numb = super.getFacade().getKonkaMobileVisitPlanService()
					.getCurrentMonthVisitCount(plan);
			if (numb > 0) {
				super.renderJavaScript(response,
						"alert('当前月已经做过计划了！');history.back();");
				return null;
			}
		}

		  if ((null==r3_code_name||r3_code_name.length<0) && (null==shop_id_name||shop_id_name.length<0)) {
		  super.renderJavaScript(response, "alert('拜访客户和拜访终端必须选一项！');history.back();");
		  return null;
		  }

		if (StringUtils.isNotBlank(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
		}
		if (StringUtils.isBlank(data_source)) {
			plan.setData_source(2);
		} else {
			plan.setData_source(Integer.valueOf(data_source));
		}
		if (StringUtils.isNotBlank(plan_of_access)) {
			plan.setPlan_of_access(Integer.valueOf(plan_of_access));
		}else{
			plan.setPlan_of_access(Integer.valueOf(0));
		}
		if (StringUtils.isNotBlank(plan_of_access_cust)) {
			plan.setPlan_of_access_cust(Integer.valueOf(plan_of_access_cust));
		}else {
			plan.setPlan_of_access_cust(Integer.valueOf(0));
		}
		if (StringUtils.isNotBlank(plan_of_access_shop)) {
			plan.setPlan_of_access_shop(Integer.valueOf(plan_of_access_shop));
		}else {
			plan.setPlan_of_access_shop(Integer.valueOf(0));
		}
		if (StringUtils.isNotBlank(plan_of_dev_cust)) {
			plan.setPlan_of_dev_cust(Integer.valueOf(plan_of_dev_cust));
		}else {
			plan.setPlan_of_dev_cust(Integer.valueOf(0));
		}
		if (StringUtils.isNotBlank(plan_task_money)) {
			BigDecimal task_money = new BigDecimal(plan_task_money);
			plan.setPlan_task_money(task_money);
		}else {
			BigDecimal task_money = new BigDecimal(0);
			plan.setPlan_task_money(task_money);
		}
		if (StringUtils.isNotBlank(plan_task_count)) {
			plan.setPlan_task_count(Long.valueOf(plan_task_count));
		}else {
			plan.setPlan_task_count(Long.valueOf(0));
		}
		if (StringUtils.isNotBlank(plan_desc)) {
			plan.setPlan_desc(plan_desc);
		}
		// 查找当前记录的创建人
		KonkaMobileVisitPlan creatR = new KonkaMobileVisitPlan();
		// 修改
		if (StringUtils.isNotBlank(plan_id)) {
			creatR.setPlan_id(Long.valueOf(plan_id));
			creatR = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(creatR);

			// 如果穿件记录的人是当前登陆的人则可以修改用户人信息
			if (null != userInfo && null != userInfo.getId() && creatR.getAdd_user_id().equals(userInfo.getId())) {
				plan.setAdd_user_id(userInfo.getId());
				if (StringUtils.isNotBlank(userInfo.getReal_name())) {
					plan.setReport_nae(userInfo.getReal_name());
				}
				if (null != userInfo.getDept_id()) {
					plan.setDept_id(userInfo.getDept_id());
				}
			}
		} else {
			plan.setAdd_user_id(userInfo.getId());
			if (StringUtils.isNotBlank(userInfo.getReal_name())) {
				plan.setReport_nae(userInfo.getReal_name());
			}
			if (null != userInfo.getDept_id()) {
				plan.setDept_id(userInfo.getDept_id());
			}
		}
		plan.setData_source(2);// web端
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept.getMap().put("dept_type_eq", 3);
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);

		if (null != konkaDept && null != konkaDept.getDept_id()) {
			plan.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}

		


		List<KonkaMobileVisitPlanDetail> detaillst = new ArrayList<KonkaMobileVisitPlanDetail>();
		KonkaMobileVisitPlanDetail detailentity = null;
		if (null!=r3_code_name&&r3_code_name.length>0) {
			for (int i = 0; i < r3_code_name.length; i++) {
				String r3codeTemp[]=r3_code_name[i].split("#");
				detailentity = new KonkaMobileVisitPlanDetail();
				detailentity.setR3_code(r3codeTemp[0]);
				detailentity.setCustomer_name(r3codeTemp[1]);
				detailentity.setVisit_date(new Date());
				detaillst.add(detailentity);
			}
		}
		if (null!=shop_id_name&&shop_id_name.length>0) {
			for (int i = 0; i < shop_id_name.length; i++) {
				String shopidTemp[]=shop_id_name[i].split("#");
				String shop_id=shopidTemp[0];
				detailentity = new KonkaMobileVisitPlanDetail();
				detailentity.setShop_id(Long.valueOf(shop_id));
				detailentity.setShop_name(shopidTemp[1]);
				detailentity.setVisit_date(new Date());
				if (shop_id.endsWith("191919")) {
					detailentity.setCustomer_type(Long.valueOf(""+10));
				}
				detaillst.add(detailentity);
			}
		}
		plan.setDetail(detaillst);
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setUpdate_user_id(userInfo.getId());
			plan.setUpdate_date(new Date());
			super.getFacade().getKonkaMobileVisitPlanService().modifyKonkaMobileVisitPlanJL(plan);
		} else {
			plan.setAdd_date(new Date());
			super.getFacade().getKonkaMobileVisitPlanService().createKonkaMobileVisitPlanJL(plan);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> allmap = new HashMap<String, Object>();
		String plan_id = (String) dynaBean.get("plan_id");// 
		String mod_id = (String) dynaBean.get("mod_id");//
		// plan
		String data_source = (String) dynaBean.get("data_source");// 
		String plan_of_access = (String) dynaBean.get("plan_of_access");// 计划拜访量
		String plan_of_access_cust = (String) dynaBean.get("plan_of_access_cust");// 计划拜访客户数量
		String plan_of_access_shop = (String) dynaBean.get("plan_of_access_shop");// 计划拜访终端数量
		String plan_of_dev_cust = (String) dynaBean.get("plan_of_dev_cust");// 计划开拓数量
		String plan_task_money = (String) dynaBean.get("plan_task_money");// 计划任务额
		String plan_task_count = (String) dynaBean.get("plan_task_count");// 计划任务量
		String plan_desc = (String) dynaBean.get("plan_desc");// 计划说明
		String year = (String) dynaBean.get("year");//
		String month = (String) dynaBean.get("month");// 
		// planDetail
		String[] r3_code_name = request.getParameterValues("r3_code_name");// 客户
		String[] shop_id_name = request.getParameterValues("shop_id_name");// 终端
		KonkaMobileVisitPlan plan = new KonkaMobileVisitPlan();
		Integer yearI = Integer.valueOf(year);
		Integer monthI = Integer.valueOf(month);
		String day = "" + KonkaMobileVisitPlanAction.day(yearI, monthI);

		Date begin_date = sdf.parse(year + "-" + month + "-" + "01");
		String beginStr = year + "-" + month + "-" + "01" + " 00:00:00";
		Date end_date = sdf.parse(year + "-" + month + "-" + day);
		String endStr = year + "-" + month + "-" + day + " 00:00:00";
		plan.setPlan_begin_date(begin_date);
		plan.setPlan_end_date(end_date);
		plan.getMap().put("beginStr", beginStr);
		plan.getMap().put("endStr", endStr);
		plan.getMap().put("add_user_id", userInfo.getId());

		// 修改权限
		/*boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 分公司管理员
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				flag = true;
			}
			// 总经理
			if (peRoleUser.getRole_id().equals(new Long(24701))) {
				flag = true;
			}
		}*/
		/**
		 * 修改
		 */
		if (StringUtils.isNotBlank(plan_id)) {
			// 系统当前时间
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
				super.getFacade().getKonkaSxOperLogService()
						.createKonkaSxOperLog(userInfo, plan_id,
								"KONKA_MOBILE_VISIT_PLAN",
								request.getLocalAddr(), "修改拜访计划");
			} else {
				
				allmap.put("error", "alert('只能在10号之前修改计划！');return false;");
				JSONArray jsonArray = JSONArray.fromObject(allmap);
				int start = jsonArray.toString().indexOf("[");
				int end = jsonArray.toString().lastIndexOf("}");
				String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();
				out.close();
				return null;
			}
		} else {// 添加
			Long numb = super.getFacade().getKonkaMobileVisitPlanService()
					.getCurrentMonthVisitCount(plan);
			if (numb > 0) {
				allmap.put("error", "alert('当前月已经做过计划了！');return false;");
				JSONArray jsonArray = JSONArray.fromObject(allmap);
				int start = jsonArray.toString().indexOf("[");
				int end = jsonArray.toString().lastIndexOf("}");
				String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();
				out.close();
				return null;
			}
		}

		if ((null==r3_code_name||r3_code_name.length==0) && (null==shop_id_name||shop_id_name.length==0)) {
		  super.renderJavaScript(response, "alert('拜访客户和拜访终端必须选一项！');");
		  return null;
		  }
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
		}
		if (StringUtils.isBlank(data_source)) {
			plan.setData_source(2);
		} else {
			plan.setData_source(Integer.valueOf(data_source));
		}
		if (StringUtils.isNotBlank(plan_of_access)) {
			plan.setPlan_of_access(Integer.valueOf(plan_of_access));
		}
		if (StringUtils.isNotBlank(plan_of_access_cust)) {
			plan.setPlan_of_access_cust(Integer.valueOf(plan_of_access_cust));
		}
		if (StringUtils.isNotBlank(plan_of_access_shop)) {
			plan.setPlan_of_access_shop(Integer.valueOf(plan_of_access_shop));
		}
		if (StringUtils.isNotBlank(plan_of_dev_cust)) {
			plan.setPlan_of_dev_cust(Integer.valueOf(plan_of_dev_cust));
		}
		if (StringUtils.isNotBlank(plan_task_money)) {
			BigDecimal task_money = new BigDecimal(plan_task_money);
			plan.setPlan_task_money(task_money);
		}
		if (StringUtils.isNotBlank(plan_task_count)) {
			plan.setPlan_task_count(Long.valueOf(plan_task_count));
		}
		if (StringUtils.isNotBlank(plan_desc)) {
			plan.setPlan_desc(plan_desc);
		}
		// 查找当前记录的创建人
		KonkaMobileVisitPlan creatR = new KonkaMobileVisitPlan();
		// 修改
		if (StringUtils.isNotBlank(plan_id)) {
			creatR.setPlan_id(Long.valueOf(plan_id));
			creatR = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(creatR);

			// 如果穿件记录的人是当前登陆的人则可以修改用户人信息
			if (null != userInfo && null != userInfo.getId() && creatR.getAdd_user_id().equals(userInfo.getId())) {
				plan.setAdd_user_id(userInfo.getId());
				if (StringUtils.isNotBlank(userInfo.getReal_name())) {
					plan.setReport_nae(userInfo.getReal_name());
				}
				if (null != userInfo.getDept_id()) {
					plan.setDept_id(userInfo.getDept_id());
				}
			}
		} else {
			plan.setAdd_user_id(userInfo.getId());
			if (StringUtils.isNotBlank(userInfo.getReal_name())) {
				plan.setReport_nae(userInfo.getReal_name());
			}
			if (null != userInfo.getDept_id()) {
				plan.setDept_id(userInfo.getDept_id());
			}
		}
		plan.setData_source(2);// web端
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(userInfo.getDept_id());
		konkaDept.getMap().put("dept_type_eq", 3);
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);

		if (null != konkaDept && null != konkaDept.getDept_id()) {
			plan.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}
		plan.setAdd_date(new Date());
		List<KonkaMobileVisitPlanDetail> detaillst = new ArrayList<KonkaMobileVisitPlanDetail>();
		
		
		KonkaMobileVisitPlanDetail detailentity = null;
		if (null!=r3_code_name&&r3_code_name.length!=0) {
			for (int i = 0; i < r3_code_name.length; i++) {
				String[] r3codeTemp = r3_code_name[i].split("#");
				detailentity = new KonkaMobileVisitPlanDetail();
				detailentity.setR3_code(r3codeTemp[0]);
				detailentity.setCustomer_name(r3codeTemp[1]);
				detailentity.setVisit_date(new Date());
				detaillst.add(detailentity);
			}
		}
		if (null!=shop_id_name&&shop_id_name.length!=0) {
			for (int i = 0; i < shop_id_name.length; i++) {
				String[] shopidTemp = shop_id_name[i].split("#");
				String shop_id=shopidTemp[0];
				detailentity = new KonkaMobileVisitPlanDetail();
				detailentity.setShop_id(Long.valueOf(shop_id));
				detailentity.setShop_name(shopidTemp[1]);
				detailentity.setVisit_date(new Date());
				if (shop_id.endsWith("191919")) {
					detailentity.setCustomer_type(Long.valueOf(""+10));
				}
				detaillst.add(detailentity);
			}
		}
		plan.setDetail(detaillst);
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setUpdate_user_id(userInfo.getId());
			plan.setUpdate_date(new Date());
			super.getFacade().getKonkaMobileVisitPlanService().modifyKonkaMobileVisitPlanJL(plan);
			allmap.put("res", plan_id);
			allmap.put("Msg", "修改成功");
		} else {
		   Long add_plan_id=super.getFacade().getKonkaMobileVisitPlanService().createKonkaMobileVisitPlanJL(plan);
		   allmap.put("res", add_plan_id);
		   allmap.put("Msg", "添加成功");
		}
		// 封装成JSON字符串
		
		allmap.put("mod_id", mod_id);
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	private static int day(int year, int month) {
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
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天
		 * calendar.getActualMaximum(int field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy#MM");
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return this.list(mapping, form, request, response);
		}

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
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
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);
		request.setAttribute("storeList", storeList);
		request.setAttribute("custList", custList);

		KonkaMobileVisitPlan plan = new KonkaMobileVisitPlan();
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
			plan.getRow().setFirst(0);
			plan.getRow().setCount(1);
		}

		if (StringUtils.isNotBlank(plan_id)) {
			plan = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(plan);
			KonkaMobileVisitPlanDetail plandetail = new KonkaMobileVisitPlanDetail();
			plandetail.setPlan_id(Long.valueOf(plan_id));
			List<KonkaMobileVisitPlanDetail> details = super.getFacade().getKonkaMobileVisitPlanDetailService()
					.getKonkaMobileVisitPlanDetailList(plandetail);
			String R3customerStr = "";
			String shopStr = "";
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : details) {
				
				if (konkaMobileVisitPlanDetail.getR3_code()!=null) {
					R3customerStr += konkaMobileVisitPlanDetail.getR3_code()+"#"+konkaMobileVisitPlanDetail.getCustomer_name()
							+ ",";
				}
				if (konkaMobileVisitPlanDetail.getShop_id()!=null) {
					shopStr += konkaMobileVisitPlanDetail.getShop_id()+"#"+konkaMobileVisitPlanDetail.getShop_name() + ",";
				}
			}
			dynaBean.set("R3customerStr", R3customerStr);
			dynaBean.set("shopStr", shopStr);
			if (null!=plan&&null!=plan.getAdd_date()) {
				request.setAttribute("today", DateFormatUtils.format(plan
						.getAdd_date(), "yyyy-MM-dd"));
			}
		}
		String yymm = sdf1.format(plan.getPlan_begin_date());
		String[] yymm1 = yymm.split("#");
		String year = yymm1[0];
		String month = yymm1[1];

		this.copyProperties(dynaBean, plan);
		dynaBean.set("year", year);
		dynaBean.set("month", month);
		dynaBean.set("mod_id", mod_id);
		return mapping.findForward("input");
	}
	public ActionForward edit1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return this.list(mapping, form, request, response);
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
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
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);
		
		List<Map<String, Object>> custListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> custtemp=null;

		if (null != custList) {
			for (KonkaR3Shop konkaR3Shop : custList) {
				custtemp=new HashMap<String, Object>();
				custtemp.put("customer_code", konkaR3Shop.getMap().get("customer_code")+"#"+konkaR3Shop.getMap().get("customer_name"));
				custtemp.put("customer_name", konkaR3Shop.getMap().get("customer_name"));
				custListMap.add(custtemp);
			}
			allmap.put("custList", custListMap);
		}
		List<Map<String, Object>> shopListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> shoptemp=null;
		if (null != storeList) {
			for (KonkaR3Store konkaR3Store : storeList) {
				shoptemp=new HashMap<String, Object>();
				shoptemp.put("store_id", konkaR3Store.getStore_id()+"#"+konkaR3Store.getStore_name());
				shoptemp.put("store_name", konkaR3Store.getStore_name());
				shopListMap.add(shoptemp);	
			}
			allmap.put("storeList", shopListMap);
		}
		KonkaMobileVisitPlan plan = new KonkaMobileVisitPlan();
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
			plan.getRow().setFirst(0);
			plan.getRow().setCount(1);
		}

		if (StringUtils.isNotBlank(plan_id)) {
			plan = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(plan);
			KonkaMobileVisitPlanDetail plandetail = new KonkaMobileVisitPlanDetail();
			plandetail.setPlan_id(Long.valueOf(plan_id));
			List<KonkaMobileVisitPlanDetail> details = super.getFacade().getKonkaMobileVisitPlanDetailService()
					.getKonkaMobileVisitPlanDetailList(plandetail);
			String R3codeStr = "";
			String shopStr = "";
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : details) {
				
				if (null!=konkaMobileVisitPlanDetail.getR3_code()&&null!=konkaMobileVisitPlanDetail.getCustomer_name()) {
					R3codeStr += konkaMobileVisitPlanDetail.getR3_code() + "#"
							+ konkaMobileVisitPlanDetail.getCustomer_name()
							+ ",";
				}
				if (null!=konkaMobileVisitPlanDetail.getShop_id()&&null!=konkaMobileVisitPlanDetail.getShop_name()) {
					shopStr += konkaMobileVisitPlanDetail.getShop_id() + "#"
							+ konkaMobileVisitPlanDetail.getShop_name() + ",";
				}
			}
			String R3codeArry[]=R3codeStr.split(",");
			String shopArry[]=shopStr.split(",");
			allmap.put("r3codeArry", R3codeArry);
			allmap.put("shopidArry", shopArry);
		}
		String yymm = DateFormatUtils.format(new Date(), "yyyy#MM");
		String[] yymm1 = yymm.split("#");
		String year = yymm1[0];
		String month = yymm1[1];

		this.copyProperties(dynaBean, plan);
		allmap.put("year", year);
		allmap.put("month", month);
		allmap.put("plan", plan);
		allmap.put("mod_id", mod_id);
		allmap.put("add_date", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return null;
		}
		KonkaMobileVisitPlan plan = null;
		if (StringUtils.isNotBlank(plan_id)) {
			plan = new KonkaMobileVisitPlan();
			plan.setPlan_id(Long.valueOf(plan_id));
			plan.getRow().setFirst(Integer.valueOf(0));
			plan.getRow().setCount(Integer.valueOf(1));
			plan = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(plan);
			/*
			 * List<KonkaMobileVisitPlan> entityList =
			 * super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlanLBPaginatedList(plan);
			 * if (null!=entityList&&entityList.size()==1) {
			 * plan=entityList.get(0);
			 * }
			 */

			List<KonkaMobileVisitPlanDetail> details = null;
			if (null != plan.getPlan_id()) {
				KonkaMobileVisitPlanDetail plandetail = new KonkaMobileVisitPlanDetail();
				plandetail.setPlan_id(Long.valueOf(plan_id));
				details = super.getFacade().getKonkaMobileVisitPlanDetailService().getKonkaMobileVisitPlanDetailList(
						plandetail);
			}

			Map map = null;
			List<Map> custList = new ArrayList<Map>();
			List<Map> storeList = new ArrayList<Map>();
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : details) {
				if (null != konkaMobileVisitPlanDetail.getR3_code()) {
					map = new HashMap();
					map.put("id", konkaMobileVisitPlanDetail.getR3_code());
					map.put("name", konkaMobileVisitPlanDetail.getCustomer_name());
					custList.add(map);
				}
				if (null != konkaMobileVisitPlanDetail.getShop_id()) {
					map = new HashMap();
					map.put("id", konkaMobileVisitPlanDetail.getShop_id());
					map.put("name", konkaMobileVisitPlanDetail.getShop_name());
					storeList.add(map);
				}
			}
			request.setAttribute("custList", custList);
			request.setAttribute("storeList", storeList);
		}
		this.copyProperties(dynaBean, plan);
		dynaBean.set("mod_id", mod_id);
		request.setAttribute("plan_begin_date", DateFormatUtils.format(plan.getPlan_begin_date(), "yyyy年MM月"));
		request.setAttribute("today", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		return mapping.findForward("view");
	}
	public ActionForward view1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return this.list(mapping, form, request, response);
		}
		Map<String, Object> allmap = new HashMap<String, Object>();
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
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
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo);
		
		List<Map<String, Object>> custListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> custtemp=null;

		if (null != custList) {
			for (KonkaR3Shop konkaR3Shop : custList) {
				custtemp=new HashMap<String, Object>();
				custtemp.put("customer_code", konkaR3Shop.getMap().get("customer_code")+"#"+konkaR3Shop.getMap().get("customer_name"));
				custtemp.put("customer_name", konkaR3Shop.getMap().get("customer_name"));
				custListMap.add(custtemp);
			}
			allmap.put("custList", custListMap);
		}
		List<Map<String, Object>> shopListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> shoptemp=null;
		if (null != storeList) {
			for (KonkaR3Store konkaR3Store : storeList) {
				shoptemp=new HashMap<String, Object>();
				shoptemp.put("store_id", konkaR3Store.getStore_id()+"#"+konkaR3Store.getStore_name());
				shoptemp.put("store_name", konkaR3Store.getStore_name());
				shopListMap.add(shoptemp);	
			}
			allmap.put("storeList", shopListMap);
		}
		KonkaMobileVisitPlan plan = new KonkaMobileVisitPlan();
		if (StringUtils.isNotBlank(plan_id)) {
			plan.setPlan_id(Long.valueOf(plan_id));
			plan.getRow().setFirst(0);
			plan.getRow().setCount(1);
		}

		if (StringUtils.isNotBlank(plan_id)) {
			plan = super.getFacade().getKonkaMobileVisitPlanService().getKonkaMobileVisitPlan(plan);
			KonkaMobileVisitPlanDetail plandetail = new KonkaMobileVisitPlanDetail();
			plandetail.setPlan_id(Long.valueOf(plan_id));
			List<KonkaMobileVisitPlanDetail> details = super.getFacade().getKonkaMobileVisitPlanDetailService()
					.getKonkaMobileVisitPlanDetailList(plandetail);
			String R3codeStr = "";
			String shopStr = "";
			for (KonkaMobileVisitPlanDetail konkaMobileVisitPlanDetail : details) {
				
				if (null!=konkaMobileVisitPlanDetail.getR3_code()&&null!=konkaMobileVisitPlanDetail.getCustomer_name()) {
					R3codeStr += konkaMobileVisitPlanDetail.getR3_code() + "#"
							+ konkaMobileVisitPlanDetail.getCustomer_name()
							+ ",";
				}
				if (null!=konkaMobileVisitPlanDetail.getShop_id()&&null!=konkaMobileVisitPlanDetail.getShop_name()) {
					shopStr += konkaMobileVisitPlanDetail.getShop_id() + "#"
							+ konkaMobileVisitPlanDetail.getShop_name() + ",";
				}
			}
			String R3codeArry[]=R3codeStr.split(",");
			String shopArry[]=shopStr.split(",");
			allmap.put("r3codeArry", R3codeArry);
			allmap.put("shopidArry", shopArry);
		}
		String yymm = DateFormatUtils.format(new Date(), "yyyy#MM");
		String[] yymm1 = yymm.split("#");
		String year = yymm1[0];
		String month = yymm1[1];

		this.copyProperties(dynaBean, plan);
		allmap.put("year", year);
		allmap.put("month", month);
		allmap.put("plan", plan);
		allmap.put("mod_id", mod_id);
		allmap.put("add_date", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return this.list(mapping, form, request, response);
		}
		KonkaMobileVisitPlan entity = new KonkaMobileVisitPlan();
		if (StringUtils.isNotBlank(plan_id)) {
			entity.setPlan_id(Long.valueOf(plan_id));
		}else {
			super.renderJavaScript(response,"alert('删除主键不能为空！');history.back();");
	        return null;
		}
		super.getFacade().getKonkaMobileVisitPlanService().removeKonkaMobileVisitPlanLB(entity);
		entity.setQueryString(super.serialize(request, "plan_id", "method"));

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	public ActionForward delete1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String plan_id = (String) dynaBean.get("plan_id");
		Map<String, Object> allmap = new HashMap<String, Object>();
		if (!GenericValidator.isLong(plan_id)) {
			super.saveError(request, "errors.long", "plan_id");
			return this.list(mapping, form, request, response);
		}
		KonkaMobileVisitPlan entity = new KonkaMobileVisitPlan();
		if (StringUtils.isNotBlank(plan_id)) {
			entity.setPlan_id(Long.valueOf(plan_id));
		}
		int delcount=super.getFacade().getKonkaMobileVisitPlanService().removeKonkaMobileVisitPlanLB(entity);
		
		// 封装成JSON字符串
		allmap.put("delcount", delcount);
		allmap.put("Msg", "删除成功！");
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Shop> getcust(PeProdUser userInfo) {
		KonkaR3Shop entity = new KonkaR3Shop();

		boolean flag = false;
		boolean fgsgly = false;//分公司管理员
		boolean fgsfz = false;//分公司副总
		boolean jybjl = false;//经营部经理
		boolean bsczr = false;//办事处主任
		boolean ywzg = false;//业务主管
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(31))) {
				fgsfz = true;
			}
			
			if (peRoleUser.getRole_id().equals(new Long(40))) {
				jybjl = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(50))) {
				bsczr = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(69))) {
				ywzg = true;
			}
			
		}
		/*if (flag) {// 是业务员
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			entity.getMap().put("fgs_dept_value", "");
		} else {*/

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly&&!fgsfz) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
			case 7:
				// 我所在的部门及以下部门可见
					if (jybjl || bsczr || ywzg || flag) {
						_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
						entity.getMap().put("dept_id_start", _dept_id);
						break;
					} else {
						return null;
					}
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				//entity.getMap().put("dept_id_start", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				return null;
			}
			entity.getMap().put("session_user_id", userInfo.getId());
		//}
		if (null != userInfo && null != userInfo.getMap().get("default")) {
			entity.getMap().put("default", userInfo.getMap().get("default"));
		}
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		userInfo.getMap().put("default", null);
		return entityList;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo) {
		KonkaR3Store entity = new KonkaR3Store();

		boolean flag = false;
		boolean fgsgly = false;
		boolean fgsfz = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(31))) {
				fgsfz = true;
			}
		}
			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly&&!fgsfz) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
				// return null;
			case 7:
				// 我所在的部门及以下部门可见    经营部经理      办事处主任      业务主管
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门       业务员
				entity.getMap().put("dept_id_start", _dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
				break;
			default:
				// 出错
			}
			entity.getMap().put("session_user_id", userInfo.getId());
	
		if (null != userInfo && null != userInfo.getMap().get("default")) {
			entity.getMap().put("default", userInfo.getMap().get("default"));
		}
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);
		userInfo.getMap().put("default", null);
		return entityList;
	}

}
