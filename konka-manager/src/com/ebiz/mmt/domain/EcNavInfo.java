package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 15:10:42
 */
public class EcNavInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String title;

	private String logo_url;

	private Integer own_sys;

	private Integer plat_sys;

	private Long dept_id;

	private Date add_date;

	private Long add_user_id;

	private Date update_date;

	private String remark;

	private Integer del_mark;

	private Integer is_show;

	private Integer order_value;

	public EcNavInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public Integer getOwn_sys() {
		return own_sys;
	}

	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	public Integer getPlat_sys() {
		return plat_sys;
	}

	public void setPlat_sys(Integer plat_sys) {
		this.plat_sys = plat_sys;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
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

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}

	public Integer getIs_show() {
		return is_show;
	}

	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer orderValue) {
		order_value = orderValue;
	}

}