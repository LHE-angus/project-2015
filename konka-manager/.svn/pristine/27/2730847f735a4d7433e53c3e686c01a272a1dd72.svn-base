package com.ebiz.mmt.service;

import java.util.Date;

/**
 * @author Ren,zhong
 * @date 2013-06-15
 */
public interface KonkaCustomerPublicService {

	public Long createStockVerify(Date opr_date, String store_id, String goods_id, String stocks, String ver_stocks,
			Long custId, String memo, String[] pyBuyPrices, String[] stackIds);

	/**
	 * 添加了期初金额字段和当前期初金额字段、盘点类型字段 2014-12-01<br/>
	 * 盘点类型 verify_type 1:单个盘点 2：月初结转 3:月中盘点 4：月末全部盘点 0 库存初始化 verify_type
	 * 
	 * @param opr_date
	 * @param store_id
	 * @param goods_id
	 * @param stocks
	 * @param money
	 * @param ver_stocks
	 * @param ver_money
	 * @param custId
	 * @param memo
	 * @param verify_type
	 * @param pyBuyPrices
	 * @param stackIds
	 * @return
	 */
	public Long createStockVerify(Date opr_date, String store_id, String goods_id, String stocks, String money,
			String ver_stocks, String ver_money, Long custId, String memo, int verify_type, String[] pyBuyPrices,
			String[] stackIds);

}
