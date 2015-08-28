package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaParagonShowinfo;

public interface KonkaParagonShowinfoService {

	Long createKonkaParagonShowinfo(KonkaParagonShowinfo t);

	int modifyKonkaParagonShowinfo(KonkaParagonShowinfo t);

	int removeKonkaParagonShowinfo(KonkaParagonShowinfo t);

	KonkaParagonShowinfo getKonkaParagonShowinfo(KonkaParagonShowinfo t);

	List<KonkaParagonShowinfo> getKonkaParagonShowinfoList(
			KonkaParagonShowinfo t);

	Long getKonkaParagonShowinfoCount(KonkaParagonShowinfo t);

	List<KonkaParagonShowinfo> getKonkaParagonShowinfoPaginatedList(
			KonkaParagonShowinfo t);

	List<KonkaParagonShowinfo> selectDistinctShopCode(KonkaParagonShowinfo t);

	KonkaParagonShowinfo getKonkaParagonShowinfoForView(KonkaParagonShowinfo t);

	void saveKonkaParagonShowinfo(KonkaParagonShowinfo t);

	String generateShopCode(KonkaParagonShowinfo t);
	
	Long getKonkaParagonShowinfoListForSelctCount(KonkaParagonShowinfo t);
	
	List<KonkaParagonShowinfo> getKonkaParagonShowinfoListForSelct(
			KonkaParagonShowinfo t);

	long selectKonkaParagonShowinfoCountForsub(KonkaParagonShowinfo t)
			throws DataAccessException;

	List<KonkaParagonShowinfo> selectKonkaParagonShowinfoPaginatedListForSub(
			KonkaParagonShowinfo t) throws DataAccessException;
}
