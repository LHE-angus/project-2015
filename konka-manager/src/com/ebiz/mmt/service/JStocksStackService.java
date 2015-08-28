package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.List;

import com.ebiz.mmt.domain.JStocksStack;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-18 09:43:03
 */
public interface JStocksStackService {

	Long createJStocksStack(JStocksStack t);

	int modifyJStocksStack(JStocksStack t);

	int removeJStocksStack(JStocksStack t);

	JStocksStack getJStocksStack(JStocksStack t);

	List<JStocksStack> getJStocksStackList(JStocksStack t);

	Long getJStocksStackCount(JStocksStack t);

	List<JStocksStack> getJStocksStackPaginatedList(JStocksStack t);

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-18
	 * @desc 商品出库
	 */
	void pop(Long cust_id, Long store_id, Long goods_id, String bill_sn);

	void push(Long cust_id, Long store_id, Long goods_id, BigDecimal goods_cost, String bill_sn);

	void delete(Long cust_id, String bill_sn);

	/**
	 * @author Ren,zhong
	 * @date 2013-06-20
	 */
	List<JStocksStack> getJStocksStackForSskcResultList(JStocksStack t);

	// 销售退货
	void rejected(Long c_id, Long store, Long goods_id, String bill_sn);

}