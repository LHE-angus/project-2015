package com.ebiz.mmt.web.struts.inter.form;

import java.io.Serializable;

/**
 * @author Pan,Gang
 * @since 2014-09-24
 * @see 请求验证
 */
public class R3ShopLink extends BaseInterForm implements Serializable {

	private static final long serialVersionUID = -1L;
	private String link_id;

	private String r3_shop_id;

	private String position;

	private String real_name;

	private String tel;

	private String mobile;

	private String addr;

	private String sex;

	private String birthday;

	private String email;

	private String favor;

	private String is_del;

	private String add_date;

	private String add_user_id;

	private String order_value;

	// 根据IHS需求新增字段
	private String job;

	private String telephone;

	private String fax;

	private String weixin;

	private String qq;

	private String is_default;

	private String is_valid;

	public R3ShopLink() {
	}

	public String getLink_id() {
		return link_id;
	}

	public void setLink_id(String linkId) {
		link_id = linkId;
	}

	public String getR3_shop_id() {
		return r3_shop_id;
	}

	public void setR3_shop_id(String r3ShopId) {
		r3_shop_id = r3ShopId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String realName) {
		real_name = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFavor() {
		return favor;
	}

	public void setFavor(String favor) {
		this.favor = favor;
	}

	public String getIs_del() {
		return is_del;
	}

	public void setIs_del(String isDel) {
		is_del = isDel;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String addDate) {
		add_date = addDate;
	}

	public String getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(String addUserId) {
		add_user_id = addUserId;
	}

	public String getOrder_value() {
		return order_value;
	}

	public void setOrder_value(String orderValue) {
		order_value = orderValue;
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

	public void setIs_default(String isDefault) {
		is_default = isDefault;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String isValid) {
		is_valid = isValid;
	}

}