package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionArea;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionAreaDao extends EntityDao<StatisticalDimensionArea> {

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	List<StatisticalDimensionArea> selectStatisticalDimensionAreaForCustomerList(StatisticalDimensionArea entity);

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	Long selectStatisticalDimensionAreaForCustomerCount(StatisticalDimensionArea entity);

	/**
	 * 区域客户统计 2014-11-22
	 * 
	 * @param entity
	 * @return
	 */
	List<StatisticalDimensionArea> selectStatisticalDimensionAreaForCustomerPaginatedList(
			StatisticalDimensionArea entity);

	/**
	 * 执行插入数据的SQL语句-乡镇 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int insertStatisticalDimensionAreaWithTown();

	/**
	 * 执行插入数据的SQL语句-县 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int insertStatisticalDimensionAreaWithCounty();

	/**
	 * 执行插入数据的SQL语句-市 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int insertStatisticalDimensionAreaWithCity();

	/**
	 * 执行插入数据的SQL语句-省 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int insertStatisticalDimensionAreaWithProvince();

	/**
	 * 删除表中的全部数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int deleteStatisticalDimensionAreaAll(StatisticalDimensionArea t);
	
	List<HashMap> getAreaListForMap(StatisticalDimensionArea t);
	
	HashMap selectCityNameById(StatisticalDimensionArea t);
}
