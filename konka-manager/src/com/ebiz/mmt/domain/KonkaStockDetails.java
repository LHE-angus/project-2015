package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
public class KonkaStockDetails extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String r3_code;
	
	private Long pd_id;
	
	private Long stock_count;

	private BigDecimal stock_cost;
	
	private Long regulation;
	
	/**
	 * @val 当前库存成本
	 */
	private BigDecimal current_cost;
	
	/**
	 * @val 当前库存数量
	 */
	private Long current_count;
	
	/**
	 * @val 初始库存日期
	 */
	private Date stock_date;
	

	public KonkaStockDetails() {

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
	 * @val r3_code
	 */
	public String getR3_code() {
		return r3_code;
	}
	/**
	 * @val r3_code
	 */
	public void setR3_code(String r3Code) {
		r3_code = r3Code;
	}
	/**
	 * @val 库存成本
	 */
	public BigDecimal getStock_cost() {
		return stock_cost;
	}
	/**
	 * @val 库存成本
	 */
	public void setStock_cost(BigDecimal stockCost) {
		stock_cost = stockCost;
	}

	/**
	 * @val 产品型号ID
	 */
	public Long getPd_id() {
		return pd_id;
	}
	
	/**
	 * @val 产品型号ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
	/**
	 * @val 库存数量
	 */
	public Long getStock_count() {
		return stock_count;
	}
	
	/**
	 * @val 库存数量
	 */
	public void setStock_count(Long stock_count) {
		this.stock_count = stock_count;
	}
	
	/**
	 * @val 盘盈盘亏数
	 */
	public Long getRegulation() {
		return regulation;
	}
	/**
	 * @val 盘盈盘亏数
	 */
	public void setRegulation(Long regulation) {
		this.regulation = regulation;
	}
	/**
	 * @val 当前库存成本
	 */
	public BigDecimal getCurrent_cost() {
		return current_cost;
	}
	
	public void setCurrent_cost(BigDecimal currentCost) {
		current_cost = currentCost;
	}
	/**
	 * @val 当前库存数量
	 */
	public Long getCurrent_count() {
		return current_count;
	}
	public void setCurrent_count(Long currentCount) {
		current_count = currentCount;
	}

	public Date getStock_date() {
		return stock_date;
	}

	public void setStock_date(Date stockDate) {
		stock_date = stockDate;
	}
	
}