package com.ebiz.mmt.domain;

import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,Huan
 */
public class BasePdType extends BaseDomain {

	private static final long serialVersionUID = -521249243895354627L;

	private Long pd_type;

	private String pd_name;

	private String pd_type_sign;

	private Integer order_sort;

	private Short del_mark;

	private Date add_date;

	private Date del_date;

	private Short is_model;

	private String brand_name;

	private List<TopicOnsalePd> topicOnsalePdList;

	private List<BasePdType> basePdTypeList;

	private List<BaseBrand> baseBrandList;

	private List<BaseBrandInfo> baseBrandInfoList;

	private List<ShopPd> shopPdList;
	
	private List<MvPdTypeJoinBrand> mvPdTypeJoinBrandList;

	public BasePdType() {

	}

	public BasePdType(Long pd_type) {
		this.pd_type = pd_type;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public String getPd_type_sign() {
		return pd_type_sign;
	}

	public void setPd_type_sign(String pd_type_sign) {
		this.pd_type_sign = pd_type_sign;
	}

	public Integer getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(Integer order_sort) {
		this.order_sort = order_sort;
	}

	public Short getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Short del_mark) {
		this.del_mark = del_mark;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Short getIs_model() {
		return is_model;
	}

	public void setIs_model(Short is_model) {
		this.is_model = is_model;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public List<TopicOnsalePd> getTopicOnsalePdList() {
		return topicOnsalePdList;
	}

	public void setTopicOnsalePdList(List<TopicOnsalePd> topicOnsalePdList) {
		this.topicOnsalePdList = topicOnsalePdList;
	}

	public List<BasePdType> getBasePdTypeList() {
		return basePdTypeList;
	}

	public void setBasePdTypeList(List<BasePdType> basePdTypeList) {
		this.basePdTypeList = basePdTypeList;
	}

	public List<BaseBrand> getBaseBrandList() {
		return baseBrandList;
	}

	public void setBaseBrandList(List<BaseBrand> baseBrandList) {
		this.baseBrandList = baseBrandList;
	}

	public List<ShopPd> getShopPdList() {
		return shopPdList;
	}

	public void setShopPdList(List<ShopPd> shopPdList) {
		this.shopPdList = shopPdList;
	}

	public List<BaseBrandInfo> getBaseBrandInfoList() {
		return baseBrandInfoList;
	}

	public void setBaseBrandInfoList(List<BaseBrandInfo> baseBrandInfoList) {
		this.baseBrandInfoList = baseBrandInfoList;
	}

	public List<MvPdTypeJoinBrand> getMvPdTypeJoinBrandList() {
		return mvPdTypeJoinBrandList;
	}

	public void setMvPdTypeJoinBrandList(List<MvPdTypeJoinBrand> mvPdTypeJoinBrandList) {
		this.mvPdTypeJoinBrandList = mvPdTypeJoinBrandList;
	}
}