package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public class EcShoppingCart extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long user_id;
	
	private Long goods_id;
	
	private Long p_index;
	
	private String md_name;
	
	private String pd_class;
	
	private String img_url;
	
	private BigDecimal price;
	
	private Integer pd_num;
	
	private Date add_date;
	
	private Integer own_sys;
	
	private String service_ids;
	
	private String user_ip;//增加用户IP防止多用户登陆购物车冲突
	
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public EcShoppingCart() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 用户ID
	 */
	public Long getUser_id() {
		return user_id;
	}
	
	/**
	 * @val 用户ID
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * @val 产品编码
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 产品编码
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
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
	 * @val 产品分类
	 */
	public String getPd_class() {
		return pd_class;
	}
	
	/**
	 * @val 产品分类
	 */
	public void setPd_class(String pd_class) {
		this.pd_class = pd_class;
	}
	
	/**
	 * @val 图片路径
	 */
	public String getImg_url() {
		return img_url;
	}
	
	/**
	 * @val 图片路径
	 */
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	/**
	 * @val 收藏价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 收藏价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 收藏数量
	 */
	public Integer getPd_num() {
		return pd_num;
	}
	
	/**
	 * @val 收藏数量
	 */
	public void setPd_num(Integer pd_num) {
		this.pd_num = pd_num;
	}
	
	/**
	 * @val 收藏时间
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 收藏时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 所属平台：1-工卡，2-触网，3-会员
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属平台：1-工卡，2-触网，3-会员
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}

	/**
	 * @val 收藏的时候的收货地址
	 */
	public Long getP_index() {
		return p_index;
	}

	/**
	 * @val 收藏的时候收货地址
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 选择的绑定服务：EC_BINDING_PD【绑定商品表】中的ID，使用 | 进行连接
	 */
	public String getService_ids() {
		return service_ids;
	}
	
	/**
	 * @val 选择的绑定服务：EC_BINDING_PD【绑定商品表】中的ID，使用 | 进行连接
	 */
	public void setService_ids(String service_ids) {
		this.service_ids = service_ids;
	}
}