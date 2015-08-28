package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaAgentsShopRelDao;
import com.ebiz.mmt.domain.KonkaAgentsShopRel;
import com.ebiz.mmt.service.KonkaAgentsShopRelService;


@Service
public class KonkaAgentsShopRelServiceImpl implements KonkaAgentsShopRelService {

	@Resource
	private KonkaAgentsShopRelDao konkaAgentsShopRelDao;

	public Long createKonkaAgentsShopRel(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.insertEntity(t);
	}

	public KonkaAgentsShopRel getKonkaAgentsShopRel(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.selectEntity(t);
	}

	public Long getKonkaAgentsShopRelCount(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.selectEntityCount(t);
	}

	public List<KonkaAgentsShopRel> getKonkaAgentsShopRelList(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.selectEntityList(t);
	}

	public int modifyKonkaAgentsShopRel(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.updateEntity(t);
	}

	public int removeKonkaAgentsShopRel(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.deleteEntity(t);
	}

	public List<KonkaAgentsShopRel> getKonkaAgentsShopRelPaginatedList(KonkaAgentsShopRel t) {
		return this.konkaAgentsShopRelDao.selectEntityPaginatedList(t);
	}

}
