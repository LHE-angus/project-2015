package com.ebiz.mmt.web.struts.manager.oa;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.util.EncryptUtils;
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
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String outexcl=(String) dynaBean.get("outexcl");
		
		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);

		entity.setFile_type(0);// 表示查询的是文件提交
		entity.getMap().put("map_file_status", map_file_status);
		entity.getMap().put("file_title_like", (String) dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));
		
		entity.getMap().put("st_archive_datetime", (String) dynaBean.get("st_archive_datetime"));
		entity.getMap().put("en_archive_datetime", (String) dynaBean.get("en_archive_datetime"));
		
		entity.getMap().put("submit_user_like", (String) dynaBean.get("submit_user_like"));
		entity.getMap().put("shopIds", null);

		entity.setIs_del(0);

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_lt_30 = false;
		boolean role_id_eq_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30) {
				role_id_lt_30 = true;
			} else if (30 == peRoleUser.getRole_id()) {
				role_id_eq_30 = true;
			}
		}

		if (role_id_lt_30) {// 管理员或事业部
			entity.getMap().put("konka_dept_id", fgs_dept_id);
		} else {
			KonkaDept _dept = this.getSuperiorForDeptType(ui.getDept_id(), 3);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.getMap().put("konka_dept_id", _dept.getDept_id());
			}
		}

		if (GenericValidator.isLong(jyb_dept_id)) {
			entity.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			entity.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		}

		 // 数据级别判断开始
        Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                entity.getMap().put("ywy_user_id", ui.getId());
                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("user_dept_id", __dept_id);
                    
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("user_dept_id", __dept_id);
               
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("ywy_user_id", ui.getId());
                
                break;
            default:
                // 出错
        }
        
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesPaginatedListForAuditIng(entity);
		
		
		if (StringUtils.isNotBlank(outexcl) && "1".equals(outexcl)) {
			if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
            String filename = "上报文件";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
			        + EncryptUtils.encodingFileName(filename) + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(recordCount.intValue());
            entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAuditIng(entity);
			request.setAttribute("allList", entityList);
			Integer count = entityList.size();
			if (count != null && count > 0) {
                return new ActionForward("/oa/AuditIngFiles/list_report.jsp");
			} else {
				// super.renderJavaScript(response,
				// "alert('没有数据要导出！');history.back();");
				return null;
			}
		}

		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		if (38 == ui.getDept_id() && role_id_eq_30) {
			request.setAttribute("canDelete", 1);
		}

		if (StringUtils.isNotBlank(fgs_dept_id)) {
			dynaBean.set("fgs_dept_id", fgs_dept_id);
		}
		
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "4")) {
		// return super.checkPopedomInvalid(request, response);
		// }

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// PeRoleUser peRoleUser =
		// (PeRoleUser)request.getSession().getAttribute(Constants.ROLE_INFO);

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ne_30 = true;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 30) {
				role_id_ne_30 = false;
				break;
			}
		}

		if (38 != ui.getDept_id() || role_id_ne_30) {
			String msg = "您不具备删除文件的权限！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			entity.setId(new Long(id));
			super.getFacade().getKonkaoaFilesService().removeKonkaoaFiles(entity);
			saveMessage(request, "entity.deleted");

			// super.createSysOperLog(request, "FILES", entity.getId(), mod_id,
			// "删除", entity.toString());
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaoaFilesService().removeKonkaoaFiles(entity);
			saveMessage(request, "entity.deleted");

			// super.createSysOperLog(request, "FILES", new Long(0), mod_id,
			// "删除", "批量删除,".concat(entity.toString()));
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			super.toWord(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}