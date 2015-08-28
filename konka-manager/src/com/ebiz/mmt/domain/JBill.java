package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:34
 */
public class JBill extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long bill_id;

	private Integer bill_type;

	private String bill_sn;

	private String bill_memo;

	private Long partner_id;

	private BigDecimal rec_money;

	private BigDecimal discount;

	private BigDecimal money;

	private BigDecimal sum_money;
	
	private BigDecimal dis_money;

	private Date opr_date;

	private Date add_date;

	private Long c_id;

	private String r_bill_sn;
	
	private String bills_title;
	
	private String bills_sumary;
	
	private Date plan_hand_time;
	
	private JBasePartner jBasePartner;
	
	private Integer send_type;   //交货方式：0-自提  1-配送
	
	private String add_user_name;   //添加人姓名
	
	private Long add_user_id;   //添加人ID
	
	private Integer bill_state;   //单据状态
	
	private Long xs_id;   //销售单位id
	
	public Integer getSend_type() {
		return send_type;
	}

	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Integer getBill_state() {
		return bill_state;
	}

	public void setBill_state(Integer bill_state) {
		this.bill_state = bill_state;
	}

	public String getR_bill_sn() {
		return r_bill_sn;
	}

	public List<JBillDetails> getjBillDetailsList() {
		return jBillDetailsList;
	}

	public void setjBillDetailsList(List<JBillDetails> jBillDetailsList) {
		this.jBillDetailsList = jBillDetailsList;
	}

	/**
	 * @val 单据ID
	 */
	public Long getBill_id() {
		return bill_id;
	}

	/**
	 * @val 单据ID
	 */
	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}

	/**
	 * @val 业务类型：10-采购 11-采购退货 20-销售 21-销售退货
	 */
	public Integer getBill_type() {
		return bill_type;
	}

	/**
	 * @val 业务类型：10-采购 11-采购退货 20-销售 21-销售退货
	 */
	public void setBill_type(Integer bill_type) {
		this.bill_type = bill_type;
	}

	/**
	 * @val 单据编号
	 */
	public String getBill_sn() {
		return bill_sn;
	}

	/**
	 * @val 单据编号
	 */
	public void setBill_sn(String bill_sn) {
		this.bill_sn = bill_sn;
	}

	/**
	 * @val 备注
	 */
	public String getBill_memo() {
		return bill_memo;
	}

	/**
	 * @val 备注
	 */
	public void setBill_memo(String bill_memo) {
		this.bill_memo = bill_memo;
	}

	/**
	 * @val 往来单位
	 */
	public Long getPartner_id() {
		return partner_id;
	}

	/**
	 * @val 往来单位
	 */
	public void setPartner_id(Long partner_id) {
		this.partner_id = partner_id;
	}

	/**
	 * @val 应付/应收/应退（元）
	 */
	public BigDecimal getRec_money() {
		return rec_money;
	}

	/**
	 * @val 应付/应收/应退（元）
	 */
	public void setRec_money(BigDecimal rec_money) {
		this.rec_money = rec_money;
	}

	/**
	 * @val 折扣（%）
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * @val 折扣（%）
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * @val 实付/实收/实退（元）
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @val 实付/实收/实退（元）
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @val 交易日期
	 */
	public Date getOpr_date() {
		return opr_date;
	}

	/**
	 * @val 交易日期
	 */
	public void setOpr_date(Date opr_date) {
		this.opr_date = opr_date;
	}

	/**
	 * @val 入库日期
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 入库日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 客户ID
	 */
	public Long getC_id() {
		return c_id;
	}

	/**
	 * @val 客户ID
	 */
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	/**
	 * @val 关联单据编号
	 */
	public void setR_bill_sn(String rBillSn) {
		r_bill_sn = rBillSn;
	}

	/**
	 * @val 关联单据编号
	 */
	private List<JBillDetails> jBillDetailsList;

	public JBill() {

	}

	public String getBills_title() {
		return bills_title;
	}

	public void setBills_title(String bills_title) {
		this.bills_title = bills_title;
	}

	public String getBills_sumary() {
		return bills_sumary;
	}

	public void setBills_sumary(String bills_sumary) {
		this.bills_sumary = bills_sumary;
	}

	public Date getPlan_hand_time() {
		return plan_hand_time;
	}

	public void setPlan_hand_time(Date plan_hand_time) {
		this.plan_hand_time = plan_hand_time;
	}

	public JBasePartner getjBasePartner() {
		return jBasePartner;
	}

	public void setjBasePartner(JBasePartner jBasePartner) {
		this.jBasePartner = jBasePartner;
	}
	
	/**
	 * @val 折扣金额
	 */
	public BigDecimal getDis_money() {
		return dis_money;
	}
	
	/**
	 * @val 折扣金额
	 */
	public void setDis_money(BigDecimal dis_money) {
		this.dis_money = dis_money;
	}
	
	/**
	 * @val 合计金额
	 */
	public BigDecimal getSum_money() {
		return sum_money;
	}
	
	/**
	 * @val 合计金额
	 */
	public void setSum_money(BigDecimal sum_money) {
		this.sum_money = sum_money;
	}

	public Long getXs_id() {
		return xs_id;
	}

	public void setXs_id(Long xs_id) {
		this.xs_id = xs_id;
	}

}