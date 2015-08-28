package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Cheng,Bing
 * @version 2012-02-14
 */
public class KonkaUserMobileSetPlan extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long next_id;

	private Long s_id;
	
	private String setplan_name;
	
	private String setplan_crc;

	private String version;
	
	private Long year;
	
	private Long month;
	
	private Long time_interval;
	
	private String blockmobile_str;
	
	private String date_str;
	
	private String time_str;	
	
	private Long gps_sendType;
	
	private Date create_date;

	private Long create_uid;

	private Date update_date;

	private Long update_uid;

	private Long is_del;

	public KonkaUserMobileSetPlan() {

	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getNext_id() {
		return next_id;
	}


	public void setNext_id(Long next_id) {
		this.next_id = next_id;
	}


	public Long getS_id() {
		return s_id;
	}


	public void setS_id(Long s_id) {
		this.s_id = s_id;
	}


	public String getSetplan_name() {
		return setplan_name;
	}


	public void setSetplan_name(String setplan_name) {
		this.setplan_name = setplan_name;
	}


	public String getSetplan_crc() {
		return setplan_crc;
	}


	public void setSetplan_crc(String setplan_crc) {
		this.setplan_crc = setplan_crc;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public Long getYear() {
		return year;
	}


	public void setYear(Long year) {
		this.year = year;
	}


	public Long getMonth() {
		return month;
	}


	public void setMonth(Long month) {
		this.month = month;
	}


	public Long getTime_interval() {
		return time_interval;
	}


	public void setTime_interval(Long time_interval) {
		this.time_interval = time_interval;
	}


	public String getBlockmobile_str() {
		return blockmobile_str;
	}


	public void setBlockmobile_str(String blockmobile_str) {
		this.blockmobile_str = blockmobile_str;
	}


	public String getDate_str() {
		return date_str;
	}


	public void setDate_str(String date_str) {
		this.date_str = date_str;
	}


	public String getTime_str() {
		return time_str;
	}


	public void setTime_str(String time_str) {
		this.time_str = time_str;
	}


	public Long getGps_sendType() {
		return gps_sendType;
	}


	public void setGps_sendType(Long gps_sendType) {
		this.gps_sendType = gps_sendType;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Long getCreate_uid() {
		return create_uid;
	}


	public void setCreate_uid(Long create_uid) {
		this.create_uid = create_uid;
	}


	public Date getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}


	public Long getUpdate_uid() {
		return update_uid;
	}


	public void setUpdate_uid(Long update_uid) {
		this.update_uid = update_uid;
	}


	public Long getIs_del() {
		return is_del;
	}


	public void setIs_del(Long is_del) {
		this.is_del = is_del;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}