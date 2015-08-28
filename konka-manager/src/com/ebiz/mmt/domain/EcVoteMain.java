package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-29 14:02:24
 */
public class EcVoteMain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String title;
	
	private String brief;
	
	private String content;
	
	private String vote_memo;
	
	private String main_pic;
	
	private Integer view_counts;
	
	private Date vote_start_time;
	
	private Date vote_end_time;
	
	private Integer vote_state;
	
	private Integer order_value;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Integer is_del;
	
	private Integer is_pub;
	
	private Integer own_sys;
	
	private Integer vote_num;
	
	private Integer vote_all_num;
	
	private Integer vote_time;
	
	public EcVoteMain() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 活动简介
	 */
	public String getBrief() {
		return brief;
	}
	
	/**
	 * @val 活动简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	/**
	 * @val 活动介绍
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 活动介绍
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @val 活动详情
	 */
	public String getVote_memo() {
		return vote_memo;
	}
	
	/**
	 * @val 活动详情
	 */
	public void setVote_memo(String vote_memo) {
		this.vote_memo = vote_memo;
	}
	
	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public String getMain_pic() {
		return main_pic;
	}
	
	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}
	
	/**
	 * @val 浏览次数
	 */
	public Integer getView_counts() {
		return view_counts;
	}
	
	/**
	 * @val 浏览次数
	 */
	public void setView_counts(Integer view_counts) {
		this.view_counts = view_counts;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public Date getVote_start_time() {
		return vote_start_time;
	}
	
	/**
	 * @val 活动开始时间
	 */
	public void setVote_start_time(Date vote_start_time) {
		this.vote_start_time = vote_start_time;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public Date getVote_end_time() {
		return vote_end_time;
	}
	
	/**
	 * @val 活动结束时间
	 */
	public void setVote_end_time(Date vote_end_time) {
		this.vote_end_time = vote_end_time;
	}
	
	/**
	 * @val 活动状态 0 未开始 1 已开始
	 */
	public Integer getVote_state() {
		return vote_state;
	}
	
	/**
	 * @val 活动状态 0 未开始 1 已开始
	 */
	public void setVote_state(Integer vote_state) {
		this.vote_state = vote_state;
	}
	
	/**
	 * @val 排序值：desc排序
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值：desc排序
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 添加时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 添加时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 添加用户ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加用户ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 是否发布： 0未发布，1已发布
	 */
	public Integer getIs_pub() {
		return is_pub;
	}
	
	/**
	 * @val 是否发布： 0未发布，1已发布
	 */
	public void setIs_pub(Integer is_pub) {
		this.is_pub = is_pub;
	}
	
	/**
	 * @val 所属系统:1工卡 2触网3会员4顺丰
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统:1工卡 2触网3会员4顺丰
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val 选项投票次数 每个选项一个用户每次可以投票次数
	 */
	public Integer getVote_num() {
		return vote_num;
	}
	
	/**
	 * @val 选项投票次数 每个选项一个用户每次可以投票次数
	 */
	public void setVote_num(Integer vote_num) {
		this.vote_num = vote_num;
	}
	
	/**
	 * @val 一个用户每次可投票数
	 */
	public Integer getVote_all_num() {
		return vote_all_num;
	}
	
	/**
	 * @val 一个用户每次可投票数
	 */
	public void setVote_all_num(Integer vote_all_num) {
		this.vote_all_num = vote_all_num;
	}
	
	/**
	 * @val 投票频率  1每天 2每周 3每月 0只能投一次
	 */
	public Integer getVote_time() {
		return vote_time;
	}
	
	/**
	 * @val 投票频率  1每天 2每周 3每月 0只能投一次
	 */
	public void setVote_time(Integer vote_time) {
		this.vote_time = vote_time;
	}
	
}