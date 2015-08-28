package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:10
 */
public class EcSallareaTemplate extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String title;

	private Long dept_id;

	private Long add_u_id;

	private Date add_date;

	private String remar;

	private Date update_date;

	private Integer state;

	private Set<String> pindexList;// 关联区域pindex

	public EcSallareaTemplate() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public Long getAdd_u_id() {
		return add_u_id;
	}

	public void setAdd_u_id(Long add_u_id) {
		this.add_u_id = add_u_id;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getRemar() {
		return remar;
	}

	public void setRemar(String remar) {
		this.remar = remar;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<String> getPindexList() {
		return pindexList;
	}

	public void setPindexList(Set<String> pindexList) {
		this.pindexList = pindexList;
	}

}