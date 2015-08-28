package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 16:35:55
 */
public class EcGoodsPriceModifyForTouc extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long goods_id;
	
	private Long dept_id;
	
	private Integer type;
	
	private BigDecimal price;
	
	private BigDecimal original_price;
	
	private Date start_time;
	
	private Date end_time;
	
	private String remarks;
	
	private Integer is_del;
	
	private Date add_date;
	
	public EcGoodsPriceModifyForTouc() {

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
	 * @val 商品ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 部门ID：如果是分公司修改的则为分公司ID，如果是客户修改的则为客户的ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 部门ID：如果是分公司修改的则为分公司ID，如果是客户修改的则为客户的ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 类型：0-修改价格，1-屏蔽商品
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * @val 类型：0-修改价格，1-屏蔽商品
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @val 价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 原价格
	 */
	public BigDecimal getOriginal_price() {
		return original_price;
	}
	
	/**
	 * @val 原价格
	 */
	public void setOriginal_price(BigDecimal original_price) {
		this.original_price = original_price;
	}
	
	/**
	 * @val 开始时间
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * @val 开始时间
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * @val 结束时间
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * @val 结束时间
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
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
	
}