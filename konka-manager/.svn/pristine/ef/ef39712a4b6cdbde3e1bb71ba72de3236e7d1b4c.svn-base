package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompPdUpNewDao;
import com.ebiz.mmt.domain.EcBcompPdUpNew;
import com.ebiz.mmt.service.EcBcompPdUpNewService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-05 12:05:18
 */
@Service
public class EcBcompPdUpNewServiceImpl implements EcBcompPdUpNewService {

	@Resource
	private EcBcompPdUpNewDao ecBcompPdUpNewDao;

	public Long createEcBcompPdUpNew(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.insertEntity(t);
	}

	public EcBcompPdUpNew getEcBcompPdUpNew(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEntity(t);
	}

	public Long getEcBcompPdUpNewCount(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEntityCount(t);
	}

	public List<EcBcompPdUpNew> getEcBcompPdUpNewList(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEntityList(t);
	}

	public int modifyEcBcompPdUpNew(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.updateEntity(t);
	}

	public int removeEcBcompPdUpNew(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.deleteEntity(t);
	}

	public List<EcBcompPdUpNew> getEcBcompPdUpNewPaginatedList(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getEcBcompPdUpNewForDetailsCount(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEcBcompPdUpNewForDetailsCount(t);
	}

	@Override
	public List<EcBcompPdUpNew> getEcBcompPdUpNewForDetailsPaginatedList(EcBcompPdUpNew t) {
		return this.ecBcompPdUpNewDao.selectEcBcompPdUpNewForDetailsPaginatedList(t);
	}

}
