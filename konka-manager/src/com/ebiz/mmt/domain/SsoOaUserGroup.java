package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 15:46:32
 */
public class SsoOaUserGroup extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String group1;
	
	private String group2;
	
	private String group3;
	
	private String group4;
	
	private String name;
	
	private String display_name;
	
	private Date add_date;
	
	private Date update_date;
	
	private String samaccount_name;
	
	private String samaccount_pwd;
	
	private String remark;
	
	private Integer order_value;
	
	private String distinguishedname;
	
	public SsoOaUserGroup() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 组织架构1
	 */
	public String getGroup1() {
		return group1;
	}
	
	/**
	 * @val 组织架构1
	 */
	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	
	/**
	 * @val 组织架构2
	 */
	public String getGroup2() {
		return group2;
	}
	
	/**
	 * @val 组织架构2
	 */
	public void setGroup2(String group2) {
		this.group2 = group2;
	}
	
	/**
	 * @val 组织架构3
	 */
	public String getGroup3() {
		return group3;
	}
	
	/**
	 * @val 组织架构3
	 */
	public void setGroup3(String group3) {
		this.group3 = group3;
	}
	
	/**
	 * @val 组织架构3
	 */
	public String getGroup4() {
		return group4;
	}
	
	/**
	 * @val 组织架构3
	 */
	public void setGroup4(String group4) {
		this.group4 = group4;
	}
	
	/**
	 * @val 姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @val 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @val 显示名
	 */
	public String getDisplay_name() {
		return display_name;
	}
	
	/**
	 * @val 显示名
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
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
	 * @val 更新时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 更新时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 账号
	 */
	public String getSamaccount_name() {
		return samaccount_name;
	}
	
	/**
	 * @val 账号
	 */
	public void setSamaccount_name(String samaccount_name) {
		this.samaccount_name = samaccount_name;
	}
	
	/**
	 * @val 密码
	 */
	public String getSamaccount_pwd() {
		return samaccount_pwd;
	}
	
	/**
	 * @val 密码
	 */
	public void setSamaccount_pwd(String samaccount_pwd) {
		this.samaccount_pwd = samaccount_pwd;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val DISTINGUISHEDNAME
	 */
	public String getDistinguishedname() {
		return distinguishedname;
	}
	
	/**
	 * @val DISTINGUISHEDNAME
	 */
	public void setDistinguishedname(String distinguishedname) {
		this.distinguishedname = distinguishedname;
	}
	
}