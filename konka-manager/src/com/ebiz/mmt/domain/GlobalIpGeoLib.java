package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-07-05 14:22:07
 */
public class GlobalIpGeoLib extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String ip1;
	
	private String ip2;
	
	private String addr;
	
	private String addr_desc;
	
	private Long ipv1;
	
	private Long ipv2;
	
	public GlobalIpGeoLib() {

	}

	public String getIp1() {
		return ip1;
	}
	
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	
	public String getIp2() {
		return ip2;
	}
	
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getAddr_desc() {
		return addr_desc;
	}
	
	public void setAddr_desc(String addr_desc) {
		this.addr_desc = addr_desc;
	}
	
	public Long getIpv1() {
		return ipv1;
	}
	
	public void setIpv1(Long ipv1) {
		this.ipv1 = ipv1;
	}
	
	public Long getIpv2() {
		return ipv2;
	}
	
	public void setIpv2(Long ipv2) {
		this.ipv2 = ipv2;
	}
	
}