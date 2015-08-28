package com.ebiz.mmt.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
 public class KonkaoaFilesContent extends BaseDomain implements Serializable {
 	
 	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
    private Long id;
           
	/**
	 * 关联ID
	 */
    private Long link_id;
           
	/**
	 * 文档内容
	 */
    private String content;
           
	public KonkaoaFilesContent() {

	}
 
 	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
           
 	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}
	
	public Long getLink_id() {
		return link_id;
	}
           
 	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return org.apache.commons.lang.StringUtils.replace(content, "&nbsp;", " ") ;
	}
           
	@Override 
    public String toString() { 
   		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
 }