package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-27 15:28:23
 */
public class KonkaXxZmdAuditUserHis extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long zmd_user_id;
	
	private Long audit_status;
	
	private String audit_option;
	
	private Date audit_date;
	
	private Long audit_user_id;
	
	private String audit_user_name;
	
	private Long audit_type;
	
	private String audit_desc;
	
	private Long audit_node_id;
	
	private String audit_node_name;
	
	private Long audit_next_node_id;
	
	private String audit_next_node_name;
	
	private Integer is_end;
	
	private String audit_data_desc;
	
	private String audit_status_desc;
	
	public KonkaXxZmdAuditUserHis() {

	}

	/**
	 * @val 审核记录ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 审核记录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 客户ID
	 */
	public Long getZmd_user_id() {
		return zmd_user_id;
	}
	
	/**
	 * @val 客户ID
	 */
	public void setZmd_user_id(Long zmd_user_id) {
		this.zmd_user_id = zmd_user_id;
	}
	
	/**
	 * @val 审核状态
	 */
	public Long getAudit_status() {
		return audit_status;
	}
	
	/**
	 * @val 审核状态
	 */
	public void setAudit_status(Long audit_status) {
		this.audit_status = audit_status;
	}
	
	/**
	 * @val 审核意见
	 */
	public String getAudit_option() {
		return audit_option;
	}
	
	/**
	 * @val 审核意见
	 */
	public void setAudit_option(String audit_option) {
		this.audit_option = audit_option;
	}
	
	/**
	 * @val 审核时间
	 */
	public Date getAudit_date() {
		return audit_date;
	}
	
	/**
	 * @val 审核时间
	 */
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	
	/**
	 * @val 审核人ID
	 */
	public Long getAudit_user_id() {
		return audit_user_id;
	}
	
	/**
	 * @val 审核人ID
	 */
	public void setAudit_user_id(Long audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	
	/**
	 * @val 审核人
	 */
	public String getAudit_user_name() {
		return audit_user_name;
	}
	
	/**
	 * @val 审核人
	 */
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	
	/**
	 * @val 审核类型
	 */
	public Long getAudit_type() {
		return audit_type;
	}
	
	/**
	 * @val 审核类型
	 */
	public void setAudit_type(Long audit_type) {
		this.audit_type = audit_type;
	}
	
	/**
	 * @val 审核描述
	 */
	public String getAudit_desc() {
		return audit_desc;
	}
	
	/**
	 * @val 审核描述
	 */
	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}
	
	/**
	 * @val 当前审核节点ID
	 */
	public Long getAudit_node_id() {
		return audit_node_id;
	}
	
	/**
	 * @val 当前审核节点ID
	 */
	public void setAudit_node_id(Long audit_node_id) {
		this.audit_node_id = audit_node_id;
	}
	
	/**
	 * @val 当前审核节点名
	 */
	public String getAudit_node_name() {
		return audit_node_name;
	}
	
	/**
	 * @val 当前审核节点名
	 */
	public void setAudit_node_name(String audit_node_name) {
		this.audit_node_name = audit_node_name;
	}
	
	/**
	 * @val 下个审核节点
	 */
	public Long getAudit_next_node_id() {
		return audit_next_node_id;
	}
	
	/**
	 * @val 下个审核节点
	 */
	public void setAudit_next_node_id(Long audit_next_node_id) {
		this.audit_next_node_id = audit_next_node_id;
	}
	
	/**
	 * @val 下个审核节点名
	 */
	public String getAudit_next_node_name() {
		return audit_next_node_name;
	}
	
	/**
	 * @val 下个审核节点名
	 */
	public void setAudit_next_node_name(String audit_next_node_name) {
		this.audit_next_node_name = audit_next_node_name;
	}
	
	/**
	 * @val 是否审核完成：0——否，1——是
	 */
	public Integer getIs_end() {
		return is_end;
	}
	
	/**
	 * @val 是否审核完成：0——否，1——是
	 */
	public void setIs_end(Integer is_end) {
		this.is_end = is_end;
	}
	
	/**
	 * @val 审核修改描述
	 */
	public String getAudit_data_desc() {
		return audit_data_desc;
	}
	
	/**
	 * @val 审核修改描述
	 */
	public void setAudit_data_desc(String audit_data_desc) {
		this.audit_data_desc = audit_data_desc;
	}
	
	/**
	 * @val 审核状态描述
	 */
	public String getAudit_status_desc() {
		return audit_status_desc;
	}
	
	/**
	 * @val 审核状态描述
	 */
	public void setAudit_status_desc(String audit_status_desc) {
		this.audit_status_desc = audit_status_desc;
	}
	
}