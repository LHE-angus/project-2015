package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileStoragesDao;
import com.ebiz.mmt.domain.KonkaMobileStorages;
import com.ebiz.mmt.service.KonkaMobileStoragesService;


@Service
public class KonkaMobileStoragesServiceImpl implements KonkaMobileStoragesService {

	@Resource
	private KonkaMobileStoragesDao konkaMobileStoragesDao;

	public Long createKonkaMobileStorages(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.insertEntity(t);
	}

	public KonkaMobileStorages getKonkaMobileStorages(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.selectEntity(t);
	}

	public Long getKonkaMobileStoragesCount(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.selectEntityCount(t);
	}

	public List<KonkaMobileStorages> getKonkaMobileStoragesList(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.selectEntityList(t);
	}

	public int modifyKonkaMobileStorages(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.updateEntity(t);
	}

	public int removeKonkaMobileStorages(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.deleteEntity(t);
	}

	public List<KonkaMobileStorages> getKonkaMobileStoragesPaginatedList(KonkaMobileStorages t) {
		return this.konkaMobileStoragesDao.selectEntityPaginatedList(t);
	}

}
