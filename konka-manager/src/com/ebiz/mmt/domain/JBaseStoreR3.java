package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-13 14:54:35
 */
public class JBaseStoreR3 extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long store_id;
	
	private String r3_code;
	
	private String memo;
	
	private Integer is_del;
	
	private Date add_date;
	
	private String sale_r3_code;
	
	private String sale_r3_name;
	
	public JBaseStoreR3() {

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
	 * @val 仓库ID
	 */
	public Long getStore_id() {
		return store_id;
	}
	
	/**
	 * @val 仓库ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	public String getR3_code() {
		return r3_code;
	}
	
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
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
	
	/**
	 * @val 客户售达方编码
	 */
	public String getSale_r3_code() {
		return sale_r3_code;
	}
	
	/**
	 * @val 客户售达方编码
	 */
	public void setSale_r3_code(String sale_r3_code) {
		this.sale_r3_code = sale_r3_code;
	}
	
	/**
	 * @val 客户送达方名称
	 */
	public String getSale_r3_name() {
		return sale_r3_name;
	}
	
	/**
	 * @val 客户送达方名称
	 */
	public void setSale_r3_name(String sale_r3_name) {
		this.sale_r3_name = sale_r3_name;
	}
	
}