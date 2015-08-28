package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionFgsLatlngDao;
import com.ebiz.mmt.domain.StatisticalDimensionFgsLatlng;
import com.ebiz.mmt.service.StatisticalDimensionFgsLatlngService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 17:57:41
 */
@Service
public class StatisticalDimensionFgsLatlngServiceImpl implements StatisticalDimensionFgsLatlngService {

	@Resource
	private StatisticalDimensionFgsLatlngDao statisticalDimensionFgsLatlngDao;
	

	public Long createStatisticalDimensionFgsLatlng(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.insertEntity(t);
	}

	public StatisticalDimensionFgsLatlng getStatisticalDimensionFgsLatlng(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.selectEntity(t);
	}

	public Long getStatisticalDimensionFgsLatlngCount(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionFgsLatlng> getStatisticalDimensionFgsLatlngList(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionFgsLatlng(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.updateEntity(t);
	}

	public int removeStatisticalDimensionFgsLatlng(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.deleteEntity(t);
	}

	public List<StatisticalDimensionFgsLatlng> getStatisticalDimensionFgsLatlngPaginatedList(StatisticalDimensionFgsLatlng t) {
		return this.statisticalDimensionFgsLatlngDao.selectEntityPaginatedList(t);
	}

}
