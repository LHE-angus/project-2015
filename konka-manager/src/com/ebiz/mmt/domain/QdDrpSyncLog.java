package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class QdDrpSyncLog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

    private Long id;
	
	private Date happen_time;
	
	private String ok_flag;
	


    private String msg;
	
	private String func;
	
    private String bid;

    public String getBid() {
        return bid;
    }


    public void setBid(String bid) {
        this.bid = bid;
    }


    public QdDrpSyncLog() {

	}

	
	public Date getHappen_time() {
		return happen_time;
	}
	
	public void setHappen_time(Date happen_time) {
		this.happen_time = happen_time;
	}
	
	public String getOk_flag() {
		return ok_flag;
	}
	
	public void setOk_flag(String ok_flag) {
		this.ok_flag = ok_flag;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getFunc() {
		return func;
	}
	
	public void setFunc(String func) {
		this.func = func;
	}


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
	
}