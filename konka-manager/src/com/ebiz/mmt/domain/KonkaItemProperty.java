package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
public class KonkaItemProperty extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long p_id;
	
	private String p_name;
	
	private Integer p_type;
	
	private Date add_time;
	
	private Long add_user_id;
	
	private String add_user_name;
	
	private Integer order_value;
	
	private Integer is_del;
	
	public KonkaItemProperty() {

	}

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}
	
	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
	public Integer getP_type() {
		return p_type;
	}

	public void setP_type(Integer p_type) {
		this.p_type = p_type;
	}
	
	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}