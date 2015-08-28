package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseCardType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public interface EcBaseCardTypeService {

	Long createEcBaseCardType(EcBaseCardType t);

	int modifyEcBaseCardType(EcBaseCardType t);

	int removeEcBaseCardType(EcBaseCardType t);

	EcBaseCardType getEcBaseCardType(EcBaseCardType t);

	List<EcBaseCardType> getEcBaseCardTypeList(EcBaseCardType t);

	Long getEcBaseCardTypeCount(EcBaseCardType t);

	List<EcBaseCardType> getEcBaseCardTypePaginatedList(EcBaseCardType t);

}