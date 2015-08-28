package com.ebiz.mmt.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
 public class KonkaoaFilesAuditPopedom extends BaseDomain implements Serializable {
 	
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
	 * 类别类型
	 */
    private Integer c_type;
           
	/**
	 * 类别编号
	 */
    private Long c_index;
           
	public KonkaoaFilesAuditPopedom() {

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
           
 	public void setC_type(Integer c_type) {
		this.c_type = c_type;
	}
	
	public Integer getC_type() {
		return c_type;
	}
           
 	public void setC_index(Long c_index) {
		this.c_index = c_index;
	}
	
	public Long getC_index() {
		return c_index;
	}
           
	@Override 
    public String toString() { 
   		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
 }