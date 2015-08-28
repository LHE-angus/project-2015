package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-15 15:54:20
 */
public class JStocksSummary extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long c_id;

	private String r3_code;

	private String customer_name;

	private Long goods_id;

	private String goods_name;

	private Date opr_date;

	private Long init_num;

	private Long come_num;

	private BigDecimal come_money;

	private Long out_num;

	private BigDecimal out_money;

	private Date add_date;

	private Integer type;

	private BigDecimal init_money;

	private BigDecimal sale_cost;

	private BigDecimal other_num;

	private BigDecimal other_money;

	public JStocksSummary() {

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
	 * @val 客户R3编码
	 */
	public String getR3_code() {
		return r3_code;
	}

	/**
	 * @val 客户R3编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}

	/**
	 * @val 客户名称
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @val 客户名称
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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
	 * @val 商品名称
	 */
	public String getGoods_name() {
		return goods_name;
	}

	/**
	 * @val 商品名称
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	/**
	 * @val 盘点时间
	 */
	public Date getOpr_date() {
		return opr_date;
	}

	/**
	 * @val 盘点时间
	 */
	public void setOpr_date(Date opr_date) {
		this.opr_date = opr_date;
	}

	/**
	 * @val 初始化数量
	 */
	public Long getInit_num() {
		return init_num;
	}

	/**
	 * @val 初始化数量
	 */
	public void setInit_num(Long init_num) {
		this.init_num = init_num;
	}

	/**
	 * @val 进货数量
	 */
	public Long getCome_num() {
		return come_num;
	}

	/**
	 * @val 进货数量
	 */
	public void setCome_num(Long come_num) {
		this.come_num = come_num;
	}

	/**
	 * @val 进货金额
	 */
	public BigDecimal getCome_money() {
		return come_money;
	}

	/**
	 * @val 进货金额
	 */
	public void setCome_money(BigDecimal come_money) {
		this.come_money = come_money;
	}

	/**
	 * @val 销售数量
	 */
	public Long getOut_num() {
		return out_num;
	}

	/**
	 * @val 销售数量
	 */
	public void setOut_num(Long out_num) {
		this.out_num = out_num;
	}

	/**
	 * @val 销售数量
	 */
	public BigDecimal getOut_money() {
		return out_money;
	}

	/**
	 * @val 销售数量
	 */
	public void setOut_money(BigDecimal out_money) {
		this.out_money = out_money;
	}

	/**
	 * @val 汇总(盘点)时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 汇总(盘点)时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 类型 0:客户库存汇总 1：自动盘点
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @val 类型 0:客户库存汇总 1：自动盘点
	 */
	public void setType(Integer type) {
		this.type = type;
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

	/**
	 * @val 期初金额
	 */
	public BigDecimal getInit_money() {
		return init_money;
	}

	/**
	 * @val 期初金额
	 */
	public void setInit_money(BigDecimal init_money) {
		this.init_money = init_money;
	}
	/**
	 * @val 其他项数量
	 */
	public BigDecimal getOther_num() {
		return other_num;
	}
	/**
	 * @val 其他项数量
	 */
	public void setOther_num(BigDecimal other_num) {
		this.other_num = other_num;
	}

	/**
	 * @val 其他项金额
	 */
	public BigDecimal getOther_money() {
		return other_money;
	}
	/**
	 * @val 其他项金额
	 */
	public void setOther_money(BigDecimal other_money) {
		this.other_money = other_money;
	}
}