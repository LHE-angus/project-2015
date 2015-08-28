package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,ZhiXiang
 * @version 2013-7-9
 * @desc 任务系数管理
 */
public class KonkaPlanMoney extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer p;
	
	private Integer y;
	
	private BigDecimal m;
	
	public KonkaPlanMoney() {

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
	 * @val 指标维度：0-年度指标，1-月度指标
	 */
	public Integer getP() {
		return p;
	}
	
	/**
	 * @val 指标维度：0-年度指标，1-月度指标
	 */
	public void setP(Integer p) {
		this.p = p;
	}
	
	/**
	 * @val 年度/月度：年度YYYY，月度YYYYMM
	 */
	public Integer getY() {
		return y;
	}
	
	/**
	 * @val 年度/月度：年度YYYY，月度YYYYMM
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	
	/**
	 * @val 任务总额（万元）
	 */
	public BigDecimal getM() {
		return m;
	}
	
	/**
	 * @val 任务总额（万元）
	 */
	public void setM(BigDecimal m) {
		this.m = m;
	}
	
}