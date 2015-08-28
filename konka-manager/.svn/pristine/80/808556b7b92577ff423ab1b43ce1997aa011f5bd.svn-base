package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
public class KonkaUserGpsInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String mobile_no;

	/* 手机传送的GPS数据类型0:百度经纬度，1谷歌经纬度  */
	private Long gps_type;
	
	private Date send_time;
	
	private Date update_time;
    /* 手机GPS经度 */
	private String gps_longitude;
    /* 手机GPS纬度  */
	private String gps_latitude;	
    /* 百度经度 */
	private String blongitude;
	/* 百度纬度 */
	private String blatitude;
    /* 谷歌经度 */
	private String glongitude;
    /* 谷歌经度 */
	private String glatitude;
	
	private String speed;

	private Long signal_strength;
	/* 地址  */
	private String address;
	/* IP  */
	private String ip;
	/* CRC码  */
	private String randCRC;
	
	public KonkaUserGpsInfo() {

	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMobile_no() {
		return mobile_no;
	}



	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}



	public Long getGps_type() {
		return gps_type;
	}



	public void setGps_type(Long gps_type) {
		this.gps_type = gps_type;
	}



	public Date getSend_time() {
		return send_time;
	}



	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}



	public Date getUpdate_time() {
		return update_time;
	}



	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}



	public String getGps_longitude() {
		return gps_longitude;
	}



	public void setGps_longitude(String gps_longitude) {
		this.gps_longitude = gps_longitude;
	}



	public String getGps_latitude() {
		return gps_latitude;
	}



	public void setGps_latitude(String gps_latitude) {
		this.gps_latitude = gps_latitude;
	}



	public String getBlongitude() {
		return blongitude;
	}



	public void setBlongitude(String blongitude) {
		this.blongitude = blongitude;
	}



	public String getBlatitude() {
		return blatitude;
	}



	public void setBlatitude(String blatitude) {
		this.blatitude = blatitude;
	}



	public String getGlongitude() {
		return glongitude;
	}



	public void setGlongitude(String glongitude) {
		this.glongitude = glongitude;
	}



	public String getGlatitude() {
		return glatitude;
	}



	public void setGlatitude(String glatitude) {
		this.glatitude = glatitude;
	}



	public String getSpeed() {
		return speed;
	}



	public void setSpeed(String speed) {
		this.speed = speed;
	}



	public Long getSignal_strength() {
		return signal_strength;
	}



	public void setSignal_strength(Long signal_strength) {
		this.signal_strength = signal_strength;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getRandCRC() {
		return randCRC;
	}



	public void setRandCRC(String randCRC) {
		this.randCRC = randCRC;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}