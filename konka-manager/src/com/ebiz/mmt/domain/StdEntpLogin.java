package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-12-23 10:47:00
 */
public class StdEntpLogin extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String user_name;
	
	private Integer user_type;
	
	private Long entp_id;
	
	private Integer own_sys;
	
	private Long p_index;
	
	private String mmt_user;
	
	private Date add_date;
	
	private String client_ip;
	
	private String client_versions;
	
	public StdEntpLogin() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 官网用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 官网用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 用户类型
	 * @val 2：销售
	 * @val 4：回收
	 */
	public Integer getUser_type() {
		return user_type;
	}
	
	/**
	 * @val 用户类型
	 * @val 2：销售
	 * @val 4：回收
	 */
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * @val 所属企业编号
	 */
	public Long getEntp_id() {
		return entp_id;
	}
	
	/**
	 * @val 所属企业编号
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}
	
	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val 所在地区
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 所在地区
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 绑定买卖提用户
	 */
	public String getMmt_user() {
		return mmt_user;
	}
	
	/**
	 * @val 绑定买卖提用户
	 */
	public void setMmt_user(String mmt_user) {
		this.mmt_user = mmt_user;
	}
	
	/**
	 * @val 统计时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 统计时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 用户登录IP
	 */
	public String getClient_ip() {
		return client_ip;
	}
	
	/**
	 * @val 用户登录IP
	 */
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	
	/**
	 * @val 客户端版本号
	 */
	public String getClient_versions() {
		return client_versions;
	}
	
	/**
	 * @val 客户端版本号
	 */
	public void setClient_versions(String client_versions) {
		this.client_versions = client_versions;
	}
	
}