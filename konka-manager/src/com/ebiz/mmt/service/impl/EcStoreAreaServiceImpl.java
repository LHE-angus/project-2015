package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcStoreAreaDao;
import com.ebiz.mmt.domain.EcStoreArea;
import com.ebiz.mmt.service.EcStoreAreaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcStoreAreaServiceImpl implements EcStoreAreaService {

	@Resource
	private EcStoreAreaDao ecStoreAreaDao;
	

	public Long createEcStoreArea(EcStoreArea t) {
		return this.ecStoreAreaDao.insertEntity(t);
	}

	public EcStoreArea getEcStoreArea(EcStoreArea t) {
		return this.ecStoreAreaDao.selectEntity(t);
	}

	public Long getEcStoreAreaCount(EcStoreArea t) {
		return this.ecStoreAreaDao.selectEntityCount(t);
	}

	public List<EcStoreArea> getEcStoreAreaList(EcStoreArea t) {
		return this.ecStoreAreaDao.selectEntityList(t);
	}

	public int modifyEcStoreArea(EcStoreArea t) {
		return this.ecStoreAreaDao.updateEntity(t);
	}

	public int removeEcStoreArea(EcStoreArea t) {
		return this.ecStoreAreaDao.deleteEntity(t);
	}

	public List<EcStoreArea> getEcStoreAreaPaginatedList(EcStoreArea t) {
		return this.ecStoreAreaDao.selectEntityPaginatedList(t);
	}

}
