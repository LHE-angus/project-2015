package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-18 09:43:02
 */
public class JStocksStack extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long stack_id;

	private Long store_id;

	private Long goods_id;

	private BigDecimal goods_cost;

	private String bill_id_pop;

	private String bill_id_push;

	private Integer status;

	private Long c_id;

	public JStocksStack() {

	}

	/**
	 * @val 栈ID
	 */
	public Long getStack_id() {
		return stack_id;
	}

	/**
	 * @val 栈ID
	 */
	public void setStack_id(Long stack_id) {
		this.stack_id = stack_id;
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
	 * @val 成本价（元）
	 */
	public BigDecimal getGoods_cost() {
		return goods_cost;
	}

	/**
	 * @val 成本价（元）
	 */
	public void setGoods_cost(BigDecimal goods_cost) {
		this.goods_cost = goods_cost;
	}

	/**
	 * @val 单据ID-出栈
	 */
	public String getBill_id_pop() {
		return bill_id_pop;
	}

	/**
	 * @val 单据ID-出栈
	 */
	public void setBill_id_pop(String bill_id_pop) {
		this.bill_id_pop = bill_id_pop;
	}

	/**
	 * @val 单据ID-入栈
	 */
	public String getBill_id_push() {
		return bill_id_push;
	}

	/**
	 * @val 单据ID-入栈
	 */
	public void setBill_id_push(String bill_id_push) {
		this.bill_id_push = bill_id_push;
	}

	/**
	 * @val 状态：0-栈内（入栈中），1-栈外（已出栈）
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @val 状态：0-栈内（入栈中），1-栈外（已出栈）
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

}