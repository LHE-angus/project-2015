package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jin,QingHua
 */
public class ShopEmployee extends UserInfo implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long employee_id;

	private Long shop_id;

	private Date add_date;

	private Integer state;

	private String employee_name;

	private String shop_name;

	private String user_name;

	private Integer shopNumber;

	public ShopEmployee() {

	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(Integer shopNumber) {
		this.shopNumber = shopNumber;
	}

}