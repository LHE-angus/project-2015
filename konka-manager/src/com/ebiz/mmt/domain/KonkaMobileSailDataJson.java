package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaMobileSailDataJson extends BaseDomain implements Serializable{
	private static final long serialVersionUID = -1L;
	
	private String store_id; //门店ID
	
	private String store_name; //门店名称
	
	private String md_name; //商品型号
	
	private Long sail_num; //销售数量
	
	private BigDecimal sail_price; //销售金额
	
	private String report_date; //上报时间
	
	private String reprot_man; //上报人

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public String getMd_name() {
		return md_name;
	}

	public void setSail_num(Long sail_num) {
		this.sail_num = sail_num;
	}

	public Long getSail_num() {
		return sail_num;
	}

	public void setSail_price(BigDecimal sail_price) {
		this.sail_price = sail_price;
	}

	public BigDecimal getSail_price() {
		return sail_price;
	}

	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}

	public String getReport_date() {
		return report_date;
	}

	public void setReprot_man(String reprot_man) {
		this.reprot_man = reprot_man;
	}

	public String getReprot_man() {
		return reprot_man;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getStore_id() {
		return store_id;
	}
	
}
