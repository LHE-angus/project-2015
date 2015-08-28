package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStoreQckcDao;
import com.ebiz.mmt.domain.KonkaJxcStoreQckc;
import com.ebiz.mmt.service.KonkaJxcStoreQckcService;

/**
 * @author Wu,Yang
 * @version 2011-11-07 14:09
 */
@Service
public class KonkaJxcStoreQckcServiceImpl implements KonkaJxcStoreQckcService {

	@Resource
	private KonkaJxcStoreQckcDao konkaJxcStoreQckcDao;
	

	public Long createKonkaJxcStoreQckc(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.insertEntity(t);
	}

	public KonkaJxcStoreQckc getKonkaJxcStoreQckc(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.selectEntity(t);
	}

	public Long getKonkaJxcStoreQckcCount(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.selectEntityCount(t);
	}

	public List<KonkaJxcStoreQckc> getKonkaJxcStoreQckcList(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStoreQckc(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.updateEntity(t);
	}

	public int removeKonkaJxcStoreQckc(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.deleteEntity(t);
	}

	public List<KonkaJxcStoreQckc> getKonkaJxcStoreQckcPaginatedList(KonkaJxcStoreQckc t) {
		return this.konkaJxcStoreQckcDao.selectEntityPaginatedList(t);
	}

}
