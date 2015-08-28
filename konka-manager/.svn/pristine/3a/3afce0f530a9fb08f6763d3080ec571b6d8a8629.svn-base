package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hui,Gang
 * @version Build 2010-12-16
 */
public class AuditIngFilesAction extends BaseMmtoaAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String map_file_status = (String) dynaBean.get("map_file_status");

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);
		if (ui.getDept_id() != 17761) {// 信息中心可以查看所有的文件
			entity.getMap().put("audit_user_id", ui.getId());
			entity.getMap().put("is_xxzx", "true");
		}
		entity.setFile_type(0);// 表示查询的是文件提交
		// entity.getMap().put("lt_file_status", 3);
		entity.getMap().put("map_file_status", map_file_status);
		entity.getMap().put("file_title_like", (String) dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));
		entity.getMap().put("submit_user_like", (String) dynaBean.get("submit_user_like"));
		entity.getMap().put("shopIds", null);

		entity.setIs_del(0);

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesPaginatedListForAuditIng(entity);

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List",
				getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
		category.setC_type(12);
		request.setAttribute("category12List",
				getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);

		// super.getModPopeDom(form, request);
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles files = new KonkaoaFiles();
		files.setId(new Long(id));
		files.getMap().put("qingjia", 1);
		files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
		if (null == files) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

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
			
			logger.info("1111111=================={}","11111");
			KonkaoaFilesProperty appProperty = new KonkaoaFilesProperty();
			appProperty.setLink_id(files.getId());
			appProperty.setAdd_type(1L);
			appProperty.setC_type(0);
			appProperty.setC_index(kfp.getC_index());
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


		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
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