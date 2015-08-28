package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-02 09:52:42
 */
public class JxcOutInDetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String out_r3_code;

	private Long out_store_id;

	private String in_r3_code;

	private Long in_store_id;

	private Long out_goods_id;

	private Long in_goods_id;

	private Integer in_num;

	private Date out_date;

	private Integer state;

	private String goods_name;

	private Long subcomp_id;

	private Date confirm_date;

	private Integer trans_type;

	private String trans_index;

	private Date add_date;

	private Integer is_del;

	private Long add_user_id;

	private BigDecimal out_money;

	private BigDecimal out_price;

	private BigDecimal in_money;

	private BigDecimal in_price;

	private String memo;

	public JxcOutInDetail() {

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
	 * @val 调出客户R3编码
	 */
	public String getOut_r3_code() {
		return out_r3_code;
	}

	/**
	 * @val 调出客户R3编码
	 */
	public void setOut_r3_code(String out_r3_code) {
		this.out_r3_code = out_r3_code;
	}

	/**
	 * @val 调出仓库ID
	 */
	public Long getOut_store_id() {
		return out_store_id;
	}

	/**
	 * @val 调出仓库ID
	 */
	public void setOut_store_id(Long out_store_id) {
		this.out_store_id = out_store_id;
	}

	/**
	 * @val 调进客户R3编码
	 */
	public String getIn_r3_code() {
		return in_r3_code;
	}

	/**
	 * @val 调进客户R3编码
	 */
	public void setIn_r3_code(String in_r3_code) {
		this.in_r3_code = in_r3_code;
	}

	/**
	 * @val 掉进仓库ID
	 */
	public Long getIn_store_id() {
		return in_store_id;
	}

	/**
	 * @val 掉进仓库ID
	 */
	public void setIn_store_id(Long in_store_id) {
		this.in_store_id = in_store_id;
	}

	/**
	 * @val 调出商品ID
	 */
	public Long getOut_goods_id() {
		return out_goods_id;
	}

	/**
	 * @val 调出商品ID
	 */
	public void setOut_goods_id(Long out_goods_id) {
		this.out_goods_id = out_goods_id;
	}

	/**
	 * @val 调进商品ID
	 */
	public Long getIn_goods_id() {
		return in_goods_id;
	}

	/**
	 * @val 调进商品ID
	 */
	public void setIn_goods_id(Long in_goods_id) {
		this.in_goods_id = in_goods_id;
	}

	/**
	 * @val 调整数量
	 */
	public Integer getIn_num() {
		return in_num;
	}

	/**
	 * @val 调整数量
	 */
	public void setIn_num(Integer in_num) {
		this.in_num = in_num;
	}

	/**
	 * @val 调整日期
	 */
	public Date getOut_date() {
		return out_date;
	}

	/**
	 * @val 调整日期
	 */
	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}

	/**
	 * @val 0，未确认
	 * @val 1，已确认【计算库存】
	 * @val 2、拒收
	 * @val 3、其他
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 0，未确认
	 * @val 1，已确认【计算库存】
	 * @val 2、拒收
	 * @val 3、其他
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 商品名称
	 */
	public String getGoods_name() {
		return goods_name;
	}

	/**
	 * @val 商品名称
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	/**
	 * @val 分公司ID
	 */
	public Long getSubcomp_id() {
		return subcomp_id;
	}

	/**
	 * @val 分公司ID
	 */
	public void setSubcomp_id(Long subcomp_id) {
		this.subcomp_id = subcomp_id;
	}

	/**
	 * @val 调拨确认日期
	 */
	public Date getConfirm_date() {
		return confirm_date;
	}

	/**
	 * @val 调拨确认日期
	 */
	public void setConfirm_date(Date confirm_date) {
		this.confirm_date = confirm_date;
	}

	/**
	 * @val 0：调入
	 * @val 1：调出
	 * @val 10：移出
	 * @val 11：移入
	 */
	public Integer getTrans_type() {
		return trans_type;
	}

	/**
	 * @val 0：调入
	 * @val 1：调出
	 * @val 10：移出
	 * @val 11：移入
	 */
	public void setTrans_type(Integer trans_type) {
		this.trans_type = trans_type;
	}

	/**
	 * @val 调拨编码
	 */
	public String getTrans_index() {
		return trans_index;
	}

	/**
	 * @val 调拨编码
	 */
	public void setTrans_index(String trans_index) {
		this.trans_index = trans_index;
	}

	/**
	 * @val 入库日期
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 入库日期
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
	 * @val 调出库存金额
	 */
	public BigDecimal getOut_money() {
		return out_money;
	}

	/**
	 * @val 调出库存金额
	 */
	public void setOut_money(BigDecimal out_money) {
		this.out_money = out_money;
	}

	/**
	 * @val 调出商品单价
	 */
	public BigDecimal getOut_price() {
		return out_price;
	}

	/**
	 * @val 调出商品单价
	 */
	public void setOut_price(BigDecimal out_price) {
		this.out_price = out_price;
	}

	/**
	 * @val 调入库存金额
	 */
	public BigDecimal getIn_money() {
		return in_money;
	}

	/**
	 * @val 调入库存金额
	 */
	public void setIn_money(BigDecimal in_money) {
		this.in_money = in_money;
	}

	/**
	 * @val 调出商品单价
	 */
	public BigDecimal getIn_price() {
		return in_price;
	}

	/**
	 * @val 调出商品单价
	 */
	public void setIn_price(BigDecimal in_price) {
		this.in_price = in_price;
	}

	/**
	 * @val 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @val 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

}