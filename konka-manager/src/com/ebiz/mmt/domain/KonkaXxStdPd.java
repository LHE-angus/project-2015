package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;
import java.util.Date;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:02
 */
public class KonkaXxStdPd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String md_name;
	
	private String spec;
	
	private Integer md_type;
	
	private Long pd_id;
	
	private Long add_user_id;
	
	private Date add_date;
	
	public KonkaXxStdPd() {

	}

	/**
	 * @val 型号名称
	 */
	public String getMd_name() {
		return md_name;
	}
	
	/**
	 * @val 型号名称
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	/**
	 * @val 规格
	 */
	public String getSpec() {
		return spec;
	}
	
	/**
	 * @val 规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	/**
	 * @val 种类：0-主销 1-停产（清理） 2-退市
	 */
	public Integer getMd_type() {
		return md_type;
	}
	
	/**
	 * @val 种类：0-主销 1-停产（清理） 2-退市
	 */
	public void setMd_type(Integer md_type) {
		this.md_type = md_type;
	}
	
	/**
	 * @val 产品大类ID
	 */
	public Long getPd_id() {
		return pd_id;
	}
	
	/**
	 * @val 产品大类ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
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
	
}