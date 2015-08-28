package com.ebiz.mmt.domain.support;

/**
 * @author XingXiuDong
 * @date 2012-08-30
 */
public class Table {

	private String title;

	private String subTitle;

	private Thead thead;

	private Tbody tbody;

	/**
	 * @val 主标题
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @val 副标题
	 */
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @val 表头
	 */
	public Thead getThead() {
		return thead;
	}

	public void setThead(Thead thead) {
		this.thead = thead;
	}

	/**
	 * @val 表正文
	 */
	public Tbody getTbody() {
		return tbody;
	}

	public void setTbody(Tbody tbody) {
		this.tbody = tbody;
	}
}
