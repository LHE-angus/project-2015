package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;


public class AreaFightInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long p_index;
	
	private String area_name;
	
	private Integer t_type;
	
	private Integer t_num;
	
	private Long gdp;
	
	private Long human_num;
	
	private Long area_size;
	
	private Long market_money;
	
	private Long market_num;
	
	private Integer jd_in;
	
	private Integer konka_in;
	
	private Integer konka_rank;
	
	private Integer market_rate;
	
	private String first_comp;
	
	private String second_comp;
	
	private String third_comp;
	
	private String not_in_reason;
	
	private Date in_date;
	
	private Integer t_status;
	
	private Long modify_user_id;
	
	private Date modify_date;
	
	private Long dept_id;
	
	public AreaFightInfo() {

	}

	/**
	 * @val 主键
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @val 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 乡镇街编码
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 乡镇街编码
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 区域名称
	 */
	public String getArea_name() {
		return area_name;
	}
	
	/**
	 * @val 区域名称
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	/**
	 * @val 类型：1-乡镇，2-街道，3-开发区
	 */
	public Integer getT_type() {
		return t_type;
	}
	
	/**
	 * @val 类型：1-乡镇，2-街道，3-开发区
	 */
	public void setT_type(Integer t_type) {
		this.t_type = t_type;
	}
	
	/**
	 * @val 所辖村数量
	 */
	public Integer getT_num() {
		return t_num;
	}
	
	/**
	 * @val 所辖村数量
	 */
	public void setT_num(Integer t_num) {
		this.t_num = t_num;
	}
	
	/**
	 * @val GDP，单位：万元
	 */
	public Long getGdp() {
		return gdp;
	}
	
	/**
	 * @val GDP，单位：万元
	 */
	public void setGdp(Long gdp) {
		this.gdp = gdp;
	}
	
	/**
	 * @val 人口，单位：万人
	 */
	public Long getHuman_num() {
		return human_num;
	}
	
	/**
	 * @val 人口，单位：万人
	 */
	public void setHuman_num(Long human_num) {
		this.human_num = human_num;
	}
	
	/**
	 * @val 面积，单位：平方公里
	 */
	public Long getArea_size() {
		return area_size;
	}
	
	/**
	 * @val 面积，单位：平方公里
	 */
	public void setArea_size(Long area_size) {
		this.area_size = area_size;
	}
	
	/**
	 * @val 彩电市场容量（金额），单位：万元
	 */
	public Long getMarket_money() {
		return market_money;
	}
	
	/**
	 * @val 彩电市场容量（金额），单位：万元
	 */
	public void setMarket_money(Long market_money) {
		this.market_money = market_money;
	}
	
	/**
	 * @val 彩电市场容量（量），单位：台
	 */
	public Long getMarket_num() {
		return market_num;
	}
	
	/**
	 * @val 彩电市场容量（量），单位：台
	 */
	public void setMarket_num(Long market_num) {
		this.market_num = market_num;
	}
	
	/**
	 * @val 家电入驻：0-是，1-否
	 */
	public Integer getJd_in() {
		return jd_in;
	}
	
	/**
	 * @val 家电入驻：0-是，1-否
	 */
	public void setJd_in(Integer jd_in) {
		this.jd_in = jd_in;
	}
	
	/**
	 * @val 康佳入驻：0-是，1-否
	 */
	public Integer getKonka_in() {
		return konka_in;
	}
	
	/**
	 * @val 康佳入驻：0-是，1-否
	 */
	public void setKonka_in(Integer konka_in) {
		this.konka_in = konka_in;
	}
	
	/**
	 * @val 康佳排名
	 */
	public Integer getKonka_rank() {
		return konka_rank;
	}
	
	/**
	 * @val 康佳排名
	 */
	public void setKonka_rank(Integer konka_rank) {
		this.konka_rank = konka_rank;
	}
	
	/**
	 * @val 市场占有率：单位：%
	 */
	public Integer getMarket_rate() {
		return market_rate;
	}
	
	/**
	 * @val 市场占有率：单位：%
	 */
	public void setMarket_rate(Integer market_rate) {
		this.market_rate = market_rate;
	}
	
	/**
	 * @val 第一对手名称
	 */
	public String getFirst_comp() {
		return first_comp;
	}
	
	/**
	 * @val 第一对手名称
	 */
	public void setFirst_comp(String first_comp) {
		this.first_comp = first_comp;
	}
	
	/**
	 * @val 第二对手名称
	 */
	public String getSecond_comp() {
		return second_comp;
	}
	
	/**
	 * @val 第二对手名称
	 */
	public void setSecond_comp(String second_comp) {
		this.second_comp = second_comp;
	}
	
	/**
	 * @val 第三对手名称
	 */
	public String getThird_comp() {
		return third_comp;
	}
	
	/**
	 * @val 第三对手名称
	 */
	public void setThird_comp(String third_comp) {
		this.third_comp = third_comp;
	}
	
	/**
	 * @val 康佳未进入原因
	 */
	public String getNot_in_reason() {
		return not_in_reason;
	}
	
	/**
	 * @val 康佳未进入原因
	 */
	public void setNot_in_reason(String not_in_reason) {
		this.not_in_reason = not_in_reason;
	}
	
	/**
	 * @val 预计进入日期
	 */
	public Date getIn_date() {
		return in_date;
	}
	
	/**
	 * @val 预计进入日期
	 */
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	
	/**
	 * @val 乡镇状态：0-正常，1-撤销
	 */
	public Integer getT_status() {
		return t_status;
	}
	
	/**
	 * @val 乡镇状态：0-正常，1-撤销
	 */
	public void setT_status(Integer t_status) {
		this.t_status = t_status;
	}
	
	/**
	 * @val 最后维护人ID
	 */
	public Long getModify_user_id() {
		return modify_user_id;
	}
	
	/**
	 * @val 最后维护人ID
	 */
	public void setModify_user_id(Long modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	
	/**
	 * @val 最后维护日期
	 */
	public Date getModify_date() {
		return modify_date;
	}
	
	/**
	 * @val 最后维护日期
	 */
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}
	
}