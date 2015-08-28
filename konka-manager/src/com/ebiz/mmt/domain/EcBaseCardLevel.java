package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public class EcBaseCardLevel extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long card_level;

	private String card_level_desc;

	private String card_level_name;

	private String card_type_addman;

	private Date card_type_adddate;

	private BigDecimal card_type_discount;

	public EcBaseCardLevel() {

	}

	/**
	 * @val 会员等级
	 */
	public Long getCard_level() {
		return card_level;
	}

	/**
	 * @val 会员等级
	 */
	public void setCard_level(Long cardLevel) {
		card_level = cardLevel;
	}

	/**
	 * @val 会员等级说明
	 */
	public String getCard_level_desc() {
		return card_level_desc;
	}

	/**
	 * @val 会员等级说明
	 */
	public void setCard_level_desc(String card_level_desc) {
		this.card_level_desc = card_level_desc;
	}

	/**
	 * @val 会员等级名称
	 */
	public String getCard_level_name() {
		return card_level_name;
	}

	/**
	 * @val 会员等级名称
	 */
	public void setCard_level_name(String card_level_name) {
		this.card_level_name = card_level_name;
	}

	/**
	 * @val 添加时间
	 */
	public String getCard_type_addman() {
		return card_type_addman;
	}

	/**
	 * @val 添加时间
	 */
	public void setCard_type_addman(String card_type_addman) {
		this.card_type_addman = card_type_addman;
	}

	/**
	 * @val 添加人
	 */
	public Date getCard_type_adddate() {
		return card_type_adddate;
	}

	/**
	 * @val 添加人
	 */
	public void setCard_type_adddate(Date card_type_adddate) {
		this.card_type_adddate = card_type_adddate;
	}

	/**
	 * @val 会员卡折扣，95表明是95折，则该会员购买则按照总的订单价格*0.95进行计算
	 */
	public BigDecimal getCard_type_discount() {
		return card_type_discount;
	}

	/**
	 * @val 会员卡折扣，95表明是95折，则该会员购买则按照总的订单价格*0.95进行计算
	 */
	public void setCard_type_discount(BigDecimal card_type_discount) {
		this.card_type_discount = card_type_discount;
	}

}