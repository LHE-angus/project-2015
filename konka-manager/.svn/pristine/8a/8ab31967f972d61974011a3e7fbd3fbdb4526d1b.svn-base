package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionAreaDao;
import com.ebiz.mmt.domain.StatisticalDimensionArea;
import com.ebiz.mmt.service.StatisticalDimensionAreaService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
@Service
public class StatisticalDimensionAreaServiceImpl implements StatisticalDimensionAreaService {

	@Resource
	private StatisticalDimensionAreaDao statisticalDimensionAreaDao;

	public Long createStatisticalDimensionArea(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.insertEntity(t);
	}

	public StatisticalDimensionArea getStatisticalDimensionArea(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectEntity(t);
	}

	public Long getStatisticalDimensionAreaCount(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionArea> getStatisticalDimensionAreaList(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionArea(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.updateEntity(t);
	}

	public int removeStatisticalDimensionArea(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.deleteEntity(t);
	}

	public List<StatisticalDimensionArea> getStatisticalDimensionAreaPaginatedList(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<StatisticalDimensionArea> getStatisticalDimensionAreaForCustomerList(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectStatisticalDimensionAreaForCustomerList(t);
	}

	@Override
	public Long getStatisticalDimensionAreaForCustomerCount(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectStatisticalDimensionAreaForCustomerCount(t);
	}

	@Override
	public List<StatisticalDimensionArea> getStatisticalDimensionAreaForCustomerPaginatedList(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectStatisticalDimensionAreaForCustomerPaginatedList(t);
	}

	@Override
	public int createStatisticalDimensionAreaWithTown() {
		return this.statisticalDimensionAreaDao.insertStatisticalDimensionAreaWithTown();
	}

	@Override
	public int createStatisticalDimensionAreaWithCounty() {
		return this.statisticalDimensionAreaDao.insertStatisticalDimensionAreaWithCounty();
	}

	@Override
	public int createStatisticalDimensionAreaWithCity() {
		return this.statisticalDimensionAreaDao.insertStatisticalDimensionAreaWithCity();
	}

	@Override
	public int createStatisticalDimensionAreaWithProvince() {
		return this.statisticalDimensionAreaDao.insertStatisticalDimensionAreaWithProvince();
	}

	public int removeStatisticalDimensionAreaAll(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.deleteStatisticalDimensionAreaAll(t);
	}

	@Override
	public List<HashMap> getAreaListForMap(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.getAreaListForMap(t);
	}

	@Override
	public HashMap getCityNameById(StatisticalDimensionArea t) {
		return this.statisticalDimensionAreaDao.selectCityNameById(t);
	}

}
