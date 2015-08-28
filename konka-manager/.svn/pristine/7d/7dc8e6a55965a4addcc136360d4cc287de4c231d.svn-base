package com.ebiz.mmt.r3;

import java.io.Serializable;

/**
 * R3客户主数据
 * 
 * @author ZHOU
 * 
 */
public class KNA1 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String MANDT;//客户端

	private String KUNNR;//客户编码

	private String LAND1;//国家代码

	private String NAME1;//名称1

	private String NAME2;//名称2

	private String ORT01;//城市

	private String PSTLZ;//邮政编码
	
	private String ADRNR;// 地址代码
	
	private String STREET;// 地址 街道 取一个地址
	
	private String BBBNR ;//标准公司号
	
	private String FISKN ;//带有财务地址的主记录帐号
	
	private String ERDAT ; //创建时间
	
	private String KUKLA ;//客户类型  id
	// F003 分公司内部客户
	// F004 F005 应收款客户
	// F007 售后材料客户
	// F011 挂账门店样机
	private String KTOKD; // 标注类型 f001

	private boolean IS_AG; // 是否是售达方

	private boolean IS_WE;// 是否是送达方

	// kna1基本客户数据,knb1有公司代码,knvv销售组织的
	// 这两个字段,在sap里面应该是在knb1的.但sap接口提供的时候，写在了kna1上。
	private String LOEVM;// 删除标识 R3有X表示删除 此处转 "x"=1 ; "-"= 0
	private String CASSD;// 销售冻结 R3有X表示冻结 此处转 "x"=1 ; "-"= 0
	//

	private KNB1 knb1 ;//对于客户来说, 每个客户都有自己的分公司
	
	public String getERDAT() {
		return ERDAT;
	}

	public void setERDAT(String eRDAT) {
		ERDAT = eRDAT;
	}
	

	public boolean getIS_AG() {
		return IS_AG;
	}

	public void setIS_AG(boolean iS_AG) {
		IS_AG = iS_AG;
	}

	public boolean getIS_WE() {
		return IS_WE;
	}

	public void setIS_WE(boolean iS_WE) {
		IS_WE = iS_WE;
	}

	public KNB1 getKnb1() {
		return knb1;
	}

	public void setKnb1(KNB1 knb1) {
		this.knb1 = knb1;
	}

	public String getADRNR() {
		return ADRNR;
	}

	public void setADRNR(String aDRNR) {
		ADRNR = aDRNR;
	}

	public String getBBBNR() {
		return BBBNR;
	}

	public void setBBBNR(String bBBNR) {
		BBBNR = bBBNR;
	}

	public String getFISKN() {
		return FISKN;
	}

	public void setFISKN(String fISKN) {
		FISKN = fISKN;
	}

	public String getDEAR1() {
		return DEAR1;
	}

	public void setDEAR1(String dEAR1) {
		DEAR1 = dEAR1;
	}

	public String getDEAR2() {
		return DEAR2;
	}

	public void setDEAR2(String dEAR2) {
		DEAR2 = dEAR2;
	}

	public String getDEAR3() {
		return DEAR3;
	}

	public void setDEAR3(String dEAR3) {
		DEAR3 = dEAR3;
	}

	public String getDEAR4() {
		return DEAR4;
	}

	public void setDEAR4(String dEAR4) {
		DEAR4 = dEAR4;
	}

	public String getDEAR5() {
		return DEAR5;
	}

	public void setDEAR5(String dEAR5) {
		DEAR5 = dEAR5;
	}

	private String DEAR1 ;
	private String DEAR2 ;
	private String DEAR3 ;
	private String DEAR4 ;
	private String DEAR5 ; //缺省售达方
	
	
	public KNA1(){
		
	}

	public String getMANDT() {
		return MANDT;
	}

	public void setMANDT(String mANDT) {
		MANDT = mANDT;
	}

	public String getKUNNR() {
		return KUNNR;
	}

	public void setKUNNR(String kUNNR) {
		KUNNR = kUNNR;
	}

	public String getLAND1() {
		return LAND1;
	}

	public void setLAND1(String lAND1) {
		LAND1 = lAND1;
	}

	public String getNAME1() {
		return NAME1;
	}

	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}

	public String getNAME2() {
		return NAME2;
	}

	public void setNAME2(String nAME2) {
		NAME2 = nAME2;
	}

	public String getORT01() {
		return ORT01;
	}

	public void setORT01(String oRT01) {
		ORT01 = oRT01;
	}

	public String getPSTLZ() {
		return PSTLZ;
	}

	public void setPSTLZ(String pSTLZ) {
		PSTLZ = pSTLZ;
	}

	public String getKTOKD() {
		return KTOKD;
	}

	public void setKTOKD(String kTOKD) {
		KTOKD = kTOKD;
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

	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}

	public String getSTREET() {
		return STREET;
	}

	public String getLOEVM() {
		return LOEVM;
	}

	public void setLOEVM(String lOEVM) {
		LOEVM = lOEVM;
	}

	public String getCASSD() {
		return CASSD;
	}

	public void setCASSD(String cASSD) {
		CASSD = cASSD;
	}

//	private String REGIO;
//
//	private String SORTL;
//
//	private String STRAS;
//
//	private String TELF1;
//
//	private String TELFX;
//
//	private String XCPDK;
//
//	private String ADRNR;
//
//	private String MCOD1;
//
//	private String MCOD2;
//
//	private String MCOD3;
//
//	private String ANRED;
//
//	private String AUFSD;
//
//	private String BAHNE;
//
//	private String BAHNS;
//
//	private String BBBNR;
//	private String BBSNR;
//	private String BEGRU;
//	private String BRSCH;
//	private String BUBKZ;
//	private String DATLT;
//	private String ERDAT;
//	private String ERNAM;
//	private String EXABL;
//	private String FAKSD;
//	private String FISKN;
//	private String KNAZK;
//	private String KNRZA;
//	private String KONZS;
//	private String KTOKD;
//	private String KUKLA;
//	private String LIFNR;
//	private String LIFSD;
//	private String LOCCO;
//	private String LOEVM;
//	private String NAME3;
//	private String NAME4;
//	private String NIELS;
//	private String ORT02;
//	private String PFACH;
//	private String PSTL2;
//	private String COUNC;
//	private String CITYC;
//	private String RPMKR;
//	private String SPERR;
//	private String SPRAS;
//	private String STCD1;
//	private String STCD2;
//	private String STKZA;
//	private String STKZU;
//	private String TELBX;
//	private String TELF2;
//	private String TELTX;
//	private String TELX1;
//	private String LZONE;
//	private String XZEMP;
//	private String VBUND;
//	private String STCEG;
//	private String DEAR1;
//	private String DEAR2;
//	private String DEAR3;
//	private String DEAR4;
//	private String DEAR5;
//	private String GFORM;
//	private String BRAN1;
//	private String BRAN2;
//	private String BRAN3;
//	private String BRAN4;
//	private String BRAN5;
//	private String EKONT;
//	private String UMSAT;
//	private String UMJAH;;
//	private String UWAER;
//	private String JMZAH;
//	private String KATR1;
//	private String KATR2;
//	private String KATR3;
//	private String KATR4;
//	private String KATR5;
//	private String KATR6;
//	private String KATR7;
//	private String KATR8;
//	private String KATR9;
//	private String KATR10;
//	private String STKZN;
//	private String UMSA1;
//	private String TXJCD;
//	private String PERIV;
//	private String ABRVW;
//	private String INSPBYDEBI;
//	private String INSPATDEBI;
//	private String KTOCD;
//	private String PFORT;
//	private String WERKS;
//	private String DTAMS;
//	private String DTAWS;
//	private String DUEFL;
//	private String HZUOR;
//	private String SPERZ;
//	private String ETIKG;
//	private String CIVVE;
//	private String MILVE;
//	private String KDKG1;
//	private String KDKG2;
//	private String KDKG3;
//	private String KDKG4;
//	private String KDKG5;
//	private String XKNZA;
//	private String FITYP;
//	private String STCDT;
//	private String STCD3;
//	private String STCD4;
//	private String XICMS;
//	private String XXIPI;
//	private String XSUBT;
//	private String TXLW1;
//	private String TXLW2;
//	private String CCC01;
//	private String CCC02;
//	private String CCC03;
//	private String CCC04;
//	private String CASSD;
//	private String KNURL;
//	private String J_1KFREPRE;
//	private String J_1KFTBUS;
//	private String J_1KFTIND;
//	private String CONFS;
//	private String UPDAT;
//	private String UPTIM;
//	private String NODEL;
//	private String DEAR6;

}
