package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaPdModelTask extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Integer year;

	private Integer month;

	private String md_name;

	private Integer is_sails;

	private Long sc_plan_num;

	private Long sails_plan_num;

	private Date add_date;

	// 报表传参使用,不需要保存 zhj
	private String cyear;
	private String cmonth;
	private String cday;
	private String lyear;// 昨天的年
	private String lmonth;// 昨天的月
	private String lday;// 昨天的天
	private String llyear;// 上个月的年
	private String llmonth;// 上个月的月

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getCmonth() {
		return cmonth;
	}

	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}

	public String getCday() {
		return cday;
	}

	public void setCday(String cday) {
		this.cday = cday;
	}

	public String getLyear() {
		return lyear;
	}

	public void setLyear(String lyear) {
		this.lyear = lyear;
	}

	public String getLmonth() {
		return lmonth;
	}

	public void setLmonth(String lmonth) {
		this.lmonth = lmonth;
	}

	public String getLday() {
		return lday;
	}

	public void setLday(String lday) {
		this.lday = lday;
	}

	public String getLlyear() {
		return llyear;
	}

	public void setLlyear(String llyear) {
		this.llyear = llyear;
	}

	public String getLlmonth() {
		return llmonth;
	}

	public void setLlmonth(String llmonth) {
		this.llmonth = llmonth;
	}

	public KonkaPdModelTask() {

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
	 * @val 年份
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @val 年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @val 月份
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @val 月份
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @val 产品型号
	 */
	public String getMd_name() {
		return md_name;
	}

	/**
	 * @val 产品型号
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	/**
	 * @val 是否退市
	 */
	public Integer getIs_sails() {
		return is_sails;
	}

	/**
	 * @val 是否退市
	 */
	public void setIs_sails(Integer is_sails) {
		this.is_sails = is_sails;
	}

	/**
	 * @val 生产计划
	 */
	public Long getSc_plan_num() {
		return sc_plan_num;
	}

	/**
	 * @val 生产计划
	 */
	public void setSc_plan_num(Long sc_plan_num) {
		this.sc_plan_num = sc_plan_num;
	}

	/**
	 * @val 销售计划
	 */
	public Long getSails_plan_num() {
		return sails_plan_num;
	}

	/**
	 * @val 销售计划
	 */
	public void setSails_plan_num(Long sails_plan_num) {
		this.sails_plan_num = sails_plan_num;
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