package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-16 16:19:13
 */
public class StatisticalDimensionDataMonth extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private BigDecimal id;

	private String r3_code;

	private String customer_code;

	private String branch_name;

	private BigDecimal shop_state;

	private BigDecimal category_id;

	private BigDecimal month_id;

	private Date join_date;

	private BigDecimal join_date_type;

	private Date stop_date;

	private BigDecimal stop_date_type;

	private BigDecimal p_index;

	private BigDecimal store_num;

	private BigDecimal loss_store_num;

	private BigDecimal add_store_num;

	private BigDecimal agent_num;

	private BigDecimal loss_agent_num;

	private BigDecimal ywy_num;

	private BigDecimal promoter_num;

	private BigDecimal month_customer_sale;

	private BigDecimal month_turnover;

	private BigDecimal month_settle_money;

	private BigDecimal month_settle_num;

	private BigDecimal month_qdxt_order_num;

	private BigDecimal month_qdxt_order_money;

	private BigDecimal month_retail_money;

	private BigDecimal month_retail_num;

	private Date update_date;

	private Long update_user_id;

	private Integer is_del;

	private Long customer_risk_type;

	private BigDecimal customer_rwwcbfb;

	private BigDecimal add_agent_num;

	private String customer_name;

	private Integer is_sdf;

	private BigDecimal month_retail_money_bz;

	private BigDecimal month_retail_money_tbzf;

	private BigDecimal month_turnover_bz;

	private BigDecimal month_turnover_tbzf;

	private BigDecimal month_settle_money_bz;

	private BigDecimal month_settle_money_tbzf;

	private BigDecimal month_qdxt_order_num_bz;

	private BigDecimal month_qdxt_order_num_zf;

	private BigDecimal pre_month_turnover;

	private BigDecimal pre_month_customer_sale;

	private BigDecimal pre_month_settle_money;

	private BigDecimal pre_month_settle_num;

	private BigDecimal pre_month_qdxt_order_num;

	private BigDecimal pre_month_qdxt_order_money;

	private BigDecimal pre_month_retail_money;

	private BigDecimal pre_month_retail_num;

	private BigDecimal pre_year_turnover;

	private BigDecimal pre_year_customer_sale;

	private BigDecimal pre_year_settle_money;

	private BigDecimal pre_year_settle_num;

	private BigDecimal pre_year_qdxt_order_num;

	private BigDecimal pre_year_qdxt_order_money;

	private BigDecimal pre_year_retail_money;

	private BigDecimal pre_year_retail_num;

	private BigDecimal month_retail_money_task;

	private BigDecimal pre_month_retail_money_task;

	private BigDecimal pre_year_retail_money_task;

	public StatisticalDimensionDataMonth() {

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
	 * @val 事业部 1.多媒体 2.白电
	 */
	public String getBranch_name() {
		return branch_name;
	}

	/**
	 * @val 事业部 1.多媒体 2.白电
	 */
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
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
	 * @val 月度
	 */
	public BigDecimal getMonth_id() {
		return month_id;
	}

	/**
	 * @val 月度
	 */
	public void setMonth_id(BigDecimal month_id) {
		this.month_id = month_id;
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
	 * @val 加盟月度分类 1位小数位
	 */
	public BigDecimal getJoin_date_type() {
		return join_date_type;
	}

	/**
	 * @val 加盟月度分类 1位小数位
	 */
	public void setJoin_date_type(BigDecimal join_date_type) {
		this.join_date_type = join_date_type;
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
	 * @val 终止月度分类 1位小数位
	 */
	public BigDecimal getStop_date_type() {
		return stop_date_type;
	}

	/**
	 * @val 终止月度分类 1位小数位
	 */
	public void setStop_date_type(BigDecimal stop_date_type) {
		this.stop_date_type = stop_date_type;
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
	 * @val 月度客户任务额
	 */
	public BigDecimal getMonth_customer_sale() {
		return month_customer_sale;
	}

	/**
	 * @val 月度客户任务额
	 */
	public void setMonth_customer_sale(BigDecimal month_customer_sale) {
		this.month_customer_sale = month_customer_sale;
	}

	/**
	 * @val 月度回款额
	 */
	public BigDecimal getMonth_turnover() {
		return month_turnover;
	}

	/**
	 * @val 月度回款额
	 */
	public void setMonth_turnover(BigDecimal month_turnover) {
		this.month_turnover = month_turnover;
	}

	/**
	 * @val 月度结算额
	 */
	public BigDecimal getMonth_settle_money() {
		return month_settle_money;
	}

	/**
	 * @val 月度结算额
	 */
	public void setMonth_settle_money(BigDecimal month_settle_money) {
		this.month_settle_money = month_settle_money;
	}

	/**
	 * @val 月度结算量
	 */
	public BigDecimal getMonth_settle_num() {
		return month_settle_num;
	}

	/**
	 * @val 月度结算量
	 */
	public void setMonth_settle_num(BigDecimal month_settle_num) {
		this.month_settle_num = month_settle_num;
	}

	/**
	 * @val 月度渠道系统订单量
	 */
	public BigDecimal getMonth_qdxt_order_num() {
		return month_qdxt_order_num;
	}

	/**
	 * @val 月度渠道系统订单量
	 */
	public void setMonth_qdxt_order_num(BigDecimal month_qdxt_order_num) {
		this.month_qdxt_order_num = month_qdxt_order_num;
	}

	/**
	 * @val 月度渠道系统订单额
	 */
	public BigDecimal getMonth_qdxt_order_money() {
		return month_qdxt_order_money;
	}

	/**
	 * @val 月度渠道系统订单额
	 */
	public void setMonth_qdxt_order_money(BigDecimal month_qdxt_order_money) {
		this.month_qdxt_order_money = month_qdxt_order_money;
	}

	/**
	 * @val 月度零售额
	 */
	public BigDecimal getMonth_retail_money() {
		return month_retail_money;
	}

	/**
	 * @val 月度零售额
	 */
	public void setMonth_retail_money(BigDecimal month_retail_money) {
		this.month_retail_money = month_retail_money;
	}

	/**
	 * @val 月度零售量
	 */
	public BigDecimal getMonth_retail_num() {
		return month_retail_num;
	}

	/**
	 * @val 月度零售量
	 */
	public void setMonth_retail_num(BigDecimal month_retail_num) {
		this.month_retail_num = month_retail_num;
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
	public Long getUpdate_user_id() {
		return update_user_id;
	}

	/**
	 * @val 更新人ID
	 */
	public void setUpdate_user_id(Long update_user_id) {
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
	 * @val 客户风险类型
	 */
	public Long getCustomer_risk_type() {
		return customer_risk_type;
	}

	/**
	 * @val 客户风险类型
	 */
	public void setCustomer_risk_type(Long customer_risk_type) {
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
	 * @val R3分类 0：售达方 1：送达方
	 */
	public Integer getIs_sdf() {
		return is_sdf;
	}

	/**
	 * @val R3分类 0：售达方 1：送达方
	 */
	public void setIs_sdf(Integer is_sdf) {
		this.is_sdf = is_sdf;
	}

	/**
	 * @val 月度零售额比重
	 */
	public BigDecimal getMonth_retail_money_bz() {
		return month_retail_money_bz;
	}

	/**
	 * @val 月度零售额比重
	 */
	public void setMonth_retail_money_bz(BigDecimal month_retail_money_bz) {
		this.month_retail_money_bz = month_retail_money_bz;
	}

	/**
	 * @val 月度零售额同比增幅
	 */
	public BigDecimal getMonth_retail_money_tbzf() {
		return month_retail_money_tbzf;
	}

	/**
	 * @val 月度零售额同比增幅
	 */
	public void setMonth_retail_money_tbzf(BigDecimal month_retail_money_tbzf) {
		this.month_retail_money_tbzf = month_retail_money_tbzf;
	}

	/**
	 * @val 月度回款额比重
	 */
	public BigDecimal getMonth_turnover_bz() {
		return month_turnover_bz;
	}

	/**
	 * @val 月度回款额比重
	 */
	public void setMonth_turnover_bz(BigDecimal month_turnover_bz) {
		this.month_turnover_bz = month_turnover_bz;
	}

	/**
	 * @val 月度回款额同比增幅
	 */
	public BigDecimal getMonth_turnover_tbzf() {
		return month_turnover_tbzf;
	}

	/**
	 * @val 月度回款额同比增幅
	 */
	public void setMonth_turnover_tbzf(BigDecimal month_turnover_tbzf) {
		this.month_turnover_tbzf = month_turnover_tbzf;
	}

	/**
	 * @val 月度结算额比重
	 */
	public BigDecimal getMonth_settle_money_bz() {
		return month_settle_money_bz;
	}

	/**
	 * @val 月度结算额比重
	 */
	public void setMonth_settle_money_bz(BigDecimal month_settle_money_bz) {
		this.month_settle_money_bz = month_settle_money_bz;
	}

	/**
	 * @val 月度结算额同比增幅
	 */
	public BigDecimal getMonth_settle_money_tbzf() {
		return month_settle_money_tbzf;
	}

	/**
	 * @val 月度结算额同比增幅
	 */
	public void setMonth_settle_money_tbzf(BigDecimal month_settle_money_tbzf) {
		this.month_settle_money_tbzf = month_settle_money_tbzf;
	}

	/**
	 * @val 月度渠道系统订单量比重
	 */
	public BigDecimal getMonth_qdxt_order_num_bz() {
		return month_qdxt_order_num_bz;
	}

	/**
	 * @val 月度渠道系统订单量比重
	 */
	public void setMonth_qdxt_order_num_bz(BigDecimal month_qdxt_order_num_bz) {
		this.month_qdxt_order_num_bz = month_qdxt_order_num_bz;
	}

	/**
	 * @val 月度渠道系统订单量增幅
	 */
	public BigDecimal getMonth_qdxt_order_num_zf() {
		return month_qdxt_order_num_zf;
	}

	/**
	 * @val 月度渠道系统订单量增幅
	 */
	public void setMonth_qdxt_order_num_zf(BigDecimal month_qdxt_order_num_zf) {
		this.month_qdxt_order_num_zf = month_qdxt_order_num_zf;
	}

	/**
	 * @val 上一月度回款额
	 */
	public BigDecimal getPre_month_turnover() {
		return pre_month_turnover;
	}

	/**
	 * @val 上一月度回款额
	 */
	public void setPre_month_turnover(BigDecimal pre_month_turnover) {
		this.pre_month_turnover = pre_month_turnover;
	}

	/**
	 * @val 上一月度客户任务额
	 */
	public BigDecimal getPre_month_customer_sale() {
		return pre_month_customer_sale;
	}

	/**
	 * @val 上一月度客户任务额
	 */
	public void setPre_month_customer_sale(BigDecimal pre_month_customer_sale) {
		this.pre_month_customer_sale = pre_month_customer_sale;
	}

	/**
	 * @val 上一月度结算额
	 */
	public BigDecimal getPre_month_settle_money() {
		return pre_month_settle_money;
	}

	/**
	 * @val 上一月度结算额
	 */
	public void setPre_month_settle_money(BigDecimal pre_month_settle_money) {
		this.pre_month_settle_money = pre_month_settle_money;
	}

	/**
	 * @val 上一月度结算量
	 */
	public BigDecimal getPre_month_settle_num() {
		return pre_month_settle_num;
	}

	/**
	 * @val 上一月度结算量
	 */
	public void setPre_month_settle_num(BigDecimal pre_month_settle_num) {
		this.pre_month_settle_num = pre_month_settle_num;
	}

	/**
	 * @val 上一月度渠道系统订单量
	 */
	public BigDecimal getPre_month_qdxt_order_num() {
		return pre_month_qdxt_order_num;
	}

	/**
	 * @val 上一月度渠道系统订单量
	 */
	public void setPre_month_qdxt_order_num(BigDecimal pre_month_qdxt_order_num) {
		this.pre_month_qdxt_order_num = pre_month_qdxt_order_num;
	}

	/**
	 * @val 上一月度渠道系统订单额
	 */
	public BigDecimal getPre_month_qdxt_order_money() {
		return pre_month_qdxt_order_money;
	}

	/**
	 * @val 上一月度渠道系统订单额
	 */
	public void setPre_month_qdxt_order_money(BigDecimal pre_month_qdxt_order_money) {
		this.pre_month_qdxt_order_money = pre_month_qdxt_order_money;
	}

	/**
	 * @val 上一月度零售额
	 */
	public BigDecimal getPre_month_retail_money() {
		return pre_month_retail_money;
	}

	/**
	 * @val 上一月度零售额
	 */
	public void setPre_month_retail_money(BigDecimal pre_month_retail_money) {
		this.pre_month_retail_money = pre_month_retail_money;
	}

	/**
	 * @val 上一月度零售量
	 */
	public BigDecimal getPre_month_retail_num() {
		return pre_month_retail_num;
	}

	/**
	 * @val 上一月度零售量
	 */
	public void setPre_month_retail_num(BigDecimal pre_month_retail_num) {
		this.pre_month_retail_num = pre_month_retail_num;
	}

	/**
	 * @val 同期回款额
	 */
	public BigDecimal getPre_year_turnover() {
		return pre_year_turnover;
	}

	/**
	 * @val 同期回款额
	 */
	public void setPre_year_turnover(BigDecimal pre_year_turnover) {
		this.pre_year_turnover = pre_year_turnover;
	}

	/**
	 * @val 同期客户任务额
	 */
	public BigDecimal getPre_year_customer_sale() {
		return pre_year_customer_sale;
	}

	/**
	 * @val 同期客户任务额
	 */
	public void setPre_year_customer_sale(BigDecimal pre_year_customer_sale) {
		this.pre_year_customer_sale = pre_year_customer_sale;
	}

	/**
	 * @val 同期结算额
	 */
	public BigDecimal getPre_year_settle_money() {
		return pre_year_settle_money;
	}

	/**
	 * @val 同期结算额
	 */
	public void setPre_year_settle_money(BigDecimal pre_year_settle_money) {
		this.pre_year_settle_money = pre_year_settle_money;
	}

	/**
	 * @val 同期结算量
	 */
	public BigDecimal getPre_year_settle_num() {
		return pre_year_settle_num;
	}

	/**
	 * @val 同期结算量
	 */
	public void setPre_year_settle_num(BigDecimal pre_year_settle_num) {
		this.pre_year_settle_num = pre_year_settle_num;
	}

	/**
	 * @val 同期渠道系统订单量
	 */
	public BigDecimal getPre_year_qdxt_order_num() {
		return pre_year_qdxt_order_num;
	}

	/**
	 * @val 同期渠道系统订单量
	 */
	public void setPre_year_qdxt_order_num(BigDecimal pre_year_qdxt_order_num) {
		this.pre_year_qdxt_order_num = pre_year_qdxt_order_num;
	}

	/**
	 * @val 同期渠道系统订单额
	 */
	public BigDecimal getPre_year_qdxt_order_money() {
		return pre_year_qdxt_order_money;
	}

	/**
	 * @val 同期渠道系统订单额
	 */
	public void setPre_year_qdxt_order_money(BigDecimal pre_year_qdxt_order_money) {
		this.pre_year_qdxt_order_money = pre_year_qdxt_order_money;
	}

	/**
	 * @val 同期零售额
	 */
	public BigDecimal getPre_year_retail_money() {
		return pre_year_retail_money;
	}

	/**
	 * @val 同期零售额
	 */
	public void setPre_year_retail_money(BigDecimal pre_year_retail_money) {
		this.pre_year_retail_money = pre_year_retail_money;
	}

	/**
	 * @val 同期零售量
	 */
	public BigDecimal getPre_year_retail_num() {
		return pre_year_retail_num;
	}

	/**
	 * @val 同期零售量
	 */
	public void setPre_year_retail_num(BigDecimal pre_year_retail_num) {
		this.pre_year_retail_num = pre_year_retail_num;
	}

	/**
	 * @val 零售任务额
	 */
	public BigDecimal getMonth_retail_money_task() {
		return month_retail_money_task;
	}

	/**
	 * @val 零售任务额
	 */
	public void setMonth_retail_money_task(BigDecimal month_retail_money_task) {
		this.month_retail_money_task = month_retail_money_task;
	}

	/**
	 * @val 上一月零售任务额
	 */
	public BigDecimal getPre_month_retail_money_task() {
		return pre_month_retail_money_task;
	}

	/**
	 * @val 上一月零售任务额
	 */
	public void setPre_month_retail_money_task(BigDecimal pre_month_retail_money_task) {
		this.pre_month_retail_money_task = pre_month_retail_money_task;
	}

	/**
	 * @val 同期零售任务额
	 */
	public BigDecimal getPre_year_retail_money_task() {
		return pre_year_retail_money_task;
	}

	/**
	 * @val 同期零售任务额
	 */
	public void setPre_year_retail_money_task(BigDecimal pre_year_retail_money_task) {
		this.pre_year_retail_money_task = pre_year_retail_money_task;
	}

}