package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.StringUtils;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */

/**
 * 注意:20150519发现,konkaoa_files表里面,is_del =0, submit_dept_id is null是流程主键ID
 * 
 * 这些数据不能删除
 * 
 * 可悲的设计
 */

public class KonkaoaFiles extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 提交部门
     */
    private String submit_dept;

    /**
     * 提交部门ID
     */
    private Long submit_dept_id;

    /**
     * 提交人
     */
    private String submit_user;

    /**
     * 提交人ID
     */
    private Long submit_user_id;

    /**
     * 是否需要盖章 0：否<br />
     * 1：是
     */
    private Integer is_stamp;

    /**
     * 0：否<br />
     * 1：是
     */
    private Long is_forward;

    /**
     * 文件标题
     */
    private String file_title;

    /**
     * 文件编号
     */
    private String file_no;

    /**
     * 申请人
     */
    private String apply_people;

    /**
     * 提交时间
     */
    private Date submit_datetime;

    /**
     * 文件密级 0：普通级；1：保密级
     */
    private Integer file_dense;

    /**
     * 时限，由文件提交人填写，文件审批流程结束后，修改为实际审批所用时间
     */
    private Long cur_audit_user_id;

    private Integer time_limit;

    /**
     * 文档标识
     */
    private Long doc_id;

    /**
     * 0：还没审批（尚未开始走流程）<br />
     * 1：正在审批中<br />
     * 2：已审批<br />
     */
    private Integer file_status;

    private Date archive_datetime;

    /**
     * 排序值
     */
    private Long order_value;

    /**
     * 是否删除
     */
    private Integer is_del;

    /**
     * 审批类别：0审批，1会签
     * 
     * 渠道系统不做会签,所以konkaoafiles.audit_type always be 0
     * 
     * 
     * KonkaoaFilesAuditNode 也有audit_type 目前不知道用来作何用?
     */
    private Integer audit_type;


    // EPP,顺丰的审批.
    // 950100 950100 900100 EPP退换货审核流程 EPP退换货审核流程 ../chengduoa/FilesSubmit.do?p_audit_node_id=730467
    // 159 900100 0 0 0 0 0 2 900100 0 0 1
    // 950200 950200 900100 顺丰退换货审核流程 顺丰退换货审核流程 ../chengduoa/FilesSubmit.do?p_audit_node_id=730761
    // 159 900100 0 0 0 0 0 2 900100 0 0 1
    //
    private Long audit_node_id;

    private String content;

    private Integer is_node;

    /**
     * 文件来源：0来自电脑，1来自手机
     */
    private Integer file_from;

    private List<KonkaPeAttachments> attachmentList;

    private List<KonkaoaFilesRecipient> filesRecipientList;

    private List<KonkaoaFilesProperty> filesPropertyList;

    private List<KonkaoaFilesAuditNode> filesAuditNodeList;

    private KonkaoaFilesAuditNode filesAuditNode;

    private KonkaoaFilesRecipient filesRecipient;

    private Date oper_datetime;

    private List<KonkaoaCategory> categoryList;

    private KonkaExpenseClaims expenseClaims;

    public KonkaExpenseClaims getExpenseClaims() {
        return expenseClaims;
    }

    public void setExpenseClaims(KonkaExpenseClaims expenseClaims) {
        this.expenseClaims = expenseClaims;
    }

    /**
     * 申请负责人电话
     */
    private String apply_user_tel;

    /**
     * 申请负责人
     */
    private String apply_user_name;

    /**
     * 文件类型：0为提交文件，1为费用申请,2为请假
     */
    private Integer file_type;

    /**
     * 驳回重新提交连接之前的ID
     */
    private Long link_id;

    public String getApply_user_tel() {
        return apply_user_tel;
    }

    public void setApply_user_tel(String apply_user_tel) {
        this.apply_user_tel = apply_user_tel;
    }

    public String getApply_user_name() {
        return apply_user_name;
    }

    public void setApply_user_name(String apply_user_name) {
        this.apply_user_name = apply_user_name;
    }

    public KonkaoaFiles() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSubmit_dept(String submit_dept) {
        this.submit_dept = submit_dept;
    }

    public String getSubmit_dept() {
        return submit_dept;
    }

    public void setSubmit_dept_id(Long submit_dept_id) {
        this.submit_dept_id = submit_dept_id;
    }

    public Long getSubmit_dept_id() {
        return submit_dept_id;
    }

    public void setSubmit_user(String submit_user) {
        this.submit_user = submit_user;
    }

    public String getSubmit_user() {
        return submit_user;
    }

    public void setSubmit_user_id(Long submit_user_id) {
        this.submit_user_id = submit_user_id;
    }

    public Long getSubmit_user_id() {
        return submit_user_id;
    }

    public void setIs_forward(Long is_forward) {
        this.is_forward = is_forward;
    }

    public Long getIs_forward() {
        return is_forward;
    }

    public void setFile_title(String file_title) {
        this.file_title = file_title;
    }

    public String getFile_title() {
        return file_title;
    }

    public void setApply_people(String apply_people) {
        this.apply_people = apply_people;
    }

    public String getApply_people() {
        return apply_people;
    }

    public void setSubmit_datetime(Date submit_datetime) {
        this.submit_datetime = submit_datetime;
    }

    public Date getSubmit_datetime() {
        return submit_datetime;
    }

    public Long getCur_audit_user_id() {
        return cur_audit_user_id;
    }

    public void setCur_audit_user_id(Long curAuditUserId) {
        cur_audit_user_id = curAuditUserId;
    }

    /**
     * 0：还没审批（尚未开始走流程）<br />
     * 1：正在审批中<br />
     * 2：已审批<br />
     */
    public void setFile_status(Integer file_status) {
        this.file_status = file_status;
    }

    /**
     * 0：还没审批（尚未开始走流程）<br />
     * 1：正在审批中<br />
     * 2：已审批<br />
     */
    public Integer getFile_status() {
        return file_status;
    }

    public void setOrder_value(Long order_value) {
        this.order_value = order_value;
    }

    public Long getOrder_value() {
        return order_value;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public String getContent() {
        return StringUtils.replace(content, "&nbsp;", " ");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<KonkaoaFilesRecipient> getFilesRecipientList() {
        return filesRecipientList;
    }

    public void setFilesRecipientList(List<KonkaoaFilesRecipient> filesRecipientList) {
        this.filesRecipientList = filesRecipientList;
    }

    public List<KonkaoaFilesProperty> getFilesPropertyList() {
        return filesPropertyList;
    }

    public void setFilesPropertyList(List<KonkaoaFilesProperty> filesPropertyList) {
        this.filesPropertyList = filesPropertyList;
    }

    public List<KonkaoaFilesAuditNode> getFilesAuditNodeList() {
        return filesAuditNodeList;
    }

    public void setFilesAuditNodeList(List<KonkaoaFilesAuditNode> filesAuditNodeList) {
        this.filesAuditNodeList = filesAuditNodeList;
    }

    public List<KonkaPeAttachments> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<KonkaPeAttachments> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public KonkaoaFilesAuditNode getFilesAuditNode() {
        return filesAuditNode;
    }

    public void setFilesAuditNode(KonkaoaFilesAuditNode filesAuditNode) {
        this.filesAuditNode = filesAuditNode;
    }

    public KonkaoaFilesRecipient getFilesRecipient() {
        return filesRecipient;
    }

    public void setFilesRecipient(KonkaoaFilesRecipient filesRecipient) {
        this.filesRecipient = filesRecipient;
    }

    public Date getOper_datetime() {
        return oper_datetime;
    }

    public void setOper_datetime(Date operDatetime) {
        oper_datetime = operDatetime;
    }

    public Integer getIs_stamp() {
        return is_stamp;
    }

    public void setIs_stamp(Integer isStamp) {
        is_stamp = isStamp;
    }

    public Date getArchive_datetime() {
        return archive_datetime;
    }

    public void setArchive_datetime(Date archiveDatetime) {
        archive_datetime = archiveDatetime;
    }

    public List<KonkaoaCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<KonkaoaCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String fileNo) {
        file_no = fileNo;
    }

    public Integer getFile_dense() {
        return file_dense;
    }

    public void setFile_dense(Integer fileDense) {
        file_dense = fileDense;
    }

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long docId) {
        doc_id = docId;
    }

    public Integer getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Integer timeLimit) {
        time_limit = timeLimit;
    }

    public Integer getAudit_type() {
        return audit_type;
    }

    public void setAudit_type(Integer auditType) {
        audit_type = auditType;
    }

    public Integer getFile_type() {
        return file_type;
    }

    public void setFile_type(Integer file_type) {
        this.file_type = file_type;
    }

    public Long getAudit_node_id() {
        return audit_node_id;
    }

    public void setAudit_node_id(Long audit_node_id) {
        this.audit_node_id = audit_node_id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Integer getFile_from() {
        return file_from;
    }

    public void setFile_from(Integer file_from) {
        this.file_from = file_from;
    }

    /**
     * @val 是否选则流程自定义 0-否；1-是
     */
    public void setIs_node(Integer is_node) {
        this.is_node = is_node;
    }

    /**
     * @val 是否选则流程自定义 0-否；1-是
     */
    public Integer getIs_node() {
        return is_node;
    }

    /**
     * 驳回重新提交连接之前的ID
     */
    public Long getLink_id() {
        return link_id;
    }

    /**
     * 驳回重新提交连接之前的ID
     */
    public void setLink_id(Long link_id) {
        this.link_id = link_id;
    }

}
