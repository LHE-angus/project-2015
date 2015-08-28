package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-13 11:37:04
 */
public class MvClsidJoinBrandXxhx extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long cls_id;
	
	private String cls_name;
	
	private Long brand_id;
	
	private String brand_name;
	
	public MvClsidJoinBrandXxhx() {

	}

	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}
	
	public String getCls_name() {
		return cls_name;
	}

	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}
	
	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
}