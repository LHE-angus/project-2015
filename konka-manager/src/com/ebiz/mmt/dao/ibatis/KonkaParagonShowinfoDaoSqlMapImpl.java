package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.KonkaParagonShowinfoDao;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Repository
public class KonkaParagonShowinfoDaoSqlMapImpl extends
		EntityDaoSqlMapImpl<KonkaParagonShowinfo> implements
		KonkaParagonShowinfoDao {

	@SuppressWarnings("unchecked")
	public List<KonkaParagonShowinfo> selectDistinctShopCode(
			KonkaParagonShowinfo t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectDistinctShopCode", t);
	}

	@Override
	public KonkaParagonShowinfo selectEntityForView(KonkaParagonShowinfo t)
			throws DataAccessException {
		return (KonkaParagonShowinfo) super.getSqlMapClientTemplate()
				.queryForObject("selectKonkaParagonShowinfoForView", t);
	}

	@Override
	public String generateShopCode(KonkaParagonShowinfo t)
			throws DataAccessException {
		return (String) super.getSqlMapClientTemplate().queryForObject(
				"generateShopCode", t);
	}
	
	@Override
	public Long selectEntityListForSelctCount(KonkaParagonShowinfo t)
			throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaParagonShowinfoListForSelctCount", t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaParagonShowinfo> selectEntityListForSelct(
			KonkaParagonShowinfo t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonShowinfoListForSelct", t);
	}

	@Override
	public long selectKonkaParagonShowinfoCountForsub(KonkaParagonShowinfo t)
			throws DataAccessException {
		return (Long) super.getSqlMapClientTemplate().queryForObject(
				"selectKonkaParagonShowinfoCountForsub", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaParagonShowinfo> selectKonkaParagonShowinfoPaginatedListForSub(
			KonkaParagonShowinfo t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"selectKonkaParagonShowinfoPaginatedListForSub", t);
	}

}
