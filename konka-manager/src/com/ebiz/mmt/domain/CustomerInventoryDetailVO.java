package com.ebiz.mmt.domain;

public class CustomerInventoryDetailVO {

    private String fgs;// 分公司
    private String r3code;// 客户code
    private String r3name;// 客户名

    private String inventoryCode;// 仓库编码
    private String inventoryName;// 仓库名
    private String sdf; // 送达方

    private String model_code;// 机型
    private int zzts;// 周转天数

    private String pc_date;// 盘存
    private String sync_date;// 最后更新时间

    private long initnum;// 初始库存
    private long innum;// 进货量
    private long salenum;// 销量
    private long xynum;// 剩余量


    public String getFgs() {
        return fgs;
    }

    public void setFgs(String fgs) {
        this.fgs = fgs;
    }

    public String getR3code() {
        return r3code;
    }

    public void setR3code(String r3code) {
        this.r3code = r3code;
    }

    public String getR3name() {
        return r3name;
    }

    public void setR3name(String r3name) {
        this.r3name = r3name;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getSdf() {
        return sdf;
    }

    public void setSdf(String sdf) {
        this.sdf = sdf;
    }

    public String getModel_code() {
        return model_code;
    }

    public void setModel_code(String model_code) {
        this.model_code = model_code;
    }

    public int getZzts() {
        return zzts;
    }

    public void setZzts(int zzts) {
        this.zzts = zzts;
    }

    public String getPc_date() {
        return pc_date;
    }

    public void setPc_date(String pc_date) {
        this.pc_date = pc_date;
    }

    public String getSync_date() {
        return sync_date;
    }

    public void setSync_date(String sync_date) {
        this.sync_date = sync_date;
    }

    public long getInitnum() {
        return initnum;
    }

    public void setInitnum(long initnum) {
        this.initnum = initnum;
    }

    public long getInnum() {
        return innum;
    }

    public void setInnum(long innum) {
        this.innum = innum;
    }

    public long getSalenum() {
        return salenum;
    }

    public void setSalenum(long salenum) {
        this.salenum = salenum;
    }

    public long getXynum() {
        return xynum;
    }

    public void setXynum(long xynum) {
        this.xynum = xynum;
    }


}