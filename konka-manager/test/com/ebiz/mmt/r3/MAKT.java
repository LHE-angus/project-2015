package com.ebiz.mmt.r3;

/**
 * 物料描述
 * @author ZHOU
 *
 */
public class MAKT {
	private String MANDT ;
	private String MATNR;// 物料号
	private String SPRAS ;
	private String MAKTX;// 描述 可以作为名称
	private String MAKTG ;
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
	public String getSPRAS() {
		return SPRAS;
	}
	public void setSPRAS(String sPRAS) {
		SPRAS = sPRAS;
	}
	public String getMAKTX() {
		return MAKTX;
	}
	public void setMAKTX(String mAKTX) {
		MAKTX = mAKTX;
	}
	public String getMAKTG() {
		return MAKTG;
	}
	public void setMAKTG(String mAKTG) {
		MAKTG = mAKTG;
	}
	
	
}
