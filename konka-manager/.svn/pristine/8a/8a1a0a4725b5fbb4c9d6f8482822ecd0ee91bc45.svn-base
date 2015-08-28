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

import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 有流程的oa
 *
 */
public class FilesArchiveAction extends BaseMmtoaAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * 本功能可以查看当前用户的部门下面所有的文件 和 公告
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String add_endtime = (String) dynaBean.get("add_endtime");
		String add_starttime = (String) dynaBean.get("add_starttime");
		String apply_man = (String) dynaBean.get("apply_man");

		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		super.copyProperties(entity, form);

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// PeRoleUser peRoleUser = (PeRoleUser) request.getSession().getAttribute(Constants.ROLE_INFO);

		if (StringUtils.isNotBlank(add_starttime)) {
			entity.getMap().put("add_starttime", add_starttime);
		}
		if (StringUtils.isNotBlank(add_endtime)) {
			entity.getMap().put("add_endtime", add_endtime);
		}
		if (StringUtils.isNotBlank(apply_man)) {
			entity.getMap().put("apply_man", apply_man);
		}

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_lt_30 = false;
		boolean role_id_eq_30 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30) {
				role_id_lt_30 = true;
			}
			if (30 == peRoleUser.getRole_id()) {
				role_id_eq_30 = true;
			}
		}

		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());

		if (role_id_lt_30) {// 管理员或事业部
			if (GenericValidator.isLong(fgs_dept_id)) {
				entity.getMap().put("konka_dept_id", fgs_dept_id);
			} else {
				entity.getMap().put("konka_dept_id", "0");
			}
		} else {
			entity.getMap().put("konka_dept_id", getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id());
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

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesArchiveCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesArchivePaginatedList(entity);
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
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
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

		KonkaoaFilesProperty fp = new KonkaoaFilesProperty();

		// 文件属性
		// 11
		KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
		filesProperty.setLink_id(files.getId());
		filesProperty.setC_type(11);
		List<KonkaoaFilesProperty> filesPropertyList = getFacade().getKonkaoaFilesPropertyService()
				.getKonkaoaFilesPropertyList(filesProperty);
		request.setAttribute("filesProperty11List", filesPropertyList);

		// 12
		filesProperty.setC_type(12);
		fp = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesProperty(filesProperty);
		if (null != fp) {
			dynaBean.set("category12", fp.getC_index());
		}

		// 13
		filesProperty.setC_type(13);
		fp = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesProperty(filesProperty);
		if (null != fp) {
			dynaBean.set("category13", fp.getC_index());
		}

		// 14
		filesProperty.setC_type(14);
		fp = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesProperty(filesProperty);
		if (null != fp) {
			dynaBean.set("category14", fp.getC_index());
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
			dynaBean.set("dept_names", StringUtils.substringBeforeLast(dept_names, ","));
		}

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(files.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(
				attachment));

		// 审批记录显示
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		// fan.getMap().put("is_audit", "is_audit");
		request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));
		// .getKonkaoaFilesAuditNodeList(fan));


		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

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

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ne_30 = true;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 30L) {
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

			// super.createSysOperLog(request, "FILES", entity.getId(), mod_id, "删除", entity.toString());
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaoaFilesService().removeKonkaFilesArchiveFiles(entity);
			saveMessage(request, "entity.deleted");

			// super.createSysOperLog(request, "FILES", new Long(0), mod_id, "删除", "批量删除,".concat(entity.toString()));
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

}