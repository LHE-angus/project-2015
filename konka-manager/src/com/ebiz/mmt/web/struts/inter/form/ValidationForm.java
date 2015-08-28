package com.ebiz.mmt.web.struts.inter.form;

import java.io.Serializable;

/**
 * @author Tudp
 * @since 2014-09-19
 * @see 请求验证
 */
public class ValidationForm extends BaseInterForm implements Serializable {

	private static final long serialVersionUID = -1L;
 
	private String req_ip;
	private String req_url;
	
	public String getReq_ip() {
		return req_ip;
	}
	public void setReq_ip(String req_ip) {
		this.req_ip = req_ip;
	}
	public String getReq_url() {
		return req_url;
	}
	public void setReq_url(String req_url) {
		this.req_url = req_url;
	} 
	  
}