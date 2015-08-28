package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
public class KonkaEmFileReceiveUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long link_id;
	
	private Long file_receive_id;
	
	public KonkaEmFileReceiveUser() {

	}

	/**
	 * @val 序号
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 序号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 文件ID
	 */
	public Long getLink_id() {
		return link_id;
	}
	
	/**
	 * @val 文件ID
	 */
	public void setLink_id(Long link_id) {
		this.link_id = link_id;
	}
	
	/**
	 * @val 文件接收方ID
	 */
	public Long getFile_receive_id() {
		return file_receive_id;
	}
	
	/**
	 * @val 文件接收方ID
	 */
	public void setFile_receive_id(Long file_receive_id) {
		this.file_receive_id = file_receive_id;
	}
	
}