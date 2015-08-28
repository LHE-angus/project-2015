package com.ebiz.mmt.web.struts.inter.form;

/**
 * 回传drp系统的对象包装类
 * @author zhouhaojie
 *
 */
public class AuditedPurchaseOrderWrap {
    //
    private Boolean auditFlag;
    private Boolean updateFlag;

    private AuditedPurchaseOrder auditedPurchaseOrder;
    private AuditedPurchaseOrderDetail[] auditedPurchaseOrderDetail;

    //
    public Boolean getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(Boolean auditFlag) {
        this.auditFlag = auditFlag;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public AuditedPurchaseOrder getAuditedPurchaseOrder() {
        return auditedPurchaseOrder;
    }

    public void setAuditedPurchaseOrder(AuditedPurchaseOrder auditedPurchaseOrder) {
        this.auditedPurchaseOrder = auditedPurchaseOrder;
    }

    public AuditedPurchaseOrderDetail[] getAuditedPurchaseOrderDetail() {
        return auditedPurchaseOrderDetail;
    }

    public void setAuditedPurchaseOrderDetail(AuditedPurchaseOrderDetail[] auditedPurchaseOrderDetail) {
        this.auditedPurchaseOrderDetail = auditedPurchaseOrderDetail;
    }



}
