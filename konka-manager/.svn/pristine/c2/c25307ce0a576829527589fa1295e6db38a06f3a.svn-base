package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaStoreUserCInfoDao;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.service.KonkaStoreUserCInfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-26 17:09:19
 */
@Service
public class KonkaStoreUserCInfoServiceImpl implements KonkaStoreUserCInfoService {

	@Resource
	private KonkaStoreUserCInfoDao konkaStoreUserCInfoDao;
	

	public Long createKonkaStoreUserCInfo(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.insertEntity(t);
	}

	public KonkaStoreUserCInfo getKonkaStoreUserCInfo(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.selectEntity(t);
	}

	public Long getKonkaStoreUserCInfoCount(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.selectEntityCount(t);
	}

	public List<KonkaStoreUserCInfo> getKonkaStoreUserCInfoList(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.selectEntityList(t);
	}

	public int modifyKonkaStoreUserCInfo(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.updateEntity(t);
	}

	public int removeKonkaStoreUserCInfo(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.deleteEntity(t);
	}

	public List<KonkaStoreUserCInfo> getKonkaStoreUserCInfoPaginatedList(KonkaStoreUserCInfo t) {
		return this.konkaStoreUserCInfoDao.selectEntityPaginatedList(t);
	}

}
