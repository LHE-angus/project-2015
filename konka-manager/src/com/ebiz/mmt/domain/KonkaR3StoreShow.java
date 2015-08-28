package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-05 14:37:11
 */
public class KonkaR3StoreShow extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dept_id;
	
	private String dept_name;
	
	private Long store_id;
	
	private String store_name;
	
	private Long add_user_id;
	
	private Integer year;
	
	private Integer month;
	
	private BigDecimal task_money;
	
	private String remark;
	
	private Date add_date;
	
	private Integer task_type;
	
	private String size;
	
	private String task_name;
	
	private String category_name;//品类名称
	
	private BigDecimal num;//数量
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public KonkaR3StoreShow() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public BigDecimal getTask_money() {
		return task_money;
	}
	
	public void setTask_money(BigDecimal task_money) {
		this.task_money = task_money;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Integer getTask_type() {
		return task_type;
	}
	
	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getTask_name() {
		return task_name;
	}
	
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	
}