package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSalesmanInfoLog;


public interface KonkaSalesmanInfoLogService {

	Long createKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t);

	int modifyKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t);

	int removeKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t);

	KonkaSalesmanInfoLog getKonkaSalesmanInfoLog(KonkaSalesmanInfoLog t);

	List<KonkaSalesmanInfoLog> getKonkaSalesmanInfoLogList(KonkaSalesmanInfoLog t);

	Long getKonkaSalesmanInfoLogCount(KonkaSalesmanInfoLog t);

	List<KonkaSalesmanInfoLog> getKonkaSalesmanInfoLogPaginatedList(KonkaSalesmanInfoLog t);

}