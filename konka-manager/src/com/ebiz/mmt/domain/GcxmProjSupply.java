package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:08
 */
public class GcxmProjSupply extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String proj_id;

	private String proj_code;

	private String supply_model;

	private String supply_num;

	private String supply_memo;

	private Long info_state;

	private Integer del_mark;

	private Integer is_validate;

	private String create_name;

	private Long create_user_id;

	private Date create_date;

	public GcxmProjSupply() {

	}

	/**
	 * @val ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 项目ID
	 */
	public String getProj_id() {
		return proj_id;
	}

	/**
	 * @val 项目ID
	 */
	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public String getProj_code() {
		return proj_code;
	}

	/**
	 * @val 项目编号：GCXM+分公司编码+8位日期+3位流水号
	 */
	public void setProj_code(String proj_code) {
		this.proj_code = proj_code;
	}

	/**
	 * @val 供货机型
	 */
	public String getSupply_model() {
		return supply_model;
	}

	/**
	 * @val 供货机型
	 */
	public void setSupply_model(String supply_model) {
		this.supply_model = supply_model;
	}

	/**
	 * @val 供货数量
	 */
	public String getSupply_num() {
		return supply_num;
	}

	/**
	 * @val 供货数量
	 */
	public void setSupply_num(String supply_num) {
		this.supply_num = supply_num;
	}

	/**
	 * @val 描述
	 */
	public String getSupply_memo() {
		return supply_memo;
	}

	/**
	 * @val 描述
	 */
	public void setSupply_memo(String supply_memo) {
		this.supply_memo = supply_memo;
	}

	/**
	 * @val 状态
	 */
	public Long getInfo_state() {
		return info_state;
	}

	/**
	 * @val 状态
	 */
	public void setInfo_state(Long info_state) {
		this.info_state = info_state;
	}

	public Integer getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Integer del_mark) {
		this.del_mark = del_mark;
	}

	public Integer getIs_validate() {
		return is_validate;
	}

	public void setIs_validate(Integer is_validate) {
		this.is_validate = is_validate;
	}

	public String getCreate_name() {
		return create_name;
	}

	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}