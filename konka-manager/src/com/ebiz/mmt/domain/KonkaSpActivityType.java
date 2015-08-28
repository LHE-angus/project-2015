package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-23 11:36:10
 */
public class KonkaSpActivityType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String hd_type;

	private Long p_type;
	
	private String hd_type_sn;
	
	private Date s_date;
	
	private Date e_date;
	
	private BigDecimal money;
	
	private String add_user;
	
	private String add_user_job_id;
	
	private Date add_date;
	
	private String update_user;
	
	private String update_user_job_id;
	
	private Date update_date;
	
	private Integer is_del;
	
	public KonkaSpActivityType() {

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
	 * @val 活动类型
	 */
	public String getHd_type() {
		return hd_type;
	}
	
	/**
	 * @val 活动类型
	 */
	public void setHd_type(String hd_type) {
		this.hd_type = hd_type;
	}
	
	/**
	 * @val 活动类型编码
	 */
	public String getHd_type_sn() {
		return hd_type_sn;
	}
	
	/**
	 * @val 活动类型编码
	 */
	public void setHd_type_sn(String hd_type_sn) {
		this.hd_type_sn = hd_type_sn;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public Date getS_date() {
		return s_date;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public Date getE_date() {
		return e_date;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	
	/**
	 * @val 活动达标金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * @val 活动达标金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	/**
	 * @val 添加人
	 */
	public String getAdd_user() {
		return add_user;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user(String add_user) {
		this.add_user = add_user;
	}
	
	/**
	 * @val 添加人岗位ID
	 */
	public String getAdd_user_job_id() {
		return add_user_job_id;
	}
	
	/**
	 * @val 添加人岗位ID
	 */
	public void setAdd_user_job_id(String add_user_job_id) {
		this.add_user_job_id = add_user_job_id;
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
	 * @val 修改人
	 */
	public String getUpdate_user() {
		return update_user;
	}
	
	/**
	 * @val 修改人
	 */
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	
	/**
	 * @val 修改人岗位ID
	 */
	public String getUpdate_user_job_id() {
		return update_user_job_id;
	}
	
	/**
	 * @val 修改人岗位ID
	 */
	public void setUpdate_user_job_id(String update_user_job_id) {
		this.update_user_job_id = update_user_job_id;
	}
	
	/**
	 * @val 修改时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 修改时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 是否删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Long getP_type() {
		return p_type;
	}

	public void setP_type(Long p_type) {
		this.p_type = p_type;
	}
	
}