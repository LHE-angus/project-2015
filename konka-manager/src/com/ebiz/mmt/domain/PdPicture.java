package com.ebiz.mmt.domain;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,Huan
 */
public class PdPicture extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long pd_id;

	private String pic_url;

	private Short del_mark;

	public PdPicture() {

	}

	public PdPicture(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public Short getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Short del_mark) {
		this.del_mark = del_mark;
	}
}