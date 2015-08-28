package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-08 14:09:38
 */
public class EcCust extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long group_id;
	
	private String cust_name;
	
	private String cust_code;
	
	private String r3_code;
	
	private Integer cust_type;
	
	private Long user_id;
	
	private Long dept_id;
	
	private Integer del_mark;
	
	private String remark;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public EcCust() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getGroup_id() {
		return group_id;
	}
	
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}
	
	public String getCust_name() {
		return cust_name;
	}
	
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	public String getCust_code() {
		return cust_code;
	}
	
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	
	public String getR3_code() {
		return r3_code;
	}
	
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	public Integer getCust_type() {
		return cust_type;
	}
	
	public void setCust_type(Integer cust_type) {
		this.cust_type = cust_type;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public Integer getDel_mark() {
		return del_mark;
	}
	
	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
}