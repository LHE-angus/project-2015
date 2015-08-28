package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public class KonkaXxGoods extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long goods_id;
	
	private Long dept_id;
	
	private String goods_name;
	
	private Long store;
	
	private Long counts;
	
	private Long state;
	
	private String memo;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public KonkaXxGoods() {

	}

	/**
	 * @val 物料ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 物料ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 物料名称
	 */
	public String getGoods_name() {
		return goods_name;
	}
	
	/**
	 * @val 物料名称
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	/**
	 * @val 仓位
	 */
	public Long getStore() {
		return store;
	}
	
	/**
	 * @val 仓位
	 */
	public void setStore(Long store) {
		this.store = store;
	}
	
	/**
	 * @val 数量
	 */
	public Long getCounts() {
		return counts;
	}
	
	/**
	 * @val 数量
	 */
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	
	/**
	 * @val 状态:BASE_TYPE
	 */
	public Long getState() {
		return state;
	}
	
	/**
	 * @val 状态:BASE_TYPE
	 */
	public void setState(Long state) {
		this.state = state;
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
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
}