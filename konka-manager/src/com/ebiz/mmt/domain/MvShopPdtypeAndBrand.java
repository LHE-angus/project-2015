package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-19 08:50:52
 */
public class MvShopPdtypeAndBrand extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long shop_id;
	
	private Long pd_type;
	
	private Long cls_id;
	
	private Long brand_id;
	
	public MvShopPdtypeAndBrand() {

	}

	public Long getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
	public Long getPd_type() {
		return pd_type;
	}
	
	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}
	
	public Long getCls_id() {
		return cls_id;
	}
	
	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}
	
	public Long getBrand_id() {
		return brand_id;
	}
	
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
}