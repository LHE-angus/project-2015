package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-13 17:30:26
 */
public class StatisticalDimensionDataMiddle extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private BigDecimal id;
	
	private String r3_code;
	
	private BigDecimal shop_state;
	
	private BigDecimal category_id;
	
	private BigDecimal year_id;
	
	private Date join_date;
	
	private Date stop_date;
	
	private BigDecimal p_index;
	
	private BigDecimal store_num;
	
	private BigDecimal loss_store_num;
	
	private BigDecimal add_store_num;
	
	private BigDecimal agent_num;
	
	private BigDecimal loss_agent_num;
	
	private BigDecimal ywy_num;
	
	private BigDecimal promoter_num;
	
	private BigDecimal annual_customer_sale;
	
	private BigDecimal annual_turnover;
	
	private BigDecimal annual_settle_money;
	
	private BigDecimal annual_settle_num;
	
	private BigDecimal settle_type_id;
	
	private BigDecimal annual_qdxt_order_num;
	
	private BigDecimal annual_qdxt_order_money;
	
	private BigDecimal annual_retail_money;
	
	private BigDecimal annual_retail_num;
	
	private BigDecimal retail_type_id;
	
	private Date update_date;
	
	private BigDecimal update_user_id;
	
	private Integer is_del;
	
	private BigDecimal turnover_type_id;
	
	private BigDecimal customer_risk_type;
	
	private BigDecimal customer_rwwcbfb;
	
	private BigDecimal annual_retail_money_bz;
	
	private BigDecimal annual_retail_money_tbzf;
	
	private BigDecimal add_agent_num;
	
	private String customer_name;
	
	private Integer is_sdf;
	
	private String customer_code;
	
	private String branch_name;
	
	private BigDecimal join_date_type;
	
	private BigDecimal stop_date_type;
	
	private BigDecimal annual_turnover_bz;
	
	private BigDecimal annual_turnover_tbzf;
	
	private BigDecimal annual_settle_money_bz;
	
	private BigDecimal annual_settle_money_tbzf;
	
	private BigDecimal annual_qdxt_order_num_bz;
	
	private BigDecimal annual_qdxt_order_num_zf;
	
	private BigDecimal pre_annual_turnover;
	
	private BigDecimal pre_annual_customer_sale;
	
	private BigDecimal pre_annual_settle_money;
	
	private BigDecimal pre_annual_settle_num;
	
	private BigDecimal pre_annual_qdxt_order_num;
	
	private BigDecimal pre_annual_qdxt_order_money;
	
	private BigDecimal pre_annual_retail_money;
	
	private BigDecimal pre_annual_retail_num;
	
	public StatisticalDimensionDataMiddle() {

	}

	/**
	 * @val ID
	 */
	public BigDecimal getId() {
		return id;
	}
	
	/**
	 * @val ID
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	/**
	 * @val R3编码
	 */
	public String getR3_code() {
		return r3_code;
	}
	
	/**
	 * @val R3编码
	 */
	public void setR3_code(String r3_code) {
		this.r3_code = r3_code;
	}
	
	/**
	 * @val 客户状态
	 */
	public BigDecimal getShop_state() {
		return shop_state;
	}
	
	/**
	 * @val 客户状态
	 */
	public void setShop_state(BigDecimal shop_state) {
		this.shop_state = shop_state;
	}
	
	/**
	 * @val 客户分类
	 */
	public BigDecimal getCategory_id() {
		return category_id;
	}
	
	/**
	 * @val 客户分类
	 */
	public void setCategory_id(BigDecimal category_id) {
		this.category_id = category_id;
	}
	
	/**
	 * @val 年度
	 */
	public BigDecimal getYear_id() {
		return year_id;
	}
	
	/**
	 * @val 年度
	 */
	public void setYear_id(BigDecimal year_id) {
		this.year_id = year_id;
	}
	
	/**
	 * @val 加盟日期
	 */
	public Date getJoin_date() {
		return join_date;
	}
	
	/**
	 * @val 加盟日期
	 */
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	
	/**
	 * @val 终止合作日期
	 */
	public Date getStop_date() {
		return stop_date;
	}
	
	/**
	 * @val 终止合作日期
	 */
	public void setStop_date(Date stop_date) {
		this.stop_date = stop_date;
	}
	
	/**
	 * @val 所属区域
	 */
	public BigDecimal getP_index() {
		return p_index;
	}
	
	/**
	 * @val 所属区域
	 */
	public void setP_index(BigDecimal p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 门店数
	 */
	public BigDecimal getStore_num() {
		return store_num;
	}
	
	/**
	 * @val 门店数
	 */
	public void setStore_num(BigDecimal store_num) {
		this.store_num = store_num;
	}
	
	/**
	 * @val 流失门店数
	 */
	public BigDecimal getLoss_store_num() {
		return loss_store_num;
	}
	
	/**
	 * @val 流失门店数
	 */
	public void setLoss_store_num(BigDecimal loss_store_num) {
		this.loss_store_num = loss_store_num;
	}
	
	/**
	 * @val 新增门店数
	 */
	public BigDecimal getAdd_store_num() {
		return add_store_num;
	}
	
	/**
	 * @val 新增门店数
	 */
	public void setAdd_store_num(BigDecimal add_store_num) {
		this.add_store_num = add_store_num;
	}
	
	/**
	 * @val 网点数
	 */
	public BigDecimal getAgent_num() {
		return agent_num;
	}
	
	/**
	 * @val 网点数
	 */
	public void setAgent_num(BigDecimal agent_num) {
		this.agent_num = agent_num;
	}
	
	/**
	 * @val 流失网点数
	 */
	public BigDecimal getLoss_agent_num() {
		return loss_agent_num;
	}
	
	/**
	 * @val 流失网点数
	 */
	public void setLoss_agent_num(BigDecimal loss_agent_num) {
		this.loss_agent_num = loss_agent_num;
	}
	
	/**
	 * @val 业务员数
	 */
	public BigDecimal getYwy_num() {
		return ywy_num;
	}
	
	/**
	 * @val 业务员数
	 */
	public void setYwy_num(BigDecimal ywy_num) {
		this.ywy_num = ywy_num;
	}
	
	/**
	 * @val 促销员数
	 */
	public BigDecimal getPromoter_num() {
		return promoter_num;
	}
	
	/**
	 * @val 促销员数
	 */
	public void setPromoter_num(BigDecimal promoter_num) {
		this.promoter_num = promoter_num;
	}
	
	/**
	 * @val 年度客户任务额
	 */
	public BigDecimal getAnnual_customer_sale() {
		return annual_customer_sale;
	}
	
	/**
	 * @val 年度客户任务额
	 */
	public void setAnnual_customer_sale(BigDecimal annual_customer_sale) {
		this.annual_customer_sale = annual_customer_sale;
	}
	
	/**
	 * @val 年度回款额
	 */
	public BigDecimal getAnnual_turnover() {
		return annual_turnover;
	}
	
	/**
	 * @val 年度回款额
	 */
	public void setAnnual_turnover(BigDecimal annual_turnover) {
		this.annual_turnover = annual_turnover;
	}
	
	/**
	 * @val 年度结算额
	 */
	public BigDecimal getAnnual_settle_money() {
		return annual_settle_money;
	}
	
	/**
	 * @val 年度结算额
	 */
	public void setAnnual_settle_money(BigDecimal annual_settle_money) {
		this.annual_settle_money = annual_settle_money;
	}
	
	/**
	 * @val 年度结算量
	 */
	public BigDecimal getAnnual_settle_num() {
		return annual_settle_num;
	}
	
	/**
	 * @val 年度结算量
	 */
	public void setAnnual_settle_num(BigDecimal annual_settle_num) {
		this.annual_settle_num = annual_settle_num;
	}
	
	/**
	 * @val 结算额分类
	 */
	public BigDecimal getSettle_type_id() {
		return settle_type_id;
	}
	
	/**
	 * @val 结算额分类
	 */
	public void setSettle_type_id(BigDecimal settle_type_id) {
		this.settle_type_id = settle_type_id;
	}
	
	/**
	 * @val 年度渠道系统订单量
	 */
	public BigDecimal getAnnual_qdxt_order_num() {
		return annual_qdxt_order_num;
	}
	
	/**
	 * @val 年度渠道系统订单量
	 */
	public void setAnnual_qdxt_order_num(BigDecimal annual_qdxt_order_num) {
		this.annual_qdxt_order_num = annual_qdxt_order_num;
	}
	
	/**
	 * @val 年度渠道系统订单额
	 */
	public BigDecimal getAnnual_qdxt_order_money() {
		return annual_qdxt_order_money;
	}
	
	/**
	 * @val 年度渠道系统订单额
	 */
	public void setAnnual_qdxt_order_money(BigDecimal annual_qdxt_order_money) {
		this.annual_qdxt_order_money = annual_qdxt_order_money;
	}
	
	/**
	 * @val 年度零售额
	 */
	public BigDecimal getAnnual_retail_money() {
		return annual_retail_money;
	}
	
	/**
	 * @val 年度零售额
	 */
	public void setAnnual_retail_money(BigDecimal annual_retail_money) {
		this.annual_retail_money = annual_retail_money;
	}
	
	/**
	 * @val 年度零售量
	 */
	public BigDecimal getAnnual_retail_num() {
		return annual_retail_num;
	}
	
	/**
	 * @val 年度零售量
	 */
	public void setAnnual_retail_num(BigDecimal annual_retail_num) {
		this.annual_retail_num = annual_retail_num;
	}
	
	/**
	 * @val 零售额分类
	 */
	public BigDecimal getRetail_type_id() {
		return retail_type_id;
	}
	
	/**
	 * @val 零售额分类
	 */
	public void setRetail_type_id(BigDecimal retail_type_id) {
		this.retail_type_id = retail_type_id;
	}
	
	/**
	 * @val 更新时间
	 */
	public Date getUpdate_date() {
		return update_date;
	}
	
	/**
	 * @val 更新时间
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	/**
	 * @val 更新人ID
	 */
	public BigDecimal getUpdate_user_id() {
		return update_user_id;
	}
	
	/**
	 * @val 更新人ID
	 */
	public void setUpdate_user_id(BigDecimal update_user_id) {
		this.update_user_id = update_user_id;
	}
	
	/**
	 * @val 删除标识
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标识
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 回款额分类ID
	 */
	public BigDecimal getTurnover_type_id() {
		return turnover_type_id;
	}
	
	/**
	 * @val 回款额分类ID
	 */
	public void setTurnover_type_id(BigDecimal turnover_type_id) {
		this.turnover_type_id = turnover_type_id;
	}
	
	/**
	 * @val 客户风险类型
	 */
	public BigDecimal getCustomer_risk_type() {
		return customer_risk_type;
	}
	
	/**
	 * @val 客户风险类型
	 */
	public void setCustomer_risk_type(BigDecimal customer_risk_type) {
		this.customer_risk_type = customer_risk_type;
	}
	
	/**
	 * @val 客户任务完成百分比
	 */
	public BigDecimal getCustomer_rwwcbfb() {
		return customer_rwwcbfb;
	}
	
	/**
	 * @val 客户任务完成百分比
	 */
	public void setCustomer_rwwcbfb(BigDecimal customer_rwwcbfb) {
		this.customer_rwwcbfb = customer_rwwcbfb;
	}
	
	/**
	 * @val 年度零售额比重
	 */
	public BigDecimal getAnnual_retail_money_bz() {
		return annual_retail_money_bz;
	}
	
	/**
	 * @val 年度零售额比重
	 */
	public void setAnnual_retail_money_bz(BigDecimal annual_retail_money_bz) {
		this.annual_retail_money_bz = annual_retail_money_bz;
	}
	
	/**
	 * @val 年度零售额同比增幅
	 */
	public BigDecimal getAnnual_retail_money_tbzf() {
		return annual_retail_money_tbzf;
	}
	
	/**
	 * @val 年度零售额同比增幅
	 */
	public void setAnnual_retail_money_tbzf(BigDecimal annual_retail_money_tbzf) {
		this.annual_retail_money_tbzf = annual_retail_money_tbzf;
	}
	
	/**
	 * @val 新增网点数
	 */
	public BigDecimal getAdd_agent_num() {
		return add_agent_num;
	}
	
	/**
	 * @val 新增网点数
	 */
	public void setAdd_agent_num(BigDecimal add_agent_num) {
		this.add_agent_num = add_agent_num;
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
	 * @val R3分类   0：售达方     1：送达方
	 */
	public Integer getIs_sdf() {
		return is_sdf;
	}
	
	/**
	 * @val R3分类   0：售达方     1：送达方
	 */
	public void setIs_sdf(Integer is_sdf) {
		this.is_sdf = is_sdf;
	}
	
	/**
	 * @val 客户合并编码
	 */
	public String getCustomer_code() {
		return customer_code;
	}
	
	/**
	 * @val 客户合并编码
	 */
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	
	/**
	 * @val 事业部  1.多媒体     2.白电
	 */
	public String getBranch_name() {
		return branch_name;
	}
	
	/**
	 * @val 事业部  1.多媒体     2.白电
	 */
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	/**
	 * @val 加盟年度分类    1位小数位
	 */
	public BigDecimal getJoin_date_type() {
		return join_date_type;
	}
	
	/**
	 * @val 加盟年度分类    1位小数位
	 */
	public void setJoin_date_type(BigDecimal join_date_type) {
		this.join_date_type = join_date_type;
	}
	
	/**
	 * @val 终止年度分类    1位小数位
	 */
	public BigDecimal getStop_date_type() {
		return stop_date_type;
	}
	
	/**
	 * @val 终止年度分类    1位小数位
	 */
	public void setStop_date_type(BigDecimal stop_date_type) {
		this.stop_date_type = stop_date_type;
	}
	
	/**
	 * @val 年度回款额比重
	 */
	public BigDecimal getAnnual_turnover_bz() {
		return annual_turnover_bz;
	}
	
	/**
	 * @val 年度回款额比重
	 */
	public void setAnnual_turnover_bz(BigDecimal annual_turnover_bz) {
		this.annual_turnover_bz = annual_turnover_bz;
	}
	
	/**
	 * @val 年度回款额同比增幅
	 */
	public BigDecimal getAnnual_turnover_tbzf() {
		return annual_turnover_tbzf;
	}
	
	/**
	 * @val 年度回款额同比增幅
	 */
	public void setAnnual_turnover_tbzf(BigDecimal annual_turnover_tbzf) {
		this.annual_turnover_tbzf = annual_turnover_tbzf;
	}
	
	/**
	 * @val 年度结算额比重
	 */
	public BigDecimal getAnnual_settle_money_bz() {
		return annual_settle_money_bz;
	}
	
	/**
	 * @val 年度结算额比重
	 */
	public void setAnnual_settle_money_bz(BigDecimal annual_settle_money_bz) {
		this.annual_settle_money_bz = annual_settle_money_bz;
	}
	
	/**
	 * @val 年度结算额同比增幅
	 */
	public BigDecimal getAnnual_settle_money_tbzf() {
		return annual_settle_money_tbzf;
	}
	
	/**
	 * @val 年度结算额同比增幅
	 */
	public void setAnnual_settle_money_tbzf(BigDecimal annual_settle_money_tbzf) {
		this.annual_settle_money_tbzf = annual_settle_money_tbzf;
	}
	
	/**
	 * @val 年度渠道系统订单量比重
	 */
	public BigDecimal getAnnual_qdxt_order_num_bz() {
		return annual_qdxt_order_num_bz;
	}
	
	/**
	 * @val 年度渠道系统订单量比重
	 */
	public void setAnnual_qdxt_order_num_bz(BigDecimal annual_qdxt_order_num_bz) {
		this.annual_qdxt_order_num_bz = annual_qdxt_order_num_bz;
	}
	
	/**
	 * @val 年度渠道系统订单量增幅
	 */
	public BigDecimal getAnnual_qdxt_order_num_zf() {
		return annual_qdxt_order_num_zf;
	}
	
	/**
	 * @val 年度渠道系统订单量增幅
	 */
	public void setAnnual_qdxt_order_num_zf(BigDecimal annual_qdxt_order_num_zf) {
		this.annual_qdxt_order_num_zf = annual_qdxt_order_num_zf;
	}
	
	/**
	 * @val 上一年度回款额
	 */
	public BigDecimal getPre_annual_turnover() {
		return pre_annual_turnover;
	}
	
	/**
	 * @val 上一年度回款额
	 */
	public void setPre_annual_turnover(BigDecimal pre_annual_turnover) {
		this.pre_annual_turnover = pre_annual_turnover;
	}
	
	/**
	 * @val 上一年度客户任务额
	 */
	public BigDecimal getPre_annual_customer_sale() {
		return pre_annual_customer_sale;
	}
	
	/**
	 * @val 上一年度客户任务额
	 */
	public void setPre_annual_customer_sale(BigDecimal pre_annual_customer_sale) {
		this.pre_annual_customer_sale = pre_annual_customer_sale;
	}
	
	/**
	 * @val 上一年度结算额
	 */
	public BigDecimal getPre_annual_settle_money() {
		return pre_annual_settle_money;
	}
	
	/**
	 * @val 上一年度结算额
	 */
	public void setPre_annual_settle_money(BigDecimal pre_annual_settle_money) {
		this.pre_annual_settle_money = pre_annual_settle_money;
	}
	
	/**
	 * @val 上一年度结算量
	 */
	public BigDecimal getPre_annual_settle_num() {
		return pre_annual_settle_num;
	}
	
	/**
	 * @val 上一年度结算量
	 */
	public void setPre_annual_settle_num(BigDecimal pre_annual_settle_num) {
		this.pre_annual_settle_num = pre_annual_settle_num;
	}
	
	/**
	 * @val 上一年度渠道系统订单量
	 */
	public BigDecimal getPre_annual_qdxt_order_num() {
		return pre_annual_qdxt_order_num;
	}
	
	/**
	 * @val 上一年度渠道系统订单量
	 */
	public void setPre_annual_qdxt_order_num(BigDecimal pre_annual_qdxt_order_num) {
		this.pre_annual_qdxt_order_num = pre_annual_qdxt_order_num;
	}
	
	/**
	 * @val 上一年度渠道系统订单额
	 */
	public BigDecimal getPre_annual_qdxt_order_money() {
		return pre_annual_qdxt_order_money;
	}
	
	/**
	 * @val 上一年度渠道系统订单额
	 */
	public void setPre_annual_qdxt_order_money(BigDecimal pre_annual_qdxt_order_money) {
		this.pre_annual_qdxt_order_money = pre_annual_qdxt_order_money;
	}
	
	/**
	 * @val 上一年度零售额
	 */
	public BigDecimal getPre_annual_retail_money() {
		return pre_annual_retail_money;
	}
	
	/**
	 * @val 上一年度零售额
	 */
	public void setPre_annual_retail_money(BigDecimal pre_annual_retail_money) {
		this.pre_annual_retail_money = pre_annual_retail_money;
	}
	
	/**
	 * @val 上一年度零售量
	 */
	public BigDecimal getPre_annual_retail_num() {
		return pre_annual_retail_num;
	}
	
	/**
	 * @val 上一年度零售量
	 */
	public void setPre_annual_retail_num(BigDecimal pre_annual_retail_num) {
		this.pre_annual_retail_num = pre_annual_retail_num;
	}
	
}