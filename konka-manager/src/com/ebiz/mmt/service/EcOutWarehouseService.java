package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcOutWarehouse;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
public interface EcOutWarehouseService {

	Long createEcOutWarehouse(EcOutWarehouse t);

	int modifyEcOutWarehouse(EcOutWarehouse t);

	int removeEcOutWarehouse(EcOutWarehouse t);

	EcOutWarehouse getEcOutWarehouse(EcOutWarehouse t);

	List<EcOutWarehouse> getEcOutWarehouseList(EcOutWarehouse t);

	Long getEcOutWarehouseCount(EcOutWarehouse t);

	List<EcOutWarehouse> getEcOutWarehousePaginatedList(EcOutWarehouse t);

}