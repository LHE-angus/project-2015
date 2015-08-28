package com.ebiz.mmt.domain.functioncharts;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 */
public class BaseChart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1064800117493765956L;

	private String value;

	private String label;

	private String category_label;

	private String color;

	private Integer is_sliced = new Integer(0);

	private String link;

	public BaseChart() {

	}

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
	 * @val 图形颜色
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @val 图形颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @val 数值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @val 数值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getIs_sliced() {
		return is_sliced;
	}

	public void setIs_sliced(Integer is_sliced) {
		this.is_sliced = is_sliced;
	}

	public String getCategory_label() {
		return category_label;
	}

	public void setCategory_label(String category_label) {
		this.category_label = category_label;
	}

}