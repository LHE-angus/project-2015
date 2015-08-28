package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-28 10:39:23
 */
public class SfhkCompany extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String comp_id;
	
	private String comp_name;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public SfhkCompany() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComp_id() {
		return comp_id;
	}
	
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	
	public String getComp_name() {
		return comp_name;
	}
	
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
}