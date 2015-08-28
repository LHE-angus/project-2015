package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionData;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionDataDao extends EntityDao<StatisticalDimensionData> {

	/**
	 * 执行插入数据的SQL语句 2014-11-07<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForInsertAndUpdate();

	/**
	 * 门店网点统计表 2014-11-07
	 * 
	 * @param t
	 * @return
	 */
	Long selectStatisticalDimensionDataForStoreAndAgentCount(StatisticalDimensionData t);

	/**
	 * 门店网点统计表 2014-11-07
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> selectStatisticalDimensionDataForStoreAndAgentPaginatedList(
			StatisticalDimensionData t);

	/**
	 * 客户分类统计表 2014-11-08
	 * 
	 * @param t
	 * @return
	 */
	Long selectStatisticalDimensionDataForCustomerFltjCount(StatisticalDimensionData t);

	/**
	 * 客户分类统计表 2014-11-08
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerFltjPaginatedList(StatisticalDimensionData t);
	
	/**
	 * 客户分类统计表 2014-11-18 导出
	 * 
	 * @param t
	 * @return
	 */
     List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerFltjList(StatisticalDimensionData t);
	
	/**
	 * 门店分类统计表 2014-11-17
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> selectStatisticalDimensionDataForStoreTypeFltjPaginatedList(StatisticalDimensionData t);

	/**
	 * 门店分类统计表 2014-11-17
	 * 
	 * @param t
	 * @return
	 */
	Long selectStatisticalDimensionDataForStoreTypeFltjCount(StatisticalDimensionData t);
	
	/**
	 * 客户户龄分析 2014-11-24
	 * 
	 * @param t
	 * @return
	 */
	Long selectStatisticalDimensionDataForCustomerAgeCount(StatisticalDimensionData t);

	/**
	 * 客户户龄分析2014-11-24
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerAgePaginatedList(
			StatisticalDimensionData t);

	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForDataInsert();

}
