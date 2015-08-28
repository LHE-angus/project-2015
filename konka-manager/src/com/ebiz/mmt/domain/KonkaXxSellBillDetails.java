package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-10 08:56:04
 */
public class KonkaXxSellBillDetails extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long sell_bill_details_id;

	private Long sell_bill_id;

	private Long pd_cls;

	private String pd_cls_name;

	private String md_name;

	private BigDecimal price;

	private Integer counts;

	private String gift;

	private String xs_remarks;

	private String zmd_fee_fp;

	private BigDecimal zmd_fee;

	private BigDecimal fix_fee;

	private Integer lock_stock_state;

	private Long pd_type;
	
	private String fac_sn;
	
	private String store_sn;

	private List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList;

	public KonkaXxSellBillDetails() {

	}

	/**
	 * @val 销售明细ID
	 */
	public Long getSell_bill_details_id() {
		return sell_bill_details_id;
	}

	/**
	 * @val 销售明细ID
	 */
	public void setSell_bill_details_id(Long sell_bill_details_id) {
		this.sell_bill_details_id = sell_bill_details_id;
	}

	/**
	 * @val 销售单ID
	 */
	public Long getSell_bill_id() {
		return sell_bill_id;
	}

	/**
	 * @val 销售单ID
	 */
	public void setSell_bill_id(Long sell_bill_id) {
		this.sell_bill_id = sell_bill_id;
	}

	/**
	 * @val 销售品类
	 */
	public Long getPd_cls() {
		return pd_cls;
	}

	/**
	 * @val 销售品类
	 */
	public void setPd_cls(Long pd_cls) {
		this.pd_cls = pd_cls;
	}

	/**
	 * @val 销售品类名称
	 */
	public String getPd_cls_name() {
		return pd_cls_name;
	}

	/**
	 * @val 销售品类名称
	 */
	public void setPd_cls_name(String pd_cls_name) {
		this.pd_cls_name = pd_cls_name;
	}

	/**
	 * @val 产品型号
	 */
	public String getMd_name() {
		return md_name;
	}

	/**
	 * @val 产品型号
	 */
	public void setMd_name(String md_name) {
		this.md_name = md_name;
	}

	/**
	 * @val 销售单价（元）
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @val 销售单价（元）
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @val 销售数量
	 */
	public Integer getCounts() {
		return counts;
	}

	/**
	 * @val 销售数量
	 */
	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	/**
	 * @val 赠品
	 */
	public String getGift() {
		return gift;
	}

	/**
	 * @val 赠品
	 */
	public void setGift(String gift) {
		this.gift = gift;
	}

	/**
	 * @val 销售备注
	 */
	public String getXs_remarks() {
		return xs_remarks;
	}

	/**
	 * @val 销售备注
	 */
	public void setXs_remarks(String xs_remarks) {
		this.xs_remarks = xs_remarks;
	}

	/**
	 * @val 佣金
	 */
	public BigDecimal getZmd_fee() {
		return zmd_fee;
	}

	/**
	 * @val 佣金
	 */
	public void setZmd_fee(BigDecimal zmd_fee) {
		this.zmd_fee = zmd_fee;
	}

	/**
	 * @val 安装费用（元）
	 */
	public BigDecimal getFix_fee() {
		return fix_fee;
	}

	/**
	 * @val 安装费用（元）
	 */
	public void setFix_fee(BigDecimal fix_fee) {
		this.fix_fee = fix_fee;
	}

	/**
	 * @val：库存锁定标识：0-未锁定 1-已锁定[默认] 2-已解锁
	 */
	public Integer getLock_stock_state() {
		return lock_stock_state;
	}

	public void setLock_stock_state(Integer lockStockState) {
		lock_stock_state = lockStockState;
	}

	/**
	 * @val：结算快照
	 */
	public String getZmd_fee_fp() {
		return zmd_fee_fp;
	}

	/**
	 * @val：结算快照
	 */
	public void setZmd_fee_fp(String zmdFeeFp) {
		zmd_fee_fp = zmdFeeFp;
	}

	public List<KonkaXxSellBillDetailsDst> getKonkaXxSellBillDetailsDstList() {
		return konkaXxSellBillDetailsDstList;
	}

	public void setKonkaXxSellBillDetailsDstList(List<KonkaXxSellBillDetailsDst> konkaXxSellBillDetailsDstList) {
		this.konkaXxSellBillDetailsDstList = konkaXxSellBillDetailsDstList;
	}

	public Long getPd_type() {
		return pd_type;
	}

	public void setPd_type(Long pdType) {
		pd_type = pdType;
	}

	public void setFac_sn(String fac_sn) {
		this.fac_sn = fac_sn;
	}

	public String getFac_sn() {
		return fac_sn;
	}

	public void setStore_sn(String store_sn) {
		this.store_sn = store_sn;
	}

	public String getStore_sn() {
		return store_sn;
	}

}