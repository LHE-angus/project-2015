package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:36
 */
public class JxcCustomer extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long shop_p_index;

	private String name;

	private String link_name;

	private String tel;

	private String fax;

	private String qq;

	private String e_mail;

	private String post;

	private Long p_index;

	private String addr;

	private String remarks;

	private Date add_date;

	private Integer is_del;

	private Long add_user_id;

	private BigDecimal init_pay;

	private BigDecimal cur_pay;

	private Integer cus_type;

	private String cus_name;

	private Integer cus_idcard_type;

	private String cus_idcard;

	private String cus_rpr;

	/**
	 * @val 客户类型 0-零售 1-批发
	 */
	public Integer getCus_type() {
		return cus_type;
	}

	public void setCus_type(Integer cusType) {
		cus_type = cusType;
	}

	public JxcCustomer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 门店商铺ID
	 */
	public Long getShop_id() {
		return shop_id;
	}

	/**
	 * @val 门店商铺ID
	 */
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	/**
	 * @val 门店所在地区
	 */
	public Long getShop_p_index() {
		return shop_p_index;
	}

	/**
	 * @val 门店所在地区
	 */
	public void setShop_p_index(Long shop_p_index) {
		this.shop_p_index = shop_p_index;
	}

	/**
	 * @val 客户名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @val 客户名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @val 联系人
	 */
	public String getLink_name() {
		return link_name;
	}

	/**
	 * @val 联系人
	 */
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	/**
	 * @val 联系电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @val 联系电话
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
	 * @val QQ号码
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @val QQ号码
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @val 电子邮箱
	 */
	public String getE_mail() {
		return e_mail;
	}

	/**
	 * @val 电子邮箱
	 */
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	/**
	 * @val 邮政编码
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @val 邮政编码
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @val 所在地区编码
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 所在地区编码
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 街道地址（送货）
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @val 街道地址（送货）
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @val 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @val 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @val 入库日期
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 入库日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 是否删除：0-未删，1-已删
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除：0-未删，1-已删
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 添加人用户ID=USER_INFO.ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人用户ID=USER_INFO.ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	/**
	 * @val 期初应收款：门店与客户之间的款额（初始化）
	 * @val 1、初始化某客户信息时，门店与客户之间的欠款情况
	 * @val 2、一经保存后不可修改，对操作员完全只读
	 * @val 3、正数：客户欠门店的款额；负数：门店欠客户的款
	 */
	public BigDecimal getInit_pay() {
		return init_pay;
	}

	/**
	 * @val 期初应收款：门店与客户之间的款额（初始化）
	 * @val 1、初始化某客户信息时，门店与客户之间的欠款情况
	 * @val 2、一经保存后不可修改，对操作员完全只读
	 * @val 3、正数：客户欠门店的款额；负数：门店欠客户的款
	 */
	public void setInit_pay(BigDecimal init_pay) {
		this.init_pay = init_pay;
	}

	/**
	 * @val 当前应收款：（实时）当前门店与客户之间的款额
	 * @val 1、通过销售单修改
	 * @val 2、通过收款单修改
	 * @val 3、正数：客户欠门店的款额；负数：门店欠客户的款额
	 */
	public BigDecimal getCur_pay() {
		return cur_pay;
	}

	/**
	 * @val 当前应收款：（实时）当前门店与客户之间的款额
	 * @val 1、通过销售单修改
	 * @val 2、通过收款单修改
	 * @val 3、正数：客户欠门店的款额；负数：门店欠客户的款额
	 */
	public void setCur_pay(BigDecimal cur_pay) {
		this.cur_pay = cur_pay;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cusName) {
		cus_name = cusName;
	}

	public Integer getCus_idcard_type() {
		return cus_idcard_type;
	}

	public void setCus_idcard_type(Integer cusIdcardType) {
		cus_idcard_type = cusIdcardType;
	}

	public String getCus_idcard() {
		return cus_idcard;
	}

	public void setCus_idcard(String cusIdcard) {
		cus_idcard = cusIdcard;
	}

	public String getCus_rpr() {
		return cus_rpr;
	}

	public void setCus_rpr(String cusRpr) {
		cus_rpr = cusRpr;
	}

}