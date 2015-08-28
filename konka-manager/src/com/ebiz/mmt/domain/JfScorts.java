package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
public class JfScorts extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String user_sn;

	private BigDecimal total_scorts;

	private Integer is_del;// 主要用来统计换卡的总积分 0：正常 1：停用

	public JfScorts() {

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
	 * @val 会员卡号
	 */
	public String getUser_sn() {
		return user_sn;
	}

	/**
	 * @val 会员卡号
	 */
	public void setUser_sn(String user_sn) {
		this.user_sn = user_sn;
	}

	/**
	 * @val 总积分
	 */
	public BigDecimal getTotal_scorts() {
		return total_scorts;
	}

	/**
	 * @val 总积分
	 */
	public void setTotal_scorts(BigDecimal total_scorts) {
		this.total_scorts = total_scorts;
	}

	/**
	 * @val 是否停用 0正常 1停用
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否停用 0正常 1停用
	 */
	public void setIs_del(Integer isDel) {
		is_del = isDel;
	}

}