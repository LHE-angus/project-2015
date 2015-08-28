package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcPd;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcPdService {

	Long createJxcPd(JxcPd t);

	int modifyJxcPd(JxcPd t);

	int removeJxcPd(JxcPd t);

	JxcPd getJxcPd(JxcPd t);

	List<JxcPd> getJxcPdList(JxcPd t);

	Long getJxcPdCount(JxcPd t);

	List<JxcPd> getJxcPdPaginatedList(JxcPd t);

	/**
	 * @author Du,HongGang
	 * @version 2011-03-03
	 */
	List<JxcPd> getJxcPdListForJX(JxcPd t);

	/**
	 * @author Chen,LiLin
	 * @version 2011-03-07
	 */
	int modifyJxcPdCount(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-03-07
	 */
	JxcPd getQcjcForStock(JxcPd t);

	JxcPd getRcjhForStock(JxcPd t);

	JxcPd getRcchForStock(JxcPd t);

	List<JxcPd> getSskcDetailsForStork(JxcPd t);

	List<JxcPd> getSsxsDetailsForStork(JxcPd t);

	List<JxcPd> getStockDetailsList(JxcPd t);

	List<JxcPd> getStockRcjhAndRcchList(JxcPd t);

	Long getStockRcjhAndRcchCount(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-06-30
	 */
	List<JxcPd> getJxcShopUsedStatisticsResultForList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-08-12
	 */
	Long getJxcPdDetailsByProvinceCount(JxcPd t);

	List<JxcPd> getJxcPdDetailsByProvincePaginatedList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-08-29
	 */
	List<JxcPd> getJxcShopSalesPdTypeStatisticsResultForList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-09-01
	 */
	List<JxcPd> getJxcShopSalesNotJDXXDetailsResultForList(JxcPd t);

	/**
	 * @author Qin yue
	 * @version 2011-10-20
	 */
	List<JxcPd> selectStockDetailsListForPc(JxcPd t);

	/**
	 * @author Zhang,Xufeng
	 * @version 2011-11-22
	 */
	List<JxcPd> getShopSellStatisticsList(JxcPd t);

	JxcPd getQcjcForShopSellStatistics(JxcPd t);

}