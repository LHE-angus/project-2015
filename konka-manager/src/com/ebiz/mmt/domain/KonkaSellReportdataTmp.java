package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaSellReportdataTmp extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * 主体
	 */
	private java.lang.String obj_name;

	/**
	 * 产品型号ID
	 */
	private java.math.BigDecimal pd_id;

	/**
	 * 产品型号
	 */
	private java.lang.String pd_name;

	/**
	 * 产品类型ID
	 */
	private java.math.BigDecimal pd_type_id;

	/**
	 * 产品类型
	 */
	private java.lang.String pd_type;

	/**
	 * 期初结存数量
	 */
	private java.math.BigDecimal a_num;

	/**
	 * 期初结存单价
	 */
	private java.math.BigDecimal a_price;

	/**
	 * 期初结存金额
	 */
	private java.math.BigDecimal a_all;

	/**
	 * 日常进货数量
	 */
	private java.math.BigDecimal b_num;

	/**
	 * 日常进货单价
	 */
	private java.math.BigDecimal b_price;

	/**
	 * 日常进货金额
	 */
	private java.math.BigDecimal b_all;

	/**
	 * 日常出货数量
	 */
	private java.math.BigDecimal c_num;

	/**
	 * 日常出货单价
	 */
	private java.math.BigDecimal c_price;

	/**
	 * 日常出货金额
	 */
	private java.math.BigDecimal c_all;

	/**
	 * 盘盈亏数量
	 */
	private java.math.BigDecimal d_num;

	/**
	 * 盘盈亏单价
	 */
	private java.math.BigDecimal d_price;

	/**
	 * 盘盈亏金额
	 */
	private java.math.BigDecimal d_all;

	/**
	 * 期末结存数量
	 */
	private java.math.BigDecimal e_num;

	/**
	 * 期末结存单价
	 */
	private java.math.BigDecimal e_price;

	/**
	 * 期末结存金额
	 */
	private java.math.BigDecimal e_all;

	/**
	 * 数据有效特征值
	 */
	private java.math.BigDecimal j_id;

	public KonkaSellReportdataTmp() {
	}
	
	public void setObj_name(java.lang.String obj_name) {
		this.obj_name = obj_name;
	}

	public java.lang.String getObj_name() {
		return this.obj_name;
	}
	
	public void setPd_id(java.math.BigDecimal pd_id) {
		this.pd_id = pd_id;
	}

	public java.math.BigDecimal getPd_id() {
		return this.pd_id;
	}
	
	public void setPd_name(java.lang.String pd_name) {
		this.pd_name = pd_name;
	}

	public java.lang.String getPd_name() {
		return this.pd_name;
	}
	
	public void setPd_type_id(java.math.BigDecimal pd_type_id) {
		this.pd_type_id = pd_type_id;
	}

	public java.math.BigDecimal getPd_type_id() {
		return this.pd_type_id;
	}
	
	public void setPd_type(java.lang.String pd_type) {
		this.pd_type = pd_type;
	}

	public java.lang.String getPd_type() {
		return this.pd_type;
	}
	
	public void setA_num(java.math.BigDecimal a_num) {
		this.a_num = a_num;
	}

	public java.math.BigDecimal getA_num() {
		return this.a_num;
	}
	
	public void setA_price(java.math.BigDecimal a_price) {
		this.a_price = a_price;
	}

	public java.math.BigDecimal getA_price() {
		return this.a_price;
	}
	
	public void setA_all(java.math.BigDecimal a_all) {
		this.a_all = a_all;
	}

	public java.math.BigDecimal getA_all() {
		return this.a_all;
	}
	
	public void setB_num(java.math.BigDecimal b_num) {
		this.b_num = b_num;
	}

	public java.math.BigDecimal getB_num() {
		return this.b_num;
	}
	
	public void setB_price(java.math.BigDecimal b_price) {
		this.b_price = b_price;
	}

	public java.math.BigDecimal getB_price() {
		return this.b_price;
	}
	
	public void setB_all(java.math.BigDecimal b_all) {
		this.b_all = b_all;
	}

	public java.math.BigDecimal getB_all() {
		return this.b_all;
	}
	
	public void setC_num(java.math.BigDecimal c_num) {
		this.c_num = c_num;
	}

	public java.math.BigDecimal getC_num() {
		return this.c_num;
	}
	
	public void setC_price(java.math.BigDecimal c_price) {
		this.c_price = c_price;
	}

	public java.math.BigDecimal getC_price() {
		return this.c_price;
	}
	
	public void setC_all(java.math.BigDecimal c_all) {
		this.c_all = c_all;
	}

	public java.math.BigDecimal getC_all() {
		return this.c_all;
	}
	
	public void setD_num(java.math.BigDecimal d_num) {
		this.d_num = d_num;
	}

	public java.math.BigDecimal getD_num() {
		return this.d_num;
	}
	
	public void setD_price(java.math.BigDecimal d_price) {
		this.d_price = d_price;
	}

	public java.math.BigDecimal getD_price() {
		return this.d_price;
	}
	
	public void setD_all(java.math.BigDecimal d_all) {
		this.d_all = d_all;
	}

	public java.math.BigDecimal getD_all() {
		return this.d_all;
	}
	
	public void setE_num(java.math.BigDecimal e_num) {
		this.e_num = e_num;
	}

	public java.math.BigDecimal getE_num() {
		return this.e_num;
	}
	
	public void setE_price(java.math.BigDecimal e_price) {
		this.e_price = e_price;
	}

	public java.math.BigDecimal getE_price() {
		return this.e_price;
	}
	
	public void setE_all(java.math.BigDecimal e_all) {
		this.e_all = e_all;
	}

	public java.math.BigDecimal getE_all() {
		return this.e_all;
	}
	
	public void setJ_id(java.math.BigDecimal j_id) {
		this.j_id = j_id;
	}

	public java.math.BigDecimal getJ_id() {
		return this.j_id;
	}


}