package com.sf.integration.warehouse;

//BSP接口 订单运费明细查询
public class OrderFy {
	private String custid = "7699462068";
	private String checkword = "IaCc2UOBDzmQQFq;QL8}jsJR129eJ_th";
	private String mailno;

	public OrderFy() {
		super();
	}

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

	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	public String toXml() {
		String xml = "";
		xml += "<mailnoDetailQuery>";
		xml += "<custid>" + custid + "</custid>";
		xml += "<checkword>" + checkword + "</checkword>";
		xml += "<orders>";
		xml += "<mailno>" + mailno + "</mailno>";
		xml += "</orders>";
		xml += "</mailnoDetailQuery>";
		return xml;
	}

}
