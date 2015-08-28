package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-05 12:05:18
 */
public class EcBcompPdUpNew extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long bcomp_pd_id;
	
	private Integer own_sys;
	
	private Long added_dept_id;
	
	private Date up_time;
	
	private Date down_time;
	
	private Integer is_last;
	
	private Long add_u_id;
	
	private String remarks;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Long modify_u_id;
	
	private Date modify_date;
	
	private Integer plat_sys;
	
	public EcBcompPdUpNew() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getBcomp_pd_id() {
		return bcomp_pd_id;
	}
	
	public void setBcomp_pd_id(Long bcomp_pd_id) {
		this.bcomp_pd_id = bcomp_pd_id;
	}
	
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	public Long getAdded_dept_id() {
		return added_dept_id;
	}
	
	public void setAdded_dept_id(Long added_dept_id) {
		this.added_dept_id = added_dept_id;
	}
	
	public Date getUp_time() {
		return up_time;
	}
	
	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}
	
	public Date getDown_time() {
		return down_time;
	}
	
	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}
	
	public Integer getIs_last() {
		return is_last;
	}
	
	public void setIs_last(Integer is_last) {
		this.is_last = is_last;
	}
	
	public Long getAdd_u_id() {
		return add_u_id;
	}
	
	public void setAdd_u_id(Long add_u_id) {
		this.add_u_id = add_u_id;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Integer getIs_del() {
		return is_del;
	}
	
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getModify_u_id() {
		return modify_u_id;
	}
	
	public void setModify_u_id(Long modify_u_id) {
		this.modify_u_id = modify_u_id;
	}
	
	public Date getModify_date() {
		return modify_date;
	}
	
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	
	public Integer getPlat_sys() {
		return plat_sys;
	}
	
	public void setPlat_sys(Integer plat_sys) {
		this.plat_sys = plat_sys;
	}
	
}