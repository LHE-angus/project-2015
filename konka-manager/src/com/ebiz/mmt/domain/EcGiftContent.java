package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:24
 */
public class EcGiftContent extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long kbp_id;
	
	private Integer type;
	
	private String content;
	
	public EcGiftContent() {

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
	 * @val 商品ID
	 */
	public Long getKbp_id() {
		return kbp_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public void setKbp_id(Long kbp_id) {
		this.kbp_id = kbp_id;
	}
	
	/**
	 * @val 类型：1-产品描述 2-产品规格 3-售后服务
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * @val 类型：1-产品描述 2-产品规格 3-售后服务
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @val 内容
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}