package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:06:13
 */
public class PshowOrdeDetails extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long bill_item_id;

	private Long order_id;

	private Long pd_id2;

	private Long pd_id;

	private Long num;

	private BigDecimal price;

	private BigDecimal total_price;

	private Integer state;

	private String remark;

	private String pd_name;

	private Long store_id;

	private BigDecimal integral;

	private BigDecimal rebates;

	List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList;

	List<EcRule> ecRuleList;// 商品规则

	private Integer rebates_status;

	private String rebates_sender;

	private Date rebates_date;

	private Integer is_send;// 短信提醒0发送成功1发送失败
	
	private BigDecimal pay_integral;
	
	private BigDecimal super_rebate;

	public BigDecimal getPay_integral() {
		return pay_integral;
	}

	public BigDecimal getSuper_rebate() {
		return super_rebate;
	}

	public void setSuper_rebate(BigDecimal super_rebate) {
		this.super_rebate = super_rebate;
	}

	public void setPay_integral(BigDecimal pay_integral) {
		this.pay_integral = pay_integral;
	}

	/**
	 * @val 返利状态
	 */
	public Integer getRebates_status() {
		return rebates_status;
	}

	/**
	 * @val 返利状态
	 */
	public void setRebates_status(Integer rebates_status) {
		this.rebates_status = rebates_status;
	}

	/**
	 * @val 返利发放人
	 */
	public String getRebates_sender() {
		return rebates_sender;
	}

	/**
	 * @val 返利发放人
	 */
	public void setRebates_sender(String rebates_sender) {
		this.rebates_sender = rebates_sender;
	}

	/**
	 * @val 返利发放时间
	 */
	public Date getRebates_date() {
		return rebates_date;
	}

	/**
	 * @val 返利发放时间
	 */
	public void setRebates_date(Date rebates_date) {
		this.rebates_date = rebates_date;
	}

	private EcUser ecUser = new EcUser();

	public EcUser getEcUser() {
		return ecUser;
	}

	public void setEcUser(EcUser ecUser) {
		this.ecUser = ecUser;
	}

	public PshowOrdeDetails() {

	}

	/**
	 * @val 细目ID
	 */
	public Long getBill_item_id() {
		return bill_item_id;
	}

	/**
	 * @val 细目ID
	 */
	public void setBill_item_id(Long bill_item_id) {
		this.bill_item_id = bill_item_id;
	}

	/**
	 * @val 订单ID
	 */
	public Long getOrder_id() {
		return order_id;
	}

	/**
	 * @val 订单ID
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	/**
	 * @val 价格ID
	 */

	public Long getPd_id2() {
		return pd_id2;
	}

	/**
	 * @val 价格ID
	 */
	public void setPd_id2(Long pdId2) {
		pd_id2 = pdId2;
	}

	/**
	 * @val 产品ID
	 */
	public Long getPd_id() {
		return pd_id;
	}

	/**
	 * @val 产品ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	/**
	 * @val 数量
	 */
	public Long getNum() {
		return num;
	}

	/**
	 * @val 数量
	 */
	public void setNum(Long num) {
		this.num = num;
	}

	/**
	 * @val 销售单价
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @val 销售单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @val 金额（元）
	 */
	public BigDecimal getTotal_price() {
		return total_price;
	}

	/**
	 * @val 金额（元）
	 */
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	/**
	 * @val 订单状态：
	 * @val 0-正常
	 * @val 1-已退货
	 * @val
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 订单状态：
	 * @val 0-正常
	 * @val 1-已退货
	 * @val
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 订单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	/**
	 * @val 仓库ID：商品出仓的仓库ID
	 */
	public Long getStore_id() {
		return store_id;
	}

	/**
	 * @val 仓库ID：商品出仓的仓库ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @val 积分
	 */
	public BigDecimal getIntegral() {
		return integral;
	}

	/**
	 * @val 积分
	 */
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	/**
	 * @val 返利
	 */
	public BigDecimal getRebates() {
		return rebates;
	}

	/**
	 * @val 返利
	 */
	public void setRebates(BigDecimal rebates) {
		this.rebates = rebates;
	}

	public List<EcBindingPdOrdeDetails> getEcBindingPdOrdeDetailsList() {
		return ecBindingPdOrdeDetailsList;
	}

	public void setEcBindingPdOrdeDetailsList(List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList) {
		this.ecBindingPdOrdeDetailsList = ecBindingPdOrdeDetailsList;
	}

	public Integer getIs_send() {
		return is_send;
	}

	public void setIs_send(Integer isSend) {
		is_send = isSend;
	}

	public List<EcRule> getEcRuleList() {
		return ecRuleList;
	}

	public void setEcRuleList(List<EcRule> ecRuleList) {
		this.ecRuleList = ecRuleList;
	}

}