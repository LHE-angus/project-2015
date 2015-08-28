package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-22 14:44:57
 */
public class PePdSellarea extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long pe_pd_id;
	
	private Long p_index;
	
	public PePdSellarea() {

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
	 * @val 关联产品ID
	 */
	public Long getPe_pd_id() {
		return pe_pd_id;
	}
	
	/**
	 * @val 关联产品ID
	 */
	public void setPe_pd_id(Long pe_pd_id) {
		this.pe_pd_id = pe_pd_id;
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
	
}