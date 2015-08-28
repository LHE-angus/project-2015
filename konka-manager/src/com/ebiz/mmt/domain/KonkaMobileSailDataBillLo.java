package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-22 10:56:34
 */
public class KonkaMobileSailDataBillLo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long audit_id;
	
	private Long bill_id;
	
	private Long oper_user_id;
	
	private Date oper_date;
	
	private BigDecimal oper_money;
	
	private String oper_mem;
	
	private Integer oper_state;
	
	public KonkaMobileSailDataBillLo() {

	}

	public Long getAudit_id() {
		return audit_id;
	}
	
	public void setAudit_id(Long audit_id) {
		this.audit_id = audit_id;
	}
	
	/**
	 * @val 票据ID
	 */
	public Long getBill_id() {
		return bill_id;
	}
	
	/**
	 * @val 票据ID
	 */
	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}
	
	/**
	 * @val 操作人ID
	 */
	public Long getOper_user_id() {
		return oper_user_id;
	}
	
	/**
	 * @val 操作人ID
	 */
	public void setOper_user_id(Long oper_user_id) {
		this.oper_user_id = oper_user_id;
	}
	
	/**
	 * @val 操作时间
	 */
	public Date getOper_date() {
		return oper_date;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}
	
	/**
	 * @val 操作金额
	 */
	public BigDecimal getOper_money() {
		return oper_money;
	}
	
	/**
	 * @val 操作金额
	 */
	public void setOper_money(BigDecimal oper_money) {
		this.oper_money = oper_money;
	}
	
	/**
	 * @val 说明
	 */
	public String getOper_mem() {
		return oper_mem;
	}
	
	/**
	 * @val 说明
	 */
	public void setOper_mem(String oper_mem) {
		this.oper_mem = oper_mem;
	}
	
	/**
	 * @val 操作状态
	 */
	public Integer getOper_state() {
		return oper_state;
	}
	
	/**
	 * @val 操作状态
	 */
	public void setOper_state(Integer oper_state) {
		this.oper_state = oper_state;
	}
	
}