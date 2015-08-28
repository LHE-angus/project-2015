package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:38
 */
public class StdEntpMain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long entp_id;

	private Long shop_id;

	private String entp_name;

	private String entp_sname;

	private String entp_ename;

	private Integer entp_type;

	private Integer p_index;

	private String c_index;

	private Long par_id;

	private Long root_entp_id;

	private String main_pd;

	private Integer entp_kind;

	private String entp_size;

	private String entp_licence;

	private String corporation;

	private String addr;

	private String postcode;

	private String linkman;

	private String tel;

	private String fax;

	private String email;

	private String www;

	private Integer is_record;

	private String sq_serial;

	private Integer single_callback;

	private Integer adv_pay_sign;

	private Integer own_sys;

	private Integer info_state;

	private String logo;

	private String entp_licence_img;

	private String entp_tax_licence;

	private String entp_tax_licence_img;

	private String entp_org_code;

	private String entp_org_code_img;

	private Date add_date;

	public StdEntpMain() {

	}

	/**
	 * @val 企业流水号
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 企业编号
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 企业编号
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
	 * @val 标识简称
	 */
	public String getEntp_sname() {
		return entp_sname;
	}

	/**
	 * @val 标识简称
	 */
	public void setEntp_sname(String entp_sname) {
		this.entp_sname = entp_sname;
	}

	/**
	 * @val 英文名称
	 */
	public String getEntp_ename() {
		return entp_ename;
	}

	/**
	 * @val 英文名称
	 */
	public void setEntp_ename(String entp_ename) {
		this.entp_ename = entp_ename;
	}

	/**
	 * @val 企业类型
	 */
	public Integer getEntp_type() {
		return entp_type;
	}

	/**
	 * @val 企业类型
	 */
	public void setEntp_type(Integer entp_type) {
		this.entp_type = entp_type;
	}

	/**
	 * @val 所属地区
	 */
	public Integer getP_index() {
		return p_index;
	}

	/**
	 * @val 所属地区
	 */
	public void setP_index(Integer p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 所属行业
	 */
	public String getC_index() {
		return c_index;
	}

	/**
	 * @val 所属行业
	 */
	public void setC_index(String c_index) {
		this.c_index = c_index;
	}

	/**
	 * @val 父企业编号
	 */
	public Long getPar_id() {
		return par_id;
	}

	/**
	 * @val 父企业编号
	 */
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}

	/**
	 * @val 根企业编号
	 */
	public Long getRoot_entp_id() {
		return root_entp_id;
	}

	/**
	 * @val 根企业编号
	 */
	public void setRoot_entp_id(Long root_entp_id) {
		this.root_entp_id = root_entp_id;
	}

	/**
	 * @val 主营产品
	 */
	public String getMain_pd() {
		return main_pd;
	}

	/**
	 * @val 主营产品
	 */
	public void setMain_pd(String main_pd) {
		this.main_pd = main_pd;
	}

	/**
	 * @val 企业性质
	 */
	public Integer getEntp_kind() {
		return entp_kind;
	}

	/**
	 * @val 企业性质
	 */
	public void setEntp_kind(Integer entp_kind) {
		this.entp_kind = entp_kind;
	}

	/**
	 * @val 经营规模
	 */
	public String getEntp_size() {
		return entp_size;
	}

	/**
	 * @val 经营规模
	 */
	public void setEntp_size(String entp_size) {
		this.entp_size = entp_size;
	}

	/**
	 * @val 企业执照
	 */
	public String getEntp_licence() {
		return entp_licence;
	}

	/**
	 * @val 企业执照
	 */
	public void setEntp_licence(String entp_licence) {
		this.entp_licence = entp_licence;
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
	 * @val 地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @val 地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @val 邮编
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @val 邮编
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @val 联系人
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * @val 联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
	 * @val 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @val 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @val 备案标识
	 */
	public Integer getIs_record() {
		return is_record;
	}

	/**
	 * @val 备案标识
	 */
	public void setIs_record(Integer is_record) {
		this.is_record = is_record;
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
	 * @val 单纯回收企业标志 0否1是
	 */
	public Integer getSingle_callback() {
		return single_callback;
	}

	/**
	 * @val 单纯回收企业标志 0否1是
	 */
	public void setSingle_callback(Integer single_callback) {
		this.single_callback = single_callback;
	}

	/**
	 * @val 先行垫付审核标识 0否1是
	 */
	public Integer getAdv_pay_sign() {
		return adv_pay_sign;
	}

	/**
	 * @val 先行垫付审核标识 0否1是
	 */
	public void setAdv_pay_sign(Integer adv_pay_sign) {
		this.adv_pay_sign = adv_pay_sign;
	}

	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	public Integer getInfo_state() {
		return info_state;
	}

	public void setInfo_state(Integer infoState) {
		info_state = infoState;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shopId) {
		shop_id = shopId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEntp_licence_img() {
		return entp_licence_img;
	}

	public void setEntp_licence_img(String entpLicenceImg) {
		entp_licence_img = entpLicenceImg;
	}

	public String getEntp_tax_licence() {
		return entp_tax_licence;
	}

	public void setEntp_tax_licence(String entpTaxLicence) {
		entp_tax_licence = entpTaxLicence;
	}

	public String getEntp_tax_licence_img() {
		return entp_tax_licence_img;
	}

	public void setEntp_tax_licence_img(String entpTaxLicenceImg) {
		entp_tax_licence_img = entpTaxLicenceImg;
	}

	public String getEntp_org_code() {
		return entp_org_code;
	}

	public void setEntp_org_code(String entpOrgCode) {
		entp_org_code = entpOrgCode;
	}

	public String getEntp_org_code_img() {
		return entp_org_code_img;
	}

	public void setEntp_org_code_img(String entpOrgCodeImg) {
		entp_org_code_img = entpOrgCodeImg;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date addDate) {
		add_date = addDate;
	}

}