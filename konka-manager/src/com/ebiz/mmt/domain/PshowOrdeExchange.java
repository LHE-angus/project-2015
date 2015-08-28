package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-06-27 17:31:25
 */
public class PshowOrdeExchange extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long old_order_id;
	
	private Long new_order_id;
	
	private BigDecimal exchange_price;
	
	private Long opr_user_id;
	
	private String opr_user_real_name;
	
	private Date oper_date;
	
	private Integer is_exchange;
	
	private Integer pd_step;
	
	private Integer exchange_info;
	
	private Integer insurance_way;
	
	private BigDecimal insurance_price;
	
	private Integer insurance_state;
	
	private String remark;
	
	private Integer exchange_state;
	
	private Integer pd_store;
	
	private Long order_detail_id;
	
	private PshowOrdeDetails pshowOrdeDetails;
	
	private PshowOrder oldPshowOrder;
	
	private PshowOrder newPshowOrder;
	
	public PshowOrdeDetails getPshowOrdeDetails() {
		return pshowOrdeDetails;
	}

	public void setPshowOrdeDetails(PshowOrdeDetails pshowOrdeDetails) {
		this.pshowOrdeDetails = pshowOrdeDetails;
	}

	public PshowOrder getOldPshowOrder() {
		return oldPshowOrder;
	}

	public void setOldPshowOrder(PshowOrder oldPshowOrder) {
		this.oldPshowOrder = oldPshowOrder;
	}

	public PshowOrder getNewPshowOrder() {
		return newPshowOrder;
	}

	public void setNewPshowOrder(PshowOrder newPshowOrder) {
		this.newPshowOrder = newPshowOrder;
	}

	public Long getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Long order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public PshowOrdeExchange() {

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
	 * @val 订单ID
	 */
	public Long getOld_order_id() {
		return old_order_id;
	}
	
	/**
	 * @val 订单ID
	 */
	public void setOld_order_id(Long old_order_id) {
		this.old_order_id = old_order_id;
	}
	
	/**
	 * @val 新订单ID
	 */
	public Long getNew_order_id() {
		return new_order_id;
	}
	
	/**
	 * @val 新订单ID
	 */
	public void setNew_order_id(Long new_order_id) {
		this.new_order_id = new_order_id;
	}
	
	/**
	 * @val 退款金额（元）
	 */
	public BigDecimal getExchange_price() {
		return exchange_price;
	}
	
	/**
	 * @val 退款金额（元）
	 */
	public void setExchange_price(BigDecimal exchange_price) {
		this.exchange_price = exchange_price;
	}
	
	/**
	 * @val 退换货操作操作人ID
	 */
	public Long getOpr_user_id() {
		return opr_user_id;
	}
	
	/**
	 * @val 退换货操作操作人ID
	 */
	public void setOpr_user_id(Long opr_user_id) {
		this.opr_user_id = opr_user_id;
	}
	
	/**
	 * @val 退换货操作操作人姓名
	 */
	public String getOpr_user_real_name() {
		return opr_user_real_name;
	}
	
	/**
	 * @val 退换货操作操作人姓名
	 */
	public void setOpr_user_real_name(String opr_user_real_name) {
		this.opr_user_real_name = opr_user_real_name;
	}
	
	/**
	 * @val 退换货操作操作时间
	 */
	public Date getOper_date() {
		return oper_date;
	}
	
	/**
	 * @val 退换货操作操作时间
	 */
	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}
	
	/**
	 * @val 是否换货 1:退货,2换货
	 */
	public Integer getIs_exchange() {
		return is_exchange;
	}
	
	/**
	 * @val 是否换货 1:退货,2换货
	 */
	public void setIs_exchange(Integer is_exchange) {
		this.is_exchange = is_exchange;
	}
	
	/**
	 * @val 机器状态 1:客户家里,2:拉回在途,3:分公司
	 */
	public Integer getPd_step() {
		return pd_step;
	}
	
	/**
	 * @val 机器状态 1:客户家里,2:拉回在途,3:分公司
	 */
	public void setPd_step(Integer pd_step) {
		this.pd_step = pd_step;
	}
	
	/**
	 * @val 退机原因1质量机退货,2拒收,3好机退货
	 */
	public Integer getExchange_info() {
		return exchange_info;
	}
	
	/**
	 * @val 退机原因1质量机退货,2拒收,3好机退货
	 */
	public void setExchange_info(Integer exchange_info) {
		this.exchange_info = exchange_info;
	}
	
	/**
	 * @val 报险方式1顺丰理赔,2保险+顺丰理赔,3保险理赔
	 */
	public Integer getInsurance_way() {
		return insurance_way;
	}
	
	/**
	 * @val 报险方式1顺丰理赔,2保险+顺丰理赔,3保险理赔
	 */
	public void setInsurance_way(Integer insurance_way) {
		this.insurance_way = insurance_way;
	}
	
	/**
	 * @val 报险金额
	 */
	public BigDecimal getInsurance_price() {
		return insurance_price;
	}
	
	/**
	 * @val 报险金额
	 */
	public void setInsurance_price(BigDecimal insurance_price) {
		this.insurance_price = insurance_price;
	}
	
	/**
	 * @val 理赔是否到账 0未到账,1已到账
	 */
	public Integer getInsurance_state() {
		return insurance_state;
	}
	
	/**
	 * @val 理赔是否到账 0未到账,1已到账
	 */
	public void setInsurance_state(Integer insurance_state) {
		this.insurance_state = insurance_state;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 财务核销 0:未退换货,1:已退换货
	 */
	public Integer getExchange_state() {
		return exchange_state;
	}
	
	/**
	 * @val 财务核销 0:未退换货,1:已退换货
	 */
	public void setExchange_state(Integer exchange_state) {
		this.exchange_state = exchange_state;
	}
	
	/**
	 * @val 机器是否入库0:未入库,1已入库
	 */
	public Integer getPd_store() {
		return pd_store;
	}
	
	/**
	 * @val 机器是否入库0:未入库,1已入库
	 */
	public void setPd_store(Integer pd_store) {
		this.pd_store = pd_store;
	}
	
}