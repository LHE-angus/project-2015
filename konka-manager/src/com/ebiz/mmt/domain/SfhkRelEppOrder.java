package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-23 14:51:55
 */
public class SfhkRelEppOrder extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String sf_order_id;// 顺丰订单id

	private String epp_order_id;// epp订单id

	private String real_name;// 同步人姓名

	private Date add_date;// 同步时间

	private Long add_user_id;// 同步人id

	private Integer sf_state;// 订单在顺丰的状态

	private Integer epp_order_state;// 订单在epp的状态

	private Integer pay_state;// 订单付款状态

	private String buy_addr;// 收货人地址

	public SfhkRelEppOrder() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSf_order_id() {
		return sf_order_id;
	}

	public void setSf_order_id(String sf_order_id) {
		this.sf_order_id = sf_order_id;
	}

	public String getEpp_order_id() {
		return epp_order_id;
	}

	public void setEpp_order_id(String epp_order_id) {
		this.epp_order_id = epp_order_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Integer getSf_state() {
		return sf_state;
	}

	public void setSf_state(Integer sf_state) {
		this.sf_state = sf_state;
	}

	public Integer getEpp_order_state() {
		return epp_order_state;
	}

	public void setEpp_order_state(Integer epp_order_state) {
		this.epp_order_state = epp_order_state;
	}

	public Integer getPay_state() {
		return pay_state;
	}

	public void setPay_state(Integer pay_state) {
		this.pay_state = pay_state;
	}

	public String getBuy_addr() {
		return buy_addr;
	}

	public void setBuy_addr(String buyAddr) {
		buy_addr = buyAddr;
	}

}