package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdStoreDao;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.service.KonkaXxZmdStoreService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-01-11 10:56:03
 */
@Service
public class KonkaXxZmdStoreServiceImpl implements KonkaXxZmdStoreService {

	@Resource
	private KonkaXxZmdStoreDao konkaXxZmdStoreDao;
	

	public Long createKonkaXxZmdStore(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.insertEntity(t);
	}

	public KonkaXxZmdStore getKonkaXxZmdStore(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.selectEntity(t);
	}

	public Long getKonkaXxZmdStoreCount(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdStore> getKonkaXxZmdStoreList(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdStore(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.updateEntity(t);
	}

	public int removeKonkaXxZmdStore(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.deleteEntity(t);
	}

	public List<KonkaXxZmdStore> getKonkaXxZmdStorePaginatedList(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.selectEntityPaginatedList(t);
	}
	
	/**
	 * @author Ren,zhong
	 * @version 2011-01-11
	 */
	public List<KonkaXxZmdStore> getSalesOrderDeliveryForStocksResultList(KonkaXxZmdStore t) {
		return this.konkaXxZmdStoreDao.selectSalesOrderDeliveryForStocksResultList(t);
	}

}
