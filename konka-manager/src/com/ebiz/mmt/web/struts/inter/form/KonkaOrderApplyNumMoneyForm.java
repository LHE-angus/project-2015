package com.ebiz.mmt.web.struts.inter.form;

import java.math.BigDecimal;
/**
 * 申请信息基类
 * @author Wang,KunLin
 *
 */
public class KonkaOrderApplyNumMoneyForm extends BaseInterForm {
	private static final long serialVersionUID = -1L;
	//申请数量
	private Long orderapplynum;
    //申请金额
	private BigDecimal orderapplymoney;
	
	public Long getOrderapplynum() {
		return orderapplynum;
	}
	public void setOrderapplynum(Long orderapplynum) {
		this.orderapplynum = orderapplynum;
	}
	public BigDecimal getOrderapplymoney() {
		return orderapplymoney;
	}
	public void setOrderapplymoney(BigDecimal orderapplymoney) {
		this.orderapplymoney = orderapplymoney;
	}
}
