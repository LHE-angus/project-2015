package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xu,ZhengYang
 * @date 2010-12-13 05:13:04
 */

/**
 * KonkaoaDocRecipient 为公告
 * 
 * @author zhou
 * 
 */
public class KonkaoaDocRecipient extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private Long id;
	
	private Long link_id;
	
	private String receive_user;
	
    private Long receive_id;// 人id或者部门id
	
    /**
     * 1：发<br />
     * 2：送<br />
     * 3：呈
     */
    private Integer receive_type;
	
    private Integer receive_user_type;// 0个人(包括群组转个人),1部门
	
	private Integer is_view;
	
	public KonkaoaDocRecipient() {

	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}
	
	public Long getLink_id() {
		return this.link_id;
	}
	
	public void setReceive_user(String receive_user) {
		this.receive_user = receive_user;
	}
	
	public String getReceive_user() {
		return this.receive_user;
	}
	
	public void setReceive_id(Long receive_id) {
		this.receive_id = receive_id;
	}
	
	public Long getReceive_id() {
		return this.receive_id;
	}
	
	public void setReceive_type(Integer receive_type) {
		this.receive_type = receive_type;
	}
	
	public Integer getReceive_type() {
		return this.receive_type;
	}
	
	public void setReceive_user_type(Integer receive_user_type) {
		this.receive_user_type = receive_user_type;
	}
	
	public Integer getReceive_user_type() {
		return this.receive_user_type;
	}
	
	public void setIs_view(Integer is_view) {
		this.is_view = is_view;
	}
	
	public Integer getIs_view() {
		return this.is_view;
	}
	
}