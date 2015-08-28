package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonShowversion extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * 形象版本ID
	 */
	private java.lang.Long version_id;

	/**
	 * 版本名称
	 */
	private java.lang.String version_name;

	/**
	 * 版本代码
	 */
	private java.lang.String version_code;

	/**
	 * 版本备注
	 */
	private java.lang.String version_memo;

	/**
	 * 添加人
	 */
	private java.lang.Long addman;

	/**
	 * 添加时间
	 */
	private java.util.Date addtime;

	public KonkaParagonShowversion() {
	}
	
	public void setVersion_id(java.lang.Long version_id) {
		this.version_id = version_id;
	}

	public java.lang.Long getVersion_id() {
		return this.version_id;
	}
	
	public void setVersion_name(java.lang.String version_name) {
		this.version_name = version_name;
	}

	public java.lang.String getVersion_name() {
		return this.version_name;
	}
	
	public void setVersion_code(java.lang.String version_code) {
		this.version_code = version_code;
	}

	public java.lang.String getVersion_code() {
		return this.version_code;
	}
	
	public void setVersion_memo(java.lang.String version_memo) {
		this.version_memo = version_memo;
	}

	public java.lang.String getVersion_memo() {
		return this.version_memo;
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



}