package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaParagonEtcashDao;
import com.ebiz.mmt.domain.KonkaParagonEtcash;
import com.ebiz.mmt.service.KonkaParagonEtcashService;

@Service
public class KonkaParagonEtcashServiceImpl implements KonkaParagonEtcashService {

	@Resource
	private KonkaParagonEtcashDao konkaParagonEtcashDao;

	public Long createKonkaParagonEtcash(KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.insertEntity(t);
	}

	public KonkaParagonEtcash getKonkaParagonEtcash(KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.selectEntity(t);
	}

	public Long getKonkaParagonEtcashCount(KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.selectEntityCount(t);
	}

	public List<KonkaParagonEtcash> getKonkaParagonEtcashList(
			KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.selectEntityList(t);
	}

	public int modifyKonkaParagonEtcash(KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.updateEntity(t);
	}

	public int removeKonkaParagonEtcash(KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.deleteEntity(t);
	}

	public List<KonkaParagonEtcash> getKonkaParagonEtcashPaginatedList(
			KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.selectEntityPaginatedList(t);
	}

	public List<HashMap<String, String>> selectKonkaParagonEtcashListByOne(
			KonkaParagonEtcash t) {
		return this.konkaParagonEtcashDao.selectKonkaParagonEtcashListByOne(t);
	}

}
