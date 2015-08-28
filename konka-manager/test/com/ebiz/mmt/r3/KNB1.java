package com.ebiz.mmt.r3;

import java.io.Serializable;

/**
 * R3客户(分公司代码层面)
 * 
 * @author ZHOU
 * 
 */
public class KNB1 implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private String MANDT;//集团

	private String KUNNR; //客户编码

	private String BUKRS; //分公司代码

	private String PERNR ; //人员编号

	private String ERDAT ;//创建时间

	private String ERNAM ;//创建对象的人员名称
	
	
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


	public String getBUKRS() {
		return BUKRS;
	}


	public void setBUKRS(String bUKRS) {
		BUKRS = bUKRS;
	}


	public String getPERNR() {
		return PERNR;
	}


	public void setPERNR(String pERNR) {
		PERNR = pERNR;
	}


	public String getERDAT() {
		return ERDAT;
	}


	public void setERDAT(String eRDAT) {
		ERDAT = eRDAT;
	}


	public String getERNAM() {
		return ERNAM;
	}


	public void setERNAM(String eRNAM) {
		ERNAM = eRNAM;
	}


	public KNB1(){
		
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
