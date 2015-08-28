package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:50
 */
public class KonkaXxLogistics extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long logistics_id;
	
	private Long dept_id;
	
	private String md_name;
	
	private Long p_index_s;
	
	private Long p_index_e;
	
	private BigDecimal fee;
	
	private Date add_date;
	
	private Integer is_del;
	
	public KonkaXxLogistics() {

	}

	/**
	 * @val 物流模版ID
	 */
	public Long getLogistics_id() {
		return logistics_id;
	}
	
	/**
	 * @val 物流模版ID
	 */
	public void setLogistics_id(Long logistics_id) {
		this.logistics_id = logistics_id;
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
	 * @val 发货地区=分公司所在地
	 */
	public Long getP_index_s() {
		return p_index_s;
	}
	
	/**
	 * @val 发货地区=分公司所在地
	 */
	public void setP_index_s(Long p_index_s) {
		this.p_index_s = p_index_s;
	}
	
	/**
	 * @val 收货地区
	 */
	public Long getP_index_e() {
		return p_index_e;
	}
	
	/**
	 * @val 收货地区
	 */
	public void setP_index_e(Long p_index_e) {
		this.p_index_e = p_index_e;
	}
	
	/**
	 * @val 物流费用
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * @val 物流费用
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
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