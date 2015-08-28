package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataMiddleDao;
import com.ebiz.mmt.domain.StatisticalDimensionDataMiddle;
import com.ebiz.mmt.service.StatisticalDimensionDataMiddleService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-13 17:30:27
 */
@Service
public class StatisticalDimensionDataMiddleServiceImpl implements StatisticalDimensionDataMiddleService {

	@Resource
	private StatisticalDimensionDataMiddleDao statisticalDimensionDataMiddleDao;
	

	public Long createStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.insertEntity(t);
	}

	public StatisticalDimensionDataMiddle getStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.selectEntity(t);
	}

	public Long getStatisticalDimensionDataMiddleCount(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionDataMiddle> getStatisticalDimensionDataMiddleList(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.updateEntity(t);
	}

	public int removeStatisticalDimensionDataMiddle(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.deleteEntity(t);
	}

	public List<StatisticalDimensionDataMiddle> getStatisticalDimensionDataMiddlePaginatedList(StatisticalDimensionDataMiddle t) {
		return this.statisticalDimensionDataMiddleDao.selectEntityPaginatedList(t);
	}

}
