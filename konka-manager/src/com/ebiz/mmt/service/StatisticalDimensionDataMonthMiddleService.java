package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionDataMonthMiddle;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-13 17:30:27
 */
public interface StatisticalDimensionDataMonthMiddleService {

	Long createStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t);

	int modifyStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t);

	int removeStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t);

	StatisticalDimensionDataMonthMiddle getStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t);

	List<StatisticalDimensionDataMonthMiddle> getStatisticalDimensionDataMonthMiddleList(StatisticalDimensionDataMonthMiddle t);

	Long getStatisticalDimensionDataMonthMiddleCount(StatisticalDimensionDataMonthMiddle t);

	List<StatisticalDimensionDataMonthMiddle> getStatisticalDimensionDataMonthMiddlePaginatedList(StatisticalDimensionDataMonthMiddle t);

}