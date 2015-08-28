package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileShopStoreDao;
import com.ebiz.mmt.domain.KonkaMobileShopStore;
import com.ebiz.mmt.service.KonkaMobileShopStoreService;

/**
 * @author Ren,zhong
 * @version 2013-04-18 16:37
 */
@Service
public class KonkaMobileShopStoreServiceImpl implements KonkaMobileShopStoreService {

	@Resource
	private KonkaMobileShopStoreDao konkaMobileShopStoreDao;
	

	public Long createKonkaMobileShopStore(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.insertEntity(t);
	}

	public KonkaMobileShopStore getKonkaMobileShopStore(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.selectEntity(t);
	}

	public Long getKonkaMobileShopStoreCount(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.selectEntityCount(t);
	}

	public List<KonkaMobileShopStore> getKonkaMobileShopStoreList(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.selectEntityList(t);
	}

	public int modifyKonkaMobileShopStore(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.updateEntity(t);
	}

	public int removeKonkaMobileShopStore(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.deleteEntity(t);
	}

	public List<KonkaMobileShopStore> getKonkaMobileShopStorePaginatedList(KonkaMobileShopStore t) {
		return this.konkaMobileShopStoreDao.selectEntityPaginatedList(t);
	}

}
