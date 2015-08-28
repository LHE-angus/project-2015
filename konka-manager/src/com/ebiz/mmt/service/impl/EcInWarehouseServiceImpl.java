package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcInWarehouseDao;
import com.ebiz.mmt.domain.EcInWarehouse;
import com.ebiz.mmt.service.EcInWarehouseService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcInWarehouseServiceImpl implements EcInWarehouseService {

	@Resource
	private EcInWarehouseDao ecInWarehouseDao;
	

	public Long createEcInWarehouse(EcInWarehouse t) {
		return this.ecInWarehouseDao.insertEntity(t);
	}

	public EcInWarehouse getEcInWarehouse(EcInWarehouse t) {
		return this.ecInWarehouseDao.selectEntity(t);
	}

	public Long getEcInWarehouseCount(EcInWarehouse t) {
		return this.ecInWarehouseDao.selectEntityCount(t);
	}

	public List<EcInWarehouse> getEcInWarehouseList(EcInWarehouse t) {
		return this.ecInWarehouseDao.selectEntityList(t);
	}

	public int modifyEcInWarehouse(EcInWarehouse t) {
		return this.ecInWarehouseDao.updateEntity(t);
	}

	public int removeEcInWarehouse(EcInWarehouse t) {
		return this.ecInWarehouseDao.deleteEntity(t);
	}

	public List<EcInWarehouse> getEcInWarehousePaginatedList(EcInWarehouse t) {
		return this.ecInWarehouseDao.selectEntityPaginatedList(t);
	}

}
