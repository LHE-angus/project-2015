package com.ebiz.mmt.r3;

import java.util.Calendar;
import java.util.Hashtable;

/**
 * Z_RFC_ZDMTRXS接口查询参数 每一个字段都是一个hashTable
 * 
 * 分别存放2条数据 如V_LFGJA: 1. LOW = 2013 2. HIGH = 2022
 * 
 * 也有可能是:1. LOW= 1988,2000,2011 2.HIGH = 2012,2013,2014 (此种情况暂时不实现)
 * 
 * 另外,V_LFGJA 年度 格式必须为 yyyy ;V_LFMON 月份格式必须为MM;
 * 
 * 而且hashTable key和value都不能放null
 * 
 * @author ZHOU
 * 
 */
public class ZdmtrxCriteria {

	// V_LFGJA 年度 多值
	// V_LFMON 月份 多值
	// V_KUNNR 客户 多值
	// V_VKORG 销售组织 多值
	// V_MATNR 机型 多值
	private static final Calendar c = Calendar.getInstance();
	// 年度
	private Hashtable<String, String> V_LFGJA;
	// 月份
	private Hashtable<String, String> V_LFMON;
	// 销售组织
	private Hashtable<String, String> V_VKORG;
	// 客户
	private Hashtable<String, String> V_KUNNR;
	// 机型
	private Hashtable<String, String> V_MATNR;

	public ZdmtrxCriteria() {
		String year = c.get(Calendar.YEAR) + "";
		String month = "";
		if ((c.get(Calendar.MONTH) + 1 )>= 10) {
			month = (c.get(Calendar.MONTH) + 1 )+"";
		}else{
			month = "0" + (c.get(Calendar.MONTH) + 1);
		}
		V_LFGJA = new Hashtable<String, String>();
		V_LFGJA.put("LOW", year);
		V_LFGJA.put("HIGH", year);

		V_LFMON = new Hashtable<String, String>();
		V_LFMON.put("LOW", month);
		V_LFMON.put("HIGH", month);

		V_VKORG = new Hashtable<String, String>();
		V_KUNNR = new Hashtable<String, String>();
		V_MATNR = new Hashtable<String, String>();
	}

	public Hashtable<String, String> getV_LFGJA() {
		return V_LFGJA;
	}

	public void setV_LFGJA(Hashtable<String, String> v_LFGJA) {
		V_LFGJA = v_LFGJA;
	}

	public Hashtable<String, String> getV_LFMON() {
		return V_LFMON;
	}

	public void setV_LFMON(Hashtable<String, String> v_LFMON) {
		V_LFMON = v_LFMON;
	}

	public Hashtable<String, String> getV_VKORG() {
		return V_VKORG;
	}

	public void setV_VKORG(Hashtable<String, String> v_VKORG) {
		V_VKORG = v_VKORG;
	}

	public Hashtable<String, String> getV_KUNNR() {
		return V_KUNNR;
	}

	public void setV_KUNNR(Hashtable<String, String> v_KUNNR) {
		V_KUNNR = v_KUNNR;
	}

	public Hashtable<String, String> getV_MATNR() {
		return V_MATNR;
	}

	public void setV_MATNR(Hashtable<String, String> v_MATNR) {
		V_MATNR = v_MATNR;
	}

	public void resetFieldValue() {
		this.getV_KUNNR().clear();
		this.getV_LFGJA().clear();
		this.getV_LFMON().clear();
		this.getV_MATNR().clear();
		this.getV_VKORG().clear();
	}

}
