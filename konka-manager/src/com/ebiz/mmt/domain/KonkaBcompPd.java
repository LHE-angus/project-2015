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
 * @date 2013-09-10 16:15:29
 */
public class KonkaBcompPd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String pd_sn;

	private String pd_spec;

	private String pd_name;

	private Integer pd_size;

	private String pd_res;

	private String main_pic;

	private String pd_desc;

	private Integer label_3d;

	private Integer label_int;

	private Integer label_www;

	private Integer label_4k;

	private Integer label_of_cate;

	private Date down_time;

	private Date last_up_time;

	private Long sale_num;

	private Long view_counts;

	private Long order_value;

	private Date add_date;

	private Integer state;

	private String dept_sn;

	private Integer own_sys;

	private Long add_u_id;

	private Long integral_type;

	private Long integral;

	private Integer lock_state;// 是否锁定

	private EcGoodsPrice ecGoodsPrice;

	private EcStocks ecStocks;

	private EcBcompPdRebates ecBcompPdRebates;

	private EcBaseExpressReachDay ecBaseExpressReachDay;

	private EcShoppingCart ecShoppingCart;

	private List<KonkaBcompPdContent> konkaBcompPdContentList;

	private List<EcBindingPd> ecBindingPdList;

	private List<EcBindingPd> ecBindingPdListForService;

	private List<EcBindingPd> ecBindingPdListForPackages;

	private String[] picArray;

	private String[] serviceIds;

	private Long timeRemains;

	private Integer is_sf;// 商品是否导入顺丰 默认0 导入成功1

	private Integer is_tj;// 是否为推荐 0 否 1是

	private Integer prod_type;// 产品类别 0表示彩电，1白电3小家电4冰箱5洗衣机6空调，10配件

	private List<EcRule> ecRuleList;// 绑定规则

	private BigDecimal sj_weight;// 实际重量

	private BigDecimal p_weight;// 拋重

	private Integer is_djq;// 判断是否代金券模式商品

	private String brand_name;// 品牌

	private Date up_time;// 更新时间

	private Integer is_allow_voucher;// 是否允许使用购物券 0允许1不允许

	public KonkaBcompPd() {

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
	 * @val 商品编码
	 */
	public String getPd_sn() {
		return pd_sn;
	}

	/**
	 * @val 商品编码
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
	 * @val 商品名称
	 */
	public String getPd_name() {
		return pd_name;
	}

	/**
	 * @val 商品名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
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
	 * @val 商品描述
	 */
	public String getPd_desc() {
		return pd_desc;
	}

	/**
	 * @val 商品描述
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
	 * @val 功能分类-4K电视：0-否 1-是
	 */
	public Integer getLabel_4k() {
		return label_4k;
	}

	/**
	 * @val 功能分类-4K电视：0-否 1-是
	 */
	public void setLabel_4k(Integer label_4k) {
		this.label_4k = label_4k;
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
	 * @val 下架时间
	 */
	public Date getDown_time() {
		return down_time;
	}

	/**
	 * @val 下架时间
	 */
	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}

	/**
	 * @val 最后上架时间
	 */
	public Date getLast_up_time() {
		return last_up_time;
	}

	/**
	 * @val 最后上架时间
	 */
	public void setLast_up_time(Date last_up_time) {
		this.last_up_time = last_up_time;
	}

	/**
	 * @val 浏览次数
	 */
	public Long getView_counts() {
		return view_counts;
	}

	/**
	 * @val 浏览次数
	 */
	public void setView_counts(Long view_counts) {
		this.view_counts = view_counts;
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

	/**
	 * @val 商品详细，规格，保修
	 */
	public List<KonkaBcompPdContent> getKonkaBcompPdContentList() {
		return konkaBcompPdContentList;
	}

	/**
	 * @val 商品详细，规格，保修
	 */
	public void setKonkaBcompPdContentList(List<KonkaBcompPdContent> konkaBcompPdContentList) {
		this.konkaBcompPdContentList = konkaBcompPdContentList;
	}

	/**
	 * @val 商品绑定商品
	 */
	public List<EcBindingPd> getEcBindingPdList() {
		return ecBindingPdList;
	}

	/**
	 * @val 商品绑定商品
	 */
	public void setEcBindingPdList(List<EcBindingPd> ecBindingPdList) {
		this.ecBindingPdList = ecBindingPdList;
	}

	public EcGoodsPrice getEcGoodsPrice() {
		return ecGoodsPrice;
	}

	public void setEcGoodsPrice(EcGoodsPrice ecGoodsPrice) {
		this.ecGoodsPrice = ecGoodsPrice;
	}

	/**
	 * @val 总销售量
	 */
	public Long getSale_num() {
		return sale_num;
	}

	/**
	 * @val 总销售量
	 */
	public void setSale_num(Long sale_num) {
		this.sale_num = sale_num;
	}

	/**
	 * @val 保修服务
	 */
	public List<EcBindingPd> getEcBindingPdListForService() {
		return ecBindingPdListForService;
	}

	/**
	 * @val 保修服务
	 */
	public void setEcBindingPdListForService(List<EcBindingPd> ecBindingPdListForService) {
		this.ecBindingPdListForService = ecBindingPdListForService;
	}

	/**
	 * @val 套餐产品
	 */
	public List<EcBindingPd> getEcBindingPdListForPackages() {
		return ecBindingPdListForPackages;
	}

	/**
	 * @val 套餐产品
	 */
	public void setEcBindingPdListForPackages(List<EcBindingPd> ecBindingPdListForPackages) {
		this.ecBindingPdListForPackages = ecBindingPdListForPackages;
	}

	/**
	 * @val 图片列表
	 */
	public String[] getPicArray() {
		return picArray;
	}

	/**
	 * @val 图片列表
	 */
	public void setPicArray(String[] picArray) {
		this.picArray = picArray;
	}

	/**
	 * @val 产品价格
	 */
	public EcStocks getEcStocks() {
		return ecStocks;
	}

	/**
	 * @val 产品价格
	 */
	public void setEcStocks(EcStocks ecStocks) {
		this.ecStocks = ecStocks;
	}

	/**
	 * @val 返利佣金
	 */
	public EcBcompPdRebates getEcBcompPdRebates() {
		return ecBcompPdRebates;
	}

	/**
	 * @val 返利佣金
	 */
	public void setEcBcompPdRebates(EcBcompPdRebates ecBcompPdRebates) {
		this.ecBcompPdRebates = ecBcompPdRebates;
	}

	/**
	 * @val 提交订单的时候保存选中的服务ID
	 */
	public String[] getServiceIds() {
		return serviceIds;
	}

	/**
	 * @val 提交订单的时候保存选中的服务ID
	 */
	public void setServiceIds(String[] serviceIds) {
		this.serviceIds = serviceIds;
	}

	public EcShoppingCart getEcShoppingCart() {
		return ecShoppingCart;
	}

	public void setEcShoppingCart(EcShoppingCart ecShoppingCart) {
		this.ecShoppingCart = ecShoppingCart;
	}

	/**
	 * @val 从1970.1.1 0:00以来的毫秒数
	 */
	public Long getTimeRemains() {
		return timeRemains;
	}

	public void setTimeRemains(Long timeRemains) {
		this.timeRemains = timeRemains;
	}

	/**
	 * @val 积分类型 0：按比例 1：按固定积分
	 */
	public Long getIntegral_type() {
		return integral_type;
	}

	/**
	 * @val 积分类型 0：按比例 1：按固定积分
	 */
	public void setIntegral_type(Long integral_type) {
		this.integral_type = integral_type;
	}

	/**
	 * @val 积分值
	 */
	public Long getIntegral() {
		return integral;
	}

	/**
	 * @val 积分值
	 */
	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public EcBaseExpressReachDay getEcBaseExpressReachDay() {
		return ecBaseExpressReachDay;
	}

	public void setEcBaseExpressReachDay(EcBaseExpressReachDay ecBaseExpressReachDay) {
		this.ecBaseExpressReachDay = ecBaseExpressReachDay;
	}

	public Integer getLock_state() {
		return lock_state;
	}

	public void setLock_state(Integer lockState) {
		lock_state = lockState;
	}

	public Integer getIs_sf() {
		return is_sf;
	}

	public void setIs_sf(Integer isSf) {
		is_sf = isSf;
	}

	public Integer getIs_tj() {
		return is_tj;
	}

	public void setIs_tj(Integer isTj) {
		is_tj = isTj;
	}

	public Integer getProd_type() {
		return prod_type;
	}

	public void setProd_type(Integer prodType) {
		prod_type = prodType;
	}

	public List<EcRule> getEcRuleList() {
		return ecRuleList;
	}

	public void setEcRuleList(List<EcRule> ecRuleList) {
		this.ecRuleList = ecRuleList;
	}

	public BigDecimal getSj_weight() {
		return sj_weight;
	}

	public void setSj_weight(BigDecimal sjWeight) {
		sj_weight = sjWeight;
	}

	public BigDecimal getP_weight() {
		return p_weight;
	}

	public void setP_weight(BigDecimal pWeight) {
		p_weight = pWeight;
	}

	public Integer getIs_djq() {
		return is_djq;
	}

	public void setIs_djq(Integer isDjq) {
		is_djq = isDjq;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brandName) {
		brand_name = brandName;
	}

	public Date getUp_time() {
		return up_time;
	}

	public void setUp_time(Date upTime) {
		up_time = upTime;
	}

	public Integer getIs_allow_voucher() {
		return is_allow_voucher;
	}

	public void setIs_allow_voucher(Integer isAllowVoucher) {
		is_allow_voucher = isAllowVoucher;
	}

}