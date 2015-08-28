package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Xu,XiaoYuan
 * @version 2012-03-02 09:12
 */
public class KonkaXxPdHistory extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long history_id;
	
	private Long dept_pd_id;
	
	private Long dept_id;
	
	private Long pd_cls;
	
	private String pd_cls_name;
	
	private String md_name;
	
	private Long pd_type;
	
	private BigDecimal price_ref;
	
	private BigDecimal price_max;
	
	private BigDecimal price_min;
	
	private BigDecimal fix_fee;
	
	private Date up_time;
	
	private Date down_time;
	
	private String remarks;
	
	private Date add_date;
	
	private Long add_user_id;

	private String fac_sn;
	
	private String store_sn;
	
	private String fac_desc;
	
	public KonkaXxPdHistory() {

	}

	public Long getHistory_id() {
		return history_id;
	}

	public void setHistory_id(Long history_id) {
		this.history_id = history_id;
	}
	
	public Long getDept_pd_id() {
		return dept_pd_id;
	}

	public void setDept_pd_id(Long dept_pd_id) {
		this.dept_pd_id = dept_pd_id;
	}
	
	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public Long getPd_cls() {
		return pd_cls;
	}

	public void setPd_cls(Long pd_cls) {
		this.pd_cls = pd_cls;
	}
	
	public String getPd_cls_name() {
		return pd_cls_name;
	}

	public void setPd_cls_name(String pd_cls_name) {
		this.pd_cls_name = pd_cls_name;
	}
	
	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}
	
	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	public void setPrice_ref(BigDecimal price_ref) {
		this.price_ref = price_ref;
	}
	
	public BigDecimal getPrice_max() {
		return price_max;
	}

	public void setPrice_max(BigDecimal price_max) {
		this.price_max = price_max;
	}
	
	public BigDecimal getPrice_min() {
		return price_min;
	}

	public void setPrice_min(BigDecimal price_min) {
		this.price_min = price_min;
	}
	
	public BigDecimal getFix_fee() {
		return fix_fee;
	}

	public void setFix_fee(BigDecimal fix_fee) {
		this.fix_fee = fix_fee;
	}
	
	public Date getUp_time() {
		return up_time;
	}

	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}
	
	public Date getDown_time() {
		return down_time;
	}

	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	/**
	 * @val 工厂
	 */
	public void setFac_sn(String fac_sn) {
		this.fac_sn = fac_sn;
	}

	/**
	 * @val 工厂
	 */
	public String getFac_sn() {
		return fac_sn;
	}

	/**
	 * @val 仓位
	 */
	public void setStore_sn(String store_sn) {
		this.store_sn = store_sn;
	}

	/**
	 * @val 仓位
	 */
	public String getStore_sn() {
		return store_sn;
	}
	
	/**
	 * @val 仓位描述
	 */
	public void setFac_desc(String fac_desc) {
		this.fac_desc = fac_desc;
	}

	/**
	 * @val 仓位描述
	 */
	public String getFac_desc() {
		return fac_desc;
	}
}