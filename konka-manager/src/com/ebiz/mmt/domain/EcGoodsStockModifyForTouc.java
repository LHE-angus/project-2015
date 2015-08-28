package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 16:41:34
 */
public class EcGoodsStockModifyForTouc extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long goods_id;
	
	private Long dept_id;
	
	private Long stocks;
	
	private String remarks;
	
	private Integer is_del;
	
	private Date add_date;
	
	public EcGoodsStockModifyForTouc() {

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
	 * @val 库存数量
	 */
	public Long getStocks() {
		return stocks;
	}
	
	/**
	 * @val 库存数量
	 */
	public void setStocks(Long stocks) {
		this.stocks = stocks;
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