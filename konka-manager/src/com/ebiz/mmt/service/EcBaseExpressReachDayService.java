package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseExpressReachDay;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-23 19:18:37
 */
public interface EcBaseExpressReachDayService {

	Long createEcBaseExpressReachDay(EcBaseExpressReachDay t);

	int modifyEcBaseExpressReachDay(EcBaseExpressReachDay t);

	int removeEcBaseExpressReachDay(EcBaseExpressReachDay t);

	EcBaseExpressReachDay getEcBaseExpressReachDay(EcBaseExpressReachDay t);

	List<EcBaseExpressReachDay> getEcBaseExpressReachDayList(EcBaseExpressReachDay t);

	Long getEcBaseExpressReachDayCount(EcBaseExpressReachDay t);

	List<EcBaseExpressReachDay> getEcBaseExpressReachDayPaginatedList(EcBaseExpressReachDay t);

	Long getEcBaseExpressReachDayForStoreNameAndFullNameCount(EcBaseExpressReachDay t);

	List<EcBaseExpressReachDay> getEcBaseExpressReachDayForStoreNameAndFullNamePaginatedList(EcBaseExpressReachDay t);
}