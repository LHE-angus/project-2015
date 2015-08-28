package com.ebiz.mmt.web.struts.webservice.dto;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.web.struts.inter.form.BaseInterForm;

/**
 * 为OA系统提供查询的服务的返回数据对象
 * 
 */
public class CXYSalesInfo extends BaseInterForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 由于OA单方面要求在结果里面把多列转一行,所以以下代码没有意义
	private List<HashMap<String, Object>> salesInfoList;


	private String all_money;
	private String all_num;

	private String r3_job_id;

	private String report_name;

	// 此促销员在职情况 0正常,-1异常
	private String is_valid;

	// 可多个;分割
	private String store_id;
	// 可多个;分割
	private String store_name;


	public void setSalesInfoList(List<HashMap<String, Object>> salesInfoList) {
		this.salesInfoList = salesInfoList;
	}

	/**
	 * @return the r3_job_id
	 */
	public String getR3_job_id() {
		return r3_job_id;
	}

	/**
	 * @param r3_job_id
	 *            the r3_job_id to set
	 */
	public void setR3_job_id(String r3_job_id) {
		this.r3_job_id = r3_job_id;
	}

	/**
	 * @return the report_name
	 */
	public String getReport_name() {
		return report_name;
	}

	/**
	 * @param report_name
	 *            the report_name to set
	 */
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}



	/**
	 * @return the all_money
	 */
	public String getAll_money() {
		return all_money;
	}

	/**
	 * @param all_money
	 *            the all_money to set
	 */
	public void setAll_money(String all_money) {
		this.all_money = all_money;
	}

	/**
	 * @return the all_num
	 */
	public String getAll_num() {
		return all_num;
	}

	/**
	 * @param all_num
	 *            the all_num to set
	 */
	public void setAll_num(String all_num) {
		this.all_num = all_num;
	}

	/**
	 * @return the store_id
	 */
	public String getStore_id() {
		return store_id;
	}

	/**
	 * @param store_id
	 *            the store_id to set
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	/**
	 * @return the store_name
	 */
	public String getStore_name() {
		return store_name;
	}

	/**
	 * @param store_name
	 *            the store_name to set
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

}
