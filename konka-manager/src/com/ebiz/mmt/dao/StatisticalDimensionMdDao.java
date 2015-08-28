package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.StatisticalDimensionMd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
public interface StatisticalDimensionMdDao extends EntityDao<StatisticalDimensionMd> {
	/**
	 * 初始化统计分析中的商品数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int initStatisticalDimensionMd();
	/**
	 * 清空统计分析中的商品数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int deleteStatisticalDimensionMdAll(StatisticalDimensionMd t);

	   //品牌
	 List<Map<String, String>> selectBrandList(StatisticalDimensionMd v);
    //品类
	 List<Map<String, String>> selectBrandTypeList(StatisticalDimensionMd v);
    //尺寸段
	 List<Map<String, String>> selectSizeSecList(StatisticalDimensionMd v);
   //尺寸
	 List<Map<String, String>> selectMdSizeList(StatisticalDimensionMd v);
   //价格段
	 List<Map<String, String>> selectPriceDuanList(StatisticalDimensionMd v);

}
