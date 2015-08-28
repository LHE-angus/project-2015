package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * 
 * @author zhou
 * 
 */
public class KonkaSapInterfaceSetting extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	// 接口描述
	private String serviceDesc;
	// 接口名称
	private String service;
	// 是否启用 0启用,-1禁用
	private String status;
	
	private String update_time;
	
	private String updated_by;
	
	public KonkaSapInterfaceSetting() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	public String getUpdated_by() {
		return updated_by;
	}
	
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
}