package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public class JStocksVerify extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String bill_sn;

	private Integer trade_type;

	private Long store_id;

	private Long goods_id;

	private Long stocks;

	private Long ver_stocks;

	private String memo;

	private Date opr_date;

	private Date add_date;

	private Long c_id;

	private Integer type;

	private BigDecimal money;

	private BigDecimal ver_money;

	public JStocksVerify() {

	}

	/**
	 * @val 盘点记录ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 盘点记录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 单据编号
	 */
	public String getBill_sn() {
		return bill_sn;
	}

	/**
	 * @val 单据编号
	 */
	public void setBill_sn(String bill_sn) {
		this.bill_sn = bill_sn;
	}

	/**
	 * @val 业务类型:30-盘亏，31-盘盈
	 */
	public Integer getTrade_type() {
		return trade_type;
	}

	/**
	 * @val 业务类型:30-盘亏，31-盘盈
	 */
	public void setTrade_type(Integer trade_type) {
		this.trade_type = trade_type;
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
	 * @val 盘点数量
	 */
	public Long getVer_stocks() {
		return ver_stocks;
	}

	/**
	 * @val 盘点数量
	 */
	public void setVer_stocks(Long ver_stocks) {
		this.ver_stocks = ver_stocks;
	}

	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @val 操作日期
	 */
	public Date getOpr_date() {
		return opr_date;
	}

	/**
	 * @val 操作日期
	 */
	public void setOpr_date(Date opr_date) {
		this.opr_date = opr_date;
	}

	/**
	 * @val 入库日期
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 入库日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
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
	 * @val 盘点类型 0:初始化 1:单个盘点 2：月初结转 3:月中盘点 4：月末全部盘点
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @val 盘点类型 0:初始化 1:单个盘点 2：月初结转 3:月中盘点 4：月末全部盘点
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @val 当前金额
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @val 当前金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @val 盘点金额
	 */
	public BigDecimal getVer_money() {
		return ver_money;
	}

	/**
	 * @val 盘点金额
	 */
	public void setVer_money(BigDecimal ver_money) {
		this.ver_money = ver_money;
	}

}