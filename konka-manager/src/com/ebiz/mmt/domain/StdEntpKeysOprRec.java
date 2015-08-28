package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-09-20 15:43:53
 */
public class StdEntpKeysOprRec extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Integer opr_type;

	private Integer own_sys;

	private Long entp_id;

	private String key_seq;

	private Integer user_type;

	private Long active_entp_id;

	private Date add_date;

	private List<StdEntpKeysKeys> stdEntpKeysKeysList;

	public StdEntpKeysOprRec() {

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
	 * @val 操作类型：0-激活，1-注销，2-注销申请
	 */
	public Integer getOpr_type() {
		return opr_type;
	}

	/**
	 * @val 操作类型：0-激活，1-注销，2-注销申请
	 */
	public void setOpr_type(Integer opr_type) {
		this.opr_type = opr_type;
	}

	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统，1：家电下乡；2：以旧换新
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	/**
	 * @val 操作企业编号
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 操作企业编号
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 激活/注销密钥值
	 */
	public String getKey_seq() {
		return key_seq;
	}

	/**
	 * @val 激活/注销密钥值
	 */
	public void setKey_seq(String key_seq) {
		this.key_seq = key_seq;
	}

	/**
	 * @val 激活/注销用户类型：2-销售，4-回收
	 */
	public Integer getUser_type() {
		return user_type;
	}

	/**
	 * @val 激活/注销用户类型：2-销售，4-回收
	 */
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	/**
	 * @val 激活后企业ID
	 */
	public Long getActive_entp_id() {
		return active_entp_id;
	}

	/**
	 * @val 激活后企业ID
	 */
	public void setActive_entp_id(Long active_entp_id) {
		this.active_entp_id = active_entp_id;
	}

	/**
	 * @val 操作时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 操作时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public List<StdEntpKeysKeys> getStdEntpKeysKeysList() {
		return stdEntpKeysKeysList;
	}

	public void setStdEntpKeysKeysList(List<StdEntpKeysKeys> stdEntpKeysKeysList) {
		this.stdEntpKeysKeysList = stdEntpKeysKeysList;
	}

}