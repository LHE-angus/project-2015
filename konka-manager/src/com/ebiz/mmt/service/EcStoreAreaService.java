package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcStoreArea;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcStoreAreaService {

	Long createEcStoreArea(EcStoreArea t);

	int modifyEcStoreArea(EcStoreArea t);

	int removeEcStoreArea(EcStoreArea t);

	EcStoreArea getEcStoreArea(EcStoreArea t);

	List<EcStoreArea> getEcStoreAreaList(EcStoreArea t);

	Long getEcStoreAreaCount(EcStoreArea t);

	List<EcStoreArea> getEcStoreAreaPaginatedList(EcStoreArea t);

}