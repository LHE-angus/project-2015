package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
public class PeModPopedom extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private Long mod_id;
	
	private Long p_index;
	
	private Long cls_id;
	
	private Long brand_id;
	
	private Date begin_date;
	
	private Date end_date;
	
	private String popedom;
	
	private Long entp_id;
	
	private Long add_user_id;
	
	private Long add_e_user_id;
	
	private Date add_date;
	
	public PeModPopedom() {

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
	 * @val 用户ID：PE_PROD_USER.ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 用户ID：PE_PROD_USER.ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 模块ID：PE_MODULE.MOD_ID
	 */
	public Long getMod_id() {
		return mod_id;
	}
	
	/**
	 * @val 模块ID：PE_MODULE.MOD_ID
	 */
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
	}
	
	/**
	 * @val 地区ID：BASE_PROVINCE_LIST_FOUR.P_INDEX
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 地区ID：BASE_PROVINCE_LIST_FOUR.P_INDEX
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 品类ID：BASE_PD_TYPE.PD_TYPE(IS_MODEL=1)
	 */
	public Long getCls_id() {
		return cls_id;
	}

	public void setCls_id(Long clsId) {
		cls_id = clsId;
	}

	/**
	 * @val 品牌ID：BASE_BRAND_INFO.BRAND_ID
	 */
	public Long getBrand_id() {
		return brand_id;
	}
	
	/**
	 * @val 品牌ID：BASE_BRAND_INFO.BRAND_ID
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
	/**
	 * @val 开始时间
	 */
	public Date getBegin_date() {
		return begin_date;
	}
	
	/**
	 * @val 开始时间
	 */
	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}
	
	/**
	 * @val 结束时间
	 */
	public Date getEnd_date() {
		return end_date;
	}
	
	/**
	 * @val 结束时间
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	/**
	 * @val 权限分为：查看(0)，添加(1)，修改(2)，删除(3)
	 * @val 以逗号分隔的字符串，如“1,2,3,4”
	 */
	public String getPopedom() {
		return popedom;
	}
	
	/**
	 * @val 权限分为：查看(0)，添加(1)，修改(2)，删除(3)
	 * @val 以逗号分隔的字符串，如“1,2,3,4”
	 */
	public void setPopedom(String popedom) {
		this.popedom = popedom;
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
	 * @val 添加用户ID（MMT）：USER_INFO.ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加用户ID（MMT）：USER_INFO.ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
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
	 * @val 授权时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 授权时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}