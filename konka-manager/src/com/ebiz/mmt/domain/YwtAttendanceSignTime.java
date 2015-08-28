package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 09:41:09
 */
public class YwtAttendanceSignTime extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Date am_sign_in_date;
	
	private Date am_sign_out_date;
	
	private Date pm_sign_in_date;
	
	private Date pm_sign_out_date;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Long user_id;
	
	private String user_name;
	
	private Long dept_id;
	
	private String dept_name;
	
	private Date am_center_pm;
	
	public YwtAttendanceSignTime() {

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
	 * @val 签到时间
	 */
	public Date getAm_sign_in_date() {
		return am_sign_in_date;
	}
	
	/**
	 * @val 签到时间
	 */
	public void setAm_sign_in_date(Date am_sign_in_date) {
		this.am_sign_in_date = am_sign_in_date;
	}
	
	/**
	 * @val 签退时间
	 */
	public Date getAm_sign_out_date() {
		return am_sign_out_date;
	}
	
	/**
	 * @val 签退时间
	 */
	public void setAm_sign_out_date(Date am_sign_out_date) {
		this.am_sign_out_date = am_sign_out_date;
	}
	
	/**
	 * @val 签到时间
	 */
	public Date getPm_sign_in_date() {
		return pm_sign_in_date;
	}
	
	/**
	 * @val 签到时间
	 */
	public void setPm_sign_in_date(Date pm_sign_in_date) {
		this.pm_sign_in_date = pm_sign_in_date;
	}
	
	/**
	 * @val 签退时间
	 */
	public Date getPm_sign_out_date() {
		return pm_sign_out_date;
	}
	
	/**
	 * @val 签退时间
	 */
	public void setPm_sign_out_date(Date pm_sign_out_date) {
		this.pm_sign_out_date = pm_sign_out_date;
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
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 分公司id
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司id
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	/**
	 * @val 分公司名称
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * @val 分公司名称
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	/**
	 * @val 上午下班和下午上班的中间时间
	 */
	public Date getAm_center_pm() {
		return am_center_pm;
	}
	
	/**
	 * @val 上午下班和下午上班的中间时间
	 */
	public void setAm_center_pm(Date am_center_pm) {
		this.am_center_pm = am_center_pm;
	}
	
}