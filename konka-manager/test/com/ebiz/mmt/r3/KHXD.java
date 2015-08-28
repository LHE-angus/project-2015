package com.ebiz.mmt.r3;

import java.math.BigDecimal;

public class KHXD {
	private String VKORG; // 销售组织
	private String KUNNR ;	//客户编码
	private String CTLPC; // (CTLPC 风险类别)信用评估等级
	private String DBWAE; // 币种;

	private BigDecimal KLIME;// (原始分配金额)信贷限额: 单个控制范围的限额
	private BigDecimal KLIMG;// (当月分配金额)信贷限额: 所有控制区的总信贷限额
	private BigDecimal DBEKR;// (信用账期额度)固定账期额度

	// 重点关注数据
	private BigDecimal ZLIMT;// (总经理担保额度)临时信贷额度
	private BigDecimal KLIMK;// (信贷限额)
	private BigDecimal OBLIG;// 信贷风险总额
	private BigDecimal ZSYED; // 剩余额度
	// 重点关注数据

	private BigDecimal KLPRZ;// (使用的信贷限额)
	private BigDecimal SKFOR;// (余额)
	private String NAME1; // 客户名称
	private String KUKLA;// 客户分类
	private String VTEXT;// 描述

	private BigDecimal SAUFT ;//用于贷方限额检查的销售值总额
	private BigDecimal UMSAV ;//用本币计的结转余额

	private BigDecimal SYED; // 自定义的
	private String ZSYZT; //

	public String getKUNNR() {
		return KUNNR;
	}
	public void setKUNNR(String kUNNR) {
		KUNNR = kUNNR;
	}
	public String getNAME1() {
		return NAME1;
	}
	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}
	public String getVKORG() {
		return VKORG;
	}
	public void setVKORG(String vKORG) {
		VKORG = vKORG;
	}
	public String getCTLPC() {
		return CTLPC;
	}
	public void setCTLPC(String cTLPC) {
		CTLPC = cTLPC;
	}
	public String getDBWAE() {
		return DBWAE;
	}
	public void setDBWAE(String dBWAE) {
		DBWAE = dBWAE;
	}
	public BigDecimal getDBEKR() {
		return DBEKR;
	}
	public void setDBEKR(BigDecimal dBEKR) {
		DBEKR = dBEKR;
	}
	public BigDecimal getZLIMT() {
		return ZLIMT;
	}
	public void setZLIMT(BigDecimal zLIMT) {
		ZLIMT = zLIMT;
	}
	public BigDecimal getKLIMK() {
		return KLIMK;
	}
	public void setKLIMK(BigDecimal kLIMK) {
		KLIMK = kLIMK;
	}
	public BigDecimal getOBLIG() {
		return OBLIG;
	}
	public void setOBLIG(BigDecimal oBLIG) {
		OBLIG = oBLIG;
	}
	public BigDecimal getKLPRZ() {
		return KLPRZ;
	}
	public void setKLPRZ(BigDecimal kLPRZ) {
		KLPRZ = kLPRZ;
	}
	public BigDecimal getSKFOR() {
		return SKFOR;
	}
	public void setSKFOR(BigDecimal sKFOR) {
		SKFOR = sKFOR;
	}
	public BigDecimal getSAUFT() {
		return SAUFT;
	}
	public void setSAUFT(BigDecimal sAUFT) {
		SAUFT = sAUFT;
	}

	public BigDecimal getSYED() {
		return SYED;
	}

	public void setSYED(BigDecimal sYED) {
		SYED = sYED;
	}

	public BigDecimal getUMSAV() {
		return UMSAV;
	}
	public void setUMSAV(BigDecimal uMSAV) {
		UMSAV = uMSAV;
	}
	public BigDecimal getKLIME() {
		return KLIME;
	}
	public void setKLIME(BigDecimal kLIME) {
		KLIME = kLIME;
	}
	public BigDecimal getKLIMG() {
		return KLIMG;
	}
	public void setKLIMG(BigDecimal kLIMG) {
		KLIMG = kLIMG;
	}
	
	/**
	 * @return the kUKLA
	 */
	public String getKUKLA() {
		return KUKLA;
	}
	/**
	 * @param kUKLA the kUKLA to set
	 */
	public void setKUKLA(String kUKLA) {
		KUKLA = kUKLA;
	}
	/**
	 * @return the vTEXT
	 */
	public String getVTEXT() {
		return VTEXT;
	}
	/**
	 * @param vTEXT the vTEXT to set
	 */
	public void setVTEXT(String vTEXT) {
		VTEXT = vTEXT;
	}
	public BigDecimal getZSYED() {
		return ZSYED;
	}
	public void setZSYED(BigDecimal zSYED) {
		ZSYED = zSYED;
	}
	/**
	 * @return the zSYZT
	 */
	public String getZSYZT() {
		return ZSYZT;
	}
	/**
	 * @param zSYZT the zSYZT to set
	 */
	public void setZSYZT(String zSYZT) {
		ZSYZT = zSYZT;
	}
	

}
