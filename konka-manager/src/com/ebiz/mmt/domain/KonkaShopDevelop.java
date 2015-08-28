package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * 
 * @author Cheng,Bing
 * @datetime 2011-10-20
 */
public class KonkaShopDevelop extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long user_id;
	private Long shop_id;
	private String shop_name;
	private Long develop_status;
	private Long shop_status;
	private Date start_date;
	private Date end_date;
	private Long add_user_id;
	private Long dept_id;
	private Long jxs_id;
	private Long r3_id;
	private String user_name;
	private Long is_del;

	private List<KonkaShopVisit> vList;

	public List<KonkaShopVisit> getvList() {
		return vList;
	}

	public void setvList(List<KonkaShopVisit> vList) {
		this.vList = vList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public Long getDevelop_status() {
		return develop_status;
	}

	public void setDevelop_status(Long develop_status) {
		this.develop_status = develop_status;
	}

	public Long getShop_status() {
		return shop_status;
	}

	public void setShop_status(Long shop_status) {
		this.shop_status = shop_status;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public Long getJxs_id() {
		return jxs_id;
	}

	public void setJxs_id(Long jxs_id) {
		this.jxs_id = jxs_id;
	}

	public Long getR3_id() {
		return r3_id;
	}

	public void setR3_id(Long r3_id) {
		this.r3_id = r3_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getIs_del() {
		return is_del;
	}

	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}

}