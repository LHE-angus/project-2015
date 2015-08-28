package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionRetailDataDao;
import com.ebiz.mmt.domain.StatisticalDimensionRetailData;
import com.ebiz.mmt.service.StatisticalDimensionRetailDataService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
@Service
public class StatisticalDimensionRetailDataServiceImpl implements StatisticalDimensionRetailDataService {

	@Resource
	private StatisticalDimensionRetailDataDao statisticalDimensionRetailDataDao;

	public Long createStatisticalDimensionRetailData(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.insertEntity(t);
	}

	public StatisticalDimensionRetailData getStatisticalDimensionRetailData(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectEntity(t);
	}

	public Long getStatisticalDimensionRetailDataCount(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionRetailData> getStatisticalDimensionRetailDataList(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionRetailData(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.updateEntity(t);
	}

	public int removeStatisticalDimensionRetailData(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.deleteEntity(t);
	}

	public List<StatisticalDimensionRetailData> getStatisticalDimensionRetailDataPaginatedList(
			StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectEntityPaginatedList(t);
	}

	@Override
	public int deleteStatisticalDimensionRetailDataByIDs() {
		return this.statisticalDimensionRetailDataDao.deleteStatisticalDimensionRetailDataByIDs();
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataDelete() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataDelete();
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsert() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataInsert();
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsertForLsAndFx() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataInsertForLsAndFx();
	}

	/**
	 * 结算(地采)数据的删除（一个月） 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataDeleteForDiCai() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataDeleteForDiCai();
	}

	/**
	 * 结算(地采)数据的插入(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsertForDiCai() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataInsertForDiCai();
	}

	/**
	 * 结算(集采)数据的删除(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataDeleteForJiCai() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataDeleteForJiCai();
	}

	/**
	 * 结算(集采)数据的插入(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsertForJiCai() {
		return this.statisticalDimensionRetailDataDao.AutoRunStatisticalDimensionRetailDataInsertForJiCai();
	}

	/**
	 * 查询指定省份下各城市尺寸段数据
	 */
	public List<HashMap> getSizeOfCityList(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectSizeOfCityList(t);
	}

	/**
	 * 根据城市ID，查询该城市下该年份，月份的销量前10机型
	 */
	public List<HashMap> getTop10CityList(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectTop10CityList(t);
	}

	/**
	 * 根据城市id获取各机型销售数据
	 */
	public List<HashMap> getModleDataList(StatisticalDimensionRetailData t) {
		return this.statisticalDimensionRetailDataDao.selectModleDataList(t);
	}

}
