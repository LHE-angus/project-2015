package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
public class EcVouchersType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String title;

	private BigDecimal price;

	private Date add_date;

	private Integer info_state;

	private String memo;

	private Long add_user_id;

	public EcVouchersType() {

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
	 * @val 购物券名称
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @val 购物券名称
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @val 信息添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 信息添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

}