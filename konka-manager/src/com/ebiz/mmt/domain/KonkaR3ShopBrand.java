package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class KonkaR3ShopBrand extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long r3_shop_id;
	
	private Long brand_id;
	
	private Long sale_year;
	
	private BigDecimal annual_salse;
	
	private Integer ranks;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Long order_value;
	
	public KonkaR3ShopBrand() {

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
	 * @val 客户ID
	 */
	public Long getR3_shop_id() {
		return r3_shop_id;
	}
	
	/**
	 * @val 客户ID
	 */
	public void setR3_shop_id(Long r3_shop_id) {
		this.r3_shop_id = r3_shop_id;
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
	
	/**
	 * @val 销售年份
	 */
	public Long getSale_year() {
		return sale_year;
	}
	
	/**
	 * @val 销售年份
	 */
	public void setSale_year(Long sale_year) {
		this.sale_year = sale_year;
	}
	
	/**
	 * @val 年销售额
	 */
	public BigDecimal getAnnual_salse() {
		return annual_salse;
	}
	
	/**
	 * @val 年销售额
	 */
	public void setAnnual_salse(BigDecimal annual_salse) {
		this.annual_salse = annual_salse;
	}
	
	/**
	 * @val 排名
	 */
	public Integer getRanks() {
		return ranks;
	}
	
	/**
	 * @val 排名
	 */
	public void setRanks(Integer ranks) {
		this.ranks = ranks;
	}
	
	/**
	 * @val 是否删除 0-未删 1-已删
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除 0-未删 1-已删
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
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
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public Long getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}
	
}