package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionAreaDao;
import com.ebiz.mmt.domain.StatisticalDimensionArea;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
@Service
public class StatisticalDimensionAreaDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionArea> implements
		StatisticalDimensionAreaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionArea> selectStatisticalDimensionAreaForCustomerList(StatisticalDimensionArea entity) {
		return this.getSqlMapClientTemplate().queryForList("selectStatisticalDimensionAreaForCustomerList", entity);
	}

	@Override
	public Long selectStatisticalDimensionAreaForCustomerCount(StatisticalDimensionArea entity) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("selectStatisticalDimensionAreaForCustomerCount",
				entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionArea> selectStatisticalDimensionAreaForCustomerPaginatedList(
			StatisticalDimensionArea entity) {
		return this.getSqlMapClientTemplate().queryForList("selectStatisticalDimensionAreaForCustomerPaginatedList",
				entity);
	}

	@Override
	public int insertStatisticalDimensionAreaWithTown() {
		try {
			this.getSqlMapClientTemplate().update("insertStatisticalDimensionAreaWithTown");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int insertStatisticalDimensionAreaWithCounty() {
		try {
			this.getSqlMapClientTemplate().update("insertStatisticalDimensionAreaWithCounty");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int insertStatisticalDimensionAreaWithCity() {
		try {
			this.getSqlMapClientTemplate().update("insertStatisticalDimensionAreaWithCity");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int insertStatisticalDimensionAreaWithProvince() {
		try {
			this.getSqlMapClientTemplate().update("insertStatisticalDimensionAreaWithProvince");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteStatisticalDimensionAreaAll(StatisticalDimensionArea t) {
		try {
			this.getSqlMapClientTemplate().delete("deleteStatisticalDimensionAreaAll", t);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public List<HashMap> getAreaListForMap(StatisticalDimensionArea t) {
		return this.getSqlMapClientTemplate().queryForList("getAreaListForMap",t);
	}

	@Override
	public HashMap selectCityNameById(StatisticalDimensionArea t) {
		return (HashMap) this.getSqlMapClientTemplate().queryForObject("selectCityNameById",t);
	}

}
