package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-22 17:27:22
 */
public class SfPindex extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String province;
	
	private String city;
	
	private String country;
	
	private String town;
	
	private String is_reached;
	
	private String par_index;
	
	private String p_index;
	
	public SfPindex() {

	}

	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getIs_reached() {
		return is_reached;
	}
	
	public void setIs_reached(String is_reached) {
		this.is_reached = is_reached;
	}
	
	public String getPar_index() {
		return par_index;
	}
	
	public void setPar_index(String par_index) {
		this.par_index = par_index;
	}
	
	public String getP_index() {
		return p_index;
	}
	
	public void setP_index(String p_index) {
		this.p_index = p_index;
	}
	
}