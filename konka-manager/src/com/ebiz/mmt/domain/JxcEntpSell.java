package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-10 11:05:58
 */
public class JxcEntpSell extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String entp_name;
	
	private String entp_man;
	
	private String link_phone;
	
	private String pd_type_str;
	
	private String pd_name;
	
	private BigDecimal pd_in_price;
	
	private BigDecimal pd_sell_price;
	
	private Long sell_count;
	
	private Date sell_date;
	
	private String cust_name;
	
	private String cust_link_phone;
	
	private String cust_link_man;
	
	private String client_ip_adde;
	
	private Date add_date;
	
	public JxcEntpSell() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 门店名称
	 */
	public String getEntp_name() {
		return entp_name;
	}
	
	/**
	 * @val 门店名称
	 */
	public void setEntp_name(String entp_name) {
		this.entp_name = entp_name;
	}
	
	/**
	 * @val 企业法人
	 */
	public String getEntp_man() {
		return entp_man;
	}
	
	/**
	 * @val 企业法人
	 */
	public void setEntp_man(String entp_man) {
		this.entp_man = entp_man;
	}
	
	/**
	 * @val 企业电话
	 */
	public String getLink_phone() {
		return link_phone;
	}
	
	/**
	 * @val 企业电话
	 */
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	
	/**
	 * @val 产品类别
	 */
	public String getPd_type_str() {
		return pd_type_str;
	}
	
	/**
	 * @val 产品类别
	 */
	public void setPd_type_str(String pd_type_str) {
		this.pd_type_str = pd_type_str;
	}
	
	/**
	 * @val 产品名称
	 */
	public String getPd_name() {
		return pd_name;
	}
	
	/**
	 * @val 产品名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	/**
	 * @val 产品进价
	 */
	public BigDecimal getPd_in_price() {
		return pd_in_price;
	}
	
	/**
	 * @val 产品进价
	 */
	public void setPd_in_price(BigDecimal pd_in_price) {
		this.pd_in_price = pd_in_price;
	}
	
	/**
	 * @val 产品售价(总价)
	 */
	public BigDecimal getPd_sell_price() {
		return pd_sell_price;
	}
	
	/**
	 * @val 产品售价(总价)
	 */
	public void setPd_sell_price(BigDecimal pd_sell_price) {
		this.pd_sell_price = pd_sell_price;
	}
	
	/**
	 * @val 销售数量
	 */
	public Long getSell_count() {
		return sell_count;
	}
	
	/**
	 * @val 销售数量
	 */
	public void setSell_count(Long sell_count) {
		this.sell_count = sell_count;
	}
	
	/**
	 * @val 销售时间
	 */
	public Date getSell_date() {
		return sell_date;
	}
	
	/**
	 * @val 销售时间
	 */
	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}
	
	/**
	 * @val 客户名称
	 */
	public String getCust_name() {
		return cust_name;
	}
	
	/**
	 * @val 客户名称
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	/**
	 * @val 客户电话
	 */
	public String getCust_link_phone() {
		return cust_link_phone;
	}
	
	/**
	 * @val 客户电话
	 */
	public void setCust_link_phone(String cust_link_phone) {
		this.cust_link_phone = cust_link_phone;
	}
	
	/**
	 * @val 客户联系人
	 */
	public String getCust_link_man() {
		return cust_link_man;
	}
	
	/**
	 * @val 客户联系人
	 */
	public void setCust_link_man(String cust_link_man) {
		this.cust_link_man = cust_link_man;
	}
	
	/**
	 * @val 客户IP地址
	 */
	public String getClient_ip_adde() {
		return client_ip_adde;
	}
	
	/**
	 * @val 客户IP地址
	 */
	public void setClient_ip_adde(String client_ip_adde) {
		this.client_ip_adde = client_ip_adde;
	}
	
	/**
	 * @val 入库时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 入库时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}