package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcHomeFloorDataDao;
import com.ebiz.mmt.domain.EcHomeFloorData;
import com.ebiz.mmt.service.EcHomeFloorDataService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-26 14:40:50
 */
@Service
public class EcHomeFloorDataServiceImpl implements EcHomeFloorDataService {

	@Resource
	private EcHomeFloorDataDao ecHomeFloorDataDao;
	

	public Long createEcHomeFloorData(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.insertEntity(t);
	}

	public EcHomeFloorData getEcHomeFloorData(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.selectEntity(t);
	}

	public Long getEcHomeFloorDataCount(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.selectEntityCount(t);
	}

	public List<EcHomeFloorData> getEcHomeFloorDataList(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.selectEntityList(t);
	}

	public int modifyEcHomeFloorData(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.updateEntity(t);
	}

	public int removeEcHomeFloorData(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.deleteEntity(t);
	}

	public List<EcHomeFloorData> getEcHomeFloorDataPaginatedList(EcHomeFloorData t) {
		return this.ecHomeFloorDataDao.selectEntityPaginatedList(t);
	}

}
