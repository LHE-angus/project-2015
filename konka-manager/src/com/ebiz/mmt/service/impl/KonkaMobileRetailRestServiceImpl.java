package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileRetailRestDao;
import com.ebiz.mmt.domain.KonkaMobileRetailRest;
import com.ebiz.mmt.service.KonkaMobileRetailRestService;


@Service
public class KonkaMobileRetailRestServiceImpl implements KonkaMobileRetailRestService {

	@Resource
	private KonkaMobileRetailRestDao konkaMobileRetailRestDao;

	public Long createKonkaMobileRetailRest(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.insertEntity(t);
	}

	public KonkaMobileRetailRest getKonkaMobileRetailRest(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.selectEntity(t);
	}

	public Long getKonkaMobileRetailRestCount(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.selectEntityCount(t);
	}

	public List<KonkaMobileRetailRest> getKonkaMobileRetailRestList(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.selectEntityList(t);
	}

	public int modifyKonkaMobileRetailRest(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.updateEntity(t);
	}

	public int removeKonkaMobileRetailRest(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.deleteEntity(t);
	}

	public List<KonkaMobileRetailRest> getKonkaMobileRetailRestPaginatedList(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.selectEntityPaginatedList(t);
	}

	public Long getRetailRestStatisticsCount(KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.getRetailRestStatisticsCount(t);
	}

	public List<KonkaMobileRetailRest> getRetailRestStatisticsPaginatedList(
			KonkaMobileRetailRest t) {
		return this.konkaMobileRetailRestDao.getRetailRestStatisticsPaginatedList(t);
	}

}
