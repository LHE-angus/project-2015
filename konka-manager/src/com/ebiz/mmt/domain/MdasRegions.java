package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
public class MdasRegions extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String r_name;

	private Integer order_value;

	private Date add_date;

	private Long add_user_id;

	private Integer is_del;

	private Date del_date;

	private List<MdasRegionsProvince> rgProvinceList;

	public MdasRegions() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
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
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
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

	/**
	 * @val 删除时间
	 */
	public Date getDel_date() {
		return del_date;
	}

	/**
	 * @val 删除时间
	 */
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public List<MdasRegionsProvince> getRgProvinceList() {
		return rgProvinceList;
	}

	public void setRgProvinceList(List<MdasRegionsProvince> rgProvinceList) {
		this.rgProvinceList = rgProvinceList;
	}

}