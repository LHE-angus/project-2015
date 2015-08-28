package com.ebiz.mmt.web.struts.manager.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPhotoUpload;
import com.ebiz.mmt.domain.KonkaPhotoUploadType;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopDev;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.VOrgOfDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import com.ibm.db2.jcc.b.re;

/**
 * @author OuYang,BaiYang
 * @version 2014-1-23
 */

public class KonkaPhotoUploadAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		String isfirst = (String) dynaBean.get("isfirst");// 是否第一次
		String excel_all = (String) dynaBean.get("excel_all");
		
		KonkaDept kdDept = new KonkaDept();
		
		//判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
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
    	dynaBean.set("_status", 1);
        if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
        String begindate = "";
		String enddate = "";
        if (StringUtils.isEmpty(isfirst)) {
    			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    			Calendar calendarbegin = Calendar.getInstance();
    			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
    			begindate = df.format(calendarbegin.getTime());
    			entity.getMap().put("_begin_report_date", begindate+" 00:00:00");
    			dynaBean.set("_begin_report_date", begindate);
    			Calendar calendarend = Calendar.getInstance();
    			enddate = df.format(calendarend.getTime());
    			entity.getMap().put("_end_report_date", enddate+" 23:59:59");
    			dynaBean.set("_end_report_date", enddate);

		} else {
			if (StringUtils.isNotBlank(_begin_report_date)) {
				entity.getMap().put("_begin_report_date", _begin_report_date+" 00:00:00");
				dynaBean.set("_begin_report_date", _begin_report_date);
			}
	        if (StringUtils.isNotBlank(_end_report_date)) {
				entity.getMap().put("_end_report_date", _end_report_date+" 23:59:59");
				dynaBean.set("_end_report_date", _end_report_date);
			}
		}
        
        
        
        
        
        //entity.setStatus(1);//已保存
        
	    entity.setReport_user_id(userInfo.getId());//只能看着自己的
		
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		//分页
        Long recordCount = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		//查询
		List<KonkaPhotoUpload> entityList = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		//导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName("拍照上传") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			
			List<KonkaPhotoUpload> entityList1 = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			Integer count=entityList1.size();
			if (count!=null&&count>0) {
				return new ActionForward("/admin/KonkaPhotoUpload/list_report.jsp");
			}else{
				//super.renderJavaScript(response, "alert('没有数据要导出！');history.back();");
				return null;
			}
		}
		
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
		request.setAttribute("photoTypes", photoTypes);
		return mapping.findForward("list");
	}
	/**
	 * 已提交
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
		String isfirst = (String) dynaBean.get("isfirst");// 是否第一次
		String excel_all = (String) dynaBean.get("excel_all");
		
		
		KonkaDept kdDept = new KonkaDept();
		
		//判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
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
        entity.setStatus(2);
    	dynaBean.set("_status", 2);
        if (StringUtils.isNotBlank(isfirst)) {
			dynaBean.set("isfirst", isfirst);
		}
        //entity.setStatus(2);//已提交
        
        String begindate = "";
		String enddate = "";
        if (StringUtils.isEmpty(isfirst)) {
    			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
    			Calendar calendarbegin = Calendar.getInstance();
    			calendarbegin.set(Calendar.DAY_OF_MONTH, calendarbegin.getActualMinimum(Calendar.DAY_OF_MONTH));
    			begindate = df.format(calendarbegin.getTime());
    			entity.getMap().put("_begin_report_date", begindate+" 00:00:00");
    			dynaBean.set("_begin_report_date", begindate);
    			Calendar calendarend = Calendar.getInstance();
    			enddate = df.format(calendarend.getTime());
    			entity.getMap().put("_end_report_date", enddate+" 23:59:59");
    			dynaBean.set("_end_report_date", enddate);
		} else {
			if (StringUtils.isNotBlank(_begin_report_date)) {
				entity.getMap().put("_begin_report_date", _begin_report_date+" 00:00:00");
				dynaBean.set("_begin_report_date", _begin_report_date);
			}
	        if (StringUtils.isNotBlank(_end_report_date)) {
				entity.getMap().put("_end_report_date", _end_report_date+" 23:59:59");
				dynaBean.set("_end_report_date", _end_report_date);
			}
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
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
		//分页
        Long recordCount = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		//查询
		List<KonkaPhotoUpload> entityList = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		//导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + EncryptUtils.encodingFileName("拍照查询") + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
			
			List<KonkaPhotoUpload> entityList1 = super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUploadLBPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			Integer count=entityList1.size();
			if (count!=null&&count>0) {
				return new ActionForward("/admin/KonkaPhotoUpload/list_report.jsp");
			}else{
				//super.renderJavaScript(response, "alert('没有数据要导出！');history.back();");
				return null;
			}
		}
		
		
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
		request.setAttribute("photoTypes", photoTypes);
		return new ActionForward("/admin/KonkaPhotoUpload/list1.jsp");
	}
	public ActionForward save(ActionMapping mapping, ActionForm form,              //保存
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		
		String id = (String) dynaBean.get("id");
		String mod_id = (String)dynaBean.get("mod_id");
		String type_id = (String)dynaBean.get("type_id");
		String r3_code_name = (String)dynaBean.get("r3_code_name");
		String shop_id_name = (String)dynaBean.get("shop_id_name");
		String report_memo = (String)dynaBean.get("report_memo");
		String report_date = (String)dynaBean.get("report_date");
		String remark = (String)dynaBean.get("remark");
		String status = (String)dynaBean.get("status");
		
		if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			if (StringUtils.isNotEmpty(status)&&StringUtils.isNumeric(status)) {
				if(Integer.valueOf(status)==1){
					return mapping.findForward("list");
				}else {
					return mapping.findForward("list1");
				}
			}
		}
        resetToken(request);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//判断session是否超时
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		
		KonkaPhotoUpload entity = new KonkaPhotoUpload();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.parseLong(id));
		}
		VOrgOfDept t=new VOrgOfDept();
		     t.setCur_dept_id(userInfo.getDept_id());
		t=super.getFacade().getVOrgOfDeptService().getVOrgOfDept(t);
		
		if (null!=id&&StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		if (StringUtils.isNotBlank(type_id)) {
			entity.setType_id(Long.valueOf(type_id));
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
		String shoptemp[]=shop_id_name.split("#");
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
			entity.setReport_memo(report_memo);
		}
		if (StringUtils.isNotBlank(report_date)) {
			entity.setReport_date(df.parse(report_date));
		}
		if (StringUtils.isNotBlank(remark)) {
			entity.setRemark(remark);
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
		entity.setIs_del(0);
		if (StringUtils.isNotEmpty(id)) { //修改
			entity.setUpdate_user_id(userInfo.getId());
			entity.setUpdate_user_name(userInfo.getUser_name());
			entity.setUpdate_date(new Date());
			int updateCount=super.getFacade().getKonkaPhotoUploadService().modifyKonkaPhotoUpload(entity);//更新
//			跟新记录数大于零表示跟新成功
/*			if (updateCount>0) {
//				取出关联的附件集合
				Attachment att=new Attachment();
				att.setLink_id(Long.valueOf(id));
				
				List<Attachment> oldatt= super.getFacade().getAttachmentService().getAttachmentList(att);
				List<Long> attr_ids=new ArrayList<Long>();
//				临时去除附件记录的id
				for (Attachment attachment : oldatt) {
					attr_ids.add(attachment.getId());
				}
//				单条删除所有附件
				for (int i = 0; i < attr_ids.size(); i++) {
					att=new Attachment();
					att.setId(attr_ids.get(i));
					super.getFacade().getAttachmentService().removeAttachment(att);
				}
			}*/
			// 拿到上传的图片附件
			List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
			Attachment attachment = null;
			if (null != uploadFileList && uploadFileList.size() > 0) {
				for (UploadFile uploadFile : uploadFileList) {
					attachment = new Attachment();
					attachment.setLink_id(Long.valueOf(id));
					attachment.setFile_name(uploadFile.getFileName());
					attachment.setFile_type(uploadFile.getContentType());
					attachment.setFile_size(new Integer(uploadFile.getFileSize()));
					attachment.setSave_path(uploadFile.getFileSavePath());
					attachment.setSave_name(uploadFile.getFileSaveName());
					attachment.setLink_tab("KONKA_PHOTO_UPLOAD");
					attachment.setFile_desc(uploadFile.getFormName());
					Short isdel = new Short("0");
					attachment.setDel_mark(isdel);
					super.getFacade().getAttachmentService().createAttachment(attachment);
				}
			}
		}else {  
			//新增
			Long newid=super.getFacade().getKonkaPhotoUploadService().createKonkaPhotoUpload(entity);

			// 拿到上传的图片附件
			List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
			Attachment attachment = null;
			if (null != uploadFileList && uploadFileList.size() > 0) {
				for (UploadFile uploadFile : uploadFileList) {
					attachment = new Attachment();
					attachment.setLink_id(newid);
					attachment.setFile_name(uploadFile.getFileName());
					attachment.setFile_type(uploadFile.getContentType());
					attachment.setFile_size(new Integer(uploadFile.getFileSize()));
					attachment.setSave_path(uploadFile.getFileSavePath());
					attachment.setSave_name(uploadFile.getFileSaveName());
					attachment.setLink_tab("KONKA_PHOTO_UPLOAD");
					attachment.setFile_desc(uploadFile.getFormName());
					Short isdel = new Short("0");
					attachment.setDel_mark(isdel);
					super.getFacade().getAttachmentService().createAttachment(attachment);
				}
			}
		    super.saveMessage(request, "entity.updated");			
		}
		StringBuffer pathBuffer = new StringBuffer();
		
	    pathBuffer.append("/admin/KonkaPhotoUpload.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,                  //新增
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.saveToken(request);
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String type_id=(String) dynaBean.get("type_id");
		String status=(String) dynaBean.get("status");
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

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dynaBean.set("cur_report_date", new Date());
		
		
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo, 1);
		request.setAttribute("custList", custList);// 客户
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo, 1);
		request.setAttribute("storeList", storeList);
		
		request.setAttribute("photoTypes", photoTypes);
	    dynaBean.set("status", status);
		request.setAttribute("type_id", type_id);
		return mapping.findForward("input");
	}
	
	//修改
	public ActionForward edit(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//导航信息
		setNaviStringToRequestScope(form, request);
		//防止重复提交
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String)dynaBean.get("mod_id");
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
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
		KonkaPhotoUpload entity=new KonkaPhotoUpload();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			entity=super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUpload(entity);
			dynaBean.set("cur_report_date", entity.getReport_date());
		}
		//		可视图片类型
		List<Map<String, String>> photoTypes=super.getFacade().getKonkaPhotoUploadTypeService().getVisiblePhotoType(photoType);
		//拿到附件
		if (StringUtils.isNotBlank(id)) {
			Attachment attr=new Attachment();
			attr.setLink_id(Long.valueOf(id));
			attr.setLink_tab("KONKA_PHOTO_UPLOAD");
			attr.setDel_mark(Short.valueOf("0"));
			List<Attachment> attachmentsList=super.getFacade().getAttachmentService().getAttachmentList(attr);
			dynaBean.set("attachmentsList", attachmentsList);	
		}
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo, 1);
		request.setAttribute("custList", custList);// 客户
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo, 1);
		request.setAttribute("storeList", storeList);
		
		request.setAttribute("photoTypes", photoTypes);
		
		request.setAttribute("type_id", entity.getType_id());
		request.setAttribute("r3_code_name", entity.getR3_code()+"#"+entity.getCustomer_name());
		request.setAttribute("shop_id_name", entity.getStore_id()+"#"+entity.getStore_name());
		
		request.setAttribute("r3_code", entity.getR3_code());
		request.setAttribute("shop_id", entity.getStore_id()==null?"":StringUtils.replace(entity.getStore_id()+"", "191919", ""));
		dynaBean.set("mod_id", mod_id);
		entity.setQueryString(super.serialize(request, "id","method"));
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}
	
	//查看
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String)dynaBean.get("mod_id");
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
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
		}
		KonkaPhotoUpload entity=new KonkaPhotoUpload();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
			entity=super.getFacade().getKonkaPhotoUploadService().getKonkaPhotoUpload(entity);
			dynaBean.set("cur_report_date", entity.getReport_date());
		}
		//		可视图片类型
		List<Map<String, String>> photoTypes=super.getFacade().getKonkaPhotoUploadTypeService().getVisiblePhotoType(photoType);
		
		//拿到附件
		if (StringUtils.isNotBlank(id)) {
			Attachment attr=new Attachment();
			attr.setLink_id(Long.valueOf(id));
			attr.setLink_tab("KONKA_PHOTO_UPLOAD");
			attr.setDel_mark(Short.valueOf("0"));
			List<Attachment> attachmentsList=super.getFacade().getAttachmentService().getAttachmentList(attr);
			dynaBean.set("attachmentsList", attachmentsList);	
		}
		// 通过用户找客户
		List<KonkaR3Shop> custList = getcust(userInfo, 1);
		request.setAttribute("custList", custList);// 客户
		// 通过用户找终端
		List<KonkaR3Store> storeList = getShop(userInfo, 1);
		request.setAttribute("storeList", storeList);
		
		request.setAttribute("photoTypes", photoTypes);
		
		request.setAttribute("type_id", entity.getType_id());
		request.setAttribute("r3_code_name", entity.getR3_code()+"#"+entity.getCustomer_name());
		request.setAttribute("shop_id_name", entity.getStore_id()+"#"+entity.getStore_name());
		entity.setQueryString(super.serialize(request, "id","method"));
		super.copyProperties(form, entity);
		dynaBean.set("mod_id", mod_id);
		return mapping.findForward("view");
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
