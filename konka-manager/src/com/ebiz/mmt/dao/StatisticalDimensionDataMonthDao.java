package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:27
 */
public interface StatisticalDimensionDataMonthDao extends EntityDao<StatisticalDimensionDataMonth> {

	/**
	 * 执行插入数据的SQL语句 2014-11-07<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForDataMonthInsertAndUpdate();

	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int AutoRunUpdateStatementForDataMonthInsert();

	// 门店网点统计 数量 （月度）
	Long selectStatisticalDimensionDataMonthForStoreAndAgentCount(StatisticalDimensionDataMonth t);

	// 门店网店统计查询 （月度）
	List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(
			StatisticalDimensionDataMonth t);

	// 客户分类统计 数量 （月度）
	Long selectStatisticalDimensionDataMonthForCustomerFltjCount(StatisticalDimensionDataMonth t);

	// 客户分类统计 查询（月度）
	List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForCustomerFltjPaginatedList(
			StatisticalDimensionDataMonth t);

	// 门店分类统计 数量（月度）
	Long selectStatisticalDimensionDataMonthForStoreTypeFltjCount(StatisticalDimensionDataMonth t);

	// 门店分类统计 查询（月度)
	List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList(
			StatisticalDimensionDataMonth t);

	/**
	 * 根据年获得目标销售额，实际销售额，同期销售额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> selectRetailMoneyByYear(StatisticalDimensionDataMonth t);

	/**
	 * 根据年获得每月的目标销售额，实际销售额，同期销售额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> selectRetailMoneyByYearForMonths(StatisticalDimensionDataMonth t);

	/**
	 * 根据区域分组，计算各种额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> selectMoneysGroupByArea(StatisticalDimensionDataMonth t);

	/**
	 * 根据区域分组，计算各种累计额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> selectMoneysGroupByAreaLj(StatisticalDimensionDataMonth t);
	
	/**
	 * 根据区域分组，计算各渠道销售累计额
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionDataMonth> selectMoneysGroupByAreaForQd(StatisticalDimensionDataMonth t);
	
	/**
	 * 根据分公司任务系数 计算 结算任务目标
	 * @param t
	 * @return
	 */
	String selectDimensionDataMonthFgsJieSuanTask(StatisticalDimensionDataMonth t) ;
	
	/**
	 * 根据年份，月份，省份，查询该省份下各城市零售量
	 * @author Liang,HouEn
	 * 2015-2-3
	 * @param t
	 * @return
	 */
	List<HashMap> selectCitysDataList(StatisticalDimensionDataMonth t);
}
