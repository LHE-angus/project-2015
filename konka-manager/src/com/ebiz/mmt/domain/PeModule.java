package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-24 10:17:10
 */
public class PeModule extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long mod_id;
	
	private Long par_id;
	
	private String mod_name;
	
	private String mod_desc;
	
	private String mod_url;
	
	private Integer order_value;
	
	private Integer is_public;
	
	private Integer del_mark;
	
	public PeModule() {

	}

	public Long getMod_id() {
		return mod_id;
	}
	
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
	}
	
	public Long getPar_id() {
		return par_id;
	}
	
	public void setPar_id(Long par_id) {
		this.par_id = par_id;
	}
	
	public String getMod_name() {
		return mod_name;
	}
	
	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}
	
	public String getMod_desc() {
		return mod_desc;
	}
	
	public void setMod_desc(String mod_desc) {
		this.mod_desc = mod_desc;
	}
	
	public String getMod_url() {
		return mod_url;
	}
	
	public void setMod_url(String mod_url) {
		this.mod_url = mod_url;
	}
	
	public Integer getOrder_value() {
		return order_value;
	}
	
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 0：私有，必须设定权限后才能访问
	 * @val 1：公共，对所有用户都有权限
	 * @val 9：管理，管理员默认有权限的模块
	 */
	public Integer getIs_public() {
		return is_public;
	}
	
	/**
	 * @val 0：私有，必须设定权限后才能访问
	 * @val 1：公共，对所有用户都有权限
	 * @val 9：管理，管理员默认有权限的模块
	 */
	public void setIs_public(Integer is_public) {
		this.is_public = is_public;
	}
	
	public Integer getDel_mark() {
		return del_mark;
	}
	
	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}
	
}