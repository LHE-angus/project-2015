package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-22 16:26:02
 */
public class KonkaBaseTypeData extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String type_name;
	
	private Long type_id;
	
	private Long par_type_id;
	
	private String memo;
	
	private Integer is_del;
	
	private Integer is_lock;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Date update_date;
	
	private Long update_user_id;
	
	private Long order_sort;
	
	private String field1;
	
	private String field2;
	
	private String field3;
	
	public KonkaBaseTypeData() {

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
	 * @val 类别名称
	 */
	public String getType_name() {
		return type_name;
	}
	
	/**
	 * @val 类别名称
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	/**
	 * @val 类别ID
	 */
	public Long getType_id() {
		return type_id;
	}
	
	/**
	 * @val 类别ID
	 */
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * @val 父类别
	 */
	public Long getPar_type_id() {
		return par_type_id;
	}
	
	/**
	 * @val 父类别
	 */
	public void setPar_type_id(Long par_type_id) {
		this.par_type_id = par_type_id;
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
	 * @val 是否删除:0正常，1已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除:0正常，1已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 是否锁定:0正常，1已锁定
	 */
	public Integer getIs_lock() {
		return is_lock;
	}
	
	/**
	 * @val 是否锁定:0正常，1已锁定
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
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
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 更新时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 更新时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 更新人ID
	 */
	public Long getUpdate_user_id() {
		return update_user_id;
	}
	
	/**
	 * @val 更新人ID
	 */
	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	/**
	 * @val 排序值 排序方式:asc
	 */
	public Long getOrder_sort() {
		return order_sort;
	}
	
	/**
	 * @val 排序值 排序方式:asc
	 */
	public void setOrder_sort(Long order_sort) {
		this.order_sort = order_sort;
	}
	
	/**
	 * @val 自定义字段1(属性)
	 */
	public String getField1() {
		return field1;
	}
	
	/**
	 * @val 自定义字段1(属性)
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	/**
	 * @val 自定义字段2(节点级别)
	 */
	public String getField2() {
		return field2;
	}
	
	/**
	 * @val 自定义字段2(节点级别)
	 */
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	/**
	 * @val 自定义字段3
	 */
	public String getField3() {
		return field3;
	}
	
	/**
	 * @val 自定义字段3
	 */
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
}