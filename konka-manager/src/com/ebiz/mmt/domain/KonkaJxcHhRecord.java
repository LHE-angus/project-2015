package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-10-15 16:57
 */
public class KonkaJxcHhRecord extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long pd_type_id;

	private String pd_type_name;

	private Long brand_id;

	private String brand_name;

	private Long pd_id;

	private String pd_name;

	private Long hh_count;

	private String hh_reason;

	private Long hh_store_id_son;

	private Long hh_store_id_par;

	private Integer hh_type;

	private Integer hh_is_confirm;

	private String remark;

	private Integer is_del;

	private Date hh_confirm_date;

	private Long add_user_type;

	private Date add_date;

	private Long add_user_id;

	private Long add_dept_id;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private Long audit_user_id;

	private Long audit_dept_id;

	private Long audit_user_type;

	private Integer is_audit;

	private String audit_reason;

	private Date approval_date;

	private BigDecimal price;

	private String add_dept_name;

	private String add_user_name;
	
	private Date in_date;

	public String getAdd_dept_name() {
		return add_dept_name;
	}

	public void setAdd_dept_name(String addDeptName) {
		add_dept_name = addDeptName;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String addUserName) {
		add_user_name = addUserName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public KonkaJxcHhRecord() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Long getPd_type_id() {
		return pd_type_id;
	}

	public void setPd_type_id(Long pd_type_id) {
		this.pd_type_id = pd_type_id;
	}

	public String getPd_type_name() {
		return pd_type_name;
	}

	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Long getHh_count() {
		return hh_count;
	}

	public void setHh_count(Long hh_count) {
		this.hh_count = hh_count;
	}

	public String getHh_reason() {
		return hh_reason;
	}

	public void setHh_reason(String hh_reason) {
		this.hh_reason = hh_reason;
	}

	public Long getHh_store_id_son() {
		return hh_store_id_son;
	}

	public void setHh_store_id_son(Long hh_store_id_son) {
		this.hh_store_id_son = hh_store_id_son;
	}

	public Long getHh_store_id_par() {
		return hh_store_id_par;
	}

	public void setHh_store_id_par(Long hh_store_id_par) {
		this.hh_store_id_par = hh_store_id_par;
	}

	public Integer getHh_type() {
		return hh_type;
	}

	public void setHh_type(Integer hh_type) {
		this.hh_type = hh_type;
	}

	public Integer getHh_is_confirm() {
		return hh_is_confirm;
	}

	public void setHh_is_confirm(Integer hh_is_confirm) {
		this.hh_is_confirm = hh_is_confirm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getHh_confirm_date() {
		return hh_confirm_date;
	}

	public void setHh_confirm_date(Date hh_confirm_date) {
		this.hh_confirm_date = hh_confirm_date;
	}

	public Long getAdd_user_type() {
		return add_user_type;
	}

	public void setAdd_user_type(Long add_user_type) {
		this.add_user_type = add_user_type;
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

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Long getDel_user_id() {
		return del_user_id;
	}

	public void setDel_user_id(Long del_user_id) {
		this.del_user_id = del_user_id;
	}

	public Long getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(Long audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public Long getAudit_dept_id() {
		return audit_dept_id;
	}

	public void setAudit_dept_id(Long audit_dept_id) {
		this.audit_dept_id = audit_dept_id;
	}

	public Long getAudit_user_type() {
		return audit_user_type;
	}

	public void setAudit_user_type(Long audit_user_type) {
		this.audit_user_type = audit_user_type;
	}

	public Integer getIs_audit() {
		return is_audit;
	}

	public void setIs_audit(Integer is_audit) {
		this.is_audit = is_audit;
	}

	public String getAudit_reason() {
		return audit_reason;
	}

	public void setAudit_reason(String audit_reason) {
		this.audit_reason = audit_reason;
	}

	public Date getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date inDate) {
		in_date = inDate;
	}

}