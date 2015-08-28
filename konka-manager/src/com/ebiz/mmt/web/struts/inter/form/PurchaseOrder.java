package com.ebiz.mmt.web.struts.inter.form;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 与电商DRP订单接口参数辅助类<br>
 * 
 * 订单信息头表
 * 
 * @author zhou
 * 
 * @since 2015-05-20
 */
public class PurchaseOrder {
    // 订单ID
    private String dsOrderId;
    // 客户编码
    private String r3Code;
    // 凭证类型SAP用
    private String docType;
    // 订单总数
    private long orderNum;
    // 订单总金额
    private BigDecimal money;
    // 折扣
    private BigDecimal goodDiscountPrice;
    // 用户名称
    private String userName;
    // 用户联系电话
    private String userTel;
    // 用户手机号码
    private String userPhone;
    // 用户地址
    private String userAddr;
    // 邮政编码
    private String userZip;
    // 备注
    private String userRemark;
    // 下单日期
    private Date orderDate;

    public PurchaseOrder() {}

    public void addOrderInfo(String dsOrderId, String r3Code, String docType, long orderNum, BigDecimal money, BigDecimal goodDiscountPrice, Date orderDate) {
        this.dsOrderId = dsOrderId;
        this.r3Code = r3Code;
        this.docType = docType;
        this.orderNum = orderNum;
        this.money = money;
        this.goodDiscountPrice = goodDiscountPrice;
        this.orderDate = orderDate;

    }

    public void addUserInfo(String userName, String userTel, String userPhone, String userAddr, String userZip, String userRemark) {
        this.userName = userName;
        this.userTel = userTel;
        this.userPhone = userPhone;
        this.userTel = userTel;
        this.userZip = userZip;
        this.userRemark = userRemark;

        
    }
    
    
    public String getDsOrderId() {
        return dsOrderId;
    }

    public void setDsOrderId(String dsOrderId) {
        this.dsOrderId = dsOrderId;
    }

    public String getR3Code() {
        return r3Code;
    }

    public void setR3Code(String r3Code) {
        this.r3Code = r3Code;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getGoodDiscountPrice() {
        return goodDiscountPrice;
    }

    public void setGoodDiscountPrice(BigDecimal goodDiscountPrice) {
        this.goodDiscountPrice = goodDiscountPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    //

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
