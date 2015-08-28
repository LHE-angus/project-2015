package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaMobileStocksHis extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * op_id
	 */
	private java.lang.Long op_id;

	/**
	 * 操作仓位
	 */
	private java.lang.Long storage_id;

	/**
	 * 操作存品
	 */
	private java.lang.Long coll_id;

	/**
	 * 操作类型
	 */
	private java.lang.Long op_type;

	/**
	 * 数量
	 */
	private java.math.BigDecimal op_num;

	/**
	 * 操作人
	 */
	private java.lang.Long op_man;

	/**
	 * 操作时间
	 */
	private java.util.Date op_time;

	public KonkaMobileStocksHis() {
	}
	
	public void setOp_id(java.lang.Long op_id) {
		this.op_id = op_id;
	}

	public java.lang.Long getOp_id() {
		return this.op_id;
	}
	
	public void setStorage_id(java.lang.Long storage_id) {
		this.storage_id = storage_id;
	}

	public java.lang.Long getStorage_id() {
		return this.storage_id;
	}
	
	public void setColl_id(java.lang.Long coll_id) {
		this.coll_id = coll_id;
	}

	public java.lang.Long getColl_id() {
		return this.coll_id;
	}
	
	public void setOp_type(java.lang.Long op_type) {
		this.op_type = op_type;
	}

	public java.lang.Long getOp_type() {
		return this.op_type;
	}
	
	public void setOp_num(java.math.BigDecimal op_num) {
		this.op_num = op_num;
	}

	public java.math.BigDecimal getOp_num() {
		return this.op_num;
	}
	
	public void setOp_man(java.lang.Long op_man) {
		this.op_man = op_man;
	}

	public java.lang.Long getOp_man() {
		return this.op_man;
	}
	
	public void setOp_time(java.util.Date op_time) {
		this.op_time = op_time;
	}

	public java.util.Date getOp_time() {
		return this.op_time;
	}


}