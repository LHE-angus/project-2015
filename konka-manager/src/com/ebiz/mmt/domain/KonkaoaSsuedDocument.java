package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

public class KonkaoaSsuedDocument extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 添加日期
	 */
	private Date add_date;

	/**
	 * 栏目id 注：notice ：公告通知 ；meeting_record 会议纪要； file ：下发文件；meeting 会议；
	 */
	private String mod_type;

	/**
	 * 栏目名称
	 */
	private String mod_name;

	/**
	 * 栏目类别名称
	 */
	private String mod_type_name;

	/**
	 * 发放部门
	 */
	private String part_name;

	/**
	 * 文件号
	 */
	private String file_code;

	/**
	 * 批准人id
	 */
	private String pass_man;

	/**
	 * 批准人name
	 */
	private String pass_man_name;

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

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	public String getFile_code() {
		return file_code;
	}

	public void setFile_code(String file_code) {
		this.file_code = file_code;
	}

	public String getPass_man() {
		return pass_man;
	}

	public void setPass_man(String pass_man) {
		this.pass_man = pass_man;
	}

	public String getMod_type() {
		return mod_type;
	}

	public void setMod_type(String mod_type) {
		this.mod_type = mod_type;
	}

	public String getMod_name() {
		return mod_name;
	}

	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}

	public KonkaoaSsuedDocument() {
	}

	public String getMod_type_name() {
		return mod_type_name;
	}

	public void setMod_type_name(String mod_type_name) {
		this.mod_type_name = mod_type_name;
	}

	public String getPass_man_name() {
		return pass_man_name;
	}

	public void setPass_man_name(String pass_man_name) {
		this.pass_man_name = pass_man_name;
	}
}