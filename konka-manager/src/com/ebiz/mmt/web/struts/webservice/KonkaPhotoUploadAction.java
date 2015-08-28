package com.ebiz.mmt.web.struts.webservice;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KonkaPhotoUploadAction extends BaseAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return this.list(mapping, form, request, response);
	}
	
	/**
	 * 拍照类型列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		//手机端控制
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//判断session是否超时
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null==userInfo) {
			super.renderJson(response,"用户登录失败!");
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		KonkaPhotoUploadType photoType=new KonkaPhotoUploadType();
		//当前用户信息\
		KonkaDept kdDept = new KonkaDept();
		if(userInfo!=null){
			int max_dlevel = super.getMaxDLevel(userInfo.getId());
			if(max_dlevel==9){
			}else{
				if (null!=userInfo.getDept_id()) {
					kdDept.setDept_id(userInfo.getDept_id());
					kdDept = super.getFacade().getKonkaDeptService().getKonkaDept(kdDept);
					photoType.getMap().put("dept_name", kdDept.getDept_name());
				}
			}
		}

		photoType.getRow().setFirst(0);
		photoType.getRow().setCount(Integer.MAX_VALUE);
		
		List<KonkaPhotoUploadType> entitylist= super.getFacade().getKonkaPhotoUploadTypeService().getKonkaPhotoUploadTypeForDeptPaginatedList(photoType);
		for (KonkaPhotoUploadType konkaPhotoUploadType : entitylist) {
			
			if (null!=konkaPhotoUploadType.getAdd_date()) {
				konkaPhotoUploadType.getMap().put("add_date",
						df.format(konkaPhotoUploadType.getAdd_date()));
			}
			if (null!=konkaPhotoUploadType.getUpdate_date()) {
				konkaPhotoUploadType.getMap().put("update_date", konkaPhotoUploadType.getUpdate_date());
			}
		}
		// 通过用户找客户
		allmap.put("entitylist", entitylist);
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
	 * 已保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		String mod_id = (String)dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String _r3_code_like = (String)dynaBean.get("_r3_code_like");
		String _store_id_like = (String)dynaBean.get("_store_id_like");
		String _customer_name_like = (String)dynaBean.get("_customer_name_like");
		String _store_name_like = (String)dynaBean.get("_store_name_like");
		String _report_user_name_like = (String)dynaBean.get("_report_user_name_like");
		String _begin_report_date = (String) dynaBean.get("_begin_report_date");
		String _end_report_date = (String) dynaBean.get("_end_report_date");
		String _is_del = (String) dynaBean.get("_is_del");
		String dept_id = (String) dynaBean.get("_dept_id");
		String _type_id = (String) dynaBean.get("_type_id");
		
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//判断session是否超时
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null==userInfo) {
			super.renderJson(response,"用户登录失败!");
			return null;
		}
		KonkaDept kdDept = new KonkaDept();
		
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("_dept_id", dept_id);
			dynaBean.set("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(_is_del)) {
			entity.getMap().put("_is_del", _is_del);
			dynaBean.set("_is_del", _is_del);
		}
		 if (StringUtils.isNotBlank(_r3_code_like)) {
				entity.getMap().put("_r3_code_like", _r3_code_like);
				dynaBean.set("_r3_code_like", _r3_code_like);
			}
         if (StringUtils.isNotBlank(_store_id_like)) {
			entity.getMap().put("_store_id_like", _store_id_like);
			dynaBean.set("_store_id_like", _store_id_like);
		 }
		 if (StringUtils.isNotBlank(_customer_name_like)) {
			entity.getMap().put("_customer_name_like", _customer_name_like);
			dynaBean.set("_customer_name_like", _customer_name_like);
		}
        if (StringUtils.isNotBlank(_store_name_like)) {
			entity.getMap().put("_store_name_like", _store_name_like);
			dynaBean.set("_store_name_like", _store_name_like);
		}
        if (StringUtils.isNotBlank(_report_user_name_like)) {
			entity.getMap().put("_report_user_name_like", _report_user_name_like);
			dynaBean.set("_report_user_name_like", _report_user_name_like);
		}
        if(StringUtils.isNotBlank(_type_id)){
        	entity.setType_id(Long.valueOf(_type_id));
        	dynaBean.set("_type_id", _type_id);
        }
    	entity.setStatus(1);
		if (StringUtils.isNotBlank(_begin_report_date)) {
			entity.getMap().put("_begin_report_date", _begin_report_date+" 00:00:00");
			dynaBean.set("_begin_report_date", _begin_report_date);
		}
        if (StringUtils.isNotBlank(_end_report_date)) {
			entity.getMap().put("_end_report_date", _end_report_date+" 23:59:59");
			dynaBean.set("_end_report_date", _end_report_date);
		}
        
        entity.setStatus(1);//已保存
        
	    entity.setReport_user_id(userInfo.getId());//只能看着自己的
		
		//分页
        Long recordCount = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		//查询
		List<KonkaPhotoUpload> entityList = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
		 for (KonkaPhotoUpload konkaPhotoUpload : entityList) {
		    	
		    	if (null!=konkaPhotoUpload&&null!=konkaPhotoUpload.getUpdate_date()) {
					konkaPhotoUpload.getMap().put("update_date",
							df.format(konkaPhotoUpload.getUpdate_date()));
				}
				if (null!=konkaPhotoUpload&&null!=konkaPhotoUpload.getReport_date()) {
					konkaPhotoUpload.getMap().put("report_date",
							df.format(konkaPhotoUpload.getReport_date()));
				}
			}
		    allmap.put("entityList", entityList);
		    
		    //拍照类型查询条件初始化
			KonkaPhotoUploadType photoType=new KonkaPhotoUploadType();
			//当前用户信息
			if(userInfo!=null){
				int max_dlevel = super.getMaxDLevel(userInfo.getId());
				if(max_dlevel==9){
					photoType.getMap().put("dept_id", "");
				}else{
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id());
					if (null!=dept_fgs&&null!=dept_fgs.getDept_id()) {
						photoType.getMap().put("dept_id", dept_fgs.getDept_id());
					}
				}
			}
			List<Map<String, String>> photoTypes=super.getFacade().getKonkaPhotoUploadTypeService().getVisiblePhotoType(photoType);
			allmap.put("photoTypes", photoTypes);
			
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
	 * 拍照上传列表  已提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		String id = (String)dynaBean.get("id");
		String _customer_name_like = (String)dynaBean.get("_customer_name_like");//客户名称
		String _store_name_like = (String)dynaBean.get("_store_name_like");//门店名称
		String _report_user_name_like = (String)dynaBean.get("_report_user_name_like");//上报人
		String _begin_report_date = (String) dynaBean.get("_begin_report_date");//上报时间
		String _end_report_date = (String) dynaBean.get("_end_report_date");//上报时间
		String dept_id = (String) dynaBean.get("_dept_id");//分公司id
		String type_id = (String) dynaBean.get("_type_id");//拍照类型
		//手机端控制
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");
		
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//判断session是否超时
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null==userInfo) {
			super.renderJson(response,"用户登录失败!");
			return null;
		}
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotBlank(id)){
        	entity.getMap().put("id", id);
        }		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("_dept_id", dept_id);
		}
        if (StringUtils.isNotBlank(_customer_name_like)) {
			entity.getMap().put("_customer_name_like", _customer_name_like);
		}
        if (StringUtils.isNotBlank(_store_name_like)) {
			entity.getMap().put("_store_name_like", _store_name_like);
		}
        if (StringUtils.isNotBlank(_report_user_name_like)) {
			entity.getMap().put("_report_user_name_like", _report_user_name_like);
		}
        if (StringUtils.isNotBlank(_begin_report_date)) {
			entity.getMap().put("_begin_report_date", _begin_report_date+" 00:00:00");
		}
        if (StringUtils.isNotBlank(_end_report_date)) {
			entity.getMap().put("_end_report_date", _end_report_date+" 23:59:59");
		}
    	entity.setStatus(2);
        if(StringUtils.isNotBlank(type_id)){
        	entity.setType_id(Long.valueOf(type_id));
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
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			entity.setReport_user_id(userInfo.getId());
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
		
		//有值传来表示手机端调的
		if (StringUtils.isNotBlank(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		}else {
			entity.getRow().setFirst(0);
		}
		if (StringUtils.isNotBlank(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		}else {
			entity.getRow().setCount(Integer.MAX_VALUE);
		}
		//查询
		List<KonkaPhotoUpload> entityList = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
		
	    for (KonkaPhotoUpload konkaPhotoUpload : entityList) {
	    	
	    	if (null!=konkaPhotoUpload&&null!=konkaPhotoUpload.getUpdate_date()) {
				konkaPhotoUpload.getMap().put("update_date",
						df.format(konkaPhotoUpload.getUpdate_date()));
			}
			if (null!=konkaPhotoUpload&&null!=konkaPhotoUpload.getReport_date()) {
				konkaPhotoUpload.getMap().put("report_date",
						df.format(konkaPhotoUpload.getReport_date()));
			}
		}
	    allmap.put("entityList", entityList);
	    
	    //拍照类型查询条件初始化
		KonkaPhotoUploadType photoType=new KonkaPhotoUploadType();
		//当前用户信息
		if(userInfo!=null){
			int max_dlevel = super.getMaxDLevel(userInfo.getId());
			if(max_dlevel==9){
				photoType.getMap().put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id());
				if (null!=dept_fgs&&null!=dept_fgs.getDept_id()) {
					photoType.getMap().put("dept_id", dept_fgs.getDept_id());
				}
			}
		}
		List<Map<String, String>> photoTypes=super.getFacade().getKonkaPhotoUploadTypeService().getVisiblePhotoType(photoType);
		allmap.put("photoTypes", photoTypes);
		
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
	
//	拍照上传初始化数据
	public ActionForward add(ActionMapping mapping, ActionForm form,                  //新增
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		// 判断用户是否是空
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//判断session是否超时
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null==userInfo) {
			super.renderJson(response,"用户登录失败!");
			return null;
		}
		String type_id=(String) dynaBean.get("type_id");
		KonkaPhotoUploadType photoType=new KonkaPhotoUploadType();
		//当前用户信息
		if(userInfo!=null){
			int max_dlevel = super.getMaxDLevel(userInfo.getId());
			if(max_dlevel==9){
				photoType.getMap().put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id());
				if (null!=dept_fgs&&null!=dept_fgs.getDept_id()) {
					photoType.getMap().put("dept_id", dept_fgs.getDept_id());
				}
			}
			photoType.getMap().put("state", 0);
		}
		List<Map<String, String>> photoTypes=super.getFacade().getKonkaPhotoUploadTypeService().getVisiblePhotoType(photoType);
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo, 1);
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo, 1);
		if (StringUtils.isNotBlank(type_id)) {
			allmap.put("type_id", type_id);
		}
        if (null!=photoTypes) {
			allmap.put("photoTypes", photoTypes);
		}
		if (null != custList) {
			allmap.put("custList", custList);
		}
		if (null != storeList) {
			allmap.put("storeList", storeList);
		}
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
	
//	保存拍照上传数据
	public ActionForward save(ActionMapping mapping, ActionForm form,              //保存
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		DynaBean dynaBean = (DynaBean) form;
		String id = (String)dynaBean.get("id");
		String type_id = (String)dynaBean.get("type_id");
		String r3_code_name = (String)dynaBean.get("r3_code_name");
		String shop_id_name = (String)dynaBean.get("shop_id_name");
		String report_memo = (String)dynaBean.get("report_memo");
		String remark = (String)dynaBean.get("remark");
		String status = (String)dynaBean.get("status");
		String position_x = (String) dynaBean.get("position_x");// 上报人经度
		String position_y = (String) dynaBean.get("position_y");// 上报人纬度
		String addr = (String) dynaBean.get("addr");// 上报人地址
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String res = "操作异常";
		//判断session是否超时
		// 判断用户是否是空
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		//判断session是否超时
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
        if (null==userInfo) {
        	super.renderJson(response,"用户登录失败!");
			return null;
		}		
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
		VOrgOfDept t=new VOrgOfDept();
		     t.setCur_dept_id(userInfo.getDept_id());
		t=super.getFacade().getVOrgOfDeptService().getVOrgOfDept(t);
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.setType_id(Long.valueOf(type_id));
		}else {
			super.renderJson(response,"拍照类型不能为空!");
			return null;
		}
		if (null!=t&&null!=t.getDept_id()) {
		    entity.setDept_id(Long.valueOf(""+t.getDept_id()));
		}
		if (null!=t&&StringUtils.isNotBlank(t.getDept_name())) {
			entity.setDept_name(t.getDept_name());
		}
		
		if (StringUtils.isNotBlank(r3_code_name)) {
			String custtemp[]=r3_code_name.split("#");
			if (StringUtils.isNotBlank(custtemp[0])) {
				entity.setR3_code(custtemp[0]);
			}
			if (StringUtils.isNotBlank(custtemp[1])) {
				entity.setCustomer_name(custtemp[1]);
			}
		}
		if (StringUtils.isNotBlank(shop_id_name)) {
			String shoptemp[] = shop_id_name.split("#");
			if (StringUtils.isNotBlank(shoptemp[0])) {
				if (shoptemp[0].endsWith("191919")) {
					entity.setType(Long.valueOf(""+10));
				}
				entity.setStore_id(Long.parseLong(shoptemp[0]));
			}
			if (StringUtils.isNotBlank(shoptemp[1])) {
				entity.setStore_name(shoptemp[1]);
			}
		}
		if (null!=userInfo.getId()) {
			entity.setReport_user_id(userInfo.getId());
		}
		if (StringUtils.isNotBlank(userInfo.getUser_name())) {
			entity.setReport_user_name(userInfo.getUser_name());
		}
		if (StringUtils.isNotBlank(report_memo)) {
			entity.setReport_memo(URLDecoder.decode(report_memo,"UTF-8"));
		}
		if (StringUtils.isNotBlank(remark)) {
			entity.setRemark(URLDecoder.decode(remark,"UTF-8"));
		}
		if (StringUtils.isNotBlank(status)) {
			entity.setStatus(Integer.valueOf(status));
		}
		if (null!=userInfo&&null!=userInfo.getId()) {
			entity.setReport_user_id(userInfo.getId());
		}
		if (null!=userInfo&&null!=userInfo.getUser_name()) {
			entity.setReport_user_name(userInfo.getUser_name());
		}
		KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
		if (StringUtils.isNotBlank(addr) && "获取地址信息失败".equals(addr)) {
			res = "GPS定位失败！";
			super.renderJson(response, JSON.toJSONString(res));
			return null;
		} else {
			if (StringUtils.isNotBlank(position_x)) {
				gps.setPosition_x(new BigDecimal(position_x));
			}
			if (StringUtils.isNotBlank(position_y)) {
				gps.setPosition_y(new BigDecimal(position_y));
			}
			gps.setAddr(addr);
		}

		if (StringUtils.isNotBlank(position_x) && StringUtils.isNotBlank(position_x)) {
			gps.setPosition_x(new BigDecimal(position_x));
			gps.setPosition_y(new BigDecimal(position_y));
		}
		entity.setIs_del(0);
		if(StringUtils.isEmpty(id)){
			entity.setReport_date(new Date());
		//新增
		Long newid=super.getFacade().getKonkaPhotoUploadService().createKonkaPhotoUpload(entity);
		  if (null!=newid) {
			  allmap.put("id", newid);
			  allmap.put("link_table", "KONKA_PHOTO_UPLOAD");
			  allmap.put("res", "新增成功");
//			  保存gps信息
				gps.setLink_id(newid);
				gps.setLink_tab("KONKA_PHOTO_UPLOAD");
				gps.setOpr_date(new Date());
				gps.setPosition_type(0);// 0 百度地图 1 google地图 2 其他地图
				super.getFacade().getKonkaMobileCustVisitGpsService().createKonkaMobileCustVisitGps(gps);
		  }else {
			  allmap.put("res", "新增失败");
		  }
		}else{
		 int updateid=super.getFacade().getKonkaPhotoUploadService().modifyKonkaPhotoUpload(entity);
		   if(updateid>0){
			   allmap.put("id", id);
			   allmap.put("res", "修改成功！");
			   allmap.put("link_table", "KONKA_PHOTO_UPLOAD");
			   /*gps.setLink_id(Long.valueOf(id));
				gps.setLink_tab("KONKA_PHOTO_UPLOAD");
				gps.setOpr_date(new Date());
				gps.setPosition_type(0);// 0 百度地图 1 google地图 2 其他地图
				
				if (null!=gps.getLink_id()&&null!=gps.getLink_tab()) {
					super.getFacade().getKonkaMobileCustVisitGpsService()
							.modifyKonkaMobileCustVisitGps(gps);
				}*/
		   }else {
			   allmap.put("res", "修改失败！");
		   }
		}
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
	private List<KonkaR3Shop> getcust(PeProdUser userInfo, Integer report_type) {
		KonkaR3Shop entity = new KonkaR3Shop();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
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
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else

		{

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
				if (!fgsgly) {
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
		entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);

		return entityList;
	}
	
	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo, Integer report_type) {
		KonkaR3Store entity = new KonkaR3Store();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
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
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else
		{

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
				if (!fgsgly) {
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
		entity.setIs_del(0);// 未被停用的
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);

		return entityList;
	}
}
