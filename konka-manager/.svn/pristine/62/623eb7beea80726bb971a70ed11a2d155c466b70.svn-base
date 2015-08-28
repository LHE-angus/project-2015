package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcDispatchAreaDao;
import com.ebiz.mmt.domain.EcDispatchArea;
import com.ebiz.mmt.service.EcDispatchAreaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-15 16:23:35
 */
@Service
public class EcDispatchAreaServiceImpl implements EcDispatchAreaService {

	@Resource
	private EcDispatchAreaDao ecDispatchAreaDao;
	

	public Long createEcDispatchArea(EcDispatchArea t) {
		return this.ecDispatchAreaDao.insertEntity(t);
	}

	public EcDispatchArea getEcDispatchArea(EcDispatchArea t) {
		return this.ecDispatchAreaDao.selectEntity(t);
	}

	public Long getEcDispatchAreaCount(EcDispatchArea t) {
		return this.ecDispatchAreaDao.selectEntityCount(t);
	}

	public List<EcDispatchArea> getEcDispatchAreaList(EcDispatchArea t) {
		return this.ecDispatchAreaDao.selectEntityList(t);
	}

	public int modifyEcDispatchArea(EcDispatchArea t) {
		return this.ecDispatchAreaDao.updateEntity(t);
	}

	public int removeEcDispatchArea(EcDispatchArea t) {
		return this.ecDispatchAreaDao.deleteEntity(t);
	}

	public List<EcDispatchArea> getEcDispatchAreaPaginatedList(EcDispatchArea t) {
		return this.ecDispatchAreaDao.selectEntityPaginatedList(t);
	}

}
