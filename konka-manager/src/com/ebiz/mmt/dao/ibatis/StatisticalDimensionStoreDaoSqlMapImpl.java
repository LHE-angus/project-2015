package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionStoreDao;
import com.ebiz.mmt.domain.StatisticalDimensionStore;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-18 11:36:03
 */
@Service
public class StatisticalDimensionStoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionStore> implements
		StatisticalDimensionStoreDao {

	@Override
	public int initStatisticalDimensionStore() {
		try {
			this.getSqlMapClientTemplate().update("initStatisticalDimensionStore");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteStatisticalDimensionStoreAll(StatisticalDimensionStore t) {
		try {
			this.getSqlMapClientTemplate().delete("deleteStatisticalDimensionStoreAll", t);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
