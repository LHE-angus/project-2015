package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-29 14:09:13
 */
public class KonkaR3OrderOplog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long log_id;
	
	private Long operator_id;
	
	private String operator_type;
	
	private String operator_desc;
	
	private Date operator_time;
	
	private Long source_id;
	
	private String attribute1;
	
	public KonkaR3OrderOplog() {

	}

	public Long getLog_id() {
		return log_id;
	}
	
	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}
	
	public Long getOperator_id() {
		return operator_id;
	}
	
	public void setOperator_id(Long operator_id) {
		this.operator_id = operator_id;
	}
	
	public String getOperator_type() {
		return operator_type;
	}
	
	public void setOperator_type(String operator_type) {
		this.operator_type = operator_type;
	}
	
	public String getOperator_desc() {
		return operator_desc;
	}
	
	public void setOperator_desc(String operator_desc) {
		this.operator_desc = operator_desc;
	}
	
	public Date getOperator_time() {
		return operator_time;
	}
	
	public void setOperator_time(Date operator_time) {
		this.operator_time = operator_time;
	}
	
	public Long getSource_id() {
		return source_id;
	}
	
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}
	
	public String getAttribute1() {
		return attribute1;
	}
	
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	
}