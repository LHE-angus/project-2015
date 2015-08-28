package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:02
 */
public class KonkaXxStdComp extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String comp_id;
	
	private String comp_name;
	
	public KonkaXxStdComp() {

	}

	/**
	 * @val 分公司标识
	 */
	public String getComp_id() {
		return comp_id;
	}
	
	/**
	 * @val 分公司标识
	 */
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	
	/**
	 * @val 分公司名称
	 */
	public String getComp_name() {
		return comp_name;
	}
	
	/**
	 * @val 分公司名称
	 */
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	
}