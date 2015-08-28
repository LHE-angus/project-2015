package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionDataMiddle;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-13 17:30:27
 */
public interface StatisticalDimensionDataMiddleService {

	Long createStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t);

	int modifyStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t);

	int removeStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t);

	StatisticalDimensionDataMiddle getStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t);

	List<StatisticalDimensionDataMiddle> getStatisticalDimensionDataMiddleList(StatisticalDimensionDataMiddle t);

	Long getStatisticalDimensionDataMiddleCount(StatisticalDimensionDataMiddle t);

	List<StatisticalDimensionDataMiddle> getStatisticalDimensionDataMiddlePaginatedList(StatisticalDimensionDataMiddle t);

}