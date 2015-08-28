package com.ebiz.mmt.dao;

import java.util.List;
import com.ebiz.ssi.dao.EntityDao;
import com.ebiz.mmt.domain.KonkaMobileFightReport;

public interface KonkaMobileFightReportDao extends
		EntityDao<KonkaMobileFightReport> {
	List<KonkaMobileFightReport> selectKonkaMobileFightReportPaginatedListForQuery(
			KonkaMobileFightReport t);
	List<KonkaMobileFightReport> selectKonkaMobileFightReportPaginatedListForQueryNew(
			KonkaMobileFightReport t);
	List<KonkaMobileFightReport> selectSumNum(KonkaMobileFightReport t);
	List<KonkaMobileFightReport> selectSumPrice(KonkaMobileFightReport t);
	Long sumKonkaMobileFightReportCount(KonkaMobileFightReport t);
}