package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-27 15:20:48
 */
public class KonkaXxAuditNote extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long audit_type;
	
	private String audit_type_desc;
	
	private Long audit_node_id;
	
	private String audit_node_name;
	
	private Long audit_next_node_id;
	
	private String audit_next_node_name;
	
	private Integer is_audit_end;
	
	private Integer audit_seq;
	
	public KonkaXxAuditNote() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAudit_type() {
		return audit_type;
	}
	
	public void setAudit_type(Long audit_type) {
		this.audit_type = audit_type;
	}
	
	public String getAudit_type_desc() {
		return audit_type_desc;
	}
	
	public void setAudit_type_desc(String audit_type_desc) {
		this.audit_type_desc = audit_type_desc;
	}
	
	public Long getAudit_node_id() {
		return audit_node_id;
	}
	
	public void setAudit_node_id(Long audit_node_id) {
		this.audit_node_id = audit_node_id;
	}
	
	public String getAudit_node_name() {
		return audit_node_name;
	}
	
	public void setAudit_node_name(String audit_node_name) {
		this.audit_node_name = audit_node_name;
	}
	
	public Long getAudit_next_node_id() {
		return audit_next_node_id;
	}
	
	public void setAudit_next_node_id(Long audit_next_node_id) {
		this.audit_next_node_id = audit_next_node_id;
	}
	
	public String getAudit_next_node_name() {
		return audit_next_node_name;
	}
	
	public void setAudit_next_node_name(String audit_next_node_name) {
		this.audit_next_node_name = audit_next_node_name;
	}
	
	public Integer getIs_audit_end() {
		return is_audit_end;
	}
	
	public void setIs_audit_end(Integer is_audit_end) {
		this.is_audit_end = is_audit_end;
	}
	
	public Integer getAudit_seq() {
		return audit_seq;
	}
	
	public void setAudit_seq(Integer audit_seq) {
		this.audit_seq = audit_seq;
	}
	
}