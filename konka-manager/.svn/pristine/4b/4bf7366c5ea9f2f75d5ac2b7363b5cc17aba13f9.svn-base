package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionKhfgsDao;
import com.ebiz.mmt.domain.StatisticalDimensionKhfgs;
import com.ebiz.mmt.service.StatisticalDimensionKhfgsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
@Service
public class StatisticalDimensionKhfgsServiceImpl implements StatisticalDimensionKhfgsService {

	@Resource
	private StatisticalDimensionKhfgsDao statisticalDimensionKhfgsDao;

	public Long createStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.insertEntity(t);
	}

	public StatisticalDimensionKhfgs getStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.selectEntity(t);
	}

	public Long getStatisticalDimensionKhfgsCount(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionKhfgs> getStatisticalDimensionKhfgsList(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.updateEntity(t);
	}

	public int removeStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.deleteEntity(t);
	}

	public List<StatisticalDimensionKhfgs> getStatisticalDimensionKhfgsPaginatedList(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.selectEntityPaginatedList(t);
	}

	@Override
	public int initStatisticalDimensionKhfgs() {
		return this.statisticalDimensionKhfgsDao.initStatisticalDimensionKhfgs();
	}

	@Override
	public int removeStatisticalDimensionKhfgsAll(StatisticalDimensionKhfgs t) {
		return this.statisticalDimensionKhfgsDao.deleteStatisticalDimensionKhfgsAll(t);
	}

}
