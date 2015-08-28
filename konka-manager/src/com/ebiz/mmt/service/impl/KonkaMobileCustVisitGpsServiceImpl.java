package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCustVisitGpsDao;
import com.ebiz.mmt.domain.KonkaMobileCustVisitGps;
import com.ebiz.mmt.service.KonkaMobileCustVisitGpsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-09-04 14:07:23
 */
@Service
public class KonkaMobileCustVisitGpsServiceImpl implements KonkaMobileCustVisitGpsService {

	@Resource
	private KonkaMobileCustVisitGpsDao konkaMobileCustVisitGpsDao;
	

	public Long createKonkaMobileCustVisitGps(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.insertEntity(t);
	}

	public KonkaMobileCustVisitGps getKonkaMobileCustVisitGps(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.selectEntity(t);
	}

	public Long getKonkaMobileCustVisitGpsCount(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.selectEntityCount(t);
	}

	public List<KonkaMobileCustVisitGps> getKonkaMobileCustVisitGpsList(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.selectEntityList(t);
	}

	public int modifyKonkaMobileCustVisitGps(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.updateEntity(t);
	}

	public int removeKonkaMobileCustVisitGps(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.deleteEntity(t);
	}

	public List<KonkaMobileCustVisitGps> getKonkaMobileCustVisitGpsPaginatedList(KonkaMobileCustVisitGps t) {
		return this.konkaMobileCustVisitGpsDao.selectEntityPaginatedList(t);
	}

}
