package com.ebiz.mmt.r3;

public class ZLEBIN {

	// ZPNUM CHAR6 CHAR 6 0 长度为6的字符字段
	// CHARG CHARG_D CHAR 10 0 批号
	// MATNR MATNR CHAR 18 0 物料号
	// MAKTX MAKTX CHAR 40 0 物料描述（短文本）
	// NAME1 NAME1_GP CHAR 35 0 名称 1

	// LGBER LGBER CHAR 3 0 存储区
	// LPTYP LVS_LPTYP CHAR 2 0 仓位类型
	// PTYPT LVS_PTYPT CHAR 20 0 仓位类型说明

	// WERKS WERKS_D CHAR 4 0 工厂
	// LGORT LGORT_D CHAR 4 0 库存地点(库位)
	// LGPLA 仓位
	// LGNUM LGNUM CHAR 3 0 仓库号
	// LGTYP LGTYP CHAR 3 0 仓储类型

	// MEINS MEINS UNIT 3 0 基本计量单位
	// NEWPAGE FLAG CHAR 1 0 一般标记
	// LINES CHAR13 CHAR 13 0 13位字符字段
	// NEWP FLAG CHAR 1 0 一般标记
	// EDATU EDATU DATS 8 0 计划行日期

	// VERPR VERPR CURR 11 2 移动平均价格/周期单价
	// SALK3 SALK3 CURR 13 2 估价的总库存价值
	// VERME LQUA_VERME QUAN 13 3 可用库存
	// TRAME LQUA_TRAME QUAN 13 3 未清转储数量
	// MENGE LQUA_VERME QUAN 13 3 可用库存
	// LINE INT2 5 0 行记录数量


	private String ZPNUM;
	private String CHARG;
	private String MATNR;
	private String MAKTX;
	private String NAME1;

	private String LGBER;
	private String LPTYP;
	private String PTYPT;

	private String WERKS;
	private String LGORT;
	private String LGPLA;
	private String LGNUM;
	private String LGTYP;

	private String MEINS;
	private String NEWPAGE;
	private String LINES;
	private String NEWP;
	private String EDATU;

	private double VERPR;
	private double SALK3;
	private double VERME;
	private double TRAME;
	private double MENGE;
	private double LINE;

	public ZLEBIN() {

	}
	public String getZPNUM() {
		return ZPNUM;
	}

	public void setZPNUM(String zPNUM) {
		ZPNUM = zPNUM;
	}

	public String getCHARG() {
		return CHARG;
	}

	public void setCHARG(String cHARG) {
		CHARG = cHARG;
	}

	public String getMATNR() {
		return MATNR;
	}

	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}

	public String getMAKTX() {
		return MAKTX;
	}

	public void setMAKTX(String mAKTX) {
		MAKTX = mAKTX;
	}

	public String getNAME1() {
		return NAME1;
	}

	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}

	public String getLGBER() {
		return LGBER;
	}

	public void setLGBER(String lGBER) {
		LGBER = lGBER;
	}

	public String getLPTYP() {
		return LPTYP;
	}

	public void setLPTYP(String lPTYP) {
		LPTYP = lPTYP;
	}

	public String getPTYPT() {
		return PTYPT;
	}

	public void setPTYPT(String pTYPT) {
		PTYPT = pTYPT;
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

	public String getLGPLA() {
		return LGPLA;
	}

	public void setLGPLA(String lGPLA) {
		LGPLA = lGPLA;
	}

	public String getLGNUM() {
		return LGNUM;
	}

	public void setLGNUM(String lGNUM) {
		LGNUM = lGNUM;
	}

	public String getLGTYP() {
		return LGTYP;
	}

	public void setLGTYP(String lGTYP) {
		LGTYP = lGTYP;
	}

	public String getMEINS() {
		return MEINS;
	}

	public void setMEINS(String mEINS) {
		MEINS = mEINS;
	}

	public String getNEWPAGE() {
		return NEWPAGE;
	}

	public void setNEWPAGE(String nEWPAGE) {
		NEWPAGE = nEWPAGE;
	}

	public String getLINES() {
		return LINES;
	}

	public void setLINES(String lINES) {
		LINES = lINES;
	}

	public String getNEWP() {
		return NEWP;
	}

	public void setNEWP(String nEWP) {
		NEWP = nEWP;
	}

	public String getEDATU() {
		return EDATU;
	}

	public void setEDATU(String eDATU) {
		EDATU = eDATU;
	}

	public double getVERPR() {
		return VERPR;
	}

	public void setVERPR(double vERPR) {
		VERPR = vERPR;
	}

	public double getSALK3() {
		return SALK3;
	}

	public void setSALK3(double sALK3) {
		SALK3 = sALK3;
	}

	public double getVERME() {
		return VERME;
	}

	public void setVERME(double vERME) {
		VERME = vERME;
	}

	public double getTRAME() {
		return TRAME;
	}

	public void setTRAME(double tRAME) {
		TRAME = tRAME;
	}

	public double getMENGE() {
		return MENGE;
	}

	public void setMENGE(double mENGE) {
		MENGE = mENGE;
	}

	public double getLINE() {
		return LINE;
	}

	public void setLINE(double lINE) {
		LINE = lINE;
	}

}