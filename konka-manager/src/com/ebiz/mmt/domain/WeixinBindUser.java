package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-24 14:43:35
 */
public class WeixinBindUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String user_name;
	
	private Long user_type;
	
	private Integer is_del;
	
	private Integer is_lock;
	
	private Date add_date;
	
	private String link_tab;
	
	private String openid;
	
	private Long user_id;
	
	public WeixinBindUser() {

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
	 * @val 用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 用户类别
	 */
	public Long getUser_type() {
		return user_type;
	}
	
	/**
	 * @val 用户类别
	 */
	public void setUser_type(Long user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * @val 是否删除:0正常，1已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除:0正常，1已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 是否锁定:0正常，1已锁定
	 */
	public Integer getIs_lock() {
		return is_lock;
	}
	
	/**
	 * @val 是否锁定:0正常，1已锁定
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 关联表
	 */
	public String getLink_tab() {
		return link_tab;
	}
	
	/**
	 * @val 关联表
	 */
	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}
	
	/**
	 * @val 微信ID
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * @val 微信ID
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
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
	
}