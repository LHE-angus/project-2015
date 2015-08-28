package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:24
 */
public class StatisticalDimensionMd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private BigDecimal id;
	
	private Long md_id;
	
	private String md_name;
	
	private String md_series;
	
	private Long par_brand_id;
	
	private String par_brand_name;
	
	private Long brand_id;
	
	private String brand_name;
	
	private Integer md_size;
	
	private Integer size_sec;
	
	private Integer is_konka;
	
	private Integer label_int;
	
	private Integer is_4k;
	
	private Integer is_db;
	
	private Integer is_ytv;
	
	private Integer is_del;
	
	public StatisticalDimensionMd() {

	}

	/**
	 * @val ID
	 */
	public BigDecimal getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	/**
	 * @val 产品ID
	 */
	public Long getMd_id() {
		return md_id;
	}
	
	/**
	 * @val 产品ID
	 */
	public void setMd_id(Long md_id) {
		this.md_id = md_id;
	}
	
	/**
	 * @val 产品名称
	 */
	public String getMd_name() {
		return md_name;
	}
	
	/**
	 * @val 产品名称
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}
	
	/**
	 * @val 系列
	 */
	public String getMd_series() {
		return md_series;
	}
	
	/**
	 * @val 系列
	 */
	public void setMd_series(String md_series) {
		this.md_series = md_series;
	}
	
	/**
	 * @val 产品品牌ID
	 */
	public Long getPar_brand_id() {
		return par_brand_id;
	}
	
	/**
	 * @val 产品品牌ID
	 */
	public void setPar_brand_id(Long par_brand_id) {
		this.par_brand_id = par_brand_id;
	}
	
	/**
	 * @val 产品品牌名称
	 */
	public String getPar_brand_name() {
		return par_brand_name;
	}
	
	/**
	 * @val 产品品牌名称
	 */
	public void setPar_brand_name(String par_brand_name) {
		this.par_brand_name = par_brand_name;
	}
	
	/**
	 * @val 产品子品牌ID
	 */
	public Long getBrand_id() {
		return brand_id;
	}
	
	/**
	 * @val 产品子品牌ID
	 */
	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}
	
	/**
	 * @val 产品子品牌名称
	 */
	public String getBrand_name() {
		return brand_name;
	}
	
	/**
	 * @val 产品子品牌名称
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	/**
	 * @val 产品尺寸
	 */
	public Integer getMd_size() {
		return md_size;
	}
	
	/**
	 * @val 产品尺寸
	 */
	public void setMd_size(Integer md_size) {
		this.md_size = md_size;
	}
	
	/**
	 * @val 尺寸段
	 */
	public Integer getSize_sec() {
		return size_sec;
	}
	
	/**
	 * @val 尺寸段
	 */
	public void setSize_sec(Integer size_sec) {
		this.size_sec = size_sec;
	}
	
	/**
	 * @val 我品/竞品标识
	 */
	public Integer getIs_konka() {
		return is_konka;
	}
	
	/**
	 * @val 我品/竞品标识
	 */
	public void setIs_konka(Integer is_konka) {
		this.is_konka = is_konka;
	}
	
	/**
	 * @val 是否智能
	 */
	public Integer getLabel_int() {
		return label_int;
	}
	
	/**
	 * @val 是否智能
	 */
	public void setLabel_int(Integer label_int) {
		this.label_int = label_int;
	}
	
	/**
	 * @val 是否4K
	 */
	public Integer getIs_4k() {
		return is_4k;
	}
	
	/**
	 * @val 是否4K
	 */
	public void setIs_4k(Integer is_4k) {
		this.is_4k = is_4k;
	}
	
	/**
	 * @val 是否大板
	 */
	public Integer getIs_db() {
		return is_db;
	}
	
	/**
	 * @val 是否大板
	 */
	public void setIs_db(Integer is_db) {
		this.is_db = is_db;
	}
	
	/**
	 * @val 是否易TV
	 */
	public Integer getIs_ytv() {
		return is_ytv;
	}
	
	/**
	 * @val 是否易TV
	 */
	public void setIs_ytv(Integer is_ytv) {
		this.is_ytv = is_ytv;
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