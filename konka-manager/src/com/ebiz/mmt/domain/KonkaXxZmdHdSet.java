package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-23 18:09:07
 */
public class KonkaXxZmdHdSet extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long hd_id;
	
	private String hd_title;
	
	private Long dept_id;
	
	private Date start_date;
	
	private Date end_date;
	
	private String hd_contend;
	
	public KonkaXxZmdHdSet() {

	}

	/**
	 * @val 分公司活动ID
	 */
	public Long getHd_id() {
		return hd_id;
	}
	
	/**
	 * @val 分公司活动ID
	 */
	public void setHd_id(Long hd_id) {
		this.hd_id = hd_id;
	}
	
	/**
	 * @val 活动标题
	 */
	public String getHd_title() {
		return hd_title;
	}
	
	/**
	 * @val 活动标题
	 */
	public void setHd_title(String hd_title) {
		this.hd_title = hd_title;
	}
	
	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 开始时间
	 */
	public Date getStart_date() {
		return start_date;
	}
	
	/**
	 * @val 开始时间
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
	
	/**
	 * @val 活动内容
	 */
	public String getHd_contend() {
		return hd_contend;
	}
	
	/**
	 * @val 活动内容
	 */
	public void setHd_contend(String hd_contend) {
		this.hd_contend = hd_contend;
	}
	
}