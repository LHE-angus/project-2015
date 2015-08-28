package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-12 16:10:13
 */
public class FileReportType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String f_type;
	
	private String f_type_dec;
	
	private String dept_sn;
	
	private String dept_name;
	
	private Long dept_id;
	
	private String add_user_name;
	
	private Long add_user_id;
	
	private Date add_date;
	
	private Integer is_del;
	
	public FileReportType() {

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
	 * @val 文件类型
	 */
	public String getF_type() {
		return f_type;
	}
	
	/**
	 * @val 文件类型
	 */
	public void setF_type(String f_type) {
		this.f_type = f_type;
	}
	
	/**
	 * @val 文件类型描述
	 */
	public String getF_type_dec() {
		return f_type_dec;
	}
	
	/**
	 * @val 文件类型描述
	 */
	public void setF_type_dec(String f_type_dec) {
		this.f_type_dec = f_type_dec;
	}
	
	/**
	 * @val 分公司编码
	 */
	public String getDept_sn() {
		return dept_sn;
	}
	
	/**
	 * @val 分公司编码
	 */
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
	}
	
	/**
	 * @val 分公司
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * @val 分公司
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	 * @val 添加人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
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
	
	/**
	 * @val 是否删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}