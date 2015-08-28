package com.ebiz.mmt.domain;

import java.io.Serializable;
import com.ebiz.ssi.domain.BaseDomain;

public class KonkaParagonSales extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	@SuppressWarnings("unused")
	private static KonkaParagonSales obj = new KonkaParagonSales();

	/**
	 * sales_id
	 */
	private java.lang.Long sales_id;

	/**
	 * show_shop_code
	 */
	private java.lang.String show_shop_code;

	/**
	 * sales
	 */
	private java.math.BigDecimal sales;

	/**
	 * sale_year
	 */
	private java.lang.String sale_year;

	/**
	 * addman
	 */
	private java.lang.Long addman;

	/**
	 * addtime
	 */
	private java.util.Date addtime;

	public KonkaParagonSales() {
	}

	public void setSales_id(java.lang.Long sales_id) {
		this.sales_id = sales_id;
	}

	public java.lang.Long getSales_id() {
		return this.sales_id;
	}

	public void setShow_shop_code(java.lang.String show_shop_code) {
		this.show_shop_code = show_shop_code;
	}

	public java.lang.String getShow_shop_code() {
		return this.show_shop_code;
	}

	public void setSales(java.math.BigDecimal sales) {
		this.sales = sales;
	}

	public java.math.BigDecimal getSales() {
		return this.sales;
	}

	public void setSale_year(java.lang.String sale_year) {
		this.sale_year = sale_year;
	}

	public java.lang.String getSale_year() {
		return this.sale_year;
	}

	public void setAddman(java.lang.Long addman) {
		this.addman = addman;
	}

	public java.lang.Long getAddman() {
		return this.addman;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public java.util.Date getAddtime() {
		return this.addtime;
	}

}