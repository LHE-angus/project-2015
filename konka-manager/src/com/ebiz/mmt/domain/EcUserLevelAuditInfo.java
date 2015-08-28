package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-15 18:37:41
 */
public class EcUserLevelAuditInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long ec_user_id;
	
	private String real_name;
	
	private String card_no;
	
	private String level_name;
	
	private String old_level_name;
	
	private Long level_id;
	
	private Date add_date;
	
	private String opr_user_id;
	
	private String opr_user_name;
	
	private String memo;
	
	public EcUserLevelAuditInfo() {

	}

	/**
	 * @val 信息ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 信息ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 用户ID
	 */
	public Long getEc_user_id() {
		return ec_user_id;
	}
	
	/**
	 * @val 用户ID
	 */
	public void setEc_user_id(Long ec_user_id) {
		this.ec_user_id = ec_user_id;
	}
	
	/**
	 * @val 姓名
	 */
	public String getReal_name() {
		return real_name;
	}
	
	/**
	 * @val 姓名
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	/**
	 * @val 工卡号
	 */
	public String getCard_no() {
		return card_no;
	}
	
	/**
	 * @val 工卡号
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	/**
	 * @val 会员等级名称
	 */
	public String getLevel_name() {
		return level_name;
	}
	
	/**
	 * @val 会员等级名称
	 */
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
	/**
	 * @val 原等级名称
	 */
	public String getOld_level_name() {
		return old_level_name;
	}
	
	/**
	 * @val 原等级名称
	 */
	public void setOld_level_name(String old_level_name) {
		this.old_level_name = old_level_name;
	}
	
	/**
	 * @val 会员等级
	 */
	public Long getLevel_id() {
		return level_id;
	}
	
	/**
	 * @val 会员等级
	 */
	public void setLevel_id(Long level_id) {
		this.level_id = level_id;
	}
	
	/**
	 * @val 升级时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 升级时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 操作用户ID
	 */
	public String getOpr_user_id() {
		return opr_user_id;
	}
	
	/**
	 * @val 操作用户ID
	 */
	public void setOpr_user_id(String opr_user_id) {
		this.opr_user_id = opr_user_id;
	}
	
	/**
	 * @val 操作人名称
	 */
	public String getOpr_user_name() {
		return opr_user_name;
	}
	
	/**
	 * @val 操作人名称
	 */
	public void setOpr_user_name(String opr_user_name) {
		this.opr_user_name = opr_user_name;
	}
	
	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}