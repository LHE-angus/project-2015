package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
public class JsTimes extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Date js_date;
	
	private Long c_id;
	
	public JsTimes() {

	}

	/**
	 * @val 结算记录ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 结算记录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 结算时间
	 */
	public Date getJs_date() {
		return js_date;
	}
	
	/**
	 * @val 结算时间
	 */
	public void setJs_date(Date js_date) {
		this.js_date = js_date;
	}
	
	/**
	 * @val 客户ID
	 */
	public Long getC_id() {
		return c_id;
	}
	
	/**
	 * @val 客户ID
	 */
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	
}