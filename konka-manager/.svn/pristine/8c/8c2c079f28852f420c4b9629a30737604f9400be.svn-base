package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataMonthDao;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.mmt.service.StatisticalDimensionDataMonthService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:28
 */
@Service
public class StatisticalDimensionDataMonthServiceImpl implements StatisticalDimensionDataMonthService {

	@Resource
	private StatisticalDimensionDataMonthDao statisticalDimensionDataMonthDao;

	public Long createStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.insertEntity(t);
	}

	public StatisticalDimensionDataMonth getStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectEntity(t);
	}

	public Long getStatisticalDimensionDataMonthCount(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthList(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.updateEntity(t);
	}

	public int removeStatisticalDimensionDataMonth(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.deleteEntity(t);
	}

	public List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthPaginatedList(
			StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectEntityPaginatedList(t);
	}

	@Override
	public int AutoRunUpdateStatementForDataMonthInsertAndUpdate() {
		return this.statisticalDimensionDataMonthDao.AutoRunUpdateStatementForDataMonthInsertAndUpdate();
	}

	@Override
	public int AutoRunUpdateStatementForDataMonthInsert() {
		return this.statisticalDimensionDataMonthDao.AutoRunUpdateStatementForDataMonthInsert();
	}

	// 门店网点统计 数量 （月度）
	public Long getStatisticalDimensionDataMonthForStoreAndAgentCount(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectStatisticalDimensionDataMonthForStoreAndAgentCount(t);
	}

	// 门店网点统计 查询（月度）
	public List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(
			StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao
				.selectStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(t);
	}

	// 客户分类统计 数量 （月度）
	public Long getStatisticalDimensionDataMonthForCustomerFltjCount(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectStatisticalDimensionDataMonthForCustomerFltjCount(t);
	}

	// 客户分类统计 查询（月度）
	public List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForCustomerFltjPaginatedList(
			StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectStatisticalDimensionDataMonthForCustomerFltjPaginatedList(t);
	}

	// 门店分类统计 数量 （月度）
	public Long getStatisticalDimensionDataMonthForStoreTypeFltjCount(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectStatisticalDimensionDataMonthForStoreTypeFltjCount(t);
	}

	// 门店分类统计 查询（月度）
	public List<StatisticalDimensionDataMonth> getStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList(
			StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao
				.selectStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList(t);
	}

	@Override
	public List<StatisticalDimensionDataMonth> getRetailMoneyByYear(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectRetailMoneyByYear(t);
	}

	@Override
	public List<StatisticalDimensionDataMonth> getRetailMoneyByYearForMonths(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectRetailMoneyByYearForMonths(t);
	}

	@Override
	public List<StatisticalDimensionDataMonth> getMoneysGroupByArea(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectMoneysGroupByArea(t);
	}

	@Override
	public List<StatisticalDimensionDataMonth> getMoneysGroupByAreaLj(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectMoneysGroupByAreaLj(t);
	}
	
	@Override
	public List<StatisticalDimensionDataMonth> getMoneysGroupByAreaForQd(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectMoneysGroupByAreaForQd(t);
	}
	@Override
	public String getDimensionDataMonthFgsJieSuanTask(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectDimensionDataMonthFgsJieSuanTask(t);
	}

	@Override
	public List<HashMap> getCitysDataList(StatisticalDimensionDataMonth t) {
		return this.statisticalDimensionDataMonthDao.selectCitysDataList(t);
	}
}
