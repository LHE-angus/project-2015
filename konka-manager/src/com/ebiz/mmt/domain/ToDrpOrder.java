package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class ToDrpOrder extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;


    private Long id;

    private String ds_order_id;

    private String r3_code;

    private String doc_type;

    private Long order_num;

    private BigDecimal order_money;

    private BigDecimal discount_price;

    private String user_name;

    private String user_tel;

    private String user_phone;

    private String user_addr;

    private String user_zip;

    private String remark;

    // 是否是作废的单子(有可能在渠道系统会把一张单作废掉)
    private Long is_del;

    // 从fromdrporder#order_date获取
    private Date order_date;

    // drp系统来渠道系统的时间(从fromdrporder#from_date获取)
    // from_date - sync_date = 订单在渠道系统停留的时间
    private Date from_date;

    // 渠道系统的订单流水号
    private String qd_tran_index;

    // 同步给DRP的执行时间(如果被同步多次,那么这个值会被更新为最后一次执行的时间)
    private Date sync_date;

    // 同步次数,不改成功或失败都会被记录
    private Long sync_count;

    // 是否成功 0 ok , -1 not ok, null 为还没有尝试执行
    private Long sync_ok;

    public ToDrpOrder() {

    }


    public String getDs_order_id() {
        return ds_order_id;
    }

    public void setDs_order_id(String ds_order_id) {
        this.ds_order_id = ds_order_id;
    }

    public String getR3_code() {
        return r3_code;
    }

    public void setR3_code(String r3_code) {
        this.r3_code = r3_code;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public Long getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Long order_num) {
        this.order_num = order_num;
    }

    public BigDecimal getOrder_money() {
        return order_money;
    }

    public void setOrder_money(BigDecimal order_money) {
        this.order_money = order_money;
    }

    public BigDecimal getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(BigDecimal discount_price) {
        this.discount_price = discount_price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public String getUser_zip() {
        return user_zip;
    }

    public void setUser_zip(String user_zip) {
        this.user_zip = user_zip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public String getQd_tran_index() {
        return qd_tran_index;
    }

    public void setQd_tran_index(String qd_tran_index) {
        this.qd_tran_index = qd_tran_index;
    }


    public Date getSync_date() {
        return sync_date;
    }

    public void setSync_date(Date sync_date) {
        this.sync_date = sync_date;
    }

    public Long getSync_count() {
        return sync_count;
    }

    public void setSync_count(Long sync_count) {
        this.sync_count = sync_count;
    }

    public Long getSync_ok() {
        return sync_ok;
    }

    public void setSync_ok(Long sync_ok) {
        this.sync_ok = sync_ok;
    }

    public Long getIs_del() {
        return is_del;
    }

    public void setIs_del(Long is_del) {
        this.is_del = is_del;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

}
