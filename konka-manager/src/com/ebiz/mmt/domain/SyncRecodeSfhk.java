package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-23 14:51:55
 */
public class SyncRecodeSfhk extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String start_date;// 订单开始时间

	private String end_date;// 订单结束时间

	private String real_name;// 同步人姓名

	private Date add_date;// 同步时间

	private String need_date;// 同步所花费时间

	private Long add_user_id;// 同步人id

	private Long sync_count;// 同步记录数

	private Integer sync_state;// 同步状态 0 成功 1失败

	public SyncRecodeSfhk() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getNeed_date() {
		return need_date;
	}

	public void setNeed_date(String need_date) {
		this.need_date = need_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Long getSync_count() {
		return sync_count;
	}

	public void setSync_count(Long sync_count) {
		this.sync_count = sync_count;
	}

	public Integer getSync_state() {
		return sync_state;
	}

	public void setSync_state(Integer sync_state) {
		this.sync_state = sync_state;
	}

}