package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-10-15 16:57
 */
public class KonkaJxcPcInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Integer pc_type;

	private Integer own_sys;

	private Long store_id;

	private Long pd_type_id;

	private String pd_type_name;

	private Long brand_id;

	private String brand_name;

	private Long pd_id;

	private String pd_name;

	private Long pc_num;

	private String pc_result;

	private String remark;

	private Date add_date;

	private Long add_user_id;

	private Long add_dept_id;

	private Integer is_del;

	private String pc_desc;

	private Long py_pk_num;

	private BigDecimal cur_cost_price;

	private Date pc_date;

	private KonkaJxcStoreState konkaJxcStoreState;

	public KonkaJxcPcInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Integer getPc_type() {
		return pc_type;
	}

	public void setPc_type(Integer pc_type) {
		this.pc_type = pc_type;
	}

	public Integer getOwn_sys() {
		return own_sys;
	}

	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public Long getPd_type_id() {
		return pd_type_id;
	}

	public void setPd_type_id(Long pd_type_id) {
		this.pd_type_id = pd_type_id;
	}

	public String getPd_type_name() {
		return pd_type_name;
	}

	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Long getPc_num() {
		return pc_num;
	}

	public void setPc_num(Long pc_num) {
		this.pc_num = pc_num;
	}

	public String getPc_result() {
		return pc_result;
	}

	public void setPc_result(String pc_result) {
		this.pc_result = pc_result;
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

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getPc_desc() {
		return pc_desc;
	}

	public void setPc_desc(String pc_desc) {
		this.pc_desc = pc_desc;
	}

	public Long getPy_pk_num() {
		return py_pk_num;
	}

	public void setPy_pk_num(Long pyPkNum) {
		py_pk_num = pyPkNum;
	}

	public Date getPc_date() {
		return pc_date;
	}

	public void setPc_date(Date pcDate) {
		pc_date = pcDate;
	}

	public BigDecimal getCur_cost_price() {
		return cur_cost_price;
	}

	public void setCur_cost_price(BigDecimal curCostPrice) {
		cur_cost_price = curCostPrice;
	}

	public KonkaJxcStoreState getKonkaJxcStoreState() {
		return konkaJxcStoreState;
	}

	public void setKonkaJxcStoreState(KonkaJxcStoreState konkaJxcStoreState) {
		this.konkaJxcStoreState = konkaJxcStoreState;
	}

}