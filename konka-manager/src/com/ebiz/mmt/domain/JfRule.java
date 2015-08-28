package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:01
 */
public class JfRule extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String jf_name;

	private Long dept_id;

	private String pd_id;

	private Integer jf_type;

	private BigDecimal jf_value;

	private Integer jf_avl_type;

	private Date jf_avl_start;

	private Date jf_avl_end;

	private Integer jf_style;

	private Date add_date;

	public JfRule() {

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
	 * @val 积分规则名称
	 */
	public String getJf_name() {
		return jf_name;
	}

	/**
	 * @val 积分规则名称
	 */
	public void setJf_name(String jf_name) {
		this.jf_name = jf_name;
	}

	/**
	 * @val 分公司
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 分公司
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 产品型号（参见konka_pe_pd_model）
	 */
	public String getPd_id() {
		return pd_id;
	}

	/**
	 * @val 产品型号（参见konka_pe_pd_model）
	 */
	public void setPd_id(String pd_id) {
		this.pd_id = pd_id;
	}

	/**
	 * @val 积分类型：1-按数量固定返积分；2-按金额比例返积分
	 */
	public Integer getJf_type() {
		return jf_type;
	}

	/**
	 * @val 积分类型：1-按数量固定返积分；2-按金额比例返积分
	 */
	public void setJf_type(Integer jf_type) {
		this.jf_type = jf_type;
	}

	/**
	 * @val 积分值/比例
	 */
	public BigDecimal getJf_value() {
		return jf_value;
	}

	/**
	 * @val 积分值/比例
	 */
	public void setJf_value(BigDecimal jf_value) {
		this.jf_value = jf_value;
	}

	/**
	 * @val 有效期：1-长期有效；2-时间段有效
	 */
	public Integer getJf_avl_type() {
		return jf_avl_type;
	}

	/**
	 * @val 有效期：1-长期有效；2-时间段有效
	 */
	public void setJf_avl_type(Integer jf_avl_type) {
		this.jf_avl_type = jf_avl_type;
	}

	/**
	 * @val 有效期开始时间
	 */
	public Date getJf_avl_start() {
		return jf_avl_start;
	}

	/**
	 * @val 有效期开始时间
	 */
	public void setJf_avl_start(Date jf_avl_start) {
		this.jf_avl_start = jf_avl_start;
	}

	/**
	 * @val 有效期截止时间
	 */
	public Date getJf_avl_end() {
		return jf_avl_end;
	}

	/**
	 * @val 有效期截止时间
	 */
	public void setJf_avl_end(Date jf_avl_end) {
		this.jf_avl_end = jf_avl_end;
	}

	/**
	 * @val 积分种类：1-专卖店
	 */
	public Integer getJf_style() {
		return jf_style;
	}

	/**
	 * @val 积分种类：1-专卖店
	 */
	public void setJf_style(Integer jf_style) {
		this.jf_style = jf_style;
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