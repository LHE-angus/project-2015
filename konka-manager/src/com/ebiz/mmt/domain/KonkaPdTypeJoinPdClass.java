package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-10-28 17:35
 */
public class KonkaPdTypeJoinPdClass extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long pd_type;
	
	private String pd_name;
	
	private Long cls_id;
	
	private String cls_name;
	
	public KonkaPdTypeJoinPdClass() {

	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}
	
	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}
	
	public String getCls_name() {
		return cls_name;
	}

	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}
	
}