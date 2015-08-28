package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonShowmanre extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * re_id
	 */
	private java.lang.Long re_id;

	/**
	 * user_id
	 */
	private java.lang.Long user_id;

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

	public KonkaParagonShowmanre() {
	}
	
	public void setRe_id(java.lang.Long re_id) {
		this.re_id = re_id;
	}

	public java.lang.Long getRe_id() {
		return this.re_id;
	}
	
	public void setUser_id(java.lang.Long user_id) {
		this.user_id = user_id;
	}

	public java.lang.Long getUser_id() {
		return this.user_id;
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


}