package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
public class EcSpecFbTeamList extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String team_name;
	
	private String group_name;
	
	private String team_flag_pic_url;
	
	private String team_main_pic_url;
	
	private String team_intro;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Long order_sort_num;
	
	private Integer is_del;
	
	public EcSpecFbTeamList() {

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
	 * @val 球队名称
	 */
	public String getTeam_name() {
		return team_name;
	}
	
	/**
	 * @val 球队名称
	 */
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	/**
	 * @val 球队国旗
	 */
	public String getTeam_flag_pic_url() {
		return team_flag_pic_url;
	}
	
	/**
	 * @val 球队国旗
	 */
	public void setTeam_flag_pic_url(String team_flag_pic_url) {
		this.team_flag_pic_url = team_flag_pic_url;
	}
	
	/**
	 * @val 球队图片
	 */
	public String getTeam_main_pic_url() {
		return team_main_pic_url;
	}
	
	/**
	 * @val 球队图片
	 */
	public void setTeam_main_pic_url(String team_main_pic_url) {
		this.team_main_pic_url = team_main_pic_url;
	}
	
	/**
	 * @val 球队介绍URL
	 */
	public String getTeam_intro() {
		return team_intro;
	}
	
	/**
	 * @val 球队介绍URL
	 */
	public void setTeam_intro(String team_intro) {
		this.team_intro = team_intro;
	}
	
	/**
	 * @val 创建时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 创建时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 创建人
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 创建人
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 排序号
	 */
	public Long getOrder_sort_num() {
		return order_sort_num;
	}
	
	/**
	 * @val 排序号
	 */
	public void setOrder_sort_num(Long order_sort_num) {
		this.order_sort_num = order_sort_num;
	}
	
	/**
	 * @val 状态
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 状态
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
    /**
     * 组名
     */
	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	
}