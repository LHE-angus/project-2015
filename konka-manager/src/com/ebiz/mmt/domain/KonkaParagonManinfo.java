package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonManinfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * seller_id
	 */
	private java.lang.Long seller_id;

	/**
	 * seller_name
	 */
	private java.lang.String seller_name;

	/**
	 * seller_code
	 */
	private java.lang.String seller_code;

	/**
	 * seller_phone
	 */
	private java.lang.String seller_phone;

	/**
	 * show_shop_code
	 */
	private java.lang.String show_shop_code;

	/**
	 * addman
	 */
	private java.lang.Long addman;

	/**
	 * addtime
	 */
	private java.util.Date addtime;

	/**
	 * seller_bank_count
	 */
	private java.lang.String seller_bank_count;

	/**
	 * seller_in
	 */
	private java.util.Date seller_in;
	private java.lang.String fixdate;

	public KonkaParagonManinfo() {
	}

	public void setSeller_id(java.lang.Long seller_id) {
		this.seller_id = seller_id;
	}

	public java.lang.Long getSeller_id() {
		return this.seller_id;
	}

	public void setSeller_name(java.lang.String seller_name) {
		this.seller_name = seller_name;
	}

	public java.lang.String getSeller_name() {
		return this.seller_name;
	}

	public void setSeller_code(java.lang.String seller_code) {
		this.seller_code = seller_code;
	}

	public java.lang.String getSeller_code() {
		return this.seller_code;
	}

	public void setSeller_phone(java.lang.String seller_phone) {
		this.seller_phone = seller_phone;
	}

	public java.lang.String getSeller_phone() {
		return this.seller_phone;
	}

	public void setShow_shop_code(java.lang.String show_shop_code) {
		this.show_shop_code = show_shop_code;
	}

	public java.lang.String getShow_shop_code() {
		return this.show_shop_code;
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

	public java.lang.String getSeller_bank_count() {
		return seller_bank_count;
	}

	public void setSeller_bank_count(java.lang.String seller_bank_count) {
		this.seller_bank_count = seller_bank_count;
	}

	public java.util.Date getSeller_in() {
		return seller_in;
	}

	public void setSeller_in(java.util.Date seller_in) {
		this.seller_in = seller_in;
	}

	public java.lang.String getFixdate() {
		return fixdate;
	}

	public void setFixdate(java.lang.String fixdate) {
		this.fixdate = fixdate;
	}
}