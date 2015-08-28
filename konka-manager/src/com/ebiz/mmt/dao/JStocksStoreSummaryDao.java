package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JStocksStoreSummary;
import com.ebiz.ssi.dao.EntityDao;

/**
 * 2015-04-03
 * 客户分仓库存汇总
 * @author Jacky
 */
public interface JStocksStoreSummaryDao extends EntityDao<JStocksStoreSummary> {
	/**
	 * 插入List
	 * @param list
	 * @return
	 */
	public Long insertJStocksStoreSummaryList(List<JStocksStoreSummary> list);
	
	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public List<JStocksStoreSummary> selectJStocksStoreSummaryForR3ShopPaginatedList(JStocksStoreSummary t);
	/**
	 * 导出客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public List<JStocksStoreSummary> selectJStocksStoreSummaryForR3ShopList(JStocksStoreSummary t);

	/**
	 * 获取客户分仓库存汇总列表
	 * 
	 * @author ChenXiaoqi
	 */
	public Long selectJStocksStoreSummaryForR3ShopCount(JStocksStoreSummary t);
	/**
	 * 获取客户分仓库存汇总数量
	 * 
	 * @author ChenXiaoqi
	 */
	public Long selectJStocksStoreSummaryForR3ShopSumCount(JStocksStoreSummary t);
}
