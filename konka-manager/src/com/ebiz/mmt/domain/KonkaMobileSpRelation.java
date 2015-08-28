package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-15 00:43:15
 */
public class KonkaMobileSpRelation extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long seller_id;
	
	private Long shop_id;
	
	public KonkaMobileSpRelation() {

	}

	public Long getSeller_id() {
		return seller_id;
	}
	
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	
	public Long getShop_id() {
		return shop_id;
	}
	
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}
	
}