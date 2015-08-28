package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JStocksStoreSummary;

/**
 * 2015-04-03
 * 客户分仓库存汇总
 * @author Jacky
 */
public interface JStocksStoreSummaryService {

	Long createJStocksStoreSummary(JStocksStoreSummary t);

	int modifyJStocksStoreSummary(JStocksStoreSummary t);

	int removeJStocksStoreSummary(JStocksStoreSummary t);

    // JStocksStoreSummary getJStocksStoreSummary(JStocksStoreSummary t);
    //
    // List<JStocksStoreSummary> getJStocksStoreSummaryList(JStocksStoreSummary t);
    //
    // Long getJStocksStoreSummaryCount(JStocksStoreSummary t);
    //
    // List<JStocksStoreSummary> getJStocksStoreSummaryPaginatedList(JStocksStoreSummary t);
    //

	/**
	 * 插入List
	 * @param list
	 * @return
	 */
	Long createJStocksStoreSummaryList(List<JStocksStoreSummary> list);
	
	/**
	 * 获取客户分仓库存汇总列表数量
	 * 
	 * @author ChenXiaoqi
	 */
	Long getJStocksStoreSummaryForR3ShopCount(JStocksStoreSummary t);
	/**
	 * 获取客户分仓库存汇总数量
	 * 
	 * @author ChenXiaoqi
	 */
	Long getJStocksStoreSummaryForR3ShopSumCount(JStocksStoreSummary t);

	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	List<JStocksStoreSummary> getJStocksStoreSummaryForR3ShopPaginatedList(JStocksStoreSummary t);
	
	/**
	 * 导出客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	List<JStocksStoreSummary> getJStocksStoreSummaryForR3ShopList(JStocksStoreSummary t);

}