package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
public class JBaseType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long type_id;
	
	private Long par_id;
	
	private String type_name;
	
	private String type_desc;
	
	private Long order_value;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Long c_id;
	
	public JBaseType() {

	}

	/**
	 * @val 类型ID
	 */
	public Long getType_id() {
		return type_id;
	}
	
	/**
	 * @val 类型ID
	 */
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * @val 父类型ID
	 */
	public Long getPar_id() {
		return par_id;
	}
	
	/**
	 * @val 父类型ID
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	/**
	 * @val 类型名称
	 */
	public String getType_name() {
		return type_name;
	}
	
	/**
	 * @val 类型名称
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	/**
	 * @val 类型描述
	 */
	public String getType_desc() {
		return type_desc;
	}
	
	/**
	 * @val 类型描述
	 */
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	
	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public Long getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
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
	 * @val 客户ID，客户ID为-1时表示系统类型
	 */
	public Long getC_id() {
		return c_id;
	}
	
	/**
	 * @val 客户ID，客户ID为-1时表示系统类型
	 */
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	
}