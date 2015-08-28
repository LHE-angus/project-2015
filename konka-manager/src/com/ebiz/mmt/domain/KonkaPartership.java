package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xiao,GuoJian
 * @date 2014-04-16 14:48:10
 */
public class KonkaPartership extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String shoudf_id;
	
	private String shoudf_name;
	
	private String songdf_id;
	
	private String songdf_name;
	
	private String add_user_id;
	
	private Date add_date;
	
	public KonkaPartership() {

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
	 * @val 售达方客户R3编号
	 */
	public String getShoudf_id() {
		return shoudf_id;
	}
	
	/**
	 * @val 售达方客户R3编号
	 */
	public void setShoudf_id(String shoudf_id) {
		this.shoudf_id = shoudf_id;
	}
	
	/**
	 * @val 售达方客户名称
	 */
	public String getShoudf_name() {
		return shoudf_name;
	}
	
	/**
	 * @val 售达方客户名称
	 */
	public void setShoudf_name(String shoudf_name) {
		this.shoudf_name = shoudf_name;
	}
	
	/**
	 * @val 送达方客户R3编号
	 */
	public String getSongdf_id() {
		return songdf_id;
	}
	
	/**
	 * @val 送达方客户R3编号
	 */
	public void setSongdf_id(String songdf_id) {
		this.songdf_id = songdf_id;
	}
	
	/**
	 * @val 送达方客户名称
	 */
	public String getSongdf_name() {
		return songdf_name;
	}
	
	/**
	 * @val 送达方客户名称
	 */
	public void setSongdf_name(String songdf_name) {
		this.songdf_name = songdf_name;
	}
	
	/**
	 * @val 添加人ID
	 */
	public String getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(String add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 添加日期
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加日期
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}