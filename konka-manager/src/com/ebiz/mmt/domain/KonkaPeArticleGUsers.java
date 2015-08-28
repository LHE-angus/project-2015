package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-11 17:25:49
 */
public class KonkaPeArticleGUsers extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long group_id;
	
	private String group_user_name;
	
	private String group_user_id;
	
	public KonkaPeArticleGUsers() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 群组ID
	 */
	public Long getGroup_id() {
		return group_id;
	}
	
	/**
	 * @val 群组ID
	 */
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}
	
	/**
	 * @val 群员名称
	 */
	public String getGroup_user_name() {
		return group_user_name;
	}
	
	/**
	 * @val 群员名称
	 */
	public void setGroup_user_name(String group_user_name) {
		this.group_user_name = group_user_name;
	}
	
	/**
	 * @val 群员ID
	 */
	public String getGroup_user_id() {
		return group_user_id;
	}
	
	/**
	 * @val 群员ID
	 */
	public void setGroup_user_id(String group_user_id) {
		this.group_user_id = group_user_id;
	}
	
}