package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Hui,Gang
 * @version Build 2010-12-24 10:27:40
 */
public class KonkaoaFilesAuditAgentUser extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 用户ID
	 */
	private Long user_id;

	/**
	 * 代理用户ID
	 */
	private Long agent_user_id;

	/**
	 * 代理用户名
	 */
	private String agent_user_name;

	/**
	 * 添加时间
	 */
	private Date add_datetime;

	/**
	 * 是否删除
	 */
	private Integer is_del;

	private Date expired_date;

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	private List<KonkaoaFilesAuditPopedom> filesAuditPopedom;

	public KonkaoaFilesAuditAgentUser() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public String getAgent_user_name() {
		return agent_user_name;
	}

	public void setAgent_user_name(String agentUserName) {
		agent_user_name = agentUserName;
	}

	public void setAgent_user_id(Long agent_user_id) {
		this.agent_user_id = agent_user_id;
	}

	public Long getAgent_user_id() {
		return agent_user_id;
	}

	public Date getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(Date addDatetime) {
		add_datetime = addDatetime;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public List<KonkaoaFilesAuditPopedom> getFilesAuditPopedom() {
		return filesAuditPopedom;
	}

	public void setFilesAuditPopedom(List<KonkaoaFilesAuditPopedom> filesAuditPopedom) {
		this.filesAuditPopedom = filesAuditPopedom;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}