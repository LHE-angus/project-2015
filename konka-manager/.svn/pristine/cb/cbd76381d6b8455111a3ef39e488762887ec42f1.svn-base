package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3ShopNew;
import com.ebiz.mmt.domain.KonkaR3ShopNewAtt;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.web.struts.bean.Pager;

import net.sf.json.JSONArray;

/**
 * 建户申请模块
 * @author Liang,HouEn
 * 2014-10-9
 */
public class CreateCustomerAction extends BaseAction {


	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.toAdd(mapping, form, request, response);
	}
	
	/**
	 * 初始化查询页面数据
	 * @author Liang,HouEn
	 * 2014-10-10
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
		
		//查询是否有新增权限
		if (null != super.checkUserModPopeDom(form, request, "1")) {
			allmap.put("is_add", true);
		}
		
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
		allmap.put("current_user_id", ui.getId());
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 35){//财务部权限
				allmap.put("role_id1", "35");
			}
			
			if(t.getRole_id() == 39){//财务经理权限
				allmap.put("role_id2", "39");
			}
			
			if(t.getRole_id() == 34){//总经理权限
				allmap.put("role_id3", "34");
			}
			if(t.getRole_id() == 18){//总部角色
				allmap.put("role_id4", "18");
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

	/**
	 * 查询建户申请列表
	 * @author Liang,HouEn
	 * 2014-10-9
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//验证权限
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.noPersission(request,response);
//		}
		
		KonkaR3ShopNew entity = getEntiy(mapping, form, request);

		Long recordCount = super.getFacade().getKonkaR3ShopNewService().getWaitAuditCustCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaR3ShopNewService().getWaitAuditCustList(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
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
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public KonkaR3ShopNew  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String dept_id = request.getParameter("dept_id");
		String cust_name = request.getParameter("cust_name");
		String ywy_name = request.getParameter("ywy_name");
		String record_sn = request.getParameter("record_sn");
		String cust_stat = request.getParameter("cust_stat");
		String type = request.getParameter("type");
		String role_id1 = request.getParameter("role_id1");
		String role_id2 = request.getParameter("role_id2");
		String role_id3 = request.getParameter("role_id3");
		String role_id4 = request.getParameter("role_id4");
		String page = request.getParameter("page");
		
		
        PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.setIs_del(0);

		//分公司ID
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setSubcomp_id(Long.valueOf(dept_id));
		}
		
		//客户名称
		if(StringUtils.isNotBlank(cust_name)){
			entity.getMap().put("cust_name_like", cust_name);
		}
		
		//业务员姓名
		if(StringUtils.isNotBlank(ywy_name)){
			entity.getMap().put("ywy_name_like", ywy_name);
		}
		
		//单号
		if(StringUtils.isNotBlank(record_sn)){
			entity.setRecord_sn(record_sn);
		}
		
		//建户状态
		if(StringUtils.isNotBlank(cust_stat)){
			if("4".equals(cust_stat)){
				entity.setIs_syn(1);
			}else{
				entity.setAudit_stat(Integer.valueOf(cust_stat));
			}
		}
		
		//角色id
		if(StringUtils.isNotBlank(role_id1)){
			entity.getMap().put("role1", role_id1);
		}
		if(StringUtils.isNotBlank(role_id2)){
			entity.getMap().put("role2", role_id2);
		}
		if(StringUtils.isNotBlank(role_id3)){
			entity.getMap().put("role3", role_id3);
		}
		if(StringUtils.isNotBlank(role_id4)){
			entity.getMap().put("role4", role_id4);
		}
		
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
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
		entity.getMap().put("page", page);
		
		if("wait".equals(type)){
			entity.getMap().put("wait_audit", true);
		}
		if("have".equals(type)){
			entity.getMap().put("have_audit", true);
		}
		
		return entity;
	}
	
	/**
	 * 进入表单页面，进行新增
	 * @author Liang,HouEn
	 * 2014-10-9
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String is_new = (String) dynaBean.get("is_new");
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("cust_id", cust_id);
		
		//当前日期
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(now);
		request.setAttribute("current_date", today);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		request.setAttribute("create_man", ui.getUser_name());
		//经办列表
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		Long fgs_dept_id = 0L;
		if(max_dlevel != 9){
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
			fgs_dept_id = dept_fgs.getDept_id();
		}

		KonkaDept entity = new KonkaDept();
		entity.getMap().put("fgs_dept_id", fgs_dept_id);
		request.setAttribute("dept_id", fgs_dept_id);
		List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptAndJbNameList(entity);
		request.setAttribute("jblist",entityList);
		
		// 客户类别
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 客户规模（年销售额）
		kc = new KonkaCategory();
		kc.setC_type(13);
		kc.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		if("no".equals(is_new)&&null!=cust_id){ //判断is_new为no,则是从客户开拓进入，否则为从建户申请进入
			KonkaR3ShopDev enti = new KonkaR3ShopDev();
			enti.setCust_id(Long.valueOf(cust_id));
			enti = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDev(enti);
			super.copyProperties(form, enti);
			
			if (null != enti.getEntp_p_index() && String.valueOf(enti.getEntp_p_index()).length() >= 6) {
				request.setAttribute("province", String.valueOf(enti.getEntp_p_index()).substring(0, 2) + "0000");
				request.setAttribute("city", String.valueOf(enti.getEntp_p_index()).substring(0, 4) + "00");
				request.setAttribute("country", String.valueOf(enti.getEntp_p_index()).substring(0, 6));
				request.setAttribute("town", String.valueOf(enti.getEntp_p_index()));
			}
			request.setAttribute("source_flag", "dev");
		}
		//跳转到form表单页面
		return mapping.findForward("input");
	}
	
	/**
	 * 保存建户申请
	 * @author Liang,HouEn
	 * 2014-10-9
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		super.getModPopeDom(form, request);
		String mod_id = (String) dynaBean.get("mod_id");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String source_flag = (String) dynaBean.get("source_flag");
		Long audit_id = 0L;
		
		//将页面信息赋值给对象
		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.getMap().put("source_flag", source_flag);
		super.copyProperties(entity, form);
		
		//处理所在城市编码
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setEntp_p_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setEntp_p_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && !GenericValidator.isLong(town)) {
			entity.setEntp_p_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
		        && GenericValidator.isLong(town)) {
			entity.setEntp_p_index(Long.valueOf(town));
		}
		
		Long cust_id = entity.getCust_id();
		if(null==entity.getCust_id()){//新增
			entity.setAdd_user_id(Long.valueOf(ui.getId()));
			entity.setMody_user_id(Long.valueOf(ui.getId()));
			
			int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
			Long fgs_dept_id = 0L;
			if(max_dlevel != 9){
//				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
//				fgs_dept_id = dept_fgs.getDept_id();
				fgs_dept_id = ui.getDept_id();
			}
			
			entity.setSubcomp_id(fgs_dept_id);
			entity.setModify_date(new Date());
			entity.setYwy_user_id(ui.getId());
			entity.setIs_syn(0);
			
			//生成单号
			Date date = new Date();
			Long ran = (long) (Math.random()*9000+1000);
			entity.setRecord_sn("NS-"+date.getTime()+ran.toString());
			
			try{
				cust_id = super.getFacade().getKonkaR3ShopNewService().saveNewCustomer(entity);
			}catch (Exception e){
				e.printStackTrace();
			}
		}else{ //修改
			//审核
			String audit_res = (String) dynaBean.get("audit_res");
			String audit_comment = (String) dynaBean.get("audit_comment");
			String role_id = (String) dynaBean.get("role_id");  //当前审批权限
			String to_back = (String) dynaBean.get("to_back");  //若驳回，则驳回的节点
			
			if (StringUtils.isNotBlank(audit_res)) {
				KonkaoaFilesAuditNode auditNode = new KonkaoaFilesAuditNode();
				
				PeRoleInfo roleinfo = new PeRoleInfo();
				roleinfo.setRole_id(Long.parseLong(role_id));
				roleinfo = super.getFacade().getPeRoleInfoService().getPeRoleInfo(roleinfo);
				auditNode.setAudit_user(roleinfo.getRole_name());
				
				if("1".equals(audit_res)){//驳回则保存驳回节点
					if("1".equals(to_back)){  //驳回到业务员
						auditNode.setAudit_node_name("业务员");
					}
					if("2".equals(to_back)){  //驳回到财务部
						auditNode.setAudit_node_name("财务部");
					}
					if("3".equals(to_back)){  //驳回到财务经理
						auditNode.setAudit_node_name("财务经理");
					}
					if("4".equals(to_back)){  //驳回到总经理
						auditNode.setAudit_node_name("总经理");
					}
				}
				auditNode.setLink_id(entity.getCust_id());
				//审批类型
				auditNode.setNode_type(3);
				auditNode.setAudit_user_id(Long.valueOf(role_id));
				auditNode.setAudit_datetime(new Date());
				
				//默认为该人员审批的最新记录
				auditNode.setAudit_level(1L);
				
				//审批结果
				if(StringUtils.isNotBlank(audit_res)){
					auditNode.setAudit_result(Integer.valueOf(audit_res));
				}
				//审批意见
				if(StringUtils.isNotBlank(audit_comment)){
					auditNode.setAudit_comment(audit_comment);
				}
				//审批角色
				if(StringUtils.isNotBlank(role_id)){
					auditNode.setAudit_user_id(Long.valueOf(role_id));
				}
				
				//审批人信息
				auditNode.setAgent_audit_user(ui.getUser_name());
				auditNode.setAgent_audit_user_id(ui.getId());
				
				super.getFacade().getKonkaoaFilesAuditNodeService().updateOldNode(auditNode);
				
				audit_id = super.getFacade().getKonkaoaFilesAuditNodeService().createKonkaoaFilesAuditNode(auditNode);
				
				if(audit_id>0){
					//更新新开户表的处理状态
					if("1".equals(audit_res)){//驳回
						entity.setAudit_stat(3);
						if("1".equals(to_back)){  //驳回到业务员
							entity.setCur_audit_user_id(0L);
						}
						if("2".equals(to_back)){  //驳回到财务部
							entity.setCur_audit_user_id(35L);
						}
						if("3".equals(to_back)){  //驳回到财务经理
							entity.setCur_audit_user_id(39L);
						}
						if("4".equals(to_back)){  //驳回到总经理
							entity.setCur_audit_user_id(34L);
						}
					}else{
						if("18".equals(role_id)){  //总部权限审批，且审批结果为通过，则完成审批
							entity.setAudit_stat(2);
						}else{
							if("35".equals(role_id)){  //下一个审批角色
								entity.setCur_audit_user_id(39L);
							}
							if("39".equals(role_id)){ //下一个审批角色
								entity.setCur_audit_user_id(34L);
							}
							if("34".equals(role_id)){ //下一个审批角色
								entity.setCur_audit_user_id(18L);
							}
							entity.setAudit_stat(1);
						}
					}
				}
			}
			entity.setMody_user_id(Long.valueOf(ui.getId()));
			
			entity.setModify_date(new Date());
			super.getFacade().getKonkaR3ShopNewService().modifyKonkaR3ShopNew(entity);
		}
		
		// 附件处理
		if(cust_id>0L){
			KonkaR3ShopNewAtt kttemp = new KonkaR3ShopNewAtt();
			kttemp.setLink_id(cust_id);
			super.getFacade().getKonkaR3ShopNewAttService().removeKonkaR3ShopNewAtt(kttemp);
			
			for(int i=1;i<=18;i++){
				KonkaR3ShopNewAtt ktt = new KonkaR3ShopNewAtt();
				ktt.setLink_id(cust_id);
				ktt.setIs_upload((String) dynaBean.get("checkbox"+i));
				ktt.setResson((String) dynaBean.get("file"+i+"_res"));
				ktt.setIs_del("0");
				super.getFacade().getKonkaR3ShopNewAttService().createKonkaR3ShopNewAtt(ktt);
			}
		}
		
		request.setAttribute("cust_id", cust_id);
		request.setAttribute("mod_id", mod_id);

		StringBuffer pathBuffer = new StringBuffer();
		if(audit_id>0){
			pathBuffer.append("/admin/CreateCustomer/list-wait.jsp?mod_id=101007");
		}else{
			pathBuffer.append("/admin/CreateCustomer/list.jsp?mod_id=101005");
		}
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);

		return forward;
	}
	
	
	/**
	 * 进入表单页面，进行修改完善信息并审批
	 * @author Liang,HouEn
	 * 2014-10-9
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toModify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String cust_id = (String) dynaBean.get("cust_id");
		String role_id1 = (String) dynaBean.get("role_id1");
		String role_id2 = (String) dynaBean.get("role_id2");
		String role_id3 = (String) dynaBean.get("role_id3");
		String role_id4 = (String) dynaBean.get("role_id4");
		request.setAttribute("mod_id", mod_id);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		//经办列表
		String fgs_dept_id = "0";

		if (!GenericValidator.isLong(fgs_dept_id)) {
			return null;
		}
		
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel!=9){
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				fgs_dept_id = dept_fgs.getDept_id().toString();
			}
		}

		KonkaDept entity = new KonkaDept();
		entity.getMap().put("fgs_dept_id", fgs_dept_id);
		List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptAndJbNameList(entity);
		request.setAttribute("jblist",entityList);
		request.setAttribute("dept_id",fgs_dept_id);
		
		// 客户类别
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 客户规模（年销售额）
		kc = new KonkaCategory();
		kc.setC_type(13);
		kc.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		
		KonkaR3ShopNew enti = new KonkaR3ShopNew();
		enti.setCust_id(Long.valueOf(cust_id));
		enti = super.getFacade().getKonkaR3ShopNewService().getKonkaR3ShopNew(enti);
		super.copyProperties(form, enti);
		
		//是否显示审核
		if(null!=role_id1&&role_id1.equals(enti.getCur_audit_user_id().toString())){//财务部权限
			request.setAttribute("show_audit", true);
			request.setAttribute("role_id", role_id1);
		}
		
		if(null!=role_id2&&role_id2.equals(enti.getCur_audit_user_id().toString())){//财务经理权限
			request.setAttribute("show_audit", true);
			request.setAttribute("role_id", role_id2);
		}
		
		if(null!=role_id3&&role_id3.equals(enti.getCur_audit_user_id().toString())){//总经理权限
			request.setAttribute("show_audit", true);
			request.setAttribute("role_id", role_id3);
		}
		if(null!=role_id4&&role_id4.equals(enti.getCur_audit_user_id().toString())){//总部角色
			request.setAttribute("show_audit", true);
			request.setAttribute("role_id", role_id4);
		}
		
		//创建人
		PeProdUser user = new PeProdUser();
		user.setId(enti.getAdd_user_id());
		user = super.getFacade().getPeProdUserService().getPeProdUser(user);
		request.setAttribute("create_man", user.getReal_name());
				
		if (null != enti.getEntp_p_index() && String.valueOf(enti.getEntp_p_index()).length() >= 6) {
			request.setAttribute("province", String.valueOf(enti.getEntp_p_index()).substring(0, 2) + "0000");
			request.setAttribute("city", String.valueOf(enti.getEntp_p_index()).substring(0, 4) + "00");
			request.setAttribute("country", String.valueOf(enti.getEntp_p_index()).substring(0, 6));
			request.setAttribute("town", String.valueOf(enti.getEntp_p_index()));
		}
		
		// 附件
		KonkaR3ShopNewAtt shop_a = new KonkaR3ShopNewAtt();
		shop_a.setLink_id(enti.getCust_id());
		request.setAttribute("attachmentList",super.getFacade().getKonkaR3ShopNewAttService().getKonkaR3ShopNewAttList(shop_a));
		
		//已审核节点
		KonkaoaFilesAuditNode done = new KonkaoaFilesAuditNode();
		done.setNode_type(3);
		//done.setAudit_level(1L);
		done.setLink_id(Long.valueOf(cust_id));
		request.setAttribute("doneList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(done));
		
		
		if(2!=enti.getAudit_stat()){  //只有未完成审核时，才有即将审核节点
			//即将审核节点
			PeRoleInfo doing_audit_role = new PeRoleInfo();
			doing_audit_role.setRole_id(enti.getCur_audit_user_id());
			doing_audit_role = super.getFacade().getPeRoleInfoService().getPeRoleInfo(doing_audit_role);
			request.setAttribute("doing_audit_role", doing_audit_role);
			
			if(doing_audit_role.getRole_id()!=18L){
				List<PeRoleInfo> todoList = new ArrayList<PeRoleInfo>();
				PeRoleInfo todo_role = new PeRoleInfo();
				if(doing_audit_role.getRole_id()==35L){
					todo_role.getMap().put("todo_role", "39,34,18");
				}
				if(doing_audit_role.getRole_id()==39L){
					todo_role.getMap().put("todo_role", "34,18");
				}
				if(doing_audit_role.getRole_id()==34L){
					todo_role.getMap().put("todo_role", "18");
				}
				todo_role.getMap().put("order_by_id", true);
				todoList = super.getFacade().getPeRoleInfoService().getPeRoleInfoList(todo_role);
				request.setAttribute("todoList", todoList);
			}
		}
		//审批结果列表
		KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
		kfan.setNode_type(3);
		kfan.setLink_id(Long.valueOf(cust_id));
		kfan.getMap().put("is_audit", true);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeListForCust(kfan));
	
		//跳转到待审客户form表单页面
		return mapping.findForward("input");
	}
	
	
	/**
	 * 查看新开户客户详情
	 * @author Liang,HouEn
	 * 2014-10-10
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
		String oper = (String) dynaBean.get("oper");
		String cust_id = (String) dynaBean.get("cust_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String type = (String) dynaBean.get("type");
		
		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.setCust_id(Long.valueOf(cust_id));
		
		entity = super.getFacade().getKonkaR3ShopNewService().getKonkaR3ShopNew(entity);

		super.copyProperties(form, entity);
		request.setAttribute("cust_id", cust_id);
		request.setAttribute("mod_id", mod_id);
		
		//创建人
		PeProdUser user = new PeProdUser();
		user.setId(entity.getAdd_user_id());
		user = super.getFacade().getPeProdUserService().getPeProdUser(user);
		request.setAttribute("create_man", user.getReal_name());

		if (StringUtils.isNotBlank(entity.getCustomer_type().toString())) {
			// 客户类别
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(10);
			kc.setIs_del(0);
			kc.setC_index(new Long(entity.getCustomer_type()));
			List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
			        .getKonkaCategoryList(kc);
			if (null != konkaCategoryList && konkaCategoryList.size() > 0) {
				kc = konkaCategoryList.get(0);
				request.setAttribute("customer_type_name", "[" + kc.getC_comm() + "]" + kc.getC_name());
			}

		}

		if (StringUtils.isNotBlank(entity.getTotal_sale()+"")&&null!=entity.getTotal_sale()) {
			// 客户规模（年销售额）
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(13);
			kc.setIs_del(0);
			kc.setC_index(new Long(entity.getTotal_sale()));
			List<KonkaCategory> entpScaleList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
			if (null != entpScaleList && entpScaleList.size() > 0) {
				kc = entpScaleList.get(0);
				request.setAttribute("entp_scale_name", kc.getC_name());
			}
		}

		if (entity.getEntp_p_index() != null && String.valueOf(entity.getEntp_p_index()).length() >= 6) {
			// 省/直辖市/自治区
			BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 2) + "0000"));
			baseProvinceFour.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
			        .getBaseProvinceListFourList(baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("province", b.getP_name());
			}
			// 市
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 4) + "00"));
			baseProvinceFourList = null;
			baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
			        baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("city", b.getP_name());
			}
			// 县
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 6)));
			baseProvinceFourList = null;
			baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
			        baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("country", b.getP_name());
			}
			// 乡镇
			if(String.valueOf(entity.getEntp_p_index()).length()>6){
				baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index())));
				baseProvinceFourList = null;
				baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
				        baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					request.setAttribute("town", b.getP_name());
				}
			}else{
				request.setAttribute("town", "");
			}
		}
		
		if (StringUtils.isNotBlank(type)){
			PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			
			//用户角色信息
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(ui.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
			
			for(PeRoleUser t : roleUserList){
				if(t.getRole_id() == 35){//财务部权限
					request.setAttribute("canSyn", true);
					break;
				}
			}
		}
		
		// 附件
		KonkaR3ShopNewAtt shop_a = new KonkaR3ShopNewAtt();
		shop_a.setLink_id(entity.getCust_id());
		request.setAttribute("attachmentList",super.getFacade().getKonkaR3ShopNewAttService().getKonkaR3ShopNewAttList(shop_a));
		
		//已审核节点
		KonkaoaFilesAuditNode done = new KonkaoaFilesAuditNode();
		done.setNode_type(3);
		//done.setAudit_level(1L);
		done.setLink_id(Long.valueOf(cust_id));
		request.setAttribute("doneList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(done));
		
		
		if(2!=entity.getAudit_stat()){  //只有未完成审核时，才有即将审核节点
			//即将审核节点
			PeRoleInfo doing_audit_role = new PeRoleInfo();
			doing_audit_role.setRole_id(entity.getCur_audit_user_id());
			doing_audit_role = super.getFacade().getPeRoleInfoService().getPeRoleInfo(doing_audit_role);
			request.setAttribute("doing_audit_role", doing_audit_role);
			
			List<PeRoleInfo> todoList = new ArrayList<PeRoleInfo>();
			PeRoleInfo todo_role = new PeRoleInfo();
			if(null!=doing_audit_role){
				if(doing_audit_role.getRole_id()!=18L){
					if(doing_audit_role.getRole_id()==35L){
						todo_role.getMap().put("todo_role", "39,34,18");
					}
					if(doing_audit_role.getRole_id()==39L){
						todo_role.getMap().put("todo_role", "34,18");
					}
					if(doing_audit_role.getRole_id()==34L){
						todo_role.getMap().put("todo_role", "18");
					}
					todo_role.getMap().put("order_by_id", true);
					todoList = super.getFacade().getPeRoleInfoService().getPeRoleInfoList(todo_role);
					request.setAttribute("todoList", todoList);
				}
			}
		}
		//审批结果列表
		KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
		kfan.setNode_type(3);
		kfan.setLink_id(Long.valueOf(cust_id));
		kfan.getMap().put("is_audit", true);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeListForCust(kfan));
	
		//跳转到form表单页面
		return mapping.findForward("view");
	}
	
	/**
	 * 同步新开户客户至R3客户管理中
	 * @author Liang,HouEn
	 * 2014-10-13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addToKonkaR3Shop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//权限验证
//		if (null == super.checkUserModPopeDom(form, request, "4")) {
//			return super.noPersission(request,response);
//		}

		DynaBean dynaBean = (DynaBean) form;

		String r3_code = (String) dynaBean.get("link_r3_code");
		String cust_id = (String) dynaBean.get("cust_id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.setCust_id(Long.valueOf(cust_id));
		entity.setLink_r3_code(r3_code);
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.getMap().put("syn_user_id", ui.getId());
		entity.getMap().put("syn_user_name", ui.getUser_name());
		
		int res = super.getFacade().getKonkaR3ShopNewService().addToKonkaR3Shop(entity);

		if(res>0){
			super.renderJavaScript(response, "location.href='CreateCustomer/list-have.jsp?mod_id="+mod_id+"'");
		}else{
			super.renderJavaScript(response, "alert('"+super.getMessage(request, "syn.new.customer.error")+"');history.back();");
		}
		return null;
	}
}