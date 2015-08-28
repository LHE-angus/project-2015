package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataDao;
import com.ebiz.mmt.domain.StatisticalDimensionData;
import com.ebiz.mmt.service.StatisticalDimensionDataService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:04
 */
@Service
public class StatisticalDimensionDataServiceImpl implements StatisticalDimensionDataService {

	@Resource
	private StatisticalDimensionDataDao statisticalDimensionDataDao;

	public Long createStatisticalDimensionData(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.insertEntity(t);
	}

	public StatisticalDimensionData getStatisticalDimensionData(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectEntity(t);
	}

	public Long getStatisticalDimensionDataCount(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionData> getStatisticalDimensionDataList(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionData(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.updateEntity(t);
	}

	public int removeStatisticalDimensionData(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.deleteEntity(t);
	}

	public List<StatisticalDimensionData> getStatisticalDimensionDataPaginatedList(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectEntityPaginatedList(t);
	}

	@Override
	public int AutoRunUpdateStatementForInsertAndUpdate() {
		return this.statisticalDimensionDataDao.AutoRunUpdateStatementForInsertAndUpdate();
	}

	//门店网点管理 数量
	@Override
	public Long getStatisticalDimensionDataForStoreAndAgentCount(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForStoreAndAgentCount(t);
	}

	//门店网点管理 查询
	@Override
	public List<StatisticalDimensionData> getStatisticalDimensionDataForStoreAndAgentPaginatedList(
			StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForStoreAndAgentPaginatedList(t);
	}

	//客户分类管理 数量
	@Override
	public Long getStatisticalDimensionDataForCustomerFltjCount(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForCustomerFltjCount(t);
	}
   
    //客户分类管理 查询
	@Override
	public List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerFltjPaginatedList(
			StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForCustomerFltjPaginatedList(t);
	}

	//客户分类管理 导出
	public List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerFltjList(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForCustomerFltjList(t);
	}
	
	//门店分类管理 数量
	@Override
	public Long getStatisticalDimensionDataForStoreTypeFltjCount(StatisticalDimensionData t){
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForStoreTypeFltjCount(t);
	}
	
	
	//门店分类管理 查询
	@Override
	public List<StatisticalDimensionData> getStatisticalDimensionDataForStoreTypeFltjPaginatedList(
			StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForStoreTypeFltjPaginatedList(t);
	}
	
	//客户户龄分析 数量
	@Override
	public Long getStatisticalDimensionDataForCustomerAgeCount(StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForCustomerAgeCount(t);
	}

	//客户户龄分析 查询
	@Override
	public List<StatisticalDimensionData> getStatisticalDimensionDataForCustomerAgePaginatedList(
			StatisticalDimensionData t) {
		return this.statisticalDimensionDataDao.selectStatisticalDimensionDataForCustomerAgePaginatedList(t);
	}

	
	public int AutoRunUpdateStatementForDataInsert() {
		return this.statisticalDimensionDataDao.AutoRunUpdateStatementForDataInsert();
	}

}
