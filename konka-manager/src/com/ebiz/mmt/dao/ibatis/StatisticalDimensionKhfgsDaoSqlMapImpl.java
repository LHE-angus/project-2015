package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionKhfgsDao;
import com.ebiz.mmt.domain.StatisticalDimensionKhfgs;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
@Service
public class StatisticalDimensionKhfgsDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionKhfgs> implements
		StatisticalDimensionKhfgsDao {

	@Override
	public int initStatisticalDimensionKhfgs() {
		try {
			this.getSqlMapClientTemplate().update("initStatisticalDimensionKhfgs");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteStatisticalDimensionKhfgsAll(StatisticalDimensionKhfgs t) {
		try {
			this.getSqlMapClientTemplate().delete("deleteStatisticalDimensionKhfgsAll", t);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
