package com.ebiz.mmt.web.struts.inter.form;

import java.math.BigDecimal;


/**
 * 与电商DRP订单接口参数辅助类<br>
 * 
 * 订单信息从表
 * 
 * @author zhou
 * 
 * @since 2015-05-20
 */
public class PurchaseOrderDetail {

	//外键ID:头表的主键
	private String dsOrderFKid;

	// 订单明细ID(跟头表主键相同但含义不一样. 这是drp系统的问题)
    private String dsOrderId;
    
	// 商品
    private String pdName;
    // 数量
    private int goodCount;
    // 价格
    private BigDecimal goodPrice;
    // 总价
    private BigDecimal goodSumPrice;
    // 折扣
    private BigDecimal goodDiscount;
    // 折扣金额
    private BigDecimal goodDiscountPrice;
    // 机型备注
    private String pdRemark;


    public PurchaseOrderDetail() {

    }


    public PurchaseOrderDetail(String dsOrderId, String pdName, int goodCount, BigDecimal goodPrice) {
        super();
        this.dsOrderId = dsOrderId;
        this.pdName = pdName;
        this.goodCount = goodCount;
        this.goodPrice = goodPrice;
    }

    public String getDsOrderFKid() {
		return dsOrderFKid;
	}


	public void setDsOrderFKid(String dsOrderFKid) {
		this.dsOrderFKid = dsOrderFKid;
	}


	public String getDsOrderId() {
		return dsOrderId;
	}


	public void setDsOrderId(String dsOrderId) {
		this.dsOrderId = dsOrderId;
	}


    public String getPdName() {
        return pdName;
    }


    public void setPdName(String pdName) {
        this.pdName = pdName;
    }


    public BigDecimal getGoodPrice() {
        return goodPrice;
    }


    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }


    public BigDecimal getGoodSumPrice() {
        return goodSumPrice;
    }


    public void setGoodSumPrice(BigDecimal goodSumPrice) {
        this.goodSumPrice = goodSumPrice;
    }


    public int getGoodCount() {
        return goodCount;
    }


    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }


    public BigDecimal getGoodDiscount() {
        return goodDiscount;
    }


    public void setGoodDiscount(BigDecimal goodDiscount) {
        this.goodDiscount = goodDiscount;
    }


    public BigDecimal getGoodDiscountPrice() {
        return goodDiscountPrice;
    }


    public void setGoodDiscountPrice(BigDecimal goodDiscountPrice) {
        this.goodDiscountPrice = goodDiscountPrice;
    }


    public String getPdRemark() {
        return pdRemark;
    }


    public void setPdRemark(String pdRemark) {
        this.pdRemark = pdRemark;
    }


}
