package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public class YwtTaskReceive extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long user_id;

	private Long role_id;

	private Long task_id;

	private String task_name;

	private Date add_date;

	private Long is_receive;

	private Long state;

	private Long audit_state;

	private Long audit_user_id;

	private String audit_name;

	private String audit_info;

	private Long dept_id;

	private Date reve_finsh_date;

	private Date audit_date;

	private String receive_info;

	private String complete_info;

	/**
	 * @val 接收意见
	 */
	public String getReceive_info() {
		return receive_info;
	}

	/**
	 * @val 接收意见
	 */
	public void setReceive_info(String receive_info) {
		this.receive_info = receive_info;
	}

	/**
	 * @val 完成意见
	 */
	public String getComplete_info() {
		return complete_info;
	}

	/**
	 * @val 完成意见
	 */
	public void setComplete_info(String complete_info) {
		this.complete_info = complete_info;
	}

	public YwtTaskReceive() {

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

	/**
	 * @val 接收人ID
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @val 接收人ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @val 接收人角色ID
	 */
	public Long getRole_id() {
		return role_id;
	}

	/**
	 * @val 接收人角色ID
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	/**
	 * @val 任务ID
	 */
	public Long getTask_id() {
		return task_id;
	}

	/**
	 * @val 任务ID
	 */
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	/**
	 * @val 任务名称
	 */
	public String getTask_name() {
		return task_name;
	}

	/**
	 * @val 任务名称
	 */
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	/**
	 * @val 接收时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 接收时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 接收状态 0：接收 1：拒绝 2：未接受
	 */
	public Long getIs_receive() {
		return is_receive;
	}

	/**
	 * @val 接收状态 0：接收 1：拒绝 2：未接受
	 */
	public void setIs_receive(Long is_receive) {
		this.is_receive = is_receive;
	}

	/**
	 * @val 完成状态 0：完成1：未完成
	 */
	public Long getState() {
		return state;
	}

	/**
	 * @val 完成状态 0：完成1：未完成
	 */
	public void setState(Long state) {
		this.state = state;
	}

	/**
	 * @val 审核状态 0：已审核 1：未审核
	 */
	public Long getAudit_state() {
		return audit_state;
	}

	/**
	 * @val 审核状态
	 */
	public void setAudit_state(Long audit_state) {
		this.audit_state = audit_state;
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
	 * @val 审核人姓名
	 */
	public String getAudit_name() {
		return audit_name;
	}

	/**
	 * @val 审核人姓名
	 */
	public void setAudit_name(String audit_name) {
		this.audit_name = audit_name;
	}

	/**
	 * @val 审核意见
	 */
	public String getAudit_info() {
		return audit_info;
	}

	/**
	 * @val 审核意见
	 */
	public void setAudit_info(String audit_info) {
		this.audit_info = audit_info;
	}

	/**
	 * @val 接收部门
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 接收部门
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 完成时间
	 */
	public Date getReve_finsh_date() {
		return reve_finsh_date;
	}

	/**
	 * @val 完成时间
	 */
	public void setReve_finsh_date(Date reve_finsh_date) {
		this.reve_finsh_date = reve_finsh_date;
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

}