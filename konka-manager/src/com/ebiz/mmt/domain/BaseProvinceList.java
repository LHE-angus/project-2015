package com.ebiz.mmt.domain;

import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

@Deprecated
public class BaseProvinceList extends BaseDomain {

	private static final long serialVersionUID = 8094274540178677681L;

	private Long p_index;

	private String p_name;

	private String s_name;

	private Long par_index;

	private Short p_level;

	private Short alone;

	private Long root_code;

	private Short p_mag;

	private String p_code;

	private Short is_west;

	private Long order_sort;

	private Date add_date;

	private Short del_mark;

	private Date del_date;

	private String full_name;

	// private String tel_code;
	//
	// private String post_code;

	public BaseProvinceList() {
	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public Long getPar_index() {
		return par_index;
	}

	public void setPar_index(Long par_index) {
		this.par_index = par_index;
	}

	public Short getP_level() {
		return p_level;
	}

	public void setP_level(Short p_level) {
		this.p_level = p_level;
	}

	public Short getAlone() {
		return alone;
	}

	public void setAlone(Short alone) {
		this.alone = alone;
	}

	public Long getRoot_code() {
		return root_code;
	}

	public void setRoot_code(Long root_code) {
		this.root_code = root_code;
	}

	public Short getP_mag() {
		return p_mag;
	}

	public void setP_mag(Short p_mag) {
		this.p_mag = p_mag;
	}

	public String getP_code() {
		return p_code;
	}

	public void setP_code(String p_code) {
		this.p_code = p_code;
	}

	public Short getIs_west() {
		return is_west;
	}

	public void setIs_west(Short is_west) {
		this.is_west = is_west;
	}

	public Long getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(Long order_sort) {
		this.order_sort = order_sort;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Short getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Short del_mark) {
		this.del_mark = del_mark;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	// public String getTel_code() {
	// return tel_code;
	// }
	//
	// public void setTel_code(String tel_code) {
	// this.tel_code = tel_code;
	// }
	//
	// public String getPost_code() {
	// return post_code;
	// }
	//
	// public void setPost_code(String post_code) {
	// this.post_code = post_code;
	// }
}