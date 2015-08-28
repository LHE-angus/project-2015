package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-20 10:28:34
 */
public class JBaseGoodsInitStock extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long goods_id;

	private Long init_count;

	private BigDecimal buy_price;

	private BigDecimal sell_price;

	private Long c_id;

	private Long store_id;

	private Date init_date;

	private Long init_user;

	private Integer init_state;

	private String init_desc;

	private BigDecimal init_money;

	public JBaseGoodsInitStock() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	 * @val 初始库存
	 */
	public Long getInit_count() {
		return init_count;
	}

	/**
	 * @val 初始库存
	 */
	public void setInit_count(Long init_count) {
		this.init_count = init_count;
	}

	/**
	 * @val 进货单价（元）
	 */
	public BigDecimal getBuy_price() {
		return buy_price;
	}

	/**
	 * @val 进货单价（元）
	 */
	public void setBuy_price(BigDecimal buy_price) {
		this.buy_price = buy_price;
	}

	/**
	 * @val 销售单价（元）
	 */
	public BigDecimal getSell_price() {
		return sell_price;
	}

	/**
	 * @val 销售单价（元）
	 */
	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
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

	/**
	 * @val 仓库ID
	 * @val
	 */
	public Long getStore_id() {
		return store_id;
	}

	/**
	 * @val 仓库ID
	 * @val
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @val 初始化时间
	 */
	public Date getInit_date() {
		return init_date;
	}

	/**
	 * @val 初始化时间
	 */
	public void setInit_date(Date init_date) {
		this.init_date = init_date;
	}

	/**
	 * @val 初始化人
	 */
	public Long getInit_user() {
		return init_user;
	}

	/**
	 * @val 初始化人
	 */
	public void setInit_user(Long init_user) {
		this.init_user = init_user;
	}

	/**
	 * @val 初始化状态
	 * @val 0，正常状态
	 * @val 1，作废状态
	 */
	public Integer getInit_state() {
		return init_state;
	}

	/**
	 * @val 初始化状态
	 * @val 0，正常状态
	 * @val 1，作废状态
	 */
	public void setInit_state(Integer init_state) {
		this.init_state = init_state;
	}

	/**
	 * @val 初始化说明
	 */
	public String getInit_desc() {
		return init_desc;
	}

	/**
	 * @val 初始化说明
	 */
	public void setInit_desc(String init_desc) {
		this.init_desc = init_desc;
	}

	/**
	 * @val 初始化金额
	 */
	public BigDecimal getInit_money() {
		return init_money;
	}

	/**
	 * @val 初始化金额
	 */
	public void setInit_money(BigDecimal init_money) {
		this.init_money = init_money;
	}

}