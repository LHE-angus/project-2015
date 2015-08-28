package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcBcompTypeService {

	Long createEcBcompType(EcBcompType t);

	int modifyEcBcompType(EcBcompType t);

	int removeEcBcompType(EcBcompType t);

	EcBcompType getEcBcompType(EcBcompType t);

	List<EcBcompType> getEcBcompTypeList(EcBcompType t);

	Long getEcBcompTypeCount(EcBcompType t);

	List<EcBcompType> getEcBcompTypePaginatedList(EcBcompType t);

}