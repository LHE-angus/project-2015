package com.ebiz.mmt.r3;

import java.math.BigDecimal;

public class StockCheckResult implements Comparable<StockCheckResult> {
	
	private String bukrs ; //公分司(知道客户,就知道它的分公司)
	
	private String matnr ;//物料
	
	private String werks; //工厂   不需要传参去检查,因有分公司就已经知道

	private String name1;// 工厂名称

	private String lgort ; //仓位  不需要传参去检查,因有分公司就已经知道

	private String lgobe;// 仓位名称

	private BigDecimal speme;// 交货单锁定库存

	private BigDecimal labst;// LABST 非限制使用库存

	private BigDecimal lamount; // 剩下量 labst - speme

	private BigDecimal amount; // 需求

	private int isOk; // 是否满足

	public BigDecimal getSpeme() {
		return speme;
	}

	public void setSpeme(BigDecimal speme) {
		this.speme = speme;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}
 
	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getLamount() {
		return lamount;
	}

	public void setLamount(BigDecimal lamount) {
		this.lamount = lamount;
	}

	public int getIsOk() {
		return isOk;
	}

	public void setIsOk(int isOk) {
		this.isOk = isOk;
	}

	/**
	 * @return the bukrs
	 */
	public String getBukrs() {
		return bukrs;
	}

	/**
	 * @param bukrs the bukrs to set
	 */
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getLgobe() {
		return lgobe;
	}

	public void setLgobe(String lgobe) {
		this.lgobe = lgobe;
	}

	@Override
	public int compareTo(StockCheckResult o) {
		return o.getLamount().compareTo(this.getLamount());
	}

	public BigDecimal getLabst() {
		return labst;
	}

	public void setLabst(BigDecimal labst) {
		this.labst = labst;
	}
	
}
