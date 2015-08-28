package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
public class EcRuleGoods extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long goods_id;
	
	private Long rule_id;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public EcRuleGoods() {

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
	 * @val 商品ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 规则ID
	 */
	public Long getRule_id() {
		return rule_id;
	}
	
	/**
	 * @val 规则ID
	 */
	public void setRule_id(Long rule_id) {
		this.rule_id = rule_id;
	}
	
	/**
	 * @val 默认值0,0未删除,1已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 默认值0,0未删除,1已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
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
	
}