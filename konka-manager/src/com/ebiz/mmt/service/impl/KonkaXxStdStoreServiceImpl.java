package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxStdStoreDao;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.service.KonkaXxStdStoreService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxStdStoreServiceImpl implements KonkaXxStdStoreService {

	@Resource
	private KonkaXxStdStoreDao konkaXxStdStoreDao;

	public Long createKonkaXxStdStore(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.insertEntity(t);
	}

	public KonkaXxStdStore getKonkaXxStdStore(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectEntity(t);
	}

	public Long getKonkaXxStdStoreCount(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectEntityCount(t);
	}

	public List<KonkaXxStdStore> getKonkaXxStdStoreList(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectEntityList(t);
	}

	public int modifyKonkaXxStdStore(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.updateEntity(t);
	}

	public int removeKonkaXxStdStore(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.deleteEntity(t);
	}

	public List<KonkaXxStdStore> getKonkaXxStdStorePaginatedList(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	public List<KonkaXxStdStore> getKonkaXxFactoryIdList(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectKonkaXxFactoryIdList(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	public List<KonkaXxStdStore> getKonkaXxStoreIdList(KonkaXxStdStore t) {
		return this.konkaXxStdStoreDao.selectKonkaXxStoreIdList(t);
	}
}
