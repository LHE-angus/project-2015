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
public class EcOutWarehouse extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer out_type;
	
	private Long goods_id;
	
	private String pd_spec;
	
	private Integer num;
	
	private BigDecimal price;
	
	private BigDecimal all_price;
	
	private Long add_u_id;
	
	private Date add_date;
	
	private String remark;
	
	private Integer is_del;
	
	private Integer is_lock;
	
	public EcOutWarehouse() {

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
	 * @val 出库类型：0-正常销售，1-内耗，2-绑定商品销售
	 */
	public Integer getOut_type() {
		return out_type;
	}
	
	/**
	 * @val 出库类型：0-正常销售，1-内耗，2-绑定商品销售
	 */
	public void setOut_type(Integer out_type) {
		this.out_type = out_type;
	}
	
	/**
	 * @val 商品ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 商品ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
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
	 * @val 出库数量
	 */
	public Integer getNum() {
		return num;
	}
	
	/**
	 * @val 出库数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	/**
	 * @val 出库单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 出库单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 出库总金额
	 */
	public BigDecimal getAll_price() {
		return all_price;
	}
	
	/**
	 * @val 出库总金额
	 */
	public void setAll_price(BigDecimal all_price) {
		this.all_price = all_price;
	}
	
	/**
	 * @val 出库人
	 */
	public Long getAdd_u_id() {
		return add_u_id;
	}
	
	/**
	 * @val 出库人
	 */
	public void setAdd_u_id(Long add_u_id) {
		this.add_u_id = add_u_id;
	}
	
	/**
	 * @val 出库时间=ADD_DATE
	 */
	public Date getAdd_date() {
		return add_date;
	}
	
	/**
	 * @val 出库时间=ADD_DATE
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
	/**
	 * @val 备注
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * @val 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 是否删除：0-未删除，1-已删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 是否锁定：0-未锁定，1-锁定
	 */
	public Integer getIs_lock() {
		return is_lock;
	}
	
	/**
	 * @val 是否锁定：0-未锁定，1-锁定
	 */
	public void setIs_lock(Integer is_lock) {
		this.is_lock = is_lock;
	}
	
}