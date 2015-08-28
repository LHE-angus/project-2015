package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileImpSailData;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-08 14:56:22
 */
public interface KonkaMobileImpSailDataService {

	Long createKonkaMobileImpSailData(KonkaMobileImpSailData t);

	int modifyKonkaMobileImpSailData(KonkaMobileImpSailData t);

	int removeKonkaMobileImpSailData(KonkaMobileImpSailData t);

	KonkaMobileImpSailData getKonkaMobileImpSailData(KonkaMobileImpSailData t);

	List<KonkaMobileImpSailData> getKonkaMobileImpSailDataList(KonkaMobileImpSailData t);

	Long getKonkaMobileImpSailDataCount(KonkaMobileImpSailData t);

	List<KonkaMobileImpSailData> getKonkaMobileImpSailDataPaginatedList(KonkaMobileImpSailData t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-08
	 */
	Long createKonkaMobileImpSailDataToView(List<KonkaMobileImpSailData> t);

}