package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-19 10:20:08
 */
public class PeShop extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long entp_id;

	private Long brand_id;

	private Long shop_id;

	private Long p_index;

	private Long category_id;

	private String category_name;

	private Long leader;

	private Long cls_id;

	private String remarks;

	private Integer state;

	private Integer order_value;

	private Long add_e_user_id;

	private Date add_date;

	private Integer is_del;

	public PeShop() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 生产企业ID:ENTP_PROD.ENTP_ID
	 */
	public Long getEntp_id() {
		return entp_id;
	}

	/**
	 * @val 生产企业ID:ENTP_PROD.ENTP_ID
	 */
	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	/**
	 * @val 生产企业主 营品牌：BASE_BRAND_INFO.BRAND_ID=ENTP_PROD.BRAND_ID
	 */
	public Long getBrand_id() {
		return brand_id;
	}

	/**
	 * @val 生产企业主 营品牌：BASE_BRAND_INFO.BRAND_ID=ENTP_PROD.BRAND_ID
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	/**
	 * @val 网点ID：ENTP_SHOP.SHOP_ID
	 */
	public Long getShop_id() {
		return shop_id;
	}

	/**
	 * @val 网点ID：ENTP_SHOP.SHOP_ID
	 */
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	/**
	 * @val 网点所在地
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 网点所在地
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}

	/**
	 * @val 网点类别ID
	 */
	public Long getCategory_id() {
		return category_id;
	}

	/**
	 * @val 网点类别ID
	 */
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	/**
	 * @val 网点类别名称
	 */
	public String getCategory_name() {
		return category_name;
	}

	/**
	 * @val 网点类别名称
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	/**
	 * @val 网点负责人
	 */
	public Long getLeader() {
		return leader;
	}

	/**
	 * @val 网点负责人
	 */
	public void setLeader(Long leader) {
		this.leader = leader;
	}

	/**
	 * @val 网点负责人负责品类：BASE_PD_CLASS.CLS_ID
	 */
	public Long getCls_id() {
		return cls_id;
	}

	/**
	 * @val 网点负责人负责品类：BASE_PD_CLASS.CLS_ID
	 */
	public void setCls_id(Long cls_id) {
		this.cls_id = cls_id;
	}

	/**
	 * @val 说明
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @val 说明
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @val 网点状态：-2、已淘汰的网点；-1、计划开拓网点；0、默认；1、自己的网点；2、计划淘汰网点
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 网点状态：-2、已淘汰的网点；-1、计划开拓网点；0、默认；1、自己的网点；2、计划淘汰网点
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 排序值
	 */
	public Integer getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序值
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public Long getAdd_e_user_id() {
		return add_e_user_id;
	}

	/**
	 * @val 企业添加人：PE_PROD_USER.ID
	 */
	public void setAdd_e_user_id(Long add_e_user_id) {
		this.add_e_user_id = add_e_user_id;
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
	 * @val 是否删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

}