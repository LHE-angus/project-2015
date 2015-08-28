package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * 分销记录
 */
public class JSubSellRec extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String sell_bill_sn;
	
	private String buy_bill_sn;
	
    private Long sell_partner_id;// 客户id
	
    private Long buy_partner_id;// 客户id
	
	private Date add_date;
	
	private Integer status;
	
	private Date confirm_date;
	
	private String confirm_money;
	
	public JSubSellRec() {

	}

	/**
	 * @val 分销记录ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 分销记录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 销售单据编号
	 */
	public String getSell_bill_sn() {
		return sell_bill_sn;
	}
	
	/**
	 * @val 销售单据编号
	 */
	public void setSell_bill_sn(String sell_bill_sn) {
		this.sell_bill_sn = sell_bill_sn;
	}
	
	/**
	 * @val 进货单据编号
	 */
	public String getBuy_bill_sn() {
		return buy_bill_sn;
	}
	
	/**
	 * @val 进货单据编号
	 */
	public void setBuy_bill_sn(String buy_bill_sn) {
		this.buy_bill_sn = buy_bill_sn;
	}
	
	/**
	 * @val 往来单位-分销商
	 */
	public Long getSell_partner_id() {
		return sell_partner_id;
	}
	
	/**
	 * @val 往来单位-分销商
	 */
	public void setSell_partner_id(Long sell_partner_id) {
		this.sell_partner_id = sell_partner_id;
	}
	
	/**
	 * @val 往来单位-客户
	 */
	public Long getBuy_partner_id() {
		return buy_partner_id;
	}
	
	/**
	 * @val 往来单位-客户
	 */
	public void setBuy_partner_id(Long buy_partner_id) {
		this.buy_partner_id = buy_partner_id;
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
	 * @val 分销确认状态：0-未确认 1-已确认  2-已退回
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 分销确认状态：0-未确认 1-已确认
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 分销确认时间
	 */
	public Date getConfirm_date() {
		return confirm_date;
	}
	
	/**
	 * @val 分销确认时间
	 */
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}

	public String getConfirm_money() {
		return confirm_money;
	}

	public void setConfirm_money(String confirm_money) {
		this.confirm_money = confirm_money;
	}
	
}