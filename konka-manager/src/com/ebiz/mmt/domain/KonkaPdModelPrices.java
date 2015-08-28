package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-23 15:17:47
 */
public class KonkaPdModelPrices extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	//月份
	private Integer price_month;
	
	//型号
	private String pd_name;
	
	//现款价
	private BigDecimal cash_price;
	
	//扣点
	private BigDecimal discount;
	
	//零售指导价
	private BigDecimal sale_price;
	
	//添加日期
	private Date add_date;
	
	public KonkaPdModelPrices() {

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
	 * @val 月份
	 */
	public Integer getPrice_month() {
		return price_month;
	}
	
	/**
	 * @val 月份
	 */
	public void setPrice_month(Integer price_month) {
		this.price_month = price_month;
	}
	
	/**
	 * @val 型号名称
	 */
	public String getPd_name() {
		return pd_name;
	}
	
	/**
	 * @val 型号名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	/**
	 * @val 现款价（元）
	 */
	public BigDecimal getCash_price() {
		return cash_price;
	}
	
	/**
	 * @val 现款价（元）
	 */
	public void setCash_price(BigDecimal cash_price) {
		this.cash_price = cash_price;
	}
	
	/**
	 * @val 扣点
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	
	/**
	 * @val 扣点
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	/**
	 * @val 指导零售价（元）
	 */
	public BigDecimal getSale_price() {
		return sale_price;
	}
	
	/**
	 * @val 指导零售价（元）
	 */
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
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
	
}