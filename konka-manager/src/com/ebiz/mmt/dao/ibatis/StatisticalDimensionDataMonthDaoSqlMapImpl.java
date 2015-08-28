package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataMonthDao;
import com.ebiz.mmt.domain.StatisticalDimensionDataMonth;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-12 10:45:27
 */
@Service
public class StatisticalDimensionDataMonthDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionDataMonth>
		implements StatisticalDimensionDataMonthDao {

	@Override
	public int AutoRunUpdateStatementForDataMonthInsertAndUpdate() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateStatementForDataMonthInsertAndUpdate");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	@Override
	public int AutoRunUpdateStatementForDataMonthInsert() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateStatementForDataMonthInsert");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	// 门店网点分类统计 数量 （月度)
	public Long selectStatisticalDimensionDataMonthForStoreAndAgentCount(StatisticalDimensionDataMonth t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataMonthForStoreAndAgentCount", t);
	}

	// 门店网点统计 查询 （月度)
	@SuppressWarnings("unchecked")
	public List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForStoreAndAgentPaginatedList(
			StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataMonthForStoreAndAgentPaginatedList", t);
	}

	// 客户分类统计 数量 （月度）
	public Long selectStatisticalDimensionDataMonthForCustomerFltjCount(StatisticalDimensionDataMonth t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataMonthForCustomerFltjCount", t);

	}

	// 客户分类统计 查询（月度）
	@SuppressWarnings("unchecked")
	public List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForCustomerFltjPaginatedList(
			StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataMonthForCustomerFltjPaginatedList", t);
	}

	// 门店分类统计 数量 （月度)
	public Long selectStatisticalDimensionDataMonthForStoreTypeFltjCount(StatisticalDimensionDataMonth t) {
		return (Long) this.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataMonthForStoreTypeFltjCount", t);
	}

	// 门店分类统计 查询 （月度)
	@SuppressWarnings("unchecked")
	public List<StatisticalDimensionDataMonth> selectStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList(
			StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataMonthForStoreTypeFltjPaginatedList", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionDataMonth> selectRetailMoneyByYear(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectRetailMoneyByYear", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionDataMonth> selectRetailMoneyByYearForMonths(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectRetailMoneyByYearForMonths", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionDataMonth> selectMoneysGroupByArea(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectMoneysGroupByArea", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionDataMonth> selectMoneysGroupByAreaLj(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectMoneysGroupByAreaLj", t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionDataMonth> selectMoneysGroupByAreaForQd(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectMoneysGroupByAreaForQd", t);
	}	
	
	// 根据分公司任务系数 计算 结算任务目标
	@SuppressWarnings("unchecked")
	@Override
	public String selectDimensionDataMonthFgsJieSuanTask(StatisticalDimensionDataMonth t) {
		return (String)this.getSqlMapClientTemplate().queryForObject("selectDimensionDataMonthFgsJieSuanTask", t);
	}

	@Override
	public List<HashMap> selectCitysDataList(StatisticalDimensionDataMonth t) {
		return super.getSqlMapClientTemplate().queryForList("selectCitysDataList", t);
	}
}
