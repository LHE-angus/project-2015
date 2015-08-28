package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
public class JsStocks extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long store_id;

	private String md_name;

	private Long c_id;

	private Long stocks;

	private BigDecimal cost;

	private BigDecimal total_cost;

	private Integer buy_type;

	private Date add_date;

	private Long link_id;

	private String link_tab;

	private Long num;

	private BigDecimal money;

	public JsStocks() {

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

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	/**
	 * @val 客户ID
	 */
	public Long getC_id() {
		return c_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
	}

	public Integer getBuy_type() {
		return buy_type;
	}

	public void setBuy_type(Integer buy_type) {
		this.buy_type = buy_type;
	}

	/**
	 * @val 入库时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 入库时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getLink_id() {
		return link_id;
	}

	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	public String getLink_tab() {
		return link_tab;
	}

	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}