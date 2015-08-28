package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-07-15 14:32:12
 */
public class EcPdEavlZan extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long goods_id;
	
	private Integer own_sys;
	
	private Long user_id;
	
	private Date add_date;
	
	private Long eavl_id;
	
	public EcPdEavlZan() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getGoods_id() {
		return goods_id;
	}
	
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getEavl_id() {
		return eavl_id;
	}
	
	public void setEavl_id(Long eavl_id) {
		this.eavl_id = eavl_id;
	}
	
}