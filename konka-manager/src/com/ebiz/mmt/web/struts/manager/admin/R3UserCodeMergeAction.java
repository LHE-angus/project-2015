package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * R3编码合并
 * 
 * @author ZHOU
 * @version 2013-07-09
 */ 
public class R3UserCodeMergeAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}
 
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		return mapping.findForward("input");
	}

	/**
	 * 客户分类列表 目前不考虑数据权限过滤
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		String customer_code = (String) dynaBean.get("customer_code");
		String customer_name = (String) dynaBean.get("customer_name");
		Pager pager = (Pager) dynaBean.get("pager");

		// 目前不考虑数据过滤
		// 当前用户
		// PeProdUser userInfo = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);
		// 当前用户的角色
		// 当前用户所属分公司id
		// Long user_dept_id = userInfo.getDept_id();

		KonkaR3Shop entity = new KonkaR3Shop();
		
		entity.getMap().put("r3_code_and_customer_code_is_not_eq", "not_empty");
		
		entity.setIs_del(new Long(0));
		if (r3_code != null && r3_code.length() >= 0) {
			entity.getMap().put("r3_code_like", r3_code.trim());// 用于模糊查询
		}
		if (customer_code != null && customer_code.length() >= 0) {
			entity.getMap().put("customer_code_like", customer_code.trim());// 合并编码
		}
		if (customer_name != null && customer_name.length() >= 0) {
			entity.getMap().put("customer_name_like", customer_name.trim());// 客户名称
		}

		// 取总数
		Long recordCount = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);

		// 分页信息
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 取数据
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = (String[]) dynaBean.get("pks");

		logger.info("pks=========>{}", pks.toString());
		logger.info("pks=========>{}", StringUtils.join(pks, ",").toString());

		if (!ArrayUtils.isEmpty(pks)) {
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.getMap().put("selects", StringUtils.join(pks, ","));
			List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService()
					.getKonkaR3ShopList(konkaR3Shop);
			// 选中的客户
			request.setAttribute("konkaR3ShopList", konkaR3ShopList);
			
		} 
		return mapping.findForward("input");
		
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 最终要处理的客户IDS
		String ids = (String) dynaBean.get("id_values");
		// 合并R/3编码
		String customer_code = (String) dynaBean.get("customer_code");
		//前台没有处理 , 
		String[] customer_ids = StringUtils.split(ids, ",");
		logger.info("customer_code=======>{}", customer_code.toString());
		logger.info("customer_ids=======>{}", customer_ids.toString());
		if(StringUtils.isBlank(customer_code)){
			String msg = super.getMessage(request, "customer_code 为空,请联系技术人员!");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		if(customer_ids.length <= 0){
			String msg = super.getMessage(request, "customer_ids 为空,请联系技术人员!");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// true 代表合并编码已经使用过,直接返回
		if(this.isExistCustomerCode(customer_code)) return null; 
		//去重复
		Set<String> set = new HashSet<String>();
		for(String s :customer_ids){
			set.add(s);
		}
//		for(String s :set){
//			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
//			konkaR3Shop.setId(Long.valueOf(s));
//			konkaR3Shop.setCustomer_code(customer_code);
//			super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(konkaR3Shop);
//		}
		//此处用super好. 不要用this 虽然this也可以.
		//1.明确指定是父类的方法,那样编译器解析的时候会更快.因为如果不指定的时候,它是先找当前类,再找父类.父类没有的时候报错
		//2.代码阅读性好.
		super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopBatch(customer_code, set);
		
		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward isExistCustomerCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean  = (DynaBean) form;
		//合并编码
		String v_customer_code = (String)dynaBean.get("v_customer_code");
		
		boolean isExist = this.isExistCustomerCode(v_customer_code);
		 
		logger.info("json string : {}", JSON.toJSONString(isExist));
		//如果重复为true 否则为 false
		super.renderJson(response, JSON.toJSONString(isExist));
		
		return null ;
	}
	
	public ActionForward getCustomerListByName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		String customer_name = (String) dynaBean.get("v_customer_name");
		
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setIs_del(new Long(0));
		if (customer_name != null && customer_name.length() >= 0) {
			entity.getMap().put("customer_name_like", customer_name.trim());// 客户名称
		}
		// 取数据
		List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);
	
		logger.info("size============{}",konkaR3ShopList.size());
		super.renderJson(response, JSON.toJSONString(konkaR3ShopList));
		
		return null ;
	}
	
	private boolean isExistCustomerCode(String customer_code){
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setCustomer_code(customer_code);
		long count  = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(konkaR3Shop);
		boolean isExist = (count>0) ? true:false;
		return isExist;
	}
	
	
	//***********改版方法**************************************************/
	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-08-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
		
		//当前日期
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String day_last = df.format(today);
		allmap.put("c_date", day_last);
		
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
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//验证权限
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.noPersission(request,response);
//		}
		
		KonkaR3Shop entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getKonkaR3ShopService().getMergeR3ShopCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaR3ShopService().getMergeR3ShopList(entity);
		}
		
		//交易客户，即“客户数”汇总
		Long custCount = super.getFacade().getKonkaR3ShopService().getMergeR3ShopcustCount(entity);
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		m.put("custCount", custCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
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
	
	/**
	 * 导出数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("合并客户列表")
		        + ".xls");
		KonkaR3Shop entity = getEntiy(mapping, form, request);
		Long recordCount = super.getFacade().getKonkaR3ShopService().getMergeR3ShopCount(entity);
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());
		List<HashMap> entityList1 = super.getFacade().getKonkaR3ShopService().getMergeR3ShopList(entity);
		request.setAttribute("allList", entityList1);
		return mapping.findForward("view");
	}
	
	/**
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public KonkaR3Shop  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		
		String page = request.getParameter("page");
		
		//分公司编码
		String dept_id = request.getParameter("dept_id");
		
		//合并编码
		String merge_code = request.getParameter("merge_code");
		
		//R3编码
		String r3_code = request.getParameter("r3_code");
		
		//合并客户名称
		String merge_name = request.getParameter("merge_name");
		
		//一级客户类型
		String merge_type1 = request.getParameter("cus_type1");
		
		//二级客户类型
		String merge_type2 = request.getParameter("cus_type2");
		
		//业务员
		String ywy_name = request.getParameter("ywy_name");
		
		//是否停用，0-未停用        1-已停用
		String is_stop = request.getParameter("is_stop");
		
		//加盟时间
		String date_begin = request.getParameter("date_begin");
		String date_end = request.getParameter("date_end");
		
		//R3分类
		String is_sdf = request.getParameter("is_sdf");
		
		//客户状态
		String cust_stat = request.getParameter("cust_stat");
		
		//所在区域
		String area_name = request.getParameter("area_name");
		
		//事业部
		String branch_name = request.getParameter("branch_name");
		
		KonkaR3Shop entity = new KonkaR3Shop();

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(merge_code)) {
			entity.setCustomer_code(merge_code);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(merge_name)) {
			entity.setCustomer_name(merge_name);
		}
		
		if (StringUtils.isNotBlank(merge_type2)) {
			entity.setCustomer_type(merge_type2);
		}else{
			if (StringUtils.isNotBlank(merge_type1)) {
				entity.getMap().put("cust_type1", merge_type1);
			}
		}
		if (StringUtils.isNotBlank(ywy_name)) {
			entity.getMap().put("ywy_name", ywy_name);
		}
		if (StringUtils.isNotBlank(is_stop)) {
			entity.setIs_del(Long.valueOf(is_stop));
		}
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (GenericValidator.isInt(is_sdf)) {
			entity.setIs_sdf(Integer.valueOf(is_sdf));
		}
		if (StringUtils.isNotBlank(cust_stat)) {
			entity.setShop_status(cust_stat);
		}
		if (StringUtils.isNotBlank(area_name)) {
			entity.setArea_name(area_name);
		}
		if (StringUtils.isNotBlank(branch_name)) {
			entity.setBranch_name(branch_name);
		}
		
		entity.getMap().put("page", page);
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
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
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		
		return entity;
	}
	
	
	/**
	 * 网点停用/启用
	 * @author Angus
	 * @date 2014-9-2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stopOrStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.noPersission(request,response);
		}

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String flag = (String) dynaBean.get("flag");
		
		int result = 1;

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			try{
				if("stop".equals(flag)){
					PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

					// 插入停用记录表
					KonkaR3Shop kShop = new KonkaR3Shop();
					kShop.setId(new Long(id));
					kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

					if (null != kShop) {
						KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
						k.setAdd_date(new Date());
						k.setAdd_user_id(ui.getId());
						k.setAdd_user_job_id(ui.getJob_id());
						k.setAdd_user_name(ui.getUser_name());
						k.setC_type(40);
						k.setChange_info("该客户被停用!");
						k.setSs_id(kShop.getId());
						k.setSs_name(kShop.getCustomer_name());
						super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
					}

					KonkaR3Shop entity = new KonkaR3Shop();
					entity.setId(new Long(id));
					entity.setIs_del(1l);
					getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);

					// 停止用户信息
					String c_ids = id;
					JBasePartner jbp = new JBasePartner();
					jbp.setC_id(Long.valueOf(id));
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}

					PeProdUser pUser = new PeProdUser();
					// pUser.setCust_id(Long.valueOf(id));
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(1);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
				}else{
					// 启用用户信息
					PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

					// 插入启用记录表
					KonkaR3Shop kShop = new KonkaR3Shop();
					kShop.setId(new Long(id));
					kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

					if (null != kShop) {
						KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
						k.setAdd_date(new Date());
						k.setAdd_user_id(ui.getId());
						k.setAdd_user_job_id(ui.getJob_id());
						k.setAdd_user_name(ui.getUser_name());
						k.setC_type(40);
						k.setChange_info("该客户被启用!");
						k.setSs_id(kShop.getId());
						k.setSs_name(kShop.getCustomer_name());
						super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
					}

					KonkaR3Shop entity = new KonkaR3Shop();
					entity.setId(new Long(id));
					entity.setIs_del(0L);
					getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);

					// 启用用户信息
					String c_ids = id;
					JBasePartner jbp = new JBasePartner();
					jbp.setC_id(Long.valueOf(id));
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}

					PeProdUser pUser = new PeProdUser();
					// pUser.setCust_id(Long.valueOf(id));
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(0);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
				}
			}catch (Exception e) {
				result = 0;
			}
		}else{
			result = 0;
		}

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", result);
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

}