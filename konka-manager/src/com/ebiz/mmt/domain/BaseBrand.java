package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,Huan
 * @author Yao,QianCheng
 * @author Xing,XiuDong: Add pd_type_name field.
 */
public class BaseBrand extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long brand_id;

	private String brand_name;

	private String pd_type_name;

	private Integer order_sort;

	private Integer del_mark;

	private Date add_date;

	private Date del_date;

	private Long pd_type;

	private String pd_name;

	public BaseBrand() {

	}

	public BaseBrand(Long brand_id) {
		this.brand_id = brand_id;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Integer getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(Integer order_sort) {
		this.order_sort = order_sort;
	}

	public Integer getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public String getPd_type_name() {
		return pd_type_name;
	}

	public void setPd_type_name(String pdTypeName) {
		pd_type_name = pdTypeName;
	}

}