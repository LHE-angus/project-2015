package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
public class BaseVisitType extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long visit_type_id;
	
	private Integer report_type;
	
	private String visit_type_name;
	
	private Long add_id;
	
	private Date add_date;
	
	private Integer state;
	
	public BaseVisitType() {

	}

	public Long getVisit_type_id() {
		return visit_type_id;
	}
	
	public void setVisit_type_id(Long visit_type_id) {
		this.visit_type_id = visit_type_id;
	}
	
	/**
	 * @val -1表示通用
	 * @val 1、正常客户拜访
	 * @val 2、老客户重拾
	 * @val 3、新客户开拓日志；
	 * @val 4、事务上报
	 * @val 
	 * @val 5预留
	 * @val 
	 * @val 
	 */
	public Integer getReport_type() {
		return report_type;
	}
	
	/**
	 * @val -1表示通用
	 * @val 1、正常客户拜访
	 * @val 2、老客户重拾
	 * @val 3、新客户开拓日志；
	 * @val 4、事务上报
	 * @val 
	 * @val 5预留
	 * @val 
	 * @val 
	 */
	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}
	
	/**
	 * @val 拜访类型
	 */
	public String getVisit_type_name() {
		return visit_type_name;
	}
	
	/**
	 * @val 拜访类型
	 */
	public void setVisit_type_name(String visit_type_name) {
		this.visit_type_name = visit_type_name;
	}
	
	/**
	 * @val 添加id
	 */
	public Long getAdd_id() {
		return add_id;
	}
	
	/**
	 * @val 添加id
	 */
	public void setAdd_id(Long add_id) {
		this.add_id = add_id;
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
	
}