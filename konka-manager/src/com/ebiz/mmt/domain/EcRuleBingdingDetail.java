package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-08 15:20:54
 */
public class EcRuleBingdingDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long detail_id;
	
	private Long rule_id;
	
	private Date add_date;
	
	private String rule_money;
	
	private String rule_type;
	
	private String rule_desc;
	
	public EcRuleBingdingDetail() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDetail_id() {
		return detail_id;
	}
	
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	
	public Long getRule_id() {
		return rule_id;
	}
	
	public void setRule_id(Long rule_id) {
		this.rule_id = rule_id;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public String getRule_money() {
		return rule_money;
	}
	
	public void setRule_money(String rule_money) {
		this.rule_money = rule_money;
	}
	
	public String getRule_type() {
		return rule_type;
	}
	
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	
	public String getRule_desc() {
		return rule_desc;
	}
	
	public void setRule_desc(String rule_desc) {
		this.rule_desc = rule_desc;
	}
	
}