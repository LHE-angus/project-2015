package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-14 11:07:13
 */
public class JxcFifoStocks extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long goods_id;

	private String goods_model;

	private String stock_in_batch;

	private Long subcomp_id;

	private String subcomp_name;

	private Long dept_id;

	private String dept_name;

	private String ywy_job_id;

	private String ywy_name;

	private String cxy_job_id;

	private String cxy_nmae;

	private String r3_code;

	private String customer_name;

	private String r3_code_ext;

	private Long stock_in_num;

	private BigDecimal stock_in_price;

	private Date stock_in_date;

	private Date stock_in_opr_date;

	private Integer stock_in_type;

	private Long stock_out_num;

	private BigDecimal stock_out_price;

	private Date stock_out_date;

	private Date stock_out_opr_date;

	private Integer stock_out_type;

	private Integer stock_state;

	private Long stock_in_store;

	private Long stock_out_store;

	private Integer stock_carry_over;

	public JxcFifoStocks() {

	}

	/**
	 * @val 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 主键
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @val 商品型号
	 */
	public String getGoods_model() {
		return goods_model;
	}

	/**
	 * @val 商品型号
	 */
	public void setGoods_model(String goods_model) {
		this.goods_model = goods_model;
	}

	/**
	 * @val 进货批次
	 */
	public String getStock_in_batch() {
		return stock_in_batch;
	}

	/**
	 * @val 进货批次
	 */
	public void setStock_in_batch(String stock_in_batch) {
		this.stock_in_batch = stock_in_batch;
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
	 * @val 分公司名称
	 */
	public String getSubcomp_name() {
		return subcomp_name;
	}

	/**
	 * @val 分公司名称
	 */
	public void setSubcomp_name(String subcomp_name) {
		this.subcomp_name = subcomp_name;
	}

	/**
	 * @val 部门ID
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 部门ID
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 部门名称
	 */
	public String getDept_name() {
		return dept_name;
	}

	/**
	 * @val 部门名称
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	/**
	 * @val 业务员jobID
	 */
	public String getYwy_job_id() {
		return ywy_job_id;
	}

	/**
	 * @val 业务员jobID
	 */
	public void setYwy_job_id(String ywy_job_id) {
		this.ywy_job_id = ywy_job_id;
	}

	/**
	 * @val 业务员姓名
	 */
	public String getYwy_name() {
		return ywy_name;
	}

	/**
	 * @val 业务员姓名
	 */
	public void setYwy_name(String ywy_name) {
		this.ywy_name = ywy_name;
	}

	/**
	 * @val 促销员ID
	 */
	public String getCxy_job_id() {
		return cxy_job_id;
	}

	/**
	 * @val 促销员ID
	 */
	public void setCxy_job_id(String cxy_job_id) {
		this.cxy_job_id = cxy_job_id;
	}

	/**
	 * @val 促销员姓名
	 */
	public String getCxy_nmae() {
		return cxy_nmae;
	}

	/**
	 * @val 促销员姓名
	 */
	public void setCxy_nmae(String cxy_nmae) {
		this.cxy_nmae = cxy_nmae;
	}

	/**
	 * @val 客户编码
	 */
	public String getR3_code() {
		return r3_code;
	}

	/**
	 * @val 客户编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}

	/**
	 * @val 客户名称
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @val 客户名称
	 */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/**
	 * @val 客户合并编码
	 */
	public String getR3_code_ext() {
		return r3_code_ext;
	}

	/**
	 * @val 客户合并编码
	 */
	public void setR3_code_ext(String r3_code_ext) {
		this.r3_code_ext = r3_code_ext;
	}

	/**
	 * @val 商品进仓数量
	 */
	public Long getStock_in_num() {
		return stock_in_num;
	}

	/**
	 * @val 商品进仓数量
	 */
	public void setStock_in_num(Long stock_in_num) {
		this.stock_in_num = stock_in_num;
	}

	/**
	 * @val 商品进仓价格
	 */
	public BigDecimal getStock_in_price() {
		return stock_in_price;
	}

	/**
	 * @val 商品进仓价格
	 */
	public void setStock_in_price(BigDecimal stock_in_price) {
		this.stock_in_price = stock_in_price;
	}

	/**
	 * @val 商品进货时间
	 */
	public Date getStock_in_date() {
		return stock_in_date;
	}

	/**
	 * @val 商品进货时间
	 */
	public void setStock_in_date(Date stock_in_date) {
		this.stock_in_date = stock_in_date;
	}

	/**
	 * @val 商品进货操作时间
	 */
	public Date getStock_in_opr_date() {
		return stock_in_opr_date;
	}

	/**
	 * @val 商品进货操作时间
	 */
	public void setStock_in_opr_date(Date stock_in_opr_date) {
		this.stock_in_opr_date = stock_in_opr_date;
	}

	/**
	 * @val 商品进仓类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货
	 *      70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 */
	public Integer getStock_in_type() {
		return stock_in_type;
	}

	/**
	 * @val 商品进仓类型 10、地采的进货 20、集采的进货 30、产品初始化 40、分销进货 50、库存盘盈 60、零售通退货
	 *      70、零售通数据无效 80、库存调拨 90、库存转仓 100、零售通数据导入负数
	 */
	public void setStock_in_type(Integer stock_in_type) {
		this.stock_in_type = stock_in_type;
	}

	/**
	 * @val 商品出仓数量
	 */
	public Long getStock_out_num() {
		return stock_out_num;
	}

	/**
	 * @val 商品出仓数量
	 */
	public void setStock_out_num(Long stock_out_num) {
		this.stock_out_num = stock_out_num;
	}

	/**
	 * @val 商品出仓价格
	 */
	public BigDecimal getStock_out_price() {
		return stock_out_price;
	}

	/**
	 * @val 商品出仓价格
	 */
	public void setStock_out_price(BigDecimal stock_out_price) {
		this.stock_out_price = stock_out_price;
	}

	/**
	 * @val 商品出仓时间
	 */
	public Date getStock_out_date() {
		return stock_out_date;
	}

	/**
	 * @val 商品出仓时间
	 */
	public void setStock_out_date(Date stock_out_date) {
		this.stock_out_date = stock_out_date;
	}

	/**
	 * @val 商品出仓操作时间
	 */
	public Date getStock_out_opr_date() {
		return stock_out_opr_date;
	}

	/**
	 * @val 商品出仓操作时间
	 */
	public void setStock_out_opr_date(Date stock_out_opr_date) {
		this.stock_out_opr_date = stock_out_opr_date;
	}

	/**
	 * @val 商品出仓类型 510、地采退货  520、集采退货  530、零售通零食  540、分销（确认的） 550、专卖店销售 560、库存盘亏 
	 * 570、库存调拨 580、库存转仓 590、产品初始化（负） 600、零售通数据导入（正数）
	 */
	public Integer getStock_out_type() {
		return stock_out_type;
	}

	/**
	 *@val 商品出仓类型 510、地采退货  520、集采退货  530、零售通零食  540、分销（确认的） 550、专卖店销售 560、库存盘亏 
	 * 570、库存调拨 580、库存转仓 590、产品初始化（负） 600、零售通数据导入（正数）
	 */
	public void setStock_out_type(Integer stock_out_type) {
		this.stock_out_type = stock_out_type;
	}

	/**
	 * @val 商品状态：10、待售；20、已售
	 */
	public Integer getStock_state() {
		return stock_state;
	}

	/**
	 * @val 商品状态：1、待售；2、已售
	 */
	public void setStock_state(Integer stock_state) {
		this.stock_state = stock_state;
	}

	/**
	 * @val 入仓的仓库
	 */
	public Long getStock_in_store() {
		return stock_in_store;
	}

	/**
	 * @val 入仓的仓库
	 */
	public void setStock_in_store(Long stock_in_store) {
		this.stock_in_store = stock_in_store;
	}

	/**
	 * @val 出仓的仓库
	 */
	public Long getStock_out_store() {
		return stock_out_store;
	}

	/**
	 * @val 出仓的仓库
	 */
	public void setStock_out_store(Long stock_out_store) {
		this.stock_out_store = stock_out_store;
	}


	/**
	 * @val 是否结转 0 不是结转的期初 1 是结转的期初
	 */
	public Integer getStock_carry_over() {
		return stock_carry_over;
	}

	public void setStock_carry_over(Integer stock_carry_over) {
		this.stock_carry_over = stock_carry_over;
	}
}