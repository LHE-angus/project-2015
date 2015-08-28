package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-09-26 15:03
 */
public class JxcUseNojdxxShop extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long shop_id;
	
	private Long shop_p_index;
	
	private Date add_date;
	
	public JxcUseNojdxxShop() {

	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
	public Long getShop_p_index() {
		return shop_p_index;
	}

	public void setShop_p_index(Long shop_p_index) {
		this.shop_p_index = shop_p_index;
	}
	
	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}