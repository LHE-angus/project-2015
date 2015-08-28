package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public class KonkaXxPdProp extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long prop_id;
	
	private Long category_id;
	
	private String prop_name;
	
	private Long cls_id;
	
	private String prop_unit;
	
	private Integer is_required;
	
	private Integer prop_type;
	
	private Integer prop_val_type;
	
	private Long prop_val_min;
	
	private Long prop_val_max;
	
	private String memo;
	
	private Date add_date;
	
	private Long order_value;
	
	private Integer is_del;
	
	private Date last_edit_date;
	
	private Long add_user_id;
	
	private Long last_edit_user_id;
	
	private List<KonkaXxPropValItem> konkaXxPropValItemList;
	
	public List<KonkaXxPropValItem> getKonkaXxPropValItemList() {
		return konkaXxPropValItemList;
	}
	
	public void setKonkaXxPropValItemList(List<KonkaXxPropValItem> konkaXxPropValItemList) {
		this.konkaXxPropValItemList = konkaXxPropValItemList;
	}
	
	public KonkaXxPdProp() {

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
	 * @val 属性名称
	 */
	public String getProp_name() {
		return prop_name;
	}
	
	/**
	 * @val 属性名称
	 */
	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
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
	 * @val 属性单位
	 */
	public String getProp_unit() {
		return prop_unit;
	}
	
	/**
	 * @val 属性单位
	 */
	public void setProp_unit(String prop_unit) {
		this.prop_unit = prop_unit;
	}
	
	/**
	 * @val 是否必填：1-是，0-否
	 */
	public Integer getIs_required() {
		return is_required;
	}
	
	/**
	 * @val 是否必填：1-是，0-否
	 */
	public void setIs_required(Integer is_required) {
		this.is_required = is_required;
	}
	
	/**
	 * @val 属性类型：0-输入，1-单选，2-多选（当此项为1或2时启用属性值项表）
	 */
	public Integer getProp_type() {
		return prop_type;
	}
	
	/**
	 * @val 属性类型：0-输入，1-单选，2-多选（当此项为1或2时启用属性值项表）
	 */
	public void setProp_type(Integer prop_type) {
		this.prop_type = prop_type;
	}
	
	/**
	 * @val 属性值类型：0-数字，1-文本，2-日期（yyyy-MM-dd）
	 */
	public Integer getProp_val_type() {
		return prop_val_type;
	}
	
	/**
	 * @val 属性值类型：0-数字，1-文本，2-日期（yyyy-MM-dd）
	 */
	public void setProp_val_type(Integer prop_val_type) {
		this.prop_val_type = prop_val_type;
	}
	
	/**
	 * @val 属性值范围最小值（仅PROP_VAL_TYPE为数字[0]）
	 */
	public Long getProp_val_min() {
		return prop_val_min;
	}
	
	/**
	 * @val 属性值范围最小值（仅PROP_VAL_TYPE为数字[0]）
	 */
	public void setProp_val_min(Long prop_val_min) {
		this.prop_val_min = prop_val_min;
	}
	
	/**
	 * @val 属性值范围最大值（仅PROP_VAL_TYPE为数字[0]）
	 */
	public Long getProp_val_max() {
		return prop_val_max;
	}
	
	/**
	 * @val 属性值范围最大值（仅PROP_VAL_TYPE为数字[0]）
	 */
	public void setProp_val_max(Long prop_val_max) {
		this.prop_val_max = prop_val_max;
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
	
	/**
	 * @val 最后修改时间
	 */
	public Date getLast_edit_date() {
		return last_edit_date;
	}
	
	/**
	 * @val 最后修改时间
	 */
	public void setLast_edit_date(Date last_edit_date) {
		this.last_edit_date = last_edit_date;
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
	 * @val 最后修改人ID
	 */
	public Long getLast_edit_user_id() {
		return last_edit_user_id;
	}
	
	/**
	 * @val 最后修改人ID
	 */
	public void setLast_edit_user_id(Long last_edit_user_id) {
		this.last_edit_user_id = last_edit_user_id;
	}
	
}