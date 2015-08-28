package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
public class KonkaMobilePaymentChis extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dept_id;
	
	private Long user_id;
	
	private Long base_payment_id;
	
	private String md_percent_id;
	
	private Date genedate;
	
	public KonkaMobilePaymentChis() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getBase_payment_id() {
		return base_payment_id;
	}
	
	public void setBase_payment_id(Long base_payment_id) {
		this.base_payment_id = base_payment_id;
	}
	
	public String getMd_percent_id() {
		return md_percent_id;
	}
	
	public void setMd_percent_id(String md_percent_id) {
		this.md_percent_id = md_percent_id;
	}
	
	public Date getGenedate() {
		return genedate;
	}
	
	public void setGenedate(Date genedate) {
		this.genedate = genedate;
	}
	
}