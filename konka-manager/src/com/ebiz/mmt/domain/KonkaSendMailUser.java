package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-02 13:27:08
 */
public class KonkaSendMailUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private String real_name;
	
	private Integer send_type;
	
	private String email;
	
	private String add_user_name;
	
	private Long add_user_id;
	
	private Date add_date;
	
	private Integer state;
	
	private Integer info_type;
	
	private String info_title;
	
	private Integer order_value;
	
	/**
	 * @val 排序号
	 */
	public Integer getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序号
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	public KonkaSendMailUser() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 接收人ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 接收人ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 接收人姓名
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * @val 接收人姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * @val 发送类别 1:主送,2:抄送
	 */
	public Integer getSend_type() {
		return send_type;
	}
	
	/**
	 * @val 发送类别 1:主送,2:抄送
	 */
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	
	/**
	 * @val 接收人邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @val 接收人邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @val 添加人
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	/**
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 状态 1:发送，0 :不发送
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 状态 1:发送，0 :不发送
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * @val 信息类别
	 */
	public Integer getInfo_type() {
		return info_type;
	}
	
	/**
	 * @val 信息类别
	 */
	public void setInfo_type(Integer info_type) {
		this.info_type = info_type;
	}
	
	/**
	 * @val 信息标题
	 */
	public String getInfo_title() {
		return info_title;
	}
	
	/**
	 * @val 信息标题
	 */
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	
}