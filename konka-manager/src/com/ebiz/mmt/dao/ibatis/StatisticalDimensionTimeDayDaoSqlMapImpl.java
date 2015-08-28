package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionTimeDayDao;
import com.ebiz.mmt.domain.StatisticalDimensionTimeDay;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-25 21:58:48
 */
@Service
public class StatisticalDimensionTimeDayDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionTimeDay>
		implements StatisticalDimensionTimeDayDao {

	@Override
	public int updateStatisticalDimensionTimeDayByDate(StatisticalDimensionTimeDay t) {
		return this.getSqlMapClientTemplate().update("updateStatisticalDimensionTimeDayByDate", t);
	}

}
