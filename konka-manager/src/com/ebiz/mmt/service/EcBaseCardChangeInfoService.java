package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseCardChangeInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public interface EcBaseCardChangeInfoService {

	Long createEcBaseCardChangeInfo(EcBaseCardChangeInfo t);

	int modifyEcBaseCardChangeInfo(EcBaseCardChangeInfo t);

	int removeEcBaseCardChangeInfo(EcBaseCardChangeInfo t);

	EcBaseCardChangeInfo getEcBaseCardChangeInfo(EcBaseCardChangeInfo t);

	List<EcBaseCardChangeInfo> getEcBaseCardChangeInfoList(EcBaseCardChangeInfo t);

	Long getEcBaseCardChangeInfoCount(EcBaseCardChangeInfo t);

	List<EcBaseCardChangeInfo> getEcBaseCardChangeInfoPaginatedList(EcBaseCardChangeInfo t);

}