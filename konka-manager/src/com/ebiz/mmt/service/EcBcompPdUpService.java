package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompPdUp;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-20 15:05:43
 */
public interface EcBcompPdUpService {

	Long createEcBcompPdUp(EcBcompPdUp t);

	int modifyEcBcompPdUp(EcBcompPdUp t);

	int removeEcBcompPdUp(EcBcompPdUp t);

	EcBcompPdUp getEcBcompPdUp(EcBcompPdUp t);

	List<EcBcompPdUp> getEcBcompPdUpList(EcBcompPdUp t);

	Long getEcBcompPdUpCount(EcBcompPdUp t);

	List<EcBcompPdUp> getEcBcompPdUpPaginatedList(EcBcompPdUp t);

}