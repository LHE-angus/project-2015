package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 09:58:12
 */
public class StatisticalDimensionSaleArea extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long p_index;
	
	private BigDecimal gdp;
	
	private Long population;
	
	private BigDecimal area;
	
	private BigDecimal market_size;
	
	private BigDecimal sale_num1;
	
	private BigDecimal sale_num2;
	
	private Integer is_del;
	
	private Date update_date;
	
	private BigDecimal wpzb;
	
	private BigDecimal jpzb1;
	
	private BigDecimal jpzb2;
	
	private BigDecimal scjj;
	
	private BigDecimal wpjj;
	
	public StatisticalDimensionSaleArea() {

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
	 * @val 区域ID
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 区域ID
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 国民生产总值
	 */
	public BigDecimal getGdp() {
		return gdp;
	}
	
	/**
	 * @val 国民生产总值
	 */
	public void setGdp(BigDecimal gdp) {
		this.gdp = gdp;
	}
	
	/**
	 * @val 人口
	 */
	public Long getPopulation() {
		return population;
	}
	
	/**
	 * @val 人口
	 */
	public void setPopulation(Long population) {
		this.population = population;
	}
	
	/**
	 * @val 面积
	 */
	public BigDecimal getArea() {
		return area;
	}
	
	/**
	 * @val 面积
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	
	/**
	 * @val 市场容量
	 */
	public BigDecimal getMarket_size() {
		return market_size;
	}
	
	/**
	 * @val 市场容量
	 */
	public void setMarket_size(BigDecimal market_size) {
		this.market_size = market_size;
	}
	
	/**
	 * @val 竞品1销售额
	 */
	public BigDecimal getSale_num1() {
		return sale_num1;
	}
	
	/**
	 * @val 竞品1销售额
	 */
	public void setSale_num1(BigDecimal sale_num1) {
		this.sale_num1 = sale_num1;
	}
	
	/**
	 * @val 竞品2销售额
	 */
	public BigDecimal getSale_num2() {
		return sale_num2;
	}
	
	/**
	 * @val 竞品2销售额
	 */
	public void setSale_num2(BigDecimal sale_num2) {
		this.sale_num2 = sale_num2;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 更新时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 更新时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 我品占比
	 */
	public BigDecimal getWpzb() {
		return wpzb;
	}
	
	/**
	 * @val 我品占比
	 */
	public void setWpzb(BigDecimal wpzb) {
		this.wpzb = wpzb;
	}
	
	/**
	 * @val 竞品占比1
	 */
	public BigDecimal getJpzb1() {
		return jpzb1;
	}
	
	/**
	 * @val 竞品占比1
	 */
	public void setJpzb1(BigDecimal jpzb1) {
		this.jpzb1 = jpzb1;
	}
	
	/**
	 * @val 竞品占比2
	 */
	public BigDecimal getJpzb2() {
		return jpzb2;
	}
	
	/**
	 * @val 竞品占比2
	 */
	public void setJpzb2(BigDecimal jpzb2) {
		this.jpzb2 = jpzb2;
	}
	
	/**
	 * @val 市场均价
	 */
	public BigDecimal getScjj() {
		return scjj;
	}
	
	/**
	 * @val 市场均价
	 */
	public void setScjj(BigDecimal scjj) {
		this.scjj = scjj;
	}
	
	/**
	 * @val 我品均价
	 */
	public BigDecimal getWpjj() {
		return wpjj;
	}
	
	/**
	 * @val 我品均价
	 */
	public void setWpjj(BigDecimal wpjj) {
		this.wpjj = wpjj;
	}
	
}