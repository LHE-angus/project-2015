package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 */
public class ShopMsg extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long msg_id;

	private Long shop_id;

	private String msg_content;

	private Long ask_id;

	private String ask_name;

	private Date ask_date;

	private String reply_content;

	private Long reply_id;

	private Date del_man2;

	private Integer info_state;

	private Integer assess_state;

	private Date assess_date;

	private String reply_link;

	private Integer mark;

	public String getAsk_name() {
		return ask_name;
	}

	public void setAsk_name(String askName) {
		ask_name = askName;
	}

	public ShopMsg() {

	}

	public Long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Long msg_id) {
		this.msg_id = msg_id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public Long getAsk_id() {
		return ask_id;
	}

	public void setAsk_id(Long ask_id) {
		this.ask_id = ask_id;
	}

	public Date getAsk_date() {
		return ask_date;
	}

	public void setAsk_date(Date ask_date) {
		this.ask_date = ask_date;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Long getReply_id() {
		return reply_id;
	}

	public void setReply_id(Long reply_id) {
		this.reply_id = reply_id;
	}

	public Date getDel_man2() {
		return del_man2;
	}

	public void setDel_man2(Date del_man2) {
		this.del_man2 = del_man2;
	}

	public Integer getInfo_state() {
		return info_state;
	}

	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}

	public Integer getAssess_state() {
		return assess_state;
	}

	public void setAssess_state(Integer assess_state) {
		this.assess_state = assess_state;
	}

	public Date getAssess_date() {
		return assess_date;
	}

	public void setAssess_date(Date assess_date) {
		this.assess_date = assess_date;
	}

	public String getReply_link() {
		return reply_link;
	}

	public void setReply_link(String replyLink) {
		reply_link = replyLink;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

}