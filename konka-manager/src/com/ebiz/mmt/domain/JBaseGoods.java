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
public class JBaseGoods extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long goods_id;
	
	private String goods_name;
	
	private String goods_sn;
	
	private Long goods_unit;
	
	private Long goods_type;
	
	private Integer goods_stutes;
	
	private String goods_desc;
	
	private Long init_count;
	
	private BigDecimal buy_price;
	
	private BigDecimal sell_price;
	
	private Integer is_konka;
	
	private Long c_id;
	
	private Date add_date;
	
	public JBaseGoods() {

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
	 * @val 商品条码
	 */
	public String getGoods_sn() {
		return goods_sn;
	}
	
	/**
	 * @val 商品条码
	 */
	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}
	
	/**
	 * @val 商品单位
	 */
	public Long getGoods_unit() {
		return goods_unit;
	}
	
	/**
	 * @val 商品单位
	 */
	public void setGoods_unit(Long goods_unit) {
		this.goods_unit = goods_unit;
	}
	
	/**
	 * @val 商品类型
	 */
	public Long getGoods_type() {
		return goods_type;
	}
	
	/**
	 * @val 商品类型
	 */
	public void setGoods_type(Long goods_type) {
		this.goods_type = goods_type;
	}
	
	/**
	 * @val 商品状态:0-上架 1-下架
	 */
	public Integer getGoods_stutes() {
		return goods_stutes;
	}
	
	/**
	 * @val 商品状态:0-上架 1-下架
	 */
	public void setGoods_stutes(Integer goods_stutes) {
		this.goods_stutes = goods_stutes;
	}
	
	/**
	 * @val 商品描述
	 */
	public String getGoods_desc() {
		return goods_desc;
	}
	
	/**
	 * @val 商品描述
	 */
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	
	/**
	 * @val 起初库存
	 */
	public Long getInit_count() {
		return init_count;
	}
	
	/**
	 * @val 起初库存
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
	 * @val 康佳商品标识:0-否，1-是
	 */
	public Integer getIs_konka() {
		return is_konka;
	}
	
	/**
	 * @val 康佳商品标识:0-否，1-是
	 */
	public void setIs_konka(Integer is_konka) {
		this.is_konka = is_konka;
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

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Date getAdd_date() {
		return add_date;
	}
	
}