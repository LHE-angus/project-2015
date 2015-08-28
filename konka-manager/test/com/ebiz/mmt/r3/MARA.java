package com.ebiz.mmt.r3;

/**
 * 物料信息
 * @author ZHOU
 *
 */
public class MARA {
	
	private String MANDT; //客户端
	private String MATNR; //物料号
	private String ERSDA; //创建日期
	private String ERNAM; //创建人
	private String LAEDA; //最后一次修改
	private String AENAM;	//最后修改人
	private String VPSTA;  //维护状态
	private String PSTAT;  //维护状态
	private String MTART;  //物料类型
	private String MATKL;	//物料组
	private String MEINS;  //基本单位
	
	private String SPART ; //产品组
	
	
	private String GROES; // 尺寸 暂时不确定是不是这个字段
	
	private String LAENG ;  //长
	
	private String BREIT ;	//宽
	
	private String HOEHE ; //高
	
	
	
	public String getGROES() {
		return GROES;
	}
	public void setGROES(String gROES) {
		GROES = gROES;
	}
	public String getLAENG() {
		return LAENG;
	}
	public void setLAENG(String lAENG) {
		LAENG = lAENG;
	}
	public String getBREIT() {
		return BREIT;
	}
	public void setBREIT(String bREIT) {
		BREIT = bREIT;
	}
	public String getHOEHE() {
		return HOEHE;
	}
	public void setHOEHE(String hOEHE) {
		HOEHE = hOEHE;
	}
	//存在物料描述表 通过MATNR关联
	private MAKT makt ;
	
	
	public MAKT getMakt() {
		return makt;
	}
	public void setMakt(MAKT makt) {
		this.makt = makt;
	}
	public String getMANDT() {
		return MANDT;
	}
	public void setMANDT(String mANDT) {
		MANDT = mANDT;
	}
	public String getMATNR() {
		return MATNR;
	}
	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}
	public String getERSDA() {
		return ERSDA;
	}
	public void setERSDA(String eRSDA) {
		ERSDA = eRSDA;
	}
	public String getERNAM() {
		return ERNAM;
	}
	public void setERNAM(String eRNAM) {
		ERNAM = eRNAM;
	}
	public String getLAEDA() {
		return LAEDA;
	}
	public void setLAEDA(String lAEDA) {
		LAEDA = lAEDA;
	}
	public String getAENAM() {
		return AENAM;
	}
	public void setAENAM(String aENAM) {
		AENAM = aENAM;
	}
	public String getVPSTA() {
		return VPSTA;
	}
	public void setVPSTA(String vPSTA) {
		VPSTA = vPSTA;
	}
	public String getPSTAT() {
		return PSTAT;
	}
	public void setPSTAT(String pSTAT) {
		PSTAT = pSTAT;
	}
	public String getMTART() {
		return MTART;
	}
	public void setMTART(String mTART) {
		MTART = mTART;
	}
	public String getMATKL() {
		return MATKL;
	}
	public void setMATKL(String mATKL) {
		MATKL = mATKL;
	}
	public String getMEINS() {
		return MEINS;
	}
	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}
	 
	public String getSPART() {
		return SPART;
	}
	public void setSPART(String sPART) {
		SPART = sPART;
	}
	
}
