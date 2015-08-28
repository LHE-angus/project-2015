package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,Huan
 */
public class PdProperty extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String property_name;

	private Long pd_type;

	private String pd_type_sign;

	private String p_unit;

	private Short p_type;

	private Short is_mandatory;

	List<PdPropertyValue> pdPropertyValueList;

	List<String> comparePdPropertyValues = new ArrayList<String>();

	public List<PdPropertyValue> getPdPropertyValueList() {
		return pdPropertyValueList;
	}

	public void setPdPropertyValueList(List<PdPropertyValue> pdPropertyValueList) {
		this.pdPropertyValueList = pdPropertyValueList;
	}

	public PdProperty() {

	}

	public PdProperty(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public String getPd_type_sign() {
		return pd_type_sign;
	}

	public void setPd_type_sign(String pd_type_sign) {
		this.pd_type_sign = pd_type_sign;
	}

	public String getP_unit() {
		return p_unit;
	}

	public void setP_unit(String p_unit) {
		this.p_unit = p_unit;
	}

	public Short getP_type() {
		return p_type;
	}

	public void setP_type(Short p_type) {
		this.p_type = p_type;
	}

	public List<String> getComparePdPropertyValues() {
		return comparePdPropertyValues;
	}

	public void setComparePdPropertyValues(List<String> comparePdPropertyValues) {
		this.comparePdPropertyValues = comparePdPropertyValues;
	}

	public Short getIs_mandatory() {
		return is_mandatory;
	}

	public void setIs_mandatory(Short isMandatory) {
		is_mandatory = isMandatory;
	}

}