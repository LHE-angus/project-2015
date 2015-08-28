package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public class KonkaXxModelPropsVal extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String md_name;
	
	private Long prop_id;
	
	private String prop_value;
	
	private String prop_item_ids;
	
	public KonkaXxModelPropsVal() {

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
	 * @val 产品型号
	 */
	public String getMd_name() {
		return md_name;
	}
	
	/**
	 * @val 产品型号
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	/**
	 * @val 属性ID
	 */
	public Long getProp_id() {
		return prop_id;
	}
	
	/**
	 * @val 属性ID
	 */
	public void setProp_id(Long prop_id) {
		this.prop_id = prop_id;
	}
	
	/**
	 * @val 产品属性值，不可超过1000个字符（不带单位）
	 */
	public String getProp_value() {
		return prop_value;
	}
	
	/**
	 * @val 产品属性值，不可超过1000个字符（不带单位）
	 */
	public void setProp_value(String prop_value) {
		this.prop_value = prop_value;
	}
	
	/**
	 * @val 属性项IDS【PROP_ITEM_IDS】，最大支持100个属性项 (17 * 100 = 1700) 
	 */
	public String getProp_item_ids() {
		return prop_item_ids;
	}
	
	/**
	 * @val 属性项IDS【PROP_ITEM_IDS】，最大支持100个属性项 (17 * 100 = 1700) 
	 */
	public void setProp_item_ids(String prop_item_ids) {
		this.prop_item_ids = prop_item_ids;
	}
	
}