package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-21 09:35:54
 */
public class KonkaXxZmdDailyStms extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long zmd_id;

	private Date stm_date;

	private Integer stm_type;

	private BigDecimal today_total_money;

	private BigDecimal his_total_money;

	private Integer today_total_counts;

	private Integer read_counts;

	private String stm_remarks;

	private String stm_title;

	public KonkaXxZmdDailyStms() {

	}

	/**
	 * @val 报表ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 报表ID
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
	 * @val 报表日期
	 */
	public Date getStm_date() {
		return stm_date;
	}

	/**
	 * @val 报表日期
	 */
	public void setStm_date(Date stm_date) {
		this.stm_date = stm_date;
	}

	/**
	 * @val 报表类型：0-未付款日报，1-已付款日报
	 */
	public Integer getStm_type() {
		return stm_type;
	}

	/**
	 * @val 报表类型：0-未付款日报，1-已付款日报
	 */
	public void setStm_type(Integer stm_type) {
		this.stm_type = stm_type;
	}

	/**
	 * @val 当日未/已付款总额
	 */
	public BigDecimal getToday_total_money() {
		return today_total_money;
	}

	/**
	 * @val 当日未/已付款总额
	 */
	public void setToday_total_money(BigDecimal today_total_money) {
		this.today_total_money = today_total_money;
	}

	/**
	 * @val 历史未/已付款总额
	 */
	public BigDecimal getHis_total_money() {
		return his_total_money;
	}

	/**
	 * @val 历史未/已付款总额
	 */
	public void setHis_total_money(BigDecimal his_total_money) {
		this.his_total_money = his_total_money;
	}

	/**
	 * @val 当日未/已付款订单数
	 */
	public Integer getToday_total_counts() {
		return today_total_counts;
	}

	/**
	 * @val 当日未/已付款订单数
	 */
	public void setToday_total_counts(Integer today_total_counts) {
		this.today_total_counts = today_total_counts;
	}

	/**
	 * @val 阅读次数
	 */
	public Integer getRead_counts() {
		return read_counts;
	}

	/**
	 * @val 阅读次数
	 */
	public void setRead_counts(Integer read_counts) {
		this.read_counts = read_counts;
	}

	/**
	 * @val 报表备注
	 */
	public String getStm_remarks() {
		return stm_remarks;
	}

	/**
	 * @val 报表备注
	 */
	public void setStm_remarks(String stm_remarks) {
		this.stm_remarks = stm_remarks;
	}

	/**
	 * @val 日报标题
	 */
	public String getStm_title() {
		return stm_title;
	}

	/**
	 * @val 日报标题
	 */
	public void setStm_title(String stmTitle) {
		stm_title = stmTitle;
	}

}