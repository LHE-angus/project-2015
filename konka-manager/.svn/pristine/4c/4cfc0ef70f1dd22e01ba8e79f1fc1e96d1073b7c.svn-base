package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:36
 */
public class JxcPd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long shop_id;

	private Long shop_p_index;

	private String name;

	private Long brand_id;

	private String brand_name;

	private Long pd_type;

	private String pd_type_name;

	private String unit;

	private Long init_count;

	private Long count;

	private Long min_count;

	private BigDecimal ref_price;

	private BigDecimal pf_price;

	private BigDecimal price;

	private String remarks;

	private Long add_user_id;

	private Date add_date;

	private Integer is_del;

	private Integer own_sys;

	private Long out_sys_id;

	private JxcPd qcjc_pd;

	private JxcPd rcjh_pd;

	private JxcPd rcch_pd;

	private JxcPd rcjh_rcch_pd;

	private BigDecimal init_cost_price;

	private BigDecimal cur_cost_price;

	private Long jxc_pd_type_id;

	private Double rcjh_money; // 库存管理：日常进货金额

	private Double rcch_money; // 库存管理：日常出货金额

	private Double qmjc_count; // 库存管理：期末结存数量

	private Double qmjc_price; // 库存管理：期末结存单价

	private Double qmjc_money; // 库存管理：期末结存金额
	
	/**
	 * 
	 */
	private Double pc_money; // 盘存金额

	public JxcPd() {

	}

	public Double getPc_money() {
		return pc_money;
	}

	public void setPc_money(Double pcMoney) {
		pc_money = pcMoney;
	}

	/**
	 * @val 产品型号ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 产品型号ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 门店商铺ID
	 */
	public Long getShop_id() {
		return shop_id;
	}

	/**
	 * @val 门店商铺ID
	 */
	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	/**
	 * @val 门店所在地区
	 */
	public Long getShop_p_index() {
		return shop_p_index;
	}

	/**
	 * @val 门店所在地区
	 */
	public void setShop_p_index(Long shop_p_index) {
		this.shop_p_index = shop_p_index;
	}

	/**
	 * @val 型号名称：特别注意--同一个门店商铺里产品型号名称必须唯一！
	 */
	public String getName() {
		return name;
	}

	/**
	 * @val 型号名称：特别注意--同一个门店商铺里产品型号名称必须唯一！
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @val 品牌名称
	 */
	public String getBrand_name() {
		return brand_name;
	}

	/**
	 * @val 品牌名称
	 */
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	/**
	 * @val 产品类型（大类）：BASE_PD_TYPE(IS_MODEL=1).PD_TYPE，0表示其他
	 */
	public Long getPd_type() {
		return pd_type;
	}

	/**
	 * @val 产品类型（大类）：BASE_PD_TYPE(IS_MODEL=1).PD_TYPE，0表示其他
	 */
	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	/**
	 * @val 产品类型（大类）名称，当BASE_PD_TYPE.PD_TYPE=0时取JXC_PD_TYPE.NAME
	 */
	public String getPd_type_name() {
		return pd_type_name;
	}

	/**
	 * @val 产品类型（大类）名称，当BASE_PD_TYPE.PD_TYPE=0时取JXC_PD_TYPE.NAME
	 */
	public void setPd_type_name(String pd_type_name) {
		this.pd_type_name = pd_type_name;
	}

	/**
	 * @val 计量单位：如 台、件、套等
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @val 计量单位：如 台、件、套等
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @val 当前库存数：实时库存数，不可人为的修改，该字段对操作员只读。
	 * @val
	 * @val 1、操作进货单时增加库存
	 * @val 2、销售产品时减小库存
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @val 当前库存数：实时库存数，不可人为的修改，该字段对操作员只读。
	 * @val
	 * @val 1、操作进货单时增加库存
	 * @val 2、销售产品时减小库存
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @val 最小库存/报警
	 */
	public Long getMin_count() {
		return min_count;
	}

	/**
	 * @val 最小库存/报警
	 */
	public void setMin_count(Long min_count) {
		this.min_count = min_count;
	}

	/**
	 * @val 参考进货价（单价）
	 */
	public BigDecimal getRef_price() {
		return ref_price;
	}

	/**
	 * @val 参考进货价（单价）
	 */
	public void setRef_price(BigDecimal ref_price) {
		this.ref_price = ref_price;
	}

	/**
	 * @val 批发价（单价）
	 */
	public BigDecimal getPf_price() {
		return pf_price;
	}

	/**
	 * @val 批发价（单价）
	 */
	public void setPf_price(BigDecimal pf_price) {
		this.pf_price = pf_price;
	}

	/**
	 * @val 零售价（单价）
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @val 零售价（单价）
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @val 备注说明
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @val 备注说明
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @val 添加人用户ID=USER_INFO.ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}

	/**
	 * @val 添加人用户ID=USER_INFO.ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}

	/**
	 * @val 录入时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 录入时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 是否删除：0-未删，1-已删
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除：0-未删，1-已删
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 所属系统：0-默认买卖提，1-家电下乡，2-以旧换新
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}

	/**
	 * @val 所属系统：0-默认买卖提，1-家电下乡，2-以旧换新
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	/*
	 * @val 外部记录ID：家电下乡产品型号ID
	 */
	public Long getOut_sys_id() {
		return out_sys_id;
	}

	public void setOut_sys_id(Long outSysId) {
		out_sys_id = outSysId;
	}

	public Long getInit_count() {
		return init_count;
	}

	public void setInit_count(Long initCount) {
		init_count = initCount;
	}

	public JxcPd getQcjc_pd() {
		return qcjc_pd;
	}

	public void setQcjc_pd(JxcPd qcjcPd) {
		qcjc_pd = qcjcPd;
	}

	public JxcPd getRcjh_pd() {
		return rcjh_pd;
	}

	public void setRcjh_pd(JxcPd rcjhPd) {
		rcjh_pd = rcjhPd;
	}

	public JxcPd getRcch_pd() {
		return rcch_pd;
	}

	public void setRcch_pd(JxcPd rcchPd) {
		rcch_pd = rcchPd;
	}

	public BigDecimal getInit_cost_price() {
		return init_cost_price;
	}

	public void setInit_cost_price(BigDecimal initCostPrice) {
		init_cost_price = initCostPrice;
	}

	public BigDecimal getCur_cost_price() {
		return cur_cost_price;
	}

	public void setCur_cost_price(BigDecimal curCostPrice) {
		cur_cost_price = curCostPrice;
	}

	public Double getRcjh_money() {
		return rcjh_money;
	}

	public void setRcjh_money(Double rcjhMoney) {
		rcjh_money = rcjhMoney;
	}

	public Double getRcch_money() {
		return rcch_money;
	}

	public void setRcch_money(Double rcchMoney) {
		rcch_money = rcchMoney;
	}

	public Double getQmjc_count() {
		return qmjc_count;
	}

	public void setQmjc_count(Double qmjcCount) {
		qmjc_count = qmjcCount;
	}

	public Double getQmjc_price() {
		return qmjc_price;
	}

	public void setQmjc_price(Double qmjcPrice) {
		qmjc_price = qmjcPrice;
	}

	public Double getQmjc_money() {
		return qmjc_money;
	}

	public void setQmjc_money(Double qmjcMoney) {
		qmjc_money = qmjcMoney;
	}

	public JxcPd getRcjh_rcch_pd() {
		return rcjh_rcch_pd;
	}

	public void setRcjh_rcch_pd(JxcPd rcjhRcchPd) {
		rcjh_rcch_pd = rcjhRcchPd;
	}

	public Long getJxc_pd_type_id() {
		return jxc_pd_type_id;
	}

	public void setJxc_pd_type_id(Long jxcPdTypeId) {
		jxc_pd_type_id = jxcPdTypeId;
	}
}