package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
public class KonkaMobileCustVisitDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long visit_id;
	
	private String customer_name;
	
	private String shop_name;
	
	private String r3_code;
	
	private Long shop_id;
	
	private Long customer_type;
	
	public KonkaMobileCustVisitDetail() {

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
	 * @val 客户名称
	 */
	public String getCustomer_name() {
		return customer_name;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	/**
	 * @val 门店名称
	 */
	public String getShop_name() {
		return shop_name;
	}
	
	/**
	 * @val 门店名称
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	/**
	 * @val 客户编码
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val 客户编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	/**
	 * @val 门店ID
	 */
	public Long getShop_id() {
		return shop_id;
	}
	
	/**
	 * @val 门店ID
	 */
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * @val 客户类型
	 */
	public Long getCustomer_type() {
		return customer_type;
	}
	
	/**
	 * @val 客户类型
	 */
	public void setCustomer_type(Long customer_type) {
		this.customer_type = customer_type;
	}
	
}