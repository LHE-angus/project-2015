package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
public class EcLuckyTime extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long lucky_id;
	
	private Date start_date;
	
	private Date end_date;
	
	private Integer is_start;
	
	private Integer is_end;
	
	private Integer is_act;
	
	public Integer getIs_start() {
		return is_start;
	}

	public void setIs_start(Integer is_start) {
		this.is_start = is_start;
	}

	public Integer getIs_end() {
		return is_end;
	}

	public void setIs_end(Integer is_end) {
		this.is_end = is_end;
	}

	public Integer getIs_act() {
		return is_act;
	}

	public void setIs_act(Integer is_act) {
		this.is_act = is_act;
	}

	public EcLuckyTime() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 活动ID
	 */
	public Long getLucky_id() {
		return lucky_id;
	}
	
	/**
	 * @val 活动ID
	 */
	public void setLucky_id(Long lucky_id) {
		this.lucky_id = lucky_id;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getStart_date() {
		return start_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	/**
	 * @val 结束时间
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @val 结束时间
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
}