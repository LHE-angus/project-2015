package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 13:45:22
 */
public class KonkaR3ShopLink extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long link_id;

	private Long r3_shop_id;

	private String position;

	private String real_name;

	// 联系电话
	private String tel;
	// 手机
	private String mobile;

	private String addr;

	private Integer sex;

	private Date birthday;

	private String email;

	private String favor;

	private Integer is_del;

	private Date add_date;

	private Long add_user_id;

	private Long order_value;
	
	//根据IHS需求新增字段
	private String job;
	// 固定电话
	private String telephone;
	
	private String fax;
	
	private String weixin;
	
	private String qq;
	
	private String is_default;
	
	private String is_valid;

	private String customer_preferences; //客户喜好


	public String getCustomer_preferences() {
		return customer_preferences;
	}

	public void setCustomer_preferences(String customerPreferences) {
		customer_preferences = customerPreferences;
	}

	public KonkaR3ShopLink() {

	}

	/**
	 * @val LINK_ID
	 */
	public Long getLink_id() {
		return link_id;
	}

	/**
	 * @val LINK_ID
	 */
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	/**
	 * @val 客户ID
	 */
	public Long getR3_shop_id() {
		return r3_shop_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setR3_shop_id(Long r3_shop_id) {
		this.r3_shop_id = r3_shop_id;
	}

	/**
	 * @val 联系人职务，KONKA_DEPT表
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @val 联系人职务，KONKA_DEPT表
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @val 联系人姓名
	 */
	public String getReal_name() {
		return real_name;
	}

	/**
	 * @val 联系人姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	/**
	 * @val 联系人电话,多个用逗号隔开
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @val 联系人电话,多个用逗号隔开
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @val 联系人手机,多个用逗号隔开
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @val 联系人手机,多个用逗号隔开
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @val 联系人地址
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @val 联系人地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @val 性别：0-男1-女 2-未知
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @val 性别：0-男1-女 2-未知
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @val 生日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @val 生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @val 个人偏好
	 */
	public String getFavor() {
		return favor;
	}

	/**
	 * @val 个人偏好
	 */
	public void setFavor(String favor) {
		this.favor = favor;
	}

	/**
	 * @val 是否删除 0-未删 1-已删
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除 0-未删 1-已删
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 添加人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public Long getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序值，排序值越大越靠前
	 */
	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

}