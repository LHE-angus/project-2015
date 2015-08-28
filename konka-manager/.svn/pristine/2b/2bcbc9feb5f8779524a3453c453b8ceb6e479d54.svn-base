
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOaModuleType;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * 
 * 客户管理--业务通--请假管理 <br>
 * 请假申请
 */
public class FilesSubmitAction extends BaseMmtoaAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		// super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String c_index_11 = (String) dynaBean.get("c_index_11");
		String c_index_12 = (String) dynaBean.get("c_index_12");
		String map_file_status = (String) dynaBean.get("map_file_status");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");// 判断审批流程

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);
		entity.getMap().put("file_title_like", dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", dynaBean.get("en_submit_datetime"));
		entity.getMap().put("c_index_11", c_index_11);
		entity.getMap().put("c_index_12", c_index_12);
		entity.getMap().put("map_file_status", map_file_status);
		if (StringUtils.isEmpty(c_index_11) && StringUtils.isEmpty(c_index_12)) {
			entity.getMap().put("not_have_query", "not_have_query");
		}
		if (StringUtils.isNotBlank(p_audit_node_id)) {// 不是提交文件
			entity.setAudit_node_id(Long.parseLong(p_audit_node_id));
			request.setAttribute("p_audit_node_id", p_audit_node_id);
		}

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));
		category.setC_type(12);
		request.setAttribute("category12List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));

		entity.setIs_del(0);
		entity.setSubmit_user_id(ui.getId());
		entity.getMap().put("qingjia", 1);// 有值查请假数据

		Pager pager = (Pager) dynaBean.get("pager");

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForPaginatedList(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
        String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");// null

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		dynaBean.set("submit_dept", konkaDept.getDept_name());
		dynaBean.set("submit_user", ui.getReal_name());
		//dynaBean.set("is_node", dynaBean.get("is_node"));
        dynaBean.set("is_node", "1");// 有流程的
		// 分公司取得
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}
		// 当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		dynaBean.set("yymm", ym.substring(2, ym.length()));
		request.setAttribute("submit_datetime", new Date());

		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		// 审批流程
		if (StringUtils.isBlank(p_audit_node_id)) {// 提交文件

			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
            filesAuditNode.setAudit_type(2);// 请假审批
			filesAuditNode.setNode_type(2);
            // filesAuditNode.setAudit_type(2);
			if (kDept != null) {
				filesAuditNode.setDept_id(kDept.getDept_id());
			} else {
				filesAuditNode.setDept_id(0L);
			}

            // 获取流程数(非流程节点)
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList) {
				request.setAttribute("KonkaoaFilesAuditNodeList", filesAuditNodeList);
			}

			// 模板
			KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
			konkaOaModuleType.setIs_del(0);
			if (kDept != null) {
				konkaOaModuleType.setDept_id(kDept.getDept_id());
			} else {
				konkaOaModuleType.setDept_id(0L);
			}
			konkaOaModuleType.setOa_type(Long.valueOf("10001801"));
			List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService()
					.getKonkaOaModuleTypeList(konkaOaModuleType);

			if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
				request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
			}
		} else {// epp审核流程
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(2);
			filesAuditNode.setLink_id(Long.parseLong(p_audit_node_id));
			filesAuditNode.setDept_id(kDept.getDept_id());
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList && filesAuditNodeList.size() > 0) {
				request.setAttribute("filesAuditNodeId", filesAuditNodeList.get(0).getLink_id());
				request.setAttribute("filesAuditNodeName", filesAuditNodeList.get(0).getAudit_node_name());
				request.setAttribute("p_audit_node_id", p_audit_node_id);
			} else {
				super.renderJavaScript(response, "alert('参数丢失，请联系管理员！');history.back()");
				return null;
			}

			// 模板
			KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
			konkaOaModuleType.setIs_del(0);
			if (kDept != null) {
				konkaOaModuleType.setDept_id(kDept.getDept_id());
            }
			konkaOaModuleType.setOa_type(Long.valueOf(10001801));
			List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService()
					.getKonkaOaModuleTypeList(konkaOaModuleType);

			if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
				request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
			}
		}

		request.setAttribute("is_add", "2");

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String id = (String) dynaBean.get("id");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles file = new KonkaoaFiles();
		file.setId(new Long(id));
		file.getMap().put("qingjia", 1);
		file = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(file);
		if (null == file) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(form, file);
		request.setAttribute("submit_datetime", file.getSubmit_datetime());
		dynaBean.set("audit_node_id", file.getAudit_node_id());

		super.setCategoryListToRequestScope(request);

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(file.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService()
				.getKonkaoaFilesRecipientList(fr);
		if (null != filesRecipientList) {
			String fa_ids = "", fa_names = "", dept_ids = "", dept_names = "";
			// song_ids = "", song_names = "", cheng_ids = "", cheng_names = "";
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
		attachment.setLink_id(file.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setLink_id(file.getId());
		_fan.setAudit_type(1);

        // 会签不做了
        // request.setAttribute("countersignNodeList",
        // super.getFacade().getKonkaoaFilesAuditNodeService()
        // .getKonkaoaFilesAuditNodeList(_fan));

		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(file.getId());
		fan.setAudit_type(0);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan));

		// 审批流程
		if (StringUtils.isBlank(p_audit_node_id)) {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(2);
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null) {
				filesAuditNode.setDept_id(kDept.getDept_id());
			} else {
				filesAuditNode.setDept_id(0L);
			}
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList) {
				request.setAttribute("KonkaoaFilesAuditNodeList", filesAuditNodeList);
			}
		} else {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(2);
			filesAuditNode.setLink_id(Long.parseLong(p_audit_node_id));
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			// if (kDept != null) {
			// filesAuditNode.setDept_id(kDept.getDept_id());
			// } else {
			// filesAuditNode.setDept_id(0L);
			// }
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList && filesAuditNodeList.size() > 0) {
				request.setAttribute("filesAuditNodeId", filesAuditNodeList.get(0).getLink_id());
				request.setAttribute("filesAuditNodeName", filesAuditNodeList.get(0).getAudit_node_name());
				request.setAttribute("p_audit_node_id", p_audit_node_id);
			}

			// 模板
			KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
			konkaOaModuleType.setIs_del(0);
			if (kDept != null) {
				konkaOaModuleType.setDept_id(kDept.getDept_id());
			} else {
				konkaOaModuleType.setDept_id(0L);
			}
			konkaOaModuleType.setOa_type(Long.valueOf("10001801"));
			List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService()
					.getKonkaOaModuleTypeList(konkaOaModuleType);

			if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
				request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
			}
		}
		return mapping.findForward("input");
	}

    /**
     * 
     * 文件提交保存方法 此时单据状态为0,单据审批类型audit_type为0(审批),1(会签,渠道系统不做)
     */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);
        entity.setFile_type(2);// 请假2,文件0,费用1
		DynaBean dynaBean = (DynaBean) form;
		String audit_node_id = (String) dynaBean.get("audit_node_id");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");

        // 是保存还是直接提交审批,区别是单据保存时,单据状态为0,还是1
        String send_to_process = (String) dynaBean.get("send_to_process");



		logger.info("=============audit_node_id=" + audit_node_id);
		logger.info("=============p_audit_node_id=" + p_audit_node_id);
		if (StringUtils.isBlank(audit_node_id)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}

		String mod_id = (String) dynaBean.get("mod_id");

		List<KonkaoaFilesProperty> filesPropertyList = new ArrayList<KonkaoaFilesProperty>();

		entity.setFilesPropertyList(filesPropertyList);
		entity.setAudit_node_id(Long.parseLong(audit_node_id));

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// 附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {

			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			// attachment.setLink_tab(uploadFile.getFormName());
			attachment.setLink_tab("FILES");
			attachment.setAdd_user_name(ui.getUser_name());
			attachment.setAdd_user_id(ui.getId());
			attachmentList.add(attachment);
		}
		entity.setAttachmentList(attachmentList);

		// 审批结点
		List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();

		// 审批人
		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setLink_id(Long.parseLong(audit_node_id));
		_fan.setAudit_type(2);
		List<KonkaoaFilesAuditNode> auditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan);

		if (null != auditNodeList && auditNodeList.size() > 0) {
			for (int i = 0; i < auditNodeList.size(); i++) {
				Long user_id = new Long(auditNodeList.get(i).getAudit_user_id());
				if (null != user_id) {
					KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
					fau.setAudit_level(new Long(i + 1));
					fau.setAudit_user_id(user_id);
					fau.setAudit_user(auditNodeList.get(i).getAudit_user());
					fau.setAudit_type(0);
					filesAuditNodeList.add(fau);
				}
			}
		} else {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}

        // 非常重要,数年前的代码逻辑
		int _audit_type = 0, file_status = 0;


		long cur_audit_user_id = auditNodeList.get(0).getAudit_user_id();

		entity.setCur_audit_user_id(cur_audit_user_id);

		entity.setFilesAuditNodeList(filesAuditNodeList);
		entity.setAudit_type(_audit_type);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);


        // 新增方法
		if (null == entity.getId()) {
            // 渠道的konkaoafiles.audit_type 只会是0.因为不做会签.另外刚保存时,状态为未提交也就是0
            if (send_to_process != null && "send_to_process".equals(send_to_process)) {
                // 提交到流程上
                file_status = 1;
            }
			entity.setSubmit_dept_id(ui.getDept_id());
			entity.setSubmit_dept(kd.getDept_name());
			entity.setSubmit_user_id(ui.getId());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String submit_datetime = (String) dynaBean.get("submit_datetime");
			entity.setSubmit_datetime(df.parse(submit_datetime));

			// 登录用户所在部门的提交文件编号最大值
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setFile_no(getQjFilesMaxNo(file_no_lm));
			entity.setFile_status(file_status);
			entity.setIs_del(0);
			super.getFacade().getKonkaoaFilesService().createKonkaoaFiles(entity);
			saveMessage(request, "entity.inserted");

			dynaBean.set("category12", "");
			dynaBean.set("category13", "");
			dynaBean.set("category14", "");
			dynaBean.set("is_stamp", "");
			dynaBean.set("is_forward", "");

			dynaBean.set("file_title", "");
			dynaBean.set("content", "");
			dynaBean.set("apply_people", "");

			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=" + mod_id);
			pathBuffer.append("&p_audit_node_id=").append(p_audit_node_id);
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		} else {
            // 渠道的konkaoafiles.audit_type 只会是0.因为不做会签.另外刚保存时,状态为未提交也就是0
            if (send_to_process != null && "send_to_process".equals(send_to_process)) {
                // 提交到流程上
                file_status = 1;
            }
            entity.setFile_status(file_status);
			entity.setSubmit_datetime(null);
			entity.setSubmit_dept(kd.getDept_name());
			entity.setAudit_node_id(Long.parseLong(audit_node_id));
			super.getFacade().getKonkaoaFilesService().modifyKonkaoaFiles(entity);
			saveMessage(request, "entity.updated");

		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&p_audit_node_id=").append(p_audit_node_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			entity.setId(new Long(id));
			super.getFacade().getKonkaoaFilesService().removeKonkaoaFiles(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaoaFiles entity = new KonkaoaFiles();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaoaFilesService().removeKonkaoaFiles(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&").append("p_audit_node_id=").append(p_audit_node_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			super.getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);

		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
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

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(files.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService()
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
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审批记录显示
		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(files.getId());
		fan.setAudit_type(0);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeListForView(fan));


		return mapping.findForward("view");
	}

	// 用于对比页面测试
	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		dynaBean.set("submit_user", ui.getUser_name());

		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));
		category.setC_type(12);
		request.setAttribute("category12List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));
		category.setC_type(13);
		request.setAttribute("category13List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));
		category.setC_type(14);
		request.setAttribute("category14List", super.getFacade().getKonkaoaCategoryService()
				.getKonkaoaCategoryListForFiles(category));

		return new ActionForward("${ctx}/manager/oa/FilesSubmit/form2.jsp");
	}

	public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");
		request.setAttribute("p_audit_node_id", p_audit_node_id);
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles file = new KonkaoaFiles();
		file.setId(new Long(id));
		file.getMap().put("qingjia", 1);		
		file = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(file);
		if (null == file) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		request.setAttribute("submit_datetime", file.getSubmit_datetime());
		dynaBean.set("audit_node_id", file.getAudit_node_id());

		super.setCategoryListToRequestScope(request);

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(file.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService()
				.getKonkaoaFilesRecipientList(fr);
		if (null != filesRecipientList) {
			String fa_ids = "", fa_names = "", dept_ids = "", dept_names = "";
			// song_ids = "", song_names = "", cheng_ids = "", cheng_names = "";
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
		attachment.setLink_id(file.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));


		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(file.getId());
		fan.setAudit_type(0);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan));

		// 审批流程
		if (StringUtils.isBlank(p_audit_node_id)) {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(2);
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null) {
				filesAuditNode.setDept_id(kDept.getDept_id());
			} else {
				filesAuditNode.setDept_id(0L);
			}
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList) {
				request.setAttribute("KonkaoaFilesAuditNodeList", filesAuditNodeList);
			}
		} else {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(2);
			filesAuditNode.setLink_id(Long.parseLong(p_audit_node_id));
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			// if (kDept != null) {
			// filesAuditNode.setDept_id(kDept.getDept_id());
			// } else {
			// filesAuditNode.setDept_id(0L);
			// }
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList && filesAuditNodeList.size() > 0) {
				request.setAttribute("filesAuditNodeId", filesAuditNodeList.get(0).getLink_id());
				request.setAttribute("filesAuditNodeName", filesAuditNodeList.get(0).getAudit_node_name());
				request.setAttribute("p_audit_node_id", p_audit_node_id);
			}

			// 模板
			KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
			konkaOaModuleType.setIs_del(0);
			if (kDept != null) {
				konkaOaModuleType.setDept_id(kDept.getDept_id());
			} else {
				konkaOaModuleType.setDept_id(0L);
			}
			konkaOaModuleType.setOa_type(Long.valueOf("10001801"));
			List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService()
					.getKonkaOaModuleTypeList(konkaOaModuleType);

			if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
				request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
			}
		}

		file.setId(null);
		file.setFile_no(null);
		super.copyProperties(form, file);

		dynaBean.set("yymm", this.getNowYearAndMonth());
		request.setAttribute("submit_datetime", new Date());

		return mapping.findForward("input");
	}

	public ActionForward resubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");
		request.setAttribute("p_audit_node_id", p_audit_node_id);
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaoaFiles file = new KonkaoaFiles();
		file.setId(new Long(id));
		file = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(file);
		if (null == file) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		request.setAttribute("submit_datetime", file.getSubmit_datetime());
		dynaBean.set("audit_node_id", file.getAudit_node_id());

		super.setCategoryListToRequestScope(request);

		// 下发范围
		KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
		fr.setLink_id(file.getId());
		List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService()
				.getKonkaoaFilesRecipientList(fr);
		if (null != filesRecipientList) {
			String fa_ids = "", fa_names = "", dept_ids = "", dept_names = "";
			// song_ids = "", song_names = "", cheng_ids = "", cheng_names = "";
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
		attachment.setLink_id(file.getId());
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));


		KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
		fan.setLink_id(file.getId());
		fan.setAudit_type(0);
		request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(fan));

		// 审批流程
		if (StringUtils.isBlank(p_audit_node_id)) {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(0);
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			if (kDept != null) {
				filesAuditNode.setDept_id(kDept.getDept_id());
			} else {
				filesAuditNode.setDept_id(0L);
			}
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList) {
				request.setAttribute("KonkaoaFilesAuditNodeList", filesAuditNodeList);
			}
		} else {
			KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
			filesAuditNode.setAudit_level(1l);
			filesAuditNode.setAudit_type(2);
			filesAuditNode.setNode_type(0);
			filesAuditNode.setLink_id(Long.parseLong(p_audit_node_id));
			KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
			// if (kDept != null) {
			// filesAuditNode.setDept_id(kDept.getDept_id());
			// } else {
			// filesAuditNode.setDept_id(0L);
			// }
			List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
					.getKonkaoaFilesAuditNodeList(filesAuditNode);

			if (null != filesAuditNodeList && filesAuditNodeList.size() > 0) {
				request.setAttribute("filesAuditNodeId", filesAuditNodeList.get(0).getLink_id());
				request.setAttribute("filesAuditNodeName", filesAuditNodeList.get(0).getAudit_node_name());
				request.setAttribute("p_audit_node_id", p_audit_node_id);
			}

			// 模板
			KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
			konkaOaModuleType.setIs_del(0);
			if (kDept != null) {
				konkaOaModuleType.setDept_id(kDept.getDept_id());
			} else {
				konkaOaModuleType.setDept_id(0L);
			}
			List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService()
					.getKonkaOaModuleTypeList(konkaOaModuleType);

			if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
				request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
			}
		}
		request.setAttribute("link_id", file.getId());
		file.setId(null);
		file.setFile_no(null);
		super.copyProperties(form, file);

		dynaBean.set("yymm", this.getNowYearAndMonth());
		request.setAttribute("submit_datetime", new Date());

		return new ActionForward("/admin/FilesSubmit/resubmit.jsp");
	}

	public ActionForward resave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);

		DynaBean dynaBean = (DynaBean) form;
		String audit_node_id = (String) dynaBean.get("audit_node_id");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");
		logger.info("=============audit_node_id=" + audit_node_id);
		logger.info("=============p_audit_node_id=" + p_audit_node_id);
		if (StringUtils.isBlank(audit_node_id)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}

		String mod_id = (String) dynaBean.get("mod_id");

		List<KonkaoaFilesProperty> filesPropertyList = new ArrayList<KonkaoaFilesProperty>();

		entity.setFilesPropertyList(filesPropertyList);
		entity.setAudit_node_id(Long.parseLong(audit_node_id));

		// 是否下发
		if (entity.getIs_forward() == 1) {
			entity.setFilesRecipientList(super.getFilesProperty(form, request, entity.getId()).getFilesRecipientList());
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// 附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });

		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {

			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			// attachment.setLink_tab(uploadFile.getFormName());
			attachment.setLink_tab("FILES");
			attachment.setAdd_user_name(ui.getUser_name());
			attachment.setAdd_user_id(ui.getId());
			attachmentList.add(attachment);
		}
		entity.setAttachmentList(attachmentList);

		// 审批结点
		List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();

		// 审批人
		KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
		_fan.setLink_id(Long.parseLong(audit_node_id));
		_fan.setAudit_type(2);
		List<KonkaoaFilesAuditNode> auditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan);

		// 查询之前的审核记录
		KonkaoaFilesAuditNode _fan1 = new KonkaoaFilesAuditNode();
		_fan1.setLink_id(entity.getLink_id());
		_fan1.setAudit_type(0);
		Long fan1_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(_fan1);
		List<KonkaoaFilesAuditNode> auditNodeList1 = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(_fan1);
		if (fan1_count > 0 && auditNodeList1 != null && auditNodeList1.size() > 0) {
			filesAuditNodeList.addAll(auditNodeList1);
		} else {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		if (null != auditNodeList && auditNodeList.size() > 0) {
			for (int i = 0; i < auditNodeList.size(); i++) {
				Long user_id = new Long(auditNodeList.get(i).getAudit_user_id());
				if (null != user_id) {
					KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
					fau.setAudit_level(new Long(i + 1 + fan1_count.intValue()));
					fau.setAudit_user_id(user_id);
					fau.setAudit_user(auditNodeList.get(i).getAudit_user());
					fau.setAudit_type(0);
					filesAuditNodeList.add(fau);
				}
			}
		} else {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}

        // 非常重要,数年前的代码逻辑
        int _audit_type = 0, file_status = 0;
		long cur_audit_user_id = auditNodeList.get(0).getAudit_user_id();

		entity.setCur_audit_user_id(cur_audit_user_id);

        // 人的节点
		entity.setFilesAuditNodeList(filesAuditNodeList);
		entity.setAudit_type(_audit_type);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		if (null == entity.getId()) {
			entity.setSubmit_dept_id(ui.getDept_id());
			entity.setSubmit_dept(kd.getDept_name());
			entity.setSubmit_user_id(ui.getId());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String submit_datetime = (String) dynaBean.get("submit_datetime");
			entity.setSubmit_datetime(df.parse(submit_datetime));

			// 登录用户所在部门的提交文件编号最大值
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setFile_no(getQjFilesMaxNo(file_no_lm));
			entity.setFile_status(file_status);
			entity.setIs_del(0);
			super.getFacade().getKonkaoaFilesService().createKonkaoaFiles(entity);
			saveMessage(request, "entity.inserted");

			dynaBean.set("category12", "");
			dynaBean.set("category13", "");
			dynaBean.set("category14", "");
			dynaBean.set("is_stamp", "");
			dynaBean.set("is_forward", "");

			dynaBean.set("file_title", "");
			dynaBean.set("content", "");
			dynaBean.set("apply_people", "");

			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=" + mod_id);
			pathBuffer.append("&p_audit_node_id=").append(p_audit_node_id);
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		} else {
			KonkaoaFiles _f = new KonkaoaFiles();
			_f.setId(entity.getId());
			_f = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(_f);
			if (null == _f) {
				saveError(request, "errors.param");
				return list(mapping, form, request, response);
			}

			if (_f.getFile_status() >= -1) {
				entity.setAudit_type(0);

				if (_f.getFile_status() == -1) {
					String countersign_all = (String) dynaBean.get("countersign_all");// 会签汇总
					KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
					fau.setAudit_comment(countersign_all);
					fau.setAudit_user_id(ui.getId());
					fau.setAudit_user(ui.getReal_name());
					fau.setAudit_datetime(new Date());
					fau.setAudit_result(2);
					fau.setAudit_type(1);
					fau.setAudit_level(new Long(1));
					entity.getFilesAuditNodeList().add(fau);
					file_status = 1;
				}
			} else {
				entity.setAudit_type(null);
			}

			entity.setFile_status(file_status);
			entity.setSubmit_datetime(null);
			entity.setSubmit_dept(kd.getDept_name());
			entity.setAudit_node_id(Long.parseLong(audit_node_id));
			super.getFacade().getKonkaoaFilesService().modifyKonkaoaFiles(entity);
			saveMessage(request, "entity.updated");

		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&p_audit_node_id=").append(p_audit_node_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String p_audit_node_id = (String) dynaBean.get("p_audit_node_id");
		String id = (String) dynaBean.get("id");

		KonkaoaFiles entity = new KonkaoaFiles();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.parseLong(id));
		} else {
			super.renderJavaScript(response, "alert('数据丢失！');history.back();");
			return null;
		}
		entity.setFile_status(4);// 撤销
		super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesOnly(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&p_audit_node_id=").append(p_audit_node_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	/**
	 * @param request
     * 自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getQjFilesMaxNo(String file_no_lm) {
		
		Long max_fileno = null;
		
		KonkaoaDocInfo kd = new KonkaoaDocInfo();
		kd.getMap().put("file_no_lm", file_no_lm);
		
		Long max_fileno_1 = super.getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;
		
		KonkaoaFiles kf = new KonkaoaFiles();
		kf.getMap().put("file_no_lm", file_no_lm);
		kf.getMap().put("qingjia", "1");
		Long max_fileno_2 = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesNoMax(kf);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;
		
		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;	
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if(max_fileno < 1000){
			file_no_r = "0000".substring(0,4-("" + max_fileno).length()) + max_fileno;
		}else{
			file_no_r = "" + max_fileno;
		}
		
		return file_no_lm + file_no_r;
	}
}