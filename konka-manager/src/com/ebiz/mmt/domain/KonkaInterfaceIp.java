package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-22 14:12:24
 */
public class KonkaInterfaceIp extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String licenses_sn;
	
	private String ip;
	
	private String remarks;
	
	private Date add_date;
	
	private Integer info_state;
	
	private Integer is_del;
	
	private Long add_user_id;
	
	public KonkaInterfaceIp() {

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
	 * @val 授权码
	 */
	public String getLicenses_sn() {
		return licenses_sn;
	}
	
	/**
	 * @val 授权码
	 */
	public void setLicenses_sn(String licenses_sn) {
		this.licenses_sn = licenses_sn;
	}
	
	/**
	 * @val IP
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * @val IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 是否注销:0-正常1-已注销
	 */
	public Integer getInfo_state() {
		return info_state;
	}
	
	/**
	 * @val 是否注销:0-正常1-已注销
	 */
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}
	
	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除:0-未删除 1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 添加用户ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加用户ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
}