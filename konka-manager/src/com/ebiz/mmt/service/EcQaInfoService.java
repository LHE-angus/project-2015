package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcQaInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcQaInfoService {

	Long createEcQaInfo(EcQaInfo t);

	int modifyEcQaInfo(EcQaInfo t);

	int removeEcQaInfo(EcQaInfo t);

	EcQaInfo getEcQaInfo(EcQaInfo t);

	List<EcQaInfo> getEcQaInfoList(EcQaInfo t);

	Long getEcQaInfoCount(EcQaInfo t);

	List<EcQaInfo> getEcQaInfoPaginatedList(EcQaInfo t);

}