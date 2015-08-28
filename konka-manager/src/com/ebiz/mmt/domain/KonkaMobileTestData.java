package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:57
 */
public class KonkaMobileTestData extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String pct_code;

	private String memo;

	private Long channel_a_id;

	private String channel_a_name;

	private Long channel_b_id;

	private String channel_b_name;

	private Long org_id;

	private String org_name;

	private Long dept_id;

	private String dept_name;

	private Long subcomp_id;

	private String subcomp_name;

	private Long office_id;

	private String office_name;

	private Long measure_id;

	private String measure_name;

	private Long prototype_id;

	private String prototype_name;

	private Long category_id;

	private String category_name;

	private Long model_id;

	private String model_name;

	private Long num;

	private Long report_id;

	private String report_name;

	private Date report_date;

	private Integer status;

	private BigDecimal price;

	private Date up_date;

	private Date down_date;

	private Integer down_cause;

	private Long plan_fp_id;// 用来区分 临时性上架或者计划性上架

	private String code;// 串码

	public KonkaMobileTestData() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPct_code() {
		return pct_code;
	}

	public void setPct_code(String pct_code) {
		this.pct_code = pct_code;
	}

	public Long getChannel_a_id() {
		return channel_a_id;
	}

	public void setChannel_a_id(Long channel_a_id) {
		this.channel_a_id = channel_a_id;
	}

	public String getChannel_a_name() {
		return channel_a_name;
	}

	public void setChannel_a_name(String channel_a_name) {
		this.channel_a_name = channel_a_name;
	}

	public Long getChannel_b_id() {
		return channel_b_id;
	}

	public void setChannel_b_id(Long channel_b_id) {
		this.channel_b_id = channel_b_id;
	}

	public String getChannel_b_name() {
		return channel_b_name;
	}

	public void setChannel_b_name(String channel_b_name) {
		this.channel_b_name = channel_b_name;
	}

	public Long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Long getSubcomp_id() {
		return subcomp_id;
	}

	public void setSubcomp_id(Long subcomp_id) {
		this.subcomp_id = subcomp_id;
	}

	public String getSubcomp_name() {
		return subcomp_name;
	}

	public void setSubcomp_name(String subcomp_name) {
		this.subcomp_name = subcomp_name;
	}

	public Long getOffice_id() {
		return office_id;
	}

	public void setOffice_id(Long office_id) {
		this.office_id = office_id;
	}

	public String getOffice_name() {
		return office_name;
	}

	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}

	public Long getMeasure_id() {
		return measure_id;
	}

	public void setMeasure_id(Long measure_id) {
		this.measure_id = measure_id;
	}

	public String getMeasure_name() {
		return measure_name;
	}

	public void setMeasure_name(String measure_name) {
		this.measure_name = measure_name;
	}

	public Long getPrototype_id() {
		return prototype_id;
	}

	public void setPrototype_id(Long prototype_id) {
		this.prototype_id = prototype_id;
	}

	public String getPrototype_name() {
		return prototype_name;
	}

	public void setPrototype_name(String prototype_name) {
		this.prototype_name = prototype_name;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Long getModel_id() {
		return model_id;
	}

	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	public Date getDown_date() {
		return down_date;
	}

	public void setDown_date(Date down_date) {
		this.down_date = down_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @val 下架原因
	 */
	public Integer getDown_cause() {
		return down_cause;
	}

	/**
	 * @val 下架原因
	 */
	public void setDown_cause(Integer down_cause) {
		this.down_cause = down_cause;
	}

	/**
	 * @val 分配计划id
	 */
	public Long getPlan_fp_id() {
		return plan_fp_id;
	}

	/**
	 * @val 分配计划id
	 */
	public void setPlan_fp_id(Long plan_fp_id) {
		this.plan_fp_id = plan_fp_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}