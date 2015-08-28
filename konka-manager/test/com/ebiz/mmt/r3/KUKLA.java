package com.ebiz.mmt.r3;

/**
 * 客户分类描述
 * @author ZHOU
 *
 */
public class KUKLA {
	
	private String MANDT; //客户端号
	private String SPRAS ;//语言KEY
	private String KUKLA ;//客户分类
	private String VTEXT ;//描述
	
	public String getMANDT() {
		return MANDT;
	}
	public void setMANDT(String mANDT) {
		MANDT = mANDT;
	}
	public String getSPRAS() {
		return SPRAS;
	}
	public void setSPRAS(String sPRAS) {
		SPRAS = sPRAS;
	}
	public String getKUKLA() {
		return KUKLA;
	}
	public void setKUKLA(String kUKLA) {
		KUKLA = kUKLA;
	}
	public String getVTEXT() {
		return VTEXT;
	}
	public void setVTEXT(String vTEXT) {
		VTEXT = vTEXT;
	}
	
}
