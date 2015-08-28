package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-12-13 09:56
 */
public class KonkaOrderInfoUpdateRecord extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String trade_index;

	private Long order_id;

	private Integer update_type;

	private Long pd_type_id;

	private String pd_type_name;

	private Long brand_id;

	private String brand_name;

	private Long pd_id;

	private String pd_name;

	private Long num_before;

	private Long num_after;

	private BigDecimal price_before;

	private BigDecimal price_after;

	private Long update_user_id;

	private String update_user_name;

	private Long update_user_dept_id;

	private String update_user_dept_name;

	private Long update_role_id;

	private String update_role_name;

	private String remark;

	private Date add_date;

	private BigDecimal discount_before;

	private BigDecimal discount_after;

	private BigDecimal good_discount_after;// 修改后的折让率
	
	private BigDecimal af_discount;//修改后的折让率
	
	private BigDecimal bf_discount;//修改前的折让率
	
	private BigDecimal af_discount_price;//修改后的折让金额
	
	private BigDecimal bf_discount_price;//修改前的折让金额

	public KonkaOrderInfoUpdateRecord() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrade_index() {
		return trade_index;
	}

	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Integer getUpdate_type() {
		return update_type;
	}

	public void setUpdate_type(Integer update_type) {
		this.update_type = update_type;
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

	public Long getNum_before() {
		return num_before;
	}

	public void setNum_before(Long num_before) {
		this.num_before = num_before;
	}

	public Long getNum_after() {
		return num_after;
	}

	public void setNum_after(Long num_after) {
		this.num_after = num_after;
	}

	public BigDecimal getPrice_before() {
		return price_before;
	}

	public void setPrice_before(BigDecimal price_before) {
		this.price_before = price_before;
	}

	public BigDecimal getPrice_after() {
		return price_after;
	}

	public void setPrice_after(BigDecimal price_after) {
		this.price_after = price_after;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public String getUpdate_user_name() {
		return update_user_name;
	}

	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}

	public Long getUpdate_user_dept_id() {
		return update_user_dept_id;
	}

	public void setUpdate_user_dept_id(Long update_user_dept_id) {
		this.update_user_dept_id = update_user_dept_id;
	}

	public String getUpdate_user_dept_name() {
		return update_user_dept_name;
	}

	public void setUpdate_user_dept_name(String update_user_dept_name) {
		this.update_user_dept_name = update_user_dept_name;
	}

	public Long getUpdate_role_id() {
		return update_role_id;
	}

	public void setUpdate_role_id(Long update_role_id) {
		this.update_role_id = update_role_id;
	}

	public String getUpdate_role_name() {
		return update_role_name;
	}

	public void setUpdate_role_name(String update_role_name) {
		this.update_role_name = update_role_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public BigDecimal getDiscount_before() {
		return discount_before;
	}

	public void setDiscount_before(BigDecimal discountBefore) {
		discount_before = discountBefore;
	}

	public BigDecimal getDiscount_after() {
		return discount_after;
	}

	public void setDiscount_after(BigDecimal discountAfter) {
		discount_after = discountAfter;
	}

	public BigDecimal getGood_discount_after() {
		return good_discount_after;
	}

	public void setGood_discount_after(BigDecimal goodDiscountAfter) {
		good_discount_after = goodDiscountAfter;
	}

	public void setAf_discount(BigDecimal af_discount) {
		this.af_discount = af_discount;
	}

	public BigDecimal getAf_discount() {
		return af_discount;
	}

	public void setBf_discount(BigDecimal bf_discount) {
		this.bf_discount = bf_discount;
	}

	public BigDecimal getBf_discount() {
		return bf_discount;
	}

	public void setAf_discount_price(BigDecimal af_discount_price) {
		this.af_discount_price = af_discount_price;
	}

	public BigDecimal getAf_discount_price() {
		return af_discount_price;
	}

	public void setBf_discount_price(BigDecimal bf_discount_price) {
		this.bf_discount_price = bf_discount_price;
	}

	public BigDecimal getBf_discount_price() {
		return bf_discount_price;
	}

}