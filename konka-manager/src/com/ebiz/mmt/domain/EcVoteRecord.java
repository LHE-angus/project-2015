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
public class EcVoteRecord extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long vote_id;
	
	private Long vote_option_id;
	
	private Long user_id;
	
	private String user_name;
	
	private Date add_date;
	
	private Integer is_del;
	
	private Integer own_sys;
	
	private String ip;
	
	public EcVoteRecord() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 投票活动ID
	 */
	public Long getVote_id() {
		return vote_id;
	}
	
	/**
	 * @val 投票活动ID
	 */
	public void setVote_id(Long vote_id) {
		this.vote_id = vote_id;
	}
	
	/**
	 * @val 投票选项ID
	 */
	public Long getVote_option_id() {
		return vote_option_id;
	}
	
	/**
	 * @val 投票选项ID
	 */
	public void setVote_option_id(Long vote_option_id) {
		this.vote_option_id = vote_option_id;
	}
	
	/**
	 * @val 投票用户ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 投票用户ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 投票用户名
	 */
	public String getUser_name() {
		return user_name;
	}
	
	/**
	 * @val 投票用户名
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	/**
	 * @val 投票时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 投票时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
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
	 * @val 所属系统:1工卡 2触网 3会员 4顺丰
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统:1工卡 2触网 3会员 4顺丰
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val IP
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * @val IP
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}