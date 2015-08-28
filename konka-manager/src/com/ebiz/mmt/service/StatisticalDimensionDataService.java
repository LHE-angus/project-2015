package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionData;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionDataService {

	Long createStatisticalDimensionData(StatisticalDimensionData t);

	int modifyStatisticalDimensionData(StatisticalDimensionData t);

	int removeStatisticalDimensionData(StatisticalDimensionData t);

	StatisticalDimensionData getStatisticalDimensionData(StatisticalDimensionData t);

	List<StatisticalDimensionData> getStatisticalDimensionDataList(StatisticalDimensionData t);

	Long getStatisticalDimensionDataCount(StatisticalDimensionData t);

	List<StatisticalDimensionData> getStatisticalDimensionDataPaginatedList(StatisticalDimensionData t);
	
	//客户户龄分析
	Long getStatisticalDimensionDataForCustomerAgeCount(StatisticalDimensionData t);
	//客户户龄分析
	List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerAgePaginatedList(StatisticalDimensionData t);

	/**
	 * 年度 执行插入数据的SQL语句，并且更新相应的比例数据 2014-11-07<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	public int AutoRunUpdateStatementForInsertAndUpdate();

	/**
	 * 门店网点统计表 2014-11-07
	 * 
	 * @param t
	 * @return
	 */
	Long getStatisticalDimensionDataForStoreAndAgentCount(StatisticalDimensionData t);

	/**
	 * 门店网点统计表 2014-11-07
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> getStatisticalDimensionDataForStoreAndAgentPaginatedList(StatisticalDimensionData t);

	/**
	 * 客户分类统计表 2014-11-08 数量
	 * 
	 * @param t
	 * @return
	 */
	Long getStatisticalDimensionDataForCustomerFltjCount(StatisticalDimensionData t);
	
	/**
	 * 客户分类统计表 2014-11-17 查询
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerFltjPaginatedList(StatisticalDimensionData t);
	
	/**
	 * 客户分类统计表 2014-11-17 导出
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerFltjList(StatisticalDimensionData t);

	/**
	 * 门店分类统计表 2014-11-08
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionData> getStatisticalDimensionDataForStoreTypeFltjPaginatedList(StatisticalDimensionData t);
	

	/**
	 * 门店分类统计表 2014-11-17
	 * 
	 * @param t
	 * @return
	 */
	Long getStatisticalDimensionDataForStoreTypeFltjCount(StatisticalDimensionData t);


	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForDataInsert();

}