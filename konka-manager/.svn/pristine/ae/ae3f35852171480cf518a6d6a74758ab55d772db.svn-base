package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.mobile.admin.MobileBaseAction;

public class SelfEventCenterAction extends MobileBaseAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);

        int i = 0;
        // List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();


        List<Long> agentAuditFileIds = null;

        /**
         * @version Build 2010-12-30
         * @desc 文件代审批功能实现
         */
        // 先查当前用户帮哪些用户代审文件，将被代理审批的用户查出来
        KonkaoaFilesAuditAgentUser faau = new KonkaoaFilesAuditAgentUser();
        faau.setIs_del(0);
        faau.setAgent_user_id(ui.getId());
        faau.getMap().put("expired_date", "true");
        List<KonkaoaFilesAuditAgentUser> filesAuditAgentUserList = getFacade().getKonkaoaFilesAuditAgentUserService().getKonkaoaFilesAuditAgentUserList(faau);
        if (null != filesAuditAgentUserList && filesAuditAgentUserList.size() > 0) {
            Long[] agent_user_ids = new Long[filesAuditAgentUserList.size()];
            for (i = 0; i < filesAuditAgentUserList.size(); i++) {
                agent_user_ids[i] = filesAuditAgentUserList.get(i).getUser_id();
            }

            // 查出被代理用户的需要审批的文件列表
            KonkaoaFiles files = new KonkaoaFiles();
            files.setIs_del(0);
            files.getMap().put("lt_file_status", 2);
            files.getMap().put("cur_audit_user_ids", agent_user_ids);
            List<KonkaoaFiles> filesList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(files);

            // 将查到的文件列表的文件属性查出，与审批权限表进行比对
            if (null != filesList && filesList.size() > 0) {
                agentAuditFileIds = new ArrayList<Long>();
                for (KonkaoaFiles _files : filesList) {
                    KonkaoaFilesAuditAgentUser _faau = new KonkaoaFilesAuditAgentUser();
                    _faau.setUser_id(_files.getCur_audit_user_id());
                    _faau.setAgent_user_id(ui.getId());
                    _faau.getMap().put("link_id", _files.getId());
                    Long count = super.getFacade().getKonkaoaFilesAuditAgentUserService().getKonkaoaAgentFilesAuditPopedomCount(_faau);
                    if (count == 0) {// 有审批权限的
                        agentAuditFileIds.add(_files.getId());
                    }
                }
            }
        }

        KonkaoaFiles entity = new KonkaoaFiles();
        entity.getMap().put("lt_file_status", 2);
        entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

        entity.setIs_del(0);
        entity.setCur_audit_user_id(ui.getId());
        entity.getMap().put("file_type_not_1", 1);

        String page = (String) dynaBean.get("currentPage");
        String pagelimit = (String) dynaBean.get("pagelimit");
        int currentPage = 1;
        int pageSize = 15;
        if (StringUtils.isNotEmpty(page)) {
            currentPage = Integer.parseInt(page);
        }
        if (StringUtils.isNotEmpty(pagelimit)) {
            pageSize = Integer.parseInt(pagelimit);
        }
        entity.getRow().setFirst((currentPage - 1) * pageSize);
        entity.getRow().setCount(pageSize);
        Long count = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

        if (count % pageSize > 0)
            request.setAttribute("pagelimit", (count / pageSize) + 1);
        else
            request.setAttribute("pagelimit", (count / pageSize));
        String outStr = "{htmlData:'";
        if (count > 0) {
            List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(entity);
            outStr += "<ol data-role=\"listview\">";
            for (KonkaoaFiles t : cList) {
                outStr += "<li>";
                outStr += "<a href=\"javascript:void(0)\" onclick=location.href=\"SelfEventCenter_form.html?id=" + t.getId() + "\">";
                outStr += t.getFile_no() + "&nbsp;&nbsp;《" + t.getFile_title() + "》&nbsp;&nbsp;" + t.getSubmit_user();
                outStr += "</a>";
                outStr += "</li>";
            }
            outStr += "</ol>',";
            outStr += "currentPage:" + currentPage + ",recordCount:" + count + "}";
        } else {
            outStr += "',";
            outStr += "currentPage:" + currentPage + ",recordCount:" + count + "}";
        }

        createMobileSysOperLog(request, "KONKAOA_FILES", 720100l, "720100", "查询", "手机端-待办文件-查询");
        log.info(outStr);
        super.renderText(response, outStr);
        return null;
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String id = (String) dynaBean.get("id");
        if (StringUtils.isBlank(id)) {
            super.renderText(response, "错误！");
            return null;
        }

        KonkaoaFiles files = new KonkaoaFiles();
        files.setId(new Long(id));
        files = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(files);
        if (null == files) {
            super.renderText(response, "数据错误，或已被删除！");
            return null;
        }
        String outStr = "{htmlData:'";
        outStr += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\">";
        outStr += "<li data-role=\"list-divider\">标   题：" + files.getFile_title() + "</li>";
        outStr += "<li>";
        outStr += "<p>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：" + files.getFile_no() + "</p>";
        outStr += "<p>负&nbsp;&nbsp;责&nbsp;&nbsp;人：" + files.getApply_user_name() + "</p>";
        if (files.getApply_user_tel() == null) {
            outStr += "<p>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</p>";
        } else {
            outStr += "<p>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：" + files.getApply_user_tel() + "</p>";
        }

        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        List<KonkaoaFilesAuditNode> auditList = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan);

        if (auditList.size() != 0) {
            String[] audit_user = new String[auditList.size()];
            for (int i = 0; i < auditList.size(); i++) {
                audit_user[i] = auditList.get(i).getAudit_user();
            }
            outStr += "<p>审&nbsp;&nbsp;批&nbsp;&nbsp;人：" + StringUtils.join(audit_user, ",") + "</p>";
        }
        outStr += "<p>申&nbsp;&nbsp;请&nbsp;&nbsp;人：" + files.getSubmit_user() + "</p>";
        if (files.getFile_status() == 0 || files.getFile_status() == -3) {
            outStr += "<p>当前状态：<strong>未审批</strong></p>";
        } else if (files.getFile_status() == 1 || files.getFile_status() == -1 || files.getFile_status() == -2) {
            outStr += "<p>当前状态：<strong>审批中</strong></p>";
        } else if (files.getFile_status() == 2) {
            outStr += "<p>当前状态：<strong>被驳回</strong></p>";
        } else if (files.getFile_status() == 3) {
            outStr += "<p>当前状态：<strong>已审批</strong></p>";
        }
        outStr += "<p>提交时间：" + s.format(files.getSubmit_datetime()) + "</p>";
        outStr += "<p>内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：" + files.getContent().replace("<p>", "").replace("</p>", "") + "</p>";
        outStr += "</li>";
        outStr += "</ul>";

        // 审批记录显示
        KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
        _fan.setLink_id(files.getId());
        _fan.setAudit_type(0);
        _fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
        List<KonkaoaFilesAuditNode> auditNodeList = getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(_fan);
        if (auditNodeList.size() != 0) {
            outStr += "<br/>";
            outStr += "<br/>";
            outStr += "<div data-role=\"controlgroup\"  data-type=\"horizontal\">";
            outStr += "<ol data-role=\"listview\">";
            for (KonkaoaFilesAuditNode t : auditNodeList) {
                outStr += "<li>";
                if (t.getAudit_result() == 2) {
                    outStr += "<span style=\"color:green\">同意</span>";
                } else if (t.getAudit_result() == 1) {
                    outStr += "<span style=\"color:red\">驳回</span>";
                } else if (t.getAudit_result() == -1) {
                    outStr += "<span style=\"color:red\">转发</span>";
                }
                outStr += "&nbsp;&nbsp;&nbsp;&nbsp;审批人：" + t.getAudit_user();
                outStr += "&nbsp;&nbsp;&nbsp;&nbsp;" + s.format(t.getAudit_datetime());
                outStr += "</li>";
            }
            outStr += "</ol>";
            outStr += "</div>";
        }

        if (files.getFile_from() == 1) {
            outStr += "',file_from:1";
        } else {
            outStr += "',file_from:0";
        }
        if (files.getFile_status() == 0 || files.getFile_status() == -3) {
            outStr += ",key:0";
        } else {
            outStr += ",key:1";
        }
        outStr += "}";
        super.renderText(response, outStr);
        return null;
    }

    public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PeProdUser peProdUser = new PeProdUser();
        peProdUser.setIs_del(0);

        PeProdUser _peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_MOBILE);

        KonkaDept kd = super.getSuperiorForDeptType(_peProdUser.getDept_id(), 3);
        if (null != kd) {
            peProdUser.getMap().put("dept_id_in", kd.getDept_id());
        }

        peProdUser.getMap().put("user_id", _peProdUser.getId());

        peProdUser.getRow().setCount(100);

        List<PeProdUser> deptList = super.getFacade().getPeProdUserService().getDeptListForSelectUser(peProdUser);
        if (deptList.size() != 0) {
            String outStr = "{htmlData:'";
            outStr += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\">";
            for (PeProdUser t : deptList) {
                peProdUser.getMap().put("dept_id", t.getDept_id());
                List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService().getPeProdUserListForSelectUser(peProdUser);
                outStr += "<li>" + peProdUserList.get(0).getDepartment() + "</li><li><p>";
                for (PeProdUser i : peProdUserList) {
                    outStr += "<a href=\"javascript:void(0)\" onclick=\"save(" + i.getId() + ")\">" + i.getUser_name() + "</a>";
                    if (peProdUserList.indexOf(i) != peProdUserList.size() - 1) {
                        outStr += "&nbsp;&nbsp;&nbsp;&nbsp;";
                    }
                    if ((peProdUserList.indexOf(i) + 1) % 4 == 0) {
                        outStr += "</p><br/><p>";
                    }
                }
                outStr += "</p></li>";
            }
            outStr += "</ul>";
            outStr += "'}";
            log.info(outStr);
            super.renderText(response, outStr);
        }
        return null;
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // if (isCancelled(request)) {
        // return list(mapping, form, request, response);
        // }
        // if (!isTokenValid(request)) {
        // saveError(request, "errors.token");
        // return list(mapping, form, request, response);
        // }
        // resetToken(request);

        DynaBean dynaBean = (DynaBean) form;
        String _id = (String) dynaBean.get("id");
        // String column_6 = (String) dynaBean.get("column_6");
        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：1驳回2同意
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语

        @SuppressWarnings("unused")
        String result = "";

        if (!GenericValidator.isLong(_id)) {
            super.renderText(response, "错误！");
            return null;
        }

        Long id = new Long(_id);

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);

        Long audti_user_id = ui.getId();

		/**
		 * 获取当前审核用户
		 */
		KonkaoaFiles existsfiles = new KonkaoaFiles();
		existsfiles.setId(id);
		existsfiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(existsfiles);
		if(existsfiles != null && existsfiles.getCur_audit_user_id() != null){
			Long user_id = ui.getId();
			//如果该文件的审核用户和当前用户不是同一个用户，提示错误
			if(!user_id.equals(existsfiles.getCur_audit_user_id())){
				
				super.renderText(response, "用户审核错误，请重新登陆后再进行审核操作！");
	            return null;
			}
		}
        KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        fau.setLink_id(id);
        fau.setAudit_user_id(audti_user_id);
        fau.setAudit_type(0);
        Long fau_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        if (fau_count < 1) {
            super.renderText(response, "错误！");
            return null;
        }

        KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
        filesAuditNode.setAudit_user_id(audti_user_id);
        filesAuditNode.setLink_id(id);
        filesAuditNode.setAudit_result(new Integer(audit_result));
        filesAuditNode.setAudit_comment(audit_comment);
        filesAuditNode.setAudit_datetime(new Date());

        KonkaoaFiles files = new KonkaoaFiles();
        files.setId(id);
        files.setIs_forward(0L);

        // 费用类别
        List<KonkaoaFilesProperty> propertyList = new ArrayList<KonkaoaFilesProperty>();

        files.setFilesPropertyList(propertyList);

        KonkaExpenseClaims claims = new KonkaExpenseClaims();
        claims.setFile_id(id);
        files.setExpenseClaims(claims);

        // 审批结点
        KonkaoaFilesAuditNode fau1 = new KonkaoaFilesAuditNode();
        fau1.setLink_id(id);
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau1);

        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
        String audit_user_id = request.getParameter("audit_user_id");
        if (StringUtils.isNotBlank(audit_user_id)) {
            Long user_id = new Long(audit_user_id);
            files.setCur_audit_user_id(user_id);
            PeProdUser user = new PeProdUser();
            user.setId(user_id);
            user = getFacade().getPeProdUserService().getPeProdUser(user);
            KonkaoaFilesAuditNode _filesAuditNode = new KonkaoaFilesAuditNode();
            _filesAuditNode.setAudit_level(max_level + 1);
            _filesAuditNode.setAudit_user_id(user_id);
            _filesAuditNode.setAudit_user(user.getUser_name());
            filesAuditNodeList.add(_filesAuditNode);
        }
        files.setFilesAuditNodeList(filesAuditNodeList);

        if (StringUtils.isNotBlank(audit_user_id)) {// 不是最后位
            if ("2".equals(audit_result)) {// 同意，继续走审批流程
                files.setCur_audit_user_id(new Long(audit_user_id));
                files.setFile_status(1);
                filesAuditNode.setAudit_result(2);
                result = "同意";
            } else if ("0".equals(audit_result)) {// 直接转发
                files.setCur_audit_user_id(new Long(audit_user_id));
                files.setFile_status(1);
                filesAuditNode.setAudit_result(0);
                result = "直接转发";
            } else {// 驳回，流程结束
                files.setFile_status(1);
                filesAuditNode.setAudit_result(1);
                result = "驳回";
            }
        } else { // 最后位审核人
            if ("2".equals(audit_result)) {// 同意
                files.setFile_status(2);
                filesAuditNode.setAudit_result(2);
                files.setArchive_datetime(filesAuditNode.getAudit_datetime());
                result = "同意,归档";
            } else {// 驳回
                files.setFile_status(1);
                filesAuditNode.setAudit_result(1);
                result = "驳回";
            }

            // 文件标识
            Long fileNo = Long.valueOf(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
            files.setDoc_id(fileNo);
        }

        filesAuditNode.setAudit_level(max_level);
        files.setFilesAuditNode(filesAuditNode);

        // 如果发送呈中有此人，则将此人设为已查看
        KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
        fr.setLink_id(id);
        fr.setReceive_id(ui.getId());
        fr.setView_date_time(filesAuditNode.getAudit_datetime());
        files.setFilesRecipient(fr);

        super.getFacade().getKonkaoaFilesService().auditFiles(files);
        super.renderText(response, "success");
        return null;

    }
}
