package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaoaDocInfo;

/**
 * @author Xu,ZhengYang
 * @date 2010-12-13 05:13:04
 */
public interface KonkaoaDocInfoService {

	Long createKonkaoaDocInfo(KonkaoaDocInfo t);

	int modifyKonkaoaDocInfo(KonkaoaDocInfo t);

	int removeKonkaoaDocInfo(KonkaoaDocInfo t);

	KonkaoaDocInfo getKonkaoaDocInfo(KonkaoaDocInfo t);

	List<KonkaoaDocInfo> getKonkaoaDocInfoList(KonkaoaDocInfo t);

	Long getKonkaoaDocInfoCount(KonkaoaDocInfo t);

	List<KonkaoaDocInfo> getKonkaoaDocInfoPaginatedList(KonkaoaDocInfo t);
	
	Long getKonkaoaDocInfoNoMax(KonkaoaDocInfo t);
}