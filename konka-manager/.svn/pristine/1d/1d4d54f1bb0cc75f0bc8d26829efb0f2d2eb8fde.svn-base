package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompPdUpDao;
import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.service.EcBcompPdUpService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-20 15:05:43
 */
@Service
public class EcBcompPdUpServiceImpl implements EcBcompPdUpService {

	@Resource
	private EcBcompPdUpDao ecBcompPdUpDao;
	

	public Long createEcBcompPdUp(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.insertEntity(t);
	}

	public EcBcompPdUp getEcBcompPdUp(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.selectEntity(t);
	}

	public Long getEcBcompPdUpCount(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.selectEntityCount(t);
	}

	public List<EcBcompPdUp> getEcBcompPdUpList(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.selectEntityList(t);
	}

	public int modifyEcBcompPdUp(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.updateEntity(t);
	}

	public int removeEcBcompPdUp(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.deleteEntity(t);
	}

	public List<EcBcompPdUp> getEcBcompPdUpPaginatedList(EcBcompPdUp t) {
		return this.ecBcompPdUpDao.selectEntityPaginatedList(t);
	}

}
