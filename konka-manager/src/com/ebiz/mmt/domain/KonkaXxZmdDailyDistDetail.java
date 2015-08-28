package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Ren,zhong
 * @version 2012-03-22 13:53
 */
public class KonkaXxZmdDailyDistDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dist_id;
	
	private Long sell_bill_id;
	
	private Long add_bill_user_id;
	
	private Long sell_bill_detail_id;
	
	private Long pd_dist_id;
	
	private String pd_cls_name;
	
	private String pd_name;
	
	private String factory_id;
	
	private String store_id;
	
	private String store_name;
	
	private Long counts;
	
	private Long dst_user_id;
	
	private String dst_user_name;
	
	public KonkaXxZmdDailyDistDetail() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDist_id() {
		return dist_id;
	}

	public void setDist_id(Long dist_id) {
		this.dist_id = dist_id;
	}
	
	public Long getSell_bill_id() {
		return sell_bill_id;
	}

	public void setSell_bill_id(Long sell_bill_id) {
		this.sell_bill_id = sell_bill_id;
	}
	
	public Long getAdd_bill_user_id() {
		return add_bill_user_id;
	}

	public void setAdd_bill_user_id(Long add_bill_user_id) {
		this.add_bill_user_id = add_bill_user_id;
	}
	
	public Long getSell_bill_detail_id() {
		return sell_bill_detail_id;
	}

	public void setSell_bill_detail_id(Long sell_bill_detail_id) {
		this.sell_bill_detail_id = sell_bill_detail_id;
	}
	
	public Long getPd_dist_id() {
		return pd_dist_id;
	}

	public void setPd_dist_id(Long pd_dist_id) {
		this.pd_dist_id = pd_dist_id;
	}
	
	public String getPd_cls_name() {
		return pd_cls_name;
	}

	public void setPd_cls_name(String pd_cls_name) {
		this.pd_cls_name = pd_cls_name;
	}
	
	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	public String getFactory_id() {
		return factory_id;
	}

	public void setFactory_id(String factory_id) {
		this.factory_id = factory_id;
	}
	
	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	
	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}
	
	public Long getDst_user_id() {
		return dst_user_id;
	}

	public void setDst_user_id(Long dst_user_id) {
		this.dst_user_id = dst_user_id;
	}
	
	public String getDst_user_name() {
		return dst_user_name;
	}

	public void setDst_user_name(String dst_user_name) {
		this.dst_user_name = dst_user_name;
	}
	
}