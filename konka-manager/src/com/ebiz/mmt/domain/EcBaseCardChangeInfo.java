package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public class EcBaseCardChangeInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String card_no;
	
	private Date opr_date;
	
	private String opr_desc;
	
	private String opr_man;
	
	public EcBaseCardChangeInfo() {

	}

	/**
	 * @val CARD_NO
	 */
	public String getCard_no() {
		return card_no;
	}
	
	/**
	 * @val CARD_NO
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	/**
	 * @val 操作时间
	 */
	public Date getOpr_date() {
		return opr_date;
	}
	
	/**
	 * @val 操作时间
	 */
	public void setOpr_date(Date opr_date) {
		this.opr_date = opr_date;
	}
	
	/**
	 * @val 操作说明
	 */
	public String getOpr_desc() {
		return opr_desc;
	}
	
	/**
	 * @val 操作说明
	 */
	public void setOpr_desc(String opr_desc) {
		this.opr_desc = opr_desc;
	}
	
	/**
	 * @val 操作人
	 */
	public String getOpr_man() {
		return opr_man;
	}
	
	/**
	 * @val 操作人
	 */
	public void setOpr_man(String opr_man) {
		this.opr_man = opr_man;
	}
	
}