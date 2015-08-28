package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaDocRecipient;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class SsuedDocumentAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String title = (String)dynaBean.get("title");
		String file_code = (String)dynaBean.get("file_code");
		String mod_type = (String)dynaBean.get("mod_type");
		String mod_id = (String) dynaBean.get("mod_id");
		
		dynaBean.set("mod_id", mod_id);
		request.setAttribute("title", title);
		request.setAttribute("file_code", file_code);
		dynaBean.set("mod_type", mod_type);
		dynaBean.set("file_code", file_code);
		dynaBean.set("title", title);
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		super.copyProperties(entity, form);
		
		String desc_type =ui.getUser_name()+"查询";
		if(StringUtils.isNotBlank(title)){
			desc_type+=("标题:"+title);
		}if(StringUtils.isNotBlank(file_code)){
			desc_type+=("编号:"+file_code);
		}if(StringUtils.isNotBlank(mod_type)){
			if("notice".equals(mod_type)){
				desc_type+="文件类别:通知公告";
			}else if("file".equals(mod_type)){
				desc_type+="文件类别:下发文件 ";
			}else{
				desc_type+="文件类别:费用申请 ";
			}
		}
		
		super.createMobileSysOperLog(request, "KONKA_SX_OPER_LOG", ui.getId(), "720500", desc_type, BeanUtils.describe(entity).toString());
		
		String page = (String) dynaBean.get("page");
		String forward = (String) dynaBean.get("forward");
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isEmpty(page))
			request.setAttribute("page", currentPage);
		else {
			currentPage = (Integer.parseInt(page))
					+ (Integer.parseInt(forward));
			request.setAttribute("page", currentPage);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);

		if (recordCount % pageSize > 0)
			request.setAttribute("pagelimit", (recordCount / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (recordCount / pageSize));
		if (recordCount > 0) {
			List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
			.getKonkaoaSsuedDocumentPaginatedList(entity);
			request.setAttribute("entityList", entityList);
		}
		
		return mapping.findForward("list");
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);

		KonkaoaDocInfo entity = new KonkaoaDocInfo();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfo(entity);
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.createMobileSysOperLog(request, "KONKAOA_DOC_INFO", entity.getId(), "720500", "" + "查看：" +ui.getUser_name()+"查看了编号:"+entity.getFile_no()+"的通知公告", BeanUtils.describe(entity).toString());

		KonkaoaDocRecipient dr = new KonkaoaDocRecipient();
		dr.setLink_id(Long.valueOf(id));
		dr.setIs_view(1);
		super.getFacade().getKonkaoaDocRecipientService().modifyKonkaoaDocRecipient(dr);

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		// 文件类别
		category.setC_type(11); // 11：文件类别
		List<KonkaoaCategory> fileTypeList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("fileTypeList", fileTypeList);

		// 重要级别impLevel
		category.setC_type(12); // 12：重要级别
		List<KonkaoaCategory> impLevelList = super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryList(category);
		request.setAttribute("impLevelList", impLevelList);

		super.copyProperties(form, entity);

		String file_type = entity.getFile_type();
		String[] file_types = StringUtils.split(file_type, ",");

		request.setAttribute("file_types", file_types);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(new Long(id));
		request.setAttribute("attachmentList",
				getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));
		if (null != entity.getReceive_type() && entity.getReceive_type() == 1) {
			// 公文下发接收人
			KonkaoaDocRecipient docRecipient = new KonkaoaDocRecipient();
			docRecipient.setReceive_type(0);
			docRecipient.setLink_id(entity.getId());
			docRecipient.setReceive_user_type(0);// 接收类型为：人
			List<KonkaoaDocRecipient> drList = super.getFacade().getKonkaoaDocRecipientService()
					.getKonkaoaDocRecipientList(docRecipient);

			Long[] user_ids = new Long[drList.size()];
			String[] user_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					user_ids[i] = drList.get(i).getReceive_id();
					user_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));

			docRecipient.setReceive_user_type(1);// 接收类型为：部门
			drList = super.getFacade().getKonkaoaDocRecipientService().getKonkaoaDocRecipientList(docRecipient);

			Long[] dept_ids = new Long[drList.size()];
			String[] dept_names = new String[drList.size()];
			if (null != drList && drList.size() > 0) {
				for (int i = 0; i < drList.size(); i++) {
					dept_ids[i] = drList.get(i).getReceive_id();
					dept_names[i] = drList.get(i).getReceive_user();
				}
			}
			dynaBean.set("dept_ids", StringUtils.join(dept_ids, ","));
			dynaBean.set("dept_names", StringUtils.join(dept_names, ","));
		}
		return mapping.findForward("input");
	}
	
	public ActionForward viewFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		
		super.createMobileSysOperLog(request, "KONKAOA_FILES", files.getId(), "720500", "" + "查看：" +ui.getUser_name()+"查看了编号:"+files.getFile_no()+"的文件", BeanUtils.describe(files).toString());
	
		super.copyProperties(form, files);
		super.setCategoryListToRequestScope(request);

		// 费用申请：费用类别
		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(0);// 获取费用申请的费用类别信息表
		request.setAttribute("categoryList", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));

		// 文件属性
		// 0,选择多种费用类别
		KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
		filesProperty.setLink_id(files.getId());
		filesProperty.setC_type(0);
		filesProperty.setAdd_type(0L);
		List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService()
				.getKonkaoaFilesPropertyList(filesProperty);

		KonkaoaFilesProperty property = new KonkaoaFilesProperty();
		property.setLink_id(files.getId());
		property.setC_type(0);
		property.setAdd_type(1L);
		List<KonkaoaFilesProperty> propertyList = super.getFacade().getKonkaoaFilesPropertyService()
				.getKonkaoaFilesPropertyList(property);
		for (KonkaoaFilesProperty kfc : propertyList) {
			PeProdUser ppu = new PeProdUser();
			ppu.setId(kfc.getAdd_user_id());
			ppu = super.getFacade().getPeProdUserService().getPeProdUser(ppu);
			if (ppu != null) {
				kfc.getMap().put("real_name", ppu.getReal_name());
			}
		}
		request.setAttribute("propertyList", propertyList);

		for (KonkaoaFilesProperty kfp : filesPropertyList) {
			KonkaoaCategory kc = new KonkaoaCategory();
			kc.setC_index(kfp.getC_index());
			kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
			kfp.getMap().put("c_name", kc.getC_name());

			KonkaoaFilesProperty appProperty = new KonkaoaFilesProperty();
			appProperty.setLink_id(files.getId());
			appProperty.setAdd_type(1L);
			appProperty.setC_type(0);
			appProperty.setC_index(kfp.getId());
			List<KonkaoaFilesProperty> appList = getFacade().getKonkaoaFilesPropertyService()
					.getKonkaoaFilesPropertyList(appProperty);
			if (0 != appList.size()) {
				kfp.getMap().put("appList", appList);

			}
		}
		request.setAttribute("filesPropertyList", filesPropertyList);

		// 费用申请相关信息
		KonkaExpenseClaims kec = new KonkaExpenseClaims();
		kec.setFile_id(files.getId());
		kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);
		if (null != kec) {
			request.setAttribute("konkaExpenseClaims", kec);
			// 获得对应的申请网点的名称
			if (null != kec.getR3_shop_id()) {
				KonkaR3Shop shop = new KonkaR3Shop();
				shop.setId(kec.getR3_shop_id());
				shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
				if (null != shop) {
					request.setAttribute("shop_name", shop.getCustomer_name());
				}
			}
		}

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(files.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = getFacade().getKonkaoaFilesRecipientService()
				.getKonkaoaFilesRecipientList(fr);
		if (null != filesRecipientList) {
			String fa_ids = "", fa_names = "", dept_ids = "", dept_names = "";
			for (KonkaoaFilesRecipient _fr : filesRecipientList) {
				switch (_fr.getReceive_user_type()) {
				case 0:
					fa_ids = fa_ids.concat(_fr.getReceive_id().toString()).concat(",");
					fa_names = fa_names.concat(_fr.getReceive_user().toString()).concat(",");
					break;
				case 1:
					dept_ids = dept_ids.concat(_fr.getReceive_id().toString()).concat(",");
					dept_names = dept_names.concat(_fr.getReceive_user().toString()).concat(",");
					break;
				}

			}
			dynaBean.set("fa_ids", fa_ids);
			dynaBean.set("fa_names", StringUtils.substringBeforeLast(fa_names, ","));
			dynaBean.set("dept_ids", dept_ids);
			if (kec != null && kec.getColumn_6() != null) {
				dynaBean.set("column_6", kec.getColumn_6());
			}
			dynaBean.set("dept_names", StringUtils.substringBeforeLast(dept_names, ","));
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(files.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审批记录显示
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));
		// .getKonkaoaFilesAuditNodeList(fan));

		// 审批人查询
		KonkaoaFilesAuditNode _fan_ = new KonkaoaFilesAuditNode();
		_fan_.setLink_id(files.getId());
		_fan_.setAudit_type(0);
		request.setAttribute("_filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan_));


		dynaBean.set("mod_id", mod_id);

		// 设置为已查看状态
		KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
		filesRecipient.setLink_id(files.getId());
		filesRecipient.setReceive_id(ui.getId());
		filesRecipient.setView_date_time(new Date());
		getFacade().getKonkaoaFilesRecipientService().modifyKonkaoaFilesRecipient(filesRecipient);

		return mapping.findForward("view");
	}
}