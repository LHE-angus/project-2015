package com.ebiz.mmt.domain;

import java.io.Serializable;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
public class KonkaPdModel extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long pd_id;
	
	private String pd_name;
	
	private Integer pd_type;
	
	private Integer order_value;
	
	public KonkaPdModel() {

	}

	/**
	 * @val 产品型号ID
	 */
	public Long getPd_id() {
		return pd_id;
	}
	
	/**
	 * @val 产品型号ID
	 */
	public void setPd_id(Long pd_id) {
		this.pd_id = pd_id;
	}
	
	/**
	 * @val 型号名称
	 */
	public String getPd_name() {
		return pd_name;
	}
	
	/**
	 * @val 型号名称
	 */
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	
	/**
	 * @val 产品类别：1、冰箱;2、彩电;3、手机;4、洗衣机;6、空调;9、电磁炉;
	 */
	public Integer getPd_type() {
		return pd_type;
	}
	
	/**
	 * @val 产品类别：1、冰箱;2、彩电;3、手机;4、洗衣机;6、空调;9、电磁炉;
	 */
	public void setPd_type(Integer pd_type) {
		this.pd_type = pd_type;
	}
	
	/**
	 * @val 排序值，值越大越靠前
	 */
	public Integer getOrder_value() {
		return order_value;
	}
	
	/**
	 * @val 排序值，值越大越靠前
	 */
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	
}