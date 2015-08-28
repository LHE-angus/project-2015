package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
public interface JxcPdDao extends EntityDao<JxcPd> {

	/**
	 * @author Du,HongGang
	 * @version 2011-03-03
	 */
	public List<JxcPd> selectJxcPdListForJX(JxcPd t);

	/**
	 * @author Chen,LiLin
	 * @version 2011-03-07
	 */
	public int updateJxcPdCount(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-03-07
	 */
	public JxcPd selectQcjcForStock(JxcPd t);

	public JxcPd selectRcjhForStock(JxcPd t);

	public JxcPd selectRcchForStock(JxcPd t);

	public List<JxcPd> selectSskcDetailsForStork(JxcPd t);

	public List<JxcPd> selectSsxsDetailsForStork(JxcPd t);

	public List<JxcPd> selectStockDetailsList(JxcPd t);

	public List<JxcPd> selectStockRcjhAndRcchList(JxcPd t);

	public Long selectStockRcjhAndRcchCount(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-06-30
	 */
	List<JxcPd> selectJxcShopUsedStatisticsResultForList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-08-12
	 */
	Long selectJxcPdDetailsByProvinceCount(JxcPd t);

	List<JxcPd> selectJxcPdDetailsByProvincePaginatedList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-08-29
	 */
	List<JxcPd> selectJxcShopSalesPdTypeStatisticsResultForList(JxcPd t);

	/**
	 * @author Ren zhong
	 * @version 2011-09-01
	 */
	List<JxcPd> selectJxcShopSalesNotJDXXDetailsResultForList(JxcPd t);

	/**
	 * @author Qin yue
	 * @version 2011-10-20
	 */
	List<JxcPd> selectStockDetailsListForPc(JxcPd t);

	/**
	 * @author Zhang,Xufeng
	 * @version 2011-11-22
	 */
	public List<JxcPd> selectShopSellStatisticsList(JxcPd t);

	public JxcPd selectQcjcForShopSellStatistics(JxcPd t);
}