package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-15 18:24:08
 */
public class KonkaOrderMessageProcess extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String title;
	
	private Integer message_type;
	
	private Long link_id;
	
	private String link_table;
	
	private Long c_user_id;
	
	private Long bc_user_id;
	
	private Date add_date;
	
	private String remark;
	
	private Integer state;
	
	private Integer is_send;
	
	public KonkaOrderMessageProcess() {

	}

	/**
	 * @val 主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 类型（0订单，1.....）
	 */
	public Integer getMessage_type() {
		return message_type;
	}
	
	/**
	 * @val 类型（0订单，1.....）
	 */
	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}
	
	/**
	 * @val 连接id
	 */
	public Long getLink_id() {
		return link_id;
	}
	
	/**
	 * @val 连接id
	 */
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}
	
	/**
	 * @val 连接的表名
	 */
	public String getLink_table() {
		return link_table;
	}
	
	/**
	 * @val 连接的表名
	 */
	public void setLink_table(String link_table) {
		this.link_table = link_table;
	}
	
	/**
	 * @val 催办的用户id
	 */
	public Long getC_user_id() {
		return c_user_id;
	}
	
	/**
	 * @val 催办的用户id
	 */
	public void setC_user_id(Long c_user_id) {
		this.c_user_id = c_user_id;
	}
	
	/**
	 * @val 悲催办的用户id
	 */
	public Long getBc_user_id() {
		return bc_user_id;
	}
	
	/**
	 * @val 悲催办的用户id
	 */
	public void setBc_user_id(Long bc_user_id) {
		this.bc_user_id = bc_user_id;
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
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 状态
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * @val 是否推送
	 */
	public Integer getIs_send() {
		return is_send;
	}
	
	/**
	 * @val 是否推送
	 */
	public void setIs_send(Integer is_send) {
		this.is_send = is_send;
	}
	
}