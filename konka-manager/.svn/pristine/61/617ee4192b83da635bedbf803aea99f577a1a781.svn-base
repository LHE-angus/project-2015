package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesContent;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * 有流程节点
 * 
 * 只有审批时,只有同意2和驳回1,没有转发0 (没有流程的审批才有转发功能)
 * 
 */
public class SelfEventCenterAction extends BaseMmtoaAction {
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        super.getModPopeDom(form, request);
        setNaviStringToRequestScope(form, request);

        DynaBean dynaBean = (DynaBean) form;
        Pager pager = (Pager) dynaBean.get("pager");

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        String role_ids = "-1";
        Boolean role_id_gt_30_btw_200_300 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 30 && peRoleUser.getRole_id() >= 200 && peRoleUser.getRole_id() < 300) role_id_gt_30_btw_200_300 = true;
            role_ids = role_ids + "," + peRoleUser.getRole_id();
        }

        int i = 0;
        // 待办事件
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


        Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesCountForAudit(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());

        entity.getRow().setCount(recordCount.intValue());
        // 查找需要审批的文件
        List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(entity);

        String contextPath = getCtxPath(request) == null ? "" : getCtxPath(request);

        for (KonkaoaFiles t : cList) {
            i++;
            KonkaoaEventInfo _t = new KonkaoaEventInfo();
            _t.setId(t.getId());
            _t.setEnterDate(t.getSubmit_datetime());
            _t.setEventiltle(t.getFile_title());
            _t.setSequence(i);
            _t.setFromPerson(t.getSubmit_user());

            // 提交的文件类型
            if (t.getFile_type() == 0) {
                _t.setEventType("文件提交");
            } else if (t.getFile_type() == 1) {
                _t.setEventType("费用申请");
            }

            if ("1".equals(t.getMap().get("agent_audit").toString()) || "0".equals(t.getMap().get("agent_audit").toString())) {
                if (t.getIs_node() == 1) {
                    _t.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=954000&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                            + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>审批</a>");
                } else {
                    _t.setEventDo("<a href='" + contextPath + "/manager/oa/SelfEventCenter.do?method=edit&mod_id=954000&is_agent="
                            + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id=" + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id="
                            + t.getId() + "'>审批</a>");
                }
            } else {

                // 是否是自定义流程 1是,0否
                if (t.getIs_node() == 1) {
                    _t.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=954000&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                            + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>会签</a>");
                } else {
                    _t.setEventDo("<a href='" + contextPath + "/manager/oa/SelfEventCenter.do?method=edit&mod_id=954000&is_agent="
                            + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id=" + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id="
                            + t.getId() + "'>会签</a>");
                }
            }
            list.add(_t);
        }


        // 未提交的审批,并且提交是你自己的.
        // KonkaoaFiles file5 = new KonkaoaFiles();
        // file5.setFile_status(0);
        // file5.setIs_del(0);
        // file5.setSubmit_user_id(ui.getId());
        // List<KonkaoaFiles> file5List =
        // super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);
        //
        // for (KonkaoaFiles t : file5List) {
        // recordCount++;
        // i++;
        // KonkaoaEventInfo _t = new KonkaoaEventInfo();
        // _t.setId(t.getId());
        // _t.setEnterDate(t.getSubmit_datetime());
        // _t.setEventiltle(t.getFile_title());
        // _t.setSequence(i);
        // _t.setFromPerson(t.getSubmit_user());
        // if (t.getFile_type() == 0) {
        // if (t.getIs_node() == 1) {
        // _t.setEventType("文件提交");
        // _t.setEventDo("<a href='FilesSubmit.do?method=edit&mod_id=954000&id=" + t.getId() +
        // "'>审批</a>");
        // } else {
        // _t.setEventType("文件提交");
        // _t.setEventDo("<a href='" + contextPath +
        // "/manager/oa/FilesSubmit.do?method=edit&mod_id=954000&id=" + t.getId() + "'>审批</a>");
        // }
        // } else if (t.getFile_type() == 1) {
        // if (t.getIs_node() == 1) {
        // _t.setEventType("费用申请");
        // _t.setEventDo("<a href='ExpenseClaims.do?method=edit&mod_id=951000&file_id=" + t.getId()
        // + "'>审批</a>");
        // } else {
        // _t.setEventType("费用申请");
        // _t.setEventDo("<a href='" + contextPath +
        // "/manager/oa/ExpenseClaims.do?method=edit&mod_id=951000&file_id=" + t.getId() +
        // "'>审批</a>");
        // }
        // }
        //
        // list.add(_t);
        // }

        // 资质审核
        KonkaXxZmdAuditUserInfo kInfo = new KonkaXxZmdAuditUserInfo();
        kInfo.getMap().put("db_role_ids", role_ids);
        if (!role_id_gt_30_btw_200_300) {
            KonkaDept fgs = getKonkaDeptForFgs(ui.getDept_id());
            if (null != fgs) {
                kInfo.setDept_id(fgs.getDept_id());
            } else {
                kInfo.setDept_id(ui.getDept_id());
            }
        }
        List<KonkaXxZmdAuditUserInfo> kInfoList = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAuditUserInfoForRoleIdList(kInfo);
        if (kInfoList.size() > 0) {
            for (KonkaXxZmdAuditUserInfo temp1 : kInfoList) {
                KonkaoaEventInfo _tr = new KonkaoaEventInfo();
                if (null != temp1.getMap().get("role_id")) {
                    _tr.setEventiltle(temp1.getUser_name() + "客户资质申请");
                    // _tr.getMap().put("title", temp1.getUser_name() +
                    // "客户资质申请");
                    _tr.setEnterDate(temp1.getAdd_date());
                    _tr.setEventType("专卖店资质申请");
                    _tr.setFromPerson(temp1.getAdd_user_name());
                    _tr.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='" + contextPath
                            + "/manager/zmd/KonkaXxZmdAuditUserInfoAudit.do?method=audit&zmd_user_id=" + temp1.getZmd_user_id() + "&role_id=" + temp1.getMap().get("role_id")
                            + "&mod_id=810200" + "'\">审核</a>");
                    list.add(_tr);
                }
            }
        }
        // 专卖店备案审核
        KonkaXxZmd zmd = new KonkaXxZmd();
        zmd.getMap().put("db_role_ids", role_ids);
        if (!role_id_gt_30_btw_200_300) {
            KonkaDept fgs = getKonkaDeptForFgs(ui.getDept_id());
            if (null != fgs) {
                zmd.setDept_id(fgs.getDept_id());
            } else {
                zmd.setDept_id(ui.getDept_id());
            }
        }
        List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdForRoleIdList(zmd);
        if (zmdList.size() > 0) {
            for (KonkaXxZmd temp1 : zmdList) {
                KonkaoaEventInfo _tr = new KonkaoaEventInfo();
                if (null != temp1.getMap().get("role_id")) {
                    _tr.setEventiltle("专卖店" + temp1.getZmd_sn() + "备案申请");
                    // _tr.getMap().put("title", "专卖店" + temp1.getZmd_sn() +
                    // "备案申请");
                    _tr.setEventType("专卖店资质申请");
                    _tr.setFromPerson(temp1.getWrite_man());
                    _tr.setEnterDate(temp1.getApply_date());
                    _tr.setEventDo("<a style=\"cursor:pointer;color:blue;\" onclick=\"javascript:window.location.href='" + contextPath
                            + "/manager/zmd/KonkaXxZmdVerification.do?method=edit&zmd_id=" + temp1.getZmd_id() + "&role_id=" + temp1.getMap().get("role_id") + "&mod_id=810200"
                            + "'\">审核</a>");
                    list.add(_tr);
                }
            }
        }
        request.setAttribute("entityList", list);
        return mapping.findForward("list");
    }


    /**
     * 有流程的事件审批
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // 文件属性
        // 0,选择多种费用类别
        KonkaoaFilesProperty filesProperty = new KonkaoaFilesProperty();
        filesProperty.setLink_id(files.getId());
        filesProperty.setC_type(0);
        filesProperty.setAdd_type(0L);
        List<KonkaoaFilesProperty> filesPropertyList = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesPropertyList(filesProperty);

        KonkaoaFilesProperty property = new KonkaoaFilesProperty();
        property.setLink_id(files.getId());
        property.setC_type(0);
        property.setAdd_type(1L);
        List<KonkaoaFilesProperty> propertyList = super.getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesPropertyList(property);
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
            appProperty.setC_index(kfp.getC_index());

            List<KonkaoaFilesProperty> appList = getFacade().getKonkaoaFilesPropertyService().getKonkaoaFilesPropertyList(appProperty);
            if (0 != appList.size()) {
                kfp.getMap().put("app_amount", appList.get(appList.size() - 1).getAmount());
                kfp.getMap().put("app_money", appList.get(appList.size() - 1).getCost());
                kfp.getMap().put("appList", appList);
            } else {
                kfp.getMap().put("app_amount", kfp.getAmount());
                kfp.getMap().put("app_money", kfp.getCost());
            }

        }
        request.setAttribute("filesPropertyList", filesPropertyList);

        // 费用申请相关信息
        KonkaExpenseClaims kec = new KonkaExpenseClaims();
        kec.setFile_id(files.getId());
        kec = super.getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(kec);
        if (null != kec) {
            KonkaoaFilesContent kfc = new KonkaoaFilesContent();
            kfc.setLink_id(kec.getFile_id());
            kfc = super.getFacade().getKonkaoaFilesContentService().getKonkaoaFilesContent(kfc);
            if (kfc != null) {
                kec.getMap().put("content", kfc.getContent());
            }
            request.setAttribute("konkaExpenseClaims", kec);
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
        List<KonkaoaFilesRecipient> filesRecipientList = getFacade().getKonkaoaFilesRecipientService().getKonkaoaFilesRecipientList(fr);
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
        request.setAttribute("attachmentList", getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsList(attachment));

        // 流程的审批人 所有
        KonkaoaFilesAuditNode auditnodes = new KonkaoaFilesAuditNode();
        auditnodes.setLink_id(files.getId());
        auditnodes.setAudit_type(0);
        request.setAttribute("_filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(auditnodes));

        // 审批记录已审批过的节点
        List<KonkaoaFilesAuditNode> auditNodeLists = new ArrayList<KonkaoaFilesAuditNode>();
        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
        fan.getMap().put("order_by_id_desc", "order_by_id_desc");
        auditNodeLists = getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan);
        request.setAttribute("filesAuditNodeList", auditNodeLists);


        dynaBean.set("now", new Date());

        // add by zhou,OA有流程部分需要指定驳回的节点
        // 当前这条记录下一个需要指的人
        Long nowNodeId = files.getCur_audit_user_id();
        Long nowLevel = null;
        // 流程的审批人 所有
        KonkaoaFilesAuditNode xnode = new KonkaoaFilesAuditNode();
        xnode.setLink_id(files.getId());
        xnode.setAudit_user_id(nowNodeId);
        xnode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(xnode);
        if (xnode != null) {
            nowLevel = xnode.getAudit_level();
        }
        Set<KonkaoaFilesAuditNode> returnNodeSet = new HashSet<KonkaoaFilesAuditNode>();
        for (KonkaoaFilesAuditNode k : auditNodeLists) {
            if (k.getAudit_level() < nowLevel) {
                returnNodeSet.add(k);
            }
        }
        request.setAttribute("returnNodeSet", returnNodeSet);

        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return list(mapping, form, request, response);
        }
        if (!isTokenValid(request)) {
            saveError(request, "errors.token");
            return list(mapping, form, request, response);
        }
        resetToken(request);

        DynaBean dynaBean = (DynaBean) form;
        String _id = (String) dynaBean.get("id");

        String column_6 = (String) dynaBean.get("column_6");

        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：2同意 ;1驳回,-1转发

        // 需要驳回的审核节点,此处不是要驳回的那个节点的具体的人或具体的岗位.而是第几个节点
        // 如果需要计算这个节点是谁或者是具体谁来审,还要查询一次
        // return_node:submit_user_id${af.map.submit_user_id}
        // 驳回的哪个节点,如果是submit_user_id12143 这样开头的,表示是驳回到制单人.此时流程审批改为第一节点,状态改成制单人
        String return_level = (String) dynaBean.get("return_node");

        String content = (String) dynaBean.get("content");// 评语
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        String mod_id = (String) dynaBean.get("mod_id");
        String[] c_index = request.getParameterValues("c_index");

        String msg = "";

        String result = "";

        if (!GenericValidator.isLong(_id)) {
            saveError(request, "errors.long", _id);
            return mapping.findForward("list");
        }

        if (audit_result != null && audit_result.equals("1")) {
            if (return_level == null || return_level.equals("")) {
                saveDirectlyError(request, "没有指定驳回的审核节点.");
                return mapping.findForward("list");
            }
        }
        // konkaoafile.id
        Long id = new Long(_id);

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        /**
         * 获取当前审核用户
         */
        KonkaoaFiles existsfiles = new KonkaoaFiles();
        existsfiles.setId(id);
        existsfiles = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(existsfiles);
        if (existsfiles != null && existsfiles.getCur_audit_user_id() != null) {
            Long user_id = ui.getId();
            // 如果该文件的审核用户和当前用户不是同一个用户，提示错误
            if (!user_id.equals(existsfiles.getCur_audit_user_id())) {

                saveError(request, "entity.user.invalid", _id);
                return new ActionForward("/chengduoa/SelfEventCenter.do?mod_id=" + mod_id, true);
            }
        }

        // 审批
        Long auditUserId = ui.getId();
        if ("1".equals(is_agent)) {// 代审
            auditUserId = new Long(agent_user_id);
        }
        // delete filesRecipient by link_id
        super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesProperty(super.getFilesProperty(form, request, id));

        // 1检查是不是当前人是不是在流程里面
        KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        fau.setLink_id(id);
        fau.setAudit_user_id(auditUserId);
        // fau.setAudit_type(0);
        Long fau_count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        if (fau_count < 1) {
            saveError(request, "entity.missing", _id);
            // 直接退出.不往下操作
            return new ActionForward("/chengduoa/SelfEventCenter.do?mod_id=" + mod_id, true);
        }

        // 2进入审批环节
        // 需要去更新审批节点的值,加上一些审批意见,审批时间
        KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
        filesAuditNode.setAudit_user_id(auditUserId);// 当前人ID或代审人
        filesAuditNode.setLink_id(id);
        filesAuditNode.setAudit_result(new Integer(audit_result));
        filesAuditNode.setAudit_comment(audit_comment);
        filesAuditNode.setAudit_datetime(new Date());

        // 文件
        KonkaoaFiles files = new KonkaoaFiles();
        files.setId(id);
        // 有流程的.重要. 某些情况下,或之前的开发的版本页面传参丢失
        files.setIs_node(1);
        files.setIs_forward(new Long((String) dynaBean.get("is_xiafa")));// 设置是否下发

        // 费用类别
        List<KonkaoaFilesProperty> propertyList = new ArrayList<KonkaoaFilesProperty>();
        if (c_index != null) {
            for (int i = 0; i < c_index.length; i++) {
                String app_money = (String) dynaBean.get("app_money_" + c_index[i]);
                String app_amount = (String) dynaBean.get("app_amount_" + c_index[i]);

                KonkaoaFilesProperty fileProperty = new KonkaoaFilesProperty();
                fileProperty.setC_index(Long.valueOf(c_index[i]));
                fileProperty.setCost(new BigDecimal(app_money.trim()));
                fileProperty.setAmount(new BigDecimal(app_amount.trim()));
                fileProperty.setLink_id(id);
                fileProperty.setAdd_type(1L);
                fileProperty.setC_type(0);
                fileProperty.setAdd_date(new Date());
                fileProperty.setAdd_user_id(super.getSessionUserInfo(request).getId());
                fileProperty.setAdd_dept_id(super.getSessionUserInfo(request).getDept_id());
                propertyList.add(fileProperty);
            }
        }
        // 设置费用相关的
        files.setFilesPropertyList(propertyList);

        KonkaExpenseClaims claims = new KonkaExpenseClaims();
        claims.setFile_id(id);
        if (StringUtils.isNotBlank(column_6)) {
            claims.setColumn_6(new BigDecimal(column_6));
        }
        files.setExpenseClaims(claims);

        // 计算审批级别
        KonkaoaFilesAuditNode kfanode = new KonkaoaFilesAuditNode();
        kfanode.setLink_id(id);
        // 这个流程总共有多少个人来审
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(kfanode);
        if (max_level >= 1) {
            kfanode.setAudit_level(1l);
        }
        kfanode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(kfanode);
        Long first_level = null;// 当前审批节点的审批级别(判断第一个审批节点的审批级别是否为空来区分新旧提交文件)
        if (null != kfanode) {
            first_level = kfanode.getAudit_level();
        }
        // 文件类型:0.文件提交、1.费用申请
        Integer file_type = null;
        KonkaoaFiles file = new KonkaoaFiles();
        file.setId(id);
        file = super.getFacade().getKonkaoaFilesService().getKonkaoaFiles(file);
        file_type = file.getFile_type();

        // ####文件提交的审批start
        if (file_type == 0) {
            // 用于判断当前正在审批的那个人是不是流程最后需要审的人
            KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
            kfan.setAudit_level(max_level);
            kfan.setAudit_user_id(ui.getId());
            kfan.setLink_id(id);
            kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(kfan);
            // 不是最后个审批人，文件状态为未审批完
            if (null == kfan && first_level != null) {
                // 取当前审批人的节点
                // TODO 如果同一个流程,同一个人重复出现,将出现bug
                KonkaoaFilesAuditNode tempfan = new KonkaoaFilesAuditNode();
                Long level = 0L;
                tempfan.setAudit_user_id(ui.getId());
                tempfan.setLink_id(id);
                tempfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(tempfan);
                if (null != tempfan) {
                    level = tempfan.getAudit_level();
                    filesAuditNode.setAudit_level(level);
                } else {// 如果沒有，则表示数据库的数据有问题
                    saveError(request, "errors.token");
                    return list(mapping, form, request, response);
                }

                // 取下一位审批人的节点
                Long next_audit_user_id = null;

                if ("2".equals(audit_result)) {// 同意，继续走审批流程
                    KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                    nextAuditNode.setAudit_level(level + 1);
                    nextAuditNode.setLink_id(id);
                    nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);

                    if (null != nextAuditNode) {
                        next_audit_user_id = nextAuditNode.getAudit_user_id();
                    } else {
                        // 没有那一个
                        saveError(request, "errors.token");
                        return list(mapping, form, request, response);
                    }

                    files.setCur_audit_user_id(new Long(next_audit_user_id));// 这个记录的下一个审批人ID
                    files.setFile_status(1);
                    filesAuditNode.setAudit_result(2);
                    result = "同意";

                } else {
                    // 20150320 by zhou ，增加驳回指定节点的功能start
                    if (return_level.contains("submit_user_id")) {
                        // 如果驳回节点是制单人,那么单据状态改为未提交,单据下一个审批人设为流程第一个人
                        // 取流程的第一人
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(1L);
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        files.setFile_status(0);// !important
                        files.setCur_audit_user_id(nextAuditNode.getAudit_user_id());
                        filesAuditNode.setAudit_result(1);
                        result = "驳回";
                    } else {
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(Long.valueOf(return_level));
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        if (null != nextAuditNode) {
                            next_audit_user_id = nextAuditNode.getAudit_user_id();
                            files.setCur_audit_user_id(next_audit_user_id);
                            files.setFile_status(1);// !important
                            filesAuditNode.setAudit_result(1);
                            result = "驳回";
                        } else {
                            // 没有那一个
                            saveDirectlyError(request, "下一个审批节点不存在,或许该节点是流程的第一个节点.");
                            return list(mapping, form, request, response);
                        }
                    }// 20150320 by zhou ，增加驳回指定节点的功能 end
                }
            } else {// 最后位审批人
                filesAuditNode.setAudit_level(max_level);
                if ("2".equals(audit_result)) {// 同意
                    files.setFile_status(2);
                    filesAuditNode.setAudit_result(2);
                    files.setArchive_datetime(filesAuditNode.getAudit_datetime());
                    result = "同意,归档";
                } else {
                    // 20150320 by zhou ，增加驳回指定节点的功能start
                    if (return_level.contains("submit_user_id")) {
                        // 如果驳回节点是制单人,那么单据状态改为未提交,单据下一个审批人设为流程第一个人
                        // 取流程的第一人
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(1L);
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);

                        files.setFile_status(0);// !important
                        files.setCur_audit_user_id(nextAuditNode.getAudit_user_id());
                        filesAuditNode.setAudit_result(1);
                        result = "驳回";
                    } else {
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(Long.valueOf(return_level));
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        Long nextUserId = null;
                        if (null != nextAuditNode) {
                            nextUserId = nextAuditNode.getAudit_user_id();
                            files.setCur_audit_user_id(nextUserId);
                            files.setFile_status(1);// !important
                            filesAuditNode.setAudit_result(1);
                            result = "驳回";
                        } else {
                            // 没有那一个
                            saveDirectlyError(request, "下一个审批节点不存在,或许该节点是流程的第一个节点.");
                            return list(mapping, form, request, response);
                        }
                    }// 20150320 by zhou ，增加驳回指定节点的功能 end
                }
            }
        } else {
            // ####费用申请的审批start
            KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
            kfan.setAudit_level(max_level);
            kfan.setAudit_user_id(ui.getId());
            kfan.setLink_id(id);
            kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(kfan);
            // 不是最后个审批人，文件状态为未审批完（按照原来流程添加的文件的审批节点的level可能为空）
            if (null == kfan && first_level != null) {
                // 取当前审批人的审批节点
                KonkaoaFilesAuditNode _kfan = new KonkaoaFilesAuditNode();
                Long level = null;
                _kfan.setAudit_user_id(ui.getId());
                _kfan.setLink_id(id);
                _kfan = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(_kfan);
                if (null != _kfan) {
                    level = _kfan.getAudit_level();
                } else {// 如果沒有，则表示数据库的数据有问题
                    saveError(request, "errors.token");
                    return list(mapping, form, request, response);
                }

                Long _audit_user_id = null;

                if ("2".equals(audit_result)) {// 同意，继续走审批流程
                    // 取下一位审批人
                    KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
                    entity.setAudit_level(level + 1);
                    entity.setLink_id(id);
                    entity = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(entity);

                    if (null != entity) {
                        _audit_user_id = entity.getAudit_user_id();
                    } else {
                        saveError(request, "errors.token");
                        return list(mapping, form, request, response);
                    }

                    files.setCur_audit_user_id(new Long(_audit_user_id));
                    files.setFile_status(1);
                    filesAuditNode.setAudit_result(2);
                    result = "同意";
                } else {
                    // 20150320 by zhou ，增加驳回指定节点的功能
                    // 驳回至制单人
                    if (return_level.contains("submit_user_id")) {
                        // 取流程的第一人
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(1L);
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        files.setFile_status(0);// important
                        files.setCur_audit_user_id(nextAuditNode.getAudit_user_id());
                        filesAuditNode.setAudit_result(1);
                        result = "驳回";
                    } else {
                        // 获取需要驳回到的节点
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(Long.valueOf(return_level));
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        Long next_audit_user_id = null;
                        if (null != nextAuditNode) {
                            next_audit_user_id = nextAuditNode.getAudit_user_id();
                            files.setCur_audit_user_id(next_audit_user_id);
                            files.setFile_status(1);// 与没有审批流程的驳回处理方式不同的
                            filesAuditNode.setAudit_result(1);
                            result = "驳回";
                        }
                    }
                }
            } else {// 最后位审批人
                if ("2".equals(audit_result)) {// 同意
                    files.setFile_status(2);
                    filesAuditNode.setAudit_result(2);
                    files.setArchive_datetime(filesAuditNode.getAudit_datetime());// 归档
                    result = "同意,归档";
                } else {
                    // 驳回至制单人
                    if (return_level.contains("submit_user_id")) {
                        // 取流程的第一人
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(1L);
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        files.setFile_status(0);// important
                        files.setCur_audit_user_id(nextAuditNode.getAudit_user_id());
                        filesAuditNode.setAudit_result(1);
                        result = "驳回";
                    } else {
                        // 获取需要驳回到的节点
                        KonkaoaFilesAuditNode nextAuditNode = new KonkaoaFilesAuditNode();
                        nextAuditNode.setAudit_level(Long.valueOf(return_level));
                        nextAuditNode.setLink_id(id);
                        nextAuditNode = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(nextAuditNode);
                        Long next_audit_user_id = null;
                        if (null != nextAuditNode) {
                            next_audit_user_id = nextAuditNode.getAudit_user_id();
                            files.setCur_audit_user_id(next_audit_user_id);
                            files.setFile_status(1);// 与没有审批流程的驳回处理方式不同的
                            filesAuditNode.setAudit_result(1);
                            result = "驳回";
                        }
                    }
                }
            }
            // 文件标识
            Long fileNo = Long.valueOf(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
            files.setDoc_id(fileNo);
        }// 费用审批结束

        if ("1".equals(is_agent)) {// 代审
            filesAuditNode.setAgent_audit_user(ui.getUser_name());
            filesAuditNode.setAgent_audit_user_id(ui.getId());
        }

        files.setFilesAuditNode(filesAuditNode);

        files.setContent(content);

        // 如果发送呈中有此人，则将此人设为已查看
        // 需要下发的人员
        KonkaoaFilesRecipient fr = new KonkaoaFilesRecipient();
        fr.setLink_id(id);
        fr.setReceive_id(ui.getId());
        fr.setView_date_time(filesAuditNode.getAudit_datetime());
        files.setFilesRecipient(fr);

        // 执行审批
        super.getFacade().getKonkaoaFilesService().auditFiles(files);
        saveMessage(request, "entity.updated");

        if (StringUtils.isNotEmpty(msg)) {
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='SelfEventCenter.do?mod_id=" + mod_id + "'}");
        } else {
            return new ActionForward("/chengduoa/SelfEventCenter.do?mod_id=" + mod_id, true);
        }
        return null;
    }
}
