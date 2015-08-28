package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionSaleAreaDao;
import com.ebiz.mmt.domain.StatisticalDimensionSaleArea;
import com.ebiz.mmt.service.StatisticalDimensionSaleAreaService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
@Service
public class StatisticalDimensionSaleAreaServiceImpl implements StatisticalDimensionSaleAreaService {

	@Resource
	private StatisticalDimensionSaleAreaDao statisticalDimensionSaleAreaDao;

	public Long createStatisticalDimensionSaleArea(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.insertEntity(t);
	}

	public StatisticalDimensionSaleArea getStatisticalDimensionSaleArea(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectEntity(t);
	}

	public Long getStatisticalDimensionSaleAreaCount(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionSaleArea> getStatisticalDimensionSaleAreaList(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionSaleArea(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.updateEntity(t);
	}

	public int removeStatisticalDimensionSaleArea(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.deleteEntity(t);
	}

	public List<StatisticalDimensionSaleArea> getStatisticalDimensionSaleAreaPaginatedList(
			StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getStatisticalDimensionSaleAreaWithPindexCount(StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectStatisticalDimensionSaleAreaWithPindexCount(t);
	}

	@Override
	public List<StatisticalDimensionSaleArea> getStatisticalDimensionSaleAreaWithPindexPaginatedList(
			StatisticalDimensionSaleArea t) {
		return this.statisticalDimensionSaleAreaDao.selectStatisticalDimensionSaleAreaWithPindexPaginatedList(t);
	}

}
