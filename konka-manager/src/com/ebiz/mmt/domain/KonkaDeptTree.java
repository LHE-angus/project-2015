package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-28 18:17:19
 */
public class KonkaDeptTree extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long dept_id;
	
	private String dept_name;
	
	private String dept_sn;
	
	private String dept_desc;
	
	private Integer dept_type;
	
	private String tree_name;
	
	private Integer is_leaf;
	
	private Integer tree_level;
	
	private Long par_id;
	
	private String root_id;
	
	private String full_name;
	
	public KonkaDeptTree() {

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
	
	public String getDept_sn() {
		return dept_sn;
	}
	
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
	}
	
	public String getDept_desc() {
		return dept_desc;
	}
	
	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}
	
	public Integer getDept_type() {
		return dept_type;
	}
	
	public void setDept_type(Integer dept_type) {
		this.dept_type = dept_type;
	}
	
	public String getTree_name() {
		return tree_name;
	}
	
	public void setTree_name(String tree_name) {
		this.tree_name = tree_name;
	}
	
	public Integer getIs_leaf() {
		return is_leaf;
	}
	
	public void setIs_leaf(Integer is_leaf) {
		this.is_leaf = is_leaf;
	}
	
	public Integer getTree_level() {
		return tree_level;
	}
	
	public void setTree_level(Integer tree_level) {
		this.tree_level = tree_level;
	}
	
	public Long getPar_id() {
		return par_id;
	}
	
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	public String getRoot_id() {
		return root_id;
	}
	
	public void setRoot_id(String root_id) {
		this.root_id = root_id;
	}
	
	public String getFull_name() {
		return full_name;
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
}