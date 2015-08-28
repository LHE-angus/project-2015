package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcOutWarehouseDao;
import com.ebiz.mmt.domain.EcOutWarehouse;
import com.ebiz.mmt.service.EcOutWarehouseService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcOutWarehouseServiceImpl implements EcOutWarehouseService {

	@Resource
	private EcOutWarehouseDao ecOutWarehouseDao;
	

	public Long createEcOutWarehouse(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.insertEntity(t);
	}

	public EcOutWarehouse getEcOutWarehouse(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.selectEntity(t);
	}

	public Long getEcOutWarehouseCount(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.selectEntityCount(t);
	}

	public List<EcOutWarehouse> getEcOutWarehouseList(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.selectEntityList(t);
	}

	public int modifyEcOutWarehouse(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.updateEntity(t);
	}

	public int removeEcOutWarehouse(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.deleteEntity(t);
	}

	public List<EcOutWarehouse> getEcOutWarehousePaginatedList(EcOutWarehouse t) {
		return this.ecOutWarehouseDao.selectEntityPaginatedList(t);
	}

}
