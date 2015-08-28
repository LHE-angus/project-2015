package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3ShopNew;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

import net.sf.json.JSONArray;

/**
 * 待审客户菜单模块
 * @author Liang,HouEn
 * 2014-10-9
 */
public class HaveAuditCustAction extends BaseAction {

	// private static HashMap<String, String> properties = new HashMap<String,
	// String>();
	// static {
	// InputStream inputStream =
	// InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
	// "webservice-url.properties");
	// Properties p = new Properties();
	// try {
	// p.load(inputStream);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// for (Object key : p.keySet()) {
	// properties.put((String) key, (String) p.get(key));
	// }
	// }

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
		String cust_stat = request.getParameter("cust_stat");
		String page = request.getParameter("page");
		
        PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3ShopNew entity = new KonkaR3ShopNew();

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
		
		//建户状态
		if(StringUtils.isNotBlank(cust_stat)){
			if("4".equals(cust_stat)){
				entity.setIs_syn(1);
			}else{
				entity.setAudit_stat(Integer.valueOf(cust_stat));
			}
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
		entity.getMap().put("have_audit", true);
		
		return entity;
	}

	/**
	 * 进入表单页面，进行修改完善信息
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
		request.setAttribute("mod_id", mod_id);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		//经办列表
		String fgs_dept_id = ui.getDept_id().toString();

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
		if (null != enti.getEntp_p_index() && String.valueOf(enti.getEntp_p_index()).length() >= 6) {
			request.setAttribute("province", String.valueOf(enti.getEntp_p_index()).substring(0, 2) + "0000");
			request.setAttribute("city", String.valueOf(enti.getEntp_p_index()).substring(0, 4) + "00");
			request.setAttribute("country", String.valueOf(enti.getEntp_p_index()).substring(0, 6));
			request.setAttribute("town", String.valueOf(enti.getEntp_p_index()));
		}
		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(cust_id));
		attachment.setLink_tab("KONKA_R3_SHOP_DEV");
		attachment.setDel_mark(new Short("0"));
		attachment.getMap().put("sort", true);
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));
		
		//跳转到待审客户form表单页面
		return mapping.findForward("input");
	}
	
	
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		String mod_id = (String) dynaBean.get("mod_id");
		
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		super.copyProperties(entity, form);
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.setMody_user_id(Long.valueOf(ui.getId()));
		entity.setModify_date(new Date());
		
		if (!"2".equals(String.valueOf(entity.getAudit_stat()))) {
			// 附件处理
			// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0,
			// new int[] { 240 });
			List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
					new int[] { 240 });

			List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
			Attachment filesAttachment = null;
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				filesAttachment.setFile_desc(uploadFile.getFormName());
				filesAttachmentList.add(filesAttachment);
			}
			entity.setAttachmentList(filesAttachmentList);
		}


		if (StringUtils.isNotBlank(town)) {
			entity.setEntp_p_index(new Long(town));
		} else if (StringUtils.isNotBlank(country)) {
			entity.setEntp_p_index(new Long(country));
		} else if (StringUtils.isNotBlank(city)) {
			entity.setEntp_p_index(new Long(city));
		} else if (StringUtils.isNotBlank(province)) {
			entity.setEntp_p_index(new Long(province));
		}
		
		int result = super.getFacade().getKonkaR3ShopNewService().modifyKonkaR3ShopNew(entity);
		
		if(result>0){
			//跳转到list列表页面
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append("/admin/WaitAuditCust/list.jsp?mod_id="+mod_id);
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			
			return forward;
		}else{
			return null;
		}
		
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
		String jhcw = (String) dynaBean.get("jhcw");
		String cwjl = (String) dynaBean.get("cwjl");
		String zjl = (String) dynaBean.get("zjl");
		
		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.setCust_id(Long.valueOf(cust_id));
		
		entity = super.getFacade().getKonkaR3ShopNewService().getKonkaR3ShopNew(entity);

		super.copyProperties(form, entity);
		request.setAttribute("cust_id", cust_id);
		request.setAttribute("mod_id", mod_id);

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
		
		if (StringUtils.isNotBlank(entity.getJb_id()+"")) {
			// 所属经办
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(entity.getJb_id());
			KonkaDept dept = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (null != dept) {
				request.setAttribute("jb_name", dept.getDept_name());
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
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index())));
			baseProvinceFourList = null;
			baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
			        baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("town", b.getP_name());
			}
		}

		if("audit".equals(oper)){
			request.setAttribute("oper", "");
			if ("true".equals(jhcw)) {
				request.setAttribute("access", "jhcw");
			}
			if ("true".equals(cwjl)) {
				request.setAttribute("access", "cwjl");
			}
			if ("true".equals(zjl)) {
				request.setAttribute("access", "zjl");
			}
		}else{
			request.setAttribute("oper", "none");
		}
		
		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(cust_id));
		attachment.setLink_tab("KONKA_R3_SHOP_DEV");
		attachment.setDel_mark(new Short("0"));
		attachment.getMap().put("sort", true);
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));
		
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
	 * 提交审批结果
	 * @author Liang,HouEn
	 * 2014-10-11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		
		String audit_res = (String) dynaBean.get("audit_res");
		String audit_comment = (String) dynaBean.get("audit_comment");
		String cust_id = (String) dynaBean.get("cust_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String access = (String) dynaBean.get("access");  //当前审批权限
		
		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		entity.setLink_id(Long.valueOf(cust_id));
		if(StringUtils.isNotBlank(audit_res)){
			entity.setAudit_result(Integer.valueOf(audit_res));
		}
		if(StringUtils.isNotBlank(audit_comment)){
			entity.setAudit_comment(audit_comment);
		}
		if(StringUtils.isNotBlank(access)){
			if("jhcw".equals(access)){
				entity.setAudit_user_id(35L);
			}
			if("cwjl".equals(access)){
				entity.setAudit_user_id(39L);
			}
			if("zjl".equals(access)){
				entity.setAudit_user_id(34L);
			}
		}
		entity.setNode_type(3);
		
		Long role_id = 0L;
		if("jhcw".equals(access)){
			role_id = 35L;
		}
		if("cwjl".equals(access)){
			role_id = 39L;
		}
		if("zjl".equals(access)){
			role_id = 34L;
		}
		entity.setAudit_user_id(role_id);
		entity.setAudit_datetime(new Date());
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		entity.setAgent_audit_user(ui.getUser_name());
		entity.setAgent_audit_user_id(ui.getId());
		
		int count = super.getFacade().getKonkaoaFilesAuditNodeService().modifySystemAplication(entity);
		
		if(count>0){
			//更新处理状态
			KonkaR3ShopNew en = new KonkaR3ShopNew();
			en.setCust_id(Long.valueOf(cust_id));
			
			if("1".equals(audit_res)){//驳回
				en.setAudit_stat(3);
			}else{
				if("zjl".equals(access)){  //总经理权限审批，且审批结果为通过，则完成审批
					en.setAudit_stat(2);
				}else{
					if("jhcw".equals(access)){  //下一个审批角色
						en.setCur_audit_user_id(39L);
					}
					if("cwjl".equals(access)){ //下一个审批角色
						en.setCur_audit_user_id(34L);
					}
					en.setAudit_stat(1);
				}
			}
			super.getFacade().getKonkaR3ShopNewService().modifyKonkaR3ShopNew(en);
			
			super.renderJavaScript(response, "alert('审批完成！');location.href='WaitAuditCust/list.jsp?mod_id="+mod_id+"'");
		}else{
			super.renderJavaScript(response, "alert('审批失败！');history.back();");
		}
		return null;
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

		String r3_code = (String) dynaBean.get("r3_code");
		String cust_id = (String) dynaBean.get("cust_id");
		
		KonkaR3ShopNew entity = new KonkaR3ShopNew();
		entity.setCust_id(Long.valueOf(cust_id));
		entity.setLink_r3_code(r3_code);
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.getMap().put("syn_user_id", ui.getId());
		
		int res = super.getFacade().getKonkaR3ShopNewService().addToKonkaR3Shop(entity);

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", res);
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