package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:54
 */
public class KonkaXxSellBillCstSatform extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long sell_bill_id;
	
	private String md_name;
	
	private String title;
	
	private Integer mark_star;
	
	private String pros;
	
	private String cons;
	
	private String content;
	
	public KonkaXxSellBillCstSatform() {

	}

	/**
	 * @val 调查表ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 调查表ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 销单ID
	 */
	public Long getSell_bill_id() {
		return sell_bill_id;
	}
	
	/**
	 * @val 销单ID
	 */
	public void setSell_bill_id(Long sell_bill_id) {
		this.sell_bill_id = sell_bill_id;
	}
	
	/**
	 * @val 产品型号名称
	 */
	public String getMd_name() {
		return md_name;
	}
	
	/**
	 * @val 产品型号名称
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 评分：很喜欢（5分） 喜欢（4分） 一般（3分） 不喜欢（2分） 很不喜欢（1分）
	 */
	public Integer getMark_star() {
		return mark_star;
	}
	
	/**
	 * @val 评分：很喜欢（5分） 喜欢（4分） 一般（3分） 不喜欢（2分） 很不喜欢（1分）
	 */
	public void setMark_star(Integer mark_star) {
		this.mark_star = mark_star;
	}
	
	/**
	 * @val 优点
	 */
	public String getPros() {
		return pros;
	}
	
	/**
	 * @val 优点
	 */
	public void setPros(String pros) {
		this.pros = pros;
	}
	
	/**
	 * @val 不足
	 */
	public String getCons() {
		return cons;
	}
	
	/**
	 * @val 不足
	 */
	public void setCons(String cons) {
		this.cons = cons;
	}
	
	/**
	 * @val 使用心得
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 使用心得
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}