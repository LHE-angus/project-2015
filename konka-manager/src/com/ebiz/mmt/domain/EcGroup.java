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
public class EcGroup extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long par_id;
	
	private String group_name;
	
	private String full_group_name;
	
	private Long link_dept_id;
	
	private Integer del_mark;
	
	private String remark;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public EcGroup() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPar_id() {
		return par_id;
	}
	
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	public String getGroup_name() {
		return group_name;
	}
	
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	public String getFull_group_name() {
		return full_group_name;
	}
	
	public void setFull_group_name(String full_group_name) {
		this.full_group_name = full_group_name;
	}
	
	public Long getLink_dept_id() {
		return link_dept_id;
	}
	
	public void setLink_dept_id(Long link_dept_id) {
		this.link_dept_id = link_dept_id;
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