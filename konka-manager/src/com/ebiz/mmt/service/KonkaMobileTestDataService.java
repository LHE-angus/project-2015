package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileTestData;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
public interface KonkaMobileTestDataService {

	Long createKonkaMobileTestData(KonkaMobileTestData t);

	int modifyKonkaMobileTestData(KonkaMobileTestData t);

	int removeKonkaMobileTestData(KonkaMobileTestData t);

	KonkaMobileTestData getKonkaMobileTestData(KonkaMobileTestData t);

	List<KonkaMobileTestData> getKonkaMobileTestDataList(KonkaMobileTestData t);

	Long getKonkaMobileTestDataCount(KonkaMobileTestData t);

	List<KonkaMobileTestData> getKonkaMobileTestDataPaginatedList(KonkaMobileTestData t);

	List<KonkaMobileTestData> getKonkaMobileTestDataAndSailDatasPaginatedList(KonkaMobileTestData t);

	List<KonkaMobileTestData> getKonkaMobileTestDataAndCodeList(KonkaMobileTestData t);
	
	List<KonkaMobileTestData> getselectKonkaMobileTestDataAndCode(KonkaMobileTestData t);
}