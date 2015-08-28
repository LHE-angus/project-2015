package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSalesmanInfo;


public interface KonkaSalesmanInfoService {

	Long createKonkaSalesmanInfo(KonkaSalesmanInfo t);

	int modifyKonkaSalesmanInfo(KonkaSalesmanInfo t);

	int removeKonkaSalesmanInfo(KonkaSalesmanInfo t);

	KonkaSalesmanInfo getKonkaSalesmanInfo(KonkaSalesmanInfo t);

	List<KonkaSalesmanInfo> getKonkaSalesmanInfoList(KonkaSalesmanInfo t);

	Long getKonkaSalesmanInfoCount(KonkaSalesmanInfo t);

	List<KonkaSalesmanInfo> getKonkaSalesmanInfoPaginatedList(KonkaSalesmanInfo t);

}