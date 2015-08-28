package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionStoreDao;
import com.ebiz.mmt.domain.StatisticalDimensionStore;
import com.ebiz.mmt.service.StatisticalDimensionStoreService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-18 11:36:03
 */
@Service
public class StatisticalDimensionStoreServiceImpl implements StatisticalDimensionStoreService {

	@Resource
	private StatisticalDimensionStoreDao statisticalDimensionStoreDao;

	public Long createStatisticalDimensionStore(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.insertEntity(t);
	}

	public StatisticalDimensionStore getStatisticalDimensionStore(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.selectEntity(t);
	}

	public Long getStatisticalDimensionStoreCount(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionStore> getStatisticalDimensionStoreList(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionStore(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.updateEntity(t);
	}

	public int removeStatisticalDimensionStore(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.deleteEntity(t);
	}

	public List<StatisticalDimensionStore> getStatisticalDimensionStorePaginatedList(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.selectEntityPaginatedList(t);
	}

	@Override
	public int initStatisticalDimensionStore() {
		return this.statisticalDimensionStoreDao.initStatisticalDimensionStore();
	}

	@Override
	public int removeStatisticalDimensionStoreAll(StatisticalDimensionStore t) {
		return this.statisticalDimensionStoreDao.deleteStatisticalDimensionStoreAll(t);
	}

}
