package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public class JStocks extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long store_id;
	
	private Long goods_id;
	
	private Long stocks;
	
	private BigDecimal total_cost;
	
	public JStocks() {

	}

	/**
	 * @val 仓库ID
	 */
	public Long getStore_id() {
		return store_id;
	}
	
	/**
	 * @val 仓库ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 库存数量
	 */
	public Long getStocks() {
		return stocks;
	}
	
	/**
	 * @val 库存数量
	 */
	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}
	
	/**
	 * @val 实时累计成本（元）
	 */
	public BigDecimal getTotal_cost() {
		return total_cost;
	}
	
	/**
	 * @val 实时累计成本（元）
	 */
	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
	}
	
}