package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-25 09:27:47
 */
public class KonkaXxZmdGcys extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long zmd_id;
	
	private String item_name;
	
	private String pd_name;
	
	private String model_name;
	
	private BigDecimal item_num;
	
	private String unit;
	
	private BigDecimal price;
	
	private BigDecimal total;
	
	public KonkaXxZmdGcys() {

	}

	/**
	 * @val 工程项目ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 工程项目ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 专卖店ID
	 */
	public Long getZmd_id() {
		return zmd_id;
	}
	
	/**
	 * @val 专卖店ID
	 */
	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}
	
	/**
	 * @val 工程项目名称
	 */
	public String getItem_name() {
		return item_name;
	}
	
	/**
	 * @val 工程项目名称
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	/**
	 * @val 品名
	 */
	public String getPd_name() {
		return pd_name;
	}
	
	/**
	 * @val 品名
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	/**
	 * @val 规格
	 */
	public String getModel_name() {
		return model_name;
	}
	
	/**
	 * @val 规格
	 */
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	/**
	 * @val 数量
	 */
	public BigDecimal getItem_num() {
		return item_num;
	}
	
	/**
	 * @val 数量
	 */
	public void setItem_num(BigDecimal item_num) {
		this.item_num = item_num;
	}
	
	/**
	 * @val 单位
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * @val 单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * @val 单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 小计
	 */
	public BigDecimal getTotal() {
		return total;
	}
	
	/**
	 * @val 小计
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}