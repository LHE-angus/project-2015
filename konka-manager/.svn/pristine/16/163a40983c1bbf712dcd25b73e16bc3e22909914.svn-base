package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcHomeFloorDao;
import com.ebiz.mmt.domain.EcHomeFloor;
import com.ebiz.mmt.service.EcHomeFloorService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-26 14:40:50
 */
@Service
public class EcHomeFloorServiceImpl implements EcHomeFloorService {

	@Resource
	private EcHomeFloorDao ecHomeFloorDao;
	

	public Long createEcHomeFloor(EcHomeFloor t) {
		return this.ecHomeFloorDao.insertEntity(t);
	}

	public EcHomeFloor getEcHomeFloor(EcHomeFloor t) {
		return this.ecHomeFloorDao.selectEntity(t);
	}

	public Long getEcHomeFloorCount(EcHomeFloor t) {
		return this.ecHomeFloorDao.selectEntityCount(t);
	}

	public List<EcHomeFloor> getEcHomeFloorList(EcHomeFloor t) {
		return this.ecHomeFloorDao.selectEntityList(t);
	}

	public int modifyEcHomeFloor(EcHomeFloor t) {
		return this.ecHomeFloorDao.updateEntity(t);
	}

	public int removeEcHomeFloor(EcHomeFloor t) {
		return this.ecHomeFloorDao.deleteEntity(t);
	}

	public List<EcHomeFloor> getEcHomeFloorPaginatedList(EcHomeFloor t) {
		return this.ecHomeFloorDao.selectEntityPaginatedList(t);
	}

}
