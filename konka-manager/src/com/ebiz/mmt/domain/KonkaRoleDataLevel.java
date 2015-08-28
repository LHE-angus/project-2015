package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-02 16:28:47
 */
public class KonkaRoleDataLevel extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long role_id;
	
	private Long dept_id;
	
	private String role_name;
	
	private String dept_name;
	
	private Date add_date;
	
	private Integer is_del;
	
	public KonkaRoleDataLevel() {

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
	 * @val 可视部门ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 可视部门ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 角色
	 */
	public String getRole_name() {
		return role_name;
	}
	
	/**
	 * @val 角色
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	/**
	 * @val 部门
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * @val 部门
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	
}