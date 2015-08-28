package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-10-15 16:57
 */
public class KonkaJxcStockBill extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long jh_sum_count;

	private BigDecimal money_must;

	private BigDecimal money_result;

	private Long part_dept_id;

	private String remark;

	private Integer is_del;

	private Long add_user_type;

	private Date add_date;

	private Long add_user_id;

	private Long add_dept_id;

	private String add_dept_name;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private String sn;

	private String add_user_name;

	private List<KonkaJxcStockBillDetails> KonkaJxcStockBillDetailsList;
	
	private KonkaJxcStockBillDetails konkaJxcStockBillDetails;// 进货明细 供退货使用
	
	private KonkaJxcFhBill konkaJxcFhBill;//发货单（收货确认）
	
	private Date jh_date;
	
	private Integer stock_src;
	
	private String fh_sn;
	
	public KonkaJxcStockBill() {
		
	}

	public KonkaJxcStockBillDetails getKonkaJxcStockBillDetails() {
		return konkaJxcStockBillDetails;
	}

	public void setKonkaJxcStockBillDetails(KonkaJxcStockBillDetails konkaJxcStockBillDetails) {
		this.konkaJxcStockBillDetails = konkaJxcStockBillDetails;
	}

	public String getAdd_dept_name() {
		return add_dept_name;
	}

	public void setAdd_dept_name(String addDeptName) {
		add_dept_name = addDeptName;
	}

	public List<KonkaJxcStockBillDetails> getKonkaJxcStockBillDetailsList() {
		return KonkaJxcStockBillDetailsList;
	}

	public void setKonkaJxcStockBillDetailsList(List<KonkaJxcStockBillDetails> konkaJxcStockBillDetailsList) {
		KonkaJxcStockBillDetailsList = konkaJxcStockBillDetailsList;
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

	public Long getJh_sum_count() {
		return jh_sum_count;
	}

	public void setJh_sum_count(Long jh_sum_count) {
		this.jh_sum_count = jh_sum_count;
	}

	public Long getPart_dept_id() {
		return part_dept_id;
	}

	public void setPart_dept_id(Long part_dept_id) {
		this.part_dept_id = part_dept_id;
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String addUserName) {
		add_user_name = addUserName;
	}

	public BigDecimal getMoney_must() {
		return money_must;
	}

	public void setMoney_must(BigDecimal moneyMust) {
		money_must = moneyMust;
	}

	public BigDecimal getMoney_result() {
		return money_result;
	}

	public void setMoney_result(BigDecimal moneyResult) {
		money_result = moneyResult;
	}

	public KonkaJxcFhBill getKonkaJxcFhBill() {
		return konkaJxcFhBill;
	}

	public void setKonkaJxcFhBill(KonkaJxcFhBill konkaJxcFhBill) {
		this.konkaJxcFhBill = konkaJxcFhBill;
	}

	public Date getJh_date() {
		return jh_date;
	}

	public void setJh_date(Date jhDate) {
		jh_date = jhDate;
	}

	public Integer getStock_src() {
		return stock_src;
	}

	public void setStock_src(Integer stockSrc) {
		stock_src = stockSrc;
	}

	public String getFh_sn() {
		return fh_sn;
	}

	public void setFh_sn(String fhSn) {
		fh_sn = fhSn;
	}

}