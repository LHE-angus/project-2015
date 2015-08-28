package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-04 14:07:23
 */
public class KonkaMobileCustVisitLink extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long posistion_id;
	
	private Long link_id;
	
	private String link_tab;
	
	private String link_man;
	
	private String link_phone;
	
	private String link_mobile;
	
	private Integer link_man_type;
	
	private String link_man_qq;
	
	private String link_man_mail;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Integer state;
	
	public KonkaMobileCustVisitLink() {

	}

	public Long getPosistion_id() {
		return posistion_id;
	}
	
	public void setPosistion_id(Long posistion_id) {
		this.posistion_id = posistion_id;
	}
	
	public Long getLink_id() {
		return link_id;
	}
	
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}
	
	public String getLink_tab() {
		return link_tab;
	}
	
	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}
	
	public String getLink_man() {
		return link_man;
	}
	
	public void setLink_man(String link_man) {
		this.link_man = link_man;
	}
	
	public String getLink_phone() {
		return link_phone;
	}
	
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	
	public String getLink_mobile() {
		return link_mobile;
	}
	
	public void setLink_mobile(String link_mobile) {
		this.link_mobile = link_mobile;
	}
	
	public Integer getLink_man_type() {
		return link_man_type;
	}
	
	public void setLink_man_type(Integer link_man_type) {
		this.link_man_type = link_man_type;
	}
	
	public String getLink_man_qq() {
		return link_man_qq;
	}
	
	public void setLink_man_qq(String link_man_qq) {
		this.link_man_qq = link_man_qq;
	}
	
	public String getLink_man_mail() {
		return link_man_mail;
	}
	
	public void setLink_man_mail(String link_man_mail) {
		this.link_man_mail = link_man_mail;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
}