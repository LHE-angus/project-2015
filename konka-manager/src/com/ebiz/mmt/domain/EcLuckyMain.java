package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
public class EcLuckyMain extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Integer lucky_type;
	
	private String title;
	
	private String brief;
	
	private String lucky_memo;
	
	private String content;
	
	private BigDecimal price;
	
	private Integer integral;
	
	private String main_pic;
	
	private Integer view_counts;
	
	private Date add_date;
	
	private Long add_user_id;
	
	private Integer is_del;
	
	private Integer max_num;
	
	private Integer min_num;
	
	private Integer lucky_num;
	
	private Integer is_pub;
	
	private Integer own_sys;
	
	private Integer need_addr;
	
	private Integer need_mobile;
	
	private Long goods_id;
	
	private String goods_name;
	
	private Integer lucky_state;
	
	private Long p_index;
	
	private Integer order_value;
	
	private String lucky_msg;
	
	private List<EcLuckyBuy> ecLuckyBuyList = new ArrayList<EcLuckyBuy>();
	
	private List<EcLuckyTime> ecLuckyTimeList = new ArrayList<EcLuckyTime>();
	
	public List<EcLuckyBuy> getEcLuckyBuyList() {
		return ecLuckyBuyList;
	}

	public void setEcLuckyBuyList(List<EcLuckyBuy> ecLuckyBuyList) {
		this.ecLuckyBuyList = ecLuckyBuyList;
	}

	public List<EcLuckyTime> getEcLuckyTimeList() {
		return ecLuckyTimeList;
	}

	public void setEcLuckyTimeList(List<EcLuckyTime> ecLuckyTimeList) {
		this.ecLuckyTimeList = ecLuckyTimeList;
	}

	public EcLuckyMain() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @val 活动类别：0抽奖，1 团购
	 */
	public Integer getLucky_type() {
		return lucky_type;
	}
	
	/**
	 * @val 活动类别：0抽奖，1 团购
	 */
	public void setLucky_type(Integer lucky_type) {
		this.lucky_type = lucky_type;
	}
	
	/**
	 * @val 标题
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @val 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @val 活动简介
	 */
	public String getBrief() {
		return brief;
	}
	
	/**
	 * @val 活动简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
	/**
	 * @val 活动须知
	 */
	public String getLucky_memo() {
		return lucky_memo;
	}
	
	/**
	 * @val 活动须知
	 */
	public void setLucky_memo(String lucky_memo) {
		this.lucky_memo = lucky_memo;
	}
	
	/**
	 * @val 活动描述
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @val 活动描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @val 金额
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @val 金额
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @val 积分
	 */
	public Integer getIntegral() {
		return integral;
	}
	
	/**
	 * @val 积分
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
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
	public Integer getView_counts() {
		return view_counts;
	}
	
	/**
	 * @val 浏览次数
	 */
	public void setView_counts(Integer view_counts) {
		this.view_counts = view_counts;
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
	 * @val 添加用户ID
	 */
	public Long getAdd_user_id() {
		return add_user_id;
	}
	
	/**
	 * @val 添加用户ID
	 */
	public void setAdd_user_id(Long add_user_id) {
		this.add_user_id = add_user_id;
	}
	
	/**
	 * @val 删除标示
	 */
	public Integer getIs_del() {
		return is_del;
	}
	
	/**
	 * @val 删除标示
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * @val 最大购买数量
	 */
	public Integer getMax_num() {
		return max_num;
	}
	
	/**
	 * @val 最大购买数量
	 */
	public void setMax_num(Integer max_num) {
		this.max_num = max_num;
	}
	
	/**
	 * @val 最小购买数量
	 */
	public Integer getMin_num() {
		return min_num;
	}
	
	/**
	 * @val 最小购买数量
	 */
	public void setMin_num(Integer min_num) {
		this.min_num = min_num;
	}
	
	/**
	 * @val 可参与次数
	 */
	public Integer getLucky_num() {
		return lucky_num;
	}
	
	/**
	 * @val 可参与次数
	 */
	public void setLucky_num(Integer lucky_num) {
		this.lucky_num = lucky_num;
	}
	
	/**
	 * @val 是否发布： 0未发布，1已发布
	 */
	public Integer getIs_pub() {
		return is_pub;
	}
	
	/**
	 * @val 是否发布： 0未发布，1已发布
	 */
	public void setIs_pub(Integer is_pub) {
		this.is_pub = is_pub;
	}
	
	/**
	 * @val 所属系统:1工卡 2触网3会员4顺丰
	 */
	public Integer getOwn_sys() {
		return own_sys;
	}
	
	/**
	 * @val 所属系统:1工卡 2触网3会员4顺丰
	 */
	public void setOwn_sys(Integer own_sys) {
		this.own_sys = own_sys;
	}
	
	/**
	 * @val 是否需要收货地址0:不需要填写1需要填写
	 */
	public Integer getNeed_addr() {
		return need_addr;
	}
	
	/**
	 * @val 是否需要收货地址0:不需要填写1需要填写
	 */
	public void setNeed_addr(Integer need_addr) {
		this.need_addr = need_addr;
	}
	
	/**
	 * @val 是否需要填写手机
	 */
	public Integer getNeed_mobile() {
		return need_mobile;
	}
	
	/**
	 * @val 是否需要填写手机
	 */
	public void setNeed_mobile(Integer need_mobile) {
		this.need_mobile = need_mobile;
	}
	
	/**
	 * @val 关联商品ID
	 */
	public Long getGoods_id() {
		return goods_id;
	}
	
	/**
	 * @val 关联商品ID
	 */
	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}
	
	/**
	 * @val 关联商品名称
	 */
	public String getGoods_name() {
		return goods_name;
	}
	
	/**
	 * @val 关联商品名称
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	/**
	 * @val 活动状态:0未开始 ，1进行中，2已结束
	 */
	public Integer getLucky_state() {
		return lucky_state;
	}
	
	/**
	 * @val 活动状态:0未开始 ，1进行中，2已结束
	 */
	public void setLucky_state(Integer lucky_state) {
		this.lucky_state = lucky_state;
	}
	
	/**
	 * @val 活动地区：默认null表示全国
	 */
	public Long getP_index() {
		return p_index;
	}
	
	/**
	 * @val 活动地区：默认null表示全国
	 */
	public void setP_index(Long p_index) {
		this.p_index = p_index;
	}
	
	/**
	 * @val 排序值：desc排序
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值：desc排序
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
	/**
	 * @val 开奖信息
	 */
	public String getLucky_msg() {
		return lucky_msg;
	}
	
	/**
	 * @val 开奖信息
	 */
	public void setLucky_msg(String lucky_msg) {
		this.lucky_msg = lucky_msg;
	}
	
}