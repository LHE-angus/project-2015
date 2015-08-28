package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:42
 */
public class EcBuyInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long ec_user_id;

	private String c_name;

	private String c_tel;

	private String c_dw_name;

	private String c_email;

	private String content;

	private Date add_date;

	private Integer info_state;

	private Integer own_sys;

	private EcUser ecUser;

	private Integer is_view;// 是否查看 默认0

	private Long view_user_id;// 查看人id

	private String view_user_name;// 查看人姓名

	public Integer getIs_view() {
		return is_view;
	}

	public void setIs_view(Integer isView) {
		is_view = isView;
	}

	public Long getView_user_id() {
		return view_user_id;
	}

	public void setView_user_id(Long viewUserId) {
		view_user_id = viewUserId;
	}

	public String getView_user_name() {
		return view_user_name;
	}

	public void setView_user_name(String viewUserName) {
		view_user_name = viewUserName;
	}

	public EcUser getEcUser() {
		return ecUser;
	}

	public void setEcUser(EcUser ecUser) {
		this.ecUser = ecUser;
	}

	public EcBuyInfo() {

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
	 * @val 用户ID：如果是没有登录则不保存
	 */
	public Long getEc_user_id() {
		return ec_user_id;
	}

	/**
	 * @val 用户ID：如果是没有登录则不保存
	 */
	public void setEc_user_id(Long ec_user_id) {
		this.ec_user_id = ec_user_id;
	}

	/**
	 * @val 客户姓名
	 */
	public String getC_name() {
		return c_name;
	}

	/**
	 * @val 客户姓名
	 */
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	/**
	 * @val 联系电话
	 */
	public String getC_tel() {
		return c_tel;
	}

	/**
	 * @val 联系电话
	 */
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}

	/**
	 * @val 单位名称
	 */
	public String getC_dw_name() {
		return c_dw_name;
	}

	/**
	 * @val 单位名称
	 */
	public void setC_dw_name(String cDwName) {
		c_dw_name = cDwName;
	}

	/**
	 * @val 联系邮箱
	 */
	public String getC_email() {
		return c_email;
	}

	/**
	 * @val 联系邮箱
	 */
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	/**
	 * @val 留言内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @val 留言内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @val 留言时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 留言时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val -1：已删除
	 * @val 0：未回复
	 * @val 1：已回复
	 */
	public Integer getInfo_state() {
		return info_state;
	}

	/**
	 * @val -1：已删除
	 * @val 0：未回复
	 * @val 1：已回复
	 */
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}

	/**
	 * @val 所属系统：1-工卡，2-触网，3--会员
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统：1-工卡，2-触网，3--会员
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

}