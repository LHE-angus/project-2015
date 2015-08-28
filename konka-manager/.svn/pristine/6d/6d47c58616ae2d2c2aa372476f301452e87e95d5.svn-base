package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionTimeMonthDao;
import com.ebiz.mmt.domain.StatisticalDimensionTimeMonth;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:27
 */
@Service
public class StatisticalDimensionTimeMonthDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionTimeMonth>
		implements StatisticalDimensionTimeMonthDao {

	@Override
	public List<Integer> selectStatisticalDimensionTimeMonthGroupByYear(StatisticalDimensionTimeMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectStatisticalDimensionTimeMonthGroupByYear", t);
	}

	@Override
	public List<StatisticalDimensionTimeMonth> selectStatisticalDimensionTimeMontOrderByMonthAsc(
			StatisticalDimensionTimeMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectStatisticalDimensionTimeMontOrderByMonthAsc", t);
	}

}
