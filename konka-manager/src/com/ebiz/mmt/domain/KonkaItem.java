package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Ren,Peng
 * @version 2011-10-20 16:41
 */
public class KonkaItem extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String item_content;
	
	private Long receive_user_id;
	
	private String receive_user_name;
	
	private Date plan_finish_date;
	
	private Integer is_finished;
	
	private String finish_status;
	
	private BigDecimal plan_finish_rate;
	
	private Long p_type_1;
	
	private Long p_type_2;
	
	private Date add_time;
	
	private Date last_update_time;
	
	private Long add_user_id;
	
	private String add_user_name;
	
	private Integer order_value;
	
	private Integer is_del;
	
	public KonkaItem() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getItem_content() {
		return item_content;
	}

	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	
	public Long getReceive_user_id() {
		return receive_user_id;
	}

	public void setReceive_user_id(Long receive_user_id) {
		this.receive_user_id = receive_user_id;
	}
	
	public String getReceive_user_name() {
		return receive_user_name;
	}

	public void setReceive_user_name(String receive_user_name) {
		this.receive_user_name = receive_user_name;
	}
	
	public Date getPlan_finish_date() {
		return plan_finish_date;
	}

	public void setPlan_finish_date(Date plan_finish_date) {
		this.plan_finish_date = plan_finish_date;
	}
	
	public Integer getIs_finished() {
		return is_finished;
	}

	public void setIs_finished(Integer is_finished) {
		this.is_finished = is_finished;
	}
	
	public String getFinish_status() {
		return finish_status;
	}

	public void setFinish_status(String finish_status) {
		this.finish_status = finish_status;
	}
	
	public BigDecimal getPlan_finish_rate() {
		return plan_finish_rate;
	}

	public void setPlan_finish_rate(BigDecimal plan_finish_rate) {
		this.plan_finish_rate = plan_finish_rate;
	}
	
	public Long getP_type_1() {
		return p_type_1;
	}

	public void setP_type_1(Long p_type_1) {
		this.p_type_1 = p_type_1;
	}
	
	public Long getP_type_2() {
		return p_type_2;
	}

	public void setP_type_2(Long p_type_2) {
		this.p_type_2 = p_type_2;
	}
	
	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	public Integer getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Date lastUpdateTime) {
		last_update_time = lastUpdateTime;
	}
	
}