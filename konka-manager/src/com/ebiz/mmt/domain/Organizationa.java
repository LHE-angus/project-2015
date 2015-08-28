package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 05:05:22
 */
public class Organizationa extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String org_name;

	private String org_code;

	private String org_tel;

	private String org_fax;

	private Long par_org_id;

	private Integer order_value;

	private Integer is_del;

	/**
	 * 部门负责人ＩＤ
	 */
	private Long org_heads_id;

	public Organizationa() {

	}

	public Long getId() {
		return this.id;
	}

	public Integer getIs_del() {
		return this.is_del;
	}

	public Integer getOrder_value() {
		return this.order_value;
	}

	public String getOrg_code() {
		return this.org_code;
	}

	public String getOrg_fax() {
		return this.org_fax;
	}

	public String getOrg_name() {
		return this.org_name;
	}

	public String getOrg_tel() {
		return this.org_tel;
	}

	public Long getPar_org_id() {
		return this.par_org_id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public void setOrg_fax(String org_fax) {
		this.org_fax = org_fax;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public void setOrg_tel(String org_tel) {
		this.org_tel = org_tel;
	}

	public void setPar_org_id(Long par_org_id) {
		this.par_org_id = par_org_id;
	}

	public Long getOrg_heads_id() {
		return org_heads_id;
	}

	public void setOrg_heads_id(Long org_heads_id) {
		this.org_heads_id = org_heads_id;
	}

}