package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-19 16:29:47
 */
public class EcVouchersAudit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long link_id;
	
	private Long opr_user_id;
	
	private String opr_user_real_name;
	
	private Date oper_date;
	
	private Integer state;
	
	private String remark;
	
	public EcVouchersAudit() {

	}

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
	
	public Long getOpr_user_id() {
		return opr_user_id;
	}
	
	public void setOpr_user_id(Long opr_user_id) {
		this.opr_user_id = opr_user_id;
	}
	
	public String getOpr_user_real_name() {
		return opr_user_real_name;
	}
	
	public void setOpr_user_real_name(String opr_user_real_name) {
		this.opr_user_real_name = opr_user_real_name;
	}
	
	public Date getOper_date() {
		return oper_date;
	}
	
	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}