package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-22 10:56:33
 */
public class KonkaMobileSailDataBill extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long bill_id;

	private Long sail_id;

	private Long adjunct_link_id;

	private String bill_no;

	private String bill_mem;

	private Integer is_valid_for_pay;

	private Date upload_time;

	private Integer data_source;

	private Long upload_user_id;

	private Integer state;

	private Integer is_valid;

	private Date valie_oper_date;

	private Long valid_oper_user_id;

	private BigDecimal dec_money;//申请提成

	private BigDecimal audit_money;//审核提成

	private BigDecimal final_audit_money;//终审提成（参考提成）

	private Date audit_date;

	private Long switch_id;

	private Attachment Attachment;

	public KonkaMobileSailDataBill() {

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
	 * @val 销售明细ID
	 */
	public Long getSail_id() {
		return sail_id;
	}

	/**
	 * @val 销售明细ID
	 */
	public void setSail_id(Long sail_id) {
		this.sail_id = sail_id;
	}

	/**
	 * @val 附件ID
	 */
	public Long getAdjunct_link_id() {
		return adjunct_link_id;
	}

	/**
	 * @val 附件ID
	 */
	public void setAdjunct_link_id(Long adjunct_link_id) {
		this.adjunct_link_id = adjunct_link_id;
	}

	/**
	 * @val 票据编号
	 */
	public String getBill_no() {
		return bill_no;
	}

	/**
	 * @val 票据编号
	 */
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	/**
	 * @val 票据备注
	 */
	public String getBill_mem() {
		return bill_mem;
	}

	/**
	 * @val 票据备注
	 */
	public void setBill_mem(String bill_mem) {
		this.bill_mem = bill_mem;
	}

	/**
	 * @val 是否参与提成核算
	 * @val 0表示参与
	 * @val 1表示不参与
	 */
	public Integer getIs_valid_for_pay() {
		return is_valid_for_pay;
	}

	/**
	 * @val 是否参与提成核算
	 * @val 0表示参与
	 * @val 1表示不参与
	 */
	public void setIs_valid_for_pay(Integer is_valid_for_pay) {
		this.is_valid_for_pay = is_valid_for_pay;
	}

	/**
	 * @val 上传时间
	 */
	public Date getUpload_time() {
		return upload_time;
	}

	/**
	 * @val 上传时间
	 */
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	/**
	 * @val 数据来源:0-手机端 1-WEB端 2-IOS手机端
	 */
	public Integer getData_source() {
		return data_source;
	}

	/**
	 * @val 数据来源:0-手机端 1-WEB端 2-IOS手机端
	 */
	public void setData_source(Integer data_source) {
		this.data_source = data_source;
	}

	/**
	 * @val 上传人ID
	 */
	public Long getUpload_user_id() {
		return upload_user_id;
	}

	/**
	 * @val 上传人ID
	 */
	public void setUpload_user_id(Long upload_user_id) {
		this.upload_user_id = upload_user_id;
	}

	/**
	 * @val 状态
	 * @val 0上传中，
	 * @val 3 初审通过
	 * @val 4初审不通过
	 * @val 5初审通过并转单
	 * @val 5终审通过
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 状态
	 * @val 0待审，
	 * @val 2 初审通过
	 * @val 4初审不通过
	 * @val 6初审通过并转单
	 * @val 8终审通过
	 * @val 10终审通过
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 是否有效
	 * @val 0有效
	 * @val 1无效
	 */
	public Integer getIs_valid() {
		return is_valid;
	}

	/**
	 * @val 是否有效
	 * @val 0有效
	 * @val 1无效
	 */
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}

	/**
	 * @val 设置有效无效时间
	 */
	public Date getValie_oper_date() {
		return valie_oper_date;
	}

	/**
	 * @val 设置有效无效时间
	 */
	public void setValie_oper_date(Date valie_oper_date) {
		this.valie_oper_date = valie_oper_date;
	}

	/**
	 * @val 设置无效用户
	 */
	public Long getValid_oper_user_id() {
		return valid_oper_user_id;
	}

	/**
	 * @val 设置无效用户
	 */
	public void setValid_oper_user_id(Long valid_oper_user_id) {
		this.valid_oper_user_id = valid_oper_user_id;
	}

	/**
	 * @val 申请提成金额
	 */
	public BigDecimal getDec_money() {
		return dec_money;
	}

	/**
	 * @val 申请提成金额
	 */
	public void setDec_money(BigDecimal dec_money) {
		this.dec_money = dec_money;
	}

	/**
	 * @val 初审提成金额
	 */
	public BigDecimal getAudit_money() {
		return audit_money;
	}

	/**
	 * @val 初审提成金额
	 */
	public void setAudit_money(BigDecimal audit_money) {
		this.audit_money = audit_money;
	}

	/**
	 * @val 终审金额
	 */
	public BigDecimal getFinal_audit_money() {
		return final_audit_money;
	}

	/**
	 * @val 终审金额
	 */
	public void setFinal_audit_money(BigDecimal final_audit_money) {
		this.final_audit_money = final_audit_money;
	}

	/**
	 * @val 最后审核时间
	 */
	public Date getAudit_date() {
		return audit_date;
	}

	/**
	 * @val 最后审核时间
	 */
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	public Attachment getAttachment() {
		return Attachment;
	}

	public void setAttachment(Attachment attachment) {
		Attachment = attachment;
	}

	public Long getSwitch_id() {
		return switch_id;
	}

	/**
	 * @val 关联转单的主键
	 */
	public void setSwitch_id(Long switch_id) {
		this.switch_id = switch_id;
	}
}