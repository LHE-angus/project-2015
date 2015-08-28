package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaContractAuditRecord;
import com.ebiz.mmt.domain.KonkaContractManager;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderMeetingManager;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2014-2-24
 */

public class KonkaContractAuditRecordAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// 上报人、分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null != ui) {
			request.setAttribute("user_name", ui.getUser_name());
			request.setAttribute("user_id", ui.getId());
		}
		Long suqu = super.getFacade().getKonkaOrderMeetingManagerService()
				.getSequenceResult(new KonkaOrderMeetingManager());
		String order_s = suqu.toString();
		if (order_s.length() < 8) {
			int z_num = 8 - order_s.length();
			for (int i = 0; i < z_num; i++) {
				order_s = "0" + order_s;
			}
		}

		if (null != ui.getDept_id()) {

			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(ui.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

			dynaBean.set("dept_id", ui.getDept_id());
			dynaBean.set("dept_name", konkaDept.getDept_name());
			dynaBean.set("add_user_name", ui.getUser_name());

			// 分公司取得
			if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
				dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());

				// 单据编号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String order_num = "HT" + fgs_dept.getDept_sn() + sdf.format(new Date()) + order_s;
				dynaBean.set("bill_sn", order_num);
			}

		}

		dynaBean.set("add_date", new Date());

		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String qd_time_start = (String) dynaBean.get("qd_time_start");
		String qd_time_end = (String) dynaBean.get("qd_time_end");
		String con_name_like = (String) dynaBean.get("con_name_like");
		String con_sn_like = (String) dynaBean.get("con_sn_like");
		String c_type = (String) dynaBean.get("c_type");
		String audit_state = (String) dynaBean.get("audit_state");
		String ywy_name_like = (String) dynaBean.get("ywy_name_like");
		String ht_time_start = (String) dynaBean.get("ht_time_start");
		String ht_time_end = (String) dynaBean.get("ht_time_end");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String add_user_name_like = (String) dynaBean.get("add_user_name_like");
		String excel_all = (String) dynaBean.get("excel_all");

		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaContractManager entity = new KonkaContractManager();

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like", r3_code_like);
		}
		if (StringUtils.isNotBlank(qd_time_start)) {
			entity.getMap().put("qd_time_start", qd_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(qd_time_end)) {
			entity.getMap().put("qd_time_end", qd_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(con_name_like)) {
			entity.getMap().put("con_name_like", con_name_like);
		}
		if (StringUtils.isNotBlank(con_sn_like)) {
			entity.getMap().put("con_sn_like", con_sn_like);
		}
		if (StringUtils.isNotBlank(c_type)) {
			entity.setC_type(Long.valueOf(c_type));
		}
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(Integer.valueOf(audit_state));
		}
		if (StringUtils.isNotBlank(ywy_name_like)) {
			entity.getMap().put("ywy_name_like", ywy_name_like);
		}
		if (StringUtils.isNotBlank(add_user_name_like)) {
			entity.getMap().put("add_user_name_like", add_user_name_like);
		}
		if (StringUtils.isNotBlank(ht_time_start)) {
			entity.getMap().put("ht_time_start", ht_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(ht_time_end)) {
			entity.getMap().put("ht_time_end", ht_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_30 = false; // 非系统管理员
		boolean role_id_eq_60 = false; // 业务员
		// boolean role_id_eq_30 = false; // 分公司管理员
		// boolean role_id_eq_60 = false;
		boolean role_id_not_10_and_60 = false;
		String role_ids = "";
		for (PeRoleUser peRoleUser : peRoleUserList) {
			role_ids = role_ids + "," + peRoleUser.getRole_id();
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() != 60L && peRoleUser.getRole_id() >= 30L) {
				role_id_not_10_and_60 = true;
			}

		}
		if (role_id_eq_60) {// 业务员只能看自己的客户
			entity.setYwy_job_id(ui.getId().toString());
		}
		if (role_id_not_10_and_60) {// 业务员和总部管理员之外看自己分公司，总部管理员可以看全部
			KonkaDept kk = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			if (null != kk && null != kk.getDept_id()) {
				entity.setDept_id(kk.getDept_id());
			}
		}
		if (role_id_eq_30) {

		}

		entity.setIs_del(0);
		entity.setF_save_state(1);// 审核已经提交的

		Long recordCount = getFacade().getKonkaContractManagerService().getKonkaContractManagerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaContractManager> entityList = getFacade().getKonkaContractManagerService()
				.getKonkaContractManagerPaginatedList(entity);

		request.setAttribute("entityList", entityList);
		request.setAttribute("user_id", ui.getId());

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
						+ "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaContractManager> entityList1 = getFacade().getKonkaContractManagerService()
					.getKonkaContractManagerList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String file_state = (String) dynaBean.get("file_state");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaContractManager entity = new KonkaContractManager();

		super.copyProperties(entity, form);

		// 附件处理
		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });
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
			filesAttachmentList.add(filesAttachment);
		}
		entity.setAttachmentList(filesAttachmentList);
		entity.setF_save_state(Integer.valueOf(file_state));
		if ("1".equals(file_state)) {
			entity.setAudit_state(2);
		} else if ("2".equals(file_state)) {
			entity.setAudit_state(1);
		}

		if (null == entity.getId()) {
			entity.setIs_del(0);
			entity.setAdd_date(new Date());
			getFacade().getKonkaContractManagerService().createKonkaContractManager(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setUpdate_date(new Date());
			entity.setUpdate_user(ui.getId().toString());
			entity.setUpdate_user(ui.getUser_name());
			getFacade().getKonkaContractManagerService().modifyKonkaContractManager(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaContractManager entity = new KonkaContractManager();
		entity.setIs_del(0);
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaContractManagerService().getKonkaContractManager(entity);
		request.setAttribute("entity", entity);

		// 上报人、分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null != ui) {
			request.setAttribute("user_name", ui.getUser_name());
			request.setAttribute("user_id", ui.getId());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(Long.valueOf(id));
		attachment.setLink_tab("KONKA_CONTRACT_MANAGER");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaContractManager entity = new KonkaContractManager();
			entity.setIs_del(0);
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaContractManagerService().removeKonkaContractManager(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaContractManager entity = new KonkaContractManager();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaContractManagerService().removeKonkaContractManager(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward auditList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaContractAuditRecord entity = new KonkaContractAuditRecord();
			entity.setLink_id(Long.valueOf(id));
			List<KonkaContractAuditRecord> entityList = super.getFacade().getKonkaContractAuditRecordService()
					.getKonkaContractAuditRecordList(entity);
			request.setAttribute("entityList", entityList);
		}
		return new ActionForward("/../manager/admin/KonkaContractAuditRecord/list2.jsp");
	}

	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaContractManager entity = new KonkaContractManager();
			entity.setAudit_state(3);// 驳回
			entity.setId(Long.valueOf(id));
			KonkaContractAuditRecord kk = new KonkaContractAuditRecord();
			kk.setAudit_date(new Date());
			kk.setAudit_state(3);
			kk.setAudit_user_id(ui.getId());
			kk.setAudit_user_name(ui.getUser_name());
			kk.setLink_id(Long.valueOf(id));
			getFacade().getKonkaContractManagerService().modifyKonkaContractManagerAndAudit(entity, kk);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaContractManager entity = new KonkaContractManager();
			entity.setAudit_state(2);// 审核通过
			entity.setId(Long.valueOf(id));
			KonkaContractAuditRecord kk = new KonkaContractAuditRecord();
			kk.setAudit_date(new Date());
			kk.setAudit_state(2);
			kk.setAudit_user_id(ui.getId());
			kk.setAudit_user_name(ui.getUser_name());
			kk.setLink_id(Long.valueOf(id));
			getFacade().getKonkaContractManagerService().modifyKonkaContractManagerAndAudit(entity, kk);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaContractManager entity = new KonkaContractManager();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaContractManagerService().getKonkaContractManager(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_CONTRACT_MANAGER");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		return mapping.findForward("view");

	}

}