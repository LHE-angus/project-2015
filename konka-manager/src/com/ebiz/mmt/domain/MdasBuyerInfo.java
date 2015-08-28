package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-05 08:43:57
 */
public class MdasBuyerInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Integer own_sys;

	private String buyer_name;

	private String buyer_id;

	private Integer buyer_sex;

	private Integer buyer_age;

	private Long buyer_p_index;

	private String buyer_address;

	private String buyer_phone;

	private String buyer_mobile;

	private Integer buyer_mobile_web;

	private Date buyer_buy_date;

	private Long par_pd_type_id;

	private String par_pd_type_name;

	private Long pd_type_id;

	private String pd_type_name;

	private Long brand_id;

	private String brand_name;

	private Long pd_id;

	private String pd_name;

	private BigDecimal pd_quote;

	private BigDecimal pd_price;

	private Long entp_id;

	private String entp_name;

	private Long sell_entp_id;

	private String sell_entp_name;

	private Long par_sell_entp_id;

	private String par_sell_entp_name;

	private Date add_date;

	private Long shop_id;

	private String shop_name;

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shopId) {
		shop_id = shopId;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shopName) {
		shop_name = shopName;
	}

	public MdasBuyerInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 所属系统：0-买卖提，1-家电下乡，2-以旧换新
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统：0-买卖提，1-家电下乡，2-以旧换新
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	/**
	 * @val 购买人姓名
	 */
	public String getBuyer_name() {
		return buyer_name;
	}

	/**
	 * @val 购买人姓名
	 */
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	/**
	 * @val 购买人身份证号
	 */
	public String getBuyer_id() {
		return buyer_id;
	}

	/**
	 * @val 购买人身份证号
	 */
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	/**
	 * @val 购买人性别：0-男，1-女
	 */
	public Integer getBuyer_sex() {
		return buyer_sex;
	}

	/**
	 * @val 购买人性别：0-男，1-女
	 */
	public void setBuyer_sex(Integer buyer_sex) {
		this.buyer_sex = buyer_sex;
	}

	/**
	 * @val 购买人年龄
	 */
	public Integer getBuyer_age() {
		return buyer_age;
	}

	/**
	 * @val 购买人年龄
	 */
	public void setBuyer_age(Integer buyer_age) {
		this.buyer_age = buyer_age;
	}

	/**
	 * @val 购买人所属区域
	 */
	public Long getBuyer_p_index() {
		return buyer_p_index;
	}

	/**
	 * @val 购买人所属区域
	 */
	public void setBuyer_p_index(Long buyer_p_index) {
		this.buyer_p_index = buyer_p_index;
	}

	/**
	 * @val 购买人详细地址
	 */
	public String getBuyer_address() {
		return buyer_address;
	}

	/**
	 * @val 购买人详细地址
	 */
	public void setBuyer_address(String buyer_address) {
		this.buyer_address = buyer_address;
	}

	/**
	 * @val 购买人固定电话
	 */
	public String getBuyer_phone() {
		return buyer_phone;
	}

	/**
	 * @val 购买人固定电话
	 */
	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

	/**
	 * @val 购买人手机
	 */
	public String getBuyer_mobile() {
		return buyer_mobile;
	}

	/**
	 * @val 购买人手机
	 */
	public void setBuyer_mobile(String buyer_mobile) {
		this.buyer_mobile = buyer_mobile;
	}

	/**
	 * @val 购买人手机网段：0-移动，1-联通，2-电信
	 */
	public Integer getBuyer_mobile_web() {
		return buyer_mobile_web;
	}

	/**
	 * @val 购买人手机网段：0-移动，1-联通，2-电信
	 */
	public void setBuyer_mobile_web(Integer buyer_mobile_web) {
		this.buyer_mobile_web = buyer_mobile_web;
	}

	/**
	 * @val 购买日期
	 */
	public Date getBuyer_buy_date() {
		return buyer_buy_date;
	}

	/**
	 * @val 购买日期
	 */
	public void setBuyer_buy_date(Date buyer_buy_date) {
		this.buyer_buy_date = buyer_buy_date;
	}

	/**
	 * @val 产品类别（父ID）
	 */
	public Long getPar_pd_type_id() {
		return par_pd_type_id;
	}

	/**
	 * @val 产品类别（父ID）
	 */
	public void setPar_pd_type_id(Long par_pd_type_id) {
		this.par_pd_type_id = par_pd_type_id;
	}

	/**
	 * @val 产品类别（父ID）名称
	 */
	public String getPar_pd_type_name() {
		return par_pd_type_name;
	}

	/**
	 * @val 产品类别（父ID）名称
	 */
	public void setPar_pd_type_name(String par_pd_type_name) {
		this.par_pd_type_name = par_pd_type_name;
	}

	/**
	 * @val 产品类别ID
	 */
	public Long getPd_type_id() {
		return pd_type_id;
	}

	/**
	 * @val 产品类别ID
	 */
	public void setPd_type_id(Long pd_type_id) {
		this.pd_type_id = pd_type_id;
	}

	/**
	 * @val 产品类别名称
	 */
	public String getPd_type_name() {
		return pd_type_name;
	}

	/**
	 * @val 产品类别名称
	 */
	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
	}

	/**
	 * @val 品牌ID
	 */
	public Long getBrand_id() {
		return brand_id;
	}

	/**
	 * @val 品牌ID
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	/**
	 * @val 品牌名称
	 */
	public String getBrand_name() {
		return brand_name;
	}

	/**
	 * @val 品牌名称
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	/**
	 * @val 产品型号ID
	 */
	public Long getPd_id() {
		return pd_id;
	}

	/**
	 * @val 产品型号ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	/**
	 * @val 产品型号名称
	 */
	public String getPd_name() {
		return pd_name;
	}

	/**
	 * @val 产品型号名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	/**
	 * @val 产品报价
	 */
	public BigDecimal getPd_quote() {
		return pd_quote;
	}

	/**
	 * @val 产品报价
	 */
	public void setPd_quote(BigDecimal pd_quote) {
		this.pd_quote = pd_quote;
	}

	/**
	 * @val 产品售价
	 */
	public BigDecimal getPd_price() {
		return pd_price;
	}

	/**
	 * @val 产品售价
	 */
	public void setPd_price(BigDecimal pd_price) {
		this.pd_price = pd_price;
	}

	/**
	 * @val 生产企业编号
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 生产企业编号
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 生产企业名称
	 */
	public String getEntp_name() {
		return entp_name;
	}

	/**
	 * @val 生产企业名称
	 */
	public void setEntp_name(String entp_name) {
		this.entp_name = entp_name;
	}

	/**
	 * @val 销售企业编号
	 */
	public Long getSell_entp_id() {
		return sell_entp_id;
	}

	/**
	 * @val 销售企业编号
	 */
	public void setSell_entp_id(Long sell_entp_id) {
		this.sell_entp_id = sell_entp_id;
	}

	/**
	 * @val 销售企业名称
	 */
	public String getSell_entp_name() {
		return sell_entp_name;
	}

	/**
	 * @val 销售企业名称
	 */
	public void setSell_entp_name(String sell_entp_name) {
		this.sell_entp_name = sell_entp_name;
	}

	/**
	 * @val 顶级销售企业编号
	 */
	public Long getPar_sell_entp_id() {
		return par_sell_entp_id;
	}

	/**
	 * @val 顶级销售企业编号
	 */
	public void setPar_sell_entp_id(Long par_sell_entp_id) {
		this.par_sell_entp_id = par_sell_entp_id;
	}

	/**
	 * @val 顶级销售企业名称
	 */
	public String getPar_sell_entp_name() {
		return par_sell_entp_name;
	}

	/**
	 * @val 顶级销售企业名称
	 */
	public void setPar_sell_entp_name(String par_sell_entp_name) {
		this.par_sell_entp_name = par_sell_entp_name;
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