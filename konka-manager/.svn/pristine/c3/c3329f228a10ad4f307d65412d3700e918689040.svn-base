package com.ebiz.mmt.web.struts;

import java.math.BigDecimal;
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

import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Derek
 * @version Build 2012-04-27
 */
@Deprecated
public class OaFileAction extends MobileListAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.unknow(mapping, form, request, response);
    }

    @Override
    public ActionForward unknow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ActionForward("/mobile/oafile/list.jsp");
    }

    public ActionForward getCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        // super.encodeCharacterForGetMethod(dynaBean, request);

        // 用户名
        String username = (String) dynaBean.get("username");
        // 密码
        String userpass = (String) dynaBean.get("userpass");

        if (StringUtils.isNotBlank(username)) {
            username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        }
      //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        PeProdUser ui = checkUser(username, userpass,android_version,ios_version);
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
        Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);

        super.renderText(response, String.valueOf(Math.ceil(recordCount)));
        return null;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);
        // 用户名
        String username = (String) dynaBean.get("username");
        // 密码
        String userpass = (String) dynaBean.get("userpass");

        if (StringUtils.isNotBlank(username)) {
            username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        }
        //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        PeProdUser ui = checkUser(username, userpass,android_version,ios_version);

        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
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

        StringBuilder outStr = new StringBuilder();
        if (recordCount > 0) {
            List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(entity);

            for (KonkaoaFiles t : cList) {
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
                } else if (t.getFile_type() == 1) {
                    _t.setEventType("费用申请");
                }

                if ("1".equals(t.getMap().get("agent_audit").toString()) || "0".equals(t.getMap().get("agent_audit").toString())) {
                    _t.setEventDo("<a href='OaFile.do?method=edit&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                            + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "&username=" + username + "&userpass=" + userpass
                            + "'><img src=\"" + request.getContextPath() + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                } else {
                    _t.setEventDo("<a href='OaFile.do?method=edit&is_countersign=is_countersign&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0")
                            + "&agent_user_id=" + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "&username=" + username + "&userpass="
                            + userpass + "'><img src=\"" + request.getContextPath() + "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>");
                }
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
                    _t.setEventDo("<a href='OaFile.do?method=edit&id=" + t.getId() + "&username=" + username + "&userpass=" + userpass + "'><img src=\"" + request.getContextPath()
                            + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                } else if (t.getFile_type() == 1) {
                    _t.setEventType("费用申请");
                    _t.setEventDo("<a href='OaFile.do?method=edit1&file_id=" + t.getId() + "&username=" + username + "&userpass=" + userpass + "'><img src=\""
                            + request.getContextPath() + "/mobile/images/huizong.jpg\" width=\"49\" height=\"30\" /></a>");
                }

                list.add(_t);
            }

            outStr.append("<div class=\"headtab31\" ></div>\n" + "<table width=\"100%\" border=\"0\"  class=\"rtab1\">\n");
            outStr.append("<tr>\n"
            // + "    <th width=\"9%\">序号</th>\n"
                    + "    <th width=\"90%\" align=\"left\" colspan=\"2\">标题</th>\n" + "    <th width=\"10%\" style=\" background-image:none\">操作</th>\n" + "</tr>");
            int ii = 1;
            for (KonkaoaEventInfo _t : list) {
                String url = (_t.getEventDo().split("href='")[1]).split("'>")[0];
                outStr.append("<tr style=\"cursor: pointer;\" onclick=\"goview('" + url + "');\">\n" + "   <td align=\"center\">" + ii
                        + "</td>\n"
                        + "   <td align=\"left\">"
                        // outStr.append("<tr>\n" + "   <td align=\"center\">" +
                        // ii + "</td>\n" +
                        // "   <td align=\"left\">"
                        + _t.getEventiltle() + "<br />\n" + "   <span class=\"leixi\">类型：" + _t.getEventType() + "</span><span class=\"leixi\">发起人：" + _t.getFromPerson()
                        + "</span> <span class=\"leixi\">时间：" + df1.format(_t.getEnterDate()) + "</span><br /></td>\n" + "   <td align=\"center\"><span class=\"xt_sp\">"
                        // + "<a href=\""
                        + _t.getEventDo()
                        // + request.getContextPath()
                        // + "/OaFile.do?method=edit&username="
                        // + username
                        // + "&id="
                        // + _t.getId()
                        // + "\">"
                        // + "<img src=\""
                        // + request.getContextPath()
                        // +
                        // "/mobile/images/xt_shengpi.jpg\" width=\"49\" height=\"30\" /></a>"
                        + "</span></td>\n" + "</tr>");
                ii++;
            }
            for (int _i = ii; _i <= pageSize; _i++) {
                outStr.append("<tr><td colspan=\"3\">&nbsp;</td></tr>");
            }
            outStr.append("</table>\n" + "<div class=\"xt_fenye\"><a onclick=\"goback(" + currentPage + ",'" + username + "','" + userpass + "');\"><img src=\""
                    + request.getContextPath() + "/mobile/images/xt_shagnyiye.jpg\" width=\"66\" height=\"24\" /></a> <a onclick=\"goforward(" + currentPage + ",'" + username
                    + "','" + userpass + "');\"><img src=\"" + request.getContextPath() + "/mobile/images/xt_xiayye.jpg\" width=\"66\" height=\"24\" /></a></div>");
            super.renderText(response, outStr.toString());
        }
        return null;
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        String username = (String) dynaBean.get("username");
        if (StringUtils.isNotBlank(username)) {
            username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        }
        dynaBean.set("user_name", username);
        return new ActionForward("/mobile/oafile/form.jsp");
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

        if (StringUtils.isNotBlank(username)) {
            username = new String(username.getBytes("iso-8859-1"), "UTF-8");
        }

        KonkaoaFiles entity = new KonkaoaFiles();
        entity.setId(Long.parseLong(id));
        entity = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(entity);
        StringBuilder outStr = new StringBuilder();
        outStr.append("<table width=\"100%\" border=\"0\"  class=\"rtab2\">\n" + "  <tr>\n" + "    <th colspan=\"2\" width=\"90%\" align=\"left\"  class=\"bno\" id=\"filetitle\">");
        outStr.append(entity.getFile_title());// 标题
        outStr.append("</th>\n" + "  </tr>\n" + "  <tr>\n" + "    <td colspan=\"2\" width=\"90%\" align=\"left\">申请人：");
        outStr.append(entity.getSubmit_user());// 申请人
        outStr.append("</td></tr><tr>\n" + "    <td colspan=\"2\" width=\"90%\" align=\"left\" class=\"bno\">负责人：");
        outStr.append(entity.getApply_user_name());// 负责人
        outStr.append("<br />\n" + "</td>\n" + "  </tr>\n" + "  <tr>\n" + "    <td colspan=\"2\" width=\"90%\" align=\"left\">电话：");
        if (null != entity.getApply_user_tel() && !"".equals(entity.getApply_user_tel())) {
            outStr.append(entity.getApply_user_tel());// 电话
        } else {
            outStr.append("");// 电话
        }
        outStr.append("</td></tr><tr>\n"
        /*
         * + "    <td align=\"left\" class=\"bno\">编号："); outStr.append(entity.getFile_no());// 编号
         * outStr.append("</td>\n" + "  </tr>\n" + "  <tr>\n"
         */
        + "    <td colspan=\"2\" width=\"90%\" align=\"left\">时间：");
        outStr.append(df1.format(entity.getSubmit_datetime()));// 时间
        outStr.append("</td></tr><tr>\n"
        /*
         * + "    <td align=\"left\" class=\"bno\">时限："); outStr.append(entity.getTime_limit());//
         * 时限 outStr.append("</td>\n" + "  </tr>\n" + "  <tr>\n"
         */
        + "    <td align=\"left\">内容：<br />\n" + "      <label for=\"textarea\"></label>\n");
        // outStr
        // .append("    <textarea name=\"textarea\" id=\"textarea\" readonly=\"readonly\" cols=\"45\" rows=\"5\" class=\"qs\">");
        // outStr.append(entity.getContent());// 内容
        // outStr.append("</textarea>\n");

        if (null != entity.getContent() && !"".equals(entity.getContent())) {
            outStr.append(entity.getContent());
        } else {
            outStr.append("");// 内容
        }
        if (entity.getFile_type() == 1) {// 费用申请
            KonkaExpenseClaims kec = new KonkaExpenseClaims();
            kec.setFile_id(Long.parseLong(id));
            kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);

            outStr.append("</td></tr><tr>\n" + " <td colspan=\"2\" width=\"90%\" align=\"left\">文件类型：费用申请");
            outStr.append("</td></tr><tr>\n" + " <td colspan=\"2\" width=\"90%\" align=\"left\">");
            outStr.append("<table width=\"100%\" border=\"0\"  class=\"rtab2\">\n" + "  <tr>\n" + "    <td width=\"60%\" align=\"left\">费用类别</td>\n"
                    + "	   <td width=\"20%\" align=\"center\">数量</td>\n" + "    <td width=\"20%\" align=\"center\">金额</td>\n" + "</tr>");
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
                        if (null != kc.getC_name() && !"".equals(kc.getC_name())) {// 类别
                            outStr.append("<tr><td align=\"left\">" + kc.getC_name() + "</td>");
                        } else {
                            outStr.append("<tr><td align=\"left\"></td>");
                        }
                        if (null != temp.getAmount() && !"".equals(String.valueOf(temp.getAmount()))) {// 数量
                            outStr.append("<td align=\"center\">" + temp.getAmount() + "</td>");
                        } else {
                            outStr.append("<tr><td align=\"left\"></td>");
                        }
                        if (null != temp.getCost() && !"".equals(String.valueOf(temp.getCost()))) {// 金额
                            outStr.append("<td align=\"center\">" + temp.getCost() + "</td></tr>");
                        } else {
                            outStr.append("<tr><td align=\"left\"></td>");
                        }

                    }
                    outStr.append("</table>\n");
                    // outStr.append("</td></tr><tr>\n" +
                    // "    <td colspan=\"2\" width=\"90%\" align=\"left\">"
                    // + kc.getC_name() + "：");
                    // outStr.append(temp.getCost());// 类型和价格

                }

            }
            outStr.append("</td></tr><tr>\n" + " <td colspan=\"2\" width=\"90%\" align=\"left\">费用总额：" + kec.getColumn_6());
            // outStr.append("</td></tr><tr>\n" +
            // "    <td colspan=\"2\" width=\"90%\" align=\"left\">");
        } else {
            outStr.append("</td></tr><tr>\n" + " <td colspan=\"2\" width=\"90%\" align=\"left\">文件类型：文件提交");
        }
        outStr.append("    <br /></td>\n" + "  </tr>\n" + "  <tr>\n" + "    <td align=\"left\">审批意见：<br /><input type=\"hidden\" id=\"username\">\n"
                + "    <textarea name=\"audit_comment\" id=\"audit_comment\" cols=\"45\" rows=\"5\" class=\"qs1\">");
        outStr.append("</textarea>\n" + "    <br /></td>\n" + "  </tr>\n" + "</table>\n" + "<div class=\"xt_fenye1\"><a onclick=\"pass();\"><img src=\"" + request.getContextPath()
                + "/mobile/images/tongyi.jpg\" width=\"93\" height=\"38\" /></a> <a onclick=\"back();\"><img src=\"" + request.getContextPath()
                + "/mobile/images/bohui.jpg\" width=\"93\" height=\"38\" /></a></div>");
        outStr.append("<input type=\"hidden\" id=\"id\" name=\"id\" type=\"text\" value=\"" + entity.getId() + "\"></input>"
                + "<input type=\"hidden\" id=\"username\" name=\"username\" type=\"text\" value=\"" + username + "\"></input>"
                + "<input type=\"hidden\" id=\"userpass\" name=\"userpass\" type=\"text\" value=\"" + userpass + "\"></input>"
                + "<input type=\"hidden\" id=\"audit_result\" name=\"audit_result\"></input>" + "<input type=\"hidden\" id=\"is_agent\" name=\"is_agent\" value=\"" + is_agent
                + "\"></input>" + "<input type=\"hidden\" id=\"agent_user_id\" name=\"agent_user_id\" value=\"" + agent_user_id + "\"></input>"
                + "<input type=\"hidden\" id=\"file_type\" name=\"file_type\" value=\"" + file_type + "\"></input>");// 附加必要信息
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

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        super.encodeCharacterForGetMethod(dynaBean, request);

        String _id = (String) dynaBean.get("id");
        String username = (String) dynaBean.get("username");
        String userpass = (String) dynaBean.get("userpass");
        //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

        PeProdUser ui = checkUser(username, userpass,android_version,ios_version);
        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }
        String column_6 = (String) dynaBean.get("column_6");
        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：1驳回2同意
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        String[] c_index = request.getParameterValues("c_index");

        if (!GenericValidator.isLong(_id)) {
            super.renderText(response, "参数 ID 非法！");
            return null;
        }

        Long id = new Long(_id);

        // 审批
        Long audti_user_id = ui.getId();
        if ("1".equals(is_agent)) {// 代审
            audti_user_id = new Long(agent_user_id);
        }

        super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesProperty(getFilesProperty(form, request, id));

        KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        fau.setLink_id(id);
        fau.setAudit_user_id(audti_user_id);
        fau.setAudit_type(0);
        Long fau_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        if (fau_count < 1) {
            saveError(request, "entity.missing", _id);
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

        // 费用类别
        List<KonkaoaFilesProperty> propertyList = new ArrayList<KonkaoaFilesProperty>();
        if (c_index != null) {
            for (int i = 0; i < c_index.length; i++) {
                KonkaoaFilesProperty fileProperty = new KonkaoaFilesProperty();
                fileProperty.setC_index(Long.valueOf(c_index[i]));
                String app_money = (String) dynaBean.get("app_money_" + c_index[i]);
                String app_amount = (String) dynaBean.get("app_amount_" + c_index[i]);
                if (StringUtils.isNotBlank(app_money)) fileProperty.setCost(new BigDecimal(app_money.trim()));
                if (StringUtils.isNotBlank(app_amount)) fileProperty.setAmount(new BigDecimal(app_amount.trim()));
                fileProperty.setLink_id(id);
                fileProperty.setAdd_type(1L);
                fileProperty.setC_type(0);
                fileProperty.setAdd_date(new Date());
                fileProperty.setAdd_user_id(super.getSessionUserInfo(request).getId());
                fileProperty.setAdd_dept_id(super.getSessionUserInfo(request).getDept_id());
                propertyList.add(fileProperty);
            }
        }

        files.setFilesPropertyList(propertyList);

        KonkaExpenseClaims claims = new KonkaExpenseClaims();
        claims.setFile_id(id);

        if (StringUtils.isNotBlank(column_6)) {
            claims.setColumn_6(new BigDecimal(column_6));
        }
        files.setExpenseClaims(claims);

        // 审批结点
        KonkaoaFilesAuditNode fau1 = new KonkaoaFilesAuditNode();
        fau1.setLink_id(id);
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau1);

        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
        String audit_user_id = "";// 一律不转发
        String audit_user_name = "";// 一律不转发
        if (!"".equals(audit_user_id)) {
            Long user_id = new Long(audit_user_id);
            files.setCur_audit_user_id(user_id);
            KonkaoaFilesAuditNode _filesAuditNode = new KonkaoaFilesAuditNode();
            _filesAuditNode.setAudit_level(max_level + 1);
            _filesAuditNode.setAudit_user_id(user_id);
            _filesAuditNode.setAudit_user(audit_user_name);
            filesAuditNodeList.add(_filesAuditNode);
        }
        files.setFilesAuditNodeList(filesAuditNodeList);


        @SuppressWarnings("unused")
        String result = "";
        if (!"".equals(audit_user_id)) {// 不是最后位
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
}
