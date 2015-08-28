package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaMobileStorages extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * 仓库编码
	 */
	private Long storage_id;

	/**
	 * 仓库名称/仓位名称
	 */
	private java.lang.String storage_name;

	/**
	 * 仓库所属分公司
	 */
	private Long storage_areacom;

	/**
	 * 所属经办
	 */
	private Long storage_com;

	/**
	 * par_id
	 */
	private Long par_id;

	/**
	 * 1.仓库
2.仓位
	 */
	private Integer storage_type;

	/**
	 * is_del
	 */
	private Integer is_del;

	public KonkaMobileStorages() {
	}

	public Long getStorage_id() {
		return storage_id;
	}

	public void setStorage_id(Long storage_id) {
		this.storage_id = storage_id;
	}

	public java.lang.String getStorage_name() {
		return storage_name;
	}

	public void setStorage_name(java.lang.String storage_name) {
		this.storage_name = storage_name;
	}

	public Long getStorage_areacom() {
		return storage_areacom;
	}

	public void setStorage_areacom(Long storage_areacom) {
		this.storage_areacom = storage_areacom;
	}

	public Long getStorage_com() {
		return storage_com;
	}

	public void setStorage_com(Long storage_com) {
		this.storage_com = storage_com;
	}

	public Long getPar_id() {
		return par_id;
	}

	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	public Integer getStorage_type() {
		return storage_type;
	}

	public void setStorage_type(Integer storage_type) {
		this.storage_type = storage_type;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	
}