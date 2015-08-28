package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public class EcUserAddrs extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private String rel_name;
	
	private String rel_phone;
	
	private String rel_tel;
	
	private Long rel_province;
	
	private Long rel_city;
	
	private Long rel_region;
	
	private String rel_addr;
	
	private Long rel_zip;
	
	private Date add_time;
	
	private Integer default_addr;
	
	private Integer own_sys;
	
	public EcUserAddrs() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 用户ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 用户ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 联系人姓名
	 */
	public String getRel_name() {
		return rel_name;
	}
	
	/**
	 * @val 联系人姓名
	 */
	public void setRel_name(String rel_name) {
		this.rel_name = rel_name;
	}
	
	/**
	 * @val 联系人手机号码
	 */
	public String getRel_phone() {
		return rel_phone;
	}
	
	/**
	 * @val 联系人手机号码
	 */
	public void setRel_phone(String rel_phone) {
		this.rel_phone = rel_phone;
	}
	
	/**
	 * @val 联系人电话号码
	 */
	public String getRel_tel() {
		return rel_tel;
	}
	
	/**
	 * @val 联系人电话号码
	 */
	public void setRel_tel(String rel_tel) {
		this.rel_tel = rel_tel;
	}
	
	/**
	 * @val 所在省
	 */
	public Long getRel_province() {
		return rel_province;
	}
	
	/**
	 * @val 所在省
	 */
	public void setRel_province(Long rel_province) {
		this.rel_province = rel_province;
	}
	
	/**
	 * @val 所在市
	 */
	public Long getRel_city() {
		return rel_city;
	}
	
	/**
	 * @val 所在市
	 */
	public void setRel_city(Long rel_city) {
		this.rel_city = rel_city;
	}
	
	/**
	 * @val 所在地区
	 */
	public Long getRel_region() {
		return rel_region;
	}
	
	/**
	 * @val 所在地区
	 */
	public void setRel_region(Long rel_region) {
		this.rel_region = rel_region;
	}
	
	/**
	 * @val 联系地址
	 */
	public String getRel_addr() {
		return rel_addr;
	}
	
	/**
	 * @val 联系地址
	 */
	public void setRel_addr(String rel_addr) {
		this.rel_addr = rel_addr;
	}
	
	/**
	 * @val 邮政编码
	 */
	public Long getRel_zip() {
		return rel_zip;
	}
	
	/**
	 * @val 邮政编码
	 */
	public void setRel_zip(Long rel_zip) {
		this.rel_zip = rel_zip;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_time() {
		return add_time;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	/**
	 * @val 一个客户只有一个默认地址:0-非默认，1-默认
	 * @val 
	 */
	public Integer getDefault_addr() {
		return default_addr;
	}
	
	/**
	 * @val 一个客户只有一个默认地址:0-非默认，1-默认
	 * @val 
	 */
	public void setDefault_addr(Integer default_addr) {
		this.default_addr = default_addr;
	}
	
	/**
	 * @val 所属平台：1-工卡，2-触网，3-会员
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属平台：1-工卡，2-触网，3-会员
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
}