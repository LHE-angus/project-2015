package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-03 09:13:11
 */
public class KonkaYwHzTj extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long user_id;
	
	private String user_name;
	
	private Long dept_id;
	
	private Long dept_one;
	
	private String dept_name_one;
	
	private Long dept_two;
	
	private String dept_name_two;
	
	private Long dept_three;
	
	private String dept_name_three;
	
	private Integer jh_visit_cust_count;
	
	private Integer jh_visit_shop_count;
	
	private Integer jh_visit_count;
	
	private Integer bf_count;
	
	private Integer bf_cust_count;
	
	private Integer bf_shop_count;
	
	private Integer zc_cust_visit_count;
	
	private Integer zc_shop_visit_count;
	
	private Integer cs_cust_visit_count;
	
	private Integer cs_shop_visit_count;
	
	private Integer jh_dev_cust_count;
	
	private Integer kt_cust_count;
	
	private Integer kt_cust_visit_count;
	
	private BigDecimal custbfb;
	
	private BigDecimal shopbfb;
	
	private BigDecimal bf_bfb;
	
	private Date update_time;
	
	private Date last_update_time;
	
	public KonkaYwHzTj() {

	}

	/**
	 * @val 用户id
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 用户id
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 用户部门id
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 用户部门id
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 部门1
	 */
	public Long getDept_one() {
		return dept_one;
	}
	
	/**
	 * @val 部门1
	 */
	public void setDept_one(Long dept_one) {
		this.dept_one = dept_one;
	}
	
	/**
	 * @val 部门1名称
	 */
	public String getDept_name_one() {
		return dept_name_one;
	}
	
	/**
	 * @val 部门1名称
	 */
	public void setDept_name_one(String dept_name_one) {
		this.dept_name_one = dept_name_one;
	}
	
	/**
	 * @val 部门2
	 */
	public Long getDept_two() {
		return dept_two;
	}
	
	/**
	 * @val 部门2
	 */
	public void setDept_two(Long dept_two) {
		this.dept_two = dept_two;
	}
	
	/**
	 * @val 部门2名称
	 */
	public String getDept_name_two() {
		return dept_name_two;
	}
	
	/**
	 * @val 部门2名称
	 */
	public void setDept_name_two(String dept_name_two) {
		this.dept_name_two = dept_name_two;
	}
	
	/**
	 * @val 部门3
	 */
	public Long getDept_three() {
		return dept_three;
	}
	
	/**
	 * @val 部门3
	 */
	public void setDept_three(Long dept_three) {
		this.dept_three = dept_three;
	}
	
	/**
	 * @val 部门3名称
	 */
	public String getDept_name_three() {
		return dept_name_three;
	}
	
	/**
	 * @val 部门3名称
	 */
	public void setDept_name_three(String dept_name_three) {
		this.dept_name_three = dept_name_three;
	}
	
	/**
	 * @val 计划拜访拜客户数
	 */
	public Integer getJh_visit_cust_count() {
		return jh_visit_cust_count;
	}
	
	/**
	 * @val 计划拜访拜客户数
	 */
	public void setJh_visit_cust_count(Integer jh_visit_cust_count) {
		this.jh_visit_cust_count = jh_visit_cust_count;
	}
	
	/**
	 * @val 计划拜访拜访门店数
	 */
	public Integer getJh_visit_shop_count() {
		return jh_visit_shop_count;
	}
	
	/**
	 * @val 计划拜访拜访门店数
	 */
	public void setJh_visit_shop_count(Integer jh_visit_shop_count) {
		this.jh_visit_shop_count = jh_visit_shop_count;
	}
	
	/**
	 * @val 计划拜访拜访数
	 */
	public Integer getJh_visit_count() {
		return jh_visit_count;
	}
	
	/**
	 * @val 计划拜访拜访数
	 */
	public void setJh_visit_count(Integer jh_visit_count) {
		this.jh_visit_count = jh_visit_count;
	}
	
	/**
	 * @val 拜访次数
	 */
	public Integer getBf_count() {
		return bf_count;
	}
	
	/**
	 * @val 拜访次数
	 */
	public void setBf_count(Integer bf_count) {
		this.bf_count = bf_count;
	}
	
	/**
	 * @val 拜访客户数
	 */
	public Integer getBf_cust_count() {
		return bf_cust_count;
	}
	
	/**
	 * @val 拜访客户数
	 */
	public void setBf_cust_count(Integer bf_cust_count) {
		this.bf_cust_count = bf_cust_count;
	}
	
	/**
	 * @val 拜访门店数
	 */
	public Integer getBf_shop_count() {
		return bf_shop_count;
	}
	
	/**
	 * @val 拜访门店数
	 */
	public void setBf_shop_count(Integer bf_shop_count) {
		this.bf_shop_count = bf_shop_count;
	}
	
	/**
	 * @val 正常客户拜访数
	 */
	public Integer getZc_cust_visit_count() {
		return zc_cust_visit_count;
	}
	
	/**
	 * @val 正常客户拜访数
	 */
	public void setZc_cust_visit_count(Integer zc_cust_visit_count) {
		this.zc_cust_visit_count = zc_cust_visit_count;
	}
	
	/**
	 * @val 正常门店拜访数
	 */
	public Integer getZc_shop_visit_count() {
		return zc_shop_visit_count;
	}
	
	/**
	 * @val 正常门店拜访数
	 */
	public void setZc_shop_visit_count(Integer zc_shop_visit_count) {
		this.zc_shop_visit_count = zc_shop_visit_count;
	}
	
	/**
	 * @val 重拾客户数
	 */
	public Integer getCs_cust_visit_count() {
		return cs_cust_visit_count;
	}
	
	/**
	 * @val 重拾客户数
	 */
	public void setCs_cust_visit_count(Integer cs_cust_visit_count) {
		this.cs_cust_visit_count = cs_cust_visit_count;
	}
	
	/**
	 * @val 重拾门店数
	 */
	public Integer getCs_shop_visit_count() {
		return cs_shop_visit_count;
	}
	
	/**
	 * @val 重拾门店数
	 */
	public void setCs_shop_visit_count(Integer cs_shop_visit_count) {
		this.cs_shop_visit_count = cs_shop_visit_count;
	}
	
	/**
	 * @val 计划开拓客户数
	 */
	public Integer getJh_dev_cust_count() {
		return jh_dev_cust_count;
	}
	
	/**
	 * @val 计划开拓客户数
	 */
	public void setJh_dev_cust_count(Integer jh_dev_cust_count) {
		this.jh_dev_cust_count = jh_dev_cust_count;
	}
	
	/**
	 * @val 开拓客户数
	 */
	public Integer getKt_cust_count() {
		return kt_cust_count;
	}
	
	/**
	 * @val 开拓客户数
	 */
	public void setKt_cust_count(Integer kt_cust_count) {
		this.kt_cust_count = kt_cust_count;
	}
	
	/**
	 * @val 开拓客户拜访数
	 */
	public Integer getKt_cust_visit_count() {
		return kt_cust_visit_count;
	}
	
	/**
	 * @val 开拓客户拜访数
	 */
	public void setKt_cust_visit_count(Integer kt_cust_visit_count) {
		this.kt_cust_visit_count = kt_cust_visit_count;
	}
	
	/**
	 * @val 客户拜访完成率
	 */
	public BigDecimal getCustbfb() {
		return custbfb;
	}
	
	/**
	 * @val 客户拜访完成率
	 */
	public void setCustbfb(BigDecimal custbfb) {
		this.custbfb = custbfb;
	}
	
	/**
	 * @val 门店拜访完成率
	 */
	public BigDecimal getShopbfb() {
		return shopbfb;
	}
	
	/**
	 * @val 门店拜访完成率
	 */
	public void setShopbfb(BigDecimal shopbfb) {
		this.shopbfb = shopbfb;
	}
	
	/**
	 * @val 计划拜访完成率
	 */
	public BigDecimal getBf_bfb() {
		return bf_bfb;
	}
	
	/**
	 * @val 计划拜访完成率
	 */
	public void setBf_bfb(BigDecimal bf_bfb) {
		this.bf_bfb = bf_bfb;
	}
	
	/**
	 * @val 当前月份数据
	 */
	public Date getUpdate_time() {
		return update_time;
	}
	
	/**
	 * @val 当前月份数据
	 */
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	/**
	 * @val 最后跟新时间
	 */
	public Date getLast_update_time() {
		return last_update_time;
	}
	
	/**
	 * @val 最后跟新时间
	 */
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}
	
}