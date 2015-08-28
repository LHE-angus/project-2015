package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xu,XiaoYuan
 * @version 2012-02-15 10:24
 */
public class KonkaOrderInfoDetailsAudit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long order_id;
	
	private Long pd_type_id;
	
	private String pd_type_name;
	
	private Long brand_id;
	
	private String brand_name;
	
	private Long pd_id;
	
	private String pd_name;
	
	private Integer good_state;
	
	private Integer audit_good_count;
	
	private BigDecimal audit_good_price;
	
	private String good_unit;
	
	private BigDecimal good_sum_price;
	
	private BigDecimal good_discount;
	
	private BigDecimal good_discount_price;
	
	private Long integral;
	
	private Integer is_del;
	
	public KonkaOrderInfoDetailsAudit() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	public Long getPd_type_id() {
		return pd_type_id;
	}

	public void setPd_type_id(Long pd_type_id) {
		this.pd_type_id = pd_type_id;
	}
	
	public String getPd_type_name() {
		return pd_type_name;
	}

	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
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
	
	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	public Integer getGood_state() {
		return good_state;
	}

	public void setGood_state(Integer good_state) {
		this.good_state = good_state;
	}
	
	public Integer getAudit_good_count() {
		return audit_good_count;
	}

	public void setAudit_good_count(Integer audit_good_count) {
		this.audit_good_count = audit_good_count;
	}
	
	public BigDecimal getAudit_good_price() {
		return audit_good_price;
	}

	public void setAudit_good_price(BigDecimal audit_good_price) {
		this.audit_good_price = audit_good_price;
	}
	
	public String getGood_unit() {
		return good_unit;
	}

	public void setGood_unit(String good_unit) {
		this.good_unit = good_unit;
	}
	
	public BigDecimal getGood_sum_price() {
		return good_sum_price;
	}

	public void setGood_sum_price(BigDecimal good_sum_price) {
		this.good_sum_price = good_sum_price;
	}
	
	public BigDecimal getGood_discount() {
		return good_discount;
	}

	public void setGood_discount(BigDecimal good_discount) {
		this.good_discount = good_discount;
	}
	
	public BigDecimal getGood_discount_price() {
		return good_discount_price;
	}

	public void setGood_discount_price(BigDecimal good_discount_price) {
		this.good_discount_price = good_discount_price;
	}
	
	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}
	
	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}