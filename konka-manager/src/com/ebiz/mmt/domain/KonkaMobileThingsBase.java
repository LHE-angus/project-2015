package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaMobileThingsBase extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	

	/**
	 * id
	 */
	private java.lang.Long id;

	/**
	 * thing_name
	 */
	private java.lang.String thing_name;

	/**
	 * thing_type
	 */
	private java.lang.Integer thing_type;

	/**
	 * charge_man
	 */
	private java.lang.Long charge_man;

	/**
	 * done_start
	 */
	private java.util.Date done_start;

	/**
	 * done_end
	 */
	private java.util.Date done_end;

	public KonkaMobileThingsBase() {
	}
	
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setThing_name(java.lang.String thing_name) {
		this.thing_name = thing_name;
	}

	public java.lang.String getThing_name() {
		return this.thing_name;
	}
	
	public void setThing_type(java.lang.Integer thing_type) {
		this.thing_type = thing_type;
	}

	public java.lang.Integer getThing_type() {
		return this.thing_type;
	}
	
	public void setCharge_man(java.lang.Long charge_man) {
		this.charge_man = charge_man;
	}

	public java.lang.Long getCharge_man() {
		return this.charge_man;
	}
	
	public void setDone_start(java.util.Date done_start) {
		this.done_start = done_start;
	}

	public java.util.Date getDone_start() {
		return this.done_start;
	}
	
	public void setDone_end(java.util.Date done_end) {
		this.done_end = done_end;
	}

	public java.util.Date getDone_end() {
		return this.done_end;
	}


}