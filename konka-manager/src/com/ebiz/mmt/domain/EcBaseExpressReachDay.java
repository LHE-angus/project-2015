package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-24 11:15:06
 */
public class EcBaseExpressReachDay extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long store_id;

	private String area_code;

	private Integer max_reach_day;

	private String desc;

	private Date add_date;

	private Integer is_del;

	public EcBaseExpressReachDay() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 仓库ID
	 */
	public Long getStore_id() {
		return store_id;
	}

	/**
	 * @val 仓库ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @val 到达地行政区划
	 */
	public String getArea_code() {
		return area_code;
	}

	/**
	 * @val 到达地行政区划
	 */
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	/**
	 * @val 最长到达天数
	 */
	public Integer getMax_reach_day() {
		return max_reach_day;
	}

	/**
	 * @val 最长到达天数
	 */
	public void setMax_reach_day(Integer max_reach_day) {
		this.max_reach_day = max_reach_day;
	}

	/**
	 * @val 描述
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @val 描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
	 * @val 删除标示
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 删除标示
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

}