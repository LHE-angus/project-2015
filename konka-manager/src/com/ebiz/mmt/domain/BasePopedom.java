package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 */
public class BasePopedom extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 权限代码
	 */
	private Integer ppdm_code;

	/**
	 * 权限明细<br />
	 * 如果PPDM_CODE为5，此字段可表示为1+4<br />
	 */
	private String ppdm_detail;

	/**
	 * 权限说明
	 */
	private String ppdm_desc;

	/**
	 * 是否是基本代码<br />
	 */
	private Integer is_base;

	public BasePopedom() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPpdm_code() {
		return ppdm_code;
	}

	public void setPpdm_code(Integer ppdm_code) {
		this.ppdm_code = ppdm_code;
	}

	public String getPpdm_detail() {
		return ppdm_detail;
	}

	public void setPpdm_detail(String ppdm_detail) {
		this.ppdm_detail = ppdm_detail;
	}

	public String getPpdm_desc() {
		return ppdm_desc;
	}

	public void setPpdm_desc(String ppdm_desc) {
		this.ppdm_desc = ppdm_desc;
	}

	public Integer getIs_base() {
		return is_base;
	}

	public void setIs_base(Integer is_base) {
		this.is_base = is_base;
	}

}