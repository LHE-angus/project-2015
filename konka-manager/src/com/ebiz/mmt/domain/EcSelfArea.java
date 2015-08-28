package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-15 15:20:15
 */
public class EcSelfArea extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String self_name;// 配送点名称

	private Long par_pindex; // 配送点上级pindex

	private String self_addr; // 配送点详细地址

	private String self_tel; // 电话

	private String area_pindex; // 管辖区域

	private BigDecimal self_money; // 配送费用

	private Date add_date; // 添加时间

	private Integer is_del; // 是否删除 0：否 1：是

	private Long p_index; // 配送点p_index

	public EcSelfArea() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSelf_name() {
		return self_name;
	}

	public void setSelf_name(String self_name) {
		this.self_name = self_name;
	}

	public Long getPar_pindex() {
		return par_pindex;
	}

	public void setPar_pindex(Long par_pindex) {
		this.par_pindex = par_pindex;
	}

	public String getSelf_addr() {
		return self_addr;
	}

	public void setSelf_addr(String self_addr) {
		this.self_addr = self_addr;
	}

	public String getSelf_tel() {
		return self_tel;
	}

	public void setSelf_tel(String self_tel) {
		this.self_tel = self_tel;
	}

	public String getArea_pindex() {
		return area_pindex;
	}

	public void setArea_pindex(String area_pindex) {
		this.area_pindex = area_pindex;
	}

	public BigDecimal getSelf_money() {
		return self_money;
	}

	public void setSelf_money(BigDecimal self_money) {
		this.self_money = self_money;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Long getP_index() {
		return p_index;
	}

	public void setP_index(Long pIndex) {
		p_index = pIndex;
	}

}