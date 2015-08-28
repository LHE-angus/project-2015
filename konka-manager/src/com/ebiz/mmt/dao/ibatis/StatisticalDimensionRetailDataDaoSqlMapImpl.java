package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionRetailDataDao;
import com.ebiz.mmt.domain.StatisticalDimensionRetailData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
@Service
public class StatisticalDimensionRetailDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionRetailData>
		implements StatisticalDimensionRetailDataDao {

	@Override
	public int deleteStatisticalDimensionRetailDataByIDs() {
		try {
			this.getSqlMapClientTemplate().update("deleteStatisticalDimensionRetailDataByIDs");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataDelete() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataDelete");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsert() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataInsert");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsertForLsAndFx() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataInsertForLsAndFx");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataDeleteForDiCai() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataDeleteForDiCai");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsertForDiCai() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataInsertForDiCai");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataDeleteForJiCai() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataDeleteForJiCai");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunStatisticalDimensionRetailDataInsertForJiCai() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunStatisticalDimensionRetailDataInsertForJiCai");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public List<HashMap> selectSizeOfCityList(StatisticalDimensionRetailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectSizeOfCityList", t);
	}

	@Override
	public List<HashMap> selectTop10CityList(StatisticalDimensionRetailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectTop10CityList", t);
	}

	@Override
	public List<HashMap> selectModleDataList(StatisticalDimensionRetailData t) {
		return super.getSqlMapClientTemplate().queryForList("selectModleDataList", t);
	}

}
