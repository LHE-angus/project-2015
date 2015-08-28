package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaMobileFightReportDao;
import com.ebiz.mmt.domain.KonkaMobileFightReport;

@Repository
public class KonkaMobileFightReportDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaMobileFightReport> implements
		KonkaMobileFightReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaMobileFightReport> selectKonkaMobileFightReportPaginatedListForQuery(
			KonkaMobileFightReport t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaMobileFightReportPaginatedListForQuery", t);
	}
	@Override
	public List<KonkaMobileFightReport> selectKonkaMobileFightReportPaginatedListForQueryNew(
			KonkaMobileFightReport t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectKonkaMobileFightReportPaginatedListForQueryNew", t);
	}
	
	@Override
	public List<KonkaMobileFightReport> selectSumPrice(KonkaMobileFightReport t) {
		return super.getSqlMapClientTemplate().queryForList(
				"sumPrice", t);
	}
	
	@Override
	public List<KonkaMobileFightReport> selectSumNum(KonkaMobileFightReport t) {
		return super.getSqlMapClientTemplate().queryForList(
				"selectSumNum", t);
	}
	
	@Override
	public Long sumKonkaMobileFightReportCount(KonkaMobileFightReport t) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("sumKonkaMobileFightReportCount", t);
	}
	
}
