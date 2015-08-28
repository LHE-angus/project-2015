package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.StatisticalDimensionSaleArea;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
public interface StatisticalDimensionSaleAreaDao extends EntityDao<StatisticalDimensionSaleArea> {

	/**
	 * 关联区域名称
	 * 
	 * @param t
	 * @return
	 */
	Long selectStatisticalDimensionSaleAreaWithPindexCount(StatisticalDimensionSaleArea t);

	/**
	 * 关联区域名称
	 * 
	 * @param t
	 * @return
	 */
	List<StatisticalDimensionSaleArea> selectStatisticalDimensionSaleAreaWithPindexPaginatedList(
			StatisticalDimensionSaleArea t);
}
