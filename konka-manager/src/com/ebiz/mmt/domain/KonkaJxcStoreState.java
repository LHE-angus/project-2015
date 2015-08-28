package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2011-10-15 16:57
 */
public class KonkaJxcStoreState extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long dept_id;

	private String dept_name;

	private Long store_id;

	private String store_name;

	private Long pd_type_id;

	private String pd_type_name;

	private Long brand_id;

	private String brand_name;

	private Long pd_id;

	private String pd_name;

	private BigDecimal pd_length;

	private BigDecimal pd_width;

	private BigDecimal pd_height;

	private String pd_unit;

	private BigDecimal pd_weight;

	private String pd_weight_unit;

	private Long pd_num;

	private Long pd_bad_num;

	private BigDecimal price;

	private BigDecimal cur_cost_price;

	private Integer is_del;

	private Date add_date;

	private Long add_user_id;

	private Date update_date;

	private Long update_user_id;

	private Date del_date;

	private Long del_user_id;

	private List<KonkaJxcStoreUpdateRecord> konkaJxcStoreUpdateRecordList;

	private BigDecimal price_ref;// 参考进货价（单价）

	private BigDecimal price_pf;// 批发价（单价）

	private BigDecimal price_ls;// 零售价（单价）

	private Long init_count;// 期初库存

	public Long getInit_count() {
		return init_count;
	}

	public void setInit_count(Long initCount) {
		init_count = initCount;
	}

	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	public void setPrice_ref(BigDecimal priceRef) {
		price_ref = priceRef;
	}

	public BigDecimal getPrice_pf() {
		return price_pf;
	}

	public void setPrice_pf(BigDecimal pricePf) {
		price_pf = pricePf;
	}

	public BigDecimal getPrice_ls() {
		return price_ls;
	}

	public void setPrice_ls(BigDecimal priceLs) {
		price_ls = priceLs;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String deptName) {
		dept_name = deptName;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String storeName) {
		store_name = storeName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCur_cost_price() {
		return cur_cost_price;
	}

	public void setCur_cost_price(BigDecimal curCostPrice) {
		cur_cost_price = curCostPrice;
	}

	public List<KonkaJxcStoreUpdateRecord> getKonkaJxcStoreUpdateRecordList() {
		return konkaJxcStoreUpdateRecordList;
	}

	public void setKonkaJxcStoreUpdateRecordList(List<KonkaJxcStoreUpdateRecord> konkaJxcStoreUpdateRecordList) {
		this.konkaJxcStoreUpdateRecordList = konkaJxcStoreUpdateRecordList;
	}

	public KonkaJxcStoreState() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public Long getDept_id() {
		return dept_id;
	}

	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public Long getPd_type_id() {
		return pd_type_id;
	}

	public void setPd_type_id(Long pd_type_id) {
		this.pd_type_id = pd_type_id;
	}

	public String getPd_type_name() {
		return pd_type_name;
	}

	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
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

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public BigDecimal getPd_length() {
		return pd_length;
	}

	public void setPd_length(BigDecimal pd_length) {
		this.pd_length = pd_length;
	}

	public BigDecimal getPd_width() {
		return pd_width;
	}

	public void setPd_width(BigDecimal pd_width) {
		this.pd_width = pd_width;
	}

	public BigDecimal getPd_height() {
		return pd_height;
	}

	public void setPd_height(BigDecimal pd_height) {
		this.pd_height = pd_height;
	}

	public String getPd_unit() {
		return pd_unit;
	}

	public void setPd_unit(String pd_unit) {
		this.pd_unit = pd_unit;
	}

	public BigDecimal getPd_weight() {
		return pd_weight;
	}

	public void setPd_weight(BigDecimal pd_weight) {
		this.pd_weight = pd_weight;
	}

	public String getPd_weight_unit() {
		return pd_weight_unit;
	}

	public void setPd_weight_unit(String pd_weight_unit) {
		this.pd_weight_unit = pd_weight_unit;
	}

	public Long getPd_num() {
		return pd_num;
	}

	public void setPd_num(Long pd_num) {
		this.pd_num = pd_num;
	}

	public Long getPd_bad_num() {
		return pd_bad_num;
	}

	public void setPd_bad_num(Long pd_bad_num) {
		this.pd_bad_num = pd_bad_num;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public Long getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public Date getDel_date() {
		return del_date;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public Long getDel_user_id() {
		return del_user_id;
	}

	public void setDel_user_id(Long del_user_id) {
		this.del_user_id = del_user_id;
	}

}