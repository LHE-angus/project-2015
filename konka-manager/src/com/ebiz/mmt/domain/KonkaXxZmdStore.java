package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:02
 */
public class KonkaXxZmdStore extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long zmd_id;

	private String factory_id;

	private String store_id;
	
	private String md_name;

	private Long real_stock_count;

	private Long locked_stock_count;

	private Long stock_count;

	public KonkaXxZmdStore() {

	}

	/**
	 * @val 专卖店ID
	 */
	public Long getZmd_id() {
		return zmd_id;
	}

	/**
	 * @val 专卖店ID
	 */
	public void setZmd_id(Long zmd_id) {
		this.zmd_id = zmd_id;
	}

	/**
	 * @val 用户ID - KONKA_PE_PROD_USER.ID
	 */
	public String getFactory_id() {
		return factory_id;
	}

	/**
	 * @val 用户ID - KONKA_PE_PROD_USER.ID
	 */
	public void setFactory_id(String factory_id) {
		this.factory_id = factory_id;
	}

	/**
	 * @val 库位ID
	 */
	public String getStore_id() {
		return store_id;
	}

	/**
	 * @val 库位ID
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public Long getReal_stock_count() {
		return real_stock_count;
	}

	public void setReal_stock_count(Long realStockCount) {
		real_stock_count = realStockCount;
	}

	public Long getLocked_stock_count() {
		return locked_stock_count;
	}

	public void setLocked_stock_count(Long lockedStockCount) {
		locked_stock_count = lockedStockCount;
	}

	public Long getStock_count() {
		return stock_count;
	}

	public void setStock_count(Long stockCount) {
		stock_count = stockCount;
	}

	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String mdName) {
		md_name = mdName;
	}

}