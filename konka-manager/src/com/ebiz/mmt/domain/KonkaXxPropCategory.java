package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public class KonkaXxPropCategory extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long category_id;
	
	private String category_name;
	
	private Long cls_id;
	
	private Long order_value;
	
	private String memo;
	
	private Integer is_del;
	
	public KonkaXxPropCategory() {

	}

	/**
	 * @val 属性类别ID
	 */
	public Long getCategory_id() {
		return category_id;
	}
	
	/**
	 * @val 属性类别ID
	 */
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	/**
	 * @val 属性类别名称
	 */
	public String getCategory_name() {
		return category_name;
	}
	
	/**
	 * @val 属性类别名称
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	/**
	 * @val 产品类别
	 */
	public Long getCls_id() {
		return cls_id;
	}
	
	/**
	 * @val 产品类别
	 */
	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
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
	 * @val 是否删除：1-是，0：否
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除：1-是，0：否
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}