package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcInWarehouse;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcInWarehouseService {

	Long createEcInWarehouse(EcInWarehouse t);

	int modifyEcInWarehouse(EcInWarehouse t);

	int removeEcInWarehouse(EcInWarehouse t);

	EcInWarehouse getEcInWarehouse(EcInWarehouse t);

	List<EcInWarehouse> getEcInWarehouseList(EcInWarehouse t);

	Long getEcInWarehouseCount(EcInWarehouse t);

	List<EcInWarehouse> getEcInWarehousePaginatedList(EcInWarehouse t);

}