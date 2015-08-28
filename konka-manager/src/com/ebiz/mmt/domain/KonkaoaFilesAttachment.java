package com.ebiz.mmt.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */
 public class KonkaoaFilesAttachment extends BaseDomain implements Serializable {
 	
 	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
    private Long id;
           
	/**
	 * 关联ID
	 */
    private Long link_id;
           
    private String link_tab;
           
	/**
	 * 源文件名
	 */
    private String file_name;
           
	/**
	 * 文件类型
	 */
    private String file_type;
           
	/**
	 * 文件大小
	 */
    private Long file_size;
           
	/**
	 * 存储路径
	 */
    private String save_path;
           
	/**
	 * 存储名称
	 */
    private String save_name;
           
	/**
	 * 文件说明
	 */
    private String file_desc;
           
	/**
	 * 是否删除
	 */
    private Integer is_del;
           
	public KonkaoaFilesAttachment() {

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
           
 	public void setLink_tab(String link_tab) {
		this.link_tab = link_tab;
	}
	
	public String getLink_tab() {
		return link_tab;
	}
           
 	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public String getFile_name() {
		return file_name;
	}
           
 	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	public String getFile_type() {
		return file_type;
	}
           
 	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}
	
	public Long getFile_size() {
		return file_size;
	}
           
 	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	
	public String getSave_path() {
		return save_path;
	}
           
 	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}
	
	public String getSave_name() {
		return save_name;
	}
           
 	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}
	
	public String getFile_desc() {
		return file_desc;
	}
           
 	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	public Integer getIs_del() {
		return is_del;
	}
           
	@Override 
    public String toString() { 
   		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
 }