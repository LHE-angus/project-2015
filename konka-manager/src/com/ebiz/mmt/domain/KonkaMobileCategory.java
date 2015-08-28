package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */
public class KonkaMobileCategory extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long c_index;

	private String c_name;

	private String c_ename;

	/**
	 * 1 项目类型<br />
	 */
	private Integer c_type;

	private String c_comm;

	private Integer c_level;

	private String par_index;

	private Long order_sort;

	private Integer order_value;

	private Integer is_del;
	
	private Integer is_type;
	
	private Integer is_lock;
	
	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}

	public Integer getIs_type() {
		return is_type;
	}

	public void setIs_type(Integer is_type) {
		this.is_type = is_type;
	}

	public KonkaMobileCategory() {

	}

	public void setC_index(Long c_index) {
		this.c_index = c_index;
	}

	public Long getC_index() {
		return this.c_index;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_name() {
		return this.c_name;
	}

	public void setC_ename(String c_ename) {
		this.c_ename = c_ename;
	}

	public String getC_ename() {
		return this.c_ename;
	}

	public void setC_type(Integer c_type) {
		this.c_type = c_type;
	}

	public Integer getC_type() {
		return this.c_type;
	}

	public void setC_comm(String c_comm) {
		this.c_comm = c_comm;
	}

	public String getC_comm() {
		return this.c_comm;
	}

	public void setC_level(Integer c_level) {
		this.c_level = c_level;
	}

	public Integer getC_level() {
		return this.c_level;
	}

	public void setPar_index(String par_index) {
		this.par_index = par_index;
	}

	public String getPar_index() {
		return this.par_index;
	}

	public void setOrder_sort(Long order_sort) {
		this.order_sort = order_sort;
	}

	public Long getOrder_sort() {
		return this.order_sort;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Integer getOrder_value() {
		return this.order_value;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Integer getIs_del() {
		return this.is_del;
	}


}