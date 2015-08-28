package com.ebiz.mmt.web.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
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

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.util.DESPlus;

public class OaFilesAction extends MobileListAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        // super.encodeCharacterForGetMethod(dynaBean, request);

        String user_id = (String) dynaBean.get("user_id");
        String userpass = (String) dynaBean.get("userpass");
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        if (StringUtils.isBlank(user_id) && StringUtils.isBlank(userpass)) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }

        PeProdUser ui = new PeProdUser();

        String username = "";
        if (user_id != null) {
            ui.setId(Long.valueOf(user_id));
            ui.setPass_word(new DESPlus().encrypt(userpass));
            ui = getFacade().getPeProdUserService().getPeProdUser(ui);
        } else {
            username = (String) request.getParameter("username");
            ui = checkUser(username, userpass,android_version,ios_version);
        }
        // 密码
        // String userpass = (String) dynaBean.get("userpass");

        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！" + username);
            return null;
        }

        int i = 0;
        List<KonkaoaEventInfo> list = new ArrayList<KonkaoaEventInfo>();

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
            // 单据状态>0并且<2 也就是1
            files.getMap().put("gt_file_status", 0);
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
        entity.getMap().put("gt_file_status", 0);
        entity.getMap().put("lt_file_status", 2);
        entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

        entity.setIs_del(0);
        entity.setCur_audit_user_id(ui.getId());

        // entity.getRow().setFirst(0);
        // entity.getRow().setCount(5);
        Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

        String page = request.getParameter("page");
        String forward = (String) dynaBean.get("forward");
        int currentPage = 1;
        int pageSize = 5;
        if (!StringUtils.isEmpty(page)) {
            currentPage = (Integer.parseInt(page)) + (Integer.parseInt(forward));
        }
        entity.getRow().setFirst((currentPage - 1) * pageSize);
        entity.getRow().setCount(pageSize);

        if (recordCount > 0) {
            dynaBean.set("currentPage", currentPage);

            List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(entity);

            for (KonkaoaFiles t : cList) {
                i++;
                KonkaoaEventInfo _t = new KonkaoaEventInfo();
                _t.setId(t.getId());

                _t.setEnterDate(t.getSubmit_datetime());
                _t.setEventiltle(t.getFile_title());
                _t.setSequence(i);
                _t.setFromPerson(t.getSubmit_user());
                if (t.getFile_type() == 0) {
                    _t.setEventType("文件提交");
                } else if (t.getFile_type() == 1) {
                    _t.setEventType("费用申请");
                }

                if ("1".equals(t.getMap().get("agent_audit").toString()) || "0".equals(t.getMap().get("agent_audit").toString())) {
                    if (t.getIs_node() == 1) {// 成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=detail&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                                + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&is_node=1" + "&id=" + t.getId() + "&username=" + ui.getUser_name() + "&user_id="
                                + ui.getId() + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath()
                                + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                    } else {// 非成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=detail&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                                + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&is_node=0" + "&id=" + t.getId() + "&username=" + ui.getUser_name() + "&user_id="
                                + ui.getId() + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath()
                                + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                    }
                } else {
                    if (t.getIs_node() == 1) {// 成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=detail&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                                + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&is_node=1" + "&id=" + t.getId() + "&username=" + ui.getUser_name() + "&user_id="
                                + ui.getId() + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath()
                                + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                    } else {// 非成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=detail&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                                + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&is_node=0" + "&id=" + t.getId() + "&username=" + ui.getUser_name() + "&user_id="
                                + ui.getId() + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath()
                                + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                    }
                }
                String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
                _t.getMap().put("url", url);
                list.add(_t);
            }

            KonkaoaFiles file5 = new KonkaoaFiles();
            file5.setFile_status(0);
            file5.setIs_del(0);
            file5.setSubmit_user_id(ui.getId());
            List<KonkaoaFiles> file5List = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);

            for (KonkaoaFiles t : file5List) {
                i++;
                KonkaoaEventInfo _t = new KonkaoaEventInfo();
                _t.setId(t.getId());
                // _t.setMod_id(20030);
                _t.setEnterDate(t.getSubmit_datetime());
                _t.setEventiltle(t.getFile_title());
                _t.setSequence(i);
                _t.setFromPerson(t.getSubmit_user());
                if (t.getFile_type() == 0) {
                    _t.setEventType("文件提交");
                    if (t.getIs_node() == 1) {// 成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=edit&id=" + t.getId() + "&is_node=1" + "&username=" + ui.getUser_name() + "&user_id=" + ui.getId() + "&userpass="
                                + userpass + "'><img src=\"" + request.getContextPath() + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                    } else {// 非成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=edit&id=" + t.getId() + "&is_node=0" + "&username=" + username + "&user_id=" + ui.getId() + "&userpass="
                                + userpass + "'><img src=\"" + request.getContextPath() + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                    }
                } else if (t.getFile_type() == 1) {
                    _t.setEventType("费用申请");
                    if (t.getIs_node() == 1) {// 成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=edit1&file_id=" + t.getId() + "&is_node=1" + "&username=" + ui.getUser_name() + "&user_id=" + ui.getId()
                                + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath() + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                    } else {// 非成都分公司
                        _t.setEventDo("<a href='OaFiles.do?method=edit1&file_id=" + t.getId() + "&is_node=0" + "&username=" + ui.getUser_name() + "&user_id=" + ui.getId()
                                + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath() + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                    }
                }
                String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
                _t.getMap().put("url", url);
                list.add(_t);
            }

            request.setAttribute("entityList", list);
        }
        return new ActionForward("/mobile/oaFiles/list.jsp");
    }

    public ActionForward getCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        // super.encodeCharacterForGetMethod(dynaBean, request);

        // 用户名
        String username = (String) dynaBean.get("username");
        // 密码
        String userpass = (String) dynaBean.get("userpass");

        String user_id = (String) dynaBean.get("user_id");
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
        PeProdUser ui = new PeProdUser();
        if (user_id != null) {
            ui.setId(Long.valueOf(user_id));
            ui.setPass_word(new DESPlus().encrypt(userpass));
            ui = getFacade().getPeProdUserService().getPeProdUser(ui);
        } else {
            username = (String) request.getParameter("username");
            ui = checkUser(username, userpass,android_version,ios_version);
        }

        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }

        int i = 0;
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
            files.getMap().put("gt_file_status", 0);
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
        entity.getMap().put("gt_file_status", 0);
        entity.getMap().put("lt_file_status", 2);
        entity.getMap().put("agentAuditFileIds", agentAuditFileIds);

        entity.setIs_del(0);
        entity.setCur_audit_user_id(ui.getId());
        Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

        super.renderText(response, String.valueOf(Math.ceil(recordCount)));
        return null;
    }

    public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        String id = (String) dynaBean.get("id");
        String username = (String) dynaBean.get("username");
        String userpass = (String) dynaBean.get("userpass");
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        String file_type = (String) dynaBean.get("file_type");

        dynaBean.set("is_agent", is_agent);
        dynaBean.set("agent_user_id", agent_user_id);
        dynaBean.set("file_type", file_type);
        dynaBean.set("username", username);
        dynaBean.set("userpass", userpass);

        // 查询当前文件
        KonkaoaFiles entity = new KonkaoaFiles();
        entity.setId(Long.parseLong(id));
        entity = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);

        super.copyProperties(form, entity);

        if (entity.getFile_type() == 1) {// 费用申请
            KonkaExpenseClaims kec = new KonkaExpenseClaims();
            kec.setFile_id(Long.parseLong(id));
            kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);

            KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
            filesProperty.setLink_id(Long.parseLong(id));
            filesProperty.setC_type(0);
            filesProperty.setAdd_type(0L);
            List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesPropertyList(filesProperty);

            if (filesPropertyList.size() > 0) {
                for (KonkaoaFilesProperty temp : filesPropertyList) {

                    KonkaoaCategory kc = new KonkaoaCategory();
                    kc.setC_index(temp.getC_index());
                    kc = super.getFacade().getKonkaoaCategoryService().getKonkaoaCategory(kc);
                    if (null != kc) {
                        temp.getMap().put("c_name", kc.getC_name());
                    }
                }

            }
            request.setAttribute("filesPropertyList", filesPropertyList);
            dynaBean.set("column_6", kec.getColumn_6());

        }
        // 审批记录显示
        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(entity.getId());
        fan.setAudit_type(0);
        fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
        request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan));

        // 附件
        KonkaPeAttachments attachment = new KonkaPeAttachments();
        attachment.setLink_id(entity.getId());
        attachment.setLink_tab("FILES");
        attachment.setIs_del(0l);
        request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

        return new ActionForward("/mobile/oaFiles/form.jsp");
    }

    // 无审批流程的审批
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;

        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：1驳回2同意
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        String _audit_user_id = (String) dynaBean.get("audit_user_id");// 下一个审批人,自行指定
        String is_node = (String) dynaBean.get("is_node");

        // 渠道OA不做会签功能
        // String is_countersign = (String) dynaBean.get("is_countersign");
        String username = (String) dynaBean.get("username");
        String userpass = (String) dynaBean.get("userpass");
        String _id = (String) dynaBean.get("id");

        PeProdUser ui = new PeProdUser();

        String suser_id = (String) dynaBean.get("user_id");
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        if (suser_id != null && !suser_id.equals("")) {
            ui.setId(Long.valueOf(suser_id));
            ui.setPass_word(new DESPlus().encrypt(userpass));
            ui = getFacade().getPeProdUserService().getPeProdUser(ui);
        } else {

            ui = checkUser(username, userpass,android_version,ios_version);
        }

        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }

        if (!GenericValidator.isLong(_id)) {
            super.renderText(response, "参数 ID 非法！");
            return null;
        }

        // 取下审批人的名字
        String _audit_user_name = "";
        if (StringUtils.isNotBlank(_audit_user_id) && GenericValidator.isLong(_audit_user_id)) {
            PeProdUser ppu = new PeProdUser();
            ppu.setId(Long.valueOf(_audit_user_id));
            ppu = super.getFacade().getPeProdUserService().getPeProdUser(ppu);
            _audit_user_name = ppu.getUser_name();
        }

        Long id = new Long(_id);
        // 审批
        Long audti_user_id = ui.getId();
        if ("1".equals(is_agent)) {// 代审
            audti_user_id = new Long(agent_user_id);
        }


        //实在没弄明白这个东西是为了搞什么的 －－王坤林2015年07月15日
        super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesProperty(getFilesProperty(form, request, id));


        // 检查当前审批人是不是流程节点中的人
        KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        fau.setLink_id(id);
        fau.setAudit_user_id(audti_user_id);
        fau.setAudit_type(0);
        Long fau_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        if (fau_count < 1) {
            saveError(request, "entity.missing", id.toString());
            super.renderText(response, "审批人信息丢失!");
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
        files.setIs_forward(0l);// 一律不下发
        files.setIs_node(Integer.parseInt(is_node));//这是没固定流程的单子

        KonkaExpenseClaims claims = new KonkaExpenseClaims();
        claims.setFile_id(id);

        files.setExpenseClaims(claims);

        // 审批结点
        KonkaoaFilesAuditNode fau1 = new KonkaoaFilesAuditNode();
        fau1.setLink_id(id);
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau1);

        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
        if (StringUtils.isNotBlank(_audit_user_id) && StringUtils.isNotBlank(_audit_user_name)) {

            Long user_id = new Long(_audit_user_id);
            files.setCur_audit_user_id(user_id);
            KonkaoaFilesAuditNode _filesAuditNode = new KonkaoaFilesAuditNode();
            _filesAuditNode.setAudit_level(max_level + 1);
            _filesAuditNode.setAudit_user_id(user_id);
            _filesAuditNode.setAudit_user(_audit_user_name);
            filesAuditNodeList.add(_filesAuditNode);
        }
        files.setFilesAuditNodeList(filesAuditNodeList);
        @SuppressWarnings("unused")
        String result = "";
        if (!"".equals(_audit_user_id)) {// 不是最后位
            if ("2".equals(audit_result)) {// 同意，继续走审批流程
                files.setCur_audit_user_id(new Long(_audit_user_id));
                files.setFile_status(1);
                filesAuditNode.setAudit_result(2);
                result = "同意";
            } else if ("0".equals(audit_result)) {// 直接转发
                files.setCur_audit_user_id(new Long(_audit_user_id));
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

        if ("1".equals(is_agent)) {// 代审
            filesAuditNode.setAgent_audit_user(ui.getUser_name());
            filesAuditNode.setAgent_audit_user_id(ui.getId());
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
        saveMessage(request, "entity.updated");

        StringBuilder outStr = new StringBuilder();
        outStr.append("success");
        super.renderText(response, outStr.toString());
        return null;
    }


    // 有审批流程的审批
    public ActionForward save1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;

        String username = (String) dynaBean.get("username");
        String userpass = (String) dynaBean.get("userpass");
        String user_id = (String) dynaBean.get("user_id");
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        // 校验用户信息
        PeProdUser ui = new PeProdUser();
        if (user_id != null && !user_id.equals("")) {
            ui.setId(Long.valueOf(user_id));
            ui.setPass_word(new DESPlus().encrypt(userpass));
            ui = getFacade().getPeProdUserService().getPeProdUser(ui);
        } else {
            username = (String) request.getParameter("username");
            ui = checkUser(username, userpass,android_version,ios_version);
        }
        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }



        String _id = (String) dynaBean.get("id");
        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：1驳回2同意
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        // 渠道不做会签
        // String is_countersign = (String) dynaBean.get("is_countersign");

        if (!GenericValidator.isLong(_id)) {
            super.renderText(response, "参数 ID 非法！");
            return null;
        }

        Long id = new Long(_id);

        Long audti_user_id = ui.getId();
        if ("1".equals(is_agent)) {// 代审
            audti_user_id = new Long(agent_user_id);
        }

        super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesProperty(getFilesProperty(form, request, id));

        // 判断当前人是否在审批流程里面
        KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        fau.setLink_id(id);
        fau.setAudit_user_id(audti_user_id);
        fau.setAudit_type(0);
        Long fau_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        if (fau_count < 1) {
            super.renderText(response, "审批人信息丢失!");
            return null;
        }


        // 更新审批节点数据
        KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
        filesAuditNode.setAudit_user_id(audti_user_id);
        filesAuditNode.setLink_id(id);
        filesAuditNode.setAudit_result(new Integer(audit_result));
        filesAuditNode.setAudit_comment(audit_comment);
        filesAuditNode.setAudit_datetime(new Date());

        KonkaoaFiles files = new KonkaoaFiles();
        files.setId(id);
        files.setIs_forward(0L);

        KonkaExpenseClaims claims = new KonkaExpenseClaims();
        claims.setFile_id(id);
        files.setExpenseClaims(claims);

        // 审批结点最多有多少个
        KonkaoaFilesAuditNode fau1 = new KonkaoaFilesAuditNode();
        fau1.setLink_id(id);
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau1);
        if (max_level >= 1) {
            fau1.setAudit_level(1l);
        } else {
            fau1.setAudit_level(1l);
        }

        // fau1 =
        // super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(fau1);
        // // 当前审批节点的审批级别(判断第一个审批节点的审批级别是否为空来区分新旧提交文件)
        // Long first_level = null;
        // if (null != fau1) {
        // first_level = fau1.getAudit_level();
        // }

        Integer file_type = null;// 文件类型:0.文件提交、1.费用申请
        KonkaoaFiles file = new KonkaoaFiles();
        file.setId(id);
        file = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(file);
        file_type = file.getFile_type();

        @SuppressWarnings("unused")
        String result = "";
        if (file_type == 0) {// 文件提交的审批
            /*
             * 文件提交的审批流程（新的）
             */
            KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
            kfan.setAudit_level(max_level);// 判断最后的审批节点是不是当前人
            kfan.setAudit_user_id(ui.getId());
            kfan.setLink_id(id);
            kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(kfan);

            if (null == kfan) {// 不是最后个审批人，文件状态为未审批完（按照原来流程添加的文件的审批节点的level可能为空）
                // 取当前审批人的审批节点
                KonkaoaFilesAuditNode tempfan = new KonkaoaFilesAuditNode();
                Long level = 0L;
                tempfan.setAudit_user_id(ui.getId());
                tempfan.setLink_id(id);
                tempfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(tempfan);
                if (null != tempfan) {
                    level = tempfan.getAudit_level();
                } else {// 如果沒有，则表示数据库的数据有问题
                    super.renderText(response, "mobile:数据丢失,当前人不是审批节点!");
                    return null;
                }

                // 取下一位审批人
                Long _audit_user_id = null;
                KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
                entity.setAudit_level(level + 1);
                entity.setLink_id(id);
                entity = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(entity);

                if (null != entity) {
                    _audit_user_id = entity.getAudit_user_id();
                } else {
                    super.renderText(response, "mobile:下一个审批人不存在!");
                    return null;
                }

                if ("2".equals(audit_result)) {// 同意，继续走审批流程
                    files.setCur_audit_user_id(new Long(_audit_user_id));
                    files.setFile_status(1);
                    filesAuditNode.setAudit_result(2);
                    result = "同意";
                } else if ("0".equals(audit_result)) {// 直接转发
                    files.setCur_audit_user_id(new Long(_audit_user_id));
                    files.setFile_status(1);
                    filesAuditNode.setAudit_result(0);
                    result = "直接转发";
                } else {// 驳回，流程结束
                    // 驳回给谁?手机端的待完成
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
            }
        } else {// 费用申请
            /*
             * 费用审批流程（新的）
             */

            // 判断最后一个人是不是当前人
            KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
            kfan.setAudit_level(max_level);
            kfan.setAudit_user_id(ui.getId());
            kfan.setLink_id(id);
            kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(kfan);

            // 不是最后个审批人，文件状态为未审批完（按照原来流程添加的文件的审批节点的level可能为空）
            if (null == kfan) {
                // 取当前审批人的审批节点
                KonkaoaFilesAuditNode _kfan = new KonkaoaFilesAuditNode();
                Long level = 0L;
                _kfan.setAudit_user_id(ui.getId());
                _kfan.setLink_id(id);
                _kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(_kfan);
                if (null != _kfan) {
                    level = _kfan.getAudit_level();
                } else {// 如果沒有，则表示数据库的数据有问题
                    super.renderText(response, "mobile:数据丢失,当前人不是审批节点!");
                    return null;
                }

                // 取下一位审批人
                KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
                Long _audit_user_id = null;
                entity.setAudit_level(level + 1);
                entity.setLink_id(id);
                entity = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(entity);

                if (null != entity) {
                    _audit_user_id = entity.getAudit_user_id();
                } else {
                    super.renderText(response, "mobile:下一个审批人不存在!");
                    return null;
                }

                if ("2".equals(audit_result)) {// 同意，继续走审批流程
                    files.setCur_audit_user_id(new Long(_audit_user_id));
                    files.setFile_status(1);
                    filesAuditNode.setAudit_result(2);
                    result = "同意";
                } else if ("0".equals(audit_result)) {// 直接转发
                    files.setCur_audit_user_id(new Long(_audit_user_id));
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
            }

            // 文件标识
            Long fileNo = Long.valueOf(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
            files.setDoc_id(fileNo);
        }

        if ("1".equals(is_agent)) {// 代审
            filesAuditNode.setAgent_audit_user(ui.getUser_name());
            filesAuditNode.setAgent_audit_user_id(ui.getId());
        }

        files.setFilesAuditNode(filesAuditNode);
        // 如果发送呈中有此人，则将此人设为已查看
        KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
        fr.setLink_id(id);
        fr.setReceive_id(ui.getId());
        fr.setView_date_time(filesAuditNode.getAudit_datetime());
        files.setFilesRecipient(fr);

        super.getFacade().getKonkaoaFilesService().auditFiles(files);
        // saveMessage(request, "entity.updated");


        StringBuilder outStr = new StringBuilder();
        outStr.append("success");
        super.renderText(response, outStr.toString());
        return null;
    }

    /**
     * @param form
     * @param request
     * @param entity
     * @param id
     * @desc 取文件发送呈数据
     * @author Wang Hao
     * @return
     */
    public KonkaoaFiles getFilesProperty(ActionForm form, HttpServletRequest request, Long id) {
        DynaBean dynaBean = (DynaBean) form;
        String[] receive_type_ids = null;
        String[] receive_dept_ids = null;
        String[] receive_type_names = null;
        List<KonkaoaFilesRecipient> filesRecipientList = new ArrayList<KonkaoaFilesRecipient>();

        KonkaoaFiles entity = new KonkaoaFiles();
        // 是否下发
        entity.setId(id);
        // 发
        receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_1_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_1_names"), ",");
        if (null != receive_type_ids) {
            for (int i = 0; i < receive_type_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(1);
                filesRecipient.setReceive_user_type(0);
                filesRecipientList.add(filesRecipient);
            }
        }

        // 下发给部门
        receive_dept_ids = StringUtils.split((String) dynaBean.get("receive_dept_1_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_dept_1_names"), ",");
        if (null != receive_dept_ids) {
            for (int i = 0; i < receive_dept_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_dept_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(1);
                filesRecipient.setReceive_user_type(1);
                filesRecipientList.add(filesRecipient);
            }
        }

        // 送
        receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_2_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_2_names"), ",");
        if (null != receive_type_ids) {
            for (int i = 0; i < receive_type_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(2);
                filesRecipient.setReceive_user_type(0);
                filesRecipientList.add(filesRecipient);
            }
        }

        // 呈
        receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_3_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_3_names"), ",");
        if (null != receive_type_ids) {
            for (int i = 0; i < receive_type_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(3);
                filesRecipient.setReceive_user_type(0);
                filesRecipientList.add(filesRecipient);
            }
        }
        entity.setFilesRecipientList(filesRecipientList);
        return entity;
    }


    @Override
    public ActionForward GetList04(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        // 用户名
        String username = (String) dynaBean.get("username");
        // 密码
        String userpass = (String) dynaBean.get("userpass");
        // 判断是否是静态页面来的数据，目的处理乱码问题
        String from_html = (String) dynaBean.get("from_html");
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
        if ("1".equals(from_html)) {
            username = URLDecoder.decode(username, "utf-8");
            userpass = URLDecoder.decode(userpass, "utf-8");
        }

        PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

        if (null == peProdUser) {
            logger.warn("用户 {}/{} 不存在", username, userpass);
            super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
            return null;
        }

        Boolean role_id_lt_30_and_200_300 = false;// 总部角色
        PeRoleUser pu = new PeRoleUser();
        pu.setUser_id(peProdUser.getId());
        List<PeRoleUser> puList = super.getFacade().getPeRoleUserService().getPeRoleUserList(pu);
        if (puList.size() > 0) {
            for (PeRoleUser p : puList) {
                if (p.getRole_id() < 30 || (p.getRole_id() > 200 && p.getRole_id() < 300)) {
                    role_id_lt_30_and_200_300 = true;
                }
            }
        }

        PeProdUser t = new PeProdUser();
        // if (StringUtils.isBlank(size)) {
        // t.getRow().setFirst(0);
        // t.getRow().setCount(30);
        // }
        // t.getMap().put("user_name_like", size);
        if (!role_id_lt_30_and_200_300) {
            t.getMap().put("dept_id_in", getKonkaDeptForFgs(peProdUser.getDept_id()).getDept_id());
        }

        List<PeProdUser> tList = super.getFacade().getPeProdUserService().getPeProdUserList(t);
        List<PeProdUser> entityList = new ArrayList<PeProdUser>();
        for (PeProdUser _t : tList) {
            PeProdUser entity = new PeProdUser();
            entity.setId(_t.getId());
            entity.setUser_name(_t.getUser_name());
            entityList.add(entity);
        }
        super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
        return null;
    }

    public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String save_name = (String) dynaBean.get("save_name");
        if (StringUtils.isBlank(save_name)) {
            super.renderJavaScript(response, "window.onload=function(){alert('错误！');history.back();}");
            return null;
        }
        KonkaPeAttachments attachments = new KonkaPeAttachments();
        attachments.setSave_name(save_name);
        attachments.setIs_del(0L);
        List<KonkaPeAttachments> attachmentsList = getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachments);

        if (attachmentsList.size() == 0) {
            super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
            return null;
        }
        KonkaPeAttachments peAttachments = attachmentsList.get(0);

        if (peAttachments.getSave_path() == null) {
            super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
            return null;
        } else {
            try {
                // 获取系统实际路径
                String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

                String path = new String(SystemPath + peAttachments.getSave_path());
                File file = new File(path);
                if (file.exists()) {
                    String filename = file.getName();

                    // 取得文件的扩展名ext
                    String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

                    InputStream fis = new BufferedInputStream(new FileInputStream(path));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();

                    response.reset();
                    response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(peAttachments.getFile_name(), "UTF-8"));
                    response.addHeader("Content-Length", "" + file.length()); // 设置返回的文件类型
                    OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); // 得到向客户端输出二进制数据的对象
                    // 根据扩展名声称客户端浏览器mime类型
                    if (ext.equals("DOC"))
                        response.setContentType("application/msword");
                    else
                        response.setContentType("application/octet-stream"); // 设置返回的文件类型
                    toClient.write(buffer); // 输出数据
                    toClient.flush();
                    toClient.close();
                } else {
                    super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                super.renderJavaScript(response, "window.onload=function(){alert('下载出错！');history.back();}");
                return null;
            }
        }
        return null;
    }

}
