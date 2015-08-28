package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-04-14 16:44:47
 */
public class KonkaR3Zsof extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String vkorg;//分公司编码
	
	private String gjahr;//年份
	
	private String monat;//月份
	
	private Long vbeln;//R3单号(61单号)
	
	private String vbedl;//物流单号（026）
	
	private String lfdat;//
	
	private String erdat;//交货单创建日期
	
	private String shdat;//
	
	private String kunnr;//客户名称
	
	private String vtext;//
	
	private String name1;
	
	public KonkaR3Zsof() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getVkorg() {
		return vkorg;
	}
	
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	
	public String getGjahr() {
		return gjahr;
	}
	
	public void setGjahr(String gjahr) {
		this.gjahr = gjahr;
	}
	
	public String getMonat() {
		return monat;
	}
	
	public void setMonat(String monat) {
		this.monat = monat;
	}
	
	public Long getVbeln() {
		return vbeln;
	}
	
	public void setVbeln(Long vbeln) {
		this.vbeln = vbeln;
	}
	
	public String getVbedl() {
		return vbedl;
	}
	
	public void setVbedl(String vbedl) {
		this.vbedl = vbedl;
	}
	
	public String getLfdat() {
		return lfdat;
	}
	
	public void setLfdat(String lfdat) {
		this.lfdat = lfdat;
	}
	
	public String getErdat() {
		return erdat;
	}
	
	public void setErdat(String erdat) {
		this.erdat = erdat;
	}
	
	public String getShdat() {
		return shdat;
	}
	
	public void setShdat(String shdat) {
		this.shdat = shdat;
	}
	
	public String getKunnr() {
		return kunnr;
	}
	
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	
	public String getVtext() {
		return vtext;
	}
	
	public void setVtext(String vtext) {
		this.vtext = vtext;
	}
	
	public String getName1() {
		return name1;
	}
	
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
}