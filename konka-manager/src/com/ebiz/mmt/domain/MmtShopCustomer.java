package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:38
 */
public class MmtShopCustomer extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long mmt_shop;

	private Long customer;

	private Integer city_code;

	public MmtShopCustomer() {

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
	 * @val 买卖提商铺
	 */
	public Long getMmt_shop() {
		return mmt_shop;
	}

	/**
	 * @val 买卖提商铺
	 */
	public void setMmt_shop(Long mmt_shop) {
		this.mmt_shop = mmt_shop;
	}

	/**
	 * @val 消费者
	 */
	public Long getCustomer() {
		return customer;
	}

	/**
	 * @val 消费者
	 */
	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Integer getCity_code() {
		return city_code;
	}

	public void setCity_code(Integer cityCode) {
		city_code = cityCode;
	}

}