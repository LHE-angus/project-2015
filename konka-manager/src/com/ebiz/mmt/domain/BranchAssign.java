package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class BranchAssign extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * id
	 */
	private java.lang.Long id;

	/**
	 * 分配类别 1R3网点分配 2经销网点分配
	 */
	private java.lang.Integer branch_type;

	/**
	 * KONKA_R3_SHOP的ID
	 */
	private java.lang.Long konka_r3_id;

	/**
	 * mmt_shop_id
	 */
	private java.lang.Long mmt_shop_id;

	/**
	 * 事业部_dept_ID
	 */
	private java.lang.Long syb_id;

	/**
	 * 分公司_Dept_ID
	 */
	private java.lang.Long fgs_id;

	/**
	 * 经营部_dept_ID
	 */
	private java.lang.Long jyb_id;

	/**
	 * 办事处_dept_ID
	 */
	private java.lang.Long bsc_id;

	/**
	 * USER_ID
	 */
	private java.lang.Long user_id;
	
	/**
	 * ADD_USER_ID
	 */
	private java.lang.Long add_user_id;
	
	/**
	 * ADD_DATE
	 */
	private Date add_date;

	public BranchAssign() {
	}
	
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setBranch_type(java.lang.Integer branch_type) {
		this.branch_type = branch_type;
	}

	public java.lang.Integer getBranch_type() {
		return this.branch_type;
	}
	
	public void setKonka_r3_id(java.lang.Long konka_r3_id) {
		this.konka_r3_id = konka_r3_id;
	}

	public java.lang.Long getKonka_r3_id() {
		return this.konka_r3_id;
	}
	
	public void setMmt_shop_id(java.lang.Long mmt_shop_id) {
		this.mmt_shop_id = mmt_shop_id;
	}

	public java.lang.Long getMmt_shop_id() {
		return this.mmt_shop_id;
	}
	
	public void setSyb_id(java.lang.Long syb_id) {
		this.syb_id = syb_id;
	}

	public java.lang.Long getSyb_id() {
		return this.syb_id;
	}
	
	public void setFgs_id(java.lang.Long fgs_id) {
		this.fgs_id = fgs_id;
	}

	public java.lang.Long getFgs_id() {
		return this.fgs_id;
	}
	
	public void setJyb_id(java.lang.Long jyb_id) {
		this.jyb_id = jyb_id;
	}

	public java.lang.Long getJyb_id() {
		return this.jyb_id;
	}
	
	public void setBsc_id(java.lang.Long bsc_id) {
		this.bsc_id = bsc_id;
	}

	public java.lang.Long getBsc_id() {
		return this.bsc_id;
	}
	
	public void setUser_id(java.lang.Long user_id) {
		this.user_id = user_id;
	}

	public java.lang.Long getUser_id() {
		return this.user_id;
	}

	public java.lang.Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(java.lang.Long addUserId) {
		add_user_id = addUserId;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date addDate) {
		add_date = addDate;
	}


}