package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
public class EcOrderPay extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String trade_index;
	
	private Date pay_date;
	
	private BigDecimal price;
	
	private Integer pay_way;
	
	private String trade_no;
	
	private String title;
	
	public EcOrderPay() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 活动ID
	 */
	public String getTrade_index() {
		return trade_index;
	}
	
	/**
	 * @val 活动ID
	 */
	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getPay_date() {
		return pay_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	
	/**
	 * @val 结束时间
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 结束时间
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 支付方式
	 */
	public Integer getPay_way() {
		return pay_way;
	}
	
	/**
	 * @val 支付方式
	 */
	public void setPay_way(Integer pay_way) {
		this.pay_way = pay_way;
	}
	
	/**
	 * @val 支付单号
	 */
	public String getTrade_no() {
		return trade_no;
	}
	
	/**
	 * @val 支付单号
	 */
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}