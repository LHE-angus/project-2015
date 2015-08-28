package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-16 17:46:07
 */
public class KonkaOaModuleContent extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long module_id;
	
	private String content;
	
	public KonkaOaModuleContent() {

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
	 * @val 模板ID
	 */
	public Long getModule_id() {
		return module_id;
	}
	
	/**
	 * @val 模板ID
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}
	
	/**
	 * @val 模板内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 模板内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}