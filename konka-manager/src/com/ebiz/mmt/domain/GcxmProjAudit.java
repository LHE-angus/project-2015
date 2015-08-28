package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmProjAudit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long proj_id;

	private String proj_code;

	private Long audit_type;

	private String audit_model;

	private BigDecimal audit_user_id;

	private Date audit_date;

	private String audit_idea;

	private Long is_meet;

	private Integer audit_result;

	private GcxmProjOffer gcxmProjOffer;

	public GcxmProjAudit() {

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
	 * @val 项目ID
	 */
	public Long getProj_id() {
		return proj_id;
	}

	/**
	 * @val 项目ID
	 */
	public void setProj_id(Long proj_id) {
		this.proj_id = proj_id;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public String getProj_code() {
		return proj_code;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}

	/**
	 * @val 审核类别
	 */
	public Long getAudit_type() {
		return audit_type;
	}

	/**
	 * @val 审核类别
	 */
	public void setAudit_type(Long audit_type) {
		this.audit_type = audit_type;
	}

	/**
	 * @val 审核人
	 */
	public String getAudit_model() {
		return audit_model;
	}

	/**
	 * @val 审核人
	 */
	public void setAudit_model(String audit_model) {
		this.audit_model = audit_model;
	}

	/**
	 * @val 审核人ID
	 */
	public BigDecimal getAudit_user_id() {
		return audit_user_id;
	}

	/**
	 * @val 审核人ID
	 */
	public void setAudit_user_id(BigDecimal audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	/**
	 * @val 审批时间
	 */
	public Date getAudit_date() {
		return audit_date;
	}

	/**
	 * @val 审批时间
	 */
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	/**
	 * @val 审批意见
	 */
	public String getAudit_idea() {
		return audit_idea;
	}

	/**
	 * @val 审批意见
	 */
	public void setAudit_idea(String audit_idea) {
		this.audit_idea = audit_idea;
	}

	/**
	 * @val 是否满足参数要求:0不满足,1满足
	 */
	public Long getIs_meet() {
		return is_meet;
	}

	/**
	 * @val 是否满足参数要求:0不满足,1满足
	 */
	public void setIs_meet(Long is_meet) {
		this.is_meet = is_meet;
	}

	public Integer getAudit_result() {
		return audit_result;
	}

	public void setAudit_result(Integer audit_result) {
		this.audit_result = audit_result;
	}

	public GcxmProjOffer getGcxmProjOffer() {
		return gcxmProjOffer;
	}

	public void setGcxmProjOffer(GcxmProjOffer gcxmProjOffer) {
		this.gcxmProjOffer = gcxmProjOffer;
	}

}