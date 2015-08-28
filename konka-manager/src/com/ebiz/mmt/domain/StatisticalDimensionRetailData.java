package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 11:01:37
 */
public class StatisticalDimensionRetailData extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private BigDecimal id;
	
	private Long dept_id;
	
	private String dept_name;
	
	private Long model_id;
	
	private String model_name;
	
	private Long customer_id;
	
	private String customer_name;
	
	private String customer_r3_code;
	
	private Long time_id;
	
	private BigDecimal num;
	
	private BigDecimal money;
	
	private BigDecimal price;
	
	private BigDecimal cash_price;
	
	private BigDecimal sale_price;
	
	private Integer price_duan;
	
	private Integer data_source;
	
	private Date report_date;
	
	private Long report_id;
	
	private String report_name;
	
	private Integer report_type;
	
	private String mastercode;
	
	private Integer age;
	
	private Integer sex;
	
	private BigDecimal ck_id;
	
	private Integer audit_state;
	
	private BigDecimal retail_id;
	
	private Date update_date;
	
	private Date retail_update_date;
	
	private BigDecimal settle_money;
	
	private BigDecimal settle_num;
	
	private Integer is_del;
	
	public StatisticalDimensionRetailData() {

	}

	/**
	 * @val ID
	 */
	public BigDecimal getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 分公司名称
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * @val 分公司名称
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	/**
	 * @val 产品ID
	 */
	public Long getModel_id() {
		return model_id;
	}
	
	/**
	 * @val 产品ID
	 */
	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}
	
	/**
	 * @val 产品名称
	 */
	public String getModel_name() {
		return model_name;
	}
	
	/**
	 * @val 产品名称
	 */
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	/**
	 * @val 客户ID
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * @val 客户ID
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	/**
	 * @val 客户名称
	 */
	public String getCustomer_name() {
		return customer_name;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	/**
	 * @val 客户R3编码
	 */
	public String getCustomer_r3_code() {
		return customer_r3_code;
	}
	
	/**
	 * @val 客户R3编码
	 */
	public void setCustomer_r3_code(String customer_r3_code) {
		this.customer_r3_code = customer_r3_code;
	}
	
	/**
	 * @val 时间ID
	 */
	public Long getTime_id() {
		return time_id;
	}
	
	/**
	 * @val 时间ID
	 */
	public void setTime_id(Long time_id) {
		this.time_id = time_id;
	}
	
	/**
	 * @val 销售数量
	 */
	public BigDecimal getNum() {
		return num;
	}
	
	/**
	 * @val 销售数量
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	
	/**
	 * @val 销售额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * @val 销售额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	/**
	 * @val 销售单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 销售单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 现款价
	 */
	public BigDecimal getCash_price() {
		return cash_price;
	}
	
	/**
	 * @val 现款价
	 */
	public void setCash_price(BigDecimal cash_price) {
		this.cash_price = cash_price;
	}
	
	/**
	 * @val 零售指导价
	 */
	public BigDecimal getSale_price() {
		return sale_price;
	}
	
	/**
	 * @val 零售指导价
	 */
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
	
	/**
	 * @val 价格段
	 */
	public Integer getPrice_duan() {
		return price_duan;
	}
	
	/**
	 * @val 价格段
	 */
	public void setPrice_duan(Integer price_duan) {
		this.price_duan = price_duan;
	}
	
	/**
	 * @val 数据源
	 */
	public Integer getData_source() {
		return data_source;
	}
	
	/**
	 * @val 数据源
	 */
	public void setData_source(Integer data_source) {
		this.data_source = data_source;
	}
	
	/**
	 * @val 上报时间
	 */
	public Date getReport_date() {
		return report_date;
	}
	
	/**
	 * @val 上报时间
	 */
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
	/**
	 * @val 上报人ID
	 */
	public Long getReport_id() {
		return report_id;
	}
	
	/**
	 * @val 上报人ID
	 */
	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}
	
	/**
	 * @val 上报人姓名
	 */
	public String getReport_name() {
		return report_name;
	}
	
	/**
	 * @val 上报人姓名
	 */
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	
	/**
	 * @val 上报类型  -1通用  1正常客户拜访   2老客户重拾   3新客户开拓   4事务上报
	 */
	public Integer getReport_type() {
		return report_type;
	}
	
	/**
	 * @val 上报类型  -1通用  1正常客户拜访   2老客户重拾   3新客户开拓   4事务上报
	 */
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
	
	/**
	 * @val 身份证号码
	 */
	public String getMastercode() {
		return mastercode;
	}
	
	/**
	 * @val 身份证号码
	 */
	public void setMastercode(String mastercode) {
		this.mastercode = mastercode;
	}
	
	/**
	 * @val 年龄
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * @val 年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * @val 性别
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * @val 性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	/**
	 * @val 仓库ID
	 */
	public BigDecimal getCk_id() {
		return ck_id;
	}
	
	/**
	 * @val 仓库ID
	 */
	public void setCk_id(BigDecimal ck_id) {
		this.ck_id = ck_id;
	}
	
	/**
	 * @val 审核状态
	 */
	public Integer getAudit_state() {
		return audit_state;
	}
	
	/**
	 * @val 审核状态
	 */
	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}
	
	/**
	 * @val 原零售数据的ID
	 */
	public BigDecimal getRetail_id() {
		return retail_id;
	}
	
	/**
	 * @val 原零售数据的ID
	 */
	public void setRetail_id(BigDecimal retail_id) {
		this.retail_id = retail_id;
	}
	
	/**
	 * @val 同步时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 同步时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 原零售数据的修改时间
	 */
	public Date getRetail_update_date() {
		return retail_update_date;
	}
	
	/**
	 * @val 原零售数据的修改时间
	 */
	public void setRetail_update_date(Date retail_update_date) {
		this.retail_update_date = retail_update_date;
	}
	
	/**
	 * @val 结算额
	 */
	public BigDecimal getSettle_money() {
		return settle_money;
	}
	
	/**
	 * @val 结算额
	 */
	public void setSettle_money(BigDecimal settle_money) {
		this.settle_money = settle_money;
	}
	
	/**
	 * @val 结算量
	 */
	public BigDecimal getSettle_num() {
		return settle_num;
	}
	
	/**
	 * @val 结算量
	 */
	public void setSettle_num(BigDecimal settle_num) {
		this.settle_num = settle_num;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}