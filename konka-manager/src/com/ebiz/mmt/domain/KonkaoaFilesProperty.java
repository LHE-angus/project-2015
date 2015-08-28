package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public class KonkaoaFilesProperty extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 关联ID
	 */
	private Long link_id;

	/**
	 * 类别类型
	 */
	private Integer c_type;

	/**
	 * 类别编号
	 */
	private Long c_index;

	
	public String getC_desc() {
		return c_desc;
	}

	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Long getAdd_dept_id() {
		return add_dept_id;
	}

	public void setAdd_dept_id(Long add_dept_id) {
		this.add_dept_id = add_dept_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_type() {
		return add_type;
	}

	public void setAdd_type(Long add_type) {
		this.add_type = add_type;
	}

	/**
	 * 类别说明
	 */
	private String c_desc;

	/**
	 * 费用数量
	 */
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 费用金额
	 */
	private BigDecimal cost;

	/**
	 * 添加人
	 */
	private Long add_user_id;

	/**
	 * 添加部门
	 */
	private Long add_dept_id;

	/**
	 * 添加时间
	 */
	private Date add_date;

	/**
	 * 添加类型 ： 0 申请 1 审批
	 */
	private Long add_type;

	public KonkaoaFilesProperty() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	public Long getLink_id() {
		return link_id;
	}

	public void setC_type(Integer c_type) {
		this.c_type = c_type;
	}

	public Integer getC_type() {
		return c_type;
	}

	public void setC_index(Long c_index) {
		this.c_index = c_index;
	}

	public Long getC_index() {
		return c_index;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}