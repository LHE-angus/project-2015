package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
public class KonkaMobileEquStore extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long equ_id;
	
	private Long channel_a_id;
	
	private String channel_a_name;
	
	private Long channel_b_id;
	
	private String channel_b_name;
	
	private Long org_id;
	
	private String org_name;
	
	private Long dept_id;
	
	private String dept_name;
	
	private Long subcomp_id;
	
	private String subcomp_name;
	
	private Long office_id;
	
	private String office_name;
	
	private Long num;
	
	private Long report_id;
	
	private String report_name;
	
	private Date report_date;
	
	private Integer status;
	
	public KonkaMobileEquStore() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getEqu_id() {
		return equ_id;
	}
	
	public void setEqu_id(Long equ_id) {
		this.equ_id = equ_id;
	}
	
	public Long getChannel_a_id() {
		return channel_a_id;
	}
	
	public void setChannel_a_id(Long channel_a_id) {
		this.channel_a_id = channel_a_id;
	}
	
	public String getChannel_a_name() {
		return channel_a_name;
	}
	
	public void setChannel_a_name(String channel_a_name) {
		this.channel_a_name = channel_a_name;
	}
	
	public Long getChannel_b_id() {
		return channel_b_id;
	}
	
	public void setChannel_b_id(Long channel_b_id) {
		this.channel_b_id = channel_b_id;
	}
	
	public String getChannel_b_name() {
		return channel_b_name;
	}
	
	public void setChannel_b_name(String channel_b_name) {
		this.channel_b_name = channel_b_name;
	}
	
	public Long getOrg_id() {
		return org_id;
	}
	
	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}
	
	public String getOrg_name() {
		return org_name;
	}
	
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public Long getSubcomp_id() {
		return subcomp_id;
	}
	
	public void setSubcomp_id(Long subcomp_id) {
		this.subcomp_id = subcomp_id;
	}
	
	public String getSubcomp_name() {
		return subcomp_name;
	}
	
	public void setSubcomp_name(String subcomp_name) {
		this.subcomp_name = subcomp_name;
	}
	
	public Long getOffice_id() {
		return office_id;
	}
	
	public void setOffice_id(Long office_id) {
		this.office_id = office_id;
	}
	
	public String getOffice_name() {
		return office_name;
	}
	
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	
	public Long getNum() {
		return num;
	}
	
	public void setNum(Long num) {
		this.num = num;
	}
	
	public Long getReport_id() {
		return report_id;
	}
	
	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}
	
	public String getReport_name() {
		return report_name;
	}
	
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	
	public Date getReport_date() {
		return report_date;
	}
	
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}