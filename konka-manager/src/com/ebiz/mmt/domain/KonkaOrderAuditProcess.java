package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-11-30 14:09
 */
public class KonkaOrderAuditProcess extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Integer process_type;

	private Date add_date;

	private Long add_user_id;

	private Long add_dept_id;

	private Long del_user_id;

	private Date del_date;

	private Integer is_del;

	private String add_user_name;

	private String add_dept_name;

	private String process_desc;

	private String customer_type;

	private Integer is_stop;
	
	private Integer used_field;//适用范围 ：     0   正常订单； 1 变更的订单

	
	private List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList;

	public List<KonkaOrderAuditProcessNode> getKonkaOrderAuditProcessNodeList() {
		return konkaOrderAuditProcessNodeList;
	}

	public void setKonkaOrderAuditProcessNodeList(List<KonkaOrderAuditProcessNode> konkaOrderAuditProcessNodeList) {
		this.konkaOrderAuditProcessNodeList = konkaOrderAuditProcessNodeList;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String addUserName) {
		add_user_name = addUserName;
	}

	public String getAdd_dept_name() {
		return add_dept_name;
	}

	public void setAdd_dept_name(String addDeptName) {
		add_dept_name = addDeptName;
	}

	public KonkaOrderAuditProcess() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProcess_type() {
		return process_type;
	}

	public void setProcess_type(Integer process_type) {
		this.process_type = process_type;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Long getAdd_dept_id() {
		return add_dept_id;
	}

	public void setAdd_dept_id(Long add_dept_id) {
		this.add_dept_id = add_dept_id;
	}

	public Long getDel_user_id() {
		return del_user_id;
	}

	public void setDel_user_id(Long del_user_id) {
		this.del_user_id = del_user_id;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public String getProcess_desc() {
		return process_desc;
	}

	public void setProcess_desc(String process_desc) {
		this.process_desc = process_desc;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public Integer getIs_stop() {
		return is_stop;
	}

	public void setIs_stop(Integer is_stop) {
		this.is_stop = is_stop;
	}
	public Integer getUsed_field() {
		return used_field;
	}

	public void setUsed_field(Integer usedField) {
		used_field = usedField;
	}

}