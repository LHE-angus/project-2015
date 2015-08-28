package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
public class EcBindingPd extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer binding_type;
	
	private String goods_name;
	
	private String goods_id;
	
	private BigDecimal price;
	
	private Integer own_sys;
	
	private String main_pic;
	
	private Long view_counts;
	
	private Long order_value;
	
	private Date add_date;
	
	private Integer state;
	
	private Long add_u_id;
	
	public EcBindingPd() {

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
	 * @val 类型：0-服务产品，1-实际产品
	 */
	public Integer getBinding_type() {
		return binding_type;
	}
	
	/**
	 * @val 类型：0-服务产品，1-实际产品
	 */
	public void setBinding_type(Integer binding_type) {
		this.binding_type = binding_type;
	}
	
	/**
	 * @val 绑定商品名称
	 */
	public String getGoods_name() {
		return goods_name;
	}
	
	/**
	 * @val 绑定商品名称
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	/**
	 * @val 绑定商品ID：关联KONKA_BCOMP_PD【商品表】中的ID，如果不是商品只是服务此字段为空
	 */
	public String getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 绑定商品ID：关联KONKA_BCOMP_PD【商品表】中的ID，如果不是商品只是服务此字段为空
	 */
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	
}