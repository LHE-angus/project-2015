package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:42
 */
public class EcCashPrice extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private String pd_sn;
	
	private BigDecimal cash_price;
	
	private Date add_date;
	
	private Integer info_state;
	
	public EcCashPrice() {

	}

	/**
	 * @val 信息ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 信息ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 用户ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 用户ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 产品型号
	 */
	public String getPd_sn() {
		return pd_sn;
	}
	
	/**
	 * @val 产品型号
	 */
	public void setPd_sn(String pd_sn) {
		this.pd_sn = pd_sn;
	}
	
	/**
	 * @val 现款价
	 */
	public BigDecimal getCash_price() {
		return cash_price;
	}
	
	/**
	 * @val 现款价
	 */
	public void setCash_price(BigDecimal cash_price) {
		this.cash_price = cash_price;
	}
	
	/**
	 * @val 日期
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val -1：已删除
	 * @val 0：未回复
	 * @val 1：已回复
	 */
	public Integer getInfo_state() {
		return info_state;
	}
	
	/**
	 * @val -1：已删除
	 * @val 0：未回复
	 * @val 1：已回复
	 */
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}
	
}