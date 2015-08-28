package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-21 09:45:18
 */
public class BasePdClass extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long cls_id;
	
	private String cls_name;
	
	private Long par_id;
	
	private Long root_id;
	
	private Integer order_value;
	
	private Integer is_del;
	
	public BasePdClass() {

	}

	/**
	 * @val 类别ID
	 */
	public Long getCls_id() {
		return cls_id;
	}
	
	/**
	 * @val 类别ID
	 */
	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}
	
	/**
	 * @val 类别名称
	 */
	public String getCls_name() {
		return cls_name;
	}
	
	/**
	 * @val 类别名称
	 */
	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}
	
	/**
	 * @val 父类别名称
	 */
	public Long getPar_id() {
		return par_id;
	}
	
	/**
	 * @val 父类别名称
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	/**
	 * @val 根类别ID
	 */
	public Long getRoot_id() {
		return root_id;
	}
	
	/**
	 * @val 根类别ID
	 */
	public void setRoot_id(Long root_id) {
		this.root_id = root_id;
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
	
	/**
	 * @val 是否删除：0-否，1-是
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除：0-否，1-是
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}