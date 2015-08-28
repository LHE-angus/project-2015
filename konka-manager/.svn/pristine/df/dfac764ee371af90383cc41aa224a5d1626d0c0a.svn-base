package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionSaleAreaDao;
import com.ebiz.mmt.domain.StatisticalDimensionSaleArea;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
@Service
public class StatisticalDimensionSaleAreaDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionSaleArea>
		implements StatisticalDimensionSaleAreaDao {

	@Override
	public Long selectStatisticalDimensionSaleAreaWithPindexCount(StatisticalDimensionSaleArea t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionSaleAreaWithPindexCount", t);
	}

	@Override
	public List<StatisticalDimensionSaleArea> selectStatisticalDimensionSaleAreaWithPindexPaginatedList(
			StatisticalDimensionSaleArea t) {
		return this.getSqlMapClientTemplate().queryForList("selectStatisticalDimensionSaleAreaWithPindexPaginatedList",
				t);
	}

}
