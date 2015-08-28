package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 * @author Xing,XiuDong: add brand_name_cn,brand_name_en,is_lock,is_del
 */
public class BaseBrandInfo extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long brand_id;

	private String brand_name;

	private String brand_logo;

	private String brand_name_cn;

	private String brand_name_en;

	private Integer order_sort;

	private Integer is_lock;

	private Integer is_del;

	private Date birthday;

	private Long sum_have;

	private Long sum_want;

	private Long sum_scan;

	private Long own_sys;

	private Long std_brand_id;

	private String brand_sn;

	private List<MvPdTypeJoinBrand> mvPdTypeJoinBrandList;

	public BaseBrandInfo() {

	}

	public BaseBrandInfo(Long brand_id) {
		this.brand_id = brand_id;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrand_logo() {
		return brand_logo;
	}

	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}

	public Integer getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(Integer order_sort) {
		this.order_sort = order_sort;
	}

	public String getBrand_name_cn() {
		return brand_name_cn;
	}

	public void setBrand_name_cn(String brandNameCn) {
		brand_name_cn = brandNameCn;
	}

	public String getBrand_name_en() {
		return brand_name_en;
	}

	public void setBrand_name_en(String brandNameEn) {
		brand_name_en = brandNameEn;
	}

	public Integer getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(Integer isLock) {
		is_lock = isLock;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer isDel) {
		is_del = isDel;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getSum_have() {
		return sum_have;
	}

	public void setSum_have(Long sumHave) {
		sum_have = sumHave;
	}

	public Long getSum_want() {
		return sum_want;
	}

	public void setSum_want(Long sumWant) {
		sum_want = sumWant;
	}

	public Long getSum_scan() {
		return sum_scan;
	}

	public void setSum_scan(Long sumScan) {
		sum_scan = sumScan;
	}

	public Long getOwn_sys() {
		return own_sys;
	}

	public void setOwn_sys(Long ownSys) {
		own_sys = ownSys;
	}

	public Long getStd_brand_id() {
		return std_brand_id;
	}

	public void setStd_brand_id(Long stdBrandId) {
		std_brand_id = stdBrandId;
	}

	public String getBrand_sn() {
		return brand_sn;
	}

	public void setBrand_sn(String brand_sn) {
		this.brand_sn = brand_sn;
	}

	public List<MvPdTypeJoinBrand> getMvPdTypeJoinBrandList() {
		return mvPdTypeJoinBrandList;
	}

	public void setMvPdTypeJoinBrandList(List<MvPdTypeJoinBrand> mvPdTypeJoinBrandList) {
		this.mvPdTypeJoinBrandList = mvPdTypeJoinBrandList;
	}

}