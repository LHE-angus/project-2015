package com.sf.integration.warehouse;

/**
 * @author TUDP
 * @version 2013-11-25
 * @desc 仓储接口销售订单 销售明细
 */
public class OrderDataDetail {
	private String root = "item";
	private String erp_order_line_num = "";// //行号 erp_order_line_num
	private String item = "";// 商品编号 item
	private String item_name = "";// 商品名称item_name
	private String uom = "";// 单位
	private String lot = "";// 批号 lot
	private String qty = "";// 数量
	private String item_price = "";// 价格
	private String item_discount = "";// 优惠金额
	private String bom_action = "";// 是否为组合商品
	private String user_def1 = "";// 预留字段 1 user_def1
	private String user_def2 = "";// 预留字段2 user_def2
	private String user_def3 = "";// 预留字段3 user_def3
	private String user_def4 = "";// 预留字段4 user_def4
	private String user_def5 = "";// 预留字段5 user_def5
	private String user_def6 = "";// 预留字段6 user_def6
	private String user_def7 = "";// 预留字段 7 user_def7
	private String user_def8 = "";// 预留字段8 user_def8

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * 订单明细序列号3
	 * 
	 * @是否必须 Y
	 */
	public String getErp_order_line_num() {
		return erp_order_line_num;
	}

	/**
	 * 订单明细序列号3
	 * 
	 * @是否必须 Y
	 */
	public void setErp_order_line_num(String erp_order_line_num) {
		this.erp_order_line_num = erp_order_line_num;
	}

	/**
	 * 商品编号 25
	 * 
	 * @是否必须 Y
	 */
	public String getItem() {
		return item;
	}

	/**
	 * 商品编号 25
	 * 
	 * @是否必须 Y
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 商品名称
	 * 
	 * @是否必须 Y
	 */
	public String getItem_name() {
		return item_name;
	}

	/**
	 * 商品名称
	 * 
	 * @是否必须 Y
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	/**
	 * 商品单位
	 * 
	 * @是否必须 Y
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * 商品单位
	 * 
	 * @是否必须 Y
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * 批号
	 */
	public String getLot() {
		return lot;
	}

	/**
	 * 批号
	 */
	public void setLot(String lot) {
		this.lot = lot;
	}

	/**
	 * 商品数量
	 * 
	 * @是否必须 Y
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * 商品数量
	 * 
	 * @是否必须 Y
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * 价格 商品销售价格
	 * 
	 * @return
	 */
	public String getItem_price() {
		return item_price;
	}

	/**
	 * 价格 商品销售价格
	 * 
	 * @return
	 */
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	/**
	 * 优惠金额
	 */
	public String getItem_discount() {
		return item_discount;
	}

	/**
	 * 优惠金额
	 */
	public void setItem_discount(String item_discount) {
		this.item_discount = item_discount;
	}

	/**
	 * 是 否 为 组合商 品 Y/N: 商品有子商品时，值 为 Y ，否则为N ，系统默认为N
	 */
	public String getBom_action() {
		return bom_action;
	}

	/**
	 * 是 否 为 组合商 品 Y/N: 商品有子商品时，值 为 Y ，否则为N ，系统默认为N
	 */
	public void setBom_action(String bom_action) {
		this.bom_action = bom_action;
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

	public String toXml() {
		String xml = "<" + root + ">";
		xml += "<erp_order_line_num>" + erp_order_line_num + "</erp_order_line_num>";
		xml += "<item>" + item + "</item>";
		xml += "<item_name>" + item_name + "</item_name>";
		xml += "<uom>" + uom + "</uom>";
		xml += "<lot>" + lot + "</lot>";
		xml += "<qty>" + qty + "</qty>";
		xml += "<item_price>" + item_price + "</item_price>";
		xml += "<item_discount>" + item_discount + "</item_discount>";
		xml += "<bom_action>" + bom_action + "</bom_action>";

		if (!"".equals(user_def1)) {
			xml += "<user_def1>" + user_def1 + "</user_def1>";
			xml += "<user_def2>" + user_def2 + "</user_def2>";
			xml += "<user_def3>" + user_def3 + "</user_def3>";
			xml += "<user_def4>" + user_def4 + "</user_def4>";
			xml += "<user_def5>" + user_def5 + "</user_def5>";
			xml += "<user_def6>" + user_def6 + "</user_def6>";
			xml += "<user_def7>" + user_def7 + "</user_def7>";
			xml += "<user_def8>" + user_def8 + "</user_def8>";
		}
		xml += "</" + root + ">";
		return xml;
	}

}