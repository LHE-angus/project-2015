package com.sf.integration.warehouse;

//BSP接口路由查询接口
public class MailnoQuery {
	private String head = "7699462068,IaCc2UOBDzmQQFq;QL8}jsJR129eJ_th";
	private String tracking_type;// 查询类别
	private String tracking_number;
	// 查询号：查询号, 如果 tracking_type=1，则此值为运 单号。如果
	// tracking_type=2， 则此值为订单号 如果有多个单号，以逗号分隔，
	// 如”123,124,125“
	private String method_type;// 查询方法：1-标准查询 2-定制查询

	public String getTracking_type() {
		return tracking_type;
	}

	public void setTracking_type(String trackingType) {
		tracking_type = trackingType;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String trackingNumber) {
		tracking_number = trackingNumber;
	}

	public String getMethod_type() {
		return method_type;
	}

	public void setMethod_type(String methodType) {
		method_type = methodType;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String toXml() {
		String xml = "";
		xml += "<Request service='RouteService' lang='zh-CN'>";
		xml += "<Head>" + head + "</Head>";
		xml += "<Body>";
		xml += "<RouteRequest tracking_type='" + tracking_type + "' method_type='" + method_type
		        + "' tracking_number='" + tracking_number + "'>";
		xml += "</RouteRequest>";
		xml += "</Body>";
		xml += "</Request>";
		return xml;
	}

}
