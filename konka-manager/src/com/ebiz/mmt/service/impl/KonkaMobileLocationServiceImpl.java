package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileLocationDao;
import com.ebiz.mmt.domain.KonkaMobileLocation;
import com.ebiz.mmt.service.KonkaMobileLocationService;

/**
 * Code by the Code Generator(Version 2.2)
 *  
 * @author Hui,Gang (mr.huig [at] gmail.com)
 * @datetime 2012-05-15 11:41:41
 */
 @Service
public class KonkaMobileLocationServiceImpl implements KonkaMobileLocationService {
	
	@Resource
	private KonkaMobileLocationDao konkaMobileLocationDao;

	public Long createKonkaMobileLocation(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.insertEntity(t);
	}

	public KonkaMobileLocation getKonkaMobileLocation(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.selectEntity(t);
	}

	public Long getKonkaMobileLocationCount(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.selectEntityCount(t);
	}

	public List<KonkaMobileLocation> getKonkaMobileLocationList(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.selectEntityList(t);
	}

	public int modifyKonkaMobileLocation(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.updateEntity(t);
	}

	public int removeKonkaMobileLocation(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.deleteEntity(t);
	}

	public List<KonkaMobileLocation> getKonkaMobileLocationPaginatedList(KonkaMobileLocation t) {
		return this.konkaMobileLocationDao.selectEntityPaginatedList(t);
	}	

}