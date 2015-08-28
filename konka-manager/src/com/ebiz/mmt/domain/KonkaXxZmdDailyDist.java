package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Ren,zhong
 * @version 2012-03-22 14:21
 */
public class KonkaXxZmdDailyDist extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long dept_id;
	
	private String dist_title;
	
	private Date dist_date;
	
	private Integer dist_type;
	
	private Integer today_total_pd_count;
	
	private Integer today_total_bill_count;
	
	private Integer read_counts;
	
	private String dist_remarks;
	
	public KonkaXxZmdDailyDist() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDist_title() {
		return dist_title;
	}

	public void setDist_title(String dist_title) {
		this.dist_title = dist_title;
	}
	
	public Date getDist_date() {
		return dist_date;
	}

	public void setDist_date(Date dist_date) {
		this.dist_date = dist_date;
	}
	
	public Integer getDist_type() {
		return dist_type;
	}

	public void setDist_type(Integer dist_type) {
		this.dist_type = dist_type;
	}
	
	public Integer getToday_total_pd_count() {
		return today_total_pd_count;
	}

	public void setToday_total_pd_count(Integer today_total_pd_count) {
		this.today_total_pd_count = today_total_pd_count;
	}
	
	public Integer getToday_total_bill_count() {
		return today_total_bill_count;
	}

	public void setToday_total_bill_count(Integer today_total_bill_count) {
		this.today_total_bill_count = today_total_bill_count;
	}
	
	public Integer getRead_counts() {
		return read_counts;
	}

	public void setRead_counts(Integer read_counts) {
		this.read_counts = read_counts;
	}
	
	public String getDist_remarks() {
		return dist_remarks;
	}

	public void setDist_remarks(String dist_remarks) {
		this.dist_remarks = dist_remarks;
	}
	
}