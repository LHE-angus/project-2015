package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 17:45:40
 */
public class PshowOrdeAudit extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long order_id;

	private Long opr_user_id;

	private String opr_user_real_name;

	private Date oper_date;

	private BigDecimal total_price;

	private Integer state;

	private String remark;

	public PshowOrdeAudit() {

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
	public Long getOrder_id() {
		return order_id;
	}

	/**
	 * @val 订单ID
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	/**
	 * @val 操作人ID
	 */
	public Long getOpr_user_id() {
		return opr_user_id;
	}

	/**
	 * @val 操作人ID
	 */
	public void setOpr_user_id(Long opr_user_id) {
		this.opr_user_id = opr_user_id;
	}

	/**
	 * @val 操作人姓名
	 */
	public String getOpr_user_real_name() {
		return opr_user_real_name;
	}

	/**
	 * @val 操作人姓名
	 */

	public void setOpr_user_real_name(String oprUserRealName) {
		opr_user_real_name = oprUserRealName;
	}

	/**
	 * @val 操作时间
	 */
	public Date getOper_date() {
		return oper_date;
	}

	/**
	 * @val 操作时间
	 */
	public void setOper_date(Date oper_date) {
		this.oper_date = oper_date;
	}

	/**
	 * @val 金额（元）
	 */
	public BigDecimal getTotal_price() {
		return total_price;
	}

	/**
	 * @val 金额（元）
	 */
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 审核备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 审核备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}