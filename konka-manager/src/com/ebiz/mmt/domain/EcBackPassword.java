package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-13 14:21:31
 */
public class EcBackPassword extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String card_no;
	
	private String real_name;
	
	private String email;
	
	private String yz_key;
	
	private Integer state;
	
	private Date add_date;
	
	public EcBackPassword() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCard_no() {
		return card_no;
	}
	
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	public String getReal_name() {
		return real_name;
	}
	
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getYz_key() {
		return yz_key;
	}
	
	public void setYz_key(String yz_key) {
		this.yz_key = yz_key;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}