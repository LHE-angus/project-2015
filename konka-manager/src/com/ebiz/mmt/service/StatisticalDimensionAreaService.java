package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionArea;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionAreaService {

	Long createStatisticalDimensionArea(StatisticalDimensionArea t);

	int modifyStatisticalDimensionArea(StatisticalDimensionArea t);

	int removeStatisticalDimensionArea(StatisticalDimensionArea t);

	StatisticalDimensionArea getStatisticalDimensionArea(StatisticalDimensionArea t);

	List<StatisticalDimensionArea> getStatisticalDimensionAreaList(StatisticalDimensionArea t);

	Long getStatisticalDimensionAreaCount(StatisticalDimensionArea t);

	List<StatisticalDimensionArea> getStatisticalDimensionAreaPaginatedList(StatisticalDimensionArea t);

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	List<StatisticalDimensionArea> getStatisticalDimensionAreaForCustomerList(StatisticalDimensionArea t);

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	Long getStatisticalDimensionAreaForCustomerCount(StatisticalDimensionArea t);

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	List<StatisticalDimensionArea> getStatisticalDimensionAreaForCustomerPaginatedList(StatisticalDimensionArea t);

	/**
	 * 执行插入数据的SQL语句-乡镇 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int createStatisticalDimensionAreaWithTown();

	/**
	 * 执行插入数据的SQL语句-县 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int createStatisticalDimensionAreaWithCounty();

	/**
	 * 执行插入数据的SQL语句-市 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int createStatisticalDimensionAreaWithCity();

	/**
	 * 执行插入数据的SQL语句-省 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int createStatisticalDimensionAreaWithProvince();

	/**
	 * 删除表中的全部数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int removeStatisticalDimensionAreaAll(StatisticalDimensionArea t);

	List<HashMap> getAreaListForMap(StatisticalDimensionArea t);
	
	/**
	 * 根据城市id获取城市名称
	 * @author Liang,HouEn
	 * 2015-2-4
	 * @param t
	 * @return
	 */
	HashMap getCityNameById(StatisticalDimensionArea t);
}