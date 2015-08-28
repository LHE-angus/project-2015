package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public class PeDept2 extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long dept_id;

	private Long par_id;

	private String dept_name;

	private String dept_desc;

	private Long dept_leader;

	private Long entp_id;

	private Long add_e_user_id;

	private Date add_date;

	private Integer is_del;

	private Long p_index;
	
	private Long pd_id;

	public PeDept2() {

	}

	/**
	 * @val 部门ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 部门ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 父部门ID
	 */
	public Long getPar_id() {
		return par_id;
	}

	/**
	 * @val 父部门ID
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	/**
	 * @val 部门名称
	 */
	public String getDept_name() {
		return dept_name;
	}

	/**
	 * @val 部门名称
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	/**
	 * @val 部门描述
	 */
	public String getDept_desc() {
		return dept_desc;
	}

	/**
	 * @val 部门描述
	 */
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}

	/**
	 * @val 部门负责人ID
	 */
	public Long getDept_leader() {
		return dept_leader;
	}

	/**
	 * @val 部门负责人ID
	 */
	public void setDept_leader(Long dept_leader) {
		this.dept_leader = dept_leader;
	}

	/**
	 * @val 生产企业ID:ENTP_PROD.ENTP_ID
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 生产企业ID:ENTP_PROD.ENTP_ID
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public Long getAdd_e_user_id() {
		return add_e_user_id;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public void setAdd_e_user_id(Long add_e_user_id) {
		this.add_e_user_id = add_e_user_id;
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

	/**
	 * @val 部门所在地
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 部门所在地
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 产品id
	 */
	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
}