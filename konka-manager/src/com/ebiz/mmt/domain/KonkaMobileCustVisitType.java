package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
public class KonkaMobileCustVisitType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long visit_id;
	
	private Integer visit_type_id;
	
	private Date add_date;
	
	private String visit_type_name;
	
	public KonkaMobileCustVisitType() {

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
	 * @val VISIT_ID
	 */
	public Long getVisit_id() {
		return visit_id;
	}
	
	/**
	 * @val VISIT_ID
	 */
	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
	}
	
	/**
	 * @val 拜访类型
	 */
	public Integer getVisit_type_id() {
		return visit_type_id;
	}
	
	/**
	 * @val 拜访类型
	 */
	public void setVisit_type_id(Integer visit_type_id) {
		this.visit_type_id = visit_type_id;
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
	 * @val 拜访类型名称
	 */
	public String getVisit_type_name() {
		return visit_type_name;
	}
	
	/**
	 * @val 拜访类型名称
	 */
	public void setVisit_type_name(String visit_type_name) {
		this.visit_type_name = visit_type_name;
	}
	
}