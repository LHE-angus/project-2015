package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-31 10:42:07
 */
public class KonkaSpActivityBookReport extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long sp_id;
	
	private Long addr_id;
	
	private String size_section;
	
	private String model_name;
	
	private String comsumer_name;
	
	private String comsumer_phone;
	
	private Long num;
	
	private Integer prepay_state;
	
	private BigDecimal prepay_money;
	
	private Date add_date;
	
	private String add_user_name;
	
	private Integer state;
	
	private String memo;
	
	private Long add_user_id;
	
	private String sp_name;
	
	private String addr_name;
	
	private String r3_code;
	
	private Long store_id;
	
	private String store_name;
	
	public KonkaSpActivityBookReport() {

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
	 * @val 促销活动ID
	 */
	public Long getSp_id() {
		return sp_id;
	}
	
	/**
	 * @val 促销活动ID
	 */
	public void setSp_id(Long sp_id) {
		this.sp_id = sp_id;
	}
	
	/**
	 * @val 促销点ID
	 */
	public Long getAddr_id() {
		return addr_id;
	}
	
	/**
	 * @val 促销点ID
	 */
	public void setAddr_id(Long addr_id) {
		this.addr_id = addr_id;
	}
	
	/**
	 * @val 预约尺寸段
	 */
	public String getSize_section() {
		return size_section;
	}
	
	/**
	 * @val 预约尺寸段
	 */
	public void setSize_section(String size_section) {
		this.size_section = size_section;
	}
	
	/**
	 * @val 预约型号
	 */
	public String getModel_name() {
		return model_name;
	}
	
	/**
	 * @val 预约型号
	 */
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	
	/**
	 * @val 消费者姓名
	 */
	public String getComsumer_name() {
		return comsumer_name;
	}
	
	/**
	 * @val 消费者姓名
	 */
	public void setComsumer_name(String comsumer_name) {
		this.comsumer_name = comsumer_name;
	}
	
	/**
	 * @val 消费者电话
	 */
	public String getComsumer_phone() {
		return comsumer_phone;
	}
	
	/**
	 * @val 消费者电话
	 */
	public void setComsumer_phone(String comsumer_phone) {
		this.comsumer_phone = comsumer_phone;
	}
	
	/**
	 * @val 预约数量
	 */
	public Long getNum() {
		return num;
	}
	
	/**
	 * @val 预约数量
	 */
	public void setNum(Long num) {
		this.num = num;
	}
	
	/**
	 * @val 是否支付定金
	 */
	public Integer getPrepay_state() {
		return prepay_state;
	}
	
	/**
	 * @val 是否支付定金
	 */
	public void setPrepay_state(Integer prepay_state) {
		this.prepay_state = prepay_state;
	}
	
	/**
	 * @val 支付定金额
	 */
	public BigDecimal getPrepay_money() {
		return prepay_money;
	}
	
	/**
	 * @val 支付定金额
	 */
	public void setPrepay_money(BigDecimal prepay_money) {
		this.prepay_money = prepay_money;
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
	 * @val 添加人姓名
	 */
	public String getAdd_user_name() {
		return add_user_name;
	}
	
	/**
	 * @val 添加人姓名
	 */
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	
	/**
	 * @val 状态
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @val 状态
	 */
	public void setState(Integer state) {
		this.state = state;
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
	 * @val 添加人ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加人ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 活动标题
	 */
	public String getSp_name() {
		return sp_name;
	}
	
	/**
	 * @val 活动标题
	 */
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}
	
	/**
	 * @val 地点标题
	 */
	public String getAddr_name() {
		return addr_name;
	}
	
	/**
	 * @val 地点标题
	 */
	public void setAddr_name(String addr_name) {
		this.addr_name = addr_name;
	}
	
	/**
	 * @val 客户编码
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val 客户编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	/**
	 * @val 门店ID
	 */
	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	/**
	 * @val 门店ID
	 */
	public Long getStore_id() {
		return store_id;
	}
	/**
	 * @val 门店名称
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	/**
	 * @val 门店名称
	 */
	public String getStore_name() {
		return store_name;
	}

}