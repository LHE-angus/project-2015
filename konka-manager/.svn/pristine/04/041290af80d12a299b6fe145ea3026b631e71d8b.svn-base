package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class KonkaBaseTypeDataAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("_is_del", "0");
		dynaBean.set("_is_lock", "0");
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String _par_type_id = (String) dynaBean.get("_par_type_id");
		String _type_name_like = (String) dynaBean.get("_type_name_like");
		String _par_type_name_like = (String) dynaBean.get("_par_type_name_like");
		String _is_del = (String) dynaBean.get("_is_del");
		String _is_lock = (String) dynaBean.get("_is_lock");
		String _begin_date = (String) dynaBean.get("_begin_date");
		String _end_date = (String) dynaBean.get("_end_date");
		String _field1 = (String) dynaBean.get("_field1");//属性
		String _field2 = (String) dynaBean.get("_field2");//级别
		String excel_all = (String) dynaBean.get("excel_all");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		if (StringUtils.isNotBlank(_par_type_id)) {
			entity.setPar_type_id(Long.parseLong(_par_type_id));
			dynaBean.set("_par_type_id", _par_type_id);
		}
		if (StringUtils.isNotBlank(_type_name_like)) {
			entity.getMap().put("_type_name_like", _type_name_like);
			dynaBean.set("_type_name_like", _type_name_like);
		}
		if (StringUtils.isNotBlank(_par_type_name_like)) {
			entity.getMap().put("_par_type_name_like", _par_type_name_like);
			dynaBean.set("_par_type_name_like", _par_type_name_like);
		}
		if (StringUtils.isNotBlank(_is_del) && isValidInt(_is_del)) {
			entity.setIs_del(Integer.valueOf(_is_del));
			dynaBean.set("_is_del", _is_del);
		}
		if (StringUtils.isNotBlank(_is_lock) && isValidInt(_is_lock)) {
			entity.setIs_lock(Integer.valueOf(_is_lock));
			dynaBean.set("_is_lock", _is_lock);
		}
		if (StringUtils.isNotBlank(_begin_date)) {
			entity.getMap().put("_begin_date", _begin_date);
			dynaBean.set("_begin_date", _begin_date);
		}
		if (StringUtils.isNotBlank(_end_date)) {
			entity.getMap().put("_end_date", _end_date);
			dynaBean.set("_end_date", _end_date);
		}
		if (StringUtils.isNotBlank(_field1)) {
			entity.getMap().put("_field1", _field1);
			dynaBean.set("_field1", _field1);
		}
		//级别
		if (StringUtils.isNotBlank(_field2)) {
			entity.getMap().put("_field2", _field2);
			dynaBean.set("_field2", _field2);
		}
		Long recordCount = super.getFacade().getKonkaBaseTypeDataService().getKonkaMobileCustVisitLBCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("基础类别数据表")
					+ ".xls");
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaBaseTypeData> entityList1 = super.getFacade().getKonkaBaseTypeDataService()
					.getKonkaMobileCustVisitPaginatedLBList(entity);
			request.setAttribute("allList", entityList1);
			return new ActionForward("/admin/KonkaBaseTypeData/listForReport.jsp");
		}
		List<KonkaBaseTypeData> entityList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaMobileCustVisitPaginatedLBList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		super.copyProperties(entity, form);
		// 拿到上传的图片附件
		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				attachment = new Attachment();
				attachment.setFile_name(uploadFile.getFileName());
				attachment.setFile_type(uploadFile.getContentType());
				attachment.setFile_size(new Integer(uploadFile.getFileSize()));
				attachment.setSave_path(uploadFile.getFileSavePath());
				attachment.setSave_name(uploadFile.getFileSaveName());
				attachment.setLink_tab("KONKA_BASE_TYPE_DATA");
				attachment.setFile_desc(uploadFile.getFormName());
				Short isdel = new Short("0");
				attachment.setDel_mark(isdel);
				attachmentList.add(attachment);
			}
		}
		
		
		if (StringUtils.isEmpty(id)) {
			entity.setAdd_user_id(user.getId());
			entity.setAdd_date(new Date());
			Long newid=super.getFacade().getKonkaBaseTypeDataService().createKonkaBaseTypeData(entity);
			
			// 添加附件
			if (null != attachmentList) {
				for (Attachment attachment1 : attachmentList) {
					if (null != newid) {
						attachment1.setLink_id(newid);
						super.getFacade().getAttachmentService()
								.createAttachment(attachment1);
					}
				}
			}
	        super.saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_user_id(user.getId());
			entity.setUpdate_date(new Date());
			super.getFacade().getKonkaBaseTypeDataService().modifyKonkaBaseTypeData(entity);
			// 添加附件
			if (null != attachmentList) {
				for (Attachment attachment1 : attachmentList) {
					if (StringUtils.isNotEmpty(id)&&StringUtils.isNumeric(id)) {
						attachment1.setLink_id(Long.valueOf(id));
						super.getFacade().getAttachmentService()
								.createAttachment(attachment1);
					}
				}
			}
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(entity);
		super.copyProperties(form, entity);
		
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("KONKA_BASE_TYPE_DATA");
		if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
		attachment.setLink_id(Long.valueOf(id));
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		request.setAttribute("fj_paths", JSON.toJSONString(attList));
		}
		dynaBean.set("_type_id", entity.getType_id());
		dynaBean.set("_par_type_id", ""+entity.getPar_type_id());
		dynaBean.set("_field2", entity.getField2());

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		entity.setId(Long.valueOf(id));
		entity.setIs_del(1);
		super.getFacade().getKonkaBaseTypeDataService().modifyKonkaBaseTypeData(entity);

		saveMessage(request, "entity.deleted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaBaseTypeDataService()
					.getKonkaBaseTypeData(entity);
			// 拿到图片
			Attachment attachment = new Attachment();
			attachment.setLink_tab("KONKA_BASE_TYPE_DATA");
			attachment.setLink_id(Long.valueOf(id));
			attachment.setDel_mark(new Short("0"));
			List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			request.setAttribute("fj_paths", JSON.toJSONString(attList));
		}
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);
		dynaBean.set("_type_id", entity.getType_id());
		dynaBean.set("_par_type_id", ""+entity.getPar_type_id());
		dynaBean.set("_field2", entity.getField2());

		return mapping.findForward("input");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_type_id = (String) dynaBean.get("par_type_id");
		
		KonkaBaseTypeData baseType=new KonkaBaseTypeData();
		if (StringUtils.isNotBlank(par_type_id)&&"0".equals(par_type_id)) {
			dynaBean.set("_field2", "1");// 默认一级节点
			dynaBean.set("field2", "1");// 默认一级节点
		} else {
			baseType.setType_id(Long.valueOf(par_type_id));
			baseType = super.getFacade().getKonkaBaseTypeDataService()
					.getKonkaBaseTypeData(baseType);
			if (null != baseType && null != baseType.getField2()
					&& StringUtils.isNumeric(baseType.getField2())) {
				dynaBean.set("_field2", ""
						+ (Integer.valueOf(baseType.getField2()) + 1));// 根据当前节点级别推算下一级级别
				dynaBean.set("field2", ""
						+ (Integer.valueOf(baseType.getField2()) + 1));// 根据当前节点级别推算下一级级别
			}
		}
		//查找当前节点的子节点
		baseType=new KonkaBaseTypeData();
		baseType.setPar_type_id(Long.valueOf(par_type_id));
		List<KonkaBaseTypeData> list=super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(baseType);
		if (null!=list&&list.size()>0&&null!=list.get(0)&&null!=list.get(0).getType_id()) {
			dynaBean.set("_type_id", list.get(0).getType_id()+1);//下一个节点的type_id
			dynaBean.set("type_id", list.get(0).getType_id()+1);//下一个节点的type_id
		}else{//第一个子节点定义生成规则
			dynaBean.set("_type_id",Long.valueOf(par_type_id)*100+1);//下一个节点的type_id
			dynaBean.set("type_id",Long.valueOf(par_type_id)*100+1);//下一个节点的type_id
		}
		if (StringUtils.isNotBlank(par_type_id)) {
			dynaBean.set("_par_type_id", par_type_id);
			dynaBean.set("par_type_id", par_type_id);
		}
		
		setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		return mapping.findForward("input");
	}

	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidInt(String str) {
		try {
			int _v = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidDouble(String str) {
		try {
			double _v = Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
