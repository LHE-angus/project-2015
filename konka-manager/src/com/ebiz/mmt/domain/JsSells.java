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
public class JsSells extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long sell_count;

	private BigDecimal sell_money;

	private BigDecimal sell_price;

	private Date sell_date;

	private BigDecimal total_cost;

	private Long c_id;

	private String md_name;

	private Integer sell_type;

	private Long link_id;

	private String link_tab;

	private Long s_id;

	public JsSells() {

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

	public Long getSell_count() {
		return sell_count;
	}

	public void setSell_count(Long sell_count) {
		this.sell_count = sell_count;
	}

	public BigDecimal getSell_money() {
		return sell_money;
	}

	public void setSell_money(BigDecimal sell_money) {
		this.sell_money = sell_money;
	}

	public BigDecimal getSell_price() {
		return sell_price;
	}

	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}

	/**
	 * @val 销售时间
	 */
	public Date getSell_date() {
		return sell_date;
	}

	/**
	 * @val 销售时间
	 */
	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public BigDecimal getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
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

	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public Integer getSell_type() {
		return sell_type;
	}

	public void setSell_type(Integer sell_type) {
		this.sell_type = sell_type;
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

	public Long getS_id() {
		return s_id;
	}

	public void setS_id(Long s_id) {
		this.s_id = s_id;
	}

}