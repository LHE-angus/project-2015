package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionRetailData;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
public interface StatisticalDimensionRetailDataService {

	Long createStatisticalDimensionRetailData(StatisticalDimensionRetailData t);

	int modifyStatisticalDimensionRetailData(StatisticalDimensionRetailData t);

	int removeStatisticalDimensionRetailData(StatisticalDimensionRetailData t);

	StatisticalDimensionRetailData getStatisticalDimensionRetailData(StatisticalDimensionRetailData t);

	List<StatisticalDimensionRetailData> getStatisticalDimensionRetailDataList(StatisticalDimensionRetailData t);

	Long getStatisticalDimensionRetailDataCount(StatisticalDimensionRetailData t);

	List<StatisticalDimensionRetailData> getStatisticalDimensionRetailDataPaginatedList(StatisticalDimensionRetailData t);

	/**
	 * 因为目前零售数据只允许修改两天之内的数据，为了保险起见，两天之内的数据的处理方法时候删除三天之内同步过的，然后重新更新三天之内的；
	 * 但是对于无效的数据
	 * ，因为无效的数据没有时间限制，所以处理方法是，查询三天以前的无效数据，并且更新时间实在三天之内的，说明是三天之内修改的，然后根据ID
	 * 更新已经同步的数据 ---此处是更新无效数据的处理 2014-12-10
	 * 
	 * @return
	 */
	public int deleteStatisticalDimensionRetailDataByIDs();

	/**
	 * 因为目前零售数据只允许修改两天之内的数据，为了保险起见，两天之内的数据的处理方法时候删除三天之内同步过的，然后重新更新三天之内的；
	 * 但是对于无效的数据
	 * ，因为无效的数据没有时间限制，所以处理方法是，查询三天以前的无效数据，并且更新时间实在三天之内的，说明是三天之内修改的，然后根据ID
	 * 更新已经同步的数据 ---此处是删除三天之内的数据 2014-12-10
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataDelete();

	/**
	 * 因为目前零售数据只允许修改两天之内的数据，为了保险起见，两天之内的数据的处理方法时候删除三天之内同步过的，然后重新更新三天之内的；
	 * 但是对于无效的数据
	 * ，因为无效的数据没有时间限制，所以处理方法是，查询三天以前的无效数据，并且更新时间实在三天之内的，说明是三天之内修改的，然后根据ID
	 * 更新已经同步的数据 ---此处是插入三天之内的数据 2014-12-10
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsert();

	/**
	 * 客户端零售和分销数据的插入 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsertForLsAndFx();

	/**
	 * 结算(地采)数据的删除（一个月） 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataDeleteForDiCai();

	/**
	 * 结算(地采)数据的插入(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsertForDiCai();

	/**
	 * 结算(集采)数据的删除(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataDeleteForJiCai();

	/**
	 * 结算(集采)数据的插入(一个月) 2014-12-11
	 * 
	 * @return
	 */
	public int AutoRunStatisticalDimensionRetailDataInsertForJiCai();
	
	/**
	 * 查询指定省份下各城市尺寸段数据
	 * @author Liang,HouEn
	 * 2015-2-3
	 * @param t
	 * @return
	 */
	List<HashMap> getSizeOfCityList(StatisticalDimensionRetailData t);
	
	/**
	 * 根据城市ID，查询该城市下该年份，月份的销量前10机型
	 * @author Liang,HouEn
	 * 2015-2-4
	 * @param t
	 * @return
	 */
	List<HashMap> getTop10CityList(StatisticalDimensionRetailData t);
	
	/**
	 * 根据城市id获取各机型销售数据
	 * @author Liang,HouEn
	 * 2015-2-4
	 * @param t
	 * @return
	 */
	List<HashMap> getModleDataList(StatisticalDimensionRetailData t);

}