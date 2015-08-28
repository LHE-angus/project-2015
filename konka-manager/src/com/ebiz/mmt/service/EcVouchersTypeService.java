package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcVouchersType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
public interface EcVouchersTypeService {

	Long createEcVouchersType(EcVouchersType t);

	int modifyEcVouchersType(EcVouchersType t);

	int removeEcVouchersType(EcVouchersType t);

	EcVouchersType getEcVouchersType(EcVouchersType t);

	List<EcVouchersType> getEcVouchersTypeList(EcVouchersType t);

	Long getEcVouchersTypeCount(EcVouchersType t);

	List<EcVouchersType> getEcVouchersTypePaginatedList(EcVouchersType t);

}