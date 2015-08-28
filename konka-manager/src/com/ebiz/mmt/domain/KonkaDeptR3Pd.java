package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-18 15:28:00
 */
public class KonkaDeptR3Pd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long pd_id;
	
	private String pd_sn;
	
	private BigDecimal price;
	
	private BigDecimal min_price;
	
	private BigDecimal max_price;
	
	private Integer year;
	
	private Integer month;
	
	private Date s_date;
	
	private Date e_date;
	
	private String r3_code;
	
	private String c_name;
	
	private Long c_type;
	
	private Long dept_id;
	
	private String dept_name;
	
	private String dept_sn;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private String add_user_name;
	
	private Date update_date;
	
	private Long update_user_id;
	
	private String update_user_name;
	
	private Integer is_del;
	
	public KonkaDeptR3Pd() {

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
	 * @val 产品编号
	 */
	public Long getPd_id() {
		return pd_id;
	}
	
	/**
	 * @val 产品编号
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
	/**
	 * @val 产品型号
	 */
	public String getPd_sn() {
		return pd_sn;
	}
	
	/**
	 * @val 产品型号
	 */
	public void setPd_sn(String pd_sn) {
		this.pd_sn = pd_sn;
	}
	
	/**
	 * @val 价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 最低价
	 */
	public BigDecimal getMin_price() {
		return min_price;
	}
	
	/**
	 * @val 最低价
	 */
	public void setMin_price(BigDecimal min_price) {
		this.min_price = min_price;
	}
	
	/**
	 * @val 最高价
	 */
	public BigDecimal getMax_price() {
		return max_price;
	}
	
	/**
	 * @val 最高价
	 */
	public void setMax_price(BigDecimal max_price) {
		this.max_price = max_price;
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
	 * @val 上架时间
	 */
	public Date getS_date() {
		return s_date;
	}
	
	/**
	 * @val 上架时间
	 */
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	
	/**
	 * @val 下架时间
	 */
	public Date getE_date() {
		return e_date;
	}
	
	/**
	 * @val 下架时间
	 */
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	
	/**
	 * @val 客户R3编码
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val 客户R3编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	/**
	 * @val 客户类型
	 */
	public String getC_name() {
		return c_name;
	}
	
	/**
	 * @val 客户类型
	 */
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	/**
	 * @val 客户类型ID
	 */
	public Long getC_type() {
		return c_type;
	}
	
	/**
	 * @val 客户类型ID
	 */
	public void setC_type(Long c_type) {
		this.c_type = c_type;
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
	 * @val 分公司编码
	 */
	public String getDept_sn() {
		return dept_sn;
	}
	
	/**
	 * @val 分公司编码
	 */
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
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
	
	/**
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 添加人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	/**
	 * @val 修改时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 修改时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 修改人ID
	 */
	public Long getUpdate_user_id() {
		return update_user_id;
	}
	
	/**
	 * @val 修改人ID
	 */
	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	/**
	 * @val 修改人
	 */
	public String getUpdate_user_name() {
		return update_user_name;
	}
	
	/**
	 * @val 修改人
	 */
	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
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
	
}