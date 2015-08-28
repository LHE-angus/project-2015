package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionTimeDay;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-25 21:58:48
 */
public interface StatisticalDimensionTimeDayService {

	Long createStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t);

	int modifyStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t);

	int removeStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t);

	StatisticalDimensionTimeDay getStatisticalDimensionTimeDay(StatisticalDimensionTimeDay t);

	List<StatisticalDimensionTimeDay> getStatisticalDimensionTimeDayList(StatisticalDimensionTimeDay t);

	Long getStatisticalDimensionTimeDayCount(StatisticalDimensionTimeDay t);

	List<StatisticalDimensionTimeDay> getStatisticalDimensionTimeDayPaginatedList(StatisticalDimensionTimeDay t);

	/**
	 * 初始化时间数据
	 * 
	 * @return
	 */
	Long initStatisticalDimensionTimeDay();

	/**
	 * 根据开始时间和结束时间维护财务等数据
	 * 
	 * @param t
	 * @return
	 */
	int modifyStatisticalDimensionTimeDayByDate(StatisticalDimensionTimeDay t);

}