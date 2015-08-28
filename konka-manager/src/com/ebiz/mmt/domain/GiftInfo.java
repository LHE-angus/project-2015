package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-07 11:39:53
 */
public class GiftInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long gift_id;
	
	private Long type_id;
	
	private String gift_name;
	
	private String gift_desc;
	
	private BigDecimal ref_price;
	
	private Date add_date;
	
	private Integer status;
	
	private Long add_user_id;
	
	private Long dept_id;
	
	private Integer order_value;
	
	public GiftInfo() {

	}

	/**
	 * @val 赠品ID
	 */
	public Long getGift_id() {
		return gift_id;
	}
	
	/**
	 * @val 赠品ID
	 */
	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}
	
	/**
	 * @val 赠品分类,关联KONKAOA_CATEGORY(C_TYPE=15)
	 */
	public Long getType_id() {
		return type_id;
	}
	
	/**
	 * @val 赠品分类,关联KONKAOA_CATEGORY(C_TYPE=15)
	 */
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * @val 赠品名称
	 */
	public String getGift_name() {
		return gift_name;
	}
	
	/**
	 * @val 赠品名称
	 */
	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}
	
	/**
	 * @val 赠品说明
	 */
	public String getGift_desc() {
		return gift_desc;
	}
	
	/**
	 * @val 赠品说明
	 */
	public void setGift_desc(String gift_desc) {
		this.gift_desc = gift_desc;
	}
	
	/**
	 * @val 参考价格（元）
	 */
	public BigDecimal getRef_price() {
		return ref_price;
	}
	
	/**
	 * @val 参考价格（元）
	 */
	public void setRef_price(BigDecimal ref_price) {
		this.ref_price = ref_price;
	}
	
	/**
	 * @val 创建时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 赠品状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 赠品状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @val 提交人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 提交人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 提交分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 提交分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
}