package com.ebiz.mmt.web.struts.manager.oa;

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
 * @author Hui,Gang
 * @version Build 2010-12-13
 */

/**
 * 
 * 无审批流程的文件提交用这里 \src\com\ebiz\mmt\web\struts\manager\oa\FilesSubmitAction.java
 * 
 * 有审批流程的文件提交用这个 \src\com\ebiz\mmt\web\struts\manager\chenduoa\FilesSubmitAction.java
 * 
 * 这谁写的破玩意?
 * 
 */
public class FilesSubmitAction extends BaseMmtoaAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        KonkaoaCategory category = new KonkaoaCategory();
        category.setIs_del(0);
        category.setC_type(11);
        request.setAttribute("category11List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(12);
        request.setAttribute("category12List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));

        entity.setIs_del(0);
        entity.setSubmit_user_id(ui.getId());
        entity.setFile_type(0);// 文件类型设为0，表示该文件是文件提交

        Pager pager = (Pager) dynaBean.get("pager");

        Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForPaginatedList(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedList(entity);

        request.setAttribute("entityList", entityList);

        return mapping.findForward("list");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        saveToken(request);

        super.getModPopeDom(form, request);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_id(ui.getDept_id());
        konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
        dynaBean.set("submit_dept", konkaDept.getDept_name());
        dynaBean.set("submit_user", ui.getReal_name());

        String is_node = (String) dynaBean.get("is_node");// 页面的值0,没有事先指定审批流程

        // 分公司取得
        if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
            KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
            dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
        }
        dynaBean.set("yymm", this.getNowYearAndMonth());
        request.setAttribute("submit_datetime", new Date());

        KonkaoaCategory category = new KonkaoaCategory();
        category.setIs_del(0);
        category.setC_type(11);
        request.setAttribute("category11List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(12);
        request.setAttribute("category12List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(13);
        request.setAttribute("category13List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(14);
        request.setAttribute("category14List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));

        // 模板
        KonkaOaModuleType konkaOaModuleType = new KonkaOaModuleType();
        konkaOaModuleType.setIs_del(0);

        KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
        if (kDept != null) {
            konkaOaModuleType.setDept_id(kDept.getDept_id());
        } else {
            konkaOaModuleType.setDept_id(0L);
        }

        List<KonkaOaModuleType> konkaOaModuleTypeList = super.getFacade().getKonkaOaModuleTypeService().getKonkaOaModuleTypeList(konkaOaModuleType);

        if (null != konkaOaModuleTypeList && konkaOaModuleTypeList.size() > 0) {
            request.setAttribute("konkaOaModuleTypeList", konkaOaModuleTypeList);
        }

        request.setAttribute("is_add", "2");
        return mapping.findForward("input");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        setNaviStringToRequestScope(form, request);

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
        request.setAttribute("submit_datetime", files.getSubmit_datetime());

        // 下发范围
        KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
        fr.setLink_id(files.getId());
        List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService().getKonkaoaFilesRecipientList(fr);
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
        request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan));

        return mapping.findForward("input");
    }

    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        setNaviStringToRequestScope(form, request);
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

        request.setAttribute("submit_datetime", files.getSubmit_datetime());

        // 附件
        KonkaPeAttachments attachment = new KonkaPeAttachments();
        attachment.setLink_id(files.getId());
        attachment.setLink_tab("FILES");
        attachment.setIs_del(0l);
        request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan));

        files.setId(null);
        files.setFile_no(null);
        super.copyProperties(form, files);

        dynaBean.set("yymm", this.getNowYearAndMonth());
        request.setAttribute("submit_datetime", new Date());

        return mapping.findForward("input");
    }


    /**
     * 
     * 文件提交保存方法 此时单据状态为0,单据审批类型audit_type为0(审批),1(会签,渠道系统不做)
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        String audit_user_id = (String) dynaBean.get("audit_user_id");
        String audit_user_name = (String) dynaBean.get("audit_user_name");
        String[] copy_file_id = request.getParameterValues("copy_file_id");


        // 是暂时保存还是直接提交审批,区别是单据保存时,单据状态为0,还是1
        String send_to_process = (String) dynaBean.get("send_to_process");
        String module_name = (String) dynaBean.get("module_name");


        if (StringUtils.isBlank(audit_user_id) || StringUtils.isBlank(audit_user_name)) {
            saveError(request, "errors.token");
            return list(mapping, form, request, response);
        }
        if (StringUtils.isNotBlank(module_name)) {
            entity.setAudit_node_id(Long.parseLong(module_name));
        }

        String mod_id = (String) dynaBean.get("mod_id");

        List<KonkaoaFilesProperty> filesPropertyList = new ArrayList<KonkaoaFilesProperty>();

        entity.setFilesPropertyList(filesPropertyList);

        // 是否下发
        if (entity.getIs_forward() == 1) {
            entity.setFilesRecipientList(super.getFilesProperty(form, request, entity.getId()).getFilesRecipientList());
        }

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        // 附件
        List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OA_PATH, true, 0, new int[] {240});
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
            attachment.setLink_tab("FILES");
            attachment.setAdd_user_name(ui.getUser_name());
            attachment.setAdd_user_id(ui.getId());
            attachmentList.add(attachment);
        }
        if (null != copy_file_id && null == entity.getId()) {
            for (int i = 0; i < copy_file_id.length; i++) {
                attachment = new KonkaPeAttachments();

                KonkaPeAttachments kpa = new KonkaPeAttachments();
                kpa.setId(Long.valueOf(copy_file_id[i]));
                attachment = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachments(kpa);
                attachment.setId(null);
                attachmentList.add(attachment);
            }
        }

        entity.setAttachmentList(attachmentList);

        // 虽然没有指定流程,但有指定哪一个人,所以也要生成审批结点
        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
        // 审批人
        KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
        _fan.setAudit_level(new Long(1));
        _fan.setAudit_user_id(new Long(audit_user_id));
        _fan.setAudit_user(audit_user_name);
        _fan.setAudit_type(0);
        // // 非常重要,数年前的代码逻辑
        int audit_type = 0, file_status = 0;
        // 渠道的konkaoafiles.audit_type 只会是0.因为不做会签.另外刚保存时,状态为未提交也就是0

        long cur_audit_user_id = _fan.getAudit_user_id();


        entity.setCur_audit_user_id(cur_audit_user_id);
        filesAuditNodeList.add(_fan);

        // 加入审批节点
        entity.setFilesAuditNodeList(filesAuditNodeList);
        entity.setAudit_type(audit_type);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_id(ui.getDept_id());
        KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

        if (null == entity.getId()) {

            //
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
            entity.setFile_no(getFilesMaxNo(file_no_lm));
            // entity.setSubmit_datetime(new Date());
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
            pathBuffer.append("&");
            pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
            ActionForward forward = new ActionForward(pathBuffer.toString(), true);
            // end
            return forward;
        } else {

            //
            if (send_to_process != null && "send_to_process".equals(send_to_process)) {
                // 提交到流程上
                file_status = 1;
            }

            KonkaoaFiles _f = new KonkaoaFiles();
            _f.setId(entity.getId());
            _f = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(_f);
            if (null == _f) {
                saveError(request, "errors.param");
                return list(mapping, form, request, response);
            }

            entity.setFile_status(file_status);
            entity.setSubmit_datetime(null);
            entity.setSubmit_dept(kd.getDept_name());
            super.getFacade().getKonkaoaFilesService().modifyKonkaoaFiles(entity);
            saveMessage(request, "entity.updated");
        }

        return new ActionForward("/oa/FilesSubmit.do?method=list&mod_id=" + mod_id, true);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String id = (String) dynaBean.get("id");
        String[] pks = (String[]) dynaBean.get("pks");
        String mod_id = (String) dynaBean.get("mod_id");

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

    public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        String id = (String) dynaBean.get("id");

        if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
            KonkaPeAttachments entity = new KonkaPeAttachments();
            entity.setId(new Long(id));
            super.getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
            // saveMessage(request, "entity.deleted");
        }

        super.renderText(response, "success");
        return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesPropertyList(filesProperty);
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
        List<KonkaoaFilesRecipient> filesRecipientList = super.getFacade().getKonkaoaFilesRecipientService().getKonkaoaFilesRecipientList(fr);
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
        request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

        // if (files.getFile_status() > -1) {
        // 审批记录显示
        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        // fan.getMap().put("is_audit", "is_audit");
        request.setAttribute("filesAuditNodeList", super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeListForView(fan));
        // .getKonkaoaFilesAuditNodeList(fan));
        // }

        KonkaoaFilesAuditNode fan2 = new KonkaoaFilesAuditNode();
        fan2.setLink_id(files.getId());
        fan2.setAudit_type(0);
        request.setAttribute("_filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan2));

        return mapping.findForward("view");
    }

    // 用于对比页面测试
    public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        saveToken(request);

        DynaBean dynaBean = (DynaBean) form;

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        // dynaBean.set("submit_dept", ui.get);
        dynaBean.set("submit_user", ui.getUser_name());

        KonkaoaCategory category = new KonkaoaCategory();
        category.setIs_del(0);
        // category.getMap().put("c_types", new Integer[] { 11, 12, 13, 14 });
        category.setC_type(11);
        request.setAttribute("category11List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(12);
        request.setAttribute("category12List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(13);
        request.setAttribute("category13List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(14);
        request.setAttribute("category14List", super.getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));

        return new ActionForward("${ctx}/manager/oa/FilesSubmit/form2.jsp");
    }
}
