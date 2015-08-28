package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionKhfgs;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionKhfgsService {

	Long createStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t);

	int modifyStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t);

	int removeStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t);

	StatisticalDimensionKhfgs getStatisticalDimensionKhfgs(StatisticalDimensionKhfgs t);

	List<StatisticalDimensionKhfgs> getStatisticalDimensionKhfgsList(StatisticalDimensionKhfgs t);

	Long getStatisticalDimensionKhfgsCount(StatisticalDimensionKhfgs t);

	List<StatisticalDimensionKhfgs> getStatisticalDimensionKhfgsPaginatedList(StatisticalDimensionKhfgs t);

	/**
	 * 初始化统计分析中的客户分公司数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int initStatisticalDimensionKhfgs();

	/**
	 * 清空统计分析中的客户分公司数据 2014-12-16<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	int removeStatisticalDimensionKhfgsAll(StatisticalDimensionKhfgs t);

}