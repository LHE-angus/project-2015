package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:27
 */
public interface StatisticalDimensionDataMonthService {

	Long createStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t);

	int modifyStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t);

	int removeStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t);

	StatisticalDimensionDataMonth getStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t);

	List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthList(StatisticalDimensionDataMonth t);

	Long getStatisticalDimensionDataMonthCount(StatisticalDimensionDataMonth t);

	List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthPaginatedList(StatisticalDimensionDataMonth t);

	// 门店网点统计 数量 （月度）
	Long getStatisticalDimensionDataMonthForStoreTypeFltjCount(StatisticalDimensionDataMonth t);

	// 门店网点统计 查询（月度）
	List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList(
			StatisticalDimensionDataMonth t);

	// 客户分类统计 数量 （月度）
	Long getStatisticalDimensionDataMonthForCustomerFltjCount(StatisticalDimensionDataMonth t);

	// 客户分类统计 查询（月度）
	List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForCustomerFltjPaginatedList(
			StatisticalDimensionDataMonth t);

	// 门店分类统计 数量 （月度）
	Long getStatisticalDimensionDataMonthForStoreAndAgentCount(StatisticalDimensionDataMonth t);

	// 门店分类统计 查询（月度）
	List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(
			StatisticalDimensionDataMonth t);

	/**
	 * 月度 执行插入数据的SQL语句，并且更新相应的比例数据 2014-11-12<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	public int AutoRunUpdateStatementForDataMonthInsertAndUpdate();

	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForDataMonthInsert();

	/**
	 * 根据年获得目标销售额，实际销售额，同期销售额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> getRetailMoneyByYear(StatisticalDimensionDataMonth t);

	/**
	 * 根据年获得每月的目标销售额，实际销售额，同期销售额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> getRetailMoneyByYearForMonths(StatisticalDimensionDataMonth t);

	/**
	 * 根据区域分组，计算各种额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> getMoneysGroupByArea(StatisticalDimensionDataMonth t);

	/**
	 * 根据区域分组，计算各种累计额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> getMoneysGroupByAreaLj(StatisticalDimensionDataMonth t);
	
	/**
	 * 根据区域分组，计算各种渠道累计额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> getMoneysGroupByAreaForQd(StatisticalDimensionDataMonth t);
	
	/**
	 * 根据分公司任务系数 计算 结算任务目标
	 * @param t
	 * @return
	 */
	String getDimensionDataMonthFgsJieSuanTask(StatisticalDimensionDataMonth t);
	
	/**
	 * 根据年份，月份，省份，查询该省份下各城市零售量
	 * @author Liang,HouEn
	 * 2015-2-3
	 * @param t
	 * @return
	 */
	List<HashMap> getCitysDataList(StatisticalDimensionDataMonth t);

}