package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-29 14:09:13
 */
public class KonkaR3ExOrderLines extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long r3_ex_order_line_id;
	
	private String material_code;
	
	private String material_name;
	
	private String material_desc;
	
	private String review_notes;
	
	private String ship_desc;
	
	private String unit_code;
	
	private Long apply_amount;
	
	private Long review_amount;
	
	private Long unit_price;
	
	private Long total_money;
	
	private String attribute1;
	
	private String attribute2;
	
	private Long r3_order_header_id;
	
	public KonkaR3ExOrderLines() {

	}

	public Long getR3_ex_order_line_id() {
		return r3_ex_order_line_id;
	}
	
	public void setR3_ex_order_line_id(Long r3_ex_order_line_id) {
		this.r3_ex_order_line_id = r3_ex_order_line_id;
	}
	
	public String getMaterial_code() {
		return material_code;
	}
	
	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}
	
	public String getMaterial_name() {
		return material_name;
	}
	
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	
	public String getMaterial_desc() {
		return material_desc;
	}
	
	public void setMaterial_desc(String material_desc) {
		this.material_desc = material_desc;
	}
	
	public String getReview_notes() {
		return review_notes;
	}
	
	public void setReview_notes(String review_notes) {
		this.review_notes = review_notes;
	}
	
	public String getShip_desc() {
		return ship_desc;
	}
	
	public void setShip_desc(String ship_desc) {
		this.ship_desc = ship_desc;
	}
	
	public String getUnit_code() {
		return unit_code;
	}
	
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	
	public Long getApply_amount() {
		return apply_amount;
	}
	
	public void setApply_amount(Long apply_amount) {
		this.apply_amount = apply_amount;
	}
	
	public Long getReview_amount() {
		return review_amount;
	}
	
	public void setReview_amount(Long review_amount) {
		this.review_amount = review_amount;
	}
	
	public Long getUnit_price() {
		return unit_price;
	}
	
	public void setUnit_price(Long unit_price) {
		this.unit_price = unit_price;
	}
	
	public Long getTotal_money() {
		return total_money;
	}
	
	public void setTotal_money(Long total_money) {
		this.total_money = total_money;
	}
	
	public String getAttribute1() {
		return attribute1;
	}
	
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	
	public String getAttribute2() {
		return attribute2;
	}
	
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	
	public Long getR3_order_header_id() {
		return r3_order_header_id;
	}
	
	public void setR3_order_header_id(Long r3_order_header_id) {
		this.r3_order_header_id = r3_order_header_id;
	}
	
}