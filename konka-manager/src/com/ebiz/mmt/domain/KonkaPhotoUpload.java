package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 16:38:19
 */
public class KonkaPhotoUpload extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long type_id;
	
	private String dept_name;
	
	private String r3_code;
	
	private String customer_name;
	
	private Long store_id;
	
	private String store_name;
	
	private Long report_user_id;
	
	private String report_user_name;
	
	private String report_memo;
	
	private Date report_date;
	
	private Long update_user_id;
	
	private String update_user_name;
	
	private Date update_date;
	
	private String remark;
	
	private Integer is_del;
	
	private Long dept_id;
	
	private Long type;
	
	private Integer status;
	
	public KonkaPhotoUpload() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getType_id() {
		return type_id;
	}
	
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getR3_code() {
		return r3_code;
	}
	
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	public Long getStore_id() {
		return store_id;
	}
	
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	public String getStore_name() {
		return store_name;
	}
	
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	public Long getReport_user_id() {
		return report_user_id;
	}
	
	public void setReport_user_id(Long report_user_id) {
		this.report_user_id = report_user_id;
	}
	
	public String getReport_user_name() {
		return report_user_name;
	}
	
	public void setReport_user_name(String report_user_name) {
		this.report_user_name = report_user_name;
	}
	
	public String getReport_memo() {
		return report_memo;
	}
	
	public void setReport_memo(String report_memo) {
		this.report_memo = report_memo;
	}
	
	public Date getReport_date() {
		return report_date;
	}
	
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
	public Long getUpdate_user_id() {
		return update_user_id;
	}
	
	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	public String getUpdate_user_name() {
		return update_user_name;
	}
	
	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getIs_del() {
		return is_del;
	}
	
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 类型 10表示网点
	 */
	public Long getType() {
		return type;
	}
	
	/**
	 * @val 类型 10表示网点
	 */
	public void setType(Long type) {
		this.type = type;
	}
	
	/**
	 * @val 状态 1保存2 提交
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @val 状态 1保存2 提交
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}