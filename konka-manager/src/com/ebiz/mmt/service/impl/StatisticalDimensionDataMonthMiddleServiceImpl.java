package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataMonthMiddleDao;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonthMiddle;
import com.ebiz.mmt.service.StatisticalDimensionDataMonthMiddleService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-13 17:30:27
 */
@Service
public class StatisticalDimensionDataMonthMiddleServiceImpl implements StatisticalDimensionDataMonthMiddleService {

	@Resource
	private StatisticalDimensionDataMonthMiddleDao statisticalDimensionDataMonthMiddleDao;
	

	public Long createStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.insertEntity(t);
	}

	public StatisticalDimensionDataMonthMiddle getStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.selectEntity(t);
	}

	public Long getStatisticalDimensionDataMonthMiddleCount(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionDataMonthMiddle> getStatisticalDimensionDataMonthMiddleList(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.updateEntity(t);
	}

	public int removeStatisticalDimensionDataMonthMiddle(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.deleteEntity(t);
	}

	public List<StatisticalDimensionDataMonthMiddle> getStatisticalDimensionDataMonthMiddlePaginatedList(StatisticalDimensionDataMonthMiddle t) {
		return this.statisticalDimensionDataMonthMiddleDao.selectEntityPaginatedList(t);
	}

}
