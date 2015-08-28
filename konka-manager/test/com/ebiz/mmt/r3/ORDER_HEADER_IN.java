package com.ebiz.mmt.r3;

import java.util.Date;
import java.util.List;

/**
 * 写R/3销售订单的头表信息
 * 
 * @author ZHOU
 * 
 */
public class ORDER_HEADER_IN {

	// 订单头和订单行日期默认一致
	private String doc_date;// 单据日期, 不是创建日期

	private String purch_no_c; // 采购订单编号

	private String purch_no_s;// 销售单号对应渠道系统订单的流水号

	private Date purch_date; // 采购订单日期

	private String sales_org;// 销售组织

	private String doc_type;// 销售凭证类型

	private String distr_chan;// 分销渠道

	private String division;// 产品组

	private String ag;// 售达方

	private String re; // 出票方

	private String rg; // 付款方

	private String we;// 送达方

	// 客户名称
	private String customerName;
	// 客户所在城市 目前使用客户所在分公司名称代替
	private String customerCity;

	// 其它
	private String customerTxt;

	private List<ORDER_ITEMS_IN> itemList; // 物料行

	// 当时进行接口同步的那个人
	private String opername;

	// 退货原因
	private String ordreason;

	public String getPurch_no_c() {
		return purch_no_c;
	}

	public void setPurch_no_c(String purch_no_c) {
		this.purch_no_c = purch_no_c;
	}

	public Date getPurch_date() {
		return purch_date;
	}

	public void setPurch_date(Date purch_date) {
		this.purch_date = purch_date;
	}

	public String getSales_org() {
		return sales_org;
	}

	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getDistr_chan() {
		return distr_chan;
	}

	public void setDistr_chan(String distr_chan) {
		this.distr_chan = distr_chan;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getAg() {
		return ag;
	}

	public void setAg(String ag) {
		this.ag = ag;
	}

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getWe() {
		return we;
	}

	public void setWe(String we) {
		this.we = we;
	}

	/**
	 * @return the itemList
	 */
	public List<ORDER_ITEMS_IN> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *            the itemList to set
	 */
	public void setItemList(List<ORDER_ITEMS_IN> itemList) {
		this.itemList = itemList;
	}

	public String getPurch_no_s() {
		return purch_no_s;
	}

	public void setPurch_no_s(String purch_no_s) {
		this.purch_no_s = purch_no_s;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	public String getOrdreason() {
		return ordreason;
	}

	public void setOrdreason(String ordreason) {
		this.ordreason = ordreason;
	}

	public String getDoc_date() {
		return doc_date;
	}

	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerTxt() {
		return customerTxt;
	}

	public void setCustomerTxt(String customerTxt) {
		this.customerTxt = customerTxt;
	}

}
