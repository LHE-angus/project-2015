package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public class KonkaoaFilesAuditNode extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * ID
     */
    private Long id;

    private String audit_node_name;

    /**
     * LINK_ID
     */
    private Long link_id;

    /**
     * 1，2，3... 依次类推，值越大越靠后
     */
    private Long audit_level;

    /**
     * 审批人
     */
    private String audit_user;

    /**
     * 审批人ID
     */
    private Long audit_user_id;

    /**
     * 代审批人
     */
    private String agent_audit_user;

    /**
     * 代审批人ID
     */
    private Long agent_audit_user_id;

    /**
     * 0：未审批 1：不同意 2：同意
     */
    private Integer audit_result;

    /**
     * 审批评语
     */
    private String audit_comment;

    private Date audit_datetime;

    /**
     * 0：审批 1：会签 2：指定流程
     * 
     * 渠道系统不做会签功能
     */
    private Integer audit_type;


    /**
     * 0文件,1费用,2请假,3建户申请, 3：建户申请审批 4：（客户，门店，网点）申请变更记录节点 OA原数据,2请假目前用于业务通
     */
    private Integer node_type;

    private Long dept_id;


    // 流程节点,由人员ID,和人员姓名组成
    private List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList;

    public KonkaoaFilesAuditNode() {

    }

    public String getAudit_node_name() {
        return audit_node_name;
    }

    public void setAudit_node_name(String audit_node_name) {
        this.audit_node_name = audit_node_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLink_id(Long link_id) {
        this.link_id = link_id;
    }

    public Long getLink_id() {
        return link_id;
    }

    public void setAudit_level(Long audit_level) {
        this.audit_level = audit_level;
    }

    public Long getAudit_level() {
        return audit_level;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user_id(Long audit_user_id) {
        this.audit_user_id = audit_user_id;
    }

    public Long getAudit_user_id() {
        return audit_user_id;
    }

    public String getAgent_audit_user() {
        return agent_audit_user;
    }

    public void setAgent_audit_user(String agentAuditUser) {
        agent_audit_user = agentAuditUser;
    }

    public Long getAgent_audit_user_id() {
        return agent_audit_user_id;
    }

    public void setAgent_audit_user_id(Long agentAuditUserId) {
        agent_audit_user_id = agentAuditUserId;
    }

    /**
     * 0：未审批 1：不同意 2：同意
     */
    public void setAudit_result(Integer audit_result) {
        this.audit_result = audit_result;
    }

    /**
     * 0：未审批 1：不同意 2：同意
     */
    public Integer getAudit_result() {
        return audit_result;
    }

    public void setAudit_comment(String audit_comment) {
        this.audit_comment = audit_comment;
    }

    public String getAudit_comment() {
        return audit_comment;
    }

    public Date getAudit_datetime() {
        return audit_datetime;
    }

    public void setAudit_datetime(Date auditDatetime) {
        audit_datetime = auditDatetime;
    }

    /**
     * 0：审批 1：会签 2：指定流程
     * 
     * 渠道系统不做会签功能
     */
    public Integer getAudit_type() {
        return audit_type;
    }

    /**
     * 0：审批 1：会签 2：指定流程
     * 
     * 渠道系统不做会签功能
     */
    public void setAudit_type(Integer auditType) {
        audit_type = auditType;
    }

    public List<KonkaoaFilesAuditNode> getKonkaoaFilesAuditNodeList() {
        return konkaoaFilesAuditNodeList;
    }

    public void setKonkaoaFilesAuditNodeList(List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList) {
        this.konkaoaFilesAuditNodeList = konkaoaFilesAuditNodeList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @val 流程类型
     */
    public void setNode_type(Integer node_type) {
        this.node_type = node_type;
    }

    /**
     * @val 流程类型
     */
    public Integer getNode_type() {
        return node_type;
    }

    /**
     * @val 分公司ID
     */
    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    /**
     * @val 分公司ID
     */
    public Long getDept_id() {
        return dept_id;
    }

}
