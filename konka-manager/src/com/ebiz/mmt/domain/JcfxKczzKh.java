package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:24
 */
public class JcfxKczzKh extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String coustmer_name;
	
	private Long cust_id;
	
	private String r3_code;
	
	private Integer is_del;
	
	private Date add_date;
	
	private String remark;
	
	private Long add_user_id;
	
	private String add_user_name;
	
	public JcfxKczzKh() {

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
	 * @val 客户名称
	 */
	public String getCoustmer_name() {
		return coustmer_name;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setCoustmer_name(String coustmer_name) {
		this.coustmer_name = coustmer_name;
	}
	
	/**
	 * @val 客户ID
	 */
	public Long getCust_id() {
		return cust_id;
	}
	
	/**
	 * @val 客户ID
	 */
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	
	/**
	 * @val R3_CODE
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val R3_CODE
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
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
	 * @val 添加人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
}