package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcHomeFloor;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-26 14:40:50
 */
public interface EcHomeFloorService {

	Long createEcHomeFloor(EcHomeFloor t);

	int modifyEcHomeFloor(EcHomeFloor t);

	int removeEcHomeFloor(EcHomeFloor t);

	EcHomeFloor getEcHomeFloor(EcHomeFloor t);

	List<EcHomeFloor> getEcHomeFloorList(EcHomeFloor t);

	Long getEcHomeFloorCount(EcHomeFloor t);

	List<EcHomeFloor> getEcHomeFloorPaginatedList(EcHomeFloor t);

}