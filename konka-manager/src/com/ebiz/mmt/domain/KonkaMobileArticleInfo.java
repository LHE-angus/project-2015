package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hu,Hao
 * @version 2013-05-30
 */
public class KonkaMobileArticleInfo extends BaseDomain implements Serializable{

	private static final long serialVersionUID = -1L;

	private String title;

	private String article_type_name;

	private String pub_date;

	private String summary;

	private String link_out_addr;

	private String img_path;

	private String add_user_name;
	
	private Long view_count;

	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @val 分类
	 */
	public void setArticle_type_name(String article_type_name) {
		this.article_type_name = article_type_name;
	}

	/**
	 * @val 分类
	 */
	public String getArticle_type_name() {
		return article_type_name;
	}

	/**
	 * @val 发布时间
	 */
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	/**
	 * @val 发布时间
	 */
	public String getPub_date() {
		return pub_date;
	}

	/**
	 * @val 内容简介
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @val 内容简介
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @val 内容链接URL
	 */
	public void setLink_out_addr(String link_out_addr) {
		this.link_out_addr = link_out_addr;
	}

	/**
	 * @val 内容链接URL
	 */
	public String getLink_out_addr() {
		return link_out_addr;
	}

	/**
	 * @val 图标地址
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @val 图标地址
	 */
	public String getImg_path() {
		return img_path;
	}

	/**
	 * @val 发布单位
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	/**
	 * @val 发布单位
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}

	/**
	 * @val 访问量
	 */
	public void setView_count(Long view_count) {
		this.view_count = view_count;
	}

	/**
	 * @val 访问量
	 */
	public Long getView_count() {
		return view_count;
	}
}
