package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-10-12 17:31:01
 */
public class MdasShopSales extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long shop_id;
	
	private BigDecimal shop_sales;
	
	private Integer c_year;
	
	private Integer c_month;
	
	private Date cal_time;
	
	public MdasShopSales() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 商铺ID
	 */
	public Long getShop_id() {
		return shop_id;
	}
	
	/**
	 * @val 商铺ID
	 */
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * @val 销售额(单位：万)
	 */
	public BigDecimal getShop_sales() {
		return shop_sales;
	}
	
	/**
	 * @val 销售额(单位：万)
	 */
	public void setShop_sales(BigDecimal shop_sales) {
		this.shop_sales = shop_sales;
	}
	
	/**
	 * @val 统计年
	 */
	public Integer getC_year() {
		return c_year;
	}
	
	/**
	 * @val 统计年
	 */
	public void setC_year(Integer c_year) {
		this.c_year = c_year;
	}
	
	/**
	 * @val 一年中的月序号(从0开始，0表示1月，以此类推)
	 */
	public Integer getC_month() {
		return c_month;
	}
	
	/**
	 * @val 一年中的月序号(从0开始，0表示1月，以此类推)
	 */
	public void setC_month(Integer c_month) {
		this.c_month = c_month;
	}
	
	/**
	 * @val 统计时间
	 */
	public Date getCal_time() {
		return cal_time;
	}
	
	/**
	 * @val 统计时间
	 */
	public void setCal_time(Date cal_time) {
		this.cal_time = cal_time;
	}
	
}