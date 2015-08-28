package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * 
 * @author Xing,XiuDong
 * @date 2012-04-13
 */
public class KonkaXxZmdPdStore extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String md_name;

	private String factory_id;

	private String store_id;

	private Long real_stock_count;

	private Long locked_stock_count;

	private Long stock_count;

	public KonkaXxZmdPdStore() {

	}

	public String getFactory_id() {
		return factory_id;
	}

	public void setFactory_id(String factoryId) {
		factory_id = factoryId;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String storeId) {
		store_id = storeId;
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