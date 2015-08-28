package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:11
 */
public class PdModelPropsVal extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long pd_id;

	private Long prop_id;

	private String prop_value;

	private String prop_item_ids;

	private List<PdModelPropsVal> pdModelPropsValList;

	private List<JxcPd> jxcPdList;

	public List<JxcPd> getJxcPdList() {
		return jxcPdList;
	}

	public void setJxcPdList(List<JxcPd> jxcPdList) {
		this.jxcPdList = jxcPdList;
	}

	public List<PdModelPropsVal> getPdModelPropsValList() {
		return pdModelPropsValList;
	}

	public void setPdModelPropsValList(List<PdModelPropsVal> pdModelPropsValList) {
		this.pdModelPropsValList = pdModelPropsValList;
	}

	public PdModelPropsVal() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 产品型号ID
	 */
	public Long getPd_id() {
		return pd_id;
	}

	/**
	 * @val 产品型号ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
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