package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public class EcGiftOrde extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long gift_id;

	private Integer order_from;

	private String trade_index;

	private Long dept_id;

	private Integer state;

	private Long opr_dept_id;

	private Long integral;

	private Long order_user_id;

	private String order_user_name;

	private String buyer_name;

	private Long buyer_p_index;

	private String buyer_addr;

	private String buyer_zip;

	private String buyer_tel;

	private String buyer_mp;

	private Integer deliver_way;

	private Integer deliver_time;

	private Integer deliver_is_call;

	private String remark;

	private String dept_remark;

	private Date add_date;

	private Integer is_del;

	private Date del_date;

	private EcUserScoreRec ecUserScoreRec;

	private	EcGift ecGift;

	public EcGiftOrde() {

	}

	/**
	 * @val 订单ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @val 订单ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @val 积分商品ID
	 */
	public Long getGift_id() {
		return gift_id;
	}

	/**
	 * @val 积分商品ID
	 */
	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}

	/**
	 * @val 订单来源：1-工卡，2-触网，3-会员
	 */
	public Integer getOrder_from() {
		return order_from;
	}

	/**
	 * @val 订单来源：1-工卡，2-触网，3-会员
	 */
	public void setOrder_from(Integer order_from) {
		this.order_from = order_from;
	}

	/**
	 * @val 交易流水号
	 */
	public String getTrade_index() {
		return trade_index;
	}

	/**
	 * @val 交易流水号
	 */
	public void setTrade_index(String trade_index) {
		this.trade_index = trade_index;
	}

	/**
	 * @val 所属分公司ID：如果积分商品是分公司添加的则为分公司ID，否则为空
	 */
	public Long getDept_id() {
		return dept_id;
	}

	/**
	 * @val 所属分公司ID：如果积分商品是分公司添加的则为分公司ID，否则为空
	 */
	public void setDept_id(Long dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 0-已预订
	 * @val 10-已兑换
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @val 订单状态：
	 * @val -30-已退货
	 * @val -20-审核未通过
	 * @val -10-已关闭
	 * @val 0-已预订
	 * @val 10-已兑换
	 * @val 20-审核通过
	 * @val 30-下发处理
	 * @val 40-商家发货
	 * @val 50-客户已换货
	 * @val 60-确认收货
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @val 正在处理分部门ID：下单的时候ID为总部，如果下发则修改为需要处理的部门ID
	 */
	public Long getOpr_dept_id() {
		return opr_dept_id;
	}

	/**
	 * @val 正在处理分部门ID：下单的时候ID为总部，如果下发则修改为需要处理的部门ID
	 */
	public void setOpr_dept_id(Long opr_dept_id) {
		this.opr_dept_id = opr_dept_id;
	}

	/**
	 * @val 订单积分
	 */
	public Long getIntegral() {
		return integral;
	}

	/**
	 * @val 订单积分
	 */
	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	/**
	 * @val 下单用户ID
	 */
	public Long getOrder_user_id() {
		return order_user_id;
	}

	/**
	 * @val 下单用户ID
	 */
	public void setOrder_user_id(Long order_user_id) {
		this.order_user_id = order_user_id;
	}

	/**
	 * @val 下单人姓名
	 */
	public String getOrder_user_name() {
		return order_user_name;
	}

	/**
	 * @val 下单人姓名
	 */
	public void setOrder_user_name(String order_user_name) {
		this.order_user_name = order_user_name;
	}

	/**
	 * @val 兑换人姓名
	 */
	public String getBuyer_name() {
		return buyer_name;
	}

	/**
	 * @val 兑换人姓名
	 */
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	/**
	 * @val 兑换人地区
	 */
	public Long getBuyer_p_index() {
		return buyer_p_index;
	}

	/**
	 * @val 兑换人地区
	 */
	public void setBuyer_p_index(Long buyer_p_index) {
		this.buyer_p_index = buyer_p_index;
	}

	/**
	 * @val 兑换人地址
	 */
	public String getBuyer_addr() {
		return buyer_addr;
	}

	/**
	 * @val 兑换人地址
	 */
	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}

	/**
	 * @val 兑换人邮编
	 */
	public String getBuyer_zip() {
		return buyer_zip;
	}

	/**
	 * @val 兑换人邮编
	 */
	public void setBuyer_zip(String buyer_zip) {
		this.buyer_zip = buyer_zip;
	}

	/**
	 * @val 兑换人固定电话
	 */
	public String getBuyer_tel() {
		return buyer_tel;
	}

	/**
	 * @val 兑换人固定电话
	 */
	public void setBuyer_tel(String buyer_tel) {
		this.buyer_tel = buyer_tel;
	}

	/**
	 * @val 兑换人手机号码（*）
	 */
	public String getBuyer_mp() {
		return buyer_mp;
	}

	/**
	 * @val 兑换人手机号码（*）
	 */
	public void setBuyer_mp(String buyer_mp) {
		this.buyer_mp = buyer_mp;
	}

	/**
	 * @val 发货方式
	 * @val 0：上门自提
	 * @val 1：商家配送
	 */
	public Integer getDeliver_way() {
		return deliver_way;
	}

	/**
	 * @val 发货方式
	 * @val 0：上门自提
	 * @val 1：商家配送
	 */
	public void setDeliver_way(Integer deliver_way) {
		this.deliver_way = deliver_way;
	}

	/**
	 * @val 发货时间设置
	 * @val 0：只工作日送货（双休日、假日不用送）
	 * @val 1：工作日、双休日与假日均可送货
	 * @val 2：只双休日、假日送货（工作日不送货）
	 */
	public Integer getDeliver_time() {
		return deliver_time;
	}

	/**
	 * @val 发货时间设置
	 * @val 0：只工作日送货（双休日、假日不用送）
	 * @val 1：工作日、双休日与假日均可送货
	 * @val 2：只双休日、假日送货（工作日不送货）
	 */
	public void setDeliver_time(Integer deliver_time) {
		this.deliver_time = deliver_time;
	}

	/**
	 * @val 发货前电话确认
	 * @val 0：否
	 * @val 1：是
	 */
	public Integer getDeliver_is_call() {
		return deliver_is_call;
	}

	/**
	 * @val 发货前电话确认
	 * @val 0：否
	 * @val 1：是
	 */
	public void setDeliver_is_call(Integer deliver_is_call) {
		this.deliver_is_call = deliver_is_call;
	}

	/**
	 * @val 订单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @val 订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @val 企业备注
	 */
	public String getDept_remark() {
		return dept_remark;
	}

	/**
	 * @val 企业备注
	 */
	public void setDept_remark(String dept_remark) {
		this.dept_remark = dept_remark;
	}

	/**
	 * @val 订单入库时间
	 */
	public Date getAdd_date() {
		return add_date;
	}

	/**
	 * @val 订单入库时间
	 */
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}

	/**
	 * @val 是否删除
	 */
	public Integer getIs_del() {
		return is_del;
	}

	/**
	 * @val 是否删除
	 */
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	/**
	 * @val 删除时间
	 */
	public Date getDel_date() {
		return del_date;
	}

	/**
	 * @val 删除时间
	 */
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}

	public EcUserScoreRec getEcUserScoreRec() {
		return ecUserScoreRec;
	}

	public void setEcUserScoreRec(EcUserScoreRec ecUserScoreRec) {
		this.ecUserScoreRec = ecUserScoreRec;
	}
	
	public EcGift getEcGift() {
		return ecGift;
	}

	public void setEcGift(EcGift ecGift) {
		this.ecGift = ecGift;
	}

}