package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
public class YwtAttendanceDay extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Date sign_in_date;
	
	private Date sign_out_date;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Long user_id;
	
	private String real_name;
	
	private String memo;
	
	private Long is_lock;
	
	public YwtAttendanceDay() {

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
	 * @val 休息开始时间
	 */
	public Date getSign_in_date() {
		return sign_in_date;
	}
	
	/**
	 * @val 休息开始时间
	 */
	public void setSign_in_date(Date sign_in_date) {
		this.sign_in_date = sign_in_date;
	}
	
	/**
	 * @val 休息结束时间
	 */
	public Date getSign_out_date() {
		return sign_out_date;
	}
	
	/**
	 * @val 休息结束时间
	 */
	public void setSign_out_date(Date sign_out_date) {
		this.sign_out_date = sign_out_date;
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
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 人员
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 人员
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 人员姓名
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * @val 人员姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * @val 是否启用 0 停用 1启用
	 */
	public Long getIs_lock() {
		return is_lock;
	}
	
	/**
	 * @val 是否启用 0 停用 1启用
	 */
	public void setIs_lock(Long is_lock) {
		this.is_lock = is_lock;
	}
	
}