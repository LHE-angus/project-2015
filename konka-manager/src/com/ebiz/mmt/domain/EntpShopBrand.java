package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xing, XiuDong
 */
public class EntpShopBrand extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long entp_id;

	private Long shop_id;

	private String pd_type_sign;

	private Long brand_id;

	private String shop_name;

	private String entp_name;

	private String brand_name;

	private Date add_date;

	private Integer order_value;

	private Short is_del;

	public EntpShopBrand() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntp_id() {
		return entp_id;
	}

	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public String getPd_type_sign() {
		return pd_type_sign;
	}

	public void setPd_type_sign(String pdTypeSign) {
		pd_type_sign = pdTypeSign;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getEntp_name() {
		return entp_name;
	}

	public void setEntp_name(String entp_name) {
		this.entp_name = entp_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public Short getIs_del() {
		return is_del;
	}

	public void setIs_del(Short is_del) {
		this.is_del = is_del;
	}

}