package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcSenderInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-03 16:39:53
 */
public interface EcSenderInfoService {

	Long createEcSenderInfo(EcSenderInfo t);

	int modifyEcSenderInfo(EcSenderInfo t);

	int removeEcSenderInfo(EcSenderInfo t);

	EcSenderInfo getEcSenderInfo(EcSenderInfo t);

	List<EcSenderInfo> getEcSenderInfoList(EcSenderInfo t);

	Long getEcSenderInfoCount(EcSenderInfo t);

	List<EcSenderInfo> getEcSenderInfoPaginatedList(EcSenderInfo t);

}