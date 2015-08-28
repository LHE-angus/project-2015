package com.ebiz.mmt.web.struts.inter.form;

import java.util.Date;
import java.util.List;

import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;

public class KonkaOrderAuditForm extends BaseInterForm {

	/**
	 * 审核记录基类
	 * @author Wang,KunLin
	 */
	private static final long serialVersionUID = -1L;
	private Long id;

	private Long link_id;

	private Integer audit_type;
	
	private String audit_type_name;

	private Integer audit_level;

	private Long audit_user_id;

	private String audit_user;

	private String audit_comment;

	private Date audit_datetime;

	private Long agent_audit_user_id;

	private String agent_audit_user;

	private Integer audit_result;
	
	private String audit_result_name;

	private Long audit_dept_id;

	private String audit_dept_name;

	private Long agent_audit_dept_id;

	private String agent_audit_dept_name;

	private Long next_node_id;

	private Long cur_node_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLink_id() {
		return link_id;
	}

	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	public Integer getAudit_type() {
		return audit_type;
	}

	public void setAudit_type(Integer audit_type) {
		this.audit_type = audit_type;
	}

	public Integer getAudit_level() {
		return audit_level;
	}

	public void setAudit_level(Integer audit_level) {
		this.audit_level = audit_level;
	}

	public Long getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(Long audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public String getAudit_user() {
		return audit_user;
	}

	public void setAudit_user(String audit_user) {
		this.audit_user = audit_user;
	}

	public String getAudit_comment() {
		return audit_comment;
	}

	public void setAudit_comment(String audit_comment) {
		this.audit_comment = audit_comment;
	}

	public Date getAudit_datetime() {
		return audit_datetime;
	}

	public void setAudit_datetime(Date audit_datetime) {
		this.audit_datetime = audit_datetime;
	}

	public Long getAgent_audit_user_id() {
		return agent_audit_user_id;
	}

	public void setAgent_audit_user_id(Long agent_audit_user_id) {
		this.agent_audit_user_id = agent_audit_user_id;
	}

	public String getAgent_audit_user() {
		return agent_audit_user;
	}

	public void setAgent_audit_user(String agent_audit_user) {
		this.agent_audit_user = agent_audit_user;
	}

	public Integer getAudit_result() {
		return audit_result;
	}

	public void setAudit_result(Integer audit_result) {
		this.audit_result = audit_result;
	}

	public Long getAudit_dept_id() {
		return audit_dept_id;
	}

	public void setAudit_dept_id(Long audit_dept_id) {
		this.audit_dept_id = audit_dept_id;
	}

	public String getAudit_dept_name() {
		return audit_dept_name;
	}

	public void setAudit_dept_name(String audit_dept_name) {
		this.audit_dept_name = audit_dept_name;
	}

	public Long getAgent_audit_dept_id() {
		return agent_audit_dept_id;
	}

	public void setAgent_audit_dept_id(Long agent_audit_dept_id) {
		this.agent_audit_dept_id = agent_audit_dept_id;
	}

	public String getAgent_audit_dept_name() {
		return agent_audit_dept_name;
	}

	public void setAgent_audit_dept_name(String agent_audit_dept_name) {
		this.agent_audit_dept_name = agent_audit_dept_name;
	}

	public Long getNext_node_id() {
		return next_node_id;
	}

	public void setNext_node_id(Long next_node_id) {
		this.next_node_id = next_node_id;
	}

	public Long getCur_node_id() {
		return cur_node_id;
	}

	public void setCur_node_id(Long cur_node_id) {
		this.cur_node_id = cur_node_id;
	}

	public String getAudit_type_name() {
		return audit_type_name;
	}

	public void setAudit_type_name(String audit_type_name) {
		this.audit_type_name = audit_type_name;
	}

	public String getAudit_result_name() {
		return audit_result_name;
	}

	public void setAudit_result_name(String audit_result_name) {
		this.audit_result_name = audit_result_name;
	}
	

	
	
}
