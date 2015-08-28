package com.sf.integration.warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TUDP
 * @version 2013-11-25
 * @desc 仓库接口 销售订单接口
 */
public class OrderData {

	private String root = "";// 出库单（销售订单）接口提供给客户系统向顺丰仓储系统发送出库单数据
	private String checkword = "";// 校验码
	private String company = "";// 货主company
	private String warehouse = "";// 仓库warehouse
	private String shop_name = "";// 商家店铺名称 shop_name
	private String erp_order = "";// 订单号码 erp_order
	private String order_type = "";// 订单类型 order_type
	private String order_date = "";// 下单日期 order_date
	private String ship_from_name = "";// 寄件公司 ship_from_name
	private String ship_from_attention_to = "";// 寄件人 ship_from_attention_to
	private String ship_from_country = "";// 寄件国家或地区 ship_from_country
	private String ship_from_province = "";// 寄件省 ship_from_province
	private String ship_from_city = "";// 寄件市 ship_from_city
	private String ship_from_area = "";// 寄件区/县 ship_from_area
	private String ship_from_address = "";// 寄件地址 ship_from_address
	private String ship_from_postal_code = "";// 寄件邮编 ship_from_postal_code
	private String ship_from_phone_num = "";// 寄件手机 ship_from_phone_num
	private String ship_from_tel_num = "";// 寄件固定电话 ship_from_tel_num
	private String ship_from_email_address = "";// 寄件邮箱地址
	// ship_from_email_address
	private String ship_to_name = "";// 寄件公司 ship_to_name
	private String ship_to_attention_to = "";// 寄件人 ship_to_attention_to
	private String ship_to_country = "";// 寄件国家或地区 ship_to_country
	private String ship_to_province = "";// 寄件省 ship_to_province
	private String ship_to_city = "";// 寄件市 ship_to_city
	private String ship_to_area = "";// 寄件区/县 ship_to_area
	private String ship_to_address = "";// 寄件地址 ship_to_address
	private String ship_to_postal_code = "";// 寄件邮编 ship_to_postal_code
	private String ship_to_phone_num = "";// 寄件手机 ship_to_phone_num
	private String ship_to_tel_num = "";// 寄件固定电话 ship_to_tel_num
	private String ship_to_email_address = "";// 寄件邮箱地址 ship_to_email_address
	private String carrier = "";// 承运商 carrier
	private String carrier_service = "";// 承运商服务类型 carrier_service
	private String route_numbers = "";// 路线编号 route_numbers
	private String packing_note = "";// 货品包装备注 packing_note
	private String complete_delivery = "";// 需整单发货 complete_delivery
	private String freight = "";// 运费 freight
	private String payment_of_charge = "";// 货主运费付款方式 payment_of_charge
	private String payment_district = "";// 付款地区：城市名称 payment_district
	private String cod = "";// 是否货到付款 cod
	private String amount = "";// 代收货款金额 amount
	private String self_pickup = "";// 是否上门自取 self_pickup
	private String value_insured = "";// 是否保价 value_insured
	private String declared_value = "";// 声明价值 declared_value
	private String return_receipt_service = "";// 签回单 return_receipt_service
	private String delivery_date = "";// 发货日期 delivery_date
	private String delivery_requested = "";// 配送要求 delivery_requested
	private String invoice = "";// 需要发票 invoice
	private String invoice_type = "";// 发票类型 invoice_type
	private String invoice_title = "";// 发票抬头 invoice_title
	private String invoice_content = "";// 发票内容 invoice_content
	private String order_note = "";// 订单备注 order_note
	private String company_note = "";// 商家备注 company_note
	private String priority = "";// 订单优先级 priority
	private String order_total_amount = "";// 订单总金额 order_total_amount
	private String order_discount = "";// 订单优惠金额 order_discount
	private String balance_amount = "";// 余额支付金额 balance_amount
	private String coupons_amount = "";// 优惠卷金额 coupons_amount
	private String gift_card_amount = "";// 礼品卡金额 gift_card_amount
	private String other_charge = "";// 其它金额 other_charge
	private String actual_amount = "";// 实际支付金额 actual_amount
	private String customer_payment_method = "";// 客户付款方式
	// customer_payment_method
	private String monthly_account = "";// 月结账号 monthly_account
	private String from_flag = "";// 寄件方标识 from_flag
	private String user_def1 = "";// 预留字段 1 user_def1
	private String user_def2 = "";// 预留字段2 user_def2
	private String user_def3 = "";// 预留字段3 user_def3
	private String user_def4 = "";// 预留字段4 user_def4
	private String user_def5 = "";// 预留字段5 user_def5
	private String user_def6 = "";// 预留字段6 user_def6
	private String user_def7 = "";// 预留字段 7 user_def7
	private String user_def8 = "";// 预留字段8 user_def8
	private String user_def9 = "";// 预留字段9 user_def9
	private String user_def10 = "";// 预留字段 10 user_def10
	private String user_def11 = "";// 预留字段 11 user_def11
	private String user_def12 = "";// 预留字段 12 user_def12
	private String user_def13 = "";// 预留字段 13 user_def13
	private String user_def14 = "";// 预留字段 14 user_def14
	private String user_def15 = "";// 预留字段 15 user_def15
	private String user_def16 = "";// 预留字段 16 user_def16
	private String user_def17 = "";// 预留字段 17 user_def17
	private String user_def18 = "";// 预留字段 18 user_def18
	private String user_def19 = "";// 预留字段 19 user_def19
	private String user_def20 = "";// 预留字段20 user_def20

	private List<OrderDataDetail> detailList = new ArrayList<OrderDataDetail>();

	public OrderData() {

	}

	/**
	 * @数据初始化 校验码, 货主company,仓库warehouse,月结账号 monthly_account
	 */
	public OrderData(String checkword, String company, String warehouse, String monthly_account) {
		this.checkword = checkword;
		this.company = company;
		this.warehouse = warehouse;
		this.monthly_account = monthly_account;
	}

	/**
	 * 入库单接口提供给客户系统向顺丰仓储系统发送入库单数据
	 * 
	 * @see 根目录非订单数据
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * 入库单接口提供给客户系统向顺丰仓储系统发送入库单数据
	 * 
	 * @see 根目录非订单数据
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * 校验码
	 * 
	 * @是否必须 Y
	 */
	public String getCheckword() {
		return checkword;
	}

	/**
	 * 校验码
	 * 
	 * @是否必须 Y
	 */
	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}

	/**
	 * 货主company
	 * 
	 * @是否必须 Y
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 货主company
	 * 
	 * @是否必须 Y
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 仓库warehouse
	 */
	public String getWarehouse() {
		return warehouse;
	}

	/**
	 * 仓库warehouse
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * 商家店铺名称 shop_name
	 */
	public String getShop_name() {
		return shop_name;
	}

	/**
	 * 商家店铺名称 shop_name
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	/**
	 * 订单号码 erp_order
	 * 
	 * @是否必须 Y
	 */
	public String getErp_order() {
		return erp_order;
	}

	/**
	 * 订单号码 erp_order
	 * 
	 * @是否必须 Y
	 */
	public void setErp_order(String erp_order) {
		this.erp_order = erp_order;
	}

	/**
	 * 订单类型 order_type 销售订单,返厂订单,换货订单,调拨订单,虚拟订单余量订单, 正常出库, 转储订单, 售后订单 ， 如果为空 ，
	 * 则系统默认 为销售订单
	 */
	public String getOrder_type() {
		return order_type;
	}

	/**
	 * 订单类型 order_type 销售订单,返厂订单,换货订单,调拨订单,虚拟订单余量订单, 正常出库, 转储订单, 售后订单 ， 如果为空 ，
	 * 则系统默认 为销售订单
	 */
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	/**
	 * 下单日期 order_date
	 * 
	 * @是否必须 Y
	 */
	public String getOrder_date() {
		return order_date;
	}

	/**
	 * 下单日期 order_date
	 * 
	 * @是否必须 Y
	 */
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	/**
	 * 寄件公司
	 */
	public String getShip_from_name() {
		return ship_from_name;
	}

	/**
	 * 寄件公司
	 */
	public void setShip_from_name(String ship_from_name) {
		this.ship_from_name = ship_from_name;
	}

	/**
	 * 寄件人
	 */
	public String getShip_from_attention_to() {
		return ship_from_attention_to;
	}

	/**
	 * 寄件人
	 */
	public void setShip_from_attention_to(String ship_from_attention_to) {
		this.ship_from_attention_to = ship_from_attention_to;
	}

	/**
	 * 寄件在国 家 或 地区
	 */
	public String getShip_from_country() {
		return ship_from_country;
	}

	/**
	 * 寄件在国 家 或 地区
	 */
	public void setShip_from_country(String ship_from_country) {
		this.ship_from_country = ship_from_country;
	}

	/**
	 * 寄件所在省
	 */
	public String getShip_from_province() {
		return ship_from_province;
	}

	/**
	 * 寄件所在省
	 */
	public void setShip_from_province(String ship_from_province) {
		this.ship_from_province = ship_from_province;
	}

	/**
	 * 寄件所在市
	 */
	public String getShip_from_city() {
		return ship_from_city;
	}

	/**
	 * 寄件所在市
	 */
	public void setShip_from_city(String ship_from_city) {
		this.ship_from_city = ship_from_city;
	}

	/**
	 * 寄件所在区/县
	 */
	public String getShip_from_area() {
		return ship_from_area;
	}

	/**
	 * 寄件所在区/县
	 */
	public void setShip_from_area(String ship_from_area) {
		this.ship_from_area = ship_from_area;
	}

	/**
	 * 寄件地址 100 寄件方标识 为 Y 时，必 填
	 */
	public String getShip_from_address() {
		return ship_from_address;
	}

	/**
	 * 寄件地址 100 寄件方标识 为 Y 时，必 填
	 */
	public void setShip_from_address(String ship_from_address) {
		this.ship_from_address = ship_from_address;
	}

	/**
	 * 寄件邮编
	 */
	public String getShip_from_postal_code() {
		return ship_from_postal_code;
	}

	/**
	 * 寄件邮编
	 */
	public void setShip_from_postal_code(String ship_from_postal_code) {
		this.ship_from_postal_code = ship_from_postal_code;
	}

	/**
	 * 寄件手机 20 寄件方标识为 Y 时，必 填
	 */
	public String getShip_from_phone_num() {
		return ship_from_phone_num;
	}

	/**
	 * 寄件手机 20 寄件方标识为 Y 时，必 填
	 */
	public void setShip_from_phone_num(String ship_from_phone_num) {
		this.ship_from_phone_num = ship_from_phone_num;
	}

	/**
	 * 固定电话字节长度20
	 */
	public String getShip_from_tel_num() {
		return ship_from_tel_num;
	}

	/**
	 * 固定电话字节长度20
	 */
	public void setShip_from_tel_num(String ship_from_tel_num) {
		this.ship_from_tel_num = ship_from_tel_num;
	}

	/**
	 * 寄件人邮箱 25
	 */
	public String getShip_from_email_address() {
		return ship_from_email_address;
	}

	/**
	 * 寄件人邮箱 25
	 */
	public void setShip_from_email_address(String ship_from_email_address) {
		this.ship_from_email_address = ship_from_email_address;
	}

	/**
	 * 收件人 公司 ship_to_name
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_name() {
		return ship_to_name;
	}

	/**
	 * 收件人 公司 ship_to_name
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_name(String ship_to_name) {
		this.ship_to_name = ship_to_name;
	}

	/**
	 * 收件人
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_attention_to() {
		return ship_to_attention_to;
	}

	/**
	 * 收件人
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_attention_to(String ship_to_attention_to) {
		this.ship_to_attention_to = ship_to_attention_to;
	}

	/**
	 * 收件人所在国 家 或 地区 ：如果为 空,则系统默 认为中国 收件人所在
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_country() {
		return ship_to_country;
	}

	/**
	 * 收件人所在国 家 或 地区 ：如果为 空,则系统默 认为中国 收件人所在
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_country(String ship_to_country) {
		this.ship_to_country = ship_to_country;
	}

	/**
	 * 收件人所在省
	 */
	public String getShip_to_province() {
		return ship_to_province;
	}

	/**
	 * 收件人所在省
	 */
	public void setShip_to_province(String ship_to_province) {
		this.ship_to_province = ship_to_province;
	}

	/**
	 * 收件人所在市
	 */
	public String getShip_to_city() {
		return ship_to_city;
	}

	/**
	 * 收件人所在市
	 */
	public void setShip_to_city(String ship_to_city) {
		this.ship_to_city = ship_to_city;
	}

	/**
	 * 收件人所在区/县
	 */
	public String getShip_to_area() {
		return ship_to_area;
	}

	/**
	 * 收件人所在区/县
	 */
	public void setShip_to_area(String ship_to_area) {
		this.ship_to_area = ship_to_area;
	}

	/**
	 * 收件人详细地址
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_address() {
		return ship_to_address;
	}

	/**
	 * 收件人详细地址
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_address(String ship_to_address) {
		this.ship_to_address = ship_to_address;
	}

	/**
	 * 地址邮编 ， 如果为空 ，则系统默认 为 “-”
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_postal_code() {
		return ship_to_postal_code;
	}

	/**
	 * 地址邮编 ， 如果为空 ，则系统默认 为 “-”
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_postal_code(String ship_to_postal_code) {
		this.ship_to_postal_code = ship_to_postal_code;
	}

	/**
	 * 手机字节长度20
	 * 
	 * @是否必须 Y
	 */
	public String getShip_to_phone_num() {
		return ship_to_phone_num;
	}

	/**
	 * 手机字节长度20
	 * 
	 * @是否必须 Y
	 */
	public void setShip_to_phone_num(String ship_to_phone_num) {
		this.ship_to_phone_num = ship_to_phone_num;
	}

	/**
	 * 固定电话字节长度20
	 */
	public String getShip_to_tel_num() {
		return ship_to_tel_num;
	}

	/**
	 * 固定电话字节长度20
	 */
	public void setShip_to_tel_num(String ship_to_tel_num) {
		this.ship_to_tel_num = ship_to_tel_num;
	}

	/**
	 * 收件人邮箱 25
	 */
	public String getShip_to_email_address() {
		return ship_to_email_address;
	}

	/**
	 * 收件人邮箱 25
	 */
	public void setShip_to_email_address(String ship_to_email_address) {
		this.ship_to_email_address = ship_to_email_address;
	}

	/**
	 * 承运商 如果为空 ，系统默认为 顺丰速运
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * 承运商 如果为空 ，系统默认为 顺丰速运
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * 承运商服务类型如果为空 ， 系统默认为 标准快递
	 */
	public String getCarrier_service() {
		return carrier_service;
	}

	/**
	 * 承运商服务类型如果为空 ， 系统默认为 标准快递
	 */
	public void setCarrier_service(String carrier_service) {
		this.carrier_service = carrier_service;
	}

	/**
	 * 路线编号 默认为空
	 */
	public String getRoute_numbers() {
		return route_numbers;
	}

	/**
	 * 路线编号 默认为空
	 */
	public void setRoute_numbers(String route_numbers) {
		this.route_numbers = route_numbers;
	}

	/**
	 * 货品包装备注 数据样例：采用礼品包 装，易碎品，高价值 ，需特殊包装
	 */
	public String getPacking_note() {
		return packing_note;
	}

	/**
	 *货品包装备注 数据样例：采用礼品包 装，易碎品，高价值 ，需特殊包装
	 */
	public void setPacking_note(String packing_note) {
		this.packing_note = packing_note;
	}

	/**
	 * 需整单发货 Y/N: 如果为空,系统默认为Y
	 */
	public String getComplete_delivery() {
		return complete_delivery;
	}

	/**
	 * 需整单发货 Y/N: 如果为空,系统默认为Y
	 */
	public void setComplete_delivery(String complete_delivery) {
		this.complete_delivery = complete_delivery;
	}

	/**
	 * 运费 19,5 收件人应支付货主运费 客户支付顺 丰运费方式
	 */
	public String getFreight() {
		return freight;
	}

	/**
	 * 运费 19,5 收件人应支付货主运费 客户支付顺 丰运费方式
	 */
	public void setFreight(String freight) {
		this.freight = freight;
	}

	/**
	 * 货主运费付款方式 收方付 、第 三方付 ：如果为空 ，系 统默认为寄 付
	 */
	public String getPayment_of_charge() {
		return payment_of_charge;
	}

	/**
	 * 货主运费付款方式 收方付 、第 三方付 ：如果为空 ，系 统默认为寄 付
	 */
	public void setPayment_of_charge(String payment_of_charge) {
		this.payment_of_charge = payment_of_charge;
	}

	/**
	 * 付款地区：城市名称第三方付必 须提供 Y/N: 如果为空 ，系统默 认为N
	 */
	public String getPayment_district() {
		return payment_district;
	}

	/**
	 * 付款地区：城市名称第三方付必 须提供 Y/N: 如果为空 ，系统默 认为N
	 */
	public void setPayment_district(String payment_district) {
		this.payment_district = payment_district;
	}

	/**
	 * 是否货到付款Y/N: 如果为认为N
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * 是否货到付款Y/N: 如果为认为N
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}

	/**
	 * 代收货款金额 当是否货到付 款为 Y时 , 则必须 填写代收货 款金额
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * 代收货款金额 当是否货到付 款为 Y时 , 则必须 填写代收货 款金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * 是否上门自取 Y/N: 如果为 空 ，系统默 N
	 */
	public String getSelf_pickup() {
		return self_pickup;
	}

	/**
	 * 是否上门自取 Y/N: 如果为 空 ，系统默 N
	 */
	public void setSelf_pickup(String self_pickup) {
		this.self_pickup = self_pickup;
	}

	/**
	 * 是否保价Y/N: 如果为 空 ，系统默 N
	 */
	public String getValue_insured() {
		return value_insured;
	}

	/**
	 * 是否保价Y/N: 如果为 空 ，系统默 N
	 */
	public void setValue_insured(String value_insured) {
		this.value_insured = value_insured;
	}

	/**
	 * 声明价值 25 如果是否保 价为 Y，则该 项 必 须 输 入 ，否则可 允许为空
	 */
	public String getDeclared_value() {
		return declared_value;
	}

	/**
	 * 声明价值 25 如果是否保 价为 Y，则该 项 必 须 输 入 ，否则可 允许为空
	 */
	public void setDeclared_value(String declared_value) {
		this.declared_value = declared_value;
	}

	/**
	 * 签回单 Y/N: 如果为空 ，系统默 认为N
	 */
	public String getReturn_receipt_service() {
		return return_receipt_service;
	}

	/**
	 * 签回单 Y/N: 如果为空 ，系统默 认为N
	 */
	public void setReturn_receipt_service(String return_receipt_service) {
		this.return_receipt_service = return_receipt_service;
	}

	/**
	 * 收件人或货 主指定的发 货日期, 不 能小于下单 时间
	 */
	public String getDelivery_date() {
		return delivery_date;
	}

	/**
	 * 收件人或货 主指定的发 货日期, 不 能小于下单时间
	 */
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	/**
	 * 配送要求25 数据样例 ：周一至周五可配送 ,周 一 至周 五 、周末均可配送,仅周末配送 ,仅限白天配送
	 */
	public String getDelivery_requested() {
		return delivery_requested;
	}

	/**
	 * 配送要求25 数据样例 ：周一至周五可配送 ,周 一 至周 五 、周末均可配送,仅周末配送 ,仅限白天配送
	 */
	public void setDelivery_requested(String delivery_requested) {
		this.delivery_requested = delivery_requested;
	}

	/**
	 * 需要发票Y/N: 默认为 N ， 如 果 为 Y，需填写发 票相关字段 信息
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * 需要发票Y/N: 默认为 N ， 如 果 为 Y，需填写发 票相关字段 信息
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	/**
	 * 发票类型值 ：普通发票/增值税发 票
	 */
	public String getInvoice_type() {
		return invoice_type;
	}

	/**
	 * 发票类型值 ：普通发票/增值税发 票
	 */
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	/**
	 * 发票抬头 25
	 */
	public String getInvoice_title() {
		return invoice_title;
	}

	/**
	 * 发票抬头 25
	 */
	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	/**
	 * 发票内容 50 值 ： 明 细 （固定值）/ 自定义内容
	 */
	public String getInvoice_content() {
		return invoice_content;
	}

	/**
	 * 发票内容 50 值 ： 明 细 （固定值）/ 自定义内容
	 */
	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}

	/**
	 * 订单备注100 收件人备注 信息
	 */
	public String getOrder_note() {
		return order_note;
	}

	/**
	 * 订单备注100 收件人备注 信息
	 */
	public void setOrder_note(String order_note) {
		this.order_note = order_note;
	}

	/**
	 * 商家备注信息 500
	 */
	public String getCompany_note() {
		return company_note;
	}

	/**
	 * 商家备注信息 500
	 */
	public void setCompany_note(String company_note) {
		this.company_note = company_note;
	}

	/**
	 * 订单优先级 允许为空 , 值 范 围 1- 99 ，数值越 小 ,优先级 越高
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * 订单优先级 允许为空 , 值 范 围 1- 99 ，数值越 小 ,优先级 越高
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * 订单总金额 19,5
	 */
	public String getOrder_total_amount() {
		return order_total_amount;
	}

	/**
	 * 订单总金额 19,5
	 */
	public void setOrder_total_amount(String order_total_amount) {
		this.order_total_amount = order_total_amount;
	}

	/**
	 * 订单优惠金
	 */
	public String getOrder_discount() {
		return order_discount;
	}

	/**
	 * 订单优惠金
	 */
	public void setOrder_discount(String order_discount) {
		this.order_discount = order_discount;
	}

	/**
	 * 余额支付金额
	 */
	public String getBalance_amount() {
		return balance_amount;
	}

	/**
	 * 余额支付金额
	 */
	public void setBalance_amount(String balance_amount) {
		this.balance_amount = balance_amount;
	}

	/**
	 * 优惠卷金额
	 */
	public String getCoupons_amount() {
		return coupons_amount;
	}

	/**
	 * 优惠卷金额
	 */
	public void setCoupons_amount(String coupons_amount) {
		this.coupons_amount = coupons_amount;
	}

	/**
	 * 礼品卡金额
	 */
	public String getGift_card_amount() {
		return gift_card_amount;
	}

	/**
	 * 礼品卡金额
	 */
	public void setGift_card_amount(String gift_card_amount) {
		this.gift_card_amount = gift_card_amount;
	}

	/**
	 * 其它金额
	 */
	public String getOther_charge() {
		return other_charge;
	}

	/**
	 * 其它金额
	 */
	public void setOther_charge(String other_charge) {
		this.other_charge = other_charge;
	}

	/**
	 * 实际支付金额
	 */
	public String getActual_amount() {
		return actual_amount;
	}

	/**
	 * 实际支付金额
	 */
	public void setActual_amount(String actual_amount) {
		this.actual_amount = actual_amount;
	}

	/**
	 * 客户付款方式
	 */
	public String getCustomer_payment_method() {
		return customer_payment_method;
	}

	/**
	 * 客户付款方式
	 */
	public void setCustomer_payment_method(String customer_payment_method) {
		this.customer_payment_method = customer_payment_method;
	}

	/**
	 * 月结账号 客户月结账号
	 */
	public String getMonthly_account() {
		return monthly_account;
	}

	/**
	 * 月结账号 客户月结账号
	 */
	public void setMonthly_account(String monthly_account) {
		this.monthly_account = monthly_account;
	}

	/**
	 * 收件方标识 from_flag Y/N
	 */
	public String getFrom_flag() {
		return from_flag;
	}

	/**
	 * 收件方标识 from_flag Y/N
	 */
	public void setFrom_flag(String from_flag) {
		this.from_flag = from_flag;
	}

	public String getUser_def1() {
		return user_def1;
	}

	public void setUser_def1(String user_def1) {
		this.user_def1 = user_def1;
	}

	public String getUser_def2() {
		return user_def2;
	}

	public void setUser_def2(String user_def2) {
		this.user_def2 = user_def2;
	}

	public String getUser_def3() {
		return user_def3;
	}

	public void setUser_def3(String user_def3) {
		this.user_def3 = user_def3;
	}

	public String getUser_def4() {
		return user_def4;
	}

	public void setUser_def4(String user_def4) {
		this.user_def4 = user_def4;
	}

	public String getUser_def5() {
		return user_def5;
	}

	public void setUser_def5(String user_def5) {
		this.user_def5 = user_def5;
	}

	public String getUser_def6() {
		return user_def6;
	}

	public void setUser_def6(String user_def6) {
		this.user_def6 = user_def6;
	}

	public String getUser_def7() {
		return user_def7;
	}

	public void setUser_def7(String user_def7) {
		this.user_def7 = user_def7;
	}

	public String getUser_def8() {
		return user_def8;
	}

	public void setUser_def8(String user_def8) {
		this.user_def8 = user_def8;
	}

	public String getUser_def9() {
		return user_def9;
	}

	public void setUser_def9(String user_def9) {
		this.user_def9 = user_def9;
	}

	public String getUser_def10() {
		return user_def10;
	}

	public void setUser_def10(String user_def10) {
		this.user_def10 = user_def10;
	}

	public String getUser_def11() {
		return user_def11;
	}

	public void setUser_def11(String user_def11) {
		this.user_def11 = user_def11;
	}

	public String getUser_def12() {
		return user_def12;
	}

	public void setUser_def12(String user_def12) {
		this.user_def12 = user_def12;
	}

	public String getUser_def13() {
		return user_def13;
	}

	public void setUser_def13(String user_def13) {
		this.user_def13 = user_def13;
	}

	public String getUser_def14() {
		return user_def14;
	}

	public void setUser_def14(String user_def14) {
		this.user_def14 = user_def14;
	}

	public String getUser_def15() {
		return user_def15;
	}

	public void setUser_def15(String user_def15) {
		this.user_def15 = user_def15;
	}

	public String getUser_def16() {
		return user_def16;
	}

	public void setUser_def16(String user_def16) {
		this.user_def16 = user_def16;
	}

	public String getUser_def17() {
		return user_def17;
	}

	public void setUser_def17(String user_def17) {
		this.user_def17 = user_def17;
	}

	public String getUser_def18() {
		return user_def18;
	}

	public void setUser_def18(String user_def18) {
		this.user_def18 = user_def18;
	}

	public String getUser_def19() {
		return user_def19;
	}

	public void setUser_def19(String user_def19) {
		this.user_def19 = user_def19;
	}

	public String getUser_def20() {
		return user_def20;
	}

	public void setUser_def20(String user_def20) {
		this.user_def20 = user_def20;
	}

	public List<OrderDataDetail> getOrderDataDetailList() {
		return detailList;
	}

	public void setOrderDataDetailList(List<OrderDataDetail> detailList) {
		this.detailList = detailList;
	}

	public String toXml() {
		String xml = "";
		xml += "<wmsSailOrderRequest>";
		xml += "<checkword>" + checkword + "</checkword>";
		xml += "<header>";
		xml += "<company>" + company + "</company>";
		xml += "<warehouse>" + warehouse + "</warehouse>";
		xml += "<shop_name>" + shop_name + "</shop_name>";
		xml += "<erp_order>" + erp_order + "</erp_order>";
		xml += "<order_type>" + order_type + "</order_type>";
		xml += "<order_date>" + order_date + "</order_date>";
		xml += "<ship_from_name>" + ship_from_name + "</ship_from_name>";
		xml += "<ship_from_attention_to>" + ship_from_attention_to + "</ship_from_attention_to>";
		xml += "<ship_from_country>" + ship_from_country + "</ship_from_country>";
		xml += "<ship_from_province>" + ship_from_province + "</ship_from_province>";
		xml += "<ship_from_city>" + ship_from_city + "</ship_from_city>";
		xml += "<ship_from_area>" + ship_from_area + "</ship_from_area>";
		xml += "<ship_from_address>" + ship_from_address + "</ship_from_address>";
		xml += "<ship_from_postal_code>" + ship_from_postal_code + "</ship_from_postal_code>";
		xml += "<ship_from_phone_num>" + ship_from_phone_num + "</ship_from_phone_num>";
		xml += "<ship_from_tel_num>" + ship_from_tel_num + "</ship_from_tel_num>";
		xml += "<ship_from_email_address>" + ship_from_email_address + "</ship_from_email_address>";
		xml += "<ship_to_name>" + ship_to_name + "</ship_to_name>";
		xml += "<ship_to_attention_to>" + ship_to_attention_to + "</ship_to_attention_to>";
		xml += "<ship_to_country>" + ship_to_country + "</ship_to_country>";
		xml += "<ship_to_province>" + ship_to_province + "</ship_to_province>";
		xml += "<ship_to_city>" + ship_to_city + "</ship_to_city>";
		xml += "<ship_to_area>" + ship_to_area + "</ship_to_area>";
		xml += "<ship_to_address>" + ship_to_address + "</ship_to_address>";
		xml += "<ship_to_postal_code>" + ship_to_postal_code + "</ship_to_postal_code>";
		xml += "<ship_to_phone_num>" + ship_to_phone_num + "</ship_to_phone_num>";
		xml += "<ship_to_tel_num>" + ship_to_tel_num + "</ship_to_tel_num>";
		xml += "<ship_to_email_address>" + ship_to_email_address + "</ship_to_email_address>";
		xml += "<carrier>" + carrier + "</carrier>";
		xml += "<carrier_service>" + carrier_service + "</carrier_service>";
		xml += "<route_numbers>" + route_numbers + "</route_numbers>";
		xml += "<packing_note>" + packing_note + "</packing_note>";
		xml += "<complete_delivery>" + complete_delivery + "</complete_delivery>";
		xml += "<freight>" + freight + "</freight>";
		xml += "<payment_of_charge>" + payment_of_charge + "</payment_of_charge>";
		xml += "<payment_district>" + payment_district + "</payment_district>";
		xml += "<cod>" + cod + "</cod>";
		xml += "<amount>" + amount + "</amount>";
		xml += "<self_pickup>" + self_pickup + "</self_pickup>";
		xml += "<value_insured>" + value_insured + "</value_insured>";
		xml += "<declared_value>" + declared_value + "</declared_value>";
		xml += "<return_receipt_service>" + return_receipt_service + "</return_receipt_service>";
		xml += "<delivery_date>" + delivery_date + "</delivery_date>";
		xml += "<delivery_requested>" + delivery_requested + "</delivery_requested>";
		xml += "<invoice>" + invoice + "</invoice>";
		xml += "<invoice_type>" + invoice_type + "</invoice_type>";
		xml += "<invoice_title>" + invoice_title + "</invoice_title>";
		xml += "<invoice_content>" + invoice_content + "</invoice_content>";
		xml += "<order_note>" + order_note + "</order_note>";
		xml += "<company_note>" + company_note + "</company_note>";
		xml += "<priority>" + priority + "</priority>";
		xml += "<order_total_amount>" + order_total_amount + "</order_total_amount>";
		xml += "<order_discount>" + order_discount + "</order_discount>";
		xml += "<balance_amount>" + balance_amount + "</balance_amount>";
		xml += "<coupons_amount>" + coupons_amount + "</coupons_amount>";
		xml += "<gift_card_amount>" + gift_card_amount + "</gift_card_amount>";
		xml += "<other_charge>" + other_charge + "</other_charge>";
		xml += "<actual_amount>" + actual_amount + "</actual_amount>";
		xml += "<customer_payment_method>" + customer_payment_method + "</customer_payment_method>";
		xml += "<monthly_account>" + monthly_account + "</monthly_account>";
		xml += "<from_flag>" + from_flag + "</from_flag>";
		if (!"".equals(user_def1)) {
			xml += "<user_def1>" + user_def1 + "</user_def1>";
			xml += "<user_def2>" + user_def2 + "</user_def2>";
			xml += "<user_def3>" + user_def3 + "</user_def3>";
			xml += "<user_def4>" + user_def4 + "</user_def4>";
			xml += "<user_def5>" + user_def5 + "</user_def5>";
			xml += "<user_def6>" + user_def6 + "</user_def6>";
			xml += "<user_def7>" + user_def7 + "</user_def7>";
			xml += "<user_def8>" + user_def8 + "</user_def8>";
			xml += "<user_def9>" + user_def9 + "</user_def9>";
			xml += "<user_def10>" + user_def10 + "</user_def10>";
			xml += "<user_def11>" + user_def11 + "</user_def11>";
			xml += "<user_def12>" + user_def12 + "</user_def12>";
			xml += "<user_def13>" + user_def13 + "</user_def13>";
			xml += "<user_def14>" + user_def14 + "</user_def14>";
			xml += "<user_def15>" + user_def15 + "</user_def15>";
			xml += "<user_def16>" + user_def16 + "</user_def16>";
			xml += "<user_def17>" + user_def17 + "</user_def17>";
			xml += "<user_def18>" + user_def18 + "</user_def18>";
			xml += "<user_def19>" + user_def19 + "</user_def19>";
			xml += "<user_def20>" + user_def20 + "</user_def20>";
		}
		xml += "</header>";

		if (detailList != null && detailList.size() > 0) {
			xml += "<detailList>";
			OrderDataDetail detail = new OrderDataDetail();
			for (int i = 0; i < detailList.size(); i++) {
				detail = new OrderDataDetail();
				detail = detailList.get(i);
				xml += detail.toXml();
			}
			xml += "</detailList>";
		}

		xml += "</wmsSailOrderRequest>";
		return xml;
	}

	public String todxztXml() {
		String xml = "";
		xml += "<wmsSailOrderStatusQueryRequest>";
		xml += "<checkword>" + checkword + "</checkword>";
		xml += "<company>" + company + "</company>";
		xml += "<orderid>" + erp_order + "</orderid>";
		xml += "</wmsSailOrderStatusQueryRequest>";
		return xml;
	}

	public String todxMxXml() {
		String xml = "";
		xml += "<wmsSailOrderQueryRequest>";
		xml += "<checkword>" + checkword + "</checkword>";
		xml += "<company>" + company + "</company>";
		xml += "<orderid>" + erp_order + "</orderid>";
		xml += "<warehouse>" + warehouse + "</warehouse>";
		xml += "</wmsSailOrderQueryRequest>";
		return xml;
	}

	public static void main(String[] arg) {
	}
}