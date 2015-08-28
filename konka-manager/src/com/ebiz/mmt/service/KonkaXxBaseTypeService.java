package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxBaseType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public interface KonkaXxBaseTypeService {

	Long createKonkaXxBaseType(KonkaXxBaseType t);

	int modifyKonkaXxBaseType(KonkaXxBaseType t);

	int removeKonkaXxBaseType(KonkaXxBaseType t);

	KonkaXxBaseType getKonkaXxBaseType(KonkaXxBaseType t);

	List<KonkaXxBaseType> getKonkaXxBaseTypeList(KonkaXxBaseType t);

	Long getKonkaXxBaseTypeCount(KonkaXxBaseType t);

	List<KonkaXxBaseType> getKonkaXxBaseTypePaginatedList(KonkaXxBaseType t);

}