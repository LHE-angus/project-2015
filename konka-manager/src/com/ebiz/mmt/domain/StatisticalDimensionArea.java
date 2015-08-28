package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public class StatisticalDimensionArea extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long p_index;
	
	private Long town_id;
	
	private String town_name;
	
	private Long county_id;
	
	private String county_name;
	
	private Long city_id;
	
	private String city_name;
	
	private Long province_id;
	
	private String province_name;
	
	private String region_name;
	
	private Integer is_del;
	
	private Date update_date;
	
	public StatisticalDimensionArea() {

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
	 * @val 区域ID
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 区域ID
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 乡镇ID
	 */
	public Long getTown_id() {
		return town_id;
	}
	
	/**
	 * @val 乡镇ID
	 */
	public void setTown_id(Long town_id) {
		this.town_id = town_id;
	}
	
	/**
	 * @val 乡镇名
	 */
	public String getTown_name() {
		return town_name;
	}
	
	/**
	 * @val 乡镇名
	 */
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	
	/**
	 * @val 县ID
	 */
	public Long getCounty_id() {
		return county_id;
	}
	
	/**
	 * @val 县ID
	 */
	public void setCounty_id(Long county_id) {
		this.county_id = county_id;
	}
	
	/**
	 * @val 县名
	 */
	public String getCounty_name() {
		return county_name;
	}
	
	/**
	 * @val 县名
	 */
	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}
	
	/**
	 * @val 市ID
	 */
	public Long getCity_id() {
		return city_id;
	}
	
	/**
	 * @val 市ID
	 */
	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
	
	/**
	 * @val 市名
	 */
	public String getCity_name() {
		return city_name;
	}
	
	/**
	 * @val 市名
	 */
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	/**
	 * @val 省ID
	 */
	public Long getProvince_id() {
		return province_id;
	}
	
	/**
	 * @val 省ID
	 */
	public void setProvince_id(Long province_id) {
		this.province_id = province_id;
	}
	
	/**
	 * @val 省名
	 */
	public String getProvince_name() {
		return province_name;
	}
	
	/**
	 * @val 省名
	 */
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	
	/**
	 * @val 大区名
	 */
	public String getRegion_name() {
		return region_name;
	}
	
	/**
	 * @val 大区名
	 */
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 更新时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 更新时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
}