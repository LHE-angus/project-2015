package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaSellReportableTmp extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * 主源ID
	 */
	private java.math.BigDecimal j_id;

	/**
	 * s_id
	 */
	private java.math.BigDecimal s_id;

	/**
	 * key_id
	 */
	private java.math.BigDecimal key_id;

	/**
	 * 开始时间
	 */
	private java.lang.String s_time;

	/**
	 * 结束时间
	 */
	private java.lang.String e_time;

	/**
	 * 最后生效入库记录ID
	 */
	private java.math.BigDecimal l_rec_id;

	/**
	 * 最后生效出库记录ID
	 */
	private java.math.BigDecimal l_sen_id;

	/**
	 * 最后生效盘点记录ID
	 */
	private java.math.BigDecimal l_sta_id;

	public KonkaSellReportableTmp() {
	}
	
	public void setJ_id(java.math.BigDecimal j_id) {
		this.j_id = j_id;
	}

	public java.math.BigDecimal getJ_id() {
		return this.j_id;
	}
	
	public void setS_id(java.math.BigDecimal s_id) {
		this.s_id = s_id;
	}

	public java.math.BigDecimal getS_id() {
		return this.s_id;
	}
	
	public void setKey_id(java.math.BigDecimal key_id) {
		this.key_id = key_id;
	}

	public java.math.BigDecimal getKey_id() {
		return this.key_id;
	}
	
	public void setS_time(java.lang.String s_time) {
		this.s_time = s_time;
	}

	public java.lang.String getS_time() {
		return this.s_time;
	}
	
	public void setE_time(java.lang.String e_time) {
		this.e_time = e_time;
	}

	public java.lang.String getE_time() {
		return this.e_time;
	}
	
	public void setL_rec_id(java.math.BigDecimal l_rec_id) {
		this.l_rec_id = l_rec_id;
	}

	public java.math.BigDecimal getL_rec_id() {
		return this.l_rec_id;
	}
	
	public void setL_sen_id(java.math.BigDecimal l_sen_id) {
		this.l_sen_id = l_sen_id;
	}

	public java.math.BigDecimal getL_sen_id() {
		return this.l_sen_id;
	}
	
	public void setL_sta_id(java.math.BigDecimal l_sta_id) {
		this.l_sta_id = l_sta_id;
	}

	public java.math.BigDecimal getL_sta_id() {
		return this.l_sta_id;
	}


}