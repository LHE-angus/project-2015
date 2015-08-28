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
public class JBillDetails extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long bill_item_id;

	private Long bill_id;

	private Long store_id;

	private Long goods_id;

	private Long num;

	private BigDecimal price;

	private BigDecimal money;

	private BigDecimal cost;

	private String gift_desc;

	private String notes;

	private JBaseGoods jBaseGoods;

	private Long gift_id;

	private Integer gift_count;

	private BigDecimal dis_money;

	private BigDecimal rec_money;

	private BigDecimal sale_cost;
	
	private Long in_store_id;
	
	private Long type_id;

	public JBillDetails() {

	}

	/**
	 * @val 细目ID
	 */
	public Long getBill_item_id() {
		return bill_item_id;
	}

	/**
	 * @val 细目ID
	 */
	public void setBill_item_id(Long bill_item_id) {
		this.bill_item_id = bill_item_id;
	}

	/**
	 * @val 单据ID
	 */
	public Long getBill_id() {
		return bill_id;
	}

	/**
	 * @val 单据ID
	 */
	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}

	/**
	 * @val 仓库
	 */
	public Long getStore_id() {
		return store_id;
	}

	/**
	 * @val 仓库
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @val 商品
	 */
	public Long getGoods_id() {
		return goods_id;
	}

	/**
	 * @val 商品
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}

	/**
	 * @val 数量
	 */
	public Long getNum() {
		return num;
	}

	/**
	 * @val 数量
	 */
	public void setNum(Long num) {
		this.num = num;
	}

	/**
	 * @val 采购价/销售价/退货价（元）
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @val 采购价/销售价/退货价（元）
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @val 金额（元）
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @val 金额（元）
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @val 成本（元），计算某商品的成本采用先进先出的原则
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @val 成本（元），计算某商品的成本采用先进先出的原则
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getGift_desc() {
		return gift_desc;
	}

	public void setGift_desc(String gift_desc) {
		this.gift_desc = gift_desc;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public JBaseGoods getjBaseGoods() {
		return jBaseGoods;
	}

	public void setjBaseGoods(JBaseGoods jBaseGoods) {
		this.jBaseGoods = jBaseGoods;
	}

	public Long getGift_id() {
		return gift_id;
	}

	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}

	public Integer getGift_count() {
		return gift_count;
	}

	public void setGift_count(Integer gift_count) {
		this.gift_count = gift_count;
	}

	/**
	 * @val 折扣金额（元）
	 */
	public BigDecimal getDis_money() {
		return dis_money;
	}

	/**
	 * @val 折扣金额（元）
	 */
	public void setDis_money(BigDecimal dis_money) {
		this.dis_money = dis_money;
	}

	/**
	 * @val 实付金额（元）
	 */
	public BigDecimal getRec_money() {
		return rec_money;
	}

	/**
	 * @val 实付金额（元）
	 */
	public void setRec_money(BigDecimal rec_money) {
		this.rec_money = rec_money;
	}

	/**
	 * @val 销售成本
	 */
	public BigDecimal getSale_cost() {
		return sale_cost;
	}

	/**
	 * @val 销售成本
	 */
	public void setSale_cost(BigDecimal sale_cost) {
		this.sale_cost = sale_cost;
	}

	public Long getIn_store_id() {
		return in_store_id;
	}

	public void setIn_store_id(Long in_store_id) {
		this.in_store_id = in_store_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

}