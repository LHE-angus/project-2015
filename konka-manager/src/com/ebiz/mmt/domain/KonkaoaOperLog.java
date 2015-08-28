package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaoaOperLog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private java.lang.Long id;

	/**
	 * 操作模块名称
	 */
	private java.lang.String ppdm_name;

	public java.lang.String getPpdm_name() {
		return ppdm_name;
	}

	public void setPpdm_name(java.lang.String ppdm_name) {
		this.ppdm_name = ppdm_name;
	}

	/**
	 * 操作表
	 */
	private java.lang.String link_tab;

	/**
	 * 被操作记录的ID
	 */
	private java.lang.Long link_id;

	/**
	 * 操作人ID
	 */
	private java.lang.Long oper_uid;

	/**
	 * 操作人
	 */
	private java.lang.String oper_uname;

	/**
	 * 操作时间
	 */
	private java.util.Date oper_time;

	/**
	 * 操作IP
	 */
	private java.lang.String oper_ip;

	/**
	 * 操作说明
	 */
	private java.lang.String oper_desc;
	
	private java.lang.String oper_type;

	public KonkaoaOperLog() {
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public java.lang.String getOper_type() {
		return oper_type;
	}

	public void setOper_type(java.lang.String operType) {
		oper_type = operType;
	}

	public void setLink_tab(java.lang.String link_tab) {
		this.link_tab = link_tab;
	}

	public java.lang.String getLink_tab() {
		return this.link_tab;
	}

	public void setLink_id(java.lang.Long link_id) {
		this.link_id = link_id;
	}

	public java.lang.Long getLink_id() {
		return this.link_id;
	}

	public void setOper_uid(java.lang.Long oper_uid) {
		this.oper_uid = oper_uid;
	}

	public java.lang.Long getOper_uid() {
		return this.oper_uid;
	}

	public void setOper_uname(java.lang.String oper_uname) {
		this.oper_uname = oper_uname;
	}

	public java.lang.String getOper_uname() {
		return this.oper_uname;
	}

	public void setOper_time(java.util.Date oper_time) {
		this.oper_time = oper_time;
	}

	public java.util.Date getOper_time() {
		return this.oper_time;
	}

	public void setOper_ip(java.lang.String oper_ip) {
		this.oper_ip = oper_ip;
	}

	public java.lang.String getOper_ip() {
		return this.oper_ip;
	}

	public void setOper_desc(java.lang.String oper_desc) {
		this.oper_desc = oper_desc;
	}

	public java.lang.String getOper_desc() {
		return this.oper_desc;
	}

}