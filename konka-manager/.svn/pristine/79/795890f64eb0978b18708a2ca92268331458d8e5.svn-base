package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaJxcStoreInfoDao;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.service.KonkaJxcStoreInfoService;

/**
 * @author Wu,Yang
 * @version 2011-10-11 09:18
 */
@Service
public class KonkaJxcStoreInfoServiceImpl implements KonkaJxcStoreInfoService {

	@Resource
	private KonkaJxcStoreInfoDao konkaJxcStoreInfoDao;
	

	public Long createKonkaJxcStoreInfo(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.insertEntity(t);
	}

	public KonkaJxcStoreInfo getKonkaJxcStoreInfo(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.selectEntity(t);
	}

	public Long getKonkaJxcStoreInfoCount(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.selectEntityCount(t);
	}

	public List<KonkaJxcStoreInfo> getKonkaJxcStoreInfoList(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.selectEntityList(t);
	}

	public int modifyKonkaJxcStoreInfo(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.updateEntity(t);
	}

	public int removeKonkaJxcStoreInfo(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.deleteEntity(t);
	}

	public List<KonkaJxcStoreInfo> getKonkaJxcStoreInfoPaginatedList(KonkaJxcStoreInfo t) {
		return this.konkaJxcStoreInfoDao.selectEntityPaginatedList(t);
	}

}
