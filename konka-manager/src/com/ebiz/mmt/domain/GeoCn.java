package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-07-22 21:13:44
 */
public class GeoCn extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long p_index;

	private Long par_index;

	private String p_name;

	private String p_names;

	private String lat;

	private String lng;

	private Date add_date;

	private Integer is_del;

	public GeoCn() {

	}

	/**
	 * @val 地区代码
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 地区代码
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 父地区代码
	 */
	public Long getPar_index() {
		return par_index;
	}

	/**
	 * @val 父地区代码
	 */
	public void setPar_index(Long par_index) {
		this.par_index = par_index;
	}

	/**
	 * @val 地区名称
	 */
	public String getP_name() {
		return p_name;
	}

	/**
	 * @val 地区名称
	 */
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	/**
	 * @val 地区全称
	 */
	public String getP_names() {
		return p_names;
	}

	/**
	 * @val 地区全称
	 */
	public void setP_names(String p_names) {
		this.p_names = p_names;
	}

	/**
	 * @val 纬度
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @val 纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @val 经度
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @val 经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
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
	 * @val 是否删除，1已删0未删
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除，1已删0未删
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

}