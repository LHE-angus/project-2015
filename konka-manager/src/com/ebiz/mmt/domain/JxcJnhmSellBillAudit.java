package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-08-17 08:47:28
 */
public class JxcJnhmSellBillAudit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long sell_bill_id;
	
	private String audit_user_name;
	
	private Integer states;
	
	private String audit_remarks;
	
	private Date audit_date;
	
	public JxcJnhmSellBillAudit() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSell_bill_id() {
		return sell_bill_id;
	}
	
	public void setSell_bill_id(Long sell_bill_id) {
		this.sell_bill_id = sell_bill_id;
	}
	
	public String getAudit_user_name() {
		return audit_user_name;
	}
	
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	
	public Integer getStates() {
		return states;
	}
	
	public void setStates(Integer states) {
		this.states = states;
	}
	
	public String getAudit_remarks() {
		return audit_remarks;
	}
	
	public void setAudit_remarks(String audit_remarks) {
		this.audit_remarks = audit_remarks;
	}
	
	public Date getAudit_date() {
		return audit_date;
	}
	
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	
}