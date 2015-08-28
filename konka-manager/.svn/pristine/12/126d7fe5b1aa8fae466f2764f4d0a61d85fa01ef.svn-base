package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionDataDao;
import com.ebiz.mmt.domain.StatisticalDimensionData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-05 20:35:03
 */
@Service
public class StatisticalDimensionDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionData> implements
		StatisticalDimensionDataDao {

	/**
	 * 执行插入数据的SQL语句 2014-11-07<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	@Override
	public int AutoRunUpdateStatementForInsertAndUpdate() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateStatementForInsertAndUpdate");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public Long selectStatisticalDimensionDataForStoreAndAgentCount(StatisticalDimensionData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataForStoreAndAgentCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionData> selectStatisticalDimensionDataForStoreAndAgentPaginatedList(
			StatisticalDimensionData t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataForStoreAndAgentPaginatedList", t);
	}

	@Override
	public Long selectStatisticalDimensionDataForCustomerFltjCount(StatisticalDimensionData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataForCustomerFltjCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerFltjPaginatedList(
			StatisticalDimensionData t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataForCustomerFltjPaginatedList", t);
	}
	
	//客户分类管理  导出
	@SuppressWarnings("unchecked")
	public List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerFltjList(
			StatisticalDimensionData t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataForCustomerFltjList", t);
	}

	public Long selectStatisticalDimensionDataForStoreTypeFltjCount(StatisticalDimensionData t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStatisticalDimensionDataForStoreTypeFltjCount",t);
	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticalDimensionData> selectStatisticalDimensionDataForStoreTypeFltjPaginatedList(
			StatisticalDimensionData t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataForStoreTypeFltjPaginatedList", t);
	}
	//客户户龄分析
	@Override
	public Long selectStatisticalDimensionDataForCustomerAgeCount(StatisticalDimensionData t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectStatisticalDimensionDataForCustomerAgeCount", t);
	}

	//客户户龄分析
	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalDimensionData> selectStatisticalDimensionDataForCustomerAgePaginatedList(
			StatisticalDimensionData t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectStatisticalDimensionDataForCustomerAgePaginatedList", t);
	}
	/**
	 * 更新过度的数据到最终的表中 2014-11-13<br/>
	 * 返回1说明正常结束，返回0说明报异常
	 */
	@Override
	public int AutoRunUpdateStatementForDataInsert() {
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateStatementForDataInsert");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
