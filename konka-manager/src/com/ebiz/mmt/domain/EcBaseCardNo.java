package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-15 10:43:29
 */
public class EcBaseCardNo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String card_no;

	private Date card_pub_date;

	private String card_sender;

	private String card_sender_dept;

	private Long card_type;

	private Long card_level;

	private String member_name;

	private String member_tel;

	private String member_id;

	private Date card_act_valid_date;

	private Date card_act_date;

	private String card_creater;

	private String card_create_dept;

	private Date card_limit_start;

	private Date card_limit_end;

	private String card_memo;

	private Integer card_allow_mul_act;

	private EcBaseCardLevel ecBaseCardLevel;

	private EcBaseCardType ecBaseCardType;

	public EcBaseCardNo() {

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
	 * @val 会员卡号
	 */
	public String getCard_no() {
		return card_no;
	}

	/**
	 * @val 会员卡号
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	/**
	 * @val 发卡日期
	 */
	public Date getCard_pub_date() {
		return card_pub_date;
	}

	/**
	 * @val 发卡日期
	 */
	public void setCard_pub_date(Date card_pub_date) {
		this.card_pub_date = card_pub_date;
	}

	/**
	 * @val 发卡人、客户
	 */
	public String getCard_sender() {
		return card_sender;
	}

	/**
	 * @val 发卡人、客户
	 */
	public void setCard_sender(String card_sender) {
		this.card_sender = card_sender;
	}

	/**
	 * @val 发卡部门
	 */
	public String getCard_sender_dept() {
		return card_sender_dept;
	}

	/**
	 * @val 发卡部门
	 */
	public void setCard_sender_dept(String card_sender_dept) {
		this.card_sender_dept = card_sender_dept;
	}

	/**
	 * @val 会员卡分类,方便进行统计汇总
	 */
	public Long getCard_type() {
		return card_type;
	}

	/**
	 * @val 会员卡分类,方便进行统计汇总
	 */
	public void setCard_type(Long card_type) {
		this.card_type = card_type;
	}

	/**
	 * @val 会员卡等级,确定会员的等级
	 */
	public Long getCard_level() {
		return card_level;
	}

	/**
	 * @val 会员卡等级,确定会员的等级
	 */
	public void setCard_level(Long card_level) {
		this.card_level = card_level;
	}

	/**
	 * @val 会员姓名
	 */
	public String getMember_name() {
		return member_name;
	}

	/**
	 * @val 会员姓名
	 */
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	/**
	 * @val 会员电话
	 */
	public String getMember_tel() {
		return member_tel;
	}

	/**
	 * @val 会员电话
	 */
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	/**
	 * @val 会员身份证
	 */
	public String getMember_id() {
		return member_id;
	}

	/**
	 * @val 会员身份证
	 */
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	/**
	 * @val 会员卡激活有效期
	 */
	public Date getCard_act_valid_date() {
		return card_act_valid_date;
	}

	/**
	 * @val 会员卡激活有效期
	 */
	public void setCard_act_valid_date(Date card_act_valid_date) {
		this.card_act_valid_date = card_act_valid_date;
	}

	/**
	 * @val 会员卡激活时间
	 */
	public Date getCard_act_date() {
		return card_act_date;
	}

	/**
	 * @val 会员卡激活时间
	 */
	public void setCard_act_date(Date card_act_date) {
		this.card_act_date = card_act_date;
	}

	/**
	 * @val 会员卡制作人
	 */
	public String getCard_creater() {
		return card_creater;
	}

	/**
	 * @val 会员卡制作人
	 */
	public void setCard_creater(String card_creater) {
		this.card_creater = card_creater;
	}

	/**
	 * @val 会员卡制作部门
	 */
	public String getCard_create_dept() {
		return card_create_dept;
	}

	/**
	 * @val 会员卡制作部门
	 */
	public void setCard_create_dept(String card_create_dept) {
		this.card_create_dept = card_create_dept;
	}

	/**
	 * @val 会员卡有效期结算
	 */
	public Date getCard_limit_start() {
		return card_limit_start;
	}

	/**
	 * @val 会员卡有效期结算
	 */
	public void setCard_limit_start(Date card_limit_start) {
		this.card_limit_start = card_limit_start;
	}

	/**
	 * @val 会员卡有效期结束
	 */
	public Date getCard_limit_end() {
		return card_limit_end;
	}

	/**
	 * @val 会员卡有效期结束
	 */
	public void setCard_limit_end(Date card_limit_end) {
		this.card_limit_end = card_limit_end;
	}

	/**
	 * @val 会员卡备注
	 */
	public String getCard_memo() {
		return card_memo;
	}

	/**
	 * @val 会员卡备注
	 */
	public void setCard_memo(String card_memo) {
		this.card_memo = card_memo;
	}

	/**
	 * @val 是否允许多次激活
	 * @val 0，不允许多次激活
	 * @val 1，允许多次激活
	 */
	public Integer getCard_allow_mul_act() {
		return card_allow_mul_act;
	}

	/**
	 * @val 是否允许多次激活
	 * @val 0，不允许多次激活
	 * @val 1，允许多次激活
	 */
	public void setCard_allow_mul_act(Integer card_allow_mul_act) {
		this.card_allow_mul_act = card_allow_mul_act;
	}

	public EcBaseCardLevel getEcBaseCardLevel() {
		return ecBaseCardLevel;
	}

	public void setEcBaseCardLevel(EcBaseCardLevel ecBaseCardLevel) {
		this.ecBaseCardLevel = ecBaseCardLevel;
	}

	public EcBaseCardType getEcBaseCardType() {
		return ecBaseCardType;
	}

	public void setEcBaseCardType(EcBaseCardType ecBaseCardType) {
		this.ecBaseCardType = ecBaseCardType;
	}

}