package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-08-23 14:38:52
 */
public class KonkaSalesDept2 extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long sales_id;
	
	private String sales_org_code;
	
	private String sales_org_name;
	
	private String p_sales_org_code;
	
	private Long p_sales_id;
	
	private Integer org_type;
	
	private Integer is_del;
	
	private Long dept_id;
	
	private String dept_code;
	
	public KonkaSalesDept2() {

	}

	/**
	 * @val SALES_ID
	 */
	public Long getSales_id() {
		return sales_id;
	}
	
	/**
	 * @val SALES_ID
	 */
	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
	
	public String getDept_code()
	{
		return dept_code;
	}

	public void setDept_code(String dept_code)
	{
		this.dept_code = dept_code;
	}

	/**
	 * @val 销售组织代码
	 */
	public String getSales_org_code() {
		return sales_org_code;
	}
	
	/**
	 * @val 销售组织代码
	 */
	public void setSales_org_code(String sales_org_code) {
		this.sales_org_code = sales_org_code;
	}
	
	/**
	 * @val 销售组织
	 */
	public String getSales_org_name() {
		return sales_org_name;
	}
	
	/**
	 * @val 销售组织
	 */
	public void setSales_org_name(String sales_org_name) {
		this.sales_org_name = sales_org_name;
	}
	
	/**
	 * @val 父组织代码
	 */
	public String getP_sales_org_code() {
		return p_sales_org_code;
	}
	
	/**
	 * @val 父组织代码
	 */
	public void setP_sales_org_code(String p_sales_org_code) {
		this.p_sales_org_code = p_sales_org_code;
	}
	
	/**
	 * @val 父SALES_ID
	 */
	public Long getP_sales_id() {
		return p_sales_id;
	}
	
	/**
	 * @val 父SALES_ID
	 */
	public void setP_sales_id(Long p_sales_id) {
		this.p_sales_id = p_sales_id;
	}
	
	/**
	 * @val 销售组织类型：1-分公司，2-经营部
	 */
	public Integer getOrg_type() {
		return org_type;
	}
	
	/**
	 * @val 销售组织类型：1-分公司，2-经营部
	 */
	public void setOrg_type(Integer org_type) {
		this.org_type = org_type;
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
	
	/**
	 * @val 对应的部门ID：和KONKA_DEPT的DEPT_ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 对应的部门ID：和KONKA_DEPT的DEPT_ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
}