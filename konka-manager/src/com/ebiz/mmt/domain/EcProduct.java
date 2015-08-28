package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-11 15:01:36
 */
public class EcProduct extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String dept_sn;

	private String pd_name;

	private String pd_sn;

	private String pd_spec;

	private Integer own_sys;

	private Integer pd_size;

	private String pd_res;

	private String main_pic;

	private String pd_desc;

	private Integer label_3d;

	private Integer label_int;

	private Integer label_www;

	private Integer label_of_cate;

	private Long order_value;

	private Date add_date;

	private Integer state;

	private Integer label_4k;

	private Integer prod_type;

	private BigDecimal sj_weight;

	private BigDecimal p_weight;

	private String brand_name;

	private Long add_u_id;

	private List<KonkaBcompPdContent> konkaBcompPdContentList;

	public EcProduct() {

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
	 * @val 分公司编码，总部添加的商品为-1
	 */
	public String getDept_sn() {
		return dept_sn;
	}

	/**
	 * @val 分公司编码，总部添加的商品为-1
	 */
	public void setDept_sn(String dept_sn) {
		this.dept_sn = dept_sn;
	}

	/**
	 * @val 产品名称
	 */
	public String getPd_name() {
		return pd_name;
	}

	/**
	 * @val 产品名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	/**
	 * @val 产品编码
	 */
	public String getPd_sn() {
		return pd_sn;
	}

	/**
	 * @val 产品编码
	 */
	public void setPd_sn(String pd_sn) {
		this.pd_sn = pd_sn;
	}

	/**
	 * @val 规格型号
	 */
	public String getPd_spec() {
		return pd_spec;
	}

	/**
	 * @val 规格型号
	 */
	public void setPd_spec(String pd_spec) {
		this.pd_spec = pd_spec;
	}

	/**
	 * @val 所属系统：1-工卡，2-触网，3-会员
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统：1-工卡，2-触网，3-会员
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	/**
	 * @val 尺寸
	 */
	public Integer getPd_size() {
		return pd_size;
	}

	/**
	 * @val 尺寸
	 */
	public void setPd_size(Integer pd_size) {
		this.pd_size = pd_size;
	}

	/**
	 * @val 分辨率
	 */
	public String getPd_res() {
		return pd_res;
	}

	/**
	 * @val 分辨率
	 */
	public void setPd_res(String pd_res) {
		this.pd_res = pd_res;
	}

	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public String getMain_pic() {
		return main_pic;
	}

	/**
	 * @val 主图地址，多个主图地址用,隔开，第一个为主图
	 */
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	/**
	 * @val 产品描述
	 */
	public String getPd_desc() {
		return pd_desc;
	}

	/**
	 * @val 产品描述
	 */
	public void setPd_desc(String pd_desc) {
		this.pd_desc = pd_desc;
	}

	/**
	 * @val 功能分类-3D电视：0-否 1-是
	 */
	public Integer getLabel_3d() {
		return label_3d;
	}

	/**
	 * @val 功能分类-3D电视：0-否 1-是
	 */
	public void setLabel_3d(Integer label_3d) {
		this.label_3d = label_3d;
	}

	/**
	 * @val 功能分类-智能电视：0-否 1-是
	 */
	public Integer getLabel_int() {
		return label_int;
	}

	/**
	 * @val 功能分类-智能电视：0-否 1-是
	 */
	public void setLabel_int(Integer label_int) {
		this.label_int = label_int;
	}

	/**
	 * @val 功能分类-网络电视：0-否 1-是
	 */
	public Integer getLabel_www() {
		return label_www;
	}

	/**
	 * @val 功能分类-网络电视：0-否 1-是
	 */
	public void setLabel_www(Integer label_www) {
		this.label_www = label_www;
	}

	/**
	 * @val 分类标签：关联表【EC_BCOMP_TYPE】0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6-团购
	 */
	public Integer getLabel_of_cate() {
		return label_of_cate;
	}

	/**
	 * @val 分类标签：关联表【EC_BCOMP_TYPE】0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6-团购
	 */
	public void setLabel_of_cate(Integer label_of_cate) {
		this.label_of_cate = label_of_cate;
	}

	/**
	 * @val 排序值
	 */
	public Long getOrder_value() {
		return order_value;
	}

	/**
	 * @val 排序值
	 */
	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
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
	 * @val 状态：0-已停用 1-正常 -1-已删除
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 状态：0-已停用 1-正常 -1-已删除
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 4K
	 */
	public Integer getLabel_4k() {
		return label_4k;
	}

	/**
	 * @val 4K
	 */
	public void setLabel_4k(Integer label_4k) {
		this.label_4k = label_4k;
	}

	/**
	 * @val 产品类别
	 */
	public Integer getProd_type() {
		return prod_type;
	}

	/**
	 * @val 产品类别
	 */
	public void setProd_type(Integer prod_type) {
		this.prod_type = prod_type;
	}

	/**
	 * @val 实际重量
	 */
	public BigDecimal getSj_weight() {
		return sj_weight;
	}

	/**
	 * @val 实际重量
	 */
	public void setSj_weight(BigDecimal sj_weight) {
		this.sj_weight = sj_weight;
	}

	/**
	 * @val 计费重量
	 */
	public BigDecimal getP_weight() {
		return p_weight;
	}

	/**
	 * @val 计费重量
	 */
	public void setP_weight(BigDecimal p_weight) {
		this.p_weight = p_weight;
	}

	/**
	 * @val 品牌
	 */
	public String getBrand_name() {
		return brand_name;
	}

	/**
	 * @val 品牌
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	/**
	 * @val 添加人
	 */
	public Long getAdd_u_id() {
		return add_u_id;
	}

	/**
	 * @val 添加人
	 */
	public void setAdd_u_id(Long add_u_id) {
		this.add_u_id = add_u_id;
	}

	public List<KonkaBcompPdContent> getKonkaBcompPdContentList() {
		return konkaBcompPdContentList;
	}

	public void setKonkaBcompPdContentList(List<KonkaBcompPdContent> konkaBcompPdContentList) {
		this.konkaBcompPdContentList = konkaBcompPdContentList;
	}

}