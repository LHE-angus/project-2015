package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 16:39:53
 */
public class EcSenderInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String real_name;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String addr;
	
	private Long dept_id;
	
	private String dept_name;
	
	private String tel;
	
	private String mobile;
	
	private String month_account;
	
	private String add_user_id;
	
	private String add_user_name;
	
	private Date add_date;
	
	public EcSenderInfo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getReal_name() {
		return real_name;
	}
	
	public void setReal_name(String real_name) {
		this.real_name = real_name;
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
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMonth_account() {
		return month_account;
	}
	
	public void setMonth_account(String month_account) {
		this.month_account = month_account;
	}
	
	public String getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(String add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}