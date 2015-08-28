package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-12 16:46:52
 */
public class EcArticleInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String mod_index;
	
	private String title;
	
	private String image_path;
	
	private String image_desc;
	
	private String image_url;
	
	private Integer own_sys;
	
	private Integer plat_sys;
	
	private Long dept_id;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Date update_date;
	
	private String remark;
	
	private Integer del_mark;
	
	private String content;
	
	private Integer info_state;
	
	public EcArticleInfo() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMod_index() {
		return mod_index;
	}
	
	public void setMod_index(String mod_index) {
		this.mod_index = mod_index;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage_path() {
		return image_path;
	}
	
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	
	public String getImage_desc() {
		return image_desc;
	}
	
	public void setImage_desc(String image_desc) {
		this.image_desc = image_desc;
	}
	
	public String getImage_url() {
		return image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	public Integer getPlat_sys() {
		return plat_sys;
	}
	
	public void setPlat_sys(Integer plat_sys) {
		this.plat_sys = plat_sys;
	}
	
	public Long getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	public Date getAdd_date() {
		return add_date;
	}
	
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getDel_mark() {
		return del_mark;
	}
	
	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getInfo_state() {
		return info_state;
	}
	
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}
	
}