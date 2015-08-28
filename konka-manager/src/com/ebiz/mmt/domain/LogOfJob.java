package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class LogOfJob extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private String oper_user_id;
	
	private String oper_real_name;
	
	private String oper_user_ip;
	
	private String link_table;
	
	private String oper_fun;
	
	private String job_type;
	
	private Date start_time;
	
	private Date end_time;
	
	private String spend_time;
	
	private String job_status;
	
	private String comments;
	
	public LogOfJob() {

	}
	
	/**
	 * @val 操作人ID
	 */
	public String getOper_user_id() {
		return oper_user_id;
	}
	
	/**
	 * @val 操作人ID
	 */
	public void setOper_user_id(String oper_user_id) {
		this.oper_user_id = oper_user_id;
	}
	
	/**
	 * @val 操作人姓名
	 */
	public String getOper_real_name() {
		return oper_real_name;
	}
	
	/**
	 * @val 操作人姓名
	 */
	public void setOper_real_name(String oper_real_name) {
		this.oper_real_name = oper_real_name;
	}
	
	/**
	 * @val 操作人所在IP
	 */
	public String getOper_user_ip() {
		return oper_user_ip;
	}
	
	/**
	 * @val 操作人所在IP
	 */
	public void setOper_user_ip(String oper_user_ip) {
		this.oper_user_ip = oper_user_ip;
	}
	
	/**
	 * @val 操作表
	 */
	public String getLink_table() {
		return link_table;
	}
	
	/**
	 * @val 操作表
	 */
	public void setLink_table(String link_table) {
		this.link_table = link_table;
	}
	
	/**
	 * @val 执行方法
	 */
	public String getOper_fun() {
		return oper_fun;
	}
	
	/**
	 * @val 执行方法
	 */
	public void setOper_fun(String oper_fun) {
		this.oper_fun = oper_fun;
	}
	
	/**
	 * @val 操作类型
	 */
	public String getJob_type() {
		return job_type;
	}
	
	/**
	 * @val 操作类型
	 */
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	/**
	 * @val 开始时间
	 */
	public Date getStart_time() {
		return start_time;
	}
	
	/**
	 * @val 开始时间
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * @val 结束时间
	 */
	public Date getEnd_time() {
		return end_time;
	}
	
	/**
	 * @val 结束时间
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * @val 操作耗时
	 */
	public String getSpend_time() {
		return spend_time;
	}
	
	/**
	 * @val 操作耗时
	 */
	public void setSpend_time(String spend_time) {
		this.spend_time = spend_time;
	}
	
	/**
	 * @val 执行状态
	 */
	public String getJob_status() {
		return job_status;
	}
	
	/**
	 * @val 执行状态
	 */
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	
	/**
	 * @val 备注
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * @val 备注
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}