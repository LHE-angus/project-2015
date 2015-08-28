package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Jin,QingHua
 */
public class ShopPd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long pd_id;

	private Long brand_id;

	private String md_name;

	private String md_sname;

	private Long pd_type;

	private Long category_id;

	private Long series_id;

	private String series_name;

	/**
	 * 产品分类期号 PD_EXT_TYPE 1：试点期间的产品型号 2：推广期间的产品型号
	 */
	private Integer is_country;

	/**
	 * 0：不属于以旧换新 1：属于以旧换新
	 */
	private Integer is_oldtonew;

	/**
	 * 0：不属于节能惠民 1：属于节能惠民
	 */
	private Integer is_low_energy;

	/**
	 * -2：申报主题促销，未审核 -1：申报主题促销，审核未通过 0：不属于主题促销 1：主题促销审核通过
	 */
	private Integer is_topic_onsale;

	private Long topic_onsale_id;

	/**
	 * 0：否 1：是
	 */
	private Integer is_price_batch;

	/**
	 * 0：否 1：是
	 */
	private Integer is_auction;

	/**
	 * 0：否 1：是
	 */
	private Integer is_recommend;

	private Integer is_onsale;

	private Integer is_hot_sale;

	private Long entp_id;

	private Long shop_id;

	private BigDecimal price;

	private BigDecimal price_limit;

	private BigDecimal mmt_price;

	private Integer price_type;

	/**
	 * O：未审核 1：审核通过
	 */
	private Integer price_audit_state;

	private String main_pic;

	private Long access_num;

	private Integer excellent_num;

	private Integer good_num;

	private Integer normal_num;

	private Integer poor_num;

	private Integer bad_num;

	/**
	 * 0：未删除 1：已删除
	 */
	private Integer state;

	private Date add_date;

	private Date del_date;

	private Date sell_start_date;

	/**
	 * 
	 1：试点期间的产品型号 2：推广期间的产品型号
	 */
	private Date sell_end_date;

	private BigDecimal disv_limit_max;

	private String p_desc;

	private String sale_service;

	private Integer is_new;

	private PdModel pd_model;

	private Integer order_sort;

	/**
	 * 0：缺货 1：有货
	 */
	private Integer stock_state;

	private Long pd_num;

	private String pd_remark;

	private String pd_type_sign;

	private String brand_name;

	private String pd_name;

	private String shop_name;

	private List<ShopPdImgs> shopPdImgsList;

	private EntpShop entp_shop;

	List<PdPropertyCustom> pdPropertyCustomList;

	/**
	 * @desc 购物车无SESSION用到
	 * @para 0：一般购买模式 1：家电下乡购买模式 2：以旧换新购买模式
	 */
	private Integer buy_mode;

	/**
	 * @desc 购物车无SESSION用到,购物车产品的数量
	 */
	private Integer cart_num;

	private Integer is_up;

	private Integer is_otn;

	public ShopPd() {

	}

	public Long getAccess_num() {
		return access_num;
	}

	public Date getAdd_date() {
		return add_date;
	}

	public Integer getBad_num() {
		return bad_num;
	}

	public Long getBrand_id() {
		return brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public Integer getBuy_mode() {
		return buy_mode;
	}

	public Integer getCart_num() {
		return cart_num;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public Date getDel_date() {
		return del_date;
	}

	public BigDecimal getDisv_limit_max() {
		return disv_limit_max;
	}

	public Long getEntp_id() {
		return entp_id;
	}

	public EntpShop getEntp_shop() {
		return entp_shop;
	}

	public Integer getExcellent_num() {
		return excellent_num;
	}

	public Integer getGood_num() {
		return good_num;
	}

	public Long getId() {
		return id;
	}

	public Integer getIs_auction() {
		return is_auction;
	}

	public Integer getIs_country() {
		return is_country;
	}

	public Integer getIs_hot_sale() {
		return is_hot_sale;
	}

	public Integer getIs_low_energy() {
		return is_low_energy;
	}

	public Integer getIs_new() {
		return is_new;
	}

	public Integer getIs_oldtonew() {
		return is_oldtonew;
	}

	public Integer getIs_onsale() {
		return is_onsale;
	}

	public Integer getIs_price_batch() {
		return is_price_batch;
	}

	public Integer getIs_recommend() {
		return is_recommend;
	}

	public Integer getIs_topic_onsale() {
		return is_topic_onsale;
	}

	public String getMain_pic() {
		return main_pic;
	}

	public String getMd_name() {
		return md_name;
	}

	public String getMd_sname() {
		return md_sname;
	}

	public BigDecimal getMmt_price() {
		return mmt_price;
	}

	public Integer getNormal_num() {
		return normal_num;
	}

	public Integer getOrder_sort() {
		return order_sort;
	}

	public String getP_desc() {
		return p_desc;
	}

	public Long getPd_id() {
		return pd_id;
	}

	public PdModel getPd_model() {
		return pd_model;
	}

	public String getPd_name() {
		return pd_name;
	}

	public Long getPd_num() {
		return pd_num;
	}

	public String getPd_remark() {
		return pd_remark;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public String getPd_type_sign() {
		return pd_type_sign;
	}

	public List<PdPropertyCustom> getPdPropertyCustomList() {
		return pdPropertyCustomList;
	}

	public Integer getPoor_num() {
		return poor_num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getPrice_audit_state() {
		return price_audit_state;
	}

	public BigDecimal getPrice_limit() {
		return price_limit;
	}

	public Integer getPrice_type() {
		return price_type;
	}

	public String getSale_service() {
		return sale_service;
	}

	public Date getSell_end_date() {
		return sell_end_date;
	}

	public Date getSell_start_date() {
		return sell_start_date;
	}

	public Long getSeries_id() {
		return series_id;
	}

	public String getSeries_name() {
		return series_name;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public List<ShopPdImgs> getShopPdImgsList() {
		return shopPdImgsList;
	}

	public Integer getState() {
		return state;
	}

	public Integer getStock_state() {
		return stock_state;
	}

	public Long getTopic_onsale_id() {
		return topic_onsale_id;
	}

	public void setAccess_num(Long access_num) {
		this.access_num = access_num;
	}

	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	public void setBad_num(Integer bad_num) {
		this.bad_num = bad_num;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public void setBrand_name(String brandName) {
		brand_name = brandName;
	}

	public void setBuy_mode(Integer buyMode) {
		buy_mode = buyMode;
	}

	public void setCart_num(Integer cartNum) {
		cart_num = cartNum;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public void setDisv_limit_max(BigDecimal disvLimitMax) {
		disv_limit_max = disvLimitMax;
	}

	public void setEntp_id(Long entp_id) {
		this.entp_id = entp_id;
	}

	public void setEntp_shop(EntpShop entpShop) {
		entp_shop = entpShop;
	}

	public void setExcellent_num(Integer excellent_num) {
		this.excellent_num = excellent_num;
	}

	public void setGood_num(Integer good_num) {
		this.good_num = good_num;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIs_auction(Integer is_auction) {
		this.is_auction = is_auction;
	}

	public void setIs_country(Integer is_country) {
		this.is_country = is_country;
	}

	public void setIs_hot_sale(Integer isHotSale) {
		is_hot_sale = isHotSale;
	}

	public void setIs_low_energy(Integer is_low_energy) {
		this.is_low_energy = is_low_energy;
	}

	public void setIs_new(Integer isNew) {
		is_new = isNew;
	}

	public void setIs_oldtonew(Integer is_oldtonew) {
		this.is_oldtonew = is_oldtonew;
	}

	public void setIs_onsale(Integer is_onsale) {
		this.is_onsale = is_onsale;
	}

	public void setIs_price_batch(Integer is_price_batch) {
		this.is_price_batch = is_price_batch;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	public void setIs_topic_onsale(Integer is_topic_onsale) {
		this.is_topic_onsale = is_topic_onsale;
	}

	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}

	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	public void setMd_sname(String md_sname) {
		this.md_sname = md_sname;
	}

	public void setMmt_price(BigDecimal mmt_price) {
		this.mmt_price = mmt_price;
	}

	public void setNormal_num(Integer normal_num) {
		this.normal_num = normal_num;
	}

	public void setOrder_sort(Integer order_sort) {
		this.order_sort = order_sort;
	}

	public void setP_desc(String pDesc) {
		p_desc = pDesc;
	}

	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}

	public void setPd_model(PdModel pdModel) {
		pd_model = pdModel;
	}

	public void setPd_name(String pdName) {
		pd_name = pdName;
	}

	public void setPd_num(Long pd_num) {
		this.pd_num = pd_num;
	}

	public void setPd_remark(String pdRemark) {
		pd_remark = pdRemark;
	}

	public void setPd_type(Long pd_type) {
		this.pd_type = pd_type;
	}

	public void setPd_type_sign(String pdTypeSign) {
		pd_type_sign = pdTypeSign;
	}

	public void setPdPropertyCustomList(List<PdPropertyCustom> pdPropertyCustomList) {
		this.pdPropertyCustomList = pdPropertyCustomList;
	}

	public void setPoor_num(Integer poor_num) {
		this.poor_num = poor_num;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setPrice_audit_state(Integer price_audit_state) {
		this.price_audit_state = price_audit_state;
	}

	public void setPrice_limit(BigDecimal price_limit) {
		this.price_limit = price_limit;
	}

	public void setPrice_type(Integer price_type) {
		this.price_type = price_type;
	}

	public void setSale_service(String saleService) {
		sale_service = saleService;
	}

	public void setSell_end_date(Date sell_end_date) {
		this.sell_end_date = sell_end_date;
	}

	public void setSell_start_date(Date sell_start_date) {
		this.sell_start_date = sell_start_date;
	}

	public void setSeries_id(Long seriesId) {
		series_id = seriesId;
	}

	public void setSeries_name(String seriesName) {
		series_name = seriesName;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public void setShop_name(String shopName) {
		shop_name = shopName;
	}

	public void setShopPdImgsList(List<ShopPdImgs> shopPdImgsList) {
		this.shopPdImgsList = shopPdImgsList;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setStock_state(Integer stock_state) {
		this.stock_state = stock_state;
	}

	public void setTopic_onsale_id(Long topic_onsale_id) {
		this.topic_onsale_id = topic_onsale_id;
	}

	public Integer getIs_up() {
		return is_up;
	}

	public void setIs_up(Integer isUp) {
		is_up = isUp;
	}

	public Integer getIs_otn() {
		return is_otn;
	}

	public void setIs_otn(Integer isOtn) {
		is_otn = isOtn;
	}

}