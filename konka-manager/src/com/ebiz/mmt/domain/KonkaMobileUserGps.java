package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-05 15:52:02
 */
public class KonkaMobileUserGps extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private String mp_sn;

	private String lng;

	private String lat;

	private Date update_time;

	public KonkaMobileUserGps() {

	}

	/**
	 * @val 手机号码
	 */
	public String getMp_sn() {
		return mp_sn;
	}

	/**
	 * @val 手机号码
	 */
	public void setMp_sn(String mp_sn) {
		this.mp_sn = mp_sn;
	}

	/**
	 * @val 经度
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @val 经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @val 纬度
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @val 纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @val 最后更新时间
	 */
	public Date getUpdate_time() {
		return update_time;
	}

	/**
	 * @val 最后更新时间
	 */
	public void setUpdate_time(Date updateTime) {
		update_time = updateTime;
	}

}