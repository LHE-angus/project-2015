package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaR3ShopDevAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String begindate = "";
		String enddate = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
			Calendar calendarbegin = Calendar.getInstance();
			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
			begindate = df.format(calendarbegin.getTime());
			dynaBean.set("begin_time", begindate);
			Calendar calendarend = Calendar.getInstance();
			enddate = df.format(calendarend.getTime());
			dynaBean.set("end_time", enddate);
		return list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");// 拜访开始时间
		String begin_date = (String) dynaBean.get("begin_time");// 拜访开始时间
		String end_date = (String) dynaBean.get("end_time");// 拜访结束时间
		String cust_name_like = (String) dynaBean.get("cust_name_like");// 客户名称
		String is_del = (String) dynaBean.get("is_del");// 拜访开始时间
		String dev_state = (String) dynaBean.get("dev_state");
		String _state = (String) dynaBean.get("_state");//开拓意向
		String link_man_name_like = (String) dynaBean.get("link_man_name_like");
		String report_nae_like = (String) dynaBean.get("report_nae_like");// 上报人
		String subcomp_id = (String) dynaBean.get("subcomp_id");
		String excel_all = (String) dynaBean.get("excel_all");//
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session
				.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		if(StringUtils.isNotBlank(is_del)&&GenericValidator.isInt(is_del)){
			konkaR3ShopDev.setIs_del(Integer.parseInt(is_del));
			dynaBean.set("is_del", is_del);
		}else{
		konkaR3ShopDev.setIs_del(0);
		dynaBean.set("is_del", is_del);
		}
		if(StringUtils.isNotBlank(begin_date)){
			konkaR3ShopDev.getMap().put("begin_time", begin_date+" 00:00:00");
			dynaBean.set("begin_time", begin_date);
		}
		if(StringUtils.isNotBlank(end_date)){
			konkaR3ShopDev.getMap().put("end_time", end_date+" 23:59:59");
			dynaBean.set("end_date", end_date);
		}
		if(StringUtils.isNotBlank(cust_name_like)){
			konkaR3ShopDev.getMap().put("cust_name_like", cust_name_like);
			dynaBean.set("cust_name_like", cust_name_like);
		}
		if(StringUtils.isNotBlank(dev_state)){
			konkaR3ShopDev.setDev_state(Integer.parseInt(dev_state));
			dynaBean.set("dev_state", dev_state);
		}
		if(StringUtils.isNotBlank(link_man_name_like)){
			konkaR3ShopDev.getMap().put("link_man_name_like", link_man_name_like);
			dynaBean.set("link_man_name_like", link_man_name_like);
		}
		if(StringUtils.isNotBlank(subcomp_id)&&GenericValidator.isLong(subcomp_id)){
			konkaR3ShopDev.setSubcomp_id(Long.valueOf(subcomp_id));
			dynaBean.set("subcomp_id", subcomp_id);
		}
		if(StringUtils.isNotBlank(_state)){
			konkaR3ShopDev.setState(Integer.parseInt(_state));
		}
		if (StringUtils.isNotBlank(report_nae_like)) {
			konkaR3ShopDev.getMap().put("report_nae_like", report_nae_like);
			dynaBean.set("report_nae_like", report_nae_like);
		}
		/**
		 * 权限使用
		 */
		if (null!=user.getDept_id()) {
			konkaR3ShopDev.getMap().put("deptId", user.getDept_id());
		}
		List<KonkaR3ShopDev> entityList=new ArrayList<KonkaR3ShopDev>();
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		boolean role_id_eq_10 = false; // 是否为系统管理员
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		List<Long> role_ids = new ArrayList<Long>(); // 当前登录用户的角色列表
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids.add(peRoleUser.getRole_id());

			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 30L) {
				role_id_eq_10 = true;
			}
		}
		if(role_id_eq_10){
		}else{
			
		}
		Long recordCount=super.getFacade().getKonkaR3ShopDevService().selectKonkaR3ShopDevLBCount(konkaR3ShopDev);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaR3ShopDev.getRow().setCount(pager.getRowCount());
		konkaR3ShopDev.getRow().setFirst(pager.getFirstRow());
		
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName("新客户开拓数据") + ".xls");
			konkaR3ShopDev.getRow().setFirst(0);
			konkaR3ShopDev.getRow().setCount(recordCount.intValue());
			
			List<KonkaR3ShopDev> entityList1 = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevLBPaginatedList(konkaR3ShopDev);
			request.setAttribute("allList", entityList1);
			Integer count=entityList1.size();
			if (count!=null&&count>0) {
				return new ActionForward("/admin/KonkaR3ShopDev/list_report.jsp");
			}else{
				//super.renderJavaScript(response, "alert('没有数据要导出！');history.back();");
				return null;
			}
		}
		
		
		entityList=super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDevLBPaginatedList(konkaR3ShopDev);
		if(null!=entityList){
			request.setAttribute("entityList", entityList);
		}
		
		
		// 拿分公司
		List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
	
		
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 拿到公司类型的下拉
		KonkaCategory konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(12);
		List<KonkaCategory> categoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
				konkaCategory);
		request.setAttribute("categoryList", categoryList);
		konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(13);
		konkaCategory.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(konkaCategory));
		String[] link_man_names ={},link_man_tels ={};
        dynaBean.set("link_man_name", link_man_names);
        dynaBean.set("link_man_tel", link_man_tels);
		// 拿分公司
		//List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		//request.setAttribute("deptList", deptList);

		request.setAttribute("today", DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
		return mapping.findForward("input");
	}

	public ActionForward save_of_mobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 类型
		String type = (String) dynaBean.get("type");
		
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderJson(response, "登录超时");
			return null;
		}
		String cust_id = (String) dynaBean.get("cust_id");// 记录的主键
		String link_r3_code = (String) dynaBean.get("link_r3_code");// 记录的主键
        String b_lng=(String) dynaBean.get("b_lng");// 地理位置-经度
        String b_lat=(String) dynaBean.get("b_lat");// 地理位置-纬度
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		super.copyProperties(konkaR3ShopDev, form);
		if (StringUtils.isBlank(cust_id)) {// 如果是添加，给时间
			konkaR3ShopDev.setAdd_date(new Date());
			if (null!=userInfo&&null!=userInfo.getId()) {
				konkaR3ShopDev.setAdd_user_id(userInfo.getId());
			}
			konkaR3ShopDev.setIs_del(0);// 是否删除
			if (null != userInfo.getId()) {
				konkaR3ShopDev.setYwy_user_id(userInfo.getId());
			}
			if(StringUtils.isNotBlank(b_lng)){// 地理位置-经度
				konkaR3ShopDev.setB_lng(b_lng);
			}
			if(StringUtils.isNotBlank(b_lat)){// 地理位置-纬度
				konkaR3ShopDev.setB_lng(b_lat);
			}
		}
		if (StringUtils.isNotBlank(link_r3_code)) {
			konkaR3ShopDev.setIs_match(1);// 如果选择了关联的r3编码，那么就说明是完成了匹配
		} else {
			konkaR3ShopDev.setIs_match(0);// 如果没选择关联的r3编码，那么就说明是完成了匹配
		}
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		
		if (null!=userInfo&&null!=userInfo.getDept_id()) {
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept.getMap().put("dept_type_eq", 3);
			konkaDept = super.getFacade().getKonkaDeptService()
					.getKonkaDeptSuperiorByDeptId(konkaDept);
		}
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkaR3ShopDev.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}

		Long id=0l;
		if (StringUtils.isBlank(cust_id)) {//添加
			id=	super.getFacade().getKonkaR3ShopDevService().createKonkaR3ShopDev(konkaR3ShopDev);
		}else{//修改
			super.getFacade().getKonkaR3ShopDevService().modifyKonkaR3ShopDev(konkaR3ShopDev);
			id=Long.valueOf(cust_id);
		}
		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString("success:"+id));
		return null;
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		String cust_id = (String) dynaBean.get("cust_id");// 记录的主键
		String link_r3_code = (String) dynaBean.get("link_r3_code");// 记录的主键
        String b_lng=(String) dynaBean.get("b_lng");// 地理位置-经度
        String b_lat=(String) dynaBean.get("b_lat");// 地理位置-纬度
        List<String> link_man_names=(List<String>)dynaBean.get("link_man_name");
        List<String> link_man_tels = (List<String>)dynaBean.get("link_man_tel");
        String link_man_nameStr="";
        String link_man_telsStr="";
        for (int i = 0; i < link_man_names.size(); i++) {
        	 link_man_nameStr+=link_man_names.get(i)+",";
        	 link_man_telsStr+=link_man_tels.get(i)+",";
		}
        
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		super.copyProperties(konkaR3ShopDev, form);
		
		konkaR3ShopDev.setLink_man_name(link_man_nameStr.substring(0,link_man_nameStr.length()-1));
		konkaR3ShopDev.setLink_man_tel(link_man_telsStr.substring(0,link_man_telsStr.length()-1));
		
		if (StringUtils.isBlank(cust_id)) {// 如果是添加，给时间
			if (null!=userInfo&&null!=userInfo.getId()) {
				konkaR3ShopDev.setAdd_user_id(userInfo.getId());
			}
			konkaR3ShopDev.setAdd_date(new Date());
			konkaR3ShopDev.setIs_del(0);// 是否删除
			if (null != userInfo.getId()) {
				konkaR3ShopDev.setYwy_user_id(userInfo.getId());
			}
			if(StringUtils.isNotBlank(b_lng)){// 地理位置-经度
				konkaR3ShopDev.setB_lng(b_lng);
			}
			if(StringUtils.isNotBlank(b_lat)){// 地理位置-纬度
				konkaR3ShopDev.setB_lng(b_lat);
			}
		}
		if (StringUtils.isNotBlank(link_r3_code)) {
			konkaR3ShopDev.setIs_match(1);// 如果选择了关联的r3编码，那么就说明是完成了匹配
		} else {
			konkaR3ShopDev.setIs_match(0);// 如果没选择关联的r3编码，那么就说明是完成了匹配
		}
		// 根据当前用户找到分公司
		KonkaDept konkaDept = new KonkaDept();
		
		if (null!=userInfo&&null!=userInfo.getDept_id()) {
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept.getMap().put("dept_type_eq", 3);
			konkaDept = super.getFacade().getKonkaDeptService()
					.getKonkaDeptSuperiorByDeptId(konkaDept);
		}
		if (null != konkaDept && null != konkaDept.getDept_id()) {
			konkaR3ShopDev.setSubcomp_id(konkaDept.getDept_id());// 分公司id
		}
		
		 //拿到上传的图片附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				attachment = new KonkaPeAttachments();
				attachment.setFile_name(uploadFile.getFileName());
				attachment.setFile_type(uploadFile.getContentType());
				attachment.setFile_size(new Long(uploadFile.getFileSize()));
				attachment.setSave_path(uploadFile.getFileSavePath());
				attachment.setSave_name(uploadFile.getFileSaveName());
				attachment.setIs_del(0l);
				attachment.setLink_tab("KONKA_R3_SHOP_DEV");
				attachment.setAdd_user_name(userInfo.getUser_name());
				attachment.setAdd_user_id(userInfo.getId());
				attachment.setFile_desc(uploadFile.getFormName());
				attachmentList.add(attachment);
			}
			konkaR3ShopDev.setKonkaPeAttachmentsList(attachmentList);
		}
		
		if (StringUtils.isBlank(cust_id)) {//添加
			super.getFacade().getKonkaR3ShopDevService().createKonkaR3ShopDev(konkaR3ShopDev);
		}else{
			super.getFacade().getKonkaR3ShopDevService().modifyKonkaR3ShopDev(konkaR3ShopDev);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.saveToken(request);
		String cust_id = (String) dynaBean.get("cust_id");
		if (StringUtils.isBlank(cust_id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 主表信息
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		konkaR3ShopDev.setCust_id(Long.valueOf(cust_id));
		konkaR3ShopDev = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDev(
				konkaR3ShopDev);
		if (null!=konkaR3ShopDev&&null!=konkaR3ShopDev.getLink_man_name()) {
			String[] link_man_names =konkaR3ShopDev.getLink_man_name().split(",");
	        String[] link_man_tels =konkaR3ShopDev.getLink_man_tel().split(",");
	        dynaBean.set("link_man_name", link_man_names);
	        dynaBean.set("link_man_tel", link_man_tels);
		}else {
			String[] link_man_names ={"",""};
	        String[] link_man_tels ={"",""};
	        dynaBean.set("link_man_name", link_man_names);
	        dynaBean.set("link_man_tel", link_man_tels);
		}
      if(null!=konkaR3ShopDev && null!= konkaR3ShopDev.getEntp_p_index()){
		String p_index = konkaR3ShopDev.getEntp_p_index().toString();
		if (!p_index.endsWith("00")) {
			if (p_index.length() == 6) {
				dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
				dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
				dynaBean.set("country", p_index);
			} else if (p_index.length() == 8) {
				dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
				dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
				dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
				dynaBean.set("town", p_index);
			}
		} else if (p_index.endsWith("0000")) {
			dynaBean.set("province", p_index);
		} else if (p_index.endsWith("00")) {
			dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
			dynaBean.set("city", p_index);
		}
}     
     // 拿到图片
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(Long.valueOf(cust_id));
		attachment.setLink_tab("KONKA_R3_SHOP_DEV");
		attachment.setIs_del(0L);
		//attachment = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(attachment);
		List<KonkaPeAttachments> piclist =super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment);
		konkaR3ShopDev.setKonkaPeAttachmentsList(piclist);
		
		super.copyProperties(form, konkaR3ShopDev);
		// 拿到公司类型的下拉
		KonkaCategory konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(12);
		List<KonkaCategory> categoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
				konkaCategory);
		request.setAttribute("categoryList", categoryList);
		konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(13);
		konkaCategory.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(konkaCategory));
		// 拿分公司
		//List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		//request.setAttribute("deptList", deptList);
		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String cust_id=(String)dynaBean.get("cust_id");
		String mod_id=(String)dynaBean.get("mod_id");
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		if(StringUtils.isNotBlank(cust_id) && GenericValidator.isLong(cust_id)){
			konkaR3ShopDev.setCust_id(Long.valueOf(cust_id));
			konkaR3ShopDev.setIs_del(1);
			super.getFacade().getKonkaR3ShopDevService().modifyKonkaR3ShopDev(konkaR3ShopDev);
		}else{
			super.renderHtml(response, "请选择一条记录！");
		}
		konkaR3ShopDev.setQueryString(super.serialize(request, "cust_id", "method"));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(konkaR3ShopDev.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.saveToken(request);
		String cust_id = (String) dynaBean.get("cust_id");
		if (StringUtils.isBlank(cust_id)) {
			super.renderHtml(response, "请选择一条记录修改!");
			return null;
		}
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			return null;
		}
		// 主表信息
		KonkaR3ShopDev konkaR3ShopDev = new KonkaR3ShopDev();
		konkaR3ShopDev.setCust_id(Long.valueOf(cust_id));
		konkaR3ShopDev = super.getFacade().getKonkaR3ShopDevService().getKonkaR3ShopDev(
				konkaR3ShopDev);
		if (null!=konkaR3ShopDev&&null!=konkaR3ShopDev.getLink_man_name()) {
			String[] link_man_names =konkaR3ShopDev.getLink_man_name().split(",");
	        String[] link_man_tels =konkaR3ShopDev.getLink_man_tel().split(",");
	        dynaBean.set("link_man_name", link_man_names);
	        dynaBean.set("link_man_tel", link_man_tels);
		}
      if(null!=konkaR3ShopDev && null!= konkaR3ShopDev.getEntp_p_index()){
		String p_index = konkaR3ShopDev.getEntp_p_index().toString();
		if (!p_index.endsWith("00")) {
			if (p_index.length() == 6) {
				dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
				dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
				dynaBean.set("country", p_index);
			} else if (p_index.length() == 8) {
				dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
				dynaBean.set("city", StringUtils.substring(p_index, 0, 4) + "00");
				dynaBean.set("country", StringUtils.substring(p_index, 0, 6));
				dynaBean.set("town", p_index);
			}
		} else if (p_index.endsWith("0000")) {
			dynaBean.set("province", p_index);
		} else if (p_index.endsWith("00")) {
			dynaBean.set("province", StringUtils.substring(p_index, 0, 2) + "0000");
			dynaBean.set("city", p_index);
		}
       }     
     // 拿到图片
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(Long.valueOf(cust_id));
		attachment.setLink_tab("KONKA_R3_SHOP_DEV");
		attachment.setIs_del(0L);
		//attachment = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(attachment);
		List<KonkaPeAttachments> piclist =super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment);
		konkaR3ShopDev.setKonkaPeAttachmentsList(piclist);
		
		super.copyProperties(form, konkaR3ShopDev);
		// 拿到公司类型的下拉
		KonkaCategory konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(12);
		List<KonkaCategory> categoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(
				konkaCategory);
		request.setAttribute("categoryList", categoryList);
		konkaCategory = new KonkaCategory();
		konkaCategory.setC_type(13);
		konkaCategory.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(konkaCategory));
		// 拿分公司
		//List<KonkaDept> deptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		//request.setAttribute("deptList", deptList);
		return new ActionForward("/../manager/admin/KonkaR3ShopDev/view.jsp");
	}
	/**
	 * 查找R3用户是否有结算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkR3Js(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		 DynaBean dynaBean = (DynaBean) form;
		 String r3code = (String) dynaBean.get("r3code");
        Long number =0L;
        ChannelDataImport cd=new ChannelDataImport();
        if (StringUtils.isNotBlank(r3code)) {
			cd.setColumn_1(r3code);
		}
        number=super.getFacade().getChannelDataImportService().getR3codeIsJs(cd);
		super.renderJson(response, JSON.toJSONString(number));
		return null;
	}

}
