package com.ebiz.mmt.r3;

public class SOXX {
	private String KUNNR; // 客户编码

	private String AUART; // 凭证类型
	private String POSNR; // 销售凭证项目号
	private String VBELN; // 单号
	private String VKORG; // 销售组织
	private String VTWEG; // 分销渠道
	private String SPART; // 产品组
	private String MATNR;// 物料号
	private String MATKL; // 物料组
	private String MEINS; // 单位

	private String VDATU; // 创建日期(会计用)
	private String AUDAT; // 凭证日期(会计用)
	private String ERDAT;// 单据在R/3创建日期

	private String WEKUNNR;// 送达方
	private String REKUNNR;// 出具发票方
	private String RGKUNNR;// 付款方

	private String WERKS;// 工厂
	private String LGORT;// 出货仓位

	private String VKBUR;// 办事处 20130910 add

	// ===================================================//
	private String NETWR; // 凭证货币计量的净价值

	private String CMPRE0;// 单价(含税) 20130910 add 项目信贷价格
	private String CMPRE;// 单价(净值) 20130910 add 项目信贷价格

	private String KZWI1;// 总净值金额(含税) 20130910 add
	private String KZWI6;// 总金额(含税)

	private String KWMENG; // 订单行每一机型所购买的数量 重要

	private String JWMENG; // 交货单数量(可按销售单号直接查,已sum CALL_Get_SOXX才有,以后会弃用,新0)
	private String MWMENG;// 已发货数量(可按销售单号直接查,已sum,CALL_Get_SOXX 才有,以后会弃用,新0)
	private String RWMENG;// 开发票数量(可按销售单号直接查,已sum,CALL_Get_SOXX 才有,以后会弃用,新0)

	// ===================================================//

	private String LFIMG_L;// 实际已交货量（可按销售单号直接查,已sum CALL_Get_SOXX_New才有）
	private String KDMAT;// 客户所用的物料编号 (CALL_Get_SOXX_New才有)
	private String BSTNK; // 客户采购订单编号(CALL_Get_SOXX_New才有)
	private String BSTDK;// 客户采购订单日期(CALL_Get_SOXX_New才有)

	private String VBELN_L;// FK交货单号(CALL_Get_SOXX_New才有)
	private String WADAT_IST;// 实际货物移动日期 交货日期(CALL_Get_SOXX_New才有)
	private String VBELN_LES;// 物流交货单号(CALL_Get_SOXX_New才有)

	private String POSNR_L;// 交货单的行项目(一个订单可能分多次来交货)(CALL_Get_SOXX_New才有)
	private String PSTYV_L;// 交货单项目类别(CALL_Get_SOXX_New才有)

	private String WERKS_L;// 发货工厂(CALL_Get_SOXX_New才有)
	private String LGORT_L;// 发货地点(CALL_Get_SOXX_New才有)

	private String K007;

	private String RB00;

	public String getK007() {
		return K007;
	}

	public void setK007(String k007) {
		K007 = k007;
	}

	public String getRB00() {
		return RB00;
	}

	public void setRB00(String rB00) {
		RB00 = rB00;
	}

	public String getVKBUR() {
		return VKBUR;
	}

	public void setVKBUR(String vKBUR) {
		VKBUR = vKBUR;
	}

	public String getCMPRE() {
		return CMPRE;
	}

	public void setCMPRE(String cMPRE) {
		CMPRE = cMPRE;
	}

	public String getCMPRE0() {
		return CMPRE0;
	}

	public void setCMPRE0(String cMPRE0) {
		CMPRE0 = cMPRE0;
	}

	public String getKZWI1() {
		return KZWI1;
	}

	public void setKZWI1(String kZWI1) {
		KZWI1 = kZWI1;
	}

	public String getKZWI6() {
		return KZWI6;
	}

	public void setKZWI6(String kZWI6) {
		KZWI6 = kZWI6;
	}

	public String getJWMENG() {
		return JWMENG;
	}

	public void setJWMENG(String jWMENG) {
		JWMENG = jWMENG;
	}

	public String getMWMENG() {
		return MWMENG;
	}

	public void setMWMENG(String mWMENG) {
		MWMENG = mWMENG;
	}

	public String getRWMENG() {
		return RWMENG;
	}

	public void setRWMENG(String rWMENG) {
		RWMENG = rWMENG;
	}

	public String getWEKUNNR() {
		return WEKUNNR;
	}

	public void setWEKUNNR(String wEKUNNR) {
		WEKUNNR = wEKUNNR;
	}

	public String getREKUNNR() {
		return REKUNNR;
	}

	public void setREKUNNR(String rEKUNNR) {
		REKUNNR = rEKUNNR;
	}

	public String getRGKUNNR() {
		return RGKUNNR;
	}

	public void setRGKUNNR(String rGKUNNR) {
		RGKUNNR = rGKUNNR;
	}

	public String getWERKS() {
		return WERKS;
	}

	public void setWERKS(String wERKS) {
		WERKS = wERKS;
	}

	public String getLGORT() {
		return LGORT;
	}

	public void setLGORT(String lGORT) {
		LGORT = lGORT;
	}

	/**
	 * @return the kUNNR
	 */
	public String getKUNNR() {
		return KUNNR;
	}

	/**
	 * @param kUNNR
	 *            the kUNNR to set
	 */
	public void setKUNNR(String kUNNR) {
		KUNNR = kUNNR;
	}

	/**
	 * @return the aUART
	 */
	public String getAUART() {
		return AUART;
	}

	/**
	 * @param aUART
	 *            the aUART to set
	 */
	public void setAUART(String aUART) {
		AUART = aUART;
	}

	/**
	 * @return the aUDAT
	 */
	public String getAUDAT() {
		return AUDAT;
	}

	/**
	 * @param aUDAT
	 *            the aUDAT to set
	 */
	public void setAUDAT(String aUDAT) {
		AUDAT = aUDAT;
	}

	/**
	 * @return the vKORG
	 */
	public String getVKORG() {
		return VKORG;
	}

	/**
	 * @param vKORG
	 *            the vKORG to set
	 */
	public void setVKORG(String vKORG) {
		VKORG = vKORG;
	}

	/**
	 * @return the vTWEG
	 */
	public String getVTWEG() {
		return VTWEG;
	}

	/**
	 * @param vTWEG
	 *            the vTWEG to set
	 */
	public void setVTWEG(String vTWEG) {
		VTWEG = vTWEG;
	}

	/**
	 * @return the sPART
	 */
	public String getSPART() {
		return SPART;
	}

	/**
	 * @param sPART
	 *            the sPART to set
	 */
	public void setSPART(String sPART) {
		SPART = sPART;
	}

	/**
	 * @return the vDATU
	 */
	public String getVDATU() {
		return VDATU;
	}

	/**
	 * @param vDATU
	 *            the vDATU to set
	 */
	public void setVDATU(String vDATU) {
		VDATU = vDATU;
	}

	/**
	 * @return the vBELN
	 */
	public String getVBELN() {
		return VBELN;
	}

	/**
	 * @param vBELN
	 *            the vBELN to set
	 */
	public void setVBELN(String vBELN) {
		VBELN = vBELN;
	}

	/**
	 * @return the pOSNR
	 */
	public String getPOSNR() {
		return POSNR;
	}

	/**
	 * @param pOSNR
	 *            the pOSNR to set
	 */
	public void setPOSNR(String pOSNR) {
		POSNR = pOSNR;
	}

	/**
	 * @return the mATNR
	 */
	public String getMATNR() {
		return MATNR;
	}

	/**
	 * @param mATNR
	 *            the mATNR to set
	 */
	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}

	/**
	 * @return the mATKL
	 */
	public String getMATKL() {
		return MATKL;
	}

	/**
	 * @param mATKL
	 *            the mATKL to set
	 */
	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}

	/**
	 * @return the mEINS
	 */
	public String getMEINS() {
		return MEINS;
	}

	/**
	 * @param mEINS
	 *            the mEINS to set
	 */
	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}

	/**
	 * @return the nETWR
	 */
	public String getNETWR() {
		return NETWR;
	}

	/**
	 * @param nETWR
	 *            the nETWR to set
	 */
	public void setNETWR(String nETWR) {
		NETWR = nETWR;
	}

	/**
	 * @return the kWMENG
	 */
	public String getKWMENG() {
		return KWMENG;
	}

	/**
	 * @param kWMENG
	 *            the kWMENG to set
	 */
	public void setKWMENG(String kWMENG) {
		KWMENG = kWMENG;
	}

	public String getERDAT() {
		return ERDAT;
	}

	public void setERDAT(String eRDAT) {
		ERDAT = eRDAT;
	}

	public String getKDMAT() {
		return KDMAT;
	}

	public void setKDMAT(String kDMAT) {
		KDMAT = kDMAT;
	}

	public String getBSTNK() {
		return BSTNK;
	}

	public void setBSTNK(String bSTNK) {
		BSTNK = bSTNK;
	}

	public String getBSTDK() {
		return BSTDK;
	}

	public void setBSTDK(String bSTDK) {
		BSTDK = bSTDK;
	}

	public String getVBELN_L() {
		return VBELN_L;
	}

	public void setVBELN_L(String vBELN_L) {
		VBELN_L = vBELN_L;
	}

	public String getWADAT_IST() {
		return WADAT_IST;
	}

	public void setWADAT_IST(String wADAT_IST) {
		WADAT_IST = wADAT_IST;
	}

	public String getVBELN_LES() {
		return VBELN_LES;
	}

	public void setVBELN_LES(String vBELN_LES) {
		VBELN_LES = vBELN_LES;
	}

	public String getPOSNR_L() {
		return POSNR_L;
	}

	public void setPOSNR_L(String pOSNR_L) {
		POSNR_L = pOSNR_L;
	}

	public String getPSTYV_L() {
		return PSTYV_L;
	}

	public void setPSTYV_L(String pSTYV_L) {
		PSTYV_L = pSTYV_L;
	}

	public String getWERKS_L() {
		return WERKS_L;
	}

	public void setWERKS_L(String wERKS_L) {
		WERKS_L = wERKS_L;
	}

	public String getLGORT_L() {
		return LGORT_L;
	}

	public void setLGORT_L(String lGORT_L) {
		LGORT_L = lGORT_L;
	}

	public String getLFIMG_L() {
		return LFIMG_L;
	}

	public void setLFIMG_L(String lFIMG_L) {
		LFIMG_L = lFIMG_L;
	}

}
