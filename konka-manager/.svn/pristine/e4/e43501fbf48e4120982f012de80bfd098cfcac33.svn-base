package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.StatisticalDimensionMd;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
public interface StatisticalDimensionMdService {

	Long createStatisticalDimensionMd(StatisticalDimensionMd t);

	int modifyStatisticalDimensionMd(StatisticalDimensionMd t);

	int removeStatisticalDimensionMd(StatisticalDimensionMd t);

	StatisticalDimensionMd getStatisticalDimensionMd(StatisticalDimensionMd t);

	List<StatisticalDimensionMd> getStatisticalDimensionMdList(StatisticalDimensionMd t);

	Long getStatisticalDimensionMdCount(StatisticalDimensionMd t);

	List<StatisticalDimensionMd> getStatisticalDimensionMdPaginatedList(StatisticalDimensionMd t);
	/**
	 * 初始化统计分析中的商品数据 2014-12-19<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int initStatisticalDimensionMd();

	/**
	 * 清空统计分析中的商品数据 2014-12-19<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int removeStatisticalDimensionMdAll(StatisticalDimensionMd t);

	 //品牌
	 List<Map<String, String>> getBrandList(StatisticalDimensionMd v);
   //品类
	 List<Map<String, String>> getBrandTypeList(StatisticalDimensionMd v);
   //尺寸段
	 List<Map<String, String>> getSizeSecList(StatisticalDimensionMd v);
  //尺寸
	 List<Map<String, String>> getMdSizeList(StatisticalDimensionMd v);
  //价格段
	 List<Map<String, String>> getPriceDuanList(StatisticalDimensionMd v);
}