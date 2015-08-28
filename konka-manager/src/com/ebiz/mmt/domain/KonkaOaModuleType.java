package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-16 17:46:07
 */
public class KonkaOaModuleType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long module_id;
	
	private String module_name;
	
	private String module_desc;
	
	private Long dept_id;
	
	private Integer order_value;
	
	private Integer is_del;
	
	private Long oa_type;
	
	public KonkaOaModuleType() {

	}

	/**
	 * @val 模板ID
	 */
	public Long getModule_id() {
		return module_id;
	}
	
	/**
	 * @val 模板ID
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}
	
	/**
	 * @val 模板名称
	 */
	public String getModule_name() {
		return module_name;
	}
	
	/**
	 * @val 模板名称
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	
	/**
	 * @val 模板说明
	 */
	public String getModule_desc() {
		return module_desc;
	}
	
	/**
	 * @val 模板说明
	 */
	public void setModule_desc(String module_desc) {
		this.module_desc = module_desc;
	}
	
	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 0：未删
	 * @val 1：已删（逻辑删）
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 0：未删
	 * @val 1：已删（逻辑删）
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 文件类别
	 */
	public Long getOa_type() {
		return oa_type;
	}
	
	/**
	 * @val 文件类别
	 */
	public void setOa_type(Long oa_type) {
		this.oa_type = oa_type;
	}
	
}