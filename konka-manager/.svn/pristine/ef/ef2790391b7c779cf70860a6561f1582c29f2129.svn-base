package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public class JxcSupplier extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long shop_p_index;

	private String name;

	private String link_man;

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

	private BigDecimal qc_pay; // 期初应付款

	private BigDecimal zj_pay; // 增加应付款

	private BigDecimal zf_pay; // 支付应付款

	private BigDecimal qm_pay; // 期末应付款

	public BigDecimal getQc_pay() {
		return qc_pay;
	}

	public void setQc_pay(BigDecimal qcPay) {
		qc_pay = qcPay;
	}

	public BigDecimal getZj_pay() {
		return zj_pay;
	}

	public void setZj_pay(BigDecimal zjPay) {
		zj_pay = zjPay;
	}

	public BigDecimal getZf_pay() {
		return zf_pay;
	}

	public void setZf_pay(BigDecimal zfPay) {
		zf_pay = zfPay;
	}

	public BigDecimal getQm_pay() {
		return qm_pay;
	}

	public void setQm_pay(BigDecimal qmPay) {
		qm_pay = qmPay;
	}

	public JxcSupplier() {

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
	 * @val 供应商名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @val 供应商名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @val 联系人姓名
	 */
	public String getLink_man() {
		return link_man;
	}

	/**
	 * @val 联系人姓名
	 */
	public void setLink_man(String link_man) {
		this.link_man = link_man;
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
	 * @val 街道地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @val 街道地址
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
	 * @val 期初应付款：门店与供应商之间的款额（初始化）
	 * @val 1、初始化某供应商时，门店与供应商之间的欠款情况
	 * @val 2、一经保存后不可修改，对操作员完全只读
	 * @val 3、正数：门店欠供应商款额；负数：供应商欠门店的款额
	 */
	public BigDecimal getInit_pay() {
		return init_pay;
	}

	/**
	 * @val 期初应付款：门店与供应商之间的款额（初始化）
	 * @val 1、初始化某供应商时，门店与供应商之间的欠款情况
	 * @val 2、一经保存后不可修改，对操作员完全只读
	 * @val 3、正数：门店欠供应商款额；负数：供应商欠门店的款额
	 */
	public void setInit_pay(BigDecimal init_pay) {
		this.init_pay = init_pay;
	}

	/**
	 * @val 当前应付款：（实时）当前门店欠供应商的款额
	 * @val
	 * @val 1、通过进货单修改
	 * @val 2、通过收款单修改（暂不做）
	 * @val 3、正数：门店欠供应商的款额；负数：供应商欠门店的款额
	 */
	public BigDecimal getCur_pay() {
		return cur_pay;
	}

	/**
	 * @val 当前应付款：（实时）当前门店欠供应商的款额
	 * @val
	 * @val 1、通过进货单修改
	 * @val 2、通过收款单修改（暂不做）
	 * @val 3、正数：门店欠供应商的款额；负数：供应商欠门店的款额
	 */
	public void setCur_pay(BigDecimal cur_pay) {
		this.cur_pay = cur_pay;
	}

}