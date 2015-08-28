package com.sf.integration.warehouse;

/**
 * 
 * @author Pan,Gang
 * @desc 仓库接口 商品目录导入
 */
public class Spml {
	private String checkword;// 校验字段
	private String company;// 货主
	private String item;// 商品编码
	private String description;// 商品名称
	private String department;// 品牌中文描述
	private String division;// 物品规格描述
	private String long_description;// 质检
	private String item_category1;// 商品品类1
	private String item_category2;// 商品品类2
	private String item_category3;// 商品品类3
	private String item_category4;// 商品品类4
	private String item_category5;// 商品品类5
	private String item_category6;// 商品品类6
	private String item_category7;// 商品品类7
	private String item_category8;// 商品品类8
	private String item_category9;// 商品品类9
	private String item_category10;// 商品品类10
	private String item_class;// 货品类
	private String item_color;// 颜色
	private String item_size;// 尺寸
	private String storage_template;// 存储模板
	private String item_style;// 保质期前置天数
	private String x_ref_item_1;// 条码1
	private String x_ref_item_2;// 条码2
	private String x_ref_item_3;// 条码3
	private String x_ref_item_4;// 条码4
	private String sequence_1;// 序号1
	private String conversion_qty_1;// 数量单位1转换比例
	private String height_1;// 高
	private String width_1;// 宽
	private String length_1;// 长
	private String weight_1;// 重量
	private String quantity_um_1;// 货品数量单位1
	private String weight_um_1;// 重量单位1
	private String dimension_um_1;// 尺寸单位1
	private String treat_as_loose_1;// 包装单位1视为零头
	private String sequence_2;// 序号1
	private String conversion_qty_2;// 数量单位1转换比例
	private String height_2;// 高
	private String width_2;// 宽
	private String length_2;// 长
	private String weight_2;// 重量
	private String quantity_um_2;// 货品数量单位1
	private String weight_um_2;// 重量单位1
	private String dimension_um_2;// 尺寸单位1
	private String treat_as_loose_2;// 包装单位1视为零头
	private String sequence_3;// 序号1
	private String conversion_qty_3;// 数量单位1转换比例
	private String height_3;// 高
	private String width_3;// 宽
	private String length_3;// 长
	private String weight_3;// 重量
	private String quantity_um_3;// 货品数量单位1
	private String weight_um_3;// 重量单位1
	private String dimension_um_3;// 尺寸单位1
	private String treat_as_loose_3;// 包装单位1视为零头
	private String sequence_4;// 序号1
	private String conversion_qty_4;// 数量单位1转换比例
	private String height_4;// 高
	private String width_4;// 宽
	private String length_4;// 长
	private String weight_4;// 重量
	private String quantity_um_4;// 货品数量单位1
	private String weight_um_4;// 重量单位1
	private String dimension_um_4;// 尺寸单位1
	private String treat_as_loose_4;// 包装单位1视为零头
	private String lot_controlled;// 是否批号控制
	private String lot_template;// 批次模板
	private String serial_num_track_inbound;// 入库序列号跟踪
	private String serial_num_track_outbound;// 出库序列号跟踪
	private String bom_action;// 是否BOM主件
	private String user_def1;// 预留字段
	private String user_def2;// 预留字段
	private String user_def3;// 预留字段
	private String user_def4;// 预留字段
	private String user_def5;// 预留字段
	private String user_def6;// 预留字段
	private String user_def7;// 预留字段
	private String user_def8;// 预留字段
	private String interface_action_code;// 接口动作

	public Spml() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCheckword() {
		return checkword;
	}

	public void setCheckword(String checkword) {
		this.checkword = checkword;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLong_description() {
		return long_description;
	}

	public void setLong_description(String longDescription) {
		long_description = longDescription;
	}

	public String getItem_category1() {
		return item_category1;
	}

	public void setItem_category1(String itemCategory1) {
		item_category1 = itemCategory1;
	}

	public String getItem_category2() {
		return item_category2;
	}

	public void setItem_category2(String itemCategory2) {
		item_category2 = itemCategory2;
	}

	public String getItem_category3() {
		return item_category3;
	}

	public void setItem_category3(String itemCategory3) {
		item_category3 = itemCategory3;
	}

	public String getItem_category4() {
		return item_category4;
	}

	public void setItem_category4(String itemCategory4) {
		item_category4 = itemCategory4;
	}

	public String getItem_category5() {
		return item_category5;
	}

	public void setItem_category5(String itemCategory5) {
		item_category5 = itemCategory5;
	}

	public String getItem_category6() {
		return item_category6;
	}

	public void setItem_category6(String itemCategory6) {
		item_category6 = itemCategory6;
	}

	public String getItem_category7() {
		return item_category7;
	}

	public void setItem_category7(String itemCategory7) {
		item_category7 = itemCategory7;
	}

	public String getItem_category8() {
		return item_category8;
	}

	public void setItem_category8(String itemCategory8) {
		item_category8 = itemCategory8;
	}

	public String getItem_category9() {
		return item_category9;
	}

	public void setItem_category9(String itemCategory9) {
		item_category9 = itemCategory9;
	}

	public String getItem_category10() {
		return item_category10;
	}

	public void setItem_category10(String itemCategory10) {
		item_category10 = itemCategory10;
	}

	public String getItem_class() {
		return item_class;
	}

	public void setItem_class(String itemClass) {
		item_class = itemClass;
	}

	public String getItem_color() {
		return item_color;
	}

	public void setItem_color(String itemColor) {
		item_color = itemColor;
	}

	public String getItem_size() {
		return item_size;
	}

	public void setItem_size(String itemSize) {
		item_size = itemSize;
	}

	public String getStorage_template() {
		return storage_template;
	}

	public void setStorage_template(String storageTemplate) {
		storage_template = storageTemplate;
	}

	public String getItem_style() {
		return item_style;
	}

	public void setItem_style(String itemStyle) {
		item_style = itemStyle;
	}

	public String getX_ref_item_1() {
		return x_ref_item_1;
	}

	public void setX_ref_item_1(String xRefItem_1) {
		x_ref_item_1 = xRefItem_1;
	}

	public String getX_ref_item_2() {
		return x_ref_item_2;
	}

	public void setX_ref_item_2(String xRefItem_2) {
		x_ref_item_2 = xRefItem_2;
	}

	public String getX_ref_item_3() {
		return x_ref_item_3;
	}

	public void setX_ref_item_3(String xRefItem_3) {
		x_ref_item_3 = xRefItem_3;
	}

	public String getX_ref_item_4() {
		return x_ref_item_4;
	}

	public void setX_ref_item_4(String xRefItem_4) {
		x_ref_item_4 = xRefItem_4;
	}

	public String getSequence_1() {
		return sequence_1;
	}

	public void setSequence_1(String sequence_1) {
		this.sequence_1 = sequence_1;
	}

	public String getConversion_qty_1() {
		return conversion_qty_1;
	}

	public void setConversion_qty_1(String conversionQty_1) {
		conversion_qty_1 = conversionQty_1;
	}

	public String getHeight_1() {
		return height_1;
	}

	public void setHeight_1(String height_1) {
		this.height_1 = height_1;
	}

	public String getWidth_1() {
		return width_1;
	}

	public void setWidth_1(String width_1) {
		this.width_1 = width_1;
	}

	public String getLength_1() {
		return length_1;
	}

	public void setLength_1(String length_1) {
		this.length_1 = length_1;
	}

	public String getWeight_1() {
		return weight_1;
	}

	public void setWeight_1(String weight_1) {
		this.weight_1 = weight_1;
	}

	public String getQuantity_um_1() {
		return quantity_um_1;
	}

	public void setQuantity_um_1(String quantityUm_1) {
		quantity_um_1 = quantityUm_1;
	}

	public String getWeight_um_1() {
		return weight_um_1;
	}

	public void setWeight_um_1(String weightUm_1) {
		weight_um_1 = weightUm_1;
	}

	public String getDimension_um_1() {
		return dimension_um_1;
	}

	public void setDimension_um_1(String dimensionUm_1) {
		dimension_um_1 = dimensionUm_1;
	}

	public String getTreat_as_loose_1() {
		return treat_as_loose_1;
	}

	public void setTreat_as_loose_1(String treatAsLoose_1) {
		treat_as_loose_1 = treatAsLoose_1;
	}

	public String getSequence_2() {
		return sequence_2;
	}

	public void setSequence_2(String sequence_2) {
		this.sequence_2 = sequence_2;
	}

	public String getConversion_qty_2() {
		return conversion_qty_2;
	}

	public void setConversion_qty_2(String conversionQty_2) {
		conversion_qty_2 = conversionQty_2;
	}

	public String getHeight_2() {
		return height_2;
	}

	public void setHeight_2(String height_2) {
		this.height_2 = height_2;
	}

	public String getWidth_2() {
		return width_2;
	}

	public void setWidth_2(String width_2) {
		this.width_2 = width_2;
	}

	public String getLength_2() {
		return length_2;
	}

	public void setLength_2(String length_2) {
		this.length_2 = length_2;
	}

	public String getWeight_2() {
		return weight_2;
	}

	public void setWeight_2(String weight_2) {
		this.weight_2 = weight_2;
	}

	public String getQuantity_um_2() {
		return quantity_um_2;
	}

	public void setQuantity_um_2(String quantityUm_2) {
		quantity_um_2 = quantityUm_2;
	}

	public String getWeight_um_2() {
		return weight_um_2;
	}

	public void setWeight_um_2(String weightUm_2) {
		weight_um_2 = weightUm_2;
	}

	public String getDimension_um_2() {
		return dimension_um_2;
	}

	public void setDimension_um_2(String dimensionUm_2) {
		dimension_um_2 = dimensionUm_2;
	}

	public String getTreat_as_loose_2() {
		return treat_as_loose_2;
	}

	public void setTreat_as_loose_2(String treatAsLoose_2) {
		treat_as_loose_2 = treatAsLoose_2;
	}

	public String getSequence_3() {
		return sequence_3;
	}

	public void setSequence_3(String sequence_3) {
		this.sequence_3 = sequence_3;
	}

	public String getConversion_qty_3() {
		return conversion_qty_3;
	}

	public void setConversion_qty_3(String conversionQty_3) {
		conversion_qty_3 = conversionQty_3;
	}

	public String getHeight_3() {
		return height_3;
	}

	public void setHeight_3(String height_3) {
		this.height_3 = height_3;
	}

	public String getWidth_3() {
		return width_3;
	}

	public void setWidth_3(String width_3) {
		this.width_3 = width_3;
	}

	public String getLength_3() {
		return length_3;
	}

	public void setLength_3(String length_3) {
		this.length_3 = length_3;
	}

	public String getWeight_3() {
		return weight_3;
	}

	public void setWeight_3(String weight_3) {
		this.weight_3 = weight_3;
	}

	public String getQuantity_um_3() {
		return quantity_um_3;
	}

	public void setQuantity_um_3(String quantityUm_3) {
		quantity_um_3 = quantityUm_3;
	}

	public String getWeight_um_3() {
		return weight_um_3;
	}

	public void setWeight_um_3(String weightUm_3) {
		weight_um_3 = weightUm_3;
	}

	public String getDimension_um_3() {
		return dimension_um_3;
	}

	public void setDimension_um_3(String dimensionUm_3) {
		dimension_um_3 = dimensionUm_3;
	}

	public String getTreat_as_loose_3() {
		return treat_as_loose_3;
	}

	public void setTreat_as_loose_3(String treatAsLoose_3) {
		treat_as_loose_3 = treatAsLoose_3;
	}

	public String getSequence_4() {
		return sequence_4;
	}

	public void setSequence_4(String sequence_4) {
		this.sequence_4 = sequence_4;
	}

	public String getConversion_qty_4() {
		return conversion_qty_4;
	}

	public void setConversion_qty_4(String conversionQty_4) {
		conversion_qty_4 = conversionQty_4;
	}

	public String getHeight_4() {
		return height_4;
	}

	public void setHeight_4(String height_4) {
		this.height_4 = height_4;
	}

	public String getWidth_4() {
		return width_4;
	}

	public void setWidth_4(String width_4) {
		this.width_4 = width_4;
	}

	public String getLength_4() {
		return length_4;
	}

	public void setLength_4(String length_4) {
		this.length_4 = length_4;
	}

	public String getWeight_4() {
		return weight_4;
	}

	public void setWeight_4(String weight_4) {
		this.weight_4 = weight_4;
	}

	public String getQuantity_um_4() {
		return quantity_um_4;
	}

	public void setQuantity_um_4(String quantityUm_4) {
		quantity_um_4 = quantityUm_4;
	}

	public String getWeight_um_4() {
		return weight_um_4;
	}

	public void setWeight_um_4(String weightUm_4) {
		weight_um_4 = weightUm_4;
	}

	public String getDimension_um_4() {
		return dimension_um_4;
	}

	public void setDimension_um_4(String dimensionUm_4) {
		dimension_um_4 = dimensionUm_4;
	}

	public String getTreat_as_loose_4() {
		return treat_as_loose_4;
	}

	public void setTreat_as_loose_4(String treatAsLoose_4) {
		treat_as_loose_4 = treatAsLoose_4;
	}

	public String getLot_controlled() {
		return lot_controlled;
	}

	public void setLot_controlled(String lotControlled) {
		lot_controlled = lotControlled;
	}

	public String getLot_template() {
		return lot_template;
	}

	public void setLot_template(String lotTemplate) {
		lot_template = lotTemplate;
	}

	public String getSerial_num_track_inbound() {
		return serial_num_track_inbound;
	}

	public void setSerial_num_track_inbound(String serialNumTrackInbound) {
		serial_num_track_inbound = serialNumTrackInbound;
	}

	public String getSerial_num_track_outbound() {
		return serial_num_track_outbound;
	}

	public void setSerial_num_track_outbound(String serialNumTrackOutbound) {
		serial_num_track_outbound = serialNumTrackOutbound;
	}

	public String getBom_action() {
		return bom_action;
	}

	public void setBom_action(String bomAction) {
		bom_action = bomAction;
	}

	public String getUser_def1() {
		return user_def1;
	}

	public void setUser_def1(String userDef1) {
		user_def1 = userDef1;
	}

	public String getUser_def2() {
		return user_def2;
	}

	public void setUser_def2(String userDef2) {
		user_def2 = userDef2;
	}

	public String getUser_def3() {
		return user_def3;
	}

	public void setUser_def3(String userDef3) {
		user_def3 = userDef3;
	}

	public String getUser_def4() {
		return user_def4;
	}

	public void setUser_def4(String userDef4) {
		user_def4 = userDef4;
	}

	public String getUser_def5() {
		return user_def5;
	}

	public void setUser_def5(String userDef5) {
		user_def5 = userDef5;
	}

	public String getUser_def6() {
		return user_def6;
	}

	public void setUser_def6(String userDef6) {
		user_def6 = userDef6;
	}

	public String getUser_def7() {
		return user_def7;
	}

	public void setUser_def7(String userDef7) {
		user_def7 = userDef7;
	}

	public String getUser_def8() {
		return user_def8;
	}

	public void setUser_def8(String userDef8) {
		user_def8 = userDef8;
	}

	public String getInterface_action_code() {
		return interface_action_code;
	}

	public void setInterface_action_code(String interfaceActionCode) {
		interface_action_code = interfaceActionCode;
	}

	public String toXml() {
		String xml = "";
		xml += "<wmsMerchantCatalogRequest >";
		xml += "<checkword>" + checkword + "</checkword>";
		xml += "<company>" + company + "</company>";
		xml += "<item>" + item + "</item>";
		xml += "<description>" + description + "</description>";
		xml += "<storage_template>" + storage_template + "</storage_template>";
		xml += "<sequence_1>" + sequence_1 + "</sequence_1>";
		xml += "<conversion_qty_1>" + conversion_qty_1 + "</conversion_qty_1>";
		xml += "<height_1>" + height_1 + "</height_1>";
		xml += "<width_1>" + width_1 + "</width_1>";
		xml += "<length_1>" + length_1 + "</length_1>";
		xml += "<weight_1>" + weight_1 + "</weight_1>";
		xml += "<quantity_um_1>" + quantity_um_1 + "</quantity_um_1>";
		xml += "<weight_um_1>" + weight_um_1 + "</weight_um_1>";
		xml += "<dimension_um_1>" + dimension_um_1 + "</dimension_um_1>";
		xml += "<treat_as_loose_1>" + treat_as_loose_1 + "</treat_as_loose_1>";
		xml += "<lot_controlled>" + lot_controlled + "</lot_controlled>";
		// xml += "<lot_template>" + lot_template + "</lot_template>";
		xml += "<serial_num_track_inbound>" + serial_num_track_inbound + "</serial_num_track_inbound>";
		xml += "<serial_num_track_outbound>" + serial_num_track_outbound + "</serial_num_track_outbound>";
		xml += "<bom_action>" + bom_action + "</bom_action>";
		xml += "<interface_action_code>" + interface_action_code + "</interface_action_code>";
		// xml += "<user_def1>" + user_def1 + "</user_def1>";
		// xml += "<user_def2>" + user_def2 + "</user_def2>";
		// xml += "<user_def3>" + user_def3 + "</user_def3>";
		// xml += "<user_def4>" + user_def4 + "</user_def4>";
		// xml += "<user_def5>" + user_def5 + "</user_def5>";
		// xml += "<user_def6>" + user_def6 + "</user_def6>";
		// xml += "<user_def7>" + user_def7 + "</user_def7>";
		// xml += "<user_def8>" + user_def8 + "</user_def8>";
		xml += "</wmsMerchantCatalogRequest>";
		return xml;
	}

}
