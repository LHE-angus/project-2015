package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmProjCompet extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long proj_id;
	
	private String proj_code;
	
	private String compet_model;
	
	private String compet_price;
	
	private String compet_memo;
	
	private String brand_name;
	
	private Long brand_id;
	
	public GcxmProjCompet() {

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
	 * @val 项目ID
	 */
	public Long getProj_id() {
		return proj_id;
	}
	
	/**
	 * @val 项目ID
	 */
	public void setProj_id(Long proj_id) {
		this.proj_id = proj_id;
	}
	
	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public String getProj_code() {
		return proj_code;
	}
	
	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}
	
	/**
	 * @val 竞品机型
	 */
	public String getCompet_model() {
		return compet_model;
	}
	
	/**
	 * @val 竞品机型
	 */
	public void setCompet_model(String compet_model) {
		this.compet_model = compet_model;
	}
	
	/**
	 * @val 竞品报价
	 */
	public String getCompet_price() {
		return compet_price;
	}
	
	/**
	 * @val 竞品报价
	 */
	public void setCompet_price(String compet_price) {
		this.compet_price = compet_price;
	}
	
	/**
	 * @val 说明
	 */
	public String getCompet_memo() {
		return compet_memo;
	}
	
	/**
	 * @val 说明
	 */
	public void setCompet_memo(String compet_memo) {
		this.compet_memo = compet_memo;
	}
	
	/**
	 * @val 品牌
	 */
	public String getBrand_name() {
		return brand_name;
	}
	
	/**
	 * @val 品牌
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	/**
	 * @val 品牌ID
	 */
	public Long getBrand_id() {
		return brand_id;
	}
	
	/**
	 * @val 品牌ID
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
}