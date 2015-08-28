package com.ebiz.mmt.web.struts.manager.oa;

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
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaEventInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditAgentUser;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesContent;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * !!!!!!!!!!
 * 
 * 没有流程的.只支持单个节点的审核的.
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

        List<KonkaoaFiles> cList = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesPaginatedListForAudit(entity);
        String contextPath = getCtxPath(request) == null ? "" : getCtxPath(request);
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

            // if ("1".equals(t.getMap().get("agent_audit").toString()) ||
            // "0".equals(t.getMap().get("agent_audit").toString())) {
            // _t.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=951000&is_agent=" +
            // ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
            // + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() +
            // "'>审批</a>");
            // } else {
            // _t.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=951000&is_agent=" +
            // ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
            // + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() +
            // "'>会签</a>");
            // }
            if(null!=t.getIs_node() && t.getIs_node()==0){
            _t.setEventDo("<a href='SelfEventCenter.do?method=edit&mod_id=951000&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                    + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>审批</a>");
            }else{
            	 _t.setEventDo("<a href='" + contextPath + "/manager/chengduoa/SelfEventCenter.do?method=edit&mod_id=951000&is_agent=" + ("1".equals(t.getMap().get("agent_audit")) ? "1" : "0") + "&agent_user_id="
                         + t.getCur_audit_user_id() + "&file_type=" + t.getFile_type() + "&id=" + t.getId() + "'>审批</a>");
            }
            list.add(_t);
        }

        // KonkaoaFiles file5 = new KonkaoaFiles();
        // file5.setFile_status(0);
        // file5.setIs_del(0);
        // file5.setSubmit_user_id(ui.getId());
        // List<KonkaoaFiles> file5List =
        // super.getFacade().getKonkaoaFilesService().getKonkaoaFilesList(file5);
        //
        // for (KonkaoaFiles t : file5List) {
        // i++;
        // KonkaoaEventInfo _t = new KonkaoaEventInfo();
        // _t.setId(t.getId());
        // // _t.setMod_id(20030);
        // _t.setEnterDate(t.getSubmit_datetime());
        // _t.setEventiltle(t.getFile_title());
        // _t.setSequence(i);
        // _t.setFromPerson(t.getSubmit_user());
        // if (t.getFile_type() == 0) {
        // _t.setEventType("文件提交");
        // _t.setEventDo("<a href='FilesSubmit.do?method=edit&mod_id=991000&id=" + t.getId() +
        // "'>审批</a>");
        // } else if (t.getFile_type() == 1) {
        // _t.setEventType("费用申请");
        // _t.setEventDo("<a href='ExpenseClaims.do?method=edit&mod_id=991000&file_id=" + t.getId()
        // + "'>审批</a>");
        // }
        //
        // list.add(_t);
        // }
        request.setAttribute("entityList", list);
        return mapping.findForward("list");
    }

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
            appProperty.setC_index(kfp.getId());

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

        // 审批人查询
        KonkaoaFilesAuditNode _fan_ = new KonkaoaFilesAuditNode();
        _fan_.setLink_id(files.getId());
        _fan_.setAudit_type(0);
        request.setAttribute("_filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(_fan_));

        // 审批记录显示
        KonkaoaFilesAuditNode fan = new KonkaoaFilesAuditNode();
        fan.setLink_id(files.getId());
        fan.setAudit_type(0);
        fan.getMap().put("is_audit", "is_audit");// 只查出经过审批后的审批记录
        fan.getMap().put("order_by_id_desc", "order_by_id_desc");
        request.setAttribute("filesAuditNodeList", getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(fan));

        dynaBean.set("now", new Date());
        return mapping.findForward("input");
    }

    /**
     * 
     * 没有审批流程的审批.
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

        DynaBean dynaBean = (DynaBean) form;
        String _id = (String) dynaBean.get("id");
        String column_6 = (String) dynaBean.get("column_6");
        String audit_result = (String) dynaBean.get("audit_result");// 审批结果：1驳回2同意
        String content = (String) dynaBean.get("content");//
        String audit_comment = (String) dynaBean.get("audit_comment");// 评语
        String is_agent = (String) dynaBean.get("is_agent");
        String agent_user_id = (String) dynaBean.get("agent_user_id");
        String mod_id = (String) dynaBean.get("mod_id");
        // String audit_note_id = (String) dynaBean.get("audit_note_id");
        String[] c_index = request.getParameterValues("c_index");

        String msg = "";

        @SuppressWarnings("unused")
        String result = "";

        if (!GenericValidator.isLong(_id)) {
            saveError(request, "errors.long", _id);
            return mapping.findForward("list");
        }
        // 单据主键ID
        Long id = new Long(_id);

        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

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
				saveError(request, "entity.user.invalid", _id);
                return new ActionForward("/oa/SelfEventCenter.do?mod_id=" + mod_id, true);
			}
		}
        // 审批
        Long audti_user_id = ui.getId();
        if ("1".equals(is_agent)) {// 代审
            audti_user_id = new Long(agent_user_id);
        }

        // 此行有delete操作
        super.getFacade().getKonkaoaFilesService().modifyKonkaoaFilesProperty(super.getFilesProperty(form, request, id));


        // 没有流程节点的,判断什么. TMD
        // KonkaoaFilesAuditNode fau = new KonkaoaFilesAuditNode();
        // fau.setLink_id(id);
        // fau.setAudit_user_id(audti_user_id);
        // fau.setAudit_type(0);
        // // 起码有一个审批节点
        // Long fau_count =
        // super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau);
        // if (fau_count < 1) {
        // saveError(request, "entity.missing", _id);
        // return new ActionForward("/oa/SelfEventCenter.do", true);
        // }

        // 填写审批意见等信息
        KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
        filesAuditNode.setAudit_user_id(audti_user_id);
        filesAuditNode.setLink_id(id);
        filesAuditNode.setAudit_result(new Integer(audit_result));
        filesAuditNode.setAudit_comment(audit_comment);
        filesAuditNode.setAudit_datetime(new Date());

        KonkaoaFiles files = new KonkaoaFiles();
        files.setId(id);
        // 有流程的.重要. 某些情况下,或之前的开发的版本页面传参丢失
        files.setIs_node(0);
        files.setIs_forward(new Long((String) dynaBean.get("is_xiafa")));
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
        // 这个审批需要多少个流程节点,此处一般都是1个的,跟有流程的审批不一样
        Long max_level = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(fau1);

        // 被转发的审批的人,会加长流程
        String audit_user_id = request.getParameter("audit_user_id");
        String audit_user_name = request.getParameter("audit_user_name");

        List<KonkaoaFilesAuditNode> filesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
        if (StringUtils.isNotBlank(audit_user_id)&& GenericValidator.isLong(audit_user_id)) {
            Long user_id = new Long(audit_user_id);
            files.setCur_audit_user_id(user_id);
            // 审批方法里面有填link_id
            KonkaoaFilesAuditNode _filesAuditNode = new KonkaoaFilesAuditNode();
            _filesAuditNode.setAudit_level(max_level + 1);// 延长流程处理
            _filesAuditNode.setAudit_user_id(user_id);
            _filesAuditNode.setAudit_user(audit_user_name);
            filesAuditNodeList.add(_filesAuditNode);
        }
        files.setFilesAuditNodeList(filesAuditNodeList);

        // 没有流程审批与有流程的审批,审批节点处理不一样.
        // audit_user_id 是指定的下一个审批人,所以这个值如果不为空,那么就直接延长流程的处理
        if (!"".equals(audit_user_id)) {
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
            } else {
                files.setFile_status(0);// 无流程的比较特殊,是驳回直接变成制单
                filesAuditNode.setAudit_result(1);
                result = "驳回";
            }
        } else {
            if ("2".equals(audit_result)) {// 同意
                files.setFile_status(2);
                filesAuditNode.setAudit_result(2);
                files.setArchive_datetime(filesAuditNode.getAudit_datetime());
                result = "同意,归档";
            } else if ("0".equals(audit_result)) {// 直接转发
                files.setCur_audit_user_id(new Long(audit_user_id));
                files.setFile_status(1);
                filesAuditNode.setAudit_result(0);
                result = "直接转发";
            } else {// 驳回
                files.setFile_status(0);// 无流程的比较特殊,是驳回直接变成制单
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
        files.setContent(content);

        // 如果发送呈中有此人，则将此人设为已查看
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
            return new ActionForward("/oa/SelfEventCenter.do?mod_id=" + mod_id, true);
        }
        return null;
    }
}
