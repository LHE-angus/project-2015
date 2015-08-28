package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 15:49:23
 */
public class EcGiftComm extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer own_sys;
	
	private Long gift_id;
	
	private Long goods_id;
	
	private Integer need_integral;
	
	private BigDecimal price;
	
	private String goods_url;
	
	private String gift_url;
	
	public EcGiftComm() {

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
	 * @val 所属系统：1-工卡，2-触网，3-会员
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统：1-工卡，2-触网，3-会员
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val 积分商品ID
	 */
	public Long getGift_id() {
		return gift_id;
	}
	
	/**
	 * @val 积分商品ID
	 */
	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
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
	 * @val 所需积分
	 */
	public Integer getNeed_integral() {
		return need_integral;
	}
	
	/**
	 * @val 所需积分
	 */
	public void setNeed_integral(Integer need_integral) {
		this.need_integral = need_integral;
	}
	
	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 商品关联URL
	 */
	public String getGoods_url() {
		return goods_url;
	}
	
	/**
	 * @val 商品关联URL
	 */
	public void setGoods_url(String goods_url) {
		this.goods_url = goods_url;
	}
	
	/**
	 * @val 积分商品关联URL
	 */
	public String getGift_url() {
		return gift_url;
	}
	
	/**
	 * @val 积分商品关联URL
	 */
	public void setGift_url(String gift_url) {
		this.gift_url = gift_url;
	}
	
}