package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaCategoryType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-07-12 16:57:52
 */
public interface KonkaCategoryTypeService {

	Long createKonkaCategoryType(KonkaCategoryType t);

	int modifyKonkaCategoryType(KonkaCategoryType t);

	int removeKonkaCategoryType(KonkaCategoryType t);

	KonkaCategoryType getKonkaCategoryType(KonkaCategoryType t);

	List<KonkaCategoryType> getKonkaCategoryTypeList(KonkaCategoryType t);

	Long getKonkaCategoryTypeCount(KonkaCategoryType t);

	List<KonkaCategoryType> getKonkaCategoryTypePaginatedList(KonkaCategoryType t);

}