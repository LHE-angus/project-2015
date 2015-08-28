package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionTimeDao;
import com.ebiz.mmt.domain.StatisticalDimensionTime;
import com.ebiz.mmt.service.StatisticalDimensionTimeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
@Service
public class StatisticalDimensionTimeServiceImpl implements StatisticalDimensionTimeService {

	@Resource
	private StatisticalDimensionTimeDao statisticalDimensionTimeDao;
	

	public Long createStatisticalDimensionTime(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.insertEntity(t);
	}

	public StatisticalDimensionTime getStatisticalDimensionTime(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.selectEntity(t);
	}

	public Long getStatisticalDimensionTimeCount(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionTime> getStatisticalDimensionTimeList(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionTime(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.updateEntity(t);
	}

	public int removeStatisticalDimensionTime(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.deleteEntity(t);
	}

	public List<StatisticalDimensionTime> getStatisticalDimensionTimePaginatedList(StatisticalDimensionTime t) {
		return this.statisticalDimensionTimeDao.selectEntityPaginatedList(t);
	}

}
