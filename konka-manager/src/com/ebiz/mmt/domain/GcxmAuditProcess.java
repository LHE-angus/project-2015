package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmAuditProcess extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String process_name;

	private String create_user_name;

	private BigDecimal create_user_id;

	private Date create_date;

	private String memo;

	private Integer is_del;

	private Long audit_type;
	
	private Long dept_id;

	private List<GcxmAuditProcessNode> gcxmAuditProcessNodeList;

	public GcxmAuditProcess() {

	}

	public List<GcxmAuditProcessNode> getGcxmAuditProcessNodeList() {
		return gcxmAuditProcessNodeList;
	}

	public void setGcxmAuditProcessNodeList(List<GcxmAuditProcessNode> gcxmAuditProcessNodeList) {
		this.gcxmAuditProcessNodeList = gcxmAuditProcessNodeList;
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
	 * @val 流程名称
	 */
	public String getProcess_name() {
		return process_name;
	}

	/**
	 * @val 流程名称
	 */
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	/**
	 * @val 创建人
	 */
	public String getCreate_user_name() {
		return create_user_name;
	}

	/**
	 * @val 创建人
	 */
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	/**
	 * @val 创建人ID
	 */
	public BigDecimal getCreate_user_id() {
		return create_user_id;
	}

	/**
	 * @val 创建人ID
	 */
	public void setCreate_user_id(BigDecimal create_user_id) {
		this.create_user_id = create_user_id;
	}

	/**
	 * @val 创建时间
	 */
	public Date getCreate_date() {
		return create_date;
	}

	/**
	 * @val 创建时间
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
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
	 * @val 删除标识:0正常,1已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 删除标识:0正常,1已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 审核流程类别（上报审核流程、报价审核流程）
	 */
	public Long getAudit_type() {
		return audit_type;
	}

	/**
	 * @val 审核流程类别（上报审核流程、报价审核流程）
	 */
	public void setAudit_type(Long audit_type) {
		this.audit_type = audit_type;
	}
	
	/**
	 * 分公司ID
	 * @return
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * 分公司ID
	 * @param deptId
	 */
	public void setDept_id(Long deptId) {
		dept_id = deptId;
	}

}