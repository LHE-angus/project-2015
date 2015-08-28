package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:42
 */
public class PeRoleInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long role_id;

	private String role_name;

	private String role_desc;

	private Long entp_id;

	private Long add_e_user_id;

	private Date add_date;
	
	private Long dept_id;
	
	private String dept_sn;

	private Integer is_del;
	
	// 数据级别
	private Integer d_level;

	private List<KonkaRoleDataLevel> kLevelLsit;
	
	public PeRoleInfo() {

	}

	/**
	 * @val 角色ID
	 */
	public Long getRole_id() {
		return role_id;
	}

	/**
	 * @val 角色ID
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	/**
	 * @val 角色名称
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @val 角色名称
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	/**
	 * @val 生产企业ID：ENTP_PROD.ENTP_ID
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 生产企业ID：ENTP_PROD.ENTP_ID
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
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司编码
	 */
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
	}

	/**
	 * @val 分公司编码
	 */
	public String getDept_sn() {
		return dept_sn;
	}

	/**
	 * @val 角色对应的数据权限
	 */
	public void setkLevelLsit(List<KonkaRoleDataLevel> kLevelLsit) {
		this.kLevelLsit = kLevelLsit;
	}

	/**
	 * @val 角色对应的数据权限
	 */
	public List<KonkaRoleDataLevel> getkLevelLsit() {
		return kLevelLsit;
	}

	public Integer getD_level() {
		return d_level;
	}

	public void setD_level(Integer d_level) {
		this.d_level = d_level;
	}
}