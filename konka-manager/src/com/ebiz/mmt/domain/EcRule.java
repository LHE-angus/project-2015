package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
public class EcRule extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer rule_type;
	
	private String rule_title;
	
	private String rule_msg;
	
	private BigDecimal rule_price;
	
	private Integer rule_num_min;
	
	private Integer rule_num_max;
	
	private String rule_area_limit;
	
	private String rule_area_allow;
	
	private Integer is_num;
	
	private Integer is_area_limit;
	
	private Integer is_area_allow;
	
	private Integer is_price;
	
	private String rule_gooods;
	
	private Date rule_start_date;
	
	private Date rule_end_date;
	
	private String memo;
	
	private Long dept_id;
	
	private Integer own_sys;
	
	private Integer info_state;
	
	private Integer is_del;
	
	private Date add_date;
	
	private Long add_user_id;
	
	public EcRule() {

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
	 * @val 类别(1:限购数量,2限购地区,3优惠费用,4增加费用,9组合套餐)
	 */
	public Integer getRule_type() {
		return rule_type;
	}
	
	/**
	 * @val 类别(1:限购数量,2限购地区,3优惠费用,4增加费用,9组合套餐)
	 */
	public void setRule_type(Integer rule_type) {
		this.rule_type = rule_type;
	}
	
	/**
	 * @val 规则名称
	 */
	public String getRule_title() {
		return rule_title;
	}
	
	/**
	 * @val 规则名称
	 */
	public void setRule_title(String rule_title) {
		this.rule_title = rule_title;
	}
	
	/**
	 * @val 规则提示消息,规则有效期内在产品展示页面显示
	 */
	public String getRule_msg() {
		return rule_msg;
	}
	
	/**
	 * @val 规则提示消息,规则有效期内在产品展示页面显示
	 */
	public void setRule_msg(String rule_msg) {
		this.rule_msg = rule_msg;
	}
	
	/**
	 * @val 规则增加或减少费用
	 */
	public BigDecimal getRule_price() {
		return rule_price;
	}
	
	/**
	 * @val 规则增加或减少费用
	 */
	public void setRule_price(BigDecimal rule_price) {
		this.rule_price = rule_price;
	}
	
	/**
	 * @val 最小购买数量
	 */
	public Integer getRule_num_min() {
		return rule_num_min;
	}
	
	/**
	 * @val 最小购买数量
	 */
	public void setRule_num_min(Integer rule_num_min) {
		this.rule_num_min = rule_num_min;
	}
	
	/**
	 * @val 最大购买数量
	 */
	public Integer getRule_num_max() {
		return rule_num_max;
	}
	
	/**
	 * @val 最大购买数量
	 */
	public void setRule_num_max(Integer rule_num_max) {
		this.rule_num_max = rule_num_max;
	}
	
	/**
	 * @val 限制购买区域
	 */
	public String getRule_area_limit() {
		return rule_area_limit;
	}
	
	/**
	 * @val 限制购买区域
	 */
	public void setRule_area_limit(String rule_area_limit) {
		this.rule_area_limit = rule_area_limit;
	}
	
	/**
	 * @val 允许购买区域
	 */
	public String getRule_area_allow() {
		return rule_area_allow;
	}
	
	/**
	 * @val 允许购买区域
	 */
	public void setRule_area_allow(String rule_area_allow) {
		this.rule_area_allow = rule_area_allow;
	}
	
	/**
	 * @val 是否启用限制购买数量
	 */
	public Integer getIs_num() {
		return is_num;
	}
	
	/**
	 * @val 是否启用限制购买数量
	 */
	public void setIs_num(Integer is_num) {
		this.is_num = is_num;
	}
	
	/**
	 * @val 是否启用限制购买区域
	 */
	public Integer getIs_area_limit() {
		return is_area_limit;
	}
	
	/**
	 * @val 是否启用限制购买区域
	 */
	public void setIs_area_limit(Integer is_area_limit) {
		this.is_area_limit = is_area_limit;
	}
	
	/**
	 * @val 是否启用允许购买区域
	 */
	public Integer getIs_area_allow() {
		return is_area_allow;
	}
	
	/**
	 * @val 是否启用允许购买区域
	 */
	public void setIs_area_allow(Integer is_area_allow) {
		this.is_area_allow = is_area_allow;
	}
	
	/**
	 * @val 是否启用规则费用
	 */
	public Integer getIs_price() {
		return is_price;
	}
	
	/**
	 * @val 是否启用规则费用
	 */
	public void setIs_price(Integer is_price) {
		this.is_price = is_price;
	}
	
	/**
	 * @val 组合商品
	 */
	public String getRule_gooods() {
		return rule_gooods;
	}
	
	/**
	 * @val 组合商品
	 */
	public void setRule_gooods(String rule_gooods) {
		this.rule_gooods = rule_gooods;
	}
	
	/**
	 * @val 有效开始日期
	 */
	public Date getRule_start_date() {
		return rule_start_date;
	}
	
	/**
	 * @val 有效开始日期
	 */
	public void setRule_start_date(Date rule_start_date) {
		this.rule_start_date = rule_start_date;
	}
	
	/**
	 * @val 有效截止日期
	 */
	public Date getRule_end_date() {
		return rule_end_date;
	}
	
	/**
	 * @val 有效截止日期
	 */
	public void setRule_end_date(Date rule_end_date) {
		this.rule_end_date = rule_end_date;
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
	
	/**
	 * @val 分公司
	 */
	public Long getDept_id() {
		return dept_id;
	}
	
	/**
	 * @val 分公司
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * @val 所属系统：1工卡、2触网
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统：1工卡、2触网
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val 状态0：未启用,1：已启用
	 */
	public Integer getInfo_state() {
		return info_state;
	}
	
	/**
	 * @val 状态0：未启用,1：已启用
	 */
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
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