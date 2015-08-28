package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcOrderExpressInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:43
 */
public interface EcOrderExpressInfoService {

	Long createEcOrderExpressInfo(EcOrderExpressInfo t);

	int modifyEcOrderExpressInfo(EcOrderExpressInfo t);

	int removeEcOrderExpressInfo(EcOrderExpressInfo t);

	EcOrderExpressInfo getEcOrderExpressInfo(EcOrderExpressInfo t);

	List<EcOrderExpressInfo> getEcOrderExpressInfoList(EcOrderExpressInfo t);

	Long getEcOrderExpressInfoCount(EcOrderExpressInfo t);

	List<EcOrderExpressInfo> getEcOrderExpressInfoPaginatedList(EcOrderExpressInfo t);

}