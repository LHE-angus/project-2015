package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmProjBuyinfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String proj_id;
	
	private String proj_code;
	
	private String size;
	
	private String buyer_num;
	
	private String budget;
	
	private String memo;
	
	public GcxmProjBuyinfo() {

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
	public String getProj_id() {
		return proj_id;
	}
	
	/**
	 * @val 项目ID
	 */
	public void setProj_id(String proj_id) {
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
	 * @val 采购尺寸
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * @val 采购尺寸
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	/**
	 * @val 采购|投标人电话
	 */
	public String getBuyer_num() {
		return buyer_num;
	}
	
	/**
	 * @val 采购|投标人电话
	 */
	public void setBuyer_num(String buyer_num) {
		this.buyer_num = buyer_num;
	}
	
	/**
	 * @val 采购预算
	 */
	public String getBudget() {
		return budget;
	}
	
	/**
	 * @val 采购预算
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	/**
	 * @val 参数要求
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @val 参数要求
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}