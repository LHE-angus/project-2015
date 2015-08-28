package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaEntpShopDao;
import com.ebiz.mmt.domain.KonkaEntpShop;
import com.ebiz.mmt.service.KonkaEntpShopService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-04-18 17:54:20
 */
@Service
public class KonkaEntpShopServiceImpl implements KonkaEntpShopService {

	@Resource
	private KonkaEntpShopDao konkaEntpShopDao;
	

	public Long createKonkaEntpShop(KonkaEntpShop t) {
		return this.konkaEntpShopDao.insertEntity(t);
	}

	public KonkaEntpShop getKonkaEntpShop(KonkaEntpShop t) {
		return this.konkaEntpShopDao.selectEntity(t);
	}

	public Long getKonkaEntpShopCount(KonkaEntpShop t) {
		return this.konkaEntpShopDao.selectEntityCount(t);
	}

	public List<KonkaEntpShop> getKonkaEntpShopList(KonkaEntpShop t) {
		return this.konkaEntpShopDao.selectEntityList(t);
	}

	public int modifyKonkaEntpShop(KonkaEntpShop t) {
		return this.konkaEntpShopDao.updateEntity(t);
	}

	public int removeKonkaEntpShop(KonkaEntpShop t) {
		return this.konkaEntpShopDao.deleteEntity(t);
	}

	public List<KonkaEntpShop> getKonkaEntpShopPaginatedList(KonkaEntpShop t) {
		return this.konkaEntpShopDao.selectEntityPaginatedList(t);
	}

}
