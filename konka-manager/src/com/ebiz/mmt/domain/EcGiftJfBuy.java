package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-24 15:46:32
 */
public class EcGiftJfBuy extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String trade_index;
	
	private Integer own_sys;
	
	private Long integral;
	
	private BigDecimal price;
	
	private Long order_value;
	
	private Date add_date;
	
	private Integer state;
	
	private String user_name;
	
	private String mobile;
	
	private Long user_id;
	
	private Date pay_date;
	
	private String trade_no;
	
	private Integer pay_type;
	
	private String title;
	
	private BigDecimal discount;
	
	private String remark;
	
	private String real_name;
	
	public EcGiftJfBuy() {

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
	 * @val 交易流水号
	 */
	public String getTrade_index() {
		return trade_index;
	}
	
	/**
	 * @val 交易流水号
	 */
	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
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
	 * @val 积分
	 */
	public Long getIntegral() {
		return integral;
	}
	
	/**
	 * @val 积分
	 */
	public void setIntegral(Long integral) {
		this.integral = integral;
	}
	
	/**
	 * @val 金额
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 排序值
	 */
	public Long getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值
	 */
	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 状态：0-等待付款1已付款,2已取消
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 状态：0-等待付款1已付款,2已取消
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * @val 购买用户
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 购买用户
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 手机
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * @val 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @val 付款时间
	 */
	public Date getPay_date() {
		return pay_date;
	}
	
	/**
	 * @val 付款时间
	 */
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
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
	
	/**
	 * @val 支付方式
	 */
	public Integer getPay_type() {
		return pay_type;
	}
	
	/**
	 * @val 支付方式
	 */
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 折扣
	 */
	public BigDecimal getDiscount() {
		return discount;
	}
	
	/**
	 * @val 折扣
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 购买人姓名
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * @val 购买人姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
}