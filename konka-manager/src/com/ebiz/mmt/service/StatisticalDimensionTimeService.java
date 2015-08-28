package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionTime;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
public interface StatisticalDimensionTimeService {

	Long createStatisticalDimensionTime(StatisticalDimensionTime t);

	int modifyStatisticalDimensionTime(StatisticalDimensionTime t);

	int removeStatisticalDimensionTime(StatisticalDimensionTime t);

	StatisticalDimensionTime getStatisticalDimensionTime(StatisticalDimensionTime t);

	List<StatisticalDimensionTime> getStatisticalDimensionTimeList(StatisticalDimensionTime t);

	Long getStatisticalDimensionTimeCount(StatisticalDimensionTime t);

	List<StatisticalDimensionTime> getStatisticalDimensionTimePaginatedList(StatisticalDimensionTime t);

}