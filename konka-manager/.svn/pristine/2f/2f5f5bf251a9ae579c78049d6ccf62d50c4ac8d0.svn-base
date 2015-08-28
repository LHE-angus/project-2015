package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 */
public class BuyerAttachment extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long user_id;

	/**
	 * 0：户口本 1：身份证֤
	 */
	private Integer att_type;

	private String card_no;

	/**
	 * 0：正面或户主页 1：反面或购买人页
	 */
	private Integer page_type;

	private String att_name;

	private String att_desc;

	private String att_path;

	/**
	 * -1：审核未通过 0：未审核 1：审核通过
	 */
	private Integer audit_state;

	private Date audit_date;

	private Date add_date;

	/**
	 * 0：未删除 1：已删除
	 */
	private Integer is_del;

	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	private List<BuyerAttachment> buyerAttachmentList;

	public List<BuyerAttachment> getBuyerAttachmentList() {
		return buyerAttachmentList;
	}

	public void setBuyerAttachmentList(List<BuyerAttachment> buyerAttachmentList) {
		this.buyerAttachmentList = buyerAttachmentList;
	}

	public BuyerAttachment() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getAtt_type() {
		return att_type;
	}

	public void setAtt_type(Integer att_type) {
		this.att_type = att_type;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public Integer getPage_type() {
		return page_type;
	}

	public void setPage_type(Integer pageType) {
		page_type = pageType;
	}

	public String getAtt_name() {
		return att_name;
	}

	public void setAtt_name(String att_name) {
		this.att_name = att_name;
	}

	public String getAtt_desc() {
		return att_desc;
	}

	public void setAtt_desc(String att_desc) {
		this.att_desc = att_desc;
	}

	public String getAtt_path() {
		return att_path;
	}

	public void setAtt_path(String att_path) {
		this.att_path = att_path;
	}

	public Integer getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}

	public Date getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

}