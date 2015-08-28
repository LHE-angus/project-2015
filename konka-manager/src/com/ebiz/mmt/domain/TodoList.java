package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-12 10:26:25
 */
public class TodoList extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer todo_type;
	
	private Integer todo_event;
	
	private String todo_title;
	
	private String todo_from;
	
	private Long todo_from_user_id;
	
	private String todo_rece;
	
	private Long todo_rece_user_id;
	
	private Date add_date;
	
	private String todo_url;
	
	private Integer todo_state;
	
	public TodoList() {

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
	 * @val 事项分类:1-OA系统协同办公，2-专卖店，3-客户进销存，4-客户管理
	 */
	public Integer getTodo_type() {
		return todo_type;
	}
	
	/**
	 * @val 事项分类:1-OA系统协同办公，2-专卖店，3-客户进销存，4-客户管理
	 */
	public void setTodo_type(Integer todo_type) {
		this.todo_type = todo_type;
	}
	
	/**
	 * @val 注册事件
	 */
	public Integer getTodo_event() {
		return todo_event;
	}
	
	/**
	 * @val 注册事件
	 */
	public void setTodo_event(Integer todo_event) {
		this.todo_event = todo_event;
	}
	
	/**
	 * @val 事项标题
	 */
	public String getTodo_title() {
		return todo_title;
	}
	
	/**
	 * @val 事项标题
	 */
	public void setTodo_title(String todo_title) {
		this.todo_title = todo_title;
	}
	
	/**
	 * @val 来自人姓名
	 */
	public String getTodo_from() {
		return todo_from;
	}
	
	/**
	 * @val 来自人姓名
	 */
	public void setTodo_from(String todo_from) {
		this.todo_from = todo_from;
	}
	
	/**
	 * @val 来自人用户ID
	 */
	public Long getTodo_from_user_id() {
		return todo_from_user_id;
	}
	
	/**
	 * @val 来自人用户ID
	 */
	public void setTodo_from_user_id(Long todo_from_user_id) {
		this.todo_from_user_id = todo_from_user_id;
	}
	
	/**
	 * @val 接收人姓名
	 */
	public String getTodo_rece() {
		return todo_rece;
	}
	
	/**
	 * @val 接收人姓名
	 */
	public void setTodo_rece(String todo_rece) {
		this.todo_rece = todo_rece;
	}
	
	/**
	 * @val 接收人用户ID
	 */
	public Long getTodo_rece_user_id() {
		return todo_rece_user_id;
	}
	
	/**
	 * @val 接收人用户ID
	 */
	public void setTodo_rece_user_id(Long todo_rece_user_id) {
		this.todo_rece_user_id = todo_rece_user_id;
	}
	
	/**
	 * @val 创建时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 事项地址,从根路径开始，不包括ctx
	 */
	public String getTodo_url() {
		return todo_url;
	}
	
	/**
	 * @val 事项地址,从根路径开始，不包括ctx
	 */
	public void setTodo_url(String todo_url) {
		this.todo_url = todo_url;
	}
	
	/**
	 * @val 处理状态:0-未处理，1-已处理
	 */
	public Integer getTodo_state() {
		return todo_state;
	}
	
	/**
	 * @val 处理状态:0-未处理，1-已处理
	 */
	public void setTodo_state(Integer todo_state) {
		this.todo_state = todo_state;
	}
	
}