package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-11-16 17:47
 */

@Deprecated
public class KonkaR3SellImpInvalidData extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Date r3_sell_date;

	private String r3_code;

	private String f;

	private String type_desc;

	private String r3_name;

	private String send_r3_name;

	private String send_r3_code;

	private Date creat_date;

	private String order_sn;

	private String order_type;

	private String project;

	private String pd_name;

	private Long count;

	private BigDecimal price;

	private BigDecimal sum_money;

	private BigDecimal k007_money;

	private BigDecimal rb00_money;

	private BigDecimal zjz_money;

	private Date jh_date;

	private String kf_jh_bill;

	private String wl_jh_bill;

	private String fh_store;

	private String purchase_order_sn;

	private String custom_wl_sn;

	private String bsc;

	private String sell_org;

	private Date add_date;

	private Long add_user_id;

	public KonkaR3SellImpInvalidData() {

	}

	public String getSend_r3_name() {
		return send_r3_name;
	}

	public void setSend_r3_name(String sendR3Name) {
		send_r3_name = sendR3Name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getR3_sell_date() {
		return r3_sell_date;
	}

	public void setR3_sell_date(Date r3_sell_date) {
		this.r3_sell_date = r3_sell_date;
	}

	public String getR3_code() {
		return r3_code;
	}

	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getType_desc() {
		return type_desc;
	}

	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

	public String getR3_name() {
		return r3_name;
	}

	public void setR3_name(String r3_name) {
		this.r3_name = r3_name;
	}

	public String getSend_r3_code() {
		return send_r3_code;
	}

	public void setSend_r3_code(String send_r3_code) {
		this.send_r3_code = send_r3_code;
	}

	public Date getCreat_date() {
		return creat_date;
	}

	public void setCreat_date(Date creat_date) {
		this.creat_date = creat_date;
	}

	public String getOrder_sn() {
		return order_sn;
	}

	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSum_money() {
		return sum_money;
	}

	public void setSum_money(BigDecimal sum_money) {
		this.sum_money = sum_money;
	}

	public BigDecimal getK007_money() {
		return k007_money;
	}

	public void setK007_money(BigDecimal k007_money) {
		this.k007_money = k007_money;
	}

	public BigDecimal getRb00_money() {
		return rb00_money;
	}

	public void setRb00_money(BigDecimal rb00_money) {
		this.rb00_money = rb00_money;
	}

	public BigDecimal getZjz_money() {
		return zjz_money;
	}

	public void setZjz_money(BigDecimal zjz_money) {
		this.zjz_money = zjz_money;
	}

	public Date getJh_date() {
		return jh_date;
	}

	public void setJh_date(Date jh_date) {
		this.jh_date = jh_date;
	}

	public String getKf_jh_bill() {
		return kf_jh_bill;
	}

	public void setKf_jh_bill(String kf_jh_bill) {
		this.kf_jh_bill = kf_jh_bill;
	}

	public String getWl_jh_bill() {
		return wl_jh_bill;
	}

	public void setWl_jh_bill(String wl_jh_bill) {
		this.wl_jh_bill = wl_jh_bill;
	}

	public String getFh_store() {
		return fh_store;
	}

	public void setFh_store(String fh_store) {
		this.fh_store = fh_store;
	}

	public String getPurchase_order_sn() {
		return purchase_order_sn;
	}

	public void setPurchase_order_sn(String purchase_order_sn) {
		this.purchase_order_sn = purchase_order_sn;
	}

	public String getCustom_wl_sn() {
		return custom_wl_sn;
	}

	public void setCustom_wl_sn(String custom_wl_sn) {
		this.custom_wl_sn = custom_wl_sn;
	}

	public String getBsc() {
		return bsc;
	}

	public void setBsc(String bsc) {
		this.bsc = bsc;
	}

	public String getSell_org() {
		return sell_org;
	}

	public void setSell_org(String sell_org) {
		this.sell_org = sell_org;
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

}