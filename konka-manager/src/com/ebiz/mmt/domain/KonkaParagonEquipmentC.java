package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonEquipmentC extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * equipment_id
	 */
	private java.lang.Long equipment_id;

	/**
	 * equipment_name
	 */
	private java.lang.String equipment_name;

	/**
	 * 1.正常
2.锁定
	 */
	private java.lang.Integer status;

	/**
	 * 1.样机尺寸
	 */
	private BigDecimal  proto_size;
	
	/**
	 * 1.演示设备
2.样机
	 */
	private java.lang.Integer etype;

	/**
	 * addman
	 */
	private java.lang.Long addman;

	/**
	 * addtime
	 */
	private java.util.Date addtime;
	
	/**
	 */
	private java.lang.Integer is_del;

	public KonkaParagonEquipmentC() {
	}
	
	public void setEquipment_id(java.lang.Long equipment_id) {
		this.equipment_id = equipment_id;
	}

	public java.lang.Long getEquipment_id() {
		return this.equipment_id;
	}
	
	public void setEquipment_name(java.lang.String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public java.lang.String getEquipment_name() {
		return this.equipment_name;
	}
	
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setEtype(java.lang.Integer etype) {
		this.etype = etype;
	}

	public java.lang.Integer getEtype() {
		return this.etype;
	}
	
	public void setAddman(java.lang.Long addman) {
		this.addman = addman;
	}

	public java.lang.Long getAddman() {
		return this.addman;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public java.util.Date getAddtime() {
		return this.addtime;
	}

	public BigDecimal getProto_size() {
		return proto_size;
	}

	public void setProto_size(BigDecimal protoSize) {
		proto_size = protoSize;
	}

	public java.lang.Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(java.lang.Integer isDel) {
		is_del = isDel;
	}

}