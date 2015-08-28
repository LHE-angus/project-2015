package com.ebiz.mmt.r3;

import java.util.Hashtable;

/**
 * zles29a接口查询参数 每一个字段都是一个hashTable
 * 
 * 分别存放2条数据 如V_BZIRK: 1. LOW = 000 2. HIGH = 999
 * 
 * 也有可能是:1. LOW= 000,0001,0003 2.HIGH = 333;444;888 (此种情况暂时不实现)
 * 
 * 另外,hashTable key和value都不能放null
 * 
 * @author ZHOU
 * 
 */
public class Zles29aCriteria {
	
	// 销售日期
	private Hashtable<String, String> V_BSTDK;
	// 销售片区
	private Hashtable<String, String> V_BZIRK;
	// 分公司名称
	private Hashtable<String, String> V_CLASS;
	// 转储单号
	private Hashtable<String, String> V_EBELN;
	// 转储单日期
	private Hashtable<String, String> V_EINDT;
	// 物料组
	private Hashtable<String, String> V_MATKL;
	// 物料号
	private Hashtable<String, String> V_MATNR;
	// 分销渠道
	private Hashtable<String, String> V_VTWEG;
	// 销售组织
	private String V_VKORG;
	
	public Zles29aCriteria() {
		V_BSTDK = new Hashtable<String, String>();
		// V_BSTDK.put("LOW", "23232");
		// V_BSTDK.put("HIGH", "33333");

		V_BZIRK = new Hashtable<String, String>();
		V_CLASS = new Hashtable<String, String>();

		// default
		V_EBELN = new Hashtable<String, String>();
		V_EBELN.put("LOW", "UA00-00000");
		V_EBELN.put("HIGH", "UZ99-99999");
		V_EINDT = new Hashtable<String, String>();
		V_MATKL = new Hashtable<String, String>();
		V_MATNR = new Hashtable<String, String>();
		V_VTWEG = new Hashtable<String, String>();
		// default
		V_VKORG = new String("1001");
	};


	public Hashtable<String, String> getV_BSTDK() {
		return V_BSTDK;
	}

	public void setV_BSTDK(Hashtable<String, String> v_BSTDK) {
		V_BSTDK = v_BSTDK;
	}

	public Hashtable<String, String> getV_BZIRK() {
		return V_BZIRK;
	}

	public void setV_BZIRK(Hashtable<String, String> v_BZIRK) {
		V_BZIRK = v_BZIRK;
	}

	public Hashtable<String, String> getV_CLASS() {
		return V_CLASS;
	}

	public void setV_CLASS(Hashtable<String, String> v_CLASS) {
		V_CLASS = v_CLASS;
	}

	public Hashtable<String, String> getV_EBELN() {
		return V_EBELN;
	}

	public void setV_EBELN(Hashtable<String, String> v_EBELN) {
		V_EBELN = v_EBELN;
	}

	public Hashtable<String, String> getV_EINDT() {
		return V_EINDT;
	}

	public void setV_EINDT(Hashtable<String, String> v_EINDT) {
		V_EINDT = v_EINDT;
	}

	public Hashtable<String, String> getV_MATKL() {
		return V_MATKL;
	}

	public void setV_MATKL(Hashtable<String, String> v_MATKL) {
		V_MATKL = v_MATKL;
	}

	public Hashtable<String, String> getV_MATNR() {
		return V_MATNR;
	}

	public void setV_MATNR(Hashtable<String, String> v_MATNR) {
		V_MATNR = v_MATNR;
	}

	public Hashtable<String, String> getV_VTWEG() {
		return V_VTWEG;
	}

	public void setV_VTWEG(Hashtable<String, String> v_VTWEG) {
		V_VTWEG = v_VTWEG;
	}

	public String getV_VKORG() {
		return V_VKORG;
	}

	public void setV_VKORG(String v_VKORG) {
		V_VKORG = v_VKORG;
	}

	public static void main(String[] args) {
		Zles29aCriteria zc = new Zles29aCriteria();
	}

}
