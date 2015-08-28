package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompPdUpNew;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-05 12:05:18
 */
public interface EcBcompPdUpNewService {

	Long createEcBcompPdUpNew(EcBcompPdUpNew t);

	int modifyEcBcompPdUpNew(EcBcompPdUpNew t);

	int removeEcBcompPdUpNew(EcBcompPdUpNew t);

	EcBcompPdUpNew getEcBcompPdUpNew(EcBcompPdUpNew t);

	List<EcBcompPdUpNew> getEcBcompPdUpNewList(EcBcompPdUpNew t);

	Long getEcBcompPdUpNewCount(EcBcompPdUpNew t);

	List<EcBcompPdUpNew> getEcBcompPdUpNewPaginatedList(EcBcompPdUpNew t);

	Long getEcBcompPdUpNewForDetailsCount(EcBcompPdUpNew t);

	List<EcBcompPdUpNew> getEcBcompPdUpNewForDetailsPaginatedList(EcBcompPdUpNew t);

}