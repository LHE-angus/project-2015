package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
public class JfScortsExchange extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String user_sn;
	
	private Long gift_id;
	
	private BigDecimal scorts;
	
	private Date add_date;
	
	private Long add_user;
	
	private String remark;
	
	public JfScortsExchange() {

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
	 * @val 会员卡号
	 */
	public String getUser_sn() {
		return user_sn;
	}
	
	/**
	 * @val 会员卡号
	 */
	public void setUser_sn(String user_sn) {
		this.user_sn = user_sn;
	}
	
	/**
	 * @val 兑换礼品ID
	 */
	public Long getGift_id() {
		return gift_id;
	}
	
	/**
	 * @val 兑换礼品ID
	 */
	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}
	
	/**
	 * @val 兑换积分
	 */
	public BigDecimal getScorts() {
		return scorts;
	}
	
	/**
	 * @val 兑换积分
	 */
	public void setScorts(BigDecimal scorts) {
		this.scorts = scorts;
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
	 * @val 操作员
	 */
	public Long getAdd_user() {
		return add_user;
	}
	
	/**
	 * @val 操作员
	 */
	public void setAdd_user(Long add_user) {
		this.add_user = add_user;
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
	
}