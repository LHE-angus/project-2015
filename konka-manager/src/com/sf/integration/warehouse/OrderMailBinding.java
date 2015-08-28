package com.sf.integration.warehouse;

public class OrderMailBinding {
	private String orderid;
	private String custid;
	private String mailno;
	private String checkword;

	public OrderMailBinding() {
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	public String getCheckword() {
		return checkword;
	}

	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}

	public String toXml() {
		String xml = "";
		xml += "<tporderBinding>";
		xml += "<orderid>" + orderid + "</orderid>";
		xml += "<custid>" + custid + "</custid>";
		xml += "<mailno>" + mailno + "</mailno>";
		xml += "<checkword>" + checkword + "</checkword>";

		xml += "</tporderBinding>";
		return xml;
	}
}
