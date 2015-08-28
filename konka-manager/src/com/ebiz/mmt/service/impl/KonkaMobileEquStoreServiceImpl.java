package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileEquStoreDao;
import com.ebiz.mmt.domain.KonkaMobileEquStore;
import com.ebiz.mmt.service.KonkaMobileEquStoreService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-22 15:41:58
 */
@Service
public class KonkaMobileEquStoreServiceImpl implements KonkaMobileEquStoreService {

	@Resource
	private KonkaMobileEquStoreDao konkaMobileEquStoreDao;
	

	public Long createKonkaMobileEquStore(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.insertEntity(t);
	}

	public KonkaMobileEquStore getKonkaMobileEquStore(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.selectEntity(t);
	}

	public Long getKonkaMobileEquStoreCount(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.selectEntityCount(t);
	}

	public List<KonkaMobileEquStore> getKonkaMobileEquStoreList(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.selectEntityList(t);
	}

	public int modifyKonkaMobileEquStore(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.updateEntity(t);
	}

	public int removeKonkaMobileEquStore(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.deleteEntity(t);
	}

	public List<KonkaMobileEquStore> getKonkaMobileEquStorePaginatedList(KonkaMobileEquStore t) {
		return this.konkaMobileEquStoreDao.selectEntityPaginatedList(t);
	}

}
