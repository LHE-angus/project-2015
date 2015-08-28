package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Liu,Huan
 */
public class PdModel extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long pd_id;

	private Long pd_type;

	private Long brand_id;

	private Long series_id;

	private String series_name;

	private Long entp_id;

	private String md_name;

	private BigDecimal price_ref;

	private BigDecimal price_limit;

	private Integer order_sort;

	private String main_pic;

	private String pd_remark;

	private Date in_date;

	private Date out_date;

	private Integer del_mark;

	private Date add_date;

	private Date del_date;

	private String pm_sign;

	private Integer pd_ext_type;

	private Short score;

	private String pd_benefit;

	private String pd_defect;

	private Integer inuse_sum;

	private Integer will_sum;

	private Integer ever_sum;

	private String content;

	private Integer is_country;

	private Integer is_low_energy;

	private BigDecimal min_price;

	private BigDecimal max_price;

	private Integer shop_count;

	private String pd_desc;

	private Long pd_big_type;

	public Long getPd_big_type() {
		return pd_big_type;
	}

	public void setPd_big_type(Long pdBigType) {
		pd_big_type = pdBigType;
	}

	private StdEntpProd stdEntpProd;

	List<PdPicture> pdPictureList;

	List<PdModelAttInstr> pdModelAttInstrList;

	public PdModel(Long pd_id) {
		this.pd_id = pd_id;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public String getPd_benefit() {
		return pd_benefit;
	}

	public void setPd_benefit(String pd_benefit) {
		this.pd_benefit = pd_benefit;
	}

	public String getPd_defect() {
		return pd_defect;
	}

	public void setPd_defect(String pd_defect) {
		this.pd_defect = pd_defect;
	}

	public PdModel() {

	}

	public Long getPd_id() {
		return pd_id;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public Long getSeries_id() {
		return series_id;
	}

	public void setSeries_id(Long seriesId) {
		series_id = seriesId;
	}

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String seriesName) {
		series_name = seriesName;
	}

	public Long getEntp_id() {
		return entp_id;
	}

	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	public String getMd_name() {
		return md_name;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public BigDecimal getPrice_ref() {
		return price_ref;
	}

	public void setPrice_ref(BigDecimal price_ref) {
		this.price_ref = price_ref;
	}

	public BigDecimal getPrice_limit() {
		return price_limit;
	}

	public void setPrice_limit(BigDecimal price_limit) {
		this.price_limit = price_limit;
	}

	public Integer getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(Integer order_sort) {
		this.order_sort = order_sort;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	public String getPd_remark() {
		return pd_remark;
	}

	public void setPd_remark(String pd_remark) {
		this.pd_remark = pd_remark;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	public Integer getDel_mark() {
		return del_mark;
	}

	public void setDel_mark(Integer del_mark) {
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

	public String getPm_sign() {
		return pm_sign;
	}

	public void setPm_sign(String pm_sign) {
		this.pm_sign = pm_sign;
	}

	public Integer getPd_ext_type() {
		return pd_ext_type;
	}

	public void setPd_ext_type(Integer pd_ext_type) {
		this.pd_ext_type = pd_ext_type;
	}

	public Integer getInuse_sum() {
		return inuse_sum;
	}

	public void setInuse_sum(Integer inuse_sum) {
		this.inuse_sum = inuse_sum;
	}

	public Integer getWill_sum() {
		return will_sum;
	}

	public void setWill_sum(Integer will_sum) {
		this.will_sum = will_sum;
	}

	public Integer getEver_sum() {
		return ever_sum;
	}

	public void setEver_sum(Integer ever_sum) {
		this.ever_sum = ever_sum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIs_country() {
		return is_country;
	}

	public void setIs_country(Integer isCountry) {
		is_country = isCountry;
	}

	public Integer getIs_low_energy() {
		return is_low_energy;
	}

	public void setIs_low_energy(Integer isLowEnergy) {
		is_low_energy = isLowEnergy;
	}

	public BigDecimal getMin_price() {
		return min_price;
	}

	public void setMin_price(BigDecimal minPrice) {
		min_price = minPrice;
	}

	public BigDecimal getMax_price() {
		return max_price;
	}

	public void setMax_price(BigDecimal maxPrice) {
		max_price = maxPrice;
	}

	public Integer getShop_count() {
		return shop_count;
	}

	public void setShop_count(Integer shopCount) {
		shop_count = shopCount;
	}

	public String getPd_desc() {
		return pd_desc;
	}

	public void setPd_desc(String pdDesc) {
		pd_desc = pdDesc;
	}

	public List<PdPicture> getPdPictureList() {
		return pdPictureList;
	}

	public void setPdPictureList(List<PdPicture> pdPictureList) {
		this.pdPictureList = pdPictureList;
	}

	public List<PdModelAttInstr> getPdModelAttInstrList() {
		return pdModelAttInstrList;
	}

	public void setPdModelAttInstrList(List<PdModelAttInstr> pdModelAttInstrList) {
		this.pdModelAttInstrList = pdModelAttInstrList;
	}

	public StdEntpProd getStdEntpProd() {
		return stdEntpProd;
	}

	public void setStdEntpProd(StdEntpProd stdEntpProd) {
		this.stdEntpProd = stdEntpProd;
	}

}