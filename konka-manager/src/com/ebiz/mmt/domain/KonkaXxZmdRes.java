package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-21 16:06:04
 */
public class KonkaXxZmdRes extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long zmd_id;
	
	private String res_name;
	
	private String res_pro;
	
	private Long res_id;
	
	public KonkaXxZmdRes() {

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
	 * @val 专卖店ID
	 */
	public Long getZmd_id() {
		return zmd_id;
	}
	
	/**
	 * @val 专卖店ID
	 */
	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}
	
	/**
	 * @val 资源名
	 */
	public String getRes_name() {
		return res_name;
	}
	
	/**
	 * @val 资源名
	 */
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	
	/**
	 * @val 资源规格
	 */
	public String getRes_pro() {
		return res_pro;
	}
	
	/**
	 * @val 资源规格
	 */
	public void setRes_pro(String res_pro) {
		this.res_pro = res_pro;
	}
	
	/**
	 * @val 资源ID
	 */
	public Long getRes_id() {
		return res_id;
	}
	
	/**
	 * @val 资源ID
	 */
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}
	
}