package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

public class MmtStdEntpUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * user_id
	 */
	private java.lang.Long user_id;

	/**
	 * user_name
	 */
	private java.lang.String user_name;

	/**
	 * pass_word
	 */
	private java.lang.String pass_word;

	/**
	 * user_type
	 */
	private java.lang.Integer user_type;

	/**
	 * entp_id
	 */
	private java.lang.Long entp_id;

	/**
	 * realname
	 */
	private java.lang.String realname;

	/**
	 * user_state
	 */
	private java.lang.Integer user_state;

	/**
	 * own_sys
	 */
	private java.lang.Integer own_sys;

	/**
	 * key_seq
	 */
	private java.lang.String key_seq;

	/**
	 * mmt_user
	 */
	private java.lang.String mmt_user;

	/**
	 * add_date
	 */
	private java.util.Date add_date;

	public MmtStdEntpUser() {
	}

	public void setUser_id(java.lang.Long user_id) {
		this.user_id = user_id;
	}
	public java.lang.Long getUser_id() {
		return this.user_id;
	}
	public void setUser_name(java.lang.String user_name) {
		this.user_name = user_name;
	}
	public java.lang.String getUser_name() {
		return this.user_name;
	}
	public void setPass_word(java.lang.String pass_word) {
		this.pass_word = pass_word;
	}
	public java.lang.String getPass_word() {
		return this.pass_word;
	}
	public void setUser_type(java.lang.Integer user_type) {
		this.user_type = user_type;
	}
	public java.lang.Integer getUser_type() {
		return this.user_type;
	}
	public void setEntp_id(java.lang.Long entp_id) {
		this.entp_id = entp_id;
	}
	public java.lang.Long getEntp_id() {
		return this.entp_id;
	}
	public void setRealname(java.lang.String realname) {
		this.realname = realname;
	}
	public java.lang.String getRealname() {
		return this.realname;
	}
	public void setUser_state(java.lang.Integer user_state) {
		this.user_state = user_state;
	}
	public java.lang.Integer getUser_state() {
		return this.user_state;
	}
	public void setOwn_sys(java.lang.Integer own_sys) {
		this.own_sys = own_sys;
	}
	public java.lang.Integer getOwn_sys() {
		return this.own_sys;
	}
	public void setKey_seq(java.lang.String key_seq) {
		this.key_seq = key_seq;
	}
	public java.lang.String getKey_seq() {
		return this.key_seq;
	}
	public void setMmt_user(java.lang.String mmt_user) {
		this.mmt_user = mmt_user;
	}
	public java.lang.String getMmt_user() {
		return this.mmt_user;
	}
	public void setAdd_date(java.util.Date add_date) {
		this.add_date = add_date;
	}
	public java.util.Date getAdd_date() {
		return this.add_date;
	}


}