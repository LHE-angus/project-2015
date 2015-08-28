package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcLogoInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-11 15:10:42
 */
public interface EcLogoInfoService {

	Long createEcLogoInfo(EcLogoInfo t);

	int modifyEcLogoInfo(EcLogoInfo t);

	int removeEcLogoInfo(EcLogoInfo t);

	EcLogoInfo getEcLogoInfo(EcLogoInfo t);

	List<EcLogoInfo> getEcLogoInfoList(EcLogoInfo t);

	Long getEcLogoInfoCount(EcLogoInfo t);

	List<EcLogoInfo> getEcLogoInfoPaginatedList(EcLogoInfo t);

}