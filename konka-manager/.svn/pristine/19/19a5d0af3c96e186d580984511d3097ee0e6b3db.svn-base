package com.sf.integration.warehouse;

public class OrderCx {
	private String custid = "7699462068";
	private String checkword = "IaCc2UOBDzmQQFq;QL8}jsJR129eJ_th";
	private String orderid;

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getCheckword() {
		return checkword;
	}

	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String toXml() {
		String xml = "";
		xml += "<Request service='OrderSearchService' lang='zh-CN'><Head>" + custid + "," + checkword
		        + "</Head><Body><OrderSearch orderid='" + orderid + "' /></Body></Request>";

		return xml;
	}
}
