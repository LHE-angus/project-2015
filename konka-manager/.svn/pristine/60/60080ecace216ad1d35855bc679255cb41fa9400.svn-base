package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
public class KonkaoaFilesRecipient extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 关联ID
	 */
	private Long link_id;

	/**
	 * 接收人姓名
	 */
	private String receive_user;

	/**
	 * 接收人ID
	 */
	private Long receive_id;

	    /**
     * 1：发<br />
     * 2：送<br />
     * 3：呈
     * 
     * 目前渠道系统只使用1
     */
	private Integer receive_type;

	        /**
     * 0：人 1：部门
     * 
     * 0代表个人,1部门.而且群组也转为个人0
     */
	private Integer receive_user_type;

	/**
	 * 0：未查看<br />
	 * 1：已查看
	 */
	private Date view_date_time;

	public KonkaoaFilesRecipient() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}

	public Long getLink_id() {
		return link_id;
	}

	public void setReceive_user(String receive_user) {
		this.receive_user = receive_user;
	}

	public String getReceive_user() {
		return receive_user;
	}

	public void setReceive_id(Long receive_id) {
		this.receive_id = receive_id;
	}

	public Long getReceive_id() {
		return receive_id;
	}

	public void setReceive_type(Integer receive_type) {
		this.receive_type = receive_type;
	}

	public Integer getReceive_type() {
		return receive_type;
	}

	public void setReceive_user_type(Integer receive_user_type) {
		this.receive_user_type = receive_user_type;
	}

	public Integer getReceive_user_type() {
		return receive_user_type;
	}

	public Date getView_date_time() {
		return view_date_time;
	}

	public void setView_date_time(Date viewDateTime) {
		view_date_time = viewDateTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}