package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JBaseGoodsInitStock;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xiao,GuoJian
 * @date 2014-06-20 10:28:34
 */
public interface JBaseGoodsInitStockService {

	Long createJBaseGoodsInitStock(JBaseGoodsInitStock t);

	Long createJBaseGoodsInitStockAndStock(JBaseGoodsInitStock t);

	int modifyJBaseGoodsInitStock(JBaseGoodsInitStock t);

	int removeJBaseGoodsInitStock(JBaseGoodsInitStock t);

	JBaseGoodsInitStock getJBaseGoodsInitStock(JBaseGoodsInitStock t);

	List<JBaseGoodsInitStock> getJBaseGoodsInitStockList(JBaseGoodsInitStock t);

	Long getJBaseGoodsInitStockCount(JBaseGoodsInitStock t);

	List<JBaseGoodsInitStock> getJBaseGoodsInitStockPaginatedList(JBaseGoodsInitStock t);

}