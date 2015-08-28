package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileThingsBaseDao;
import com.ebiz.mmt.domain.KonkaMobileThingsBase;
import com.ebiz.mmt.service.KonkaMobileThingsBaseService;


@Service
public class KonkaMobileThingsBaseServiceImpl implements KonkaMobileThingsBaseService {

	@Resource
	private KonkaMobileThingsBaseDao konkaMobileThingsBaseDao;

	public Long createKonkaMobileThingsBase(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.insertEntity(t);
	}

	public KonkaMobileThingsBase getKonkaMobileThingsBase(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.selectEntity(t);
	}

	public Long getKonkaMobileThingsBaseCount(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.selectEntityCount(t);
	}

	public List<KonkaMobileThingsBase> getKonkaMobileThingsBaseList(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.selectEntityList(t);
	}

	public int modifyKonkaMobileThingsBase(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.updateEntity(t);
	}

	public int removeKonkaMobileThingsBase(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.deleteEntity(t);
	}

	public List<KonkaMobileThingsBase> getKonkaMobileThingsBasePaginatedList(KonkaMobileThingsBase t) {
		return this.konkaMobileThingsBaseDao.selectEntityPaginatedList(t);
	}

}
