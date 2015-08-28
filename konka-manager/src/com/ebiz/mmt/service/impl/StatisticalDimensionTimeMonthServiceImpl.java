package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionTimeMonthDao;
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.mmt.service.StatisticalDimensionTimeMonthService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:28
 */
@Service
public class StatisticalDimensionTimeMonthServiceImpl implements StatisticalDimensionTimeMonthService {

	@Resource
	private StatisticalDimensionTimeMonthDao statisticalDimensionTimeMonthDao;

	public Long createStatisticalDimensionTimeMonth(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.insertEntity(t);
	}

	public StatisticalDimensionTimeMonth getStatisticalDimensionTimeMonth(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectEntity(t);
	}

	public Long getStatisticalDimensionTimeMonthCount(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionTimeMonth> getStatisticalDimensionTimeMonthList(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionTimeMonth(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.updateEntity(t);
	}

	public int removeStatisticalDimensionTimeMonth(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.deleteEntity(t);
	}

	public List<StatisticalDimensionTimeMonth> getStatisticalDimensionTimeMonthPaginatedList(
			StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectEntityPaginatedList(t);
	}

	/**
	 * 获取有多少个年度 2014-11-24
	 * 
	 * @param t
	 * @return
	 */
	public List<Integer> getStatisticalDimensionTimeMonthGroupByYear(StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectStatisticalDimensionTimeMonthGroupByYear(t);
	}

	/**
	 * 获取有多少个月度 2014-11-24
	 * 
	 * @param t
	 * @return
	 */
	public List<StatisticalDimensionTimeMonth> getStatisticalDimensionTimeMontOrderByMonthAsc(
			StatisticalDimensionTimeMonth t) {
		return this.statisticalDimensionTimeMonthDao.selectStatisticalDimensionTimeMontOrderByMonthAsc(t);
	}

}
