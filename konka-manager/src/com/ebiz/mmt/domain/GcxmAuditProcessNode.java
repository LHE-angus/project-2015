package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmAuditProcessNode extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long process_id;
	
	private String audit_name;
	
	private Long audit_user_id;
	
	private String audit_role_name;
	
	private Long audit_role_id;
	
	private String memo;
	
	private String pre_audit_name;
	
	private Long pre_audit_id;
	
	private String pre_audit_role_name;
	
	private Long pre_audit_role_id;
	
	private String next_audit_name;
	
	private Long next_audit_user_id;
	
	private String next_audit_role_name;
	
	private Long next_audit_role_id;
	
	private String node_name;
	
	private String node_logo;
	
	public GcxmAuditProcessNode() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProcess_id() {
		return process_id;
	}
	
	public void setProcess_id(Long process_id) {
		this.process_id = process_id;
	}
	
	/**
	 * @val 审核人
	 */
	public String getAudit_name() {
		return audit_name;
	}
	
	/**
	 * @val 审核人
	 */
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
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
	 * @val 审核角色
	 */
	public String getAudit_role_name() {
		return audit_role_name;
	}
	
	/**
	 * @val 审核角色
	 */
	public void setAudit_role_name(String audit_role_name) {
		this.audit_role_name = audit_role_name;
	}
	
	/**
	 * @val 审核角色ID
	 */
	public Long getAudit_role_id() {
		return audit_role_id;
	}
	
	/**
	 * @val 审核角色ID
	 */
	public void setAudit_role_id(Long audit_role_id) {
		this.audit_role_id = audit_role_id;
	}
	
	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @val 上审核人
	 */
	public String getPre_audit_name() {
		return pre_audit_name;
	}
	
	/**
	 * @val 上审核人
	 */
	public void setPre_audit_name(String pre_audit_name) {
		this.pre_audit_name = pre_audit_name;
	}
	
	/**
	 * @val 上审核人ID
	 */
	public Long getPre_audit_id() {
		return pre_audit_id;
	}
	
	/**
	 * @val 上审核人ID
	 */
	public void setPre_audit_id(Long pre_audit_id) {
		this.pre_audit_id = pre_audit_id;
	}
	
	/**
	 * @val 上审核角色
	 */
	public String getPre_audit_role_name() {
		return pre_audit_role_name;
	}
	
	/**
	 * @val 上审核角色
	 */
	public void setPre_audit_role_name(String pre_audit_role_name) {
		this.pre_audit_role_name = pre_audit_role_name;
	}
	
	/**
	 * @val 上审核角色ID
	 */
	public Long getPre_audit_role_id() {
		return pre_audit_role_id;
	}
	
	/**
	 * @val 上审核角色ID
	 */
	public void setPre_audit_role_id(Long pre_audit_role_id) {
		this.pre_audit_role_id = pre_audit_role_id;
	}
	
	/**
	 * @val 下审核人
	 */
	public String getNext_audit_name() {
		return next_audit_name;
	}
	
	/**
	 * @val 下审核人
	 */
	public void setNext_audit_name(String next_audit_name) {
		this.next_audit_name = next_audit_name;
	}
	
	/**
	 * @val 下审核人ID
	 */
	public Long getNext_audit_user_id() {
		return next_audit_user_id;
	}
	
	/**
	 * @val 下审核人ID
	 */
	public void setNext_audit_user_id(Long next_audit_user_id) {
		this.next_audit_user_id = next_audit_user_id;
	}
	
	/**
	 * @val 下审核角色
	 */
	public String getNext_audit_role_name() {
		return next_audit_role_name;
	}
	
	/**
	 * @val 下审核角色
	 */
	public void setNext_audit_role_name(String next_audit_role_name) {
		this.next_audit_role_name = next_audit_role_name;
	}
	
	/**
	 * @val 下审核角色ID
	 */
	public Long getNext_audit_role_id() {
		return next_audit_role_id;
	}
	
	/**
	 * @val 下审核角色ID
	 */
	public void setNext_audit_role_id(Long next_audit_role_id) {
		this.next_audit_role_id = next_audit_role_id;
	}
	
	/**
	 * @val 节点名称
	 */
	public String getNode_name() {
		return node_name;
	}
	
	/**
	 * @val 节点名称
	 */
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
	
	/**
	 * @val 节点图标
	 */
	public String getNode_logo() {
		return node_logo;
	}
	
	/**
	 * @val 节点图标
	 */
	public void setNode_logo(String node_logo) {
		this.node_logo = node_logo;
	}
	
}