package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-16 14:06:29
 */
public class KonkaFightActivityManager extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String activity_name;
	
	private Long store_id;
	
	private Long brand_id;
	
	private String model;
	
	private Date begin_date;
	
	private Date end_date;
	
	private Long sale_num;
	
	private BigDecimal sale_money;
	
	private String memo;
	
	private Long add_user;
	
	private Date add_date;
	
	private String add_user_name;
	
	
	private Integer is_del;
	
	
	public KonkaFightActivityManager() {

	}

	/**
	 * @val 主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 活动名称
	 */
	public String getActivity_name() {
		return activity_name;
	}
	
	/**
	 * @val 活动名称
	 */
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	
	/**
	 * @val 门店
	 */
	public Long getStore_id() {
		return store_id;
	}
	
	/**
	 * @val 门店
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	/**
	 * @val 品牌
	 */
	public Long getBrand_id() {
		return brand_id;
	}
	
	/**
	 * @val 品牌
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
	/**
	 * @val 型号
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * @val 型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public Date getBegin_date() {
		return begin_date;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @val 销售量
	 */
	public Long getSale_num() {
		return sale_num;
	}
	
	/**
	 * @val 销售量
	 */
	public void setSale_num(Long sale_num) {
		this.sale_num = sale_num;
	}
	
	/**
	 * @val 销售金额
	 */
	public BigDecimal getSale_money() {
		return sale_money;
	}
	
	/**
	 * @val 销售金额
	 */
	public void setSale_money(BigDecimal sale_money) {
		this.sale_money = sale_money;
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
	 * @val 添加人
	 */
	public Long getAdd_user() {
		return add_user;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user(Long add_user) {
		this.add_user = add_user;
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
	 * @val 添加人姓名
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	/**
	 * @val 添加人姓名
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	/**
	 * @val 删除状态
	 */
	public Integer getIs_del() {
		return is_del;
	}
	/**
	 * @val 删除状态
	 */
	public void setIs_del(Integer isDel) {
		is_del = isDel;
	}
	
	
	
}