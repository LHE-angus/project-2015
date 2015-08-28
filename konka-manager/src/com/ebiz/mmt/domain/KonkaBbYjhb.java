package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class KonkaBbYjhb extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String mandt;
	
	private String zlb;
	
	private String bukrs;
	
	private String lfgja;
	
	private String lfmon;
	
	private Double zjssl;
	
	private Double zjsje;
	
	private Double zhkje;
	
	private Date erdat;
	
	private String uname;
	
	private String butxt;
	
	private Date synctime;
	
	private String syncusr;
	
	public KonkaBbYjhb() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMandt() {
		return mandt;
	}
	
	public void setMandt(String mandt) {
		this.mandt = mandt;
	}
	
	public String getZlb() {
		return zlb;
	}
	
	public void setZlb(String zlb) {
		this.zlb = zlb;
	}
	
	public String getBukrs() {
		return bukrs;
	}
	
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	
	/**
	 * @val 会计年度
	 */
	public String getLfgja() {
		return lfgja;
	}
	
	/**
	 * @val 会计年度
	 */
	public void setLfgja(String lfgja) {
		this.lfgja = lfgja;
	}
	
	/**
	 * @val 会计月度
	 */
	public String getLfmon() {
		return lfmon;
	}
	
	/**
	 * @val 会计月度
	 */
	public void setLfmon(String lfmon) {
		this.lfmon = lfmon;
	}
	
	/**
	 * @val 结算数量
	 */
	public Double getZjssl() {
		return zjssl;
	}
	
	/**
	 * @val 结算数量
	 */
	public void setZjssl(Double zjssl) {
		this.zjssl = zjssl;
	}
	
	/**
	 * @val 结算金额
	 */
	public Double getZjsje() {
		return zjsje;
	}
	
	/**
	 * @val 结算金额
	 */
	public void setZjsje(Double zjsje) {
		this.zjsje = zjsje;
	}
	
	/**
	 * @val 汇款金额
	 */
	public Double getZhkje() {
		return zhkje;
	}
	
	/**
	 * @val 汇款金额
	 */
	public void setZhkje(Double zhkje) {
		this.zhkje = zhkje;
	}
	
	public Date getErdat() {
		return erdat;
	}
	
	public void setErdat(Date erdat) {
		this.erdat = erdat;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getButxt() {
		return butxt;
	}
	
	public void setButxt(String butxt) {
		this.butxt = butxt;
	}
	
	public Date getSynctime() {
		return synctime;
	}
	
	public void setSynctime(Date synctime) {
		this.synctime = synctime;
	}
	
	public String getSyncusr() {
		return syncusr;
	}
	
	public void setSyncusr(String syncusr) {
		this.syncusr = syncusr;
	}
	
}