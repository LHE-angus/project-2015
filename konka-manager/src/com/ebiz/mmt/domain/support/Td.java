package com.ebiz.mmt.domain.support;

/**
 * @author XingXiuDong
 * @date 2012-08-30
 */
public class Td {

	private String tdDataType = "0"; // 0或者为空-正常表格数据，1-标题

	private String unit = ""; // 单位

	private String text = "";

	private String link = "";

	/**
	 * @val 链接地址，只有地址当前页打开，n-地址（新窗口打开），支持JS方法JavaScript:js_method(parent_method_name, param1#param2...);
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @val 链接地址，只有地址当前页打开，n-地址（新窗口打开），支持JS方法JavaScript:js_method(parent_method_name, param1#param2...);
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @val 数值单位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @val 数值单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @val 0或者为空-正常表格数据，1-标题
	 */
	public String getTdDataType() {
		return tdDataType;
	}

	/**
	 * @val 0或者为空-正常表格数据，1-标题
	 */
	public void setTdDataType(String tdDataType) {
		this.tdDataType = tdDataType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
