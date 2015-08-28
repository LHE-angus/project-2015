package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;


public class TaskContent extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long participant_id;
	
	private String task_p_type;
	
	private Double task_xs;
	
	private Double task_ed;
	
	private String task_desc;
	
	private Integer task_year;
	
	private Integer task_month;
	
	private Integer task_day;
	

	public TaskContent() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParticipant_id() {
		return participant_id;
	}
	
	public void setParticipant_id(Long participant_id) {
		this.participant_id = participant_id;
	}
	
	public String getTask_p_type() {
		return task_p_type;
	}
	
	public void setTask_p_type(String task_p_type) {
		this.task_p_type = task_p_type;
	}
	
	public Double getTask_xs() {
		return task_xs;
	}
	
	public void setTask_xs(Double task_xs) {
		this.task_xs = task_xs;
	}
	
	public Double getTask_ed() {
		return task_ed;
	}
	
	public void setTask_ed(Double task_ed) {
		this.task_ed = task_ed;
	}
	
	public String getTask_desc() {
		return task_desc;
	}
	
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
	
	public Integer getTask_year() {
		return task_year;
	}
	
	public void setTask_year(Integer task_year) {
		this.task_year = task_year;
	}
	
	public Integer getTask_month() {
		return task_month;
	}
	
	public void setTask_month(Integer task_month) {
		this.task_month = task_month;
	}
	
	public Integer getTask_day() {
		return task_day;
	}
	
	public void setTask_day(Integer task_day) {
		this.task_day = task_day;
	}

	
}