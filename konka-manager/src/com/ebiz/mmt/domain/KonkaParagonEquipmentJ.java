package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonEquipmentJ extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * equipment_j_id
	 */
	private java.lang.Long equipment_j_id;

	/**
	 * equipment_id
	 */
	private java.lang.Long equipment_id;

	/**
	 * show_shop_code
	 */
	private java.lang.String show_shop_code;

	/**
	 * equipment_num
	 */
	private java.lang.Long equipment_num;

	/**
	 * addman
	 */
	private java.lang.Long addman;

	/**
	 * addtime
	 */
	private java.util.Date addtime;

	/**
	 * startime
	 */
	private java.util.Date startime;

	/**
	 * endtime
	 */
	private java.util.Date endtime;

	private java.lang.String fixdate;

	public KonkaParagonEquipmentJ() {
	}

	public void setEquipment_j_id(java.lang.Long equipment_j_id) {
		this.equipment_j_id = equipment_j_id;
	}

	public java.lang.Long getEquipment_j_id() {
		return this.equipment_j_id;
	}

	public void setEquipment_id(java.lang.Long equipment_id) {
		this.equipment_id = equipment_id;
	}

	public java.lang.Long getEquipment_id() {
		return this.equipment_id;
	}

	public void setShow_shop_code(java.lang.String show_shop_code) {
		this.show_shop_code = show_shop_code;
	}

	public java.lang.String getShow_shop_code() {
		return this.show_shop_code;
	}

	public void setEquipment_num(java.lang.Long equipment_num) {
		this.equipment_num = equipment_num;
	}

	public java.lang.Long getEquipment_num() {
		return this.equipment_num;
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

	public java.util.Date getStartime() {
		return startime;
	}

	public void setStartime(java.util.Date startime) {
		this.startime = startime;
	}

	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}

	public java.lang.String getFixdate() {
		return fixdate;
	}

	public void setFixdate(java.lang.String fixdate) {
		this.fixdate = fixdate;
	}

}