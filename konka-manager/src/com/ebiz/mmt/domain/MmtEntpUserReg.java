package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-04-29 14:55:31
 */
public class MmtEntpUserReg extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long entp_id;

	private String entp_name;

	private Long p_index;

	private String addr;

	private String corporation;

	private String tel;

	private String fax;

	private String www;

	private String entp_desc;

	private Long employee_count;

	private Long year_sell;

	private Long store_area;

	private Long busi_area;

	private Integer busi_range;

	private String busi_type;

	private String main_pd;

	private Integer is_chain;

	private Integer chain_type;

	private Integer entp_kind;

	private Integer logistics_type;

	private Integer channel_type;

	private Date build_date;

	private Long reg_capital;

	private String reg_office;

	private String entp_license;

	private String entp_license_img;

	private String entp_org_code;

	private String entp_org_code_img;

	private String entp_tax_reg;

	private String entp_tax_reg_img;

	private Integer is_entp_top;

	private Integer bid_type;

	private String sq_serial;

	private String pd_cert_src;

	private String other_cert_src;

	private Integer is_del;

	private Date add_date;

	private Date last_modify_date;

	private Long shop_id;

	public MmtEntpUserReg() {

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
	 * @val 企业ID
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 企业ID
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 企业名称
	 */
	public String getEntp_name() {
		return entp_name;
	}

	/**
	 * @val 企业名称
	 */
	public void setEntp_name(String entp_name) {
		this.entp_name = entp_name;
	}

	/**
	 * @val 所属区域
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 所属区域
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 详细地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @val 详细地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @val 法人代表
	 */
	public String getCorporation() {
		return corporation;
	}

	/**
	 * @val 法人代表
	 */
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	/**
	 * @val 电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @val 电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @val 传真
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @val 传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @val 网址
	 */
	public String getWww() {
		return www;
	}

	/**
	 * @val 网址
	 */
	public void setWww(String www) {
		this.www = www;
	}

	/**
	 * @val 简介
	 */
	public String getEntp_desc() {
		return entp_desc;
	}

	/**
	 * @val 简介
	 */
	public void setEntp_desc(String entp_desc) {
		this.entp_desc = entp_desc;
	}

	/**
	 * @val 员工人数
	 */
	public Long getEmployee_count() {
		return employee_count;
	}

	/**
	 * @val 员工人数
	 */
	public void setEmployee_count(Long employee_count) {
		this.employee_count = employee_count;
	}

	/**
	 * @val 年销售额
	 */
	public Long getYear_sell() {
		return year_sell;
	}

	/**
	 * @val 年销售额
	 */
	public void setYear_sell(Long year_sell) {
		this.year_sell = year_sell;
	}

	/**
	 * @val 仓储面积
	 */
	public Long getStore_area() {
		return store_area;
	}

	/**
	 * @val 仓储面积
	 */
	public void setStore_area(Long store_area) {
		this.store_area = store_area;
	}

	/**
	 * @val 经营面积
	 */
	public Long getBusi_area() {
		return busi_area;
	}

	/**
	 * @val 经营面积
	 */
	public void setBusi_area(Long busi_area) {
		this.busi_area = busi_area;
	}

	/**
	 * @val 经营辐射范围：1-省级 2-市级 3-县级 4-乡镇级 5-村级
	 */
	public Integer getBusi_range() {
		return busi_range;
	}

	/**
	 * @val 经营辐射范围：1-省级 2-市级 3-县级 4-乡镇级 5-村级
	 */
	public void setBusi_range(Integer busi_range) {
		this.busi_range = busi_range;
	}

	/**
	 * @val 经营类别
	 */
	public String getBusi_type() {
		return busi_type;
	}

	/**
	 * @val 经营类别
	 */
	public void setBusi_type(String busi_type) {
		this.busi_type = busi_type;
	}

	/**
	 * @val 主营品牌
	 */
	public String getMain_pd() {
		return main_pd;
	}

	/**
	 * @val 主营品牌
	 */
	public void setMain_pd(String main_pd) {
		this.main_pd = main_pd;
	}

	/**
	 * @val 是否连锁：0-否；1-是；
	 */
	public Integer getIs_chain() {
		return is_chain;
	}

	/**
	 * @val 是否连锁：0-否；1-是；
	 */
	public void setIs_chain(Integer is_chain) {
		this.is_chain = is_chain;
	}

	/**
	 * @val 连锁性质：1-全国连锁 2-区域型连锁 3-商超百货系统 11-一般网点 12-专卖店 13-直营店
	 */
	public Integer getChain_type() {
		return chain_type;
	}

	/**
	 * @val 连锁性质：1-全国连锁 2-区域型连锁 3-商超百货系统 11-一般网点 12-专卖店 13-直营店
	 */
	public void setChain_type(Integer chain_type) {
		this.chain_type = chain_type;
	}

	/**
	 * @val 企业性质：1-国有企业； 2-集体企业 3-联营企业； 4-股份合作制企业 5-私营企业； 6-个体户； 7-合伙企业； 8-有限责任公司； 9-股份有限公司
	 */
	public Integer getEntp_kind() {
		return entp_kind;
	}

	/**
	 * @val 企业性质：1-国有企业； 2-集体企业 3-联营企业； 4-股份合作制企业 5-私营企业； 6-个体户； 7-合伙企业； 8-有限责任公司； 9-股份有限公司
	 */
	public void setEntp_kind(Integer entp_kind) {
		this.entp_kind = entp_kind;
	}

	/**
	 * @val 物流情况：1-自己配送； 2-第三方物流
	 */
	public Integer getLogistics_type() {
		return logistics_type;
	}

	/**
	 * @val 物流情况：1-自己配送； 2-第三方物流
	 */
	public void setLogistics_type(Integer logistics_type) {
		this.logistics_type = logistics_type;
	}

	/**
	 * @val 进货渠道：1-厂家直供； 2-代理商供货
	 */
	public Integer getChannel_type() {
		return channel_type;
	}

	/**
	 * @val 进货渠道：1-厂家直供； 2-代理商供货
	 */
	public void setChannel_type(Integer channel_type) {
		this.channel_type = channel_type;
	}

	/**
	 * @val 成立日期
	 */
	public Date getBuild_date() {
		return build_date;
	}

	/**
	 * @val 成立日期
	 */
	public void setBuild_date(Date build_date) {
		this.build_date = build_date;
	}

	/**
	 * @val 注册资本
	 */
	public Long getReg_capital() {
		return reg_capital;
	}

	/**
	 * @val 注册资本
	 */
	public void setReg_capital(Long reg_capital) {
		this.reg_capital = reg_capital;
	}

	/**
	 * @val 登记机关
	 */
	public String getReg_office() {
		return reg_office;
	}

	/**
	 * @val 登记机关
	 */
	public void setReg_office(String reg_office) {
		this.reg_office = reg_office;
	}

	/**
	 * @val 营业执照号
	 */
	public String getEntp_license() {
		return entp_license;
	}

	/**
	 * @val 营业执照号
	 */
	public void setEntp_license(String entp_license) {
		this.entp_license = entp_license;
	}

	/**
	 * @val 上传营业执照
	 */
	public String getEntp_license_img() {
		return entp_license_img;
	}

	/**
	 * @val 上传营业执照
	 */
	public void setEntp_license_img(String entp_license_img) {
		this.entp_license_img = entp_license_img;
	}

	/**
	 * @val 组织机构代码
	 */
	public String getEntp_org_code() {
		return entp_org_code;
	}

	/**
	 * @val 组织机构代码
	 */
	public void setEntp_org_code(String entp_org_code) {
		this.entp_org_code = entp_org_code;
	}

	/**
	 * @val 上传组装机构代码证
	 */
	public String getEntp_org_code_img() {
		return entp_org_code_img;
	}

	/**
	 * @val 上传组装机构代码证
	 */
	public void setEntp_org_code_img(String entp_org_code_img) {
		this.entp_org_code_img = entp_org_code_img;
	}

	/**
	 * @val 税务登记证
	 */
	public String getEntp_tax_reg() {
		return entp_tax_reg;
	}

	/**
	 * @val 税务登记证
	 */
	public void setEntp_tax_reg(String entp_tax_reg) {
		this.entp_tax_reg = entp_tax_reg;
	}

	/**
	 * @val 上传税务登记证
	 */
	public String getEntp_tax_reg_img() {
		return entp_tax_reg_img;
	}

	/**
	 * @val 上传税务登记证
	 */
	public void setEntp_tax_reg_img(String entp_tax_reg_img) {
		this.entp_tax_reg_img = entp_tax_reg_img;
	}

	/**
	 * @val 家电下乡顶级企业：0-否；1-是；
	 */
	public Integer getIs_entp_top() {
		return is_entp_top;
	}

	/**
	 * @val 家电下乡顶级企业：0-否；1-是；
	 */
	public void setIs_entp_top(Integer is_entp_top) {
		this.is_entp_top = is_entp_top;
	}

	/**
	 * @val 中标类型：1-家电下乡；2-以旧换新销售；4-以旧换新回收
	 */
	public Integer getBid_type() {
		return bid_type;
	}

	/**
	 * @val 中标类型：1-家电下乡；2-以旧换新销售；4-以旧换新回收
	 */
	public void setBid_type(Integer bid_type) {
		this.bid_type = bid_type;
	}

	/**
	 * @val 授权编号
	 */
	public String getSq_serial() {
		return sq_serial;
	}

	/**
	 * @val 授权编号
	 */
	public void setSq_serial(String sq_serial) {
		this.sq_serial = sq_serial;
	}

	/**
	 * @val 产品证书地址
	 */
	public String getPd_cert_src() {
		return pd_cert_src;
	}

	/**
	 * @val 产品证书地址
	 */
	public void setPd_cert_src(String pd_cert_src) {
		this.pd_cert_src = pd_cert_src;
	}

	/**
	 * @val 其他证书
	 */
	public String getOther_cert_src() {
		return other_cert_src;
	}

	/**
	 * @val 其他证书
	 */
	public void setOther_cert_src(String other_cert_src) {
		this.other_cert_src = other_cert_src;
	}

	/**
	 * @val 是否删除：0-否；1-是；
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除：0-否；1-是；
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date addDate) {
		add_date = addDate;
	}

	public Date getLast_modify_date() {
		return last_modify_date;
	}

	public void setLast_modify_date(Date lastModifyDate) {
		last_modify_date = lastModifyDate;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shopId) {
		shop_id = shopId;
	}

}