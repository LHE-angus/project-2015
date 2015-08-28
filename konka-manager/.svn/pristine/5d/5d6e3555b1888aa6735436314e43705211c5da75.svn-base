package com.ebiz.mmt.web.struts.mobile.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13
 */
public class FilesSubmitAction extends MobileBaseAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.add(mapping, form, request, response);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_id(ui.getDept_id());
        konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
        dynaBean.set("submit_dept", konkaDept.getDept_name());
        dynaBean.set("submit_user", ui.getReal_name());

        // 分公司取得
        if (konkaDept.getDept_type() > 2) {
            KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
            dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
        }
        // 当前年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String ym = sdf.format(date);
        String yymm = ym.substring(2, ym.length());
        dynaBean.set("yymm", yymm);

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

        dynaBean.set("category13", "1302");// 文件机密级默认
        dynaBean.set("category12", "1203");// 文件级别默认

        return mapping.findForward("input");
    }


    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);

        DynaBean dynaBean = (DynaBean) form;
        String audit_user_name = (String) dynaBean.get("audit_user_name");
        String content = (String) dynaBean.get("content");
        String file_title = (String) dynaBean.get("file_title");

        KonkaoaFiles entity = new KonkaoaFiles();
        entity.setFile_from(1);// 来自手机
        entity.setTime_limit(1);// 时限一天
        entity.setSubmit_user(ui.getReal_name());

        Long audit_user_id = new Long(0);
        if (StringUtils.isBlank(file_title) || ("标题").equals(file_title)) {
            super.renderText(response, "请填写标题！");
            return null;
        }
        if (StringUtils.isBlank(content)) {
            super.renderText(response, "请填写内容！");
            return null;
        }
        if (StringUtils.isBlank(audit_user_name) || ("审批人").equals(audit_user_name)) {
            super.renderText(response, "请填写审批人！");
            return null;
        } else if (StringUtils.isNotBlank(audit_user_name)) {
            PeProdUser user = new PeProdUser();
            user.setUser_name(audit_user_name);
            user = getFacade().getPeProdUserService().getPeProdUserResult(user);
            if (user == null) {
                super.renderText(response, "无法查找到\"" + audit_user_name + "\"！请检查是否输入有误");
                return null;
            } else if (user.getUser_type() == 0) {
                super.renderText(response, "不可以提交给管理员");
                return null;
            } else if (user != null && user.getDept_id() != null) {
                Long audit_dept_id = super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id();
                Long submit_dept_id = super.getSuperiorForDeptType(ui.getDept_id(), 3).getDept_id();
                if (!audit_dept_id.equals(submit_dept_id)) {
                    super.renderText(response, audit_user_name + "此人与您不在同一分公司，请重新输入审批人！");
                    return null;
                } else {
                    audit_user_id = user.getId();
                }
            }
        }


        super.copyProperties(entity, form);

        List<KonkaoaFilesProperty> filesPropertyList = new ArrayList<KonkaoaFilesProperty>();


        entity.setFilesPropertyList(filesPropertyList);

        // // 附件
        // List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
        // List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
        // KonkaPeAttachments attachment = null;
        // for (UploadFile uploadFile : uploadFileList) {
        //
        // attachment = new KonkaPeAttachments();
        // attachment.setFile_name(uploadFile.getFileName());
        // attachment.setFile_type(uploadFile.getContentType());
        // attachment.setFile_size(new Long(uploadFile.getFileSize()));
        // attachment.setSave_path(uploadFile.getFileSavePath());
        // attachment.setSave_name(uploadFile.getFileSaveName());
        // attachment.setIs_del(0l);
        // // attachment.setLink_tab(uploadFile.getFormName());
        // attachment.setLink_tab("FILES");
        // attachment.setAdd_user_name(ui.getUser_name());
        // attachment.setAdd_user_id(ui.getId());
        // attachmentList.add(attachment);
        // }
        // entity.setAttachmentList(attachmentList);

        // 审批结点
        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();

        // 审批人
        KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
        // _fan.setAudit_level(new Long(1));
        _fan.setAudit_user_id(audit_user_id);
        _fan.setAudit_user(audit_user_name);
        _fan.setAudit_type(0);
        // // 非常重要,数年前的代码逻辑
        // 手机端的提交直接状态改为审批中,区别于web端的功能实现
        int audit_type = 0, file_status = 1;
        long cur_audit_user_id = _fan.getAudit_user_id();


        entity.setCur_audit_user_id(cur_audit_user_id);

        filesAuditNodeList.add(_fan);

        entity.setFilesAuditNodeList(filesAuditNodeList);
        entity.setAudit_type(audit_type);

        KonkaDept konkaDept = new KonkaDept();
        konkaDept.setDept_id(ui.getDept_id());
        KonkaDept kd = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

        entity.setSubmit_dept_id(ui.getDept_id());
        entity.setSubmit_dept(kd.getDept_name());
        entity.setSubmit_user_id(ui.getId());

        entity.setSubmit_datetime(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String ym = sdf.format(date);
        String yymm = ym.substring(2, ym.length());

        // 登录用户所在部门的提交文件编号最大值
        String file_no_lm = "FS" + yymm;
        entity.setFile_no(getFilesMaxNo(file_no_lm));
        entity.setFile_status(file_status);
        entity.setIs_del(0);
        super.getFacade().getKonkaoaFilesService().createKonkaoaFiles(entity);
        saveMessage(request, "entity.inserted");


        dynaBean.set("is_forward", "");

        dynaBean.set("file_title", "");
        dynaBean.set("content", "");

        createMobileSysOperLog(request, "KONKAOA_FILES", 720200l, "720200", "新增", "手机端-文件提交-新增");
        super.renderText(response, "success");
        return null;
    }
}
