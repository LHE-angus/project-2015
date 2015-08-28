package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.ssi.dao.EntityDao;

public interface KonkaParagonShowinfoDao extends
		EntityDao<KonkaParagonShowinfo> {
	public List<KonkaParagonShowinfo> selectDistinctShopCode(
			KonkaParagonShowinfo t);

	KonkaParagonShowinfo selectEntityForView(KonkaParagonShowinfo t);

	String generateShopCode(KonkaParagonShowinfo t) throws DataAccessException;

	public List<KonkaParagonShowinfo> selectEntityListForSelct(
			KonkaParagonShowinfo t) throws DataAccessException;

	public long selectKonkaParagonShowinfoCountForsub(KonkaParagonShowinfo t)
			throws DataAccessException;

	public List<KonkaParagonShowinfo> selectKonkaParagonShowinfoPaginatedListForSub(
			KonkaParagonShowinfo t) throws DataAccessException;

	public Long selectEntityListForSelctCount(KonkaParagonShowinfo t)
			throws DataAccessException;

}