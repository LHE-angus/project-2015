package com.ebiz.mmt.web.struts.inter.form;

import java.io.Serializable;

/**
 * @author Xiao,GuoJian
 * @since 2014-09-23
 * @see 订单查询页面
 */
public class KonkaOrderInfoSearch extends BaseInterForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	private Long id;// 订单ID

	private String trade_index;// 交易流水号

	private String order_date;// 提交日期

	private String r3_code;// R3客户编码

	private String customer_name;// 客户名称

	private String customer_type_name;// 客户类型

	private Long order_num;// 数量

	private String money;// 金额￥

	private String good_discount_price;// 折让￥

	private String order_state;// 订单状态

	private Long r3_id;// r3系统返回的订单号

	private String is_change;// 订单变更 0-未变更 1-变更未同步 2-变更并同步

	private String is_delivered;// 发货状态 0-未发货 1-已发货

	private String vbedl;// R3物流单号

	private String shipping_date;// 发货时间

	private String receiving_date;// 收货时间

	private String branch_name;// 分公司名称

	private String handle_name;// 经办名称

	private String message_confirm_state;// 短信变更状态

	private String process_name;// 流程

	private String order_type;// 订单来源 0-在线下单 1-图片下单 2-触网转单 4-从返利转

	private String add_date;// 创建日期

	private String next_audit_role_name;// 待审核角色

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
	 * @val 交易流水号
	 */
	public String getTrade_index() {
		return trade_index;
	}

	/**
	 * @val 交易流水号
	 */
	public void setTrade_index(String tradeIndex) {
		trade_index = tradeIndex;
	}

	/**
	 * @val 提交日期
	 */
	public String getOrder_date() {
		return order_date;
	}

	/**
	 * @val 提交日期
	 */
	public void setOrder_date(String orderDate) {
		order_date = orderDate;
	}

	/**
	 * @val R3客户编码
	 */
	public String getR3_code() {
		return r3_code;
	}

	/**
	 * @val R3客户编码
	 */
	public void setR3_code(String r3Code) {
		r3_code = r3Code;
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
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}

	/**
	 * @val 客户类型
	 */
	public String getCustomer_type_name() {
		return customer_type_name;
	}

	/**
	 * @val 客户类型
	 */
	public void setCustomer_type_name(String customerTypeName) {
		customer_type_name = customerTypeName;
	}

	/**
	 * @val 数量
	 */
	public Long getOrder_num() {
		return order_num;
	}

	/**
	 * @val 数量
	 */
	public void setOrder_num(Long orderNum) {
		order_num = orderNum;
	}

	/**
	 * @val 金额￥
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @val 金额￥
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @val 折让￥
	 */
	public String getGood_discount_price() {
		return good_discount_price;
	}

	/**
	 * @val 折让￥
	 */
	public void setGood_discount_price(String goodDiscountPrice) {
		good_discount_price = goodDiscountPrice;
	}

	/**
	 * @val 待审核角色
	 */
	public String getNext_audit_role_name() {
		return next_audit_role_name;
	}

	/**
	 * @val 待审核角色
	 */
	public void setNext_audit_role_name(String nextAuditRoleName) {
		next_audit_role_name = nextAuditRoleName;
	}

	/**
	 * @val 订单状态
	 */
	public String getOrder_state() {
		return order_state;
	}

	/**
	 * @val 订单状态
	 */
	public void setOrder_state(String orderState) {
		order_state = orderState;
	}

	/**
	 * @val r3系统返回的订单号
	 */
	public Long getR3_id() {
		return r3_id;
	}

	/**
	 * @val r3系统返回的订单号
	 */
	public void setR3_id(Long r3Id) {
		r3_id = r3Id;
	}

	/**
	 * @val 订单变更 0-未变更 1-变更未同步 2-变更并同步
	 */
	public String getIs_change() {
		return is_change;
	}

	/**
	 * @val 订单变更 0-未变更 1-变更未同步 2-变更并同步
	 */
	public void setIs_change(String isChange) {
		is_change = isChange;
	}

	/**
	 * @val 发货状态 0-未发货 1-已发货
	 */
	public String getIs_delivered() {
		return is_delivered;
	}

	/**
	 * @val 发货状态 0-未发货 1-已发货
	 */
	public void setIs_delivered(String isDelivered) {
		is_delivered = isDelivered;
	}

	/**
	 * @val R3物流单号
	 */
	public String getVbedl() {
		return vbedl;
	}

	/**
	 * @val R3物流单号
	 */
	public void setVbedl(String vbedl) {
		this.vbedl = vbedl;
	}

	/**
	 * @val 发货时间
	 */
	public String getShipping_date() {
		return shipping_date;
	}

	/**
	 * @val 发货时间
	 */
	public void setShipping_date(String shippingDate) {
		shipping_date = shippingDate;
	}

	/**
	 * @val 收货时间
	 */
	public String getReceiving_date() {
		return receiving_date;
	}

	/**
	 * @val 收货时间
	 */
	public void setReceiving_date(String receivingDate) {
		receiving_date = receivingDate;
	}

	/**
	 * @val 分公司名称
	 */
	public String getBranch_name() {
		return branch_name;
	}

	/**
	 * @val 分公司名称
	 */
	public void setBranch_name(String branchName) {
		branch_name = branchName;
	}

	/**
	 * @val 经办名称
	 */
	public String getHandle_name() {
		return handle_name;
	}

	/**
	 * @val 经办名称
	 */
	public void setHandle_name(String handleName) {
		handle_name = handleName;
	}

	/**
	 * @val 短信变更状态
	 */
	public String getMessage_confirm_state() {
		return message_confirm_state;
	}

	/**
	 * @val 短信变更状态
	 */
	public void setMessage_confirm_state(String messageConfirmState) {
		message_confirm_state = messageConfirmState;
	}

	/**
	 * @val 流程
	 */
	public String getProcess_name() {
		return process_name;
	}

	/**
	 * @val 流程
	 */
	public void setProcess_name(String processName) {
		process_name = processName;
	}

	/**
	 * @val 订单来源 0-在线下单 1-图片下单 2-触网转单 4-从返利转
	 */
	public String getOrder_type() {
		return order_type;
	}

	/**
	 * @val 订单来源 0-在线下单 1-图片下单 2-触网转单 4-从返利转
	 */
	public void setOrder_type(String orderType) {
		order_type = orderType;
	}

	/**
	 * @val 创建日期
	 */
	public String getAdd_date() {
		return add_date;
	}

	/**
	 * @val 创建日期
	 */
	public void setAdd_date(String addDate) {
		add_date = addDate;
	}
}
